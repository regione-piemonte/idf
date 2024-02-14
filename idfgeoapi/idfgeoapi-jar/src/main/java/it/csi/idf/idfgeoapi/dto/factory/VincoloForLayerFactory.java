/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfgeoapi.dto.factory;

import it.csi.ecogis.util.dto.GeoJSONFeature;
import it.csi.idf.idfgeoapi.dto.VincoloForLayerDto;
import it.csi.idf.idfgeoapi.util.Constants;
import it.csi.idf.idfgeoapi.util.ConvertUtil;

public class VincoloForLayerFactory {
	
	
	public static VincoloForLayerDto create(GeoJSONFeature feature) {
		VincoloForLayerDto result = new VincoloForLayerDto();
		result.setIdGeoPlIntervVincidro(ConvertUtil.convertToInteger(feature.getId()));
		result.setGeometry(feature.getGeometry());
		String idIntervento = (String)feature.getProperties().get(Constants.GEECO_VINC_LABEL_INTERVENTO);
		result.setFkIntervento(ConvertUtil.convertToInteger(idIntervento));
		return result;
	}

	public static VincoloForLayerDto createForUpdate(GeoJSONFeature feature) {
		VincoloForLayerDto result = new VincoloForLayerDto();
		result.setGeometry(feature.getGeometry());
		String id = (String)feature.getProperties().get(Constants.GEECO_VINC_LABEL_IDENTIFICATIVO);
		result.setIdFeature(ConvertUtil.convertToLong(id));
		return result;
	}
}
