/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.PartforInterventoDAO;
import it.csi.idf.idfapi.dto.ChiusuraInformazioni;
import it.csi.idf.idfapi.dto.InterventoRiepilogo;
import it.csi.idf.idfapi.dto.PartforRipresaInformazioni;
import it.csi.idf.idfapi.mapper.ChiusuraInformazioniMapper;
import it.csi.idf.idfapi.mapper.PartforRipresaInformazioniMapper;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.ListUtil;

@Component
public class PartforInterventoDAOImpl implements PartforInterventoDAO {

	final static Logger logger = Logger.getLogger(PartforInterventoDAOImpl.class);

	@Override
	public int createParforInterv(Integer idParticelaForestale, Integer idIntervento) throws DuplicateKeyException  {

		String sql = "INSERT INTO idf_r_partfor_intervento( "
				+ "	id_intervento, idgeo_pl_particella_forest, data_inizio_validita) VALUES (?, ?, ?)";

		List<Object> parameters = new ArrayList<>();

		parameters.add(idIntervento);
		parameters.add(idParticelaForestale);
		parameters.add(java.sql.Date.valueOf(LocalDate.now()));

		logger.info("into partForInterventoDAO");

		try {
		return DBUtil.jdbcTemplate.update(sql, parameters.toArray());
		}catch(DuplicateKeyException dpe ) {
			throw new DuplicateKeyException("Errore chiave dupplicata");
		}
		

	}

	@Override
	public void deletePartforIntervento(Integer idIntervento) {

		StringBuilder sql = new StringBuilder();

		sql.append("DELETE FROM idf_r_partfor_intervento ");
		sql.append("WHERE id_intervento = ? ");

		DBUtil.jdbcTemplate.update(sql.toString(), idIntervento);
	}

	@Override
	public List<PartforRipresaInformazioni> getPartforRipresaInformazioniForChiusura(Integer idIntervento) {
		StringBuilder sql = new StringBuilder();

		sql.append("select igppf.cod_particella_for, irpi.ripresa_intervento, igppf.ripresa_attuale ");
		sql.append(", (igppf.ripresa_attuale-irpi.ripresa_intervento) as ripresa_residua ");
		sql.append(" from idf_r_partfor_intervento irpi ");
		sql.append(" join idf_geo_pl_particella_forest igppf using (idgeo_pl_particella_forest) ");
		sql.append(" where id_intervento = ? ");

		return DBUtil.jdbcTemplate.query(sql.toString(), new PartforRipresaInformazioniMapper(), idIntervento);
	}

	@Override
	public ChiusuraInformazioni getChiusuraInformazioniByIdIntervento(Integer idIntervento) {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT irpi.data_inizio_validita, irpi.data_fine_validita,sup_reale_tagliata_rid, ");
		sql.append("sum(sup_tagliata_ha) as sup_tagliata_ha, stima_valore_lotto, valore_aggiudicazione_asta, itis.flg_conforme_deroga ");
		sql.append(" FROM idf_r_partfor_intervento irpi ");
		sql.append(" JOIN idf_geo_pl_lotto_intervento igpli on irpi.id_intervento = igpli.fk_intervento ");
		sql.append(" JOIN idf_t_interv_selvicolturale itis using(id_intervento) ");
		sql.append(" WHERE id_intervento = ? ");
		sql.append(" group by irpi.data_inizio_validita, irpi.data_fine_validita, sup_reale_tagliata_rid, ");
		sql.append(" stima_valore_lotto, valore_aggiudicazione_asta, itis.flg_conforme_deroga");

		List<ChiusuraInformazioni> list = DBUtil.jdbcTemplate.query(sql.toString(), new ChiusuraInformazioniMapper(),
				idIntervento);
		
		if (ListUtil.isEmpty(list)) {
			return null;
		} else
			return list.get(0);
	}

	@Override
	public void updatePartforInterventoWithChiusuraDates(Integer idIntervento,
			InterventoRiepilogo interventoRiepilogo) {
		
		StringBuilder update = new StringBuilder();
		
		update.append("UPDATE idf_r_partfor_intervento set data_inizio_validita = ?, data_fine_validita = ?");
		update.append(" where id_intervento = ? ");
		
		List<Object> parameters = new ArrayList<>();
		parameters.add(java.sql.Date.valueOf(interventoRiepilogo.getChiusuraInformazioni().getDataInizio()));
		parameters.add(java.sql.Date.valueOf(interventoRiepilogo.getChiusuraInformazioni().getDataFine()));
		parameters.add(idIntervento);
		
		DBUtil.jdbcTemplate.update(update.toString(), parameters.toArray());
		
		
	}

