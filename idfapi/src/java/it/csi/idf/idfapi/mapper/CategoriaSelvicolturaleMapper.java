/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import it.csi.idf.idfapi.dto.CategoriaSelvicolturale;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoriaSelvicolturaleMapper implements RowMapper<CategoriaSelvicolturale>{
	@Override
	public CategoriaSelvicolturale mapRow(ResultSet rs, int arg1) throws SQLException {
		CategoriaSelvicolturale catIntervento = new CategoriaSelvicolturale();
		catIntervento.setIdCategoriaSelvicolturale(rs.getInt("id_categ_intervento"));
		catIntervento.setDescrCategoriaSelvicolturale(rs.getString("descr_categ_intervento"));
		catIntervento.setMtdOrdinamento(rs.getInt("mtd_ordinamento"));
		catIntervento.setFlgVisibile(rs.getByte("flg_visibile"));
		return catIntervento;
	}

}
