/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.pojo;

import java.math.BigDecimal;
import java.util.List;

import it.csi.idf.idfapi.dto.PlainParticelleCatastali;
import it.csi.idf.idfapi.dto.RicadenzaInformazioni;

public class Superfici {
	
	private Integer idIntervento;

	private BigDecimal totaleSuperficieCatastale;
	private BigDecimal totaleSuperficieTrasf;
	
	private BigDecimal totaleSuperficieBoscata;
	private BigDecimal totaleSuperficieNonBoscata;
	private BigDecimal totaleSuperficieInVincolo;
	private BigDecimal totaleSuperficieBoscataInVincolo;
	private BigDecimal totaleSuperficieNonBoscataInVincolo;
	
	
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Superfici [idIntervento=");
		builder.append(idIntervento);
		builder.append(", totaleSuperficieCatastale=");
		builder.append(totaleSuperficieCatastale);
		builder.append(", totaleSuperficieTrasf=");
		builder.append(totaleSuperficieTrasf);
		builder.append(", totaleSuperficieBoscata=");
		builder.append(totaleSuperficieBoscata);
		builder.append(", totaleSuperficieNonBoscata=");
		builder.append(totaleSuperficieNonBoscata);
		builder.append(", totaleSuperficieInVincolo=");
		builder.append(totaleSuperficieInVincolo);
		builder.append(", totaleSuperficieBoscataInVincolo=");
		builder.append(totaleSuperficieBoscataInVincolo);
		builder.append(", totaleSuperficieNonBoscataInVincolo=");
		builder.append(totaleSuperficieNonBoscataInVincolo);
		builder.append("]");
		return builder.toString();
	}
	public Integer getIdIntervento() {
		return idIntervento;
	}
	public void setIdIntervento(Integer idIntervento) {
		this.idIntervento = idIntervento;
	}
	public BigDecimal getTotaleSuperficieCatastale() {
		return totaleSuperficieCatastale;
	}
	public void setTotaleSuperficieCatastale(BigDecimal totaleSuperficieCatastale) {
		this.totaleSuperficieCatastale = totaleSuperficieCatastale;
	}
	public BigDecimal getTotaleSuperficieTrasf() {
		return totaleSuperficieTrasf;
	}
	public void setTotaleSuperficieTrasf(BigDecimal totaleSuperficieTrasf) {
		this.totaleSuperficieTrasf = totaleSuperficieTrasf;
	}
	public BigDecimal getTotaleSuperficieBoscata() {
		return totaleSuperficieBoscata;
	}
	public void setTotaleSuperficieBoscata(BigDecimal totaleSuperficieBoscata) {
		this.totaleSuperficieBoscata = totaleSuperficieBoscata;
	}
	public BigDecimal getTotaleSuperficieNonBoscata() {
		return totaleSuperficieNonBoscata;
	}
	public void setTotaleSuperficieNonBoscata(BigDecimal totaleSuperficieNonBoscata) {
		this.totaleSuperficieNonBoscata = totaleSuperficieNonBoscata;
	}
	public BigDecimal getTotaleSuperficieInVincolo() {
		return totaleSuperficieInVincolo;
	}
	public void setTotaleSuperficieInVincolo(BigDecimal totaleSuperficieInVincolo) {
		this.totaleSuperficieInVincolo = totaleSuperficieInVincolo;
	}
	public BigDecimal getTotaleSuperficieBoscataInVincolo() {
		return totaleSuperficieBoscataInVincolo;
	}
	public void setTotaleSuperficieBoscataInVincolo(BigDecimal totaleSuperficieBoscataInVincolo) {
		this.totaleSuperficieBoscataInVincolo = totaleSuperficieBoscataInVincolo;
	}
	public BigDecimal getTotaleSuperficieNonBoscataInVincolo() {
		return totaleSuperficieNonBoscataInVincolo;
	}
	public void setTotaleSuperficieNonBoscataInVincolo(BigDecimal totaleSuperficieNonBoscataInVincolo) {
		this.totaleSuperficieNonBoscataInVincolo = totaleSuperficieNonBoscataInVincolo;
	}
	
	
}

