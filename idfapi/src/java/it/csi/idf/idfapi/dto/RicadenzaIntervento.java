/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.util.Objects;
import java.util.StringJoiner;

public class RicadenzaIntervento {

	private Integer id;
	private String tipoVincolo;
	private String nomeVincolo;
	private String provvedimento;
	private float percentuale;

	public String toStringRreport() {
		StringBuilder sb = new StringBuilder();
		if (null != tipoVincolo) {
			sb.append(tipoVincolo);
			sb.append(" - ");
		}
		if (null != nomeVincolo) {
			sb.append(nomeVincolo);
			sb.append(" ");
		}
		if (null != provvedimento) {
			sb.append(provvedimento);
			sb.append(" ");
		}
		if (0 != percentuale) {
			sb.append(percentuale);
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

	public String getTipoVincolo() {
		return tipoVincolo;
	}

	public void setTipoVincolo(String tipoVincolo) {
		this.tipoVincolo = tipoVincolo;
	}

	public String getNomeVincolo() {
		return nomeVincolo;
	}

	public void setNomeVincolo(String nomeVincolo) {
		this.nomeVincolo = nomeVincolo;
	}

	public String getProvvedimento() {
		return provvedimento;
	}

	public void setProvvedimento(String provvedimento) {
		this.provvedimento = provvedimento;
	}

	public float getPercentuale() {
		return percentuale;
	}

	public void setPercentuale(float percentuale) {
		this.percentuale = percentuale;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nomeVincolo, percentuale, provvedimento, tipoVincolo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RicadenzaIntervento other = (RicadenzaIntervento) obj;
		return Objects.equals(id, other.id) && Objects.equals(nomeVincolo, other.nomeVincolo)
				&& percentuale == other.percentuale && Objects.equals(provvedimento, other.provvedimento)
				&& Objects.equals(tipoVincolo, other.tipoVincolo);
	}

}
