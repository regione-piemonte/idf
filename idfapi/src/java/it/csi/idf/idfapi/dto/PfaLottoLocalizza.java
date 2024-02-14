/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;
import java.util.List;

public class PfaLottoLocalizza {
	
	private Object plInterventoGeometria;
	private Object ptInterventoGeometria;
	private Object lnInterventoGeometria;
	private List<PlainParticelleCatastali> particelleCatastali;
	private BigDecimal totaleSuperficieCatastale;
	private BigDecimal totaleSuperficieTrasf;
	private List<RicadenzaInformazioni> ricadenzaAreeProtette;
	private List<RicadenzaInformazioni> ricadenzaNatura2000;
	private List<RicadenzaInformazioni> ricadenzaPopolamentiDaSeme;
	private List<RicadenzaPortaseme> ricadenzaPortaSeme;
	private List<RicadenzaIntervento> ricadenzaIntervento;
	private List<RicadenzaInformazioni> ricadenzaCategorieForestali;
	private boolean ricadenzaVincoloIdrogeologico;
	private String localita;
	private FasciaAltimetrica fasciaAltimetrica;
	
	public Object getPlInterventoGeometria() {
		return plInterventoGeometria;
	}
	public void setPlInterventoGeometria(Object plInterventoGeometria) {
		this.plInterventoGeometria = plInterventoGeometria;
	}
	public Object getPtInterventoGeometria() {
		return ptInterventoGeometria;
	}
	public void setPtInterventoGeometria(Object ptInterventoGeometria) {
		this.ptInterventoGeometria = ptInterventoGeometria;
	}
	public Object getLnInterventoGeometria() {
		return lnInterventoGeometria;
	}
	public void setLnInterventoGeometria(Object lnInterventoGeometria) {
		this.lnInterventoGeometria = lnInterventoGeometria;
	}
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

	public FasciaAltimetrica getFasciaAltimetrica() {
		return fasciaAltimetrica;
	}

	public void setFasciaAltimetrica(FasciaAltimetrica fasciaAltimetrica) {
		this.fasciaAltimetrica = fasciaAltimetrica;
	}

	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
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

	public List<RicadenzaPortaseme> getRicadenzaPortaSeme() {
		return ricadenzaPortaSeme;
	}

	public void setRicadenzaPortaSeme(List<RicadenzaPortaseme> ricadenzaPortaSeme) {
		this.ricadenzaPortaSeme = ricadenzaPortaSeme;
	}

	public List<RicadenzaIntervento> getRicadenzaIntervento() {
		return ricadenzaIntervento;
	}

	public void setRicadenzaIntervento(List<RicadenzaIntervento> ricadenzaIntervento) {
		this.ricadenzaIntervento = ricadenzaIntervento;
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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PfaLottoLocalizza [plInterventoGeometria=");
		builder.append(plInterventoGeometria);
		builder.append(", ptInterventoGeometria=");
		builder.append(ptInterventoGeometria);
		builder.append(", lnInterventoGeometria=");
		builder.append(lnInterventoGeometria);
		builder.append(", particelleCatastali=");
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
		builder.append("]");
		return builder.toString();
	}
}
