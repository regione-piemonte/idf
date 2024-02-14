/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.FasciaAltimetricaDAO;
import it.csi.idf.idfapi.dto.FasciaAltimetrica;
import it.csi.idf.idfapi.mapper.FasciaAltimetricaMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class FasciaAltimetricaDAOImpl implements FasciaAltimetricaDAO{

	@Override
	public List<FasciaAltimetrica> findAllFasciaAltimetrica() {
		
		String query = "SELECT * FROM idf_d_fascia_altimetrica where flg_visibile = 1 order by mtd_ordinamento ";
		
		return DBUtil.jdbcTemplate.query(query, new FasciaAltimetricaMapper());
	}

	@Override
	public FasciaAltimetrica getFasciaAltimetrica(Integer idFascia) {
		String query = "SELECT * FROM idf_d_fascia_altimetrica where flg_visibile = 1 and id_fascia_altimetrica = ? ";
		try {
			return DBUtil.jdbcTemplate.queryForObject(query, new FasciaAltimetricaMapper(), idFascia);
		}
		catch(Exception ex) {
			return null;
		}
	}
}
