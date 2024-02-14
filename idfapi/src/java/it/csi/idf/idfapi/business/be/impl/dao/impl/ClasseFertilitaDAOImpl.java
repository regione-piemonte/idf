/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.ClasseFertilitaDAO;
import it.csi.idf.idfapi.dto.ClasseFertilita;
import it.csi.idf.idfapi.mapper.ClasseFertilitaMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class ClasseFertilitaDAOImpl implements ClasseFertilitaDAO {

	@Override
	public List<ClasseFertilita> findAll() {
		String sql = "SELECT * FROM idf_d_classe_fertilita WHERE flg_visibile=1";
		return DBUtil.jdbcTemplate.query(sql, new ClasseFertilitaMapper());		
	}

}
