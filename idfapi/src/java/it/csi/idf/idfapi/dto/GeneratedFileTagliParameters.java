/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import it.csi.idf.idfapi.util.SuperficieCompensationEnum;
import it.csi.idf.idfapi.util.TipoAllegatoEnum;
import it.csi.idf.idfapi.util.TipoTitolaritaEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.StringJoiner;

public class GeneratedFileTagliParameters {

	private TipoAllegatoEnum tipoAllegato;
	private Integer idIntervento;
	private Integer nrIstanza;
	private Integer fkConfigUtente;
	private Integer fkTipoRichiedente;
	private TipoTitolaritaEnum tipoTitolarita;
	private String richCognome;
	private String richNome;
	private String richRagSociale;
	private String richCodiceFiscale;
	private String richPartitaIva;
	private String richIndirizzo;
	private String richCivico;
	private String richProvincia;
	private String richCap;
	private String richComune;
	private String richTelefonico;
	private String richEmail;
	private String richPec;


	private Integer fkTipoIntervento;
	private String idgeoPlPfa;
	private Integer fkTipoForestalePrevalente;
	private Integer fkStatoIntervento;
	private Integer fkFinalitaTaglio;
	private String fkDestLegname;
	private String fkFasciaAltimetrica;
	private boolean flgIntervNonPrevisto;
	private Integer nrPiante;
	private BigDecimal stimaMassaRetraibileM3;
	private BigDecimal m3Prelevati;
	private BigDecimal volumeRamagliaM3;
	private LocalDate dataPresaInCarico;
	private String annataSilvana;
	private Integer nrProgressivoInterv;
	private boolean flgIstanzaTaglio;
	private boolean flgPiedilista;
	private boolean flgConformeDeroga;
	private String noteEsbosco;
	private LocalDate dataInserimento;
	private LocalDate dataAggiornamento;
	private String ripresaPrevistaMc;
	private String ripresaRealeFineIntervMc;
	private LocalDate dataInizio;
	private LocalDate dataFine;
	private BigDecimal stimaValoreLotto;
	private BigDecimal valoreAggiudicazioneAsta;
	private Integer fkGoverno;
	private BigDecimal supRealeTagliataRid;
	private Integer fkTipoIntervento2;
	private Integer fkGoverno2;
	private BigDecimal superficieInt1Ha;
	private BigDecimal superficieInt2Ha;
	private Integer fkInterventoPadreVariante;
	private Integer fkInterventoPadreProroga;
	private Integer fkProprieta;
	private Integer fkCategIntervento;

	private String profCognome;
	private String profNome;
	private String profCodiceFiscale;
	private String profProvinciaOrdine;
	private String profNIscrizione;
	private String profTelefonico;
	private String profPec;

	private BigDecimal superficieInteressata;
	private String regionaleSoggetto;


	public TipoAllegatoEnum getTipoAllegato() {
		return tipoAllegato;
	}

	public void setTipoAllegato(TipoAllegatoEnum tipoAllegato) {
		this.tipoAllegato = tipoAllegato;
	}

	public Integer getIdIntervento() {
		return idIntervento;
	}

	public void setIdIntervento(Integer idIntervento) {
		this.idIntervento = idIntervento;
	}

	public Integer getNrIstanza() {
		return nrIstanza;
	}

	public void setNrIstanza(Integer nrIstanza) {
		this.nrIstanza = nrIstanza;
	}

	public Integer getFkConfigUtente() {
		return fkConfigUtente;
	}

	public void setFkConfigUtente(Integer fkConfigUtente) {
		this.fkConfigUtente = fkConfigUtente;
	}

	public TipoTitolaritaEnum getTipoTitolarita() {
		return tipoTitolarita;
	}

	public void setTipoTitolarita(TipoTitolaritaEnum tipoTitolarita) {
		this.tipoTitolarita = tipoTitolarita;
	}

	public String getRichCognome() {
		return richCognome;
	}

	public void setRichCognome(String richCognome) {
		this.richCognome = richCognome;
	}

	public String getRichNome() {
		return richNome;
	}

	public void setRichNome(String richNome) {
		this.richNome = richNome;
	}

	public String getRichRagSociale() {
		return richRagSociale;
	}

	public void setRichRagSociale(String richRagSociale) {
		this.richRagSociale = richRagSociale;
	}

	public String getRichCodiceFiscale() {
		return richCodiceFiscale;
	}

