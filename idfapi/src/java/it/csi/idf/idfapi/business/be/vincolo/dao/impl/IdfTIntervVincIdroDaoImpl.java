/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.dao.impl;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.vincolo.dao.GenericJdbcDAO;
import it.csi.idf.idfapi.business.be.vincolo.dao.IdfTIntervVincIdroDAO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfTIntervVincIdro;
import it.csi.idf.idfapi.dto.InfoVarianteProroga;
import it.csi.idf.idfapi.mapper.DoubleMapper;
import it.csi.idf.idfapi.mapper.InfoVarianteProrogaMapper;
import it.csi.idf.idfapi.mapper.StringMapper;
import it.csi.idf.idfapi.util.DBUtil;
	
/**
 * IdfTIntervVincIdro DAO implementation 
 * 
 */
//@Named("IdfTIntervVincIdroDAO")
@Component //nMarcaBollo n_marca_bollo
public class IdfTIntervVincIdroDaoImpl extends GenericJdbcDAO<IdfTIntervVincIdro> implements IdfTIntervVincIdroDAO {
	
	public static Logger logger = Logger.getLogger(IdfTIntervVincIdroDaoImpl.class);

	private final static String SQL_SELECT_ALL = 
		"select id_intervento, fk_intervento_padre_variante, fk_intervento_padre_proroga, fk_tipo_intervento, desc_tipo_interv_altro, movimenti_terra_mc, movimenti_terra_vincidro_mc, mesi_intervento, flg_aree_dissesto, flg_aree_esondazione, flg_cauzione_vi, flg_compensazione, cm_bosc_euro, cm_nobosc_euro, flg_art7_a, flg_art7_b, flg_art7_c, flg_art7_d, flg_art7_d_bis, flg_art9_a, flg_art9_b, flg_art9_c, flg_spese_istruttoria, flg_esenzione_marca_bollo, n_marca_bollo, flg_importo, flg_copia_conforme, flg_conf_servizi, flg_suap, annotazioni, data_inserimento, data_aggiornamento, fk_config_utente, flg_proprieta, flg_dissensi, cm_supbosc_ha, cm_supnobosc_ha from idf_t_interv_vinc_idro"; 

	private final static String SQL_SELECT = 
		"select id_intervento, fk_intervento_padre_variante, fk_intervento_padre_proroga, fk_tipo_intervento, desc_tipo_interv_altro, movimenti_terra_mc, movimenti_terra_vincidro_mc, mesi_intervento, flg_aree_dissesto, flg_aree_esondazione, flg_cauzione_vi, flg_compensazione, cm_bosc_euro, cm_nobosc_euro, flg_art7_a, flg_art7_b, flg_art7_c, flg_art7_d, flg_art7_d_bis, flg_art9_a, flg_art9_b, flg_art9_c, flg_spese_istruttoria, flg_esenzione_marca_bollo, n_marca_bollo, flg_importo, flg_copia_conforme, flg_conf_servizi, flg_suap, annotazioni, data_inserimento, data_aggiornamento, fk_config_utente, flg_proprieta, flg_dissensi, cm_supbosc_ha, cm_supnobosc_ha from idf_t_interv_vinc_idro where id_intervento = ?";

	private final static String SQL_INSERT = 
		"insert into idf_t_interv_vinc_idro ( id_intervento, fk_intervento_padre_variante, fk_intervento_padre_proroga, fk_tipo_intervento, desc_tipo_interv_altro, movimenti_terra_mc, movimenti_terra_vincidro_mc, mesi_intervento, flg_aree_dissesto, flg_aree_esondazione, flg_cauzione_vi, flg_compensazione, cm_bosc_euro, cm_nobosc_euro, flg_art7_a, flg_art7_b, flg_art7_c, flg_art7_d, flg_art7_d_bis, flg_art9_a, flg_art9_b, flg_art9_c, flg_spese_istruttoria, flg_esenzione_marca_bollo, n_marca_bollo, flg_importo, flg_copia_conforme, flg_conf_servizi, flg_suap, annotazioni, data_inserimento, data_aggiornamento, fk_config_utente, flg_proprieta, flg_dissensi, cm_supbosc_ha, cm_supnobosc_ha ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

