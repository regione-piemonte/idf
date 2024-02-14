/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.service.impl;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.vincolo.dao.IdfTIntervVincIdroDAO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfTIntervVincIdro;
import it.csi.idf.idfapi.business.be.vincolo.service.GenericJdbcService;
import it.csi.idf.idfapi.business.be.vincolo.service.IdfTIntervVincIdroService;

/**
 * IdfTIntervVincIdro Service implementation 
 * 
 */
//@Named("IdfTIntervVincIdroService")
@Component
public class IdfTIntervVincIdroServiceImpl extends GenericJdbcService<IdfTIntervVincIdro> implements IdfTIntervVincIdroService {
    
    @Autowired
	private IdfTIntervVincIdroDAO idfTIntervVincIdroDao;

    //----------------------------------------------------------------------
	/**
	 * Service constructor
	 */
	public IdfTIntervVincIdroServiceImpl() {
		super();
	}

	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfTIntervVincIdro findById( BigDecimal idIntervento ) {
		IdfTIntervVincIdro idfTIntervVincIdro = idfTIntervVincIdroDao.findById(idIntervento) ;
		if ( idfTIntervVincIdro != null ) {
			return idfTIntervVincIdro ;
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
	public List<IdfTIntervVincIdro> findAll() {
		return idfTIntervVincIdroDao.findAll();
	}

	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param idfTIntervVincIdro Dto
	 * @return true if found, false if not found
	 */
	@Override
	public boolean load( IdfTIntervVincIdro idfTIntervVincIdro ) {
		return idfTIntervVincIdroDao.load(idfTIntervVincIdro) ;
	}

    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param idfTIntervVincIdro
	 */
	@Override
	public long insert(IdfTIntervVincIdro idfTIntervVincIdro) {
		idfTIntervVincIdroDao.create(idfTIntervVincIdro);
		return 0L ;
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfTIntervVincIdro create(IdfTIntervVincIdro idfTIntervVincIdro) {
		idfTIntervVincIdroDao.create(idfTIntervVincIdro);
		return idfTIntervVincIdro ;
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean update(IdfTIntervVincIdro idfTIntervVincIdro) {
		return  idfTIntervVincIdroDao.update(idfTIntervVincIdro);

	}	

    

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean deleteById( BigDecimal idIntervento ) {
		return  idfTIntervVincIdroDao.deleteById(idIntervento);
		
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean delete( IdfTIntervVincIdro idfTIntervVincIdro ) {
		return idfTIntervVincIdroDao.delete(idfTIntervVincIdro);
		
	}

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param idIntervento;
	 * @return
	 */
	 @Override
	public boolean exists( BigDecimal idIntervento ) {
		return idfTIntervVincIdroDao.exists(idIntervento);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param idfTIntervVincIdro
	 * @return
	 */
	 @Override
	public boolean exists( IdfTIntervVincIdro idfTIntervVincIdro ) {
		return idfTIntervVincIdroDao.exists(idfTIntervVincIdro);
	}

    //----------------------------------------------------------------------
	/**
	 * Counts all the records present in the database
	 * @return
	 */
	@Override
	public long count() {
		return idfTIntervVincIdroDao.countAll();
	}

   
}
