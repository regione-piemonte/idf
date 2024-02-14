/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
//
// Created on 2020-10-04 ( Date ISO 2020-10-04 - Time 22:37:05 )
// java7-persistance-jdbc-T300
package it.csi.idf.idfapi.business.be.vincolo.dao.impl;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.vincolo.dao.GenericJdbcDAO;
import it.csi.idf.idfapi.business.be.vincolo.dao.IdfDCategoriaProfessionaleDAO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfDCategoriaProfessionale;

/**
 * IdfDCategoriaProfessionale DAO implementation 
 * 
 */
//@Named("IdfDCategoriaProfessionaleDAO")
@Component
public class IdfDCategoriaProfessionaleDaoImpl extends GenericJdbcDAO<IdfDCategoriaProfessionale> implements IdfDCategoriaProfessionaleDAO {

	private final static String SQL_SELECT_ALL = 
		"select id_categoria_professionale, descr_categoria_professionale, mtd_ordinamento, flg_visibile from idf_d_categoria_professionale"; 

	private final static String SQL_SELECT = 
		"select id_categoria_professionale, descr_categoria_professionale, mtd_ordinamento, flg_visibile from idf_d_categoria_professionale where id_categoria_professionale = ?";

	private final static String SQL_INSERT = 
		"insert into idf_d_categoria_professionale ( id_categoria_professionale, descr_categoria_professionale, mtd_ordinamento, flg_visibile ) values ( ?, ?, ?, ? )";

	private final static String SQL_UPDATE = 
		"update idf_d_categoria_professionale set descr_categoria_professionale = ?, mtd_ordinamento = ?, flg_visibile = ? where id_categoria_professionale = ?";

	private final static String SQL_DELETE = 
		"delete from idf_d_categoria_professionale where id_categoria_professionale = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from idf_d_categoria_professionale";

	private final static String SQL_COUNT = 
		"select count(*) from idf_d_categoria_professionale where id_categoria_professionale = ?";

    //----------------------------------------------------------------------
	/**
	 * DAO constructor
	 */
	public IdfDCategoriaProfessionaleDaoImpl() {
		super();
	}

