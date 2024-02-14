/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.InterventoPiano;
import it.csi.idf.idfapi.util.StringUtils;

public class InterventoPianoMapper implements RowMapper<InterventoPiano>{

	@Override
	public InterventoPiano mapRow(ResultSet rs, int arg1) throws SQLException {

		InterventoPiano interventi = new InterventoPiano();
		
		interventi.setPfaDenominazione(rs.getString("denominazione"));
		interventi.setIdIntervento(rs.getInt("id_intervento"));
		interventi.setNrProgressivoInterv(rs.getInt("nr_progressivo_interv"));
		interventi.setAnnataSilvana(rs.getString("annata_silvana"));
		interventi.setnParticelaForestale((Integer[])rs.getArray("idgeo_pl_particella_forest").getArray());
		interventi.setCodParticelaForestale(StringUtils.sort((String[])rs.getArray("cod_particella_for").getArray()));		
		interventi.setDenominazioneParticella((String[])rs.getArray("denominazione_particella").getArray());
		interventi.setDataInizio(rs.getDate("data_inizio_intervento") == null ? (rs.getDate("data_presa_in_carico") != null ? rs.getDate("data_presa_in_carico").toLocalDate(): null) : rs.getDate("data_inizio_intervento").toLocalDate());
        interventi.setDataFine(rs.getDate("data_fine_intervento") == null ? null : rs.getDate("data_fine_intervento").toLocalDate());
        interventi.setDescrizione(rs.getString("descrizione_intervento"));
		interventi.setLocalita(rs.getString("localita"));
		interventi.setSuperficieInteressata(rs.getBigDecimal("superficie_interessata_ha"));
        interventi.setM3Prelevati(rs.getInt("m3_prelevati"));
        interventi.setDescrStatoIntervento(rs.getString("descr_stato_intervento"));
        interventi.setComunicazioneDiTaglio(rs.getByte("flg_istanza_taglio") == 1 ? "YES" : "NO");
        interventi.setNrIstanzaForestale(rs.getString("num_istanza"));
		return interventi;
	}
	
}
