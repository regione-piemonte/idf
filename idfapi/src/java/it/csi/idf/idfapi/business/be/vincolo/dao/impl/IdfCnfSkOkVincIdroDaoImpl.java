/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
//
// Created on 2020-10-04 ( Date ISO 2020-10-04 - Time 22:37:04 )
// java7-persistance-jdbc-T300
package it.csi.idf.idfapi.business.be.vincolo.dao.impl;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.vincolo.dao.GenericJdbcDAO;
import it.csi.idf.idfapi.business.be.vincolo.dao.IdfCnfSkOkVincIdroDAO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfCnfSkOkVincIdro;

/**
 * IdfCnfSkOkVincIdro DAO implementation 
 * 
 */
//@Named("IdfCnfSkOkVincIdroDAO")
@Component
public class IdfCnfSkOkVincIdroDaoImpl extends GenericJdbcDAO<IdfCnfSkOkVincIdro> implements IdfCnfSkOkVincIdroDAO {

	private final static String SQL_SELECT_ALL = 
		"select id_intervento, flg_sez1, flg_sez2, flg_sez3, flg_sez4, flg_sez5, flg_sez6 from idf_cnf_sk_ok_vinc_idro"; 

	private final static String SQL_SELECT = 
		"select id_intervento, flg_sez1, flg_sez2, flg_sez3, flg_sez4, flg_sez5, flg_sez6 from idf_cnf_sk_ok_vinc_idro where id_intervento = ?";

	private final static String SQL_INSERT = 
		"insert into idf_cnf_sk_ok_vinc_idro ( id_intervento, flg_sez1, flg_sez2, flg_sez3, flg_sez4, flg_sez5, flg_sez6 ) values ( ?, ?, ?, ?, ?, ?, ? )";

	private final static String SQL_UPDATE = 
		"update idf_cnf_sk_ok_vinc_idro set flg_sez1 = ?, flg_sez2 = ?, flg_sez3 = ?, flg_sez4 = ?, flg_sez5 = ?, flg_sez6 = ? where id_intervento = ?";

	private final static String SQL_DELETE = 
		"delete from idf_cnf_sk_ok_vinc_idro where id_intervento = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from idf_cnf_sk_ok_vinc_idro";

	private final static String SQL_COUNT = 
		"select count(*) from idf_cnf_sk_ok_vinc_idro where id_intervento = ?";

    //----------------------------------------------------------------------
	/**
	 * DAO constructor
	 */
	public IdfCnfSkOkVincIdroDaoImpl() {
		super();
	}

    //----------------------------------------------------------------------
	@Override
	protected void setAutoIncrementedKey(IdfCnfSkOkVincIdro record, long value) {
		throw new IllegalStateException("Unexpected call to method 'setAutoIncrementedKey'");
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForPrimaryKey(PreparedStatement ps, int i, IdfCnfSkOkVincIdro idfCnfSkOkVincIdro) throws SQLException {
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, idfCnfSkOkVincIdro.getIdIntervento() ) ; // "id_intervento" : java.math.BigDecimal
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForInsert(PreparedStatement ps, int i, IdfCnfSkOkVincIdro idfCnfSkOkVincIdro) throws SQLException {
		//--- Set PRIMARY KEY and DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, idfCnfSkOkVincIdro.getIdIntervento() ) ; // "id_intervento" : java.math.BigDecimal
		setValue(ps, i++, idfCnfSkOkVincIdro.getFlgSez1() ) ; // "flg_sez1" : java.lang.Integer
		setValue(ps, i++, idfCnfSkOkVincIdro.getFlgSez2() ) ; // "flg_sez2" : java.lang.Integer
		setValue(ps, i++, idfCnfSkOkVincIdro.getFlgSez3() ) ; // "flg_sez3" : java.lang.Integer
		setValue(ps, i++, idfCnfSkOkVincIdro.getFlgSez4() ) ; // "flg_sez4" : java.lang.Integer
		setValue(ps, i++, idfCnfSkOkVincIdro.getFlgSez5() ) ; // "flg_sez5" : java.lang.Integer
		setValue(ps, i++, idfCnfSkOkVincIdro.getFlgSez6() ) ; // "flg_sez6" : java.lang.Integer
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForUpdate(PreparedStatement ps, int i, IdfCnfSkOkVincIdro idfCnfSkOkVincIdro) throws SQLException {
		//--- Set DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, idfCnfSkOkVincIdro.getFlgSez1() ) ; // "flg_sez1" : java.lang.Integer
		setValue(ps, i++, idfCnfSkOkVincIdro.getFlgSez2() ) ; // "flg_sez2" : java.lang.Integer
		setValue(ps, i++, idfCnfSkOkVincIdro.getFlgSez3() ) ; // "flg_sez3" : java.lang.Integer
		setValue(ps, i++, idfCnfSkOkVincIdro.getFlgSez4() ) ; // "flg_sez4" : java.lang.Integer
		setValue(ps, i++, idfCnfSkOkVincIdro.getFlgSez5() ) ; // "flg_sez5" : java.lang.Integer
		setValue(ps, i++, idfCnfSkOkVincIdro.getFlgSez6() ) ; // "flg_sez6" : java.lang.Integer
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, idfCnfSkOkVincIdro.getIdIntervento() ) ; // "id_intervento" : java.math.BigDecimal
	}

	//----------------------------------------------------------------------
	/**
	 * Creates a new instance of the bean and populates it with the given primary value(s)
	 * @param idIntervento;
	 * @return the new instance
	 */
	private IdfCnfSkOkVincIdro newInstanceWithPrimaryKey( BigDecimal idIntervento ) {
		IdfCnfSkOkVincIdro idfCnfSkOkVincIdro = new IdfCnfSkOkVincIdro();
		idfCnfSkOkVincIdro.setIdIntervento( idIntervento );
		return idfCnfSkOkVincIdro ;
	}

	//----------------------------------------------------------------------
	@Override
	protected IdfCnfSkOkVincIdro newInstance() {
		return new IdfCnfSkOkVincIdro() ;
	}

    //----------------------------------------------------------------------
	@Override
	protected IdfCnfSkOkVincIdro populateBean(ResultSet rs, IdfCnfSkOkVincIdro idfCnfSkOkVincIdro) throws SQLException {

		//--- Set data from ResultSet to Bean attributes
		idfCnfSkOkVincIdro.setIdIntervento(rs.getBigDecimal("id_intervento")); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfCnfSkOkVincIdro.setIdIntervento(null); }; // not primitive number => keep null value if any
		idfCnfSkOkVincIdro.setFlgSez1(rs.getInt("flg_sez1")); // java.lang.Integer
		if ( rs.wasNull() ) { idfCnfSkOkVincIdro.setFlgSez1(null); }; // not primitive number => keep null value if any
		idfCnfSkOkVincIdro.setFlgSez2(rs.getInt("flg_sez2")); // java.lang.Integer
		if ( rs.wasNull() ) { idfCnfSkOkVincIdro.setFlgSez2(null); }; // not primitive number => keep null value if any
		idfCnfSkOkVincIdro.setFlgSez3(rs.getInt("flg_sez3")); // java.lang.Integer
		if ( rs.wasNull() ) { idfCnfSkOkVincIdro.setFlgSez3(null); }; // not primitive number => keep null value if any
		idfCnfSkOkVincIdro.setFlgSez4(rs.getInt("flg_sez4")); // java.lang.Integer
		if ( rs.wasNull() ) { idfCnfSkOkVincIdro.setFlgSez4(null); }; // not primitive number => keep null value if any
		idfCnfSkOkVincIdro.setFlgSez5(rs.getInt("flg_sez5")); // java.lang.Integer
		if ( rs.wasNull() ) { idfCnfSkOkVincIdro.setFlgSez5(null); }; // not primitive number => keep null value if any
		idfCnfSkOkVincIdro.setFlgSez6(rs.getInt("flg_sez6")); // java.lang.Integer
		if ( rs.wasNull() ) { idfCnfSkOkVincIdro.setFlgSez6(null); }; // not primitive number => keep null value if any
		return idfCnfSkOkVincIdro ;
	}

