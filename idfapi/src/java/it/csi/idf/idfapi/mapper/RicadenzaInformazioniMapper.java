/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.RicadenzaInformazioni;

public class RicadenzaInformazioniMapper implements RowMapper<RicadenzaInformazioni>{
	@Override
		public RicadenzaInformazioni mapRow(ResultSet rs, int arg1) throws SQLException {
		RicadenzaInformazioni ricadenza = new RicadenzaInformazioni();
		ricadenza.setCodiceAmministrativo(rs.getString("codice_amministrativo"));
		ricadenza.setNome(rs.getString("descrizione"));
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		ricadenza.setPercentualeRicadenza(Float.valueOf(decimalFormat.format(rs.getFloat("percentuale")).replace(",", ".")));
		return ricadenza;
	}
}
