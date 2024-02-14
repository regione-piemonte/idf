/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
//
// Created on 2020-10-04 ( Date ISO 2020-10-04 - Time 22:37:06 )
// java7-persistance-jdbc-T300
package it.csi.idf.idfapi.business.be.vincolo.service;

import java.util.List;

import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfTSoggetto;
/**
 * IdfTSoggetto Service  
 */
public interface IdfTSoggettoService   {
    
    
	/* (non-Javadoc)
	 * @see interface 
	 */
	public IdfTSoggetto findById( Integer idSoggetto );
	//----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	public List<IdfTSoggetto> findAll() ;

	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param idfTSoggetto Dto
	 * @return true if found, false if not found
	 */
	public boolean load( IdfTSoggetto idfTSoggetto );
    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param idfTSoggetto
	 */
	public long insert(IdfTSoggetto idfTSoggetto);

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	public IdfTSoggetto create(IdfTSoggetto idfTSoggetto);

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	public boolean update(IdfTSoggetto idfTSoggetto);
    
    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	public boolean deleteById( Integer idSoggetto );

	/* (non-Javadoc)
	 * @see interface 
	 */
	public boolean delete( IdfTSoggetto idfTSoggetto );

	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param idSoggetto;
	 * @return
	 */
	public boolean exists( Integer idSoggetto );
	/**
	 * Checks the existence of the given bean in the database 
	 * @param idfTSoggetto
	 * @return
	 */
	public boolean exists( IdfTSoggetto idfTSoggetto ) ;
	/**
	 * Counts all the records present in the database
	 * @return
	 */
	public long count() ;

   
}
