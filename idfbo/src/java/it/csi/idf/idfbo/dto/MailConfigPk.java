/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.dto;

import java.io.Serializable;

/**
 * Primary Key del DTO MailConfigDto.
 * E' utilizzato per tutte le operazioni di lettura dati per chiave primaria. 
 * @generated
 */
public class MailConfigPk implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * store della proprieta' associata alla colonna ID_MAIL
	 * @generated
	 */
	protected Integer idMail;

	/**
	 * Imposta il valore della proprieta' idMail associata alla
	 * colonna ID_MAIL.
	 * @generated
	 */
	public void setIdMail(Integer val) {

		idMail = val;

	}

	/**
	 * Legge il valore della proprieta' idMail associata alla
	 * @generated
	 */
	public Integer getIdMail() {

		return idMail;

	}

	/**
	 * Costruttore di una chiave primaria vuota
	 * @generated 
	 */
	public MailConfigPk() {
		//empty constructor
	}

	/**
	 * Costruttore di una chiave primaria a partire dai valori delle varie colonne
	 * @generated
	 */
	public MailConfigPk(

			final Integer idMail

	) {

		this.setIdMail(idMail);

	}

	/**
	 * Method 'equals'. 
	 * Due istanze di MailConfigPk sono equals se i valori di tutti i campi coincidono.
	 * 
	 * @param _other
	 * @return boolean se i due oggetti sono uguali
	 */
	public boolean equals(Object _other) {
		if (_other == null) {
			return false;
		}

		if (_other == this) {
			return true;
		}

		if (!(_other instanceof MailConfigPk)) {
			return false;
		}

		final MailConfigPk _cast = (MailConfigPk) _other;

		if (idMail == null ? _cast.getIdMail() != null : !idMail.equals(_cast.getIdMail())) {
			return false;
		}

		return true;
	}

	/**
	 * Method 'hashCode'
	 * 
	 * @return int
	 */
	public int hashCode() {
		int _hashCode = 0;

		if (idMail != null) {
			_hashCode = 29 * _hashCode + idMail.hashCode();
		}

		return _hashCode;
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString() {
		StringBuilder ret = new StringBuilder();

		ret.append("it.csi.idf.idfapi.business.dao.idf.dto.MailConfigPk: ");
		ret.append("idMail=" + idMail);

		return ret.toString();
	}
}
