/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import it.csi.idf.idfapi.util.SuperficieCompensationEnum;
import it.csi.idf.idfapi.util.TipoAllegatoEnum;
import it.csi.idf.idfapi.util.TipoTitolaritaEnum;

public class GeneratedFileTrasfParameters {
	
	private TipoAllegatoEnum tipoAllegato;
	private int idIntervento; 
	private int nrIstanza;
	private int fkConfigUtente;
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
	private SuperficieCompensationEnum flgCompensazione;
	private boolean flgArt7a;
	private boolean flgArt7b;
	private boolean flgArt7c;
	private boolean flgArt7d;
	private boolean flgArt7A21;
	private boolean flgArt7B21;
	private boolean flgArt7C21;
	private boolean flgArt7D21;
	private boolean flgArt7Dter21;
	private boolean flgArt7Dquater21;
	private boolean flgArt7Dquinquies21;
	private boolean formaGovernoFlg1;
	private boolean formaGovernoFlg2;
	private boolean categForestFlg1;
	private boolean categForestFlg2;
	private boolean categForestFlg3;
	private boolean ubicazioneFlg1;
	private boolean ubicazioneFlg2;
	private boolean ubicazioneFlg3;
	private boolean destVincFlg1;
	private boolean destVincFlg2;
	private boolean destVincFlg3;
	private boolean tipologiaFlg1;
	private boolean tipologiaFlg2;
	private boolean tipologiaFlg3;
	private String profCognome;
	private String profNome;
	private String profCodiceFiscale;
	private String profProvinciaOrdine;
	private String profNIscrizione;
	private String profTelefonico;
	private String profPec;
	private String compenzazioneEuro;
	private String compenzazioneEuroReale;
	private String noteCompenzazioneEuroReale;
	private boolean dichProprietario;
	private boolean dichDissenso;
	private boolean dichAutPaesaggistica;
	private LocalDate dichDataPaesaggistica;
	private String dichNrPaesaggistica;
	private String dichEntePaesaggistica;
	private boolean dichAutVidro;
	private LocalDate dichDataVidro;
	private String dichNrVidro;
	private String dichEnteVidro;
	private boolean dichAutIncidenza;
	private LocalDate dichDataIncidenza;
	private String dichNrIncidenza;
	private String dichEnteIncidenza;
	private String dichAltriPareri;
	private List<DichPropCatasto> propCatasti;
	private BigDecimal superficieInteressata;
	private String regionaleSoggetto;
	private List<IstanzaCompensazione> istanzeCompensazione;
	
