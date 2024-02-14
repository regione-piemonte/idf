/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.IstanzaCompensazioneDAO;
import it.csi.idf.idfapi.dto.IstanzaCompensazione;
import it.csi.idf.idfapi.mapper.IstanzaCompensazioneMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class IstanzaCompensazioneDAOImpl implements IstanzaCompensazioneDAO {

	@Override
	public void insertIstanzaCompensazione(IstanzaCompensazione istanzaCompensazione) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO idf_temp_istanza_compensazione(");
		sql.append("num_istanza_compensazione, fk_intervento, data_inserimento");
		sql.append(", data_aggiornamento, fk_config_utente, data_presentazione, data_autorizzazione");
		sql.append(") VALUES(?,?,?,?,?,?,?)");
		
		List<Object> parameters = new ArrayList<>();
		parameters.add(istanzaCompensazione.getNumIstanzaCompensazione());
		parameters.add(istanzaCompensazione.getFkIntervento());
		parameters.add(istanzaCompensazione.getDataInserimento() == null ? null : Date.valueOf(istanzaCompensazione.getDataInserimento()));
		parameters.add(istanzaCompensazione.getDataAggiornamento() == null ? null : Date.valueOf(istanzaCompensazione.getDataAggiornamento()));
		parameters.add(istanzaCompensazione.getFkConfigUtente());
		parameters.add(istanzaCompensazione.getDataPresentazione() == null ? null : Date.valueOf(istanzaCompensazione.getDataPresentazione()));
		parameters.add(istanzaCompensazione.getDataAutorizzazione() == null ? null : Date.valueOf(istanzaCompensazione.getDataAutorizzazione()));
		
		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}

	@Override
	public List<IstanzaCompensazione> getAllByFkIntervento(int fkIntervento) {
		String sql = "SELECT * FROM idf_temp_istanza_compensazione WHERE fk_intervento = ?";
		return DBUtil.jdbcTemplate.query(sql, new IstanzaCompensazioneMapper(), fkIntervento);
	}

	@Override
	public void deleteIstanza(int numIstanzaCompensazione) {
		String sql = "DELETE FROM idf_temp_istanza_compensazione WHERE num_istanza_compensazione = ?";
		DBUtil.jdbcTemplate.update(sql, numIstanzaCompensazione);
	}
	
	@Override
	public void deleteIstanzaByIntervento(int idIntervento) {
		String sql = "DELETE FROM idf_temp_istanza_compensazione WHERE fk_intervento = ?";
		DBUtil.jdbcTemplate.update(sql, idIntervento);
	}

	@Override
	public IstanzaCompensazione getByNumIstanza(int numIstanza) {
		String sql = "SELECT * FROM idf_temp_istanza_compensazione WHERE num_istanza_compensazione = ?";
		List<IstanzaCompensazione> list = DBUtil.jdbcTemplate.query(sql, new IstanzaCompensazioneMapper(), numIstanza);
		return list != null && list.size() > 0 ? list.get(0):null;
	}
}
