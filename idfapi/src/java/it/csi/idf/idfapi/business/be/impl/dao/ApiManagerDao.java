/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.business.be.exceptions.dao.ApiManagerDaoException;
import it.csi.idf.idfapi.dto.ApiManagerDto;
import it.csi.idf.idfapi.dto.ApiManagerPk;

/**
 * Interfaccia pubblica del DAO ApiManager.
 * Espone le operazioni che possono essere eseguite per la gestione 
 * della tabella [Table[TRANSIENT]]
 * @generated
 */
public interface ApiManagerDao {

	/** 
	 * Returns all rows from the IDF_CNF_PARAM_APIMGR table that match the primary key criteria
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public ApiManagerDto findByPrimaryKey(ApiManagerPk pk) throws ApiManagerDaoException;
	
	/** 
	 * Implementazione del finder parametriValidi
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public ApiManagerDto findParametriValidi() throws ApiManagerDaoException;
	
	@SuppressWarnings("unchecked")
	public ApiManagerDto findParametriInternetValidi() throws ApiManagerDaoException;
	
	

}
