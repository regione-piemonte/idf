/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class LottoIntervento {
	private Integer idLotto;
	private String geometriaGml;
	private Double superficeLottoHa;
	private Double totSuperficeIntervento;
	public Integer getIdLotto() {
		return idLotto;
	}
	public String getGeometriaGml() {
		return geometriaGml;
	}
	public Double getSuperficeLottoHa() {
		return superficeLottoHa;
	}
	public Double getTotSuperficeIntervento() {
		return totSuperficeIntervento;
	}
	public void setIdLotto(Integer idLotto) {
		this.idLotto = idLotto;
	}
	public void setGeometriaGml(String geometriaGml) {
		this.geometriaGml = geometriaGml;
	}
	public void setSuperficeLottoHa(Double superficeLottoHa) {
		this.superficeLottoHa = superficeLottoHa;
	}
	public void setTotSuperficeIntervento(Double totSuperficeIntervento) {
		this.totSuperficeIntervento = totSuperficeIntervento;
	}
	
}
