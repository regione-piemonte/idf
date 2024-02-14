/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.mock;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.service.INDEX;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class INDEXImpl implements INDEX {

	@Override
	public int uploadDocument(byte[] file, String fileName, long fileSize, int idIntervento, int tipoDocumento) {
		// TODO MOCK ID THAT SOULD BE RETURNED BY EXTERNAL SERVICE
		String sql1 = "SELECT COALESCE(MAX(id_documento) + 1, 1) FROM idf_temp_index_documento";
		int id = DBUtil.jdbcTemplate.queryForInt(sql1);
		String sql2 = "INSERT INTO idf_temp_index_documento(id_documento) VALUES (?)";
		DBUtil.jdbcTemplate.update(sql2, id);
		return id;
	}

	@Override
	public boolean fileExistWithID(int id) {
		String sql = "SELECT id_documento_allegato FROM idf_t_documento_allegato WHERE id_documento_allegato = ?";
		try {
			DBUtil.jdbcTemplate.queryForInt(sql, id);
			return true;
		} catch (EmptyResultDataAccessException erdae) {
			return false;
		}
	}

	@Override
	public void deleteFileWithID(int id) {
		String sql = "DELETE FROM idf_t_documento_allegato WHERE id_documento_allegato = ?";
		DBUtil.jdbcTemplate.queryForInt(sql, id);
	}
}
