/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.service;

import java.util.List;

import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfGeoPlIntervVincidro;
/**
 * IdfGeoPlIntervVincidro Service  
 */
public interface IdfGeoPlIntervVincidroService   {
    
    
	/* (non-Javadoc)
	 * @see interface 
	 */
	public IdfGeoPlIntervVincidro findById( Integer idGeoPlIntervVincidro );
	//----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	public List<IdfGeoPlIntervVincidro> findAll() ;

	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param idfGeoPlIntervVincidro Dto
	 * @return true if found, false if not found
	 */
	public boolean load( IdfGeoPlIntervVincidro idfGeoPlIntervVincidro );
    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param idfGeoPlIntervVincidro
	 */
	public long insert(IdfGeoPlIntervVincidro idfGeoPlIntervVincidro);

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	public IdfGeoPlIntervVincidro create(IdfGeoPlIntervVincidro idfGeoPlIntervVincidro);

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	public boolean update(IdfGeoPlIntervVincidro idfGeoPlIntervVincidro);
    
    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	public boolean deleteById( Integer idGeoPlIntervVincidro );

	/* (non-Javadoc)
	 * @see interface 
	 */
	public boolean delete( IdfGeoPlIntervVincidro idfGeoPlIntervVincidro );

	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param idGeoPlIntervVincidro;
	 * @return
	 */
	public boolean exists( Integer idGeoPlIntervVincidro );
	/**
	 * Checks the existence of the given bean in the database 
	 * @param idfGeoPlIntervVincidro
	 * @return
	 */
	public boolean exists( IdfGeoPlIntervVincidro idfGeoPlIntervVincidro ) ;
	/**
	 * Counts all the records present in the database
	 * @return
	 */
	public long count() ;

   
}
