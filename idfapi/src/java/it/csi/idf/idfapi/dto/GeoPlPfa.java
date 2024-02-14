/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;


public class GeoPlPfa {

	private Integer idGeoPlPfa;
	private String denominazione;
	private String fonteFinanziamento;
	private Object geometry;
	// GEOMETRIA:geometry(Geometry,32632);
	private LocalDate dataInizioValidita;
	private LocalDate dataFineValidita;
	private LocalDate dataApprovazione;
	private LocalDate dataRevisione;
	private BigDecimal supPianificataTotale;
	private BigDecimal supForestaleGestAttiva;
	private String note;
	private String utenteAggiornamento;
	private String nDelibera;
	private Integer durataPfaAnni;
	private Integer flgRevisione;
	private BigDecimal proprietaSilvopastHa;
	private BigDecimal proprietaForestaleHa;
	private BigDecimal superfTotBoscataHa;
	private BigDecimal superfBoscGestAttivaHa;
	private BigDecimal superfGestNonAttivaTotHa;
	private BigDecimal superfGestNonAttivaMonHa;
	private BigDecimal superfGestNonAttivaEvlHa;
	private BigDecimal superfAltriUsiHa;
	private String nomeBrevePfa;
	private Integer fk_versione;
	private LocalDate dataInserimento;
	private LocalDate dataAggiornamento;
	private BigDecimal propNonForestaleHa;
	private BigDecimal supPianifNonForestaleHa;
	private BigDecimal supPianifForestaleHa;
	
	
	
	
	public Object getGeometry() {
		return geometry;
	}
	public void setGeometry(Object geometry) {
		this.geometry = geometry;
	}
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
	public String getFonteFinanziamento() {
		return fonteFinanziamento;
	}
	public void setFonteFinanziamento(String fonteFinanziamento) {
		this.fonteFinanziamento = fonteFinanziamento;
	}
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
	public LocalDate getDataApprovazione() {
		return dataApprovazione;
	}
	public void setDataApprovazione(LocalDate dataApprovazione) {
		this.dataApprovazione = dataApprovazione;
	}
	public LocalDate getDataRevisione() {
		return dataRevisione;
	}
	public void setDataRevisione(LocalDate dataRevisione) {
		this.dataRevisione = dataRevisione;
	}
	
