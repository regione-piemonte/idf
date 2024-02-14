/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.ChiusuraInformazioni;
import it.csi.idf.idfapi.util.DBUtil;

public class ChiusuraInformazioniMapper implements RowMapper<ChiusuraInformazioni> {

	@Override
	public ChiusuraInformazioni mapRow(ResultSet rs, int arg1) throws SQLException {

		ChiusuraInformazioni chiusuraInformazioni = new ChiusuraInformazioni();
		
		chiusuraInformazioni.setDataInizio(
				rs.getDate("data_inizio_validita") == null ? null : rs.getDate("data_inizio_validita").toLocalDate());
		chiusuraInformazioni.setDataFine(
				rs.getDate("data_fine_validita") == null ? null : rs.getDate("data_fine_validita").toLocalDate());
		chiusuraInformazioni.setSuperficieRealeTagliata(rs.getBigDecimal("sup_tagliata_ha") == null ? null
				: rs.getBigDecimal("sup_tagliata_ha").setScale(4, RoundingMode.FLOOR));
		
		chiusuraInformazioni.setSuperficieTagliataInRiduzione(rs.getBigDecimal("sup_reale_tagliata_rid") == null ? null
				: rs.getBigDecimal("sup_reale_tagliata_rid").setScale(4, RoundingMode.FLOOR));
		
		chiusuraInformazioni.setStimaValoreLotto(DBUtil.getIntegerValueFromResultSet(rs, "stima_valore_lotto"));
		chiusuraInformazioni.setValoreDellAsta(DBUtil.getIntegerValueFromResultSet(rs, "valore_aggiudicazione_asta"));
		chiusuraInformazioni.setFlgConformeDeroga(rs.getString("flg_conforme_deroga"));
		
		return chiusuraInformazioni;
	}

}
