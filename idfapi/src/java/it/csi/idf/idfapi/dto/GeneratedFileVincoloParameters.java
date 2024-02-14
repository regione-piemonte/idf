/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import it.csi.idf.idfapi.util.TipoAllegatoEnum;
import it.csi.idf.idfapi.util.TipoTitolaritaEnum;

public class GeneratedFileVincoloParameters {
	private TipoAllegatoEnum tipoAllegato;
	private int nrIstanza;
	private int idIntervento; 
	private int idTipoIstanza;
	private int fkConfigUtente;
	private TipoTitolaritaEnum tipoTitolarita;
	private int richId;
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
	private String descTipoTntervAltro;
	private Double movimentiTerraMc;
	private Double movimentiTerraVincidroMc; 
	private Integer mesiIntervento;
	private boolean flgAreeDissesto;
	private boolean flgAreeEsondazione;
	private Integer flgCauzioneVi; 
	private String flgCompensazione;
	private Double cmBoscoEuro;
	private Double cmNoBoscoEuro; 	
	private boolean flgGovCeduo ; 	
	private boolean flgGovFustaia ; 	
	private boolean flgGovMisto ; 	
	private boolean flgGovAltro ; 	
	private boolean flgGovRobineti ; 	
	private boolean flgGovCastagneti ;	
	private boolean flgArt7a;
	private boolean flgArt7b;
	private boolean flgArt7c;
	private boolean flgArt7d;
	private boolean flgArt7dBis;
	private boolean flgArt9a;
	private boolean flgArt9b;
	private boolean flgArt9c;
	private boolean flgSpeseIstruttoria;
	private boolean flgEsenzioneMarcaBollo;
	private String nMarcaBollo;
	private boolean flgImporto;
	private boolean flgCopiaConforme;
	private boolean flgConfServizi;
	private boolean flgSuap;
	private String annotazioni;
	private boolean flgProprieta;
	private boolean flgDissensi;
	private Double cmSupboscHa;
	private Double cmSupnoboscHa;
	private String profCognome;
	private String profNome;
	private String profCodiceFiscale;
	private String profProvinciaOrdine;
	private String profNIscrizione;
	private String profTelefonico;
	private String profPec;
	private String compenzazioneEuro;
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
	private List<Integer> allegatiTypes;
	