	//----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfCnfSkOkVincIdro findById( BigDecimal idIntervento ) {
		IdfCnfSkOkVincIdro idfCnfSkOkVincIdro = newInstanceWithPrimaryKey( idIntervento ) ;
		if ( super.doSelect(idfCnfSkOkVincIdro) ) {
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
		return super.doSelectAll();
	}

	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param idfCnfSkOkVincIdro
	 * @return true if found, false if not found
	 */
	@Override
	public boolean load( IdfCnfSkOkVincIdro idfCnfSkOkVincIdro ) {
		return super.doSelect(idfCnfSkOkVincIdro) ;
	}

    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param idfCnfSkOkVincIdro
	 */
	@Override
	public long insert(IdfCnfSkOkVincIdro idfCnfSkOkVincIdro) {
		super.doInsert(idfCnfSkOkVincIdro);
		return 0L ;
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfCnfSkOkVincIdro create(IdfCnfSkOkVincIdro idfCnfSkOkVincIdro) {
		insert(idfCnfSkOkVincIdro);
		return idfCnfSkOkVincIdro ;
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean update(IdfCnfSkOkVincIdro idfCnfSkOkVincIdro) {
		int r = super.doUpdate(idfCnfSkOkVincIdro);
		return r > 0 ;

	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfCnfSkOkVincIdro save(IdfCnfSkOkVincIdro idfCnfSkOkVincIdro) {
		if ( super.doExists(idfCnfSkOkVincIdro) ) {
			super.doUpdate(idfCnfSkOkVincIdro);
		}
		else {
			super.doInsert(idfCnfSkOkVincIdro);
		}
		return idfCnfSkOkVincIdro ;
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean deleteById( BigDecimal idIntervento ) {
		IdfCnfSkOkVincIdro idfCnfSkOkVincIdro = newInstanceWithPrimaryKey( idIntervento ) ;
		int r = super.doDelete(idfCnfSkOkVincIdro);
		return r > 0 ;
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean delete( IdfCnfSkOkVincIdro idfCnfSkOkVincIdro ) {
		int r = super.doDelete(idfCnfSkOkVincIdro);
		return r > 0 ;
	}

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param idIntervento;
	 * @return
	 */
	// @Override
	@Override
	public boolean exists( BigDecimal idIntervento ) {
		IdfCnfSkOkVincIdro idfCnfSkOkVincIdro = newInstanceWithPrimaryKey( idIntervento ) ;
		return super.doExists(idfCnfSkOkVincIdro);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param idfCnfSkOkVincIdro
	 * @return
	 */
	// @Override
	@Override
	public boolean exists( IdfCnfSkOkVincIdro idfCnfSkOkVincIdro ) {
		return super.doExists(idfCnfSkOkVincIdro);
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
