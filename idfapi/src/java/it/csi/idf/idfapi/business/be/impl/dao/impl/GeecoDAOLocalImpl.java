/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.impl.dao.GeecoLocalDAO;
import it.csi.idf.idfapi.dto.AreaDiSaggioDatiGeneraliDTO;
import it.csi.idf.idfapi.dto.GeecoAdsFeature;
import it.csi.idf.idfapi.dto.GeecoLayer;
import it.csi.idf.idfapi.dto.GeecoPfaEventoFeature;
import it.csi.idf.idfapi.dto.GeecoPfaFeature;
import it.csi.idf.idfapi.dto.GeecoPfaInterventoFeature;
import it.csi.idf.idfapi.dto.GeecoProfilo;
import it.csi.idf.idfapi.dto.GeecoTrsFeature;
import it.csi.idf.idfapi.dto.GeecoVincoloFeature;
import it.csi.idf.idfapi.mapper.AreaDiSaggioDatiGeneraliMapper;
import it.csi.idf.idfapi.mapper.GeecoAdsFeatureMapper;
import it.csi.idf.idfapi.mapper.GeecoLayerMapper;
import it.csi.idf.idfapi.mapper.GeecoPfaEventoFeatureMapper;
import it.csi.idf.idfapi.mapper.GeecoPfaFeatureMapper;
import it.csi.idf.idfapi.mapper.GeecoPfaInterventoFeatureMapper;
import it.csi.idf.idfapi.mapper.GeecoProfiloMapper;
import it.csi.idf.idfapi.mapper.GeecoTrsFeatureMapper;
import it.csi.idf.idfapi.mapper.GeecoVincoloFeatureMapper;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.QueryUtil;


@Component
public class GeecoDAOLocalImpl extends AbstractDAO implements GeecoLocalDAO {

	private final GeecoAdsFeatureMapper adsMapper = new GeecoAdsFeatureMapper();
	private final GeecoProfiloMapper profiloMapper = new GeecoProfiloMapper();
	private final GeecoLayerMapper layerMapper = new GeecoLayerMapper();
	private final GeecoTrsFeatureMapper trsMapper = new GeecoTrsFeatureMapper();
	private final AreaDiSaggioDatiGeneraliMapper adsGeneraliMapper = new AreaDiSaggioDatiGeneraliMapper();
	private final GeecoPfaEventoFeatureMapper eventoMapper = new GeecoPfaEventoFeatureMapper();
	private final GeecoPfaInterventoFeatureMapper interventoMapper = new GeecoPfaInterventoFeatureMapper();
	private final GeecoVincoloFeatureMapper vincoloMapper = new GeecoVincoloFeatureMapper(); 
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	

	@Override
	public List<GeecoAdsFeature> findGeometriaAdsById(String[] idgeoPtAds) throws RecordNotFoundException {
		LOGGER.debug("[GeecoDAOImpl::findGeometriaAdsById] START");
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT idgeo_pt_ads, codice_ads, ST_AsText(geometria) as geometria, json_build_object(");
			sql.append("'type', 'Feature',");
			sql.append("'id', idgeo_pt_ads,");
			sql.append("'geometry', ST_AsGeoJSON(st_multi(geometria))::json,");
			sql.append("'properties', json_build_object(");
			sql.append("'Identificativo', text(idgeo_pt_ads),");
			sql.append("'N. ADS', codice_ads");
			sql.append(")");
			sql.append(") ");
			sql.append("FROM idf_geo_pt_area_di_saggio ");
			sql.append("WHERE idgeo_pt_ads");
			sql.append(QueryUtil.appendInCondition(idgeoPtAds));

			LOGGER.info("Query per GEO ADS *" + sql + "*");
			

			final MapSqlParameterSource params = new MapSqlParameterSource();
			return jdbcTemplate.query(sql.toString(), params, adsMapper);
		} finally {
			LOGGER.debug("[GeecoDAOImpl::findGeometriaAdsById] END");
		}


	}
	
	

