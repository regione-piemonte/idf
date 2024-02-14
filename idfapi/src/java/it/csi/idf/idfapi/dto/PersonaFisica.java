/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.time.LocalDate;
import java.util.Objects;

import org.codehaus.jackson.annotate.JsonProperty;

public class PersonaFisica {
	
	private String codiceFiscale;
	private String cognome;
	private String nome;
	private String istatComuneRes;
	private String indirizzoRes;
	private String civicoRes;
	private Integer capResidenca;
	private Integer telefono;
	private String mail;
	private Boolean flagTrattamento;
	private LocalDate dateIns;
	
	@JsonProperty("codiceFiscale")
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	
	@JsonProperty("cognome")
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	@JsonProperty("nome")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@JsonProperty("istatComuneRes")
	public String getIstatComuneRes() {
		return istatComuneRes;
	}
	public void setIstatComuneRes(String istatComuneRes) {
		this.istatComuneRes = istatComuneRes;
	}
	
	@JsonProperty("indirizzoRes")
	public String getIndirizzoRes() {
		return indirizzoRes;
	}
	public void setIndirizzoRes(String indirizzoRes) {
		this.indirizzoRes = indirizzoRes;
	}
	
	@JsonProperty("civicoRes")
	public String getCivicoRes() {
		return civicoRes;
	}
	public void setCivicoRes(String civicoRes) {
		this.civicoRes = civicoRes;
	}
	
	@JsonProperty("capResidenca")
	public Integer getCapResidenca() {
		return capResidenca;
	}
	public void setCapResidenca(Integer capResidenca) {
		this.capResidenca = capResidenca;
	}
	
	@JsonProperty("telefono")
	public Integer getTelefono() {
		return telefono;
	}
	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}
	
	@JsonProperty("mail")
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	@JsonProperty("flagTrattamento")
	public Boolean getFlagTrattamento() {
		return flagTrattamento;
	}
	public void setFlagTrattamento(Boolean flagTrattamento) {
		this.flagTrattamento = flagTrattamento;
	}
	
	@JsonProperty("dateIns")
	public LocalDate getDateIns() {
		return dateIns;
	}
	public void setDateIns(LocalDate dateIns) {
		this.dateIns = dateIns;
	}
	
	@Override
	  public boolean equals(Object o) {
	    if (this == o) {
	      return true;
	    }
	    if (o == null || getClass() != o.getClass()) {
	      return false;
	    }
	    
	    PersonaFisica personaFisica = (PersonaFisica) o;
	    
	    return Objects.equals(codiceFiscale, personaFisica.codiceFiscale) &&
	            Objects.equals(cognome,personaFisica.cognome) &&
	            Objects.equals(nome,personaFisica.nome) &&
	            Objects.equals(istatComuneRes,personaFisica.istatComuneRes) &&
	            Objects.equals(indirizzoRes,personaFisica.indirizzoRes) &&
	            Objects.equals(civicoRes,personaFisica.civicoRes) &&
	            Objects.equals(capResidenca,personaFisica.capResidenca) &&
	            Objects.equals(telefono,personaFisica.telefono) &&
	            Objects.equals(mail,personaFisica.mail) &&
	            Objects.equals(flagTrattamento,personaFisica.flagTrattamento) &&
	            Objects.equals(dateIns,personaFisica.dateIns);
	}
	
	  @Override
	  public int hashCode() {
		return Objects.hash(codiceFiscale, cognome, nome, istatComuneRes, indirizzoRes, civicoRes, capResidenca,
				telefono, mail, flagTrattamento, dateIns);
	  }
	  
	  @Override
	  public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("class PersonaFisica {\n");
	    
	    sb.append("    codiceFiscale: ").append(toIndentedString(codiceFiscale)).append("\n");
	    sb.append("    cognome: ").append(toIndentedString(cognome)).append("\n");
	    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
	    sb.append("    istatComuneRes: ").append(toIndentedString(istatComuneRes)).append("\n");
	    sb.append("    indirizzoRes: ").append(toIndentedString(indirizzoRes)).append("\n");
	    sb.append("    civicoRes: ").append(toIndentedString(civicoRes)).append("\n");
	    sb.append("    capResidenca: ").append(toIndentedString(capResidenca)).append("\n");
	    sb.append("    telefono: ").append(toIndentedString(telefono)).append("\n");
	    sb.append("    mail: ").append(toIndentedString(mail)).append("\n");
	    sb.append("    flagTrattamento: ").append(toIndentedString(flagTrattamento)).append("\n");
	    sb.append("    dateIns: ").append(toIndentedString(dateIns)).append("\n");
	    
	    sb.append("}");
	    return sb.toString();
	  }
	  
	  /**
	   * Convert the given object to string with each line indented by 4 spaces
	   * (except the first line).
	   */
	  private String toIndentedString(Object o) {
	    if (o == null) {
	      return "null";
	    }
	    return o.toString().replace("\n", "\n    ");
	  }
}