    //----------------------------------------------------------------------
	@Override
	protected void setAutoIncrementedKey(IdfDCategoriaProfessionale record, long value) {
		throw new IllegalStateException("Unexpected call to method 'setAutoIncrementedKey'");
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForPrimaryKey(PreparedStatement ps, int i, IdfDCategoriaProfessionale idfDCategoriaProfessionale) throws SQLException {
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, idfDCategoriaProfessionale.getIdCategoriaProfessionale() ) ; // "id_categoria_professionale" : java.math.BigDecimal
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForInsert(PreparedStatement ps, int i, IdfDCategoriaProfessionale idfDCategoriaProfessionale) throws SQLException {
		//--- Set PRIMARY KEY and DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, idfDCategoriaProfessionale.getIdCategoriaProfessionale() ) ; // "id_categoria_professionale" : java.math.BigDecimal
		setValue(ps, i++, idfDCategoriaProfessionale.getDescrCategoriaProfessionale() ) ; // "descr_categoria_professionale" : java.lang.String
		setValue(ps, i++, idfDCategoriaProfessionale.getMtdOrdinamento() ) ; // "mtd_ordinamento" : java.math.BigDecimal
		setValue(ps, i++, idfDCategoriaProfessionale.getFlgVisibile() ) ; // "flg_visibile" : java.math.BigDecimal
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForUpdate(PreparedStatement ps, int i, IdfDCategoriaProfessionale idfDCategoriaProfessionale) throws SQLException {
		//--- Set DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, idfDCategoriaProfessionale.getDescrCategoriaProfessionale() ) ; // "descr_categoria_professionale" : java.lang.String
		setValue(ps, i++, idfDCategoriaProfessionale.getMtdOrdinamento() ) ; // "mtd_ordinamento" : java.math.BigDecimal
		setValue(ps, i++, idfDCategoriaProfessionale.getFlgVisibile() ) ; // "flg_visibile" : java.math.BigDecimal
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, idfDCategoriaProfessionale.getIdCategoriaProfessionale() ) ; // "id_categoria_professionale" : java.math.BigDecimal
	}

	//----------------------------------------------------------------------
	/**
	 * Creates a new instance of the bean and populates it with the given primary value(s)
	 * @param idCategoriaProfessionale;
	 * @return the new instance
	 */
	private IdfDCategoriaProfessionale newInstanceWithPrimaryKey( BigDecimal idCategoriaProfessionale ) {
		IdfDCategoriaProfessionale idfDCategoriaProfessionale = new IdfDCategoriaProfessionale();
		idfDCategoriaProfessionale.setIdCategoriaProfessionale( idCategoriaProfessionale );
		return idfDCategoriaProfessionale ;
	}

	//----------------------------------------------------------------------
	@Override
	protected IdfDCategoriaProfessionale newInstance() {
		return new IdfDCategoriaProfessionale() ;
	}

    //----------------------------------------------------------------------
	@Override
	protected IdfDCategoriaProfessionale populateBean(ResultSet rs, IdfDCategoriaProfessionale idfDCategoriaProfessionale) throws SQLException {

		//--- Set data from ResultSet to Bean attributes
		idfDCategoriaProfessionale.setIdCategoriaProfessionale(rs.getBigDecimal("id_categoria_professionale")); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfDCategoriaProfessionale.setIdCategoriaProfessionale(null); }; // not primitive number => keep null value if any
		idfDCategoriaProfessionale.setDescrCategoriaProfessionale(rs.getString("descr_categoria_professionale")); // java.lang.String
		idfDCategoriaProfessionale.setMtdOrdinamento(rs.getBigDecimal("mtd_ordinamento")); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfDCategoriaProfessionale.setMtdOrdinamento(null); }; // not primitive number => keep null value if any
		idfDCategoriaProfessionale.setFlgVisibile(rs.getBigDecimal("flg_visibile")); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfDCategoriaProfessionale.setFlgVisibile(null); }; // not primitive number => keep null value if any
		return idfDCategoriaProfessionale ;
	}

	//----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfDCategoriaProfessionale findById( BigDecimal idCategoriaProfessionale ) {
		IdfDCategoriaProfessionale idfDCategoriaProfessionale = newInstanceWithPrimaryKey( idCategoriaProfessionale ) ;
		if ( super.doSelect(idfDCategoriaProfessionale) ) {
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
		return super.doSelectAll();
	}

	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param idfDCategoriaProfessionale
	 * @return true if found, false if not found
	 */
	@Override
	public boolean load( IdfDCategoriaProfessionale idfDCategoriaProfessionale ) {
		return super.doSelect(idfDCategoriaProfessionale) ;
	}

    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param idfDCategoriaProfessionale
	 */
	@Override
	public long insert(IdfDCategoriaProfessionale idfDCategoriaProfessionale) {
		super.doInsert(idfDCategoriaProfessionale);
		return 0L ;
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfDCategoriaProfessionale create(IdfDCategoriaProfessionale idfDCategoriaProfessionale) {
		insert(idfDCategoriaProfessionale);
		return idfDCategoriaProfessionale ;
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean update(IdfDCategoriaProfessionale idfDCategoriaProfessionale) {
		int r = super.doUpdate(idfDCategoriaProfessionale);
		return r > 0 ;

	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfDCategoriaProfessionale save(IdfDCategoriaProfessionale idfDCategoriaProfessionale) {
		if ( super.doExists(idfDCategoriaProfessionale) ) {
			super.doUpdate(idfDCategoriaProfessionale);
		}
		else {
			super.doInsert(idfDCategoriaProfessionale);
		}
		return idfDCategoriaProfessionale ;
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean deleteById( BigDecimal idCategoriaProfessionale ) {
		IdfDCategoriaProfessionale idfDCategoriaProfessionale = newInstanceWithPrimaryKey( idCategoriaProfessionale ) ;
		int r = super.doDelete(idfDCategoriaProfessionale);
		return r > 0 ;
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean delete( IdfDCategoriaProfessionale idfDCategoriaProfessionale ) {
		int r = super.doDelete(idfDCategoriaProfessionale);
		return r > 0 ;
	}

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param idCategoriaProfessionale;
	 * @return
	 */
	// @Override
	@Override
	public boolean exists( BigDecimal idCategoriaProfessionale ) {
		IdfDCategoriaProfessionale idfDCategoriaProfessionale = newInstanceWithPrimaryKey( idCategoriaProfessionale ) ;
		return super.doExists(idfDCategoriaProfessionale);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param idfDCategoriaProfessionale
	 * @return
	 */
	// @Override
	@Override
	public boolean exists( IdfDCategoriaProfessionale idfDCategoriaProfessionale ) {
		return super.doExists(idfDCategoriaProfessionale);
	}

    //----------------------------------------------------------------------
	/**
	 * Counts all the records present in the database
	 * @return
	 */
	@Override
	public long countAll() {
		return super.doCountAll();
	}

    //----------------------------------------------------------------------
	@Override
	protected String getSqlSelect() {
		return SQL_SELECT ;
	}
    //----------------------------------------------------------------------
	@Override
	protected String getSqlSelectAll() {
		return SQL_SELECT_ALL;
	}
    //----------------------------------------------------------------------
	@Override
	protected String getSqlInsert() {
		return SQL_INSERT ;
	}
    //----------------------------------------------------------------------
	@Override
	protected String getSqlUpdate() {
		return SQL_UPDATE ;
	}
    //----------------------------------------------------------------------
	@Override
	protected String getSqlDelete() {
		return SQL_DELETE ;
	}
    //----------------------------------------------------------------------
	@Override
	protected String getSqlCount() {
		return SQL_COUNT ;
	}
    //----------------------------------------------------------------------
	@Override
	protected String getSqlCountAll() {
		return SQL_COUNT_ALL ;
	}

}
