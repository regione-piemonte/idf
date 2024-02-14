/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.dao;

//
// Created on 2020-10-04 ( Date ISO 2020-10-04 - Time 22:37:06 )
// java7-persistance-jdbc-T300package it.csi.idf.idfapi.validation.dao;


import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfTSoggetto;

/**
 * Dao Interface for IdfTSoggetto.
 */
public interface IdfTSoggettoDAO { 

	/**
	 * Tries t o find an entity using its Id / Primary Key
	 * @param idSoggetto
	 * @return entity
	 */
	IdfTSoggetto findById( Integer idSoggetto  ) ;

	/**
	 * Finds all entities.
	 * @return all entities
	 */
	List<IdfTSoggetto> findAll();

	/**
	 * Counts all the records present in the database
	 * @return
	 */
	public long countAll() ;


	/**
	 * Saves the given entity in the database (create or update)
	 * @param entity
	 * @return entity
	 */
	IdfTSoggetto save(IdfTSoggetto entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return true if the entity has been updated, false if not found and not updated
	 */
	boolean update(IdfTSoggetto entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	IdfTSoggetto create(IdfTSoggetto entity);

	/**
	 * Deletes an entity using its Id / Primary Key
	 * @param idSoggetto
	 * @return true if the entity has been deleted, false if not found and not deleted
	 */
	boolean deleteById( Integer idSoggetto );

	/**
	 * Deletes an entity using the Id / Primary Key stored in the given object
	 * @param the entity to be deleted (supposed to have a valid Id/PK )
	 * @return true if the entity has been deleted, false if not found and not deleted
	 */
	boolean delete( IdfTSoggetto entity );

	/**
	 * Returns true if an entity exists with the given Id / Primary Key 
	 * @param idSoggetto
	 * @return
	 */
	public boolean exists( Integer idSoggetto ) ;

	/**
	 * Returns true if the given entity exists
	 * @param entity
	 * @return
	 */
	public boolean exists( IdfTSoggetto entity ) ;

	boolean load(IdfTSoggetto idfTSoggetto);

	RowMapper<IdfTSoggetto> getRowMapper();

}
