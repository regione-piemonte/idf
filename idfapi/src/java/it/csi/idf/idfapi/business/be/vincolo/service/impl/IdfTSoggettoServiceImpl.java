/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.vincolo.dao.IdfTSoggettoDAO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfTSoggetto;
import it.csi.idf.idfapi.business.be.vincolo.service.GenericJdbcService;
import it.csi.idf.idfapi.business.be.vincolo.service.IdfTSoggettoService;

/**
 * IdfTSoggetto Service implementation 
 * 
 */
//@Named("IdfTSoggettoService")
@Component
public class IdfTSoggettoServiceImpl extends GenericJdbcService<IdfTSoggetto> implements IdfTSoggettoService {
    
    @Autowired
	private IdfTSoggettoDAO idfTSoggettoDao;

    //----------------------------------------------------------------------
	/**
	 * Service constructor
	 */
	public IdfTSoggettoServiceImpl() {
		super();
	}

	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfTSoggetto findById( Integer idSoggetto ) {
		IdfTSoggetto idfTSoggetto = idfTSoggettoDao.findById(idSoggetto) ;
		if ( idfTSoggetto != null ) {
			return idfTSoggetto ;
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
	public List<IdfTSoggetto> findAll() {
		
		return idfTSoggettoDao.findAll();
	}

	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param idfTSoggetto Dto
	 * @return true if found, false if not found
	 */
	@Override
	public boolean load( IdfTSoggetto idfTSoggetto ) {
		return idfTSoggettoDao.load(idfTSoggetto) ;
	}

    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param idfTSoggetto
	 */
	@Override
	public long insert(IdfTSoggetto idfTSoggetto) {
		 idfTSoggettoDao.create(idfTSoggetto);
		return 0L ;
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfTSoggetto create(IdfTSoggetto idfTSoggetto) {
		idfTSoggettoDao.create(idfTSoggetto);
		return idfTSoggetto ;
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean update(IdfTSoggetto idfTSoggetto) {
		return  idfTSoggettoDao.update(idfTSoggetto);

	}	

    

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean deleteById( Integer idSoggetto ) {
		return  idfTSoggettoDao.deleteById(idSoggetto);
		
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean delete( IdfTSoggetto idfTSoggetto ) {
		return idfTSoggettoDao.delete(idfTSoggetto);
		
	}

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param idSoggetto;
	 * @return
	 */
	 @Override
	public boolean exists( Integer idSoggetto ) {
		return idfTSoggettoDao.exists(idSoggetto);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param idfTSoggetto
	 * @return
	 */
	 @Override
	public boolean exists( IdfTSoggetto idfTSoggetto ) {
		return idfTSoggettoDao.exists(idfTSoggetto);
	}

    //----------------------------------------------------------------------
	/**
	 * Counts all the records present in the database
	 * @return
	 */
	@Override
	public long count() {
		return idfTSoggettoDao.countAll();
	}

   
}
