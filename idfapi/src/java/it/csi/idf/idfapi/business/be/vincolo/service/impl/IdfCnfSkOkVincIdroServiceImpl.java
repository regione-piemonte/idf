/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.service.impl;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.vincolo.dao.IdfCnfSkOkVincIdroDAO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfCnfSkOkVincIdro;
import it.csi.idf.idfapi.business.be.vincolo.service.GenericJdbcService;
import it.csi.idf.idfapi.business.be.vincolo.service.IdfCnfSkOkVincIdroService;

/**
 * IdfCnfSkOkVincIdro Service implementation 
 * 
 */
//@Named("IdfCnfSkOkVincIdroService")
@Component
public class IdfCnfSkOkVincIdroServiceImpl extends GenericJdbcService<IdfCnfSkOkVincIdro> implements IdfCnfSkOkVincIdroService {
    
    @Autowired
	private IdfCnfSkOkVincIdroDAO idfCnfSkOkVincIdroDao;

    //----------------------------------------------------------------------
	/**
	 * Service constructor
	 */
	public IdfCnfSkOkVincIdroServiceImpl() {
		super();
	}

	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfCnfSkOkVincIdro findById( BigDecimal idIntervento ) {
		IdfCnfSkOkVincIdro idfCnfSkOkVincIdro = idfCnfSkOkVincIdroDao.findById(idIntervento) ;
		if ( idfCnfSkOkVincIdro != null ) {
			return idfCnfSkOkVincIdro ;
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
	public List<IdfCnfSkOkVincIdro> findAll() {
		return idfCnfSkOkVincIdroDao.findAll();
	}

	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param idfCnfSkOkVincIdro Dto
	 * @return true if found, false if not found
	 */
	@Override
	public boolean load( IdfCnfSkOkVincIdro idfCnfSkOkVincIdro ) {
		return idfCnfSkOkVincIdroDao.load(idfCnfSkOkVincIdro) ;
	}

    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param idfCnfSkOkVincIdro
	 */
	@Override
	public long insert(IdfCnfSkOkVincIdro idfCnfSkOkVincIdro) {
		idfCnfSkOkVincIdroDao.insert(idfCnfSkOkVincIdro);
		return 0L ;
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfCnfSkOkVincIdro create(IdfCnfSkOkVincIdro idfCnfSkOkVincIdro) {
		idfCnfSkOkVincIdroDao.create(idfCnfSkOkVincIdro);
		return idfCnfSkOkVincIdro ;
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean update(IdfCnfSkOkVincIdro idfCnfSkOkVincIdro) {
		return  idfCnfSkOkVincIdroDao.update(idfCnfSkOkVincIdro);

	}	

    

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean deleteById( BigDecimal idIntervento ) {
		return  idfCnfSkOkVincIdroDao.deleteById(idIntervento);
		
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean delete( IdfCnfSkOkVincIdro idfCnfSkOkVincIdro ) {
		return idfCnfSkOkVincIdroDao.delete(idfCnfSkOkVincIdro);
		
	}

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param idIntervento;
	 * @return
	 */
	 @Override
	public boolean exists( BigDecimal idIntervento ) {
		return idfCnfSkOkVincIdroDao.exists(idIntervento);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param idfCnfSkOkVincIdro
	 * @return
	 */
	 @Override
	public boolean exists( IdfCnfSkOkVincIdro idfCnfSkOkVincIdro ) {
		return idfCnfSkOkVincIdroDao.exists(idfCnfSkOkVincIdro);
	}

    //----------------------------------------------------------------------
	/**
	 * Counts all the records present in the database
	 * @return
	 */
	@Override
	public long count() {
		return idfCnfSkOkVincIdroDao.countAll();
	}

   
}
