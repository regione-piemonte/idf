/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import it.csi.idf.idfapi.dto.Priorita;

public class PrioritaMapper implements RowMapper<Priorita> {

	@Override
	public Priorita mapRow(ResultSet rs, int arg1) throws SQLException {
		
		Priorita priorita = new Priorita();
		
		priorita.setIdPriorita(rs.getInt("id_priorita"));
		priorita.setDescrPriorita(rs.getString("descr_priorita"));
		priorita.setCodPriorita(rs.getString("cod_priorita"));
		priorita.setFkConfigIpla(rs.getInt("fk_config_ipla"));
		priorita.setMtd_ordinamento(rs.getInt("mtd_ordinamento"));
		priorita.setFlg_visibile(rs.getByte("flg_visibile"));
		
		return priorita;
	}

}
