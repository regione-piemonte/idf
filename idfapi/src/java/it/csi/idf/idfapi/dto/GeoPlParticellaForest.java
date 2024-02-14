/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import it.csi.idf.idfapi.config.LocalDateDeserializer;
import it.csi.idf.idfapi.config.LocalDateSerializer;

public class GeoPlParticellaForest {
	
	private Integer idgeoPlParticellaForest;
	private Integer fkPadre;
	private Integer idgeoPlPfa;
	private Integer fkCompresa;
	private String denominazioneParticella;
	private BigDecimal ettari;
	private String codParticellaFor;
	private LocalDate dataInizioValidita;
	private LocalDate dataFineValidita;
	private Object geometria;
	private BigDecimal provvigioneHa;
	private BigDecimal provvigioneReale;
	private Integer percTassoRipresaPot;
	private Integer percTara;
	private BigDecimal ripresaTotHa;
	private BigDecimal ripresaAttuale;
	private BigDecimal ripresaResidua;
	
	public Integer getIdgeoPlParticellaForest() {
		return idgeoPlParticellaForest;
	}
	public void setIdgeoPlParticellaForest(Integer idgeoPlParticellaForest) {
		this.idgeoPlParticellaForest = idgeoPlParticellaForest;
	}
	public Integer getFkPadre() {
		return fkPadre;
	}
	public void setFkPadre(Integer fkPadre) {
		this.fkPadre = fkPadre;
	}
	public Integer getIdgeoPlPfa() {
		return idgeoPlPfa;
	}
	public void setIdgeoPlPfa(Integer idgeoPlPfa) {
		this.idgeoPlPfa = idgeoPlPfa;
	}
	public Integer getFkCompresa() {
		return fkCompresa;
	}
	public void setFkCompresa(Integer fkCompresa) {
		this.fkCompresa = fkCompresa;
	}
	public String getDenominazioneParticella() {
		return denominazioneParticella;
	}
	public void setDenominazioneParticella(String denominazioneParticella) {
		this.denominazioneParticella = denominazioneParticella;
	}
	public BigDecimal getEttari() {
		return ettari;
	}
	public void setEttari(BigDecimal ettari) {
		this.ettari = ettari;
	}
	public String getCodParticellaFor() {
		return codParticellaFor;
	}
	public void setCodParticellaFor(String codParticellaFor) {
		this.codParticellaFor = codParticellaFor;
	}
	
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	public LocalDate getDataInizioValidita() {
		return dataInizioValidita;
	}
	public void setDataInizioValidita(LocalDate dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}
	public LocalDate getDataFineValidita() {
		return dataFineValidita;
	}
	public void setDataFineValidita(LocalDate dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}
	public Object getGeometria() {
		return geometria;
	}
	public void setGeometria(Object geometria) {
		this.geometria = geometria;
	}
	public BigDecimal getProvvigioneHa() {
		return provvigioneHa;
	}
	public void setProvvigioneHa(BigDecimal provvigioneHa) {
		this.provvigioneHa = provvigioneHa;
	}
	public BigDecimal getProvvigioneReale() {
		return provvigioneReale;
	}
	public void setProvvigioneReale(BigDecimal provvigioneReale) {
		this.provvigioneReale = provvigioneReale;
	}
	public Integer getPercTassoRipresaPot() {
		return percTassoRipresaPot;
	}
	public void setPercTassoRipresaPot(Integer percTassoRipresaPot) {
		this.percTassoRipresaPot = percTassoRipresaPot;
	}
	public Integer getPercTara() {
		return percTara;
	}
	public void setPercTara(Integer percTara) {
		this.percTara = percTara;
	}
	public BigDecimal getRipresaTotHa() {
		return ripresaTotHa;
	}
	public void setRipresaTotHa(BigDecimal ripresaTotHa) {
		this.ripresaTotHa = ripresaTotHa;
	}
	public BigDecimal getRipresaAttuale() {
		return ripresaAttuale;
	}
	public void setRipresaAttuale(BigDecimal ripresaAttuale) {
		this.ripresaAttuale = ripresaAttuale;
	}
	public BigDecimal getRipresaResidua() {
		return ripresaResidua;
	}
	public void setRipresaResidua(BigDecimal ripresaResidua) {
		this.ripresaResidua = ripresaResidua;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GeoPlParticellaForest [idgeoPlParticellaForest=");
		builder.append(idgeoPlParticellaForest);
		builder.append(", fkPadre=");
		builder.append(fkPadre);
		builder.append(", idgeoPlPfa=");
		builder.append(idgeoPlPfa);
		builder.append(", fkCompresa=");
		builder.append(fkCompresa);
		builder.append(", denominazioneParticella=");
		builder.append(denominazioneParticella);
		builder.append(", ettari=");
		builder.append(ettari);
		builder.append(", codParticellaFor=");
		builder.append(codParticellaFor);
		builder.append(", dataInizioValidita=");
		builder.append(dataInizioValidita);
		builder.append(", dataFineValidita=");
		builder.append(dataFineValidita);
		builder.append(", geometria=");
		builder.append(geometria);
		builder.append(", provvigioneHa=");
		builder.append(provvigioneHa);
		builder.append(", provvigioneReale=");
		builder.append(provvigioneReale);
		builder.append(", percTassoRipresaPot=");
		builder.append(percTassoRipresaPot);
		builder.append(", percTara=");
		builder.append(percTara);
		builder.append(", ripresaTotHa=");
		builder.append(ripresaTotHa);
		builder.append(", ripresaAttuale=");
		builder.append(ripresaAttuale);
		builder.append(", ripresaResidua=");
		builder.append(ripresaResidua);
		builder.append("]");
		return builder.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GeoPlParticellaForest other = (GeoPlParticellaForest) obj;
		if (idgeoPlParticellaForest == null) {
			if (other.idgeoPlParticellaForest != null)
				return false;
		} else if (!idgeoPlParticellaForest.equals(other.idgeoPlParticellaForest))
			return false;
		if (idgeoPlPfa == null) {
			if (other.idgeoPlPfa != null)
				return false;
		} else if (!idgeoPlPfa.equals(other.idgeoPlPfa))
			return false;
		return true;
	}
	
	
}
