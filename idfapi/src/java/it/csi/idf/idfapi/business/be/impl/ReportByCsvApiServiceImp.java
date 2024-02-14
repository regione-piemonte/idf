/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.SecurityContext;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.supercsv.io.CsvListReader;
import org.supercsv.prefs.CsvPreference;

import it.csi.idf.idfapi.business.be.ReportByCsvApi;
import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.business.be.impl.dao.ReportByCsvDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SpecieDAO;
import it.csi.idf.idfapi.dto.CampoReport;
import it.csi.idf.idfapi.dto.CsvReport;
import it.csi.idf.idfapi.dto.TSpecie;
import it.csi.idf.idfapi.util.IplaEnum;
import it.csi.idf.idfapi.util.SpringSupportedResource;
import it.csi.idf.idfapi.util.TipoAdsEnum;
import it.csi.idf.idfapi.util.report.ReportCsvFieldsEnum;
import it.csi.idf.idfapi.util.report.ReportUtil;
import it.csi.idf.idfapi.util.report.ReportUtilBasimetria;
import it.csi.idf.idfapi.util.report.ReportUtilDiametri;
import it.csi.idf.idfapi.util.report.ReportUtilIncrementi;
import it.csi.idf.idfapi.util.report.ReportUtilIpsometria;
import it.csi.idf.idfapi.util.report.ReportUtilVolumi;

public class ReportByCsvApiServiceImp extends SpringSupportedResource implements ReportByCsvApi {
	
	static final Logger logger = Logger.getLogger(ReportByCsvApiServiceImp.class);
	
	@Autowired
	private ReportByCsvDAO csvReport;
	
	@Autowired
	private SpecieDAO specieDAO;

