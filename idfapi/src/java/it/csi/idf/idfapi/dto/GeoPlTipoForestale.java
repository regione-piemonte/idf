/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import org.codehaus.jackson.annotate.JsonProperty;

public class GeoPlTipoForestale {
	private Integer idGeoTipoForestale;
	private Integer fkTipoForestale;
	private Object geometria;
	
	@JsonProperty("idGeoTipoForestale")
	public Integer getIdGeoTipoForestale() {
		return idGeoTipoForestale;
	}
	
	public void setIdGeoTipoForestale(Integer idGeoTipoForestale) {
		this.idGeoTipoForestale = idGeoTipoForestale;
	}
	
	@JsonProperty("fkTipoForestale")
	public Integer getFkTipoForestale() {
		return fkTipoForestale;
	}
	
	public void setFkTipoForestale(Integer fkTipoForestale) {
		this.fkTipoForestale = fkTipoForestale;
	}
	
	@JsonProperty("geometria")
	public Object getGeometria() {
		return geometria;
	}
	
	public void setGeometria(Object geometria) {
		this.geometria = geometria;
	}
	
	
}
