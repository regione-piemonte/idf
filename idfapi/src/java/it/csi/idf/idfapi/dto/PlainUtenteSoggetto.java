/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.util.Objects;

import org.codehaus.jackson.annotate.JsonProperty;

public class PlainUtenteSoggetto {
	
	private Integer idSoggetto;
	private Integer idConfigUtente;
	private String codiceFiscale;
	private String cognome;
	private String nome;
	private String ruolo;
	private String provider;
	private String nrTelefonico;
	private String email;
	private Byte flgPrivacy;

	public Integer getIdSoggetto() {
		return idSoggetto;
	}
	public void setIdSoggetto(Integer idSoggetto) {
		this.idSoggetto = idSoggetto;
	}
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRuolo() {
		return ruolo;
	}
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getNrTelefonico() {
		return nrTelefonico;
	}
	public void setNrTelefonico(String nrTelefonico) {
		this.nrTelefonico = nrTelefonico;
	}
	@JsonProperty("email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Byte getFlgPrivacy() {
		return flgPrivacy;
	}
	public void setFlgPrivacy(Byte flgPrivacy) {
		this.flgPrivacy = flgPrivacy;
	}
	public Integer getIdConfigUtente() {
		return idConfigUtente;
	}
	public void setIdConfigUtente(Integer idConfigUtente) {
		this.idConfigUtente = idConfigUtente;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(idSoggetto, codiceFiscale, cognome, nome, ruolo, provider, nrTelefonico, email,
				 flgPrivacy, idConfigUtente);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PlainUtenteSoggetto utenteSoggetto = (PlainUtenteSoggetto) o;
		return Objects.equals(idSoggetto, utenteSoggetto.idSoggetto) 
				&& Objects.equals(idConfigUtente, utenteSoggetto.idConfigUtente)
				&& Objects.equals(codiceFiscale, utenteSoggetto.codiceFiscale) 
				&& Objects.equals(cognome, utenteSoggetto.cognome) 
				&& Objects.equals(nome, utenteSoggetto.nome)
				&& Objects.equals(ruolo, utenteSoggetto.ruolo)
				&& Objects.equals(provider, utenteSoggetto.provider)
				&& Objects.equals(email, utenteSoggetto.email)
				&& Objects.equals(flgPrivacy, utenteSoggetto.flgPrivacy)
				&& Objects.equals(nrTelefonico, utenteSoggetto.nrTelefonico);
	}
	
	 @Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class PlainUtenteSoggetto {\n");
		sb.append("    idSoggetto: ").append(idSoggetto).append("\n");
		sb.append("    idConfigUtente: ").append(idConfigUtente).append("\n");
		sb.append("    codiceFiscale: ").append(codiceFiscale).append("\n");
		sb.append("    cognome: ").append(cognome).append("\n");
		sb.append("    nome: ").append(nome).append("\n");
		sb.append("    ruolo: ").append(ruolo).append("\n");
		sb.append("    provider: ").append(provider).append("\n");
		sb.append("    nrTelefonico: ").append(nrTelefonico).append("\n");
		sb.append("    email: ").append(email).append("\n");
		sb.append("    flgSettoreRegionale: ").append(flgPrivacy).append("\n");
		sb.append("}");
		return sb.toString();
	}
}
