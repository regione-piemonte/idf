/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.GeoPlLottoInterventoDAO;
import it.csi.idf.idfapi.dto.LottoIntervento;
import it.csi.idf.idfapi.mapper.DoubleMapper;
import it.csi.idf.idfapi.mapper.LottoInterventoMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class GeoPlLottoInterventoDAOImpl implements GeoPlLottoInterventoDAO {
	
	public static Logger logger = Logger.getLogger(GeoPlLottoInterventoDAOImpl.class);

	@Override
	public void deleteGeoPlLottoIntervento(int idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM idf_geo_pl_lotto_intervento");
		sql.append(" WHERE fk_intervento = ?");
		
		DBUtil.jdbcTemplate.update(sql.toString(), idIntervento);
	}
	
	@Override
	public void updateSupTagliataByIntervento(int idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("update idf_geo_pl_lotto_intervento ");
		sql.append( "set sup_tagliata_ha = ROUND((ST_Area(ST_MakeValid(geometria))/10000)::numeric , 4) ");
		sql.append(" WHERE fk_intervento = ? ");
		
		DBUtil.jdbcTemplate.update(sql.toString(), idIntervento);
	}

	@Override
	public void updateSupTagliataById(int idGeo) {
		StringBuilder sql = new StringBuilder();
		sql.append("update idf_geo_pl_lotto_intervento ");
		sql.append( "set sup_tagliata_ha = ROUND((ST_Area(ST_MakeValid(geometria))/10000)::numeric , 4) ");
		sql.append(" WHERE idgeo_pl_lotto_intervento = ? ");

		DBUtil.jdbcTemplate.update(sql.toString(), idGeo);
	}

	@Override
	public BigDecimal getCuttingVolumesShareByForestParticella(Integer idIntervento, Integer idGeoParticellaForest) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT (public.st_area(ST_INTERSECTION(igpli.geometria,parfor.geometria))");
		sql.append("/(SELECT sum(public.st_area(ST_INTERSECTION(igpli.geometria,parfor.geometria)))"); 
		sql.append(" FROM idf_geo_pl_lotto_intervento igpli");
		sql.append(" JOIN idf_geo_pl_particella_forest parfor ON ST_Intersects(igpli.geometria, parfor.geometria)) ) as perc_of_total"); 
		sql.append(" FROM idf_geo_pl_lotto_intervento igpli");
		sql.append(" JOIN idf_geo_pl_particella_forest parfor ON ST_Intersects(igpli.geometria, parfor.geometria)"); 
		sql.append(" WHERE igpli.fk_intervento =? AND parfor.idgeo_pl_particella_forest =?");
		
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), BigDecimal.class, idIntervento, idGeoParticellaForest);
	}

	@Override
	public BigDecimal getSupTagliataForForestParticle(Integer idIntervento, Integer idGeoParticellaForest) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT public.st_area(ST_INTERSECTION(igpli.geometria,parfor.geometria)) as area");
		sql.append("FROM idf_geo_pl_lotto_intervento igpli");
		sql.append("JOIN idf_geo_pl_particella_forest parfor ON ST_Intersects(igpli.geometria, parfor.geometria)");
		sql.append("where igpli.fk_intervento =? and parfor.idgeo_pl_particella_forest = ?");
		
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), BigDecimal.class, idIntervento, idGeoParticellaForest);
	}
	
	@Override
	public List<LottoIntervento> getGeometrieGmlByFkIntervento(Integer idIntervento) {
		
		StringBuilder sql = new StringBuilder();
		// 777 sin convertir a GML
		sql.append("select idgeo_pl_lotto_intervento as id_lotto, ST_ASGEOJSON( ST_TRANSFORM(lot.geometria, 4326) ) as geometriagml, lot.sup_tagliata_ha as sup_lotto_ha,  ");
		// 777 sql.append("select idgeo_pl_lotto_intervento as id_lotto, public.st_asgml(lot.geometria) as geometriagml, lot.sup_tagliata_ha as sup_lotto_ha,  ");
		sql.append("(select sum(sup_tagliata_ha) from idf_geo_pl_lotto_intervento where fk_intervento =lot.fk_intervento  ");
		sql.append("	group by fk_intervento ) as tot_sup_intervento_ha  ");
		sql.append("from idf_geo_pl_lotto_intervento lot where lot.fk_intervento = ? ");
		return DBUtil.jdbcTemplate.query(sql.toString(), new Object[] {idIntervento}, new LottoInterventoMapper());
	}
	public List<LottoIntervento> getGeometrieGmlByFkInterventoNew(Integer idIntervento) {
		
		StringBuilder sql = new StringBuilder();
		// 777 sin convertir a GML
		//sql.append("select idgeo_pl_lotto_intervento as id_lotto, ST_ASGEOJSON( ST_TRANSFORM(lot.geometria, 4326) ) as geometriagml, lot.sup_tagliata_ha as sup_lotto_ha,  ");
		sql.append("select idgeo_pl_lotto_intervento as id_lotto, public.st_asgml(lot.geometria) as geometriagml, lot.sup_tagliata_ha as sup_lotto_ha,  ");
		sql.append("(select sum(sup_tagliata_ha) from idf_geo_pl_lotto_intervento where fk_intervento =lot.fk_intervento  ");
		sql.append("	group by fk_intervento ) as tot_sup_intervento_ha  ");
		sql.append("from idf_geo_pl_lotto_intervento lot where lot.fk_intervento = ? ");
		return DBUtil.jdbcTemplate.query(sql.toString(), new Object[] {idIntervento}, new LottoInterventoMapper());
	}
	@Override
	public Double getAreaTrasformazioneByFkIntervento(Integer idIntervento) {
		
		String sql = "select sum(sup_tagliata_ha) FROM idf_geo_pl_lotto_intervento WHERE fk_intervento = ?";
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), 
				new Object[] {idIntervento}, new DoubleMapper());
	}
	
	@Override
	public void addGeometria(Integer idIntervento, Integer idgeoPlPropCatasto) {
		
		
		logger.info("addGeometria - idIntervento: " + idIntervento + " - idgeoPlPropCatasto: " + idgeoPlPropCatasto);
		String sql = "select fn_lotto_interv_aggiungi_geo(?,?)";
		
		Integer res = DBUtil.jdbcTemplate.queryForObject(sql.toString(), Integer.class, new Object[] {idIntervento, idgeoPlPropCatasto});
		if(res == null || res > 0) {
			logger.error("addGeometria - Error code: " + res);
		}
		logger.info("addGeometria - done");
	}

	@Override
	public void removeGeometria(Integer idIntervento, Integer idgeoPlPropCatasto) {
		String sql = "select fn_lotto_interv_togli_geo(?,?)";
		Integer res = DBUtil.jdbcTemplate.queryForObject(sql.toString(),Integer.class, new Object[] {idIntervento, idgeoPlPropCatasto});
		if(res == null || res > 0) {
			logger.error("addGeometria - Error code: " + res);
		}
		logger.info("removeGeometria - done: ");
	}

	@Override
	public Double getAreaItersecWithParticella(Integer idIntervento, Integer idgeo_pl_prop_catasto) {
		String sql = "select sum(ST_AREA (ST_INTERSECTION(a.geometria , b.geometria))) as area "
				+ "from idf_geo_pl_lotto_intervento a,  idf_geo_pl_prop_catasto b "
				+ "where a.fk_intervento = ? and b.idgeo_pl_prop_catasto = ? "
				+ "and ST_Intersects(a.geometria , b.geometria )";
		
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), 
				new Object[] {idIntervento,idgeo_pl_prop_catasto}, new DoubleMapper());
	}


	@Override
	public void duplicateIntervento(Integer idIntervento, Integer idInterventoNew, Integer idConfUtente) {
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO idf_geo_pl_lotto_intervento (idgeo_pl_lotto_intervento,  ");
		sql.append(" fk_intervento, data_inserimento, geometria, sup_tagliata_ha, descrizione ) ");
		sql.append(" SELECT nextval('seq_idf_geo_pl_lotto_intervento'), ? , ? ,geometria, sup_tagliata_ha, descrizione ");
		sql.append(" FROM idf_geo_pl_lotto_intervento where fk_intervento = ? ");
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
	public Double getAreaInterventoByIdIntervento(Integer idIntervento) {
		String sql = "select sum(sup_tagliata_ha) as areaHa " +
				"from idf_geo_pl_lotto_intervento a " +
				"where fk_intervento = ? ";
		return DBUtil.jdbcTemplate.queryForObject(sql,
				new Object[] {idIntervento}, new DoubleMapper());
	}

	@Override
	public List<String> getIntersezioneGeometriaComune(Integer idIntervento) {

		StringBuilder sql = new StringBuilder();
		sql.append("select igpc.istat_comune from idf_geo_pl_comune igpc where ST_Intersects ( ");
		sql.append("(SELECT ST_UNION (geomInt) as geomIntUnion FROM ( ");
		sql.append("SELECT igpli.geometria as geomInt FROM idf_geo_pl_lotto_intervento igpli WHERE igpli.fk_intervento = ?) as sbqry), igpc.geometria) GROUP BY igpc.istat_comune");
		return DBUtil.jdbcTemplate.queryForList(sql.toString(), String.class, new Object[] {idIntervento});
	}

	@Override
	public List<String> getIntersezioneGeometriaComuneTrasf(Integer idIntervento) {

		StringBuilder sql = new StringBuilder();
		sql.append("select igpc.istat_comune from idf_geo_pl_comune igpc where ST_Intersects ( ");
		sql.append("(SELECT ST_UNION (geomInt) as geomIntUnion FROM ( ");
		sql.append("SELECT igpli.geometria as geomInt FROM idf_geo_pl_interv_trasf igpli WHERE igpli.fk_intervento = ?) as sbqry), igpc.geometria) GROUP BY igpc.istat_comune");
		return DBUtil.jdbcTemplate.queryForList(sql.toString(), String.class, new Object[] {idIntervento});
	}
}
