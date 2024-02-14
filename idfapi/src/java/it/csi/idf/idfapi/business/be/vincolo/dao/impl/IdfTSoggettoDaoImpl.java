/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.dao.impl;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.vincolo.dao.GenericJdbcDAO;
import it.csi.idf.idfapi.business.be.vincolo.dao.IdfTSoggettoDAO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfTSoggetto;

/**
 * IdfTSoggetto DAO implementation 
 * 
 */
//@Named("IdfTSoggettoDAO")
@Component
public class IdfTSoggettoDaoImpl extends GenericJdbcDAO<IdfTSoggetto> implements IdfTSoggettoDAO {

	private final static String SQL_SELECT_ALL = 
		"select id_soggetto, fk_comune, nome, cognome, codice_fiscale, partita_iva, denominazione, indirizzo, nr_telefonico, e_mail, pec, n_iscrizione_ordine, istat_prov_iscrizione_ordine, istat_prov_competenza_terr, flg_settore_regionale, data_inserimento, data_aggiornamento, fk_config_utente, civico, cap, fk_categoria_professionale, istat_comune_competenza_terr from idf_t_soggetto"; 

	private final static String SQL_SELECT = 
		"select id_soggetto, fk_comune, nome, cognome, codice_fiscale, partita_iva, denominazione, indirizzo, nr_telefonico, e_mail, pec, n_iscrizione_ordine, istat_prov_iscrizione_ordine, istat_prov_competenza_terr, flg_settore_regionale, data_inserimento, data_aggiornamento, fk_config_utente, civico, cap, fk_categoria_professionale, istat_comune_competenza_terr from idf_t_soggetto where id_soggetto = ?";

	private final static String SQL_INSERT = 
		"insert into idf_t_soggetto ( fk_comune, nome, cognome, codice_fiscale, partita_iva, denominazione, indirizzo, nr_telefonico, e_mail, pec, n_iscrizione_ordine, istat_prov_iscrizione_ordine, istat_prov_competenza_terr, flg_settore_regionale, data_inserimento, data_aggiornamento, fk_config_utente, civico, cap, fk_categoria_professionale, istat_comune_competenza_terr ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

	private final static String SQL_UPDATE = 
		"update idf_t_soggetto set fk_comune = ?, nome = ?, cognome = ?, codice_fiscale = ?, partita_iva = ?, denominazione = ?, indirizzo = ?, nr_telefonico = ?, e_mail = ?, pec = ?, n_iscrizione_ordine = ?, istat_prov_iscrizione_ordine = ?, istat_prov_competenza_terr = ?, flg_settore_regionale = ?, data_inserimento = ?, data_aggiornamento = ?, fk_config_utente = ?, civico = ?, cap = ?, fk_categoria_professionale = ?, istat_comune_competenza_terr = ? where id_soggetto = ?";

	private final static String SQL_DELETE = 
		"delete from idf_t_soggetto where id_soggetto = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from idf_t_soggetto";

	private final static String SQL_COUNT = 
		"select count(*) from idf_t_soggetto where id_soggetto = ?";

    //----------------------------------------------------------------------
	/**
	 * DAO constructor
	 */
	public IdfTSoggettoDaoImpl() {
		super();
	}

