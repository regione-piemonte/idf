/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.PropcatastoInterventoDAO;
import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.dto.PlainParticelleCatastali;
import it.csi.idf.idfapi.dto.PropcatastoIntervento;
import it.csi.idf.idfapi.mapper.DoubleMapper;
import it.csi.idf.idfapi.mapper.PlainPropCatastoMapper;
import it.csi.idf.idfapi.mapper.PropcatastoInterventoMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class PropcatastoInterventoDAOImpl implements PropcatastoInterventoDAO{
	
	public static Logger logger = Logger.getLogger(PropcatastoInterventoDAOImpl.class); 

	@Override
	public List<PropcatastoIntervento> getAllPropcatastoByIdIntervento(int idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT idgeo_pl_prop_catasto, id_intervento, data_inserimento");
		sql.append(", data_aggiornamento, fk_config_utente");
		sql.append(" FROM idf_r_propcatasto_intervento");
		sql.append(" WHERE id_intervento = ?");

		return DBUtil.jdbcTemplate.query(sql.toString(), new PropcatastoInterventoMapper(), idIntervento);
	}

	@Override
	public List<PropcatastoIntervento> getPropcatastoByIdInterventoAndByIdGeo(int idIntervento, int idGeo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT idgeo_pl_prop_catasto, id_intervento, data_inserimento");
		sql.append(", data_aggiornamento, fk_config_utente");
		sql.append(" FROM idf_r_propcatasto_intervento");
		sql.append(" WHERE id_intervento = ? AND idgeo_pl_prop_catasto = ?");
		List<Object> parameters = new ArrayList<>();
		parameters.add(idIntervento);
		parameters.add(idGeo);
		return DBUtil.jdbcTemplate.query(sql.toString(), new PropcatastoInterventoMapper(), parameters.toArray());
	}

	@Override
	public void insertPropcatastoIntervento(PropcatastoIntervento propcatastoIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO idf_r_propcatasto_intervento(");
		sql.append("idgeo_pl_prop_catasto, id_intervento, data_inserimento");
		sql.append(", data_aggiornamento, fk_config_utente, sup_intervento_ha");
		sql.append(") VALUES(?, ?, ?, ?, ?, ?)");
		
		List<Object> parameters = new ArrayList<>();
		parameters.add(propcatastoIntervento.getIdgeoPlPropCatasto());
		parameters.add(propcatastoIntervento.getIdIntervento());
		parameters.add(propcatastoIntervento.getDataInserimento() == null ? null
				: Date.valueOf(propcatastoIntervento.getDataInserimento()));
		parameters.add(propcatastoIntervento.getDataAggiornamento() == null ? null
				: Date.valueOf(propcatastoIntervento.getDataAggiornamento()));
		parameters.add(propcatastoIntervento.getFkConfigUtente());
		parameters.add(propcatastoIntervento.getSupIntervento());
		
		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}

	@Override
	public void deletePropcatastoIntervento(int idgeoPlPropCatasto, int idIntervento) {
		String sql = "DELETE FROM idf_r_propcatasto_intervento WHERE idgeo_pl_prop_catasto = ? AND id_intervento = ?";
		DBUtil.jdbcTemplate.update(sql, idgeoPlPropCatasto, idIntervento);
	}
	
	@Override
	public void insertParticelleInPropcatastoIntervento(Integer idIntervento, PlainParticelleCatastali plainParticelleCatastali,
			ConfigUtente loggedConfig) throws DuplicateKeyException {

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO idf_r_propcatasto_intervento(idgeo_pl_prop_catasto, id_intervento, data_inserimento, fk_config_utente, sup_intervento_ha) ");
		sql.append("VALUES(?, ?, ?, ?, ?) ");

		List<Object> parameters = new ArrayList<>();
		logger.info("insertParticelleInPropcatastoIntervento -> id_intervento: " + idIntervento + " - idgeo_pl_prop_catasto: " + plainParticelleCatastali.getId());

		parameters.add(plainParticelleCatastali.getId());
		parameters.add(idIntervento);
		parameters.add(java.sql.Date.valueOf(LocalDate.now()));
		parameters.add(loggedConfig.getIdConfigUtente());
		parameters.add(plainParticelleCatastali.getSupIntervento() != null?plainParticelleCatastali.getSupIntervento():plainParticelleCatastali.getSupCatastale());

		try {
			DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
		}catch(DuplicateKeyException de) {
			de.printStackTrace();
			logger.info("<---- insertParticelleInPropcatastoIntervento " + de);
			throw new DuplicateKeyException("Elemento gia' presente");
		}
	}

	@Override
	public void deleteAllByIdIntervento(Integer idIntervento) {
		String sql = "DELETE FROM idf_r_propcatasto_intervento WHERE id_intervento = ?";
		DBUtil.jdbcTemplate.update(sql, idIntervento);
		
	}

	@Override
	public Double getAreaCatastaleByIdIntervento(Integer idIntervento) {
		String sql = "select sum(sup_catastale_ha) as areaHa " + 
				"from idf_r_propcatasto_intervento a " + 
				"inner join idf_geo_pl_prop_catasto b on a.idgeo_pl_prop_catasto = b.idgeo_pl_prop_catasto " + 
				"where id_intervento = ?";
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), 
				new Object[] {idIntervento}, new DoubleMapper());
	}

	@Override
	public Double getAreaInterventoByIdIntervento(Integer idIntervento) {
		String sql = "select sum(sup_intervento_ha) as areaHa " +
				"from idf_r_propcatasto_intervento a " +
				"inner join idf_geo_pl_prop_catasto b on a.idgeo_pl_prop_catasto = b.idgeo_pl_prop_catasto " +
				"where id_intervento = ? ";
		return DBUtil.jdbcTemplate.queryForObject(sql,
				new Object[] {idIntervento}, new DoubleMapper());
	}

	@Override
	public List<PlainParticelleCatastali> getPfaAllPropcatastoByGeomGeeco(int idIntervento) {
		String sql = "select sum( st_area(st_intersection(cts.geometria,lotto.geom))/10000) as area , " + 
				"cts.idgeo_pl_prop_catasto " + 
				"from idf_geo_pl_prop_catasto cts," + 
				"(SELECT ST_UNION (geometria ) as geom " + 
				"		FROM idf_geo_pl_lotto_intervento   " + 
				"		WHERE fk_intervento = ? ) as lotto " + 
				"where st_intersects(cts.geometria,lotto.geom)  " + 
				"and st_area(st_intersection(cts.geometria,lotto.geom)) > 0.5 " +
				"group by cts.idgeo_pl_prop_catasto";
		logger.info("getPfaAllPropcatastoByGeomGeeco -sql: " + sql + " -- idIntervento: " + idIntervento);
		return DBUtil.jdbcTemplate.query(sql.toString(), 
				new Object[] {idIntervento}, new PlainPropCatastoMapper());
	}
}
