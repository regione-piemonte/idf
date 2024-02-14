/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.util.Objects;

import org.codehaus.jackson.annotate.JsonProperty;

public class Professionista {
	private Integer partitaIva;
	private String nIscrizione;
	private String provincia;
	private Integer telefono;
	private String pec;

	@JsonProperty("partitaIva")
	public Integer getPartitaIva() {
		return partitaIva;
	}

	public void setPartitaIva(Integer partitaIva) {
		this.partitaIva = partitaIva;
	}

	@JsonProperty("nIscrizione")
	public String getnIscrizione() {
		return nIscrizione;
	}

	public void setnIscrizione(String nIscrizione) {
		this.nIscrizione = nIscrizione;
	}

	@JsonProperty("provincia")
	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	@JsonProperty("telefono")
	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	@JsonProperty("pec")
	public String getPec() {
		return pec;
	}

	public void setPec(String pec) {
		this.pec = pec;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Professionista confUtente = (Professionista) o;

		return Objects.equals(partitaIva, confUtente.partitaIva) && Objects.equals(nIscrizione, confUtente.nIscrizione)
				&& Objects.equals(provincia, confUtente.provincia) && Objects.equals(telefono, confUtente.telefono)
				&& Objects.equals(pec, confUtente.pec);
	}

	@Override
	public int hashCode() {
		return Objects.hash(partitaIva, nIscrizione, provincia, telefono, pec);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class PersonaFisica {\n");

		sb.append("    partitaIva: ").append(toIndentedString(partitaIva)).append("\n");
		sb.append("    nIscrizione: ").append(toIndentedString(nIscrizione)).append("\n");
		sb.append("    provincia: ").append(toIndentedString(provincia)).append("\n");
		sb.append("    telefono: ").append(toIndentedString(telefono)).append("\n");
		sb.append("    pec: ").append(toIndentedString(pec)).append("\n");

		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
