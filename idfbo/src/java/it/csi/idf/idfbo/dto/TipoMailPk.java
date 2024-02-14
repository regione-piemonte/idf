/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.dto;

import java.io.Serializable;

/**
 * Primary Key del DTO TipoMailDto.
 * E' utilizzato per tutte le operazioni di lettura dati per chiave primaria. 
 * @generated
 */
public class TipoMailPk implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * store della proprieta' associata alla colonna ID_TIPO_MAIL
	 * @generated
	 */
	protected Integer idTipoMail;

	/**
	 * Imposta il valore della proprieta' idTipoMail associata alla
	 * colonna ID_TIPO_MAIL.
	 * @generated
	 */
	public void setIdTipoMail(Integer val) {

		idTipoMail = val;

	}

	/**
	 * Legge il valore della proprieta' idTipoMail associata alla
	 * @generated
	 */
	public Integer getIdTipoMail() {

		return idTipoMail;

	}

	/**
	 * Costruttore di una chiave primaria vuota
	 * @generated 
	 */
	public TipoMailPk() {
		//empty constructor
	}

	/**
	 * Costruttore di una chiave primaria a partire dai valori delle varie colonne
	 * @generated
	 */
	public TipoMailPk(

			final Integer idTipoMail

	) {

		this.setIdTipoMail(idTipoMail);

	}

	/**
	 * Method 'equals'. 
	 * Due istanze di TipoMailPk sono equals se i valori di tutti i campi coincidono.
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

		if (!(_other instanceof TipoMailPk)) {
			return false;
		}

		final TipoMailPk _cast = (TipoMailPk) _other;

		if (idTipoMail == null ? _cast.getIdTipoMail() != null : !idTipoMail.equals(_cast.getIdTipoMail())) {
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

		if (idTipoMail != null) {
			_hashCode = 29 * _hashCode + idTipoMail.hashCode();
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

		ret.append("it.csi.idf.idfapi.business.dao.idf.dto.TipoMailPk: ");
		ret.append("idTipoMail=" + idTipoMail);

		return ret.toString();
	}
}
