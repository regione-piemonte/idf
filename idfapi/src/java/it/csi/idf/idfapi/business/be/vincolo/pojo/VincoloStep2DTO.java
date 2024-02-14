/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.pojo;

import java.math.BigDecimal;
import java.util.List;

import it.csi.idf.idfapi.dto.PlainParticelleCatastali;
import it.csi.idf.idfapi.dto.RicadenzaInformazioni;

public class VincoloStep2DTO {
	
	private Integer idIntervento;
	private List<PlainParticelleCatastali> particelleCatastali;
	private BigDecimal totaleSuperficieCatastale;
	private BigDecimal totaleSuperficieTrasf;
	
	private BigDecimal totaleSuperficieBoscata;
	private BigDecimal totaleSuperficieNonBoscata;
	private BigDecimal totaleSuperficieInVincolo;
	private BigDecimal totaleSuperficieBoscataInVincolo;
	private BigDecimal totaleSuperficieNonBoscataInVincolo;
	
	private List<RicadenzaInformazioni> ricadenzaAreeProtette;
	private List<RicadenzaInformazioni> ricadenzaNatura2000;
	private List<RicadenzaInformazioni> ricadenzaPopolamentiDaSeme;
	private List<RicadenzaInformazioni> ricadenzaCategorieForestali;
	private boolean ricadenzaVincoloIdrogeologico;
	private String annotazioni;

	public List<PlainParticelleCatastali> getParticelleCatastali() {
		return particelleCatastali;
	}
	public void setParticelleCatastali(List<PlainParticelleCatastali> particelleCatastali) {
		this.particelleCatastali = particelleCatastali;
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
	public List<RicadenzaInformazioni> getRicadenzaAreeProtette() {
		return ricadenzaAreeProtette;
	}
	public void setRicadenzaAreeProtette(List<RicadenzaInformazioni> ricadenzaAreeProtette) {
		this.ricadenzaAreeProtette = ricadenzaAreeProtette;
	}
	public List<RicadenzaInformazioni> getRicadenzaNatura2000() {
		return ricadenzaNatura2000;
	}
	public void setRicadenzaNatura2000(List<RicadenzaInformazioni> ricadenzaNatura2000) {
		this.ricadenzaNatura2000 = ricadenzaNatura2000;
	}
	public List<RicadenzaInformazioni> getRicadenzaPopolamentiDaSeme() {
		return ricadenzaPopolamentiDaSeme;
	}
	public void setRicadenzaPopolamentiDaSeme(List<RicadenzaInformazioni> ricadenzaPopolamentiDaSeme) {
		this.ricadenzaPopolamentiDaSeme = ricadenzaPopolamentiDaSeme;
	}
	public List<RicadenzaInformazioni> getRicadenzaCategorieForestali() {
		return ricadenzaCategorieForestali;
	}
	public void setRicadenzaCategorieForestali(List<RicadenzaInformazioni> ricadenzaCategorieForestali) {
		this.ricadenzaCategorieForestali = ricadenzaCategorieForestali;
	}
	public boolean isRicadenzaVincoloIdrogeologico() {
		return ricadenzaVincoloIdrogeologico;
	}
	public void setRicadenzaVincoloIdrogeologico(boolean ricadenzaVincoloIdrogeologico) {
		this.ricadenzaVincoloIdrogeologico = ricadenzaVincoloIdrogeologico;
	}
	public String getAnnotazioni() {
		return annotazioni;
	}
	public void setAnnotazioni(String annotazioni) {
		this.annotazioni = annotazioni;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlainSecondoSezione [particelleCatastali=");
		builder.append(particelleCatastali);
		builder.append(", totaleSuperficieCatastale=");
		builder.append(totaleSuperficieCatastale);
		builder.append(", totaleSuperficieTrasf=");
		builder.append(totaleSuperficieTrasf);
		builder.append(", ricadenzaAreeProtette=");
		builder.append(ricadenzaAreeProtette);
		builder.append(", ricadenzaNatura2000=");
		builder.append(ricadenzaNatura2000);
		builder.append(", ricadenzaPopolamentiDaSeme=");
		builder.append(ricadenzaPopolamentiDaSeme);
		builder.append(", ricadenzaCategorieForestali=");
		builder.append(ricadenzaCategorieForestali);
		builder.append(", ricadenzaVincoloIdrogeologico=");
		builder.append(ricadenzaVincoloIdrogeologico);
		builder.append(", annotazioni=");
		builder.append(annotazioni);
		builder.append("]");
		return builder.toString();
	}
	public Integer getIdIntervento() {
		return idIntervento;
	}
	public void setIdIntervento(Integer idIntervento) {
		this.idIntervento = idIntervento;
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

