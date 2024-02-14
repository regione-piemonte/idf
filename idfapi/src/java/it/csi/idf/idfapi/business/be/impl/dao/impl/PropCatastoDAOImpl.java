/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.opengis.geometry.Geometry;
import org.opengis.geometry.coordinate.GeometryFactory;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.impl.dao.PropCatastoDAO;
import it.csi.idf.idfapi.dto.DichPropCatasto;
import it.csi.idf.idfapi.dto.PlainIntDTO;
import it.csi.idf.idfapi.dto.PlainParticelleCat;
import it.csi.idf.idfapi.dto.PlainParticelleCatastali;
import it.csi.idf.idfapi.dto.PlainStringDTO;
import it.csi.idf.idfapi.dto.PropCatasto;
import it.csi.idf.idfapi.dto.PropcatastoIntervento;
import it.csi.idf.idfapi.mapper.DichPropCatastoMapper;
import it.csi.idf.idfapi.mapper.PlainParticelleCatMapper;
import it.csi.idf.idfapi.mapper.PlainParticelleCatastaliMapper;
import it.csi.idf.idfapi.mapper.PropCatastoIntColumnMapper;
import it.csi.idf.idfapi.mapper.PropCatastoMapper;
import it.csi.idf.idfapi.mapper.PropCatastoStringColumnMapper;
import it.csi.idf.idfapi.util.AIKeyHolderUtil;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.SpringSupportedResource;

@Component
public class PropCatastoDAOImpl implements PropCatastoDAO {
	
	public static Logger logger = Logger.getLogger(PropCatastoDAOImpl.class);

	private static final String SEZIONE_COLUMN_NAME = "sezione";
	private static final String FOGLIO_COLUMN_NAME = "foglio";
	private static final String PARTICELLA_COLUMN_NAME = "particella";
	
	private PlainParticelleCatMapper plainParticelleCatMapper = new PlainParticelleCatMapper();

	private PlainParticelleCatastaliMapper plainParticelleCatastaliMapper = new PlainParticelleCatastaliMapper();
	private final PropCatastoMapper propCatastoMapper = new PropCatastoMapper();
	private final DichPropCatastoMapper dichPropCatastoMapper = new DichPropCatastoMapper();
	private final PropCatastoStringColumnMapper propCatastoSezioniMapper = new PropCatastoStringColumnMapper(
			SEZIONE_COLUMN_NAME);
	private final PropCatastoIntColumnMapper propCatastoFoglioMapper = new PropCatastoIntColumnMapper(
			FOGLIO_COLUMN_NAME);
	private final PropCatastoStringColumnMapper propCatastoParticellaMapper = new PropCatastoStringColumnMapper(
			PARTICELLA_COLUMN_NAME);

	@Override
	public List<PropCatasto> findAllCatasti() {

		String sql = "SELECT * FROM idf_geo_pl_prop_catasto";

		return DBUtil.jdbcTemplate.query(sql, propCatastoMapper);
	}

	@Override
	public PropCatasto findCatastoByID(Integer idGeoPlPropCatasto) throws RecordNotFoundException {

		String sql = "SELECT * FROM idf_geo_pl_prop_catasto c WHERE c.idgeo_pl_prop_catasto = ?";

		return DBUtil.jdbcTemplate.queryForObject(sql, propCatastoMapper, idGeoPlPropCatasto);
	}

