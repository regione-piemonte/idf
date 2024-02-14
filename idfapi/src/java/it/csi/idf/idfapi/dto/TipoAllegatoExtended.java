/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class TipoAllegatoExtended {
	
	private int idTipoAllegato;
	private String descrTipoAllegato;
	private boolean flgObbligatorio;
	
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
	public boolean isFlgObbligatorio() {
		return flgObbligatorio;
	}
	public void setFlgObbligatorio(boolean flgObbligatorio) {
		this.flgObbligatorio = flgObbligatorio;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TipoAllegatoExtended [idTipoAllegato=");
		builder.append(idTipoAllegato);
		builder.append(", descrTipoAllegato=");
		builder.append(descrTipoAllegato);
		builder.append(", flgObbligatorio=");
		builder.append(flgObbligatorio);
		builder.append("]");
		return builder.toString();
	}
	
	
}