    //----------------------------------------------------------------------
	@Override
	protected void setAutoIncrementedKey(IdfTSoggetto record, long value) {
		record.setIdSoggetto((int) value);
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForPrimaryKey(PreparedStatement ps, int i, IdfTSoggetto idfTSoggetto) throws SQLException {
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, idfTSoggetto.getIdSoggetto() ) ; // "id_soggetto" : java.lang.Integer
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForInsert(PreparedStatement ps, int i, IdfTSoggetto idfTSoggetto) throws SQLException {
		//--- Set PRIMARY KEY and DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		// "id_soggetto" is auto-incremented => no set in insert		
		setValue(ps, i++, idfTSoggetto.getFkComune() ) ; // "fk_comune" : java.lang.Integer
		setValue(ps, i++, idfTSoggetto.getNome() ) ; // "nome" : java.lang.String
		setValue(ps, i++, idfTSoggetto.getCognome() ) ; // "cognome" : java.lang.String
		setValue(ps, i++, idfTSoggetto.getCodiceFiscale() ) ; // "codice_fiscale" : java.lang.String
		setValue(ps, i++, idfTSoggetto.getPartitaIva() ) ; // "partita_iva" : java.lang.String
		setValue(ps, i++, idfTSoggetto.getDenominazione() ) ; // "denominazione" : java.lang.String
		setValue(ps, i++, idfTSoggetto.getIndirizzo() ) ; // "indirizzo" : java.lang.String
		setValue(ps, i++, idfTSoggetto.getNrTelefonico() ) ; // "nr_telefonico" : java.lang.String
		setValue(ps, i++, idfTSoggetto.getEMail() ) ; // "e_mail" : java.lang.String
		setValue(ps, i++, idfTSoggetto.getPec() ) ; // "pec" : java.lang.String
		setValue(ps, i++, idfTSoggetto.getNIscrizioneOrdine() ) ; // "n_iscrizione_ordine" : java.lang.String
		setValue(ps, i++, idfTSoggetto.getIstatProvIscrizioneOrdine() ) ; // "istat_prov_iscrizione_ordine" : java.lang.String
		setValue(ps, i++, idfTSoggetto.getIstatProvCompetenzaTerr() ) ; // "istat_prov_competenza_terr" : java.lang.String
		setValue(ps, i++, idfTSoggetto.getFlgSettoreRegionale() ) ; // "flg_settore_regionale" : java.lang.Integer
		setValue(ps, i++, idfTSoggetto.getDataInserimento() ) ; // "data_inserimento" : java.util.Date
		setValue(ps, i++, idfTSoggetto.getDataAggiornamento() ) ; // "data_aggiornamento" : java.util.Date
		setValue(ps, i++, idfTSoggetto.getFkConfigUtente() ) ; // "fk_config_utente" : java.lang.Integer
		setValue(ps, i++, idfTSoggetto.getCivico() ) ; // "civico" : java.lang.String
		setValue(ps, i++, idfTSoggetto.getCap() ) ; // "cap" : java.lang.String
		setValue(ps, i++, idfTSoggetto.getFkCategoriaProfessionale() ) ; // "fk_categoria_professionale" : java.lang.Integer
		setValue(ps, i++, idfTSoggetto.getIstatComuneCompetenzaTerr() ) ; // "istat_comune_competenza_terr" : java.lang.String
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForUpdate(PreparedStatement ps, int i, IdfTSoggetto idfTSoggetto) throws SQLException {
		//--- Set DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, idfTSoggetto.getFkComune() ) ; // "fk_comune" : java.lang.Integer
		setValue(ps, i++, idfTSoggetto.getNome() ) ; // "nome" : java.lang.String
		setValue(ps, i++, idfTSoggetto.getCognome() ) ; // "cognome" : java.lang.String
		setValue(ps, i++, idfTSoggetto.getCodiceFiscale() ) ; // "codice_fiscale" : java.lang.String
		setValue(ps, i++, idfTSoggetto.getPartitaIva() ) ; // "partita_iva" : java.lang.String
		setValue(ps, i++, idfTSoggetto.getDenominazione() ) ; // "denominazione" : java.lang.String
		setValue(ps, i++, idfTSoggetto.getIndirizzo() ) ; // "indirizzo" : java.lang.String
		setValue(ps, i++, idfTSoggetto.getNrTelefonico() ) ; // "nr_telefonico" : java.lang.String
		setValue(ps, i++, idfTSoggetto.getEMail() ) ; // "e_mail" : java.lang.String
		setValue(ps, i++, idfTSoggetto.getPec() ) ; // "pec" : java.lang.String
		setValue(ps, i++, idfTSoggetto.getNIscrizioneOrdine() ) ; // "n_iscrizione_ordine" : java.lang.String
		setValue(ps, i++, idfTSoggetto.getIstatProvIscrizioneOrdine() ) ; // "istat_prov_iscrizione_ordine" : java.lang.String
		setValue(ps, i++, idfTSoggetto.getIstatProvCompetenzaTerr() ) ; // "istat_prov_competenza_terr" : java.lang.String
		setValue(ps, i++, idfTSoggetto.getFlgSettoreRegionale() ) ; // "flg_settore_regionale" : java.lang.Integer
		setValue(ps, i++, idfTSoggetto.getDataInserimento() ) ; // "data_inserimento" : java.util.Date
		setValue(ps, i++, idfTSoggetto.getDataAggiornamento() ) ; // "data_aggiornamento" : java.util.Date
		setValue(ps, i++, idfTSoggetto.getFkConfigUtente() ) ; // "fk_config_utente" : java.lang.Integer
		setValue(ps, i++, idfTSoggetto.getCivico() ) ; // "civico" : java.lang.String
		setValue(ps, i++, idfTSoggetto.getCap() ) ; // "cap" : java.lang.String
		setValue(ps, i++, idfTSoggetto.getFkCategoriaProfessionale() ) ; // "fk_categoria_professionale" : java.lang.Integer
		setValue(ps, i++, idfTSoggetto.getIstatComuneCompetenzaTerr() ) ; // "istat_comune_competenza_terr" : java.lang.String
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, idfTSoggetto.getIdSoggetto() ) ; // "id_soggetto" : java.lang.Integer
	}

	//----------------------------------------------------------------------
	/**
	 * Creates a new instance of the bean and populates it with the given primary value(s)
	 * @param idSoggetto;
	 * @return the new instance
	 */
	private IdfTSoggetto newInstanceWithPrimaryKey( Integer idSoggetto ) {
		IdfTSoggetto idfTSoggetto = new IdfTSoggetto();
		idfTSoggetto.setIdSoggetto( idSoggetto );
		return idfTSoggetto ;
	}

	//----------------------------------------------------------------------
	@Override
	protected IdfTSoggetto newInstance() {
		return new IdfTSoggetto() ;
	}

    //----------------------------------------------------------------------
	@Override
	protected IdfTSoggetto populateBean(ResultSet rs, IdfTSoggetto idfTSoggetto) throws SQLException {

		//--- Set data from ResultSet to Bean attributes
		idfTSoggetto.setIdSoggetto(rs.getInt("id_soggetto")); // java.lang.Integer
		if ( rs.wasNull() ) { idfTSoggetto.setIdSoggetto(null); }; // not primitive number => keep null value if any
		idfTSoggetto.setFkComune(rs.getInt("fk_comune")); // java.lang.Integer
		if ( rs.wasNull() ) { idfTSoggetto.setFkComune(null); }; // not primitive number => keep null value if any
		idfTSoggetto.setNome(rs.getString("nome")); // java.lang.String
		idfTSoggetto.setCognome(rs.getString("cognome")); // java.lang.String
		idfTSoggetto.setCodiceFiscale(rs.getString("codice_fiscale")); // java.lang.String
		idfTSoggetto.setPartitaIva(rs.getString("partita_iva")); // java.lang.String
		idfTSoggetto.setDenominazione(rs.getString("denominazione")); // java.lang.String
		idfTSoggetto.setIndirizzo(rs.getString("indirizzo")); // java.lang.String
		idfTSoggetto.setNrTelefonico(rs.getString("nr_telefonico")); // java.lang.String
		idfTSoggetto.setEMail(rs.getString("e_mail")); // java.lang.String
		idfTSoggetto.setPec(rs.getString("pec")); // java.lang.String
		idfTSoggetto.setNIscrizioneOrdine(rs.getString("n_iscrizione_ordine")); // java.lang.String
		idfTSoggetto.setIstatProvIscrizioneOrdine(rs.getString("istat_prov_iscrizione_ordine")); // java.lang.String
		idfTSoggetto.setIstatProvCompetenzaTerr(rs.getString("istat_prov_competenza_terr")); // java.lang.String
		idfTSoggetto.setFlgSettoreRegionale(rs.getInt("flg_settore_regionale")); // java.lang.Integer
		if ( rs.wasNull() ) { idfTSoggetto.setFlgSettoreRegionale(null); }; // not primitive number => keep null value if any
		idfTSoggetto.setDataInserimento(rs.getDate("data_inserimento")); // java.util.Date
		idfTSoggetto.setDataAggiornamento(rs.getDate("data_aggiornamento")); // java.util.Date
		idfTSoggetto.setFkConfigUtente(rs.getInt("fk_config_utente")); // java.lang.Integer
		if ( rs.wasNull() ) { idfTSoggetto.setFkConfigUtente(null); }; // not primitive number => keep null value if any
		idfTSoggetto.setCivico(rs.getString("civico")); // java.lang.String
		idfTSoggetto.setCap(rs.getString("cap")); // java.lang.String
		idfTSoggetto.setFkCategoriaProfessionale(rs.getInt("fk_categoria_professionale")); // java.lang.Integer
		if ( rs.wasNull() ) { idfTSoggetto.setFkCategoriaProfessionale(null); }; // not primitive number => keep null value if any
		idfTSoggetto.setIstatComuneCompetenzaTerr(rs.getString("istat_comune_competenza_terr")); // java.lang.String
		return idfTSoggetto ;
	}

	//----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfTSoggetto findById( Integer idSoggetto ) {
		IdfTSoggetto idfTSoggetto = newInstanceWithPrimaryKey( idSoggetto ) ;
		if ( super.doSelect(idfTSoggetto) ) {
			return idfTSoggetto ;
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
	public List<IdfTSoggetto> findAll() {
		return super.doSelectAll();
	}

	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param idfTSoggetto
	 * @return true if found, false if not found
	 */
	//@Override
	@Override
	public boolean load( IdfTSoggetto idfTSoggetto ) {
		return super.doSelect(idfTSoggetto) ;
	}

    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param idfTSoggetto
	 */
	public long insert(IdfTSoggetto idfTSoggetto) {
		Long key = super.doInsertAutoIncr(idfTSoggetto);
		return key.longValue();
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfTSoggetto create(IdfTSoggetto idfTSoggetto) {
		insert(idfTSoggetto);
		return idfTSoggetto ;
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean update(IdfTSoggetto idfTSoggetto) {
		int r = super.doUpdate(idfTSoggetto);
		return r > 0 ;

	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfTSoggetto save(IdfTSoggetto idfTSoggetto) {
		if ( super.doExists(idfTSoggetto) ) {
			super.doUpdate(idfTSoggetto);
		}
		else {
			super.doInsert(idfTSoggetto);
		}
		return idfTSoggetto ;
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean deleteById( Integer idSoggetto ) {
		IdfTSoggetto idfTSoggetto = newInstanceWithPrimaryKey( idSoggetto ) ;
		int r = super.doDelete(idfTSoggetto);
		return r > 0 ;
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean delete( IdfTSoggetto idfTSoggetto ) {
		int r = super.doDelete(idfTSoggetto);
		return r > 0 ;
	}

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param idSoggetto;
	 * @return
	 */
	// @Override
	@Override
	public boolean exists( Integer idSoggetto ) {
		IdfTSoggetto idfTSoggetto = newInstanceWithPrimaryKey( idSoggetto ) ;
		return super.doExists(idfTSoggetto);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param idfTSoggetto
	 * @return
	 */
	// @Override
	@Override
	public boolean exists( IdfTSoggetto idfTSoggetto ) {
		return super.doExists(idfTSoggetto);
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
	public  RowMapper<IdfTSoggetto> getRowMapper()
	{
		return new IdfTSoggettoMapper();
		
	}
	class IdfTSoggettoMapper implements RowMapper<IdfTSoggetto>{

	@Override
	public IdfTSoggetto mapRow(ResultSet rs, int rowNum) throws SQLException {
		IdfTSoggetto IdfTSoggetto=new IdfTSoggetto();
		return populateBean(rs,IdfTSoggetto);
		
	}
}	

}