	@Override
	public Response getReportData(String adsType,int idReport,MultipartFormDataInput form, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		logger.info("idReport: " + idReport);
		fillSpecieMap();
		try {
			String fieldSeparator = ",";
			String filterSpecie = httpRequest.getParameter("filterSpecie");
			String filterTipoForestale =httpRequest.getParameter("filterTipoForestale");
			String filterCatForestale = httpRequest.getParameter("filterCatForestale");
			String filterDiametroMin = httpRequest.getParameter("filterDiametroMin");
			String filterDiametroMax = httpRequest.getParameter("filterDiametroMax");
			Map<String, List<InputPart>> uploadForm = form.getFormDataMap();
			logger.info("uploadForm size: " + uploadForm.get("form").size());
			logger.info("Filters -> filterSpecie: " + filterSpecie + " - filterTipoForestale: " + filterTipoForestale + 
					" - filterCatForestale: " + filterCatForestale);
			
			InputPart inputPart = uploadForm.get("form").get(0);
			
			String fileNameWithExtension = getFileNameWithExtension(inputPart.getHeaders());
			if(!fileNameWithExtension.toUpperCase().endsWith(".CSV")) {
				return Response.ok("Error. Il file deve essere in formato CSV").build();
			}
			
			logMemory();			
			byte[] content = inputPart.getBodyAsString().getBytes(StandardCharsets.UTF_8);		
			logMemory();
            List<CampoReport> campiRepotList = csvReport.getReportDetail(idReport);
            
            logger.info("campiRepotList is null" + (campiRepotList == null));
            
            Map<String,Integer> mapFields = getMapFields(fieldSeparator, content, campiRepotList);
            
            logger.info("mapFields is null" + (mapFields == null));
            
            String[][] csvValues = getCsvVaues(content, campiRepotList,mapFields, adsType);
            
            if(csvValues.length < 2) {
            	throw new ValidationException("Nessun record trovato per il tipo area di saggio selezionato!");
            }
            
            switch(idReport) {
            case 1: 
            	return Response.ok(ReportUtilBasimetria.calcoloBasimetria(mapFields, csvValues, TipoAdsEnum.SUPERFICIE_NOTA)).build();
            case 2:
            	return Response.ok(ReportUtilIpsometria.calcoloIpsometria(mapFields, csvValues, TipoAdsEnum.SUPERFICIE_NOTA,filterSpecie,filterTipoForestale,filterCatForestale)).build();
            case 3:
            	return Response.ok(ReportUtilDiametri.calcoloDiametri(mapFields, csvValues, TipoAdsEnum.SUPERFICIE_NOTA)).build();
            case 4:
            	return Response.ok(ReportUtilVolumi.calcoloVolumi(mapFields, csvValues, TipoAdsEnum.SUPERFICIE_NOTA)).build();
            case 5:
            	return Response.ok(ReportUtilIncrementi.calcoloIncrementi(mapFields, csvValues, TipoAdsEnum.SUPERFICIE_NOTA,filterSpecie,
            			filterDiametroMin==null?null:Double.parseDouble(filterDiametroMin),
            					filterDiametroMax==null?null:Double.parseDouble(filterDiametroMax))).build();
            case 6: 
            	return Response.ok(ReportUtilBasimetria.calcoloBasimetria(mapFields, csvValues, TipoAdsEnum.RELASCOPICA_SEMPLICE)).build();
            case 7: 
            	return Response.ok(ReportUtilBasimetria.calcoloBasimetria(mapFields, csvValues, TipoAdsEnum.RELASCOPICA_COMPLETA)).build();
            case 8:
            	return Response.ok(ReportUtilIpsometria.calcoloIpsometria(mapFields, csvValues, TipoAdsEnum.RELASCOPICA_COMPLETA,filterSpecie,filterTipoForestale,filterCatForestale)).build();
            case 9:
            	return Response.ok(ReportUtilDiametri.calcoloDiametri(mapFields, csvValues, TipoAdsEnum.RELASCOPICA_COMPLETA)).build();
            case 10:
            	return Response.ok(ReportUtilVolumi.calcoloVolumi(mapFields, csvValues, TipoAdsEnum.RELASCOPICA_COMPLETA)).build();
            case 11:
            	return Response.ok(ReportUtilIncrementi.calcoloIncrementi(mapFields, csvValues, TipoAdsEnum.RELASCOPICA_COMPLETA,filterSpecie,
            			filterDiametroMin==null?null:Double.parseDouble(filterDiametroMin),
            					filterDiametroMax==null?null:Double.parseDouble(filterDiametroMax))).build();
            default:throw new ValidationException("Report richiesto non gestito.");
            	
            }
		}catch(ValidationException ex) {
        	return Response.ok(ex.getTesto()).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	private void logMemory() {
		Runtime rt = Runtime.getRuntime();
		Long totalMemory = rt.totalMemory()/1024;
		Long freeMemory = rt.freeMemory()/1024;
		logger.info("Total memory: " +  totalMemory	+ " - free memory: " + freeMemory 
				+" - usage memory: " + (totalMemory - freeMemory));

	}
	
	@Override
	public Response getReportsDetail(Integer reportId,SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		
		return Response.ok(csvReport.getReportDetail(reportId)).build();
	}
	
	
	
	@Override
	public Response getReportsListByAdsType(int idAdsType, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		
		logger.info("idAdsType: " + idAdsType);
		
		return Response.ok(csvReport.getReportList(idAdsType)).build();
	}
	
	private String getFileNameWithExtension(MultivaluedMap<String, String> header) {

		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
		
		//test
		Set<String> keys = header.keySet();
		for (String key : keys) {
			
			logger.info("key: " + key + " -> " + header.get(key));
		}
		
		//end test

		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {

				String[] name = filename.split("=");

				return name[1].trim().replaceAll("\"", "");
			}
		}
		return "unknown";
	}
	
	int getPositionStringInArray(String s,String[]array) {
		for(int i=0;i<array.length;i++) {
			if(array[i].indexOf(s)>-1) {
				return i;
			}
		}
		return -1;
	}
	
	private void fillSpecieMap() {
		if(ReportUtil.getMapSpecie() == null) {
			List<TSpecie> listSpecie = specieDAO.findAllSpecieByIpla(IplaEnum.INVENTARI);
			Map<String, String> mapSpecie = new HashMap<String,String>();
			for(TSpecie item : listSpecie) {
				mapSpecie.put(item.getCodice(), item.getVolgare());
			}
			ReportUtil.setMapSpecie(mapSpecie);
		}
	}
	
	private Map<String,Integer> getMapFields(String fieldSeparator, byte[] content, List<CampoReport> campiRepotList) throws Exception{
		InputStream is = null;
		BufferedReader bfReader = null;
        try {
	        is = new ByteArrayInputStream(content);
	        bfReader = new BufferedReader(new InputStreamReader(is));
	        String row = null;
	        String[] csvItems = null;
	        Map<String,Integer> mapFields = new HashMap<String,Integer>();
	        if((row = bfReader.readLine()) != null){
	        	csvItems = row.toUpperCase().split(fieldSeparator);
            	logger.info(csvItems.length + " - " + row);    		
            	int idx;
            	for(int i = 0;i<campiRepotList.size();i++) {
            		idx = ArrayUtils.indexOf(csvItems, campiRepotList.get(i).getDenominazione().toUpperCase());
            		if(idx == -1) {
            			idx = getPositionStringInArray(campiRepotList.get(i).getDenominazione().toUpperCase(),csvItems);
            		}
            		if(idx == -1) {
            			throw new ValidationException("Campo \"" + campiRepotList.get(i).getDenominazione() + "\" non trovato!");
            		}else {
            			mapFields.put(campiRepotList.get(i).getDenominazione(), idx);
            			logger.info("Map: " + campiRepotList.get(i).getDenominazione() + " -> " + idx);
            		}
            	}	          
	        }
	        return mapFields;
        }finally {
            try{
                if(is != null) is.close();
            } catch (Exception ex){
                logger.info("Exception closing is");
            }
        }
	}

	private String[][] getCsvVaues(byte[] content, List<CampoReport> campiRepotList, Map<String,Integer> mapFields,
			String adsType) throws Exception {
		InputStream is = null;
		 CsvListReader listReader = null;
		BufferedReader bfReader = null;
		List<String[]> res = new ArrayList<String[]>();
		int minRowSize = campiRepotList.size();
        try {
	        is = new ByteArrayInputStream(content);
	        bfReader = new BufferedReader(new InputStreamReader(is));
	        
	        listReader = new CsvListReader(bfReader, CsvPreference.STANDARD_PREFERENCE);
	        List<String> fieldsInCurrentRow;
	        String[] nextRecord;
	        int rowCount  = 0;
	        // we are going to read data line by line 
	        int idAdsType = mapFields.get(ReportCsvFieldsEnum.ADS_TYPE.getValue());
	        int maxFieldId = getMaxFieldId(mapFields);
	        while ((fieldsInCurrentRow = listReader.read()) != null) { 
	        	nextRecord = new String[fieldsInCurrentRow.size()];
	        	fieldsInCurrentRow.toArray(nextRecord);
	        	
	        	if(maxFieldId > fieldsInCurrentRow.size()) {
	        		logger.info("Errore: numero valori " + fieldsInCurrentRow.size() 
	        		+ " inferirore al numero campi: " + maxFieldId + " - riga: " + (rowCount + 1) 
	        		+ " - values: " + fieldsInCurrentRow.toString() );
	        	}else {
	        	
		        	if(rowCount++ > 0 && adsType.equals(nextRecord[idAdsType])) {
		        		if(minRowSize <= nextRecord.length) {
			        		checkNumericValues(campiRepotList, mapFields,nextRecord,rowCount);
				        	res.add(nextRecord);
		        		}else {
		        			logger.info("************ row rowCount: " + rowCount + " - row size: " + nextRecord.length);
		        		}
			        	
		        	}
	        	}
	        } 
	        return res.toArray(new String[res.size()][]);
        }finally {
            try{
                if(is != null) is.close();
                if(listReader != null) listReader.close();
            } catch (Exception ex){
                 
            }
        }
	}
	
	private int getMaxFieldId(Map<String,Integer> mapFields) {
		int max = -1;
		Set<String> keys = mapFields.keySet();
		for(String key : keys) {
			if(mapFields.get(key) > max) {
				max = mapFields.get(key);
			}
		}
		return max;
	}
	
	private void checkNumericValues(List<CampoReport> campiRepotList, Map<String,Integer> mapFields, String[] record, int rowNum) {
		int idField;
		int dim = -1;
		for(int i = 0;i<campiRepotList.size();i++) {
			dim = i;
			try {
				campiRepotList.get(i);
				campiRepotList.get(i).getDenominazione();
	    		idField = mapFields.get(campiRepotList.get(i).getDenominazione());
	    		if(record[idField] == null) {
	    			record[idField] = "";
	    		}
	    		if("number".equals(campiRepotList.get(i).getTipo()) && record[idField].length() > 0
	    				&& !NumberUtils.isNumber(record[mapFields.get(campiRepotList.get(i).getDenominazione())])) {
	    			throw new ValidationException("Campo \"" + campiRepotList.get(i).getDenominazione() + "\" non numerico alla riga: " + (rowNum)+
	    					" - value: " + record[mapFields.get(campiRepotList.get(i).getDenominazione())]);
	    		}
			}catch(Exception ex) {
				logger.info("errore at " + dim);
			}
    		
    	}
	}
	
	@Override
	public Response downloadExcel(CsvReport csvReport, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return getDownloadExcel(csvReport.getTitle(),csvReport.getDataCsv());
	}
	
	@Override
	public Response downloadExcelGet(String title, String dataCsv, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return getDownloadExcel(title, dataCsv);
	}
		
	private Response getDownloadExcel(String title, String dataCsv) {
		logger.info("title: " + title + " - dataCsv: " + dataCsv);	
		
		//clean html
//		title = title.replace("<sup>", "^");
//		title = title.replace("</sup>", "");
//		dataCsv = dataCsv.replaceAll("<sup>", "^");
//		dataCsv = dataCsv.replaceAll("</sup>", "");
		title = title.replace("<sup>2</sup>", "\u00B2");
		title = title.replace("<sup>3</sup>", "\u00B3");
		dataCsv = dataCsv.replaceAll("<sup>2</sup>", "\u00B2");
		dataCsv = dataCsv.replaceAll("<sup>3</sup>", "\u00B3");
		

		HSSFWorkbook hwb = new HSSFWorkbook();
		ResponseBuilder response = null;
		String[]dataRows = dataCsv.split("\r\n");
		String[]dataRow = dataRows[0].split(";");

		// Create a Sheet
		Sheet sheet = hwb.createSheet(title);

		// Create a Font for styling header cells
		Font headerFont = hwb.createFont();

		headerFont.setFontHeightInPoints((short) 16);
		headerFont.setColor(IndexedColors.DARK_BLUE.getIndex());

		// Create a CellStyle with the font
		CellStyle headerCellStyle = hwb.createCellStyle();
		headerCellStyle.setFont(headerFont);
		headerCellStyle.setWrapText(true);

		// Create a Row
		Row headerRow = sheet.createRow(0);
		headerRow.setHeightInPoints(24);

		// Create cells
		for (int i = 0; i < dataRow.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(dataRow[i]);
			cell.setCellStyle(headerCellStyle);
			headerRow.setRowStyle(headerCellStyle);
		}
		String value;
		for(int i=1;i < dataRows.length;i++) {
			Row row = sheet.createRow(i+1);
			dataRow = dataRows[i].split(";");
			Cell cell;
			for(int k=0;k < dataRow.length;k++) {
				value = dataRow[k].replace(",", ".");
				cell = row.createCell(k);
				if(isNumeric(value)) {
					cell.setCellType(CellType.NUMERIC);
					cell.setCellValue(Float.parseFloat(value));
				}else {
					cell.setCellValue(dataRow[k]==null || "null".contentEquals(dataRow[k])?"":dataRow[k]);
				}
			}
		}
		// Resize all columns to fit the content size
		for (int i = 0; i < dataRow.length; i++) {
			sheet.autoSizeColumn(i);
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			hwb.write(baos);
		} catch (IOException e) {
			logger.fatal("An exception occurred.", e);
		}

		LocalDateTime now = LocalDateTime.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

		String formatDateTime = now.format(formatter);

		String filename = title + formatDateTime;
		response = Response.ok(baos.toByteArray());
		response.header("Content-disposition", "attachment; filename=" + filename + ".xls");
		return response.build();
	}

	@Override
	public Response getAdsTypeList(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return Response.ok(csvReport.getAdsTypeList()).build();
	}
	
	private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

	public boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false; 
	    }
	    return pattern.matcher(strNum).matches();
	}

	

}
