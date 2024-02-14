/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfgeoapi.dto.factory;

import it.csi.ecogis.util.dto.GeoJSONFeature;
import it.csi.idf.idfgeoapi.dto.EventoLnForLayerDto;
import it.csi.idf.idfgeoapi.dto.EventoPlForLayerDto;
import it.csi.idf.idfgeoapi.dto.EventoPtForLayerDto;
import it.csi.idf.idfgeoapi.dto.InterventoLnForLayerDto;
import it.csi.idf.idfgeoapi.dto.InterventoPlForLayerDto;
import it.csi.idf.idfgeoapi.dto.InterventoPtForLayerDto;
import it.csi.idf.idfgeoapi.dto.PfaForLayerDto;
import it.csi.idf.idfgeoapi.util.Constants;
import it.csi.idf.idfgeoapi.util.ConvertUtil;

public class PfaForLayerFactory {
	
	public static PfaForLayerDto create(GeoJSONFeature feature) {
		PfaForLayerDto result = new PfaForLayerDto();
		result.setGeometry(feature.getGeometry());
		result.setIdFeature(ConvertUtil.convertToLong(feature.getId()));
		return result;
	}
	
	public static EventoPtForLayerDto createPtEvento(GeoJSONFeature feature) {
		EventoPtForLayerDto result = new EventoPtForLayerDto();
		result.setGeometry(feature.getGeometry());
		result.setFkEvento(ConvertUtil.convertToInteger((String)feature.getProperties().get(Constants.GEECO_PFA_LABEL_ID_EVENTO)));
		result.setDescrizione((String)feature.getProperties().get(Constants.GEECO_PFA_LABEL_DESCRIZIONE));
		return result;
	}

	public static EventoLnForLayerDto createLnEvento(GeoJSONFeature feature) {
		EventoLnForLayerDto result = new EventoLnForLayerDto();
		result.setGeometry(feature.getGeometry());
		result.setFkEvento(ConvertUtil.convertToInteger((String)feature.getProperties().get(Constants.GEECO_PFA_LABEL_ID_EVENTO)));
		result.setDescrizione((String)feature.getProperties().get(Constants.GEECO_PFA_LABEL_DESCRIZIONE));
		return result;
	}

	public static EventoPlForLayerDto createPlEvento(GeoJSONFeature feature) {
		EventoPlForLayerDto result = new EventoPlForLayerDto();
		result.setGeometry(feature.getGeometry());
		result.setFkEvento(ConvertUtil.convertToInteger((String)feature.getProperties().get(Constants.GEECO_PFA_LABEL_ID_EVENTO)));
		result.setDescrizione((String)feature.getProperties().get(Constants.GEECO_PFA_LABEL_DESCRIZIONE));
		return result;
	}
	
	public static InterventoPtForLayerDto createPtIntervento(GeoJSONFeature feature) {
		InterventoPtForLayerDto result = new InterventoPtForLayerDto();
		result.setGeometry(feature.getGeometry());
		result.setFkIntervento(ConvertUtil.convertToInteger((String)feature.getProperties().get(Constants.GEECO_PFA_LABEL_ID_INTERVENTO)));
		result.setDescrizione((String)feature.getProperties().get(Constants.GEECO_PFA_LABEL_DESCRIZIONE));
		return result;
	}

	public static InterventoLnForLayerDto createLnIntervento(GeoJSONFeature feature) {
		InterventoLnForLayerDto result = new InterventoLnForLayerDto();
		result.setGeometry(feature.getGeometry());
		result.setFkIntervento(ConvertUtil.convertToInteger((String)feature.getProperties().get(Constants.GEECO_PFA_LABEL_ID_INTERVENTO)));
		result.setDescrizione((String)feature.getProperties().get(Constants.GEECO_PFA_LABEL_DESCRIZIONE));
		return result;
	}

	public static InterventoPlForLayerDto createPlIntervento(GeoJSONFeature feature) {
		InterventoPlForLayerDto result = new InterventoPlForLayerDto();
		result.setGeometry(feature.getGeometry());
		result.setFkIntervento(ConvertUtil.convertToInteger((String)feature.getProperties().get(Constants.GEECO_PFA_LABEL_ID_INTERVENTO)));
		result.setDescrizione((String)feature.getProperties().get(Constants.GEECO_PFA_LABEL_DESCRIZIONE));
		return result;
	}
	

	//	public EventoLnForLayerDto create(String layerId, GeoJSONFeature feature) {
//		
//	}
//	
}
