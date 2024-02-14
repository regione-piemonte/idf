/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class Destinazione {
	
	private Integer idDestinazione;
	private String descrDestinazione;
	private String codDestinazione;
	private Integer mtdOrdinamento;
	private Integer fkConfigIpla;
	private Byte flgVisibile;
	
	public Integer getIdDestinazione() {
		return idDestinazione;
	}
	public void setIdDestinazione(Integer idDestinazione) {
		this.idDestinazione = idDestinazione;
	}
	public String getDescrDestinazione() {
		return descrDestinazione;
	}
	public void setDescrDestinazione(String descrDestinazione) {
		this.descrDestinazione = descrDestinazione;
	}
	public String getCodDestinazione() {
		return codDestinazione;
	}
	public void setCodDestinazione(String codDestinazione) {
		this.codDestinazione = codDestinazione;
	}
	public Integer getMtdOrdinamento() {
		return mtdOrdinamento;
	}
	public void setMtdOrdinamento(Integer mtdOrdinamento) {
		this.mtdOrdinamento = mtdOrdinamento;
	}
	public Integer getFkConfigIpla() {
		return fkConfigIpla;
	}
	public void setFkConfigIpla(Integer fkConfigIpla) {
		this.fkConfigIpla = fkConfigIpla;
	}
	public Byte getFlgVisibile() {
		return flgVisibile;
	}
	public void setFlgVisibile(Byte flgVisibile) {
		this.flgVisibile = flgVisibile;
	}
	
	
}
