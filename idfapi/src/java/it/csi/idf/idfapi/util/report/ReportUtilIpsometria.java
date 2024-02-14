/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import it.csi.idf.idfapi.dto.RegressioneLineare;
import it.csi.idf.idfapi.util.TipoAdsEnum;

public class ReportUtilIpsometria {
	
	final static Logger logger = Logger.getLogger(ReportUtilIpsometria.class);
	
	public static Map<String,Object> calcoloIpsometria(Map<String,Integer> mapFields, String[][] data, TipoAdsEnum adsType,
			String filtroSpecie, String filtroTipoForestale, String filtroCategoriaForestale){
		Integer idFilterField  = null;
		String[][] workingData = data;
		String filterValue = null;
		if(filtroSpecie !=null && filtroSpecie.length() > 0) {
			idFilterField = mapFields.get(ReportCsvFieldsEnum.SPECIE_CODE.getValue());
			filterValue = filtroSpecie;
		}else if(filtroTipoForestale !=null && filtroTipoForestale.length() > 0) {
			idFilterField = mapFields.get(ReportCsvFieldsEnum.TIPO_FORESTALE.getValue());
			filterValue = filtroTipoForestale;
		}else if(filtroCategoriaForestale !=null && filtroCategoriaForestale.length() > 0) {
			idFilterField = mapFields.get(ReportCsvFieldsEnum.POPOLAMENTO.getValue());
			filterValue = filtroCategoriaForestale;
		}
		if(idFilterField != null) {
			logger.debug("inside filter -> idFilterField: " + idFilterField + " - filterValue: " + filterValue);
			workingData = filterIpsometriaRows(data, mapFields, idFilterField, filterValue);
		}
		Map<String,Object> infoWorkingData = getInfoWorkingData(mapFields, workingData);
		
		Map<String, List<Double>> orderedDiametriAndAltezze =getOrderedDiametriAndAltezze(mapFields, workingData);
		
		Map<String, Object> datiElaborati;
		if(orderedDiametriAndAltezze.get("diametri").size()== 0) {
			datiElaborati = new HashMap<String, Object>();
			datiElaborati.put("error","Con il filtro impostato non e' presente il dato relativo all'altezza!");
		}else {		
			datiElaborati =elaborazioneDatiIpsomentira(orderedDiametriAndAltezze);
		}
		Map<String,Object> res = new HashMap<String,Object>();
		Map<String,Object> ipsometriaData = new HashMap<String,Object>();
		res.put("ipsometria", ipsometriaData);

		ipsometriaData.put("filters", getFilters(mapFields, data));
		ipsometriaData.put("infoWorkingData", infoWorkingData);
		ipsometriaData.put("ipsometiraData", datiElaborati);
		res.put("riepilogo", ReportUtil.getRiepilogo("Ipsometria", mapFields, data, adsType));
		res.put("infoDatiCampione",ReportUtil.getDatiCampione(mapFields, data, adsType));
		logger.debug("End ipsometria - data: " + data.length + " - workingData: " + workingData.length);
		return res;
	}
	
	private static Map<String,Object> getInfoWorkingData (Map<String,Integer> mapFields, String[][] data){
		Set<String> listAreeSaggio = new HashSet<String>();
		Map<String,Integer> mapAlberiSpecie = new TreeMap<String,Integer>();
		Map<String,Double> mapDiametroMax = new TreeMap<String,Double>();
		Map<String,Double> mapDiametroMin = new LinkedHashMap<String,Double>();
		Map<String,Double> mapAltezzaMax = new LinkedHashMap<String,Double>();
		Map<String,Double> mapAltezzaMin = new LinkedHashMap<String,Double>();
		String value;
		int totAlberi = 0;
		String specie;
		Double[] diametroAltezza;
		Integer idCodTipoCampione = mapFields.get(ReportCsvFieldsEnum.COD_TIPO_CAMPIONE.getValue());
		for(String[] row : data) { 
			listAreeSaggio.add(row[mapFields.get(ReportCsvFieldsEnum.ADS.getValue())]);
			value = row[mapFields.get(ReportCsvFieldsEnum.SPECIE_CODE.getValue())];
			diametroAltezza = getDiametroAltezza(row, mapFields);
			
			if("CAV".equals(row[idCodTipoCampione]) && diametroAltezza != null) {
				totAlberi++;
				if(mapAlberiSpecie.get(value)==null) {
					mapAlberiSpecie.put(value, 1);
					logger.debug("Specie: " + value);
				}else {
					mapAlberiSpecie.put(value, mapAlberiSpecie.get(value) + 1);
				}
				specie = row[mapFields.get(ReportCsvFieldsEnum.SPECIE_CODE.getValue())];
				updateDiametriMaxAndMin(specie, diametroAltezza[0], mapDiametroMax,mapDiametroMin);
				updateAltezzaMaxAndMin(specie, diametroAltezza[1], mapAltezzaMax,mapAltezzaMin);
			}
		}
		mapAlberiSpecie.put("Totale", totAlberi);
		List<Object> table1 = new ArrayList<Object>();
		Map<String,Object> row = new LinkedHashMap<String,Object>();
		row.put("Totale ads",listAreeSaggio.size());
		row.put("Totale alberi",totAlberi); //data.length);
		for(String key : mapAlberiSpecie.keySet()) {
			row.put(key,mapAlberiSpecie.get(key));
		}
		table1.add(row);
		List<Object> table2 = new ArrayList<Object>();
		
		for(String key : mapDiametroMax.keySet()) {
			row = new LinkedHashMap<String,Object>();
			table2.add(row);
			if(ReportUtil.getMapSpecie().get(key) != null) {
				row.put("Specie campione",key + " - " + ReportUtil.getMapSpecie().get(key));
			}else {
				row.put("Specie campione",key);
			}
			row.put("Diametro min", mapDiametroMin.get(key));
			row.put("Diametro max", mapDiametroMax.get(key));
			row.put("Altezza min", mapAltezzaMin.get(key));
			row.put("Altezza max", mapAltezzaMax.get(key));
			row.put("Numero alberi campione", mapAlberiSpecie.get(key));
		}
		
		Map<String,Object> res = new HashMap<String,Object>();
		res.put("Table1",table1);
		res.put("Table2",table2);
		return res;
	}
	
