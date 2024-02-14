/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.AmbitoDto;

public class AmbitoDtoMapper implements  RowMapper<AmbitoDto> {

	@Override
	public AmbitoDto mapRow(ResultSet rs ,int arg1) throws SQLException {
		AmbitoDto ambito = new AmbitoDto();

		ambito.setIdAmbitoIstanza(rs.getInt("id_ambito_istanza"));

		ambito.setMtdOrdinamento(rs.getInt("mtd_ordinamento"));
		ambito.setDescrAmbitoIstanza(rs.getString("descr_ambito_istanza"));
		return ambito;

	}

	

}
