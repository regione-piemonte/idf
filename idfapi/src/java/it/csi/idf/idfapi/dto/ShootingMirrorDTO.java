/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;

public class ShootingMirrorDTO {

	private Integer idgeoPlParticellaForest;
	private String codParticellaForest;
	private Integer idgeoPlPfa;
	private BigDecimal ettari;
	private BigDecimal supTagliataHa;
	private BigDecimal provvigioneHa;
	private BigDecimal provvigioneReale;
	private BigDecimal percTassoRipresaPot;
	private BigDecimal percTara;
	private BigDecimal alreadyCut;
	private BigDecimal notAlreadyCut;
	private BigDecimal ripresaTotHa;
	private BigDecimal ripresaIntervento;
	private BigDecimal ripresaAttuale;
	private BigDecimal ripresaResidua;
	
	
	
	public Integer getIdgeoPlParticellaForest() {
		return idgeoPlParticellaForest;
	}
	public String getCodParticellaForest() {
		return codParticellaForest;
	}
	public Integer getIdgeoPlPfa() {
		return idgeoPlPfa;
	}
	public BigDecimal getEttari() {
		return ettari;
	}
	public BigDecimal getSupTagliataHa() {
		return supTagliataHa;
	}
	public BigDecimal getProvvigioneHa() {
		return provvigioneHa;
	}
	public BigDecimal getProvvigioneReale() {
		return provvigioneReale;
	}
	public BigDecimal getPercTassoRipresaPot() {
		return percTassoRipresaPot;
	}
	public BigDecimal getPercTara() {
		return percTara;
	}
	public BigDecimal getAlreadyCut() {
		return alreadyCut;
	}
	public BigDecimal getNotAlreadyCut() {
		return notAlreadyCut;
	}
	public BigDecimal getRipresaTotHa() {
		return ripresaTotHa;
	}
	public BigDecimal getRipresaIntervento() {
		return ripresaIntervento;
	}
	public BigDecimal getRipresaAttuale() {
		return ripresaAttuale;
	}
	public BigDecimal getRipresaResidua() {
		return ripresaResidua;
	}
	public void setIdgeoPlParticellaForest(Integer idgeoPlParticellaForest) {
		this.idgeoPlParticellaForest = idgeoPlParticellaForest;
	}
	public void setCodParticellaForest(String codParticellaForest) {
		this.codParticellaForest = codParticellaForest;
	}
	public void setIdgeoPlPfa(Integer idgeoPlPfa) {
		this.idgeoPlPfa = idgeoPlPfa;
	}
	public void setEttari(BigDecimal ettari) {
		this.ettari = ettari;
	}
	public void setSupTagliataHa(BigDecimal supTagliataHa) {
		this.supTagliataHa = supTagliataHa;
	}
	public void setProvvigioneHa(BigDecimal provvigioneHa) {
		this.provvigioneHa = provvigioneHa;
	}
	public void setProvvigioneReale(BigDecimal provvigioneReale) {
		this.provvigioneReale = provvigioneReale;
	}
	public void setPercTassoRipresaPot(BigDecimal percTassoRipresaPot) {
		this.percTassoRipresaPot = percTassoRipresaPot;
	}
	public void setPercTara(BigDecimal percTara) {
		this.percTara = percTara;
	}
	public void setAlreadyCut(BigDecimal alreadyCut) {
		this.alreadyCut = alreadyCut;
	}
	public void setNotAlreadyCut(BigDecimal notAlreadyCut) {
		this.notAlreadyCut = notAlreadyCut;
	}
	public void setRipresaTotHa(BigDecimal ripresaTotHa) {
		this.ripresaTotHa = ripresaTotHa;
	}
	public void setRipresaIntervento(BigDecimal ripresaIntervento) {
		this.ripresaIntervento = ripresaIntervento;
	}
	public void setRipresaAttuale(BigDecimal ripresaAttuale) {
		this.ripresaAttuale = ripresaAttuale;
	}
	public void setRipresaResidua(BigDecimal ripresaResidua) {
		this.ripresaResidua = ripresaResidua;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ShootingMirrorDTO [idgeoPlParticellaForest=");
		builder.append(idgeoPlParticellaForest);
		builder.append("ShootingMirrorDTO [codParticellaForest=");
		builder.append(codParticellaForest);
		builder.append(", idgeoPlPfa=");
		builder.append(idgeoPlPfa);
		builder.append(", ettari=");
		builder.append(ettari);
		builder.append(", supTagliataHa=");
		builder.append(supTagliataHa);
		builder.append(", provvigioneHa=");
		builder.append(provvigioneHa);
		builder.append(", provvigioneReale=");
		builder.append(provvigioneReale);
		builder.append(", percTassoRipresaPot=");
		builder.append(percTassoRipresaPot);
		builder.append(", percTara=");
		builder.append(percTara);
		builder.append(", alreadyCut=");
		builder.append(alreadyCut);
		builder.append(", notAlreadyCut=");
		builder.append(notAlreadyCut);
		builder.append(", ripresaTotHa=");
		builder.append(ripresaTotHa);
		builder.append(", ripresaIntervento=");
		builder.append(ripresaIntervento);
		builder.append(", ripresaAttuale=");
		builder.append(ripresaAttuale);
		builder.append(", ripresaResidua=");
		builder.append(ripresaResidua);
		builder.append("]");
		return builder.toString();
	}
	
	
}
