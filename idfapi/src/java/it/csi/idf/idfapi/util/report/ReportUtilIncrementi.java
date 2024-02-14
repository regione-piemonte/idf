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

public class ReportUtilIncrementi {

	static final Logger logger = Logger.getLogger(ReportUtilIncrementi.class);
	
	public static Map<String,Object> calcoloIncrementi(Map<String,Integer> mapFields, String[][] data, TipoAdsEnum adsType,
			String filtroSpecie, Double filtroMinDiametro, Double filtroMaxDiametro){
		
		Map<String,Object> res = new HashMap<String,Object>();
		Map<String,Object> incrementiData = getIncrementiData(mapFields,data);
		
		String[][] filterdData = filterIncrementiRows(mapFields,data,filtroSpecie,filtroMinDiametro,filtroMaxDiametro);
		logger.debug("data filtered dim: " + filterdData.length);
		Map<String,Object> infoIncrementiCavallettati = getInfoIncrementiCavallettati(mapFields,filterdData);
	
		incrementiData.put("tableCavallettati",infoIncrementiCavallettati.get("listTableWorking"));
		incrementiData.put("listPointsCampione",infoIncrementiCavallettati.get("listPointsCampione"));
		res.put("incrementi", incrementiData);
		res.put("riepilogo",ReportUtil.getRiepilogo("Incrementi", mapFields, data, adsType));
		res.put("infoDatiCampione",ReportUtil.getDatiCampione(mapFields, data, adsType));
		
		return res;
	}
	
