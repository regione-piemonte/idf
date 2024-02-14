/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
//
// Created on 2020-10-04 ( Date ISO 2020-10-04 - Time 21:09:07 )
// java7-persistance-jdbc-T300
package it.csi.idf.idfapi.business.be.vincolo.service;

import java.util.List;

import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfCnfDelega;
/**
 * IdfCnfDelega Service  
 */
public interface IdfCnfDelegaService   {
    
    
	/* (non-Javadoc)
	 * @see interface 
	 */
	public IdfCnfDelega findById( String cfDelegante, Integer idConfigUtente );
	//----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	public List<IdfCnfDelega> findAll() ;

	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param idfCnfDelega Dto
	 * @return true if found, false if not found
	 */
	public boolean load( IdfCnfDelega idfCnfDelega );
    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param idfCnfDelega
	 */
	public long insert(IdfCnfDelega idfCnfDelega);

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	public IdfCnfDelega create(IdfCnfDelega idfCnfDelega);

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	public boolean update(IdfCnfDelega idfCnfDelega);
    
    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	public boolean delete( String cfDelegante, Integer idConfigUtente );

	/* (non-Javadoc)
	 * @see interface 
	 */
	public boolean delete( IdfCnfDelega idfCnfDelega );

	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param cfDelegante;
	 * @param idConfigUtente;
	 * @return
	 */
	public boolean exists( String cfDelegante, Integer idConfigUtente );
	/**
	 * Checks the existence of the given bean in the database 
	 * @param idfCnfDelega
	 * @return
	 */
	public boolean exists( IdfCnfDelega idfCnfDelega ) ;
	/**
	 * Counts all the records present in the database
	 * @return
	 */
	public long count() ;
	public boolean deleteById(String cfDelegante, Integer idConfigUtente);

   
}
