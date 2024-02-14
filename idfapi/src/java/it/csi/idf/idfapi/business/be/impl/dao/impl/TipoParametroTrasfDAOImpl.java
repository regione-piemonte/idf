/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.TipoParametroTrasfDAO;
import it.csi.idf.idfapi.dto.TipoParametroTrasf;
import it.csi.idf.idfapi.mapper.TipoParametroTrasfMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class TipoParametroTrasfDAOImpl implements TipoParametroTrasfDAO {
	
	private final TipoParametroTrasfMapper tipoParametroTrasfMapper = new TipoParametroTrasfMapper();

	@Override
	public List<TipoParametroTrasf> getTipoParametroTrasfs() {
		StringBuilder sql = new StringBuilder("SELECT id_tipo_paramero_trasf, tipo_paremetro_trasf, mtd_ordinamento, flg_visibile");
		sql.append(" FROM idf_d_tipo_parametro_trasf WHERE id_tipo_paramero_trasf <> 0");
		sql.append(" ORDER BY mtd_ordinamento");
		
		return DBUtil.jdbcTemplate.query(sql.toString(), tipoParametroTrasfMapper);
	}
}
