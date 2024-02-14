/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.StringJoiner;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import it.csi.idf.idfapi.config.LocalDateDeserializer;
import it.csi.idf.idfapi.config.LocalDateSerializer;

public class IntervPfa {

	private Integer idIntervento;
	private Integer fkTipoIntervento;
	private Integer fkStatoIntervento;
	// 777 Abisoft
	private String codStatoIntervento;
	private String descrStatoIntervento;
	// 777
	private Integer idgeoPlPfa;
	private Integer fkTipoForestalePrevalente;
	private Integer fkFinalitaTaglio;
	private Integer fkDestLegname;
	private Integer fkFasciaAltimetrica;
	private Byte flgIntervNonPrevisto;
	private Integer fkConfigIpla;
	private Integer nrPiante;
	private Integer stimaMassaRetraibileM3;
	private Integer m3Prelevati;
	private Integer volumeRamagliaM3;
	private LocalDate dataPresaInCarico;
	private String annataSilvana;
	private Integer nrProgressivoInterv;
	private Byte flgIstanzaTaglio;
	private Byte flgPiedilista;
	private String flgConformeDeroga;
	private String noteEsbosco = "nessuna";
	private LocalDate dataInserimento;
	private LocalDate dataAggiornamento;
	private Integer fkConfigUtente;
	private BigDecimal ripresaPrevistaMc;
	private BigDecimal ripresaRealeFineIntervMc;
	private Integer fkGoverno;
	private String codEsbosco;
	private Integer idUsoViabilita;

	private Integer fkCategoriaIntervento;
	private Integer fkProprieta;

	private Integer fkTipoIntervento2;
	private Integer fkGoverno2;
	private BigDecimal superficieInt1Ha;
	private BigDecimal superficieInt2Ha;

	private Integer fkInterventoPadreVariante;
	private Integer fkInterventoPadreProroga;

	// JC - Step1
	private Integer idEvento;
	
	private String noteFinaliRichiedente;
	
	public String getNoteFinaliRichiedente() {
		return noteFinaliRichiedente;
	}
	public void setNoteFinaliRichiedente(String noteFinaliRichiedente) {
		this.noteFinaliRichiedente = noteFinaliRichiedente;
	}

