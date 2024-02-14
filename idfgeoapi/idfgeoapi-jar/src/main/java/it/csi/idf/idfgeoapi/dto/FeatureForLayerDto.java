/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfgeoapi.dto;

import java.io.Serializable;

import org.locationtech.jts.geom.Geometry;

public class FeatureForLayerDto  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -324195460318559988L;
	
	private Geometry geometry;
	private Long idFeature;
	
	/**
	 * @return the geometry
	 */
	public Geometry getGeometry() {
		return geometry;
	}
	/**
	 * @param geometry the geometry to set
	 */
	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}
	/**
	 * @return the idFeature
	 */
	public Long getIdFeature() {
		return idFeature;
	}
	/**
	 * @param idFeature the idFeature to set
	 */
	public void setIdFeature(Long idFeature) {
		this.idFeature = idFeature;
	}
	
	
}
