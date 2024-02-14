/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.CategoriaProfessionale;
import it.csi.idf.idfapi.dto.TipoIntervento;

public class CategoriaProfessionaleMapper implements RowMapper<CategoriaProfessionale>{
	@Override
	public CategoriaProfessionale mapRow(ResultSet rs, int arg1) throws SQLException {
		CategoriaProfessionale categoriaProfessionale = new CategoriaProfessionale();
		categoriaProfessionale.setIdCategoriaProfessionale(rs.getInt("id_categoria_professionale"));
		categoriaProfessionale.setDescrCategoriaProfessionale(rs.getString("descr_categoria_professionale"));
		categoriaProfessionale.setMtdOrdinamento(rs.getInt("mtd_ordinamento"));
		categoriaProfessionale.setFlgVisibile(rs.getByte("flg_visibile"));
		
		return categoriaProfessionale;
	}

}