	public static Map<String,Object> getInfoIncrementiCavallettati(Map<String,Integer> mapFields, String[][] data){
		ReportUtil.specieNotManagedForVolume = new HashSet<String>();
		int numAlberiCavallettati = 0;
		int numAlberiCampione = 0;
		String specie;
		Map<String,Double[]> mapPointsCampione = new HashMap<String,Double[]>();
		Map<String,Integer> mapCountCamBySpecie = new TreeMap<String,Integer>();
		Map<String,Integer> mapCountCavBySpecie = new TreeMap<String,Integer>();
		String tipoCampione;
		//working dati campione
		Map<String, Double> mapValus = new HashMap<String,Double>();
		mapValus.put("totpvx",0d);
		mapValus.put("totvol",0d);
		mapValus.put("totvolPV",0d);
		mapValus.put("numAlberiCampione",0d);
//		mapValus.put("totpvx", mapValus.get("totpvx") + incrPercentuale);
//		mapValus.put("numAlberiCampione", mapValus.get("numAlberiCampione") + 1);
//		if(mapValus.get("diametroMin") == null) {
//			mapValus.put("diametroMin",diametro);
//			mapValus.put("diametroMax",diametro);
		for(String[]row: data) {
			tipoCampione = row[mapFields.get(ReportCsvFieldsEnum.COD_TIPO_CAMPIONE.getValue())];
			specie = row[mapFields.get(ReportCsvFieldsEnum.SPECIE_CODE.getValue())];
			if("CAM".equals(tipoCampione)) {
				updateDatiCampione(mapFields, row, mapPointsCampione, mapValus);	
				numAlberiCampione++;
				if(mapCountCamBySpecie.get(specie) == null) {
					mapCountCamBySpecie.put(specie, 1);
				}else {
					mapCountCamBySpecie.put(specie, mapCountCamBySpecie.get(specie) + 1);
				}
				
			} else if("CAV".equals(tipoCampione)) {
				numAlberiCavallettati++;
				if(mapCountCavBySpecie.get(specie) == null) {
					mapCountCavBySpecie.put(specie, 1);
				}else {
					mapCountCavBySpecie.put(specie, mapCountCavBySpecie.get(specie) + 1);
				}
			}
		}
		
		logger.info("Alberi campione totali: " + numAlberiCampione);
		
		mapValus.put("mediaPonderaleIncrementi",mapValus.get("totvolPV")/mapValus.get("totvol"));
		
		
		logger.debug("updateDatiCavallettati");
		RegressioneLineare regrLin = ReportUtil.getRegressioneLineare(mapFields, data);
		mapValus.put("totpvx",0d);
		mapValus.put("totvol",0d);
		mapValus.put("totvolPV",0d);
		mapValus.put("totarea",0d);
		updateDatiCavallettati(mapFields, data, regrLin, mapValus);
		
		logger.debug("totpvx: " + mapValus.get("totpvx") + " - totarea: " + mapValus.get("totarea"));
		
		List<Object> listTableWorking = new ArrayList<Object>();
		String label = "Label";
		String valoreCol1 = "Numero alberi campione";
		String valoreCol2 = "Numero alberi cavallettati";
		Map<String,Object> mapElem;
		
		for(String key:mapCountCavBySpecie.keySet()) {
			mapElem = new LinkedHashMap<String,Object>();
			listTableWorking.add(mapElem);
			mapElem.put(label, key + " - " + ReportUtil.getMapSpecie().get(key));
			mapElem.put(valoreCol1, mapCountCamBySpecie.get(key)==null? 0: mapCountCamBySpecie.get(key));
			mapElem.put(valoreCol2, mapCountCavBySpecie.get(key));
		}
		mapElem = new LinkedHashMap<String,Object>();
		listTableWorking.add(mapElem);
		mapElem.put(label, "Totale");
		mapElem.put(valoreCol1, numAlberiCampione);
		mapElem.put(valoreCol2, numAlberiCavallettati);
		
		mapElem = new LinkedHashMap<String,Object>();
		listTableWorking.add(mapElem);
		mapElem.put(label, "-");
		mapElem.put(valoreCol1, "-");
		mapElem.put(valoreCol2, "-");
		
//		mapElem = new LinkedHashMap<String,Object>();
//		listTableWorking.add(mapElem);
//		mapElem.put(label, "Alberi campione");
//		mapElem.put(valoreCol1, mapValus.get("numAlberiCampione"));
//		mapElem.put(valoreCol2, " ");
		
		mapElem = new LinkedHashMap<String,Object>();
		listTableWorking.add(mapElem);
		mapElem.put(label, "Diametri (cm)");
		mapElem.put(valoreCol1, (mapValus.get("diametroMin")==null?"":mapValus.get("diametroMin")) + 
				" - " + (mapValus.get("diametroMax")==null?"":mapValus.get("diametroMax")));
		mapElem.put(valoreCol2, " ");
		
		mapElem = new LinkedHashMap<String,Object>();
		listTableWorking.add(mapElem);
		mapElem.put(label, "Media ponderata incrementi (%)");
		Double mediaPonderaleIncrementi = mapValus.get("mediaPonderaleIncrementi");
		mapElem.put(valoreCol1, mediaPonderaleIncrementi == null ?"-":ReportUtil.format2(mediaPonderaleIncrementi));
		mapElem.put(valoreCol2, " ");
		
		mapElem = new LinkedHashMap<String,Object>();
		listTableWorking.add(mapElem);
		mapElem.put(label, "-");
		mapElem.put(valoreCol1, "-");
		mapElem.put(valoreCol2, " ");
		
//		mapElem = new LinkedHashMap<String,Object>();
//		listTableWorking.add(mapElem);
//		mapElem.put(label, "Cavallettamento");
//		mapElem.put(valoreCol1, numAlberiCavallettati);
//		mapElem.put(valoreCol2, " ");
		
		mapElem = new LinkedHashMap<String,Object>();
		listTableWorking.add(mapElem);
		mapElem.put(label, "Incremento corrente (m<sup>3</sup>/ha)");
		mapElem.put(valoreCol1, mapValus.get("totarea")== null || mapValus.get("totarea") == 0?
				"-": ReportUtil.format2(mapValus.get("totpvx")/(mapValus.get("totarea"))));
		mapElem.put(valoreCol2, " ");
		
		mapElem = new LinkedHashMap<String,Object>();
		listTableWorking.add(mapElem);
		mapElem.put(label, "Volume (m<sup>3</sup>)");
		mapElem.put(valoreCol1, ReportUtil.format2((mapValus.get("totvol"))));
		mapElem.put(valoreCol2, " ");
		
		Map<String,Object> res = new HashMap<String,Object>();
		res.put("listTableWorking", listTableWorking);
		res.put("listPointsCampione", mapPointsCampione.values());
		
		return res;
	}
	
