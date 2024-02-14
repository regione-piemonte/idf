/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import org.codehaus.jackson.annotate.JsonProperty;


public class CategoriaForestale {

	private Integer idCategoriaForestale;
	private Integer fkParamMacroCatfor;
	private String codiceAmministrativo;
	private String descrizione;
	private Integer mtdOrdinamento;
	private Byte flgVisibile;
	
	@JsonProperty("idCategoriaForestale")
	public Integer getIdCategoriaForestale() {
		return idCategoriaForestale;
	}
	public void setIdCategoriaForestale(Integer idCategoriaForestale) {
		this.idCategoriaForestale = idCategoriaForestale;
	}
	@JsonProperty("codiceAmministrativo")
	public String getCodiceAmministrativo() {
		return codiceAmministrativo;
	}
	
	@JsonProperty("descrizione")
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public Integer getFkParamMacroCatfor() {
		return fkParamMacroCatfor;
	}
	public void setFkParamMacroCatfor(Integer fkParamMacroCatfor) {
		this.fkParamMacroCatfor = fkParamMacroCatfor;
	}
	public Integer getMtdOrdinamento() {
		return mtdOrdinamento;
	}
	public void setMtdOrdinamento(Integer mtdOrdinamento) {
		this.mtdOrdinamento = mtdOrdinamento;
	}
	public Byte getFlgVisibile() {
		return flgVisibile;
	}
	public void setFlgVisibile(Byte flgVisibile) {
		this.flgVisibile = flgVisibile;
	}
	public void setCodiceAmministrativo(String codiceAmministrativo) {
		this.codiceAmministrativo = codiceAmministrativo;
	}
	
	

}
