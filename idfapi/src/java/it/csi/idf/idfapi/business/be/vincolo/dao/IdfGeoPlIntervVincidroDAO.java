/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.dao;


import java.util.List;

import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfGeoPlIntervVincidro;

/**
 * Dao Interface for IdfGeoPlIntervVincidro.
 */
public interface IdfGeoPlIntervVincidroDAO { 

	/**
	 * Tries t o find an entity using its Id / Primary Key
	 * @param idGeoPlIntervVincidro
	 * @return entity
	 */
	IdfGeoPlIntervVincidro findById( Integer idGeoPlIntervVincidro  ) ;

	/**
	 * Finds all entities.
	 * @return all entities
	 */
	List<IdfGeoPlIntervVincidro> findAll();

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
	IdfGeoPlIntervVincidro save(IdfGeoPlIntervVincidro entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return true if the entity has been updated, false if not found and not updated
	 */
	boolean update(IdfGeoPlIntervVincidro entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	IdfGeoPlIntervVincidro create(IdfGeoPlIntervVincidro entity);

	/**
	 * Deletes an entity using its Id / Primary Key
	 * @param idGeoPlIntervVincidro
	 * @return true if the entity has been deleted, false if not found and not deleted
	 */
	boolean deleteById( Integer idGeoPlIntervVincidro );
	
	/**
	 * Deletes an entity using its fkIntervento
	 * @param fkIntervento
	 * @return the number of deleted items
	 */
	int deleteByFkIntervento( Integer fkIntervento );

	/**
	 * Deletes an entity using the Id / Primary Key stored in the given object
	 * @param the entity to be deleted (supposed to have a valid Id/PK )
	 * @return true if the entity has been deleted, false if not found and not deleted
	 */
	boolean delete( IdfGeoPlIntervVincidro entity );

	/**
	 * Returns true if an entity exists with the given Id / Primary Key 
	 * @param idGeoPlIntervVincidro
	 * @return
	 */
	public boolean exists( Integer idGeoPlIntervVincidro ) ;

	/**
	 * Returns true if the given entity exists
	 * @param entity
	 * @return
	 */
	public boolean exists( IdfGeoPlIntervVincidro entity ) ;

	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param idfGeoPlIntervVincidro
	 * @return true if found, false if not found
	 */
	boolean load(IdfGeoPlIntervVincidro idfGeoPlIntervVincidro);

	long insert(IdfGeoPlIntervVincidro idfGeoPlIntervVincidro);
	
	void insertIntervVincidro(IdfGeoPlIntervVincidro idfGeoPlIntervVincidro);
	
	void duplicateIntervento(Integer idIntervento, Integer idInterventoNew, Integer idConfUtente);

}
