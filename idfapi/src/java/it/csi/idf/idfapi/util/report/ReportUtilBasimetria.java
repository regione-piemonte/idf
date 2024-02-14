/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util.report;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import it.csi.idf.idfapi.dto.RegressioneLineare;
import it.csi.idf.idfapi.util.TipoAdsEnum;

public class ReportUtilBasimetria {
	
	static final Logger logger = Logger.getLogger(ReportUtilBasimetria.class);
	
	
	
	public static Map<String,Object> calcoloBasimetria(Map<String,Integer> mapFields, String[][] data, TipoAdsEnum adsType){
		if(TipoAdsEnum.SUPERFICIE_NOTA == adsType) {
			return calcoloBasimetriaSupNota(mapFields, data);
		}else {
			return calcoloBasimetriaRelascopica(mapFields, data, adsType);
		}
		
	}
	
	public static Map<String,Object> calcoloBasimetriaSupNota(Map<String,Integer> mapFields, String[][] data){
		
		Map<String,Object> res = new HashMap<String,Object>();
		Map<String,Object> basimetriaData = new HashMap<String,Object>();
		res.put("basimetria", basimetriaData);
		logger.debug("start: getRiepilogo");
		Map<String, Object> riepilogo = ReportUtil.getRiepilogo("Basimetria", mapFields, data, TipoAdsEnum.SUPERFICIE_NOTA);
		logger.debug("end: getRiepilogo");
		logger.debug("start: basimetriaAreaSaggioAndTotali");
		List<Map<String, Object>> basimetriaAreaSaggioAndTot = basimetriaAreaSaggioAndTotali(mapFields, data);
		logger.debug("end: basimetriaAreaSaggioAndTotali");
		List<Map<String, Object>> totali = new ArrayList<Map<String, Object>>();
		totali.add(basimetriaAreaSaggioAndTot.get(0));
		basimetriaAreaSaggioAndTot.remove(0);
		basimetriaData.put("areaSaggio",basimetriaAreaSaggioAndTot);
		basimetriaData.put("specie",basimetriaSpecie( mapFields, data));
		basimetriaData.put("qualita",basimetriaQualitaAndVolumi( mapFields, data));
		
		basimetriaData.put("totali",totali);
		res.put("riepilogo",riepilogo);

		logger.debug("start: getDatiCampione");
		res.put("infoDatiCampione",ReportUtil.getDatiCampione(mapFields, data, TipoAdsEnum.SUPERFICIE_NOTA));
		logger.debug("end: getDatiCampione --- FINE");
		return res;
	}
	
