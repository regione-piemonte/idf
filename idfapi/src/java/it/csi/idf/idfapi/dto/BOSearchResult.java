/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import it.csi.idf.idfapi.config.LocalDateDeserializer;
import it.csi.idf.idfapi.config.LocalDateSerializer;
import it.csi.idf.idfapi.util.SuperficieCompensationEnum;

public class BOSearchResult {
	
	private int idIstanza;
	private TipoIstanzaResource tipologiaIstanza;
	private String annoIstanza;
	private Integer numeroIstanza;
	private StatoIstanzaResource statoIstanza;
	private LocalDate dataPresentazione;
	private SoggettoResource richiedente;
	
	private List<ComuneResource> comuneInfo;
	private String transfInfo;
	private ComuneResource comune;
	private boolean areeProtette;
	private boolean natura2000;
	private boolean populamenti;
	private boolean vincIdrogeologico;
	private SuperficieCompensationEnum compensazione;
	private BigDecimal euro;
	
	public int getIdIstanza() {
		return idIstanza;
	}
	public void setIdIstanza(int idIstanza) {
		this.idIstanza = idIstanza;
	}
	public TipoIstanzaResource getTipologiaIstanza() {
		return tipologiaIstanza;
	}
	public void setTipologiaIstanza(TipoIstanzaResource tipologiaIstanza) {
		this.tipologiaIstanza = tipologiaIstanza;
	}
	public String getAnnoIstanza() {
		return annoIstanza;
	}
	public void setAnnoIstanza(String annoIstanza) {
		this.annoIstanza = annoIstanza;
	}
	public Integer getNumeroIstanza() {
		return numeroIstanza;
	}
	public void setNumeroIstanza(Integer numeroIstanza) {
		this.numeroIstanza = numeroIstanza;
	}
	public StatoIstanzaResource getStatoIstanza() {
		return statoIstanza;
	}
	public void setStatoIstanza(StatoIstanzaResource statoIstanza) {
		this.statoIstanza = statoIstanza;
	}
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	public LocalDate getDataPresentazione() {
		return dataPresentazione;
	}
	public void setDataPresentazione(LocalDate dataPresentazione) {
		this.dataPresentazione = dataPresentazione;
	}
	public SoggettoResource getRichiedente() {
		return richiedente;
	}
	public void setRichiedente(SoggettoResource richiedente) {
		this.richiedente = richiedente;
	}
	public ComuneResource getComune() {
		return comune;
	}
	public void setComune(ComuneResource comune) {
		this.comune = comune;
	}
	public boolean isAreeProtette() {
		return areeProtette;
	}
	public void setAreeProtette(boolean areeProtette) {
		this.areeProtette = areeProtette;
	}
	public boolean isNatura2000() {
		return natura2000;
	}
	public void setNatura2000(boolean natura2000) {
		this.natura2000 = natura2000;
	}
	public boolean isPopulamenti() {
		return populamenti;
	}
	public void setPopulamenti(boolean populamenti) {
		this.populamenti = populamenti;
	}
	public boolean isVincIdrogeologico() {
		return vincIdrogeologico;
	}
	public void setVincIdrogeologico(boolean vincIdrogeologico) {
		this.vincIdrogeologico = vincIdrogeologico;
	}
	public SuperficieCompensationEnum getCompensazione() {
		return compensazione;
	}
	public void setCompensazione(SuperficieCompensationEnum compensazione) {
		this.compensazione = compensazione;
	}
	public BigDecimal getEuro() {
		return euro;
	}
	public void setEuro(BigDecimal euro) {
		this.euro = euro;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BOSearchResult [idIstanza=");
		builder.append(idIstanza);
		builder.append(", tipologiaIstanza=");
		builder.append(tipologiaIstanza);
		builder.append(", annoIstanza=");
		builder.append(annoIstanza);
		builder.append(", numeroIstanza=");
		builder.append(numeroIstanza);
		builder.append(", statoIstanza=");
		builder.append(statoIstanza);
		builder.append(", dataPresentazione=");
		builder.append(dataPresentazione);
		builder.append(", richiedente=");
		builder.append(richiedente);
		builder.append(", comune=");
		builder.append(comune);
		builder.append(", areeProtette=");
		builder.append(areeProtette);
		builder.append(", natura2000=");
		builder.append(natura2000);
		builder.append(", populamenti=");
		builder.append(populamenti);
		builder.append(", vincIdrogeologico=");
		builder.append(vincIdrogeologico);
		builder.append(", compensazione=");
		builder.append(compensazione);
		builder.append(", euro=");
		builder.append(euro);
		builder.append("]");
		return builder.toString();
	}
	public List<ComuneResource> getComuneInfo() {
		return comuneInfo;
	}
	public void setComuneIinfo(List<ComuneResource> comuneInfo) {
		this.comuneInfo = comuneInfo;
	}
	public String getTransfInfo() {
		return transfInfo;
	}
	public void setTransfInfo(String transfInfo) {
		this.transfInfo = transfInfo;
	}
}
