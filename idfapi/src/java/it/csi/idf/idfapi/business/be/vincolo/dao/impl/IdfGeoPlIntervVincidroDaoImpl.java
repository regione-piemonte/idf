/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
//
// Created on 2020-10-04 ( Date ISO 2020-10-04 - Time 22:37:05 )
// java7-persistance-jdbc-T300
package it.csi.idf.idfapi.business.be.vincolo.dao.impl;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.vincolo.dao.GenericJdbcDAO;
import it.csi.idf.idfapi.business.be.vincolo.dao.IdfGeoPlIntervVincidroDAO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfGeoPlIntervVincidro;
import it.csi.idf.idfapi.util.DBUtil;

/**
 * IdfGeoPlIntervVincidro DAO implementation 
 * 
 */
//@Named("IdfGeoPlIntervVincidroDAO")
@Component
public class IdfGeoPlIntervVincidroDaoImpl extends GenericJdbcDAO<IdfGeoPlIntervVincidro> implements IdfGeoPlIntervVincidroDAO {

	private final static String SQL_SELECT_ALL = 
		"select id_geo_pl_interv_vincidro, fk_intervento, data_inserimento, geometria from idf_geo_pl_interv_vincidro"; 

	private final static String SQL_SELECT = 
		"select id_geo_pl_interv_vincidro, fk_intervento, data_inserimento, geometria from idf_geo_pl_interv_vincidro where id_geo_pl_interv_vincidro = ?";

	private final static String SQL_INSERT = 
		"insert into idf_geo_pl_interv_vincidro ( id_geo_pl_interv_vincidro, fk_intervento, data_inserimento, geometria ) values ( ?, ?, ?, ? )";

	private final static String SQL_UPDATE = 
		"update idf_geo_pl_interv_vincidro set fk_intervento = ?, data_inserimento = ?, geometria = ? where id_geo_pl_interv_vincidro = ?";

	private final static String SQL_DELETE = 
		"delete from idf_geo_pl_interv_vincidro where id_geo_pl_interv_vincidro = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from idf_geo_pl_interv_vincidro";

	private final static String SQL_COUNT = 
		"select count(*) from idf_geo_pl_interv_vincidro where id_geo_pl_interv_vincidro = ?";

    //----------------------------------------------------------------------
	/**
	 * DAO constructor
	 */
	public IdfGeoPlIntervVincidroDaoImpl() {
		super();
	}

