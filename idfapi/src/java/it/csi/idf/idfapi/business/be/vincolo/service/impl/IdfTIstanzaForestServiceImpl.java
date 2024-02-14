/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.service.impl;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.vincolo.dao.IdfTIstanzaForestDAO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfTIstanzaForest;
import it.csi.idf.idfapi.business.be.vincolo.service.GenericJdbcService;
import it.csi.idf.idfapi.business.be.vincolo.service.IdfTIstanzaForestService;

/**
 * IdfTIstanzaForest Service implementation 
 * 
 */
//@Named("IdfTIstanzaForestService")
@Component
public class IdfTIstanzaForestServiceImpl extends GenericJdbcService<IdfTIstanzaForest> implements IdfTIstanzaForestService {
    
    @Autowired
	private IdfTIstanzaForestDAO idfTIstanzaForestDao;

    //----------------------------------------------------------------------
	/**
	 * Service constructor
	 */
	public IdfTIstanzaForestServiceImpl() {
		super();
	}

	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfTIstanzaForest findById( BigDecimal idIstanzaIntervento ) {
		IdfTIstanzaForest idfTIstanzaForest = idfTIstanzaForestDao.findById(idIstanzaIntervento) ;
		if ( idfTIstanzaForest != null ) {
			return idfTIstanzaForest ;
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
	public List<IdfTIstanzaForest> findAll() {
		return idfTIstanzaForestDao.findAll();
	}

	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param idfTIstanzaForest Dto
	 * @return true if found, false if not found
	 */
	@Override
	public boolean load( IdfTIstanzaForest idfTIstanzaForest ) {
		return idfTIstanzaForestDao.load(idfTIstanzaForest) ;
	}

    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param idfTIstanzaForest
	 */
	@Override
	public long insert(IdfTIstanzaForest idfTIstanzaForest) {
		idfTIstanzaForestDao.create(idfTIstanzaForest);
		return 0L ;
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfTIstanzaForest create(IdfTIstanzaForest idfTIstanzaForest) {
		idfTIstanzaForestDao.create(idfTIstanzaForest);
		return idfTIstanzaForest ;
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean update(IdfTIstanzaForest idfTIstanzaForest) {
		return  idfTIstanzaForestDao.update(idfTIstanzaForest);

	}	

    

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean deleteById( BigDecimal idIstanzaIntervento ) {
		return  idfTIstanzaForestDao.deleteById(idIstanzaIntervento);
		
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean delete( IdfTIstanzaForest idfTIstanzaForest ) {
		return idfTIstanzaForestDao.delete(idfTIstanzaForest);
		
	}

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param idIstanzaIntervento;
	 * @return
	 */
	 @Override
	public boolean exists( BigDecimal idIstanzaIntervento ) {
		return idfTIstanzaForestDao.exists(idIstanzaIntervento);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param idfTIstanzaForest
	 * @return
	 */
	 @Override
	public boolean exists( IdfTIstanzaForest idfTIstanzaForest ) {
		return idfTIstanzaForestDao.exists(idfTIstanzaForest);
	}

    //----------------------------------------------------------------------
	/**
	 * Counts all the records present in the database
	 * @return
	 */
	@Override
	public long count() {
		return idfTIstanzaForestDao.countAll();
	}

   
}
