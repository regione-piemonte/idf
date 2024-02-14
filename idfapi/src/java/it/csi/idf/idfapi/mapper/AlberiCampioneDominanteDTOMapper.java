/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.AlberiCampioneDominanteDTO;

public class AlberiCampioneDominanteDTOMapper implements RowMapper<AlberiCampioneDominanteDTO> {

	@Override
	public AlberiCampioneDominanteDTO mapRow(ResultSet rs, int arg1) throws SQLException {
		AlberiCampioneDominanteDTO alberiCampioneDominante = new AlberiCampioneDominanteDTO();
		
		alberiCampioneDominante.setCodiceAds(rs.getString("codice_ads"));
		alberiCampioneDominante.setSpecie(rs.getInt("id_specie"));
		alberiCampioneDominante.setLatino(rs.getString("latino"));
		alberiCampioneDominante.setQualita(rs.getString("qualita"));
		alberiCampioneDominante.setDiametro(rs.getInt("diametro"));
		alberiCampioneDominante.setAltezza(rs.getInt("altezza"));
		alberiCampioneDominante.setIncremento(rs.getInt("altezza"));
		alberiCampioneDominante.setEta(rs.getInt("eta"));
		alberiCampioneDominante.setNote(rs.getString("note"));
		alberiCampioneDominante.setCodTipoCampione(rs.getString("cod_tipo_campione"));
		alberiCampioneDominante.setCodiceSpecie(rs.getString("codice"));
		alberiCampioneDominante.setSpecieLationo(rs.getString("latino"));
		alberiCampioneDominante.setSpecieVolgare(rs.getString("volgare"));
		alberiCampioneDominante.setGruppo(rs.getString("cod_gruppo"));
		alberiCampioneDominante.setSemePollone(rs.getString("flg_pollone_seme"));
		
		return alberiCampioneDominante;
	}

}
