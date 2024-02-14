/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.StringJoiner;

public class TagliXls {

	private Integer nrIstanzaForestale;
	private String annoIstanza;
	private String tipoIstanza;
	private String statoIstanza;
	private String stringComune;
	private String categIntervento;
	private String proprietaBosco;
	private String annataSilvana;
	private Date dataInizio;
	private String statoIntervento;
	private String interventoCompensativo;
	private String tipoRichiedente;
	private String richiedente;
	private String nrAlboForestale;
	private String utilizzatore;
	private String tecnicoForestale;
	private BigDecimal superficieInteressataHa;
	private String ricadenzaAapp;
	private String ricadenzaRn2000;
	private String ricadenzaPopseme;
	private String categorieForestali;
	private String pfa;
	private String fasciaAltimetrica;
	private String descrizioneIntervento;
	private String governoPrincipale;
	private String tipoInterventoPrincipale;
	private BigDecimal superficieInt1Ha;
	private String governoSecondario;
	private String tipoInterventoSecondario;
	private BigDecimal superficieInt2Ha;
	private String stringUsoviab;
	private String noteEsbosco;
	private BigDecimal volumeSpecie;
	private String descrUdm;
	private BigDecimal volumeRamagliaM3;
	private Date dataInserimento;
	private Date dataInvio;
	private String nrProtocollo;
	private Date dataProtocollo;
	private Date dataVerifica;
	private String nrDeterminaAut;
	private Date dataTermineAut;
	private String compilatore;


	public Integer getNrIstanzaForestale() {
		return nrIstanzaForestale;
	}

	public void setNrIstanzaForestale(Integer nrIstanzaForestale) {
		this.nrIstanzaForestale = nrIstanzaForestale;
	}

	public String getAnnoIstanza() {
		return annoIstanza;
	}

	public void setAnnoIstanza(String annoIstanza) {
		this.annoIstanza = annoIstanza;
	}

	public String getTipoIstanza() {
		return tipoIstanza;
	}

	public void setTipoIstanza(String tipoIstanza) {
		this.tipoIstanza = tipoIstanza;
	}

	public String getStatoIstanza() {
		return statoIstanza;
	}

	public void setStatoIstanza(String statoIstanza) {
		this.statoIstanza = statoIstanza;
	}

	public String getStringComune() {
		return stringComune;
	}

	public void setStringComune(String stringComune) {
		this.stringComune = stringComune;
	}

	public String getCategIntervento() {
		return categIntervento;
	}

	public void setCategIntervento(String categIntervento) {
		this.categIntervento = categIntervento;
	}

	public String getProprietaBosco() {
		return proprietaBosco;
	}

	public void setProprietaBosco(String proprietaBosco) {
		this.proprietaBosco = proprietaBosco;
	}

	public String getAnnataSilvana() {
		return annataSilvana;
	}

