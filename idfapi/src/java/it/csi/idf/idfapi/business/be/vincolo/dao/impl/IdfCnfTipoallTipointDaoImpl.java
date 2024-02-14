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
import it.csi.idf.idfapi.business.be.vincolo.dao.IdfCnfTipoallTipointDAO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfCnfTipoallTipoint;
import it.csi.idf.idfapi.dto.TipoAllegatoExtended;
import it.csi.idf.idfapi.mapper.TipoAllegatoExtendedMapper;
import it.csi.idf.idfapi.util.DBUtil;

/**
 * IdfCnfTipoallTipoint DAO implementation 
 * 
 */
//@Named("IdfCnfTipoallTipointDAO")
@Component
public class IdfCnfTipoallTipointDaoImpl extends GenericJdbcDAO<IdfCnfTipoallTipoint> implements IdfCnfTipoallTipointDAO {

	private final static String SQL_SELECT_ALL = 
		"select id_tipo_allegato, id_tipo_intervento, flg_obbligatorio from idf_cnf_tipoall_tipoint"; 
	
	private final static String SQL_SELECT_BY_TIPO_INTERVENTO = 
			"select id_tipo_allegato, id_tipo_intervento, flg_obbligatorio from idf_cnf_tipoall_tipoint WHERE id_tipo_intervento = ?"; 

	private final static String SQL_SELECT = 
		"select id_tipo_allegato, id_tipo_intervento, flg_obbligatorio from idf_cnf_tipoall_tipoint where id_tipo_allegato = ? and id_tipo_intervento = ?";

	private final static String SQL_INSERT = 
		"insert into idf_cnf_tipoall_tipoint ( id_tipo_allegato, id_tipo_intervento, flg_obbligatorio ) values ( ?, ?, ? )";

	private final static String SQL_UPDATE = 
		"update idf_cnf_tipoall_tipoint set flg_obbligatorio = ? where id_tipo_allegato = ? and id_tipo_intervento = ?";

	private final static String SQL_DELETE = 
		"delete from idf_cnf_tipoall_tipoint where id_tipo_allegato = ? and id_tipo_intervento = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from idf_cnf_tipoall_tipoint";

	private final static String SQL_COUNT = 
		"select count(*) from idf_cnf_tipoall_tipoint where id_tipo_allegato = ? and id_tipo_intervento = ?";

    //----------------------------------------------------------------------
	/**
	 * DAO constructor
	 */
	public IdfCnfTipoallTipointDaoImpl() {
		super();
	}

