/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.RicadenzaIntervento;

public class RicadenzaInterventoMapper implements RowMapper<RicadenzaIntervento> {
	@Override
	public RicadenzaIntervento mapRow(ResultSet rs, int arg1) throws SQLException {
		RicadenzaIntervento ricadenza = new RicadenzaIntervento();
		ricadenza.setTipoVincolo(rs.getString("tipo_vincolo"));
		ricadenza.setNomeVincolo(rs.getString("nome_vincolo"));
		ricadenza.setProvvedimento(rs.getString("provvedimento"));
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		ricadenza.setPercentuale(
				Float.valueOf(decimalFormat.format(rs.getFloat("percentuale_ricadenza")).replace(",", ".")));
		return ricadenza;
	}
}
