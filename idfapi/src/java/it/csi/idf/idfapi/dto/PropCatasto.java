/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

public class PropCatasto {

	private Integer idGeoPlPropCatasto;
	private Integer idGeoPlPfa;
	private Integer fkComune;
	private String sezione;
	private Integer foglio;
	private String allegatoCatasto;
	private String sviluppoCatasto;
	private String particella;
	private BigDecimal supCatastaleMq;
	private BigDecimal supCartograficaHa;
	private BigDecimal supInterventoHa;
	private Integer fkProprieta;
	private String intestato;
	private String qualitaColtura;
	private Byte flgUsiCivici;
	private Byte flgPossessiContest;
	private Byte flgLivellari;
	private LocalDate dataInizioValidita;//not null
	private LocalDate dataFineValidita;
	private String note;
	private LocalDate dataAggiornamentoDatocatast;
	private LocalDate dataInserimento;
	private String geometria;  //7777 
	private Integer fkConfigUtente;


	public Integer getIdGeoPlPropCatasto() {
		return idGeoPlPropCatasto;
	}

	public void setIdGeoPlPropCatasto(Integer idGeoPlPropCatasto) {
		this.idGeoPlPropCatasto = idGeoPlPropCatasto;
	}

	public Integer getIdGeoPlPfa() {
		return idGeoPlPfa;
	}

	public void setIdGeoPlPfa(Integer idGeoPlPfa) {
		this.idGeoPlPfa = idGeoPlPfa;
	}

	public Integer getFkComune() {
		return fkComune;
	}

	public void setFkComune(Integer fkComune) {
		this.fkComune = fkComune;
	}

	public String getSezione() {
		return sezione;
	}

	public void setSezione(String sezione) {
		this.sezione = sezione;
	}

	public Integer getFoglio() {
		return foglio;
	}

	public void setFoglio(Integer foglio) {
		this.foglio = foglio;
	}

	public String getAllegatoCatasto() {
		return allegatoCatasto;
	}

	public void setAllegatoCatasto(String allegatoCatasto) {
		this.allegatoCatasto = allegatoCatasto;
	}

	public String getSviluppoCatasto() {
		return sviluppoCatasto;
	}

	public void setSviluppoCatasto(String sviluppoCatasto) {
		this.sviluppoCatasto = sviluppoCatasto;
	}

	public String getParticella() {
		return particella;
	}

	public void setParticella(String particella) {
		this.particella = particella;
	}

	public BigDecimal getSupCatastaleMq() {
		return supCatastaleMq;
	}

	public void setSupCatastaleMq(BigDecimal supCatastaleMq) {
		this.supCatastaleMq = supCatastaleMq;
	}

	public BigDecimal getSupCartograficaHa() {
		return supCartograficaHa;
	}

	public void setSupCartograficaHa(BigDecimal supCartograficaHa) {
		this.supCartograficaHa = supCartograficaHa;
	}

	public BigDecimal getSupInterventoHa() {
		return supInterventoHa;
	}

	public void setSupInterventoHa(BigDecimal supInterventoHa) {
		this.supInterventoHa = supInterventoHa;
	}

	public Integer getFkProprieta() {
		return fkProprieta;
	}

	public void setFkProprieta(Integer fkProprieta) {
		this.fkProprieta = fkProprieta;
	}

	public String getIntestato() {
		return intestato;
	}

	public void setIntestato(String intestato) {
		this.intestato = intestato;
	}

	public String getQualitaColtura() {
		return qualitaColtura;
	}

	public void setQualitaColtura(String qualitaColtura) {
		this.qualitaColtura = qualitaColtura;
	}

	public Byte getFlgUsiCivici() {
		return flgUsiCivici;
	}

	public void setFlgUsiCivici(Byte flgUsiCivici) {
		this.flgUsiCivici = flgUsiCivici;
	}

	public Byte getFlgPossessiContest() {
		return flgPossessiContest;
	}

	public void setFlgPossessiContest(Byte flgPossessiContest) {
		this.flgPossessiContest = flgPossessiContest;
	}

	public Byte getFlgLivellari() {
		return flgLivellari;
	}

