/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.io.Serializable;

import it.csi.ecogis.geeco_java_client.dto.internal.GeoJsonFeature;

public class GeecoFeature implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2652374984838163688L;
	
	private GeoJsonFeature feature;

	public GeoJsonFeature getFeature() {
		return feature;
	}

	public void setFeature(GeoJsonFeature feature) {
		this.feature = feature;
	}
	
	
}
