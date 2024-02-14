/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.GeoPfaSearchDettaglio;

public class GeoPfaDettaglioSearchMapper implements RowMapper<GeoPfaSearchDettaglio> {

	@Override
	public GeoPfaSearchDettaglio mapRow(ResultSet rs, int arg1) throws SQLException {

		GeoPfaSearchDettaglio geoSearch = new GeoPfaSearchDettaglio();
		geoSearch.setIdGeoPlPfa(rs.getInt("idgeo_pl_pfa"));
		geoSearch.setDenominazione(rs.getString("denominazione"));
		geoSearch.setDataInizioValidita(
				rs.getDate("data_inizio_validita") == null ? null : rs.getDate("data_inizio_validita").toLocalDate());
		geoSearch.setDataFineValidita(
				rs.getDate("data_fine_validita") == null ? null : rs.getDate("data_fine_validita").toLocalDate());
		geoSearch.setDataApprovazione(
				rs.getDate("data_approvazione") == null ? null : rs.getDate("data_approvazione").toLocalDate());
		geoSearch.setDataRevisione(
				rs.getDate("data_revisione") == null ? null : rs.getDate("data_revisione").toLocalDate());
		geoSearch.setGeometria(rs.getObject("geometria"));
		geoSearch.setFonteFinanziamento(rs.getString("fonte_finanziamento"));
		geoSearch.setFlgRevisione(rs.getByte("flg_revisione"));
		geoSearch.setPropNonForestaleHa(getHa(rs.getBigDecimal("prop_non_forestale_ha")));
		geoSearch.setSupPianifNonForestaleHa(getHa(rs.getBigDecimal("sup_pianif_non_forestale_ha")));
		geoSearch.setProprietaSilvopastHa(getHa(rs.getBigDecimal("proprieta_silvopast_ha")));
		geoSearch.setProprietaForestaleHa(getHa(rs.getBigDecimal("proprieta_forestale_ha")));
		geoSearch.setSuperfBocsGestAttivaHa(getHa(rs.getBigDecimal("superf_bosc_gest_attiva_ha")));
		geoSearch.setSupPianifForestaleHa(getHa(rs.getBigDecimal("sup_pianif_forestale_ha")));
		geoSearch.setSuperfGestNonAttivaMonHa(getHa(rs.getBigDecimal("superf_gest_non_attiva_mon_ha")));
		geoSearch.setSuperfGestNonAttivaTotHa(getHa(rs.getBigDecimal("superf_gest_non_attiva_tot_ha")));
		geoSearch.setSuperfGestNonAttivaEvlHa(getHa(rs.getBigDecimal("superf_gest_non_attiva_evl_ha")));
		geoSearch.setGestori((String[]) rs.getArray("gestori").getArray());
		geoSearch.setDenominazioneComuni(rs.getString("denominazione_comuni"));
		geoSearch.setIdComuni((Integer[]) rs.getArray("id_comuni").getArray());
		geoSearch.setDenominazioneProvincie(rs.getString("denominazione_provincie"));
		geoSearch.setIstatProvincie((String[]) rs.getArray("istat_provincie").getArray());
		geoSearch.setDescrPropriete(rs.getString("descr_propriete"));
		geoSearch.setDelibera(rs.getString("n_delibera"));

		return geoSearch;

	}
	
	private BigDecimal getHa(BigDecimal value) {
		return value == null? null:value.setScale(2, RoundingMode.FLOOR);
	}

}