	public static void updateDatiCampione(Map<String,Integer> mapFields, String[]row, Map<String,Double[]> mapPointsCampione, Map<String,Double> mapValus) {
		Double incremento = Double.parseDouble(row[mapFields.get(ReportCsvFieldsEnum.INCREMENTO.getValue())]);
		Double diametro = Double.parseDouble(row[mapFields.get(ReportCsvFieldsEnum.DIAMETRO.getValue())]);
		if(incremento > 0 && diametro > 0) {
			Double altezza = Double.parseDouble(row[mapFields.get(ReportCsvFieldsEnum.ALTEZZA.getValue())]);
			if(mapPointsCampione.get(diametro + "-" + incremento) == null) {
				mapPointsCampione.put(diametro + "-" + incremento, new Double[] {diametro,incremento});
			}
			Double volume  = ReportUtil.getVolumeAlbero(mapFields, row);
			Double incrPercentuale = getIncremento(diametro,volume,incremento);
			
			logger.debug("incr: " + incremento +" - diametro: " + diametro + " - altezza: " + altezza 
					+ " -  pvx: " + incrPercentuale + " - volume: " + volume + " - volPV: " + (volume*incrPercentuale));
			
			mapValus.put("totpvx", mapValus.get("totpvx") + incrPercentuale);
			mapValus.put("totvol", mapValus.get("totvol") + volume);
			mapValus.put("totvolPV", mapValus.get("totvolPV") + (volume*incrPercentuale));
			
			mapValus.put("numAlberiCampione", mapValus.get("numAlberiCampione") + 1);
			if(mapValus.get("diametroMin") == null) {
				mapValus.put("diametroMin",diametro);
				mapValus.put("diametroMax",diametro);
			}else {
				if(mapValus.get("diametroMin") > diametro) {
					mapValus.put("diametroMin",diametro);
				}else if(mapValus.get("diametroMax") < diametro) {
					mapValus.put("diametroMax",diametro);
				}
			}
		}
	}
	
	public static void updateDatiCavallettati(Map<String,Integer> mapFields, String[][] data, 
			RegressioneLineare regrLin,	Map<String,Double> mapValus) {
		logger.info("updateDatiCavallettati start");
		Double raggio;
		Double incalzo;
		Double volume;
		String ads;
		Double diametro;
		String tipoCampione;
		Double mediaPonderaleIncrementi = mapValus.get("mediaPonderaleIncrementi");
		Set<String> adsSet = new HashSet<String>();
		for(String[]row: data) {
			diametro = Double.parseDouble(row[mapFields.get(ReportCsvFieldsEnum.DIAMETRO.getValue())]);
			tipoCampione = row[mapFields.get(ReportCsvFieldsEnum.COD_TIPO_CAMPIONE.getValue())];
			if("CAV".equals(tipoCampione)) {
				volume  = ReportUtil.getVolumeAlbero(mapFields, row, regrLin.getAltezzaByDiametro(diametro));
				mapValus.put("totvol", mapValus.get("totvol") + volume);
				mapValus.put("totpvx",mapValus.get("totpvx") + (mediaPonderaleIncrementi * volume)/100);
				ads = row[mapFields.get(ReportCsvFieldsEnum.ADS.getValue())];
				if(!adsSet.contains(ads)) {
					adsSet.add(ads);
					raggio = Double.parseDouble(row[mapFields.get(ReportCsvFieldsEnum.RAGGIO.getValue())]);
					incalzo = Double.parseDouble(row[mapFields.get(ReportCsvFieldsEnum.INCLINAZIO.getValue())]);
					mapValus.put("totarea", mapValus.get("totarea") + ReportUtil.getSuperficieHa(raggio, incalzo));
				}
			}
		}
		
	}
	
	public static String[][] filterIncrementiRows(Map<String,Integer> mapFields, String[][] data, 
			String filtroSpecie, Double filtroMinDiametro, Double filtroMaxDiametro){
		if(ReportUtil.notEmpty(filtroSpecie) || filtroMinDiametro != null || filtroMaxDiametro != null) {
			logger.debug("filtered true -> filtroSpecie: " + filtroSpecie + " - Diametro min: " + filtroMinDiametro +
					" - Diametro max: " + filtroMaxDiametro);
			List<String[]> resList = new ArrayList<String[]>();
			String specie;
			Double diametro;
			if(filtroSpecie != null) {
				filtroSpecie = filtroSpecie.split(" - ")[0];
			}
			for(String[] row : data) {
				specie = row[mapFields.get(ReportCsvFieldsEnum.SPECIE_CODE.getValue())];
				diametro = Double.parseDouble(row[mapFields.get(ReportCsvFieldsEnum.DIAMETRO.getValue())]);
				if((filtroSpecie == null || filtroSpecie.equals(specie)) && (filtroMinDiametro == null || diametro >= filtroMinDiametro)
						&& (filtroMaxDiametro == null || diametro <= filtroMaxDiametro)) {
					resList.add(row);
				}
			}
			
			String[][] result = new String[resList.size()][];
			for(int i = 0; i < resList.size(); i++) {
				result[i] = resList.get(i);
			}
			return result;
		}
		return data;
	}


