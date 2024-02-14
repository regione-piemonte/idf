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
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import it.csi.idf.idfapi.dto.RegressioneLineare;
import it.csi.idf.idfapi.util.TipoAdsEnum;

public class ReportUtilVolumi {
	
	static final Logger logger = Logger.getLogger(ReportUtilVolumi.class);
	
	public static Map<String,Object> calcoloVolumi(Map<String,Integer> mapFields, String[][] data, TipoAdsEnum adsType){
		
//		return calcoloVolumiSuperficieNota(mapFields, data, adsType);
		if(TipoAdsEnum.SUPERFICIE_NOTA == adsType) {
			return calcoloVolumiSuperficieNota(mapFields, data, adsType);
		}else {
			return calcoloVolumiRelascopica(mapFields, data, adsType);
		}
	}
	
	private static Map<String,Object> calcoloVolumiSuperficieNota(Map<String,Integer> mapFields, String[][] data, TipoAdsEnum adsType){
		Map<String,Object> res = new HashMap<String,Object>();
		Map<String,Object> diametriData = new HashMap<String,Object>();
		res.put("volumi", diametriData);
		Map<String,Object> classiDiametriche = getTablesClassiDiametriche(data, mapFields);
		diametriData.put("classiDiametrichePercentuali",classiDiametriche.get("classiDiametrichePercentuali"));
		diametriData.put("classiDiametricheM3",classiDiametriche.get("classiDiametricheM3"));
		diametriData.put("specieList",classiDiametriche.get("specieList"));
		res.put("riepilogo",ReportUtil.getRiepilogo("Volumi", mapFields, data, adsType));
		res.put("infoDatiCampione",ReportUtil.getDatiCampione(mapFields, data, adsType));
		return res;
	}
	
	private static Map<String,Object> calcoloVolumiRelascopica(Map<String,Integer> mapFields, String[][] data, TipoAdsEnum adsType){
		Map<String,Object> res = new HashMap<String,Object>();
		res.put("riepilogo",ReportUtil.getRiepilogo("Volumi", mapFields, data, adsType));
		res.put("infoDatiCampione",ReportUtil.getDatiCampione(mapFields, data, adsType));
		res.put("volumi", getDatiVolumiPerSpecie(mapFields, data, false));	 
		return res;
	}
	
