/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import it.csi.idf.idfapi.business.be.impl.dao.TipoViabUsoviabInterventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.UsoviabInterventoselvDAO;
import it.csi.idf.idfapi.util.DBUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class TipoViabUsoviabInterventoDAOImpl implements TipoViabUsoviabInterventoDAO {

	final static Logger logger = Logger.getLogger(TipoViabUsoviabInterventoDAOImpl.class);

	@Override
	public int createTipoViabIntervento(Integer idTipoViabilita, Integer idIntervento, Integer fkUsoViabilita) {

		StringBuilder query = new StringBuilder();

		query.append("INSERT INTO idf_r_int_usoviab_tipoviab(");
		query.append("id_tipo_viabilita, id_intervento, fk_uso_viabilita, data_inserimento) ");
		query.append("VALUES (?, ?, ?, ?)");

		List<Object> parameters = new ArrayList<>();

		parameters.add(idTipoViabilita);
		parameters.add(idIntervento);
		parameters.add(fkUsoViabilita);
		parameters.add(java.sql.Date.valueOf(LocalDate.now()));

		logger.info("into create idf_r_int_usoviab_tipoviab");

		return DBUtil.jdbcTemplate.update(query.toString(), parameters.toArray());
	}

	@Override
	public void deleteTipoViabIntervento(Integer idIntervento) {

		String sql = "DELETE FROM idf_r_int_usoviab_tipoviab WHERE id_intervento = ?";

		DBUtil.jdbcTemplate.update(sql, idIntervento);
	}


	@Override
	public boolean existsTipoViabilitaIntervento(Integer idIntervento, Integer idTipoViabilita, Integer fkUsoViabilita) {
		String sql = "select count(*) from idf_r_int_usoviab_tipoviab where id_intervento = ? and id_tipo_viabilita = ? and fk_uso_viabilita = ?";
		int res = DBUtil.jdbcTemplate.queryForInt(sql.toString() ,idIntervento, idTipoViabilita, fkUsoViabilita);
		return res > 0;
	}


	@Override
	public List<Integer> findAllIdsByInterventoAndUsoViab(Integer idIntervento, Integer fkUsoViabilita) {
		String sql = "select id_tipo_viabilita from idf_r_int_usoviab_tipoviab where id_intervento = ? and fk_uso_viabilita = ?";
		return DBUtil.jdbcTemplate.queryForList(sql, new Object[] { idIntervento, fkUsoViabilita }, Integer.class);
	}
}
