/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.TipoInterventoDAO;
import it.csi.idf.idfapi.dto.TipoIntervento;
import it.csi.idf.idfapi.mapper.TipoInterventoMapper;
import it.csi.idf.idfapi.util.ConformeDerogaEnum;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class TipoInterventoDAOImpl implements TipoInterventoDAO {

	private final TipoInterventoMapper tipoInterventoMapper = new TipoInterventoMapper();

	@Override
	public List<TipoIntervento> findAllTipoIntervento() {
		String sql = "SELECT * FROM idf_d_tipo_intervento WHERE flg_visibile = 1 ORDER BY mtd_ordinamento";
		return DBUtil.jdbcTemplate.query(sql, tipoInterventoMapper);
	}

	@Override
	public List<TipoIntervento> findAllTipoInterventoCompetenzaRegionale() {
		String sql = "SELECT * FROM idf_d_tipo_intervento WHERE cod_intervento = 'R' ORDER BY mtd_ordinamento";
		return DBUtil.jdbcTemplate.query(sql, tipoInterventoMapper);
	}

	@Override
	public Integer findConfigIplaByTipoIntervento(Integer idTipoIntervento) {
		String sql = "SELECT fk_config_ipla FROM idf_d_tipo_intervento WHERE id_tipo_intervento = ?";
		return DBUtil.jdbcTemplate.queryForObject(sql, Integer.class, idTipoIntervento);
	}

	@Override
	public List<TipoIntervento> findAllTipoInterventoByFkConfigIpla(Integer fkConfigIpla) {
		String sql = "SELECT * FROM idf_d_tipo_intervento WHERE flg_visibile = 1 AND fk_config_ipla = ? ORDER BY mtd_ordinamento";
		return DBUtil.jdbcTemplate.query(sql, new Object[] { fkConfigIpla }, tipoInterventoMapper);
	}

	@Override
	public List<TipoIntervento> findAllTipoInterventoByGovernoAndFkConfigIpla(Integer fkConfigIpla, Integer idGoverno) {
		String sql = "SELECT itv.* FROM idf_d_tipo_intervento itv "
				+ " inner join idf_r_governo_tipointerv gov on itv.id_tipo_intervento = gov.id_tipo_intervento "
				+ " WHERE flg_visibile = 1 AND fk_config_ipla = ? and gov.id_governo  = ? ORDER BY mtd_ordinamento";
		return DBUtil.jdbcTemplate.query(sql, new Object[] { fkConfigIpla, idGoverno }, tipoInterventoMapper);
	}

	@Override
	public List<TipoIntervento> findAllByGovernoAndFkConfigIplaAndMacroIntervento(Integer fkConfigIpla,
			Integer idGoverno, Integer idMacroIntervento) {
		String sql = "SELECT itv.* FROM idf_d_tipo_intervento itv "
				+ " inner join idf_r_governo_tipointerv gov on itv.id_tipo_intervento = gov.id_tipo_intervento "
				+ " WHERE flg_visibile = 1 AND fk_macrointervento = ? AND fk_config_ipla = ? and gov.id_governo  = ? ORDER BY mtd_ordinamento";
		return DBUtil.jdbcTemplate.query(sql, new Object[] { idMacroIntervento, fkConfigIpla, idGoverno },
				tipoInterventoMapper);
	}

	@Override
	public List<TipoIntervento> findAllByFkConfigIplaAndCategoriaIntervento(Integer fkConfigIpla,
			Integer idCategoriaIntervento) {
		String sql = "SELECT itv.* " + "FROM idf_d_tipo_intervento itv "
				+ "JOIN idf_d_macrointervento idm ON idm.id_macrointervento = itv.fk_macrointervento "
				+ "JOIN idf_d_categ_interv_selvicolturale idcis ON idcis.id_categ_intervento = idm.fk_categ_intervento  "
				+ "WHERE itv.flg_visibile = 1 AND itv.fk_config_ipla = ? AND idcis.id_categ_intervento = ? ORDER BY mtd_ordinamento";
		return DBUtil.jdbcTemplate.query(sql, new Object[] { fkConfigIpla, idCategoriaIntervento },
				tipoInterventoMapper);
	}

	// GP 20062023
	@Override
	public List<HashMap> getTipoInterventoConformeDeroga() {

		List<HashMap> ltsMap2 = new ArrayList<>();
		List<HashMap> ltsMapr = new ArrayList<>();
		HashMap<String, Object> instMap1 = new HashMap<>();
		instMap1.put("Key", "Conforme");
		instMap1.put("Value", ConformeDerogaEnum.C.toString());

		ltsMap2.add(instMap1);
		HashMap<String, Object> instMap2 = new HashMap<>();
		instMap2.put("Key", "Deroga");
		instMap2.put("Value", ConformeDerogaEnum.D.toString());

		ltsMap2.add(instMap2);

		return ltsMap2;
	}
}