	public void setFlgLivellari(Byte flgLivellari) {
		this.flgLivellari = flgLivellari;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public LocalDate getDataAggiornamentoDatocatast() {
		return dataAggiornamentoDatocatast;
	}

	public void setDataAggiornamentoDatocatast(LocalDate dataAggiornamentoDatocatast) {
		this.dataAggiornamentoDatocatast = dataAggiornamentoDatocatast;
	}

	public LocalDate getDataInserimento() {
		return dataInserimento;
	}

	public void setDataInserimento(LocalDate dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public Integer getFkConfigUtente() {
		return fkConfigUtente;
	}

	public void setFkConfigUtente(Integer fkConfigUtente) {
		this.fkConfigUtente = fkConfigUtente;
	}
//7777
	public String getGeometria() {
		return geometria;
	}

	public void setGeometria(String geometria) {
		this.geometria = geometria;
	}
////
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof PropCatasto)) return false;
		PropCatasto that = (PropCatasto) o;
		return Objects.equals(idGeoPlPropCatasto, that.idGeoPlPropCatasto) && Objects.equals(idGeoPlPfa, that.idGeoPlPfa) && Objects.equals(fkComune, that.fkComune) && Objects.equals(sezione, that.sezione) && Objects.equals(foglio, that.foglio) && Objects.equals(allegatoCatasto, that.allegatoCatasto) && Objects.equals(sviluppoCatasto, that.sviluppoCatasto) && Objects.equals(particella, that.particella) && Objects.equals(supCatastaleMq, that.supCatastaleMq) && Objects.equals(supCartograficaHa, that.supCartograficaHa) && Objects.equals(supInterventoHa, that.supInterventoHa) && Objects.equals(fkProprieta, that.fkProprieta) && Objects.equals(intestato, that.intestato) && Objects.equals(qualitaColtura, that.qualitaColtura) && Objects.equals(flgUsiCivici, that.flgUsiCivici) && Objects.equals(flgPossessiContest, that.flgPossessiContest) && Objects.equals(flgLivellari, that.flgLivellari) && Objects.equals(dataInizioValidita, that.dataInizioValidita) && Objects.equals(dataFineValidita, that.dataFineValidita) && Objects.equals(note, that.note) && Objects.equals(dataAggiornamentoDatocatast, that.dataAggiornamentoDatocatast) && Objects.equals(dataInserimento, that.dataInserimento) && Objects.equals(fkConfigUtente, that.fkConfigUtente);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idGeoPlPropCatasto, idGeoPlPfa, fkComune, sezione, foglio, allegatoCatasto, sviluppoCatasto, particella, supCatastaleMq, supCartograficaHa, supInterventoHa, fkProprieta, intestato, qualitaColtura, flgUsiCivici, flgPossessiContest, flgLivellari, dataInizioValidita, dataFineValidita, note, dataAggiornamentoDatocatast, dataInserimento, fkConfigUtente);
	}

	@Override
	public String toString() {
		return new StringJoiner("\n ", PropCatasto.class.getSimpleName() + "[", "]")
				.add("idGeoPlPropCatasto=" + idGeoPlPropCatasto)
				.add("idGeoPlPfa=" + idGeoPlPfa)
				.add("fkComune=" + fkComune)
				.add("sezione='" + sezione + "'")
				.add("foglio=" + foglio)
				.add("allegatoCatasto='" + allegatoCatasto + "'")
				.add("sviluppoCatasto='" + sviluppoCatasto + "'")
				.add("particella='" + particella + "'")
				.add("supCatastaleMq=" + supCatastaleMq)
				.add("supCartograficaHa=" + supCartograficaHa)
				.add("supInterventoHa=" + supInterventoHa)
				.add("fkProprieta=" + fkProprieta)
				.add("intestato='" + intestato + "'")
				.add("qualitaColtura='" + qualitaColtura + "'")
				.add("flgUsiCivici=" + flgUsiCivici)
				.add("flgPossessiContest=" + flgPossessiContest)
				.add("flgLivellari=" + flgLivellari)
				.add("dataInizioValidita=" + dataInizioValidita)
				.add("dataFineValidita=" + dataFineValidita)
				.add("note='" + note + "'")
				.add("dataAggiornamentoDatocatast=" + dataAggiornamentoDatocatast)
				.add("dataInserimento=" + dataInserimento)				
				.add("fkConfigUtente=" + fkConfigUtente)
				.add("geometria=" + geometria)
				//777
				.toString();
	}
}