	public static Map<String,Object> calcoloBasimetriaRelascopica(Map<String,Integer> mapFields, String[][] data,TipoAdsEnum adsType){

		Map<String,Double[]> conteggi = getMapConteggi(mapFields, data);
		Double numAds = conteggi.get("ads")[0];
		conteggi.put("ads", new Double[] {0d,0d});
		//Map<String,Double> basimetriaMediaSpecie = getBasimetriaMediaSpecie(mapFields, data);
		
		Double basimetricaSemeTot = 0d;
		Double basimetricaPolloneTot = 0d;
		
		conteggi.remove("ads");
		for(String specie : conteggi.keySet()) {
			basimetricaSemeTot += conteggi.get(specie)[0]/numAds;
			basimetricaPolloneTot += conteggi.get(specie)[1]/numAds;
		}
		
		
		
		Double totMqHa;
		
		logKeySet("conteggi", conteggi.keySet());
		//logKeySet("basimetriaMediaSpecie", basimetriaMediaSpecie.keySet());
		
		logger.debug("numAds: " + numAds);
		
		List<Object> basimetriaSpecie = new ArrayList<Object>();
		Map<String,String> row;
		Double sumSemeMqHa = 0d;
		Double sumSemePerc = 0d;
		Double sumPolloneMqHa = 0d;
		Double sumPollonePerc = 0d;
		Double sumTotMqHa = 0d;
		Double sumTotPerc = 0d;
		String valueStr;
		
		for(String specie : conteggi.keySet()) {
			row = new LinkedHashMap<String,String>();
			basimetriaSpecie.add(row);
			row.put("Specie", specie + " - " + ReportUtil.getMapSpecie().get(specie));
			
			valueStr = ReportUtil.format2(conteggi.get(specie)[0]/numAds);
			row.put("Seme (m<sup>2</sup>/ha)", valueStr);
			sumSemeMqHa += Double.parseDouble(valueStr.replace(",", ".")); 
			
			valueStr = basimetricaSemeTot==0?"0,0":ReportUtil.format2(((conteggi.get(specie)[0]/numAds)/basimetricaSemeTot) * 100);
			row.put("Seme (%)", valueStr);
			sumSemePerc += Double.parseDouble(valueStr.replace(",", ".")); 
			
			valueStr = ReportUtil.format2(conteggi.get(specie)[1]/numAds);
			row.put("Pollone (m<sup>2</sup>/ha)", valueStr);
			sumPolloneMqHa += Double.parseDouble(valueStr.replace(",", ".")); 
			
			valueStr = basimetricaPolloneTot==0?"0,0":ReportUtil.format2(((conteggi.get(specie)[1]/numAds)/basimetricaPolloneTot) * 100);
			row.put("Pollone (%)", valueStr);
			totMqHa = (conteggi.get(specie)[0] + conteggi.get(specie)[1])/numAds;
			sumPollonePerc += Double.parseDouble(valueStr.replace(",", ".")); 
			
			valueStr = ReportUtil.format2(totMqHa);
			row.put("Totale (m<sup>2</sup>/ha)", valueStr);
			sumTotMqHa += Double.parseDouble(valueStr.replace(",", ".")); 
			
			valueStr = ReportUtil.format2(totMqHa/(basimetricaSemeTot + basimetricaPolloneTot)*100);
			row.put("Totale (%)", valueStr);
			sumTotPerc +=  Double.parseDouble(valueStr.replace(",", ".")); 
			
		}
		row = new LinkedHashMap<String,String>();
		basimetriaSpecie.add(row);
		row.put("Specie", "Totale");
		row.put("Seme (m<sup>2</sup>/ha)",ReportUtil.format2(sumSemeMqHa)); //ReportUtil.format2(basimetricaSemeTot));
		row.put("Seme (%)",ReportUtil.format1(sumSemePerc)); //basimetricaSemeTot==0?"0,0":"100,0");
		row.put("Pollone (m<sup>2</sup>/ha)", ReportUtil.format2(sumPolloneMqHa));//ReportUtil.format2(basimetricaPolloneTot));
		row.put("Pollone (%)", ReportUtil.format1(sumPollonePerc));//basimetricaPolloneTot==0?"0,0":"100,0");
		row.put("Totale (m<sup>2</sup>/ha)", ReportUtil.format2(sumTotMqHa));//ReportUtil.format2(basimetricaSemeTot + basimetricaPolloneTot));
		row.put("Totale (%)", "100");//ReportUtil.format1(sumTotPerc));//"100,0");
		
		Map<String,Object> basimetriaData = new HashMap<String,Object>();
		basimetriaData.put("specie", basimetriaSpecie);
		
		Map<String, Object> riepilogo = ReportUtil.getRiepilogo("Basimetria", mapFields, data, adsType);
		Map<String,Object> res = new HashMap<String,Object>();
//		totali.add(basimetriaAreaSaggioAndTot.get(0));
//		basimetriaAreaSaggioAndTot.remove(0);
//		basimetriaData.put("areaSaggio",basimetriaAreaSaggioAndTot);
//		basimetriaData.put("specie",basimetriaSpecie( mapFields, data));
//		basimetriaData.put("qualita",basimetriaQualitaAndVolumi( mapFields, data));
//		basimetriaData.put("totali",totali);
		res.put("riepilogo",riepilogo);
		if(TipoAdsEnum.RELASCOPICA_COMPLETA == adsType) {
			res.put("infoDatiCampione",ReportUtil.getDatiCampione(mapFields, data, TipoAdsEnum.RELASCOPICA_COMPLETA));
		}
		res.put("basimetria", basimetriaData);
		
		return res;
	}
	
