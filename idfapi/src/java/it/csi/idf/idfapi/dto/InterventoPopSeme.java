/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.util.Objects;

public class InterventoPopSeme {
	
	private Integer idIntervento;
	private Integer idPopolamentoSeme;
	
	public Integer getIdIntervento() {
		return idIntervento;
	}
	public void setIdIntervento(Integer idIntervento) {
		this.idIntervento = idIntervento;
	}
	public Integer getIdPopolamentoSeme() {
		return idPopolamentoSeme;
	}
	public void setIdPopolamentoSeme(Integer idPopolamentoSeme) {
		this.idPopolamentoSeme = idPopolamentoSeme;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(idIntervento, idPopolamentoSeme);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		InterventoPopSeme interventoPopSeme = (InterventoPopSeme) o;
		return Objects.equals(idIntervento, interventoPopSeme.idIntervento)
			&& Objects.equals(idPopolamentoSeme, interventoPopSeme.idPopolamentoSeme);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class InterventoPopSeme {\n");
		sb.append("    idIntervento: ").append(idIntervento).append("\n");
		sb.append("    idPopolamentoSeme: ").append(idPopolamentoSeme).append("\n");
		sb.append("}");
		return sb.toString();
	}
}