	private final static String SQL_UPDATE = 
		"update idf_t_interv_vinc_idro set fk_intervento_padre_variante = ?, fk_intervento_padre_proroga = ?, "
		+ "fk_tipo_intervento = ?, desc_tipo_interv_altro = ?, movimenti_terra_mc = ?, movimenti_terra_vincidro_mc = ?, "
		+ "mesi_intervento = ?, flg_aree_dissesto = ?, flg_aree_esondazione = ?, flg_cauzione_vi = ?, flg_compensazione = ?, "
		+ "cm_bosc_euro = ?, cm_nobosc_euro = ?, flg_art7_a = ?, flg_art7_b = ?, flg_art7_c = ?, flg_art7_d = ?, "
		+ "flg_art7_d_bis = ?, flg_art9_a = ?, flg_art9_b = ?, flg_art9_c = ?, flg_spese_istruttoria = ?, "
		+ "flg_esenzione_marca_bollo = ?, n_marca_bollo = ?, flg_importo = ?, flg_copia_conforme = ?, flg_conf_servizi = ?, "
		+ "flg_suap = ?, annotazioni = ?, data_aggiornamento = ?, fk_config_utente = ?, flg_proprieta = ?,"
		+ " flg_dissensi = ?, cm_supbosc_ha = ?, cm_supnobosc_ha = ? where id_intervento = ?";

	private final static String SQL_DELETE = 
		"delete from idf_t_interv_vinc_idro where id_intervento = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from idf_t_interv_vinc_idro";

	private final static String SQL_COUNT = 
		"select count(*) from idf_t_interv_vinc_idro where id_intervento = ?";

    //----------------------------------------------------------------------
	/**
	 * DAO constructor
	 */
	public IdfTIntervVincIdroDaoImpl() {
		super();
	}

