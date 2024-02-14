/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.TipoIstanzaDAO;
import it.csi.idf.idfapi.dto.TipoIstanzaResource;
import it.csi.idf.idfapi.mapper.TipoIstanzaResourceMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class TipoIstanzaDAOImpl implements TipoIstanzaDAO {

	private final TipoIstanzaResourceMapper tipoIstanzaResMapper = new TipoIstanzaResourceMapper();
	
	@Override
	public List<TipoIstanzaResource> getTrasformazioneTipo() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id_tipo_istanza, descr_dett_tipoistanza");
		sql.append(" FROM idf_d_tipo_istanza");
		sql.append(" WHERE id_tipo_istanza = 1");
		
		return Collections.singletonList(DBUtil.jdbcTemplate.queryForObject(sql.toString(), tipoIstanzaResMapper));
	}
	
	@Override
	public List<TipoIstanzaResource> getTipiIstanzaByUser(Integer idCnfUtente) {
		StringBuilder sql = new StringBuilder();
		sql.append("select id_tipo_istanza, CONCAT(ambist.descr_ambito_istanza,': ' ,descr_dett_tipoistanza)");
		sql.append(" as  descr_dett_tipoistanza ");
		sql.append(" FROM idf_d_tipo_istanza ist");
		sql.append(" inner join idf_d_ambito_istanza ambist on ambist.id_ambito_istanza = ist.fk_ambito_istanza ");
		sql.append(" inner join idf_cnf_configutente_ambitoist amb on amb.id_ambito_istanza = ist.fk_ambito_istanza");
		sql.append(" where amb.id_config_utente = ? order by ist.id_tipo_istanza ");
		
		return DBUtil.jdbcTemplate.query(sql.toString(), tipoIstanzaResMapper, idCnfUtente);
	}

	@Override
	public List<TipoIstanzaResource> getTipiIstanzaAttiveAmbito(Integer idAmbito) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id_tipo_istanza, descr_dett_tipoistanza");
		sql.append(" FROM idf_d_tipo_istanza");
		sql.append(" WHERE fk_ambito_istanza = ? and flg_visibile = 1 order by mtd_ordinamento " );

		return DBUtil.jdbcTemplate.query(sql.toString(), tipoIstanzaResMapper, idAmbito);
	}

	@Override
	public List<TipoIstanzaResource> getAllTipoIstanza() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id_tipo_istanza, descr_dett_tipoistanza");
		sql.append(" FROM idf_d_tipo_istanza");
		
		return DBUtil.jdbcTemplate.query(sql.toString(), tipoIstanzaResMapper);
	}

	
}