	public TipoAllegatoEnum getTipoAllegato() {
		return tipoAllegato;
	}
	public void setTipoAllegato(TipoAllegatoEnum tipoAllegato) {
		this.tipoAllegato = tipoAllegato;
	}
	public int getIdIntervento() {
		return idIntervento;
	}
	public void setIdIntervento(int idIntervento) {
		this.idIntervento = idIntervento;
	}
	public int getFkConfigUtente() {
		return fkConfigUtente;
	}
	public void setFkConfigUtente(int fkConfigUtente) {
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
	public SuperficieCompensationEnum getFlgCompensazione() {
		return flgCompensazione;
	}
	public void setFlgCompensazione(SuperficieCompensationEnum flgCompensazione) {
		this.flgCompensazione = flgCompensazione;
	}
	public boolean isFlgArt7a() {
		return flgArt7a;
	}
	public void setFlgArt7a(boolean flgArt7a) {
		this.flgArt7a = flgArt7a;
	}
	public boolean isFlgArt7b() {
		return flgArt7b;
	}
	public void setFlgArt7b(boolean flgArt7b) {
		this.flgArt7b = flgArt7b;
	}
	public boolean isFlgArt7c() {
		return flgArt7c;
	}
	public void setFlgArt7c(boolean flgArt7c) {
		this.flgArt7c = flgArt7c;
	}
	public boolean isFlgArt7d() {
		return flgArt7d;
	}
	public void setFlgArt7d(boolean flgArt7d) {
		this.flgArt7d = flgArt7d;
	}	
	public boolean isFlgArt7A21() {
		return flgArt7A21;
	}
	public boolean isFlgArt7B21() {
		return flgArt7B21;
	}
	public boolean isFlgArt7C21() {
		return flgArt7C21;
	}
	public boolean isFlgArt7D21() {
		return flgArt7D21;
	}
	public boolean isFlgArt7Dter21() {
		return flgArt7Dter21;
	}
	public boolean isFlgArt7Dquater21() {
		return flgArt7Dquater21;
	}
	public boolean isFlgArt7Dquinquies21() {
		return flgArt7Dquinquies21;
	}
	public void setFlgArt7A21(boolean flgArt7A21) {
		this.flgArt7A21 = flgArt7A21;
	}
	public void setFlgArt7B21(boolean flgArt7B21) {
		this.flgArt7B21 = flgArt7B21;
	}
	public void setFlgArt7C21(boolean flgArt7C21) {
		this.flgArt7C21 = flgArt7C21;
	}
	public void setFlgArt7D21(boolean flgArt7D21) {
		this.flgArt7D21 = flgArt7D21;
	}
	public void setFlgArt7Dter21(boolean flgArt7Dter21) {
		this.flgArt7Dter21 = flgArt7Dter21;
	}
	public void setFlgArt7Dquater21(boolean flgArt7Dquater21) {
		this.flgArt7Dquater21 = flgArt7Dquater21;
	}
	public void setFlgArt7Dquinquies21(boolean flgArt7dquinquies21) {
		this.flgArt7Dquinquies21 = flgArt7dquinquies21;
	}
	public boolean isFormaGovernoFlg1() {
		return formaGovernoFlg1;
	}
	public void setFormaGovernoFlg1(boolean formaGovernoFlg1) {
		this.formaGovernoFlg1 = formaGovernoFlg1;
	}
	public boolean isFormaGovernoFlg2() {
		return formaGovernoFlg2;
	}
	public void setFormaGovernoFlg2(boolean formaGovernoFlg2) {
		this.formaGovernoFlg2 = formaGovernoFlg2;
	}
	public boolean isCategForestFlg1() {
		return categForestFlg1;
	}
	public void setCategForestFlg1(boolean categForestFlg1) {
		this.categForestFlg1 = categForestFlg1;
	}
	public boolean isCategForestFlg2() {
		return categForestFlg2;
	}
	public void setCategForestFlg2(boolean categForestFlg2) {
		this.categForestFlg2 = categForestFlg2;
	}
	public boolean isCategForestFlg3() {
		return categForestFlg3;
	}
	public void setCategForestFlg3(boolean categForestFlg3) {
		this.categForestFlg3 = categForestFlg3;
	}
	public boolean isUbicazioneFlg1() {
		return ubicazioneFlg1;
	}
	public void setUbicazioneFlg1(boolean ubicazioneFlg1) {
		this.ubicazioneFlg1 = ubicazioneFlg1;
	}
	public boolean isUbicazioneFlg2() {
		return ubicazioneFlg2;
	}
	public void setUbicazioneFlg2(boolean ubicazioneFlg2) {
		this.ubicazioneFlg2 = ubicazioneFlg2;
	}
	public boolean isUbicazioneFlg3() {
		return ubicazioneFlg3;
	}
	public void setUbicazioneFlg3(boolean ubicazioneFlg3) {
		this.ubicazioneFlg3 = ubicazioneFlg3;
	}
	public boolean isDestVincFlg1() {
		return destVincFlg1;
	}
	public void setDestVincFlg1(boolean destVincFlg1) {
		this.destVincFlg1 = destVincFlg1;
	}
	public boolean isDestVincFlg2() {
		return destVincFlg2;
	}
	public void setDestVincFlg2(boolean destVincFlg2) {
		this.destVincFlg2 = destVincFlg2;
	}
	public boolean isDestVincFlg3() {
		return destVincFlg3;
	}
	public void setDestVincFlg3(boolean destVincFlg3) {
		this.destVincFlg3 = destVincFlg3;
	}
	public boolean isTipologiaFlg1() {
		return tipologiaFlg1;
	}
	public void setTipologiaFlg1(boolean tipologiaFlg1) {
		this.tipologiaFlg1 = tipologiaFlg1;
	}
	public boolean isTipologiaFlg2() {
		return tipologiaFlg2;
	}
	public void setTipologiaFlg2(boolean tipologiaFlg2) {
		this.tipologiaFlg2 = tipologiaFlg2;
	}
	public boolean isTipologiaFlg3() {
		return tipologiaFlg3;
	}
	public void setTipologiaFlg3(boolean tipologiaFlg3) {
		this.tipologiaFlg3 = tipologiaFlg3;
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
	public String getCompenzazioneEuro() {
		return compenzazioneEuro;
	}
	public void setCompenzazioneEuro(String compenzazioneEuro) {
		this.compenzazioneEuro = compenzazioneEuro;
	}	
	public String getCompenzazioneEuroReale() {
		return compenzazioneEuroReale;
	}
	public String getNoteCompenzazioneEuroReale() {
		return noteCompenzazioneEuroReale;
	}
	public void setCompenzazioneEuroReale(String compenzazioneEuroReale) {
		this.compenzazioneEuroReale = compenzazioneEuroReale;
	}
	public void setNoteCompenzazioneEuroReale(String noteCompenzazioneEuroReale) {
		this.noteCompenzazioneEuroReale = noteCompenzazioneEuroReale;
	}
	public boolean isDichProprietario() {
		return dichProprietario;
	}
	public void setDichProprietario(boolean dichProprietario) {
		this.dichProprietario = dichProprietario;
	}
	public boolean isDichDissenso() {
		return dichDissenso;
	}
	public void setDichDissenso(boolean dichDissenso) {
		this.dichDissenso = dichDissenso;
	}
	public boolean isDichAutPaesaggistica() {
		return dichAutPaesaggistica;
	}
	public void setDichAutPaesaggistica(boolean dichAutPaesaggistica) {
		this.dichAutPaesaggistica = dichAutPaesaggistica;
	}
	public LocalDate getDichDataPaesaggistica() {
		return dichDataPaesaggistica;
	}
	public void setDichDataPaesaggistica(LocalDate dichDataPaesaggistica) {
		this.dichDataPaesaggistica = dichDataPaesaggistica;
	}
	public String getDichNrPaesaggistica() {
		return dichNrPaesaggistica;
	}
	public void setDichNrPaesaggistica(String dichNrPaesaggistica) {
		this.dichNrPaesaggistica = dichNrPaesaggistica;
	}
	public String getDichEntePaesaggistica() {
		return dichEntePaesaggistica;
	}
	public void setDichEntePaesaggistica(String dichEntePaesaggistica) {
		this.dichEntePaesaggistica = dichEntePaesaggistica;
	}
	public boolean isDichAutVidro() {
		return dichAutVidro;
	}
	public void setDichAutVidro(boolean dichAutVidro) {
		this.dichAutVidro = dichAutVidro;
	}
	public LocalDate getDichDataVidro() {
		return dichDataVidro;
	}
	public void setDichDataVidro(LocalDate dichDataVidro) {
		this.dichDataVidro = dichDataVidro;
	}
	public String getDichNrVidro() {
		return dichNrVidro;
	}
	public void setDichNrVidro(String dichNrVidro) {
		this.dichNrVidro = dichNrVidro;
	}
	public String getDichEnteVidro() {
		return dichEnteVidro;
	}
	public void setDichEnteVidro(String dichEnteVidro) {
		this.dichEnteVidro = dichEnteVidro;
	}
	public boolean isDichAutIncidenza() {
		return dichAutIncidenza;
	}
	public void setDichAutIncidenza(boolean dichAutIncidenza) {
		this.dichAutIncidenza = dichAutIncidenza;
	}
	public LocalDate getDichDataIncidenza() {
		return dichDataIncidenza;
	}
	public void setDichDataIncidenza(LocalDate dichDataIncidenza) {
		this.dichDataIncidenza = dichDataIncidenza;
	}
	public String getDichNrIncidenza() {
		return dichNrIncidenza;
	}
	public void setDichNrIncidenza(String dichNrIncidenza) {
		this.dichNrIncidenza = dichNrIncidenza;
	}
	public String getDichEnteIncidenza() {
		return dichEnteIncidenza;
	}
	public void setDichEnteIncidenza(String dichEnteIncidenza) {
		this.dichEnteIncidenza = dichEnteIncidenza;
	}
	public String getDichAltriPareri() {
		return dichAltriPareri;
	}
	public void setDichAltriPareri(String dichAltriPareri) {
		this.dichAltriPareri = dichAltriPareri;
	}
	public List<DichPropCatasto> getPropCatasti() {
		return propCatasti;
	}
	public void setPropCatasti(List<DichPropCatasto> propCatasti) {
		this.propCatasti = propCatasti;
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
	public List<IstanzaCompensazione> getIstanzeCompensazione() {
		return istanzeCompensazione;
	}
	public void setIstanzeCompensazione(List<IstanzaCompensazione> istanzaCompensazione) {
		this.istanzeCompensazione = istanzaCompensazione;
	}
	public int getNrIstanza() {
		return nrIstanza;
	}
	public void setNrIstanza(int nrIstanza) {
		this.nrIstanza = nrIstanza;
	}
	@Override
	public String toString() {
		return "GeneratedFileTrasfParameters [tipoAllegato=" + tipoAllegato + ", idIntervento=" + idIntervento
				+", nrIstanza" + nrIstanza
				+ ", fkConfigUtente=" + fkConfigUtente + ", tipoTitolarita=" + tipoTitolarita + ", richCognome="
				+ richCognome + ", richNome=" + richNome + ", richRagSociale=" + richRagSociale + ", richCodiceFiscale="
				+ richCodiceFiscale + ", richPartitaIva=" + richPartitaIva + ", richIndirizzo=" + richIndirizzo
				+ ", richCivico=" + richCivico + ", richProvincia=" + richProvincia + ", richCap=" + richCap
				+ ", richComune=" + richComune + ", richTelefonico=" + richTelefonico + ", richEmail=" + richEmail
				+ ", richPec=" + richPec + ", flgCompensazione=" + flgCompensazione + ", flgArt7a=" + flgArt7a
				+ ", flgArt7b=" + flgArt7b + ", flgArt7c=" + flgArt7c + ", flgArt7d=" + flgArt7d + ", flgArt7A21="
				+ flgArt7A21 + ", flgArt7B21=" + flgArt7B21 + ", flgArt7C21=" + flgArt7C21 + ", flgArt7D21="
				+ flgArt7D21 + ", flgArt7Dter21=" + flgArt7Dter21 + ", flgArt7Dquater21=" + flgArt7Dquater21
				+ ", flgArt7Dquinquies21=" + flgArt7Dquinquies21 + ", formaGovernoFlg1=" + formaGovernoFlg1
				+ ", formaGovernoFlg2=" + formaGovernoFlg2 + ", categForestFlg1=" + categForestFlg1
				+ ", categForestFlg2=" + categForestFlg2 + ", categForestFlg3=" + categForestFlg3 + ", ubicazioneFlg1="
				+ ubicazioneFlg1 + ", ubicazioneFlg2=" + ubicazioneFlg2 + ", ubicazioneFlg3=" + ubicazioneFlg3
				+ ", destVincFlg1=" + destVincFlg1 + ", destVincFlg2=" + destVincFlg2 + ", destVincFlg3=" + destVincFlg3
				+ ", tipologiaFlg1=" + tipologiaFlg1 + ", tipologiaFlg2=" + tipologiaFlg2 + ", tipologiaFlg3="
				+ tipologiaFlg3 + ", profCognome=" + profCognome + ", profNome=" + profNome + ", profCodiceFiscale="
				+ profCodiceFiscale + ", profProvinciaOrdine=" + profProvinciaOrdine + ", profNIscrizione="
				+ profNIscrizione + ", profTelefonico=" + profTelefonico + ", profPec=" + profPec
				+ ", compenzazioneEuro=" + compenzazioneEuro + ", dichProprietario=" + dichProprietario
				+ ", dichDissenso=" + dichDissenso + ", dichAutPaesaggistica=" + dichAutPaesaggistica
				+ ", dichDataPaesaggistica=" + dichDataPaesaggistica + ", dichNrPaesaggistica=" + dichNrPaesaggistica
				+ ", dichEntePaesaggistica=" + dichEntePaesaggistica + ", dichAutVidro=" + dichAutVidro
				+ ", dichDataVidro=" + dichDataVidro + ", dichNrVidro=" + dichNrVidro + ", dichEnteVidro="
				+ dichEnteVidro + ", dichAutIncidenza=" + dichAutIncidenza + ", dichDataIncidenza=" + dichDataIncidenza
				+ ", dichNrIncidenza=" + dichNrIncidenza + ", dichEnteIncidenza=" + dichEnteIncidenza
				+ ", dichAltriPareri=" + dichAltriPareri + ", propCatasti=" + propCatasti + ", superficieInteressata="
				+ superficieInteressata + ", regionaleSoggetto=" + regionaleSoggetto + ", istanzeCompensazione="
				+ istanzeCompensazione + "]";
	}
	
	
}
