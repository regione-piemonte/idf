/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

public class Governo {

	private Integer idGoverno;
	private String descrGoverno;

	@JsonIgnore
	private Integer mtdOrdinamento;
	@JsonIgnore
	private Byte flgVisibile;
	
	public Integer getIdGoverno() {
		return idGoverno;
	}
	public void setIdGoverno(Integer idGoverno) {
		this.idGoverno = idGoverno;
	}
	public String getDescrGoverno() {
		return descrGoverno;
	}
	public void setDescrGoverno(String descrGoverno) {
		this.descrGoverno = descrGoverno;
	}
	public Integer getMtdOrdinamento() {
		return mtdOrdinamento;
	}
	public void setMtdOrdinamento(Integer mtdOrdinamento) {
		this.mtdOrdinamento = mtdOrdinamento;
	}
	public Byte getFlgVisibile() {
		return flgVisibile;
	}
	public void setFlgVisibile(Byte flgVisibile) {
		this.flgVisibile = flgVisibile;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descrGoverno == null) ? 0 : descrGoverno.hashCode());
		result = prime * result + ((flgVisibile == null) ? 0 : flgVisibile.hashCode());
		result = prime * result + ((idGoverno == null) ? 0 : idGoverno.hashCode());
		result = prime * result + ((mtdOrdinamento == null) ? 0 : mtdOrdinamento.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Governo other = (Governo) obj;
		if (descrGoverno == null) {
			if (other.descrGoverno != null)
				return false;
		} else if (!descrGoverno.equals(other.descrGoverno))
			return false;
		if (flgVisibile == null) {
			if (other.flgVisibile != null)
				return false;
		} else if (!flgVisibile.equals(other.flgVisibile))
			return false;
		if (idGoverno == null) {
			if (other.idGoverno != null)
				return false;
		} else if (!idGoverno.equals(other.idGoverno))
			return false;
		if (mtdOrdinamento == null) {
			if (other.mtdOrdinamento != null)
				return false;
		} else if (!mtdOrdinamento.equals(other.mtdOrdinamento))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Governo [idGoverno=");
		builder.append(idGoverno);
		builder.append(", descrGoverno=");
		builder.append(descrGoverno);
		builder.append(", mtdOrdinamento=");
		builder.append(mtdOrdinamento);
		builder.append(", flgVisibile=");
		builder.append(flgVisibile);
		builder.append("]");
		return builder.toString();
	}
	

}
