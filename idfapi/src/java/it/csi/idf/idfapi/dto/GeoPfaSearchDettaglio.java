/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import it.csi.idf.idfapi.config.LocalDateSerializer;

public class GeoPfaSearchDettaglio {
	
	private Integer idGeoPlPfa;
	private String denominazione;
	private LocalDate dataInizioValidita;
	private LocalDate dataFineValidita;
	private LocalDate dataRevisione;
	private LocalDate dataApprovazione;
	private Object geometria;
	private String fonteFinanziamento;
	private Byte flgRevisione;
	private BigDecimal propNonForestaleHa;
	private BigDecimal supPianifNonForestaleHa;
	private BigDecimal proprietaSilvopastHa;
	private BigDecimal proprietaForestaleHa;
	private BigDecimal superfBocsGestAttivaHa;
	private BigDecimal supPianifForestaleHa;
	private BigDecimal superfGestNonAttivaMonHa;
	private BigDecimal superfGestNonAttivaTotHa;
	private BigDecimal superfGestNonAttivaEvlHa;
	private String[] gestori;
	private String denominazioneComuni;
	private Integer[] idComuni;
	private String denominazioneProvincie;
	private String[] istatProvincie;
	private String descrPropriete;
	private List<RicadenzaHolder> ricadenzeAree;
	private List<RicadenzaHolder> ricadenzeRn2000;
	private List<RicadenzaHolder> ricadenzePopseme;
	private String delibera;
	
