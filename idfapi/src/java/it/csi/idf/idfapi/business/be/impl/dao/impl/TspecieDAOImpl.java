/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import it.csi.idf.idfapi.mapper.TipoForestaleMapper;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.SpecieDAO;
import it.csi.idf.idfapi.dto.GruppoSpecie;
import it.csi.idf.idfapi.dto.TSpecie;
import it.csi.idf.idfapi.mapper.GruppoSpecieMapper;
import it.csi.idf.idfapi.mapper.TSpecieMapper;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.IplaEnum;

@Component
public class TspecieDAOImpl implements SpecieDAO {

	@Override
	public List<TSpecie> findAllSpecie() {
		String SQL = "SELECT * FROM idf_t_specie WHERE flg_visibile = 1 order by mtd_ordinamento";
		return DBUtil.jdbcTemplate.query(SQL, new TSpecieMapper());
	}
	
	@Override
	public List<TSpecie> findAllSpecieByIpla(IplaEnum ipla) {
		String SQL = "SELECT * FROM idf_t_specie where fk_config_ipla = ? and flg_visibile = 1 order by mtd_ordinamento";
		return DBUtil.jdbcTemplate.query(SQL, new TSpecieMapper(),ipla.getTypeValue());
	}
	
	@Override
	public List<GruppoSpecie> findAllGruppoSpecie() {
		String SQL = "SELECT * FROM idf_d_gruppo";
		return DBUtil.jdbcTemplate.query(SQL, new GruppoSpecieMapper());
		
	}

	@Override
	public TSpecie getSpecie(Integer idSpecie) {
		String SQL = "SELECT * FROM idf_t_specie WHERE id_specie = ?";
		return DBUtil.jdbcTemplate.queryForObject(SQL, new TSpecieMapper(), idSpecie);
	}
}
