/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfgeoapi.dto.factory;

import it.csi.ecogis.util.dto.GeoJSONFeature;
import it.csi.idf.idfgeoapi.dto.TrasformazioneForLayerDto;
import it.csi.idf.idfgeoapi.util.Constants;
import it.csi.idf.idfgeoapi.util.ConvertUtil;

public class TrasformazioneForLayerFactory {

	public static TrasformazioneForLayerDto create(String layerId, GeoJSONFeature feature) {
		TrasformazioneForLayerDto result = new TrasformazioneForLayerDto();
		result.setIdgeoPlIntervTrasf(ConvertUtil.convertToInteger(feature.getId()));
		result.setGeometry(feature.getGeometry());
		String idIntervento = (String)feature.getProperties().get(Constants.GEECO_TRS_LABEL_INTERVENTO);
		result.setFkIntervento(ConvertUtil.convertToInteger(idIntervento));
		return result;
	}

	public static TrasformazioneForLayerDto createForUpdate(String layerId, GeoJSONFeature feature) {
		TrasformazioneForLayerDto result = new TrasformazioneForLayerDto();
		result.setGeometry(feature.getGeometry());
		String id = (String)feature.getProperties().get(Constants.GEECO_TRS_LABEL_IDENTIFICATIVO);
		result.setIdFeature(ConvertUtil.convertToLong(id));
		return result;
	}

}
