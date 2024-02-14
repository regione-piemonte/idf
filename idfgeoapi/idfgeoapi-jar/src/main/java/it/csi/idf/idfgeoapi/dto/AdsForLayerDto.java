/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfgeoapi.dto;

public class AdsForLayerDto extends FeatureForLayerDto {
	
	private static final long serialVersionUID = -494475837215632594L;
	
	private String codiceAds;
	private Integer idGeoPtAds;
    private Integer fkComune;
    private Integer fkSoggetto;
    private String dataRil;
    private Integer fkSettore;
    private Integer fkProprieta;
    private Integer fkTipoAds;
    private Integer fkAssetto;
    private Integer fkEsposizione;
    private Integer fkComunitaMontana;
    private Integer fkTipoForestale;
    private Integer fkDestinazione;
    private Integer fkPriorita;
    private String flgDia;
    private String quota;
    private String inclinazione;
    private String dataInizioValidita;
    private String dataFineValidita;
    private String note;
    private Integer fkAmbito;
    private Integer idgeoPlParticellaForestale;
    private Integer fkDanno;
    private Integer fkTipoIntervento;
    private Integer fkStatoAds;
    private String ambitoSpecifico;
    private String geometriaString;
    
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
	 * @return the idGeoPtAds
	 */
	public Integer getIdGeoPtAds() {
		return idGeoPtAds;
	}
	/**
	 * @param idGeoPtAds the idGeoPtAds to set
	 */
	public void setIdGeoPtAds(Integer idGeoPtAds) {
		this.idGeoPtAds = idGeoPtAds;
	}
	/**
	 * @return the fkComune
	 */
	public Integer getFkComune() {
		return fkComune;
	}
	/**
	 * @param fkComune the fkComune to set
	 */
	public void setFkComune(Integer fkComune) {
		this.fkComune = fkComune;
	}
	/**
	 * @return the fkSoggetto
	 */
	public Integer getFkSoggetto() {
		return fkSoggetto;
	}
	/**
	 * @param fkSoggetto the fkSoggetto to set
	 */
	public void setFkSoggetto(Integer fkSoggetto) {
		this.fkSoggetto = fkSoggetto;
	}
	/**
	 * @return the dataRil
	 */
	public String getDataRil() {
		return dataRil;
	}
	/**
	 * @param dataRil the dataRil to set
	 */
	public void setDataRil(String dataRil) {
		this.dataRil = dataRil;
	}
	/**
	 * @return the fkSettore
	 */
	public Integer getFkSettore() {
		return fkSettore;
	}
	/**
	 * @param fkSettore the fkSettore to set
	 */
	public void setFkSettore(Integer fkSettore) {
		this.fkSettore = fkSettore;
	}
	/**
	 * @return the fkProprieta
	 */
	public Integer getFkProprieta() {
		return fkProprieta;
	}
	/**
	 * @param fkProprieta the fkProprieta to set
	 */
	public void setFkProprieta(Integer fkProprieta) {
		this.fkProprieta = fkProprieta;
	}
	/**
	 * @return the fkTipoAds
	 */
	public Integer getFkTipoAds() {
		return fkTipoAds;
	}
	/**
	 * @param fkTipoAds the fkTipoAds to set
	 */
	public void setFkTipoAds(Integer fkTipoAds) {
		this.fkTipoAds = fkTipoAds;
	}
	/**
	 * @return the fkAssetto
	 */
	public Integer getFkAssetto() {
		return fkAssetto;
	}
	/**
	 * @param fkAssetto the fkAssetto to set
	 */
	public void setFkAssetto(Integer fkAssetto) {
		this.fkAssetto = fkAssetto;
	}
	/**
	 * @return the fkEsposizione
	 */
	public Integer getFkEsposizione() {
		return fkEsposizione;
	}
	/**
	 * @param fkEsposizione the fkEsposizione to set
	 */
	public void setFkEsposizione(Integer fkEsposizione) {
		this.fkEsposizione = fkEsposizione;
	}
	/**
	 * @return the fkComunitaMontana
	 */
	public Integer getFkComunitaMontana() {
		return fkComunitaMontana;
	}
	/**
	 * @param fkComunitaMontana the fkComunitaMontana to set
	 */
	public void setFkComunitaMontana(Integer fkComunitaMontana) {
		this.fkComunitaMontana = fkComunitaMontana;
	}
	/**
	 * @return the fkTipoForestale
	 */
	public Integer getFkTipoForestale() {
		return fkTipoForestale;
	}
	/**
	 * @param fkTipoForestale the fkTipoForestale to set
	 */
	public void setFkTipoForestale(Integer fkTipoForestale) {
		this.fkTipoForestale = fkTipoForestale;
	}
	/**
	 * @return the fkDestinazione
	 */
	public Integer getFkDestinazione() {
		return fkDestinazione;
	}
	/**
	 * @param fkDestinazione the fkDestinazione to set
	 */
	public void setFkDestinazione(Integer fkDestinazione) {
		this.fkDestinazione = fkDestinazione;
	}
	/**
	 * @return the fkPriorita
	 */
	public Integer getFkPriorita() {
		return fkPriorita;
	}
	/**
	 * @param fkPriorita the fkPriorita to set
	 */
	public void setFkPriorita(Integer fkPriorita) {
		this.fkPriorita = fkPriorita;
	}
	/**
	 * @return the flgDia
	 */
	public String getFlgDia() {
		return flgDia;
	}
	/**
	 * @param flgDia the flgDia to set
	 */
	public void setFlgDia(String flgDia) {
		this.flgDia = flgDia;
	}
	/**
	 * @return the quota
	 */
	public String getQuota() {
		return quota;
	}
	/**
	 * @param quota the quota to set
	 */
	public void setQuota(String quota) {
		this.quota = quota;
	}
	/**
	 * @return the inclinazione
	 */
	public String getInclinazione() {
		return inclinazione;
	}
	/**
	 * @param inclinazione the inclinazione to set
	 */
	public void setInclinazione(String inclinazione) {
		this.inclinazione = inclinazione;
	}
	/**
	 * @return the dayaInizioValidita
	 */
	public String getDataInizioValidita() {
		return dataInizioValidita;
	}
	/**
	 * @param dayaInizioValidita the dayaInizioValidita to set
	 */
	public void setDataInizioValidita(String dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}
	/**
	 * @return the dataFineValidita
	 */
	public String getDataFineValidita() {
		return dataFineValidita;
	}
	/**
	 * @param dataFineValidita the dataFineValidita to set
	 */
	public void setDataFineValidita(String dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}
	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}
	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * @return the fkAmbito
	 */
	public Integer getFkAmbito() {
		return fkAmbito;
	}
	/**
	 * @param fkAmbito the fkAmbito to set
	 */
	public void setFkAmbito(Integer fkAmbito) {
		this.fkAmbito = fkAmbito;
	}
	/**
	 * @return the idgeoPlParticellaForestale
	 */
	public Integer getIdgeoPlParticellaForestale() {
		return idgeoPlParticellaForestale;
	}
	/**
	 * @param idgeoPlParticellaForestale the idgeoPlParticellaForestale to set
	 */
	public void setIdgeoPlParticellaForestale(Integer idgeoPlParticellaForestale) {
		this.idgeoPlParticellaForestale = idgeoPlParticellaForestale;
	}
	/**
	 * @return the fkDanno
	 */
	public Integer getFkDanno() {
		return fkDanno;
	}
	/**
	 * @param fkDanno the fkDanno to set
	 */
	public void setFkDanno(Integer fkDanno) {
		this.fkDanno = fkDanno;
	}
	/**
	 * @return the fkTipoIntervento
	 */
	public Integer getFkTipoIntervento() {
		return fkTipoIntervento;
	}
	/**
	 * @param fkTipoIntervento the fkTipoIntervento to set
	 */
	public void setFkTipoIntervento(Integer fkTipoIntervento) {
		this.fkTipoIntervento = fkTipoIntervento;
	}
	/**
	 * @return the fkStatoAds
	 */
	public Integer getFkStatoAds() {
		return fkStatoAds;
	}
	/**
	 * @param fkStatoAds the fkStatoAds to set
	 */
	public void setFkStatoAds(Integer fkStatoAds) {
		this.fkStatoAds = fkStatoAds;
	}
	/**
	 * @return the ambitoSpecifico
	 */
	public String getAmbitoSpecifico() {
		return ambitoSpecifico;
	}
	/**
	 * @param ambitoSpecifico the ambitoSpecifico to set
	 */
	public void setAmbitoSpecifico(String ambitoSpecifico) {
		this.ambitoSpecifico = ambitoSpecifico;
	}
	/**
	 * @return the geometriaString
	 */
	public String getGeometriaString() {
		return geometriaString;
	}
	/**
	 * @param geometriaString the geometriaString to set
	 */
	public void setGeometriaString(String geometriaString) {
		this.geometriaString = geometriaString;
	}


	

	
	
	
	
}
