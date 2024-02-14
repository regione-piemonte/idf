/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.LottoIntervento;

public class LottoInterventoMapper implements RowMapper<LottoIntervento> {

	@Override
	public LottoIntervento mapRow(ResultSet rs, int arg1) throws SQLException {
		LottoIntervento lottoInterv = new LottoIntervento();
		lottoInterv.setIdLotto(rs.getInt("id_lotto"));
		lottoInterv.setGeometriaGml(rs.getString("geometriagml"));
		lottoInterv.setSuperficeLottoHa(rs.getDouble("sup_lotto_ha"));
		lottoInterv.setTotSuperficeIntervento(rs.getDouble("tot_sup_intervento_ha"));				
		return lottoInterv;	
	}

}
