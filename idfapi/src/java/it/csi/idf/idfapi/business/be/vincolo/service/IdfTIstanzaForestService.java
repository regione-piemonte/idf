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

import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfTIstanzaForest;
/**
 * IdfTIstanzaForest Service  
 */
public interface IdfTIstanzaForestService   {
    
    
	/* (non-Javadoc)
	 * @see interface 
	 */
	public IdfTIstanzaForest findById( BigDecimal idIstanzaIntervento );
	//----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	public List<IdfTIstanzaForest> findAll() ;

	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param idfTIstanzaForest Dto
	 * @return true if found, false if not found
	 */
	public boolean load( IdfTIstanzaForest idfTIstanzaForest );
    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param idfTIstanzaForest
	 */
	public long insert(IdfTIstanzaForest idfTIstanzaForest);

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	public IdfTIstanzaForest create(IdfTIstanzaForest idfTIstanzaForest);

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	public boolean update(IdfTIstanzaForest idfTIstanzaForest);
    
    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	public boolean deleteById( BigDecimal idIstanzaIntervento );

	/* (non-Javadoc)
	 * @see interface 
	 */
	public boolean delete( IdfTIstanzaForest idfTIstanzaForest );

	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param idIstanzaIntervento;
	 * @return
	 */
	public boolean exists( BigDecimal idIstanzaIntervento );
	/**
	 * Checks the existence of the given bean in the database 
	 * @param idfTIstanzaForest
	 * @return
	 */
	public boolean exists( IdfTIstanzaForest idfTIstanzaForest ) ;
	/**
	 * Counts all the records present in the database
	 * @return
	 */
	public long count() ;

   
}
