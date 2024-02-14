/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.time.LocalDate;

import org.codehaus.jackson.annotate.JsonProperty;



public class Comune {

	private Integer idComune;
	private String istatComune;
    private String istatProv;
	private String denominazioneComune;
	private Integer fk_area_forestale;
	private String codiceBelfiore;
	//private Object geometria;
	private LocalDate dataInizioValidita;
	private LocalDate dataFineValidita;
	
	
	@JsonProperty("idComune")
	public Integer getIdComune() {
		return idComune;
	}
	public void setIdComune(Integer idComune) {
		this.idComune = idComune;
	}
	@JsonProperty("istatComune")
	public String getIstatComune() {
		return istatComune;
	}
	public void setIstatComune(String istatComune) {
		this.istatComune = istatComune;
	}
	@JsonProperty("istatProv")
	public String getIstatProv() {
		return istatProv;
	}
	public void setIstatProv(String istatProv) {
		this.istatProv = istatProv;
	}
	@JsonProperty("denominazioneComune")
	public String getDenominazioneComune() {
		return denominazioneComune;
	}
	public void setDenominazioneComune(String denominazioneComune) {
		this.denominazioneComune = denominazioneComune;
	}
	@JsonProperty("fk_area_forestale")
	public Integer getFk_area_forestale() {
		return fk_area_forestale;
	}
	public void setFk_area_forestale(Integer fk_area_forestale) {
		this.fk_area_forestale = fk_area_forestale;
	}
	@JsonProperty("codiceBelfiore")
	public String getCodiceBelfiore() {
		return codiceBelfiore;
	}
	public void setCodiceBelfiore(String codiceBelfiore) {
		this.codiceBelfiore = codiceBelfiore;
	}
	@JsonProperty("dataInizioValidita")
	public LocalDate getDataInizioValidita() {
		return dataInizioValidita;
	}
	public void setDataInizioValidita(LocalDate dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}
	@JsonProperty("dataFineValidita")
	public LocalDate getDataFineValidita() {
		return dataFineValidita;
	}
	public void setDataFineValidita(LocalDate dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codiceBelfiore == null) ? 0 : codiceBelfiore.hashCode());
		result = prime * result + ((dataFineValidita == null) ? 0 : dataFineValidita.hashCode());
		result = prime * result + ((dataInizioValidita == null) ? 0 : dataInizioValidita.hashCode());
		result = prime * result + ((denominazioneComune == null) ? 0 : denominazioneComune.hashCode());
		result = prime * result + ((fk_area_forestale == null) ? 0 : fk_area_forestale.hashCode());
		result = prime * result + ((idComune == null) ? 0 : idComune.hashCode());
		result = prime * result + ((istatComune == null) ? 0 : istatComune.hashCode());
		result = prime * result + ((istatProv == null) ? 0 : istatProv.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comune other = (Comune) obj;
		if (codiceBelfiore == null) {
			if (other.codiceBelfiore != null)
				return false;
		} else if (!codiceBelfiore.equals(other.codiceBelfiore))
			return false;
		if (dataFineValidita == null) {
			if (other.dataFineValidita != null)
				return false;
		} else if (!dataFineValidita.equals(other.dataFineValidita))
			return false;
		if (dataInizioValidita == null) {
			if (other.dataInizioValidita != null)
				return false;
		} else if (!dataInizioValidita.equals(other.dataInizioValidita))
			return false;
		if (denominazioneComune == null) {
			if (other.denominazioneComune != null)
				return false;
		} else if (!denominazioneComune.equals(other.denominazioneComune))
			return false;
		if (fk_area_forestale == null) {
			if (other.fk_area_forestale != null)
				return false;
		} else if (!fk_area_forestale.equals(other.fk_area_forestale))
			return false;
		if (idComune == null) {
			if (other.idComune != null)
				return false;
		} else if (!idComune.equals(other.idComune))
			return false;
		if (istatComune == null) {
			if (other.istatComune != null)
				return false;
		} else if (!istatComune.equals(other.istatComune))
			return false;
		if (istatProv == null) {
			if (other.istatProv != null)
				return false;
		} else if (!istatProv.equals(other.istatProv))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Comune [idComune=" + idComune + ", istatComune=" + istatComune + ", istatProv=" + istatProv
				+ ", denominazioneComune=" + denominazioneComune + ", fk_area_forestale=" + fk_area_forestale
				+ ", supTotale=" + ", supForestale=" + ", indiceBoscosita=" + ", codiceBelfiore=" + codiceBelfiore + ", dataInizioValidita=" + dataInizioValidita
				+ ", dataFineValidita=" + dataFineValidita + "]";
	}
	
	
	
	
	
	
}
