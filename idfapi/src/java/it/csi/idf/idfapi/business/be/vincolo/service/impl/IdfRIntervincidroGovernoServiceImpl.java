/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.service.impl;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.vincolo.dao.IdfRIntervincidroGovernoDAO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfRIntervincidroGoverno;
import it.csi.idf.idfapi.business.be.vincolo.service.GenericJdbcService;
import it.csi.idf.idfapi.business.be.vincolo.service.IdfRIntervincidroGovernoService;

/**
 * IdfRIntervincidroGoverno Service implementation 
 * 
 */
//@Named("IdfRIntervincidroGovernoService")
@Component
public class IdfRIntervincidroGovernoServiceImpl extends GenericJdbcService<IdfRIntervincidroGoverno> implements IdfRIntervincidroGovernoService {
    
    @Autowired
	private IdfRIntervincidroGovernoDAO idfRIntervincidroGovernoDao;

    //----------------------------------------------------------------------
	/**
	 * Service constructor
	 */
	public IdfRIntervincidroGovernoServiceImpl() {
		super();
	}

	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfRIntervincidroGoverno findById( BigDecimal idIntervento, Integer idGoverno ) {
		IdfRIntervincidroGoverno idfRIntervincidroGoverno = idfRIntervincidroGovernoDao.findById(idIntervento, idGoverno) ;
		if ( idfRIntervincidroGoverno != null ) {
			return idfRIntervincidroGoverno ;
		}
		else {
			return null ; // Not found
		}
	}
	//----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public List<IdfRIntervincidroGoverno> findAll() {
		return idfRIntervincidroGovernoDao.findAll();
	}

	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param idfRIntervincidroGoverno Dto
	 * @return true if found, false if not found
	 */
	@Override
	public boolean load( IdfRIntervincidroGoverno idfRIntervincidroGoverno ) {
		return idfRIntervincidroGovernoDao.load(idfRIntervincidroGoverno) ;
	}

    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param idfRIntervincidroGoverno
	 */
	@Override
	public long insert(IdfRIntervincidroGoverno idfRIntervincidroGoverno) {
		idfRIntervincidroGovernoDao.save(idfRIntervincidroGoverno);
		return 0L ;
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfRIntervincidroGoverno create(IdfRIntervincidroGoverno idfRIntervincidroGoverno) {
		idfRIntervincidroGovernoDao.create(idfRIntervincidroGoverno);
		return idfRIntervincidroGoverno ;
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean update(IdfRIntervincidroGoverno idfRIntervincidroGoverno) {
		return  idfRIntervincidroGovernoDao.update(idfRIntervincidroGoverno);

	}	

    

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean deleteById( BigDecimal idIntervento, Integer idGoverno ) {
		return  idfRIntervincidroGovernoDao.deleteById(idIntervento, idGoverno);
		
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean delete( IdfRIntervincidroGoverno idfRIntervincidroGoverno ) {
		return idfRIntervincidroGovernoDao.delete(idfRIntervincidroGoverno);
		
	}

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param idIntervento;
	 * @param idGoverno;
	 * @return
	 */
	 @Override
	public boolean exists( BigDecimal idIntervento, Integer idGoverno ) {
		return idfRIntervincidroGovernoDao.exists(idIntervento, idGoverno);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param idfRIntervincidroGoverno
	 * @return
	 */
	 @Override
	public boolean exists( IdfRIntervincidroGoverno idfRIntervincidroGoverno ) {
		return idfRIntervincidroGovernoDao.exists(idfRIntervincidroGoverno);
	}

    //----------------------------------------------------------------------
	/**
	 * Counts all the records present in the database
	 * @return
	 */
	@Override
	public long count() {
		return idfRIntervincidroGovernoDao.countAll();
	}

   
}
