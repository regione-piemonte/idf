/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.time.LocalDate;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

import it.csi.idf.idfapi.config.LocalDateDefaultDeserializer;

public class FatDocumentoAllegato {
	
	private Integer idDocumentoAllegato;
	private Integer idGeoPlPfa;
	private Integer fkIntervento;
	private Integer idTipoAllegato;
	private String descrTipoAllegato = "";
	private String nomeAllegato = "";
	private Long dimensioneAllegatoKB;
	private LocalDate dataIniziValidita;
	private LocalDate dataFineValidita;
	private LocalDate dataInserimento;
	private LocalDate dataAggiornamento;
	private Integer fkConfigUtente;
	private String note = "";
	private boolean isDeleted;
	
	private String uidIndex;
	private String idDocDoqui;
	private String udClassificazioneDoqui;
	
	public Integer getIdDocumentoAllegato() {
		return idDocumentoAllegato;
	}
	public void setIdDocumentoAllegato(Integer idDocumentoAllegato) {
		this.idDocumentoAllegato = idDocumentoAllegato;
	}
	public Integer getIdGeoPlPfa() {
		return idGeoPlPfa;
	}
	public void setIdGeoPlPfa(Integer idGeoPlPfa) {
		this.idGeoPlPfa = idGeoPlPfa;
	}
	public Integer getFkIntervento() {
		return fkIntervento;
	}
	public void setFkIntervento(Integer fkIntervento) {
		this.fkIntervento = fkIntervento;
	}
	public Integer getIdTipoAllegato() {
		return idTipoAllegato;
	}
	public void setIdTipoAllegato(Integer idTipoAllegato) {
		this.idTipoAllegato = idTipoAllegato;
	}
	public String getDescrTipoAllegato() {
		return descrTipoAllegato;
	}
	public void setDescrTipoAllegato(String descrTipoAllegato) {
		this.descrTipoAllegato = descrTipoAllegato;
	}
	public String getNomeAllegato() {
		return nomeAllegato;
	}
	public void setNomeAllegato(String nomeAllegato) {
		this.nomeAllegato = nomeAllegato;
	}
	public Long getDimensioneAllegatoKB() {
		return dimensioneAllegatoKB;
	}
	public void setDimensioneAllegatoKB(Long dimensioneAllegatoKB) {
		this.dimensioneAllegatoKB = dimensioneAllegatoKB;
	}
	@JsonDeserialize(using = LocalDateDefaultDeserializer.class)
	public LocalDate getDataIniziValidita() {
		return dataIniziValidita;
	}
	public void setDataIniziValidita(LocalDate dataIniziValidita) {
		this.dataIniziValidita = dataIniziValidita;
	}
	@JsonDeserialize(using = LocalDateDefaultDeserializer.class)
	public LocalDate getDataFineValidita() {
		return dataFineValidita;
	}
	public void setDataFineValidita(LocalDate dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}
	@JsonDeserialize(using = LocalDateDefaultDeserializer.class)
	public LocalDate getDataInserimento() {
		return dataInserimento;
	}
	public void setDataInserimento(LocalDate dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	@JsonDeserialize(using = LocalDateDefaultDeserializer.class)
	public LocalDate getDataAggiornamento() {
		return dataAggiornamento;
	}
	public void setDataAggiornamento(LocalDate dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}
	public Integer getFkConfigUtente() {
		return fkConfigUtente;
	}
	public void setFkConfigUtente(Integer fkConfigUtente) {
		this.fkConfigUtente = fkConfigUtente;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
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
		result = prime * result + ((idDocumentoAllegato == null) ? 0 : idDocumentoAllegato.hashCode());
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
		FatDocumentoAllegato other = (FatDocumentoAllegato) obj;
		if (idDocumentoAllegato == null) {
			if (other.idDocumentoAllegato != null)
				return false;
		} else if (!idDocumentoAllegato.equals(other.idDocumentoAllegato)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FatDocumentoAllegato [idDocumentoAllegato=");
		builder.append(idDocumentoAllegato);
		builder.append(", idGeoPlPfa=");
		builder.append(idGeoPlPfa);
		builder.append(", fkIntervento=");
		builder.append(fkIntervento);
		builder.append(", idTipoAllegato=");
		builder.append(idTipoAllegato);
		builder.append(", descrTipoAllegato=");
		builder.append(descrTipoAllegato);
		builder.append(", nomeAllegato=");
		builder.append(nomeAllegato);
		builder.append(", dimensioneAllegatoKB=");
		builder.append(dimensioneAllegatoKB);
		builder.append(", dataIniziValidita=");
		builder.append(dataIniziValidita);
		builder.append(", dataFineValidita=");
		builder.append(dataFineValidita);
		builder.append(", dataInserimento=");
		builder.append(dataInserimento);
		builder.append(", dataAggiornamento=");
		builder.append(dataAggiornamento);
		builder.append(", fkConfigUtente=");
		builder.append(fkConfigUtente);
		builder.append(", note=");
		builder.append(note);
		builder.append(", uid=");
		builder.append(uidIndex);
		builder.append(", isDeleted=");
		builder.append(isDeleted);
		builder.append("]");
		return builder.toString();
	}
	
	public String getUidIndex() {
		return uidIndex;
	}
	public void setUidIndex(String uidIndex) {
		this.uidIndex = uidIndex;
	}
	
	public String getUdClassificazioneDoqui() {
		return udClassificazioneDoqui;
	}
	public void setUdClassificazioneDoqui(String udClassificazioneDoqui) {
		this.udClassificazioneDoqui = udClassificazioneDoqui;
	}
	public String getIdDocDoqui() {
		return idDocDoqui;
	}
	public void setIdDocDoqui(String idDocDoqui) {
		this.idDocDoqui = idDocDoqui;
	}
}
