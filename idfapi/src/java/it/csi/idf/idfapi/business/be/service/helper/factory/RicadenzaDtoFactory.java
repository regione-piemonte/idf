/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.service.helper.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.csi.idf.idfapi.business.be.service.helper.dto.Ricadenza;
import it.csi.idf.idfapi.util.ConvertUtil;

public final class RicadenzaDtoFactory {
	
	private static final String TIPOLOGIA_RICADENZA_SIC = "SIC";
	private static final String TIPOLOGIA_RICADENZA_ZPS = "ZPS";
	
	private static Ricadenza createRicadenzaSic(it.csi.idf.idfapi.util.service.integration.gsareprot.sic.Ricadenza dto) {
		Ricadenza result = new Ricadenza();
		if(dto!=null) {
			result.setCodiceAmministrativo(dto.getCodiceAmministrativo());
			result.setNome(dto.getNome());
			result.setPercentualeRicadenza(ConvertUtil.convertToString(dto.getPercentualeDiGeometriaOccupataDalParco()));
			result.setTipologiaSito(TIPOLOGIA_RICADENZA_SIC);	
		}
		return result;
	}
	
	private static Ricadenza createRicadenzaZps(it.csi.idf.idfapi.util.service.integration.gsareprot.zps.Ricadenza dto) {
		Ricadenza result = new Ricadenza();
		if(dto!=null) {
			result.setCodiceAmministrativo(dto.getCodiceAmministrativo());
			result.setNome(dto.getNome());
			result.setPercentualeRicadenza(ConvertUtil.convertToString(dto.getPercentualeDiGeometriaOccupataDalParco()));
			result.setTipologiaSito(TIPOLOGIA_RICADENZA_ZPS);	
		}
		return result;
	}
	
	public static List<Ricadenza> createRicadenzeSic(it.csi.idf.idfapi.util.service.integration.gsareprot.sic.Ricadenza[] list){
		List<Ricadenza> result = new ArrayList<Ricadenza>();
		if(list!=null) {
			for (it.csi.idf.idfapi.util.service.integration.gsareprot.sic.Ricadenza ricadenza : list) {
				result.add(createRicadenzaSic(ricadenza));
			}
		}
		return result;
	}
	
	public static List<Ricadenza> createRicadenzeZps(it.csi.idf.idfapi.util.service.integration.gsareprot.zps.Ricadenza[] list){
		List<Ricadenza> result = new ArrayList<Ricadenza>();
		if(list!=null) {
			for (it.csi.idf.idfapi.util.service.integration.gsareprot.zps.Ricadenza ricadenza : list) {
				result.add(createRicadenzaZps(ricadenza));
			}
		}
		return result;
	} 
	
	public static Map<String, Ricadenza> createMapRicadenzeSic(it.csi.idf.idfapi.util.service.integration.gsareprot.sic.Ricadenza[] list){
		
		HashMap<String, Ricadenza> result = new HashMap<String, Ricadenza>();
		if(list!=null) {
			for (it.csi.idf.idfapi.util.service.integration.gsareprot.sic.Ricadenza ricadenza : list) {
				result.put(ricadenza.getCodiceAmministrativo(), createRicadenzaSic(ricadenza));
			}
		}
		
		return result;
	}
	
	public static Map<String, Ricadenza> createMapRicadenzeZps(it.csi.idf.idfapi.util.service.integration.gsareprot.zps.Ricadenza[] list){
		
		HashMap<String, Ricadenza> result = new HashMap<String, Ricadenza>();
		if(list!=null) {
			for (it.csi.idf.idfapi.util.service.integration.gsareprot.zps.Ricadenza ricadenza : list) {
				result.put(ricadenza.getCodiceAmministrativo(), createRicadenzaZps(ricadenza));
			}
		}
		
		return result;
	}
}