	public static Map<String,Double> getDatiBasimetriaRelascopica(Map<String,Integer> mapFields, String[][] data,TipoAdsEnum adsType){

		Map<String,Double[]> conteggi = getMapConteggi(mapFields, data);
		Double numAds = conteggi.get("ads")[0];
		conteggi.put("ads", new Double[] {0d,0d});
		//Map<String,Double> basimetriaMediaSpecie = getBasimetriaMediaSpecie(mapFields, data);
		
		Double basimetricaSemeTot = 0d;
		Double basimetricaPolloneTot = 0d;
		
		Map<String,Double> res = new HashMap<String,Double>();
		
		conteggi.remove("ads");
		for(String specie : conteggi.keySet()) {
			basimetricaSemeTot += conteggi.get(specie)[0]/numAds;
			basimetricaPolloneTot += conteggi.get(specie)[1]/numAds;
		}
		
		Double totMqHa;
		logKeySet("conteggi", conteggi.keySet());		
	
		for(String specie : conteggi.keySet()) {
			totMqHa = (conteggi.get(specie)[0] + conteggi.get(specie)[1])/numAds;
			res.put(specie, totMqHa);	
		}
		return res;
	}

	public static Double getBasimetriaTotMqHa(Map<String,Integer> mapFields, String[][] data){

		Map<String,Double[]> conteggi = getMapConteggi(mapFields, data);
		Double numAds = conteggi.get("ads")[0];
		conteggi.put("ads", new Double[] {0d,0d});
		//Map<String,Double> basimetriaMediaSpecie = getBasimetriaMediaSpecie(mapFields, data);
		
		Double basimetricaSemeTot = 0d;
		Double basimetricaPolloneTot = 0d;
		
		conteggi.remove("ads");
		for(String specie : conteggi.keySet()) {
			basimetricaSemeTot += conteggi.get(specie)[0]/numAds;
			basimetricaPolloneTot += conteggi.get(specie)[1]/numAds;
		}
		
		Double sumTotMqHa = 0d;
		Double totMqHa;
		
		for(String specie : conteggi.keySet()) {
			totMqHa = (conteggi.get(specie)[0] + conteggi.get(specie)[1])/numAds;
			sumTotMqHa += totMqHa;		
		}
		return sumTotMqHa;
	}

	private static void logKeySet(String name, Set<String> set) {
		StringBuilder sb = new StringBuilder();
		sb.append("name: ").append(name).append(" - ");
		for(String key :set) {
			sb.append(key).append(" - ");
		}
		logger.debug(sb.toString());
	}
	
	private static Map<String,Double[]> getMapConteggi(Map<String,Integer> mapFields, String[][] data){
		Map<String,Double[]> mapSpecieSemePollone = new TreeMap<String,Double[]>();
		Map<String,Boolean> mapAds = new HashMap<String,Boolean>();
		String ads;
		String specie;
		Integer fattoreAds;
		Double[] workingVet;
		for(int i = 0;i < data.length;i++) {
			if(StringUtils.isNumeric(data[i][mapFields.get(ReportCsvFieldsEnum.NUM_ALBERI_SEME.getValue())]) &&
					StringUtils.isNumeric(data[i][mapFields.get(ReportCsvFieldsEnum.NUM_ALBERI_POLLONE.getValue())])) {
				ads = data[i][mapFields.get(ReportCsvFieldsEnum.ADS.getValue())];
				if(mapAds.get(ads) == null) {
					mapAds.put(ads, true);
				}
				specie = data[i][mapFields.get(ReportCsvFieldsEnum.SPECIE_CODE.getValue())];
				workingVet = mapSpecieSemePollone.get(specie);
				if(workingVet == null) {
					workingVet = new Double[] {0d,0d};//count seme, pollone
					mapSpecieSemePollone.put(specie, workingVet);
				}
				fattoreAds = Integer.parseInt(data[i][mapFields.get(ReportCsvFieldsEnum.FATTORE.getValue())]);
					workingVet[0] += (fattoreAds * Double.parseDouble((data[i][mapFields.get(ReportCsvFieldsEnum.NUM_ALBERI_SEME.getValue())])));
					workingVet[1] += (fattoreAds * Double.parseDouble((data[i][mapFields.get(ReportCsvFieldsEnum.NUM_ALBERI_POLLONE.getValue())])));
				
			}
		}
		mapSpecieSemePollone.put("ads", new Double[] {0d + mapAds.keySet().size(),0d});
		logger.debug("mapSpecieSemePollone size: " + mapSpecieSemePollone.keySet().size());
		return mapSpecieSemePollone;
	}
	
