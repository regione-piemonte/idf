/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.StringJoiner;


public class ConfigUtente {

	private Integer idConfigUtente;
	private Integer fkProfiloUtente;
	private Integer fkTipoAccreditamento;
	private Integer fkSoggettoSportello;
	private Integer nrAccessi;
	private Timestamp dataPrimoAccesso;
	private Timestamp dataUltimoAccesso;
	private Byte flgPrivacy;
	private Integer fkSoggetto;
	private Integer fkProcedura;

	public Integer getIdConfigUtente() {
		return idConfigUtente;
	}

	public void setIdConfigUtente(Integer idConfigUtente) {
		this.idConfigUtente = idConfigUtente;
	}

	public Integer getFkProfiloUtente() {
		return fkProfiloUtente;
	}

	public void setFkProfiloUtente(Integer fkProfiloUtente) {
		this.fkProfiloUtente = fkProfiloUtente;
	}

	public Integer getFkTipoAccreditamento() {
		return fkTipoAccreditamento;
	}

	public void setFkTipoAccreditamento(Integer fkTipoAccreditamento) {
		this.fkTipoAccreditamento = fkTipoAccreditamento;
	}

	public Integer getNrAccessi() {
		return nrAccessi;
	}

	public void setNrAccessi(Integer nrAccessi) {
		this.nrAccessi = nrAccessi;
	}

	public Timestamp getDataPrimoAccesso() {
		return dataPrimoAccesso;
	}

	public void setDataPrimoAccesso(Timestamp dataPrimoAccesso) {
		this.dataPrimoAccesso = dataPrimoAccesso;
	}

	public Timestamp getDataUltimoAccesso() {
		return dataUltimoAccesso;
	}

	public void setDataUltimoAccesso(Timestamp dataUltimoAccesso) {
		this.dataUltimoAccesso = dataUltimoAccesso;
	}

	public Byte getFlgPrivacy() {
		return flgPrivacy;
	}

	public void setFlgPrivacy(Byte flgPrivacy) {
		this.flgPrivacy = flgPrivacy;
	}

	public Integer getFkSoggetto() {
		return fkSoggetto;
	}

	public void setFkSoggetto(Integer fkSoggetto) {
		this.fkSoggetto = fkSoggetto;
	}

	public Integer getFkProcedura() {
		return fkProcedura;
	}

	public void setFkProcedura(Integer fkProcedura) {
		this.fkProcedura = fkProcedura;
	}

	public Integer getFkSoggettoSportello() {
		return fkSoggettoSportello;
	}

	public void setFkSoggettoSportello(Integer fkSoggettoSportello) {
		this.fkSoggettoSportello = fkSoggettoSportello;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ConfigUtente)) return false;
		ConfigUtente that = (ConfigUtente) o;
		return Objects.equals(idConfigUtente, that.idConfigUtente) && Objects.equals(fkProfiloUtente, that.fkProfiloUtente) && Objects.equals(fkTipoAccreditamento, that.fkTipoAccreditamento) && Objects.equals(fkSoggettoSportello, that.fkSoggettoSportello) && Objects.equals(nrAccessi, that.nrAccessi) && Objects.equals(dataPrimoAccesso, that.dataPrimoAccesso) && Objects.equals(dataUltimoAccesso, that.dataUltimoAccesso) && Objects.equals(flgPrivacy, that.flgPrivacy) && Objects.equals(fkSoggetto, that.fkSoggetto) && Objects.equals(fkProcedura, that.fkProcedura);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idConfigUtente, fkProfiloUtente, fkTipoAccreditamento, fkSoggettoSportello, nrAccessi, dataPrimoAccesso, dataUltimoAccesso, flgPrivacy, fkSoggetto, fkProcedura);
	}

	@Override
	public String toString() {
		return new StringJoiner("\n ", ConfigUtente.class.getSimpleName() + "[", "]")
				.add("idConfigUtente=" + idConfigUtente)
				.add("fkProfiloUtente=" + fkProfiloUtente)
				.add("fkTipoAccreditamento=" + fkTipoAccreditamento)
				.add("fkSoggettoSportello=" + fkSoggettoSportello)
				.add("nrAccessi=" + nrAccessi)
				.add("dataPrimoAccesso=" + dataPrimoAccesso)
				.add("dataUltimoAccesso=" + dataUltimoAccesso)
				.add("flgPrivacy=" + flgPrivacy)
				.add("fkSoggetto=" + fkSoggetto)
				.add("fkProcedura=" + fkProcedura)
				.toString();
	}
}
