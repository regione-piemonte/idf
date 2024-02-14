/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonProperty;

public class TSettore {
	
	private Integer idSettore;
	private Integer fkAreaForestale;
	private String numSettore;
	private String nomeSettore;
	private BigDecimal pendenzaMedia;
	private Integer supTotale;
	private BigDecimal pendenzaMin;
	private BigDecimal pendenzaMax;
	private Integer quotaMin;
	private Integer quotaMax;
	private BigDecimal quotaMed;
	
	@JsonProperty("idSettore")
	public Integer getIdSettore() {
		return idSettore;
	}
	public void setIdSettore(Integer idSettore) {
		this.idSettore = idSettore;
	}
	
	@JsonProperty("fkAreaForestale")
	public Integer getFkAreaForestale() {
		return fkAreaForestale;
	}
	public void setFkAreaForestale(Integer fkAreaForestale) {
		this.fkAreaForestale = fkAreaForestale;
	}
	@JsonProperty("numSettore")
	public String getNumSettore() {
		return numSettore;
	}
	public void setNumSettore(String numSettore) {
		this.numSettore = numSettore;
	}
	@JsonProperty("nomeSettore")
	public String getNomeSettore() {
		return nomeSettore;
	}
	public void setNomeSettore(String nomeSettore) {
		this.nomeSettore = nomeSettore;
	}
	@JsonProperty("pendenzaMedia")
	public BigDecimal getPendenzaMedia() {
		return pendenzaMedia;
	}
	public void setPendenzaMedia(BigDecimal pendenzaMedia) {
		this.pendenzaMedia = pendenzaMedia;
	}
	@JsonProperty("supTotale")
	public Integer getSupTotale() {
		return supTotale;
	}
	public void setSupTotale(Integer supTotale) {
		this.supTotale = supTotale;
	}
	@JsonProperty("pendenzaMin")
	public BigDecimal getPendenzaMin() {
		return pendenzaMin;
	}
	public void setPendenzaMin(BigDecimal pendenzaMin) {
		this.pendenzaMin = pendenzaMin;
	}
	@JsonProperty("pendenzaMax")
	public BigDecimal getPendenzaMax() {
		return pendenzaMax;
	}
	public void setPendenzaMax(BigDecimal pendenzaMax) {
		this.pendenzaMax = pendenzaMax;
	}
	@JsonProperty("quotaMin")
	public Integer getQuotaMin() {
		return quotaMin;
	}
	public void setQuotaMin(Integer quotaMin) {
		this.quotaMin = quotaMin;
	}
	@JsonProperty("quotaMax")
	public Integer getQuotaMax() {
		return quotaMax;
	}
	public void setQuotaMax(Integer quotaMax) {
		this.quotaMax = quotaMax;
	}
	@JsonProperty("quotaMed")
	public BigDecimal getQuotaMed() {
		return quotaMed;
	}
	public void setQuotaMed(BigDecimal quotaMed) {
		this.quotaMed = quotaMed;
	}
	
	
	
	
}