	private static void updateDiametriMaxAndMin(String specie, Double diametro,
		Map<String,Double> mapDiametroMax, Map<String,Double> mapDiametroMin) {
		//absolute diametro max and min
		updateMaxAndMin("Totale",diametro, mapDiametroMax ,mapDiametroMin);
		//specie diametro max and min
		updateMaxAndMin(specie,diametro, mapDiametroMax ,mapDiametroMin);
	}
	
	private static void updateAltezzaMaxAndMin(String specie, Double altezza,
		Map<String,Double> mapAltezzaMax, Map<String,Double> mapAltezzaMin) {
		//absolute altezza max and min
		updateMaxAndMin("Totale",altezza, mapAltezzaMax ,mapAltezzaMin);
		//specie altezza max and min
		updateMaxAndMin(specie,altezza, mapAltezzaMax ,mapAltezzaMin);
	}
	
	private static void updateMaxAndMin(String key, Double valore, Map<String,Double> mapMax, Map<String,Double> mapMin) {
		if(mapMax.get(key) == null) {
			mapMax.put(key, valore);
			mapMin.put(key, valore); 
		}else {
			Double savedVal = mapMax.get(key);
			if(savedVal < valore) {
				mapMax.put(key, valore);
			}
			savedVal = mapMin.get(key);
			if(savedVal > valore) {
				mapMin.put(key, valore);
			}
		}
	}
	
	private static Map<String, Object> elaborazioneDatiIpsomentira(Map<String, List<Double>> diametriAndAltezze){
		List<Double> diametriList = diametriAndAltezze.get("diametri");
		List<Double> altezzeList = diametriAndAltezze.get("altezze");
		List<Double> lognDiametri = new ArrayList<Double>();
		for(int i = 0; i< diametriList.size();i++) {
			lognDiametri.add(Math.log(diametriList.get(i)));
		}
		RegressioneLineare regrLin = new RegressioneLineare(lognDiametri,altezzeList);
		double m = regrLin.getM();
		double q = regrLin.getQ();
		Double[][] pointsLineInterp = new Double[lognDiametri.size()][];
		for(int i = 0; i<lognDiametri.size();i++) {
			pointsLineInterp[i] = new Double[] {ReportUtil.round2(lognDiametri.get(i)),(m*lognDiametri.get(i)+q)};
		}
		Double[][] pointsLine = new Double[2][];
		int lastDiametroId = diametriList.size()-1;
		pointsLine[0] = new Double[] {diametriList.get(0),ReportUtil.round3(m*lognDiametri.get(0)+q)};
		pointsLine[1] = new Double[] {diametriList.get(lastDiametroId),ReportUtil.round3(m*lognDiametri.get(lastDiametroId)+q)};
		
		Double[][] pointsNormal = new Double[altezzeList.size()][];
		Double[][] pointsInterpolation = new Double[altezzeList.size()][];
		for(int i=0;i<altezzeList.size();i++) {
			pointsNormal[i] = new Double[] {ReportUtil.round2(diametriList.get(i)),ReportUtil.round2(altezzeList.get(i))};
			//pointsInterpolation[i] = new Double[] {ReportUtil.round2(lognDiametri.get(i)),ReportUtil.round2(altezzeList.get(i))};
			pointsInterpolation[i] = new Double[] {ReportUtil.round2(diametriList.get(i)),ReportUtil.round2(m*lognDiametri.get(i)+q)};
		}
				
		Map<String, Object> res = new HashMap<String,Object>();
		res.put("m", ReportUtil.format3(regrLin.getM()));
		res.put("q", ReportUtil.format3(regrLin.getQ()));
		res.put("r2",ReportUtil.format(regrLin.getR2(),5));
		res.put("pointsNormal", pointsNormal);
		res.put("pointsInterpolation", pointsInterpolation);
		res.put("pointsLineNormal", pointsLine);
		res.put("pointsLineInterpolation", pointsLineInterp);
		return res;
	}
	
