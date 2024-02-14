/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;
import java.util.Date;

public class TrasformazioniXls {
	

	private Integer idIstanza;
	private String anno;
	private String statoIstanza;
	private String richiedente;
	private String comune;
	private String ricadenzaAapp;
	private String ricadenzaRn2000;
	private String ricadenzaPopSeme;
	private String ricadenzaVincIdro;
	private BigDecimal supInteressataHa;
	private String governoPrevalente;
	private String catForPrevalente;
	private String ubicazionePrevalente;
	private String vincoloPaesaggistico;
	private String vincoloIdrogeologico;
	private String vincoloAreaProtteta;
	private String tipoTrasf1;
	private String tipoTrasf2;
	private String tipoTrasf3;
	private String compensazione;
	private String flagArt7a;
	private String flagArt7b;
	private String flagArt7c;
	private String flagArt7d;	
	private String flagArt7A21;
	private String flagArt7B21;
	private String flagArt7C21;
	private String flagArt7D21;
	private String flagArt7Dter21;
	private String flagArt7Dquater21;
	private String flagArt7Dquinquies21;	
	private BigDecimal compensazioneEuro;
	private BigDecimal compensazioneEuroReale;
	private String compensazioneNote;
	private String tecnicoForestale;
	private String dichiarazProprieta;
	private String dichiarazDissensi;
	private String autPaesaggistica;
	private String numAutorizPaesagg;
	private Date dataAutorizPaesagg;
	private String enteAutorizPaesagg;
	private String autorizVincoloIdro;
	private String numAutorizVincoloIdro;
	private Date dataAutorizVincoloIdro;
	private String enteAutorizVincoloIdro;
	private String valIncidenzaNr2000;
	private String numAutValIncidenzaNr2000;
	private Date dataAutValIncidenzaNr2000;
	private String enteAutValIncidenzaNr2000;
	private String altriPareri;
	private String flagCauzioneCf;
	private String flagVersamenteCm;
	private String noteDichiarazione;
	private Date dataInserimento;
	private Date dataInvio;
	private String nrProtocollo;
	private Date dataProtocollo;
	private String utenteCompilatore;
	private Date dataVerifica;
	private String motiviazioneRifiuto;
	private String utenteGestore;
	
