/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import it.csi.idf.idfapi.dto.GeneratedFileTagliParameters;
import it.csi.idf.idfapi.mapper.DichiarazioneTagliSummaryMapper;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.DichiarazioneSummaryDAO;
import it.csi.idf.idfapi.dto.GeneratedFileTrasfParameters;
import it.csi.idf.idfapi.dto.GeneratedFileVincoloParameters;
import it.csi.idf.idfapi.dto.TSoggetto;
import it.csi.idf.idfapi.mapper.DichiarazioneTrasfSummaryMapper;
import it.csi.idf.idfapi.mapper.DichiarazioneVincoloSummaryMapper;
import it.csi.idf.idfapi.mapper.TSoggettoMapper;
import it.csi.idf.idfapi.util.AmbitoIstanzaEnum;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class DichiarazioneSummaryDAOImpl implements DichiarazioneSummaryDAO {

	@Override 
	public GeneratedFileTrasfParameters getSummaryTrasformazioni(int idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT si.id_intervento, si.fk_config_utente, ifor.nr_istanza_forestale");
		sql.append(", sog.cognome, sog.nome, sog.denominazione, sog.codice_fiscale, sog.partita_iva");
		sql.append(", sog.indirizzo, sog.civico, sog.cap, sog.nr_telefonico, sog.e_mail, sog.pec");
		sql.append(", cmn.denominazione_comune, prov.denominazione_prov");
		sql.append(", it.flg_compensazione, it.flg_art7_a, it.flg_art7_b, it.flg_art7_c, it.flg_art7_d");
		sql.append(", it.flg_art7_a_21, it.flg_art7_b_21, it.flg_art7_c_21, it.flg_art7_d_21, it.flg_art7_dter_21");
		sql.append(", it.flg_art7_dquater_21, it.flg_art7_dquinquies_21, it.compensazione_euro_teorica");
		sql.append(", it.compensazione_euro_reale, it.compensazione_note, it.flg_proprieta, it.flg_dissensi");
		sql.append(", it.flg_aut_paesaggistica, it.data_aut_paesaggistica, it.nr_aut_paesaggistica, it.ente_aut_paesaggistica");
		sql.append(", it.flg_aut_vidro, it.data_aut_vidro, it.nr_aut_vidro, it.ente_aut_vidro");
		sql.append(", it.flg_aut_incidenza, it.data_aut_incidenza, it.nr_aut_incidenza, it.ente_aut_incidenza, it.altri_pareri ");
		sql.append(", inter.superficie_interessata_ha");
		sql.append(", array_to_string(array(SELECT id_parametro_trasf FROM idf_r_paramtrasf_trasformazion");
		sql.append(" WHERE id_intervento = ? ORDER BY id_parametro_trasf), ', ') as id_parametro_trasf");
		sql.append(", sgPf.cognome as prof_cognome, sgPf.nome as prof_nome");
		sql.append(", sgPf.codice_fiscale as prof_codice_fiscale, sgPf.istat_prov_iscrizione_ordine as prof_prov_ordine");
		sql.append(", sgPf.n_iscrizione_ordine as prof_iscrizione, sgPf.nr_telefonico as prof_telefonico, sgPf.pec as prof_pec");
		sql.append(" FROM idf_r_soggetto_intervento si");
		sql.append(" JOIN idf_t_soggetto sog ON si.id_soggetto = sog.id_soggetto");
		sql.append(" JOIN idf_geo_pl_comune cmn ON sog.fk_comune = cmn.id_comune");
		sql.append(" JOIN idf_geo_pl_provincia prov ON cmn.istat_prov = prov.istat_prov");
		sql.append(" JOIN idf_t_interv_trasformazione it ON si.id_intervento = it.id_intervento");
		sql.append(" JOIN idf_t_intervento inter ON si.id_intervento = inter.id_intervento");
		sql.append(" JOIN idf_t_istanza_forest ifor on ifor.id_istanza_intervento =it.id_intervento ");
		sql.append(" LEFT JOIN idf_t_soggetto sgPf ON inter.fk_soggetto_profess = sgPf.id_soggetto");
		sql.append(" WHERE si.id_intervento = ? ");
		
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), new DichiarazioneTrasfSummaryMapper(), idIntervento, idIntervento);
	}

	@Override
	public GeneratedFileTagliParameters getSummaryTagli(int idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("	si.id_intervento, ");
		sql.append("	si.fk_config_utente, ");
		sql.append("	ifor.nr_istanza_forestale, ");
		sql.append("	sog.cognome, ");
		sql.append("	sog.nome, ");
		sql.append("	sog.denominazione, ");
		sql.append("	sog.codice_fiscale, ");
		sql.append("	sog.partita_iva, ");
		sql.append("	sog.indirizzo, ");
		sql.append("	sog.civico, ");
		sql.append("	sog.cap, ");
		sql.append("	sog.nr_telefonico, ");
		sql.append("	sog.e_mail, ");
		sql.append("	sog.pec, ");
		sql.append("	cmn.denominazione_comune, ");
		sql.append("	prov.denominazione_prov, ");
		sql.append("	itis.fk_tipo_intervento, ");
		sql.append("	itis.idgeo_pl_pfa, ");
		sql.append("	itis.fk_tipo_forestale_prevalente, ");
		sql.append("	itis.fk_stato_intervento, ");
		sql.append("	itis.fk_finalita_taglio, ");
		sql.append("	itis.fk_dest_legname, ");
		sql.append("	itis.fk_fascia_altimetrica, ");
		sql.append("	itis.flg_interv_non_previsto, ");
		sql.append("	itis.nr_piante, ");
		sql.append("	itis.stima_massa_retraibile_m3, ");
		sql.append("	itis.m3_prelevati, ");
		sql.append("	itis.volume_ramaglia_m3, ");
		sql.append("	itis.data_presa_in_carico, ");
		sql.append("	itis.annata_silvana, ");
		sql.append("	itis.nr_progressivo_interv, ");
		sql.append("	itis.flg_istanza_taglio, ");
		sql.append("	itis.flg_piedilista, ");
		sql.append("	itis.flg_conforme_deroga, ");
		sql.append("	itis.note_esbosco, ");
		sql.append("	itis.data_inserimento, ");
		sql.append("	itis.data_aggiornamento, ");
		sql.append("	itis.ripresa_prevista_mc, ");
		sql.append("	itis.ripresa_reale_fine_interv_mc, ");
		sql.append("	itis.data_inizio, ");
		sql.append("	itis.data_fine, ");
		sql.append("	itis.stima_valore_lotto, ");
		sql.append("	itis.valore_aggiudicazione_asta, ");
		sql.append("	itis.fk_governo, ");
		sql.append("	itis.sup_reale_tagliata_rid, ");
		sql.append("	itis.fk_tipo_intervento2, ");
		sql.append("	itis.fk_governo2, ");
		sql.append("	itis.superficie_int1_ha, ");
		sql.append("	itis.superficie_int2_ha, ");
		sql.append("	itis.fk_intervento_padre_variante, ");
		sql.append("	itis.fk_intervento_padre_proroga, ");
		sql.append("	itis.fk_proprieta, ");
		sql.append("	itis.fk_categ_intervento, ");
		sql.append("	inter.superficie_interessata_ha, ");
		sql.append("	array_to_string(ARRAY( ");
		sql.append("		SELECT 	id_parametro_trasf FROM idf_r_paramtrasf_trasformazion ");
		sql.append("		WHERE id_intervento = ? ORDER BY id_parametro_trasf), ', ') AS id_parametro_trasf, ");
		sql.append("	sgPf.cognome AS prof_cognome, ");
		sql.append("	sgPf.nome AS prof_nome, ");
		sql.append("	sgPf.codice_fiscale AS prof_codice_fiscale, ");
		sql.append("	sgPf.istat_prov_iscrizione_ordine AS prof_prov_ordine, ");
		sql.append("	sgPf.n_iscrizione_ordine AS prof_iscrizione, ");
		sql.append("	sgPf.nr_telefonico AS prof_telefonico, ");
		sql.append("	sgPf.pec AS prof_pec ");
		sql.append("FROM idf_r_soggetto_intervento si ");
		sql.append("JOIN idf_t_soggetto sog ON si.id_soggetto = sog.id_soggetto ");
		sql.append("JOIN idf_geo_pl_comune cmn ON sog.fk_comune = cmn.id_comune ");
		sql.append("JOIN idf_geo_pl_provincia prov ON cmn.istat_prov = prov.istat_prov ");
		sql.append("JOIN idf_t_interv_selvicolturale itis ON si.id_intervento = itis.id_intervento ");
		sql.append("JOIN idf_t_intervento inter ON	si.id_intervento = inter.id_intervento ");
		sql.append("JOIN idf_t_istanza_forest ifor ON ifor.id_istanza_intervento = si.id_intervento ");
		sql.append("LEFT JOIN idf_t_soggetto sgPf ON inter.fk_soggetto_profess = sgPf.id_soggetto ");
		sql.append("WHERE si.id_intervento = ? AND si.id_tipo_soggetto = 1");

		GeneratedFileTagliParameters result = null;
		try {
			result =  DBUtil.jdbcTemplate.queryForObject(sql.toString(), new DichiarazioneTagliSummaryMapper(), idIntervento, idIntervento);
		}catch(Exception ex) {
			return new GeneratedFileTagliParameters();
		}
		return result;
	}

	@Override
	public GeneratedFileVincoloParameters getSummaryVincolo(int idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT si.id_intervento, si.fk_config_utente, ifor.fk_tipo_istanza , ifor.nr_istanza_forestale,");
		sql.append("sog.id_soggetto, sog.cognome, sog.nome, sog.denominazione, sog.codice_fiscale, sog.partita_iva, ");
		sql.append("sog.indirizzo, sog.civico, sog.cap, sog.nr_telefonico, sog.e_mail, sog.pec, ");
		sql.append("cmn.denominazione_comune, prov.denominazione_prov,");
		sql.append("it.desc_tipo_interv_altro, it.movimenti_terra_mc, it.movimenti_terra_vincidro_mc, ");
		sql.append("it.mesi_intervento, it.flg_aree_dissesto, it.flg_aree_esondazione, it.flg_cauzione_vi, ");
		sql.append("it.flg_compensazione, it.cm_bosc_euro, it.cm_nobosc_euro, it.flg_art7_a, it.flg_art7_b, ");
		sql.append("it.flg_art7_c, it.flg_art7_d, it.flg_art7_d_bis, it.flg_art9_a, it.flg_art9_b, it.flg_art9_c, ");
		sql.append("it.flg_spese_istruttoria, it.flg_esenzione_marca_bollo, it.n_marca_bollo, it.flg_importo, ");
		sql.append("it.flg_copia_conforme, it.flg_conf_servizi, it.flg_suap, it.annotazioni, ");
		sql.append("it.fk_config_utente, it.flg_proprieta, it.flg_dissensi, ");
		sql.append("it.cm_supbosc_ha, it.cm_supnobosc_ha, inter.superficie_interessata_ha, ");
		sql.append("array_to_string(array(SELECT id_governo FROM idf_r_intervincidro_governo ");
		sql.append("WHERE id_intervento = ?), ',') as ids_governo, ");
		sql.append("array_to_string(array(select distinct fk_tipo_allegato FROM idf_t_documento_allegato  ");
		sql.append("WHERE fk_intervento = ?), ',') as allegati_types, ");
		sql.append("sgPf.cognome as prof_cognome, sgPf.nome as prof_nome, sgPf.codice_fiscale as prof_codice_fiscale,");
		sql.append("sgPf.istat_prov_iscrizione_ordine as prof_prov_ordine, sgPf.n_iscrizione_ordine as prof_iscrizione, ");
		sql.append("sgPf.nr_telefonico as prof_telefonico, sgPf.pec as prof_pec ");
		sql.append("FROM idf_r_soggetto_intervento si ");
		sql.append("JOIN idf_t_soggetto sog ON si.id_soggetto = sog.id_soggetto ");
		sql.append("JOIN idf_geo_pl_comune cmn ON sog.fk_comune = cmn.id_comune ");
		sql.append("JOIN idf_geo_pl_provincia prov ON cmn.istat_prov = prov.istat_prov ");
		sql.append("JOIN idf_t_interv_vinc_idro it ON si.id_intervento = it.id_intervento ");
		sql.append("JOIN idf_t_intervento inter ON si.id_intervento = inter.id_intervento ");
		sql.append("JOIN idf_t_istanza_forest ifor on ifor.id_istanza_intervento =it.id_intervento ");
		sql.append("LEFT JOIN idf_t_soggetto sgPf ON inter.fk_soggetto_profess = sgPf.id_soggetto ");
		sql.append("WHERE si.id_intervento = ? ");
		
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), new DichiarazioneVincoloSummaryMapper()
				, idIntervento, idIntervento, idIntervento);
	}
	
	@Override
	public TSoggetto getSoggettoCompetenteRegionale (int idIntervento, AmbitoIstanzaEnum ambito) {
		String tableName = "idf_geo_pl_interv_trasf";
		if(ambito == AmbitoIstanzaEnum.VINCOLO_IDROGEOLOGICO) {
			tableName = "idf_geo_pl_interv_vincidro";
		} else if (ambito == AmbitoIstanzaEnum.TAGLIO_IN_BOSCO) {
			tableName = "idf_geo_pl_lotto_intervento";
		}



		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM IDF_T_SOGGETTO soggetto ");
		sql.append("WHERE soggetto.istat_prov_competenza_terr in ");
		sql.append("( SELECT risultato1.istat_prov FROM ");
		sql.append("( SELECT ROW_NUMBER() OVER(ORDER BY risultato.area DESC) AS Row, ");
		sql.append("risultato.istat_prov, risultato.area ");
		sql.append("FROM ( ");
		sql.append("SELECT provincia.istat_prov, ");
		sql.append("SUM(ST_AREA(ST_Intersection ");
		sql.append("( provincia.geometria,  ");
		sql.append("( SELECT ST_UNION (geomTrasf) as geomTrasfUnion ");
		sql.append("FROM ( ");
		sql.append("SELECT geoTrasf.geometria as geomTrasf FROM ");
		sql.append(tableName);
		sql.append(" geoTrasf ");
		sql.append("WHERE geoTrasf.fk_intervento = ? ");
		sql.append(") as sbqry ");
		sql.append(")))) as area  ");
		sql.append("FROM ");
		sql.append("idf_geo_pl_provincia provincia ");
		sql.append("WHERE ");
		sql.append("ST_Intersects ( ");
		sql.append("( SELECT ST_UNION (geomTrasf) as geomTrasfUnion ");
		sql.append("FROM ( ");
		sql.append("SELECT geoTrasf.geometria as geomTrasf FROM ");
		sql.append(tableName);
		sql.append(" geoTrasf ");
		sql.append("WHERE geoTrasf.fk_intervento = ? ");
		sql.append(") as sbqry ");
		sql.append("), provincia.geometria ");
		sql.append(") GROUP BY ");
		sql.append("provincia.istat_prov ");
		sql.append(") as risultato ");
		sql.append(") as risultato1 WHERE risultato1.row = 1 ");
		sql.append(")  AND soggetto.flg_settore_regionale = 1 ");
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), new TSoggettoMapper() , idIntervento, idIntervento);
	}
	
	@Override
	public TSoggetto getSoggettoCompetenteComunale (int idIntervento, AmbitoIstanzaEnum ambito) {
		String tableName = "idf_geo_pl_interv_trasf";
		if(ambito == AmbitoIstanzaEnum.VINCOLO_IDROGEOLOGICO) {
			tableName = "idf_geo_pl_interv_vincidro";
		}
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM IDF_T_SOGGETTO soggetto WHERE soggetto.istat_comune_competenza_terr in "); 
		sql.append("(SELECT risultato1.istat_comune FROM "); 
		sql.append("( SELECT ROW_NUMBER() OVER(ORDER BY risultato.area DESC) AS Row, risultato.istat_comune, risultato.area "); 
		sql.append("FROM ( SELECT comune.istat_comune, SUM(ST_AREA(ST_Intersection "); 
		sql.append("( comune.geometria,  ( "); 
		sql.append("SELECT ST_UNION (geomTrasf) as geomTrasfUnion FROM  "); 
		sql.append("( SELECT geoTrasf.geometria as geomTrasf FROM ");
		sql.append(tableName);
		sql.append(" geoTrasf "); 
		sql.append("WHERE geoTrasf.fk_intervento = ? ) as sbqry )))) as area "); 
		sql.append("FROM idf_geo_pl_comune comune WHERE ST_Intersects ( ( "); 
		sql.append("SELECT ST_UNION (geomTrasf) as geomTrasfUnion FROM ( SELECT geoTrasf.geometria as geomTrasf "); 
		sql.append("FROM ");
		sql.append(tableName);
		sql.append(" geoTrasf WHERE geoTrasf.fk_intervento = ? ) as sbqry ), comune.geometria "); 
		sql.append(") GROUP BY comune.istat_comune) as risultato ) as risultato1 WHERE risultato1.row = 1 )  ");
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), new TSoggettoMapper() , idIntervento, idIntervento);
	}
	
	
	
	
	
	
}
