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

import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfTIntervVincIdro;
/**
 * IdfTIntervVincIdro Service  
 */
public interface IdfTIntervVincIdroService   {
    
    
	/* (non-Javadoc)
	 * @see interface 
	 */
	public IdfTIntervVincIdro findById( BigDecimal idIntervento );
	//----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	public List<IdfTIntervVincIdro> findAll() ;

	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param idfTIntervVincIdro Dto
	 * @return true if found, false if not found
	 */
	public boolean load( IdfTIntervVincIdro idfTIntervVincIdro );
    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param idfTIntervVincIdro
	 */
	public long insert(IdfTIntervVincIdro idfTIntervVincIdro);

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	public IdfTIntervVincIdro create(IdfTIntervVincIdro idfTIntervVincIdro);

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	public boolean update(IdfTIntervVincIdro idfTIntervVincIdro);
    
    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	public boolean deleteById( BigDecimal idIntervento );

	/* (non-Javadoc)
	 * @see interface 
	 */
	public boolean delete( IdfTIntervVincIdro idfTIntervVincIdro );

	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param idIntervento;
	 * @return
	 */
	public boolean exists( BigDecimal idIntervento );
	/**
	 * Checks the existence of the given bean in the database 
	 * @param idfTIntervVincIdro
	 * @return
	 */
	public boolean exists( IdfTIntervVincIdro idfTIntervVincIdro ) ;
	/**
	 * Counts all the records present in the database
	 * @return
	 */
	public long count() ;

   
}
