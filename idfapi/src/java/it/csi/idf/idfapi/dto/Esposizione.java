/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class Esposizione {
	
	private Integer idEsposizione;
	private String descrEsposizione;
	private String codEsposizione;
	private Integer mtdOrdinamento;
	private Byte flgVisibile;
	
	public Integer getIdEsposizione() {
		return idEsposizione;
	}
	public void setIdEsposizione(Integer idEsposizione) {
		this.idEsposizione = idEsposizione;
	}
	public String getDescrEsposizione() {
		return descrEsposizione;
	}
	public void setDescrEsposizione(String descrEsposizione) {
		this.descrEsposizione = descrEsposizione;
	}
	public String getCodEsposizione() {
		return codEsposizione;
	}
	public void setCodEsposizione(String codEsposizione) {
		this.codEsposizione = codEsposizione;
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
