/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import it.csi.idf.idfapi.util.TipoAdsEnum;

public class ReportUtilDiametri {

	static final Logger logger = Logger.getLogger(ReportUtilDiametri.class);
	static final Integer stepClass = 5;
	
	public static Map<String,Object> calcoloDiametri(Map<String,Integer> mapFields, String[][] data, TipoAdsEnum adsType){
		
		Map<String,Object> res = new HashMap<String,Object>();
		Map<String,Object> diametriData = new HashMap<String,Object>();
		res.put("diametri", diametriData);
		diametriData.put("diametriData",getDiametriMap( mapFields, data));
		if(adsType == TipoAdsEnum.RELASCOPICA_COMPLETA) {
			diametriData.put("infoRelascopia", ReportUtil.getRelascopia(mapFields, data, false));
		}
		res.put("riepilogo",ReportUtil.getRiepilogo("Diametri", mapFields, data, adsType));
		res.put("infoDatiCampione",ReportUtil.getDatiCampione(mapFields, data, adsType));
		return res;
	}
	
	private static Map<String,Object> getDiametriMap(Map<String,Integer> mapFields, String[][] data) {
		Map<Integer,Integer> mapDiametri = new TreeMap<Integer,Integer>();
		Integer classId;
		logger.debug("mapFields: " + mapFields.keySet().size());
		int fieldDiametroPos = mapFields.get(ReportCsvFieldsEnum.DIAMETRO.getValue());
		Integer minDiamGroup = null;
		Integer maxDiamGroup = null;
		String diametroStr;
		for(String[] row : data) {
			diametroStr= row[fieldDiametroPos];
			if("CAV".equals(row[mapFields.get(ReportCsvFieldsEnum.COD_TIPO_CAMPIONE.getValue())]) 
					&& diametroStr.length() > 0) {
				classId = ReportUtil.getClasse(Double.parseDouble(diametroStr),stepClass);
				if(classId > 85) {
					classId = 85;
				}
				if(minDiamGroup == null || classId < minDiamGroup) {
					minDiamGroup = classId;
				}
				if(maxDiamGroup == null || classId > maxDiamGroup) {
					maxDiamGroup = classId;
				}
				if(mapDiametri.get(classId) == null) {
					mapDiametri.put(classId,0);
				}
				mapDiametri.put(classId,mapDiametri.get(classId)+1);
			}
		}
		int valuesDim = (maxDiamGroup - minDiamGroup)/stepClass;
		String[] labels = new String[maxDiamGroup/stepClass];
		Integer[] values = new Integer[maxDiamGroup/stepClass];
		logger.debug("min: " + minDiamGroup + " - max: " + maxDiamGroup + " - valuesDim: " + valuesDim);
		for(int i=5;i<minDiamGroup;i=i+stepClass) {
			labels[i/stepClass -1] = "" + i;
			values[i/stepClass -1] = 0;
		}
		for(int i=minDiamGroup;i<=maxDiamGroup;i=i+stepClass) {
			labels[i/stepClass -1] = i > 80 ?">80":"" + i;
			values[i/stepClass -1] = mapDiametri.get(i)==null?0:mapDiametri.get(i);
		}
		
		Map<String,Object> result = new LinkedHashMap<String,Object>();
		result.put("labels", labels);
		result.put("values", values);
		
		return result;
	}
	
	
}
