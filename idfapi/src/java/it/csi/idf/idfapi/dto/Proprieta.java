/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class Proprieta {
	
	private Integer idProprieta;
	private String descrProprieta;
	private String codProprieta;
	private Integer mtdOrdinamento;
	private Byte flgVisibile;
	private Integer fkConfigIpla;
	public Integer getIdProprieta() {
		return idProprieta;
	}
	public void setIdProprieta(Integer idProprieta) {
		this.idProprieta = idProprieta;
	}
	public String getDescrProprieta() {
		return descrProprieta;
	}
	public void setDescrProprieta(String descrProprieta) {
		this.descrProprieta = descrProprieta;
	}
	public String getCodProprieta() {
		return codProprieta;
	}
	public void setCodProprieta(String codProprieta) {
		this.codProprieta = codProprieta;
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
	public Integer getFkConfigIpla() {
		return fkConfigIpla;
	}
	public void setFkConfigIpla(Integer fkConfigIpla) {
		this.fkConfigIpla = fkConfigIpla;
	}
	
}
