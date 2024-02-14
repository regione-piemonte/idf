/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.util.Objects;

import org.codehaus.jackson.annotate.JsonProperty;

public class Procedura {
	private Integer idProcedura;
	private String nomeProcedura;
	
	@JsonProperty("idProcedura")
	public Integer getIdProcedura() {
		return idProcedura;
	}
	public void setIdProcedura(Integer idProcedura) {
		this.idProcedura = idProcedura;
	}
	
	@JsonProperty("nomeProcedura")
	public String getNomeProcedura() {
		return nomeProcedura;
	}
	public void setNomeProcedura(String nomeProcedura) {
		this.nomeProcedura = nomeProcedura;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Procedura procedura = (Procedura) o;
		return Objects.equals(idProcedura, procedura.idProcedura)
				&& Objects.equals(nomeProcedura, procedura.nomeProcedura);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idProcedura, nomeProcedura);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Procedura {\n");
		sb.append("    idProcedura: ").append(idProcedura).append("\n");
		sb.append("    nomeProcedura: ").append(nomeProcedura).append("\n");
		sb.append("}");
		return sb.toString();
	}
}
