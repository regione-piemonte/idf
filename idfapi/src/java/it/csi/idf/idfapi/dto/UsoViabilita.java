/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class UsoViabilita {

	private Integer idUsoViabilita;
	private String descrUsoViabilita;
	private Integer mtdOrdinamento;
	private Byte flgVisible;
	
	public Integer getIdUsoViabilita() {
		return idUsoViabilita;
	}
	public void setIdUsoViabilita(Integer idUsoViabilita) {
		this.idUsoViabilita = idUsoViabilita;
	}
	public String getDescrUsoViabilita() {
		return descrUsoViabilita;
	}
	public void setDescrUsoViabilita(String descrUsoViabilita) {
		this.descrUsoViabilita = descrUsoViabilita;
	}
	public Integer getMtdOrdinamento() {
		return mtdOrdinamento;
	}
	public void setMtdOrdinamento(Integer mtdOrdinamento) {
		this.mtdOrdinamento = mtdOrdinamento;
	}
	public Byte getFlgVisible() {
		return flgVisible;
	}
	public void setFlgVisible(Byte flgVisible) {
		this.flgVisible = flgVisible;
	}
	
	
	
}