	@Override
	public void updatePartforInterventoWithRipresaIntervento(Integer idIntervento, Integer idGeoParticellaForest, BigDecimal ripresaIntervento) {
		StringBuilder update = new StringBuilder();
		
		update.append("UPDATE idf_r_partfor_intervento set ripresa_intervento = ?");
		update.append(" WHERE id_intervento = ? AND idgeo_pl_particella_forest= ? ");
		
		List<Object> parameters = new ArrayList<>();
		parameters.add(ripresaIntervento);
		parameters.add(idIntervento);
		parameters.add(idGeoParticellaForest);
		
		DBUtil.jdbcTemplate.update(update.toString(), parameters.toArray());
		
	}

	
	@Override
	public void updatePartforInterventoAtCompleta(Integer idIntervento,Integer idGeoParticellaForest,
			BigDecimal ripresaAttuale) {
		StringBuilder update = new StringBuilder();
		
		update.append("UPDATE idf_r_partfor_intervento set ripresa_attuale = ? ");
		update.append(" WHERE id_intervento = ? AND idgeo_pl_particella_forest= ? ");
		
		List<Object> parameters = new ArrayList<>();
		parameters.add(ripresaAttuale);
		parameters.add(idIntervento);
		parameters.add(idGeoParticellaForest);
		
		DBUtil.jdbcTemplate.update(update.toString(), parameters.toArray());
		
	}

	@Override
	public void updatePartforInterventoAfterEditing(Integer idIntervento) {
		logger.info("updatePartforInterventoAfterEditing - idIntervento: " + idIntervento);
		String sql = "select fn_utl_agg_perc_intersez_intervento_part_forest(?)";
		
		Integer res = DBUtil.jdbcTemplate.queryForObject(sql.toString(),Integer.class, new Object[] {idIntervento});
		if(res == null || res > 0) {
			logger.error("updatePartforInterventoAfterEditing - Error code: " + res);
		}
		logger.info("updatePartforInterventoAfterEditing - done");
	}

	private List<Pair<Integer, Double>> getPercentInterventoByIdInterventoPfa(Integer idIntervento) {
	StringBuilder sql = new StringBuilder();
			
	sql.append("select fore.idgeo_pl_particella_forest as id_part_for, SUM(ST_AREA(ST_Intersection(fore.geometria, geo.geometria)))/10000 as sup ");
	sql.append("from idf_r_partfor_intervento irpi  ");
	sql.append("inner join idf_geo_pl_lotto_intervento geo on geo.fk_intervento = irpi.id_intervento  ");
	sql.append("inner join idf_geo_pl_particella_forest fore on fore.idgeo_pl_particella_forest = irpi.idgeo_pl_particella_forest  ");
	sql.append("where id_intervento = ? and ST_Intersects(fore.geometria, geo.geometria) ");
	sql.append("group by fore.idgeo_pl_particella_forest ");
	sql.append("order by fore.idgeo_pl_particella_forest ");
	
	return DBUtil.jdbcTemplate.query(sql.toString(), new Object[] {idIntervento},  new ResultSetExtractor<List<Pair<Integer, Double>>>()
    {
    	@Override
		public List<Pair<Integer, Double>> extractData(ResultSet rs) throws SQLException {
    		List<Pair<Integer, Double>> res = new ArrayList<Pair<Integer, Double>>();
    		while(rs.next()) {
    			res.add(new ImmutablePair<Integer, Double>(rs.getInt("id_part_for"), rs.getDouble("sup")));
    		}
			return res ;
		}
    });
		
	}

	@Override
	public void updateRipresaInterventoPfa(Integer idIntervento, Double areaInterventoHa) {
		List<Pair<Integer, Double>> listPercentualeIntervento = getPercentInterventoByIdInterventoPfa(idIntervento);
		StringBuilder sql = new StringBuilder();
		sql.append("update idf_r_partfor_intervento ");
		sql.append("set ripresa_intervento =  ");
		sql.append("(select case WHEN stima_massa_retraibile_m3 is null ");
		sql.append("THEN 0 ELSE stima_massa_retraibile_m3 end ");
		sql.append("from idf_t_interv_selvicolturale itis where id_intervento = ?) * ? / ? ");
		sql.append("where id_intervento = ? and idgeo_pl_particella_forest = ?");
		Object[] params = new Object[] {idIntervento,null,areaInterventoHa,idIntervento,null};
		for(Pair<Integer, Double> item : listPercentualeIntervento) {
			params[1] = item.getValue();
			params[4] = item.getKey();
			DBUtil.jdbcTemplate.update(sql.toString(), params);
		}
	} 

}
