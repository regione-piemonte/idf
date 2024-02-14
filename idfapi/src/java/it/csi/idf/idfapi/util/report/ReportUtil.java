/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util.report;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Locale.Category;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import it.csi.idf.idfapi.dto.RegressioneLineare;
import it.csi.idf.idfapi.dto.RicadenzaInformazioni;
import it.csi.idf.idfapi.util.TipoAdsEnum;

public class ReportUtil {
	
	static final Logger logger = Logger.getLogger(ReportUtil.class);
	
	private static Map<String,String> mapSpecie =  null;
	
	private static boolean isEnabledLog = false;
	
	static Set<String> specieNotManagedForVolume;
	
	public static final Double PI = 3.14159d;

	private static DecimalFormat decimalFormat = new DecimalFormat("##0.##");
	
	static RegressioneLineare getRegressioneLineare(Map<String,Integer> mapFields, String[][] data) {
		List<Double> listDiametriLn = new ArrayList<Double>();
		List<Double> listAltezze = new ArrayList<Double>();
		String diametroStr;
		String altezzaStr;
		Double diametro;
		Double altezza;
		for(String[] row: data) {
			diametroStr = row[mapFields.get(ReportCsvFieldsEnum.DIAMETRO.getValue())];
			altezzaStr = row[mapFields.get(ReportCsvFieldsEnum.ALTEZZA.getValue())];	
			if(diametroStr.length() > 0 && altezzaStr.length() > 0) {
				diametro = Double.parseDouble(diametroStr);
				altezza = Double.parseDouble(altezzaStr);
				if(diametro > 0 && altezza > 0) {
					listDiametriLn.add(Math.log(diametro));
					listAltezze.add(altezza);
				}
			}
		}
		return new RegressioneLineare(listDiametriLn, listAltezze);
	}
	
	static Double getVolumeAlbero(Map<String,Integer> mapFields, String[] data) {
		String altezzaStr = data[mapFields.get(ReportCsvFieldsEnum.ALTEZZA.getValue())];
		Double altezza = altezzaStr.length()>0? Double.parseDouble(altezzaStr):0d;
		return getVolumeAlbero(mapFields, data,altezza);
	}
			
	static Double getVolumeAlbero(Map<String,Integer> mapFields, String[] data, Double altezza) {
		Double diametro = Double.parseDouble(data[mapFields.get(ReportCsvFieldsEnum.DIAMETRO.getValue())]);
		String specieStr = data[mapFields.get(ReportCsvFieldsEnum.SPECIE_CODE.getValue())].toUpperCase().replace(" ", "_");
		return getVolumeAlbero(diametro, altezza, specieStr);
	}
		
