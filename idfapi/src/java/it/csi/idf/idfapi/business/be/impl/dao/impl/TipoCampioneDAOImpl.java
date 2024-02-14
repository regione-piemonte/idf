/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.TipoCampioneDAO;
import it.csi.idf.idfapi.dto.TipoCampione;
import it.csi.idf.idfapi.mapper.TipoCampioneMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class TipoCampioneDAOImpl implements TipoCampioneDAO {

	@Override
	public List<TipoCampione> findAllTipoCampione(Byte flgVisibile) {
		
		if (flgVisibile == null) {
			flgVisibile = 1;
		}
		String sql = new String("SELECT * FROM idf_d_tipo_campione WHERE flg_visibile = ?");		
		return DBUtil.jdbcTemplate.query(sql, new TipoCampioneMapper(), flgVisibile);
		
	}

}
