/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class RelascopicaSemplice {
	private Long idgeoPtAds;
	private Integer fkTipoStrutturalePrinc;
	private Integer fkTipoStrutturaleSecond;
	private Integer fattoreNumerazione;
	public Long getIdgeoPtAds() {
		return idgeoPtAds;
	}
	public void setIdgeoPtAds(Long idgeoPtAds) {
		this.idgeoPtAds = idgeoPtAds;
	}
	public Integer getFkTipoStrutturalePrinc() {
		return fkTipoStrutturalePrinc;
	}
	public void setFkTipoStrutturalePrinc(Integer fkTipoStrutturalePrinc) {
		this.fkTipoStrutturalePrinc = fkTipoStrutturalePrinc;
	}
	public Integer getFkTipoStrutturaleSecond() {
		return fkTipoStrutturaleSecond;
	}
	public void setFkTipoStrutturaleSecond(Integer fkTipoStrutturaleSecond) {
		this.fkTipoStrutturaleSecond = fkTipoStrutturaleSecond;
	}
	public Integer getFattoreNumerazione() {
		return fattoreNumerazione;
	}
	public void setFattoreNumerazione(Integer fattoreNumerazione) {
		this.fattoreNumerazione = fattoreNumerazione;
	}

}
