/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.TipoEventoDAO;
import it.csi.idf.idfapi.dto.TipoEvento;
import it.csi.idf.idfapi.mapper.TipoEventoMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class TipoEventoDAOImpl implements TipoEventoDAO{

	@Override
	public List<TipoEvento> findAllTipoEventi() {

		String query = "SELECT * FROM idf_d_tipo_evento";
		
		return DBUtil.jdbcTemplate.query(query, new TipoEventoMapper());
	}

	
}
