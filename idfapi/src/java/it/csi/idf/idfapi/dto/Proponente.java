/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import org.codehaus.jackson.annotate.JsonProperty;

public class Proponente {

	private Integer id_proponente;
	private String denom_proponente;
	private String codice_fiscale;
	private String partita_iva;
	// ISTAT_COMUNE
	private String indirizzo;
	private String nr_telefonico;
	private String e_mail;
	private String pec;

	@JsonProperty("id_proponente")
	public Integer getId_proponente() {
		return id_proponente;
	}

	public void setId_proponente(Integer id_proponente) {
		this.id_proponente = id_proponente;
	}

	@JsonProperty("denom_proponente")
	public String getDenom_proponente() {
		return denom_proponente;
	}

	public void setDenom_proponente(String denom_proponente) {
		this.denom_proponente = denom_proponente;
	}

	@JsonProperty("codice_fiscale")
	public String getCodice_fiscale() {
		return codice_fiscale;
	}

	public void setCodice_fiscale(String codice_fiscale) {
		this.codice_fiscale = codice_fiscale;
	}

	@JsonProperty("partita_iva")
	public String getPartita_iva() {
		return partita_iva;
	}

	public void setPartita_iva(String partita_iva) {
		this.partita_iva = partita_iva;
	}

	@JsonProperty("indirizzo")
	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	@JsonProperty("nr_telefonico")
	public String getNr_telefonico() {
		return nr_telefonico;
	}

	public void setNr_telefonico(String nr_telefonico) {
		this.nr_telefonico = nr_telefonico;
	}

	@JsonProperty("e_mail")
	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	@JsonProperty("pec")
	public String getPec() {
		return pec;
	}

	public void setPec(String pec) {
		this.pec = pec;
	}

}