	private static Map<String,Double> getBasimetriaMediaSpecie(Map<String,Integer> mapFields, String[][] data){
		Map<String,Double> mapBasimetriaSpecie = new TreeMap<String,Double>();
		Map<String,Integer> mapCountBySpecie = new HashMap<String,Integer>();
		String specie;
		Double diametro;
		String diametroStr;
		Double basimetriaAlbero;
		for(int i = 0;i < data.length;i++) {
			//if("CAV".equals(data[i][mapFields.get(ReportCsvFieldsEnum.COD_TIPO_CAMPIONE.getValue())])) {
			specie = data[i][mapFields.get(ReportCsvFieldsEnum.SPECIE_CODE.getValue())];
			diametroStr = data[i][mapFields.get(ReportCsvFieldsEnum.DIAMETRO.getValue())];
			if(diametroStr.length() > 0) {
				diametro = Double.parseDouble(diametroStr);
				basimetriaAlbero = ReportUtil.getAreaBasimentricaAlbero(diametro);
				if(mapBasimetriaSpecie.get(specie) == null) {
					mapBasimetriaSpecie.put(specie, 0d);
					mapCountBySpecie.put(specie, 0);
				}
				mapBasimetriaSpecie.put(specie, mapBasimetriaSpecie.get(specie) + basimetriaAlbero);
				mapCountBySpecie.put(specie, mapCountBySpecie.get(specie) + 1);
			}
		//}
		}
		//calcolo basimetria media per specie
		for(String key : mapBasimetriaSpecie.keySet()) {
			mapBasimetriaSpecie.put(key, mapBasimetriaSpecie.get(key)/mapCountBySpecie.get(key));
		}
		return mapBasimetriaSpecie;
	}
	
