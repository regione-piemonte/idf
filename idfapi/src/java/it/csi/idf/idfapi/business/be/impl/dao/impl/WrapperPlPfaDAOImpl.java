/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.AreaProtettaDAO;
import it.csi.idf.idfapi.business.be.impl.dao.EventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.GeoPlPfaDAO;
import it.csi.idf.idfapi.business.be.impl.dao.InterventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.PopsemeDAO;
import it.csi.idf.idfapi.business.be.impl.dao.RN2000DAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperPlPfaDAO;
import it.csi.idf.idfapi.dto.EventoPiano;
import it.csi.idf.idfapi.dto.ExcelDTO;
import it.csi.idf.idfapi.dto.GeoPfaSearch;
import it.csi.idf.idfapi.dto.GeoPfaSearchDettaglio;
import it.csi.idf.idfapi.dto.GeoPlPfaExcelDTO;
import it.csi.idf.idfapi.dto.InterventoPiano;
import it.csi.idf.idfapi.mapper.RicadenzaHolderMapper;
import it.csi.idf.idfapi.util.EventoExcelEnum;
import it.csi.idf.idfapi.util.InterventoExcelEnum;
import it.csi.idf.idfapi.util.PaginatedList;
import it.csi.idf.idfapi.util.PfaExcelEnums;
import it.csi.idf.idfapi.util.PfaExcelNonPublicEnums;

@Component
public class WrapperPlPfaDAOImpl implements WrapperPlPfaDAO {

	static final Logger logger = Logger.getLogger(WrapperPlPfaDAOImpl.class);
	
	static final SimpleDateFormat  sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Autowired
	private GeoPlPfaDAO geoPlPfaDAO;

	@Autowired
	private PopsemeDAO popsemeDAO;

	@Autowired
	private AreaProtettaDAO areaProtettaDAO;

	@Autowired
	private RN2000DAO rn2000DAO;

	@Autowired
	private EventoDAO eventoDAO;

	@Autowired
	private InterventoDAO interventoDAO;

	@Override
	public PaginatedList<GeoPfaSearch> getPublicPianiForestaliSearch(Map<String, String> queryParams) {
		return geoPlPfaDAO.searchPianiForestali(queryParams);
	}

	@Override
	public GeoPfaSearchDettaglio getPublicPfaSearchByID(Integer idGeoPlPfa, Integer idSoggetto) {
		GeoPfaSearchDettaglio geoPfaSearchDettaglio = null;
		if(idSoggetto == null) {
			geoPfaSearchDettaglio = geoPlPfaDAO.pianoForestaleSearchDettaglio(idGeoPlPfa);
		}else {
			geoPfaSearchDettaglio = geoPlPfaDAO.pianoForestaleSearchDettaglioByUser(idGeoPlPfa,idSoggetto);
		}
		
		if(geoPfaSearchDettaglio != null) {

			geoPfaSearchDettaglio
					.setRicadenzeAree(RicadenzaHolderMapper.areeToRicadenza(areaProtettaDAO.getByIdgeoPlPfa(idGeoPlPfa)));
			geoPfaSearchDettaglio
					.setRicadenzeRn2000(RicadenzaHolderMapper.rnsToRicadenza(rn2000DAO.getByIdgeoPlPfa(idGeoPlPfa)));
			geoPfaSearchDettaglio.setRicadenzePopseme(
					RicadenzaHolderMapper.popsemesToRicadenza(popsemeDAO.getFatPopsemeByIdgeoPlPfa(idGeoPlPfa)));
		}

		return geoPfaSearchDettaglio;
	}