	private static Map<String, List<Double>> getOrderedDiametriAndAltezze(Map<String,Integer> mapFields, String[][] data){
		List<Double> diametriList = new ArrayList<Double>();
		List<Double> altezzeList = new ArrayList<Double>();
		int countNotAvailable = 0;
		int countAvailable = 0;
		Double[] diametroAltezza;
		for(String[] row : data) {		
			diametroAltezza = getDiametroAltezza(row, mapFields);
			if(diametroAltezza != null) {
				countAvailable++;
				diametriList.add(diametroAltezza[0]);
				altezzeList.add(diametroAltezza[1]);				
			}else {
				countNotAvailable++;
			}			
		}
		logger.info("Total not available: " + countNotAvailable + " of: " + data.length);
		logger.info("Total available: " + countAvailable);
		//order list by diametro
		Double minVal = -1d;
		int posMinVal = -1;
		Double aux;
		for(int i = 0; i < diametriList.size() -1; i++) {
			minVal = diametriList.get(i);
			posMinVal = i;
			for(int j = i +1; j < diametriList.size(); j++) {
				if(minVal > diametriList.get(j)) {
					minVal = diametriList.get(j);
					posMinVal = j;
				}
			}
			if(posMinVal != i) {
				aux = diametriList.get(i);
				diametriList.set(i, diametriList.get(posMinVal));
				diametriList.set(posMinVal, aux);
				
				aux = altezzeList.get(i);
				altezzeList.set(i, altezzeList.get(posMinVal));
				altezzeList.set(posMinVal, aux);
			}
		}
		
		Map<String, List<Double>> res = new HashMap<String,List<Double>>();
		res.put("diametri", diametriList);
		res.put("altezze", altezzeList);
		return res;
	}
	
	
	private static String[][] filterIpsometriaRows(String[][] data, Map<String,Integer> mapFields,
			int idFilterField, String filterValue){
		List<String[]> listRows = new ArrayList<String[]>();
		filterValue = filterValue.split(" - ")[0];
		for(String[] row : data) {
			if(filterValue.contentEquals(row[idFilterField])) {
				listRows.add(row);
			}
		}
		String [][] result = new String[listRows.size()][];
		for(int i = 0; i < listRows.size(); i++) {
			result[i] = listRows.get(i);
		}
		return result;
	}
	
	private static Map<String,Set<String>> getFilters(Map<String,Integer> mapFields, String[][] data) {
		Map<String,Set<String>> filtersMap = new HashMap<String,Set<String>>();
		Set<String> categorieForestali = new TreeSet<String>();
		Set<String> tipiForestali = new TreeSet<String>();
		Set<String> specie = new TreeSet<String>();
		filtersMap.put("categorieForestali", categorieForestali);
		filtersMap.put("tipiForestali", tipiForestali);
		filtersMap.put("specie", specie);
		String codSpecie;
		Integer idCodTipoCampione = mapFields.get(ReportCsvFieldsEnum.COD_TIPO_CAMPIONE.getValue());
//		Double[] diametroAltezza;
		for(String[]row : data) {
//			diametroAltezza = getDiametroAltezza(row, mapFields);
			if("CAV".equals(row[idCodTipoCampione])) { //&& diametroAltezza != null
				categorieForestali.add(row[mapFields.get(ReportCsvFieldsEnum.POPOLAMENTO.getValue())]);
				tipiForestali.add(row[mapFields.get(ReportCsvFieldsEnum.TIPO_FORESTALE.getValue())]);
				codSpecie = row[mapFields.get(ReportCsvFieldsEnum.SPECIE_CODE.getValue())];
				specie.add(codSpecie + " - " + ReportUtil.getMapSpecie().get(codSpecie));
			}
		}
		
		return filtersMap;
	}
	
	private static Double[] getDiametroAltezza(String[]row, Map<String,Integer> mapFields) {
		String diametroStr = row[mapFields.get(ReportCsvFieldsEnum.DIAMETRO.getValue())];
		String altezzaStr = row[mapFields.get(ReportCsvFieldsEnum.ALTEZZA.getValue())];
		if(diametroStr.length() > 0 && altezzaStr.length() > 0) {
			Double diametroVal = Double.parseDouble(diametroStr);
			Double altezzaVal = Double.parseDouble(altezzaStr);
			if(diametroVal > 0 && altezzaVal > 0) {
				return new Double[] {diametroVal,altezzaVal};
			}
		}
		return null;
	}

}
