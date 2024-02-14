/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.ShootingMirrorDAO;
import it.csi.idf.idfapi.dto.ShootingMirrorDTO;
import it.csi.idf.idfapi.mapper.ShootingMirrorMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class ShootingMirrorDAOImpl implements ShootingMirrorDAO {
	
	private static Logger logger = Logger.getLogger(ShootingMirrorDAOImpl.class);

	private final ShootingMirrorMapper shootingMirrorMapper= new ShootingMirrorMapper();
	
	@Override
	public List<ShootingMirrorDTO> getAllParticlesForShootingMirror(Integer idIntervento) {
		
		StringBuilder sql = new StringBuilder();
//		sql.append("select p.*, p.provvigione_reale*(p.perc_tasso_ripresa_pot -p.perc_tara)/100 as ripresa_tot_ha ");
//		sql.append(", p.provvigione_reale *(perc_tasso_ripresa_pot -perc_tara)/100- p.already_cut as ripresa_attuale ");
//		sql.append(",p.provvigione_reale *(perc_tasso_ripresa_pot -perc_tara)/100 - p.already_cut - p.ripresa_intervento as ripresa_residua from ( ");
//		sql.append(" select igppf.idgeo_pl_particella_forest, igppf.idgeo_pl_pfa, ettari, igpl.sup_tagliata_ha, cod_particella_for, provvigione_ha"); 
//		sql.append(", perc_tasso_ripresa_pot, perc_tara, provvigione_ha*ettari as provvigione_reale ");
//		sql.append(",irpi.ripresa_intervento, (select sum(ripresa_intervento) from idf_r_partfor_intervento irpi ");
//		sql.append(" inner join idf_t_interv_selvicolturale itis using(id_intervento )");
//		sql.append(" where irpi.idgeo_pl_particella_forest =igppf.idgeo_pl_particella_forest and ");
//		sql.append(" fk_stato_intervento =(select id_stato_intervento from idf_d_stato_intervento where descr_stato_intervento like 'Concluso')) as already_cut ");
//		sql.append(" from idf_geo_pl_particella_forest igppf ");
//		sql.append(" inner join idf_r_partfor_intervento irpi using(idgeo_pl_particella_forest ) ");
//		sql.append(" inner join idf_t_interv_selvicolturale itis using(id_intervento ) ");
//		sql.append(" left join idf_geo_pl_lotto_intervento igpl on itis.id_intervento = igpl.fk_intervento ");
//		sql.append( "where irpi.id_intervento =? ) as p");
		
		sql.append("select distinct igppf.idgeo_pl_particella_forest, igppf.ripresa_tot_ha, igppf.idgeo_pl_pfa, ettari,");
		sql.append("  igpl.sup_tagliata_ha, cod_particella_for, provvigione_ha , perc_tasso_ripresa_pot, perc_tara,");
		sql.append(" provvigione_ha*ettari as provvigione_reale ,irpi.ripresa_intervento,");
		sql.append(" 	(select sum(ripresa_intervento) from idf_r_partfor_intervento irpi");
		sql.append(" 	inner join idf_t_interv_selvicolturale itis using(id_intervento )");
		sql.append(" 	where irpi.idgeo_pl_particella_forest =igppf.idgeo_pl_particella_forest");
		sql.append(" 	and  fk_stato_intervento =(select id_stato_intervento from idf_d_stato_intervento ");
		sql.append("    where descr_stato_intervento like 'Concluso')) as already_cut");
		sql.append(" from  idf_geo_pl_lotto_intervento igpl");
		sql.append(" left join idf_geo_pl_particella_forest igppf  on igppf.cod_particella_for <> '0' AND ST_Intersects(igpl.geometria , igppf.geometria)");
		sql.append(" left join idf_r_partfor_intervento irpi on irpi.idgeo_pl_particella_forest=igppf.idgeo_pl_particella_forest");
		sql.append("  	and  irpi.id_intervento = igpl.fk_intervento");
		sql.append(" where igpl.fk_intervento = ? ");
		
		
		logger.info("getAllParticlesForShootingMirror sql: " + sql.toString() + " - idIntervento: " + idIntervento);
		return DBUtil.jdbcTemplate.query(sql.toString(), shootingMirrorMapper,idIntervento);
	}

	@Override
	public List<ShootingMirrorDTO> getAllParticlesForShootingMirrorNEW(Integer idIntervento) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct length(cod_particella_for), cod_particella_for, idgeo_pl_particella_forest, ripresa_tot_ha, ");
		sql.append("provvigione_ha, perc_tasso_ripresa_pot, perc_tara, idgeo_pl_pfa, ettari, sup_tagliata_ha, ");
		sql.append("provvigione_reale ,ripresa_intervento,already_cut,not_already_cut ");
		sql.append("from ( ");
		sql.append("select igppf.idgeo_pl_particella_forest, igppf.ripresa_tot_ha, igppf.idgeo_pl_pfa, ettari, ");
		sql.append("cod_particella_for, provvigione_ha, perc_tasso_ripresa_pot, perc_tara, ");
		sql.append("provvigione_ha*ettari as provvigione_reale ,irpi.ripresa_intervento, ");
		sql.append("(select sum(ripresa_intervento) from idf_r_partfor_intervento irpi ");
		sql.append("inner join idf_t_interv_selvicolturale itis using(id_intervento ) ");
		sql.append("	where irpi.idgeo_pl_particella_forest =igppf.idgeo_pl_particella_forest ");
		sql.append("and  	fk_stato_intervento = 5) as already_cut , ");
		sql.append("(select sum(ripresa_intervento) from idf_r_partfor_intervento irpi ");
		sql.append("inner join idf_t_interv_selvicolturale itis using(id_intervento ) ");
		sql.append("	where irpi.idgeo_pl_particella_forest =igppf.idgeo_pl_particella_forest ");
		sql.append("and  	fk_stato_intervento <5) as  not_already_cut, ");
		sql.append("(select sum(sup_tagliata_ha) from idf_geo_pl_lotto_intervento  ");
		sql.append("	where fk_intervento = irpi.id_intervento) as sup_tagliata_ha  ");
		sql.append("from  idf_geo_pl_particella_forest igppf ");
		sql.append("inner join idf_r_partfor_intervento irpi on irpi.idgeo_pl_particella_forest=igppf.idgeo_pl_particella_forest ");
		sql.append("where irpi.id_intervento = ? ) as foo where cod_particella_for <> '0' ");
		sql.append("order by length(cod_particella_for), cod_particella_for ");
		logger.info("getAllParticlesForShootingMirrorNEW sql: " + sql.toString() + " - idIntervento: " + idIntervento);
		return DBUtil.jdbcTemplate.query(sql.toString(), shootingMirrorMapper,idIntervento);
	}

}
