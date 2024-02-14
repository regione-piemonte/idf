/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import it.csi.idf.idfapi.dto.Governo;
import it.csi.idf.idfapi.dto.IntervSelvicolturale;
import it.csi.idf.idfapi.dto.IntervSelvicolturaleFat;
import it.csi.idf.idfapi.dto.TipoIntervento;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IntervSelvicolturaleFatMapper implements RowMapper<IntervSelvicolturaleFat> {
	
	static final Logger logger = Logger.getLogger(IntervSelvicolturaleFatMapper.class);

	@Override
	public IntervSelvicolturaleFat mapRow(ResultSet rs, int arg1) throws SQLException {

		IntervSelvicolturaleFat interventoSelvcolt = new IntervSelvicolturaleFat();

		Governo gov1 = new Governo();
		interventoSelvcolt.setGovernoPrincipale(gov1);
		Governo gov2 = new Governo();
		interventoSelvcolt.setGovernoSecondario(gov2);

		TipoIntervento tipoInt1 = new TipoIntervento();
		interventoSelvcolt.setTipoInterventoPrincipale(tipoInt1);
		TipoIntervento tipoInt2 = new TipoIntervento();
		interventoSelvcolt.setTipoInterventoSecondario(tipoInt2);

		//RCH
		interventoSelvcolt.setIdTipoEvento(rs.getInt("id_tipo_evento"));
		
		interventoSelvcolt.setIdIntervento(rs.getInt("id_intervento"));
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


		interventoSelvcolt.setFkGoverno(rs.getInt("fk_governo"));
		gov1.setIdGoverno(rs.getInt("fk_governo"));
		gov1.setDescrGoverno(rs.getString("descr_governo1"));


		interventoSelvcolt.setFkGoverno2(rs.getInt("fk_governo2"));
		gov2.setIdGoverno(rs.getInt("fk_governo2"));
		gov2.setDescrGoverno(rs.getString("descr_governo2"));

		interventoSelvcolt.setFkTipoIntervento(rs.getInt("fk_tipo_intervento"));
		tipoInt1.setIdTipoIntervento(rs.getInt("fk_tipo_intervento"));
		tipoInt1.setDescrTipoIntervento(rs.getString("desc_tipo_int1"));
		tipoInt1.setCodTipoIntervento(rs.getString("cod_tipo_int1"));


		interventoSelvcolt.setFkTipoIntervento2(rs.getInt("fk_tipo_intervento2"));
		tipoInt2.setIdTipoIntervento(rs.getInt("fk_tipo_intervento2"));
		tipoInt2.setDescrTipoIntervento(rs.getString("desc_tipo_int2"));
		tipoInt2.setCodTipoIntervento(rs.getString("cod_tipo_int2"));

		interventoSelvcolt.setFkProprieta(rs.getInt("fk_proprieta"));
		interventoSelvcolt.setFkCategoriaIntervento(rs.getInt("fk_categ_intervento"));


		interventoSelvcolt.setSuperficieInt1Ha(rs.getBigDecimal("superficie_int1_ha") == null ? null	:rs.getBigDecimal("superficie_int1_ha"));

		interventoSelvcolt.setSuperficieInt2Ha(rs.getBigDecimal("superficie_int2_ha") == null ? null	:rs.getBigDecimal("superficie_int2_ha"));

		///7777
		interventoSelvcolt.setNoteFinaliRichiedente(rs.getString("note_finali_richiedente"));
		
		interventoSelvcolt.setIdEvento(rs.getInt("id_evento"));
		interventoSelvcolt.setIdTipoEvento(rs.getInt("id_tipo_evento"));
		
		try {
			interventoSelvcolt.setFkInterventoPadreProroga(rs.getInt("fk_intervento_padre_proroga"));
		} catch (SQLException e) {
			//throw new RuntimeException(e);
		}

		try {
			interventoSelvcolt.setFkInterventoPadreVariante(rs.getInt("fk_intervento_padre_variante"));
		} catch (SQLException e) {
//			throw new RuntimeException(e);
		}

		

		return interventoSelvcolt;
	}

}
