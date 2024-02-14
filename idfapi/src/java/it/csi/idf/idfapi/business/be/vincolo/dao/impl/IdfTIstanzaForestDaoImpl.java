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
import it.csi.idf.idfapi.business.be.vincolo.dao.IdfTIstanzaForestDAO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfTIstanzaForest;

/**
 * IdfTIstanzaForest DAO implementation 
 * 
 */
//@Named("IdfTIstanzaForestDAO")
@Component
public class IdfTIstanzaForestDaoImpl extends GenericJdbcDAO<IdfTIstanzaForest> implements IdfTIstanzaForestDAO {

	private final static String SQL_SELECT_ALL = 
		"select id_istanza_intervento, fk_sogg_settore_regionale, fk_stato_istanza, nr_istanza_forestale, data_invio, data_verifica, data_protocollo, nr_protocollo, data_ult_agg, data_inserimento, data_aggiornamento, fk_config_utente, fk_tipo_istanza, nr_determina_aut, data_termine_aut, motivazione_rifiuto from idf_t_istanza_forest"; 

	private final static String SQL_SELECT = 
		"select id_istanza_intervento, fk_sogg_settore_regionale, fk_stato_istanza, nr_istanza_forestale, data_invio, data_verifica, data_protocollo, nr_protocollo, data_ult_agg, data_inserimento, data_aggiornamento, fk_config_utente, fk_tipo_istanza, nr_determina_aut, data_termine_aut, motivazione_rifiuto from idf_t_istanza_forest where id_istanza_intervento = ?";

	private final static String SQL_INSERT = 
		"insert into idf_t_istanza_forest ( id_istanza_intervento, fk_sogg_settore_regionale, fk_stato_istanza, nr_istanza_forestale, data_invio, data_verifica, data_protocollo, nr_protocollo, data_ult_agg, data_inserimento, data_aggiornamento, fk_config_utente, fk_tipo_istanza, nr_determina_aut, data_termine_aut, motivazione_rifiuto ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

	private final static String SQL_UPDATE = 
		"update idf_t_istanza_forest set fk_sogg_settore_regionale = ?, fk_stato_istanza = ?, nr_istanza_forestale = ?, data_invio = ?, data_verifica = ?, data_protocollo = ?, nr_protocollo = ?, data_ult_agg = ?, data_inserimento = ?, data_aggiornamento = ?, fk_config_utente = ?, fk_tipo_istanza = ?, nr_determina_aut = ?, data_termine_aut = ?, motivazione_rifiuto = ? where id_istanza_intervento = ?";

	private final static String SQL_DELETE = 
		"delete from idf_t_istanza_forest where id_istanza_intervento = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from idf_t_istanza_forest";

	private final static String SQL_COUNT = 
		"select count(*) from idf_t_istanza_forest where id_istanza_intervento = ?";

    //----------------------------------------------------------------------
	/**
	 * DAO constructor
	 */
	public IdfTIstanzaForestDaoImpl() {
		super();
	}

