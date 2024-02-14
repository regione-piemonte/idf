/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.TipoIntervento;

public class TipoInterventoMapper implements RowMapper<TipoIntervento>{
	@Override
	public TipoIntervento mapRow(ResultSet rs, int arg1) throws SQLException {
		TipoIntervento tipoIntervento = new TipoIntervento();
		tipoIntervento.setIdTipoIntervento(rs.getInt("id_tipo_intervento"));
		tipoIntervento.setDescrTipoIntervento(rs.getString("descr_intervento"));
		tipoIntervento.setCodTipoIntervento(rs.getString("cod_intervento"));
		tipoIntervento.setFkConfigIpla(rs.getInt("fk_config_ipla"));
		tipoIntervento.setMtdOrdinamento(rs.getInt("mtd_ordinamento"));
		tipoIntervento.setFlgVisibile(rs.getByte("flg_visibile"));
		tipoIntervento.setFkMacroIntervento(rs.getInt("fk_macrointervento"));
		
		
		return tipoIntervento;
	}

}