    //----------------------------------------------------------------------
	@Override
	protected void setAutoIncrementedKey(IdfGeoPlIntervVincidro record, long value) {
		throw new IllegalStateException("Unexpected call to method 'setAutoIncrementedKey'");
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForPrimaryKey(PreparedStatement ps, int i, IdfGeoPlIntervVincidro idfGeoPlIntervVincidro) throws SQLException {
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, idfGeoPlIntervVincidro.getIdGeoPlIntervVincidro() ) ; // "id_geo_pl_interv_vincidro" : java.lang.Integer
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForInsert(PreparedStatement ps, int i, IdfGeoPlIntervVincidro idfGeoPlIntervVincidro) throws SQLException {
		//--- Set PRIMARY KEY and DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, idfGeoPlIntervVincidro.getIdGeoPlIntervVincidro() ) ; // "id_geo_pl_interv_vincidro" : java.lang.Integer
		setValue(ps, i++, idfGeoPlIntervVincidro.getFkIntervento() ) ; // "fk_intervento" : java.math.BigDecimal
		setValue(ps, i++, idfGeoPlIntervVincidro.getDataInserimento().toString() ) ; // "data_inserimento" : java.util.Date
		setValue(ps, i++, idfGeoPlIntervVincidro.getGeometria().toString() ) ; // "geometria" : java.lang.String
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForUpdate(PreparedStatement ps, int i, IdfGeoPlIntervVincidro idfGeoPlIntervVincidro) throws SQLException {
		//--- Set DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, idfGeoPlIntervVincidro.getFkIntervento() ) ; // "fk_intervento" : java.math.BigDecimal
		setValue(ps, i++, idfGeoPlIntervVincidro.getDataInserimento().toString() ) ; // "data_inserimento" : java.util.Date
		setValue(ps, i++, idfGeoPlIntervVincidro.getGeometria().toString() ) ; // "geometria" : java.lang.String
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, idfGeoPlIntervVincidro.getIdGeoPlIntervVincidro() ) ; // "id_geo_pl_interv_vincidro" : java.lang.Integer
	}

	//----------------------------------------------------------------------
	/**
	 * Creates a new instance of the bean and populates it with the given primary value(s)
	 * @param idGeoPlIntervVincidro;
	 * @return the new instance
	 */
	private IdfGeoPlIntervVincidro newInstanceWithPrimaryKey( Integer idGeoPlIntervVincidro ) {
		IdfGeoPlIntervVincidro idfGeoPlIntervVincidro = new IdfGeoPlIntervVincidro();
		idfGeoPlIntervVincidro.setIdGeoPlIntervVincidro( idGeoPlIntervVincidro );
		return idfGeoPlIntervVincidro ;
	}

	//----------------------------------------------------------------------
	@Override
	protected IdfGeoPlIntervVincidro newInstance() {
		return new IdfGeoPlIntervVincidro() ;
	}

    //----------------------------------------------------------------------
	@Override
	protected IdfGeoPlIntervVincidro populateBean(ResultSet rs, IdfGeoPlIntervVincidro idfGeoPlIntervVincidro) throws SQLException {

		//--- Set data from ResultSet to Bean attributes
		idfGeoPlIntervVincidro.setIdGeoPlIntervVincidro(rs.getInt("id_geo_pl_interv_vincidro")); // java.lang.Integer
		if ( rs.wasNull() ) { idfGeoPlIntervVincidro.setIdGeoPlIntervVincidro(null); }; // not primitive number => keep null value if any
		idfGeoPlIntervVincidro.setFkIntervento(rs.getInt(("fk_intervento"))); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfGeoPlIntervVincidro.setFkIntervento(null); }; // not primitive number => keep null value if any
		//idfGeoPlIntervVincidro.setDataInserimento(rs.getDate("data_inserimento")); // java.util.Date
		
		idfGeoPlIntervVincidro.setDataInserimento(Instant.ofEpochMilli(rs.getDate("data_inserimento").getTime()).atZone(ZoneId.systemDefault()).toLocalDate()); // java.util.Date
		
		idfGeoPlIntervVincidro.setGeometria(rs.getString("geometria")); // java.lang.String
		return idfGeoPlIntervVincidro ;
	}

