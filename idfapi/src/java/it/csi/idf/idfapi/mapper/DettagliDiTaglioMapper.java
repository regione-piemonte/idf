/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.DettagliDiTaglio;
import it.csi.idf.idfapi.util.DBUtil;

public class DettagliDiTaglioMapper implements RowMapper<DettagliDiTaglio>{

	@Override
	public DettagliDiTaglio mapRow(ResultSet rs, int arg1) throws SQLException {
		DettagliDiTaglio dettagliDiTaglio = new DettagliDiTaglio();
		
		dettagliDiTaglio.setVolumeRamaglia(DBUtil.getIntegerValueFromResultSet(rs, "volume_ramaglia_m3"));
		dettagliDiTaglio.setStimaMassaRetraibileM3(DBUtil.getIntegerValueFromResultSet(rs, "stima_massa_retraibile_m3"));
		dettagliDiTaglio.setUsoViabilita(rs.getString("descr_uso_viabilita"));
		dettagliDiTaglio.setEsbosco(rs.getString("descr_esbosco"));
		dettagliDiTaglio.setNoteEsbosco(rs.getString("note_esbosco"));
		dettagliDiTaglio.setTipoIstanzaTrasmessa(rs.getString("descr_dett_tipoistanza"));
		dettagliDiTaglio.setNumeroIstanza(DBUtil.getIntegerValueFromResultSet(rs, "nr_istanza_forestale"));
		dettagliDiTaglio.setTipoIstanzaTrasmessa(rs.getString("descr_dett_tipoistanza"));
		dettagliDiTaglio.setNumeroPiante(rs.getInt("nr_piante")); 
		dettagliDiTaglio.setFlgConformeDeroga(rs.getString("flg_conforme_deroga")); 
		return dettagliDiTaglio;
	}

}
