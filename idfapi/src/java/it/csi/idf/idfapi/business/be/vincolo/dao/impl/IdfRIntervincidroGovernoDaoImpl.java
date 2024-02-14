/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.dao.impl;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.vincolo.dao.GenericJdbcDAO;
import it.csi.idf.idfapi.business.be.vincolo.dao.IdfRIntervincidroGovernoDAO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfRIntervincidroGoverno;
import it.csi.idf.idfapi.util.DBUtil;

/**
 * IdfRIntervincidroGoverno DAO implementation 
 * 
 */
//@Named("IdfRIntervincidroGovernoDAO")
@Component
public class IdfRIntervincidroGovernoDaoImpl extends GenericJdbcDAO<IdfRIntervincidroGoverno> implements IdfRIntervincidroGovernoDAO {

	private final static String SQL_SELECT_ALL = 
		"select id_intervento, id_governo, data_inserimento, fk_config_utente from idf_r_intervincidro_governo"; 

	private final static String SQL_SELECT = 
		"select id_intervento, id_governo, data_inserimento, fk_config_utente from idf_r_intervincidro_governo where id_intervento = ? and id_governo = ?";

	private final static String SQL_INSERT = 
		"insert into idf_r_intervincidro_governo ( id_intervento, id_governo, data_inserimento, fk_config_utente ) values ( ?, ?, ?, ? )";

	private final static String SQL_UPDATE = 
		"update idf_r_intervincidro_governo set data_inserimento = ?, fk_config_utente = ? where id_intervento = ? and id_governo = ?";

	private final static String SQL_DELETE = 
		"delete from idf_r_intervincidro_governo where id_intervento = ? and id_governo = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from idf_r_intervincidro_governo";

	private final static String SQL_COUNT = 
		"select count(*) from idf_r_intervincidro_governo where id_intervento = ? and id_governo = ?";

    //----------------------------------------------------------------------
	/**
	 * DAO constructor
	 */
	public IdfRIntervincidroGovernoDaoImpl() {
		super();
	}

    //----------------------------------------------------------------------
	@Override
	protected void setAutoIncrementedKey(IdfRIntervincidroGoverno record, long value) {
		throw new IllegalStateException("Unexpected call to method 'setAutoIncrementedKey'");
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForPrimaryKey(PreparedStatement ps, int i, IdfRIntervincidroGoverno idfRIntervincidroGoverno) throws SQLException {
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, idfRIntervincidroGoverno.getIdIntervento() ) ; // "id_intervento" : java.math.BigDecimal
		setValue(ps, i++, idfRIntervincidroGoverno.getIdGoverno() ) ; // "id_governo" : java.lang.Integer
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForInsert(PreparedStatement ps, int i, IdfRIntervincidroGoverno idfRIntervincidroGoverno) throws SQLException {
		//--- Set PRIMARY KEY and DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, idfRIntervincidroGoverno.getIdIntervento() ) ; // "id_intervento" : java.math.BigDecimal
		setValue(ps, i++, idfRIntervincidroGoverno.getIdGoverno() ) ; // "id_governo" : java.lang.Integer
		setValue(ps, i++, idfRIntervincidroGoverno.getDataInserimento() ) ; // "data_inserimento" : java.util.Date
		setValue(ps, i++, idfRIntervincidroGoverno.getFkConfigUtente() ) ; // "fk_config_utente" : java.lang.Integer
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForUpdate(PreparedStatement ps, int i, IdfRIntervincidroGoverno idfRIntervincidroGoverno) throws SQLException {
		//--- Set DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, idfRIntervincidroGoverno.getDataInserimento() ) ; // "data_inserimento" : java.util.Date
		setValue(ps, i++, idfRIntervincidroGoverno.getFkConfigUtente() ) ; // "fk_config_utente" : java.lang.Integer
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, idfRIntervincidroGoverno.getIdIntervento() ) ; // "id_intervento" : java.math.BigDecimal
		setValue(ps, i++, idfRIntervincidroGoverno.getIdGoverno() ) ; // "id_governo" : java.lang.Integer
	}

	//----------------------------------------------------------------------
	/**
	 * Creates a new instance of the bean and populates it with the given primary value(s)
	 * @param idIntervento;
	 * @param idGoverno;
	 * @return the new instance
	 */
	private IdfRIntervincidroGoverno newInstanceWithPrimaryKey( BigDecimal idIntervento, Integer idGoverno ) {
		IdfRIntervincidroGoverno idfRIntervincidroGoverno = new IdfRIntervincidroGoverno();
		idfRIntervincidroGoverno.setIdIntervento( idIntervento );
		idfRIntervincidroGoverno.setIdGoverno( idGoverno );
		return idfRIntervincidroGoverno ;
	}

	//----------------------------------------------------------------------
	@Override
	protected IdfRIntervincidroGoverno newInstance() {
		return new IdfRIntervincidroGoverno() ;
	}

    //----------------------------------------------------------------------
	@Override
	protected IdfRIntervincidroGoverno populateBean(ResultSet rs, IdfRIntervincidroGoverno idfRIntervincidroGoverno) throws SQLException {

		//--- Set data from ResultSet to Bean attributes
		idfRIntervincidroGoverno.setIdIntervento(rs.getBigDecimal("id_intervento")); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfRIntervincidroGoverno.setIdIntervento(null); }; // not primitive number => keep null value if any
		idfRIntervincidroGoverno.setIdGoverno(rs.getInt("id_governo")); // java.lang.Integer
		if ( rs.wasNull() ) { idfRIntervincidroGoverno.setIdGoverno(null); }; // not primitive number => keep null value if any
		idfRIntervincidroGoverno.setDataInserimento(rs.getDate("data_inserimento")); // java.util.Date
		idfRIntervincidroGoverno.setFkConfigUtente(rs.getInt("fk_config_utente")); // java.lang.Integer
		if ( rs.wasNull() ) { idfRIntervincidroGoverno.setFkConfigUtente(null); }; // not primitive number => keep null value if any
		return idfRIntervincidroGoverno ;
	}