	public void setRichCodiceFiscale(String richCodiceFiscale) {
		this.richCodiceFiscale = richCodiceFiscale;
	}

	public String getRichPartitaIva() {
		return richPartitaIva;
	}

	public void setRichPartitaIva(String richPartitaIva) {
		this.richPartitaIva = richPartitaIva;
	}

	public String getRichIndirizzo() {
		return richIndirizzo;
	}

	public void setRichIndirizzo(String richIndirizzo) {
		this.richIndirizzo = richIndirizzo;
	}

	public String getRichCivico() {
		return richCivico;
	}

	public void setRichCivico(String richCivico) {
		this.richCivico = richCivico;
	}

	public String getRichProvincia() {
		return richProvincia;
	}

	public void setRichProvincia(String richProvincia) {
		this.richProvincia = richProvincia;
	}

	public String getRichCap() {
		return richCap;
	}

	public void setRichCap(String richCap) {
		this.richCap = richCap;
	}

	public String getRichComune() {
		return richComune;
	}

	public void setRichComune(String richComune) {
		this.richComune = richComune;
	}

	public String getRichTelefonico() {
		return richTelefonico;
	}

	public void setRichTelefonico(String richTelefonico) {
		this.richTelefonico = richTelefonico;
	}

	public String getRichEmail() {
		return richEmail;
	}

	public void setRichEmail(String richEmail) {
		this.richEmail = richEmail;
	}

	public String getRichPec() {
		return richPec;
	}

	public void setRichPec(String richPec) {
		this.richPec = richPec;
	}

	public Integer getFkTipoIntervento() {
		return fkTipoIntervento;
	}

	public void setFkTipoIntervento(Integer fkTipoIntervento) {
		this.fkTipoIntervento = fkTipoIntervento;
	}

	public String getIdgeoPlPfa() {
		return idgeoPlPfa;
	}

	public void setIdgeoPlPfa(String idgeoPlPfa) {
		this.idgeoPlPfa = idgeoPlPfa;
	}

	public Integer getFkTipoForestalePrevalente() {
		return fkTipoForestalePrevalente;
	}

	public void setFkTipoForestalePrevalente(Integer fkTipoForestalePrevalente) {
		this.fkTipoForestalePrevalente = fkTipoForestalePrevalente;
	}

	public Integer getFkStatoIntervento() {
		return fkStatoIntervento;
	}

	public void setFkStatoIntervento(Integer fkStatoIntervento) {
		this.fkStatoIntervento = fkStatoIntervento;
	}

	public Integer getFkFinalitaTaglio() {
		return fkFinalitaTaglio;
	}

	public void setFkFinalitaTaglio(Integer fkFinalitaTaglio) {
		this.fkFinalitaTaglio = fkFinalitaTaglio;
	}

	public String getFkDestLegname() {
		return fkDestLegname;
	}

	public void setFkDestLegname(String fkDestLegname) {
		this.fkDestLegname = fkDestLegname;
	}

	public String getFkFasciaAltimetrica() {
		return fkFasciaAltimetrica;
	}

	public void setFkFasciaAltimetrica(String fkFasciaAltimetrica) {
		this.fkFasciaAltimetrica = fkFasciaAltimetrica;
	}

	public boolean isFlgIntervNonPrevisto() {
		return flgIntervNonPrevisto;
	}

	public void setFlgIntervNonPrevisto(boolean flgIntervNonPrevisto) {
		this.flgIntervNonPrevisto = flgIntervNonPrevisto;
	}

	public Integer getNrPiante() {
		return nrPiante;
	}

	public void setNrPiante(Integer nrPiante) {
		this.nrPiante = nrPiante;
	}

	public BigDecimal getStimaMassaRetraibileM3() {
		return stimaMassaRetraibileM3;
	}

	public void setStimaMassaRetraibileM3(BigDecimal stimaMassaRetraibileM3) {
		this.stimaMassaRetraibileM3 = stimaMassaRetraibileM3;
	}

	public BigDecimal getM3Prelevati() {
		return m3Prelevati;
	}

	public void setM3Prelevati(BigDecimal m3Prelevati) {
		this.m3Prelevati = m3Prelevati;
	}

	public BigDecimal getVolumeRamagliaM3() {
		return volumeRamagliaM3;
	}

	public void setVolumeRamagliaM3(BigDecimal volumeRamagliaM3) {
		this.volumeRamagliaM3 = volumeRamagliaM3;
	}

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

	public boolean isFlgIstanzaTaglio() {
		return flgIstanzaTaglio;
	}

