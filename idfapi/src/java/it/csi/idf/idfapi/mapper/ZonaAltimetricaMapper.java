/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.ZonaAltimetrica;

public class ZonaAltimetricaMapper implements RowMapper<ZonaAltimetrica>{

	@Override
	public ZonaAltimetrica mapRow(ResultSet rs, int arg1) throws SQLException {
		ZonaAltimetrica zonaAltimetrica = new ZonaAltimetrica();

		zonaAltimetrica.setIdgeoPlZonaAltimetrica(rs.getInt("idgeo_pl_zona_altimetrica"));
		zonaAltimetrica.setFkComune(rs.getInt("fk_comune"));
		zonaAltimetrica.setSezione(rs.getString("sezione"));
		zonaAltimetrica.setFoglio(rs.getInt("foglio"));
		zonaAltimetrica.setDataInizioValidita(
				rs.getDate("data_inizio_validita") == null ? null : rs.getDate("data_inizio_validita").toLocalDate());
		zonaAltimetrica.setDataFineValidita(
				rs.getDate("data_fine_validita") == null ? null : rs.getDate("data_fine_validita").toLocalDate());
		zonaAltimetrica.setNote(rs.getString("note"));
		// add geometria
		zonaAltimetrica.setFkParamtrasfZonaAltimetrica(rs.getInt("fk_paramtrasf_zona_altimetrica"));
		zonaAltimetrica.setDataInserimento(
				rs.getDate("data_inserimento") == null ? null : rs.getDate("data_inserimento").toLocalDate());
		zonaAltimetrica.setDataAggiornamento(
				rs.getDate("data_aggiornamento") == null ? null : rs.getDate("data_aggiornamento").toLocalDate());

		return zonaAltimetrica;
	}

}
