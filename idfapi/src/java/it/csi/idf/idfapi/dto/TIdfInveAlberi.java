/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.time.LocalDate;
import java.util.Objects;

import org.codehaus.jackson.annotate.JsonProperty;

public class TIdfInveAlberi {

	private Long idSpecie = null;
	private Integer idInveAlberi = null;
	private Long idgeoPtAds = null;
	private Integer diametro = null;
	private Character flgPolloneSeme = null;
	private LocalDate dataInizio = null;
	private LocalDate dataFine = null;

	@JsonProperty("idSpecie")
	public Long getIdSpecie() {
		return idSpecie;
	}

	public void setIdSpecie(Long idSpecie) {
		this.idSpecie = idSpecie;
	}

	@JsonProperty("idInveAlberi")
	public Integer getIdInveAlberi() {
		return idInveAlberi;
	}

	public void setIdInveAlberi(Integer idInveAlberi) {
		this.idInveAlberi = idInveAlberi;
	}

	@JsonProperty("idgeoPtAds")
	public Long getIdgeoPtAds() {
		return idgeoPtAds;
	}

	public void setIdgeoPtAds(Long idgeoPtAds) {
		this.idgeoPtAds = idgeoPtAds;
	}

	@JsonProperty("diametro")
	public Integer getDiametro() {
		return diametro;
	}

	public void setDiametro(Integer diametro) {
		this.diametro = diametro;
	}

	@JsonProperty("flgPolloneSeme")
	public Character getFlgPolloneSeme() {
		return flgPolloneSeme;
	}

	public void setFlgPolloneSeme(Character flgPolloneSeme) {
		this.flgPolloneSeme = flgPolloneSeme;
	}

	@JsonProperty("dataInizio")
	public LocalDate getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}

	@JsonProperty("dataFine")
	public LocalDate getDataFine() {
		return dataFine;
	}

	public void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
	}

	@Override
	  public boolean equals(Object o) {
	    if (this == o) {
	      return true;
	    }
	    if (o == null || getClass() != o.getClass()) {
	      return false;
	    }
	    TIdfInveAlberi alberi = (TIdfInveAlberi) o;
	    return Objects.equals(idSpecie, alberi.idSpecie) &&
	        Objects.equals(idInveAlberi, alberi.idInveAlberi) &&
	        Objects.equals(idgeoPtAds, alberi.idgeoPtAds) &&
	        Objects.equals(diametro, alberi.diametro) &&
	        Objects.equals(flgPolloneSeme, alberi.flgPolloneSeme) &&
	        Objects.equals(dataInizio, alberi.dataInizio)&&
	        Objects.equals(dataFine, alberi.dataFine);
	  }
	@Override
	  public int hashCode() {
	    return Objects.hash(idSpecie, idInveAlberi, idgeoPtAds, diametro, flgPolloneSeme, dataInizio,dataFine);
	  }
	
	@Override
	  public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("class TIdfInveAlberi {\n");
	    
	    sb.append("    idSpecie: ").append(idSpecie).append("\n");
	    sb.append("    idInveAlberi: ").append(idInveAlberi).append("\n");
	    sb.append("    idgeoPtAds: ").append(idgeoPtAds).append("\n");
	    sb.append("    diametro: ").append(diametro).append("\n");
	    sb.append("    flgPolloneSeme: ").append(flgPolloneSeme).append("\n");
	    sb.append("    dataInizio: ").append(dataInizio).append("\n");
	    sb.append("    dataFine: ").append(dataFine).append("\n");
	
	    sb.append("}");
	    return sb.toString();
	  }
}
