/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfgeoapi.integration.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import it.csi.idf.idfgeoapi.dto.GeecoProfiloDto;
import it.csi.idf.idfgeoapi.exception.DaoException;
import it.csi.idf.idfgeoapi.integration.dao.GeecoDao;
import it.csi.idf.idfgeoapi.integration.dao.impl.rowmapper.GeecoProfiloRowMapper;
import it.csi.idf.idfgeoapi.util.Constants;



	@Repository
public class GeecoDaoImpl extends AbstractDAO implements GeecoDao {
	
	protected transient Logger LOG = Logger.getLogger(Constants.COMPONENT_NAME);
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	@Autowired
	public GeecoDaoImpl(DataSource dataSource, NamedParameterJdbcTemplate namedJdbcTemplate) {
		this.namedJdbcTemplate = namedJdbcTemplate;
		setDataSource(dataSource);
	}

	@Override
	public GeecoProfiloDto getGeecoProfiloByIdLayer(String idLayer) throws DaoException {
		LOG.info("[GeecoDAOImpl::getGeecoProfiloByIdLayer] BEGIN");
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT "); 
			sql.append("p.id_geeco_profilo,"); 
			sql.append("p.descr_geeco_profilo,"); 
			sql.append("p.fk_procedura,"); 
			sql.append("p.url_ritorno,"); 
			sql.append("p.env_info,"); 
			sql.append("p.flg_autentic_lettura_scrittura "); 
			sql.append("FROM "); 
			sql.append("idf_asr_r_geeco_profilo_layer pl,"); 
			sql.append("idf_asr_t_geeco_profilo p "); 
			sql.append("WHERE pl.id_geeco_profilo = p.id_geeco_profilo"); 
			sql.append(" AND pl.id_geeco_layer =:id_geeco_layer");
			
			
			
			
			
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id_geeco_layer", idLayer);
			
			List<GeecoProfiloDto> list = namedJdbcTemplate.query(sql.toString(), paramMap, new GeecoProfiloRowMapper());
			
			return list.isEmpty()?new GeecoProfiloDto():list.get(0);
		}
		catch (Exception e) {
			throw new DaoException("Errore sql :"+e.getMessage());
		}
		finally {
			LOG.info("[GeecoDAOImpl::getGeecoProfiloByIdLayer] END");
		}
		
	}
	

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void testResources() throws DaoException {
		LOG.debug("[GeecoDAOImpl::testResources] BEGIN");
		try {
			final String sql = new String("select now()");
			
			namedJdbcTemplate.queryForObject(sql, (HashMap) null, String.class);
		}
		catch (Exception e) {
			throw new DaoException("Errore sql :"+e.getMessage());
		}
		finally {
			LOG.debug("[GeecoDAOImpl::testResources] END");
		}
	}

	
	
	
	
	
	
}
