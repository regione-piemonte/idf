/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.ProprietaDAO;
import it.csi.idf.idfapi.dto.Proprieta;
import it.csi.idf.idfapi.mapper.ProprietaMapper;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.IplaEnum;
@Component
public class ProprietaDAOImpl implements ProprietaDAO {
	
	@Override
	public List<Proprieta> findAllProprieta() {
		String SQL = "SELECT * FROM idf_d_proprieta WHERE flg_visibile = 1 order by mtd_ordinamento";
		return DBUtil.jdbcTemplate.query(SQL, new ProprietaMapper());	
	}
	
	@Override
	public List<Proprieta> findAllProprietaByIpla(IplaEnum ipla) {
		String SQL = "SELECT * FROM idf_d_proprieta where fk_config_ipla = ? and flg_visibile = 1 order by mtd_ordinamento";
		return DBUtil.jdbcTemplate.query(SQL, new ProprietaMapper(),ipla.getTypeValue());	
	}

	@Override
	public Proprieta findById(Integer idProprieta) {
		String sql = "SELECT * FROM idf_d_proprieta WHERE id_proprieta = ? AND flg_visibile = 1 ";
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), new ProprietaMapper(), idProprieta);
	}
}
