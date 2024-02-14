/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.util.Objects;
import java.util.StringJoiner;

import org.codehaus.jackson.annotate.JsonProperty;

public class PlainAdpforHome {
	private Integer tipoIstanzaId;
	private Integer categoriaProfessionale;
	private String tipoIstanzaDescr;
	private String fkTipoAccreditamento;
	private Integer fkSoggettoSportello;
	private Integer fkProfilo;
	private String partitaIva;
	private String pec;
	private String numeroIscrizione;
	private String provinciaOrdine;
	private String codiceFiscaleDelega;

	private String ownerCodiceFiscale;

	@JsonProperty("categoriaProfessionale")
	public Integer getCategoriaProfessionale() {
		return categoriaProfessionale;
	}

	public void setCategoriaProfessionale(Integer categoriaProfessionale) {
		this.categoriaProfessionale = categoriaProfessionale;
	}

	@JsonProperty("tipoIstanzaId")
	public Integer getTipoIstanzaId() {
		return tipoIstanzaId;
	}

	public void setTipoIstanzaId(Integer tipoIstanzaId) {
		this.tipoIstanzaId = tipoIstanzaId;
	}

	@JsonProperty("tipoIstanzaDescr")
	public String getTipoIstanzaDescr() {
		return tipoIstanzaDescr;
	}

	public void setTipoIstanzaDescr(String tipoIstanzaDescr) {
		this.tipoIstanzaDescr = tipoIstanzaDescr;
	}

	@JsonProperty("fkTipoAccreditamento")
	public String getFkTipoAccreditamento() {
		return fkTipoAccreditamento;
	}

	public void setFkTipoAccreditamento(String fkTipoAccreditamento) {
		this.fkTipoAccreditamento = fkTipoAccreditamento;
	}

	@JsonProperty("partitaIva")
	public String getPartitaIva() {
		return partitaIva;
	}

	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}

	@JsonProperty("pec")
	public String getPec() {
		return pec;
	}

	public void setPec(String pec) {
		this.pec = pec;
	}

	@JsonProperty("numeroIscrizione")
	public String getNumeroIscrizione() {
		return numeroIscrizione;
	}

	public void setNumeroIscrizione(String numeroIscrizione) {
		this.numeroIscrizione = numeroIscrizione;
	}

	@JsonProperty("provinciaOrdine")
	public String getProvinciaOrdine() {
		return provinciaOrdine;
	}

	public void setProvinciaOrdine(String provinciaOrdine) {
		this.provinciaOrdine = provinciaOrdine;
	}

	@JsonProperty("codiceFiscaleDelega")
	public String getCodiceFiscaleDelega() {
		return codiceFiscaleDelega;
	}

	public void setCodiceFiscaleDelega(String codiceFiscaleDelega) {
		this.codiceFiscaleDelega = codiceFiscaleDelega;
	}

	@JsonProperty("fkProfilo")
	public Integer getFkProfilo() {
		return fkProfilo;
	}

	public void setFkProfilo(Integer fkProfilo) {
		this.fkProfilo = fkProfilo;
	}

	public String getOwnerCodiceFiscale() {
		return ownerCodiceFiscale;
	}

	public void setOwnerCodiceFiscale(String ownerCodiceFiscale) {
		this.ownerCodiceFiscale = ownerCodiceFiscale;
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
		if (!(o instanceof PlainAdpforHome)) return false;
		PlainAdpforHome that = (PlainAdpforHome) o;
		return Objects.equals(tipoIstanzaId, that.tipoIstanzaId) && Objects.equals(categoriaProfessionale, that.categoriaProfessionale) && Objects.equals(tipoIstanzaDescr, that.tipoIstanzaDescr) && Objects.equals(fkTipoAccreditamento, that.fkTipoAccreditamento) && Objects.equals(fkSoggettoSportello, that.fkSoggettoSportello) && Objects.equals(fkProfilo, that.fkProfilo) && Objects.equals(partitaIva, that.partitaIva) && Objects.equals(pec, that.pec) && Objects.equals(numeroIscrizione, that.numeroIscrizione) && Objects.equals(provinciaOrdine, that.provinciaOrdine) && Objects.equals(codiceFiscaleDelega, that.codiceFiscaleDelega) && Objects.equals(ownerCodiceFiscale, that.ownerCodiceFiscale);
	}

	@Override
	public int hashCode() {
		return Objects.hash(tipoIstanzaId, categoriaProfessionale, tipoIstanzaDescr, fkTipoAccreditamento, fkSoggettoSportello, fkProfilo, partitaIva, pec, numeroIscrizione, provinciaOrdine, codiceFiscaleDelega, ownerCodiceFiscale);
	}

	@Override
	public String toString() {
		return new StringJoiner("\n ", PlainAdpforHome.class.getSimpleName() + "[", "]")
				.add("tipoIstanzaId=" + tipoIstanzaId)
				.add("categoriaProfessionale=" + categoriaProfessionale)
				.add("tipoIstanzaDescr='" + tipoIstanzaDescr + "'")
				.add("fkTipoAccreditamento='" + fkTipoAccreditamento + "'")
				.add("fkSoggettoSportello='" + fkSoggettoSportello + "'")
				.add("fkProfilo=" + fkProfilo)
				.add("partitaIva='" + partitaIva + "'")
				.add("pec='" + pec + "'")
				.add("numeroIscrizione='" + numeroIscrizione + "'")
				.add("provinciaOrdine='" + provinciaOrdine + "'")
				.add("codiceFiscaleDelega='" + codiceFiscaleDelega + "'")
				.add("ownerCodiceFiscale='" + ownerCodiceFiscale + "'")
				.toString();
	}
}
