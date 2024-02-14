/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class TipoEvento {

	private Integer idTipoEvento;
	private String descrTipoEvento;
	private Integer mtdOrdinamento;
	private Byte flgVisibile;
	
	public Integer getIdTipoEvento() {
		return idTipoEvento;
	}
	public void setIdTipoEvento(Integer idTipoEvento) {
		this.idTipoEvento = idTipoEvento;
	}
	public String getDescrTipoEvento() {
		return descrTipoEvento;
	}
	public void setDescrTipoEvento(String descrTipoEvento) {
		this.descrTipoEvento = descrTipoEvento;
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
