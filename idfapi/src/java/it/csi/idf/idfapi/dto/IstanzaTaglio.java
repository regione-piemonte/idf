/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.time.LocalDate;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

import it.csi.idf.idfapi.config.LocalDateDefaultDeserializer;

public class IstanzaTaglio {

	private Integer idIstanza;
	private LocalDate anno;
	private Integer numIstanza;
	private LocalDate dataPresentazioneIstanza;
	private LocalDate dataAutorizzazioneIstanza;
	private String descIntervento;
	private String comuniInteressati;
	private Integer stimaMassaRetraibile;
	private String unita;
	private String tipoComunicazione;
	private String specieCoinvolte;
	private String stato;
	private String governo;
	private String tipoIstanza;
	private String tipoIntervento;
	private Integer categoriaIntervento;
	private boolean isDeleted;
	
	public Integer getIdIstanza() {
		return idIstanza;
	}
	public void setIdIstanza(Integer idIstanza) {
		this.idIstanza = idIstanza;
	}
	@JsonDeserialize(using = LocalDateDefaultDeserializer.class)
	public LocalDate getAnno() {
		return anno;
	}
	public void setAnno(LocalDate anno) {
		this.anno = anno;
	}
	public Integer getNumIstanza() {
		return numIstanza;
	}
	public void setNumIstanza(Integer numIstanza) {
		this.numIstanza = numIstanza;
	}
	@JsonDeserialize(using = LocalDateDefaultDeserializer.class)
	public LocalDate getDataPresentazioneIstanza() {
		return dataPresentazioneIstanza;
	}
	public void setDataPresentazioneIstanza(LocalDate dataPresentazioneIstanza) {
		this.dataPresentazioneIstanza = dataPresentazioneIstanza;
	}
	@JsonDeserialize(using = LocalDateDefaultDeserializer.class)
	public LocalDate getDataAutorizzazioneIstanza() {
		return dataAutorizzazioneIstanza;
	}
	public void setDataAutorizzazioneIstanza(LocalDate dataAutorizzazioneIstanza) {
		this.dataAutorizzazioneIstanza = dataAutorizzazioneIstanza;
	}
	public String getDescIntervento() {
		return descIntervento;
	}
	public void setDescIntervento(String descIntervento) {
		this.descIntervento = descIntervento;
	}
	public String getComuniInteressati() {
		return comuniInteressati;
	}
	public void setComuniInteressati(String comuniInteressati) {
		this.comuniInteressati = comuniInteressati;
	}
	public Integer getStimaMassaRetraibile() {
		return stimaMassaRetraibile;
	}
	public void setStimaMassaRetraibile(Integer stimaMassaRetraibile) {
		this.stimaMassaRetraibile = stimaMassaRetraibile;
	}
	public String getUnita() {
		return unita;
	}
	public void setUnita(String unita) {
		this.unita = unita;
	}
	public String getTipoComunicazione() {
		return tipoComunicazione;
	}
	public void setTipoComunicazione(String tipoComunicazione) {
		this.tipoComunicazione = tipoComunicazione;
	}
	
	public String getSpecieCoinvolte() {
		return specieCoinvolte;
	}
	public void setSpecieCoinvolte(String specieCoinvolte) {
		this.specieCoinvolte = specieCoinvolte;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public String getGoverno() {
		return governo;
	}
	public void setGoverno(String governo) {
		this.governo = governo;
	}
	
	public String getTipoIstanza() {
		return tipoIstanza;
	}
	public void setTipoIstanza(String tipoIstanza) {
		this.tipoIstanza = tipoIstanza;
	}
	
	public String getTipoIntervento() {
		return tipoIntervento;
	}
	public void setTipoIntervento(String tipoIntervento) {
		this.tipoIntervento = tipoIntervento;
	}
	public Integer getCategoriaIntervento() {
		return categoriaIntervento;
	}
	public void setCategoriaIntervento(Integer categoriaIntervento) {
		this.categoriaIntervento = categoriaIntervento;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numIstanza == null) ? 0 : numIstanza.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IstanzaTaglio other = (IstanzaTaglio) obj;
		if (numIstanza == null) {
			if (other.numIstanza != null)
				return false;
		} else if (!numIstanza.equals(other.numIstanza)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "IstanzaTaglio [idIstanza=" + idIstanza + ", anno=" + anno + ", numIstanza=" + numIstanza
				+ ", dataPresentazioneIstanza=" + dataPresentazioneIstanza + ", dataAutorizzazioneIstanza="
				+ dataAutorizzazioneIstanza + ", descIntervento=" + descIntervento + ", comuniInteressati="
				+ comuniInteressati + ", stimaMassaRetraibile=" + stimaMassaRetraibile + ", unita=" + unita
				+ ", tipoComunicazione=" + tipoComunicazione + ", stato=" + stato + ", governo=" + governo
				+ ", tipoIstanza=" + tipoIstanza + ", tipoIntervento=" + tipoIntervento + ", categoriaIntervento="
				+ categoriaIntervento + "]";
	}
	
	
	
	
}
