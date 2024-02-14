/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.rest;

import java.io.Serializable;

import it.csi.idf.idfapi.util.CodiceEnum;
import it.csi.idf.idfapi.util.TipologiaResponseEnum;

public class BaseRestResponse<T> implements Serializable {
	
	private static final long serialVersionUID = -7690786130689073927L;
	private CodiceEnum codice;
	private String testo;
	private TipologiaResponseEnum tipologia;
	private T payload;
	
	public CodiceEnum getCodice() {
		return codice;
	}
	public void setCodice(CodiceEnum codice) {
		this.codice = codice;
	}
	public String getTesto() {
		return testo;
	}
	public void setTesto(String testo) {
		this.testo = testo;
	}
	public TipologiaResponseEnum getTipologia() {
		return tipologia;
	}
	public void setTipologia(TipologiaResponseEnum tipologia) {
		this.tipologia = tipologia;
	}
	public T getPayload() {
		return payload;
	}
	public void setPayload(T payload) {
		this.payload = payload;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BaseRestResponse [codice=");
		builder.append(codice);
		builder.append(", testo=");
		builder.append(testo);
		builder.append(", tipologia=");
		builder.append(tipologia);
		builder.append(", payload=");
		builder.append(payload);
		builder.append("]");
		return builder.toString();
	}
}
