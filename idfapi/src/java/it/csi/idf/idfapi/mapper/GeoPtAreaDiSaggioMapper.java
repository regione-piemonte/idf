/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.GeoPtAreaDiSaggio;

public class GeoPtAreaDiSaggioMapper implements RowMapper<GeoPtAreaDiSaggio> {

	@Override
	public GeoPtAreaDiSaggio mapRow(ResultSet rs, int arg1) throws SQLException {
		GeoPtAreaDiSaggio areaDiSaggio = new GeoPtAreaDiSaggio();
		
		areaDiSaggio.setIdgeoPtAds(rs.getLong("idgeo_pt_ads"));
		areaDiSaggio.setFkComune(rs.getInt("fk_comune"));
		areaDiSaggio.setDataRil(rs.getDate("data_ril") != null ? rs.getDate("data_ril").toLocalDate() : null);
		areaDiSaggio.setCodiceAds(rs.getString("codice_ads"));
		areaDiSaggio.setFkSettore(rs.getInt("fk_settore"));
		areaDiSaggio.setFkProprieta(rs.getInt("fk_proprieta"));
		areaDiSaggio.setFkTipoAds(rs.getInt("fk_tipo_ads"));
		areaDiSaggio.setFkAssetto(rs.getInt("fk_assetto"));
		areaDiSaggio.setFkEsposizione(rs.getInt("fk_esposizione"));
		areaDiSaggio.setFkComunitaMontana(rs.getInt("fk_comunita_montana"));
		areaDiSaggio.setFkGeoTipoForestale(rs.getInt("fk_tipo_forestale"));
		areaDiSaggio.setFkDestinazione(rs.getInt("fk_destinazione"));
		areaDiSaggio.setFkPriorita(rs.getInt("fk_priorita"));
		areaDiSaggio.setFlgDia(rs.getString("flg_dia"));
		areaDiSaggio.setFkSoggetto(rs.getInt("fk_soggetto"));
		areaDiSaggio.setQuota(rs.getInt("quota"));
		areaDiSaggio.setInclinazione(rs.getInt("inclinazione"));
		areaDiSaggio.setDataInizioValidita(rs.getDate("data_inizio_validita") != null ? rs.getDate("data_inizio_validita").toLocalDate() : null);
		areaDiSaggio.setDataFineValidita(rs.getDate("data_fine_validita") != null ? rs.getDate("data_fine_validita").toLocalDate() : null);
		areaDiSaggio.setNote(rs.getString("note"));
		areaDiSaggio.setFkAmbito(rs.getInt("fk_ambito"));
		areaDiSaggio.setIdgeoPlParticellaForest(rs.getInt("idgeo_pl_particella_forest"));
		areaDiSaggio.setFkDanno(rs.getInt("fk_danno"));
		areaDiSaggio.setFkTipoIntervento(rs.getInt("fk_tipo_intervento"));
//		areaDiSaggio.setGeomtria(rs.getDate("geometria").toLocalDate());

		
		return areaDiSaggio;
	}

}
