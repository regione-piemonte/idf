/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.TSpecie;

public class TSpecieMapper implements RowMapper<TSpecie>{

	@Override
	public TSpecie mapRow(ResultSet rs, int arg1) throws SQLException {
		TSpecie specie = new TSpecie();
		specie.setIdSpecie(rs.getInt("id_specie"));
		specie.setCodice(rs.getString("codice"));
		specie.setCodGruppo(rs.getString("cod_gruppo"));
		specie.setCodicePignatti(rs.getString("codice_pignatti"));
		specie.setLatino(rs.getString("latino"));
		specie.setVolgare(rs.getString("volgare"));
		specie.setFlg386(rs.getString("flg_386"));
		specie.setLinkScheda(rs.getString("link_scheda"));
		specie.setMtdOrdinamento(rs.getInt("mtd_ordinamento"));
		specie.setFlgVisibile(rs.getByte("flg_visibile"));
		specie.setFlgConiflatif(rs.getString("flg_coniflatif"));
		if(rs.getString("densita") != null) {
			specie.setDensita(rs.getFloat("densita"));
		}
		return specie;
	}
}
