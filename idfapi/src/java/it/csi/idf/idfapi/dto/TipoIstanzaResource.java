/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class TipoIstanzaResource {
	
	private int idTipoIstanza;
	private String descrDettTipoIstanza;
	
	public int getIdTipoIstanza() {
		return idTipoIstanza;
	}
	public void setIdTipoIstanza(int idTipoIstanza) {
		this.idTipoIstanza = idTipoIstanza;
	}
	public String getDescrDettTipoIstanza() {
		return descrDettTipoIstanza;
	}
	public void setDescrDettTipoIstanza(String descrDettTipoIstanza) {
		this.descrDettTipoIstanza = descrDettTipoIstanza;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TipoIstanzaResource [idTipoIstanza=");
		builder.append(idTipoIstanza);
		builder.append(", descrDettTipoIstanza=");
		builder.append(descrDettTipoIstanza);
		builder.append("]");
		return builder.toString();
	}
}
