/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class GeecoAdsFeature extends GeecoFeature {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2352603614664526066L;
	
	private String idgeoPtAds;
	private String codiceAds;
	private String geometria;
	
	/**
	 * @return the idgeoPtAds
	 */
	public String getIdgeoPtAds() {
		return idgeoPtAds;
	}
	/**
	 * @param idgeoPtAds the idgeoPtAds to set
	 */
	public void setIdgeoPtAds(String idgeoPtAds) {
		this.idgeoPtAds = idgeoPtAds;
	}
	/**
	 * @return the codiceAds
	 */
	public String getCodiceAds() {
		return codiceAds;
	}
	/**
	 * @param codiceAds the codiceAds to set
	 */
	public void setCodiceAds(String codiceAds) {
		this.codiceAds = codiceAds;
	}
	/**
	 * @return the geometria
	 */
	public String getGeometria() {
		return geometria;
	}
	/**
	 * @param geometria the geometria to set
	 */
	public void setGeometria(String geometria) {
		this.geometria = geometria;
	}
	
	
}
