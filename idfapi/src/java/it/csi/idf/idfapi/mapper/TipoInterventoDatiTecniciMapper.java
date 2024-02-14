/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.TipoInterventoDatiTecnici;
import it.csi.idf.idfapi.util.ConformeDerogaEnum;

public class TipoInterventoDatiTecniciMapper implements RowMapper<TipoInterventoDatiTecnici>{
	
	static final Logger logger = Logger.getLogger(TipoInterventoDatiTecniciMapper.class);

	@Override
	public TipoInterventoDatiTecnici mapRow(ResultSet rs, int arg1) throws SQLException {

		TipoInterventoDatiTecnici interv = new TipoInterventoDatiTecnici();
		String conformeDeroga = rs.getString("flg_conforme_deroga");
		interv.setConformeDeroga(
				ConformeDerogaEnum.C.toString().equals(conformeDeroga) ? ConformeDerogaEnum.C
						: ConformeDerogaEnum.D.toString().equals(conformeDeroga) ? ConformeDerogaEnum.D : null);
		interv.setProgressivoNumerico(rs.getInt("nr_progressivo_interv"));
		interv.setFkStatoIntervento(rs.getInt("fk_stato_intervento"));
		interv.setDataPresuntaIntervento(
				rs.getDate("data_presa_in_carico") == null ? null : rs.getDate("data_presa_in_carico").toLocalDate());
		interv.setAnnataSilvana(rs.getString("annata_silvana"));
		//interv.setIdParticelaForestale((Integer[])rs.getArray("idgeo_pl_particella_forest").getArray());
		if(rs.getString("id_evento") != null) {
			interv.setIdEventoCorelato(rs.getInt("id_evento"));
		}
		
		/*
		StringBuilder progressivoNomeBreve = new StringBuilder();
		progressivoNomeBreve.append(rs.getInt("progressivo_evento_pfa"));
		progressivoNomeBreve.append("/");
		progressivoNomeBreve.append(rs.getString("nome_breve"));
		interv.setProgressivoNomeBreveEvento(progressivoNomeBreve.toString());
		*/if(rs.getString("fk_governo")!=null) {
			interv.setFkGoverno(rs.getInt("fk_governo"));
		}
		
		interv.setRichiedePiedilsta(rs.getByte("flg_piedilista"));
		interv.setDescrizione(rs.getString("descrizione_intervento"));
		interv.setLocalita(rs.getString("localita"));
		interv.setSuperficieInteressata(rs.getBigDecimal("superficie_interessata_ha"));
		interv.setFkTipoIntervento(rs.getInt("fk_tipo_intervento"));
		//interv.setFasciaAltimetrica(rs.getInt("fascia_altimetrica"));
		interv.setFkFinalitaTaglio(rs.getInt("fk_finalita_taglio"));
		interv.setFkDestLegname(rs.getInt("fk_dest_legname"));
		
		return interv;
	}
}
