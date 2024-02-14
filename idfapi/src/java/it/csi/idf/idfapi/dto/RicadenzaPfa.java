/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.util.Objects;
import java.util.StringJoiner;

public class RicadenzaPfa {

	private Integer id;
	private String denominazione;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof RicadenzaPfa)) return false;
		RicadenzaPfa that = (RicadenzaPfa) o;
		return Objects.equals(id, that.id) && Objects.equals(denominazione, that.denominazione);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, denominazione);
	}

	@Override
	public String toString() {
		return new StringJoiner("\n ", RicadenzaPfa.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("denominazione='" + denominazione + "'")
				.toString();
	}
}
