/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.vincolo.dao.IdfCnfTipoallTipointDAO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfCnfTipoallTipoint;
import it.csi.idf.idfapi.business.be.vincolo.service.GenericJdbcService;
import it.csi.idf.idfapi.business.be.vincolo.service.IdfCnfTipoallTipointService;

/**
 * IdfCnfTipoallTipoint Service implementation 
 * 
 */
//@Named("IdfCnfTipoallTipointService")
@Component
public class IdfCnfTipoallTipointServiceImpl extends GenericJdbcService<IdfCnfTipoallTipoint> implements IdfCnfTipoallTipointService {
    
    @Autowired
	private IdfCnfTipoallTipointDAO idfCnfTipoallTipointDao;

    //----------------------------------------------------------------------
	/**
	 * Service constructor
	 */
	public IdfCnfTipoallTipointServiceImpl() {
		super();
	}

	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfCnfTipoallTipoint findById( Integer idTipoAllegato, BigDecimal idTipoIntervento ) {
		IdfCnfTipoallTipoint idfCnfTipoallTipoint = idfCnfTipoallTipointDao.findById(idTipoAllegato, idTipoIntervento) ;
		if ( idfCnfTipoallTipoint != null ) {
			return idfCnfTipoallTipoint ;
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
	public List<IdfCnfTipoallTipoint> findAll() {
		return idfCnfTipoallTipointDao.findAll();
	}

	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param idfCnfTipoallTipoint Dto
	 * @return true if found, false if not found
	 */
	@Override
	public boolean load( IdfCnfTipoallTipoint idfCnfTipoallTipoint ) {
		return idfCnfTipoallTipointDao.load(idfCnfTipoallTipoint) ;
	}

    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param idfCnfTipoallTipoint
	 */
	@Override
	public long insert(IdfCnfTipoallTipoint idfCnfTipoallTipoint) {
		idfCnfTipoallTipointDao.insert(idfCnfTipoallTipoint);
		return 0L ;
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfCnfTipoallTipoint create(IdfCnfTipoallTipoint idfCnfTipoallTipoint) {
		idfCnfTipoallTipointDao.create(idfCnfTipoallTipoint);
		return idfCnfTipoallTipoint ;
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean update(IdfCnfTipoallTipoint idfCnfTipoallTipoint) {
		return  idfCnfTipoallTipointDao.update(idfCnfTipoallTipoint);

	}	

    

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean deleteById( Integer idTipoAllegato, BigDecimal idTipoIntervento ) {
		return  idfCnfTipoallTipointDao.deleteById(idTipoAllegato, idTipoIntervento);
		
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean delete( IdfCnfTipoallTipoint idfCnfTipoallTipoint ) {
		return idfCnfTipoallTipointDao.delete(idfCnfTipoallTipoint);
		
	}

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param idTipoAllegato;
	 * @param idTipoIntervento;
	 * @return
	 */
	 @Override
	public boolean exists( Integer idTipoAllegato, BigDecimal idTipoIntervento ) {
		return idfCnfTipoallTipointDao.exists(idTipoAllegato, idTipoIntervento);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param idfCnfTipoallTipoint
	 * @return
	 */
	 @Override
	public boolean exists( IdfCnfTipoallTipoint idfCnfTipoallTipoint ) {
		return idfCnfTipoallTipointDao.exists(idfCnfTipoallTipoint);
	}

    //----------------------------------------------------------------------
	/**
	 * Counts all the records present in the database
	 * @return
	 */
	@Override
	public long count() {
		return idfCnfTipoallTipointDao.countAll();
	}

   
}
