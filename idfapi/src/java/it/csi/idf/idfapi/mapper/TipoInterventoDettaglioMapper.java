/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.TipoInterventoDettaglio;
import it.csi.idf.idfapi.util.ConformeDerogaEnum;
import it.csi.idf.idfapi.util.DBUtil;

public class TipoInterventoDettaglioMapper implements RowMapper<TipoInterventoDettaglio> {

	@Override
	public TipoInterventoDettaglio mapRow(ResultSet rs, int arg1) throws SQLException {
		TipoInterventoDettaglio tipoInterventoDettaglio = new TipoInterventoDettaglio();

		tipoInterventoDettaglio.setConformeDeroga("C".equals(rs.getString("flg_conforme_deroga")) ? ConformeDerogaEnum.C.getValue() : ConformeDerogaEnum.D.getValue());
		tipoInterventoDettaglio.setAnnataSilvana(rs.getString("annata_silvana"));
		tipoInterventoDettaglio.setProgressivoIntervento(rs.getInt("nr_progressivo_interv"));
		tipoInterventoDettaglio.setDataPresuntaIntervento(rs.getDate("data_presa_in_carico") == null ? null
				: rs.getDate("data_presa_in_carico").toLocalDate());
		tipoInterventoDettaglio.setDataInizioIntervento(rs.getDate("data_inizio_intervento") == null ? null
				: rs.getDate("data_inizio_intervento").toLocalDate());
		tipoInterventoDettaglio.setDataFineIntervento(rs.getDate("data_fine_intervento") == null ? null
				: rs.getDate("data_fine_intervento").toLocalDate());
		tipoInterventoDettaglio.setIdParticelaForestale((Integer[]) rs.getArray("idgeo_pl_particella_forest").getArray());
		tipoInterventoDettaglio.setGoverno(rs.getString("governo"));
		tipoInterventoDettaglio.setEventoCorrelato(rs.getString("evento"));
		tipoInterventoDettaglio.setTipoIntervento(rs.getString("tipo_di_intervento"));
		tipoInterventoDettaglio.setRichiedePiedilsta(rs.getInt("flg_piedilista")==0 ? "Nessuna" : "Si");
		tipoInterventoDettaglio.setDescrizione(rs.getString("descrizione"));
		tipoInterventoDettaglio.setLocalita(rs.getString("localita"));
		tipoInterventoDettaglio.setSuperficieInteressata(DBUtil.getIntegerValueFromResultSet(rs, "superficie_interessata_ha"));
		tipoInterventoDettaglio.setStatoIntervento(rs.getString("descr_stato_intervento"));
		tipoInterventoDettaglio.setNumeroPiante(DBUtil.getIntegerValueFromResultSet(rs, "numero_piante"));
		tipoInterventoDettaglio.setM3Prelevati(DBUtil.getIntegerValueFromResultSet(rs, "m3_prelevati"));
		tipoInterventoDettaglio.setFasciaAltimetrica(rs.getString("fascia_altimetrica"));
		tipoInterventoDettaglio.setFinalitaTaglio(rs.getString("finalita_del_taglio"));
		tipoInterventoDettaglio.setDestLegname(rs.getString("destinazione_del_legname"));
		

		return tipoInterventoDettaglio;
	}

}