	public Integer getIdIstanza() {
		return idIstanza;
	}
	public String getAnno() {
		return anno;
	}
	public String getStatoIstanza() {
		return statoIstanza;
	}
	public String getRichiedente() {
		return richiedente;
	}
	public String getComune() {
		return comune;
	}
	public String getRicadenzaAapp() {
		return ricadenzaAapp;
	}
	public String getRicadenzaRn2000() {
		return ricadenzaRn2000;
	}
	public String getRicadenzaPopSeme() {
		return ricadenzaPopSeme;
	}
	public String getRicadenzaVincIdro() {
		return ricadenzaVincIdro;
	}
	public BigDecimal getSupInteressataHa() {
		return supInteressataHa;
	}
	public String getGovernoPrevalente() {
		return governoPrevalente;
	}
	public String getCatForPrevalente() {
		return catForPrevalente;
	}
	public String getUbicazionePrevalente() {
		return ubicazionePrevalente;
	}
	public String getVincoloPaesaggistico() {
		return vincoloPaesaggistico;
	}
	public String getVincoloIdrogeologico() {
		return vincoloIdrogeologico;
	}
	public String getVincoloAreaProtteta() {
		return vincoloAreaProtteta;
	}
	public String getTipoTrasf1() {
		return tipoTrasf1;
	}
	public String getTipoTrasf2() {
		return tipoTrasf2;
	}
	public String getTipoTrasf3() {
		return tipoTrasf3;
	}
	public String getCompensazione() {
		return compensazione;
	}
	public String getFlagArt7a() {
		return flagArt7a;
	}
	public String getFlagArt7b() {
		return flagArt7b;
	}
	public String getFlagArt7c() {
		return flagArt7c;
	}
	public String getFlagArt7d() {
		return flagArt7d;
	}	
	public String getFlagArt7A21() {
		return flagArt7A21;
	}
	public String getFlagArt7B21() {
		return flagArt7B21;
	}
	public String getFlagArt7C21() {
		return flagArt7C21;
	}
	public String getFlagArt7D21() {
		return flagArt7D21;
	}
	public String getFlagArt7Dter21() {
		return flagArt7Dter21;
	}
	public String getFlagArt7Dquater21() {
		return flagArt7Dquater21;
	}
	public String getFlagArt7Dquinquies21() {
		return flagArt7Dquinquies21;
	}
	public void setFlagArt7A21(String flagArt7A21) {
		this.flagArt7A21 = flagArt7A21;
	}
	public void setFlagArt7B21(String flagArt7B21) {
		this.flagArt7B21 = flagArt7B21;
	}
	public void setFlagArt7C21(String flagArt7C21) {
		this.flagArt7C21 = flagArt7C21;
	}
	public void setFlagArt7D21(String flagArt7D21) {
		this.flagArt7D21 = flagArt7D21;
	}
	public void setFlagArt7Dter21(String flagArt7Dter21) {
		this.flagArt7Dter21 = flagArt7Dter21;
	}
	public void setFlagArt7Dquater21(String flagArt7Dquater21) {
		this.flagArt7Dquater21 = flagArt7Dquater21;
	}
	public void setFlagArt7Dquinquies21(String flagArt7Dquinquies21) {
		this.flagArt7Dquinquies21 = flagArt7Dquinquies21;
	}
	public BigDecimal getCompensazioneEuro() {
		return compensazioneEuro;
	}	
	public BigDecimal getCompensazioneEuroReale() {
		return compensazioneEuroReale;
	}
	public String getCompensazioneNote() {
		return compensazioneNote;
	}
	public void setCompensazioneEuroReale(BigDecimal compensazioneEuroReale) {
		this.compensazioneEuroReale = compensazioneEuroReale;
	}
	public void setCompensazioneNote(String compensazioneNote) {
		this.compensazioneNote = compensazioneNote;
	}
	public String getTecnicoForestale() {
		return tecnicoForestale;
	}
	public String getDichiarazProprieta() {
		return dichiarazProprieta;
	}
	public String getDichiarazDissensi() {
		return dichiarazDissensi;
	}
	public String getAutPaesaggistica() {
		return autPaesaggistica;
	}
	public String getNumAutorizPaesagg() {
		return numAutorizPaesagg;
	}
	public Date getDataAutorizPaesagg() {
		return dataAutorizPaesagg;
	}
	public String getEnteAutorizPaesagg() {
		return enteAutorizPaesagg;
	}
	public String getAutorizVincoloIdro() {
		return autorizVincoloIdro;
	}
	public String getNumAutorizVincoloIdro() {
		return numAutorizVincoloIdro;
	}
	public Date getDataAutorizVincoloIdro() {
		return dataAutorizVincoloIdro;
	}
	public String getEnteAutorizVincoloIdro() {
		return enteAutorizVincoloIdro;
	}
	public String getValIncidenzaNr2000() {
		return valIncidenzaNr2000;
	}
	public String getNumAutValIncidenzaNr2000() {
		return numAutValIncidenzaNr2000;
	}
	public Date getDataAutValIncidenzaNr2000() {
		return dataAutValIncidenzaNr2000;
	}
	public String getEnteAutValIncidenzaNr2000() {
		return enteAutValIncidenzaNr2000;
	}
	public String getAltriPareri() {
		return altriPareri;
	}
	public String getFlagCauzioneCf() {
		return flagCauzioneCf;
	}
	public String getFlagVersamenteCm() {
		return flagVersamenteCm;
	}
	public String getNoteDichiarazione() {
		return noteDichiarazione;
	}
	public Date getDataInserimento() {
		return dataInserimento;
	}
	public Date getDataInvio() {
		return dataInvio;
	}
	public String getNrProtocollo() {
		return nrProtocollo;
	}
	public Date getDataProtocollo() {
		return dataProtocollo;
	}
	public String getUtenteCompilatore() {
		return utenteCompilatore;
	}
	public Date getDataVerifica() {
		return dataVerifica;
	}
	public String getMotiviazioneRifiuto() {
		return motiviazioneRifiuto;
	}
	public String getUtenteGestore() {
		return utenteGestore;
	}
	public void setIdIstanza(Integer idIstanza) {
		this.idIstanza = idIstanza;
	}
	public void setAnno(String anno) {
		this.anno = anno;
	}
	public void setStatoIstanza(String statoIstanza) {
		this.statoIstanza = statoIstanza;
	}
	public void setRichiedente(String richiedente) {
		this.richiedente = richiedente;
	}
	public void setComune(String comune) {
		this.comune = comune;
	}
	public void setRicadenzaAapp(String ricadenzaAapp) {
		this.ricadenzaAapp = ricadenzaAapp;
	}
	public void setRicadenzaRn2000(String ricadenzaRn2000) {
		this.ricadenzaRn2000 = ricadenzaRn2000;
	}
	public void setRicadenzaPopSeme(String ricadenzaPopSeme) {
		this.ricadenzaPopSeme = ricadenzaPopSeme;
	}
	public void setRicadenzaVincIdro(String ricadenzaVincIdro) {
		this.ricadenzaVincIdro = ricadenzaVincIdro;
	}
	public void setSupInteressataHa(BigDecimal supInteressataHa) {
		this.supInteressataHa = supInteressataHa;
	}
	public void setGovernoPrevalente(String governoPrevalente) {
		this.governoPrevalente = governoPrevalente;
	}
	public void setCatForPrevalente(String catForPrevalente) {
		this.catForPrevalente = catForPrevalente;
	}
	public void setUbicazionePrevalente(String ubicazionePrevalente) {
		this.ubicazionePrevalente = ubicazionePrevalente;
	}
	public void setVincoloPaesaggistico(String vincoloPaesaggistico) {
		this.vincoloPaesaggistico = vincoloPaesaggistico;
	}
	public void setVincoloIdrogeologico(String vincoloIdrogeologico) {
		this.vincoloIdrogeologico = vincoloIdrogeologico;
	}
	public void setVincoloAreaProtteta(String vincoloAreaProtteta) {
		this.vincoloAreaProtteta = vincoloAreaProtteta;
	}
	public void setTipoTrasf1(String tipoTrasf1) {
		this.tipoTrasf1 = tipoTrasf1;
	}
	public void setTipoTrasf2(String tipoTrasf2) {
		this.tipoTrasf2 = tipoTrasf2;
	}
	public void setTipoTrasf3(String tipoTrasf3) {
		this.tipoTrasf3 = tipoTrasf3;
	}
	public void setCompensazione(String compensazione) {
		this.compensazione = compensazione;
	}
	public void setFlagArt7a(String flagArt7a) {
		this.flagArt7a = flagArt7a;
	}
	public void setFlagArt7b(String flagArt7b) {
		this.flagArt7b = flagArt7b;
	}
	public void setFlagArt7c(String flagArt7c) {
		this.flagArt7c = flagArt7c;
	}
	public void setFlagArt7d(String flagArt7d) {
		this.flagArt7d = flagArt7d;
	}
	public void setCompensazioneEuro(BigDecimal compensazioneEuro) {
		this.compensazioneEuro = compensazioneEuro;
	}
	public void setTecnicoForestale(String tecnicoForestale) {
		this.tecnicoForestale = tecnicoForestale;
	}
	public void setDichiarazProprieta(String dichiarazProprieta) {
		this.dichiarazProprieta = dichiarazProprieta;
	}
	public void setDichiarazDissensi(String dichiarazDissensi) {
		this.dichiarazDissensi = dichiarazDissensi;
	}
	public void setAutPaesaggistica(String autPaesaggistica) {
		this.autPaesaggistica = autPaesaggistica;
	}
	public void setNumAutorizPaesagg(String numAutorizPaesagg) {
		this.numAutorizPaesagg = numAutorizPaesagg;
	}
	public void setDataAutorizPaesagg(Date dataAutorizPaesagg) {
		this.dataAutorizPaesagg = dataAutorizPaesagg;
	}
	public void setEnteAutorizPaesagg(String enteAutorizPaesagg) {
		this.enteAutorizPaesagg = enteAutorizPaesagg;
	}
	public void setAutorizVincoloIdro(String autorizVincoloIdro) {
		this.autorizVincoloIdro = autorizVincoloIdro;
	}
	public void setNumAutorizVincoloIdro(String numAutorizVincoloIdro) {
		this.numAutorizVincoloIdro = numAutorizVincoloIdro;
	}
	public void setDataAutorizVincoloIdro(Date dataAutorizVincoloIdro) {
		this.dataAutorizVincoloIdro = dataAutorizVincoloIdro;
	}
	public void setEnteAutorizVincoloIdro(String enteAutorizVincoloIdro) {
		this.enteAutorizVincoloIdro = enteAutorizVincoloIdro;
	}
	public void setValIncidenzaNr2000(String valIncidenzaNr2000) {
		this.valIncidenzaNr2000 = valIncidenzaNr2000;
	}
	public void setNumAutValIncidenzaNr2000(String numAutValIncidenzaNr2000) {
		this.numAutValIncidenzaNr2000 = numAutValIncidenzaNr2000;
	}
	public void setDataAutValIncidenzaNr2000(Date dataAutValIncidenzaNr2000) {
		this.dataAutValIncidenzaNr2000 = dataAutValIncidenzaNr2000;
	}
	public void setEnteAutValIncidenzaNr2000(String enteAutValIncidenzaNr2000) {
		this.enteAutValIncidenzaNr2000 = enteAutValIncidenzaNr2000;
	}
	public void setAltriPareri(String altriPareri) {
		this.altriPareri = altriPareri;
	}
	public void setFlagCauzioneCf(String flagCauzioneCf) {
		this.flagCauzioneCf = flagCauzioneCf;
	}
	public void setFlagVersamenteCm(String flagVersamenteCm) {
		this.flagVersamenteCm = flagVersamenteCm;
	}
	public void setNoteDichiarazione(String noteDichiarazione) {
		this.noteDichiarazione = noteDichiarazione;
	}
	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	public void setDataInvio(Date dataInvio) {
		this.dataInvio = dataInvio;
	}
	public void setNrProtocollo(String nrProtocollo) {
		this.nrProtocollo = nrProtocollo;
	}
	public void setDataProtocollo(Date dataProtocollo) {
		this.dataProtocollo = dataProtocollo;
	}
	public void setUtenteCompilatore(String utenteCompilatore) {
		this.utenteCompilatore = utenteCompilatore;
	}
	public void setDataVerifica(Date dataVerifica) {
		this.dataVerifica = dataVerifica;
	}
	public void setMotiviazioneRifiuto(String motiviazioneRifiuto) {
		this.motiviazioneRifiuto = motiviazioneRifiuto;
	}
	public void setUtenteGestore(String utenteGestore) {
		this.utenteGestore = utenteGestore;
	}
	
}
