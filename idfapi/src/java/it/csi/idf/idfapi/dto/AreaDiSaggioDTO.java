/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class AreaDiSaggioDTO {
	
	private Long idgeoPtAds;
	private String codiceADS;
	private String tipologiaDiRilievo;
	private String ambitoDiRilevamento;
	private Integer idDettaglioAmbito;
	private String dettaglioAmbito;
	private String ambitoSpecifico;
	private String dataRilevamento;
	private String fkSoggettoPg;
	private String fkSoggettoPf;
	private String comune;
	private Double utmEST;
	private Double utmNORD;
	private String quota;
	private String espozione;	
	private String inclinazione;
	private String particellaForestale;
	private String proprieta;
	private Double densitCampionamento;
	private Integer raggioArea;
	private String categoriaForestale;
	private Integer tipoForestale;
	private Integer assetto;
	private Integer stadioSviluppo;
	
		
	
	public Long getIdgeoPtAds() {
		return idgeoPtAds;
	}
	public void setIdgeoPtAds(Long idgeoPtAds) {
		this.idgeoPtAds = idgeoPtAds;
	}
	public Double getDensitCampionamento() {
		return densitCampionamento;
	}
	public void setDensitCampionamento(Double densitCampionamento) {
		this.densitCampionamento = densitCampionamento;
	}
	public Integer getRaggioArea() {
		return raggioArea;
	}
	public void setRaggioArea(Integer raggioArea) {
		this.raggioArea = raggioArea;
	}
	public String getCategoriaForestale() {
		return categoriaForestale;
	}
	public void setCategoriaForestale(String categoriaForestale) {
		this.categoriaForestale = categoriaForestale;
	}
	public Integer getTipoForestale() {
		return tipoForestale;
	}
	public void setTipoForestale(Integer tipoForestale) {
		this.tipoForestale = tipoForestale;
	}
	public Integer getAssetto() {
		return assetto;
	}
	public void setAssetto(Integer assetto) {
		this.assetto = assetto;
	}
	public Integer getStadioSviluppo() {
		return stadioSviluppo;
	}
	public void setStadioSviluppo(Integer stadioSviluppo) {
		this.stadioSviluppo = stadioSviluppo;
	}
	public String getCodiceADS() {
		return codiceADS;
	}
	public void setCodiceADS(String codiceADS) {
		this.codiceADS = codiceADS;
	}
	public String getTipologiaDiRilievo() {
		return tipologiaDiRilievo;
	}
	public void setTipologiaDiRilievo(String tipologiaDiRilievo) {
		this.tipologiaDiRilievo = tipologiaDiRilievo;
	}
	public String getAmbitoDiRilevamento() {
		return ambitoDiRilevamento;
	}
	public void setAmbitoDiRilevamento(String ambitoDiRilevamento) {
		this.ambitoDiRilevamento = ambitoDiRilevamento;
	}
	
	public Integer getIdDettaglioAmbito() {
		return idDettaglioAmbito;
	}
	public void setIdDettaglioAmbito(Integer idDettaglioAmbito) {
		this.idDettaglioAmbito = idDettaglioAmbito;
	}
	public String getDettaglioAmbito() {
		return dettaglioAmbito;
	}
	public void setDettaglioAmbito(String dettaglioAmbito) {
		this.dettaglioAmbito = dettaglioAmbito;
	}
	
	public String getAmbitoSpecifico() {
		return ambitoSpecifico;
	}
	public void setAmbitoSpecifico(String ambitoSpecifico) {
		this.ambitoSpecifico = ambitoSpecifico;
	}
	public String getDataRilevamento() {
		return dataRilevamento;
	}
	public void setDataRilevamento(String dataRilevamento) {
		this.dataRilevamento = dataRilevamento;
	}
	public String getFkSoggettoPg() {
		return fkSoggettoPg;
	}
	public void setFkSoggettoPg(String fkSoggettoPg) {
		this.fkSoggettoPg = fkSoggettoPg;
	}
	public String getFkSoggettoPf() {
		return fkSoggettoPf;
	}
	public void setFkSoggettoPf(String fkSoggettoPf) {
		this.fkSoggettoPf = fkSoggettoPf;
	}
	public String getComune() {
		return comune;
	}
	public void setComune(String comune) {
		this.comune = comune;
	}
	public Double getUtmEST() {
		return utmEST;
	}
	public void setUtmEST(Double utmEST) {
		this.utmEST = utmEST;
	}
	public Double getUtmNORD() {
		return utmNORD;
	}
	public void setUtmNORD(Double utmNORD) {
		this.utmNORD = utmNORD;
	}
	public String getQuota() {
		return quota;
	}
	public void setQuota(String quota) {
		this.quota = quota;
	}
	public String getEspozione() {
		return espozione;
	}
	public void setEspozione(String espozione) {
		this.espozione = espozione;
	}
	public String getInclinazione() {
		return inclinazione;
	}
	public void setInclinazione(String inclinazione) {
		this.inclinazione = inclinazione;
	}
	public String getParticellaForestale() {
		return particellaForestale;
	}
	public void setParticellaForestale(String particellaForestale) {
		this.particellaForestale = particellaForestale;
	}
	public String getProprieta() {
		return proprieta;
	}
	public void setProprieta(String proprieta) {
		this.proprieta = proprieta;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AreaDiSaggioDTO [codiceADS=");
		builder.append(codiceADS);
		builder.append(", tipologiaDiRilievo=");
		builder.append(tipologiaDiRilievo);
		builder.append(", ambitoDiRilevamento=");
		builder.append(ambitoDiRilevamento);
		builder.append(", dettaglioAmbito=");
		builder.append(dettaglioAmbito);
		builder.append(", dataRilevamento=");
		builder.append(dataRilevamento);
		builder.append(", fkSoggettoPg=");
		builder.append(fkSoggettoPg);
		builder.append(", fkSoggettoPf=");
		builder.append(fkSoggettoPf);
		builder.append(", comune=");
		builder.append(comune);
		builder.append(", utmEST=");
		builder.append(utmEST);
		builder.append(", utmNORD=");
		builder.append(utmNORD);
		builder.append(", quota=");
		builder.append(quota);
		builder.append(", espozione=");
		builder.append(espozione);
		builder.append(", inclinazione=");
		builder.append(inclinazione);
		builder.append(", particellaForestale=");
		builder.append(particellaForestale);
		builder.append(", proprieta=");
		builder.append(proprieta);
		builder.append(", densitCampionamento=");
		builder.append(densitCampionamento);
		builder.append(", raggioArea=");
		builder.append(raggioArea);
		builder.append(", categoriaForestale=");
		builder.append(categoriaForestale);
		builder.append(", tipoForestale=");
		builder.append(tipoForestale);
		builder.append(", assetto=");
		builder.append(assetto);
		builder.append(", stadioSviluppo=");
		builder.append(stadioSviluppo);
		builder.append("]");
		return builder.toString();
	}
	

	
	
}
