/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.PopolamentoSemeDAO;
import it.csi.idf.idfapi.dto.InterventoPopSeme;
import it.csi.idf.idfapi.dto.PopolamentoSeme;
import it.csi.idf.idfapi.mapper.PopolamentoSemeMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class PopolamentoSemeDAOImpl implements PopolamentoSemeDAO {

	@Override
	public PopolamentoSeme getByCodiceAmministrativo(String codiceAmministrativo) {
		StringBuilder sql = new StringBuilder("SELECT id_popolamento_seme, codice_amministrativo, denominazione");
		sql.append(", specie_idonee, data_modifica, data_fine_validita");
		sql.append(" FROM idf_geo_pl_popolamento_seme WHERE codice_amministrativo = ?");
		
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), new PopolamentoSemeMapper(), codiceAmministrativo);
	}

	@Override
	public List<PopolamentoSeme> getAllByInterventoPopSemes(List<InterventoPopSeme> popolamentoSemes) {
		StringBuilder sql = new StringBuilder("SELECT id_popolamento_seme, codice_amministrativo, denominazione");
		sql.append(", specie_idonee, data_modifica, data_fine_validita");
		sql.append(" FROM idf_geo_pl_popolamento_seme WHERE id_popolamento_seme IN(");
		
		for(int i = 0; i < popolamentoSemes.size(); i++) {
			sql.append(popolamentoSemes.get(i).getIdPopolamentoSeme());
			if(i != popolamentoSemes.size() - 1) {
				sql.append(",");
			}
		}
		sql.append(")");
		
		return DBUtil.jdbcTemplate.query(sql.toString(), new PopolamentoSemeMapper());
	}
}
