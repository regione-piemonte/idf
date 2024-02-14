/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.util.Objects;

public class InterventoAapp {
	
	private Integer idIntervento;
	private String codiceAapp;
	
	public Integer getIdIntervento() {
		return idIntervento;
	}
	public void setIdIntervento(Integer idIntervento) {
		this.idIntervento = idIntervento;
	}
	public String getCodiceAapp() {
		return codiceAapp;
	}
	public void setCodiceAapp(String codiceAapp) {
		this.codiceAapp = codiceAapp;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(idIntervento, codiceAapp);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		InterventoAapp interventoAapp = (InterventoAapp) o;
		return Objects.equals(idIntervento, interventoAapp.idIntervento)
			&& Objects.equals(codiceAapp, interventoAapp.codiceAapp);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class InterventoAapp {\n");
		sb.append("    idIntervento: ").append(idIntervento).append("\n");
		sb.append("    codiceAapp: ").append(codiceAapp).append("\n");
		sb.append("}");
		return sb.toString();
	}
}
