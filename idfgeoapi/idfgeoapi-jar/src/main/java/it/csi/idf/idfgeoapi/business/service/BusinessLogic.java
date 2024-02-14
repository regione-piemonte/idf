/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfgeoapi.business.service;

import it.csi.ecogis.util.dto.GeoJSONFeature;
import it.csi.idf.idfgeoapi.dto.*;
import it.csi.idf.idfgeoapi.exception.DaoException;
import it.csi.idf.idfgeoapi.exception.IdfGeoException;

public interface BusinessLogic {

	public GeoJSONFeature insertFeatureOnLayer(String layerId, GeoJSONFeature feature) throws DaoException, IdfGeoException;
	public GeoJSONFeature updateFeatureOnLayer(String layerId, GeoJSONFeature feature) throws DaoException; 
	public void deleteFeatureOnLayer(String layerId, Long featureId) throws DaoException;
	public void testResources()throws DaoException; 
	
}
