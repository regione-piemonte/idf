/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.dao;

import java.math.BigDecimal;

//
// Created on 2020-10-04 ( Date ISO 2020-10-04 - Time 22:37:05 )
// java7-persistance-jdbc-T300package it.csi.idf.idfapi.validation.dao;


import java.util.List;

import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfCnfTipoallTipoint;
import it.csi.idf.idfapi.dto.TipoAllegatoExtended;

/**
 * Dao Interface for IdfCnfTipoallTipoint.
 */
public interface IdfCnfTipoallTipointDAO { 

	/**
	 * Tries t o find an entity using its Id / Primary Key
	 * @param idTipoAllegato
	 * @param idTipoIntervento
	 * @return entity
	 */
	IdfCnfTipoallTipoint findById( Integer idTipoAllegato, BigDecimal idTipoIntervento  ) ;

	/**
	 * Finds all entities.
	 * @return all entities
	 */
	List<IdfCnfTipoallTipoint> findAll();

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
	IdfCnfTipoallTipoint save(IdfCnfTipoallTipoint entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return true if the entity has been updated, false if not found and not updated
	 */
	boolean update(IdfCnfTipoallTipoint entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	IdfCnfTipoallTipoint create(IdfCnfTipoallTipoint entity);

	/**
	 * Deletes an entity using its Id / Primary Key
	 * @param idTipoAllegato
	 * @param idTipoIntervento
	 * @return true if the entity has been deleted, false if not found and not deleted
	 */
	boolean deleteById( Integer idTipoAllegato, BigDecimal idTipoIntervento );

	/**
	 * Deletes an entity using the Id / Primary Key stored in the given object
	 * @param the entity to be deleted (supposed to have a valid Id/PK )
	 * @return true if the entity has been deleted, false if not found and not deleted
	 */
	boolean delete( IdfCnfTipoallTipoint entity );

	/**
	 * Returns true if an entity exists with the given Id / Primary Key 
	 * @param idTipoAllegato
	 * @param idTipoIntervento
	 * @return
	 */
	public boolean exists( Integer idTipoAllegato, BigDecimal idTipoIntervento ) ;

	/**
	 * Returns true if the given entity exists
	 * @param entity
	 * @return
	 */
	public boolean exists( IdfCnfTipoallTipoint entity ) ;

	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param idfCnfTipoallTipoint
	 * @return true if found, false if not found
	 */
	boolean load(IdfCnfTipoallTipoint idfCnfTipoallTipoint);

	/**
	 * Inserts the given bean in the database 
	 * @param idfCnfTipoallTipoint
	 */
	long insert(IdfCnfTipoallTipoint idfCnfTipoallTipoint);

	
	public List<TipoAllegatoExtended> getTipiDocumentoByIdTipoIntervento(BigDecimal idIntervento);

}
