/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class MacroCatfor {
	
	private Integer idParamMacroCatfor;
	private String descrCatfor;
	private Integer mtdOrdinamento;
	private Byte flgVisible;
	
	public Integer getIdParamMacroCatfor() {
		return idParamMacroCatfor;
	}
	public void setIdParamMacroCatfor(Integer idParamMacroCatfor) {
		this.idParamMacroCatfor = idParamMacroCatfor;
	}
	public String getDescrCatfor() {
		return descrCatfor;
	}
	public void setDescrCatfor(String descrCatfor) {
		this.descrCatfor = descrCatfor;
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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MacroCatfor [idParamMacroCatfor=");
		builder.append(idParamMacroCatfor);
		builder.append(", descrCatfor=");
		builder.append(descrCatfor);
		builder.append(", mtdOrdinamento=");
		builder.append(mtdOrdinamento);
		builder.append(", flgVisible=");
		builder.append(flgVisible);
		builder.append("]");
		return builder.toString();
	}
}
