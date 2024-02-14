/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.AreaDiSaggio;
import it.csi.idf.idfapi.dto.AreaDiSaggioDatiGeneraliDTO;
import it.csi.idf.idfapi.util.DBUtil;

public class AreaDiSaggioDatiGeneraliMapper implements RowMapper<AreaDiSaggioDatiGeneraliDTO> {

	@Override
	public AreaDiSaggioDatiGeneraliDTO mapRow(ResultSet rs, int arg1) throws SQLException {
		AreaDiSaggioDatiGeneraliDTO areaDiSaggio = new AreaDiSaggioDatiGeneraliDTO();
		
		areaDiSaggio.setIdgeoPtAds(rs.getLong("idgeo_pt_ads"));
		areaDiSaggio.setCodiceADS(rs.getString("codice_ads"));
		areaDiSaggio.setProvincia(rs.getString("istat_prov"));
		areaDiSaggio.setComune(DBUtil.getIntegerValueFromResultSet(rs, "fk_comune"));		
		areaDiSaggio.setTipologia(rs.getInt("fk_tipo_ads"));
		areaDiSaggio.setEspozione(rs.getInt("fk_esposizione"));		
		areaDiSaggio.setInclinazione(rs.getInt("inclinazione"));
		areaDiSaggio.setProprieta(rs.getInt("fk_proprieta"));
		areaDiSaggio.setQuota(rs.getInt("quota"));
		areaDiSaggio.setUtmEst(rs.getBigDecimal("st_x"));
		areaDiSaggio.setUtmNord(rs.getBigDecimal("st_y"));	
		areaDiSaggio.setAmbitoDiRilevamento(DBUtil.getIntegerValueFromResultSet(rs, "ambitoDiRilevamento"));
		areaDiSaggio.setDettaglioAmbito(DBUtil.getIntegerValueFromResultSet(rs, "fk_ambito"));		
		areaDiSaggio.setAmbitoSpecifico(rs.getString("ambito_specifico"));
		areaDiSaggio.setDataRilevamento(rs.getDate("data_ril") == null ? null : rs.getDate("data_ril").toLocalDate());
		
		return areaDiSaggio;
	}

}
