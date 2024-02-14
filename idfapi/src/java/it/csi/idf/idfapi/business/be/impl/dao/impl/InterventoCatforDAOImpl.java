/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.InterventoCatforDAO;
import it.csi.idf.idfapi.dto.InterventoCatfor;
import it.csi.idf.idfapi.mapper.InterventoCatforMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class InterventoCatforDAOImpl implements InterventoCatforDAO {

	@Override
	public int insertInterventoCatfor(InterventoCatfor interventoCatfor) {
		StringBuilder sql = new StringBuilder("INSERT INTO idf_r_intervento_catfor(");
		sql.append("id_intervento, id_categoria_forestale, data_inserimento, data_aggiornamento, fk_config_utente");
		sql.append(") VALUES(?,?,?,?,?)");
		
		List<Object> parameters = new ArrayList<>();
		parameters.add(interventoCatfor.getIdIntervento());
		parameters.add(interventoCatfor.getIdCategoriaForestale());
		parameters.add(interventoCatfor.getDataInserimento() == null ? java.sql.Date.valueOf(LocalDate.now()) : Date.valueOf(interventoCatfor.getDataInserimento()));
		parameters.add(interventoCatfor.getDataAggiornamento() == null ? java.sql.Date.valueOf(LocalDate.now()) : Date.valueOf(interventoCatfor.getDataAggiornamento()));
		parameters.add(interventoCatfor.getFkConfigUtente());		
		
		return DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}

	@Override
	public void deleteInterventosById(int idIntervento) {
		String sql = "DELETE FROM idf_r_intervento_catfor WHERE id_intervento = ?";
		DBUtil.jdbcTemplate.update(sql, idIntervento);
	}

	@Override
	public List<InterventoCatfor> getInterventosByIdIntervento(int idIntervento) {
		String sql = "SELECT * FROM idf_r_intervento_catfor WHERE id_intervento = ?";
		return DBUtil.jdbcTemplate.query(sql, new InterventoCatforMapper(), idIntervento);
	}

	@Override
	public void deleteByIdCategoriaAndIdIntervento(int idIntervento, int codiceAmministratico) {
		String sql = "DELETE FROM idf_r_intervento_catfor WHERE id_intervento = ? AND id_categoria_forestale = ?";
		DBUtil.jdbcTemplate.update(sql, idIntervento, codiceAmministratico);
	}
}
