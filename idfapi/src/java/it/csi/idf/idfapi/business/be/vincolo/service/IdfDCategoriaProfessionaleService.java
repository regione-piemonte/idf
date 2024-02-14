/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
//
// Created on 2020-10-04 ( Date ISO 2020-10-04 - Time 22:37:05 )
// java7-persistance-jdbc-T300
package it.csi.idf.idfapi.business.be.vincolo.service;

import java.math.BigDecimal;
import java.util.List;

import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfDCategoriaProfessionale;
/**
 * IdfDCategoriaProfessionale Service  
 */
public interface IdfDCategoriaProfessionaleService   {
    
    
	/* (non-Javadoc)
	 * @see interface 
	 */
	public IdfDCategoriaProfessionale findById( BigDecimal idCategoriaProfessionale );
	//----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	public List<IdfDCategoriaProfessionale> findAll() ;

	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param idfDCategoriaProfessionale Dto
	 * @return true if found, false if not found
	 */
	public boolean load( IdfDCategoriaProfessionale idfDCategoriaProfessionale );
    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param idfDCategoriaProfessionale
	 */
	public long insert(IdfDCategoriaProfessionale idfDCategoriaProfessionale);

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	public IdfDCategoriaProfessionale create(IdfDCategoriaProfessionale idfDCategoriaProfessionale);

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	public boolean update(IdfDCategoriaProfessionale idfDCategoriaProfessionale);
    
    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	public boolean deleteById( BigDecimal idCategoriaProfessionale );

	/* (non-Javadoc)
	 * @see interface 
	 */
	public boolean delete( IdfDCategoriaProfessionale idfDCategoriaProfessionale );

	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param idCategoriaProfessionale;
	 * @return
	 */
	public boolean exists( BigDecimal idCategoriaProfessionale );
	/**
	 * Checks the existence of the given bean in the database 
	 * @param idfDCategoriaProfessionale
	 * @return
	 */
	public boolean exists( IdfDCategoriaProfessionale idfDCategoriaProfessionale ) ;
	/**
	 * Counts all the records present in the database
	 * @return
	 */
	public long count() ;

   
}
