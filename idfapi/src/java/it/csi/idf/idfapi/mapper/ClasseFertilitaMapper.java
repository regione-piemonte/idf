/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.ClasseFertilita;

public class ClasseFertilitaMapper implements RowMapper<ClasseFertilita>{
	
	@Override
	public ClasseFertilita mapRow(ResultSet rs, int arg1) throws SQLException {
		
		ClasseFertilita classeFertilita = new ClasseFertilita();
		
		classeFertilita.setIdClasseFertilita(rs.getInt("id_classe_fertilita"));
		classeFertilita.setDescrClasseFertilita(rs.getString("descr_classe_fertilita"));
		classeFertilita.setFlgVisibile(rs.getBoolean("flg_visibile"));
		classeFertilita.setMtdOrdinamento(rs.getInt("mtd_ordinamento"));		
		
		return classeFertilita;
		
	}
}