	@Override
	public List<PropCatasto> getPropCatastosByPropcatastoIntervento(List<PropcatastoIntervento> propcatastoIntervento) {

		if (propcatastoIntervento.isEmpty()) {
			return Collections.emptyList();
		}

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT idgeo_pl_prop_catasto, idgeo_pl_pfa, fk_comune, sezione, foglio, allegato_catasto");
		sql.append(", sviluppo_catasto, particella, sup_catastale_ha, sup_cartografica_ha, fk_proprieta, intestato");
		sql.append(", qualita_coltura, flg_usi_civici, flg_possessi_contest, flg_livellari, data_inizio_validita");
		sql.append(", data_fine_validita, note, data_aggiornamento_datocatast, data_inserimento, fk_config_utente");
		//7777
		sql.append(", ST_AsText(geometria) as geometria");
		sql.append(" FROM idf_geo_pl_prop_catasto WHERE idgeo_pl_prop_catasto IN(");

		for (int i = 0; i < propcatastoIntervento.size(); i++) {
			sql.append(propcatastoIntervento.get(i).getIdgeoPlPropCatasto());
			if (i != propcatastoIntervento.size() - 1) {
				sql.append(", ");
			}
		}
		sql.append(")");

		return DBUtil.jdbcTemplate.query(sql.toString(), propCatastoMapper);
	}

	@Override
	public List<PropCatasto> getPropCatastosByidIntervento(Integer idIntervento) {
		String sql = "SELECT igppc.*, irpi.sup_intervento_ha " +
				" FROM idf_geo_pl_prop_catasto igppc  " +
				" join idf_r_propcatasto_intervento irpi on igppc.idgeo_pl_prop_catasto=irpi.idgeo_pl_prop_catasto " +
				" WHERE irpi.id_intervento = ? ";

		return DBUtil.jdbcTemplate.query(sql, propCatastoMapper, idIntervento);

	}

