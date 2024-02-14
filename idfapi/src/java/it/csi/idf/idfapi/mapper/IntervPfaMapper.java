/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.IntervPfa;

public class IntervPfaMapper implements RowMapper<IntervPfa> {
	
	static final Logger logger = Logger.getLogger(IntervPfaMapper.class);

	@Override
	public IntervPfa mapRow(ResultSet rs, int arg1) throws SQLException {

		IntervPfa interventoSelvcolt = new IntervPfa();

		interventoSelvcolt.setCodStatoIntervento(rs.getString("cod_stato_intervento"));
		interventoSelvcolt.setDescrStatoIntervento(rs.getString("descr_stato_intervento"));
		
		interventoSelvcolt.setIdIntervento(rs.getInt("id_intervento"));
		interventoSelvcolt.setFkTipoIntervento(rs.getInt("fk_tipo_intervento"));
		interventoSelvcolt.setFkStatoIntervento(rs.getInt("fk_stato_intervento"));
		interventoSelvcolt.setIdgeoPlPfa(rs.getInt("idgeo_pl_pfa"));
		interventoSelvcolt.setFkTipoForestalePrevalente(rs.getObject("fk_tipo_forestale_prevalente") == null ? null :rs.getInt("fk_tipo_forestale_prevalente"));

		interventoSelvcolt.setFkFinalitaTaglio(rs.getInt("fk_finalita_taglio"));
		interventoSelvcolt.setFkDestLegname(rs.getInt("fk_dest_legname"));
		interventoSelvcolt.setFkFasciaAltimetrica(rs.getInt("fk_fascia_altimetrica"));
		interventoSelvcolt.setFlgIntervNonPrevisto(rs.getByte("flg_interv_non_previsto"));

		interventoSelvcolt.setNrPiante(rs.getObject("nr_piante") == null ? null : rs.getInt("nr_piante"));
		interventoSelvcolt.setStimaMassaRetraibileM3(rs.getObject("stima_massa_retraibile_m3")== null ? null :rs.getInt("stima_massa_retraibile_m3"));
		interventoSelvcolt.setM3Prelevati(rs.getObject("m3_prelevati")== null ? null : rs.getInt("m3_prelevati"));
		interventoSelvcolt.setVolumeRamagliaM3(rs.getObject("volume_ramaglia_m3")== null ? null :rs.getInt("volume_ramaglia_m3"));

		interventoSelvcolt.setDataPresaInCarico(
				rs.getDate("data_presa_in_carico") == null ? null : rs.getDate("data_presa_in_carico").toLocalDate());
		interventoSelvcolt.setAnnataSilvana(rs.getString("annata_silvana"));
		interventoSelvcolt.setNrProgressivoInterv((Integer)rs.getInt("nr_progressivo_interv") == null ? null : rs.getInt("nr_progressivo_interv"));
		interventoSelvcolt.setFlgIstanzaTaglio(rs.getByte("flg_istanza_taglio"));

		interventoSelvcolt.setFlgPiedilista(rs.getByte("flg_piedilista"));
		interventoSelvcolt.setFlgConformeDeroga(rs.getString("flg_conforme_deroga"));
		interventoSelvcolt.setNoteEsbosco(rs.getString("note_esbosco"));
		interventoSelvcolt.setDataInserimento(
				rs.getDate("data_inserimento") == null ? null : rs.getDate("data_inserimento").toLocalDate());
		interventoSelvcolt.setDataAggiornamento(
				rs.getDate("data_aggiornamento") == null ? null : rs.getDate("data_aggiornamento").toLocalDate());

		interventoSelvcolt.setFkConfigUtente(rs.getInt("fk_config_utente"));
		interventoSelvcolt.setRipresaPrevistaMc(rs.getBigDecimal("ripresa_prevista_mc") == null ? null
				: rs.getBigDecimal("ripresa_prevista_mc").setScale(0, RoundingMode.FLOOR));
		interventoSelvcolt.setRipresaRealeFineIntervMc(rs.getBigDecimal("ripresa_reale_fine_interv_mc") == null ? null 
				:rs.getBigDecimal("ripresa_reale_fine_interv_mc").setScale(0, RoundingMode.FLOOR));
		if(rs.getString("fk_governo")!=null) {
			interventoSelvcolt.setFkGoverno(rs.getInt("fk_governo"));
		}
		
		interventoSelvcolt.setNoteFinaliRichiedente(rs.getString("note_finali_richiedente"));
		
		try {
			interventoSelvcolt.setIdUsoViabilita(rs.getInt("fk_uso_viabilita"));
		} catch (SQLException e) {
			logger.warn("IntervSelvicolturaleMapper: fk_uso_viabilita non presente nel result set");
		}

		try {
			interventoSelvcolt.setCodEsbosco(rs.getString("cod_esbosco"));
		} catch (SQLException e) {
			logger.warn("IntervSelvicolturaleMapper: cod_esbosco non presente nel result set");
		}

		interventoSelvcolt.setFkProprieta(rs.getInt("fk_proprieta"));
		interventoSelvcolt.setFkCategoriaIntervento(rs.getInt("fk_categ_intervento"));

		interventoSelvcolt.setFkTipoIntervento2(rs.getInt("fk_tipo_intervento2"));
		interventoSelvcolt.setFkGoverno2(rs.getInt("fk_governo2"));

		interventoSelvcolt.setSuperficieInt1Ha(rs.getBigDecimal("superficie_int1_ha") == null ? null
				:rs.getBigDecimal("superficie_int1_ha").setScale(0, RoundingMode.FLOOR));

		interventoSelvcolt.setSuperficieInt2Ha(rs.getBigDecimal("superficie_int2_ha") == null ? null
				:rs.getBigDecimal("superficie_int2_ha").setScale(0, RoundingMode.FLOOR));

		interventoSelvcolt.setIdEvento(rs.getInt("id_evento"));
		interventoSelvcolt.setIdTipoEvento(rs.getInt("id_tipo_evento"));
		
		return interventoSelvcolt;
	}

}