	public static List<Map<String,Object>> basimetriaAreaSaggioAndTotali(Map<String,Integer> mapFields, String[][] data){
		Map<String, Double> mapAdsAlberiList = new HashMap<String, Double>();
		Map<String, Double> mapAdsAreaList = new HashMap<String, Double>();
		Map<String, Double> mapAdsVolumeList = new HashMap<String, Double>();
		Map<String, Double> mapAdsSuperficieList = new TreeMap<String, Double>();
		Map<String, Double> mapAdsCeppaieList = new HashMap<String, Double>();		
		Map<String, String> mapAdsPopolamentoList = new HashMap<String, String>();
		Map<String, String> mapAdsTipoList = new HashMap<String, String>();
		ReportUtil.specieNotManagedForVolume = new HashSet<String>();
		RegressioneLineare regrLin = ReportUtil.getRegressioneLineare(mapFields, data);
		Double appo = null;
		int countAlberiCavallettati = 0;
		Double diametroAlbero;
		String ads;
		String adsOld = null;
		int volumiNotFound = 0;
		logger.debug("totale dati size: " + data.length);
	
		for(int i = 0;i < data.length;i++) {
			if(i%10000 == 0) {
        		logger.debug("1- riga: " + i);
        	}
			if("CAV".equals(data[i][mapFields.get(ReportCsvFieldsEnum.COD_TIPO_CAMPIONE.getValue())])) {
				countAlberiCavallettati++;
				ads = data[i][mapFields.get(ReportCsvFieldsEnum.ADS.getValue())];
				if(adsOld == null) {
					adsOld = ads;
				}else {
					if(!adsOld.equals(ads)) {
						logger.debug("Volumi null per ads: " + adsOld + " -> " + volumiNotFound);
						logger.debug(adsOld + " AreaBasimetrica: " + mapAdsAreaList.get(adsOld));
						logger.debug(adsOld + " Superficie: " + mapAdsSuperficieList.get(adsOld));
						adsOld = ads;
						volumiNotFound = 0;
					}
				}
				
				try {
					Double.parseDouble(data[i][mapFields.get(ReportCsvFieldsEnum.RAGGIO.getValue())]);
				}catch(Exception ex) {
					logger.debug("Error riga: " + i +  " - value: >" + data[i][mapFields.get(ReportCsvFieldsEnum.RAGGIO.getValue())] + "<");
				}
				
				if(mapAdsAreaList.get(ads) == null) {
					logger.debug("Raggio: " + data[i][mapFields.get(ReportCsvFieldsEnum.RAGGIO.getValue())] +
							" - ADS" + ads);
					appo = ReportUtil.getSuperficieHa(
							Double.parseDouble(data[i][mapFields.get(ReportCsvFieldsEnum.RAGGIO.getValue())]), 
							Double.parseDouble(data[i][mapFields.get(ReportCsvFieldsEnum.INCLINAZIO.getValue())]));
					mapAdsSuperficieList.put(ads,appo);
					
					mapAdsAreaList.put(ads,0d);
					mapAdsVolumeList.put(ads,0d);
					mapAdsAlberiList.put(ads,0d);
					
					//CEPPAIE
					if(data[i][mapFields.get(ReportCsvFieldsEnum.CEPPAIE.getValue())].length() == 0) {
						data[i][mapFields.get(ReportCsvFieldsEnum.CEPPAIE.getValue())] = "0";
					}
					appo = Double.parseDouble(data[i][mapFields.get(ReportCsvFieldsEnum.CEPPAIE.getValue())]);
					mapAdsCeppaieList.put(ads,appo);
					
					mapAdsPopolamentoList.put(ads,
							data[i][mapFields.get(ReportCsvFieldsEnum.POPOLAMENTO.getValue())]);
					
					mapAdsTipoList.put(ads,
							data[i][mapFields.get(ReportCsvFieldsEnum.TIPO_FORESTALE.getValue())]);
				}
				diametroAlbero = Double.parseDouble(data[i][mapFields.get(ReportCsvFieldsEnum.DIAMETRO.getValue())]);
				appo = ReportUtil.getAreaBasimentricaAlbero(diametroAlbero);
				//logger.debug(ads + " - basimetrica: " + appo);
				appo+= mapAdsAreaList.get(ads);
				mapAdsAreaList.put(ads,appo);
				
				appo = ReportUtil.getVolumeAlbero(mapFields, data[i],regrLin.getAltezzaByDiametro(diametroAlbero));
				if(appo == 0) {
					volumiNotFound++;
				}
				appo+= mapAdsVolumeList.get(ads);
				mapAdsVolumeList.put(ads,appo);
				
				mapAdsAlberiList.put(ads, mapAdsAlberiList.get(ads) + 1);
			}
		}
		logger.debug("Volumi null per ads: " + adsOld + " -> " + volumiNotFound);
		
		List<Map<String,Object>> res = new ArrayList<Map<String,Object>>();
		Map<String,Object> row;
		Set<String> keys = mapAdsSuperficieList.keySet();
		Double totCeppaie = 0d;
		Double totAreaBasimetrica = 0d;
		Double totHa = 0d;
		Double totVolume = 0d;
		Double totAlberiHa = 0d;
		for(String key:keys) {
			row = new LinkedHashMap<String,Object>();
			res.add(row);
			row.put("Codice ads", key.replaceAll("\\.", ""));	
			row.put("Superficie (ha)", ReportUtil.format2(mapAdsSuperficieList.get(key)));
			row.put("Area basimetrica (m<sup>2</sup>/ha)", ReportUtil.format2(mapAdsAreaList.get(key)/mapAdsSuperficieList.get(key)));
			row.put("Volume (m<sup>3</sup>/ha)", ReportUtil.format2(mapAdsVolumeList.get(key)/mapAdsSuperficieList.get(key)));
			//row.put("Volume m<sup>3</sup>", ReportUtil.format2(mapAdsVolumeList.get(key)));
			row.put("Alberi/ha", ReportUtil.format2(mapAdsAlberiList.get(key)/mapAdsSuperficieList.get(key)));
			row.put("Ceppaie/ha", ReportUtil.format2(mapAdsCeppaieList.get(key)/mapAdsSuperficieList.get(key)));			
			row.put("Categoria forestale", mapAdsPopolamentoList.get(key));
			row.put("Tipo", mapAdsTipoList.get(key));
			
			totAreaBasimetrica += mapAdsAreaList.get(key);
			totVolume += mapAdsVolumeList.get(key);
			totCeppaie+= mapAdsCeppaieList.get(key);
			totHa+= mapAdsSuperficieList.get(key); 
			totAlberiHa+= mapAdsAlberiList.get(key)/mapAdsSuperficieList.get(key);
		}
		row = new LinkedHashMap<String,Object>();
		res.add(row);
		row.put("Codice ads", "Totale");	
		row.put("Superficie (ha)", "-");//ReportUtil.format2(totHa));
		row.put("Area basimetrica (m<sup>2</sup>/ha)", ReportUtil.format2(totAreaBasimetrica/totHa));
		row.put("Volume (m<sup>3</sup>/ha)", ReportUtil.format2(totVolume/totHa));
		//row.put("Volume m<sup>3</sup>","-");// ReportUtil.format2(totVolume));
//		row.put("Alberi/ha",ReportUtil.format2(totAlberiHa/keys.size()));// ReportUtil.format2(totVolume));
		row.put("Alberi/ha",ReportUtil.format2(countAlberiCavallettati/totHa));// ReportUtil.format2(totVolume));
		row.put("Ceppaie/ha", ReportUtil.format2(totCeppaie/totHa));			
		row.put("Categoria forestale", "-");
		row.put("Tipo", "-");
	
		//insert totali row
		row = new LinkedHashMap<String,Object>();
		res.add(0,row);
		row.put("Aree di saggio", keys.size());
		row.put("Alberi", countAlberiCavallettati);
		row.put("Numero ceppaie/ha", ReportUtil.format2(totCeppaie/totHa));
		return res;
	}
	