    //----------------------------------------------------------------------
	@Override
	protected void setAutoIncrementedKey(IdfTIntervVincIdro record, long value) {
		throw new IllegalStateException("Unexpected call to method 'setAutoIncrementedKey'");
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForPrimaryKey(PreparedStatement ps, int i, IdfTIntervVincIdro idfTIntervVincIdro) throws SQLException {
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, idfTIntervVincIdro.getIdIntervento() ) ; // "id_intervento" : java.math.BigDecimal
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForInsert(PreparedStatement ps, int i, IdfTIntervVincIdro idfTIntervVincIdro) throws SQLException {
		//--- Set PRIMARY KEY and DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, idfTIntervVincIdro.getIdIntervento() ) ; // "id_intervento" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getFkInterventoPadreVariante() ) ; // "fk_intervento_padre_variante" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getFkInterventoPadreProroga() ) ; // "fk_intervento_padre_proroga" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getFkTipoIntervento() ) ; // "fk_tipo_intervento" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getDescTipoIntervAltro() ) ; // "desc_tipo_interv_altro" : java.lang.String
		setValue(ps, i++, idfTIntervVincIdro.getMovimentiTerraMc() ) ; // "movimenti_terra_mc" : java.lang.Integer
		setValue(ps, i++, idfTIntervVincIdro.getMovimentiTerraVincidroMc() ) ; // "movimenti_terra_vincidro_mc" : java.lang.Integer
		setValue(ps, i++, idfTIntervVincIdro.getMesiIntervento() ) ; // "mesi_intervento" : java.lang.String
		setValue(ps, i++, idfTIntervVincIdro.getFlgAreeDissesto() ) ; // "flg_aree_dissesto" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getFlgAreeEsondazione() ) ; // "flg_aree_esondazione" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getFlgCauzioneVi() ) ; // "flg_cauzione_vi" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getFlgCompensazione() ) ; // "flg_compensazione" : java.lang.String
		setValue(ps, i++, idfTIntervVincIdro.getCmBoscEuro() ) ; // "cm_bosc_euro" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getCmNoboscEuro() ) ; // "cm_nobosc_euro" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getFlgArt7A() ) ; // "flg_art7_a" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getFlgArt7B() ) ; // "flg_art7_b" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getFlgArt7C() ) ; // "flg_art7_c" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getFlgArt7D() ) ; // "flg_art7_d" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getFlgArt7DBis() ) ; // "flg_art7_d_bis" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getFlgArt9A() ) ; // "flg_art9_a" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getFlgArt9B() ) ; // "flg_art9_b" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getFlgArt9C() ) ; // "flg_art9_c" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getFlgSpeseIstruttoria() ) ; // "flg_spese_istruttoria" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getFlgEsenzioneMarcaBollo() ) ; // "flg_esenzione_marca_bollo" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getNMarcaBollo() ) ; // "n_marca_bollo" : java.lang.String
		setValue(ps, i++, idfTIntervVincIdro.getFlgImporto() ) ; // "flg_importo" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getFlgCopiaConforme() ) ; // "flg_copia_conforme" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getFlgConfServizi() ) ; // "flg_conf_servizi" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getFlgSuap() ) ; // "flg_suap" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getAnnotazioni() ) ; // "annotazioni" : java.lang.String
		setValue(ps, i++, idfTIntervVincIdro.getDataInserimento().toString() ) ; // "data_inserimento" : java.util.Date
		setValue(ps, i++, idfTIntervVincIdro.getDataAggiornamento() ) ; // "data_aggiornamento" : java.util.Date
		setValue(ps, i++, idfTIntervVincIdro.getFkConfigUtente() ) ; // "fk_config_utente" : java.lang.Integer
		setValue(ps, i++, idfTIntervVincIdro.getFlgProprieta() ) ; // "flg_proprieta" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getFlgDissensi() ) ; // "flg_dissensi" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getCmSupboscHa() ) ; // "cm_supbosc_ha" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getCmSupnoboscHa() ) ; // "cm_supnobosc_ha" : java.math.BigDecimal
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForUpdate(PreparedStatement ps, int i, IdfTIntervVincIdro idfTIntervVincIdro) throws SQLException {
		//--- Set DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, idfTIntervVincIdro.getFkInterventoPadreVariante() ) ; // "fk_intervento_padre_variante" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getFkInterventoPadreProroga() ) ; // "fk_intervento_padre_proroga" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getFkTipoIntervento() ) ; // "fk_tipo_intervento" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getDescTipoIntervAltro() ) ; // "desc_tipo_interv_altro" : java.lang.String
		setValue(ps, i++, idfTIntervVincIdro.getMovimentiTerraMc() ) ; // "movimenti_terra_mc" : java.lang.Integer
		setValue(ps, i++, idfTIntervVincIdro.getMovimentiTerraVincidroMc() ) ; // "movimenti_terra_vincidro_mc" : java.lang.Integer
		setValue(ps, i++, idfTIntervVincIdro.getMesiIntervento() ) ; // "mesi_intervento" : java.lang.String
		setValue(ps, i++, idfTIntervVincIdro.getFlgAreeDissesto() ) ; // "flg_aree_dissesto" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getFlgAreeEsondazione() ) ; // "flg_aree_esondazione" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getFlgCauzioneVi() ) ; // "flg_cauzione_vi" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getFlgCompensazione() ) ; // "flg_compensazione" : java.lang.String
		setValue(ps, i++, idfTIntervVincIdro.getCmBoscEuro() ) ; // "cm_bosc_euro" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getCmNoboscEuro() ) ; // "cm_nobosc_euro" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getFlgArt7A() ) ; // "flg_art7_a" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getFlgArt7B() ) ; // "flg_art7_b" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getFlgArt7C() ) ; // "flg_art7_c" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getFlgArt7D() ) ; // "flg_art7_d" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getFlgArt7DBis() ) ; // "flg_art7_d_bis" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getFlgArt9A() ) ; // "flg_art9_a" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getFlgArt9B() ) ; // "flg_art9_b" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getFlgArt9C() ) ; // "flg_art9_c" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getFlgSpeseIstruttoria() ) ; // "flg_spese_istruttoria" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getFlgEsenzioneMarcaBollo() ) ; // "flg_esenzione_marca_bollo" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getNMarcaBollo() ) ; // "n_marca_bollo" : java.lang.String
		setValue(ps, i++, idfTIntervVincIdro.getFlgImporto() ) ; // "flg_importo" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getFlgCopiaConforme() ) ; // "flg_copia_conforme" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getFlgConfServizi() ) ; // "flg_conf_servizi" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getFlgSuap() ) ; // "flg_suap" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getAnnotazioni() ) ; // "annotazioni" : java.lang.String
		setValue(ps, i++, idfTIntervVincIdro.getDataAggiornamento() ) ; // "data_aggiornamento" : java.util.Date
		setValue(ps, i++, idfTIntervVincIdro.getFkConfigUtente() ) ; // "fk_config_utente" : java.lang.Integer
		setValue(ps, i++, idfTIntervVincIdro.getFlgProprieta() ) ; // "flg_proprieta" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getFlgDissensi() ) ; // "flg_dissensi" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getCmSupboscHa() ) ; // "cm_supbosc_ha" : java.math.BigDecimal
		setValue(ps, i++, idfTIntervVincIdro.getCmSupnoboscHa() ) ; // "cm_supnobosc_ha" : java.math.BigDecimal
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, idfTIntervVincIdro.getIdIntervento() ) ; // "id_intervento" : java.math.BigDecimal
	}

	//----------------------------------------------------------------------
	/**
	 * Creates a new instance of the bean and populates it with the given primary value(s)
	 * @param idIntervento;
	 * @return the new instance
	 */
	private IdfTIntervVincIdro newInstanceWithPrimaryKey( BigDecimal idIntervento ) {
		IdfTIntervVincIdro idfTIntervVincIdro = new IdfTIntervVincIdro();
		idfTIntervVincIdro.setIdIntervento( idIntervento );
		return idfTIntervVincIdro ;
	}

	//----------------------------------------------------------------------
	@Override
	protected IdfTIntervVincIdro newInstance() {
		return new IdfTIntervVincIdro() ;
	}

    //----------------------------------------------------------------------
	@Override
	protected IdfTIntervVincIdro populateBean(ResultSet rs, IdfTIntervVincIdro idfTIntervVincIdro) throws SQLException {

		//--- Set data from ResultSet to Bean attributes
		idfTIntervVincIdro.setIdIntervento(rs.getBigDecimal("id_intervento")); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfTIntervVincIdro.setIdIntervento(null); }; // not primitive number => keep null value if any
		idfTIntervVincIdro.setFkInterventoPadreVariante(rs.getBigDecimal("fk_intervento_padre_variante")); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfTIntervVincIdro.setFkInterventoPadreVariante(null); }; // not primitive number => keep null value if any
		idfTIntervVincIdro.setFkInterventoPadreProroga(rs.getBigDecimal("fk_intervento_padre_proroga")); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfTIntervVincIdro.setFkInterventoPadreProroga(null); }; // not primitive number => keep null value if any
		idfTIntervVincIdro.setFkTipoIntervento(rs.getBigDecimal("fk_tipo_intervento")); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfTIntervVincIdro.setFkTipoIntervento(null); }; // not primitive number => keep null value if any
		idfTIntervVincIdro.setDescTipoIntervAltro(rs.getString("desc_tipo_interv_altro")); // java.lang.String
		idfTIntervVincIdro.setMovimentiTerraMc(rs.getInt("movimenti_terra_mc")); // java.lang.Integer
		if ( rs.wasNull() ) { idfTIntervVincIdro.setMovimentiTerraMc(null); }; // not primitive number => keep null value if any
		idfTIntervVincIdro.setMovimentiTerraVincidroMc(rs.getInt("movimenti_terra_vincidro_mc")); // java.lang.Integer
		if ( rs.wasNull() ) { idfTIntervVincIdro.setMovimentiTerraVincidroMc(null); }; // not primitive number => keep null value if any
		idfTIntervVincIdro.setMesiIntervento(rs.getString("mesi_intervento")); // java.lang.String
		idfTIntervVincIdro.setFlgAreeDissesto(rs.getBigDecimal("flg_aree_dissesto")); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfTIntervVincIdro.setFlgAreeDissesto(null); }; // not primitive number => keep null value if any
		idfTIntervVincIdro.setFlgAreeEsondazione(rs.getBigDecimal("flg_aree_esondazione")); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfTIntervVincIdro.setFlgAreeEsondazione(null); }; // not primitive number => keep null value if any
		idfTIntervVincIdro.setFlgCauzioneVi(rs.getBigDecimal("flg_cauzione_vi")); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfTIntervVincIdro.setFlgCauzioneVi(null); }; // not primitive number => keep null value if any
		idfTIntervVincIdro.setFlgCompensazione(rs.getString("flg_compensazione")); // java.lang.String
		idfTIntervVincIdro.setCmBoscEuro(rs.getBigDecimal("cm_bosc_euro")); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfTIntervVincIdro.setCmBoscEuro(null); }; // not primitive number => keep null value if any
		idfTIntervVincIdro.setCmNoboscEuro(rs.getBigDecimal("cm_nobosc_euro")); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfTIntervVincIdro.setCmNoboscEuro(null); }; // not primitive number => keep null value if any
		idfTIntervVincIdro.setFlgArt7A(rs.getBigDecimal("flg_art7_a")); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfTIntervVincIdro.setFlgArt7A(null); }; // not primitive number => keep null value if any
		idfTIntervVincIdro.setFlgArt7B(rs.getBigDecimal("flg_art7_b")); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfTIntervVincIdro.setFlgArt7B(null); }; // not primitive number => keep null value if any
		idfTIntervVincIdro.setFlgArt7C(rs.getBigDecimal("flg_art7_c")); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfTIntervVincIdro.setFlgArt7C(null); }; // not primitive number => keep null value if any
		idfTIntervVincIdro.setFlgArt7D(rs.getBigDecimal("flg_art7_d")); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfTIntervVincIdro.setFlgArt7D(null); }; // not primitive number => keep null value if any
		idfTIntervVincIdro.setFlgArt7DBis(rs.getBigDecimal("flg_art7_d_bis")); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfTIntervVincIdro.setFlgArt7DBis(null); }; // not primitive number => keep null value if any
		idfTIntervVincIdro.setFlgArt9A(rs.getBigDecimal("flg_art9_a")); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfTIntervVincIdro.setFlgArt9A(null); }; // not primitive number => keep null value if any
		idfTIntervVincIdro.setFlgArt9B(rs.getBigDecimal("flg_art9_b")); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfTIntervVincIdro.setFlgArt9B(null); }; // not primitive number => keep null value if any
		idfTIntervVincIdro.setFlgArt9C(rs.getBigDecimal("flg_art9_c")); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfTIntervVincIdro.setFlgArt9C(null); }; // not primitive number => keep null value if any
		idfTIntervVincIdro.setFlgSpeseIstruttoria(rs.getBigDecimal("flg_spese_istruttoria")); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfTIntervVincIdro.setFlgSpeseIstruttoria(null); }; // not primitive number => keep null value if any
		idfTIntervVincIdro.setFlgEsenzioneMarcaBollo(rs.getBigDecimal("flg_esenzione_marca_bollo")); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfTIntervVincIdro.setFlgEsenzioneMarcaBollo(null); }; // not primitive number => keep null value if any
		idfTIntervVincIdro.setNMarcaBollo(rs.getString("n_marca_bollo")); // java.lang.String
		idfTIntervVincIdro.setFlgImporto(rs.getBigDecimal("flg_importo")); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfTIntervVincIdro.setFlgImporto(null); }; // not primitive number => keep null value if any
		idfTIntervVincIdro.setFlgCopiaConforme(rs.getBigDecimal("flg_copia_conforme")); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfTIntervVincIdro.setFlgCopiaConforme(null); }; // not primitive number => keep null value if any
		idfTIntervVincIdro.setFlgConfServizi(rs.getBigDecimal("flg_conf_servizi")); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfTIntervVincIdro.setFlgConfServizi(null); }; // not primitive number => keep null value if any
		idfTIntervVincIdro.setFlgSuap(rs.getBigDecimal("flg_suap")); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfTIntervVincIdro.setFlgSuap(null); }; // not primitive number => keep null value if any
		idfTIntervVincIdro.setAnnotazioni(rs.getString("annotazioni")); // java.lang.String
		//idfTIntervVincIdro.setDataInserimento(rs.getDate("data_inserimento").toInstant().atZone(ZoneId.systemDefault()).toLocalDate()); // java.util.Date
		idfTIntervVincIdro.setDataInserimento(Instant.ofEpochMilli(rs.getDate("data_inserimento").getTime()).atZone(ZoneId.systemDefault()).toLocalDate()); // java.util.Date
		idfTIntervVincIdro.setDataAggiornamento(rs.getDate("data_aggiornamento")); // java.util.Date
		idfTIntervVincIdro.setFkConfigUtente(rs.getInt("fk_config_utente")); // java.lang.Integer
		if ( rs.wasNull() ) { idfTIntervVincIdro.setFkConfigUtente(null); }; // not primitive number => keep null value if any
		idfTIntervVincIdro.setFlgProprieta(rs.getBigDecimal("flg_proprieta")); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfTIntervVincIdro.setFlgProprieta(null); }; // not primitive number => keep null value if any
		idfTIntervVincIdro.setFlgDissensi(rs.getBigDecimal("flg_dissensi")); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfTIntervVincIdro.setFlgDissensi(null); }; // not primitive number => keep null value if any
		
		idfTIntervVincIdro.setCmSupboscHa(rs.getBigDecimal("cm_supbosc_ha")); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfTIntervVincIdro.setCmSupboscHa(null); }; // not primitive number => keep null value if any
		idfTIntervVincIdro.setCmSupnoboscHa(rs.getBigDecimal("cm_supnobosc_ha")); // java.math.BigDecimal
		if ( rs.wasNull() ) { idfTIntervVincIdro.setCmSupnoboscHa(null); }; // not primitive number => keep null value if any
		
		
		return idfTIntervVincIdro ;
	}

	//----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfTIntervVincIdro findById( BigDecimal idIntervento ) {
		IdfTIntervVincIdro idfTIntervVincIdro = newInstanceWithPrimaryKey( idIntervento ) ;
		if ( super.doSelect(idfTIntervVincIdro) ) {
			return idfTIntervVincIdro ;
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
	public List<IdfTIntervVincIdro> findAll() {
		return super.doSelectAll();
	}

	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param idfTIntervVincIdro
	 * @return true if found, false if not found
	 */
	@Override
	public boolean load( IdfTIntervVincIdro idfTIntervVincIdro ) {
		return super.doSelect(idfTIntervVincIdro) ;
	}

    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param idfTIntervVincIdro
	 */
	public long insert(IdfTIntervVincIdro idfTIntervVincIdro) {
		super.doInsert(idfTIntervVincIdro);
		return 0L ;
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public int create(IdfTIntervVincIdro idfTIntervVincIdro) {
		StringBuilder sql = new StringBuilder("insert into idf_t_interv_vinc_idro ");
		sql.append("(id_intervento, fk_intervento_padre_variante, fk_intervento_padre_proroga, fk_tipo_intervento, ");
		sql.append("desc_tipo_interv_altro, movimenti_terra_mc, movimenti_terra_vincidro_mc, mesi_intervento, ");
		sql.append("flg_aree_dissesto, flg_aree_esondazione, flg_cauzione_vi, flg_compensazione, cm_bosc_euro, ");
		sql.append("cm_nobosc_euro, flg_art7_a, flg_art7_b, flg_art7_c, flg_art7_d, flg_art7_d_bis, flg_art9_a, ");
		sql.append("flg_art9_b, flg_art9_c, flg_spese_istruttoria, flg_esenzione_marca_bollo, n_marca_bollo, ");
		sql.append("flg_importo, flg_copia_conforme, flg_conf_servizi, flg_suap, annotazioni, data_inserimento, ");
		sql.append("data_aggiornamento, fk_config_utente, flg_proprieta, flg_dissensi, cm_supbosc_ha, cm_supnobosc_ha) ");
		sql.append("values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
		sql.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");

		List<Object> params = new ArrayList<>();
		params.add(idfTIntervVincIdro.getIdIntervento());
		params.add(idfTIntervVincIdro.getFkInterventoPadreVariante());
		params.add(idfTIntervVincIdro.getFkInterventoPadreProroga());
		params.add(idfTIntervVincIdro.getFkTipoIntervento());
		params.add(idfTIntervVincIdro.getDescTipoIntervAltro());
		params.add(idfTIntervVincIdro.getMovimentiTerraMc());
		params.add(idfTIntervVincIdro.getMovimentiTerraVincidroMc());
		params.add(idfTIntervVincIdro.getMesiIntervento());
		params.add(idfTIntervVincIdro.getFlgAreeDissesto());
		params.add(idfTIntervVincIdro.getFlgAreeEsondazione());
		params.add(idfTIntervVincIdro.getFlgCauzioneVi());
		params.add(idfTIntervVincIdro.getFlgCompensazione());
		params.add(idfTIntervVincIdro.getCmBoscEuro());
		params.add(idfTIntervVincIdro.getCmNoboscEuro());
		params.add(idfTIntervVincIdro.getFlgArt7A());
		params.add(idfTIntervVincIdro.getFlgArt7B());
		params.add(idfTIntervVincIdro.getFlgArt7C());
		params.add(idfTIntervVincIdro.getFlgArt7D());
		params.add(idfTIntervVincIdro.getFlgArt7DBis());
		params.add(idfTIntervVincIdro.getFlgArt9A());
		params.add(idfTIntervVincIdro.getFlgArt9B());
		params.add(idfTIntervVincIdro.getFlgArt9C());
		params.add(idfTIntervVincIdro.getFlgSpeseIstruttoria());
		params.add(idfTIntervVincIdro.getFlgEsenzioneMarcaBollo());
		params.add(idfTIntervVincIdro.getNMarcaBollo());
		params.add(idfTIntervVincIdro.getFlgImporto());
		params.add(idfTIntervVincIdro.getFlgCopiaConforme());
		params.add(idfTIntervVincIdro.getFlgConfServizi());
		params.add(idfTIntervVincIdro.getFlgSuap());
		params.add(idfTIntervVincIdro.getAnnotazioni());
		params.add(idfTIntervVincIdro.getDataInserimento() == null ? null : Date.valueOf(idfTIntervVincIdro.getDataInserimento()));
		params.add(idfTIntervVincIdro.getDataAggiornamento() == null ? null : idfTIntervVincIdro.getDataAggiornamento());
		params.add(idfTIntervVincIdro.getFkConfigUtente());
		params.add(idfTIntervVincIdro.getFlgProprieta());
		params.add(idfTIntervVincIdro.getFlgDissensi());
		params.add(idfTIntervVincIdro.getCmSupboscHa());
		params.add(idfTIntervVincIdro.getCmSupnoboscHa());

		return DBUtil.jdbcTemplate.update(sql.toString(), params.toArray());
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean update(IdfTIntervVincIdro idfTIntervVincIdro) {
		int r = super.doUpdate(idfTIntervVincIdro);
		return r > 0 ;

	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public IdfTIntervVincIdro save(IdfTIntervVincIdro idfTIntervVincIdro) {
		if ( super.doExists(idfTIntervVincIdro) ) {
			super.doUpdate(idfTIntervVincIdro);
		}
		else {
			super.doInsert(idfTIntervVincIdro);
		}
		return idfTIntervVincIdro ;
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean deleteById( BigDecimal idIntervento ) {
		IdfTIntervVincIdro idfTIntervVincIdro = newInstanceWithPrimaryKey( idIntervento ) ;
		int r = super.doDelete(idfTIntervVincIdro);
		return r > 0 ;
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean delete( IdfTIntervVincIdro idfTIntervVincIdro ) {
		int r = super.doDelete(idfTIntervVincIdro);
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
		IdfTIntervVincIdro idfTIntervVincIdro = newInstanceWithPrimaryKey( idIntervento ) ;
		return super.doExists(idfTIntervVincIdro);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param idfTIntervVincIdro
	 * @return
	 */
	// @Override
	@Override
	public boolean exists( IdfTIntervVincIdro idfTIntervVincIdro ) {
		return super.doExists(idfTIntervVincIdro);
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
	public List<String> getGeometrieGmlByFkIntervento(Integer idIntervento) {
		String sql = "SELECT st_asgml(geometria) as geometriagml FROM idf_geo_pl_interv_vincidro WHERE fk_intervento = ?";
		return DBUtil.jdbcTemplate.query(sql.toString(), 
				new Object[] {idIntervento}, new StringMapper());
	}

	@Override
	public List<String> getGeometrieGeoJSONByFkIntervento(Integer idIntervento) {
		//String sql = "SELECT st_asgml(geometria) as geometriagml FROM idf_geo_pl_interv_vincidro WHERE fk_intervento = ?";
		String sql = "SELECT ST_ASGEOJSON( ST_TRANSFORM(geometria, 4326)) as geometriagml FROM idf_geo_pl_interv_vincidro WHERE fk_intervento = ?";
		return DBUtil.jdbcTemplate.query(sql.toString(), 
				new Object[] {idIntervento}, new StringMapper());
	}
	
	@Override
	public Double getAreaItersecWithParticella(Integer idIntervento, Integer idgeo_pl_prop_catasto) {
		
		String sql = "select sum(ST_AREA (ST_INTERSECTION(a.geometria , b.geometria))) as area "
				+ "from idf_geo_pl_interv_vincidro a,  idf_geo_pl_prop_catasto b "
				+ "where a.fk_intervento = ? and b.idgeo_pl_prop_catasto = ? "
				+ "and ST_Intersects(a.geometria , b.geometria )";
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), 
				new Object[] {idIntervento,idgeo_pl_prop_catasto}, new DoubleMapper());
	}
	
	@Override
	public Double getAreaTrasformazioneByFkIntervento(Integer idIntervento) {
		
		String sql = "select sum(ST_AREA (geometria)) FROM idf_geo_pl_interv_vincidro WHERE fk_intervento = ?";
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), 
				new Object[] {idIntervento}, new DoubleMapper());
	}
	
	@Override
	public Double getAreaSuperficieBoscataByFkIntervento(Integer idIntervento) {
		String sql = "select sum(ST_AREA (ST_INTERSECTION(a.geometria , b.geometria))) as area "
				+ "from idf_geo_pl_interv_vincidro a,  idf_geo_pl_tipo_forestale b "
				+ "where a.fk_intervento = ? "
				+ "and ST_Intersects(a.geometria , b.geometria )";
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), 
				new Object[] {idIntervento}, new DoubleMapper());
	};

	@Override
	public Double getAreaSuperficieInVincoloByFkIntervento(Integer idIntervento){
		String sql = "select sum(ST_AREA (ST_INTERSECTION(a.geometria , b.geometria))) as area "
				+ "from idf_geo_pl_interv_vincidro a,  idf_geo_pl_vinc_idro10k b "
				+ "where a.fk_intervento = ? "
				+ "and ST_Intersects(a.geometria , b.geometria )";
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), 
				new Object[] {idIntervento}, new DoubleMapper());
	};

	@Override
	public Double getAreaSuperficieBoscataInVincoloByFkIntervento(Integer idIntervento){
		String sql = "select sum(ST_AREA (ST_INTERSECTION(ST_INTERSECTION(a.geometria , b.geometria), c.geometria))) as area "
				+ "from idf_geo_pl_interv_vincidro a,  idf_geo_pl_vinc_idro10k b, idf_geo_pl_tipo_forestale c "
				+ "where a.fk_intervento = ? "
				+ "and ST_Intersects(a.geometria , b.geometria ) "
				+ "and ST_Intersects(ST_INTERSECTION(a.geometria , b.geometria), c.geometria)";
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), 
				new Object[] {idIntervento}, new DoubleMapper());
	};
	
	@Override
	public void removeGeometria(Integer idIntervento, Integer idGeoPlPropCatasto) {
		String sql = "select fn_interv_vincidro_togli_geo(?,(select st_asgml(geometria) from idf_geo_pl_prop_catasto where idgeo_pl_prop_catasto = ?))";
		Integer res = DBUtil.jdbcTemplate.queryForObject(sql.toString(),Integer.class, new Object[] {idIntervento,idGeoPlPropCatasto});
		if(res == null || res > 0) {
			logger.error("addGeometria - Error code: " + res);
		}
		logger.info("removeGeometria - done: ");
	}


	
	@Override
	public void addGeometria(Integer idIntervento, Object geometriGml) {
		logger.info("addGeometria - idIntervento: " + idIntervento + " - geometriGml: " + geometriGml);
		String sql = "select fn_interv_vincidro_aggiungi_geo(?,ST_AsGML(ST_GeomFromText(?, 32632)))";
		
		Integer res = DBUtil.jdbcTemplate.queryForObject(sql.toString(), Integer.class, new Object[] {idIntervento,geometriGml});
		if(res == null || res > 0) {
			logger.error("addGeometria - Error code: " + res);
		}
		logger.info("addGeometria - done");
	}

	@Override
	public boolean isCauzioneMancante(Integer idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("select CASE  "); 
		sql.append("WHEN invi.flg_cauzione_vi = 1 AND docall.id_documento_allegato IS NULL THEN 1  "); 
		sql.append("ELSE 0 ");
		sql.append("END stato_cauzione ");
		sql.append("from idf_t_interv_vinc_idro invi ");
		sql.append("LEFT JOIN idf_t_documento_allegato docall on invi.id_intervento = docall.fk_intervento and docall.fk_tipo_allegato=106 "); 
		sql.append("WHERE ");
		sql.append("invi.id_intervento = ?");
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(),Integer.class,idIntervento) == 1;
	}
	
	@Override
	public boolean isCompensazioneFisicaMancante(Integer idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("select CASE  "); 
		sql.append("WHEN invi.flg_compensazione = 'F' AND docall.id_documento_allegato IS NULL THEN 1  "); 
		sql.append("ELSE 0 ");
		sql.append("END stato_compensazione ");
		sql.append("from idf_t_interv_vinc_idro invi ");
		sql.append("LEFT JOIN idf_t_documento_allegato docall on invi.id_intervento = docall.fk_intervento and docall.fk_tipo_allegato=152 "); 
		sql.append("WHERE ");
		sql.append("invi.id_intervento = ?");
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(),Integer.class,idIntervento) == 1;
	}

	@Override
	public boolean isCompensazioneMonetariaMancante(Integer idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("select CASE  "); 
		sql.append("WHEN invi.flg_compensazione = 'M' AND docall.id_documento_allegato IS NULL THEN 1  "); 
		sql.append("ELSE 0 ");
		sql.append("END stato_compensazione ");
		sql.append("from idf_t_interv_vinc_idro invi ");
		sql.append("LEFT JOIN idf_t_documento_allegato docall on invi.id_intervento = docall.fk_intervento and docall.fk_tipo_allegato=153 "); 
		sql.append("WHERE ");
		sql.append("invi.id_intervento = ?");
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(),Integer.class,idIntervento) == 1;
	}

	@Override
	public boolean deleteById(int idIntervento) {
		String sql = "delete from idf_t_interv_vinc_idro where id_intervento = ? ";
		return DBUtil.jdbcTemplate.update(sql, idIntervento) > 0;
	}

	@Override
	public InfoVarianteProroga getInfoVarianteProroga(Integer idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("select fk_intervento_padre_proroga, fk_intervento_padre_variante, ");
		sql.append("(select count(id_intervento ) as dim from idf_t_interv_vinc_idro ");
		sql.append("	where fk_intervento_padre_proroga = ?) as proroghe,"); 
		sql.append("(select count(id_intervento ) as dim from idf_t_interv_vinc_idro ");
		sql.append("	where fk_intervento_padre_variante = ?) as varianti "); 
		sql.append("from idf_t_interv_vinc_idro a where id_intervento = ?");
		List<Object> params = new ArrayList<Object>();
		params.add(idIntervento);
		params.add(idIntervento);
		params.add(idIntervento);
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), params.toArray(), new InfoVarianteProrogaMapper());
	}

	@Override
	public void updateTitolaritaIntervento(Integer idIntervento, Integer idConfUtente) {
		String sql = "update idf_t_interv_vinc_idro set fk_config_utente = ? where id_intervento = ? ";
		DBUtil.jdbcTemplate.update(sql, idConfUtente, idIntervento) ;
		sql = "update idf_t_intervento set fk_config_utente_compilatore = ? where id_intervento = ? ";
		DBUtil.jdbcTemplate.update(sql, idConfUtente, idIntervento) ;
		
		
	}

}
