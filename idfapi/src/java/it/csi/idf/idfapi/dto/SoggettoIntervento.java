/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

public class SoggettoIntervento {
	
	private Integer idIntervento;
	private Integer idSoggetto;
	private Integer idTipoSoggetto;
	private LocalDate dataInizioValidita;
	private LocalDate dataFineValidita;
	private LocalDate dataInserimento;
	private LocalDate dataAggiornamento;
	private Integer fkConfigUtente;

	private Integer fkTipoRichiedente;
	private String nrAlboForestale;
	public Integer getIdIntervento() {
		return idIntervento;
	}
	public void setIdIntervento(Integer idIntervento) {
		this.idIntervento = idIntervento;
	}
	public Integer getIdSoggetto() {
		return idSoggetto;
	}
	public void setIdSoggetto(Integer idSoggetto) {
		this.idSoggetto = idSoggetto;
	}
	public Integer getIdTipoSoggetto() {
		return idTipoSoggetto;
	}
	public void setIdTipoSoggetto(Integer idTipoSoggetto) {
		this.idTipoSoggetto = idTipoSoggetto;
	}
	public LocalDate getDataInizioValidita() {
		return dataInizioValidita;
	}
	public void setDataInizioValidita(LocalDate dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}
	public LocalDate getDataFineValidita() {
		return dataFineValidita;
	}
	public void setDataFineValidita(LocalDate dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}
	public LocalDate getDataInserimento() {
		return dataInserimento;
	}
	public void setDataInserimento(LocalDate dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
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


	public Integer getFkTipoRichiedente() {
		return fkTipoRichiedente;
	}

	public void setFkTipoRichiedente(Integer fkTipoRichiedente) {
		this.fkTipoRichiedente = fkTipoRichiedente;
	}

	public String getNrAlboForestale() {
		return nrAlboForestale;
	}

	public void setNrAlboForestale(String nrAlboForestale) {
		this.nrAlboForestale = nrAlboForestale;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof SoggettoIntervento)) return false;
		SoggettoIntervento that = (SoggettoIntervento) o;
		return Objects.equals(idIntervento, that.idIntervento) && Objects.equals(idSoggetto, that.idSoggetto) && Objects.equals(idTipoSoggetto, that.idTipoSoggetto) && Objects.equals(dataInizioValidita, that.dataInizioValidita) && Objects.equals(dataFineValidita, that.dataFineValidita) && Objects.equals(dataInserimento, that.dataInserimento) && Objects.equals(dataAggiornamento, that.dataAggiornamento) && Objects.equals(fkConfigUtente, that.fkConfigUtente) && Objects.equals(fkTipoRichiedente, that.fkTipoRichiedente) && Objects.equals(nrAlboForestale, that.nrAlboForestale);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idIntervento, idSoggetto, idTipoSoggetto, dataInizioValidita, dataFineValidita, dataInserimento, dataAggiornamento, fkConfigUtente, fkTipoRichiedente, nrAlboForestale);
	}

	@Override
	public String toString() {
		return new StringJoiner("\n ", SoggettoIntervento.class.getSimpleName() + "[", "]")
				.add("idIntervento=" + idIntervento)
				.add("idSoggetto=" + idSoggetto)
				.add("idTipoSoggetto=" + idTipoSoggetto)
				.add("dataInizioValidita=" + dataInizioValidita)
				.add("dataFineValidita=" + dataFineValidita)
				.add("dataInserimento=" + dataInserimento)
				.add("dataAggiornamento=" + dataAggiornamento)
				.add("fkConfigUtente=" + fkConfigUtente)
				.add("fkTipoRichiedente=" + fkTipoRichiedente)
				.add("nrAlboForestale='" + nrAlboForestale + "'")
				.toString();
	}
}
