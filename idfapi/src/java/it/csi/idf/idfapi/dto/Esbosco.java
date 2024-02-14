/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class Esbosco {
	private String descrEsbosco;
	private String codEsbosco;
	private Integer mtdOrdinamento;
	private Byte flgVisibile;
	
	public String getDescrEsbosco() {
		return descrEsbosco;
	}
	public void setDescrEsbosco(String descrEsbosco) {
		this.descrEsbosco = descrEsbosco;
	}
	public String getCodEsbosco() {
		return codEsbosco;
	}
	public void setCodEsbosco(String codEsbosco) {
		this.codEsbosco = codEsbosco;
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
	
	
}
