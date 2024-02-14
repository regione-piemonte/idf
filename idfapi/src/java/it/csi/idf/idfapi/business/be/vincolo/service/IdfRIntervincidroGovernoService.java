/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
//
// Created on 2020-10-04 ( Date ISO 2020-10-04 - Time 22:37:06 )
// java7-persistance-jdbc-T300
package it.csi.idf.idfapi.business.be.vincolo.service;

import java.math.BigDecimal;
import java.util.List;

import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfRIntervincidroGoverno;
/**
 * IdfRIntervincidroGoverno Service  
 */
public interface IdfRIntervincidroGovernoService   {
    
    
	/* (non-Javadoc)
	 * @see interface 
	 */
	public IdfRIntervincidroGoverno findById( BigDecimal idIntervento, Integer idGoverno );
	//----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	public List<IdfRIntervincidroGoverno> findAll() ;

	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param idfRIntervincidroGoverno Dto
	 * @return true if found, false if not found
	 */
	public boolean load( IdfRIntervincidroGoverno idfRIntervincidroGoverno );
    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param idfRIntervincidroGoverno
	 */
	public long insert(IdfRIntervincidroGoverno idfRIntervincidroGoverno);

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	public IdfRIntervincidroGoverno create(IdfRIntervincidroGoverno idfRIntervincidroGoverno);

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	public boolean update(IdfRIntervincidroGoverno idfRIntervincidroGoverno);
    
    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	public boolean deleteById( BigDecimal idIntervento, Integer idGoverno );

	/* (non-Javadoc)
	 * @see interface 
	 */
	public boolean delete( IdfRIntervincidroGoverno idfRIntervincidroGoverno );

	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param idIntervento;
	 * @param idGoverno;
	 * @return
	 */
	public boolean exists( BigDecimal idIntervento, Integer idGoverno );
	/**
	 * Checks the existence of the given bean in the database 
	 * @param idfRIntervincidroGoverno
	 * @return
	 */
	public boolean exists( IdfRIntervincidroGoverno idfRIntervincidroGoverno ) ;
	/**
	 * Counts all the records present in the database
	 * @return
	 */
	public long count() ;

   
}
