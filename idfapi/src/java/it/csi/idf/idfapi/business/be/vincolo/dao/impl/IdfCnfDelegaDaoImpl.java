/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.dao.impl;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.vincolo.dao.GenericJdbcDAO;
import it.csi.idf.idfapi.business.be.vincolo.dao.IdfCnfDelegaDAO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfCnfDelega;

/**
 * IdfCnfDelega DAO implementation 
 * 
 */
//@Named("IdfCnfDelegaDAO")
@Component
public class IdfCnfDelegaDaoImpl extends GenericJdbcDAO<IdfCnfDelega> implements IdfCnfDelegaDAO {

	private final static String SQL_SELECT_ALL = 
		"select cf_delegante, id_config_utente, data_inizio, data_fine from idf_cnf_delega"; 

	private final static String SQL_SELECT = 
		"select cf_delegante, id_config_utente, data_inizio, data_fine from idf_cnf_delega where cf_delegante = ? and id_config_utente = ?";

	private final static String SQL_INSERT = 
		"insert into idf_cnf_delega ( cf_delegante, id_config_utente, data_inizio, data_fine ) values ( ?, ?, ?, ? )";

	private final static String SQL_UPDATE = 
		"update idf_cnf_delega set data_inizio = ?, data_fine = ? where cf_delegante = ? and id_config_utente = ?";

	private final static String SQL_DELETE = 
		"delete from idf_cnf_delega where cf_delegante = ? and id_config_utente = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from idf_cnf_delega";

	private final static String SQL_COUNT = 
		"select count(*) from idf_cnf_delega where cf_delegante = ? and id_config_utente = ?";

    //----------------------------------------------------------------------
	/**
	 * DAO constructor
	 */
	public IdfCnfDelegaDaoImpl() {
		super();
	}

    //----------------------------------------------------------------------
	@Override
	protected void setAutoIncrementedKey(IdfCnfDelega record, long value) {
		throw new IllegalStateException("Unexpected call to method 'setAutoIncrementedKey'");
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForPrimaryKey(PreparedStatement ps, int i, IdfCnfDelega idfCnfDelega) throws SQLException {
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, idfCnfDelega.getCfDelegante() ) ; // "cf_delegante" : java.lang.String
		setValue(ps, i++, idfCnfDelega.getIdConfigUtente() ) ; // "id_config_utente" : java.lang.Integer
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForInsert(PreparedStatement ps, int i, IdfCnfDelega idfCnfDelega) throws SQLException {
		//--- Set PRIMARY KEY and DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, idfCnfDelega.getCfDelegante() ) ; // "cf_delegante" : java.lang.String
		setValue(ps, i++, idfCnfDelega.getIdConfigUtente() ) ; // "id_config_utente" : java.lang.Integer
		setValue(ps, i++, idfCnfDelega.getDataInizio() ) ; // "data_inizio" : java.util.Date
		setValue(ps, i++, idfCnfDelega.getDataFine() ) ; // "data_fine" : java.util.Date
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForUpdate(PreparedStatement ps, int i, IdfCnfDelega idfCnfDelega) throws SQLException {
		//--- Set DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, idfCnfDelega.getDataInizio() ) ; // "data_inizio" : java.util.Date
		setValue(ps, i++, idfCnfDelega.getDataFine() ) ; // "data_fine" : java.util.Date
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, idfCnfDelega.getCfDelegante() ) ; // "cf_delegante" : java.lang.String
		setValue(ps, i++, idfCnfDelega.getIdConfigUtente() ) ; // "id_config_utente" : java.lang.Integer
	}

	//----------------------------------------------------------------------
	/**
	 * Creates a new instance of the bean and populates it with the given primary value(s)
	 * @param cfDelegante;
	 * @param idConfigUtente;
	 * @return the new instance
	 */
	private IdfCnfDelega newInstanceWithPrimaryKey( String cfDelegante, Integer idConfigUtente ) {
		IdfCnfDelega idfCnfDelega = new IdfCnfDelega();
		idfCnfDelega.setCfDelegante( cfDelegante );
		idfCnfDelega.setIdConfigUtente( idConfigUtente );
		return idfCnfDelega ;
	}

	//----------------------------------------------------------------------
	@Override
	protected IdfCnfDelega newInstance() {
		return new IdfCnfDelega() ;
	}

    //----------------------------------------------------------------------
	@Override
	protected IdfCnfDelega populateBean(ResultSet rs, IdfCnfDelega idfCnfDelega) throws SQLException {

		//--- Set data from ResultSet to Bean attributes
		idfCnfDelega.setCfDelegante(rs.getString("cf_delegante")); // java.lang.String
		idfCnfDelega.setIdConfigUtente(rs.getInt("id_config_utente")); // java.lang.Integer
		if ( rs.wasNull() ) { idfCnfDelega.setIdConfigUtente(null); }; // not primitive number => keep null value if any
		idfCnfDelega.setDataInizio(rs.getDate("data_inizio")); // java.util.Date
		idfCnfDelega.setDataFine(rs.getDate("data_fine")); // java.util.Date
		return idfCnfDelega ;
	}

	//----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfCnfDelega findById( String cfDelegante, Integer idConfigUtente ) {
		IdfCnfDelega idfCnfDelega = newInstanceWithPrimaryKey( cfDelegante, idConfigUtente ) ;
		if ( super.doSelect(idfCnfDelega) ) {
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
		return super.doSelectAll();
	}

	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param idfCnfDelega
	 * @return true if found, false if not found
	 */
	@Override
	public boolean load( IdfCnfDelega idfCnfDelega ) {
		return super.doSelect(idfCnfDelega) ;
	}

    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param idfCnfDelega
	 */
	@Override
	
	public long insert(IdfCnfDelega idfCnfDelega) {
		super.doInsert(idfCnfDelega);
		return 0L ;
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfCnfDelega create(IdfCnfDelega idfCnfDelega) {
		insert(idfCnfDelega);
		return idfCnfDelega ;
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean update(IdfCnfDelega idfCnfDelega) {
		int r = super.doUpdate(idfCnfDelega);
		return r > 0 ;

	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfCnfDelega save(IdfCnfDelega idfCnfDelega) {
		if ( super.doExists(idfCnfDelega) ) {
			super.doUpdate(idfCnfDelega);
		}
		else {
			super.doInsert(idfCnfDelega);
		}
		return idfCnfDelega ;
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean deleteById( String cfDelegante, Integer idConfigUtente ) {
		IdfCnfDelega idfCnfDelega = newInstanceWithPrimaryKey( cfDelegante, idConfigUtente ) ;
		int r = super.doDelete(idfCnfDelega);
		return r > 0 ;
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean delete( IdfCnfDelega idfCnfDelega ) {
		int r = super.doDelete(idfCnfDelega);
		return r > 0 ;
	}

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param cfDelegante;
	 * @param idConfigUtente;
	 * @return
	 */
	@Override
	public boolean exists( String cfDelegante, Integer idConfigUtente ) {
		IdfCnfDelega idfCnfDelega = newInstanceWithPrimaryKey( cfDelegante, idConfigUtente ) ;
		return super.doExists(idfCnfDelega);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param idfCnfDelega
	 * @return
	 */
	@Override
	public boolean exists( IdfCnfDelega idfCnfDelega ) {
		return super.doExists(idfCnfDelega);
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
