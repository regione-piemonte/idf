/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.time.LocalDate;
import java.util.Arrays;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import it.csi.idf.idfapi.config.LocalDateSerializer;

public class GeoPfaSearch {

	private Integer idGeoPlPfa;
	private String denominazione;
	private Object geometria;
	private Integer[] idComuni;
	private String[] istatProv;
	private String denominazioneComuni;
	private String denominazioneProvincie;
	private String siglaProvincie;
	private LocalDate dataInizioValidita;
	private LocalDate dataFineValidita;
	private LocalDate dataApprovazione;
	
	public Integer getIdGeoPlPfa() {
		return idGeoPlPfa;
	}
	public void setIdGeoPlPfa(Integer idGeoPlPfa) {
		this.idGeoPlPfa = idGeoPlPfa;
	}
	public String getDenominazione() {
		return denominazione;
	}
	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}
	public Object getGeometria() {
		return geometria;
	}
	public void setGeometria(Object geometria) {
		this.geometria = geometria;
	}
	public Integer[] getIdComuni() {
		return idComuni;
	}
	public void setIdComuni(Integer[] idComuni) {
		this.idComuni = idComuni;
	}
	public String[] getIstatProv() {
		return istatProv;
	}
	public void setIstatProv(String[] istatProv) {
		this.istatProv = istatProv;
	}
	public String getDenominazioneComuni() {
		return denominazioneComuni;
	}
	public void setDenominazioneComuni(String denominazioneComuni) {
		this.denominazioneComuni = denominazioneComuni;
	}
	public String getDenominazioneProvincie() {
		return denominazioneProvincie;
	}
	public void setDenominazioneProvincie(String denominazioneProvincie) {
		this.denominazioneProvincie = denominazioneProvincie;
	}
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDate getDataInizioValidita() {
		return dataInizioValidita;
	}
	public void setDataInizioValidita(LocalDate dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDate getDataFineValidita() {
		return dataFineValidita;
	}
	public void setDataFineValidita(LocalDate dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDate getDataApprovazione() {
		return dataApprovazione;
	}
	public void setDataApprovazione(LocalDate dataApprovazione) {
		this.dataApprovazione = dataApprovazione;
	}
	
	
	
	public String getSiglaProvincie() {
		return siglaProvincie;
	}
	public void setSiglaProvincie(String siglaProvincie) {
		this.siglaProvincie = siglaProvincie;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GeoPfaSearch [idGeoPlPfa=");
		builder.append(idGeoPlPfa);
		builder.append(", denominazione=");
		builder.append(denominazione);
		builder.append(", geometria=");
		builder.append(geometria);
		builder.append(", idComuni=");
		builder.append(Arrays.toString(idComuni));
		builder.append(", istatProv=");
		builder.append(Arrays.toString(istatProv));
		builder.append(", denominazioneComuni=");
		builder.append(denominazioneComuni);
		builder.append(", denominazioneProvincie=");
		builder.append(denominazioneProvincie);
		builder.append(", siglaProvincie=");
		builder.append(siglaProvincie);
		builder.append(", dataInizioValidita=");
		builder.append(dataInizioValidita);
		builder.append(", dataFineValidita=");
		builder.append(dataFineValidita);
		builder.append(", dataApprovazione=");
		builder.append(dataApprovazione);
		builder.append("]");
		return builder.toString();
	}
	
}
