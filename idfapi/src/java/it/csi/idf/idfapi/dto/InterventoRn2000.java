/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.util.Objects;

public class InterventoRn2000 {
	
	private Integer idIntervento;
	private String codiceRn2000;
	
	public Integer getIdIntervento() {
		return idIntervento;
	}
	public void setIdIntervento(Integer idIntervento) {
		this.idIntervento = idIntervento;
	}
	public String getCodiceRn2000() {
		return codiceRn2000;
	}
	public void setCodiceRn2000(String codiceRn2000) {
		this.codiceRn2000 = codiceRn2000;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(idIntervento, codiceRn2000);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		InterventoRn2000 interventoRn2000 = (InterventoRn2000) o;
		return Objects.equals(idIntervento, interventoRn2000.idIntervento)
			&& Objects.equals(codiceRn2000, interventoRn2000.codiceRn2000);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class InterventoRn2000 {\n");
		sb.append("    idIntervento: ").append(idIntervento).append("\n");
		sb.append("    codiceRn2000: ").append(codiceRn2000).append("\n");
		sb.append("}");
		return sb.toString();
	}
}