	//----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfGeoPlIntervVincidro findById( Integer idGeoPlIntervVincidro ) {
		IdfGeoPlIntervVincidro idfGeoPlIntervVincidro = newInstanceWithPrimaryKey( idGeoPlIntervVincidro ) ;
		if ( super.doSelect(idfGeoPlIntervVincidro) ) {
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
		return super.doSelectAll();
	}

	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param idfGeoPlIntervVincidro
	 * @return true if found, false if not found
	 */
	@Override
	public boolean load( IdfGeoPlIntervVincidro idfGeoPlIntervVincidro ) {
		return super.doSelect(idfGeoPlIntervVincidro) ;
	}

    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param idfGeoPlIntervVincidro
	 */
	@Override
	public long insert(IdfGeoPlIntervVincidro idfGeoPlIntervVincidro) {
		super.doInsert(idfGeoPlIntervVincidro);
		return 0L ;
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfGeoPlIntervVincidro create(IdfGeoPlIntervVincidro idfGeoPlIntervVincidro) {
		insert(idfGeoPlIntervVincidro);
		return idfGeoPlIntervVincidro ;
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean update(IdfGeoPlIntervVincidro idfGeoPlIntervVincidro) {
		int r = super.doUpdate(idfGeoPlIntervVincidro);
		return r > 0 ;

	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfGeoPlIntervVincidro save(IdfGeoPlIntervVincidro idfGeoPlIntervVincidro) {
		if ( super.doExists(idfGeoPlIntervVincidro) ) {
			super.doUpdate(idfGeoPlIntervVincidro);
		}
		else {
			super.doInsert(idfGeoPlIntervVincidro);
		}
		return idfGeoPlIntervVincidro ;
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean deleteById( Integer idGeoPlIntervVincidro ) {
		IdfGeoPlIntervVincidro idfGeoPlIntervVincidro = newInstanceWithPrimaryKey( idGeoPlIntervVincidro ) ;
		int r = super.doDelete(idfGeoPlIntervVincidro);
		return r > 0 ;
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean delete( IdfGeoPlIntervVincidro idfGeoPlIntervVincidro ) {
		int r = super.doDelete(idfGeoPlIntervVincidro);
		return r > 0 ;
	}

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param idGeoPlIntervVincidro;
	 * @return
	 */
	// @Override
	@Override
	public boolean exists( Integer idGeoPlIntervVincidro ) {
		IdfGeoPlIntervVincidro idfGeoPlIntervVincidro = newInstanceWithPrimaryKey( idGeoPlIntervVincidro ) ;
		return super.doExists(idfGeoPlIntervVincidro);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param idfGeoPlIntervVincidro
	 * @return
	 */
	// @Override
	@Override
	public boolean exists( IdfGeoPlIntervVincidro idfGeoPlIntervVincidro ) {
		return super.doExists(idfGeoPlIntervVincidro);
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
	public void insertIntervVincidro(IdfGeoPlIntervVincidro idfGeoPlIntervVincidro) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO idf_geo_pl_interv_vincidro(");
		//sql.append("fk_intervento, data_inserimento");
		//append(") VALUES (?, ?)");
		sql.append("fk_intervento, data_inserimento, geometria");
		sql.append(") VALUES (?, ?, ?)");
		
		List<Object> parameters = new ArrayList<>();
		parameters.add(idfGeoPlIntervVincidro.getFkIntervento());
		parameters.add(idfGeoPlIntervVincidro.getDataInserimento() == null ? null : Date.valueOf(idfGeoPlIntervVincidro.getDataInserimento()));
		parameters.add(idfGeoPlIntervVincidro.getGeometria());
		
		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}

	@Override
	public void duplicateIntervento(Integer idIntervento, Integer idInterventoNew, Integer idConfUtente) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into idf_geo_pl_interv_vincidro(id_geo_pl_interv_vincidro, ");
		sql.append("fk_intervento, data_inserimento, geometria) "); 
		sql.append("select nextval('seq_idf_geo_pl_interv_vincidro'), ?, ?, geometria ");
		sql.append("from idf_geo_pl_interv_vincidro where fk_intervento = ?");
		List<Object> params = new ArrayList<Object>();
		params.add(idInterventoNew);
		params.add(Date.valueOf(LocalDate.now()));
		params.add(idIntervento);
		DBUtil.jdbcTemplate.update(sql.toString(), params.toArray());
		
		sql = new StringBuilder();
		sql.append("INSERT INTO idf_r_propcatasto_intervento (idgeo_pl_prop_catasto, id_intervento, data_inserimento, ");
		sql.append("fk_config_utente, sup_intervento_ha) "); 
		sql.append("select idgeo_pl_prop_catasto, ?, ?, ?, sup_intervento_ha ");
		sql.append("from idf_r_propcatasto_intervento where id_intervento = ?");
		params = new ArrayList<Object>();
		params.add(idInterventoNew);
		params.add(Date.valueOf(LocalDate.now()));
		params.add(idConfUtente);
		params.add(idIntervento);
		DBUtil.jdbcTemplate.update(sql.toString(), params.toArray());
	}

	@Override
	public int deleteByFkIntervento(Integer fkIntervento) {
		String sql = "delete from idf_geo_pl_interv_vincidro where fk_intervento = ?";
		return DBUtil.jdbcTemplate.update(sql, fkIntervento);
	}

}
