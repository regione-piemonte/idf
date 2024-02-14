/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.TipoAllegatoExtended;

public class TipoAllegatoExtendedMapper implements RowMapper<TipoAllegatoExtended>{

	@Override
	public TipoAllegatoExtended mapRow(ResultSet rs, int arg1) throws SQLException {
		TipoAllegatoExtended tipoAllegato = new TipoAllegatoExtended();
		
		tipoAllegato.setIdTipoAllegato(rs.getInt("id_tipo_allegato"));
		tipoAllegato.setDescrTipoAllegato(rs.getString("descr_tipo_allegato"));
		tipoAllegato.setFlgObbligatorio(rs.getBoolean("flg_obbligatorio"));
		return tipoAllegato;
	}
}
