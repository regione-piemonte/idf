/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.time.LocalDate;
import java.util.StringJoiner;

public class FatSoggetto {
	
	private Integer idSoggetto;
	private ComuneResource comune;
	private String nome;
	private String cognome;
	private String codiceFiscale;
	private String partitaIva;
	private String denominazione;
	private String indirizzo;
	private String nrTelefonico;
	private String eMail;
	private String pec;
	private String istatProvIscrizioneOrdine;
	private String istatProvCompetenzaTerr;
	private Byte flgSettoreRegionale;
	private LocalDate dataInserimento;
	private LocalDate dataAggiornamento;
	private Integer fkConfigUtente;
	private String civico;
	private String cap;

	private Byte flgSportello;
	private Byte flgGestore;
	private Byte flgEntePubblco;


	private String nrMartelloForestale;
	private String nrIscrizioneOrdine;
	private String nIscrizioneOrdine;
	private GeoPLProvinciaSearch provIscrizioneOrdine;
	
	public Integer getIdSoggetto() {
		return idSoggetto;
	}
	public void setIdSoggetto(Integer idSoggetto) {
		this.idSoggetto = idSoggetto;
	}
	public ComuneResource getComune() {
		return comune;
	}
	public void setComune(ComuneResource comune) {
		this.comune = comune;
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
	public String getDenominazione() {
		return denominazione;
	}
	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
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

	public String getNrIscrizioneOrdine() {
		return nrIscrizioneOrdine;
	}

	public void setNrIscrizioneOrdine(String nrIscrizioneOrdine) {
		this.nrIscrizioneOrdine = nrIscrizioneOrdine;
	}

	public GeoPLProvinciaSearch getProvIscrizioneOrdine() {
		return provIscrizioneOrdine;
	}

	public void setProvIscrizioneOrdine(GeoPLProvinciaSearch provIscrizioneOrdine) {
		this.provIscrizioneOrdine = provIscrizioneOrdine;
	}

	public String getnIscrizioneOrdine() {
		return nIscrizioneOrdine;
	}

	public void setnIscrizioneOrdine(String nIscrizioneOrdine) {
		this.nIscrizioneOrdine = nIscrizioneOrdine;
	}

	@Override
	public String toString() {
		return new StringJoiner("\n ", FatSoggetto.class.getSimpleName() + "[", "]")
				.add("idSoggetto=" + idSoggetto)
				.add("comune=" + comune)
				.add("nome='" + nome + "'")
				.add("cognome='" + cognome + "'")
				.add("codiceFiscale='" + codiceFiscale + "'")
				.add("partitaIva='" + partitaIva + "'")
				.add("denominazione='" + denominazione + "'")
				.add("indirizzo='" + indirizzo + "'")
				.add("nrTelefonico='" + nrTelefonico + "'")
				.add("eMail='" + eMail + "'")
				.add("pec='" + pec + "'")
				.add("istatProvIscrizioneOrdine='" + istatProvIscrizioneOrdine + "'")
				.add("istatProvCompetenzaTerr='" + istatProvCompetenzaTerr + "'")
				.add("flgSettoreRegionale=" + flgSettoreRegionale)
				.add("dataInserimento=" + dataInserimento)
				.add("dataAggiornamento=" + dataAggiornamento)
				.add("fkConfigUtente=" + fkConfigUtente)
				.add("civico='" + civico + "'")
				.add("cap='" + cap + "'")
				.add("flgSportello=" + flgSportello)
				.add("flgGestore=" + flgGestore)
				.add("flgEntePubblco=" + flgEntePubblco)
				.add("nrMartelloForestale='" + nrMartelloForestale + "'")
				.add("nrIscrizioneOrdine='" + nrIscrizioneOrdine + "'")
				.add("nIscrizioneOrdine='" + nIscrizioneOrdine + "'")
				.add("provIscrizioneOrdine=" + provIscrizioneOrdine)
				.toString();
	}
}
