/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.CategoriaProfessionaleDAO;
import it.csi.idf.idfapi.dto.CategoriaProfessionale;
import it.csi.idf.idfapi.mapper.CategoriaProfessionaleMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class CategoriaProfessionaleDAOImpl implements CategoriaProfessionaleDAO {

	private final CategoriaProfessionaleMapper categoriaProfessionaleMapper = new CategoriaProfessionaleMapper();

	@Override
	public List<CategoriaProfessionale> findAllCategoriaProfessionale() {
		String sql = "SELECT * FROM idf_d_categoria_professionale WHERE flg_visibile = 1 ORDER BY mtd_ordinamento";
		return DBUtil.jdbcTemplate.query(sql, categoriaProfessionaleMapper);
	}
	@Override
	public CategoriaProfessionale getCategoriaProfessionaleById(Integer id) {
		String sql = "SELECT * FROM idf_d_categoria_professionale WHERE id_categoria_professionale = ?";
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), categoriaProfessionaleMapper, id);
	}

}