	private static Map<String,Object> getTablesClassiDiametriche(String[][] data, 
			Map<String,Integer> mapFields){
		
		Map<String,Map<String,Double[]>> mapClassi = new TreeMap<String,Map<String,Double[]>>();
		Map<String,Double[]> workingMapClass;
		
		Integer classe;
		String classeStr;
		String specie;
		Double diametroAlbero;
		String diametroStr;
		Integer numAlberi = 0;
		Collection<String> specieSet = new TreeSet<String>();
		RegressioneLineare regrLin = ReportUtil.getRegressioneLineare(mapFields, data);
		for(String[]row : data) {
			diametroStr = row[mapFields.get(ReportCsvFieldsEnum.DIAMETRO.getValue())];
			if("CAV".equals(row[mapFields.get(ReportCsvFieldsEnum.COD_TIPO_CAMPIONE.getValue())]) && diametroStr.length() > 0) {
				numAlberi++;
				//Gestione valori per classe **************************************************************************************
				diametroAlbero = Double.parseDouble(diametroStr);
				classe = ReportUtil.getClasse(diametroAlbero, 5);
				//classe = classe > 80?0:classe;//classe max 80
				if(classe > 80) {
					classeStr = ">80";
				}else {
					classeStr = classe.toString();
				}
				if(mapClassi.get(classeStr) == null) {
					mapClassi.put(classeStr,new HashMap<String, Double[]>());
				}
				workingMapClass = mapClassi.get(classeStr);
				specie = row[mapFields.get(ReportCsvFieldsEnum.SPECIE_CODE.getValue())];
				specieSet.add(specie);
				if(workingMapClass.get(specie) == null) {
					workingMapClass.put(specie, new Double[] {0d,0d}); //Double[0]numero alberi - Double[1]->volume
				}
				workingMapClass.get(specie)[0] += 1;
				workingMapClass.get(specie)[1] += ReportUtil.getVolumeAlbero(diametroAlbero, regrLin.getAltezzaByDiametro(diametroAlbero),specie);
			}
		}

		Set<String> classi = mapClassi.keySet();
		Collection<Map<String,String>> resClassiDiametriche = new HashSet<Map<String,String>>();
		Collection<Map<String,String>> resClassiDiametricheM3 = new HashSet<Map<String,String>>();
		Map<String,String> rowPerc;
		Map<String,String> rowM3;
		
		Map<String,String> rowSpecieTot = new LinkedHashMap<String,String>();
		Map<String,String> rowM3SpecieTot = new LinkedHashMap<String,String>();
		rowSpecieTot.put("Classi diametriche", "Totale");
		rowM3SpecieTot.put("Classi diametriche", "Totale");
		
		
		Double[] specieValues;
		Double totClasse;
		Double totClasseM3;
		Double totClasseSum = 0d;
		Double totClasseM3Sum = 0d;
		Double value;
		String valueFormated;
		for(String valClasse : classi) {
			rowPerc = new LinkedHashMap<String,String>();
			totClasse = 0d;
			totClasseM3 = 0d;
			rowM3 = new LinkedHashMap<String,String>();
			resClassiDiametriche.add(rowPerc);
			resClassiDiametricheM3.add(rowM3);
			rowPerc.put("Classi diametriche", valClasse);
			rowM3.put("Classi diametriche", valClasse);
			workingMapClass = mapClassi.get(valClasse);
			for(String valSpecie : specieSet) {
				specieValues = workingMapClass.get(valSpecie);
				if(specieValues == null) {
					specieValues = new Double[] {0d,0d};
				}
				//volumi percent
				value = 100 * specieValues[0] / numAlberi;
				rowPerc.put(valSpecie , ReportUtil.format2(value));
				totClasse+= value;
				updateTotaleSpecie(rowSpecieTot,valSpecie,rowPerc.get(valSpecie).toString());
				
				//volumi m3
				value = specieValues[1];
				rowM3.put(valSpecie , specieValues[0] > 0 && specieValues[1] == 0?"N.D.":ReportUtil.format2(value));
				totClasseM3+= value;
				updateTotaleSpecie(rowM3SpecieTot,valSpecie,rowM3.get(valSpecie).toString());
			}
			//add totali per specie
			
			valueFormated = ReportUtil.format2(totClasse);
			rowPerc.put("Totale" , valueFormated);
			totClasseSum += Double.parseDouble(valueFormated.replace(".", "").replace(",", "."));
			
			valueFormated = ReportUtil.format2(totClasseM3);
			rowM3.put("Totale" , valueFormated);
			totClasseM3Sum += Double.parseDouble(valueFormated.replace(".", "").replace(",", "."));
		}
		
		rowSpecieTot.put("Totale" ,ReportUtil.format2(totClasseSum));
		resClassiDiametriche.add(rowSpecieTot);
		
		logger.info("totClasseM3Sum: " + totClasseM3Sum);
		rowM3SpecieTot.put("Totale" ,ReportUtil.format2(totClasseM3Sum));
		resClassiDiametricheM3.add(rowM3SpecieTot);
		
		Collection<String> specieFilterSet = new TreeSet<String>();
		for(String valSpecie : specieSet) {
			specieFilterSet.add(valSpecie + " - " + ReportUtil.getMapSpecie().get(valSpecie));
		}
		
		//Modifica non programmata, percentuale su volume e non su alberi come prima
		resClassiDiametriche = calcolaPercentualiSuSingoloVolume(resClassiDiametricheM3,totClasseM3Sum);
		
		Map<String,Object> res = new HashMap<String,Object>(); 
		res.put("classiDiametrichePercentuali", resClassiDiametriche);
		res.put("classiDiametricheM3", resClassiDiametricheM3);
		res.put("specieList", specieFilterSet);
		
		return res;
	}
	