	@Override
	public byte[] generateExcel(ExcelDTO excel, UriInfo uriInfo) {
		HSSFWorkbook hwb = new HSSFWorkbook();
		List<GeoPfaSearchDettaglio> pfaList = new ArrayList<>();

		// List<PfaExcelEnums> columns = Arrays.asList(PfaExcelEnums.values());

		for (GeoPlPfaExcelDTO idPfa : excel.getPfaExcelDto()) {
			Integer idGeoPfa = idPfa.getIdGeoPfa();
			pfaList.add(geoPlPfaDAO.pianoForestaleSearchDettaglio(idGeoPfa));
		}
		
		// Create sheet
		Sheet pfaSheet = hwb.createSheet("PGF");

		
		CellStyle headerCellStyle = getCellStyle(hwb);

		// Create a Row
		Row headerRow = pfaSheet.createRow(0);
		headerRow.setHeightInPoints(24);

		// Create cells

		//checking if incoming call is from public application or no
		boolean isPfaPublic = uriInfo.getAbsolutePath().toString().contains("public");
		
		if (isPfaPublic) {
			List<PfaExcelEnums> publicPFAColumns = Arrays.asList(PfaExcelEnums.values());

			createHeaderCellsForPublicPFA(publicPFAColumns, headerRow, headerCellStyle);

			//fillSheetForPublicPfa(pfaList, pfaSheet);
			fillSheetForPfa(pfaList, pfaSheet);

			resizeColums(publicPFAColumns, pfaSheet);

		} else {

			List<PfaExcelNonPublicEnums> columnsForPFASheet = Arrays.asList(PfaExcelNonPublicEnums.values());
			
			List<EventoPiano> eventoList = new ArrayList<EventoPiano>();
			List<InterventoPiano> interventoList = new ArrayList<InterventoPiano>();

			for (GeoPlPfaExcelDTO idPfa : excel.getPfaExcelDto()) {

				Integer idGeoPfa = idPfa.getIdGeoPfa();
				eventoList.addAll(eventoDAO.findEventiPianoDTOByIdGeoPlPfa(idGeoPfa));
				interventoList.addAll(interventoDAO.findInterventiPianiByIdGeoPlPfa(idGeoPfa));
			}

			// List<EventoPiano> eventoList = eventoDAO.findEventiPianoDTOByIdGeoPlPfa();

			createHeaderCellsForPFA(columnsForPFASheet, headerRow, headerCellStyle);
			fillSheetForPfa(pfaList, pfaSheet);
			resizeColums(columnsForPFASheet, pfaSheet);
			
			createSheetInterventi(hwb, headerCellStyle, interventoList);
			createSheetEventi(hwb, headerCellStyle, eventoList);

		}

		ByteArrayOutputStream bArray = new ByteArrayOutputStream();

		try {
			hwb.write(bArray);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

		return bArray.toByteArray();
	}
	
	public CellStyle getCellStyle(HSSFWorkbook hwb) {		
		Font headerFont = hwb.createFont();
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.BLACK.getIndex());
		CellStyle headerCellStyle = hwb.createCellStyle();
		headerCellStyle.setFont(headerFont);
		headerCellStyle.setWrapText(true);		
		return headerCellStyle;
	}
	
	@Override
	public void createSheetInterventi(HSSFWorkbook hwb, CellStyle headerCellStyle, List<InterventoPiano> interventoList) {
		Sheet interventoSheet = hwb.createSheet("Interventi");
		List<InterventoExcelEnum> columnsForInterventiSheet = Arrays.asList(InterventoExcelEnum.values());
		Row headerRowForInterventiSheet = interventoSheet.createRow(0);
		headerRowForInterventiSheet.setHeightInPoints(24);

		createHeaderCellsForInterventi(columnsForInterventiSheet, headerRowForInterventiSheet, headerCellStyle);
		fillSheetForInterventi(interventoList, interventoSheet);
		resizeColums(columnsForInterventiSheet, interventoSheet);
	}
	
	@Override
	public void createSheetEventi(HSSFWorkbook hwb, CellStyle headerCellStyle, List<EventoPiano> eventoList) {
		Sheet eventoSheet = hwb.createSheet("Eventi");
		List<EventoExcelEnum> columnsForEventsSheet = Arrays.asList(EventoExcelEnum.values());
		Row headerRowForEventSheet = eventoSheet.createRow(0);
		headerRowForEventSheet.setHeightInPoints(24);

		createHeaderCellsForEventi(columnsForEventsSheet, headerRowForEventSheet, headerCellStyle);
		fillSheetForEventi(eventoList, eventoSheet);
		resizeColums(columnsForEventsSheet, eventoSheet);
	}
	
	private void resizeColums(List list, Sheet sheet) {
		for (int i = 0; i < list.size(); i++) {
			sheet.autoSizeColumn(i);
		}
	}