	private static Collection<Map<String,Object>> basimetriaSpecie(Map<String,Integer> mapFields, String[][] data) {
		
		Map<String, Double> mapSpecieAreaBasim = new TreeMap<String, Double>();
		Double basimetrica;
		Double basimetricaTotale = 0d;
		String specie;
		for(int i = 0;i < data.length;i++) {
			if("CAV".equals(data[i][mapFields.get(ReportCsvFieldsEnum.COD_TIPO_CAMPIONE.getValue())])) {
				specie = data[i][mapFields.get(ReportCsvFieldsEnum.SPECIE_CODE.getValue())];
				if(mapSpecieAreaBasim.get(specie) == null) {
					mapSpecieAreaBasim.put(specie,0d);
				}
				
				//set aree basimetriche m2 specie
				basimetrica = ReportUtil.getAreaBasimentricaAlbero(Double.parseDouble(data[i][mapFields.get(ReportCsvFieldsEnum.DIAMETRO.getValue())]));
				basimetricaTotale+= basimetrica;
				mapSpecieAreaBasim.put(specie,mapSpecieAreaBasim.get(specie) + basimetrica);
			}
		}
		List<Map<String,Object>> maps = new ArrayList<Map<String,Object>>();
		
		Map<String,Object> row;
		Double totPercent = 0d;
		String valueStr;
		Set<String>keysSpecie = mapSpecieAreaBasim.keySet();
		for (String keySpecie :  keysSpecie) {
			row = new LinkedHashMap<String,Object>();
			maps.add(row);
			row.put("Specie", keySpecie + " - " + ReportUtil.getMapSpecie().get(keySpecie));
			row.put("Area basimetrica (m<sup>2</sup>)", ReportUtil.format2(mapSpecieAreaBasim.get(keySpecie)));
			valueStr = ReportUtil.format2((mapSpecieAreaBasim.get(keySpecie)/basimetricaTotale)*100);
			row.put("Percentuali relative (%)", valueStr);	
			totPercent+= Double.parseDouble(valueStr.replace(",", "."));
		}
		row = new LinkedHashMap<String,Object>();
		maps.add(row);
		row.put("Specie", "Totale");
		row.put("Area basimetrica (m<sup>2</sup>)", ReportUtil.format2(basimetricaTotale));
		row.put("Percentuali relative (%)", "100");//ReportUtil.format2(totPercent));
		return maps;
	}
	
