/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.PlainSecondoPfaEvento;

public class PlainSecondoPfaEventoMapper implements RowMapper<PlainSecondoPfaEvento> {

	@Override
	public PlainSecondoPfaEvento mapRow(ResultSet rs, int arg1) throws SQLException {
		PlainSecondoPfaEvento pfaEvento = new PlainSecondoPfaEvento();
		
		pfaEvento.setIdEvento(rs.getInt("id_evento"));
		pfaEvento.setProgressivoEventoPfa(rs.getInt("progressivo_evento_pfa"));
		pfaEvento.setNomeBreve(rs.getString("nome_breve"));
		pfaEvento.setDataEvento(rs.getDate("data_evento") == null ? null : rs.getDate("data_evento").toLocalDate());
		pfaEvento.setIdgeoPlParticelaForest((Integer[])rs.getArray("idgeo_pl_particella_forest").getArray());
		pfaEvento.setCodParticelle((String[])rs.getArray("cod_particella_for").getArray());
		pfaEvento.setDenominazioneParticella((String[])rs.getArray("denominazione_particella").getArray());
		pfaEvento.setTipoEvento(rs.getInt("fk_tipo_evento"));
		pfaEvento.setDescrEvento(rs.getString("descr_evento"));
		pfaEvento.setLocalita(rs.getString("localita"));
		pfaEvento.setSupInteressataHa(rs.getBigDecimal("sup_interessata_ha"));
		Integer[] percsDanno = (Integer[])rs.getArray("perc_danni").getArray();
		pfaEvento.setPercDanno(percsDanno[0]);
		pfaEvento.setAnnataSilvana(rs.getInt("annata_silvana"));
		
		return pfaEvento;
	}
}
