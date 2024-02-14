/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.service.helper.dto;

import java.io.Serializable;

public class Ricadenza implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1835400663550505060L;
	
	private String codiceAmministrativo;
	private String nome;
	private String tipologiaSito;
	private String percentualeRicadenza;
	/**
	 * @return the codiceAmministrativo
	 */
	public String getCodiceAmministrativo() {
		return codiceAmministrativo;
	}
	/**
	 * @param codiceAmministrativo the codiceAmministrativo to set
	 */
	public void setCodiceAmministrativo(String codiceAmministrativo) {
		this.codiceAmministrativo = codiceAmministrativo;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the tipologiaSito
	 */
	public String getTipologiaSito() {
		return tipologiaSito;
	}
	/**
	 * @param tipologiaSito the tipologiaSito to set
	 */
	public void setTipologiaSito(String tipologiaSito) {
		this.tipologiaSito = tipologiaSito;
	}
	/**
	 * @return the percentualeRicadenza
	 */
	public String getPercentualeRicadenza() {
		return percentualeRicadenza;
	}
	/**
	 * @param percentualeRicadenza the percentualeRicadenza to set
	 */
	public void setPercentualeRicadenza(String percentualeRicadenza) {
		this.percentualeRicadenza = percentualeRicadenza;
	}
	
	

}
