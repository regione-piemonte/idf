/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.AmbitoRilievoDAO;
import it.csi.idf.idfapi.dto.AmbitoRilievo;
import it.csi.idf.idfapi.mapper.AmbitoRilievoMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class AmbitoRilievoDAOImpl implements AmbitoRilievoDAO {
	
	@Override
	public List<AmbitoRilievo> findAllAmbitoRilievo() {
		String SQL = "SELECT * FROM idf_d_ambito_rilievo WHERE fk_padre=0 and flg_visibile = 1 order by mtd_ordinamento";
		return DBUtil.jdbcTemplate.query(SQL, new AmbitoRilievoMapper());	
	}
	
	@Override
	public List<AmbitoRilievo> findAmbitoRilievoSpecificare() {
		String SQL = "SELECT * FROM idf_d_ambito_rilievo WHERE fk_padre=0 and flg_specificare = 1 "
				+ "and flg_visibile = 1 order by mtd_ordinamento";
		return DBUtil.jdbcTemplate.query(SQL, new AmbitoRilievoMapper());	
	}
	
	@Override
	public AmbitoRilievo saveNewAmbito(AmbitoRilievo ambito) {
		String SQL = "Insert into idf_d_ambito_rilievo( id_ambito, descr_ambito, flg_visibile, fk_padre) "
				+ "VALUES ((select max(id_ambito)+1 from idf_d_ambito_rilievo)"
				+ ",?,?,?)";
		List<Object> parameters = new ArrayList<>();
		parameters.add(ambito.getDescrAmbito());
		parameters.add(1);
		parameters.add(ambito.getFkPadre());
		DBUtil.jdbcTemplate.update(SQL, parameters.toArray());
		/* TODO: add generated key to be able to return it.  */
		return ambito;
	}
	
	@Override
	public List<AmbitoRilievo> findChildAmbitos(int fkPadre) {
		String SQL = "SELECT * FROM idf_d_ambito_rilievo WHERE fk_padre=? and flg_visibile = 1 order by mtd_ordinamento";
		List<Object> parameters = new ArrayList<>();
		parameters.add(fkPadre);
		return DBUtil.jdbcTemplate.query(SQL, new AmbitoRilievoMapper(),parameters.toArray());
	}

	@Override
	public List<AmbitoRilievo> findAmbitoByPadreAndDescr(int fkPadre, String desc) {
		String SQL = "SELECT * FROM idf_d_ambito_rilievo WHERE fk_padre=? and lower(descr_ambito) = ? "
				+ "and flg_visibile = 1 order by mtd_ordinamento";
		List<Object> parameters = new ArrayList<>();
		parameters.add(fkPadre);
		parameters.add(desc.toLowerCase());
		return DBUtil.jdbcTemplate.query(SQL, new AmbitoRilievoMapper(),parameters.toArray());
	}
	

	
}
