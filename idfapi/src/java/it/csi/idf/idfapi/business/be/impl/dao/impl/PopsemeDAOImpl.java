/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.PopsemeDAO;
import it.csi.idf.idfapi.dto.Popseme;
import it.csi.idf.idfapi.mapper.PopsemeMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class PopsemeDAOImpl implements PopsemeDAO {

	private final PopsemeMapper popsemeMapper = new PopsemeMapper();
	
	@Override
	public List<Popseme> getFatPopsemeByIdgeoPlPfa(int idgeoPlPfa) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT popseme.idgeo_pl_pfa, popseme.id_popolamento_seme, popseme.data_inizio_validita, popseme.data_fine_validita");
		sql.append(", popseme.sup_ricadenza_ha, popseme.perc_ricadenza, seme.denominazione");
		sql.append(" FROM idf_r_pfa_popseme popseme");
		sql.append(" JOIN idf_geo_pl_popolamento_seme seme ON seme.id_popolamento_seme = popseme.id_popolamento_seme");
		sql.append(" WHERE popseme.idgeo_pl_pfa = ?");
		
		return DBUtil.jdbcTemplate.query(sql.toString(), popsemeMapper, idgeoPlPfa);
	}
}
