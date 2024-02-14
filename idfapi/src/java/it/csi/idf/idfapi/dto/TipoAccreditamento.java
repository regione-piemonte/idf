/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.util.Objects;

import org.codehaus.jackson.annotate.JsonProperty;

public class TipoAccreditamento {
	private Integer idTipoAccreditamento;
	private String descrTipoAccreditamento;

	@JsonProperty("idTipoAccreditamento")
	public Integer getIdTipoAccreditamento() {
		return idTipoAccreditamento;
	}

	public void setIdTipoAccreditamento(Integer idTipoAccreditamento) {
		this.idTipoAccreditamento = idTipoAccreditamento;
	}

	@JsonProperty("descrTipoAccreditamento")
	public String getDescrTipoAccreditamento() {
		return descrTipoAccreditamento;
	}

	public void setDescrTipoAccreditamento(String descrTipoAccreditamento) {
		this.descrTipoAccreditamento = descrTipoAccreditamento;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		TipoAccreditamento tipoAccreditamento = (TipoAccreditamento) o;
		return Objects.equals(idTipoAccreditamento, tipoAccreditamento.idTipoAccreditamento)
				&& Objects.equals(descrTipoAccreditamento, tipoAccreditamento.descrTipoAccreditamento);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idTipoAccreditamento, descrTipoAccreditamento);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class TipoAccreditamento {\n");
		sb.append("    idTipoAccreditamento: ").append(idTipoAccreditamento).append("\n");
		sb.append("    descrTipoAccreditamento: ").append(descrTipoAccreditamento).append("\n");
		sb.append("}");
		return sb.toString();
	}
}
