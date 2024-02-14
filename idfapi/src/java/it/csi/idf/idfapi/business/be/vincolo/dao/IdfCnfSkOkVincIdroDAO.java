/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.dao;

import java.math.BigDecimal;

//
// Created on 2020-10-04 ( Date ISO 2020-10-04 - Time 22:37:04 )
// java7-persistance-jdbc-T300package it.csi.idf.idfapi.validation.dao;


import java.util.List;

import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfCnfSkOkVincIdro;

/**
 * Dao Interface for IdfCnfSkOkVincIdro.
 */
public interface IdfCnfSkOkVincIdroDAO { 

	/**
	 * Tries t o find an entity using its Id / Primary Key
	 * @param idIntervento
	 * @return entity
	 */
	IdfCnfSkOkVincIdro findById( BigDecimal idIntervento  ) ;

	/**
	 * Finds all entities.
	 * @return all entities
	 */
	List<IdfCnfSkOkVincIdro> findAll();

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
	IdfCnfSkOkVincIdro save(IdfCnfSkOkVincIdro entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return true if the entity has been updated, false if not found and not updated
	 */
	boolean update(IdfCnfSkOkVincIdro entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	IdfCnfSkOkVincIdro create(IdfCnfSkOkVincIdro entity);

	/**
	 * Deletes an entity using its Id / Primary Key
	 * @param idIntervento
	 * @return true if the entity has been deleted, false if not found and not deleted
	 */
	boolean deleteById( BigDecimal idIntervento );

	/**
	 * Deletes an entity using the Id / Primary Key stored in the given object
	 * @param the entity to be deleted (supposed to have a valid Id/PK )
	 * @return true if the entity has been deleted, false if not found and not deleted
	 */
	boolean delete( IdfCnfSkOkVincIdro entity );

	/**
	 * Returns true if an entity exists with the given Id / Primary Key 
	 * @param idIntervento
	 * @return
	 */
	public boolean exists( BigDecimal idIntervento ) ;

	/**
	 * Returns true if the given entity exists
	 * @param entity
	 * @return
	 */
	public boolean exists( IdfCnfSkOkVincIdro entity ) ;

	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param idfCnfSkOkVincIdro
	 * @return true if found, false if not found
	 */
	boolean load(IdfCnfSkOkVincIdro idfCnfSkOkVincIdro);

	/**
	 * Inserts the given bean in the database 
	 * @param idfCnfSkOkVincIdro
	 */
	long insert(IdfCnfSkOkVincIdro idfCnfSkOkVincIdro);

}
