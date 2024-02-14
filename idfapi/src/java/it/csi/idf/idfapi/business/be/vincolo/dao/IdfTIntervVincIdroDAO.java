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

import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfTIntervVincIdro;
import it.csi.idf.idfapi.dto.InfoVarianteProroga;

/**
 * Dao Interface for IdfTIntervVincIdro.
 */
public interface IdfTIntervVincIdroDAO { 

	/**
	 * Tries t o find an entity using its Id / Primary Key
	 * @param idIntervento
	 * @return entity
	 */
	IdfTIntervVincIdro findById( BigDecimal idIntervento  ) ;

	/**
	 * Finds all entities.
	 * @return all entities
	 */
	List<IdfTIntervVincIdro> findAll();

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
	IdfTIntervVincIdro save(IdfTIntervVincIdro entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return true if the entity has been updated, false if not found and not updated
	 */
	boolean update(IdfTIntervVincIdro entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	int create(IdfTIntervVincIdro entity);

	/**
	 * Deletes an entity using its Id / Primary Key
	 * @param idIntervento
	 * @return true if the entity has been deleted, false if not found and not deleted
	 */
	boolean deleteById( BigDecimal idIntervento );
	
	/**
	 * Deletes an entity using its Id / Primary Key
	 * @param idIntervento
	 * @return true if the entity has been deleted, false if not found and not deleted
	 */
	boolean deleteById( int idIntervento );

	/**
	 * Deletes an entity using the Id / Primary Key stored in the given object
	 * @param the entity to be deleted (supposed to have a valid Id/PK )
	 * @return true if the entity has been deleted, false if not found and not deleted
	 */
	boolean delete( IdfTIntervVincIdro entity );

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
	public boolean exists( IdfTIntervVincIdro entity ) ;

	boolean load(IdfTIntervVincIdro idfTIntervVincIdro);
	
	List<String> getGeometrieGmlByFkIntervento(Integer idIntervento);

	List<String> getGeometrieGeoJSONByFkIntervento(Integer idIntervento);
	
	Double getAreaItersecWithParticella(Integer idIntervento, Integer idgeo_pl_prop_catasto);
	
	Double getAreaTrasformazioneByFkIntervento(Integer idIntervento);
	
	void addGeometria(Integer idIntervento, Object geometriGml);

	Double getAreaSuperficieBoscataByFkIntervento(Integer idIntervento);

	Double getAreaSuperficieInVincoloByFkIntervento(Integer idIntervento);

	Double getAreaSuperficieBoscataInVincoloByFkIntervento(Integer idIntervento);

	void removeGeometria(Integer idIntervento, Integer idGeoPlPropCatasto);
	
	boolean isCauzioneMancante(Integer idIntervento);
	boolean isCompensazioneFisicaMancante(Integer idIntervento);
	boolean isCompensazioneMonetariaMancante(Integer idIntervento);
	
	InfoVarianteProroga getInfoVarianteProroga(Integer idIntervento);
	
	void updateTitolaritaIntervento(Integer idIntervento, Integer idConfUtente);



}
