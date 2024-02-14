/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class StadioSviluppo {
	
	private String codStadioSviluppo;
	private String descrStadioSviluppo;
	private Integer mtdOrdinamento;
	private Byte flgVisibile;
	public String getCodStadioSviluppo() {
		return codStadioSviluppo;
	}
	public void setCodStadioSviluppo(String codStadioSviluppo) {
		this.codStadioSviluppo = codStadioSviluppo;
	}
	public String getDescrStadioSviluppo() {
		return descrStadioSviluppo;
	}
	public void setDescrStadioSviluppo(String descrStadioSviluppo) {
		this.descrStadioSviluppo = descrStadioSviluppo;
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