	public static Map<String,Object> getIncrementiData(Map<String,Integer> mapFields, String[][] data){
		List<Double[]> listPointsIncrementi = new ArrayList<Double[]>();
		Map<String,Integer> mapCountBySpecie = new TreeMap<String,Integer>();
		Set<String> ads = new HashSet<String>();
		Set<String> specieFilter = new TreeSet<String>();
		Double diametro;
		Double incremento;
		Double volume;
		String tipoCampione;
		String specie;
		Double incrPercentuale;
		Double incrPercentualeMedio;
		Integer countAlberiCam = 0;
		Double totpvx = 0d;
		Double totvol = 0d;
		for(String[]row : data) {
			specie = row[mapFields.get(ReportCsvFieldsEnum.SPECIE_CODE.getValue())];
			specieFilter.add(specie + " - " + ReportUtil.getMapSpecie().get(specie));
			tipoCampione = row[mapFields.get(ReportCsvFieldsEnum.COD_TIPO_CAMPIONE.getValue())];
			
			if("CAM".equals(tipoCampione)) {
				diametro = Double.parseDouble(row[mapFields.get(ReportCsvFieldsEnum.DIAMETRO.getValue())]);
				incremento = Double.parseDouble(row[mapFields.get(ReportCsvFieldsEnum.INCREMENTO.getValue())]);
				if(diametro > 0 && incremento > 0) {
					countAlberiCam++;
					ads.add(row[mapFields.get(ReportCsvFieldsEnum.ADS.getValue())]);
					
					if(mapCountBySpecie.get(specie) == null) {
						mapCountBySpecie.put(specie, 1);
					}else {
						mapCountBySpecie.put(specie, mapCountBySpecie.get(specie) + 1);
					}
					
					volume  = ReportUtil.getVolumeAlbero(mapFields, row);

					incrPercentuale = getIncremento(diametro,volume,incremento);
					incrPercentualeMedio = (incrPercentuale * volume)/volume;
					
					totpvx+= incrPercentuale;
					totvol+= volume;
					
					listPointsIncrementi.add(new Double[] {diametro, incrPercentualeMedio});
				}
			}
		}
		List<Object> listTableTot = new ArrayList<Object>();
		Map<String,Object> mapElem = new LinkedHashMap<String,Object>();
		listTableTot.add(mapElem);
		String label = "Label";
		String valore = "Valore";
		mapElem.put(label, "Aree di Saggio");
		mapElem.put(valore, ads.size());
		
		mapElem = new LinkedHashMap<String,Object>();
		listTableTot.add(mapElem);
		mapElem.put(label, "Tot alberi campione");
		mapElem.put(valore, countAlberiCam);
		
		for(String key:mapCountBySpecie.keySet()) {
			mapElem = new LinkedHashMap<String,Object>();
			listTableTot.add(mapElem);
			mapElem.put(label, key);
			mapElem.put(valore, mapCountBySpecie.get(key));
		}
		
		mapElem = new LinkedHashMap<String,Object>();
		listTableTot.add(mapElem);
		mapElem.put(label, "Media ponderale incrementi (%)");
		mapElem.put(valore, ReportUtil.format2(totpvx/totvol));
		
		
		Map<String,Object> res = new HashMap<String,Object>();
		res.put("tableTotali", listTableTot);
		res.put("pointsIncrementi", listPointsIncrementi);
		res.put("specieFilter", specieFilter);
		return res;
	}
	
	private static Double getIncremento(Double diametro, Double volume, Double incremento) {
		Double aincr = 3.8d;
		if(diametro < 18) {
			aincr = 6d;
		}else if(diametro < 42) {
			aincr = 4d;
		}
		Double pvi = aincr * (incremento/diametro);
		//Double pvj = (pvi * volume)/volume;
		return pvi;
	}
}
