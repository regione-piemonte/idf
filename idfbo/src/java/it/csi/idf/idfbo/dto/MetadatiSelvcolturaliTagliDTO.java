/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.dto;

import org.codehaus.jackson.annotate.JsonProperty;

public class MetadatiSelvcolturaliTagliDTO {

	private String id;
	private Long nr_pratica;
	private String data_invio_pratica;
	private Integer anno_pratica;
	private String denominazione_richiedente;
	private String cognome_richiedente;
	private String nome_richiedente;
	private String PG_partita_iva_richiedente;
	private String PG_codice_fiscale_richiedente;
	private String PG_indirizzo_pec_richiedente;
	private String PF_codice_fiscale_richiedente;
	private String PF_indirizzo_mail_richiedente;
	private String sigla_provincia;
	private String comune;

	// private String soggetto_gestore;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	public Long getNr_pratica() {
		return nr_pratica;
	}
	
	@JsonProperty("numero_pratica")
	public void setNr_pratica(Long nr_pratica) {
		this.nr_pratica = nr_pratica;
	}

	public String getData_invio_pratica() {
		return data_invio_pratica;
	}

	public void setData_invio_pratica(String data_invio_pratica) {
		this.data_invio_pratica = data_invio_pratica;
	}

	public Integer getAnno_pratica() {
		return anno_pratica;
	}

	public void setAnno_pratica(Integer anno_pratica) {
		this.anno_pratica = anno_pratica;
	}

	public String getDenominazione_richiedente() {
		return denominazione_richiedente;
	}

	public void setDenominazione_richiedente(String denominazione_richiedente) {
		this.denominazione_richiedente = denominazione_richiedente;
	}

	public String getCognome_richiedente() {
		return cognome_richiedente;
	}

	public void setCognome_richiedente(String cognome_richiedente) {
		this.cognome_richiedente = cognome_richiedente;
	}

	public String getNome_richiedente() {
		return nome_richiedente;
	}

	public void setNome_richiedente(String nome_richiedente) {
		this.nome_richiedente = nome_richiedente;
	}
	
	@JsonProperty("PG_partita_iva_richiedente")
	public String getPG_partita_iva_richiedente() {
		return PG_partita_iva_richiedente;
	}
	
	public void setPG_partita_iva_richiedente(String pG_partita_iva_richiedente) {
		this.PG_partita_iva_richiedente = pG_partita_iva_richiedente;
	}
	
	@JsonProperty("PG_codice_fiscale_richiedente")
	public String getPG_codice_fiscale_richiedente() {
		return PG_codice_fiscale_richiedente;
	}

	public void setPG_codice_fiscale_richiedente(String pG_codice_fiscale_richiedente) {
		this.PG_codice_fiscale_richiedente = pG_codice_fiscale_richiedente;
	}
	
	@JsonProperty("PG_indirizzo_pec_richiedente")
	public String getPG_indirizzo_pec_richiedente() {
		return PG_indirizzo_pec_richiedente;
	}
	
	public void setPG_indirizzo_pec_richiedente(String pG_indirizzo_pec_richiedente) {
		this.PG_indirizzo_pec_richiedente = pG_indirizzo_pec_richiedente;
	}
	
	@JsonProperty("PF_codice_fiscale_richiedente")
	public String getPF_codice_fiscale_richiedente() {
		return PF_codice_fiscale_richiedente;
	}

	public void setPF_codice_fiscale_richiedente(String pF_codice_fiscale_richiedente) {
		this.PF_codice_fiscale_richiedente = pF_codice_fiscale_richiedente;
	}
	
	@JsonProperty("PF_indirizzo_mail_richiedente")
	public String getPF_indirizzo_mail_richiedente() {
		return PF_indirizzo_mail_richiedente;
	}

	public void setPF_indirizzo_mail_richiedente(String pF_indirizzo_mail_richiedente) {
		this.PF_indirizzo_mail_richiedente = pF_indirizzo_mail_richiedente;
	}

	public String getSigla_provincia() {
		return sigla_provincia;
	}

	public void setSigla_provincia(String sigla_provincia) {
		this.sigla_provincia = sigla_provincia;
	}

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

}