	@Override
	public List<DichPropCatasto> getDichPropCatastosByPropcatastoIntervento(
			List<PropcatastoIntervento> propcatastoIntervento) {
		if (propcatastoIntervento.isEmpty()) {
			throw new IllegalArgumentException("List<PropcatastoIntervento> can not be empty!");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT cat.sezione, cat.foglio, cat.particella, cat.sup_catastale_ha, cat.sup_cartografica_ha");
		sql.append(", cmn.denominazione_comune, prov.denominazione_prov,irpi.sup_intervento_ha ");
		sql.append(" FROM idf_geo_pl_prop_catasto cat");
		sql.append(" JOIN idf_geo_pl_comune cmn ON cat.fk_comune = cmn.id_comune");
		sql.append(" JOIN idf_geo_pl_provincia prov ON cmn.istat_prov = prov.istat_prov");
		sql.append(" join idf_r_propcatasto_intervento irpi on cat.idgeo_pl_prop_catasto = irpi.idgeo_pl_prop_catasto");
		sql.append(" WHERE cat.idgeo_pl_prop_catasto IN(");

		for (int i = 0; i < propcatastoIntervento.size(); i++) {
			sql.append(propcatastoIntervento.get(i).getIdgeoPlPropCatasto());
			if (i != propcatastoIntervento.size() - 1) {
				sql.append(", ");
			}
		}
		sql.append(")");

		return DBUtil.jdbcTemplate.query(sql.toString(), dichPropCatastoMapper);
	}

	@Override
	public List<PlainStringDTO> findSezioniByComune(int fkComune) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT(");
		sql.append(SEZIONE_COLUMN_NAME);
		sql.append("),char_length(");
		sql.append(SEZIONE_COLUMN_NAME);
		sql.append(") FROM idf_geo_pl_prop_catasto a ");
		sql.append("inner join idf_r_propcatasto_intervento b on a.idgeo_pl_prop_catasto = b.idgeo_pl_prop_catasto ");
		sql.append("WHERE fk_comune = ?");
		sql.append(" ORDER BY char_length(");
		sql.append(SEZIONE_COLUMN_NAME);
		sql.append( "),");
		sql.append(SEZIONE_COLUMN_NAME);
		logger.info("findSezioniByComune - sql: " + sql.toString() + " - comune: " + fkComune);
		return DBUtil.jdbcTemplate.query(sql.toString(), propCatastoSezioniMapper, fkComune);
	}

	@Override
	public List<PlainIntDTO> findFogliByComune(int fkComune, String sezione) {
		if("_".equals(sezione)) {
			sezione = "";
		}
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT(");
		sql.append(FOGLIO_COLUMN_NAME);
		sql.append(") FROM idf_geo_pl_prop_catasto a  ");
		sql.append("inner join idf_r_propcatasto_intervento b on a.idgeo_pl_prop_catasto = b.idgeo_pl_prop_catasto ");
		sql.append("WHERE fk_comune = ? and sezione = ?");
		sql.append(" ORDER BY ");
		sql.append(FOGLIO_COLUMN_NAME);
		logger.info("findFogliByComune - sql: " + sql.toString() + " - comune: " + fkComune + " - sezione: "+ sezione);
		return DBUtil.jdbcTemplate.query(sql.toString(), propCatastoFoglioMapper, fkComune, sezione);
	}

	@Override
	public List<PlainStringDTO> findParticelleByComuneSezioneFoglio(int fkComune, String sezione, int foglio) {
		if("_".equals(sezione)) {
			sezione = "";
		}
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT(");
		sql.append(PARTICELLA_COLUMN_NAME);
		sql.append("),char_length(");
		sql.append(PARTICELLA_COLUMN_NAME);
		sql.append(") FROM idf_geo_pl_prop_catasto a ");
		sql.append("WHERE fk_comune = ? and sezione = ? and foglio = ?");
		sql.append(" ORDER BY char_length(");
		sql.append(PARTICELLA_COLUMN_NAME);
		sql.append( "),");
		sql.append(PARTICELLA_COLUMN_NAME);
		logger.info("findParticelleByComuneSezioneFoglio - sql: " + sql.toString() 
			+ " - comune: " + fkComune + " - sezione: "+ sezione + " - foglio: " + foglio);
		return DBUtil.jdbcTemplate.query(sql.toString(), propCatastoParticellaMapper, fkComune, sezione, foglio);
	}
	
	@Override
	public List<PlainStringDTO> findSezioniByComuneAndTipoIstanza(int fkComune, int idTipoIstanza) {
		List<Integer> tipoIstanze = new ArrayList<>();
		tipoIstanze.add(idTipoIstanza);
		if (idTipoIstanza == 2) {
			tipoIstanze.add(3);
		}
		MapSqlParameterSource inQueryParams = new MapSqlParameterSource();
		inQueryParams.addValue("tipoIstanze", tipoIstanze);
		inQueryParams.addValue("fkComune", fkComune);

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT(");
		sql.append(SEZIONE_COLUMN_NAME);
		sql.append("),char_length(");
		sql.append(SEZIONE_COLUMN_NAME);
		sql.append(") FROM idf_geo_pl_prop_catasto a ");
		sql.append("inner join idf_r_propcatasto_intervento b on a.idgeo_pl_prop_catasto = b.idgeo_pl_prop_catasto ");
		sql.append("inner join IDF_T_ISTANZA_FOREST c on b.id_intervento = c.id_istanza_intervento ");
		sql.append("WHERE fk_comune = :fkComune and c.fk_tipo_istanza in (:tipoIstanze) and fk_stato_istanza > 1 ");
		sql.append(" ORDER BY char_length(");
		sql.append(SEZIONE_COLUMN_NAME);
		sql.append( "),");
		sql.append(SEZIONE_COLUMN_NAME);
		logger.info("findSezioniByComune - sql: " + sql.toString() + " - comune: " + fkComune + " - idTipoIstanza:" + idTipoIstanza);
/*
		Object[] params = new Object[] {fkComune,idTipoIstanza};
		return DBUtil.jdbcTemplate.query(sql.toString(), propCatastoSezioniMapper, params);
*/

		return DBUtil.namedJdbcTemplate.query(
				sql.toString(),
				inQueryParams,
				propCatastoSezioniMapper);

	}

	@Override
	public List<PlainIntDTO> findFogliByComuneAndSezioneAndTipoIstnaza(int fkComune, String sezione, int idTipoIstanza) {
		List<Integer> tipoIstanze = new ArrayList<>();
		tipoIstanze.add(idTipoIstanza);
		if (idTipoIstanza == 2) {
			tipoIstanze.add(3);
		}
		MapSqlParameterSource inQueryParams = new MapSqlParameterSource();
		inQueryParams.addValue("tipoIstanze", tipoIstanze);
		inQueryParams.addValue("fkComune", fkComune);
		inQueryParams.addValue("sezione", sezione);
		if("_".equals(sezione)) {
			inQueryParams.addValue("sezione2", "");
		} else {
			inQueryParams.addValue("sezione2", sezione);
		}

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT(");
		sql.append(FOGLIO_COLUMN_NAME);
		sql.append(") FROM idf_geo_pl_prop_catasto a  ");
		sql.append("inner join idf_r_propcatasto_intervento b on a.idgeo_pl_prop_catasto = b.idgeo_pl_prop_catasto ");
		sql.append("inner join IDF_T_ISTANZA_FOREST c on b.id_intervento = c.id_istanza_intervento ");
		sql.append("WHERE fk_comune = :fkComune and (sezione = :sezione or sezione = :sezione2) and c.fk_tipo_istanza in (:tipoIstanze) and fk_stato_istanza > 1 ");
		sql.append(" ORDER BY ");
		sql.append(FOGLIO_COLUMN_NAME);
		logger.info("findFogliByComune - sql: " + sql.toString() + " - comune: " + fkComune + " - sezione: "+ sezione + " - idTipoIstanza:" + idTipoIstanza);
/*
		Object[] params = new Object[] {fkComune, sezione,idTipoIstanza};
		return DBUtil.jdbcTemplate.query(sql.toString(), propCatastoFoglioMapper, params);
*/

		return DBUtil.namedJdbcTemplate.query(
				sql.toString(),
				inQueryParams,
				propCatastoFoglioMapper);
	}

	@Override
	public List<PlainStringDTO> findParticelleByComuneAndSezioneAndFoglioAndTipoIstanza(int fkComune, String sezione,	int foglio, int idTipoIstanza) {

		List<Integer> tipoIstanze = new ArrayList<>();
		tipoIstanze.add(idTipoIstanza);
		if (idTipoIstanza == 2) {
			tipoIstanze.add(3);
		}
		MapSqlParameterSource inQueryParams = new MapSqlParameterSource();
		inQueryParams.addValue("tipoIstanze", tipoIstanze);
		inQueryParams.addValue("fkComune", fkComune);
		inQueryParams.addValue("sezione", sezione);
		inQueryParams.addValue("foglio", foglio);
		if("_".equals(sezione)) {
			inQueryParams.addValue("sezione2", "");
		} else {
			inQueryParams.addValue("sezione2", sezione);
		}

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT(");
		sql.append(PARTICELLA_COLUMN_NAME);
		sql.append("),char_length(trim(");
		sql.append(PARTICELLA_COLUMN_NAME);
		sql.append(")) FROM idf_geo_pl_prop_catasto a ");
		sql.append("inner join idf_r_propcatasto_intervento b on a.idgeo_pl_prop_catasto = b.idgeo_pl_prop_catasto ");
		sql.append("inner join IDF_T_ISTANZA_FOREST c on b.id_intervento = c.id_istanza_intervento ");
		sql.append("WHERE fk_comune = :fkComune and (sezione = :sezione or sezione = :sezione2) and foglio = :foglio and c.fk_tipo_istanza in (:tipoIstanze) and fk_stato_istanza > 1 ");
		sql.append(" ORDER BY char_length(trim(");
		sql.append(PARTICELLA_COLUMN_NAME);
		sql.append( ")),");
		sql.append(PARTICELLA_COLUMN_NAME);
		logger.info("findParticelleByComuneSezioneFoglio - sql: " + sql.toString() 
			+ " - comune: " + fkComune + " - sezione: "+ sezione + " - foglio: " + foglio + " - idTipoIstanza:" + idTipoIstanza);
/*		Object[] params = new Object[] {fkComune, sezione, foglio, idTipoIstanza};
		return DBUtil.jdbcTemplate.query(sql.toString(), propCatastoParticellaMapper, params);*/
		return DBUtil.namedJdbcTemplate.query(
				sql.toString(),
				inQueryParams,
				propCatastoParticellaMapper);
	}

	@Override
	public List<PlainParticelleCat> findParticelleByAll(int fkComune, String sezione, int foglio,
			String particella) {
		StringBuilder sql = new StringBuilder();

		sql.append(
				"SELECT idgeo_pl_prop_catasto,fk_comune,sezione, foglio,particella,sup_catastale_ha,sup_cartografica_ha,");
		sql.append(" i.geometria,denominazione_comune,istat_comune,istat_prov ");
		sql.append(" FROM idf_geo_pl_prop_catasto i JOIN idf_geo_pl_comune igpc on i.fk_comune = igpc.id_comune ");
		sql.append("  WHERE fk_comune = ? and sezione = ? and foglio = ? and particella = ? ");
		sql.append(" ORDER BY particella");

		
		return DBUtil.jdbcTemplate.query(sql.toString(), plainParticelleCatMapper, fkComune, sezione, foglio,
				particella);
	}

//	@Override
//	public void updateAllNoteOfOneIntervento(String annotazioni, int idIntervento) {
//		StringBuilder sql = new StringBuilder();
//		sql.append("UPDATE idf_geo_pl_prop_catasto SET note = ?");
//		sql.append(" WHERE idgeo_pl_prop_catasto IN(");
//		sql.append("SELECT idgeo_pl_prop_catasto FROM idf_r_propcatasto_intervento");
//		sql.append(" WHERE id_intervento = ?)");
//
//		DBUtil.jdbcTemplate.update(sql.toString(), annotazioni, idIntervento);
//	}

	@Override
	public List<DichPropCatasto> getDichPropCatastosByIdIntervento(Integer idIntervento) {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT cat.sezione, cat.foglio, cat.particella, cat.sup_catastale_ha, cat.sup_cartografica_ha");
		sql.append(", cmn.denominazione_comune, prov.denominazione_prov,irpi.sup_intervento_ha ");
		sql.append(" FROM idf_geo_pl_prop_catasto cat");
		sql.append(" JOIN idf_geo_pl_comune cmn ON cat.fk_comune = cmn.id_comune");
		sql.append(" JOIN idf_geo_pl_provincia prov ON cmn.istat_prov = prov.istat_prov");
		sql.append(" JOIN idf_r_propcatasto_intervento irpi on cat.idgeo_pl_prop_catasto = irpi.idgeo_pl_prop_catasto");
		// sql.append(" WHERE cat.idgeo_pl_prop_catasto IN(");
		// sql.append(" SELECT idgeo_pl_prop_catasto FROM idf_r_propcatasto_intervento
		// WHERE id_intervento =?) AND ");
		sql.append(" WHERE id_intervento =?");

		return DBUtil.jdbcTemplate.query(sql.toString(), dichPropCatastoMapper, idIntervento);
	}

	@Override
	public PropCatasto findPropCatastoByPlainParticelle(Integer idGeoPlPfa, Integer comune, String sezione,
			Integer foglio, String particella) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT idgeo_pl_prop_catasto, idgeo_pl_pfa, fk_comune, sezione, foglio, allegato_catasto");
		sql.append(", sviluppo_catasto, particella, sup_catastale_ha, sup_cartografica_ha, fk_proprieta, intestato");
		sql.append(", qualita_coltura, flg_usi_civici, flg_possessi_contest, flg_livellari, data_inizio_validita");
		sql.append(", data_fine_validita, note, data_aggiornamento_datocatast, data_inserimento, fk_config_utente");
		sql.append(
				" FROM idf_geo_pl_prop_catasto WHERE idgeo_pl_pfa = ? and fk_comune = ? and sezione = ? and foglio = ? and particella=?");

		List<Object> parameters = new ArrayList<>();

		parameters.add(idGeoPlPfa);
		parameters.add(comune);
		parameters.add(sezione);
		parameters.add(foglio);
		parameters.add(particella);

		return DataAccessUtils
				.singleResult(DBUtil.jdbcTemplate.query(sql.toString(), propCatastoMapper, parameters.toArray()));

	}

	@Override
	public List<PropCatasto> getAllPropCatastoByComune(Integer idGeoPlPfa, Integer comune) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT idgeo_pl_prop_catasto, idgeo_pl_pfa, fk_comune, sezione, foglio, allegato_catasto");
			sql.append(", sviluppo_catasto, particella, sup_catastale_ha, sup_cartografica_ha, fk_proprieta, intestato");
			sql.append(", qualita_coltura, flg_usi_civici, flg_possessi_contest, flg_livellari, data_inizio_validita");
			sql.append(", data_fine_validita, note, data_aggiornamento_datocatast, data_inserimento, fk_config_utente,geometria");
			sql.append(" FROM idf_geo_pl_prop_catasto WHERE idgeo_pl_pfa = ? and fk_comune = ? ");

			List<Object> parameters = new ArrayList<>();

			parameters.add(idGeoPlPfa);
			parameters.add(comune);
			logger.info("<------ getAllPropCatastoByComune" + sql.toString());
			return DBUtil.jdbcTemplate.query(sql.toString(), propCatastoMapper, parameters.toArray());
		} catch (Exception e) {
			e.printStackTrace();
			//response = Response.serverError().entity("GeecoApiServiceImpl:getGeecoConfiguration - Errore nella generazione del JSON per geeco "+e.getMessage()).build();
			//response = Response.ok().entity("Error").build();
			return null;
		}

	}

	@Override
	public void insertSupInterventoValue(PlainParticelleCatastali plainParticelleCatastali) {

		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE idf_r_propcatasto_intervento SET sup_intervento_ha = ?");
		sql.append(" WHERE idgeo_pl_prop_catasto = ?");

		List<Object> parameters = new ArrayList<>();

		parameters.add(plainParticelleCatastali.getSupCatastale().multiply(BigDecimal.valueOf(0.30)));
		parameters.add(plainParticelleCatastali.getId());

		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}

	@Override
	public List<PlainParticelleCatastali> getPlainParticelleByIdIntervento(Integer idIntervento) {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT propcat.idgeo_pl_prop_catasto, propcat.fk_comune, propcat.sezione, propcat.foglio");
		sql.append(" , propcat.particella, propcat.sup_cartografica_ha, interv.sup_intervento_ha");
		sql.append(" ,igpc.denominazione_comune , igpc.istat_comune , igpc.istat_prov, propcat.geometria");
		sql.append(" FROM idf_geo_pl_prop_catasto propcat");
		sql.append(" JOIN idf_r_propcatasto_intervento interv using(idgeo_pl_prop_catasto)");
		sql.append(" JOIN idf_geo_pl_comune igpc on propcat.fk_comune = igpc.id_comune ");
		sql.append(" WHERE id_intervento = ? ");

		return DBUtil.jdbcTemplate.query(sql.toString(), new PlainParticelleCatastaliMapper(), idIntervento);

	}


	@Override
	public List<PlainParticelleCatastali> findParticelleByComuneFoglioParticella(int fkComune, String sezione, int foglio, String particella) {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT propcat.idgeo_pl_prop_catasto, propcat.fk_comune, propcat.sezione, propcat.foglio");
		sql.append(" , propcat.particella, propcat.sup_catastale_ha, propcat.fk_config_utente, propcat.sup_cartografica_ha, interv.sup_intervento_ha");
		sql.append(" , propcat.geometria, igpc.denominazione_comune , igpc.istat_comune , igpc.istat_prov");
		sql.append(" FROM idf_geo_pl_prop_catasto propcat");
		sql.append(" JOIN idf_r_propcatasto_intervento interv using(idgeo_pl_prop_catasto)");
		sql.append(" JOIN idf_geo_pl_comune igpc on propcat.fk_comune = igpc.id_comune ");
		sql.append(" WHERE fk_comune = ? AND sezione = ? AND foglio = ? AND particella = ?");

		List<Object> parameters = new ArrayList<>();

		parameters.add(fkComune);
		parameters.add(trim(sezione));
		parameters.add(foglio);
		parameters.add(trim(particella));

		return DBUtil.jdbcTemplate.query(sql.toString(), new PlainParticelleCatastaliMapper(), parameters.toArray());

	}

	@Override
	public PlainParticelleCatastali insertParticella(PlainParticelleCatastali particella) {
		
		try {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO idf_geo_pl_prop_catasto (idgeo_pl_pfa, fk_comune, sezione");
		sql.append(",foglio,particella,sup_catastale_ha, sup_cartografica_ha, fk_proprieta");
		sql.append(",flg_usi_civici,flg_possessi_contest,flg_livellari,data_inizio_validita,");
		sql.append("data_aggiornamento_datocatast, data_inserimento, fk_config_utente, geometria) ");
		//sql.append("values (0,?,?,?,?,?,?,0,0,0,0,?,?,?,?,ST_GeomFromText(?))"); // necesitamos esta funcion 
		sql.append("values (0,?,?,?,?,?,?,0,0,0,0,?,?,?,?,ST_GeomFromText(?, 32632))"); // necesitamos esta funcion 
		//sql.append("values (0,?,?,?,?,?,?,0,0,0,0,?,?,?,?,?)"); // entrara directo geoJSON ---> geoJSON
		
		List<Object> parameters = new ArrayList<>();

		parameters.add(particella.getComune().getIdComune());
		parameters.add(trim(particella.getSezione()));
		parameters.add(particella.getFoglio());
		parameters.add(trim(particella.getParticella()));
		parameters.add(particella.getSupCatastale());
		parameters.add(particella.getSupCartograficaHa());
		parameters.add(new Timestamp(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()));
		parameters.add(null == particella.getDataAggiornamentoDatoCatastale() ?	null
				: Timestamp.valueOf(particella.getDataAggiornamentoDatoCatastale().atStartOfDay())) ;
		parameters.add(new Timestamp(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()));
		parameters.add(particella.getFkConfigUtente());
		
		//parameters.add(this.convertToPolygonString(particella.getGeometry().toString()));
		parameters.add(particella.getGeometry());
		logger.info("SQL PropCatasto: " + sql.toString() + " ---- " + parameters.toString());
		Integer idgeoPlPropCatasto = AIKeyHolderUtil.updateWithGenKey("idgeo_pl_prop_catasto", sql.toString(), parameters);
		logger.info("insertParticella - idgeoPlPropCatasto: " + idgeoPlPropCatasto);
		particella.setIdGeoPlPropCatasto(idgeoPlPropCatasto);		
		particella.setId(idgeoPlPropCatasto);		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.info("<----- insertParticella " + e);
		}
		return particella;
	}
	
    public static String convertToPolygonString(String geometryString) {
        String coordinatesPart = geometryString.split("coordinates\":\\[")[1];
        String[] pointsArray = coordinatesPart.split("\\[\\[")[1].split("\\]\\],\\[")[0].split("\\],\\[");
        StringBuilder coordinatesBuilder = new StringBuilder();
        for (String point : pointsArray) {
            String[] coordinates = point.replace("[", "").replace("]", "").split(",");
            coordinatesBuilder.append(coordinates[0]).append(" ").append(coordinates[1]).append(", ");
        }
        coordinatesBuilder.setLength(coordinatesBuilder.length() - 2); // Eliminar la última coma y el espacio

        String polygon = "POLYGON((" + coordinatesBuilder.toString() + "))";
        return polygon.replace("}", "");
    }
    
	private String trim(String value) {
		if(value != null) {
			return value.trim();
		}
		return value;
	}
	
	
}