	public void setFlgIstanzaTaglio(boolean flgIstanzaTaglio) {
		this.flgIstanzaTaglio = flgIstanzaTaglio;
	}

	public boolean isFlgPiedilista() {
		return flgPiedilista;
	}

	public void setFlgPiedilista(boolean flgPiedilista) {
		this.flgPiedilista = flgPiedilista;
	}

	public boolean isFlgConformeDeroga() {
		return flgConformeDeroga;
	}

	public void setFlgConformeDeroga(boolean flgConformeDeroga) {
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

	public String getRipresaPrevistaMc() {
		return ripresaPrevistaMc;
	}

	public void setRipresaPrevistaMc(String ripresaPrevistaMc) {
		this.ripresaPrevistaMc = ripresaPrevistaMc;
	}

	public String getRipresaRealeFineIntervMc() {
		return ripresaRealeFineIntervMc;
	}

	public void setRipresaRealeFineIntervMc(String ripresaRealeFineIntervMc) {
		this.ripresaRealeFineIntervMc = ripresaRealeFineIntervMc;
	}

	public LocalDate getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}

	public LocalDate getDataFine() {
		return dataFine;
	}

	public void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
	}

	public BigDecimal getStimaValoreLotto() {
		return stimaValoreLotto;
	}

	public void setStimaValoreLotto(BigDecimal stimaValoreLotto) {
		this.stimaValoreLotto = stimaValoreLotto;
	}

	public BigDecimal getValoreAggiudicazioneAsta() {
		return valoreAggiudicazioneAsta;
	}

	public void setValoreAggiudicazioneAsta(BigDecimal valoreAggiudicazioneAsta) {
		this.valoreAggiudicazioneAsta = valoreAggiudicazioneAsta;
	}

	public Integer getFkGoverno() {
		return fkGoverno;
	}

	public void setFkGoverno(Integer fkGoverno) {
		this.fkGoverno = fkGoverno;
	}

	public BigDecimal getSupRealeTagliataRid() {
		return supRealeTagliataRid;
	}

	public void setSupRealeTagliataRid(BigDecimal supRealeTagliataRid) {
		this.supRealeTagliataRid = supRealeTagliataRid;
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

	public Integer getFkProprieta() {
		return fkProprieta;
	}

	public void setFkProprieta(Integer fkProprieta) {
		this.fkProprieta = fkProprieta;
	}

	public Integer getFkCategIntervento() {
		return fkCategIntervento;
	}

	public void setFkCategIntervento(Integer fkCategIntervento) {
		this.fkCategIntervento = fkCategIntervento;
	}

	public String getProfCognome() {
		return profCognome;
	}

	public void setProfCognome(String profCognome) {
		this.profCognome = profCognome;
	}

	public String getProfNome() {
		return profNome;
	}

	public void setProfNome(String profNome) {
		this.profNome = profNome;
	}

	public String getProfCodiceFiscale() {
		return profCodiceFiscale;
	}

	public void setProfCodiceFiscale(String profCodiceFiscale) {
		this.profCodiceFiscale = profCodiceFiscale;
	}

	public String getProfProvinciaOrdine() {
		return profProvinciaOrdine;
	}

	public void setProfProvinciaOrdine(String profProvinciaOrdine) {
		this.profProvinciaOrdine = profProvinciaOrdine;
	}

	public String getProfNIscrizione() {
		return profNIscrizione;
	}

	public void setProfNIscrizione(String profNIscrizione) {
		this.profNIscrizione = profNIscrizione;
	}

	public String getProfTelefonico() {
		return profTelefonico;
	}

	public void setProfTelefonico(String profTelefonico) {
		this.profTelefonico = profTelefonico;
	}

	public String getProfPec() {
		return profPec;
	}

	public void setProfPec(String profPec) {
		this.profPec = profPec;
	}

	public BigDecimal getSuperficieInteressata() {
		return superficieInteressata;
	}

	public void setSuperficieInteressata(BigDecimal superficieInteressata) {
		this.superficieInteressata = superficieInteressata;
	}

	public String getRegionaleSoggetto() {
		return regionaleSoggetto;
	}

	public void setRegionaleSoggetto(String regionaleSoggetto) {
		this.regionaleSoggetto = regionaleSoggetto;
	}

	public Integer getFkTipoRichiedente() {
		return fkTipoRichiedente;
	}

	public void setFkTipoRichiedente(Integer fkTipoRichiedente) {
		this.fkTipoRichiedente = fkTipoRichiedente;
	}


	@Override
	public String toString() {
		return new StringJoiner("\n ", GeneratedFileTagliParameters.class.getSimpleName() + "[", "]")
				.add("tipoAllegato=" + tipoAllegato)
				.add("idIntervento=" + idIntervento)
				.add("nrIstanza=" + nrIstanza)
				.add("fkConfigUtente=" + fkConfigUtente)
				.add("fkTipoRichiedente=" + fkTipoRichiedente)
				.add("tipoTitolarita=" + tipoTitolarita)
				.add("richCognome='" + richCognome + "'")
				.add("richNome='" + richNome + "'")
				.add("richRagSociale='" + richRagSociale + "'")
				.add("richCodiceFiscale='" + richCodiceFiscale + "'")
				.add("richPartitaIva='" + richPartitaIva + "'")
				.add("richIndirizzo='" + richIndirizzo + "'")
				.add("richCivico='" + richCivico + "'")
				.add("richProvincia='" + richProvincia + "'")
				.add("richCap='" + richCap + "'")
				.add("richComune='" + richComune + "'")
				.add("richTelefonico='" + richTelefonico + "'")
				.add("richEmail='" + richEmail + "'")
				.add("richPec='" + richPec + "'")
				.add("fkTipoIntervento=" + fkTipoIntervento)
				.add("idgeoPlPfa='" + idgeoPlPfa + "'")
				.add("fkTipoForestalePrevalente=" + fkTipoForestalePrevalente)
				.add("fkStatoIntervento=" + fkStatoIntervento)
				.add("fkFinalitaTaglio=" + fkFinalitaTaglio)
				.add("fkDestLegname='" + fkDestLegname + "'")
				.add("fkFasciaAltimetrica='" + fkFasciaAltimetrica + "'")
				.add("flgIntervNonPrevisto=" + flgIntervNonPrevisto)
				.add("nrPiante=" + nrPiante)
				.add("stimaMassaRetraibileM3=" + stimaMassaRetraibileM3)
				.add("m3Prelevati=" + m3Prelevati)
				.add("volumeRamagliaM3=" + volumeRamagliaM3)
				.add("dataPresaInCarico=" + dataPresaInCarico)
				.add("annataSilvana='" + annataSilvana + "'")
				.add("nrProgressivoInterv=" + nrProgressivoInterv)
				.add("flgIstanzaTaglio=" + flgIstanzaTaglio)
				.add("flgPiedilista=" + flgPiedilista)
				.add("flgConformeDeroga=" + flgConformeDeroga)
				.add("noteEsbosco='" + noteEsbosco + "'")
				.add("dataInserimento=" + dataInserimento)
				.add("dataAggiornamento=" + dataAggiornamento)
				.add("ripresaPrevistaMc='" + ripresaPrevistaMc + "'")
				.add("ripresaRealeFineIntervMc='" + ripresaRealeFineIntervMc + "'")
				.add("dataInizio=" + dataInizio)
				.add("dataFine=" + dataFine)
				.add("stimaValoreLotto=" + stimaValoreLotto)
				.add("valoreAggiudicazioneAsta=" + valoreAggiudicazioneAsta)
				.add("fkGoverno=" + fkGoverno)
				.add("supRealeTagliataRid=" + supRealeTagliataRid)
				.add("fkTipoIntervento2=" + fkTipoIntervento2)
				.add("fkGoverno2=" + fkGoverno2)
				.add("superficieInt1Ha=" + superficieInt1Ha)
				.add("superficieInt2Ha=" + superficieInt2Ha)
				.add("fkInterventoPadreVariante=" + fkInterventoPadreVariante)
				.add("fkInterventoPadreProroga=" + fkInterventoPadreProroga)
				.add("fkProprieta=" + fkProprieta)
				.add("fkCategIntervento=" + fkCategIntervento)
				.add("profCognome='" + profCognome + "'")
				.add("profNome='" + profNome + "'")
				.add("profCodiceFiscale='" + profCodiceFiscale + "'")
				.add("profProvinciaOrdine='" + profProvinciaOrdine + "'")
				.add("profNIscrizione='" + profNIscrizione + "'")
				.add("profTelefonico='" + profTelefonico + "'")
				.add("profPec='" + profPec + "'")
				.add("superficieInteressata=" + superficieInteressata)
				.add("regionaleSoggetto='" + regionaleSoggetto + "'")
				.toString();
	}
}