	@Override
	public GeecoProfilo findProfiloById(String idProfiloGeeco) throws RecordNotFoundException {
		LOGGER.debug("[GeecoDAOImpl::findProfiloById] START");
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("prof.id_geeco_profilo,");
			sql.append("prof.descr_geeco_profilo,");
			sql.append("prof.fk_procedura,");
			sql.append("prof.url_ritorno,");
			sql.append("prof.env_info, ");
			sql.append("prof.flg_autentic_lettura_scrittura,");
			sql.append("prod.uuid,");
			sql.append("prod.versione ");
			sql.append("FROM ");
			sql.append("idf_asr_t_geeco_profilo prof, idf_asr_t_geeco_prodotto prod ");
			sql.append("WHERE id_geeco_profilo = :id_geeco_profilo ");
			sql.append("AND prof.fk_geeco_prodotto = prod.id_geeco_prodotto ");

			LOGGER.debug("Query per PROFILO GEECO * " + sql + "*");
			
			final MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("id_geeco_profilo", idProfiloGeeco, java.sql.Types.INTEGER);
			return jdbcTemplate.queryForObject(sql.toString(), params, profiloMapper);

			//return DBUtil.jdbcTemplate.queryForObject(sql.toString(), profiloMapper, idProfiloGeeco);
			
			//return DBUtil.jdbcTemplate.queryForObject(sql.toString(), new GeecoProfiloMapper(), Integer.parseInt(idProfiloGeeco));
		} finally {
			LOGGER.debug("[GeecoDAOImpl::findProfiloById] END");
		}
	}

	@Override
	public List<GeecoLayer> findLayerByIdProfilo(String idProfiloGeeco) {
		LOGGER.debug("[GeecoDAOImpl::findLayerByIdProfilo] START");
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("pl.id_geeco_profilo,");
			sql.append("l.id_geeco_layer,");
			sql.append("l.descr_asr_geeco_layer, ");
			sql.append("pl.flg_tipo_accesso_scrittura,");
			sql.append("pl.flg_tipo_accesso_canc, ");
			sql.append("l.flg_tipo_layer ");
			sql.append("FROM ");
			sql.append("idf_asr_t_geeco_layer l, idf_asr_r_geeco_profilo_layer pl ");
			sql.append("WHERE l.id_geeco_layer = pl.id_geeco_layer ");
			sql.append("AND pl.id_geeco_profilo =:id_geeco_profilo");

			LOGGER.debug("Query per PROFILO GEECO * " + sql + "*");
			
			final MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("id_geeco_profilo", idProfiloGeeco, java.sql.Types.INTEGER);
			return jdbcTemplate.query(sql.toString(), params, layerMapper);
			
			//return DBUtil.jdbcTemplate.query(sql.toString(), layerMapper, Integer.parseInt(idProfiloGeeco));
		} finally {
			LOGGER.debug("[GeecoDAOImpl::findLayerByIdProfilo] END");
		}
	}
	
	@Override
	public GeecoTrsFeature findGeometriaTrsById(String idTrs) throws RecordNotFoundException {
		LOGGER.debug("[GeecoDAOImpl::findGeometriaTrsById] START");
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("idgeo_pl_interv_trasf, "); 
			sql.append("data_inserimento, "); 
			sql.append("fk_intervento, "); 
			sql.append("ST_AsText(geometria) as geometria, "); 
			sql.append("round(st_area(geometria)::numeric,4) as superficie, ");
			sql.append("json_build_object(");
			sql.append("'type', 'Feature',");
			sql.append("'id', idgeo_pl_interv_trasf,");
			sql.append("'geometry', ST_AsGeoJSON(geometria)::json,");
			sql.append("'properties', json_build_object(");
			sql.append("'Identificativo', text(idgeo_pl_interv_trasf),");
			sql.append("'Intervento', fk_intervento,");
			//sql.append("'Data invio istanza', '',");
			sql.append("'Superficie', ROUND((st_area(geometria)/10000)::numeric, 4)");
			sql.append(")");
			sql.append(") ");  
			sql.append("FROM idf_geo_pl_interv_trasf "); 
			sql.append("WHERE idgeo_pl_interv_trasf = :idgeo_pl_interv_trasf");
			
			LOGGER.debug("Query  findGeometriaTrsById* " + sql + "*");
			
			final MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("idgeo_pl_interv_trasf", idTrs, java.sql.Types.INTEGER);
			return jdbcTemplate.queryForObject(sql.toString(), params, trsMapper);
			
		} 
		finally {
			LOGGER.debug("[GeecoDAOImpl::findGeometriaTrsById] END");
		}
	}

	@Override
	public List<GeecoTrsFeature> findGeometrieTrsById(String[] idIntervento) throws RecordNotFoundException {
		LOGGER.debug("[GeecoDAOImpl::findGeometrieTrsById] START");
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("idgeo_pl_interv_trasf, "); 
			sql.append("data_inserimento, "); 
			sql.append("fk_intervento, "); 
			sql.append("ST_AsText(geometria) as geometria, "); 
			sql.append("round(st_area(geometria)::numeric,4) as superficie, ");
			sql.append("json_build_object(");
			sql.append("'type', 'Feature',");
			sql.append("'id', idgeo_pl_interv_trasf,");
			sql.append("'geometry', ST_AsGeoJSON(geometria)::json,");
			sql.append("'properties', json_build_object(");
			sql.append("'Identificativo', text(idgeo_pl_interv_trasf),");
			sql.append("'Id intervento', fk_intervento,");
			//sql.append("'Data invio istanza', '',");
			sql.append("'Superficie (ha)', ROUND((st_area(geometria)/10000)::numeric, 4)");
			sql.append(")");
			sql.append(") ");  
			sql.append("FROM idf_geo_pl_interv_trasf "); 
			sql.append("WHERE fk_intervento");
			sql.append(QueryUtil.appendInCondition(idIntervento));
			final MapSqlParameterSource params = new MapSqlParameterSource();
			
			
			LOGGER.debug("Query  findGeometrieTrsById* " + sql + "*");
			
			return jdbcTemplate.query(sql.toString(), params, trsMapper);
			
		} 
		finally {
			LOGGER.debug("[GeecoDAOImpl::findGeometrieTrsById] END");
		}
	}
	
	@Override
	public List<GeecoVincoloFeature> findGeometrieVincoloById(String[] idIntervento) throws RecordNotFoundException {
		LOGGER.debug("[GeecoDAOImpl::findGeometrieVincoloById] START");
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("id_geo_pl_interv_vincidro, "); 
			sql.append("data_inserimento, "); 
			sql.append("fk_intervento, "); 
			sql.append("ST_AsText(geometria) as geometria, "); 
			sql.append("round(st_area(geometria)::numeric,4) as superficie, ");
			sql.append("json_build_object(");
			sql.append("'type', 'Feature',");
			sql.append("'id', id_geo_pl_interv_vincidro,");
			sql.append("'geometry', ST_AsGeoJSON(geometria)::json,");
			sql.append("'properties', json_build_object(");
			sql.append("'Identificativo', text(id_geo_pl_interv_vincidro),");
			sql.append("'Id intervento', fk_intervento,");
			//sql.append("'Data invio istanza', '',");
			sql.append("'Superficie (ha)', ROUND((st_area(geometria)/10000)::numeric, 4)");
			sql.append(")");
			sql.append(") ");  
			sql.append("FROM idf_geo_pl_interv_vincidro "); 
			sql.append("WHERE fk_intervento");
			sql.append(QueryUtil.appendInCondition(idIntervento));
			final MapSqlParameterSource params = new MapSqlParameterSource();
			
			
			LOGGER.debug("Query  findGeometrieVincoloById* " + sql + "*");
			
			return jdbcTemplate.query(sql.toString(), params, vincoloMapper);
			
		} 
		finally {
			LOGGER.debug("[GeecoDAOImpl::findGeometrieVincoloById] END");
		}
	}

	public AreaDiSaggioDatiGeneraliDTO findADSByIdgeoPtAds(Long idgeoPtAds) {
		LOGGER.debug("[GeecoDAOImpl::findADSByIdgeoPtAds] START");
		List<AreaDiSaggioDatiGeneraliDTO> list = null;
		try {
			StringBuilder sql = new StringBuilder("Select ads.*, ST_X(ST_TRANSFORM(ads.geometria,32632)),ST_Y(ST_TRANSFORM(ads.geometria,32632)), comune.istat_prov istat_prov, ambito.fk_padre as ambitoDiRilevamento "); 
			sql.append(" FROM idf_geo_pt_area_di_saggio ads left join idf_geo_pl_comune comune on ads.fk_comune= comune.id_comune "); 
			sql.append(" left JOIN idf_d_ambito_rilievo ambito on ads.fk_ambito=ambito.id_ambito ");
			sql.append(" WHERE ads.idgeo_pt_ads = :idgeoPtAds") ;
			
			LOGGER.info("findADSByIdgeoPtAds sql: " + sql.toString());
			
			final MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("idgeoPtAds", idgeoPtAds, java.sql.Types.NUMERIC);
			
			list = jdbcTemplate.query(sql.toString(), params, adsGeneraliMapper);
			
			
			
		}
		catch (Throwable  e) {
			LOGGER.info("Eccezione "+e.getMessage());
			e.printStackTrace();
		}
		finally {
			LOGGER.debug("[GeecoDAOImpl::findADSByIdgeoPtAds] END");
		}
		return list.isEmpty()?null:list.get(0);
		
	}


	@Override
	public List<GeecoPfaEventoFeature> findGeometriePfaEventoById(String[] idEvento, String tipo)
			throws RecordNotFoundException {
		LOGGER.debug("[GeecoDAOImpl::findGeometriePfaEventoById] START");
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT e.id_evento, te.id, te.tipo, te.descrizione, e.progressivo_evento_pfa, ST_AsText(te.geometria) as geometria, ");
			sql.append("pfa.idgeo_pl_pfa, pfa.denominazione, json_build_object(");
			sql.append("'type', 'Feature',");
			sql.append("'id', te.id,");
			sql.append("'geometry', ST_AsGeoJSON(te.geometria)::json,");
			sql.append("'properties', json_build_object(");
			sql.append("'Identificativo', text(te.id),");
			sql.append("'N. Evento', e.progressivo_evento_pfa, ");
			sql.append("'Id evento', e.id_evento, ");
			sql.append("'Denominazione PFA', pfa.denominazione, ");
			sql.append("'Descrizione', te.descrizione ");
			sql.append(")");
			sql.append(") ");
			sql.append("FROM ");
//			sql.append("idf_r_evento_partfor ep,"); 
//			sql.append("idf_geo_pl_particella_forest pf,"); 
//			sql.append("idf_geo_pl_pfa pfa, ");
			sql.append("idf_t_evento e ");
			sql.append("left join (select t.* from(");
			sql.append("select idgeo_pl_evento id, 'PL' tipo, geometria, fk_evento, descrizione "); 
			sql.append("from idf_geo_pl_evento "); 
			sql.append("union "); 
			sql.append("select idgeo_pt_evento id, 'PT' tipo, st_multi(geometria), fk_evento, descrizione "); 
			sql.append("from idf_geo_pt_evento "); 
			sql.append("union "); 
			sql.append("select idgeo_ln_evento id, 'LN' tipo, geometria, fk_evento, descrizione "); 
			sql.append("from idf_geo_ln_evento");
			sql.append(") t where t.tipo = :tipo) te "); 
			sql.append("on e.id_evento = te.fk_evento ");
			sql.append("WHERE e.idgeo_pl_pfa = pfa.idgeo_pl_pfa ");
//			sql.append("WHERE e.id_evento = ep.id_evento "); 
//			sql.append("and ep.idgeo_pl_particella_forest = pf.idgeo_pl_particella_forest "); 
//			sql.append("and pf.idgeo_pl_pfa = pfa.idgeo_pl_pfa "); 
//			sql.append("and e.id_evento ");
			sql.append(QueryUtil.appendInCondition(idEvento));
			
			final MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("tipo", tipo, java.sql.Types.VARCHAR);
			
			return jdbcTemplate.query(sql.toString(), params, eventoMapper);
			
		} finally {
			LOGGER.debug("[GeecoDAOImpl::findGeometriePfaEventoById] END");
		}
	}


	@Override
	public List<GeecoPfaInterventoFeature> findGeometriePfaInterventoById(String[] idIntervento, String tipo)
			throws RecordNotFoundException {
		LOGGER.debug("[GeecoDAOImpl::findGeometriePfaEventoById] START");
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT i.id_intervento, ti.id, ti.tipo, ti.descrizione, i.nr_progressivo_interv , ST_AsText(ti.geometria) as geometria, ");
			sql.append("pfa.idgeo_pl_pfa, pfa.denominazione, json_build_object(");
			sql.append("'type', 'Feature',");
			sql.append("'id', ti.id,");
			sql.append("'geometry', ST_AsGeoJSON(ti.geometria)::json,");
			sql.append("'properties', json_build_object(");
			sql.append("'Identificativo', text(ti.id),");
			sql.append("'N. Intervento', i.nr_progressivo_interv, ");
			sql.append("'Id intervento', i.id_intervento, ");
			sql.append("'Denominazione PFA', pfa.denominazione, ");
			sql.append("'Descrizione', ti.descrizione ");
			sql.append(")");
			sql.append(") ");
			sql.append("FROM  idf_geo_pl_pfa pfa, idf_t_interv_selvicolturale i ");
			sql.append("left join (select t.* from(");
			sql.append("select idgeo_pl_lotto_intervento id, 'PL' tipo, geometria, fk_intervento as id_intervento, descrizione "); 
			sql.append("from idf_geo_pl_lotto_intervento "); 
			sql.append("union "); 
			sql.append("select idgeo_pt_lotto_intervento id, 'PT' tipo, st_multi(geometria), id_intervento, descrizione "); 
			sql.append("from idf_geo_pt_lotto_intervento "); 
			sql.append("union "); 
			sql.append("select idgeo_ln_lotto_intervento id, 'LN' tipo, geometria, id_intervento, descrizione "); 
			sql.append("from idf_geo_ln_lotto_intervento");
			sql.append(") t where t.tipo = :tipo) ti "); 
			sql.append("ON 	i.id_intervento = ti.id_intervento ");
			sql.append("WHERE pfa.idgeo_pl_pfa = i.idgeo_pl_pfa "); 
			sql.append("and i.id_intervento ");
			sql.append(QueryUtil.appendInCondition(idIntervento));
			
			
			final MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("tipo", tipo, java.sql.Types.VARCHAR);
			
			return jdbcTemplate.query(sql.toString(), params, interventoMapper);
			
		} finally {
			LOGGER.debug("[GeecoDAOImpl::findGeometriePfaEventoById] END");
		}
	}


	@Override
	public List<GeecoPfaFeature> findGeometriePfaById(String[] idPfa) throws RecordNotFoundException {
		LOGGER.debug("[GeecoDAOImpl::findGeometriePfaById] START");
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select a.*, json_build_object( ");
			sql.append("'type', 'Feature', ");
			sql.append("'id', a.id, ");
			sql.append("'geometry', ST_AsGeoJSON(a.geometria)::json, ");
			sql.append("'properties', json_build_object( ");
			sql.append("'Identificativo', text(a.idgeo_pl_pfa), ");
			sql.append("'Comune', a.denominazione_comune, ");
			sql.append("'Codice PFA', a.denominazione "); 
			sql.append(") ");
			sql.append(") ");  
			sql.append("from ( ");
//			sql.append(" select distinct pfa.idgeo_pl_pfa, pfa.denominazione, '-' as denominazione_comune, (ST_DUMP(pfa.geometria)).geom as geometria ");
//			sql.append(" select distinct pfa.idgeo_pl_pfa, pfa.denominazione, '-' as denominazione_comune, ST_AsText(pfa.geometria) as geometria ");
//			sql.append(" select distinct pfa.idgeo_pl_pfa, pfa.denominazione, '-' as denominazione_comune, (ST_DUMP(pfa.geometria)).geom as geometria ");
//			sql.append("from "); 
//			sql.append("idf_geo_pl_pfa pfa ");
//			sql.append("where  "); 
//			sql.append("pfa.idgeo_pl_pfa ");
//			sql.append(QueryUtil.appendInCondition(idPfa));
//			sql.append(") a, idf_geo_pl_pfa p ");
			sql.append("select b.idgeo_pl_pfa||''||row_number() over() as id,b.idgeo_pl_pfa, b.geometria, b.denominazione_comune, b.denominazione  from (select (ST_DUMP(pfa.geometria)).geom as geometria, pfa.idgeo_pl_pfa, pfa.denominazione, '-' as denominazione_comune from idf_geo_pl_pfa pfa) b  ");
			sql.append("where  b.idgeo_pl_pfa  ");
			sql.append(QueryUtil.appendInCondition(idPfa));
			sql.append(") a, idf_geo_pl_pfa p ");
			sql.append("where p.idgeo_pl_pfa = a.idgeo_pl_pfa ");
	
			
			LOGGER.info("Query per findGeometriePfaById *" + sql + "*");
			
			return jdbcTemplate.query(sql.toString(), new MapSqlParameterSource(),new GeecoPfaFeatureMapper());
		} finally {
			LOGGER.debug("[GeecoDAOImpl::findGeometriePfaById] END");
		}
		
	}
	
	@Override
	public List<GeecoPfaFeature> findGeometriePfaByIdIntervento(String[] idIntervento) throws RecordNotFoundException {
		LOGGER.debug("[GeecoDAOImpl::findGeometriePfaByIdIntervento] START");
		try {
			StringBuilder sql = new StringBuilder();
//			sql.append("select a.*, json_build_object( ");
//			sql.append("'type', 'Feature', ");
//			sql.append("'id', a.idgeo_pl_pfa, ");
//			sql.append("'geometry', ST_AsGeoJSON(a.geometria)::json, ");
//			sql.append("'properties', json_build_object( ");
//			sql.append("'Identificativo', text(a.idgeo_pl_pfa), ");
//			sql.append("'Descrizione', a.denominazione_comune, ");
//			sql.append("'Denominazione PFA', a.denominazione )) ");  
//			sql.append("from( ");
//			sql.append("select distinct pfa.idgeo_pl_pfa, pfa.denominazione, c.denominazione_comune, ST_AsText(pfa.geometria) as geometria "); 
//			sql.append("from ");
//			sql.append("idf_geo_pl_pfa pfa, ");
//			sql.append("idf_t_interv_selvicolturale i, ");
//			sql.append("idf_geo_pl_prop_catasto pc, ");
//			sql.append("idf_geo_pl_comune c ");
//			sql.append("where pfa.idgeo_pl_pfa = i.idgeo_pl_pfa "); 
//			sql.append("and pfa.idgeo_pl_pfa  = pc.idgeo_pl_pfa ");
//			sql.append("and pc.fk_comune = c.id_comune "); 
//			sql.append("and i.id_intervento "); 
//			sql.append(QueryUtil.appendInCondition(idIntervento));
//			sql.append(") a, ");
//			sql.append("idf_geo_pl_pfa p ");
//			sql.append("where p.idgeo_pl_pfa = a.idgeo_pl_pfa ");
			
			
			sql.append("select ");
			sql.append("a.*, ");
			sql.append("json_build_object( 'type', 'Feature', 'id', a.id, 'geometry', ST_AsGeoJSON(a.geometria)::json, 'properties', json_build_object( 'Identificativo', text(a.idgeo_pl_pfa), 'Descrizione', '', 'Denominazione PFA', a.denominazione )) ");
			sql.append("from ");
			sql.append("( ");
			sql.append("select ");
			sql.append("b.idgeo_pl_pfa ||''||row_number() over() as id, ");
			sql.append("b.idgeo_pl_pfa, ");
			sql.append("b.denominazione_comune, ");
			sql.append("b.denominazione, ");
			sql.append("b.geometria "); 
			sql.append("from ");
			sql.append("(select ");
			sql.append("(ST_DUMP(pfa.geometria)).geom as geometria, ");
			sql.append("pfa.idgeo_pl_pfa, ");
			sql.append("pfa.denominazione, ");
			sql.append("'-' as denominazione_comune ");
			sql.append("from ");
			sql.append("idf_geo_pl_pfa pfa) b, ");
			sql.append("idf_t_interv_selvicolturale i ");
			sql.append("where ");
			sql.append("b.idgeo_pl_pfa = i.idgeo_pl_pfa ");
			sql.append("and i.id_intervento "); 
			sql.append(QueryUtil.appendInCondition(idIntervento));
			sql.append(") a, ");
			sql.append("idf_geo_pl_pfa p ");
			sql.append("where ");
			sql.append("p.idgeo_pl_pfa = a.idgeo_pl_pfa ");
			
			LOGGER.info("Query per findGeometriePfaByIdIntervento *" + sql + "*");
			
			return jdbcTemplate.query(sql.toString(), new MapSqlParameterSource(),new GeecoPfaFeatureMapper());
		} finally {
			LOGGER.debug("[GeecoDAOImpl::findGeometriePfaByIdIntervento] END");
		}
		
	}

	public List<GeecoPfaFeature> findGeometriePfaByIdEvento(String[] idEvento) throws RecordNotFoundException {
		LOGGER.debug("[GeecoDAOImpl::findGeometriePfaByIdEvento] START");
		try {
			StringBuilder sql = new StringBuilder();
//			sql.append("select a.*, json_build_object( ");
//			sql.append("'type', 'Feature', ");
//			sql.append("'id', a.idgeo_pl_pfa, ");
//			sql.append("'geometry', ST_AsGeoJSON(a.geometria)::json, ");
//			sql.append("'properties', json_build_object( ");
//			sql.append("'Identificativo', text(a.idgeo_pl_pfa), ");
//			sql.append("'Descrizione', a.denominazione_comune, ");
//			sql.append("'Denominazione PFA', a.denominazione )) ");  
//			sql.append("from( ");
//			sql.append("select distinct pfa.idgeo_pl_pfa, pfa.denominazione, e.id_evento, e.progressivo_evento_pfa, c.denominazione_comune, ST_AsText(pfa.geometria) as geometria "); 
//			sql.append("from "); 
//			sql.append("idf_r_evento_partfor ep, ");
//			sql.append("idf_geo_pl_particella_forest pf, ");
//			sql.append("idf_geo_pl_pfa pfa, ");
//			sql.append("idf_t_evento e, ");
//			sql.append("idf_geo_pl_prop_catasto pc, ");
//			sql.append("idf_geo_pl_comune c ");
//			sql.append("where e.id_evento = ep.id_evento ");
//			sql.append("and ep.idgeo_pl_particella_forest = pf.idgeo_pl_particella_forest ");
//			sql.append("and pf.idgeo_pl_pfa = pfa.idgeo_pl_pfa ");
//			sql.append("and pfa.idgeo_pl_pfa = pc.idgeo_pl_pfa "); 
//			sql.append("and pc.fk_comune = c.id_comune "); 
//			sql.append("and e.id_evento "); 
//			sql.append(QueryUtil.appendInCondition(idEvento));
//			sql.append(") a, ");
//			sql.append("idf_geo_pl_pfa p ");
//			sql.append("where p.idgeo_pl_pfa = a.idgeo_pl_pfa ");
			
			sql.append("select ");
			sql.append("a.*, ");
			sql.append("json_build_object( "); 
			sql.append("'type', 'Feature', "); 
			sql.append("'id', a.id, "); 
			sql.append("'geometry', ST_AsGeoJSON(a.geometria)::json, "); 
			sql.append("'properties', json_build_object( "); 
			sql.append("'Identificativo', text(a.idgeo_pl_pfa), "); 
			sql.append("'Descrizione', a.denominazione_comune, "); 
			sql.append("'Denominazione PFA', a.denominazione )) ");
			sql.append("from ");
			sql.append("( ");
			sql.append("select ");
			sql.append("b.idgeo_pl_pfa ||''||row_number() over() as id, ");
			sql.append("b.idgeo_pl_pfa, ");
			sql.append("b.denominazione, ");
			sql.append("b.denominazione_comune, ");
			sql.append("b.geometria ");
			sql.append("from ");  
			sql.append("(select ");
			sql.append("(ST_DUMP(pfa.geometria)).geom as geometria, ");
			sql.append("pfa.idgeo_pl_pfa, ");
			sql.append("pfa.denominazione, ");
			sql.append("'-' as denominazione_comune ");
			sql.append("from ");
			sql.append("idf_geo_pl_pfa pfa) b, ");
			sql.append("idf_t_evento e ");
			sql.append("where ");
			sql.append("e.idgeo_pl_pfa = b.idgeo_pl_pfa ");
			sql.append("and e.id_evento ");
			sql.append(QueryUtil.appendInCondition(idEvento));
			sql.append(") a, "); 
			sql.append("idf_geo_pl_pfa p ");
			sql.append("where ");
			sql.append("p.idgeo_pl_pfa = a.idgeo_pl_pfa ");
			
			LOGGER.info("Query per findGeometriePfaByIdEvento *" + sql + "*");
			
			return  jdbcTemplate.query(sql.toString(), new MapSqlParameterSource(),new GeecoPfaFeatureMapper());
		} finally {
			LOGGER.debug("[GeecoDAOImpl::findGeometriePfaByIdEvento] END");
		}
	}

	@Override
	public int countGeometrieEvento(String[] idEvento) {
		LOGGER.debug("[GeecoDAOImpl::countGeometrieEvento] START");
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select count(a.id) from( ");
			sql.append("select idgeo_pl_evento id, fk_evento, geometria ");
			sql.append("from idf_geo_pl_evento ");
			sql.append("union ");
			sql.append("select idgeo_pt_evento id, fk_evento, geometria ");
			sql.append("from idf_geo_pt_evento ");
			sql.append("union ");
			sql.append("select idgeo_ln_evento id, fk_evento, geometria ");
			sql.append("from idf_geo_ln_evento) a, idf_t_evento e ");
			sql.append("where e.id_evento = a.fk_evento ");
			sql.append("and e.id_evento ");
			sql.append(QueryUtil.appendInCondition(idEvento));
			sql.append(" and a.geometria is not null ");

			LOGGER.info("Query per countGeometrieEvento *" + sql + "*");

			return jdbcTemplate.queryForObject(sql.toString(), new MapSqlParameterSource(),  Integer.class);
		} finally {
			LOGGER.debug("[GeecoDAOImpl::countGeometrieEvento] END");
		}
	}

	@Override
	public int countGeometrieIntervento(String[] idIntervento) {
		LOGGER.debug("[GeecoDAOImpl::countGeometrieIntervento] START");
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select count(a.id) ");
			sql.append("from ");
			sql.append("(select ");
			sql.append("idgeo_pl_lotto_intervento id, geometria, fk_intervento as id_intervento ");
			sql.append("from idf_geo_pl_lotto_intervento ");
			sql.append("union ");
			sql.append("select ");
			sql.append("idgeo_pt_lotto_intervento id, geometria, id_intervento ");
			sql.append("from idf_geo_pt_lotto_intervento ");
			sql.append("union ");
			sql.append("select ");
			sql.append("idgeo_ln_lotto_intervento id, geometria, id_intervento ");
			sql.append("from idf_geo_ln_lotto_intervento) a, idf_t_interv_selvicolturale i ");
			sql.append("where i.id_intervento = a.id_intervento ");
			sql.append("and i.id_intervento ");
			sql.append(QueryUtil.appendInCondition(idIntervento));
			sql.append(" and a.geometria is not null ");

			LOGGER.info("Query per countGeometrieIntervento *" + sql + "*");

			return jdbcTemplate.queryForObject(sql.toString(), new MapSqlParameterSource(),  Integer.class);
		} finally {
			LOGGER.debug("[GeecoDAOImpl::countGeometrieIntervento] END");
		}
	}


	@Override
	public List<GeecoProfilo> testQuery(String first, String input) {
		LOGGER.debug("[GeecoDAOImpl::testQuery] START");
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("prof.id_geeco_profilo,");
			sql.append("prof.descr_geeco_profilo,");
			sql.append("prof.fk_procedura,");
			sql.append("prof.url_ritorno,");
			sql.append("prof.env_info, ");
			sql.append("prof.flg_autentic_lettura_scrittura,");
			sql.append("prod.uuid,");
			sql.append("prod.versione ");
			sql.append("FROM ");
			sql.append("idf_asr_t_geeco_profilo prof, idf_asr_t_geeco_prodotto prod ");
			sql.append("WHERE UPPER(prof.descr_geeco_profilo) LIKE :descr ");
			sql.append("AND prof.fk_geeco_prodotto = prod.id_geeco_prodotto ");
			
			StringBuilder like = new StringBuilder();
			like.append(first);
			like.append("%");
			like.append(input.toUpperCase());
			like.append("%");

			LOGGER.debug("Query per PROFILO GEECO * " + sql + "*");
			
			final MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("descr", like);
			
			return jdbcTemplate.query(sql.toString(), params, profiloMapper);
		} finally {
			LOGGER.debug("[GeecoDAOImpl::testQuery] END");
		}
	}


}
