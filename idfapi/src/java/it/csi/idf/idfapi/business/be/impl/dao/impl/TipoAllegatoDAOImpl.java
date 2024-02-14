/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.TipoAllegatoDAO;
import it.csi.idf.idfapi.dto.TipoAllegato;
import it.csi.idf.idfapi.mapper.TipoAllegatoMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class TipoAllegatoDAOImpl implements TipoAllegatoDAO {

	private final TipoAllegatoMapper tipoAllegatoMapper = new TipoAllegatoMapper();

	@Override
	public TipoAllegato getTipoById(int idTipoAllegato) {
		String sql = "SELECT id_tipo_allegato, descr_tipo_allegato FROM idf_d_tipo_allegato WHERE id_tipo_allegato = ?";
		return DBUtil.jdbcTemplate.queryForObject(sql, tipoAllegatoMapper, idTipoAllegato);
	}

	@Override
	public List<TipoAllegato> getAllTipoAllegato() {
		String sql = "SELECT id_tipo_allegato, descr_tipo_allegato FROM idf_d_tipo_allegato";
		return DBUtil.jdbcTemplate.query(sql, tipoAllegatoMapper);

	}
	
	@Override
	public TipoAllegato getAllTipoAllegatoTipoById(Integer idTipoAllegato) {
		//String sql = "SELECT id_tipo_allegato, descr_tipo_allegato FROM idf_d_tipo_allegato WHERE id_tipo_allegato = ?";
		String sql = "SELECT id_tipo_allegato,descr_tipo_allegato FROM idf_d_tipo_allegato where fk_origine_allegato = 5 and flg_visibile in(0) and fk_tipo_allegato_padre =? and mtd_ordinamento is not null order by mtd_ordinamento asc";
		return DBUtil.jdbcTemplate.queryForObject(sql, tipoAllegatoMapper,Integer.valueOf(idTipoAllegato) );
	}


	@Override
	public List<TipoAllegato> getAllTipoAllegatoPfa() {
		String sql = "SELECT id_tipo_allegato, descr_tipo_allegato FROM idf_d_tipo_allegato " +
				" where fk_origine_allegato = 5 and flg_visibile = 1 	  order by mtd_ordinamento";
		return DBUtil.jdbcTemplate.query(sql, tipoAllegatoMapper);
	}

	@Override
	public List<Integer> getIdsTipiAllegatoConFirmaDigitale() {
		StringBuilder sql = new StringBuilder("SELECT id_tipo_allegato FROM idf_d_tipo_allegato ");
		sql.append(" where descr_tipo_allegato like '%firmato digitalmente' ");
		sql.append("or descr_tipo_allegato like '%per firma digitale' ");
		sql.append("order by mtd_ordinamento");
		return DBUtil.jdbcTemplate.queryForList(sql.toString(), Integer.class);
	}
}
