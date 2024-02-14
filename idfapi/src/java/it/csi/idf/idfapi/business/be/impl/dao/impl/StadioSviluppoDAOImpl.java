/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.StadioSviluppoDAO;
import it.csi.idf.idfapi.dto.StadioSviluppo;
import it.csi.idf.idfapi.mapper.StadioSviluppoMapper;
import it.csi.idf.idfapi.util.DBUtil;
@Component
public class StadioSviluppoDAOImpl implements StadioSviluppoDAO {
	
	@Override
	public List<StadioSviluppo> findAllStadioSviluppo() {
		String SQL = "SELECT * FROM idf_d_stadio_sviluppo WHERE flg_visibile = 1 order by mtd_ordinamento";
		return DBUtil.jdbcTemplate.query(SQL, new StadioSviluppoMapper());	
	}

}
