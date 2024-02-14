/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.util.Objects;
import java.util.StringJoiner;

public class RicadenzaPortaseme {

	private Integer id;
	private String localita;
	private String specie;

	public String toStringRreport(){
		StringBuilder sb = new StringBuilder();
		if (null != localita) {
			sb.append(localita);
			sb.append(" - ");
		}
		if (null != specie) {
			sb.append(specie);
			sb.append(" ");
		}
		return sb.toString();
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public String getSpecie() {
		return specie;
	}

	public void setSpecie(String specie) {
		this.specie = specie;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof RicadenzaPortaseme)) return false;
		RicadenzaPortaseme that = (RicadenzaPortaseme) o;
		return Objects.equals(id, that.id) && Objects.equals(localita, that.localita) && Objects.equals(specie, that.specie);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, localita, specie);
	}

	@Override
	public String toString() {
		return new StringJoiner("\n ", RicadenzaPortaseme.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("localita='" + localita + "'")
				.add("specie='" + specie + "'")
				.toString();
	}
}
