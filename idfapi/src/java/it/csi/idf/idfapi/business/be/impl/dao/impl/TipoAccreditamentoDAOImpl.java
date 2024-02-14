/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.TipoAccreditamentoDAO;
import it.csi.idf.idfapi.dto.TipoAccreditamento;
import it.csi.idf.idfapi.mapper.TipoAccreditamentoMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class TipoAccreditamentoDAOImpl implements TipoAccreditamentoDAO {

	@Override
	public TipoAccreditamento getTipoAccreditamentoById(int tipoAccreditamento) {
		String sql = "SELECT * FROM idf_cnf_tipo_accreditamento ta WHERE ta.id_tipo_accreditamento = ?";
		return DBUtil.jdbcTemplate.queryForObject(sql, new TipoAccreditamentoMapper(), tipoAccreditamento);
	}
}
