/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Objects;
import java.util.StringJoiner;

public class TipoRichiedente {


	private Integer idTipoRichiedente;
	private String descrTipoRichiedente;
	private Integer ordinamento;
	private Byte flgVisibile;


	public Integer getIdTipoRichiedente() {
		return idTipoRichiedente;
	}

	public void setIdTipoRichiedente(Integer idTipoRichiedente) {
		this.idTipoRichiedente = idTipoRichiedente;
	}

	public String getDescrTipoRichiedente() {
		return descrTipoRichiedente;
	}

	public void setDescrTipoRichiedente(String descrTipoRichiedente) {
		this.descrTipoRichiedente = descrTipoRichiedente;
	}

	public Integer getOrdinamento() {
		return ordinamento;
	}

	public void setOrdinamento(Integer ordinamento) {
		this.ordinamento = ordinamento;
	}

	public Byte getFlgVisibile() {
		return flgVisibile;
	}

	public void setFlgVisibile(Byte flgVisibile) {
		this.flgVisibile = flgVisibile;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof TipoRichiedente)) return false;
		TipoRichiedente that = (TipoRichiedente) o;
		return Objects.equals(idTipoRichiedente, that.idTipoRichiedente) && Objects.equals(descrTipoRichiedente, that.descrTipoRichiedente) && Objects.equals(ordinamento, that.ordinamento) && Objects.equals(flgVisibile, that.flgVisibile);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idTipoRichiedente, descrTipoRichiedente, ordinamento, flgVisibile);
	}


	@Override
	public String toString() {
		return new StringJoiner("\n ", TipoRichiedente.class.getSimpleName() + "[", "]")
				.add("idTipoRichiedente=" + idTipoRichiedente)
				.add("descrTipoRichiedente='" + descrTipoRichiedente + "'")
				.add("ordinamento=" + ordinamento)
				.add("flgVisibile=" + flgVisibile)
				.toString();
	}
}
