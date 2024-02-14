/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.exceptions.DuplicateRecordException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.impl.dao.ProvinciaDAO;
import it.csi.idf.idfapi.dto.GeoPLProvincia;
import it.csi.idf.idfapi.dto.GeoPLProvinciaSearch;
import it.csi.idf.idfapi.mapper.ProvinciaMapper;
import it.csi.idf.idfapi.mapper.ProvinciaSearchMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class ProvinciaDAOImpl implements ProvinciaDAO {
	
	private final ProvinciaMapper provinciaMapper = new ProvinciaMapper();
	private final ProvinciaSearchMapper provinciaSearchMapper = new ProvinciaSearchMapper();

	@Override
	public List<GeoPLProvincia> findAllProvincia() {
		
		String sql = "SELECT * FROM idf_geo_pl_provincia WHERE fk_regione = 1 ORDER BY denominazione_prov";
		return DBUtil.jdbcTemplate.query(sql, provinciaMapper);
	}

	@Override
	public GeoPLProvincia findProvinciaByIstatProv(String istatProv) throws RecordNotFoundException {
		
		String sql = "SELECT * FROM idf_geo_pl_provincia prov WHERE prov.istat_prov = ? ORDER BY denominazione_prov";
		return DBUtil.jdbcTemplate.queryForObject(sql, provinciaMapper, istatProv);
	}

	@Override
	public int createProvincia(GeoPLProvincia provincia) throws DuplicateRecordException {
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO idf_geo_pl_provincia(");
		sql.append("istat_prov, sigla_prov, denominazione_prov, geometria");
		sql.append(") VALUES (?, ?, ?, ?)");
	
		List<Object> parameters = new ArrayList<>();

		parameters.add(provincia.getIstatProv());
		parameters.add(provincia.getSiglaProv());
		parameters.add(provincia.getDenominazioneProv());
		parameters.add(provincia.getGeometria());

		return DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}
	
	@Override
	public List<GeoPLProvinciaSearch> findAllSearchProvincia() {
		
		String sql = "SELECT istat_prov, sigla_prov, denominazione_prov FROM idf_geo_pl_provincia where fk_regione = 1 ORDER BY denominazione_prov";
		return DBUtil.jdbcTemplate.query(sql, provinciaSearchMapper);
	}

	@Override
	public List<GeoPLProvinciaSearch> findAllSearchItaliaProvincia() {

		String sql = "SELECT istat_prov, sigla_prov, denominazione_prov FROM idf_geo_pl_provincia ORDER BY denominazione_prov";
		return DBUtil.jdbcTemplate.query(sql, provinciaSearchMapper);
	}

	@Override
	public List<GeoPLProvinciaSearch> findSearchProvinciaByIstatProv(String istatProv) {
		
		String sql = "SELECT istat_prov, sigla_prov, denominazione_prov FROM idf_geo_pl_provincia WHERE istat_prov = ? ORDER BY denominazione_prov";
		return DBUtil.jdbcTemplate.query(sql, provinciaSearchMapper, istatProv);
	}

	@Override
	public List<GeoPLProvinciaSearch> findProvinciaBoEnabled(int idSoggetto) {
		String sql = "select istat_prov ,sigla_prov , denominazione_prov ,null as geometria ,fk_regione from idf_geo_pl_provincia " + 
				"where istat_prov in ( " + 
				"select pg.istat_prov_competenza_terr from IDF_R_PF_PG pfpg inner join idf_t_soggetto pg on pfpg.id_soggetto_pg = pg.id_soggetto  " + 
				"where pfpg.id_soggetto_pf = ? " + 
				"union all  " + 
				"select istat_prov from idf_geo_pl_comune igpc where istat_comune in ( " + 
				"select pg.istat_comune_competenza_terr from IDF_R_PF_PG pfpg inner join idf_t_soggetto pg on pfpg.id_soggetto_pg = pg.id_soggetto  " + 
				"where pfpg.id_soggetto_pf = ?) " + 
				")order by denominazione_prov ";
		return DBUtil.jdbcTemplate.query(sql, provinciaSearchMapper, idSoggetto,idSoggetto);
	}
}
