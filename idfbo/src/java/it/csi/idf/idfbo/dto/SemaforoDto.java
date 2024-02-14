/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.dto;

import java.io.Serializable;

/**
 * Data transfer object relativo al DAO TipoMailDao.
 * @generated
 */
public class SemaforoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idSemaforo;
	private String codice;
	private Integer valore;
	
	
	
	

	/**
	 * @return the idSemaforo
	 */
	public Integer getIdSemaforo() {
		return idSemaforo;
	}

	/**
	 * @param idSemaforo the idSemaforo to set
	 */
	public void setIdSemaforo(Integer idSemaforo) {
		this.idSemaforo = idSemaforo;
	}

	/**
	 * @return the codice
	 */
	public String getCodice() {
		return codice;
	}

	/**
	 * @param codice the codice to set
	 */
	public void setCodice(String codice) {
		this.codice = codice;
	}

	/**
	 * @return the valore
	 */
	public Integer getValore() {
		return valore;
	}

	/**
	 * @param valore the valore to set
	 */
	public void setValore(Integer valore) {
		this.valore = valore;
	}

	/**
	 * la semantica dell'equals del DTO e' la stessa della PK
	 * (ovvero della superclasse).
	 * @param other l'oggetto con cui effettuare il confronto
	 * @return true se i due oggetti sono semanticamente da considerarsi uguali
	 */
	public boolean equals(Object other) {
		return super.equals(other);
	}

	/**
	 * la semantica dell'hashCode del DTO e' la stessa della PK
	 * (ovvero della superclasse).
	 * 
	 * @return int
	 */
	public int hashCode() {
		return super.hashCode();
	}

}
