/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfgeoapi.dto.factory;

import it.csi.ecogis.util.dto.GeoJSONFeature;
import it.csi.idf.idfgeoapi.dto.AdsForLayerDto;
import it.csi.idf.idfgeoapi.dto.TipoAdsEnum;
import it.csi.idf.idfgeoapi.util.Constants;
import it.csi.idf.idfgeoapi.util.ConvertUtil;

public class AdsForLayerFactory {

	public static AdsForLayerDto createForUpdate(String layerId, GeoJSONFeature feature) {

		AdsForLayerDto result = new AdsForLayerDto();
		result.setGeometry(feature.getGeometry());
		String nAds = (String) feature.getProperties().get(Constants.GEECO_ADS_LABEL_N_ADS);
		result.setCodiceAds(nAds);
		result.setFkTipoAds(TipoAdsEnum.getIdFromLayer(layerId));
		result.setIdFeature(ConvertUtil.convertToLong(feature.getId()));
		return result;
	}

	public static AdsForLayerDto createForInsert(String layerId, GeoJSONFeature feature) {

		AdsForLayerDto result = new AdsForLayerDto();
		result.setGeometry(feature.getGeometry());
		String nAds = (String) feature.getProperties().get(Constants.GEECO_ADS_LABEL_N_ADS);
		result.setCodiceAds(nAds);
		result.setFkTipoAds(TipoAdsEnum.getIdFromLayer(layerId));
		String id = (String)feature.getProperties().get(Constants.GEECO_ADS_LABEL_IDENTIFICATIVO);
		result.setIdFeature(ConvertUtil.convertToLong(id));
		return result;
	}

}
