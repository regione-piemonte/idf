/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.CategoriaForestale;

public class CategoriaForestaleMapper implements RowMapper<CategoriaForestale>{

	@Override
	public CategoriaForestale mapRow(ResultSet rs, int arg1) throws SQLException {
		CategoriaForestale categoria = new CategoriaForestale();
		categoria.setIdCategoriaForestale(rs.getInt("id_categoria_forestale"));
		categoria.setFkParamMacroCatfor(rs.getInt("fk_param_macro_catfor"));
		categoria.setCodiceAmministrativo(rs.getString("codice_amministrativo"));
		categoria.setDescrizione(rs.getString("descrizione"));
		categoria.setMtdOrdinamento(rs.getInt("mtd_ordinamento"));
		categoria.setFlgVisibile(rs.getByte("flg_visibile"));
		
		return categoria;
	}

}
