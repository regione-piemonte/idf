/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.time.LocalDate;
import java.util.Objects;

import org.codehaus.jackson.annotate.JsonProperty;

public class IstanzaCompensazione {
	private Integer numIstanzaCompensazione;
	private Integer fkIntervento;
	private LocalDate dataInserimento;
	private LocalDate dataAggiornamento;
	private Integer fkConfigUtente;
	private LocalDate dataPresentazione;
	private LocalDate dataAutorizzazione;
	
	@JsonProperty("numIstanzaCompensazione")
	public Integer getNumIstanzaCompensazione() {
		return numIstanzaCompensazione;
	}
	public void setNumIstanzaCompensazione(Integer numIstanzaCompensazione) {
		this.numIstanzaCompensazione = numIstanzaCompensazione;
	}
	
	@JsonProperty("fkIntervento")
	public Integer getFkIntervento() {
		return fkIntervento;
	}
	public void setFkIntervento(Integer fkIntervento) {
		this.fkIntervento = fkIntervento;
	}
	
	@JsonProperty("dataInserimento")
	public LocalDate getDataInserimento() {
		return dataInserimento;
	}
	public void setDataInserimento(LocalDate dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	
	@JsonProperty("dataAggiornamento")
	public LocalDate getDataAggiornamento() {
		return dataAggiornamento;
	}
	public void setDataAggiornamento(LocalDate dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}
	
	@JsonProperty("fkConfigUtente")
	public Integer getFkConfigUtente() {
		return fkConfigUtente;
	}
	public void setFkConfigUtente(Integer fkConfigUtente) {
		this.fkConfigUtente = fkConfigUtente;
	}
	
	public LocalDate getDataPresentazione() {
		return dataPresentazione;
	}
	public void setDataPresentazione(LocalDate dataPresentazione) {
		this.dataPresentazione = dataPresentazione;
	}
	public LocalDate getDataAutorizzazione() {
		return dataAutorizzazione;
	}
	public void setDataAutorizzazione(LocalDate dataAutorizzazione) {
		this.dataAutorizzazione = dataAutorizzazione;
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		IstanzaCompensazione istanzaCompensazione = (IstanzaCompensazione) o;
		return Objects.equals(numIstanzaCompensazione, istanzaCompensazione.numIstanzaCompensazione)
			&& Objects.equals(fkIntervento, istanzaCompensazione.fkIntervento)
			&& Objects.equals(dataInserimento, istanzaCompensazione.dataInserimento)
			&& Objects.equals(dataAggiornamento, istanzaCompensazione.dataAggiornamento)
			&& Objects.equals(fkConfigUtente, istanzaCompensazione.fkConfigUtente)
			&& Objects.equals(dataPresentazione, istanzaCompensazione.dataPresentazione)
			&& Objects.equals(dataAutorizzazione, istanzaCompensazione.dataAutorizzazione);
	}

	@Override
	public int hashCode() {
		return Objects.hash(numIstanzaCompensazione, fkIntervento, dataInserimento, dataAggiornamento, fkConfigUtente,
				dataPresentazione, dataAutorizzazione);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class IstanzaCompensazione {\n");
		sb.append("    numIstanzaCompensazione: ").append(numIstanzaCompensazione).append("\n");
		sb.append("    fkIntervento: ").append(fkIntervento).append("\n");
		sb.append("    dataInserimento: ").append(dataInserimento).append("\n");
		sb.append("    dataAggiornamento: ").append(dataAggiornamento).append("\n");
		sb.append("    fkConfigUtente: ").append(fkConfigUtente).append("\n");
		sb.append("    dataPresentazione: ").append(dataPresentazione).append("\n");
		sb.append("    dataAutorizzazione: ").append(dataAutorizzazione).append("\n");
		sb.append("}");
		return sb.toString();
	}
}