	public void setAnnataSilvana(String annataSilvana) {
		this.annataSilvana = annataSilvana;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public String getStatoIntervento() {
		return statoIntervento;
	}

	public void setStatoIntervento(String statoIntervento) {
		this.statoIntervento = statoIntervento;
	}

	public String getInterventoCompensativo() {
		return interventoCompensativo;
	}

	public void setInterventoCompensativo(String interventoCompensativo) {
		this.interventoCompensativo = interventoCompensativo;
	}

	public String getTipoRichiedente() {
		return tipoRichiedente;
	}

	public void setTipoRichiedente(String tipoRichiedente) {
		this.tipoRichiedente = tipoRichiedente;
	}

	public String getRichiedente() {
		return richiedente;
	}

	public void setRichiedente(String richiedente) {
		this.richiedente = richiedente;
	}

	public String getNrAlboForestale() {
		return nrAlboForestale;
	}

	public void setNrAlboForestale(String nrAlboForestale) {
		this.nrAlboForestale = nrAlboForestale;
	}

	public String getUtilizzatore() {
		return utilizzatore;
	}

	public void setUtilizzatore(String utilizzatore) {
		this.utilizzatore = utilizzatore;
	}

	public String getTecnicoForestale() {
		return tecnicoForestale;
	}

	public void setTecnicoForestale(String tecnicoForestale) {
		this.tecnicoForestale = tecnicoForestale;
	}

	public BigDecimal getSuperficieInteressataHa() {
		return superficieInteressataHa;
	}

	public void setSuperficieInteressataHa(BigDecimal superficieInteressataHa) {
		this.superficieInteressataHa = superficieInteressataHa;
	}

	public String getRicadenzaAapp() {
		return ricadenzaAapp;
	}

	public void setRicadenzaAapp(String ricadenzaAapp) {
		this.ricadenzaAapp = ricadenzaAapp;
	}

	public String getRicadenzaRn2000() {
		return ricadenzaRn2000;
	}

	public void setRicadenzaRn2000(String ricadenzaRn2000) {
		this.ricadenzaRn2000 = ricadenzaRn2000;
	}

	public String getRicadenzaPopseme() {
		return ricadenzaPopseme;
	}

	public void setRicadenzaPopseme(String ricadenzaPopseme) {
		this.ricadenzaPopseme = ricadenzaPopseme;
	}

	public String getCategorieForestali() {
		return categorieForestali;
	}

	public void setCategorieForestali(String categorieForestali) {
		this.categorieForestali = categorieForestali;
	}

	public String getPfa() {
		return pfa;
	}

	public void setPfa(String pfa) {
		this.pfa = pfa;
	}

	public String getFasciaAltimetrica() {
		return fasciaAltimetrica;
	}

	public void setFasciaAltimetrica(String fasciaAltimetrica) {
		this.fasciaAltimetrica = fasciaAltimetrica;
	}

	public String getDescrizioneIntervento() {
		return descrizioneIntervento;
	}

	public void setDescrizioneIntervento(String descrizioneIntervento) {
		this.descrizioneIntervento = descrizioneIntervento;
	}

	public String getGovernoPrincipale() {
		return governoPrincipale;
	}

	public void setGovernoPrincipale(String governoPrincipale) {
		this.governoPrincipale = governoPrincipale;
	}

	public String getTipoInterventoPrincipale() {
		return tipoInterventoPrincipale;
	}

	public void setTipoInterventoPrincipale(String tipoInterventoPrincipale) {
		this.tipoInterventoPrincipale = tipoInterventoPrincipale;
	}

	public BigDecimal getSuperficieInt1Ha() {
		return superficieInt1Ha;
	}

	public void setSuperficieInt1Ha(BigDecimal superficieInt1Ha) {
		this.superficieInt1Ha = superficieInt1Ha;
	}

	public String getGovernoSecondario() {
		return governoSecondario;
	}

	public void setGovernoSecondario(String governoSecondario) {
		this.governoSecondario = governoSecondario;
	}

	public String getTipoInterventoSecondario() {
		return tipoInterventoSecondario;
	}

	public void setTipoInterventoSecondario(String tipoInterventoSecondario) {
		this.tipoInterventoSecondario = tipoInterventoSecondario;
	}

	public BigDecimal getSuperficieInt2Ha() {
		return superficieInt2Ha;
	}

	public void setSuperficieInt2Ha(BigDecimal superficieInt2Ha) {
		this.superficieInt2Ha = superficieInt2Ha;
	}

	public String getStringUsoviab() {
		return stringUsoviab;
	}

	public void setStringUsoviab(String stringUsoviab) {
		this.stringUsoviab = stringUsoviab;
	}

	public String getNoteEsbosco() {
		return noteEsbosco;
	}

	public void setNoteEsbosco(String noteEsbosco) {
		this.noteEsbosco = noteEsbosco;
	}

	public BigDecimal getVolumeSpecie() {
		return volumeSpecie;
	}

	public void setVolumeSpecie(BigDecimal volumeSpecie) {
		this.volumeSpecie = volumeSpecie;
	}

	public String getDescrUdm() {
		return descrUdm;
	}

	public void setDescrUdm(String descrUdm) {
		this.descrUdm = descrUdm;
	}

	public BigDecimal getVolumeRamagliaM3() {
		return volumeRamagliaM3;
	}

	public void setVolumeRamagliaM3(BigDecimal volumeRamagliaM3) {
		this.volumeRamagliaM3 = volumeRamagliaM3;
	}

	public Date getDataInserimento() {
		return dataInserimento;
	}

	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public Date getDataInvio() {
		return dataInvio;
	}

	public void setDataInvio(Date dataInvio) {
		this.dataInvio = dataInvio;
	}

	public String getNrProtocollo() {
		return nrProtocollo;
	}

	public void setNrProtocollo(String nrProtocollo) {
		this.nrProtocollo = nrProtocollo;
	}

	public Date getDataProtocollo() {
		return dataProtocollo;
	}

	public void setDataProtocollo(Date dataProtocollo) {
		this.dataProtocollo = dataProtocollo;
	}

	public Date getDataVerifica() {
		return dataVerifica;
	}

	public void setDataVerifica(Date dataVerifica) {
		this.dataVerifica = dataVerifica;
	}

	public String getNrDeterminaAut() {
		return nrDeterminaAut;
	}

	public void setNrDeterminaAut(String nrDeterminaAut) {
		this.nrDeterminaAut = nrDeterminaAut;
	}

	public Date getDataTermineAut() {
		return dataTermineAut;
	}

	public void setDataTermineAut(Date dataTermineAut) {
		this.dataTermineAut = dataTermineAut;
	}

	public String getCompilatore() {
		return compilatore;
	}

	public void setCompilatore(String compilatore) {
		this.compilatore = compilatore;
	}

	@Override
	public String toString() {
		return new StringJoiner("\n ", TagliXls.class.getSimpleName() + "[", "]")
				.add("nrIstanzaForestale=" + nrIstanzaForestale)
				.add("annoIstanza='" + annoIstanza + "'")
				.add("tipoIstanza='" + tipoIstanza + "'")
				.add("statoIstanza='" + statoIstanza + "'")
				.add("stringComune='" + stringComune + "'")
				.add("categIntervento='" + categIntervento + "'")
				.add("proprietaBosco='" + proprietaBosco + "'")
				.add("annataSilvana='" + annataSilvana + "'")
				.add("dataInizio=" + dataInizio)
				.add("statoIntervento='" + statoIntervento + "'")
				.add("interventoCompensativo='" + interventoCompensativo + "'")
				.add("tipoRichiedente='" + tipoRichiedente + "'")
				.add("richiedente='" + richiedente + "'")
				.add("nrAlboForestale='" + nrAlboForestale + "'")
				.add("utilizzatore='" + utilizzatore + "'")
				.add("tecnicoForestale='" + tecnicoForestale + "'")
				.add("superficieInteressataHa=" + superficieInteressataHa)
				.add("ricadenzaAapp='" + ricadenzaAapp + "'")
				.add("ricadenzaRn2000='" + ricadenzaRn2000 + "'")
				.add("ricadenzaPopseme='" + ricadenzaPopseme + "'")
				.add("categorieForestali='" + categorieForestali + "'")
				.add("pfa='" + pfa + "'")
				.add("fasciaAltimetrica='" + fasciaAltimetrica + "'")
				.add("descrizioneIntervento='" + descrizioneIntervento + "'")
				.add("governoPrincipale='" + governoPrincipale + "'")
				.add("tipoInterventoPrincipale='" + tipoInterventoPrincipale + "'")
				.add("superficieInt1Ha=" + superficieInt1Ha)
				.add("governoSecondario='" + governoSecondario + "'")
				.add("tipoInterventoSecondario='" + tipoInterventoSecondario + "'")
				.add("superficieInt2Ha=" + superficieInt2Ha)
				.add("stringUsoviab='" + stringUsoviab + "'")
				.add("noteEsbosco='" + noteEsbosco + "'")
				.add("volumeSpecie=" + volumeSpecie)
				.add("descrUdm='" + descrUdm + "'")
				.add("volumeRamagliaM3=" + volumeRamagliaM3)
				.add("dataInserimento=" + dataInserimento)
				.add("dataInvio=" + dataInvio)
				.add("nrProtocollo='" + nrProtocollo + "'")
				.add("dataProtocollo=" + dataProtocollo)
				.add("dataVerifica=" + dataVerifica)
				.add("nrDeterminaAut='" + nrDeterminaAut + "'")
				.add("dataTermineAut=" + dataTermineAut)
				.add("compilatore='" + compilatore + "'")
				.toString();
	}
}
