/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.GeoPlPfa;

public class GeoPlPfaMapper implements RowMapper<GeoPlPfa> {

	@Override
	public GeoPlPfa mapRow(ResultSet rs, int arg1) throws SQLException {

		GeoPlPfa geoPlPfa = new GeoPlPfa();

		geoPlPfa.setIdGeoPlPfa(rs.getInt("idgeo_pl_pfa"));
		geoPlPfa.setDenominazione(rs.getString("denominazione"));
		geoPlPfa.setFonteFinanziamento(rs.getString("fonte_finanziamento"));
		geoPlPfa.setGeometry(rs.getObject("geometria"));
		geoPlPfa.setDataInizioValidita(rs.getDate("data_inizio_validita").toLocalDate());
		geoPlPfa.setDataFineValidita(rs.getDate("data_fine_validita").toLocalDate());
		geoPlPfa.setDataApprovazione(
				rs.getDate("data_approvazione") == null ? null : rs.getDate("data_approvazione").toLocalDate());
		geoPlPfa.setDataRevisione(
				rs.getDate("data_revisione") == null ? null : rs.getDate("data_revisione").toLocalDate());
		geoPlPfa.setSupPianificataTotale(rs.getBigDecimal("sup_pianificata_totale_ha"));
		geoPlPfa.setSupForestaleGestAttiva(rs.getBigDecimal("sup_forestale_gest_attiva_ha"));
		geoPlPfa.setNote(rs.getString("note"));
		geoPlPfa.setUtenteAggiornamento(rs.getString("utente_aggiornamento"));
		geoPlPfa.setnDelibera(rs.getString("n_delibera"));
		geoPlPfa.setDurataPfaAnni(rs.getInt("durata_pfa_anni"));
		geoPlPfa.setFlgRevisione(rs.getInt("flg_revisione"));
		geoPlPfa.setProprietaSilvopastHa(rs.getBigDecimal("proprieta_silvopast_ha"));
		geoPlPfa.setProprietaForestaleHa(rs.getBigDecimal("proprieta_forestale_ha"));
		geoPlPfa.setSuperfTotBoscataHa(rs.getBigDecimal("superf_tot_boscata_ha"));
		geoPlPfa.setSuperfBoscGestAttivaHa(rs.getBigDecimal("superf_bosc_gest_attiva_ha"));
		geoPlPfa.setSuperfGestNonAttivaTotHa(rs.getBigDecimal("superf_gest_non_attiva_tot_ha"));
		geoPlPfa.setSuperfGestNonAttivaMonHa(rs.getBigDecimal("superf_gest_non_attiva_mon_ha"));
		geoPlPfa.setSuperfGestNonAttivaEvlHa(rs.getBigDecimal("superf_gest_non_attiva_evl_ha"));
		geoPlPfa.setSuperfAltriUsiHa(rs.getBigDecimal("superf_altri_usi_ha"));
		geoPlPfa.setNomeBrevePfa(rs.getString("nome_breve_pfa"));
		geoPlPfa.setFk_versione(rs.getInt("fk_versione"));
		geoPlPfa.setDataInserimento(
				rs.getDate("data_inserimento") == null ? null : rs.getDate("data_inserimento").toLocalDate());
		geoPlPfa.setDataAggiornamento(
				rs.getDate("data_aggiornamento") == null ? null : rs.getDate("data_aggiornamento").toLocalDate());
		geoPlPfa.setPropNonForestaleHa(rs.getBigDecimal("prop_non_forestale_ha"));
		geoPlPfa.setSupPianifNonForestaleHa(rs.getBigDecimal("sup_pianif_non_forestale_ha"));
		geoPlPfa.setSupPianifForestaleHa(rs.getBigDecimal("sup_pianif_forestale_ha"));

		return geoPlPfa;
	}

}