    //----------------------------------------------------------------------
	@Override
	protected void setAutoIncrementedKey(IdfCnfTipoallTipoint record, long value) {
		throw new IllegalStateException("Unexpected call to method 'setAutoIncrementedKey'");
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForPrimaryKey(PreparedStatement ps, int i, IdfCnfTipoallTipoint idfCnfTipoallTipoint) throws SQLException {
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, idfCnfTipoallTipoint.getIdTipoAllegato() ) ; // "id_tipo_allegato" : java.lang.Integer
		setValue(ps, i++, idfCnfTipoallTipoint.getIdTipoIntervento() ) ; // "id_tipo_intervento" : java.math.BigDecimal
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForInsert(PreparedStatement ps, int i, IdfCnfTipoallTipoint idfCnfTipoallTipoint) throws SQLException {
		//--- Set PRIMARY KEY and DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, idfCnfTipoallTipoint.getIdTipoAllegato() ) ; // "id_tipo_allegato" : java.lang.Integer
		setValue(ps, i++, idfCnfTipoallTipoint.getIdTipoIntervento() ) ; // "id_tipo_intervento" : java.math.BigDecimal
		setValue(ps, i++, idfCnfTipoallTipoint.getFlgObbligatorio() ) ; // "flg_obbligatorio" : java.math.BigDecimal
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForUpdate(PreparedStatement ps, int i, IdfCnfTipoallTipoint idfCnfTipoallTipoint) throws SQLException {
		//--- Set DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, idfCnfTipoallTipoint.getFlgObbligatorio() ) ; // "flg_obbligatorio" : java.math.BigDecimal
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, idfCnfTipoallTipoint.getIdTipoAllegato() ) ; // "id_tipo_allegato" : java.lang.Integer
		setValue(ps, i++, idfCnfTipoallTipoint.getIdTipoIntervento() ) ; // "id_tipo_intervento" : java.math.BigDecimal
	}

	//----------------------------------------------------------------------
	/**
	 * Creates a new instance of the bean and populates it with the given primary value(s)
	 * @param idTipoAllegato;
	 * @param idTipoIntervento;
	 * @return the new instance
	 */
	private IdfCnfTipoallTipoint newInstanceWithPrimaryKey( Integer idTipoAllegato, BigDecimal idTipoIntervento ) {
		IdfCnfTipoallTipoint idfCnfTipoallTipoint = new IdfCnfTipoallTipoint();
		idfCnfTipoallTipoint.setIdTipoAllegato( idTipoAllegato );
		idfCnfTipoallTipoint.setIdTipoIntervento( idTipoIntervento );
		return idfCnfTipoallTipoint ;
	}

	//----------------------------------------------------------------------
	@Override
	protected IdfCnfTipoallTipoint newInstance() {
		return new IdfCnfTipoallTipoint() ;
	}

    //----------------------------------------------------------------------
	@Override
	protected IdfCnfTipoallTipoint populateBean(ResultSet rs, IdfCnfTipoallTipoint idfCnfTipoallTipoint) throws SQLException {

		//--- Set data from ResultSet to Bean attributes
		idfCnfTipoallTipoint.setIdTipoAllegato(rs.getInt("id_tipo_allegato")); // java.lang.Integer
		if ( rs.wasNull() ) { idfCnfTipoallTipoint.setIdTipoAllegato(null); }; // not primitive number => keep null value if any
		idfCnfTipoallTipoint.setIdTipoIntervento(rs.getBigDecimal("id_tipo_intervento")); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfCnfTipoallTipoint.setIdTipoIntervento(null); }; // not primitive number => keep null value if any
		idfCnfTipoallTipoint.setFlgObbligatorio(rs.getBigDecimal("flg_obbligatorio")); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfCnfTipoallTipoint.setFlgObbligatorio(null); }; // not primitive number => keep null value if any
		return idfCnfTipoallTipoint ;
	}

	//----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfCnfTipoallTipoint findById( Integer idTipoAllegato, BigDecimal idTipoIntervento ) {
		IdfCnfTipoallTipoint idfCnfTipoallTipoint = newInstanceWithPrimaryKey( idTipoAllegato, idTipoIntervento ) ;
		if ( super.doSelect(idfCnfTipoallTipoint) ) {
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
		return super.doSelectAll();
	}

	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param idfCnfTipoallTipoint
	 * @return true if found, false if not found
	 */
	@Override
	public boolean load( IdfCnfTipoallTipoint idfCnfTipoallTipoint ) {
		return super.doSelect(idfCnfTipoallTipoint) ;
	}

    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param idfCnfTipoallTipoint
	 */
	@Override
	public long insert(IdfCnfTipoallTipoint idfCnfTipoallTipoint) {
		super.doInsert(idfCnfTipoallTipoint);
		return 0L ;
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfCnfTipoallTipoint create(IdfCnfTipoallTipoint idfCnfTipoallTipoint) {
		insert(idfCnfTipoallTipoint);
		return idfCnfTipoallTipoint ;
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean update(IdfCnfTipoallTipoint idfCnfTipoallTipoint) {
		int r = super.doUpdate(idfCnfTipoallTipoint);
		return r > 0 ;

	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfCnfTipoallTipoint save(IdfCnfTipoallTipoint idfCnfTipoallTipoint) {
		if ( super.doExists(idfCnfTipoallTipoint) ) {
			super.doUpdate(idfCnfTipoallTipoint);
		}
		else {
			super.doInsert(idfCnfTipoallTipoint);
		}
		return idfCnfTipoallTipoint ;
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean deleteById( Integer idTipoAllegato, BigDecimal idTipoIntervento ) {
		IdfCnfTipoallTipoint idfCnfTipoallTipoint = newInstanceWithPrimaryKey( idTipoAllegato, idTipoIntervento ) ;
		int r = super.doDelete(idfCnfTipoallTipoint);
		return r > 0 ;
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean delete( IdfCnfTipoallTipoint idfCnfTipoallTipoint ) {
		int r = super.doDelete(idfCnfTipoallTipoint);
		return r > 0 ;
	}

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param idTipoAllegato;
	 * @param idTipoIntervento;
	 * @return
	 */
	// @Override
	@Override
	public boolean exists( Integer idTipoAllegato, BigDecimal idTipoIntervento ) {
		IdfCnfTipoallTipoint idfCnfTipoallTipoint = newInstanceWithPrimaryKey( idTipoAllegato, idTipoIntervento ) ;
		return super.doExists(idfCnfTipoallTipoint);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param idfCnfTipoallTipoint
	 * @return
	 */
	// @Override
	@Override
	public boolean exists( IdfCnfTipoallTipoint idfCnfTipoallTipoint ) {
		return super.doExists(idfCnfTipoallTipoint);
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
	public List<TipoAllegatoExtended> getTipiDocumentoByIdTipoIntervento(BigDecimal idTipoIntervento) {
		String sql = "SELECT tipo.id_tipo_allegato, tipo.descr_tipo_allegato, rel.flg_obbligatorio FROM idf_d_tipo_allegato tipo " + 
				"JOIN idf_cnf_tipoall_tipoint rel " + 
				"ON tipo.id_tipo_allegato = rel.id_tipo_allegato " + 
				"WHERE rel.id_tipo_intervento = ? ORDER BY mtd_ordinamento ASC";
		return DBUtil.jdbcTemplate.query(sql.toString(), 
				new Object[] {idTipoIntervento}, new TipoAllegatoExtendedMapper());
	}
	

}