	private static Collection<Map<String,String>> calcolaPercentualiSuSingoloVolume(
			Collection<Map<String,String>> listVolumi, Double volumeTot){
		Collection<Map<String,String>> res = new HashSet<Map<String,String>>();
		Set<String> keys;
		Map<String,String> row;
		boolean isFirsField;
		Double volume;
		for(Map<String,String> item : listVolumi) {
			keys = item.keySet();
			row = new LinkedHashMap<String,String>();
			res.add(row);
			isFirsField = true;
			for(String key : keys) {
				if(isFirsField) {
					row.put(key,item.get(key));
					isFirsField = false;
				}else if(!"N.D.".equals(item.get(key))){
					volume = Double.parseDouble(item.get(key).replace(".", "").replace(",", "."));
					row.put(key,ReportUtil.format2((volume/volumeTot)*100));
				}
			}
		}
		return res;
	}
	
	
	private static void updateTotaleSpecie(Map<String,String> row, String specie, String value) {
		if(row.get(specie) == null) {
			row.put(specie, value);
		}else if(!"N.D.".equals(row.get(specie).toString())){
			Double val = Double.parseDouble(row.get(specie).toString().replace(".", "").replace(",", "."));
			row.put(specie, (ReportUtil.format2(val + Double.parseDouble(value.replace(".", "").replace(",", ".")))));
		}
	}

