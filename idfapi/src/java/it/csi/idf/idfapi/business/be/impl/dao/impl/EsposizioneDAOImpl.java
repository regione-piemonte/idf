/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.EsposizioneDAO;
import it.csi.idf.idfapi.dto.Esposizione;
import it.csi.idf.idfapi.mapper.EsposizioneMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class EsposizioneDAOImpl implements EsposizioneDAO {

	@Override
	public List<Esposizione> findAllEsposizione() {
		String SQL = "SELECT * FROM idf_d_esposizione";
		return DBUtil.jdbcTemplate.query(SQL, new EsposizioneMapper());	
	}
	

}
