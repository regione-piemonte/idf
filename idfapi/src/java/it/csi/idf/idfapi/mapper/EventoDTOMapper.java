/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.EventoDTO;

public class EventoDTOMapper implements RowMapper<EventoDTO>{

	@Override
	public EventoDTO mapRow(ResultSet rs, int arg1) throws SQLException {

		EventoDTO evento = new EventoDTO();
		
		evento.setIdEvento(rs.getInt("id_evento"));
		evento.setProgressivoEventoPfa(rs.getInt("progressivo_evento_pfa"));
		evento.setNomeBreve(rs.getString("nome_breve"));
		evento.setDataEvento(rs.getDate("data_evento") == null ? null : rs.getDate("data_evento").toLocalDate());
		evento.setIdgeoPlParticelaForest((Integer[])rs.getArray("idgeo_pl_particella_forest").getArray());
		evento.setDenominazioneParticella((String[])rs.getArray("denominazione_particella").getArray());
		evento.setCodParticella((String[])rs.getArray("cod_particella_for").getArray());
		evento.setTipoEvento(rs.getInt("fk_tipo_evento"));
		evento.setDescrEvento(rs.getString("descr_evento"));
		evento.setLocalita(rs.getString("localita"));
		evento.setSupInteressataHa(rs.getBigDecimal("sup_interessata_ha"));
		evento.setPercDanno(rs.getInt("perc_danno"));
		evento.setAnnataSilvana(rs.getInt("annata_silvana"));
		
		return evento;
	}
}
