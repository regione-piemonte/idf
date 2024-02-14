/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

public class TagliPfaStep4 {


	private IntervPfaFat intervPfaFat;

	private Intervento intervento;

	List<TagliHeading> headings;

	List<SpeciePfaIntervento> specieInteressate;





	public Intervento getIntervento() {
		return intervento;
	}

	public void setIntervento(Intervento intervento) {
		this.intervento = intervento;
	}

	public List<TagliHeading> getHeadings() {
		return headings;
	}

	public void setHeadings(List<TagliHeading> headings) {
		this.headings = headings;
	}

	public List<SpeciePfaIntervento> getSpecieInteressate() {
		return specieInteressate;
	}

	public void setSpecieInteressate(List<SpeciePfaIntervento> specieInteressate) {
		this.specieInteressate = specieInteressate;
	}

	public IntervPfaFat getIntervPfaFat() {
		return intervPfaFat;
	}

	public void setIntervPfaFat(IntervPfaFat intervPfaFat) {
		this.intervPfaFat = intervPfaFat;
	}
}

