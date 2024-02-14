/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfgeoapi.integration.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import it.csi.ecogis.util.conversion.GeoJSONGeometryConverter;
import it.csi.ecogis.util.dto.GeoJSONFeature;
import it.csi.idf.idfgeoapi.dto.*;
import it.csi.idf.idfgeoapi.dto.factory.GeoJSONFeatureFactory;
import it.csi.idf.idfgeoapi.exception.DaoException;
import it.csi.idf.idfgeoapi.integration.dao.LayerDao;
import it.csi.idf.idfgeoapi.integration.dao.impl.rowmapper.AdsRowMapper;
import it.csi.idf.idfgeoapi.integration.dao.impl.rowmapper.EventoRowMapper;
import it.csi.idf.idfgeoapi.integration.dao.impl.rowmapper.InterventoRowMapper;
import it.csi.idf.idfgeoapi.integration.dao.impl.rowmapper.TaglioRowMapper;
import it.csi.idf.idfgeoapi.integration.dao.impl.rowmapper.TrasformazioniRowMapper;
import it.csi.idf.idfgeoapi.integration.dao.impl.rowmapper.VincoloRowMapper;
import it.csi.idf.idfgeoapi.util.Constants;
import it.csi.idf.idfgeoapi.util.ConvertUtil;

import static it.csi.idf.idfgeoapi.util.builder.ToStringBuilder.objectToString;

@Repository
public class LayerDaoImpl extends AbstractDAO implements LayerDao {
	
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Autowired
	public LayerDaoImpl(DataSource dataSource, NamedParameterJdbcTemplate  namedJdbcTemplate) {
		this.namedJdbcTemplate = namedJdbcTemplate;
		setDataSource(dataSource);
	}

