/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import it.csi.idf.idfapi.business.be.impl.dao.SpecieInterventoFinalitaDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SpeciePfaInterventoDAO;
import it.csi.idf.idfapi.dto.SpecieInterventoFinalita;
import it.csi.idf.idfapi.dto.SpecieInterventoVolumes;
import it.csi.idf.idfapi.dto.SpeciePfaIntervento;
import it.csi.idf.idfapi.mapper.SpecieInterventoFinalitaMapper;
import it.csi.idf.idfapi.mapper.SpecieInterventoVolumesMapper;
import it.csi.idf.idfapi.util.DBUtil;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class SpecieInterventoFinalitaDAOImpl implements SpecieInterventoFinalitaDAO {

	private static Logger logger = Logger.getLogger(SpecieInterventoFinalitaDAOImpl.class);

	@Override
	public int createSpecieInterventoFinalita(SpecieInterventoFinalita specieIntervento, Integer idIntervento, Integer fkConfigUtente) {
		StringBuilder insert = new StringBuilder();

		insert.append("INSERT INTO idf_r_specie_intervento_finalita");
		insert.append(" (id_specie, id_intervento, id_finalita_taglio, ");
		insert.append(" perc_autoconsumo, perc_commerciale, data_inserimento, ");
		insert.append(" fk_config_utente, flg_specie_priorita )");
		insert.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

		List<Object> parameters = new ArrayList<>();

		parameters.add(specieIntervento.getIdSpecie());
		parameters.add(idIntervento);
		parameters.add(specieIntervento.getIdFinalitaTaglio());
		parameters.add(specieIntervento.getPercAutoconsumo());
		parameters.add(specieIntervento.getPercCommerciale());
		parameters.add(java.sql.Date.valueOf(LocalDate.now()));
		parameters.add(fkConfigUtente);
		parameters.add(specieIntervento.getFlgSpeciePriorita());
		return DBUtil.jdbcTemplate.update(insert.toString(), parameters.toArray());
	}

	@Override
	public void deleteSpecieInterventoFinalita(Integer idSpecie, Integer idIntervento, Integer idFinalita) {

		StringBuilder sql = new StringBuilder();

		sql.append("DELETE FROM idf_r_specie_intervento_finalita");
		sql.append(" WHERE id_specie = ? AND id_intervento = ? AND id_finalita_taglio=?");

		List<Object> parameters = new ArrayList<>();

		parameters.add(idSpecie);
		parameters.add(idIntervento);
		parameters.add(idFinalita);

		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());

	}

	@Override
	public void deleteAllByIdIntervento(Integer idIntervento) {
		StringBuilder sql = new StringBuilder();

		sql.append("DELETE FROM idf_r_specie_intervento_finalita");
		sql.append(" WHERE id_intervento = ?");

		DBUtil.jdbcTemplate.update(sql.toString(), idIntervento);
	}

	@Override
	public List<SpecieInterventoFinalita> getSpecieByInterventoId(Integer idIntervento) {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT * from idf_r_specie_intervento_finalita ");
		sql.append(" WHERE id_intervento = ? ");

		return DBUtil.jdbcTemplate.query(sql.toString(), new SpecieInterventoFinalitaMapper(),idIntervento );
	}

	@Override
	public List<SpecieInterventoFinalita> getAllSpecieByInterventoAndIdSpecie(Integer idIntervento, Integer idSpecie) {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT if.*, ft.descr_finalita_taglio from idf_r_specie_intervento_finalita if ");
		sql.append("inner join idf_d_finalita_taglio ft on ft.id_finalita_taglio = if.id_finalita_taglio ");
		sql.append(" WHERE id_intervento = ? and id_specie = ?");

		return DBUtil.jdbcTemplate.query(sql.toString(), new SpecieInterventoFinalitaMapper(),idIntervento, idSpecie );
	}
}
