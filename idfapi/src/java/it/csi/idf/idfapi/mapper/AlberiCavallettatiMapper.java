/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.AlberiCampioneDominanteDTO;

public class AlberiCavallettatiMapper implements RowMapper<AlberiCampioneDominanteDTO> {

	@Override
	public AlberiCampioneDominanteDTO mapRow(ResultSet rs, int arg1) throws SQLException {
		AlberiCampioneDominanteDTO alberiCampioneDominante = new AlberiCampioneDominanteDTO();
		
		alberiCampioneDominante.setCodiceAds(rs.getString("codice_ads"));
		alberiCampioneDominante.setSpecie(rs.getInt("id_specie"));
		alberiCampioneDominante.setLatino(rs.getString("latino"));
		alberiCampioneDominante.setGruppo(rs.getString("cod_gruppo"));
		alberiCampioneDominante.setDiametro(rs.getInt("diametro"));
		alberiCampioneDominante.setSemePollone(rs.getString("flg_pollone_seme"));
		
		return alberiCampioneDominante;
	}

}
