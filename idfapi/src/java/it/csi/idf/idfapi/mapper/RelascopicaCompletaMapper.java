/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.RelascopicaSempliceDTO;

public class RelascopicaCompletaMapper implements RowMapper<RelascopicaSempliceDTO> {

	@Override
	public RelascopicaSempliceDTO mapRow(ResultSet rs, int arg1) throws SQLException {
		
		RelascopicaSempliceDTO relascopicaSemplice= new RelascopicaSempliceDTO();
		
		relascopicaSemplice.setCodiceAds(rs.getString("codice_ads"));
		relascopicaSemplice.setSpecie(rs.getString("id_specie"));
		relascopicaSemplice.setDiametro(rs.getInt("diametro"));
		relascopicaSemplice.setAltezza(rs.getInt("altezza"));
		relascopicaSemplice.setIncremento(rs.getInt("incremento"));
		relascopicaSemplice.setTipo(rs.getString("flg_pollone_seme"));
		return relascopicaSemplice;
	}

}
