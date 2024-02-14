/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.impl.dao.TipoForestaleDAO;
import it.csi.idf.idfapi.dto.TipoForestale;
import it.csi.idf.idfapi.mapper.TipoForestaleMapper;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.IplaEnum;

@Component
public class TipoForestaleDAOImpl implements TipoForestaleDAO {
	@Override
	public List<TipoForestale> findAllTipoForestale() {
		String sql = "SELECT * FROM idf_t_tipo_forestale";
		return DBUtil.jdbcTemplate.query(sql, new TipoForestaleMapper());
		
	}
	
	@Override
	public List<TipoForestale> findAllTipoForestaleByIpla(IplaEnum ipla) {
		String sql = "SELECT * FROM idf_t_tipo_forestale where fk_config_ipla = ?";
		return DBUtil.jdbcTemplate.query(sql, new TipoForestaleMapper(),ipla.getTypeValue());
		
	}

	@Override
	public List<TipoForestale> findAllTipoByCategoria(Integer categoriaForestale) throws RecordNotFoundException {
		String query = "SELECT * FROM idf_t_tipo_forestale f WHERE f.fk_categoria_forestale = ?";
		
		return DBUtil.jdbcTemplate.query(query, new TipoForestaleMapper(), categoriaForestale);
	}
	
	@Override
	public List<TipoForestale> findAllTipoByCategoriaAndIpla(Integer categoriaForestale, IplaEnum ipla) 
			throws RecordNotFoundException {
		String query = "SELECT * FROM idf_t_tipo_forestale f WHERE f.fk_categoria_forestale = ? and fk_config_ipla = ? ";
		
		return DBUtil.jdbcTemplate.query(query, new TipoForestaleMapper(), categoriaForestale, ipla.getTypeValue());
	}

	@Override
	public TipoForestale getTipoForestaleById(int idTipoForestale) {
		String sql = "SELECT * FROM idf_t_tipo_forestale WHERE id_tipo_forestale = ?";
		
		return DBUtil.jdbcTemplate.queryForObject(sql, new TipoForestaleMapper(), idTipoForestale);
	}
}
