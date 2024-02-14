/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import it.csi.idf.idfapi.business.be.impl.dao.IntervSelvicoulturaleDAO;
import it.csi.idf.idfapi.business.be.impl.dao.IntervSelvicoulturaleTraferimentiDAO;
import it.csi.idf.idfapi.dto.*;
import it.csi.idf.idfapi.mapper.AdsrelSpecieMapper;
import it.csi.idf.idfapi.mapper.DettagliDiTaglioMapper;
import it.csi.idf.idfapi.mapper.IntervSelvicolturaleMapper;
import it.csi.idf.idfapi.mapper.RicadenzaInfoMapper;
import it.csi.idf.idfapi.util.DBUtil;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class IntervSelvicoulturaleTrasferimentiDAOImpl implements IntervSelvicoulturaleTraferimentiDAO {


	@Override
	public void insert(Integer idInterventoSelv, Integer idInterventoTrasf, ConfigUtente loggedConfig) {
		StringBuilder sql = new StringBuilder();

		sql.append("INSERT INTO idf_r_int_selv_trasf(");
		sql.append("id_intervento_selv, id_intervento_trasf, ");
		sql.append("data_inserimento, data_aggiornamento, fk_config_utente )");
		sql.append(" VALUES (?,?,?,?,?)");

		List<Object> parameters = new ArrayList<>();
		parameters.add(idInterventoSelv);
		parameters.add(idInterventoTrasf);
		parameters.add(Date.valueOf(LocalDate.now()));
		parameters.add(Date.valueOf(LocalDate.now()));
		parameters.add(loggedConfig.getIdConfigUtente());

		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());

	}


	@Override
	public int deleteByIdInterventoSelv(Integer idInterventoSelv) {
		String SQL = "DELETE FROM idf_r_int_selv_trasf where id_intervento_selv = ? ";
		return DBUtil.jdbcTemplate.update(SQL, idInterventoSelv);
	}
}
