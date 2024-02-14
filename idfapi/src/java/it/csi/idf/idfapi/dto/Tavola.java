/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;

public class Tavola {
	private Integer idSpecie;
	private Integer numTavolaCoeffCub;
	private String flgConiflatif;
	private String descrizione;
	private BigDecimal t0;
	private BigDecimal t1;
	private BigDecimal t2;
	private BigDecimal t3;
	public Integer getIdSpecie() {
		return idSpecie;
	}
	public void setIdSpecie(Integer idSpecie) {
		this.idSpecie = idSpecie;
	}
	public Integer getNumTavolaCoeffCub() {
		return numTavolaCoeffCub;
	}
	public void setNumTavolaCoeffCub(Integer numTavolaCoeffCub) {
		this.numTavolaCoeffCub = numTavolaCoeffCub;
	}
	public String getFlgConiflatif() {
		return flgConiflatif;
	}
	public void setFlgConiflatif(String flgConiflatif) {
		this.flgConiflatif = flgConiflatif;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public BigDecimal getT0() {
		return t0;
	}
	public void setT0(BigDecimal t0) {
		this.t0 = t0;
	}
	public BigDecimal getT1() {
		return t1;
	}
	public void setT1(BigDecimal t1) {
		this.t1 = t1;
	}
	public BigDecimal getT2() {
		return t2;
	}
	public void setT2(BigDecimal t2) {
		this.t2 = t2;
	}
	public BigDecimal getT3() {
		return t3;
	}
	public void setT3(BigDecimal t3) {
		this.t3 = t3;
	}
	
	
	
	
}
