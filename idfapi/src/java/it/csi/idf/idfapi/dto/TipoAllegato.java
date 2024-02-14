/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class TipoAllegato {
	
	private int idTipoAllegato;
	private String descrTipoAllegato;
	
	public int getIdTipoAllegato() {
		return idTipoAllegato;
	}
	public void setIdTipoAllegato(int idTipoAllegato) {
		this.idTipoAllegato = idTipoAllegato;
	}
	public String getDescrTipoAllegato() {
		return descrTipoAllegato;
	}
	public void setDescrTipoAllegato(String descrTipoAllegato) {
		this.descrTipoAllegato = descrTipoAllegato;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TipoAllegato [idTipoAllegato=");
		builder.append(idTipoAllegato);
		builder.append(", descrTipoAllegato=");
		builder.append(descrTipoAllegato);
		builder.append("]");
		return builder.toString();
	}
}
