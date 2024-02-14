/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import it.csi.idf.idfapi.dto.Danno;

public class DannoMapper implements RowMapper<Danno>{

	@Override
	public Danno mapRow(ResultSet rsDanno, int arg1) throws SQLException {

		Danno danno  = new Danno();
		
		danno.setIdDanno(rsDanno.getInt("id_danno"));
		danno.setCodDanno(rsDanno.getString("cod_danno"));
		danno.setDescrDanno(rsDanno.getString("descr_danno"));
		danno.setMtdOrdinamento(rsDanno.getInt("mtd_ordinamento"));
		danno.setFlgVisibile(rsDanno.getByte("flg_visibile"));
		
		return danno;
	}

}