	public static Map<String,Object> getDatiVolumiPerSpecie(Map<String,Integer> mapFields, String[][] data, boolean isGetVolumeTot) {
		Map<String,Double> mapConteggiSpecie = new TreeMap<String,Double>();
		Map<String,Double> mapConteggiCavallettati = new TreeMap<String,Double>();
		Map<String,Integer> mapConteggiCavSpecie = new TreeMap<String,Integer>();
		Set<String> adsList = new TreeSet<String>();
		String specie;
		String diametroStr;
		Double contSeme;
		Double contPollone;
		Double fattoreNum;
		Double areaSingoloAlbero;
		for(String[]row : data) {
			specie = row[mapFields.get(ReportCsvFieldsEnum.SPECIE_CODE.getValue())];
			
			//conteggi specie
			if(StringUtils.isNumeric(row[mapFields.get(ReportCsvFieldsEnum.NUM_ALBERI_SEME.getValue())]) &&
					StringUtils.isNumeric(row[mapFields.get(ReportCsvFieldsEnum.NUM_ALBERI_POLLONE.getValue())])) {
				adsList.add(row[mapFields.get(ReportCsvFieldsEnum.ADS.getValue())]);
				
				contSeme = Double.parseDouble(row[mapFields.get(ReportCsvFieldsEnum.NUM_ALBERI_SEME.getValue())]);
				contPollone = Double.parseDouble(row[mapFields.get(ReportCsvFieldsEnum.NUM_ALBERI_POLLONE.getValue())]);
				fattoreNum = Double.parseDouble(row[mapFields.get(ReportCsvFieldsEnum.FATTORE.getValue())]);
				if(mapConteggiSpecie.get(specie) == null) {
					mapConteggiSpecie.put(specie, (contSeme + contPollone) * fattoreNum);
				}else {
					mapConteggiSpecie.put(specie, mapConteggiSpecie.get(specie) + (contSeme + contPollone) * fattoreNum);
				}
			}
			
			//area basimetrica da cavallettamento
			diametroStr = row[mapFields.get(ReportCsvFieldsEnum.DIAMETRO.getValue())];
			if("CAV".equals(row[mapFields.get(ReportCsvFieldsEnum.COD_TIPO_CAMPIONE.getValue())]) && diametroStr.length() > 0) {
				if(mapConteggiCavallettati.get(specie) == null) {
					mapConteggiCavallettati.put(specie, 0d);
					mapConteggiCavSpecie.put(specie, 0);
				}
				areaSingoloAlbero = ReportUtil.getAreaBasimentricaAlbero(Double.parseDouble(diametroStr));
				mapConteggiCavallettati.put(specie, mapConteggiCavallettati.get(specie) + areaSingoloAlbero);
				mapConteggiCavSpecie.put(specie,mapConteggiCavSpecie.get(specie) + 1);
			}
			
		}
		int totAds = adsList.size();
		Double totRelascopicaNotRound = 0d;
		for(String spec : mapConteggiSpecie.keySet()) {
			mapConteggiSpecie.put(spec,mapConteggiSpecie.get(spec)/totAds);
			totRelascopicaNotRound += mapConteggiSpecie.get(spec);
		}
		
		Double totMediaCavNotRound = 0d;
		for(String spec : mapConteggiCavallettati.keySet()) {
			mapConteggiCavallettati.put(spec, mapConteggiCavallettati.get(spec)/mapConteggiCavSpecie.get(spec));
			totMediaCavNotRound += mapConteggiCavallettati.get(spec);
		}
		
		List<Map<String,String>> volumiPerSpecie = new ArrayList<Map<String,String>>();
		Map<String,String> row;
		Double diametroMedio;
		Double altezzaMedia;
		Double numAlberi;
		Double volumeSpecie;
		Double volumeTot = 0d;
		int count = 0;
		RegressioneLineare regrLin = ReportUtil.getRegressioneLineare(mapFields, data);
		String[] labels = new String[] 
				{"Specie","Diametro medio (cm)","Altezza media (m)","N. alberi/ha","Volume (m<sup>3</sup>/ha)","Volume (%)"};
		for(String spec : mapConteggiSpecie.keySet()) {
			row = new LinkedHashMap<String,String>();
			volumiPerSpecie.add(row);
			row.put(labels[0], spec);
			if(mapConteggiCavallettati.get(spec) == null) {
				row.put(labels[1], "");
				row.put(labels[2], "");
				row.put(labels[3], "");
				row.put(labels[4], "");
				row.put(labels[5], "");
				
			}else {
				count++;
				diametroMedio = getDiametroMedio(mapConteggiCavallettati.get(spec));
				row.put(labels[1], ReportUtil.format2(diametroMedio));
				altezzaMedia = regrLin.getAltezzaByDiametro(diametroMedio);
				row.put(labels[2], ReportUtil.format2(altezzaMedia));
				numAlberi = mapConteggiSpecie.get(spec)/mapConteggiCavallettati.get(spec);
				row.put(labels[3], ReportUtil.format(numAlberi,0));
				volumeSpecie = ReportUtil.getVolumeAlbero(diametroMedio, altezzaMedia, spec) * numAlberi;
				row.put(labels[4], ReportUtil.format2(volumeSpecie));
				volumeTot += volumeSpecie;
				row.put(labels[5], volumeSpecie.toString());
			}
		}
		
		Double totDiametroMedio = 0d;
		Double totAltezzaMedia = 0d;
		Double totVolumeHa = 0d;
		Double totVolumePerc = 0d;
		
		List<String> graficoLabels = new ArrayList<String>();
		List<String> graficoValues = new ArrayList<String>();
		
		for(Map<String,String> row_ : volumiPerSpecie) {
			if(row_.get(labels[1]).length() > 0) {
				graficoLabels.add(row_.get(labels[0]));
				graficoValues.add(row_.get(labels[4]));
				
				totDiametroMedio = updateTotale(totDiametroMedio, row_.get(labels[1]));
				totAltezzaMedia = updateTotale(totAltezzaMedia, row_.get(labels[2]));
				totVolumeHa = updateTotale(totVolumeHa, row_.get(labels[4]));
				row_.put(labels[5], ReportUtil.format2(100 * Double.parseDouble(row_.get(labels[5]))/volumeTot));
				totVolumePerc = updateTotale(totVolumePerc, row_.get(labels[5]));
			}
		}
		
		Map<String,Object> res = new HashMap<String,Object>();
		
		if(isGetVolumeTot) {
			res.put("VolumeTot", totVolumeHa);
			return res;
		}
		
		row = new LinkedHashMap<String,String>();
		volumiPerSpecie.add(row);
		row.put(labels[0], "Totale");
		row.put(labels[1], ReportUtil.format2(totDiametroMedio/count));
		row.put(labels[2], ReportUtil.format2(totAltezzaMedia/count));
		row.put(labels[3], ReportUtil.format(totRelascopicaNotRound/(totMediaCavNotRound/count),0));
		row.put(labels[4], ReportUtil.format2(totVolumeHa));
		row.put(labels[5], "100");//ReportUtil.format2(totVolumePerc));
		
		res.put("volumiPerSpecie",volumiPerSpecie);
		
		Map<String,Object> mapGrafico = new HashMap<String,Object>();
		mapGrafico.put("labels", graficoLabels);
		mapGrafico.put("values", graficoValues);
		res.put("graficoVolumiPerSpecie",mapGrafico);	
		return res;
	}
	
	private static Double updateTotale(Double totale, String value) {
		return totale + Double.parseDouble(value.replace(".", "").replace(",", "."));
	}
	
	private static Double getDiametroMedio(Double mediaDaCavallettamento){
		return Math.sqrt(mediaDaCavallettamento/ReportUtil.PI) * 200;
	}

}
