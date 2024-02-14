/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.GeoPlParticellaForestDAO;
import it.csi.idf.idfapi.dto.GeoPlParticellaForest;
import it.csi.idf.idfapi.dto.PlainParticelleCatastali;
import it.csi.idf.idfapi.mapper.GeoPlParticellaForestMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class GeoPlParticellaForestDAOImpl implements GeoPlParticellaForestDAO {

	static final Logger logger = Logger.getLogger(GeoPlParticellaForestDAOImpl.class);
	
	private final GeoPlParticellaForestMapper geoPlParticellaForestMapper = new GeoPlParticellaForestMapper();
	
	
	@Override
	public GeoPlParticellaForest getOneIdByGeoPlPfa(Integer idGeoPlPfa) {
		String sql = "SELECT * FROM idf_geo_pl_particella_forest WHERE idgeo_pl_pfa = ?  LIMIT 1";
		return DBUtil.jdbcTemplate.queryForObject(sql, geoPlParticellaForestMapper, idGeoPlPfa);
	}

	@Override
	public GeoPlParticellaForest getForestParticleById(Integer idGeoPlParticellaForest) {
		String sql = "SELECT * FROM idf_geo_pl_particella_forest WHERE idgeo_pl_particella_forest = ?";
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), geoPlParticellaForestMapper, idGeoPlParticellaForest);
	}

	@Override
	public List<GeoPlParticellaForest> getForestParticleByInterventoId(Integer idIntervento) {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM idf_geo_pl_particella_forest");
		sql.append(" INNER JOIN idf_r_partfor_intervento  irpi USING (idgeo_pl_particella_forest)");
		sql.append(" WHERE irpi.id_intervento = ?");

		return DBUtil.jdbcTemplate.query(sql.toString(), geoPlParticellaForestMapper, idIntervento);
	}

	@Override
	public List<GeoPlParticellaForest> getForestParticleForShootingMirror(Integer idIntervento) {

		StringBuilder sql = new StringBuilder();

		sql.append("select p.*, p.provvigione_reale*(p.perc_tasso_ripresa_pot -p.perc_tara)/100 as ripresa_tot_ha ");
		sql.append(", p.provvigione_reale *(perc_tasso_ripresa_pot -perc_tara)/100- p.already_cut as ripresa_attuale ");
		sql.append(
				",p.provvigione_reale *(perc_tasso_ripresa_pot -perc_tara)/100 - p.already_cut - p.ripresa_intervento as ripresa_residua from ( ");
		sql.append(
				" select igppf.idgeo_pl_particella_forest, igppf.idgeo_pl_pfa, ettari, igpl.sup_tagliata_ha, cod_particella_for, provvigione_ha");
		sql.append(", perc_tasso_ripresa_pot, perc_tara, provvigione_ha*ettari as provvigione_reale ");
		sql.append(
				" , fk_padre, fk_compresa, denominazione_particella,irpi.data_inizio_validita, irpi.data_fine_validita ");
		sql.append(",irpi.ripresa_intervento, (select sum(ripresa_intervento) from idf_r_partfor_intervento irpi ");
		sql.append(" inner join idf_t_interv_selvicolturale itis using(id_intervento )");
		sql.append(
				" where irpi.idgeo_pl_particella_forest =igppf.idgeo_pl_particella_forest and fk_stato_intervento =2) as already_cut ");
		sql.append(" from idf_geo_pl_particella_forest igppf ");
		sql.append(" inner join idf_r_partfor_intervento irpi using(idgeo_pl_particella_forest ) ");
		sql.append(" inner join idf_t_interv_selvicolturale itis using(id_intervento ) ");
		sql.append(" inner join idf_geo_pl_lotto_intervento igpl on itis.id_intervento = igpl.fk_intervento ");
		sql.append("where irpi.id_intervento =? ) as p");

		return DBUtil.jdbcTemplate.query(sql.toString(), geoPlParticellaForestMapper, idIntervento);

	}

	@Override
	public List<Integer> getIdsForestParticlesByIdEvento(Integer idEvento) {

		StringBuilder sql = new StringBuilder();
		sql.append("select distinct idgeo_pl_particella_forest ");
		sql.append("from ( ");
		sql.append("select pf.idgeo_pl_particella_forest  ");
		sql.append("from idf_geo_pl_evento pl  ");
		sql.append("left join Idf_geo_pl_particella_forest pf on ST_Intersects(pl.geometria , pf.geometria)  ");
		sql.append("where pl.fk_evento = ? ");
		sql.append("union all  ");
		sql.append("select pf.idgeo_pl_particella_forest  ");
		sql.append("from idf_geo_ln_evento lin  ");
		sql.append("left join Idf_geo_pl_particella_forest pf on ST_Intersects(lin.geometria , pf.geometria)  ");
		sql.append("where lin.fk_evento = ? ");
		sql.append("union all ");
		sql.append("select pf.idgeo_pl_particella_forest  ");
		sql.append("from idf_geo_pt_evento pt  ");
		sql.append("left join Idf_geo_pl_particella_forest pf on ST_Intersects(pt.geometria , pf.geometria)  ");
		sql.append("where pt.fk_evento = ? ");
		sql.append(") as foo where idgeo_pl_particella_forest is not null ");
		logger.info("getIdsForestParticlesByIdEvento - sql: " + sql.toString() + " -- idEvento:" + idEvento);
		
		Object[] params = new Object[]{idEvento, idEvento, idEvento};
		return DBUtil.jdbcTemplate.queryForList(sql.toString(),Integer.class, params);
	}

	public List<Integer> getIdForestParticlesByIdGeoPfaWithSize(Integer idgeoPlfa, Integer size) {

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT idgeo_pl_particella_forest ");
		sql.append(" FROM idf_geo_pl_particella_forest where idgeo_pl_pfa =?");
		sql.append(" ORDER BY RANDOM() ");
		sql.append(" LIMIT ?");
		List<Integer> list = DBUtil.jdbcTemplate.queryForList(sql.toString(), Integer.class, idgeoPlfa, size);

		return list;
	}

	@Override
	public List<GeoPlParticellaForest> getForestParticlesByCadastralParticles(
			List<PlainParticelleCatastali> particelleCatastali, Integer idGeoPlPfa) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT parfor.*");
		sql.append(" FROM idf_geo_pl_particella_forest parfor");
		sql.append(" JOIN idf_geo_pl_prop_catasto propcat ON ST_Intersects(parfor.geometria, propcat.geometria) ");
		sql.append(" WHERE parfor.idgeo_pl_pfa =? AND propcat.idgeo_pl_prop_catasto =? ");

		List<GeoPlParticellaForest> particles = new ArrayList<GeoPlParticellaForest>();
		
		for (PlainParticelleCatastali plainParticelleCatastali : particelleCatastali) {
			List<GeoPlParticellaForest> list = DBUtil.jdbcTemplate.query(sql.toString(), geoPlParticellaForestMapper,
					idGeoPlPfa, plainParticelleCatastali.getId());
			for (GeoPlParticellaForest gppf : list) {
				if (!particles.contains(gppf)) {
					particles.add(gppf);
				}
			}
		}
		return particles;

	}

	@Override
	public List<GeoPlParticellaForest> getForestParticlesByGeomIntervento(Integer idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct * from( ");
		sql.append("select pf.* ");
		sql.append("from idf_geo_pl_lotto_intervento pl ");
		sql.append("inner join  idf_geo_pl_particella_forest pf on pl.fk_intervento = ? and ST_Intersects(pl.geometria , pf.geometria) ");
		sql.append("union all ");
		sql.append("select pf.* ");
		sql.append("from idf_geo_ln_lotto_intervento lin ");
		sql.append("inner join  idf_geo_pl_particella_forest pf on lin.id_intervento = ? and ST_Intersects(lin.geometria , pf.geometria) ");
		sql.append("union all ");
		sql.append("select pf.* ");
		sql.append("from idf_geo_pt_lotto_intervento pt ");
		sql.append("inner join  idf_geo_pl_particella_forest pf on pt.id_intervento = ? and ST_Intersects(pt.geometria , pf.geometria) ");
		sql.append(") as foo ");
		List<Object>params = new ArrayList<>();
		params.add(idIntervento);
		params.add(idIntervento);
		params.add(idIntervento);
		return DBUtil.jdbcTemplate.query(sql.toString(), geoPlParticellaForestMapper, params.toArray());
	}

}
