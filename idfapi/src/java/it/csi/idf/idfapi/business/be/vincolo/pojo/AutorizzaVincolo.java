/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.pojo;

import java.util.Date;

public class AutorizzaVincolo {

	private Integer idIntervento;
	private Date dataVerifica;
	private String nrDeterminaAutoriz;
	private Date dataTermineAut;
	private Date dataFineIntervento;
	
	public Integer getIdIntervento() {
		return idIntervento;
	}
	public Date getDataVerifica() {
		return dataVerifica;
	}
	public String getNrDeterminaAutoriz() {
		return nrDeterminaAutoriz;
	}
	public Date getDataTermineAut() {
		return dataTermineAut;
	}
	public Date getDataFineIntervento() {
		return dataFineIntervento;
	}
	public void setIdIntervento(Integer idIntervento) {
		this.idIntervento = idIntervento;
	}
	public void setDataVerifica(Date dataVerifica) {
		this.dataVerifica = dataVerifica;
	}
	public void setNrDeterminaAutoriz(String nrDeterminaAutoriz) {
		this.nrDeterminaAutoriz = nrDeterminaAutoriz;
	}
	public void setDataTermineAut(Date dataTermineAut) {
		this.dataTermineAut = dataTermineAut;
	}
	public void setDataFineIntervento(Date dataFineIntervento) {
		this.dataFineIntervento = dataFineIntervento;
	}
	
	
	
}
