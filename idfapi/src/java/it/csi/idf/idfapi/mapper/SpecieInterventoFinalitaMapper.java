/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import it.csi.idf.idfapi.dto.SpecieInterventoFinalita;
import it.csi.idf.idfapi.util.DBUtil;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SpecieInterventoFinalitaMapper implements RowMapper<SpecieInterventoFinalita>{

	@Override
	public SpecieInterventoFinalita mapRow(ResultSet rs, int arg1) throws SQLException {
		SpecieInterventoFinalita specie = new SpecieInterventoFinalita();
		
		specie.setIdSpecie(DBUtil.getIntegerValueFromResultSet(rs, "id_specie"));
		specie.setIdIntervento(rs.getInt("id_intervento"));
		specie.setIdFinalitaTaglio(rs.getInt("id_finalita_taglio"));
		specie.setFlgSpeciePriorita(rs.getString("flg_specie_priorita"));
		specie.setPercAutoconsumo(rs.getFloat("perc_autoconsumo"));
		specie.setPercCommerciale(rs.getFloat("perc_commerciale"));

		specie.setDescrFinalitaTaglio(rs.getString("descr_finalita_taglio"));

		return specie;
	}
	

}
