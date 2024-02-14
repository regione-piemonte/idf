/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.util.List;

public class IstanzaRiepilogo {

	private List<ComuneResource> comuneResource;
	private IstanzaDetails istanzaDetails;
	private DescrizioneIntervento descrizioneIntervento;
	private List<DichPropCatasto> propCatastos;
	private UtilizzatoreDetails utilizzatoreDetails;
	private RicadenzaInfo ricadenzaInfo;

	public List<ComuneResource> getComuneResource() {
		return comuneResource;
	}

	public void setComuneResource(List<ComuneResource> comuneResource) {
		this.comuneResource = comuneResource;
	}

	public IstanzaDetails getIstanzaDetails() {
		return istanzaDetails;
	}

	public void setIstanzaDetails(IstanzaDetails istanzaDetails) {
		this.istanzaDetails = istanzaDetails;
	}

	public DescrizioneIntervento getDescrizioneIntervento() {
		return descrizioneIntervento;
	}

	public void setDescrizioneIntervento(DescrizioneIntervento descrizioneIntervento) {
		this.descrizioneIntervento = descrizioneIntervento;
	}

	public List<DichPropCatasto> getPropCatastos() {
		return propCatastos;
	}

	public void setPropCatastos(List<DichPropCatasto> propCatastos) {
		this.propCatastos = propCatastos;
	}

	public UtilizzatoreDetails getUtilizzatoreDetails() {
		return utilizzatoreDetails;
	}

	public void setUtilizzatoreDetails(UtilizzatoreDetails utilizzatoreDetails) {
		this.utilizzatoreDetails = utilizzatoreDetails;
	}

	public RicadenzaInfo getRicadenzaInfo() {
		return ricadenzaInfo;
	}

	public void setRicadenzaInfo(RicadenzaInfo ricadenzaInfo) {
		this.ricadenzaInfo = ricadenzaInfo;
	}

}
