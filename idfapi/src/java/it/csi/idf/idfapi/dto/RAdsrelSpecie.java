/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

import org.codehaus.jackson.annotate.JsonProperty;

public class RAdsrelSpecie {
	
	private Long idSpecie;
	private Long idgeoPtAds;
	private String codTipoCampione;
	private String flgPolloneSeme;
	private String qualita;
	private Integer diametro;
	private Integer altezza;
	private Integer incremento;
	private Integer eta;
	private Integer nrAlberiSeme;
	private Integer nrAlberiPollone;
	private Timestamp dataInizioValidita; 
	private LocalDate dataFineValidita;
	private String note;

	@JsonProperty("idSpecie")
	public Long getIdSpecie() {
		return idSpecie;
	}

	public void setIdSpecie(Long idSpecie) {
		this.idSpecie = idSpecie;
	}


	@JsonProperty("idgeoPtAds")
	public Long getIdgeoPtAds() {
		return idgeoPtAds;
	}

	public void setIdgeoPtAds(Long idgeoPtAds) {
		this.idgeoPtAds = idgeoPtAds;
	}
	
	@JsonProperty("flgPolloneSeme")
	public String getFlgPolloneSeme() {
		return flgPolloneSeme;
	}
	@JsonProperty("codTipoCampione")
	public String getCodTipoCampione() {
		return codTipoCampione;
	}

	public void setCodTipoCampione(String codTipoCampione) {
		this.codTipoCampione = codTipoCampione;
	}

	public void setFlgPolloneSeme(String flgPolloneSeme) {
		this.flgPolloneSeme = flgPolloneSeme;
	}
	@JsonProperty("nrAlberiPollone")
	public Integer getNrAlberiPollone() {
		return nrAlberiPollone;
	}

	public void setNrAlberiPollone(Integer nrAlberiPollone) {
		this.nrAlberiPollone = nrAlberiPollone;
	}
	@JsonProperty("dataInizioValidita")
	public Timestamp getDataInizioValidita() {
		return dataInizioValidita;
	}

	public void setDataInizioValidita(Timestamp dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	@JsonProperty("qualita")
	public String getQualita() {
		return qualita;
	}

	public void setQualita(String qualita) {
		this.qualita = qualita;
	}

	@JsonProperty("diametro")
	public Integer getDiametro() {
		return diametro;
	}

	public void setDiametro(Integer diametro) {
		this.diametro = diametro;
	}

	@JsonProperty("altezza")
	public Integer getAltezza() {
		return altezza;
	}

	public void setAltezza(Integer altezza) {
		this.altezza = altezza;
	}

	@JsonProperty("incremento")
	public Integer getIncremento() {
		return incremento;
	}

	public void setIncremento(Integer incremento) {
		this.incremento = incremento;
	}

	@JsonProperty("eta")
	public Integer getEta() {
		return eta;
	}

	public void setEta(Integer eta) {
		this.eta = eta;
	}

	@JsonProperty("nrAlberiSeme")
	public Integer getNrAlberiSeme() {
		return nrAlberiSeme;
	}

	public void setNrAlberiSeme(Integer nrAlberiSeme) {
		this.nrAlberiSeme = nrAlberiSeme;
	}


	@JsonProperty("dataFineValidita")
	public LocalDate getDataFineValidita() {
		return dataFineValidita;
	}

	public void setDataFineValidita(LocalDate dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

	@JsonProperty("note")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	@Override
	  public boolean equals(Object o) {
	    if (this == o) {
	      return true;
	    }
	    if (o == null || getClass() != o.getClass()) {
	      return false;
	    }
	    RAdsrelSpecie specie = (RAdsrelSpecie) o;
	    return Objects.equals(idSpecie, specie.idSpecie) &&
	        Objects.equals(idgeoPtAds, specie.idgeoPtAds) &&
	        Objects.equals(qualita, specie.qualita) &&
	        Objects.equals(diametro, specie.diametro) &&
	        Objects.equals(altezza, specie.altezza) &&
	        Objects.equals(incremento, specie.incremento)&&
	        Objects.equals(eta, specie.eta) &&
	        Objects.equals(codTipoCampione, specie.codTipoCampione) &&
	        Objects.equals(flgPolloneSeme, specie.flgPolloneSeme) &&
	        Objects.equals(nrAlberiPollone, specie.nrAlberiPollone) &&
	        Objects.equals(nrAlberiSeme, specie.nrAlberiSeme) &&
	        Objects.equals(dataInizioValidita, specie.dataInizioValidita) &&
	        Objects.equals(dataFineValidita, specie.dataFineValidita) &&
	        Objects.equals(note, specie.note);
	  }
	@Override
	  public int hashCode() {
	    return Objects.hash(idSpecie, idgeoPtAds, codTipoCampione,flgPolloneSeme, qualita, diametro, altezza, incremento,eta,nrAlberiSeme,nrAlberiPollone,dataInizioValidita,dataFineValidita,note);
	  }
	
	@Override
	  public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("class RAdsSpecie {\n");
	    
	    sb.append("    idSpecie: ").append(idSpecie).append("\n");
	    sb.append("    idgeoPtAds: ").append(idgeoPtAds).append("\n");
	    sb.append("    codTipoCampione: ").append(codTipoCampione).append("\n");
	    sb.append("    flgPolloneSeme: ").append(flgPolloneSeme).append("\n");
	    sb.append("    qualita: ").append(qualita).append("\n");
	    sb.append("    diametro: ").append(diametro).append("\n");
	    sb.append("    altezza: ").append(altezza).append("\n");
	    sb.append("    incremento: ").append(incremento).append("\n");
	    sb.append("    eta: ").append(eta).append("\n");
	    sb.append("    nrAlberiSeme: ").append(nrAlberiSeme).append("\n");
	    sb.append("    nrAlberiPollone: ").append(nrAlberiPollone).append("\n");
	    sb.append("    dataInizioValidita: ").append(dataInizioValidita).append("\n");
	    sb.append("    dataFineValidita: ").append(dataFineValidita).append("\n");
	    sb.append("    note: ").append(note).append("\n");
	    sb.append("}");
	    return sb.toString();
	  }


}
