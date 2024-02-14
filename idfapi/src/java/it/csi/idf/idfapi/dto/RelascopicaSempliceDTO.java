/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;

public class RelascopicaSempliceDTO {
	
	private String specie;
	private String specieLatino;
	private String note;
	private String codiceAds;
	private String comune;
	private Integer alberiSeme;
	private Integer alberiPollone;
	private String tipologia;
	private Integer fattoreNumerazione;
	private String categoriaForestale;
	private String dataRilevamento;
	private String espozione;	
	private String inclinazione;
	private String particella;
	private String proprieta;
	private String tipoForestale;
	private Integer quota;
	private String tipoStrutturale;
	private String tipo;
	private Integer diametro;
	private Integer altezza;
	private Integer incremento;
	private String codTipoCampione;
	private BigDecimal utmNord;
	private BigDecimal utmEst;
	
	
	public BigDecimal getUtmNord() {
		return utmNord;
	}
	public void setUtmNord(BigDecimal utmNord) {
		this.utmNord = utmNord;
	}
	
	public BigDecimal getUtmEst() {
		return utmEst;
	}
	public void setUtmEst(BigDecimal utmEst) {
		this.utmEst = utmEst;
	}
	
	public String getCodTipoCampione() {
		return codTipoCampione;
	}
	public void setCodTipoCampione(String codTipoCampione) {
		this.codTipoCampione = codTipoCampione;
	}
	
	public String getDataRilevamento() {
		return dataRilevamento;
	}
	public void setDataRilevamento(String dataRilevamento) {
		this.dataRilevamento = dataRilevamento;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	
	public String getSpecie() {
		return specie;
	}
	public void setSpecie(String specie) {
		this.specie = specie;
	}
	
	
	
	public String getSpecieLatino() {
		return specieLatino;
	}
	public void setSpecieLatino(String specieLatino) {
		this.specieLatino = specieLatino;
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
	
	public String getComune() {
		return comune;
	}
	public void setComune(String comune) {
		this.comune = comune;
	}
	
	public Integer getAlberiSeme() {
		return alberiSeme;
	}
	public void setAlberiSeme(Integer alberiSeme) {
		this.alberiSeme = alberiSeme;
	}
	
	public Integer getAlberiPollone() {
		return alberiPollone;
	}
	public void setAlberiPollone(Integer alberiPollone) {
		this.alberiPollone = alberiPollone;
	}
	
	public String getTipologia() {
		return tipologia;
	}
	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}
	
	public Integer getFattoreNumerazione() {
		return fattoreNumerazione;
	}
	public void setFattoreNumerazione(Integer fattoreNumerazione) {
		this.fattoreNumerazione = fattoreNumerazione;
	}
	
	public String getCategoriaForestale() {
		return categoriaForestale;
	}
	public void setCategoriaForestale(String categoriaForestale) {
		this.categoriaForestale = categoriaForestale;
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
	public String getParticella() {
		return particella;
	}
	public void setParticella(String particella) {
		this.particella = particella;
	}
	public String getProprieta() {
		return proprieta;
	}
	public void setProprieta(String proprieta) {
		this.proprieta = proprieta;
	}
	public String getTipoForestale() {
		return tipoForestale;
	}
	public void setTipoForestale(String tipoForestale) {
		this.tipoForestale = tipoForestale;
	}
	public Integer getQuota() {
		return quota;
	}
	public void setQuota(Integer quota) {
		this.quota = quota;
	}
	public String getTipoStrutturale() {
		return tipoStrutturale;
	}
	public void setTipoStrutturale(String tipoStrutturale) {
		this.tipoStrutturale = tipoStrutturale;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RelascopicaSempliceDTO [specie=");
		builder.append(specie);
		builder.append(", note=");
		builder.append(note);
		builder.append(", codiceAds=");
		builder.append(codiceAds);
		builder.append(", comune=");
		builder.append(comune);
		builder.append(", alberiSeme=");
		builder.append(alberiSeme);
		builder.append(", alberiPollone=");
		builder.append(alberiPollone);
		builder.append(", tipologia=");
		builder.append(tipologia);
		builder.append(", fattoreNumerazione=");
		builder.append(fattoreNumerazione);
		builder.append(", categoriaForestale=");
		builder.append(categoriaForestale);
		builder.append(", dataRilevamento=");
		builder.append(dataRilevamento);
		builder.append(", espozione=");
		builder.append(espozione);
		builder.append(", inclinazione=");
		builder.append(inclinazione);
		builder.append(", particella=");
		builder.append(particella);
		builder.append(", proprieta=");
		builder.append(proprieta);
		builder.append(", tipoForestale=");
		builder.append(tipoForestale);
		builder.append(", quota=");
		builder.append(quota);
		builder.append(", tipoStrutturale=");
		builder.append(tipoStrutturale);
		builder.append(", tipo=");
		builder.append(tipo);
		builder.append(", diametro=");
		builder.append(diametro);
		builder.append(", altezza=");
		builder.append(altezza);
		builder.append(", incremento=");
		builder.append(incremento);
		builder.append(", codTipoCampione=");
		builder.append(codTipoCampione);
		builder.append(", utmNord=");
		builder.append(utmNord);
		builder.append(", utmEst=");
		builder.append(utmEst);
		builder.append("]");
		return builder.toString();
	}
	
	
	

	
	
}
