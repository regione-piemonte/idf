/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.dto;

/**
 * Data transfer object relativo al DAO TipoMailDao.
 * @generated
 */
public class TipoMailDto extends TipoMailPk {

	private static final long serialVersionUID = 1L;

	/**
	 * store della proprieta' associata alla colonna FK_AMBITO_ISTANZA
	 * @generated
	 */
	protected java.math.BigDecimal fkAmbitoIstanza;

	/**
	 * Imposta il valore della proprieta' fkAmbitoIstanza associata alla
	 * colonna FK_AMBITO_ISTANZA.
	 * @generated
	 */
	public void setFkAmbitoIstanza(java.math.BigDecimal val) {

		fkAmbitoIstanza = val;

	}

	/**
	 * Legge il valore della proprieta' fkAmbitoIstanza associata alla
	 * @generated
	 */
	public java.math.BigDecimal getFkAmbitoIstanza() {

		return fkAmbitoIstanza;

	}

	/**
	 * store della proprieta' associata alla colonna OGGETTO
	 * @generated
	 */
	protected String oggetto;

	/**
	 * Imposta il valore della proprieta' oggetto associata alla
	 * colonna OGGETTO.
	 * @generated
	 */
	public void setOggetto(String val) {

		oggetto = val;

	}

	/**
	 * Legge il valore della proprieta' oggetto associata alla
	 * @generated
	 */
	public String getOggetto() {

		return oggetto;

	}

	/**
	 * store della proprieta' associata alla colonna TESTO
	 * @generated
	 */
	protected String testo;

	/**
	 * Imposta il valore della proprieta' testo associata alla
	 * colonna TESTO.
	 * @generated
	 */
	public void setTesto(String val) {

		testo = val;

	}

	/**
	 * Legge il valore della proprieta' testo associata alla
	 * @generated
	 */
	public String getTesto() {

		return testo;

	}

	/**
	 * Crea una istanza di TipoMailPk a partire dal valore dei campi chiave del DTO
	 * 
	 * @return TipoMailPk
	 * @generated
	 */
	public TipoMailPk createPk() {
		return new TipoMailPk(idTipoMail);
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
