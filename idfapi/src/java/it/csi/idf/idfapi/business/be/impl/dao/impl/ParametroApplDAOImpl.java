/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.ParametroApplDAO;
import it.csi.idf.idfapi.dto.ParametroAppl;
import it.csi.idf.idfapi.mapper.ParametroApplMapper;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.TipoParametroApplEnum;

@Component
public class ParametroApplDAOImpl implements ParametroApplDAO {

	@Override
	public ParametroAppl getParamertroByTipo(TipoParametroApplEnum tipoParametroAppl) {
		StringBuilder sql = new StringBuilder("SELECT * FROM idf_cnf_parametro_appl");
		sql.append(" WHERE fk_tipo_param_appl =");
		sql.append(" (SELECT id_tipo_param_appl FROM idf_cnf_tipo_param_appl");
		sql.append(" WHERE descr_tipo_param_appl = ?)");

		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), new ParametroApplMapper(),
				tipoParametroAppl.toString());
	}

	@Override
	public List<ParametroAppl> getParamertriByTipo(TipoParametroApplEnum tipoParametroAppl) {
		StringBuilder sql = new StringBuilder("SELECT * FROM idf_cnf_parametro_appl");
		sql.append(" WHERE fk_tipo_param_appl =");
		sql.append(" (SELECT id_tipo_param_appl FROM idf_cnf_tipo_param_appl");
		sql.append(" WHERE descr_tipo_param_appl = ?) ORDER BY id_parametro_appl");

		return DBUtil.jdbcTemplate.query(sql.toString(), new ParametroApplMapper(), tipoParametroAppl.toString());
	}
}
