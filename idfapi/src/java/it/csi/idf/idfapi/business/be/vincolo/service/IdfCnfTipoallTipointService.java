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

import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfCnfTipoallTipoint;
/**
 * IdfCnfTipoallTipoint Service  
 */
public interface IdfCnfTipoallTipointService   {
    
    
	/* (non-Javadoc)
	 * @see interface 
	 */
	public IdfCnfTipoallTipoint findById( Integer idTipoAllegato, BigDecimal idTipoIntervento );
	//----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	public List<IdfCnfTipoallTipoint> findAll() ;

	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param idfCnfTipoallTipoint Dto
	 * @return true if found, false if not found
	 */
	public boolean load( IdfCnfTipoallTipoint idfCnfTipoallTipoint );
    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param idfCnfTipoallTipoint
	 */
	public long insert(IdfCnfTipoallTipoint idfCnfTipoallTipoint);

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	public IdfCnfTipoallTipoint create(IdfCnfTipoallTipoint idfCnfTipoallTipoint);

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	public boolean update(IdfCnfTipoallTipoint idfCnfTipoallTipoint);
    
    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	public boolean deleteById( Integer idTipoAllegato, BigDecimal idTipoIntervento );
	/* (non-Javadoc)
	 * @see interface 
	 */
	public boolean delete( IdfCnfTipoallTipoint idfCnfTipoallTipoint );

	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param idTipoAllegato;
	 * @param idTipoIntervento;
	 * @return
	 */
	public boolean exists( Integer idTipoAllegato, BigDecimal idTipoIntervento );
	/**
	 * Checks the existence of the given bean in the database 
	 * @param idfCnfTipoallTipoint
	 * @return
	 */
	public boolean exists( IdfCnfTipoallTipoint idfCnfTipoallTipoint ) ;
	/**
	 * Counts all the records present in the database
	 * @return
	 */
	public long count() ;
	

   
}
