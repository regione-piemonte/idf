/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfgeoapi.integration.dao.impl.rowmapper;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import it.csi.ecogis.util.conversion.GeoJSONGeometryConverter;
import it.csi.idf.idfgeoapi.dto.AdsForLayerDto;

public class AdsRowMapper implements RowMapper<AdsForLayerDto> {

	@Override
	public AdsForLayerDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		AdsForLayerDto dto = new AdsForLayerDto();
		dto.setIdGeoPtAds(rs.getInt("idgeo_pt_ads"));
		dto.setFkComune(rs.getInt("fk_comune"));
		dto.setFkSoggetto(rs.getInt("fk_soggetto"));
		dto.setDataRil(rs.getString("data_ril"));
		dto.setCodiceAds(rs.getString("codice_ads"));
		dto.setFkSettore(rs.getInt("fk_settore"));
		dto.setFkProprieta(rs.getInt("fk_proprieta"));
		dto.setFkTipoAds(rs.getInt("fk_tipo_ads"));
		dto.setFkAssetto(rs.getInt("fk_assetto"));
		dto.setFkEsposizione(rs.getInt("fk_esposizione"));
		dto.setFkComunitaMontana(rs.getInt("fk_comunita_montana"));
		dto.setFkTipoForestale(rs.getInt("fk_tipo_forestale"));
		dto.setFkDestinazione(rs.getInt("fk_destinazione"));
		dto.setFkPriorita(rs.getInt("fk_priorita"));
		dto.setFlgDia(rs.getString("flg_dia"));
		dto.setQuota(rs.getString("quota"));
		dto.setInclinazione(rs.getString("inclinazione"));
		dto.setDataInizioValidita(rs.getString("data_inizio_validita"));
		dto.setDataFineValidita(rs.getString("data_fine_validita"));
		dto.setNote(rs.getString("note"));
		dto.setFkAmbito(rs.getInt("fk_ambito"));
		dto.setIdgeoPlParticellaForestale(rs.getInt("idgeo_pl_particella_forest"));
		dto.setFkDanno(rs.getInt("fk_danno"));
		dto.setFkTipoIntervento(rs.getInt("fk_tipo_intervento"));
		dto.setGeometriaString(rs.getString("geometria"));
		if(StringUtils.isEmpty(dto.getGeometriaString())) {
			try {
				dto.setGeometry(GeoJSONGeometryConverter.getGeometryFromGeoJSON(dto.getGeometriaString()));
			}
			catch (IOException e) {
				dto.setGeometry(null);
			}
		} 
		dto.setFkStatoAds(rs.getInt("fk_stato_ads"));
		dto.setAmbitoSpecifico(rs.getString("ambito_specifico"));
		
		return dto;
	}

}
