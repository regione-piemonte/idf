/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class DestLegname {

	private Integer idDestLegname;
	private String descrDestLegname;
	private Integer mtdOrdinamento;
	private Byte flgVisibile;
	
	public Integer getIdDestLegname() {
		return idDestLegname;
	}
	public void setIdDestLegname(Integer idDestLegname) {
		this.idDestLegname = idDestLegname;
	}
	public String getDescrDestLegname() {
		return descrDestLegname;
	}
	public void setDescrDestLegname(String descrDestLegname) {
		this.descrDestLegname = descrDestLegname;
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