	static Double getVolumeAlbero(Double diametro, Double altezza, String specieStr) {
		
		ReportSpecieEnum specie = null;
		try {
			specie = ReportSpecieEnum.valueOf(specieStr);
		}catch(IllegalArgumentException ex) {
			if(specieNotManagedForVolume!= null && !specieNotManagedForVolume.contains(specieStr)) {
				specieNotManagedForVolume.add(specieStr);
				logger.error("IllegalArgumentException for: " + specieStr);
			}
		}
		Double res = 0d; 
		String formula = "not found";
		if(specie != null) {
			switch(specie) {
				case AG:
				case ON:
				case OV:
					formula = "(-2.2932*10+3.2641*Math.pow(12,-2)*Math.pow(diametro,2)*altezza+2.9991*diametro)/1000";
					res =   (-2.2932*10+3.2641*Math.pow(12,-2)*Math.pow(diametro,2)*altezza+2.9991*diametro)/1000; 
					break;
				case AE:
				case AL:
				case AN:
				case AR:
				case CG:
				case CT:
				case IA:
				case JR:
				case LA:
				case MS:
				case PD:
				case PL:
				case PO:
				case PT:
				case PV:
				case PW:
				case PY:
				case PZ:
				case SA:
				case SD:
				case SN:
				case ST:
				case SU:
				case TC:
				case TP:
				case UC:
				case UG:
				case UM:
				case UP:
					formula = "(2.3118+3.1278*Math.pow(10,-2)*Math.pow(diametro,2)*altezza+3.7159*Math.pow(10,-1)*diametro)/1000";
					res = (2.3118+3.1278*Math.pow(10,-2)*Math.pow(diametro,2)*altezza+3.7159*Math.pow(10,-1)*diametro)/1000; 
					break;
				case PU:
				case PX:
				case TB:
					formula = "(2.6279+3.3389*Math.pow(10,-2)*Math.pow(diametro,2)*altezza)/1000";
					res = (2.6279+3.3389*Math.pow(10,-2)*Math.pow(diametro,2)*altezza)/1000; 
					break;
				case LD:
					formula = "(-1.6519*10+2.9979*Math.pow(10,-2)*Math.pow(diametro,2)*altezza+3.1506*diametro)/1000";
					res = (-1.6519*10+2.9979*Math.pow(10,-2)*Math.pow(diametro,2)*altezza+3.1506*diametro)/1000;
					break;
				case DU:
					formula = "(-7.9946+3.3343*Math.pow(10,-2)*Math.pow(diametro,2)*altezza+1.2186*diametro)/1000";
					res = (-7.9946+3.3343*Math.pow(10,-2)*Math.pow(diametro,2)*altezza+1.2186*diametro)/1000;
					break;
				case PH:
					formula = "(-1.2508*Math.pow(10,-1)+3.1518*Math.pow(10,-2)*Math.pow(diametro,2)*altezza+2.3748*diametro)/1000";
					res = (-1.2508*Math.pow(10,-1)+3.1518*Math.pow(10,-2)*Math.pow(diametro,2)*altezza+2.3748*diametro)/1000;
					break;
				case AI:
					formula = "(-2.2932*10+3.2641*Math.pow(10,-2)*Math.pow(diametro,2)*altezza+2.9991*diametro)/1000";
					res = (-2.2932*10+3.2641*Math.pow(10,-2)*Math.pow(diametro,2)*altezza+2.9991*diametro)/1000;
					break;
				case AC:
				case ES:
				case PA:
					formula = "(-9.1298+3.4866*Math.pow(10,-2)*Math.pow(diametro,2)*altezza+1.4633*diametro)/1000";
					res = (-9.1298+3.4866*Math.pow(10,-2)*Math.pow(diametro,2)*altezza+1.4633*diametro)/1000;
					break;
				case AM:
				case AO:
				case AP:
				case AT:
				case AU:
				case AX:
					formula = "(1.6905+3.7082*Math.pow(10,-2)*Math.pow(diametro,2)*altezza)/1000";
					res = (1.6905+3.7082*Math.pow(10,-2)*Math.pow(diametro,2)*altezza)/1000;
					break;
	
				case RP:
					formula = "(-2.1214+3.7123*Math.pow(10,-2)*Math.pow(diametro,2)*altezza+1.4296*Math.pow(10,-1)*diametro)/1000";
					res = (-2.1214+3.7123*Math.pow(10,-2)*Math.pow(diametro,2)*altezza+1.4296*Math.pow(10,-1)*diametro)/1000;
					break;
				case PN:
					formula = "(-2.148*10+3.3448*Math.pow(10,-2)*Math.pow(diametro,2)*altezza+2.9088*diametro)/1000";
					res = (-2.148*10+3.3448*Math.pow(10,-2)*Math.pow(diametro,2)*altezza+2.9088*diametro)/1000;
					break;
				case CS:
					formula = "(-2.001+3.6524*Math.pow(10,-2)*Math.pow(diametro,2)*altezza+7.4466*Math.pow(10,-1)*diametro)/1000";
					res = (-2.001+3.6524*Math.pow(10,-2)*Math.pow(diametro,2)*altezza+7.4466*Math.pow(10,-1)*diametro)/1000;
					break;
				case QC:
					formula = "(-4.3221*Math.pow(10,-2)+3.8079*Math.pow(10,-2)*Math.pow(diametro,2)*altezza)/1000";
					res = (-4.3221*Math.pow(10,-2)+3.8079*Math.pow(10,-2)*Math.pow(diametro,2)*altezza)/1000;
					break;
				case AA:
					formula = "(-1.8381+3.7836*Math.pow(10,-2)*Math.pow(diametro,2)*altezza+3.9934*Math.pow(10,-1)*diametro)/1000";
					res = (-1.8381+3.7836*Math.pow(10,-2)*Math.pow(diametro,2)*altezza+3.9934*Math.pow(10,-1)*diametro)/1000;
					break;
				case PM:
					formula = "(2.9963+3.8302*Math.pow(10,-2)*Math.pow(diametro,2)*altezza)/1000";
					res = (2.9963+3.8302*Math.pow(10,-2)*Math.pow(diametro,2)*altezza)/1000;
					break;
				case CB:
				case OC:
					formula = "(-1.4983+3.8828*Math.pow(10,-2)*Math.pow(diametro,2)*altezza)/1000";
					res = (-1.4983+3.8828*Math.pow(10,-2)*Math.pow(diametro,2)*altezza)/1000;
					break;
				case SC:
				case SX:
					formula = "(-2.314+3.8926*Math.pow(10,-2)*Math.pow(diametro,2)*altezza)/1000";
					res = (-2.314+3.8926*Math.pow(10,-2)*Math.pow(diametro,2)*altezza)/1000;
					break;
				case BP:
					formula = "0.001614+0.372428*Math.pow(10,-4)*Math.pow(diametro,2)*altezza+0.959885*Math.pow(10,-3)*diametro+" + 
							"-0.240608*Math.pow(10,-3)*altezza";
					res = 0.001614+0.372428*Math.pow(10,-4)*Math.pow(diametro,2)*altezza+0.959885*Math.pow(10,-3)*diametro+
					-0.240608*Math.pow(10,-3)*altezza;
					break;
				case FO:
					formula = "(-1.1137+3.9108*Math.pow(10,-2)*Math.pow(diametro,2)*altezza)/1000";
					res = (-1.1137+3.9108*Math.pow(10,-2)*Math.pow(diametro,2)*altezza)/1000;
					break;
				case FS:
					formula = "(8.1151*Math.pow(10,-1)+3.8965*Math.pow(10,-2)*Math.pow(diametro,2)*altezza)/1000";
					res = (8.1151*Math.pow(10,-1)+3.8965*Math.pow(10,-2)*Math.pow(diametro,2)*altezza)/1000;
					break;
				case FE:
					formula = "(-1.1137*Math.pow(10,-1)+3.9108*Math.pow(10,-2)*Math.pow(diametro,2)*altezza)/1000";
					res = (-1.1137*Math.pow(10,-1)+3.9108*Math.pow(10,-2)*Math.pow(diametro,2)*altezza)/1000;
					break;
				case PC:
					formula = "(2.8521+3.9504*Math.pow(10,-2)*Math.pow(diametro,2)*altezza)/1000";
					res = (2.8521+3.9504*Math.pow(10,-2)*Math.pow(diametro,2)*altezza)/1000;
					break;
				case PS:
					formula = "(3.1803+3.9899*Math.pow(10,-2)*Math.pow(diametro,2)*altezza)/1000";
					res = (3.1803+3.9899*Math.pow(10,-2)*Math.pow(diametro,2)*altezza)/1000;
					break;
				case QD:
				case QF:
				case QR:
				case QX:
					formula = "0.307106*Math.pow(10,-4)*Math.pow(diametro,2)*altezza+0.172373*Math.pow(10,-3)*diametro+" + 
							"-0.252758*Math.pow(10,-3)*altezza+0.585386*Math.pow(10,-4)*Math.pow(diametro,2)" + 
							"+-0.120911*Math.pow(10,-4)*Math.pow(altezza,2)+0.113982*Math.pow(10,-5)*diametro*Math.pow(altezza,2)+" + 
							"0.15238*Math.pow(10,-7)*Math.pow(diametro,2)*Math.pow(altezza,2)+" + 
							"0.488191*Math.pow(10,-5)*Math.pow(diametro,3)+0.397981*Math.pow(10,-9)*Math.pow(diametro,3)*Math.pow(altezza,2)";
					res = 0.307106*Math.pow(10,-4)*Math.pow(diametro,2)*altezza+0.172373*Math.pow(10,-3)*diametro+
					-0.252758*Math.pow(10,-3)*altezza+0.585386*Math.pow(10,-4)*Math.pow(diametro,2)
					+-0.120911*Math.pow(10,-4)*Math.pow(altezza,2)+0.113982*Math.pow(10,-5)*diametro*Math.pow(altezza,2)+
					0.15238*Math.pow(10,-7)*Math.pow(diametro,2)*Math.pow(altezza,2)+
					0.488191*Math.pow(10,-5)*Math.pow(diametro,3)+0.397981*Math.pow(10,-9)*Math.pow(diametro,3)*Math.pow(altezza,2);
					break;
				case QI:
					formula = "(-2.2219+3.9685*Math.pow(10,-2)*Math.pow(diametro,2)*altezza+6.2762*Math.pow(10,-1)*diametro)/1000";
					res = (-2.2219+3.9685*Math.pow(10,-2)*Math.pow(diametro,2)*altezza+6.2762*Math.pow(10,-1)*diametro)/1000;
					break;
				case PP:
					formula = "(-4.0404*Math.pow(10,-1)+4.1113*Math.pow(10,-2)*Math.pow(diametro,2)*altezza)/1000";
					res = (-4.0404*Math.pow(10,-1)+4.1113*Math.pow(10,-2)*Math.pow(diametro,2)*altezza)/1000;
					break;
					//=(2,9963+3,8302*10^-2*D28^2*E28)/1000
				case PR:
					formula = "(2.9963+3.8302*Math.pow(10,-2)*Math.pow(diametro,2)*altezza)/1000";
					res = (2.9963+3.8302*Math.pow(10,-2)*Math.pow(diametro,2)*altezza)/1000;
					break;
				case QP:
					formula = "(5.1025*Math.pow(10,-1)+4.5184*Math.pow(10,-2)*Math.pow(diametro,2)*altezza+" + 
							"-3.6026*Math.pow(10,-1)*diametro)/1000";
					res = (5.1025*Math.pow(10,-1)+4.5184*Math.pow(10,-2)*Math.pow(diametro,2)*altezza+
							-3.6026*Math.pow(10,-1)*diametro)/1000;
					break;
			}
		}
		res = Math.abs(res);
		if(isEnabledLog) {
			logger.info("Specie: " + specieStr + " - Diametro: " + diametro + " - Altezza: " + altezza 
					+ " - Volume: " + res + " - Formula: " + formula );
		}
		return res;
	}
	