	//----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfRIntervincidroGoverno findById( BigDecimal idIntervento, Integer idGoverno ) {
		IdfRIntervincidroGoverno idfRIntervincidroGoverno = newInstanceWithPrimaryKey( idIntervento, idGoverno ) ;
		if ( super.doSelect(idfRIntervincidroGoverno) ) {
			return idfRIntervincidroGoverno ;
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
	public List<IdfRIntervincidroGoverno> findAll() {
		return super.doSelectAll();
	}

	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param idfRIntervincidroGoverno
	 * @return true if found, false if not found
	 */
	@Override
	public boolean load( IdfRIntervincidroGoverno idfRIntervincidroGoverno ) {
		return super.doSelect(idfRIntervincidroGoverno) ;
	}

    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param idfRIntervincidroGoverno
	 */
	public long insert(IdfRIntervincidroGoverno idfRIntervincidroGoverno) {
		super.doInsert(idfRIntervincidroGoverno);
		return 0L ;
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfRIntervincidroGoverno create(IdfRIntervincidroGoverno idfRIntervincidroGoverno) {
		insert(idfRIntervincidroGoverno);
		return idfRIntervincidroGoverno ;
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean update(IdfRIntervincidroGoverno idfRIntervincidroGoverno) {
		int r = super.doUpdate(idfRIntervincidroGoverno);
		return r > 0 ;

	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfRIntervincidroGoverno save(IdfRIntervincidroGoverno idfRIntervincidroGoverno) {
		if ( super.doExists(idfRIntervincidroGoverno) ) {
			super.doUpdate(idfRIntervincidroGoverno);
		}
		else {
			super.doInsert(idfRIntervincidroGoverno);
		}
		return idfRIntervincidroGoverno ;
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean deleteById( BigDecimal idIntervento, Integer idGoverno ) {
		IdfRIntervincidroGoverno idfRIntervincidroGoverno = newInstanceWithPrimaryKey( idIntervento, idGoverno ) ;
		int r = super.doDelete(idfRIntervincidroGoverno);
		return r > 0 ;
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean delete( IdfRIntervincidroGoverno idfRIntervincidroGoverno ) {
		int r = super.doDelete(idfRIntervincidroGoverno);
		return r > 0 ;
	}

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param idIntervento;
	 * @param idGoverno;
	 * @return
	 */
	// @Override
	@Override
	public boolean exists( BigDecimal idIntervento, Integer idGoverno ) {
		IdfRIntervincidroGoverno idfRIntervincidroGoverno = newInstanceWithPrimaryKey( idIntervento, idGoverno ) ;
		return super.doExists(idfRIntervincidroGoverno);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param idfRIntervincidroGoverno
	 * @return
	 */
	// @Override
	@Override
	public boolean exists( IdfRIntervincidroGoverno idfRIntervincidroGoverno ) {
		return super.doExists(idfRIntervincidroGoverno);
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

	@Override
	public int deleteByIdIntervento(Integer idIntervento) {
		String sql = "delete from idf_r_intervincidro_governo where id_intervento = ? ";
		return DBUtil.jdbcTemplate.update(sql, idIntervento);
	}

}