	public BigDecimal getSupPianificataTotale() {
		return supPianificataTotale;
	}
	public void setSupPianificataTotale(BigDecimal supPianificataTotale) {
		this.supPianificataTotale = supPianificataTotale;
	}
	public BigDecimal getSupForestaleGestAttiva() {
		return supForestaleGestAttiva;
	}
	public void setSupForestaleGestAttiva(BigDecimal supForestaleGestAttiva) {
		this.supForestaleGestAttiva = supForestaleGestAttiva;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getUtenteAggiornamento() {
		return utenteAggiornamento;
	}
	public void setUtenteAggiornamento(String utenteAggiornamento) {
		this.utenteAggiornamento = utenteAggiornamento;
	}
	public String getnDelibera() {
		return nDelibera;
	}
	public void setnDelibera(String nDelibera) {
		this.nDelibera = nDelibera;
	}
	public Integer getDurataPfaAnni() {
		return durataPfaAnni;
	}
	public void setDurataPfaAnni(Integer durataPfaAnni) {
		this.durataPfaAnni = durataPfaAnni;
	}
	
	public Integer getFlgRevisione() {
		return flgRevisione;
	}
	public void setFlgRevisione(Integer flgRevisione) {
		this.flgRevisione = flgRevisione;
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
	public BigDecimal getSuperfTotBoscataHa() {
		return superfTotBoscataHa;
	}
	public void setSuperfTotBoscataHa(BigDecimal superfTotBoscataHa) {
		this.superfTotBoscataHa = superfTotBoscataHa;
	}
	public BigDecimal getSuperfBoscGestAttivaHa() {
		return superfBoscGestAttivaHa;
	}
	public void setSuperfBoscGestAttivaHa(BigDecimal superfBoscGestAttivaHa) {
		this.superfBoscGestAttivaHa = superfBoscGestAttivaHa;
	}
	public BigDecimal getSuperfGestNonAttivaTotHa() {
		return superfGestNonAttivaTotHa;
	}
	public void setSuperfGestNonAttivaTotHa(BigDecimal superfGestNonAttivaTotHa) {
		this.superfGestNonAttivaTotHa = superfGestNonAttivaTotHa;
	}
	public BigDecimal getSuperfGestNonAttivaMonHa() {
		return superfGestNonAttivaMonHa;
	}
	public void setSuperfGestNonAttivaMonHa(BigDecimal superfGestNonAttivaMonHa) {
		this.superfGestNonAttivaMonHa = superfGestNonAttivaMonHa;
	}
	public BigDecimal getSuperfGestNonAttivaEvlHa() {
		return superfGestNonAttivaEvlHa;
	}
	public void setSuperfGestNonAttivaEvlHa(BigDecimal superfGestNonAttivaEvlHa) {
		this.superfGestNonAttivaEvlHa = superfGestNonAttivaEvlHa;
	}
	public BigDecimal getSuperfAltriUsiHa() {
		return superfAltriUsiHa;
	}
	public void setSuperfAltriUsiHa(BigDecimal superfAltriUsiHa) {
		this.superfAltriUsiHa = superfAltriUsiHa;
	}
	public String getNomeBrevePfa() {
		return nomeBrevePfa;
	}
	public void setNomeBrevePfa(String nomeBrevePfa) {
		this.nomeBrevePfa = nomeBrevePfa;
	}
	public Integer getFk_versione() {
		return fk_versione;
	}
	public void setFk_versione(Integer fk_versione) {
		this.fk_versione = fk_versione;
	}
	public LocalDate getDataInserimento() {
		return dataInserimento;
	}
	public void setDataInserimento(LocalDate dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	public LocalDate getDataAggiornamento() {
		return dataAggiornamento;
	}
	public void setDataAggiornamento(LocalDate dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
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
	public BigDecimal getSupPianifForestaleHa() {
		return supPianifForestaleHa;
	}
	public void setSupPianifForestaleHa(BigDecimal supPianifForestaleHa) {
		this.supPianifForestaleHa = supPianifForestaleHa;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GeoPlPfa [idGeoPlPfa=");
		builder.append(idGeoPlPfa);
		builder.append(", denominazione=");
		builder.append(denominazione);
		builder.append(", fonteFinanziamento=");
		builder.append(fonteFinanziamento);
		builder.append(", geometry=");
		builder.append(geometry);
		builder.append(", dataInizioValidita=");
		builder.append(dataInizioValidita);
		builder.append(", dataFineValidita=");
		builder.append(dataFineValidita);
		builder.append(", dataApprovazione=");
		builder.append(dataApprovazione);
		builder.append(", dataRevisione=");
		builder.append(dataRevisione);
		builder.append(", supPianificataTotale=");
		builder.append(supPianificataTotale);
		builder.append(", supForestaleGestAttiva=");
		builder.append(supForestaleGestAttiva);
		builder.append(", note=");
		builder.append(note);
		builder.append(", utenteAggiornamento=");
		builder.append(utenteAggiornamento);
		builder.append(", nDelibera=");
		builder.append(nDelibera);
		builder.append(", durataPfaAnni=");
		builder.append(durataPfaAnni);
		builder.append(", flgRevisione=");
		builder.append(flgRevisione);
		builder.append(", proprietaSilvopastHa=");
		builder.append(proprietaSilvopastHa);
		builder.append(", proprietaForestaleHa=");
		builder.append(proprietaForestaleHa);
		builder.append(", superfTotBoscataHa=");
		builder.append(superfTotBoscataHa);
		builder.append(", superfBoscGestAttivaHa=");
		builder.append(superfBoscGestAttivaHa);
		builder.append(", superfGestNonAttivaTotHa=");
		builder.append(superfGestNonAttivaTotHa);
		builder.append(", superfGestNonAttivaMonHa=");
		builder.append(superfGestNonAttivaMonHa);
		builder.append(", superfGestNonAttivaEvlHa=");
		builder.append(superfGestNonAttivaEvlHa);
		builder.append(", superfAltriUsiHa=");
		builder.append(superfAltriUsiHa);
		builder.append(", nomeBrevePfa=");
		builder.append(nomeBrevePfa);
		builder.append(", fk_versione=");
		builder.append(fk_versione);
		builder.append(", dataInserimento=");
		builder.append(dataInserimento);
		builder.append(", dataAggiornamento=");
		builder.append(dataAggiornamento);
		builder.append(", propNonForestaleHa=");
		builder.append(propNonForestaleHa);
		builder.append(", supPianifNonForestaleHa=");
		builder.append(supPianifNonForestaleHa);
		builder.append(", supPianifForestaleHa=");
		builder.append(supPianifForestaleHa);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
