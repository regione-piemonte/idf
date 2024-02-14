/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class StatoIntervento {
	
	private Integer idStatoIntervento;
	private String codStatointervento;
	private String descrStatoIntervento;
	
	public Integer getIdStatoIntervento() {
		return idStatoIntervento;
	}
	public String getCodStatointervento() {
		return codStatointervento;
	}
	public String getDescrStatoIntervento() {
		return descrStatoIntervento;
	}
	public void setIdStatoIntervento(Integer idStatoIntervento) {
		this.idStatoIntervento = idStatoIntervento;
	}
	public void setCodStatointervento(String codStatointervento) {
		this.codStatointervento = codStatointervento;
	}
	public void setDescrStatoIntervento(String descrStatoIntervento) {
		this.descrStatoIntervento = descrStatoIntervento;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StatoIntervento [idStatoIntervento=");
		builder.append(idStatoIntervento);
		builder.append(", codStatointervento=");
		builder.append(codStatointervento);
		builder.append(", descrStatoIntervento=");
		builder.append(descrStatoIntervento);
		builder.append("]");
		return builder.toString();
	}
}
