/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.dto;

import java.io.Serializable;

/**
 * Primary Key del DTO ApiManagerDto.
 * E' utilizzato per tutte le operazioni di lettura dati per chiave primaria. 
 * @generated
 */
public class ApiManagerPk implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * store della proprieta' associata alla colonna ID_CONFIG_PARAM_APIMGR
	 * @generated
	 */
	protected Integer idConfigParamApimgr;

	/**
	 * Imposta il valore della proprieta' idConfigParamApimgr associata alla
	 * colonna ID_CONFIG_PARAM_APIMGR.
	 * @generated
	 */
	public void setIdConfigParamApimgr(Integer val) {

		idConfigParamApimgr = val;

	}

	/**
	 * Legge il valore della proprieta' idConfigParamApimgr associata alla
	 * @generated
	 */
	public Integer getIdConfigParamApimgr() {

		return idConfigParamApimgr;

	}

	/**
	 * Costruttore di una chiave primaria vuota
	 * @generated 
	 */
	public ApiManagerPk() {
		//empty constructor
	}

	/**
	 * Costruttore di una chiave primaria a partire dai valori delle varie colonne
	 * @generated
	 */
	public ApiManagerPk(

			final Integer idConfigParamApimgr

	) {

		this.setIdConfigParamApimgr(idConfigParamApimgr);

	}

	/**
	 * Method 'equals'. 
	 * Due istanze di ApiManagerPk sono equals se i valori di tutti i campi coincidono.
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

		if (!(_other instanceof ApiManagerPk)) {
			return false;
		}

		final ApiManagerPk _cast = (ApiManagerPk) _other;

		if (idConfigParamApimgr == null
				? _cast.getIdConfigParamApimgr() != null
				: !idConfigParamApimgr.equals(_cast.getIdConfigParamApimgr())) {
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

		if (idConfigParamApimgr != null) {
			_hashCode = 29 * _hashCode + idConfigParamApimgr.hashCode();
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

		ret.append("it.csi.idf.idfapi.dto.ApiManagerPk: ");
		ret.append("idConfigParamApimgr=" + idConfigParamApimgr);

		return ret.toString();
	}
}
