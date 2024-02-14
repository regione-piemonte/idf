/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.EventoPiano;

public class EventoPianoMapper implements RowMapper<EventoPiano> {

	@Override
	public EventoPiano mapRow(ResultSet rs, int arg1) throws SQLException {
		EventoPiano evento = new EventoPiano();

		evento.setPfaDenominazione(rs.getString("denominazione"));
		evento.setIdEvento(rs.getInt("id_evento"));
		evento.setProgressivoEventoPfa(rs.getInt("progressivo_evento_pfa"));
		evento.setNomeBreve(rs.getString("nome_breve"));
		evento.setDataEvento(rs.getDate("data_evento") == null ? null : rs.getDate("data_evento").toLocalDate());
		evento.setIdgeoPlParticelaForest((Integer[])rs.getArray("idgeo_pl_particella_forest").getArray());
		evento.setCodParticelaForest((String[])rs.getArray("cod_particella_for").getArray());
		evento.setDenominazioneParticella((String[])rs.getArray("denominazione_particella").getArray());
		evento.setTipoEvento(rs.getInt("fk_tipo_evento"));
		evento.setDescrEvento(rs.getString("descr_evento"));
		evento.setLocalita(rs.getString("localita"));
		evento.setSupInteressataHa(rs.getBigDecimal("sup_interessata_ha"));
		evento.setPercentualeDanno(rs.getBigDecimal("perc_danno"));
		evento.setDescrTipoEvento(rs.getString("descr_tipo_evento"));
		return evento;
	}
}
