/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.IntervTrasf;

public class IntervTrasfMapper implements RowMapper<IntervTrasf> {

	@Override
	public IntervTrasf mapRow(ResultSet rs, int arg1) throws SQLException {
		IntervTrasf intervTrasf = new IntervTrasf();
		intervTrasf.setDataInserimento(rs.getDate("data_inserimento") == null ? null
				: rs.getDate("data_inserimento").toLocalDate());
		intervTrasf.setFkIntervento(rs.getInt("fk_intervento"));
		intervTrasf.setIdgeoPlIntervTrasf(rs.getInt("idgeo_pl_interv_trasf"));
		intervTrasf.setGeometria(rs.getObject("geometria"));
		
		return intervTrasf;
	}

}