	public Integer getIdGeoPlPfa() {
		return idGeoPlPfa;
	}
	public void setIdGeoPlPfa(Integer idGeoPlPfa) {
		this.idGeoPlPfa = idGeoPlPfa;
	}
	public String getDenominazione() {
		return denominazione;
	}
	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDate getDataInizioValidita() {
		return dataInizioValidita;
	}
	public void setDataInizioValidita(LocalDate dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDate getDataFineValidita() {
		return dataFineValidita;
	}
	public void setDataFineValidita(LocalDate dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDate getDataRevisione() {
		return dataRevisione;
	}
	public void setDataRevisione(LocalDate dataRevisione) {
		this.dataRevisione = dataRevisione;
	}
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDate getDataApprovazione() {
		return dataApprovazione;
	}
	public void setDataApprovazione(LocalDate dataApprovazione) {
		this.dataApprovazione = dataApprovazione;
	}
	public Object getGeometria() {
		return geometria;
	}
	public void setGeometria(Object geometria) {
		this.geometria = geometria;
	}
	public String getFonteFinanziamento() {
		return fonteFinanziamento;
	}
	public void setFonteFinanziamento(String fonteFinanziamento) {
		this.fonteFinanziamento = fonteFinanziamento;
	}
	public Byte getFlgRevisione() {
		return flgRevisione;
	}
	public void setFlgRevisione(Byte flgRevisione) {
		this.flgRevisione = flgRevisione;
	}
	public BigDecimal getPropNonForestaleHa() {
		return propNonForestaleHa;
	}
	public void setPropNonForestaleHa(BigDecimal propNonForestaleHa) {
		this.propNonForestaleHa = propNonForestaleHa;
	}
	public BigDecimal getSupPianifNonForestaleHa() {
		return supPianifNonForestaleHa;
	}
	public void setSupPianifNonForestaleHa(BigDecimal supPianifNonForestaleHa) {
		this.supPianifNonForestaleHa = supPianifNonForestaleHa;
	}
	public BigDecimal getProprietaSilvopastHa() {
		return proprietaSilvopastHa;
	}
	public void setProprietaSilvopastHa(BigDecimal proprietaSilvopastHa) {
		this.proprietaSilvopastHa = proprietaSilvopastHa;
	}
	public BigDecimal getProprietaForestaleHa() {
		return proprietaForestaleHa;
	}
	public void setProprietaForestaleHa(BigDecimal proprietaForestaleHa) {
		this.proprietaForestaleHa = proprietaForestaleHa;
	}
	public BigDecimal getSuperfBocsGestAttivaHa() {
		return superfBocsGestAttivaHa;
	}
	public void setSuperfBocsGestAttivaHa(BigDecimal superfBocsGestAttivaHa) {
		this.superfBocsGestAttivaHa = superfBocsGestAttivaHa;
	}
	public BigDecimal getSupPianifForestaleHa() {
		return supPianifForestaleHa;
	}
	public void setSupPianifForestaleHa(BigDecimal supPianifForestaleHa) {
		this.supPianifForestaleHa = supPianifForestaleHa;
	}
	public BigDecimal getSuperfGestNonAttivaMonHa() {
		return superfGestNonAttivaMonHa;
	}
	public void setSuperfGestNonAttivaMonHa(BigDecimal superfGestNonAttivaMonHa) {
		this.superfGestNonAttivaMonHa = superfGestNonAttivaMonHa;
	}
	public BigDecimal getSuperfGestNonAttivaTotHa() {
		return superfGestNonAttivaTotHa;
	}
	public void setSuperfGestNonAttivaTotHa(BigDecimal superfGestNonAttivaTotHa) {
		this.superfGestNonAttivaTotHa = superfGestNonAttivaTotHa;
	}
	public BigDecimal getSuperfGestNonAttivaEvlHa() {
		return superfGestNonAttivaEvlHa;
	}
	public void setSuperfGestNonAttivaEvlHa(BigDecimal superfGestNonAttivaEvlHa) {
		this.superfGestNonAttivaEvlHa = superfGestNonAttivaEvlHa;
	}
	public String[] getGestori() {
		return gestori;
	}
	public void setGestori(String[] gestori) {
		this.gestori = gestori;
	}
	public String getDenominazioneComuni() {
		return denominazioneComuni;
	}
	public void setDenominazioneComuni(String denominazioneComuni) {
		this.denominazioneComuni = denominazioneComuni;
	}
	public Integer[] getIdComuni() {
		return idComuni;
	}
	public void setIdComuni(Integer[] idComuni) {
		this.idComuni = idComuni;
	}
	public String getDenominazioneProvincie() {
		return denominazioneProvincie;
	}
	public void setDenominazioneProvincie(String denominazioneProvincie) {
		this.denominazioneProvincie = denominazioneProvincie;
	}
	public String[] getIstatProvincie() {
		return istatProvincie;
	}
	public void setIstatProvincie(String[] istatProvincie) {
		this.istatProvincie = istatProvincie;
	}
	public String getDescrPropriete() {
		return descrPropriete;
	}
	public void setDescrPropriete(String descrPropriete) {
		this.descrPropriete = descrPropriete;
	}
	public List<RicadenzaHolder> getRicadenzeAree() {
		return ricadenzeAree;
	}
	public void setRicadenzeAree(List<RicadenzaHolder> ricadenzeAree) {
		this.ricadenzeAree = ricadenzeAree;
	}
	public List<RicadenzaHolder> getRicadenzeRn2000() {
		return ricadenzeRn2000;
	}
	public void setRicadenzeRn2000(List<RicadenzaHolder> ricadenzeRn2000) {
		this.ricadenzeRn2000 = ricadenzeRn2000;
	}
	public List<RicadenzaHolder> getRicadenzePopseme() {
		return ricadenzePopseme;
	}
	public void setRicadenzePopseme(List<RicadenzaHolder> ricadenzePopseme) {
		this.ricadenzePopseme = ricadenzePopseme;
	}	
	public String getDelibera() {
		return delibera;
	}
	public void setDelibera(String delibera) {
		this.delibera = delibera;
	}
	@Override
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GeoPfaSearchDettaglio [idGeoPlPfa=");
		builder.append(idGeoPlPfa);
		builder.append(", denominazione=");
		builder.append(denominazione);
		builder.append(", dataInizioValidita=");
		builder.append(dataInizioValidita);
		builder.append(", dataFineValidita=");
		builder.append(dataFineValidita);
		builder.append(", dataRevisione=");
		builder.append(dataRevisione);
		builder.append(", dataApprovazione=");
		builder.append(dataApprovazione);
		builder.append(", geometria=");
		builder.append(geometria);
		builder.append(", fonteFinanziamento=");
		builder.append(fonteFinanziamento);
		builder.append(", flgRevisione=");
		builder.append(flgRevisione);
		builder.append(", propNonForestaleHa=");
		builder.append(propNonForestaleHa);
		builder.append(", supPianifNonForestaleHa=");
		builder.append(supPianifNonForestaleHa);
		builder.append(", proprietaSilvopastHa=");
		builder.append(proprietaSilvopastHa);
		builder.append(", proprietaForestaleHa=");
		builder.append(proprietaForestaleHa);
		builder.append(", superfBocsGestAttivaHa=");
		builder.append(superfBocsGestAttivaHa);
		builder.append(", supPianifForestaleHa=");
		builder.append(supPianifForestaleHa);
		builder.append(", superfGestNonAttivaMonHa=");
		builder.append(superfGestNonAttivaMonHa);
		builder.append(", superfGestNonAttivaTotHa=");
		builder.append(superfGestNonAttivaTotHa);
		builder.append(", superfGestNonAttivaEvlHa=");
		builder.append(superfGestNonAttivaEvlHa);
		builder.append(", gestori=");
		builder.append(Arrays.toString(gestori));
		builder.append(", denominazioneComuni=");
		builder.append(denominazioneComuni);
		builder.append(", idComuni=");
		builder.append(Arrays.toString(idComuni));
		builder.append(", denominazioneProvincie=");
		builder.append(denominazioneProvincie);
		builder.append(", istatProvincie=");
		builder.append(Arrays.toString(istatProvincie));
		builder.append(", descrPropriete=");
		builder.append(descrPropriete);
		builder.append(", ricadenzeAree=");
		builder.append(ricadenzeAree);
		builder.append(", ricadenzeRn2000=");
		builder.append(ricadenzeRn2000);
		builder.append(", ricadenzePopseme=");
		builder.append(ricadenzePopseme);
		builder.append(", delibera=");
		builder.append(delibera);
		builder.append("]");
		return builder.toString();
	}
}
