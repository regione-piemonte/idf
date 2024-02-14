/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.util.Objects;

import org.codehaus.jackson.annotate.JsonProperty;

public class ProfiloUtente {
	private Integer idProfiloUtente;
	private String profiloUtente;
	private byte mtdFlgAttivo;
	private Integer fkProcedura;
	
	@JsonProperty("idProfiloUtente")
	public Integer getIdProfiloUtente() {
		return idProfiloUtente;
	}
	public void setIdProfiloUtente(Integer idProfiloUtente) {
		this.idProfiloUtente = idProfiloUtente;
	}
	
	@JsonProperty("profiloUtente")
	public String getProfiloUtente() {
		return profiloUtente;
	}
	public void setProfiloUtente(String profiloUtente) {
		this.profiloUtente = profiloUtente;
	}
	
	@JsonProperty("mtdFlgAttivo")
	public byte getMtdFlgAttivo() {
		return mtdFlgAttivo;
	}
	public void setMtdFlgAttivo(byte mtdFlgAttivo) {
		this.mtdFlgAttivo = mtdFlgAttivo;
	}
	
	@JsonProperty("fkProcedura")
	public Integer getFkProcedura() {
		return fkProcedura;
	}
	public void setFkProcedura(Integer fkProcedura) {
		this.fkProcedura = fkProcedura;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ProfiloUtente profUtente = (ProfiloUtente) o;
		return Objects.equals(idProfiloUtente, profUtente.idProfiloUtente)
			&& Objects.equals(profiloUtente, profUtente.profiloUtente)
			&& Objects.equals(mtdFlgAttivo, profUtente.mtdFlgAttivo)
			&& Objects.equals(fkProcedura, profUtente.fkProcedura);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idProfiloUtente, profiloUtente, mtdFlgAttivo, fkProcedura);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ProfiloUtente {\n");
		
		sb.append("    idProfiloUtente: ").append(idProfiloUtente).append("\n");
		sb.append("    profiloUtente: ").append(profiloUtente).append("\n");
		sb.append("    mtdFlgAttivo: ").append(mtdFlgAttivo).append("\n");
		sb.append("    fkProcedura: ").append(fkProcedura).append("\n");
		
		sb.append("}");
		return sb.toString();
	}

}
