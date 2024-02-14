/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class UtilizzatoreDetails {

	private SoggettoResource richiedente;
	private SoggettoResource utilizzatore;
	private SoggettoResource tecnicoForestale;

	public SoggettoResource getRichiedente() {
		return richiedente;
	}

	public void setRichiedente(SoggettoResource richiedente) {
		this.richiedente = richiedente;
	}

	public SoggettoResource getUtilizzatore() {
		return utilizzatore;
	}

	public void setUtilizzatore(SoggettoResource utilizzatore) {
		this.utilizzatore = utilizzatore;
	}

	public SoggettoResource getTecnicoForestale() {
		return tecnicoForestale;
	}

	public void setTecnicoForestale(SoggettoResource tecnicoForestale) {
		this.tecnicoForestale = tecnicoForestale;
	}

}