	private static Collection<Map<String,Object>> basimetriaQualitaAndVolumi(Map<String,Integer> mapFields, String[][] data) {
		//Double[0] = basimetrica qualita per ads; /Double[1] = volume qualita per ads
		Map<String, Map<String, Double[]>> mapAssettoQualita = new TreeMap<String, Map<String, Double[]>>();
		Map<String, Double> mapAssettoVolumi = new HashMap<String, Double>();
		Map<String, Double[]> workingMapAssetto;
		Double basimetricaTotale = 0d;
		Double volumeTotale = 0d;
		Double basimetricaQualita;
		Double basimetrica;
		Double volumeQualita;
		Double diametroAlbero;
		Double volume;
		String assetto;
		Double basimetriaTotP = 0d;
		Double basimetriaTotS = 0d;
		Double volumeTotP = 0d;
		Double volumeTotS = 0d;
		RegressioneLineare regrLin = ReportUtil.getRegressioneLineare(mapFields, data);
		logger.debug("RegrLin -> m: " + regrLin.getM() + " - q:" + regrLin.getQ());
		ReportUtil.specieNotManagedForVolume = new HashSet<String>();
		for(int i = 0;i < data.length;i++) {
			if("CAV".equals(data[i][mapFields.get(ReportCsvFieldsEnum.COD_TIPO_CAMPIONE.getValue())])) {
				assetto = data[i][mapFields.get(ReportCsvFieldsEnum.ASSETTO.getValue())];
				if(mapAssettoQualita.get(assetto) == null) {
					mapAssettoQualita.put(assetto,new TreeMap<String, Double[]>());
					mapAssettoVolumi.put(assetto, 0d);
				}
				workingMapAssetto = mapAssettoQualita.get(assetto);
				if(workingMapAssetto.get(data[i][mapFields.get(ReportCsvFieldsEnum.QUALITA.getValue())]) == null){
					workingMapAssetto.put(data[i][mapFields.get(ReportCsvFieldsEnum.QUALITA.getValue())],new Double[] {0d,0d});
				}
				//set basimetrica qualita per ads
				basimetricaQualita = workingMapAssetto.get(data[i][mapFields.get(ReportCsvFieldsEnum.QUALITA.getValue())])[0];
				diametroAlbero = Double.parseDouble(data[i][mapFields.get(ReportCsvFieldsEnum.DIAMETRO.getValue())]);
				basimetrica = ReportUtil.getAreaBasimentricaAlbero(diametroAlbero);//  Math.pow(diametroAlbero/200,2)*3.14159d;xxx
				basimetricaQualita+= basimetrica;
				workingMapAssetto.get(data[i][mapFields.get(ReportCsvFieldsEnum.QUALITA.getValue())])[0] = basimetricaQualita;
				basimetricaTotale+= basimetrica;
				
				//set volume qualita per ads
				volumeQualita = workingMapAssetto.get(data[i][mapFields.get(ReportCsvFieldsEnum.QUALITA.getValue())])[1];
				volume = ReportUtil.getVolumeAlbero(mapFields, data[i],regrLin.getAltezzaByDiametro(diametroAlbero));
				volumeQualita+= volume;
				workingMapAssetto.get(data[i][mapFields.get(ReportCsvFieldsEnum.QUALITA.getValue())])[1] = volumeQualita;
				mapAssettoVolumi.put(assetto, 
						mapAssettoVolumi.get(assetto) + volume);
				volumeTotale += volume;
			}
		}
		List<Map<String,Object>> maps = new ArrayList<Map<String,Object>>();
		
		Map<String,Object> row;
		Set<String>keysAssetto = mapAssettoQualita.keySet();
		Set<String>keysQualita;
		for (String keyAssetto :  keysAssetto) {
			workingMapAssetto = mapAssettoQualita.get(keyAssetto);
			keysQualita = workingMapAssetto.keySet();
			for(String keyQualita :  keysQualita) {
				row = new LinkedHashMap<String,Object>();
				maps.add(row);
				row.put("Assetto", keyAssetto);
				row.put("Qualita'", "S".equals(keyQualita)?"Seme":("P".equals(keyQualita)?"Pollone":"-"));
				basimetricaQualita = workingMapAssetto.get(keyQualita)[0] * 100 / basimetricaTotale;
				row.put("Area basimetrica (%)", ReportUtil.format2(basimetricaQualita));
				volumeQualita = workingMapAssetto.get(keyQualita)[1] * 100 / volumeTotale;
				row.put("Volumi (%)", ReportUtil.format2(volumeQualita));
				if("P".equals(keyQualita)) {
					basimetriaTotP += basimetricaQualita;
					volumeTotP += volumeQualita;
				}else {
					basimetriaTotS += basimetricaQualita;
					volumeTotS += volumeQualita;
				}
			}
		}
		//set totali qualita P
		row = new LinkedHashMap<String,Object>();
		maps.add(row);
		row.put("Assetto", "Totale");
		row.put("Qualita'", "Pollone");
		row.put("Area basimetrica (%)", ReportUtil.format2(basimetriaTotP));
		row.put("Volumi (%)", ReportUtil.format2(volumeTotP));
		
		//set totali qualita S
		row = new LinkedHashMap<String,Object>();
		maps.add(row);
		row.put("Assetto", "Totale");
		row.put("Qualita'", "Seme");
		row.put("Area basimetrica (%)", ReportUtil.format2(basimetriaTotS));
		row.put("Volumi (%)", ReportUtil.format2(volumeTotS));
		return maps;
	}


}
