/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.time.LocalDate;
import java.util.Objects;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import it.csi.idf.idfapi.config.LocalDateDeserializer;
import it.csi.idf.idfapi.config.LocalDateSerializer;

public class Delega {
	
	public Delega() {}
	
	public Delega(String cfDelegante, Integer idConfigUtente, String fileName, String uidIndex) {
		this.cfDelegante = cfDelegante;
		this.idConfigUtente = idConfigUtente;
		this.fileName = fileName;
		this.uidIndex = uidIndex;
	}
	
	private String cfDelegante;
	private Integer idConfigUtente;
	private String uidIndex;
	private String fileName;
	
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate dataInizio;
	
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate dataFine;
	
	
	public String getCfDelegante() {
		return cfDelegante;
	}
	
	public void setCfDelegante(String cfDelegante) {
		this.cfDelegante = cfDelegante;
	}
	
	public Integer getIdConfigUtente() {
		return idConfigUtente;
	}
	
	public void setIdConfigUtente(Integer idConfigUtente) {
		this.idConfigUtente = idConfigUtente;
	}
	
	public LocalDate getDataInizio() {
		return dataInizio;
	}
	
	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}
	
	
	public LocalDate getDataFine() {
		return dataFine;
	}
	public void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
	}

	public String getUidIndex() {
		return uidIndex;
	}

	public void setUidIndex(String uidIndex) {
		this.uidIndex = uidIndex;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cfDelegante == null) ? 0 : cfDelegante.hashCode());
		result = prime * result + ((idConfigUtente == null) ? 0 : idConfigUtente.hashCode());
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
		Delega other = (Delega) obj;
		if (cfDelegante == null) {
			if (other.cfDelegante != null)
				return false;
		} else if (!cfDelegante.equals(other.cfDelegante))
			return false;
		if (idConfigUtente == null) {
			if (other.idConfigUtente != null)
				return false;
		} else if (!idConfigUtente.equals(other.idConfigUtente))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Delega [cfDelegante=" + cfDelegante + ", idConfigUtente=" + idConfigUtente + ", uidIndex=" + uidIndex
				+ ", dataInizio=" + dataInizio + ", dataFine=" + dataFine + "]";
	}
	

	
}
