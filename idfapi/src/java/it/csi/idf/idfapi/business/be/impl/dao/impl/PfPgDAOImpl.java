/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.impl.dao.PfPgDAO;
import it.csi.idf.idfapi.dto.PfPg;
import it.csi.idf.idfapi.mapper.PfPgMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class PfPgDAOImpl implements PfPgDAO {

	@Override
	public void createPfPg(PfPg pfPg) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO idf_r_pf_pg(");
		sql.append("id_soggetto_pf, id_soggetto_pg, data_inizio_validita, data_fine_validita, data_inserimento");
		sql.append(", data_aggiornamento, fk_config_utente");
		sql.append(") VALUES(?,?,?,?,?,?,?)");
		
		List<Object> parameters = new ArrayList<>();
		parameters.add(pfPg.getIdSoggettoPf());
		parameters.add(pfPg.getIdSoggettoPg());
		parameters.add(pfPg.getDataInizioValidita() == null ? null : Date.valueOf(pfPg.getDataInizioValidita()));
		parameters.add(pfPg.getDataFineValidita() == null ? null : Date.valueOf(pfPg.getDataFineValidita()));
		parameters.add(pfPg.getDataInserimento() == null ? null : Date.valueOf(pfPg.getDataInserimento()));
		parameters.add(pfPg.getDataAggiornamento() == null ? null : Date.valueOf(pfPg.getDataAggiornamento()));
		parameters.add(pfPg.getFkConfigUtente());
		
		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}

	@Override
	public PfPg getBySoggettoPfAndSoggettoPg(int idSoggetoPf, int idSoggettoPg) throws RecordNotUniqueException {
		String sql = "SELECT * FROM idf_r_pf_pg WHERE id_soggetto_pf = ? AND id_soggetto_pg = ?";
		
		List<PfPg> pfPgs = DBUtil.jdbcTemplate.query(sql, new PfPgMapper(), idSoggetoPf, idSoggettoPg);
		
		if(pfPgs.isEmpty()) {
			return null;
		} else if(pfPgs.size() == 1) {
			return pfPgs.get(0);
		} else {
			throw new RecordNotUniqueException();
		}
	}
}
