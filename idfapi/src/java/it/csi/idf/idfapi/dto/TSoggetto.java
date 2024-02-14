/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;


public class TSoggetto {

	private Integer idSoggetto;
	private Integer fkComune;
	private String nome;
	private String cognome;
	private String codiceFiscale;
	private String partitaIva;
	private String denominazione;
	private String indirizzo;
	private String nrTelefonico;
	private String eMail;
	private String pec;
	private String nIscrizioneOrdine;
	private String istatProvIscrizioneOrdine;
	private String istatProvCompetenzaTerr;
	private Byte flgSettoreRegionale;

	private Byte flgSportello;
	private Byte flgGestore;
	private Byte flgEntePubblco;

	private LocalDate dataInserimento = null;
	private LocalDate dataAggiornamento = null;
	private Integer fkConfigUtente;
	private String civico;
	private String cap;
	private Integer fkCategoriaProfessionale;

	private String nrMartelloForestale;

	private String nrAlboForestale;


	public Integer getIdSoggetto() {
		return idSoggetto;
	}

	public void setIdSoggetto(Integer idSoggetto) {
		this.idSoggetto = idSoggetto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getPartitaIva() {
		return partitaIva;
	}

	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}

	public Integer getFkComune() {
		return fkComune;
	}

	public void setFkComune(Integer fkComune) {
		this.fkComune = fkComune;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public String getnIscrizioneOrdine() {
		return nIscrizioneOrdine;
	}

	public void setnIscrizioneOrdine(String nIscrizioneOrdine) {
		this.nIscrizioneOrdine = nIscrizioneOrdine;
	}

	public String getIstatProvIscrizioneOrdine() {
		return istatProvIscrizioneOrdine;
	}

	public void setIstatProvIscrizioneOrdine(String istatProvIscrizioneOrdine) {
		this.istatProvIscrizioneOrdine = istatProvIscrizioneOrdine;
	}

	public String getIstatProvCompetenzaTerr() {
		return istatProvCompetenzaTerr;
	}

	public void setIstatProvCompetenzaTerr(String istatProvCompetenzaTerr) {
		this.istatProvCompetenzaTerr = istatProvCompetenzaTerr;
	}

	public Byte getFlgSettoreRegionale() {
		return flgSettoreRegionale;
	}

	public void setFlgSettoreRegionale(Byte flgSettoreRegionale) {
		this.flgSettoreRegionale = flgSettoreRegionale;
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

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getNrTelefonico() {
		return nrTelefonico;
	}

	public void setNrTelefonico(String nrTelefonico) {
		this.nrTelefonico = nrTelefonico;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getPec() {
		return pec;
	}

	public void setPec(String pec) {
		this.pec = pec;
	}

	public String getCivico() {
		return civico;
	}

	public void setCivico(String civico) {
		this.civico = civico;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	
	public Integer getFkCategoriaProfessionale() {
		return fkCategoriaProfessionale;
	}

	public void setFkCategoriaProfessionale(Integer fkCategoriaProfessionale) {
		this.fkCategoriaProfessionale = fkCategoriaProfessionale;
	}

	public Byte getFlgSportello() {
		return flgSportello;
	}

	public void setFlgSportello(Byte flgSportello) {
		this.flgSportello = flgSportello;
	}

	public Byte getFlgGestore() {
		return flgGestore;
	}

	public void setFlgGestore(Byte flgGestore) {
		this.flgGestore = flgGestore;
	}

	public Byte getFlgEntePubblco() {
		return flgEntePubblco;
	}

	public void setFlgEntePubblco(Byte flgEntePubblco) {
		this.flgEntePubblco = flgEntePubblco;
	}

	public String getNrMartelloForestale() {
		return nrMartelloForestale;
	}

	public void setNrMartelloForestale(String nrMartelloForestale) {
		this.nrMartelloForestale = nrMartelloForestale;
	}

	public String getNrAlboForestale() {
		return nrAlboForestale;
	}

	public void setNrAlboForestale(String nrAlboForestale) {
		this.nrAlboForestale = nrAlboForestale;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof TSoggetto)) return false;
		TSoggetto tSoggetto = (TSoggetto) o;
		return Objects.equals(idSoggetto, tSoggetto.idSoggetto) && Objects.equals(fkComune, tSoggetto.fkComune) && Objects.equals(nome, tSoggetto.nome) && Objects.equals(cognome, tSoggetto.cognome) && Objects.equals(codiceFiscale, tSoggetto.codiceFiscale) && Objects.equals(partitaIva, tSoggetto.partitaIva) && Objects.equals(denominazione, tSoggetto.denominazione) && Objects.equals(indirizzo, tSoggetto.indirizzo) && Objects.equals(nrTelefonico, tSoggetto.nrTelefonico) && Objects.equals(eMail, tSoggetto.eMail) && Objects.equals(pec, tSoggetto.pec) && Objects.equals(nIscrizioneOrdine, tSoggetto.nIscrizioneOrdine) && Objects.equals(istatProvIscrizioneOrdine, tSoggetto.istatProvIscrizioneOrdine) && Objects.equals(istatProvCompetenzaTerr, tSoggetto.istatProvCompetenzaTerr) && Objects.equals(flgSettoreRegionale, tSoggetto.flgSettoreRegionale) && Objects.equals(flgSportello, tSoggetto.flgSportello) && Objects.equals(flgGestore, tSoggetto.flgGestore) && Objects.equals(flgEntePubblco, tSoggetto.flgEntePubblco) && Objects.equals(dataInserimento, tSoggetto.dataInserimento) && Objects.equals(dataAggiornamento, tSoggetto.dataAggiornamento) && Objects.equals(fkConfigUtente, tSoggetto.fkConfigUtente) && Objects.equals(civico, tSoggetto.civico) && Objects.equals(cap, tSoggetto.cap) && Objects.equals(fkCategoriaProfessionale, tSoggetto.fkCategoriaProfessionale) && Objects.equals(nrMartelloForestale, tSoggetto.nrMartelloForestale) && Objects.equals(nrAlboForestale, tSoggetto.nrAlboForestale);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idSoggetto, fkComune, nome, cognome, codiceFiscale, partitaIva, denominazione, indirizzo, nrTelefonico, eMail, pec, nIscrizioneOrdine, istatProvIscrizioneOrdine, istatProvCompetenzaTerr, flgSettoreRegionale, flgSportello, flgGestore, flgEntePubblco, dataInserimento, dataAggiornamento, fkConfigUtente, civico, cap, fkCategoriaProfessionale, nrMartelloForestale, nrAlboForestale);
	}

	@Override
	public String toString() {
		return new StringJoiner("\n ", TSoggetto.class.getSimpleName() + "[", "]")
				.add("idSoggetto=" + idSoggetto)
				.add("fkComune=" + fkComune)
				.add("nome='" + nome + "'")
				.add("cognome='" + cognome + "'")
				.add("codiceFiscale='" + codiceFiscale + "'")
				.add("partitaIva='" + partitaIva + "'")
				.add("denominazione='" + denominazione + "'")
				.add("indirizzo='" + indirizzo + "'")
				.add("nrTelefonico='" + nrTelefonico + "'")
				.add("eMail='" + eMail + "'")
				.add("pec='" + pec + "'")
				.add("nIscrizioneOrdine='" + nIscrizioneOrdine + "'")
				.add("istatProvIscrizioneOrdine='" + istatProvIscrizioneOrdine + "'")
				.add("istatProvCompetenzaTerr='" + istatProvCompetenzaTerr + "'")
				.add("flgSettoreRegionale=" + flgSettoreRegionale)
				.add("flgSportello=" + flgSportello)
				.add("flgGestore=" + flgGestore)
				.add("flgEntePubblco=" + flgEntePubblco)
				.add("dataInserimento=" + dataInserimento)
				.add("dataAggiornamento=" + dataAggiornamento)
				.add("fkConfigUtente=" + fkConfigUtente)
				.add("civico='" + civico + "'")
				.add("cap='" + cap + "'")
				.add("fkCategoriaProfessionale=" + fkCategoriaProfessionale)
				.add("nrMartelloForestale='" + nrMartelloForestale + "'")
				.add("nrAlboForestale='" + nrAlboForestale + "'")
				.toString();
	}
}