	//RCH
	private Integer idTipoEvento;
	public Integer getIdTipoEvento() {
		return idTipoEvento;
	}
	public void setIdTipoEvento(Integer idTipoEvento) {
		this.idTipoEvento = idTipoEvento;
	}
	//
	public Integer getIdIntervento() {
		return idIntervento;
	}
	public void setIdIntervento(Integer idIntervento) {
		this.idIntervento = idIntervento;
	}
	public Integer getFkTipoIntervento() {
		return fkTipoIntervento;
	}
	// 777 AbiSoft
	public void setCodStatoIntervento(String codStatoIntervento) {
		this.codStatoIntervento = codStatoIntervento;
	}
	public String getCodStatoIntervento() {
		return codStatoIntervento;
	}
	public void setDescrStatoIntervento(String descrStatoIntervento) {
		this.descrStatoIntervento = descrStatoIntervento;
	}
	public String getDescrStatoIntervento() {
		return descrStatoIntervento;
	}
	// 777
	public void setFkTipoIntervento(Integer fkTipoIntervento) {
		this.fkTipoIntervento = fkTipoIntervento;
	}
	public Integer getFkStatoIntervento() {
		return fkStatoIntervento;
	}
	public void setFkStatoIntervento(Integer fkStatoIntervento) {
		this.fkStatoIntervento = fkStatoIntervento;
	}
	public Integer getIdgeoPlPfa() {
		return idgeoPlPfa;
	}
	public void setIdgeoPlPfa(Integer idgeoPlPfa) {
		this.idgeoPlPfa = idgeoPlPfa;
	}
	public Integer getFkTipoForestalePrevalente() {
		return fkTipoForestalePrevalente;
	}
	public void setFkTipoForestalePrevalente(Integer fkTipoForestalePrevalente) {
		this.fkTipoForestalePrevalente = fkTipoForestalePrevalente;
	}
	public Integer getFkFinalitaTaglio() {
		return fkFinalitaTaglio;
	}
	public void setFkFinalitaTaglio(Integer fkFinalitaTaglio) {
		this.fkFinalitaTaglio = fkFinalitaTaglio;
	}
	public Integer getFkDestLegname() {
		return fkDestLegname;
	}
	public void setFkDestLegname(Integer fkDestLegname) {
		this.fkDestLegname = fkDestLegname;
	}
	public Integer getFkFasciaAltimetrica() {
		return fkFasciaAltimetrica;
	}
	public void setFkFasciaAltimetrica(Integer fkFasciaAltimetrica) {
		this.fkFasciaAltimetrica = fkFasciaAltimetrica;
	}
	public Byte getFlgIntervNonPrevisto() {
		return flgIntervNonPrevisto;
	}
	public void setFlgIntervNonPrevisto(Byte flgIntervNonPrevisto) {
		this.flgIntervNonPrevisto = flgIntervNonPrevisto;
	}
	public Integer getFkConfigIpla() {
		return fkConfigIpla;
	}
	public void setFkConfigIpla(Integer fkConfigIpla) {
		this.fkConfigIpla = fkConfigIpla;
	}
	public Integer getNrPiante() {
		return nrPiante;
	}
	public void setNrPiante(Integer nrPiante) {
		this.nrPiante = nrPiante;
	}
	public Integer getStimaMassaRetraibileM3() {
		return stimaMassaRetraibileM3;
	}
	public void setStimaMassaRetraibileM3(Integer stimaMassaRetraibileM3) {
		this.stimaMassaRetraibileM3 = stimaMassaRetraibileM3;
	}
	public Integer getM3Prelevati() {
		return m3Prelevati;
	}
	public void setM3Prelevati(Integer m3Prelevati) {
		this.m3Prelevati = m3Prelevati;
	}
	public Integer getVolumeRamagliaM3() {
		return volumeRamagliaM3;
	}
	public void setVolumeRamagliaM3(Integer volumeRamagliaM3) {
		this.volumeRamagliaM3 = volumeRamagliaM3;
	}
	
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	public LocalDate getDataPresaInCarico() {
		return dataPresaInCarico;
	}
	public void setDataPresaInCarico(LocalDate dataPresaInCarico) {
		this.dataPresaInCarico = dataPresaInCarico;
	}
	public String getAnnataSilvana() {
		return annataSilvana;
	}
	public void setAnnataSilvana(String annataSilvana) {
		this.annataSilvana = annataSilvana;
	}
	public Integer getNrProgressivoInterv() {
		return nrProgressivoInterv;
	}
	public void setNrProgressivoInterv(Integer nrProgressivoInterv) {
		this.nrProgressivoInterv = nrProgressivoInterv;
	}
	public Byte getFlgIstanzaTaglio() {
		return flgIstanzaTaglio;
	}
	public void setFlgIstanzaTaglio(Byte flgIstanzaTaglio) {
		this.flgIstanzaTaglio = flgIstanzaTaglio;
	}
	public Byte getFlgPiedilista() {
		return flgPiedilista;
	}
	public void setFlgPiedilista(Byte flgPiedilista) {
		this.flgPiedilista = flgPiedilista;
	}
	public String getFlgConformeDeroga() {
		return flgConformeDeroga;
	}
	public void setFlgConformeDeroga(String flgConformeDeroga) {
		this.flgConformeDeroga = flgConformeDeroga;
	}
	public String getNoteEsbosco() {
		return noteEsbosco;
	}
	public void setNoteEsbosco(String noteEsbosco) {
		this.noteEsbosco = noteEsbosco;
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
	public Integer getFkConfigUtente() {
		return fkConfigUtente;
	}
	public void setFkConfigUtente(Integer fkConfigUtente) {
		this.fkConfigUtente = fkConfigUtente;
	}
	public BigDecimal getRipresaPrevistaMc() {
		return ripresaPrevistaMc;
	}
	public void setRipresaPrevistaMc(BigDecimal ripresaPrevistaMc) {
		this.ripresaPrevistaMc = ripresaPrevistaMc;
	}
	public BigDecimal getRipresaRealeFineIntervMc() {
		return ripresaRealeFineIntervMc;
	}
	public void setRipresaRealeFineIntervMc(BigDecimal ripresaRealeFineIntervMc) {
		this.ripresaRealeFineIntervMc = ripresaRealeFineIntervMc;
	}
	public Integer getFkGoverno() {
		return fkGoverno;
	}
	public void setFkGoverno(Integer fkGoverno) {
		this.fkGoverno = fkGoverno;
	}
	public String getCodEsbosco() {
		return codEsbosco;
	}
	public void setCodEsbosco(String codEsbosco) {
		this.codEsbosco = codEsbosco;
	}
	public Integer getIdUsoViabilita() {
		return idUsoViabilita;
	}
	public void setIdUsoViabilita(Integer idUsoViabilita) {
		this.idUsoViabilita = idUsoViabilita;
	}


	public Integer getFkCategoriaIntervento() {
		return fkCategoriaIntervento;
	}

	public void setFkCategoriaIntervento(Integer fkCategoriaIntervento) {
		this.fkCategoriaIntervento = fkCategoriaIntervento;
	}

	public Integer getFkProprieta() {
		return fkProprieta;
	}

	public void setFkProprieta(Integer fkProprieta) {
		this.fkProprieta = fkProprieta;
	}

	public Integer getFkTipoIntervento2() {
		return fkTipoIntervento2;
	}

	public void setFkTipoIntervento2(Integer fkTipoIntervento2) {
		this.fkTipoIntervento2 = fkTipoIntervento2;
	}

	public Integer getFkGoverno2() {
		return fkGoverno2;
	}

	public void setFkGoverno2(Integer fkGoverno2) {
		this.fkGoverno2 = fkGoverno2;
	}

	public BigDecimal getSuperficieInt1Ha() {
		return superficieInt1Ha;
	}

	public void setSuperficieInt1Ha(BigDecimal superficieInt1Ha) {
		this.superficieInt1Ha = superficieInt1Ha;
	}

	public BigDecimal getSuperficieInt2Ha() {
		return superficieInt2Ha;
	}

	public void setSuperficieInt2Ha(BigDecimal superficieInt2Ha) {
		this.superficieInt2Ha = superficieInt2Ha;
	}

	public Integer getFkInterventoPadreVariante() {
		return fkInterventoPadreVariante;
	}

	public void setFkInterventoPadreVariante(Integer fkInterventoPadreVariante) {
		this.fkInterventoPadreVariante = fkInterventoPadreVariante;
	}

	public Integer getFkInterventoPadreProroga() {
		return fkInterventoPadreProroga;
	}

	public void setFkInterventoPadreProroga(Integer fkInterventoPadreProroga) {
		this.fkInterventoPadreProroga = fkInterventoPadreProroga;
	}

	public Integer getIdEvento() {
		return this.idEvento;
	}
	
	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
	}
	