	public TipoAllegatoEnum getTipoAllegato() {
		return tipoAllegato;
	}
	public int getIdIntervento() {
		return idIntervento;
	}
	public int getIdTipoIstanza() {
		return idTipoIstanza;
	}
	public int getFkConfigUtente() {
		return fkConfigUtente;
	}
	public TipoTitolaritaEnum getTipoTitolarita() {
		return tipoTitolarita;
	}
	public int getRichId() {
		return richId;
	}
	public String getRichCognome() {
		return richCognome;
	}
	public String getRichNome() {
		return richNome;
	}
	public String getRichRagSociale() {
		return richRagSociale;
	}
	public String getRichCodiceFiscale() {
		return richCodiceFiscale;
	}
	public String getRichPartitaIva() {
		return richPartitaIva;
	}
	public String getRichIndirizzo() {
		return richIndirizzo;
	}
	public String getRichCivico() {
		return richCivico;
	}
	public String getRichProvincia() {
		return richProvincia;
	}
	public String getRichCap() {
		return richCap;
	}
	public String getRichComune() {
		return richComune;
	}
	public String getRichTelefonico() {
		return richTelefonico;
	}
	public String getRichEmail() {
		return richEmail;
	}
	public String getRichPec() {
		return richPec;
	}
	public String getDescTipoTntervAltro() {
		return descTipoTntervAltro;
	}
	public Double getMovimentiTerraMc() {
		return movimentiTerraMc;
	}
	public Double getMovimentiTerraVincidroMc() {
		return movimentiTerraVincidroMc;
	}
	public Integer getMesiIntervento() {
		return mesiIntervento;
	}
	public boolean isFlgAreeDissesto() {
		return flgAreeDissesto;
	}
	public boolean isFlgAreeEsondazione() {
		return flgAreeEsondazione;
	}
	public Integer getFlgCauzioneVi() {
		return flgCauzioneVi;
	}
	public String getFlgCompensazione() {
		return flgCompensazione;
	}
	public Double getCmBoscoEuro() {
		return cmBoscoEuro;
	}
	public Double getCmNoBoscoEuro() {
		return cmNoBoscoEuro;
	}
	public boolean isFlgGovCeduo() {
		return flgGovCeduo;
	}
	public boolean isFlgGovFustaia() {
		return flgGovFustaia;
	}
	public boolean isFlgGovMisto() {
		return flgGovMisto;
	}
	public boolean isFlgGovAltro() {
		return flgGovAltro;
	}
	public boolean isFlgGovRobineti() {
		return flgGovRobineti;
	}
	public boolean isFlgGovCastagneti() {
		return flgGovCastagneti;
	}
	public boolean isFlgArt7a() {
		return flgArt7a;
	}
	public boolean isFlgArt7b() {
		return flgArt7b;
	}
	public boolean isFlgArt7c() {
		return flgArt7c;
	}
	public boolean isFlgArt7d() {
		return flgArt7d;
	}
	public boolean isFlgArt7dBis() {
		return flgArt7dBis;
	}
	public boolean isFlgArt9a() {
		return flgArt9a;
	}
	public boolean isFlgArt9b() {
		return flgArt9b;
	}
	public boolean isFlgArt9c() {
		return flgArt9c;
	}
	public boolean isFlgSpeseIstruttoria() {
		return flgSpeseIstruttoria;
	}
	public boolean isFlgEsenzioneMarcaBollo() {
		return flgEsenzioneMarcaBollo;
	}
	public String getnMarcaBollo() {
		return nMarcaBollo;
	}
	public boolean isFlgImporto() {
		return flgImporto;
	}
	public boolean isFlgCopiaConforme() {
		return flgCopiaConforme;
	}
	public boolean isFlgConfServizi() {
		return flgConfServizi;
	}
	public boolean isFlgSuap() {
		return flgSuap;
	}
	public String getAnnotazioni() {
		return annotazioni;
	}
	public boolean isFlgProprieta() {
		return flgProprieta;
	}
	public boolean isFlgDissensi() {
		return flgDissensi;
	}
	public Double getCmSupboscHa() {
		return cmSupboscHa;
	}
	public Double getCmSupnoboscHa() {
		return cmSupnoboscHa;
	}
	public String getProfCognome() {
		return profCognome;
	}
	public String getProfNome() {
		return profNome;
	}
	public String getProfCodiceFiscale() {
		return profCodiceFiscale;
	}
	public String getProfProvinciaOrdine() {
		return profProvinciaOrdine;
	}
	public String getProfNIscrizione() {
		return profNIscrizione;
	}
	public String getProfTelefonico() {
		return profTelefonico;
	}
	public String getProfPec() {
		return profPec;
	}
	public String getCompenzazioneEuro() {
		return compenzazioneEuro;
	}
	public boolean isDichProprietario() {
		return dichProprietario;
	}
	public boolean isDichDissenso() {
		return dichDissenso;
	}
	public boolean isDichAutPaesaggistica() {
		return dichAutPaesaggistica;
	}
	public LocalDate getDichDataPaesaggistica() {
		return dichDataPaesaggistica;
	}
	public String getDichNrPaesaggistica() {
		return dichNrPaesaggistica;
	}
	public String getDichEntePaesaggistica() {
		return dichEntePaesaggistica;
	}
	public boolean isDichAutVidro() {
		return dichAutVidro;
	}
	public LocalDate getDichDataVidro() {
		return dichDataVidro;
	}
	public String getDichNrVidro() {
		return dichNrVidro;
	}
	public String getDichEnteVidro() {
		return dichEnteVidro;
	}
	public boolean isDichAutIncidenza() {
		return dichAutIncidenza;
	}
	public LocalDate getDichDataIncidenza() {
		return dichDataIncidenza;
	}
	public String getDichNrIncidenza() {
		return dichNrIncidenza;
	}
	public String getDichEnteIncidenza() {
		return dichEnteIncidenza;
	}
	public String getDichAltriPareri() {
		return dichAltriPareri;
	}
	public List<DichPropCatasto> getPropCatasti() {
		return propCatasti;
	}
	public BigDecimal getSuperficieInteressata() {
		return superficieInteressata;
	}
	public String getRegionaleSoggetto() {
		return regionaleSoggetto;
	}
	public List<IstanzaCompensazione> getIstanzeCompensazione() {
		return istanzeCompensazione;
	}
	public List<Integer> getAllegatiTypes() {
		return allegatiTypes;
	}
	public void setTipoAllegato(TipoAllegatoEnum tipoAllegato) {
		this.tipoAllegato = tipoAllegato;
	}
	public void setIdIntervento(int idIntervento) {
		this.idIntervento = idIntervento;
	}
	public void setIdTipoIstanza(int idTipoIstanza) {
		this.idTipoIstanza = idTipoIstanza;
	}
	public void setFkConfigUtente(int fkConfigUtente) {
		this.fkConfigUtente = fkConfigUtente;
	}
	public void setTipoTitolarita(TipoTitolaritaEnum tipoTitolarita) {
		this.tipoTitolarita = tipoTitolarita;
	}
	public void setRichId(int richId) {
		this.richId = richId;
	}
	public void setRichCognome(String richCognome) {
		this.richCognome = richCognome;
	}
	public void setRichNome(String richNome) {
		this.richNome = richNome;
	}
	public void setRichRagSociale(String richRagSociale) {
		this.richRagSociale = richRagSociale;
	}
	public void setRichCodiceFiscale(String richCodiceFiscale) {
		this.richCodiceFiscale = richCodiceFiscale;
	}
	public void setRichPartitaIva(String richPartitaIva) {
		this.richPartitaIva = richPartitaIva;
	}
	public void setRichIndirizzo(String richIndirizzo) {
		this.richIndirizzo = richIndirizzo;
	}
	public void setRichCivico(String richCivico) {
		this.richCivico = richCivico;
	}
	public void setRichProvincia(String richProvincia) {
		this.richProvincia = richProvincia;
	}
	public void setRichCap(String richCap) {
		this.richCap = richCap;
	}
	public void setRichComune(String richComune) {
		this.richComune = richComune;
	}
	public void setRichTelefonico(String richTelefonico) {
		this.richTelefonico = richTelefonico;
	}
	public void setRichEmail(String richEmail) {
		this.richEmail = richEmail;
	}
	public void setRichPec(String richPec) {
		this.richPec = richPec;
	}
	public void setDescTipoTntervAltro(String descTipoTntervAltro) {
		this.descTipoTntervAltro = descTipoTntervAltro;
	}
	public void setMovimentiTerraMc(Double movimentiTerraMc) {
		this.movimentiTerraMc = movimentiTerraMc;
	}
	public void setMovimentiTerraVincidroMc(Double movimentiTerraVincidroMc) {
		this.movimentiTerraVincidroMc = movimentiTerraVincidroMc;
	}
	public void setMesiIntervento(Integer mesiIntervento) {
		this.mesiIntervento = mesiIntervento;
	}
	public void setFlgAreeDissesto(boolean flgAreeDissesto) {
		this.flgAreeDissesto = flgAreeDissesto;
	}
	public void setFlgAreeEsondazione(boolean flgAreeEsondazione) {
		this.flgAreeEsondazione = flgAreeEsondazione;
	}
	public void setFlgCauzioneVi(Integer flgCauzioneVi) {
		this.flgCauzioneVi = flgCauzioneVi;
	}
	public void setFlgCompensazione(String flgCompensazione) {
		this.flgCompensazione = flgCompensazione;
	}
	public void setCmBoscoEuro(Double cmBoscoEuro) {
		this.cmBoscoEuro = cmBoscoEuro;
	}
	public void setCmNoBoscoEuro(Double cmNoBoscoEuro) {
		this.cmNoBoscoEuro = cmNoBoscoEuro;
	}
	public void setFlgGovCeduo(boolean flgGovCeduo) {
		this.flgGovCeduo = flgGovCeduo;
	}
	public void setFlgGovFustaia(boolean flgGovFustaia) {
		this.flgGovFustaia = flgGovFustaia;
	}
	public void setFlgGovMisto(boolean flgGovMisto) {
		this.flgGovMisto = flgGovMisto;
	}
	public void setFlgGovAltro(boolean flgGovAltro) {
		this.flgGovAltro = flgGovAltro;
	}
	public void setFlgGovRobineti(boolean flgGovRobineti) {
		this.flgGovRobineti = flgGovRobineti;
	}
	public void setFlgGovCastagneti(boolean flgGovCastagneti) {
		this.flgGovCastagneti = flgGovCastagneti;
	}
	public void setFlgArt7a(boolean flgArt7a) {
		this.flgArt7a = flgArt7a;
	}
	public void setFlgArt7b(boolean flgArt7b) {
		this.flgArt7b = flgArt7b;
	}
	public void setFlgArt7c(boolean flgArt7c) {
		this.flgArt7c = flgArt7c;
	}
	public void setFlgArt7d(boolean flgArt7d) {
		this.flgArt7d = flgArt7d;
	}
	public void setFlgArt7dBis(boolean flgArt7dBis) {
		this.flgArt7dBis = flgArt7dBis;
	}
	public void setFlgArt9a(boolean flgArt9a) {
		this.flgArt9a = flgArt9a;
	}
	public void setFlgArt9b(boolean flgArt9b) {
		this.flgArt9b = flgArt9b;
	}
	public void setFlgArt9c(boolean flgArt9c) {
		this.flgArt9c = flgArt9c;
	}
	public void setFlgSpeseIstruttoria(boolean flgSpeseIstruttoria) {
		this.flgSpeseIstruttoria = flgSpeseIstruttoria;
	}
	public void setFlgEsenzioneMarcaBollo(boolean flgEsenzioneMarcaBollo) {
		this.flgEsenzioneMarcaBollo = flgEsenzioneMarcaBollo;
	}
	public void setnMarcaBollo(String nMarcaBollo) {
		this.nMarcaBollo = nMarcaBollo;
	}
	public void setFlgImporto(boolean flgImporto) {
		this.flgImporto = flgImporto;
	}
	public void setFlgCopiaConforme(boolean flgCopiaConforme) {
		this.flgCopiaConforme = flgCopiaConforme;
	}
	public void setFlgConfServizi(boolean flgConfServizi) {
		this.flgConfServizi = flgConfServizi;
	}
	public void setFlgSuap(boolean flgSuap) {
		this.flgSuap = flgSuap;
	}
	public void setAnnotazioni(String annotazioni) {
		this.annotazioni = annotazioni;
	}
	public void setFlgProprieta(boolean flgProprieta) {
		this.flgProprieta = flgProprieta;
	}
	public void setFlgDissensi(boolean flgDissensi) {
		this.flgDissensi = flgDissensi;
	}
	public void setCmSupboscHa(Double cmSupboscHa) {
		this.cmSupboscHa = cmSupboscHa;
	}
	public void setCmSupnoboscHa(Double cmSupnoboscHa) {
		this.cmSupnoboscHa = cmSupnoboscHa;
	}
	public void setProfCognome(String profCognome) {
		this.profCognome = profCognome;
	}
	public void setProfNome(String profNome) {
		this.profNome = profNome;
	}
	public void setProfCodiceFiscale(String profCodiceFiscale) {
		this.profCodiceFiscale = profCodiceFiscale;
	}
	public void setProfProvinciaOrdine(String profProvinciaOrdine) {
		this.profProvinciaOrdine = profProvinciaOrdine;
	}
	public void setProfNIscrizione(String profNIscrizione) {
		this.profNIscrizione = profNIscrizione;
	}
	public void setProfTelefonico(String profTelefonico) {
		this.profTelefonico = profTelefonico;
	}
	public void setProfPec(String profPec) {
		this.profPec = profPec;
	}
	public void setCompenzazioneEuro(String compenzazioneEuro) {
		this.compenzazioneEuro = compenzazioneEuro;
	}
	public void setDichProprietario(boolean dichProprietario) {
		this.dichProprietario = dichProprietario;
	}
	public void setDichDissenso(boolean dichDissenso) {
		this.dichDissenso = dichDissenso;
	}
	public void setDichAutPaesaggistica(boolean dichAutPaesaggistica) {
		this.dichAutPaesaggistica = dichAutPaesaggistica;
	}
	public void setDichDataPaesaggistica(LocalDate dichDataPaesaggistica) {
		this.dichDataPaesaggistica = dichDataPaesaggistica;
	}
	public void setDichNrPaesaggistica(String dichNrPaesaggistica) {
		this.dichNrPaesaggistica = dichNrPaesaggistica;
	}
	public void setDichEntePaesaggistica(String dichEntePaesaggistica) {
		this.dichEntePaesaggistica = dichEntePaesaggistica;
	}
	public void setDichAutVidro(boolean dichAutVidro) {
		this.dichAutVidro = dichAutVidro;
	}
	public void setDichDataVidro(LocalDate dichDataVidro) {
		this.dichDataVidro = dichDataVidro;
	}
	public void setDichNrVidro(String dichNrVidro) {
		this.dichNrVidro = dichNrVidro;
	}
	public void setDichEnteVidro(String dichEnteVidro) {
		this.dichEnteVidro = dichEnteVidro;
	}
	public void setDichAutIncidenza(boolean dichAutIncidenza) {
		this.dichAutIncidenza = dichAutIncidenza;
	}
	public void setDichDataIncidenza(LocalDate dichDataIncidenza) {
		this.dichDataIncidenza = dichDataIncidenza;
	}
	public void setDichNrIncidenza(String dichNrIncidenza) {
		this.dichNrIncidenza = dichNrIncidenza;
	}
	public void setDichEnteIncidenza(String dichEnteIncidenza) {
		this.dichEnteIncidenza = dichEnteIncidenza;
	}
	public void setDichAltriPareri(String dichAltriPareri) {
		this.dichAltriPareri = dichAltriPareri;
	}
	public void setPropCatasti(List<DichPropCatasto> propCatasti) {
		this.propCatasti = propCatasti;
	}
	public void setSuperficieInteressata(BigDecimal superficieInteressata) {
		this.superficieInteressata = superficieInteressata;
	}
	public void setRegionaleSoggetto(String regionaleSoggetto) {
		this.regionaleSoggetto = regionaleSoggetto;
	}
	public void setIstanzeCompensazione(List<IstanzaCompensazione> istanzeCompensazione) {
		this.istanzeCompensazione = istanzeCompensazione;
	}
	public void setAllegatiTypes(List<Integer> allegatiTypes) {
		this.allegatiTypes = allegatiTypes;
	}
	public int getNrIstanza() {
		return nrIstanza;
	}
	public void setNrIstanza(int nrIstanza) {
		this.nrIstanza = nrIstanza;
	} 
	
	
}
