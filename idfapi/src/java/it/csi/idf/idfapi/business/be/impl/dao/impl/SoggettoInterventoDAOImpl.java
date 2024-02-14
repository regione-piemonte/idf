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

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.impl.dao.SoggettoInterventoDAO;
import it.csi.idf.idfapi.dto.SoggettoIntervento;
import it.csi.idf.idfapi.dto.SoggettoResource;
import it.csi.idf.idfapi.mapper.SoggettoInterventoMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class SoggettoInterventoDAOImpl implements SoggettoInterventoDAO {

	@Override
	public int createSoggettoIntervento(SoggettoIntervento soggettoIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO idf_r_soggetto_intervento(");
		sql.append("id_intervento, id_soggetto, id_tipo_soggetto, data_inizio_validita, data_fine_validita, ");
		sql.append("data_inserimento, data_aggiornamento, fk_config_utente, nr_albo_forestale, fk_tipo_richiedente )");
		sql.append("VALUES(?,?,?,?,?,?,?,?,?,?)");

		List<Object> parameters = new ArrayList<>();

		parameters.add(soggettoIntervento.getIdIntervento());
		parameters.add(soggettoIntervento.getIdSoggetto());
		parameters.add(soggettoIntervento.getIdTipoSoggetto());
		parameters.add(soggettoIntervento.getDataInizioValidita() == null ? null
				: Date.valueOf(soggettoIntervento.getDataInizioValidita()));
		parameters.add(soggettoIntervento.getDataFineValidita() == null ? null
				: Date.valueOf(soggettoIntervento.getDataFineValidita()));
		parameters.add(soggettoIntervento.getDataInserimento() == null ? Date.valueOf(LocalDate.now())
				: Date.valueOf(soggettoIntervento.getDataInserimento()));
		parameters.add(soggettoIntervento.getDataAggiornamento() == null ? null
				: Date.valueOf(soggettoIntervento.getDataAggiornamento()));
		parameters.add(soggettoIntervento.getFkConfigUtente());
		parameters.add(soggettoIntervento.getNrAlboForestale());
		parameters.add(soggettoIntervento.getFkTipoRichiedente() == null ? 0 : soggettoIntervento.getFkTipoRichiedente());

		return DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}

	@Override
	public SoggettoIntervento getSoggettoInterventoByIdInterventoAndIdTipoSoggetto(Integer idIntervento, Integer idTipoSoggetto)
			throws RecordNotFoundException, RecordNotUniqueException {

		String sql = "SELECT * FROM idf_r_soggetto_intervento WHERE id_intervento = ? and id_tipo_soggetto = ?";


		List<Object> parameters = new ArrayList<>();
		parameters.add(idIntervento);
		parameters.add(idTipoSoggetto);

		List<SoggettoIntervento> interventos = DBUtil.jdbcTemplate.query(
				sql,
				new SoggettoInterventoMapper(),
				parameters.toArray());

		if (interventos.isEmpty()) {
			throw new RecordNotFoundException();
		} else if (interventos.size() > 1) {
			throw new RecordNotUniqueException();
		} else {
			return interventos.get(0);
		}
	}


	@Override
	public SoggettoIntervento getSoggettoInterventoById(Integer idIntervento) throws RecordNotFoundException, RecordNotUniqueException {
		return getSoggettoInterventoByIdInterventoAndIdTipoSoggetto(idIntervento, 1);
	}

	@Override
	public List<SoggettoIntervento> getAllSoggettosByInterventoId(Integer idIntervento) {

		String sql = "SELECT * FROM idf_r_soggetto_intervento WHERE id_intervento = ?";

		return DBUtil.jdbcTemplate.query(sql, new SoggettoInterventoMapper(), idIntervento);

	}

	@Override
	public void updateWithIdSoggetto(int idSoggetto, int idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE idf_r_soggetto_intervento SET");
		sql.append(" id_soggetto = ?,  data_aggiornamento = ?");
		sql.append(" WHERE id_intervento = ?");

		DBUtil.jdbcTemplate.update(sql.toString(), idSoggetto, Date.valueOf(LocalDate.now()), idIntervento);
	}

	@Override
	public void updateSoggetoInterventoAtChiusura(Integer idIntervento, SoggettoResource utilizzatore) {

		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE idf_r_soggetto_intervento SET");
		sql.append(" id_soggetto = ?,  data_aggiornamento = ?");
		sql.append(" WHERE id_intervento = ? and id_soggetto = ?");

		List<Object> parameters = new ArrayList<>();
		parameters.add(utilizzatore.getIdSoggetto());
		parameters.add(Date.valueOf(LocalDate.now()));
		parameters.add(idIntervento);

		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());

	}

	@Override
	public int updateSoggetoIntervento(Integer idIntervento, Integer idTipoSoggetto, Integer idSoggetto) {

		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE idf_r_soggetto_intervento SET");
		sql.append(" id_soggetto = ?,  data_aggiornamento = ?");
		sql.append(" WHERE id_intervento = ? and id_tipo_soggetto = ?");

		List<Object> parameters = new ArrayList<>();
		parameters.add(idSoggetto);
		parameters.add(Date.valueOf(LocalDate.now()));
		parameters.add(idIntervento);
		parameters.add(idTipoSoggetto);

		return DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());

	}

	@Override
	public void deleteByIdIntervento(Integer idIntervento) {
		String sql = "DELETE FROM idf_r_soggetto_intervento WHERE id_intervento =?";
		DBUtil.jdbcTemplate.update(sql.toString(), idIntervento);
	}

	@Override
	public void deleteUtilizzatoreByIdIntervento(Integer idIntervento) {
		String sql = "DELETE FROM idf_r_soggetto_intervento WHERE id_tipo_soggetto = 2 and id_intervento =?";
		DBUtil.jdbcTemplate.update(sql.toString(), idIntervento);
	}

}
