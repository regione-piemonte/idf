/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.vincolo.dao.IdfGeoPlIntervVincidroDAO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfGeoPlIntervVincidro;
import it.csi.idf.idfapi.business.be.vincolo.service.GenericJdbcService;
import it.csi.idf.idfapi.business.be.vincolo.service.IdfGeoPlIntervVincidroService;

/**
 * IdfGeoPlIntervVincidro Service implementation 
 * 
 */
//@Named("IdfGeoPlIntervVincidroService")
@Component
public class IdfGeoPlIntervVincidroServiceImpl extends GenericJdbcService<IdfGeoPlIntervVincidro> implements IdfGeoPlIntervVincidroService {
    
    @Autowired
	private IdfGeoPlIntervVincidroDAO idfGeoPlIntervVincidroDao;

    //----------------------------------------------------------------------
	/**
	 * Service constructor
	 */
	public IdfGeoPlIntervVincidroServiceImpl() {
		super();
	}

	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfGeoPlIntervVincidro findById( Integer idGeoPlIntervVincidro ) {
		IdfGeoPlIntervVincidro idfGeoPlIntervVincidro = idfGeoPlIntervVincidroDao.findById(idGeoPlIntervVincidro) ;
		if ( idfGeoPlIntervVincidro != null ) {
			return idfGeoPlIntervVincidro ;
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
	public List<IdfGeoPlIntervVincidro> findAll() {
		return idfGeoPlIntervVincidroDao.findAll();
	}

	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param idfGeoPlIntervVincidro Dto
	 * @return true if found, false if not found
	 */
	@Override
	public boolean load( IdfGeoPlIntervVincidro idfGeoPlIntervVincidro ) {
		return idfGeoPlIntervVincidroDao.load(idfGeoPlIntervVincidro) ;
	}

    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param idfGeoPlIntervVincidro
	 */
	@Override
	public long insert(IdfGeoPlIntervVincidro idfGeoPlIntervVincidro) {
		idfGeoPlIntervVincidroDao.insert(idfGeoPlIntervVincidro);
		return 0L ;
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfGeoPlIntervVincidro create(IdfGeoPlIntervVincidro idfGeoPlIntervVincidro) {
		idfGeoPlIntervVincidroDao.create(idfGeoPlIntervVincidro);
		return idfGeoPlIntervVincidro ;
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean update(IdfGeoPlIntervVincidro idfGeoPlIntervVincidro) {
		return  idfGeoPlIntervVincidroDao.update(idfGeoPlIntervVincidro);

	}	

    

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean deleteById( Integer idGeoPlIntervVincidro ) {
		return  idfGeoPlIntervVincidroDao.deleteById(idGeoPlIntervVincidro);
		
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean delete( IdfGeoPlIntervVincidro idfGeoPlIntervVincidro ) {
		return idfGeoPlIntervVincidroDao.delete(idfGeoPlIntervVincidro);
		
	}

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param idGeoPlIntervVincidro;
	 * @return
	 */
	 @Override
	public boolean exists( Integer idGeoPlIntervVincidro ) {
		return idfGeoPlIntervVincidroDao.exists(idGeoPlIntervVincidro);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param idfGeoPlIntervVincidro
	 * @return
	 */
	 @Override
	public boolean exists( IdfGeoPlIntervVincidro idfGeoPlIntervVincidro ) {
		return idfGeoPlIntervVincidroDao.exists(idfGeoPlIntervVincidro);
	}

    //----------------------------------------------------------------------
	/**
	 * Counts all the records present in the database
	 * @return
	 */
	@Override
	public long count() {
		return idfGeoPlIntervVincidroDao.countAll();
	}

   
}
