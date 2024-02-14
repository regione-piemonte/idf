/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.RelascopicaSempliceDTO;



public class RelascopicaSempliceMapper implements RowMapper<RelascopicaSempliceDTO> {

	@Override
	public RelascopicaSempliceDTO mapRow(ResultSet rs, int arg1) throws SQLException {
		
		RelascopicaSempliceDTO relascopicaSemplice= new RelascopicaSempliceDTO();
		
		relascopicaSemplice.setCodiceAds(rs.getString("codice_ads"));
		relascopicaSemplice.setComune(rs.getString("denominazione_comune"));
		relascopicaSemplice.setTipologia(rs.getString("descr_tipo_ads"));
		relascopicaSemplice.setCategoriaForestale(rs.getString("descrizione"));
		relascopicaSemplice.setDataRilevamento(rs.getString("data_ril"));
		relascopicaSemplice.setEspozione(rs.getString("descr_esposizione"));
		relascopicaSemplice.setInclinazione(rs.getString("inclinazione"));
		relascopicaSemplice.setTipoForestale(rs.getString("tipo"));
		relascopicaSemplice.setAlberiPollone(rs.getInt("nr_alberi_pollone"));
		relascopicaSemplice.setAlberiSeme(rs.getInt("nr_alberi_seme"));
		relascopicaSemplice.setSpecie(rs.getString("id_specie"));
		relascopicaSemplice.setSpecieLatino(rs.getString("latino"));
		relascopicaSemplice.setQuota(rs.getInt("quota"));
		relascopicaSemplice.setFattoreNumerazione(rs.getInt("fattore_numerazione"));
		relascopicaSemplice.setParticella(rs.getString("denominazione_particella"));
		relascopicaSemplice.setNote(rs.getString("note"));
		relascopicaSemplice.setProprieta(rs.getString("descr_proprieta"));
		relascopicaSemplice.setTipoStrutturale(rs.getString("descr_tipo_strutturale"));
		relascopicaSemplice.setTipo(rs.getString("flg_pollone_seme"));
		relascopicaSemplice.setDiametro(rs.getInt("diametro"));
		relascopicaSemplice.setAltezza(rs.getInt("altezza"));
		relascopicaSemplice.setIncremento(rs.getInt("incremento"));
		relascopicaSemplice.setCodTipoCampione(rs.getString("cod_tipo_campione"));
		relascopicaSemplice.setUtmEst(rs.getBigDecimal("st_x"));
		relascopicaSemplice.setUtmNord(rs.getBigDecimal("st_y"));
		return relascopicaSemplice;
	}

}
