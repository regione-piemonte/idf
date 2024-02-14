/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import it.csi.idf.idfapi.dto.RAdsrelSpecie;
import it.csi.idf.idfapi.util.CodTipoCampioneEnum;

public class AreaDiSaggioAlberiCamDomValidator implements StepValidator{
	
	public static final Logger logger = Logger.getLogger(AreaDiSaggioAlberiCamDomValidator.class);
	
	List<RAdsrelSpecie> listOdAlberiCamDov;
	public AreaDiSaggioAlberiCamDomValidator(List<RAdsrelSpecie> listOdAlberiCamDov) {
		super();
		this.listOdAlberiCamDov = listOdAlberiCamDov;
		
	}

	public static final String ERROR_PREFIX = "DATI_ONE_";


	@Override
	public HashMap<String,String> validateFields() {
		HashMap<String,String> errorMap = new HashMap<>();
		int count = 0;
		if(listOdAlberiCamDov == null) {
			listOdAlberiCamDov = new ArrayList<RAdsrelSpecie>();
		}
		
		if(!isDominantePresent()) {//dominante obbligatorio
			RAdsrelSpecie dom = new RAdsrelSpecie();
			dom.setCodTipoCampione(CodTipoCampioneEnum.DOM.toString());
			logger.info(CodTipoCampioneEnum.DOM.toString() + " - equals: " + (CodTipoCampioneEnum.DOM.equals(CodTipoCampioneEnum.DOM.toString())));
			listOdAlberiCamDov.add(dom);
		}
		for (Iterator<RAdsrelSpecie> iterator = listOdAlberiCamDov.iterator(); iterator.hasNext();) {

			RAdsrelSpecie rAdsrelSpecie = iterator.next();
	
			UtilValidator.checkNotNullFiledValidity("id_specie"+count, errorMap, rAdsrelSpecie.getIdSpecie(), UtilValidator.MANDATORY_FIELD_MESSAGE);
			String qualita = rAdsrelSpecie.getQualita()==null || rAdsrelSpecie.getQualita().length()==0?null:rAdsrelSpecie.getQualita();
			UtilValidator.checkNotNullFiledValidity("qualita"+count, errorMap, qualita, UtilValidator.MANDATORY_FIELD_MESSAGE);
			UtilValidator.checkNotNullFiledValidity("diametro"+count, errorMap, rAdsrelSpecie.getDiametro(), UtilValidator.MANDATORY_FIELD_MESSAGE);
			//UtilValidator.checkNotNullFiledValidity("altezza", errorMap, rAdsrelSpecie.getAltezza(), UtilValidator.MANDATORY_FIELD_MESSAGE);
			UtilValidator.checkNotNullFiledValidity("incremento"+count, errorMap, rAdsrelSpecie.getIncremento(), UtilValidator.MANDATORY_FIELD_MESSAGE);
			UtilValidator.checkNotNullFiledValidity("eta"+count, errorMap, rAdsrelSpecie.getEta(), UtilValidator.MANDATORY_FIELD_MESSAGE);
			
//			if (rAdsrelSpecie.getIdSpecie()!=null && !idSpecieSet.add(rAdsrelSpecie.getIdSpecie())) {
//				errorMap.put("general", "There can't be any duplicates in the list");
//			}
			
			count++;
			
		}
				
		logger.info("errorMap size:" + errorMap.size());
		
		return errorMap;
	}


	@Override
	public int getStepNumber() {
		// TODO Auto-generated method stub
		return 2;
	}
	
	private boolean isDominantePresent() {
		if(listOdAlberiCamDov != null && listOdAlberiCamDov.size() > 0) {
			for (Iterator<RAdsrelSpecie> iterator = listOdAlberiCamDov.iterator(); iterator.hasNext();) {
				RAdsrelSpecie rAdsrelSpecie = iterator.next();
				logger.info("isDominantePresent - codCampine: " + rAdsrelSpecie.getCodTipoCampione());
				if(CodTipoCampioneEnum.DOM.toString().equals(rAdsrelSpecie.getCodTipoCampione())) {
					logger.info("isDominantePresent: true");
					return true;
				}
			}
		}
		logger.info("isDominantePresent: false");
		return false;
	}
}