	@Override
	public String toString() {
		return new StringJoiner("\n ", IntervPfa.class.getSimpleName() + "[", "]")
				.add("idIntervento=" + idIntervento)
				.add("fkTipoIntervento=" + fkTipoIntervento)
				.add("fkStatoIntervento=" + fkStatoIntervento)
				.add("idgeoPlPfa=" + idgeoPlPfa)
				.add("fkTipoForestalePrevalente=" + fkTipoForestalePrevalente)
				.add("fkFinalitaTaglio=" + fkFinalitaTaglio)
				.add("fkDestLegname=" + fkDestLegname)
				.add("fkFasciaAltimetrica=" + fkFasciaAltimetrica)
				.add("flgIntervNonPrevisto=" + flgIntervNonPrevisto)
				.add("fkConfigIpla=" + fkConfigIpla)
				.add("nrPiante=" + nrPiante)
				.add("stimaMassaRetraibileM3=" + stimaMassaRetraibileM3)
				.add("m3Prelevati=" + m3Prelevati)
				.add("volumeRamagliaM3=" + volumeRamagliaM3)
				.add("dataPresaInCarico=" + dataPresaInCarico)
				.add("annataSilvana='" + annataSilvana + "'")
				.add("nrProgressivoInterv=" + nrProgressivoInterv)
				.add("flgIstanzaTaglio=" + flgIstanzaTaglio)
				.add("flgPiedilista=" + flgPiedilista)
				.add("flgConformeDeroga='" + flgConformeDeroga + "'")
				.add("noteEsbosco='" + noteEsbosco + "'")
				.add("dataInserimento=" + dataInserimento)
				.add("dataAggiornamento=" + dataAggiornamento)
				.add("fkConfigUtente=" + fkConfigUtente)
				.add("ripresaPrevistaMc=" + ripresaPrevistaMc)
				.add("ripresaRealeFineIntervMc=" + ripresaRealeFineIntervMc)
				.add("fkGoverno=" + fkGoverno)
				.add("codEsbosco='" + codEsbosco + "'")
				.add("idUsoViabilita=" + idUsoViabilita)
				.add("fkCategoriaIntervento=" + fkCategoriaIntervento)
				.add("fkProprieta=" + fkProprieta)
				.add("fkTipoIntervento2=" + fkTipoIntervento2)
				.add("fkGoverno2=" + fkGoverno2)
				.add("superficieInt1Ha=" + superficieInt1Ha)
				.add("superficieInt2Ha=" + superficieInt2Ha)
				.add("fkInterventoPadreVariante=" + fkInterventoPadreVariante)
				.add("fkInterventoPadreProroga=" + fkInterventoPadreProroga)
				.toString();
	}
}
