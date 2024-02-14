/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.DatiMailInvoIstanza;

public class DatiMailInvoIstanzaMapper  implements RowMapper<DatiMailInvoIstanza>{

	@Override
	public DatiMailInvoIstanza mapRow(ResultSet rs, int arg1) throws SQLException {

		DatiMailInvoIstanza datiMail  = new DatiMailInvoIstanza();
		
		datiMail.setIdIstanzaIntervento(rs.getInt("id_istanza_intervento"));
		datiMail.setNrIstanzaForestale(rs.getInt("nr_istanza_forestale"));
		datiMail.setMailRichiedente(rs.getString("mail_richiedente"));
		datiMail.setIdSoggettoGestore(rs.getInt("id_soggetto_gestore"));
		datiMail.setDenominazioneGestore(rs.getString("denominazione_gestore"));
		datiMail.setTelefonoGestore(rs.getString("telefono_gestore"));
		datiMail.setMailGestore(rs.getString("mail_gestore"));
		datiMail.setPecGestore(rs.getString("pec_gestore"));
		
		return datiMail;
	}

}
