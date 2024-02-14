/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class InterventoDatiTehnici {

	private TipoInterventoDatiTecnici tipoIntervento;
	private IntervSelvicolturale intervSelvicolturale;
	private SpeciePfaIntervento[] speciePfaIntervento;
	private Integer idTipoIstanza;

	public TipoInterventoDatiTecnici getTipoIntervento() {
		return tipoIntervento;
	}

	public void setTipoIntervento(TipoInterventoDatiTecnici tipoIntervento) {
		this.tipoIntervento = tipoIntervento;
	}

	public IntervSelvicolturale getIntervSelvicolturale() {
		return intervSelvicolturale;
	}

	public void setIntervSelvicolturale(IntervSelvicolturale intervSelvicolturale) {
		this.intervSelvicolturale = intervSelvicolturale;
	}

	public SpeciePfaIntervento[] getSpeciePfaIntervento() {
		return speciePfaIntervento;
	}

	public void setSpeciePfaIntervento(SpeciePfaIntervento[] speciePfaIntervento) {
		this.speciePfaIntervento = speciePfaIntervento;
	}

	public Integer getIdTipoIstanza() {
		return idTipoIstanza;
	}

	public void setIdTipoIstanza(Integer idTipoIstanza) {
		this.idTipoIstanza = idTipoIstanza;
	}

	
	

}