	static Map<String,Object> getRiepilogo(String lavorazione,Map<String,Integer> mapFields, String[][] data, TipoAdsEnum adsType) {
		Collection<String> totAds = new HashSet<String>();
		int countAlberiCavallettati = 0;
		Integer idCodTipoCampione = mapFields.get(ReportCsvFieldsEnum.COD_TIPO_CAMPIONE.getValue());
		Integer idDiametro = mapFields.get(ReportCsvFieldsEnum.DIAMETRO.getValue());
		for(String[] item:data) {
			if(idCodTipoCampione != null && "CAV".equals(item[idCodTipoCampione]) 
					&& (idDiametro == null || ( item[idDiametro].length() > 0 && Double.parseDouble(item[idDiametro]) > 0) )) {
				countAlberiCavallettati++;
			}
			totAds.add(item[mapFields.get(ReportCsvFieldsEnum.ADS.getValue())]);
		}
		Map<String,Object> mapRiepilogo = new LinkedHashMap<String,Object>();
		mapRiepilogo.put("lavorazione",lavorazione);
		mapRiepilogo.put("ads",totAds.size());
		mapRiepilogo.put("alberi", TipoAdsEnum.RELASCOPICA_SEMPLICE == adsType?"N/A":countAlberiCavallettati);
		return mapRiepilogo;
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();
	 
	    BigDecimal bd = new BigDecimal(Double.toString(value));
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	static double round3(double value) {
		try {
	    return round(value, 3);
		}catch(NumberFormatException ex) {
			logger.error("NumberFormatException for: " + value);
			return -1;
		}
	}
	
	public static double round2(double value) {
		try {
	    return round(value, 2);
		}catch(NumberFormatException ex) {
			logger.error("NumberFormatException for: " + value);
			return -1;
		}
	}
	
	static String format(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();
	 
	    Locale fmtLocale = Locale.getDefault(Category.FORMAT);
	    NumberFormat formatter = NumberFormat.getInstance(fmtLocale);
	    formatter.setMaximumFractionDigits(places);
	    formatter.setMinimumFractionDigits(places);
	    return formatter.format(value);
	}
	
	static String format3(double value) {
		try {
	    return format(value, 3);
		}catch(NumberFormatException ex) {
			logger.error("NumberFormatException for: " + value);
			return "N.D.";
		}
	}
	
	static String format2(double value) {
		try {
	    return format(value, 2);
		}catch(NumberFormatException ex) {
			logger.error("NumberFormatException for: " + value);
			return "N.D.";
		}
	}
	
	static String format1(double value) {
		try {
	    return format(value, 1);
		}catch(NumberFormatException ex) {
			logger.error("NumberFormatException for: " + value);
			return "N.D.";
		}
	}
	
	static Double getAreaBasimentricaAlbero(Double diametro) {
		Double res = Math.pow((diametro)/200,2)*PI;
		logger.debug("-> " + res);
		return res; 
	}
	
	static Double getSuperficie(Double raggio,Double incalzo) {
		Double incalzoRad = incalzo * 0.0174533; //incalzo in radianti
		//formula: (3.14159 * (ADS.raggio)^2 *Coseno(Conversione da gradi a radianti(ADS.inclinazio)))  / 10000
		Double result = (PI * Math.pow(raggio,2) * Math.cos(incalzoRad));
		logger.debug("Raggio: " + raggio + " - Inclinazio: " + incalzo + " - result: " + result);
		return  result;
	}
	
	static Double getSuperficieHa(Double raggio,Double incalzo) {
		return getSuperficie( raggio,incalzo)/10000;
	}
	
	static boolean notEmpty(String str) {
		if(str != null && !str.isEmpty()) {
			return true;
		}
		return false;
	} 
	static Integer getClasse(Double value,Integer step){
		Double res;
		Double rest = value%step;
		if(rest > step/2) {
			res = value - rest + step;
		}else {
			res = value - rest;
		}
		return res.intValue();
	 }
	
	static List<Map<String,Object>> getDatiCampione(Map<String,Integer> mapFields, String[][] data, TipoAdsEnum adsType) {
		ReportUtil.specieNotManagedForVolume = new HashSet<String>();
		Set listAds = new HashSet<String>();
		Double numAlberi = 0d;
		int numConteggi = 0;
		double totAlberiSeme = 0d;
		double totAlberiPollone = 0d;
		Double somDensita = 0d;
		Double somSuperificie = 0d;
		//Double somXas = 0d;
		Double somAreaBasimAlbero = 0d;
		//Double somAbXas = 0d;
		Double somVolume = 0d;
		Double somCeppaieXas = 0d;
		Double somCeppaie = 0d;
		Map<String,Integer> mapCountAlberiAds = new TreeMap<String,Integer>();
		Map<String,Double> mapAdsRaggio = new HashMap<String,Double>();
		Map<String,Double> mapAdsInclinazio = new HashMap<String,Double>();
		Map<String,Double> mapAdsVolumi = new HashMap<String,Double>();
		Map<String,Double> mapAdsSuperficie = new HashMap<String,Double>();
		
		String ads;
		Double xas;
		String ceppaieStr;
		Double ceppaie;
		Double volume;
		Double volumeAlbero;
		Double superficie;
		Double diametroAlbero;
		Double areaBasimAlbero;
		String specie;
		String diametroStr;
		RegressioneLineare regrLin = ReportUtil.getRegressioneLineare(mapFields, data);
		Integer idFieldNumAlberiSeme = mapFields.get(ReportCsvFieldsEnum.NUM_ALBERI_SEME.getValue());
		Integer idFieldNumAlberiPollone = mapFields.get(ReportCsvFieldsEnum.NUM_ALBERI_POLLONE.getValue());
		for(String[]row : data) {
			diametroStr = row[mapFields.get(ReportCsvFieldsEnum.DIAMETRO.getValue())];
			ads = row[mapFields.get(ReportCsvFieldsEnum.ADS.getValue())];
			listAds.add(ads);
			if("CAV".equals(row[mapFields.get(ReportCsvFieldsEnum.COD_TIPO_CAMPIONE.getValue())])
					&& diametroStr.length()>0) {
				numAlberi++;
				//gestione valori per ADS
				if(mapCountAlberiAds.get(ads) == null) {
					
					mapCountAlberiAds.put(ads,0);
					mapAdsVolumi.put(ads,0d);
					mapAdsSuperficie.put(ads,0d);
					if(TipoAdsEnum.SUPERFICIE_NOTA == adsType) {
						mapAdsRaggio.put(ads, 
							Double.parseDouble(row[mapFields.get(ReportCsvFieldsEnum.RAGGIO.getValue())]));
					
						mapAdsInclinazio.put(ads, 
							Double.parseDouble(row[mapFields.get(ReportCsvFieldsEnum.INCLINAZIO.getValue())]));
					
						somDensita+= Double.parseDouble(row[mapFields.get(ReportCsvFieldsEnum.DENSITA.getValue())]);
					
						xas = getXas(mapFields, row);
						ceppaieStr = row[mapFields.get(ReportCsvFieldsEnum.CEPPAIE.getValue())];
						ceppaie = Double.parseDouble(ceppaieStr.length()==0?"0":ceppaieStr);
						somCeppaieXas+= ceppaie * xas;
						somCeppaie+= ceppaie;
						logger.debug("ceppaie: " + ceppaie + " - xas: " + xas + " - sumCappaieXas_ " + somCeppaieXas +
								" - somCeppaie: " + somCeppaie);
					
						superficie = ReportUtil.getSuperficie(Double.parseDouble(row[mapFields.get(ReportCsvFieldsEnum.RAGGIO.getValue())]), 
								Double.parseDouble(row[mapFields.get(ReportCsvFieldsEnum.INCLINAZIO.getValue())]));
						mapAdsSuperficie.put(ads, superficie);
						somSuperificie+= superficie/10000;
						logger.debug("ADS: " + ads + " - superficie: " + superficie);
					}
				
				}
				mapCountAlberiAds.put(ads,mapCountAlberiAds.get(ads) + 1);
				
				diametroAlbero = Double.parseDouble(diametroStr);
				areaBasimAlbero = ReportUtil.getAreaBasimentricaAlbero(diametroAlbero);
				somAreaBasimAlbero+= areaBasimAlbero;
				specie = row[mapFields.get(ReportCsvFieldsEnum.SPECIE_CODE.getValue())];
				volumeAlbero = ReportUtil.getVolumeAlbero(diametroAlbero, regrLin.getAltezzaByDiametro(diametroAlbero),specie);
				logger.debug("volumeAlbero: " + volumeAlbero);
				somVolume+= volumeAlbero;
				
				volume = volumeAlbero + mapAdsVolumi.get(row[mapFields.get(ReportCsvFieldsEnum.ADS.getValue())]);
				mapAdsVolumi.put(row[mapFields.get(ReportCsvFieldsEnum.ADS.getValue())], volume);
			}else if(idFieldNumAlberiSeme != null && row[idFieldNumAlberiSeme].length() > 0) {
				numConteggi++;
				totAlberiSeme+= Double.parseDouble(row[idFieldNumAlberiSeme]);
				totAlberiPollone+= Double.parseDouble(row[idFieldNumAlberiPollone]);		
			}
		}
		
		Integer totAds = listAds.size();
		
		logger.debug("somSuperificie: " + somSuperificie);
		
		List<Map<String,Object>> resTotali = new ArrayList<Map<String,Object>>();
		Map<String,Object> row = new LinkedHashMap<String,Object>();
		resTotali.add(row);
		row.put("-", "Numero aree di saggio");
		row.put("valore", totAds);
		
		row = new LinkedHashMap<String,Object>();
		resTotali.add(row);
		row.put("-", "Numero alberi cavallettati");
		row.put("valore", numAlberi);
		
		if(TipoAdsEnum.RELASCOPICA_COMPLETA == adsType) {
//			row = new LinkedHashMap<String,Object>();
//			resTotali.add(row);
//			row.put("-", "Numero conteggi");
//			row.put("valore",numConteggi);
			
			row = new LinkedHashMap<String,Object>();
			resTotali.add(row);
			row.put("-", "Numero alberi seme conteggiati");
			row.put("valore",format1(totAlberiSeme));
			
			row = new LinkedHashMap<String,Object>();
			resTotali.add(row);
			row.put("-", "Numero alberi pollone conteggiati");
			row.put("valore",format1(totAlberiPollone));
			
		}
		
		if(TipoAdsEnum.SUPERFICIE_NOTA == adsType) {
			row = new LinkedHashMap<String,Object>();
			resTotali.add(row);
			row.put("-", "Superficie boscata rappresentata (ha)");
			//row.put("valore", format2(somDensita) + " or " + format2(somSuperificie) + " ???" );
			row.put("valore",format1(somDensita));
		}
		
//		row = new LinkedHashMap<String,Object>();
//		resTotali.add(row);
//		row.put("-", "Numero alberi/ha");
//		row.put("valore", ReportUtil.round2(numAlberi/somSuperificie));
		
		row = new LinkedHashMap<String,Object>();
		resTotali.add(row);
		row.put("-", "Area basimetrica (m<sup>2</sup>/ha)");
		if(TipoAdsEnum.SUPERFICIE_NOTA == adsType) {
			row.put("valore", ReportUtil.format2(somAreaBasimAlbero/somSuperificie));
		}else {
			row.put("valore",ReportUtil.format2(ReportUtilBasimetria.getBasimetriaTotMqHa(mapFields, data)));
		}
		
		if(TipoAdsEnum.RELASCOPICA_COMPLETA == adsType) {
			row = new LinkedHashMap<String,Object>();
			resTotali.add(row);
			row.put("-", "Volume (m<sup>3</sup>/ha)");
			Double volumeTot = (Double) ReportUtilVolumi.getDatiVolumiPerSpecie(mapFields, data, true).get("VolumeTot");
			row.put("valore", ReportUtil.format2(volumeTot));
		}
		
		if(TipoAdsEnum.SUPERFICIE_NOTA == adsType) {
			row = new LinkedHashMap<String,Object>();
			resTotali.add(row);
			row.put("-", "Volume (m<sup>3</sup>/ha)");
			row.put("valore", ReportUtil.format2(somVolume/somSuperificie));
			logger.debug("somVolume: " + somVolume + " - somSuperificie: " + somSuperificie);
		
			row = new LinkedHashMap<String,Object>();
			resTotali.add(row);
			row.put("-", "Numero ceppaie (n/ha)");
			row.put("valore", ReportUtil.format2(somCeppaie/somSuperificie));
		}
		
		row = new LinkedHashMap<String,Object>();
		resTotali.add(row);
		row.put("-", "Diametro medio (cm)");
		//Double diametroMedio = 200 * Math.sqrt((somAbXas/somXas)/3.1416);
		Double diametroMedio = Math.sqrt((somAreaBasimAlbero/numAlberi)/3.1416)*200;
		row.put("valore", (diametroMedio != null && diametroMedio > 0d)?ReportUtil.format2(diametroMedio):"n.d.");
		
		row = new LinkedHashMap<String,Object>();
		resTotali.add(row);
		row.put("-", "Altezza media (m)");
		row.put("valore",diametroMedio == null?"n/d":ReportUtil.format2(regrLin.getAltezzaByDiametro(diametroMedio)));
		
		
		if(TipoAdsEnum.SUPERFICIE_NOTA == adsType) {
		fillErroriAndCoefVariablita(resTotali, totAds, mapCountAlberiAds, mapAdsRaggio, mapAdsInclinazio, 
				mapAdsVolumi, mapAdsSuperficie);
		}
		
		if(TipoAdsEnum.RELASCOPICA_COMPLETA == adsType) {
			row = new LinkedHashMap<String,Object>();
			resTotali.add(row);
			row.put("-", "N. alberi/ha");
			Object val = getRelascopia(mapFields, data, true);
			row.put("valore",val == null?"":val.toString().replace("[", "").replace("]", ""));
		}
		
		
		return resTotali;
	}
	
	private static void fillErroriAndCoefVariablita(List<Map<String,Object>> resTotali, Integer rnads, 
			Map<String,Integer> mapCountAlberiAds, Map<String,Double> mapAdsRaggio, Map<String,Double> mapAdsInclinazio,
			Map<String,Double> mapAdsVolumi,Map<String,Double> mapAdsSuperfice) {
		Double snas = 0d;
		Double snas2 = 0d;
		Double snasAds;
		Double svas = 0d;
		Double svas2 = 0d;
		Double svasAds;
		Double vol;
		Double supas;
		Integer totAds = mapCountAlberiAds.keySet().size();
		Map<String,Double> mapAlberiHaAds = new TreeMap<String,Double>();
		//Map<String,Double> mapSuperficieAds = new TreeMap<String,Double>();
		Double superficieAds;
		
		for(String ads : mapCountAlberiAds.keySet()) {
			superficieAds=  ReportUtil.getSuperficie(mapAdsRaggio.get(ads),mapAdsInclinazio.get(ads));
			snasAds = mapCountAlberiAds.get(ads) * 10000 /superficieAds;
			
			mapAlberiHaAds.put(ads, snasAds);
			snas+= snasAds;
			snas2+= Math.pow(snasAds, 2);
			
			logger.debug(ads + " - n alberi ha: " + snasAds + " - quadrato: " + Math.pow(snasAds, 2));
			
			vol = mapAdsVolumi.get(ads);
			supas = mapAdsSuperfice.get(ads);
			svasAds = vol *10000 / supas;
			svas+= svasAds;
			svas2+= Math.pow(svasAds, 2);
		}
		Double alberiHaMedio = snas/totAds;
		logger.debug("tot  - n alberi ha: " + (alberiHaMedio) + " - quadrato: " + snas2);
		
		Double rmn=snas/rnads;
		Double rmv=svas/rnads;
		
		Double sdn = Math.sqrt((snas2- (Math.pow(snas,2) / (totAds)))/ (totAds-1));
		Double sdv = Math.sqrt((svas2- (Math.pow(svas,2) / (totAds)))/ (totAds-1));
		
		logger.debug("Math.sqrt(((" + snas2 + " - Math.pow(" + snas + ",2)) / (" + totAds + "))/ (" + totAds + "-1));");
		logger.debug("Math.sqrt(((" + (snas2 - Math.pow( snas ,2)) + ") / (" + totAds + "))/ (" + totAds + "-1));");
	
		//Validita statistica
			//Coefficente di variabilita
		Double cvn=sdn/rmn*100;
		Double cvv=sdv/rmv*100;
	
				//errori statistici
		Double ern=totAds == 0?0:cvn/Math.sqrt(totAds);
		Double erv=totAds == 0?0d:cvv/Math.sqrt(totAds);
		
		logger.debug("rnads: " + rnads + " - snas: " + snas + " - rmn: " + rmn + " - svas: " + svas + " - rmv: " + rmv 
				+ " - sdn: " + sdn + " - sdv: " + sdv + " - snas2: " + snas2 + " - svas2: " + svas2 
				+ " - cvn: " + cvn + " - cvv: " + cvv + " - ern: " + ern + " - erv: " + erv);
		
		Map<String,Object> row = new LinkedHashMap<String,Object>();
		resTotali.add(2,row);
		row.put("-", "Numero alberi (n/ha)");
		row.put("valore", ReportUtil.format2(alberiHaMedio));
		
		row = new LinkedHashMap<String,Object>();
		resTotali.add(row);
		row.put("-", "Errore statistico (significativita' 67%) per numero alberi");
		row.put("valore", ReportUtil.format2(ern) + "%");
		
		row = new LinkedHashMap<String,Object>();
		resTotali.add(row);
		row.put("-", "Errore statistico (significativita' 67%) per volumi");
		row.put("valore", ReportUtil.format2(erv) + "%");
		
		row = new LinkedHashMap<String,Object>();
		resTotali.add(row);
		row.put("-", "Coefficiente di variabilita' per numero alberi");
		row.put("valore", ReportUtil.format2(cvn) + "%");
		
		row = new LinkedHashMap<String,Object>();
		resTotali.add(row);
		row.put("-", "Coefficiente di variabilita' per volumi");
		row.put("valore", ReportUtil.format2(cvv) + "%");
	}
	
	private static Double getXas(Map<String,Integer> mapFields, String[] data) {
		
		Double densita = Double.parseDouble(data[mapFields.get(ReportCsvFieldsEnum.DENSITA.getValue())]);
		Double raggio = Double.parseDouble(data[mapFields.get(ReportCsvFieldsEnum.RAGGIO.getValue())]);
		Double inclinazio = Double.parseDouble(data[mapFields.get(ReportCsvFieldsEnum.INCLINAZIO.getValue())]);
		//inclinazio radianti
		inclinazio = inclinazio * 0.0174533;
		//formula: densita*10000 / (3.14159 * (raggio)^2) * COSENO (RADIANTI( inclinazio))
		return densita*10000 / (3.14159 * Math.pow(raggio,2)) * Math.cos (inclinazio);
	}
	
	
	//************** test ***********************
//	static Double[] diametri = new Double[]{12d,14d,14d,16d,16d,16d,18d,19d,20d,20d,20d,21d};
//	static Double[] altezze = new Double[] {10d,10d,12d,11d,8d,10d,9d,13d,12d,11d,11d,13d}; 
	public static void mainx(String[] args) {
		
		Double[] diametri = new Double[]{12d,14d,14d,16d,16d,16d,18d,19d,20d,20d,20d,21d};
		Double[] altezze = new Double[] {10d,10d,12d,11d,8d,10d,9d,13d,12d,11d,11d,13d}; 
		
		Double[] diametriLnFix = new Double[] {2.48d,2.64d,2.64d,2.77d,2.77d,2.77d,2.89d,2.94d,3.00d,3.00d,3.00d,3.04d}; 
		Double[] diametriLn = new Double[diametri.length];
		
		for(int i=0;i<diametri.length;i++) {
			diametriLn[i] = Math.log(diametri[i]);
			
		}
		
		Double[][] regrData = new Double[2][];
		regrData[0] = altezze;
		regrData[1] = diametriLnFix;
		//double[] regrVal = mRegrLin(diametriLn,altezze);
		RegressioneLineare regrLin = new RegressioneLineare(diametriLn, altezze);
		
		Map<String, List<Double>> diametriAndAltezze = new HashMap<String, List<Double>>();
		diametriAndAltezze.put("diametri", Arrays.asList(diametri));
		diametriAndAltezze.put("altezze", Arrays.asList(altezze));
		
		Map<String, Object> res = null;//elaborazioneDatiIpsomentira(diametriAndAltezze);
		Double[][] pointsInterpolation = (Double[][]) res.get("pointsInterpolation");
		
		printArray2x2(pointsInterpolation);

		Double[][] pointsLine = (Double[][]) res.get("pointsLine");
		
		printArray2x2(pointsLine);

		Double[][] pointsLineInterpolation = (Double[][]) res.get("pointsLineInterpolation");
		
		printArray2x2(pointsLineInterpolation);
			
		
		
	}
	
	private static void printArray2x2(Double[][] vet) {
		for(Double[]subVet:vet) {
			
		}
		
	}
	
	
	public static void testSpecie(String[] args) {
		String specie = "AA,AN,AT,ES,ON,AC,AG,AI,AE,AL,AM,AX,AU,AO,AP,AR,OV,BP,BP,CB,CB,CS,FE,FO,FS,IA,JR,LA,LD,MS,OC,PA,PY,PV,PC,PO,PH,DU,PN,PW,PD,CG,PM,PP,PL,CT,PX,PS,PZ,PT,PU,QC,QI,QR,QP,QF,QX,RP,SX,SA,SU,SC,SD,SN,ST,TB,TC,TP,UG,UC,UM,UP";
		String[] specieVet = specie.split(",");
		for(String key:specieVet) {
			
		}
	}
	
	public static void main(String[] args) {
		Double x = 1234567.1239d;
		Locale fmtLocale = Locale.getDefault(Category.FORMAT);
		NumberFormat formatter = NumberFormat.getInstance(fmtLocale);
		
	}

	public static Map<String, String> getMapSpecie() {
		return mapSpecie;
	}

	public static void setMapSpecie(Map<String, String> mapSpecie) {
		ReportUtil.mapSpecie = mapSpecie;
	}
	
	public static List<Object> getRelascopia(Map<String,Integer> mapFields, String[][] data, boolean isGetAlberi) {
		Map<String,Double> mapConteggiSpecie = new TreeMap<String,Double>();
//		Map<String,Double> mapConteggiPercent = new TreeMap<String,Double>();
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
		
		List<Object> res = new ArrayList<Object>();
		Map<String,String> row;
		Double totRelascopica = 0d;
		Double totMediaCav = 0d;
		Double totDiametroMedio = 0d;
		String[] labels = new String[]{"Specie","Area basimetrica relascopica (m<sup>2</sup>/ha)",
				"Area basimetrica media da cavallettamento (m<sup>2</sup>/ha)","Diametro medio (cm)","N. alberi/ha"};
		int count = 0;
		for(String spec : mapConteggiSpecie.keySet()) {
			row = new LinkedHashMap<String,String>();
			res.add(row);
			row.put(labels[0], spec);
			row.put(labels[1], ReportUtil.format1(mapConteggiSpecie.get(spec)));
			totRelascopica = updateTotale(totRelascopica,row.get(labels[1]));
			if(mapConteggiCavallettati.get(spec) == null) {
				row.put(labels[2], "");
				row.put(labels[3], "");
				row.put(labels[4], "");
			}else {
				count++;
				row.put(labels[2], 
						ReportUtil.format3(mapConteggiCavallettati.get(spec)));
				totMediaCav = updateTotale(totMediaCav,row.get(labels[2]));
				row.put(labels[3], ReportUtil.format2(getDiametroMedio(mapConteggiCavallettati.get(spec))));
				totDiametroMedio = updateTotale(totDiametroMedio,row.get(labels[3]));
				row.put(labels[4], ReportUtil.format(mapConteggiSpecie.get(spec)/mapConteggiCavallettati.get(spec),0));
			}
		}
		
		if(isGetAlberi) {
			res = new ArrayList<Object>();
			res.add(ReportUtil.format(totRelascopicaNotRound/(totMediaCavNotRound/count),0));
		}else {
			row = new LinkedHashMap<String,String>();
			res.add(row);
			row.put(labels[0], "Totale");
			row.put(labels[1], ReportUtil.format1(totRelascopica));
			row.put(labels[2], count==0?"":ReportUtil.format3(totMediaCav/count));
			row.put(labels[3], count==0?"":ReportUtil.format2(totDiametroMedio/count));
			row.put(labels[4], count==0?"":ReportUtil.format(totRelascopicaNotRound/(totMediaCavNotRound/count),0));
		}
			
		return res;
	}

	public static String toStringFormat(Float number){
		if (number == null) number = 1f;
		return decimalFormat.format(number);
	}

	public static String toStringFormat(Integer number){
		if (number == null) number = 1;
		return decimalFormat.format(number);
	}

	public static String m3ToTon(Float m3s, Float densita) {
		if (densita == null) densita = 1f;
		return decimalFormat.format(m3ToTonF(m3s, densita));
	}

	public static Float m3ToTonF(Float m3s, Float densita) {
		if (densita == null) densita = 1f;
		return m3s * densita;
	}

	public static String m3ToQ(Float m3s, Float densita) {
		if (densita == null) densita = 1f;
		return decimalFormat.format(m3ToQF(m3s, densita));
	}

	public static Float m3ToQF(Float m3s, Float densita) {
		if (densita == null) densita = 1f;
		return m3s * densita * 10;
	}


	private static Double updateTotale(Double totale, String value) {
		return totale + Double.parseDouble(value.replace(",", "."));
	}
	
	private static Double getDiametroMedio(Double mediaDaCavallettamento){
		return Math.sqrt(mediaDaCavallettamento/ReportUtil.PI) * 200;
	}

}
