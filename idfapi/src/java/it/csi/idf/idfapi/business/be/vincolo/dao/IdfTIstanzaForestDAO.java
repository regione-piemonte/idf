/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.dao;

import java.math.BigDecimal;

//
// Created on 2020-10-04 ( Date ISO 2020-10-04 - Time 22:37:06 )
// java7-persistance-jdbc-T300package it.csi.idf.idfapi.validation.dao;


import java.util.List;

import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfTIstanzaForest;

/**
 * Dao Interface for IdfTIstanzaForest.
 */
public interface IdfTIstanzaForestDAO { 

	/**
	 * Tries t o find an entity using its Id / Primary Key
	 * @param idIstanzaIntervento
	 * @return entity
	 */
	IdfTIstanzaForest findById( BigDecimal idIstanzaIntervento  ) ;

	/**
	 * Finds all entities.
	 * @return all entities
	 */
	List<IdfTIstanzaForest> findAll();

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
	IdfTIstanzaForest save(IdfTIstanzaForest entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return true if the entity has been updated, false if not found and not updated
	 */
	boolean update(IdfTIstanzaForest entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	IdfTIstanzaForest create(IdfTIstanzaForest entity);

	/**
	 * Deletes an entity using its Id / Primary Key
	 * @param idIstanzaIntervento
	 * @return true if the entity has been deleted, false if not found and not deleted
	 */
	boolean deleteById( BigDecimal idIstanzaIntervento );

	/**
	 * Deletes an entity using the Id / Primary Key stored in the given object
	 * @param the entity to be deleted (supposed to have a valid Id/PK )
	 * @return true if the entity has been deleted, false if not found and not deleted
	 */
	boolean delete( IdfTIstanzaForest entity );

	/**
	 * Returns true if an entity exists with the given Id / Primary Key 
	 * @param idIstanzaIntervento
	 * @return
	 */
	public boolean exists( BigDecimal idIstanzaIntervento ) ;

	/**
	 * Returns true if the given entity exists
	 * @param entity
	 * @return
	 */
	public boolean exists( IdfTIstanzaForest entity ) ;

	boolean load(IdfTIstanzaForest idfTIstanzaForest);

}
