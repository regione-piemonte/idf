/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.SpeciePfaInterventoDAO;
import it.csi.idf.idfapi.dto.SpecieInterventoVolumes;
import it.csi.idf.idfapi.dto.SpeciePfaIntervento;
import it.csi.idf.idfapi.dto.TSpecie;
import it.csi.idf.idfapi.mapper.SpecieInterventoVolumesMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class SpeciePfaInterventoDAOImpl implements SpeciePfaInterventoDAO {

	private static Logger logger = Logger.getLogger(SpeciePfaInterventoDAOImpl.class);

	@Override
	public int createSpeciePfaIntervento(SpeciePfaIntervento speciePfaIntervento, Integer idIntervento) {

		StringBuilder insert = new StringBuilder();

		insert.append("INSERT INTO idf_r_specie_pfa_intervento");
		insert.append(" (id_specie");
		insert.append(", id_intervento");
		insert.append(", volume_specie");
		insert.append(", flg_specie_priorita");
		insert.append(", fk_udm");
		insert.append(", numero_piante)");
		insert.append(" VALUES (?, ?, ?, ?, ?, ?)");

		List<Object> parameters = new ArrayList<>();

		parameters.add(speciePfaIntervento.getIdSpecie());
		parameters.add(idIntervento);
		parameters.add(speciePfaIntervento.getVolumeSpecia());
		parameters.add(speciePfaIntervento.getFlgSpeciePriorita());
		parameters.add(speciePfaIntervento.getFkUdm());
		parameters.add(speciePfaIntervento.getNumPiante());

		return DBUtil.jdbcTemplate.update(insert.toString(), parameters.toArray());
	}

	@Override
	public void deleteSpeciePfaIntervento(Integer idSpecie, Integer idIntervento) {

		StringBuilder sql = new StringBuilder();

		sql.append("DELETE FROM idf_r_specie_pfa_intervento");
		sql.append(" WHERE id_specie = ? AND id_intervento = ?");

		List<Object> parameters = new ArrayList<>();

		parameters.add(idSpecie);
		parameters.add(idIntervento);

		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
		;
	}

	@Override
	public void updateSpeciePfaIntervento(SpeciePfaIntervento specieInterv, Integer idIntervento) {

		String sql = "UPDATE idf_r_specie_pfa_intervento SET volume_specie = ? WHERE id_specie=? AND id_intervento= ?";

		List<Object> parameters = new ArrayList<>();
		parameters.add(specieInterv.getVolumeSpecia());
		parameters.add(specieInterv.getIdSpecie());
		parameters.add(idIntervento);

		DBUtil.jdbcTemplate.update(sql, parameters.toArray());
		logger.info("Data in idf_r_specie_pfa_intervento is updated");

	}

	@Override
	public Integer getSpeciaVolumeByInterventoId(Integer idIntervento) {
		String sql = "SELECT sum(volume_specie) FROM idf_r_specie_pfa_intervento WHERE id_intervento=?";
		return DBUtil.jdbcTemplate.queryForObject(sql, Integer.class, idIntervento);
	}

	@Override
	public int[] batchCreate(SpeciePfaIntervento[] specieIntervs, Integer idIntervento) {

		for (SpeciePfaIntervento speciePfaIntervento : specieIntervs) {
			speciePfaIntervento.setIdIntervento(idIntervento);
		}

		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(specieIntervs);

		StringBuilder insert = new StringBuilder();

		insert.append("INSERT INTO idf_r_specie_pfa_intervento");
		insert.append(" (id_specie");
		insert.append(", id_intervento");
		insert.append(", volume_specie");
		insert.append(", flg_specie_priorita");
		insert.append(", fk_udm");
		insert.append(", numero_piante)");
		insert.append(" VALUES (:idSpecie, :idIntervento, :volumeSpecia, :flgSpeciePriorita, :fkUdm, :numPiante)");

		return DBUtil.namedJdbcTemplate.batchUpdate(insert.toString(), params);

	}

	@Override
	public void deleteAllSpeciePfaInterventoByIdIntervento(Integer idIntervento) {

		StringBuilder sql = new StringBuilder();

		sql.append("DELETE FROM idf_r_specie_pfa_intervento");
		sql.append(" WHERE id_intervento = ?");

		DBUtil.jdbcTemplate.update(sql.toString(), idIntervento);
	}

	@Override
	public List<SpecieInterventoVolumes> getSpecieByInterventoId(Integer idIntervento) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT id_specie, volgare, volume_specie, flg_specie_priorita, numero_piante , densita, descr_udm, fk_udm FROM idf_r_specie_pfa_intervento irps");
		sql.append(" JOIN idf_t_specie its using (id_specie)"); 
		sql.append(" JOIN idf_d_udm idu on idu.id_udm = irps.fk_udm ");  
		sql.append(" WHERE id_intervento = ? ");
		
		return DBUtil.jdbcTemplate.query(sql.toString(), new SpecieInterventoVolumesMapper(),idIntervento );
	}


}
