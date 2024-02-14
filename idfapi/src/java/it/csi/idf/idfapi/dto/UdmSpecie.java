/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class UdmSpecie {

	private Integer idUdm;
	private String descrUdm;
	private Integer mtdOrdinamento;
	private Byte flgVisibile;
	
	public Integer getIdUdm() {
		return idUdm;
	}
	public void setIdUdm(Integer idUdm) {
		this.idUdm = idUdm;
	}
	public String getDescrUdm() {
		return descrUdm;
	}
	public void setDescrUdm(String descrUdm) {
		this.descrUdm = descrUdm;
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
