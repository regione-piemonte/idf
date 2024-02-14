/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.dao;


import java.util.List;

import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfCnfDelega;

/**
 * Dao Interface for IdfCnfDelega.
 */
public interface IdfCnfDelegaDAO { 

	/**
	 * Tries t o find an entity using its Id / Primary Key
	 * @param cfDelegante
	 * @param idConfigUtente
	 * @return entity
	 */
	IdfCnfDelega findById( String cfDelegante, Integer idConfigUtente  ) ;

	/**
	 * Finds all entities.
	 * @return all entities
	 */
	List<IdfCnfDelega> findAll();

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
	IdfCnfDelega save(IdfCnfDelega entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return true if the entity has been updated, false if not found and not updated
	 */
	boolean update(IdfCnfDelega entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	IdfCnfDelega create(IdfCnfDelega entity);

	/**
	 * Deletes an entity using its Id / Primary Key
	 * @param cfDelegante
	 * @param idConfigUtente
	 * @return true if the entity has been deleted, false if not found and not deleted
	 */
	boolean deleteById( String cfDelegante, Integer idConfigUtente );

	/**
	 * Deletes an entity using the Id / Primary Key stored in the given object
	 * @param the entity to be deleted (supposed to have a valid Id/PK )
	 * @return true if the entity has been deleted, false if not found and not deleted
	 */
	boolean delete( IdfCnfDelega entity );

	/**
	 * Returns true if an entity exists with the given Id / Primary Key 
	 * @param cfDelegante
	 * @param idConfigUtente
	 * @return
	 */
	public boolean exists( String cfDelegante, Integer idConfigUtente ) ;

	/**
	 * Returns true if the given entity exists
	 * @param entity
	 * @return
	 */
	public boolean exists( IdfCnfDelega entity ) ;

	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param idfCnfDelega
	 * @return true if found, false if not found
	 */
	boolean load(IdfCnfDelega idfCnfDelega);

	/**
	 * Inserts the given bean in the database 
	 * @param idfCnfDelega
	 */
	long insert(IdfCnfDelega idfCnfDelega);

	/**
	 * Inserts the given bean in the database 
	 * @param idfCnfDelega
	 */

}
