/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.vincolo.dao.IdfCnfDelegaDAO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfCnfDelega;
import it.csi.idf.idfapi.business.be.vincolo.service.GenericJdbcService;
import it.csi.idf.idfapi.business.be.vincolo.service.IdfCnfDelegaService;

/**
 * IdfCnfDelega Service implementation 
 * 
 */
//@Named("IdfCnfDelegaService")
@Component
public class IdfCnfDelegaServiceImpl extends GenericJdbcService<IdfCnfDelega> implements IdfCnfDelegaService {
    
    @Autowired
	private IdfCnfDelegaDAO idfCnfDelegaDao;

    //----------------------------------------------------------------------
	/**
	 * Service constructor
	 */
	public IdfCnfDelegaServiceImpl() {
		super();
	}

	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfCnfDelega findById( String cfDelegante, Integer idConfigUtente ) {
		IdfCnfDelega idfCnfDelega = idfCnfDelegaDao.findById(cfDelegante, idConfigUtente) ;
		if ( idfCnfDelega != null ) {
			return idfCnfDelega ;
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
	public List<IdfCnfDelega> findAll() {
		return idfCnfDelegaDao.findAll();
	}

	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param idfCnfDelega Dto
	 * @return true if found, false if not found
	 */
	@Override
	public boolean load( IdfCnfDelega idfCnfDelega ) {
		return idfCnfDelegaDao.load(idfCnfDelega) ;
	}

    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param idfCnfDelega
	 */
	@Override
	public long insert(IdfCnfDelega idfCnfDelega) {
		idfCnfDelegaDao.create(idfCnfDelega);
		return 0L ;
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfCnfDelega create(IdfCnfDelega idfCnfDelega) {
		idfCnfDelegaDao.create(idfCnfDelega);
		return idfCnfDelega ;
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean update(IdfCnfDelega idfCnfDelega) {
		return  idfCnfDelegaDao.update(idfCnfDelega);

	}	

    

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean delete( String cfDelegante, Integer idConfigUtente ) {
		return  idfCnfDelegaDao.deleteById(cfDelegante, idConfigUtente);
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean delete( IdfCnfDelega idfCnfDelega ) {
		return idfCnfDelegaDao.delete(idfCnfDelega);
		
	}

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param cfDelegante;
	 * @param idConfigUtente;
	 * @return
	 */
	// @Override
	@Override
	public boolean exists( String cfDelegante, Integer idConfigUtente ) {
		return idfCnfDelegaDao.exists(cfDelegante, idConfigUtente);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param idfCnfDelega
	 * @return
	 */
	// @Override
	@Override
	public boolean exists( IdfCnfDelega idfCnfDelega ) {
		return idfCnfDelegaDao.exists(idfCnfDelega);
	}

    //----------------------------------------------------------------------
	/**
	 * Counts all the records present in the database
	 * @return
	 */
	@Override
	public long count() {
		return idfCnfDelegaDao.countAll();
	}

	@Override
	public boolean deleteById(String cfDelegante, Integer idConfigUtente) {
		IdfCnfDelega idfCnfDelega=new IdfCnfDelega();
		idfCnfDelega.setCfDelegante(cfDelegante);
		idfCnfDelega.setIdConfigUtente(idConfigUtente);
		
		return delete(idfCnfDelega);
	}

   
}
