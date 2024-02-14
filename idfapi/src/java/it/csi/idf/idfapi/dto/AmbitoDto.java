/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class AmbitoDto {
	
	int idAmbitoIstanza ;  
	String descrAmbitoIstanza;  
	int mtdOrdinamento  ;
	
	
	public AmbitoDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AmbitoDto(int idAmbitoIstanza, String descrAmbitoIstanza, int mtdOrdinamenot) {
		super();
		this.idAmbitoIstanza = idAmbitoIstanza;
		this.descrAmbitoIstanza = descrAmbitoIstanza;
		this.mtdOrdinamento = mtdOrdinamento;
	}
	public int getIdAmbitoIstanza() {
		return idAmbitoIstanza;
	}
	public void setIdAmbitoIstanza(int idAmbitoIstanza) {
		this.idAmbitoIstanza = idAmbitoIstanza;
	}
	public String getDescrAmbitoIstanza() {
		return descrAmbitoIstanza;
	}
	public void setDescrAmbitoIstanza(String descrAmbitoIstanza) {
		this.descrAmbitoIstanza = descrAmbitoIstanza;
	}
	public int getMtdOrdinamento() {
		return mtdOrdinamento;
	}
	public void setMtdOrdinamento(int mtdOrdinamento) {
		this.mtdOrdinamento = mtdOrdinamento;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AmbitoDto [idAmbitoIstanza=");
		builder.append(idAmbitoIstanza);
		builder.append(", ");
		if (descrAmbitoIstanza != null) {
			builder.append("descrAmbitoIstanza=");
			builder.append(descrAmbitoIstanza);
			builder.append(", ");
		}
		builder.append("mtdOrdinamento=");
		builder.append(mtdOrdinamento);
		builder.append("]");
		return builder.toString();
	}
	
	

}
