/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.SupForestaleDAO;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class SupForestaleDAOImpl implements SupForestaleDAO {

	@Override
	public int getTipoForestaleIntersectPropCatasto(int idIntervento) {
		try {
			StringBuilder sql = new StringBuilder();

			sql.append("SELECT DISTINCT supfor.fk_tipo_forestale");
			sql.append(" FROM idf_geo_pl_sup_forestale supfor");
			sql.append(
					" JOIN idf_geo_pl_prop_catasto propcat ON ST_Intersects(supfor.geometria, propcat.geometria)");
			sql.append(
					" JOIN idf_r_propcatasto_intervento propcatint ON propcat.idgeo_pl_prop_catasto = propcatint.idgeo_pl_prop_catasto");
			sql.append(" WHERE propcatint.id_intervento = ?");

			return DBUtil.jdbcTemplate.queryForObject(sql.toString(),Integer.class, idIntervento);
		} catch (EmptyResultDataAccessException e) {
			return -1;
		}
	}
}
