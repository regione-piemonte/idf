/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.util.ArrayList;
import java.util.List;

public class PlainRelascopicaSemplice {
	
	private Long idgeoPtAds;
	private Integer fkTipoStrutturalePrinc;
	private Integer fattoreNumerazione;
	private String note;
	
	private List<RAdsrelSpecie> plainAdsrelSpecie;
	
	public PlainRelascopicaSemplice() {
		this.plainAdsrelSpecie = new ArrayList<>();
	}
	
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
	
	public Integer getFattoreNumerazione() {
		return fattoreNumerazione;
	}
	public void setFattoreNumerazione(Integer fattoreNumerazione) {
		this.fattoreNumerazione = fattoreNumerazione;
	}
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public List<RAdsrelSpecie> getPlainAdsrelSpecie() {
		return plainAdsrelSpecie;
	}
	public void setPlainAdsrelSpecie(List<RAdsrelSpecie> plainAdsrelSpecie) {
		this.plainAdsrelSpecie = plainAdsrelSpecie;
	}
	
	

	
}
