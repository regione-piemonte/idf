/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import org.codehaus.jackson.annotate.JsonProperty;

public class AmbitoRilievo {
	
	private Integer idAmbito;
	private String descrAmbito;
	private Integer mtdOrdinamento;
	private Byte flgVisibile;
	private Integer fkPadre;
	private Byte flgSpecificare;
	
	@JsonProperty("idAmbito")
	public Integer getIdAmbito() {
		return idAmbito;
	}
	public void setIdAmbito(Integer idAmbito) {
		this.idAmbito = idAmbito;
	}
	@JsonProperty("descrAmbito")
	public String getDescrAmbito() {
		return descrAmbito;
	}
	public void setDescrAmbito(String descrAmbito) {
		this.descrAmbito = descrAmbito;
	}
	@JsonProperty("mtdOrdinamento")
	public Integer getMtdOrdinamento() {
		return mtdOrdinamento;
	}
	public void setMtdOrdinamento(Integer mtdOrdinamento) {
		this.mtdOrdinamento = mtdOrdinamento;
	}
	@JsonProperty("flgVisibile")
	public Byte getFlgVisibile() {
		return flgVisibile;
	}
	public void setFlgVisibile(Byte flgVisibile) {
		this.flgVisibile = flgVisibile;
	}
	public Integer getFkPadre() {
		return fkPadre;
	}
	public void setFkPadre(Integer fkPadre) {
		this.fkPadre = fkPadre;
	}
	
	public Byte getFlgSpecificare() {
		return flgSpecificare;
	}
	public void setFlgSpecificare(Byte flgSpecificare) {
		this.flgSpecificare = flgSpecificare;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AmbitoRilievo [idAmbito=");
		builder.append(idAmbito);
		builder.append(", descrAmbito=");
		builder.append(descrAmbito);
		builder.append(", mtdOrdinamento=");
		builder.append(mtdOrdinamento);
		builder.append(", flgVisibile=");
		builder.append(flgVisibile);
		builder.append(", fkPadre=");
		builder.append(fkPadre);
		builder.append("]");
		return builder.toString();
	}	
	
	
}
