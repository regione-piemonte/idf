/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.time.LocalDate;
import java.util.Objects;

public class InterventoCatfor {
	
	private Integer idIntervento;
	private Integer idCategoriaForestale;
	private LocalDate dataInserimento;
	private LocalDate dataAggiornamento;
	private Integer fkConfigUtente;
	
	public Integer getIdIntervento() {
		return idIntervento;
	}
	public void setIdIntervento(Integer idIntervento) {
		this.idIntervento = idIntervento;
	}
	public Integer getIdCategoriaForestale() {
		return idCategoriaForestale;
	}
	public void setIdCategoriaForestale(Integer idCategoriaForestale) {
		this.idCategoriaForestale = idCategoriaForestale;
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
	
	@Override
	public int hashCode() {
		return Objects.hash(idIntervento, idCategoriaForestale, dataInserimento, dataAggiornamento, fkConfigUtente);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		InterventoCatfor interventoCatfor = (InterventoCatfor) o;
		return Objects.equals(idIntervento, interventoCatfor.idIntervento)
			&& Objects.equals(idCategoriaForestale, interventoCatfor.idCategoriaForestale);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class InterventoCatfor {\n");
		sb.append("    idIntervento: ").append(idIntervento).append("\n");
		sb.append("    idCategoriaForestale: ").append(idCategoriaForestale).append("\n");
		sb.append("    dataInserimento: ").append(dataInserimento).append("\n");
		sb.append("    dataAggiornamento: ").append(dataAggiornamento).append("\n");
		sb.append("    fkConfigUtente: ").append(fkConfigUtente).append("\n");
		sb.append("}");
		return sb.toString();
	}
}
