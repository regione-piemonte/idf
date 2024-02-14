/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class PartforRipresaInformazioni {

	private Integer codParticcella;
	private Integer ripresaIntervento;
	private Integer ripresaRealeFineInt;
	private Integer ripresaResidua;

	public Integer getCodParticcella() {
		return codParticcella;
	}

	public void setCodParticcella(Integer codParticcella) {
		this.codParticcella = codParticcella;
	}

	public Integer getRipresaIntervento() {
		return ripresaIntervento;
	}

	public void setRipresaIntervento(Integer ripresaIntervento) {
		this.ripresaIntervento = ripresaIntervento;
	}

	public Integer getRipresaRealeFineInt() {
		return ripresaRealeFineInt;
	}

	public void setRipresaRealeFineInt(Integer ripresaRealeFineInt) {
		this.ripresaRealeFineInt = ripresaRealeFineInt;
	}

	public Integer getRipresaResidua() {
		return ripresaResidua;
	}

	public void setRipresaResidua(Integer ripresaResidua) {
		this.ripresaResidua = ripresaResidua;
	}

}
