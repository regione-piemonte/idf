/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class AlberiCampioneDominanteDTO {
	
	private Integer specie;
	private String latino;
	private String qualita;
	private Integer diametro;
	private Integer altezza;
	private Integer incremento;
	private Integer eta;
	private String note;
	private String codiceAds;
	private String codTipoCampione;
	private String codiceSpecie;
	private String specieLationo;
	private String specieVolgare;
	private String gruppo;
	private String semePollone;

	public Integer getSpecie() {
		return specie;
	}
	public void setSpecie(Integer specie) {
		this.specie = specie;
	}
	
	public String getLatino() {
		return latino;
	}
	public void setLatino(String specieName) {
		this.latino = specieName;
	}
	
	public String getQualita() {
		return qualita;
	}
	public void setQualita(String qualita) {
		this.qualita = qualita;
	}
	
	public Integer getDiametro() {
		return diametro;
	}
	public void setDiametro(Integer diametro) {
		this.diametro = diametro;
	}
	
	public Integer getAltezza() {
		return altezza;
	}
	public void setAltezza(Integer altezza) {
		this.altezza = altezza;
	}
	
	public Integer getIncremento() {
		return incremento;
	}
	public void setIncremento(Integer incremento) {
		this.incremento = incremento;
	}
	
	public Integer getEta() {
		return eta;
	}
	public void setEta(Integer eta) {
		this.eta = eta;
	}
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	public String getCodiceAds() {
		return codiceAds;
	}
	public void setCodiceAds(String codiceAds) {
		this.codiceAds = codiceAds;
	}
	
	public String getCodTipoCampione() {
		return codTipoCampione;
	}
	public void setCodTipoCampione(String codTipoCampione) {
		this.codTipoCampione = codTipoCampione;
	}
	
	public String getCodiceSpecie() {
		return codiceSpecie;
	}
	public void setCodiceSpecie(String codiceSpecie) {
		this.codiceSpecie = codiceSpecie;
	}
		
	public String getSpecieLationo() {
		return specieLationo;
	}
	public void setSpecieLationo(String specieLationo) {
		this.specieLationo = specieLationo;
	}
	
	public String getSpecieVolgare() {
		return specieVolgare;
	}
	public void setSpecieVolgare(String specieVolgare) {
		this.specieVolgare = specieVolgare;
	}
	public String getGruppo() {
		return gruppo;
	}
	public void setGruppo(String gruppo) {
		this.gruppo = gruppo;
	}
	
	public String getSemePollone() {
		return semePollone;
	}
	public void setSemePollone(String semePollone) {
		this.semePollone = semePollone;
	}

	
}
