/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import it.csi.idf.idfapi.dto.TipoCampione;

public class TipoCampioneMapper implements RowMapper<TipoCampione> {

	@Override
	public TipoCampione mapRow(ResultSet rs, int arg1) throws SQLException {
			
		TipoCampione tipoCampione = new TipoCampione();
			
		tipoCampione.setCodTipoCampione(rs.getString("cod_tipo_campione"));
	    tipoCampione.setDescrTipoCampione(rs.getString( "descr_tipo_campione"));
	    tipoCampione.setMtdOrdinamento(rs.getInt( "mtd_ordinamento"));
	    tipoCampione.setFlgVisibile(rs.getByte( "flg_visibile"));

	    return tipoCampione;
	}

}
