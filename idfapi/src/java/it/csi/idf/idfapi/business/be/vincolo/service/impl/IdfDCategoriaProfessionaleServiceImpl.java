/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.service.impl;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.vincolo.dao.IdfDCategoriaProfessionaleDAO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfDCategoriaProfessionale;
import it.csi.idf.idfapi.business.be.vincolo.service.GenericJdbcService;
import it.csi.idf.idfapi.business.be.vincolo.service.IdfDCategoriaProfessionaleService;

/**
 * IdfDCategoriaProfessionale Service implementation 
 * 
 */
//@Named("IdfDCategoriaProfessionaleService")
@Component
public class IdfDCategoriaProfessionaleServiceImpl extends GenericJdbcService<IdfDCategoriaProfessionale> implements IdfDCategoriaProfessionaleService {
    
    @Autowired
	private IdfDCategoriaProfessionaleDAO idfDCategoriaProfessionaleDao;

    //----------------------------------------------------------------------
	/**
	 * Service constructor
	 */
	public IdfDCategoriaProfessionaleServiceImpl() {
		super();
	}

	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfDCategoriaProfessionale findById( BigDecimal idCategoriaProfessionale ) {
		IdfDCategoriaProfessionale idfDCategoriaProfessionale = idfDCategoriaProfessionaleDao.findById(idCategoriaProfessionale) ;
		if ( idfDCategoriaProfessionale != null ) {
			return idfDCategoriaProfessionale ;
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
	public List<IdfDCategoriaProfessionale> findAll() {
		return idfDCategoriaProfessionaleDao.findAll();
	}

	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param idfDCategoriaProfessionale Dto
	 * @return true if found, false if not found
	 */
	@Override
	public boolean load( IdfDCategoriaProfessionale idfDCategoriaProfessionale ) {
		return idfDCategoriaProfessionaleDao.load(idfDCategoriaProfessionale) ;
	}

    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param idfDCategoriaProfessionale
	 */
	@Override
	public long insert(IdfDCategoriaProfessionale idfDCategoriaProfessionale) {
		idfDCategoriaProfessionaleDao.insert(idfDCategoriaProfessionale);
		return 0L ;
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfDCategoriaProfessionale create(IdfDCategoriaProfessionale idfDCategoriaProfessionale) {
		idfDCategoriaProfessionaleDao.create(idfDCategoriaProfessionale);
		return idfDCategoriaProfessionale ;
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean update(IdfDCategoriaProfessionale idfDCategoriaProfessionale) {
		return  idfDCategoriaProfessionaleDao.update(idfDCategoriaProfessionale);

	}	

    

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean deleteById( BigDecimal idCategoriaProfessionale ) {
		return  idfDCategoriaProfessionaleDao.deleteById(idCategoriaProfessionale);
		
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean delete( IdfDCategoriaProfessionale idfDCategoriaProfessionale ) {
		return idfDCategoriaProfessionaleDao.delete(idfDCategoriaProfessionale);
		
	}

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param idCategoriaProfessionale;
	 * @return
	 */
	 @Override
	public boolean exists( BigDecimal idCategoriaProfessionale ) {
		return idfDCategoriaProfessionaleDao.exists(idCategoriaProfessionale);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param idfDCategoriaProfessionale
	 * @return
	 */
	 @Override
	public boolean exists( IdfDCategoriaProfessionale idfDCategoriaProfessionale ) {
		return idfDCategoriaProfessionaleDao.exists(idfDCategoriaProfessionale);
	}

    //----------------------------------------------------------------------
	/**
	 * Counts all the records present in the database
	 * @return
	 */
	@Override
	public long count() {
		return idfDCategoriaProfessionaleDao.countAll();
	}

   
}
