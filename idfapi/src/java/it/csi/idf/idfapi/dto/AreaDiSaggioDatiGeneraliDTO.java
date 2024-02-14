/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import it.csi.idf.idfapi.config.LocalDateDeserializer;
import it.csi.idf.idfapi.config.LocalDateSerializer;

public class AreaDiSaggioDatiGeneraliDTO {
	
	Long idgeoPtAds;
	String codiceADS;	
    BigDecimal utmNord;
    BigDecimal utmEst;
    String provincia;
    Integer comune;
    Integer tipologia;
    Integer ambitoDiRilevamento;
    Integer dettaglioAmbito;    
    String ambitoSpecifico;
    LocalDate dataRilevamento;
    Integer quota;
    Integer espozione;
    Integer inclinazione;
    Long particellaForestale;
    Integer proprieta;
    
    public AreaDiSaggioDatiGeneraliDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getIdgeoPtAds() {
		return idgeoPtAds;
	}
	public void setIdgeoPtAds(Long idgeoPtAds) {
		this.idgeoPtAds = idgeoPtAds;
	}

	public String getCodiceADS() {
		return codiceADS;
	}
	public void setCodiceADS(String codiceADS) {
		this.codiceADS = codiceADS;
	}

	public BigDecimal getUtmNord() {
		//richiesta in visualizzazione solo parte intera
		return utmNord==null?null:utmNord.setScale(0, RoundingMode.HALF_UP);
	}
	public void setUtmNord(BigDecimal utmNord) {
		this.utmNord = utmNord;
	}

	public BigDecimal getUtmEst() {
		//richiesta in visualizzazione solo parte intera
		return utmEst==null?null:utmEst.setScale(0, RoundingMode.HALF_UP);
	}
	public void setUtmEst(BigDecimal utmEst) {
		this.utmEst = utmEst;
	}

	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public Integer getComune() {
		return comune;
	}
	public void setComune(Integer comune) {
		this.comune = comune;
	}

	public Integer getTipologia() {
		return tipologia;
	}
	public void setTipologia(Integer tipologia) {
		this.tipologia = tipologia;
	}
	
	public Integer getAmbitoDiRilevamento() {
		return ambitoDiRilevamento;
	}

	public void setAmbitoDiRilevamento(Integer ambitoDiRilevamento) {
		this.ambitoDiRilevamento = ambitoDiRilevamento;
	}

	public Integer getDettaglioAmbito() {
		return dettaglioAmbito;
	}
	public void setDettaglioAmbito(Integer dettaglioAmbito) {
		this.dettaglioAmbito = dettaglioAmbito;
	}

	public String getAmbitoSpecifico() {
		return ambitoSpecifico;
	}
	public void setAmbitoSpecifico(String ambitoSpecifico) {
		this.ambitoSpecifico = ambitoSpecifico;
	}

	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	public LocalDate getDataRilevamento() {
		return dataRilevamento;
	}
	public void setDataRilevamento(LocalDate dataRilevamento) {
		this.dataRilevamento = dataRilevamento;
	}

	public Integer getQuota() {
		return quota;
	}
	public void setQuota(Integer quota) {
		this.quota = quota;
	}

	public Integer getEspozione() {
		return espozione;
	}

	public void setEspozione(Integer espozione) {
		this.espozione = espozione;
	}

	public Integer getInclinazione() {
		return inclinazione;
	}
	public void setInclinazione(Integer inclinazione) {
		this.inclinazione = inclinazione;
	}

	public Long getParticellaForestale() {
		return particellaForestale;
	}
	public void setParticellaForestale(Long particellaForestale) {
		this.particellaForestale = particellaForestale;
	}

	public Integer getProprieta() {
		return proprieta;
	}
	public void setProprieta(Integer proprieta) {
		this.proprieta = proprieta;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AreaDiSaggioDatiGeneraliDTO [idgeoPtAds=");
		builder.append(idgeoPtAds);
		builder.append(", codiceADS=");
		builder.append(codiceADS);
		builder.append(", utmNord=");
		builder.append(utmNord);
		builder.append(", utmEst=");
		builder.append(utmEst);
		builder.append(", provincia=");
		builder.append(provincia);
		builder.append(", comune=");
		builder.append(comune);
		builder.append(", tipologia=");
		builder.append(tipologia);
		builder.append(", ambitoDiRilevamento=");
		builder.append(ambitoDiRilevamento);
		builder.append(", dettaglioAmbito=");
		builder.append(dettaglioAmbito);
		builder.append(", ambitoSpecifico=");
		builder.append(ambitoSpecifico);
		builder.append(", dataRilevamento=");
		builder.append(dataRilevamento);
		builder.append(", quota=");
		builder.append(quota);
		builder.append(", esposizione=");
		builder.append(espozione);
		builder.append(", inclinazione=");
		builder.append(inclinazione);
		builder.append(", particellaForestale=");
		builder.append(particellaForestale);
		builder.append(", proprieta=");
		builder.append(proprieta);
		builder.append("]");
		return builder.toString();
	}
            
}
