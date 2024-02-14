/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.util.StringJoiner;

public class IntervSelvicolturaleFat extends IntervSelvicolturale {


	private TipoIntervento tipoInterventoPrincipale;
	private Governo governoPrincipale;
	private TipoIntervento tipoInterventoSecondario;
	private Governo governoSecondario;


	public TipoIntervento getTipoInterventoPrincipale() {
		return tipoInterventoPrincipale;
	}

	public void setTipoInterventoPrincipale(TipoIntervento tipoInterventoPrincipale) {
		this.tipoInterventoPrincipale = tipoInterventoPrincipale;
	}

	public Governo getGovernoPrincipale() {
		return governoPrincipale;
	}

	public void setGovernoPrincipale(Governo governoPrincipale) {
		this.governoPrincipale = governoPrincipale;
	}

	public TipoIntervento getTipoInterventoSecondario() {
		return tipoInterventoSecondario;
	}

	public void setTipoInterventoSecondario(TipoIntervento tipoInterventoSecondario) {
		this.tipoInterventoSecondario = tipoInterventoSecondario;
	}

	public Governo getGovernoSecondario() {
		return governoSecondario;
	}

	public void setGovernoSecondario(Governo governoSecondario) {
		this.governoSecondario = governoSecondario;
	}

	@Override
	public String toString() {
		return new StringJoiner("\n ", IntervSelvicolturaleFat.class.getSimpleName() + "[", "]")
				.add("tipoInterventoPrincipale=" + tipoInterventoPrincipale)
				.add("governoPrincipale=" + governoPrincipale)
				.add("tipoInterventoSecondario=" + tipoInterventoSecondario)
				.add("governoSecondario=" + governoSecondario)
				.toString();
	}
}
