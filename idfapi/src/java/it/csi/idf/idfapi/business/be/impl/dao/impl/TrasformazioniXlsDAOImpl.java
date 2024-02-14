/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;
import java.util.StringJoiner;

import it.csi.idf.idfapi.dto.*;
import it.csi.idf.idfapi.mapper.*;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.TrasformazioniXlsDAO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.VincoloXls;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class TrasformazioniXlsDAOImpl implements TrasformazioniXlsDAO {
	
	static final Logger logger = Logger.getLogger(TrasformazioniXlsDAOImpl.class);

	@Override
	public List<TrasformazioniXls> getTraformazinoniXlsByIstanze(List<Integer> idsIstanze) {
		String sql = "SELECT * FROM idf_v_trasformazioni " + getWhereInClausole(idsIstanze);
		logger.info("getTraformazinoniXlsByIstanze sql: " + sql);
		return DBUtil.jdbcTemplate.query(sql, new TrasformazioniXlsMapper());
	}

	@Override
	public List<TrasformazCatastoXls> getTraformazCatastoXlsByIstanze(List<Integer> idsIstanze) {
		String sql = "SELECT * FROM idf_v_trasf_catasto " + getWhereInClausole(idsIstanze);
		logger.info("getTraformazCatastoXlsByIstanze sql: " + sql);
		return DBUtil.jdbcTemplate.query(sql, new TrasformazCatastoXlsMapper());
	}
	
	
	private String getWhereInClausole(List<Integer> idsIstanze) {
		StringBuilder sb = new StringBuilder("where nr_istanza_forestale IN(");
		boolean isFirst = true;
		for (int i = 0; i < idsIstanze.size(); i++) {
			if ((i + 1) % 100 == 0) {
				sb.append(") or nr_istanza_forestale IN(");
				isFirst = true;
			}
			if (isFirst) {
				sb.append(idsIstanze.get(i));
				isFirst = false;
			} else {
				sb.append(",").append(idsIstanze.get(i));
			}
		}
		sb.append(")");
		return sb.toString();
	}

	@Override
	public List<VincoloXls> getVincoloXlsByIstanze(List<Integer> idsIstanze) {
		String sql = "SELECT * FROM idf_v_vincolo_idrogeologico " + getWhereInClausole(idsIstanze);
		logger.debug("getVincoloXlsByIstanze sql: " + sql);
		return DBUtil.jdbcTemplate.query(sql, new VincoloXlsMapper());
	}

	@Override
	public List<TrasformazCatastoXls> getVincoloCatastoXlsByIstanze(List<Integer> idsIstanze) {
		String sql = "SELECT * FROM idf_v_vincidro_catasto " + getWhereInClausole(idsIstanze);
		logger.debug("getVincoloCatastoXlsByIstanze sql: " + sql);
		return DBUtil.jdbcTemplate.query(sql, new TrasformazCatastoXlsMapper());
	}


	@Override
	public List<TagliXls> getTagliXlsByIstanze(List<Integer> ids) {
		String sql = "SELECT * FROM idf_v_int_selvicolturali " + getWhereInClausole(ids);
		logger.info("getTagliXlsByIstanze sql: " + sql);
		return DBUtil.jdbcTemplate.query(sql, new TagliXlsMapper());
	}

	@Override
	public List<TagliSpecieXls> getTagliSpecieXlsByIstanze(List<Integer> ids) {
		String sql = "SELECT * FROM idf_v_int_selvicolt_specie " + getWhereInClausole(ids);
		logger.info("getTagliSpecieXlsByIstanze sql: " + sql);
		return DBUtil.jdbcTemplate.query(sql, new TagliSpecieXlsMapper());
	}

	@Override
	public List<TagliCatastoXls> getTagliCatastoXlsByIstanze(List<Integer> ids) {
		String sql = "SELECT * FROM idf_v_int_selvicolt_catasto " + getWhereInClausole(ids);
		logger.info("getTagliCatastoXlsByIstanze sql: " + sql);
		return DBUtil.jdbcTemplate.query(sql, new TagliCatastoXlsMapper());

	}
}