	@Override
	public FeatureForLayerDto insertFeatureOnLayer(FeatureForLayerDto feature) throws DaoException {
		LOG.info("[LayerDAOImpl::insertFeatureOnLayer] BEGIN");
		FeatureForLayerDto dto = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO .....TODO ");
			LOG.debug(sql.toString());

			int affectedRows = getJdbcTemplate().update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(sql.toString());
					/* TODO Example...
					ps.setLong(1, feature.getId());
					ps.setDouble(2, feature.getGeometry().getCoordinate().getX());
					ps.setDouble(3, feature.getGeometry().getCoordinate().getY());
					*/
					return ps;
				}
			});
			if (affectedRows != 1) {
				throw new DaoException("DaoException while trying to insert FeatureForLayerDto");
			}
			return dto;
		} catch (Exception e) {
			LOG.error("Exception while trying to insert FeatureForLayerDto", e);
			throw new DaoException("DaoException while trying to insert FeatureForLayerDto", e);
		}
	}

	@Override
	public GeoJSONFeature insertAdsOnLayer(AdsForLayerDto dto) throws DaoException {
		LOG.info("[LayerDAOImpl::insertAdsOnLayer] BEGIN");
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO "); 
			sql.append("idf_geo_pt_area_di_saggio "); 
			sql.append("("); 
			sql.append("codice_ads,");
			sql.append("fk_tipo_ads, ");
			sql.append("geometria"); 
			sql.append(") "); 
			sql.append("VALUES ("); 
			sql.append(":codice_ads,"); 
			sql.append(":fk_tipo_ads,");
			sql.append("st_geometryn(st_geomfromgeojson(:geometria),1) "); 
			sql.append(")");
			
			LOG.info("QUERY *"+sql+"*");
			
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("codice_ads", dto.getCodiceAds());
			namedParameters.addValue("fk_tipo_ads", dto.getFkTipoAds());
			String geo = GeoJSONGeometryConverter.convertJTSGeometryToGeoJSON(dto.getGeometry(),Constants.GEECO_EPSG_PIEMONTE);
			
			namedParameters.addValue("geometria", geo,Types.VARCHAR);
			
			KeyHolder keyHolder = new GeneratedKeyHolder();
			
			namedJdbcTemplate.update(sql.toString(), namedParameters, keyHolder, new String[] {"idgeo_pt_ads"});
			
			long newPk = keyHolder.getKey().longValue();
			
			LOG.info("NEW KEY *"+newPk+"*");
			
			return GeoJSONFeatureFactory.create(newPk, dto);
		} catch (Exception e) {
			LOG.error("Exception while trying to insert FeatureForLayerDto", e);
			throw new DaoException("DaoException while trying to insert FeatureForLayerDto", e);
		}
	}

	@Override
	public void updateAdsOnLayer(AdsForLayerDto dto) throws DaoException {
		LOG.info("[LayerDAOImpl::updateAdsOnLayer] BEGIN");
		try {
			final StringBuilder sql = new StringBuilder();
			sql.append("UPDATE idf_geo_pt_area_di_saggio ");
			sql.append("SET geometria = st_geometryn(st_geomfromgeojson(:geometria),1) ");
			sql.append("WHERE idgeo_pt_ads = :idgeo_pt_ads");
			
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			
			
			
			String geo = GeoJSONGeometryConverter.convertJTSGeometryToGeoJSON(dto.getGeometry(),Constants.GEECO_EPSG_PIEMONTE);
			
			
			
			namedParameters.addValue("geometria", geo, Types.VARCHAR);
			namedParameters.addValue("idgeo_pt_ads", dto.getIdFeature());
			
			namedJdbcTemplate.update(sql.toString(), namedParameters);
			
		} catch (Exception e) {
			LOG.error("Exception while trying to updateAdsOnLayer FeatureForLayerDto", e);
			throw new DaoException("DaoException while trying to updateAdsOnLayer FeatureForLayerDto", e);
		}
		finally {
			LOG.info("[LayerDAOImpl::updateAdsOnLayer] END");
		}
	}

	@Override
	public void deleteAdsOnLayer(Long featureId) throws DaoException {
		LOG.info("[LayerDAOImpl::deleteAdsOnLayer] BEGIN");
		try {
			final StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM idf_geo_pt_area_di_saggio ");
			sql.append("WHERE idgeo_pt_ads = :idgeo_pt_ads");
			
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("idgeo_pt_ads", featureId);
			
			namedJdbcTemplate.update(sql.toString(), namedParameters);
			
		} catch (Exception e) {
			LOG.error("Exception while trying to deleteAdsOnLayer FeatureForLayerDto", e);
			throw new DaoException("DaoException while trying to deleteAdsOnLayer FeatureForLayerDto", e);
		}
	}
		

	public AdsForLayerDto findAdsById(String idAds) throws DaoException {
		LOG.info("[LayerDAOImpl::findAdsById] BEGIN");
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT "); 
			sql.append("idgeo_pt_ads,");
			sql.append("fk_comune,");
			sql.append("fk_soggetto,");
			sql.append("to_char(data_ril,'DD/MM/YYYY') as data_ril,");
			sql.append("codice_ads,");
			sql.append("fk_settore,");
			sql.append("fk_proprieta,");
			sql.append("fk_tipo_ads,");
			sql.append("fk_assetto,");
			sql.append("fk_esposizione,");
			sql.append("fk_comunita_montana,");
			sql.append("fk_tipo_forestale,");
			sql.append("fk_destinazione,");
			sql.append("fk_priorita,");
			sql.append("flg_dia,");
			sql.append("quota,");
			sql.append("inclinazione,");
			sql.append("to_char(data_inizio_validita,'DD/MM/YYYY') as data_inizio_validita,");
			sql.append("to_char(data_fine_validita,'DD/MM/YYYY') as data_fine_validita,");
			sql.append("note,");
			sql.append("fk_ambito,");
			sql.append("idgeo_pl_particella_forest,");
			sql.append("fk_danno,");
			sql.append("fk_tipo_intervento,");
			sql.append("geometria,");
			sql.append("fk_stato_ads,");
			sql.append("ambito_specifico ");
			sql.append("FROM ");
			sql.append("idf_geo_pt_area_di_saggio ");  
			sql.append("WHERE idgeo_pt_ads = :idgeo_pt_ads"); 
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("idgeo_pt_ads", ConvertUtil.convertToLong(idAds));
			
			
			
			
			
			List<AdsForLayerDto> list = namedJdbcTemplate.query(sql.toString(), paramMap, new AdsRowMapper());
			if(!list.isEmpty()) {
				return list.get(0);
			}
			return null;
		}
		catch (Exception e) {
			throw new DaoException("Errore sql :"+e.getMessage());
		}
		finally {
			LOG.debug("[LayerDAOImpl::findAdsById] END");
		}
	}
	
	@Override
	public Long insertTrasfomazioneOnLayer(TrasformazioneForLayerDto dto) throws DaoException {
		LOG.info("[LayerDAOImpl::insertTrasfomazioneOnLayer] BEGIN");
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO "); 
			sql.append("idf_geo_pl_interv_trasf "); 
			sql.append("("); 
			sql.append("fk_intervento, ");
			sql.append("data_inserimento, ");
			sql.append("geometria"); 
			sql.append(") "); 
			sql.append("VALUES ("); 
			sql.append(":fk_intervento,"); 
			sql.append("now(),");
			sql.append("st_geomfromgeojson(:geometria) "); 
			sql.append(")");
			
			LOG.info("QUERY *"+sql+"*");
			
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("fk_intervento", dto.getFkIntervento());
			String geo = GeoJSONGeometryConverter.convertJTSGeometryToGeoJSON(dto.getGeometry(),Constants.GEECO_EPSG_PIEMONTE);
			
			namedParameters.addValue("geometria", geo,Types.VARCHAR);
			
			KeyHolder keyHolder = new GeneratedKeyHolder();
			
			namedJdbcTemplate.update(sql.toString(), namedParameters, keyHolder, new String[] {"idgeo_pl_interv_trasf"});
			
			long newPk = keyHolder.getKey().longValue();
			
			
			
			return newPk;
		} catch (Exception e) {
			LOG.error("Exception while trying to insert FeatureForLayerDto", e);
			throw new DaoException("DaoException while trying to insert FeatureForLayerDto", e);
		}
	}
	
	
	public TrasformazioneForLayerDto findTrasformazioneById(Long id) throws DaoException{
		LOG.info("[LayerDAOImpl::findTrasformazioneById] BEGIN");
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT "); 
			sql.append("idgeo_pl_interv_trasf,");
			sql.append("fk_intervento,");
			//sql.append("st_area(geometria) as superficie,");
			sql.append("round(st_area(geometria)::numeric,4) as superficie, ");
			sql.append("geometria ");
			sql.append("FROM idf_geo_pl_interv_trasf "); 
			sql.append("WHERE idgeo_pl_interv_trasf = :idgeo_pl_interv_trasf");
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("idgeo_pl_interv_trasf", id);
			
			List<TrasformazioneForLayerDto> list = namedJdbcTemplate.query(sql.toString(), paramMap, new TrasformazioniRowMapper());
			if(!list.isEmpty()) {
				return list.get(0);
			}
			return null;
		}
		catch (Exception e) {
			throw new DaoException("Errore sql :"+e.getMessage());
		}
		finally {
			LOG.debug("[LayerDAOImpl::findTrasformazioneById] END");
		}
	}
	
	@Override
	public void updateTrasformazioneOnLayer(TrasformazioneForLayerDto dto) throws DaoException {
		LOG.info("[LayerDAOImpl::updateTrasformazioneOnLayer] BEGIN");
		try {
			final StringBuilder sql = new StringBuilder();
			sql.append("UPDATE idf_geo_pl_interv_trasf ");
			sql.append("SET geometria = st_geomfromgeojson(:geometria) ");
			sql.append("WHERE idgeo_pl_interv_trasf = :idgeo_pl_interv_trasf");
			
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			
			
			
			String geo = GeoJSONGeometryConverter.convertJTSGeometryToGeoJSON(dto.getGeometry(),Constants.GEECO_EPSG_PIEMONTE);
			
			
			
			namedParameters.addValue("geometria", geo, Types.VARCHAR);
			namedParameters.addValue("idgeo_pl_interv_trasf", dto.getIdFeature());
			
			namedJdbcTemplate.update(sql.toString(), namedParameters);
			
		} catch (Exception e) {
			LOG.error("Exception while trying to updateTrasformazioneOnLayer FeatureForLayerDto", e);
			throw new DaoException("DaoException while trying to updateAdsOnLayer FeatureForLayerDto", e);
		}
		finally {
			LOG.info("[LayerDAOImpl::updateTrasformazioneOnLayer] END");
		}
	}
	
	
	@Override
	public long insertEventoPtOnLayer(EventoPtForLayerDto dto) throws DaoException {
		LOG.info("[LayerDAOImpl::insertEventoPtOnLayer] BEGIN");
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO "); 
			sql.append("idf_geo_pt_evento "); 
			sql.append("("); 
			sql.append("data_inserimento,");
			sql.append("fk_evento, ");
			sql.append("geometria, ");
			sql.append("descrizione ");
			sql.append(") "); 
			sql.append("VALUES ("); 
			sql.append("now(),"); 
			sql.append(":fk_evento,");
			sql.append("st_geometryn(st_geomfromgeojson(:geometria),1), ");
			sql.append(":descrizione");
			sql.append(")");
			
			LOG.info("QUERY *"+sql+"*");
			
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("fk_evento", dto.getFkEvento());
			String geo = GeoJSONGeometryConverter.convertJTSGeometryToGeoJSON(dto.getGeometry(),Constants.GEECO_EPSG_PIEMONTE);
			
			namedParameters.addValue("geometria", geo,Types.VARCHAR);
			namedParameters.addValue("descrizione", dto.getDescrizione());
			
			KeyHolder keyHolder = new GeneratedKeyHolder();
			
			namedJdbcTemplate.update(sql.toString(), namedParameters, keyHolder, new String[] {"idgeo_pt_evento"});
			
			long newPk = keyHolder.getKey().longValue();
			
			LOG.info("---> NEW KEY PT --> *"+newPk+"*");
			
//			return GeoJSONFeatureFactory.create(newPk, dto);
			return newPk;
		} catch (Exception e) {
			LOG.error("Exception while trying to insert insertEventoPtOnLayer", e);
			throw new DaoException("DaoException while trying to insert insertEventoPtOnLayer", e);
		}
		finally {
			LOG.info("[LayerDAOImpl::insertEventoPtOnLayer] END");
		}
	}
	
	@Override
	public long insertEventoPlOnLayer(EventoPlForLayerDto dto) throws DaoException {
		LOG.info("[LayerDAOImpl::insertEventoPlOnLayer] BEGIN");
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO "); 
			sql.append("idf_geo_pl_evento "); 
			sql.append("("); 
			sql.append("data_inserimento,");
			sql.append("fk_evento, ");
			sql.append("geometria, ");
			sql.append("superficie_ha, ");
			sql.append("descrizione ");
			sql.append(") "); 
			sql.append("VALUES ("); 
			sql.append("now(), "); 
			sql.append(":fk_evento, ");
			sql.append("st_geomfromgeojson(:geometria), ");
			sql.append(":superficie_ha, ");
			sql.append(":descrizione");
			sql.append(")");
			
			LOG.info("QUERY *"+sql+"*");
			
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("fk_evento", dto.getFkEvento());
			namedParameters.addValue("superficie_ha", dto.getSuperficieHa());
			namedParameters.addValue("descrizione", dto.getDescrizione());
			
			String geo = GeoJSONGeometryConverter.convertJTSGeometryToGeoJSON(dto.getGeometry(),Constants.GEECO_EPSG_PIEMONTE);
			namedParameters.addValue("geometria", geo,Types.VARCHAR);
			
			KeyHolder keyHolder = new GeneratedKeyHolder();
			
			namedJdbcTemplate.update(sql.toString(), namedParameters, keyHolder, new String[] {"idgeo_pl_evento"});
			
			long newPk = keyHolder.getKey().longValue();
			
			LOG.info("---> NEW KEY PL --> *"+newPk+"*");
			
			//return GeoJSONFeatureFactory.create(newPk, dto);
			return newPk;
		} catch (Exception e) {
			LOG.error("Exception while trying to insert insertEventoPlOnLayer", e);
			throw new DaoException("DaoException while trying to insert insertEventoPlOnLayer", e);
		}
		finally {
			LOG.info("[LayerDAOImpl::insertEventoPlOnLayer] END");
		}
	}
	
	@Override
	public long insertEventoLnOnLayer(EventoLnForLayerDto dto) throws DaoException {
		LOG.info("[LayerDAOImpl::insertEventoLnOnLayer] BEGIN");
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO "); 
			sql.append("idf_geo_ln_evento "); 
			sql.append("("); 
			sql.append("data_inserimento,");
			sql.append("fk_evento, ");
			sql.append("geometria, ");
			sql.append("lunghezza_metri, ");
			sql.append("descrizione ");
			sql.append(") "); 
			sql.append("VALUES ("); 
			sql.append("now(), "); 
			sql.append(":fk_evento, ");
			sql.append("st_geomfromgeojson(:geometria), ");
			sql.append(":lunghezza_metri, ");
			sql.append(":descrizione");
			sql.append(")");
			
			LOG.info("QUERY *"+sql+"*");
			
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("fk_evento", dto.getFkEvento());
			namedParameters.addValue("lunghezza_metri", dto.getLunghezzaMetri());
			namedParameters.addValue("descrizione", dto.getDescrizione());
			
			String geo = GeoJSONGeometryConverter.convertJTSGeometryToGeoJSON(dto.getGeometry(),Constants.GEECO_EPSG_PIEMONTE);
			namedParameters.addValue("geometria", geo,Types.VARCHAR);
			
			KeyHolder keyHolder = new GeneratedKeyHolder();
			
			namedJdbcTemplate.update(sql.toString(), namedParameters, keyHolder, new String[] {"idgeo_ln_evento"});
			
			long newPk = keyHolder.getKey().longValue();
			
			LOG.info("---> NEW KEY LN --> *"+newPk+"*");
			
			//return GeoJSONFeatureFactory.create(newPk, dto);
			return newPk;
		} catch (Exception e) {
			LOG.error("Exception while trying to insert insertEventoPtOnLayer", e);
			throw new DaoException("DaoException while trying to insert insertEventoLnOnLayer", e);
		}
		finally {
			LOG.info("[LayerDAOImpl::insertEventoLnOnLayer] END");
		}
	}

	@Override
	public void updateEventoLnOnLayer(PfaForLayerDto dto) throws DaoException {
		LOG.info("[LayerDAOImpl::updateEventoLnOnLayer] BEGIN");
		try {
			final StringBuilder sql = new StringBuilder();
			sql.append("UPDATE idf_geo_ln_evento ");
			sql.append("SET geometria = st_geomfromgeojson(:geometria) ");
			sql.append("WHERE idgeo_ln_evento = :idgeo_ln_evento");
			
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			
			
			
			String geo = GeoJSONGeometryConverter.convertJTSGeometryToGeoJSON(dto.getGeometry(),Constants.GEECO_EPSG_PIEMONTE);
			
			
			
			namedParameters.addValue("geometria", geo, Types.VARCHAR);
			namedParameters.addValue("idgeo_ln_evento", dto.getIdFeature());
			
			namedJdbcTemplate.update(sql.toString(), namedParameters);
			
		} catch (Exception e) {
			LOG.error("Exception while trying to updateEventoLnOnLayer", e);
			throw new DaoException("DaoException while trying to updateEventoLnOnLayer", e);
		}
		finally {
			LOG.info("[LayerDAOImpl::updateEventoLnOnLayer] END");
		}
	}
	
	@Override
	public void updateEventoPtOnLayer(PfaForLayerDto dto) throws DaoException {
		LOG.info("[LayerDAOImpl::updateEventoPtOnLayer] BEGIN");
		try {
			final StringBuilder sql = new StringBuilder();
			sql.append("UPDATE idf_geo_pt_evento ");
			sql.append("SET geometria = st_geometryn(st_geomfromgeojson(:geometria),1) ");
			sql.append("WHERE idgeo_pt_evento = :idgeo_pt_evento");
			
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			
			
			
			String geo = GeoJSONGeometryConverter.convertJTSGeometryToGeoJSON(dto.getGeometry(),Constants.GEECO_EPSG_PIEMONTE);
			
			
			
			namedParameters.addValue("geometria", geo, Types.VARCHAR);
			namedParameters.addValue("idgeo_pt_evento", dto.getIdFeature());
			
			namedJdbcTemplate.update(sql.toString(), namedParameters);
			
		} catch (Exception e) {
			LOG.error("Exception while trying to updateEventoPtOnLayer FeatureForLayerDto", e);
			throw new DaoException("DaoException while trying to updateEventoPtOnLayer FeatureForLayerDto", e);
		}
		finally {
			LOG.info("[LayerDAOImpl::updateEventoPtOnLayer] END");
		}
	}
	
	@Override
	public void updateEventoPlOnLayer(PfaForLayerDto dto) throws DaoException {
		LOG.info("[LayerDAOImpl::updateEventoPlOnLayer] BEGIN");
		try {
			final StringBuilder sql = new StringBuilder();
			sql.append("UPDATE idf_geo_pl_evento ");
			sql.append("SET geometria = st_geomfromgeojson(:geometria) ");
			sql.append("WHERE idgeo_pl_evento = :idgeo_pl_evento");
			
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			
			
			
			String geo = GeoJSONGeometryConverter.convertJTSGeometryToGeoJSON(dto.getGeometry(),Constants.GEECO_EPSG_PIEMONTE);
			
			
			
			namedParameters.addValue("geometria", geo, Types.VARCHAR);
			namedParameters.addValue("idgeo_pl_evento", dto.getIdFeature());
			
			namedJdbcTemplate.update(sql.toString(), namedParameters);
			
		} catch (Exception e) {
			LOG.error("Exception while trying to updateEventoPlOnLayer FeatureForLayerDto", e);
			throw new DaoException("DaoException while trying to updateEventoPlOnLayer FeatureForLayerDto", e);
		}
		finally {
			LOG.info("[LayerDAOImpl::updateEventoPlOnLayer] END");
		}
	}
	
	
	public EventoForLayerDto findEventoById(String idEvento, String idLayer) throws DaoException{
		LOG.info("[LayerDAOImpl::findEventoById] BEGIN");
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT "); 
			sql.append("e.id_evento, te.id, te.tipo, te.descrizione, e.progressivo_evento_pfa, ");
			sql.append("te.geometria as geometria, pfa.idgeo_pl_pfa, pfa.denominazione ");
			sql.append("FROM (");
			sql.append("select idgeo_pl_evento id, 'PL' tipo, geometria, fk_evento, descrizione "); 
			sql.append("from idf_geo_pl_evento "); 
			sql.append("union "); 
			sql.append("select idgeo_pt_evento id, 'PT' tipo, st_multi(geometria), fk_evento, descrizione "); 
			sql.append("from idf_geo_pt_evento "); 
			sql.append("union "); 
			sql.append("select idgeo_ln_evento id, 'LN' tipo, geometria, fk_evento, descrizione "); 
			sql.append("from idf_geo_ln_evento) te, "); 
			sql.append("idf_t_evento e, "); 
			sql.append("idf_r_evento_partfor ep, "); 
			sql.append("idf_geo_pl_particella_forest pf, "); 
			sql.append("idf_geo_pl_pfa pfa, "); 
			sql.append("idf_asr_t_geeco_layer l "); 
			sql.append("WHERE e.id_evento = te.fk_evento "); 
			sql.append("and e.id_evento = ep.id_evento "); 
			sql.append("and ep.idgeo_pl_particella_forest = pf.idgeo_pl_particella_forest "); 
			sql.append("and pf.idgeo_pl_pfa = pfa.idgeo_pl_pfa "); 
			sql.append("and l.flg_tipo_layer  = te.tipo ");
			sql.append("and e.id_evento = :id_evento ");
			sql.append("and l.id_geeco_layer = '");
			sql.append(idLayer);
			sql.append("'");
			
			LOG.info("Query findEvento -------> ");
			LOG.info(sql);
			
			MapSqlParameterSource namedParameters = new MapSqlParameterSource("id_evento", idEvento);
			//namedParameters.addValue("id_geeco_layer", idLayer, Types.VARCHAR);
			
//			paramMap.put("id_evento", idEvento);
//			paramMap.put("id_geeco_layer", idLayer);
//			
			List<EventoForLayerDto> list = namedJdbcTemplate.query(sql.toString(), namedParameters, new EventoRowMapper());
			if(!list.isEmpty()) {
				return list.get(0);
			}
			return null;
		}
		catch (Exception e) {
			throw new DaoException("Errore sql :"+e.getMessage());
		}
		finally {
			LOG.debug("[LayerDAOImpl::findEventoById] END");
		}
	}
	
	
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long insertInterventoLnOnLayer(InterventoLnForLayerDto dto) throws DaoException {
		LOG.info("[LayerDAOImpl::insertInterventoLnOnLayer] BEGIN");
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO "); 
			sql.append("idf_geo_ln_lotto_intervento "); 
			sql.append("("); 
			sql.append("data_inserimento,");
			sql.append("id_intervento, ");
			sql.append("geometria, ");
			sql.append("lunghezza_metri, ");
			sql.append("descrizione ");
			sql.append(") "); 
			sql.append("VALUES ("); 
			sql.append("now(), "); 
			sql.append(":id_intervento, ");
			sql.append("st_geomfromgeojson(:geometria), ");
			sql.append(":lunghezza_metri, ");
			sql.append(":descrizione");
			sql.append(")");
			
			LOG.info("QUERY *"+sql+"*");
			
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("id_intervento", dto.getFkIntervento());
			namedParameters.addValue("lunghezza_metri", dto.getLunghezzaMetri());
			namedParameters.addValue("descrizione", dto.getDescrizione());
			
			String geo = GeoJSONGeometryConverter.convertJTSGeometryToGeoJSON(dto.getGeometry(),Constants.GEECO_EPSG_PIEMONTE);
			namedParameters.addValue("geometria", geo,Types.VARCHAR);
			
			KeyHolder keyHolder = new GeneratedKeyHolder();
			
			namedJdbcTemplate.update(sql.toString(), namedParameters, keyHolder, new String[] {"idgeo_ln_lotto_intervento"});
			
			long newPk = keyHolder.getKey().longValue();
			
			LOG.info("---> NEW KEY LN --> *"+newPk+"*");
			
			//return GeoJSONFeatureFactory.create(newPk, dto);
			return newPk;
		} catch (Exception e) {
			LOG.error("Exception while trying to insert insertInterventoLnOnLayer", e);
			throw new DaoException("DaoException while trying to insert insertInterventoLnOnLayer", e);
		}
		finally {
			LOG.info("[LayerDAOImpl::insertInterventoLnOnLayer] END");
		}
	}

	@Override
	public long insertInterventoPlOnLayer(InterventoPlForLayerDto dto) throws DaoException {
		LOG.info("[LayerDAOImpl::insertInterventoPlOnLayer] BEGIN");
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO "); 
			sql.append("idf_geo_pl_lotto_intervento "); 
			sql.append("("); 
			sql.append("data_inserimento,");
			sql.append("fk_intervento, ");
			sql.append("geometria, ");
			sql.append("sup_tagliata_ha, ");
			sql.append("descrizione ");
			sql.append(") ");  
			sql.append("VALUES ("); 
			sql.append("now(), "); 
			sql.append(":fk_intervento, ");
			sql.append("st_geomfromgeojson(:geometria), ");
			sql.append(":sup_tagliata_ha, ");
			sql.append(":descrizione");
			sql.append(")");
			
			LOG.info("QUERY *"+sql+"*");
			
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("fk_intervento", dto.getFkIntervento());
			namedParameters.addValue("sup_tagliata_ha", dto.getSuperficieTagliataHa());
			namedParameters.addValue("descrizione", dto.getDescrizione());
			
			String geo = GeoJSONGeometryConverter.convertJTSGeometryToGeoJSON(dto.getGeometry(),Constants.GEECO_EPSG_PIEMONTE);
			namedParameters.addValue("geometria", geo,Types.VARCHAR);
			
			KeyHolder keyHolder = new GeneratedKeyHolder();
			
			namedJdbcTemplate.update(sql.toString(), namedParameters, keyHolder, new String[] {"idgeo_pl_lotto_intervento"});
			
			long newPk = keyHolder.getKey().longValue();
			
			LOG.info("---> NEW KEY PL --> *"+newPk+"*");
			
			//return GeoJSONFeatureFactory.create(newPk, dto);
			return newPk;
		} catch (Exception e) {
			LOG.error("Exception while trying to insert insertInterventoPlOnLayer", e);
			throw new DaoException("DaoException while trying to insert insertInterventoPlOnLayer", e);
		}
		finally {
			LOG.info("[LayerDAOImpl::insertInterventoPlOnLayer] END");
		}
	}

	@Override
	public long insertInterventoPtOnLayer(InterventoPtForLayerDto dto) throws DaoException {
		LOG.info("[LayerDAOImpl::insertInterventoPtOnLayer] BEGIN");
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO "); 
			sql.append("idf_geo_pt_lotto_intervento "); 
			sql.append("("); 
			sql.append("data_inserimento,");
			sql.append("id_intervento, ");
			sql.append("geometria, ");
			sql.append("descrizione ");
			sql.append(") "); 
			sql.append("VALUES ("); 
			sql.append("now(),"); 
			sql.append(":id_intervento,");
			sql.append("st_geometryn(st_geomfromgeojson(:geometria),1), ");
			sql.append(":descrizione");
			sql.append(")");
			
			LOG.info("QUERY *"+sql+"*");
			
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("id_intervento", dto.getFkIntervento());
			String geo = GeoJSONGeometryConverter.convertJTSGeometryToGeoJSON(dto.getGeometry(),Constants.GEECO_EPSG_PIEMONTE);
			
			namedParameters.addValue("geometria", geo,Types.VARCHAR);
			namedParameters.addValue("descrizione", dto.getDescrizione());
			
			KeyHolder keyHolder = new GeneratedKeyHolder();
			
			namedJdbcTemplate.update(sql.toString(), namedParameters, keyHolder, new String[] {"idgeo_pt_lotto_intervento"});
			
			long newPk = keyHolder.getKey().longValue();
			
			LOG.info("---> NEW KEY PT --> *"+newPk+"*");
			
//			return GeoJSONFeatureFactory.create(newPk, dto);
			return newPk;
		} catch (Exception e) {
			LOG.error("Exception while trying to insert insertInterventoPtOnLayer", e);
			throw new DaoException("DaoException while trying to insert insertInterventoPtOnLayer", e);
		}
		finally {
			LOG.info("[LayerDAOImpl::insertInterventoPtOnLayer] END");
		}
	}

	@Override
	public InterventoForLayerDto findInterventoById(String idIntervento, String idLayer) throws DaoException {
		LOG.info("[LayerDAOImpl::findEventoById] BEGIN");
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT "); 
			sql.append("i.id_intervento, ti.id, ti.tipo, ti.descrizione, i.nr_progressivo_interv , ST_AsText(ti.geometria) as geometria, pfa.idgeo_pl_pfa, pfa.denominazione");
			sql.append("FROM (");
			sql.append("select idgeo_pl_lotto_intervento id, 'PL' tipo, geometria, fk_intervento as id_intervento, descrizione "); 
			sql.append("from idf_geo_pl_lotto_intervento "); 
			sql.append("union "); 
			sql.append("select idgeo_pt_lotto_intervento id, 'PT' tipo, st_multi(geometria), id_intervento, descrizione "); 
			sql.append("from idf_geo_pt_lotto_intervento "); 
			sql.append("union "); 
			sql.append("select idgeo_ln_lotto_intervento id, 'LN' tipo, geometria, id_intervento, descrizione "); 
			sql.append("from idf_geo_ln_lotto_intervento) ti, "); 
			sql.append("idf_t_interv_selvicolturale i, "); 
			sql.append("idf_r_evento_partfor ep, "); 
			sql.append("idf_geo_pl_pfa pfa, "); 
			sql.append("idf_asr_t_geeco_layer l "); 
			sql.append("WHERE i.id_intervento = ti.id_intervento "); 
			sql.append("and i.idgeo_pl_pfa = pfa.idgeo_pl_pfa "); 
			sql.append("and l.flg_tipo_layer  = ti.tipo ");
			sql.append("and i.id_intervento = :id_intervento ");
//			sql.append("and l.id_geeco_layer = :id_geeco_layer");
			sql.append("and l.id_geeco_layer = '");
			sql.append(idLayer);
			sql.append("'");
			
			LOG.info("Query findEvento -------> ");
			LOG.info(sql);
			
			MapSqlParameterSource namedParameters = new MapSqlParameterSource("id_intervento", idIntervento);
//			namedParameters.addValue("id_geeco_layer", idLayer, Types.VARCHAR);
			
//			paramMap.put("id_evento", idEvento);
//			paramMap.put("id_geeco_layer", idLayer);
//			
			List<InterventoForLayerDto> list = namedJdbcTemplate.query(sql.toString(), namedParameters, new InterventoRowMapper());
			if(!list.isEmpty()) {
				return list.get(0);
			}
			return null;
		}
		catch (Exception e) {
			throw new DaoException("Errore sql :"+e.getMessage());
		}
		finally {
			LOG.debug("[LayerDAOImpl::findEventoById] END");
		}
	}

	
	@Override
	public void updateInterventoLnOnLayer(PfaForLayerDto dto) throws DaoException {
		LOG.info("[LayerDAOImpl::updateInterventoLnOnLayer] BEGIN");
		try {
			final StringBuilder sql = new StringBuilder();
			sql.append("UPDATE idf_geo_ln_lotto_intervento ");
			sql.append("SET geometria = st_geomfromgeojson(:geometria) ");
			sql.append("WHERE idgeo_ln_lotto_intervento = :idgeo_ln_lotto_intervento");
			
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			
			
			
			String geo = GeoJSONGeometryConverter.convertJTSGeometryToGeoJSON(dto.getGeometry(),Constants.GEECO_EPSG_PIEMONTE);
			
			
			
			namedParameters.addValue("geometria", geo, Types.VARCHAR);
			namedParameters.addValue("idgeo_ln_lotto_intervento", dto.getIdFeature());
			
			namedJdbcTemplate.update(sql.toString(), namedParameters);
			
		} catch (Exception e) {
			LOG.error("Exception while trying to updateInterventoLnOnLayer", e);
			throw new DaoException("DaoException while trying to updateInterventoLnOnLayer", e);
		}
		finally {
			LOG.info("[LayerDAOImpl::updateInterventoLnOnLayer] END");
		}
	}
	
	@Override
	public void updateInterventoPtOnLayer(PfaForLayerDto dto) throws DaoException {
		LOG.info("[LayerDAOImpl::updateInterventoPtOnLayer] BEGIN");
		try {
			final StringBuilder sql = new StringBuilder();
			sql.append("UPDATE idf_geo_pt_lotto_intervento ");
			sql.append("SET geometria = st_geometryn(st_geomfromgeojson(:geometria),1) ");
			sql.append("WHERE idgeo_pt_lotto_intervento = :idgeo_pt_lotto_intervento");
			
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			
			
			
			String geo = GeoJSONGeometryConverter.convertJTSGeometryToGeoJSON(dto.getGeometry(),Constants.GEECO_EPSG_PIEMONTE);
			
			
			
			namedParameters.addValue("geometria", geo, Types.VARCHAR);
			namedParameters.addValue("idgeo_pt_lotto_intervento", dto.getIdFeature());
			
			namedJdbcTemplate.update(sql.toString(), namedParameters);
			
		} catch (Exception e) {
			LOG.error("Exception while trying to updateInterventoPtOnLayer FeatureForLayerDto", e);
			throw new DaoException("DaoException while trying to updateInterventoPtOnLayer FeatureForLayerDto", e);
		}
		finally {
			LOG.info("[LayerDAOImpl::updateInterventoPtOnLayer] END");
		}
	}
	
	@Override
	public void updateInterventoPlOnLayer(PfaForLayerDto dto) throws DaoException {
		LOG.info("[LayerDAOImpl::updateInterventoPlOnLayer] BEGIN");
		try {
			final StringBuilder sql = new StringBuilder();
			sql.append("UPDATE idf_geo_pl_lotto_intervento ");
			sql.append("SET geometria = st_geomfromgeojson(:geometria) ");
			sql.append("WHERE idgeo_pl_lotto_intervento = :idgeo_pl_lotto_intervento");
			
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			
			
			
			String geo = GeoJSONGeometryConverter.convertJTSGeometryToGeoJSON(dto.getGeometry(),Constants.GEECO_EPSG_PIEMONTE);
			
			
			
			namedParameters.addValue("geometria", geo, Types.VARCHAR);
			namedParameters.addValue("idgeo_pl_lotto_intervento", dto.getIdFeature());
			
			namedJdbcTemplate.update(sql.toString(), namedParameters);
			
		} catch (Exception e) {
			LOG.error("Exception while trying to updateInterventoPlOnLayer FeatureForLayerDto", e);
			throw new DaoException("DaoException while trying to updateInterventoPlOnLayer FeatureForLayerDto", e);
		}
		finally {
			LOG.info("[LayerDAOImpl::updateInterventoPlOnLayer] END");
		}
	}
	
	@Override
	public Long insertVincoloOnLayer(VincoloForLayerDto dto) throws DaoException {
		LOG.info("[LayerDAOImpl::insertVincoloOnLayer] BEGIN");
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO "); 
			sql.append("idf_geo_pl_interv_vincidro "); 
			sql.append("("); 
			sql.append("fk_intervento, ");
			sql.append("data_inserimento, ");
			sql.append("geometria"); 
			sql.append(") "); 
			sql.append("VALUES ("); 
			sql.append(":fk_intervento,"); 
			sql.append("now(),");
			sql.append("st_geomfromgeojson(:geometria) "); 
			sql.append(")");
			
			LOG.info("QUERY *"+sql+"*");
			
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("fk_intervento", dto.getFkIntervento());
			String geo = GeoJSONGeometryConverter.convertJTSGeometryToGeoJSON(dto.getGeometry(),Constants.GEECO_EPSG_PIEMONTE);
			
			namedParameters.addValue("geometria", geo,Types.VARCHAR);
			
			KeyHolder keyHolder = new GeneratedKeyHolder();
			
			namedJdbcTemplate.update(sql.toString(), namedParameters, keyHolder, new String[] {"id_geo_pl_interv_vincidro"});
			
			long newPk = keyHolder.getKey().longValue();
			
			
			
			return newPk;
		} catch (Exception e) {
			LOG.error("Exception while trying to insert Vincolo On Layer", e);
			throw new DaoException("DaoException while trying to insertVincoloOnLayer", e);
		}
	}
	
	@Override
	public VincoloForLayerDto findVincoloById(Long id) throws DaoException{
		LOG.info("[LayerDAOImpl::findVincoloById] BEGIN");
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT "); 
			sql.append("id_geo_pl_interv_vincidro,");
			sql.append("fk_intervento,");
			sql.append("round(st_area(geometria)::numeric,4) as superficie, ");
			sql.append("geometria ");
			sql.append("FROM idf_geo_pl_interv_vincidro "); 
			sql.append("WHERE id_geo_pl_interv_vincidro = :id_geo_pl_interv_vincidro");
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id_geo_pl_interv_vincidro", id);
			
			List<VincoloForLayerDto> list = namedJdbcTemplate.query(sql.toString(), paramMap, new VincoloRowMapper());
			if(!list.isEmpty()) {
				return list.get(0);
			}
			return null;
		}
		catch (Exception e) {
			throw new DaoException("Errore sql :"+e.getMessage());
		}
		finally {
			LOG.debug("[LayerDAOImpl::findVincoloById] END");
		}
	}
	
	@Override
	public void updateVincoloOnLayer(VincoloForLayerDto dto) throws DaoException {
		LOG.info("[LayerDAOImpl::] BEGIN");
		try {
			final StringBuilder sql = new StringBuilder();
			sql.append("UPDATE idf_geo_pl_interv_vincidro ");
			sql.append("SET geometria = st_geomfromgeojson(:geometria) ");
			sql.append("WHERE id_geo_pl_interv_vincidro = :id_geo_pl_interv_vincidro");
			
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			
			String geo = GeoJSONGeometryConverter.convertJTSGeometryToGeoJSON(dto.getGeometry(),Constants.GEECO_EPSG_PIEMONTE);
			
			namedParameters.addValue("geometria", geo, Types.VARCHAR);
			namedParameters.addValue("id_geo_pl_interv_vincidro", dto.getIdFeature());
			
			namedJdbcTemplate.update(sql.toString(), namedParameters);
			
		} catch (Exception e) {
			LOG.error("Exception while trying to updateVincoloOnLayer FeatureForLayerDto", e);
			throw new DaoException("DaoException while trying to updateVincoloOnLayer FeatureForLayerDto", e);
		}
		finally {
			LOG.info("[LayerDAOImpl::updateVincoloOnLayer] END");
		}
	}
	
	@Override
	public void updateTaglioOnLayer(TaglioForLayerDto dto) throws DaoException {
		LOG.info("[LayerDAOImpl::] BEGIN");
		try {
			final StringBuilder sql = new StringBuilder();
			sql.append("UPDATE idf_geo_pl_lotto_intervento ");
			sql.append("SET geometria = st_geomfromgeojson(:geometria) ");
			sql.append("WHERE idgeo_pl_lotto_intervento = :idgeo_pl_lotto_intervento");
			
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			
			String geo = GeoJSONGeometryConverter.convertJTSGeometryToGeoJSON(dto.getGeometry(),Constants.GEECO_EPSG_PIEMONTE);
			
			namedParameters.addValue("geometria", geo, Types.VARCHAR);
			namedParameters.addValue("idgeo_pl_lotto_intervento", dto.getIdFeature());
			
			namedJdbcTemplate.update(sql.toString(), namedParameters);
			
		} catch (Exception e) {
			LOG.error("Exception while trying to updateTagliOnLayer FeatureForLayerDto", e);
			throw new DaoException("DaoException while trying to updateTagliOnLayer FeatureForLayerDto", e);
		}
		finally {
			LOG.info("[LayerDAOImpl::updateTagliOnLayer] END");
		}
	}

	@Override
	public Long insertTaglioOnLayer(TaglioForLayerDto dto) throws DaoException {
		LOG.info("[LayerDAOImpl::insertTaglioOnLayer] BEGIN");
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO "); 
			sql.append("idf_geo_pl_lotto_intervento "); 
			sql.append("("); 
			sql.append("data_inserimento,");
			sql.append("fk_intervento, ");
			sql.append("geometria");
			sql.append(") ");  
			sql.append("VALUES ("); 
			sql.append("now(), "); 
			sql.append(":fk_intervento, ");
			sql.append("st_geomfromgeojson(:geometria)");
			sql.append(")");
			
			LOG.info("QUERY *"+sql+"*");
			
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("fk_intervento", dto.getFkIntervento());
			
			String geo = GeoJSONGeometryConverter.convertJTSGeometryToGeoJSON(dto.getGeometry(),Constants.GEECO_EPSG_PIEMONTE);
			namedParameters.addValue("geometria", geo,Types.VARCHAR);
			
			KeyHolder keyHolder = new GeneratedKeyHolder();
			
			namedJdbcTemplate.update(sql.toString(), namedParameters, keyHolder, new String[] {"idgeo_pl_lotto_intervento"});
			
			long newPk = keyHolder.getKey().longValue();
			
			LOG.info("---> NEW KEY PL --> *"+newPk+"*");
			return newPk;
		} catch (Exception e) {
			LOG.error("Exception while trying to insertTaglioOnLayer", e);
			throw new DaoException("DaoException while trying to insertTaglioOnLayer", e);
		}
	}

	@Override
	public TaglioForLayerDto findTaglioById(Long id) throws DaoException {
		LOG.info("[LayerDAOImpl::findTaglioById] BEGIN");
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT "); 
			sql.append("idgeo_pl_lotto_intervento,");
			sql.append("fk_intervento,");
			//sql.append("st_area(geometria) as superficie,");
			sql.append("round(st_area(geometria)::numeric,4) as superficie, ");
			sql.append("geometria ");
			sql.append("FROM idf_geo_pl_lotto_intervento "); 
			sql.append("WHERE idgeo_pl_lotto_intervento = :idgeo_pl_lotto_intervento");
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("idgeo_pl_lotto_intervento", id);
			
			List<TaglioForLayerDto> list = namedJdbcTemplate.query(sql.toString(), paramMap, new TaglioRowMapper());
			if(!list.isEmpty()) {
				return list.get(0);
			}
			return null;
		}
		catch (Exception e) {
			throw new DaoException("Errore sql :"+e.getMessage());
		}
		finally {
			LOG.debug("[LayerDAOImpl::findTaglioById] END");
		}
	}

	@Override
	public void deleteTrasformazioneOnLayer(Long featureId) throws DaoException {
		LOG.info("[LayerDAOImpl::deleteTrasformazioneOnLayer] BEGIN");
		try {
			final StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM idf_geo_pl_interv_trasf ");
			sql.append("WHERE idgeo_pl_interv_trasf = :idgeo_pl_interv_trasf");
			
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("idgeo_pl_interv_trasf", featureId);
			
			namedJdbcTemplate.update(sql.toString(), namedParameters);
			
		} catch (Exception e) {
			LOG.error("Exception while trying to deleteTrasformazioneOnLayer FeatureForLayerDto", e);
			throw new DaoException("DaoException while trying to deleteTrasformazioneOnLayer FeatureForLayerDto", e);
		}
		finally {
			LOG.info("[LayerDAOImpl::deleteTrasformazioneOnLayer] END");
		}
	}

	@Override
	public void deleteVincoloOnLayer(Long featureId) throws DaoException {
		LOG.info("[LayerDAOImpl::deleteVincoloOnLayer] BEGIN");
		try {
			final StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM idf_geo_pl_interv_vincidro ");
			sql.append("WHERE id_geo_pl_interv_vincidro = :id_geo_pl_interv_vincidro");
			
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("id_geo_pl_interv_vincidro", featureId);
			
			namedJdbcTemplate.update(sql.toString(), namedParameters);
			
		} catch (Exception e) {
			LOG.error("Exception while trying to deleteVincoloOnLayer FeatureForLayerDto", e);
			throw new DaoException("DaoException while trying to deleteVincoloOnLayer FeatureForLayerDto", e);
		}
		finally {
			LOG.info("[LayerDAOImpl::deleteVincoloOnLayer] END");
		}
	}

	@Override
	public void deleteTaglioOnLayer(Long featureId) throws DaoException {
		LOG.info("[LayerDAOImpl::deleteTaglioOnLayer] BEGIN");
		try {
			final StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM idf_geo_pl_lotto_intervento ");
			sql.append("WHERE idgeo_pl_lotto_intervento = :idgeo_pl_lotto_intervento");
			
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("idgeo_pl_lotto_intervento", featureId);
			
			namedJdbcTemplate.update(sql.toString(), namedParameters);
			
		} catch (Exception e) {
			LOG.error("Exception while trying to deleteTaglioOnLayer FeatureForLayerDto", e);
			throw new DaoException("DaoException while trying to deleteTaglioOnLayer FeatureForLayerDto", e);
		}
		finally {
			LOG.info("[LayerDAOImpl::deleteTaglioOnLayer] END");
		}
	}

	@Override
	public void deleteEventoLnOnLayer(Long featureId) throws DaoException {
		LOG.info("[LayerDAOImpl::deleteEventoLnOnLayer] BEGIN");
		try {
			final StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM idf_geo_ln_evento ");
			sql.append("WHERE idgeo_ln_evento = :idgeo_ln_evento");
			
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("idgeo_ln_evento", featureId);
			
			namedJdbcTemplate.update(sql.toString(), namedParameters);
			
		} catch (Exception e) {
			LOG.error("Exception while trying to deleteEventoLnOnLayer FeatureForLayerDto", e);
			throw new DaoException("DaoException while trying to deleteEventoLnOnLayer FeatureForLayerDto", e);
		}
		finally {
			LOG.info("[LayerDAOImpl::deleteEventoLnOnLayer] END");
		}
	}

	@Override
	public void deleteEventoPlOnLayer(Long featureId) throws DaoException {
		LOG.info("[LayerDAOImpl::deleteEventoPlOnLayer] BEGIN");
		try {
			final StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM idf_geo_pl_evento ");
			sql.append("WHERE idgeo_pl_evento = :idgeo_pl_evento");
			
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("idgeo_pl_evento", featureId);
			
			namedJdbcTemplate.update(sql.toString(), namedParameters);
			
		} catch (Exception e) {
			LOG.error("Exception while trying to deleteEventoPlOnLayer FeatureForLayerDto", e);
			throw new DaoException("DaoException while trying to deleteEventoPlOnLayer FeatureForLayerDto", e);
		}
		finally {
			LOG.info("[LayerDAOImpl::deleteEventoPlOnLayer] END");
		}
	}

	@Override
	public void deleteEventoPtOnLayer(Long featureId) throws DaoException {
		LOG.info("[LayerDAOImpl::deleteEventoPtOnLayer] BEGIN");
		try {
			final StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM idf_geo_pt_evento ");
			sql.append("WHERE idgeo_pt_evento = :idgeo_pt_evento");
			
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("idgeo_pt_evento", featureId);
			
			namedJdbcTemplate.update(sql.toString(), namedParameters);
			
		} catch (Exception e) {
			LOG.error("Exception while trying to deleteEventoPtOnLayer FeatureForLayerDto", e);
			throw new DaoException("DaoException while trying to deleteEventoPtOnLayer FeatureForLayerDto", e);
		}
		finally {
			LOG.info("[LayerDAOImpl::deleteEventoPtOnLayer] END");
		}
	}

	@Override
	public void deleteInterventoLnOnLayer(Long featureId) throws DaoException {
		LOG.info("[LayerDAOImpl::deleteInterventoLnOnLayer] BEGIN");
		try {
			final StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM idf_geo_ln_lotto_intervento ");
			sql.append("WHERE idgeo_ln_lotto_intervento = :idgeo_ln_lotto_intervento");
			
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("idgeo_ln_lotto_intervento", featureId);
			
			namedJdbcTemplate.update(sql.toString(), namedParameters);
			
		} catch (Exception e) {
			LOG.error("Exception while trying to deleteInterventoLnOnLayer FeatureForLayerDto", e);
			throw new DaoException("DaoException while trying to deleteInterventoLnOnLayer FeatureForLayerDto", e);
		}
		finally {
			LOG.info("[LayerDAOImpl::deleteInterventoLnOnLayer] END");
		}
	}

	@Override
	public void deleteInterventoPlOnLayer(Long featureId) throws DaoException {
		LOG.info("[LayerDAOImpl::deleteInterventoPlOnLayer] BEGIN");
		try {
			final StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM idf_geo_pl_lotto_intervento ");
			sql.append("WHERE idgeo_pl_lotto_intervento = :idgeo_pl_lotto_intervento");
			
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("idgeo_pl_lotto_intervento", featureId);
			
			namedJdbcTemplate.update(sql.toString(), namedParameters);
			
		} catch (Exception e) {
			LOG.error("Exception while trying to deleteInterventoPlOnLayer FeatureForLayerDto", e);
			throw new DaoException("DaoException while trying to deleteInterventoPlOnLayer FeatureForLayerDto", e);
		}
		finally {
			LOG.info("[LayerDAOImpl::deleteInterventoPlOnLayer] END");
		}
	}

	
	@Override
	public void deleteInterventoPtOnLayer(Long featureId) throws DaoException {
		LOG.info("[LayerDAOImpl::deleteInterventoPtOnLayer] BEGIN");
		try {
			final StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM idf_geo_pt_lotto_intervento ");
			sql.append("WHERE idgeo_pt_lotto_intervento = :idgeo_pt_lotto_intervento");
			
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("idgeo_pt_lotto_intervento", featureId);
			
			namedJdbcTemplate.update(sql.toString(), namedParameters);
			
		} catch (Exception e) {
			LOG.error("Exception while trying to deleteInterventoPtOnLayer FeatureForLayerDto", e);
			throw new DaoException("DaoException while trying to deleteInterventoPtOnLayer FeatureForLayerDto", e);
		}
		finally {
			LOG.info("[LayerDAOImpl::deleteInterventoPtOnLayer] END");
		}
		
	}
	
	

}