	private void createHeaderCellsForEventi(List<EventoExcelEnum> columnsForEvents, Row headerRowforEventSheet,
			CellStyle headerCellStyle) {
		for (int i = 0; i < columnsForEvents.size(); i++) {
			Cell cell = headerRowforEventSheet.createCell(i);
			cell.setCellValue(columnsForEvents.get(i).getValue());
			cell.setCellStyle(headerCellStyle);
			headerRowforEventSheet.setRowStyle(headerCellStyle);
		}
	}

	private void createHeaderCellsForInterventi(List<InterventoExcelEnum> columns,
			Row headerRowforInterventiSheet, CellStyle headerCellStyle) {
		for (int i = 0; i < columns.size(); i++) {
			Cell cell = headerRowforInterventiSheet.createCell(i);
			cell.setCellValue(columns.get(i).getValue());
			cell.setCellStyle(headerCellStyle);
			headerRowforInterventiSheet.setRowStyle(headerCellStyle);
		}
	}

	public void createHeaderCellsForPublicPFA(List<PfaExcelEnums> columns, Row headerRow,
			CellStyle headerCellStyle) {
		for (int i = 0; i < columns.size(); i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns.get(i).getValue());
			cell.setCellStyle(headerCellStyle);
			headerRow.setRowStyle(headerCellStyle);
		}
	}

	public void createHeaderCellsForPFA(List<PfaExcelNonPublicEnums> columns, Row headerRow,
			CellStyle headerCellStyle) {
		for (int i = 0; i < columns.size(); i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns.get(i).getValue());
			cell.setCellStyle(headerCellStyle);
			headerRow.setRowStyle(headerCellStyle);
		}
	}

	public void fillSheetForPublicPfa(List<GeoPfaSearchDettaglio> list, Sheet sheet) {
		int rowNum = 1;
		for (GeoPfaSearchDettaglio pfa : list) {
			Row row = sheet.createRow(rowNum++);

			row.createCell(0).setCellValue(pfa.getDenominazione());
			row.createCell(1).setCellValue(pfa.getDenominazioneProvincie());
			row.createCell(2).setCellValue(pfa.getDenominazioneComuni());
			row.createCell(3).setCellValue( pfa.getDataInizioValidita() == null ? "": pfa.getDataInizioValidita().toString());
			row.createCell(4).setCellValue(pfa.getDataFineValidita() == null ? "": pfa.getDataFineValidita().toString());
		}
	}

	public void fillSheetForPfa(List<GeoPfaSearchDettaglio> list, Sheet sheet) {
		int rowNum = 1;
		for (GeoPfaSearchDettaglio pfa : list) {
			Row row = sheet.createRow(rowNum++);

			row.createCell(0).setCellValue(pfa.getDenominazione());
			row.createCell(1).setCellValue(pfa.getDenominazioneProvincie());
			row.createCell(2).setCellValue(pfa.getDenominazioneComuni());
			row.createCell(3).setCellValue(pfa.getDataInizioValidita() == null ? "" : pfa.getDataInizioValidita().toString());
			row.createCell(4).setCellValue(pfa.getDataFineValidita() == null ? "" : pfa.getDataFineValidita().toString());
			String delibera = pfa.getDelibera() == null? "" : pfa.getDelibera() + " ";
			delibera += pfa.getDataApprovazione() == null? "" : "del " + pfa.getDataApprovazione().toString() + " ";
			row.createCell(5).setCellValue(delibera);
			row.createCell(6).setCellValue(listAsString(pfa.getGestori()));
			row.createCell(7).setCellValue(pfa.getFonteFinanziamento());
			row.createCell(8).setCellValue(pfa.getDescrPropriete());
			row.createCell(9).setCellValue(pfa.getFlgRevisione() == 1 ? "Si":"No");
			row.createCell(10).setCellValue(pfa.getProprietaSilvopastHa() == null ? "" : pfa.getProprietaSilvopastHa().toString());
			row.createCell(11).setCellValue(pfa.getProprietaForestaleHa() == null ? "" : pfa.getProprietaForestaleHa().toString());
			row.createCell(12).setCellValue(pfa.getPropNonForestaleHa() == null ? "" : pfa.getPropNonForestaleHa().toString());
			row.createCell(13).setCellValue(pfa.getSupPianifNonForestaleHa() == null ? "" : pfa.getSupPianifNonForestaleHa().toString());
			row.createCell(14).setCellValue(pfa.getSupPianifForestaleHa() == null ? "" : pfa.getSupPianifForestaleHa().toString());
			row.createCell(12).setCellValue(pfa.getSuperfGestNonAttivaTotHa() == null ? "" : pfa.getSuperfGestNonAttivaTotHa().toString());			
			row.createCell(15).setCellValue(pfa.getSuperfBocsGestAttivaHa() == null ? "" : pfa.getSuperfBocsGestAttivaHa().toString());
			row.createCell(16).setCellValue(pfa.getSuperfGestNonAttivaMonHa() == null ? "" : pfa.getSuperfGestNonAttivaMonHa().toString());
			row.createCell(17).setCellValue(pfa.getSuperfGestNonAttivaEvlHa() == null ? "" : pfa.getSuperfGestNonAttivaEvlHa().toString());
			

		}
	}

	public void fillSheetForEventi(List<EventoPiano> list, Sheet sheet) {
		int rowNum = 1;
		if (list != null) {
			for (EventoPiano eventi : list) {
				Row row = sheet.createRow(rowNum++);

				row.createCell(0).setCellValue(eventi.getPfaDenominazione());
				row.createCell(1).setCellValue(eventi.getProgressivoEventoPfa());
				row.createCell(2).setCellValue(eventi.getNomeBreve());
				row.createCell(3).setCellValue(eventi.getDataEvento()==null ? "":eventi.getDataEvento().toString());
				// row.createCell(4).setCellValue(Arrays.toString(eventi.getIdgeoPlParticelaForest()));
				row.createCell(4).setCellValue(listAsString(eventi.getIdgeoPlParticelaForest()));
				row.createCell(5).setCellValue(eventi.getDescrTipoEvento());
				row.createCell(6).setCellValue(eventi.getDescrEvento());
				row.createCell(7).setCellValue(eventi.getLocalita());
				row.createCell(8).setCellValue(eventi.getSupInteressataHa() == null ? 0 : eventi.getSupInteressataHa().doubleValue());
				row.createCell(9).setCellValue(eventi.getPercentualeDanno() == null ? 0 : eventi.getPercentualeDanno().doubleValue());

			}
		}
	}

	public void fillSheetForInterventi(List<InterventoPiano> list, Sheet sheet) {

		int rowNum = 1;
		if (list != null) {
			for (InterventoPiano interventi : list) {
				Row row = sheet.createRow(rowNum++);

				row.createCell(0).setCellValue(interventi.getPfaDenominazione());
				row.createCell(1).setCellValue(interventi.getNrProgressivoInterv());
				row.createCell(2).setCellValue(interventi.getAnnataSilvana());
				row.createCell(3).setCellValue(listAsString(interventi.getnParticelaForestale()));
				row.createCell(4)
						.setCellValue(interventi.getDataInizio() == null ? "" : interventi.getDataInizio().toString());
				row.createCell(5)
						.setCellValue(interventi.getDataFine() == null ? "" : interventi.getDataFine().toString());
				row.createCell(6).setCellValue(interventi.getDescrizione() == null ? "" : interventi.getDescrizione());
				row.createCell(7).setCellValue(interventi.getLocalita() == null ? "" : interventi.getLocalita());
				row.createCell(8).setCellValue(interventi.getSuperficieInteressata() == null ? "" : 
					interventi.getSuperficieInteressata() == null ? "":interventi.getSuperficieInteressata().toString());
				row.createCell(9).setCellValue(interventi.getM3Prelevati() == null ? "" : 
					interventi.getSuperficieInteressata() == null ? "":interventi.getSuperficieInteressata().toString());
				row.createCell(10).setCellValue(interventi.getDescrStatoIntervento() == null ? "" : interventi.getDescrStatoIntervento());
				row.createCell(11).setCellValue(interventi.getNrIstanzaForestale() == null ? "" : interventi.getNrIstanzaForestale());
				
			}
		}
	}
	
	private String listAsString(Object [] items) {
		if(items == null || items.length == 0) {
			return "";
		}
		StringBuilder sb = null;
		for(int i=0;i<items.length;i++) {
			if(items[i] != null) {
				if(sb == null) {
					sb = new StringBuilder(items[i].toString());
				}else {
					sb.append(", ").append(items[i]);
				}
			}
		}
		return sb == null?"": sb.toString();
	}

}
