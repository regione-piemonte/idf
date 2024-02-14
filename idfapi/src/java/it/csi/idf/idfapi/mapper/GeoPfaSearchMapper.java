/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.GeoPfaSearch;

public class GeoPfaSearchMapper implements RowMapper<GeoPfaSearch> {

	@Override
	public GeoPfaSearch mapRow(ResultSet rs, int arg1) throws SQLException {
		GeoPfaSearch geoPfaSearch = new GeoPfaSearch();

		geoPfaSearch.setIdGeoPlPfa(rs.getInt("idgeo_pl_pfa"));
		geoPfaSearch.setDenominazione(rs.getString("denominazione"));
		geoPfaSearch.setGeometria(rs.getObject("geometria"));
		geoPfaSearch.setDataInizioValidita(
				rs.getDate("data_inizio_validita") == null ? null : rs.getDate("data_inizio_validita").toLocalDate());
		geoPfaSearch.setDataFineValidita(
				rs.getDate("data_fine_validita") == null ? null : rs.getDate("data_fine_validita").toLocalDate());
		geoPfaSearch.setDataApprovazione(
				rs.getDate("data_approvazione") == null ? null : rs.getDate("data_approvazione").toLocalDate());
		geoPfaSearch.setDenominazioneComuni(rs.getString("denominazione_comuni"));
		geoPfaSearch.setIdComuni((Integer[]) rs.getArray("id_comuni").getArray());
		geoPfaSearch.setDenominazioneProvincie(rs.getString("denominazione_provincie"));
		geoPfaSearch.setSiglaProvincie(rs.getString("sigla_provincie"));
		geoPfaSearch.setIstatProv((String[]) rs.getArray("istat_provincie").getArray());

		return geoPfaSearch;
	}
}