    //----------------------------------------------------------------------
	@Override
	protected void setAutoIncrementedKey(IdfTIstanzaForest record, long value) {
		throw new IllegalStateException("Unexpected call to method 'setAutoIncrementedKey'");
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForPrimaryKey(PreparedStatement ps, int i, IdfTIstanzaForest idfTIstanzaForest) throws SQLException {
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, idfTIstanzaForest.getIdIstanzaIntervento() ) ; // "id_istanza_intervento" : java.math.BigDecimal
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForInsert(PreparedStatement ps, int i, IdfTIstanzaForest idfTIstanzaForest) throws SQLException {
		//--- Set PRIMARY KEY and DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, idfTIstanzaForest.getIdIstanzaIntervento() ) ; // "id_istanza_intervento" : java.math.BigDecimal
		setValue(ps, i++, idfTIstanzaForest.getFkSoggSettoreRegionale() ) ; // "fk_sogg_settore_regionale" : java.math.BigDecimal
		setValue(ps, i++, idfTIstanzaForest.getFkStatoIstanza() ) ; // "fk_stato_istanza" : java.math.BigDecimal
		setValue(ps, i++, idfTIstanzaForest.getNrIstanzaForestale() ) ; // "nr_istanza_forestale" : java.math.BigDecimal
		setValue(ps, i++, idfTIstanzaForest.getDataInvio() ) ; // "data_invio" : java.util.Date
		setValue(ps, i++, idfTIstanzaForest.getDataVerifica() ) ; // "data_verifica" : java.util.Date
		setValue(ps, i++, idfTIstanzaForest.getDataProtocollo() ) ; // "data_protocollo" : java.util.Date
		setValue(ps, i++, idfTIstanzaForest.getNrProtocollo() ) ; // "nr_protocollo" : java.lang.String
		setValue(ps, i++, idfTIstanzaForest.getDataUltAgg() ) ; // "data_ult_agg" : java.util.Date
		setValue(ps, i++, idfTIstanzaForest.getDataInserimento() ) ; // "data_inserimento" : java.util.Date
		setValue(ps, i++, idfTIstanzaForest.getDataAggiornamento() ) ; // "data_aggiornamento" : java.util.Date
		setValue(ps, i++, idfTIstanzaForest.getFkConfigUtente() ) ; // "fk_config_utente" : java.lang.Integer
		setValue(ps, i++, idfTIstanzaForest.getFkTipoIstanza() ) ; // "fk_tipo_istanza" : java.math.BigDecimal
		setValue(ps, i++, idfTIstanzaForest.getNrDeterminaAut() ) ; // "nr_determina_aut" : java.lang.String
		setValue(ps, i++, idfTIstanzaForest.getDataTermineAut() ) ; // "data_termine_aut" : java.util.Date
		setValue(ps, i++, idfTIstanzaForest.getMotivazioneRifiuto() ) ; // "motivazione_rifiuto" : java.lang.String
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForUpdate(PreparedStatement ps, int i, IdfTIstanzaForest idfTIstanzaForest) throws SQLException {
		//--- Set DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, idfTIstanzaForest.getFkSoggSettoreRegionale() ) ; // "fk_sogg_settore_regionale" : java.math.BigDecimal
		setValue(ps, i++, idfTIstanzaForest.getFkStatoIstanza() ) ; // "fk_stato_istanza" : java.math.BigDecimal
		setValue(ps, i++, idfTIstanzaForest.getNrIstanzaForestale() ) ; // "nr_istanza_forestale" : java.math.BigDecimal
		setValue(ps, i++, idfTIstanzaForest.getDataInvio() ) ; // "data_invio" : java.util.Date
		setValue(ps, i++, idfTIstanzaForest.getDataVerifica() ) ; // "data_verifica" : java.util.Date
		setValue(ps, i++, idfTIstanzaForest.getDataProtocollo() ) ; // "data_protocollo" : java.util.Date
		setValue(ps, i++, idfTIstanzaForest.getNrProtocollo() ) ; // "nr_protocollo" : java.lang.String
		setValue(ps, i++, idfTIstanzaForest.getDataUltAgg() ) ; // "data_ult_agg" : java.util.Date
		setValue(ps, i++, idfTIstanzaForest.getDataInserimento() ) ; // "data_inserimento" : java.util.Date
		setValue(ps, i++, idfTIstanzaForest.getDataAggiornamento() ) ; // "data_aggiornamento" : java.util.Date
		setValue(ps, i++, idfTIstanzaForest.getFkConfigUtente() ) ; // "fk_config_utente" : java.lang.Integer
		setValue(ps, i++, idfTIstanzaForest.getFkTipoIstanza() ) ; // "fk_tipo_istanza" : java.math.BigDecimal
		setValue(ps, i++, idfTIstanzaForest.getNrDeterminaAut() ) ; // "nr_determina_aut" : java.lang.String
		setValue(ps, i++, idfTIstanzaForest.getDataTermineAut() ) ; // "data_termine_aut" : java.util.Date
		setValue(ps, i++, idfTIstanzaForest.getMotivazioneRifiuto() ) ; // "motivazione_rifiuto" : java.lang.String
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, idfTIstanzaForest.getIdIstanzaIntervento() ) ; // "id_istanza_intervento" : java.math.BigDecimal
	}

	//----------------------------------------------------------------------
	/**
	 * Creates a new instance of the bean and populates it with the given primary value(s)
	 * @param idIstanzaIntervento;
	 * @return the new instance
	 */
	private IdfTIstanzaForest newInstanceWithPrimaryKey( BigDecimal idIstanzaIntervento ) {
		IdfTIstanzaForest idfTIstanzaForest = new IdfTIstanzaForest();
		idfTIstanzaForest.setIdIstanzaIntervento( idIstanzaIntervento );
		return idfTIstanzaForest ;
	}

	//----------------------------------------------------------------------
	@Override
	protected IdfTIstanzaForest newInstance() {
		return new IdfTIstanzaForest() ;
	}

    //----------------------------------------------------------------------
	@Override
	protected IdfTIstanzaForest populateBean(ResultSet rs, IdfTIstanzaForest idfTIstanzaForest) throws SQLException {

		//--- Set data from ResultSet to Bean attributes
		idfTIstanzaForest.setIdIstanzaIntervento(rs.getBigDecimal("id_istanza_intervento")); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfTIstanzaForest.setIdIstanzaIntervento(null); }; // not primitive number => keep null value if any
		idfTIstanzaForest.setFkSoggSettoreRegionale(rs.getBigDecimal("fk_sogg_settore_regionale")); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfTIstanzaForest.setFkSoggSettoreRegionale(null); }; // not primitive number => keep null value if any
		idfTIstanzaForest.setFkStatoIstanza(rs.getBigDecimal("fk_stato_istanza")); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfTIstanzaForest.setFkStatoIstanza(null); }; // not primitive number => keep null value if any
		idfTIstanzaForest.setNrIstanzaForestale(rs.getBigDecimal("nr_istanza_forestale")); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfTIstanzaForest.setNrIstanzaForestale(null); }; // not primitive number => keep null value if any
		idfTIstanzaForest.setDataInvio(rs.getDate("data_invio")); // java.util.Date
		idfTIstanzaForest.setDataVerifica(rs.getDate("data_verifica")); // java.util.Date
		idfTIstanzaForest.setDataProtocollo(rs.getDate("data_protocollo")); // java.util.Date
		idfTIstanzaForest.setNrProtocollo(rs.getString("nr_protocollo")); // java.lang.String
		idfTIstanzaForest.setDataUltAgg(rs.getDate("data_ult_agg")); // java.util.Date
		idfTIstanzaForest.setDataInserimento(rs.getDate("data_inserimento")); // java.util.Date
		idfTIstanzaForest.setDataAggiornamento(rs.getDate("data_aggiornamento")); // java.util.Date
		idfTIstanzaForest.setFkConfigUtente(rs.getInt("fk_config_utente")); // java.lang.Integer
		if ( rs.wasNull() ) { idfTIstanzaForest.setFkConfigUtente(null); }; // not primitive number => keep null value if any
		idfTIstanzaForest.setFkTipoIstanza(rs.getBigDecimal("fk_tipo_istanza")); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfTIstanzaForest.setFkTipoIstanza(null); }; // not primitive number => keep null value if any
		idfTIstanzaForest.setNrDeterminaAut(rs.getString("nr_determina_aut")); // java.lang.String
		idfTIstanzaForest.setDataTermineAut(rs.getDate("data_termine_aut")); // java.util.Date
		idfTIstanzaForest.setMotivazioneRifiuto(rs.getString("motivazione_rifiuto")); // java.lang.String
		return idfTIstanzaForest ;
	}

	//----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfTIstanzaForest findById( BigDecimal idIstanzaIntervento ) {
		IdfTIstanzaForest idfTIstanzaForest = newInstanceWithPrimaryKey( idIstanzaIntervento ) ;
		if ( super.doSelect(idfTIstanzaForest) ) {
			return idfTIstanzaForest ;
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
	public List<IdfTIstanzaForest> findAll() {
		return super.doSelectAll();
	}

	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param idfTIstanzaForest
	 * @return true if found, false if not found
	 */
	//@Override
	@Override
	public boolean load( IdfTIstanzaForest idfTIstanzaForest ) {
		return super.doSelect(idfTIstanzaForest) ;
	}

    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param idfTIstanzaForest
	 */
	public long insert(IdfTIstanzaForest idfTIstanzaForest) {
		super.doInsert(idfTIstanzaForest);
		return 0L ;
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfTIstanzaForest create(IdfTIstanzaForest idfTIstanzaForest) {
		insert(idfTIstanzaForest);
		return idfTIstanzaForest ;
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean update(IdfTIstanzaForest idfTIstanzaForest) {
		int r = super.doUpdate(idfTIstanzaForest);
		return r > 0 ;

	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfTIstanzaForest save(IdfTIstanzaForest idfTIstanzaForest) {
		if ( super.doExists(idfTIstanzaForest) ) {
			super.doUpdate(idfTIstanzaForest);
		}
		else {
			super.doInsert(idfTIstanzaForest);
		}
		return idfTIstanzaForest ;
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean deleteById( BigDecimal idIstanzaIntervento ) {
		IdfTIstanzaForest idfTIstanzaForest = newInstanceWithPrimaryKey( idIstanzaIntervento ) ;
		int r = super.doDelete(idfTIstanzaForest);
		return r > 0 ;
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean delete( IdfTIstanzaForest idfTIstanzaForest ) {
		int r = super.doDelete(idfTIstanzaForest);
		return r > 0 ;
	}

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param idIstanzaIntervento;
	 * @return
	 */
	// @Override
	@Override
	public boolean exists( BigDecimal idIstanzaIntervento ) {
		IdfTIstanzaForest idfTIstanzaForest = newInstanceWithPrimaryKey( idIstanzaIntervento ) ;
		return super.doExists(idfTIstanzaForest);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param idfTIstanzaForest
	 * @return
	 */
	// @Override
	@Override
	public boolean exists( IdfTIstanzaForest idfTIstanzaForest ) {
		return super.doExists(idfTIstanzaForest);
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
