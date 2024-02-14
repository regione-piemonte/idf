/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//import java.util.logging.Logger;

import org.apache.log4j.Logger;

import it.csi.idf.idfapi.dto.*;
import it.csi.idf.idfapi.dto.IntervPfa;
import it.csi.idf.idfapi.mapper.*;
import it.csi.idf.idfapi.util.Constants;
import it.csi.idf.idfapi.util.TipoAllegatoPfaEnum;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.IntervPfaDAO;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class IntervPfaDAOImpl implements IntervPfaDAO {

	private static Logger logger = Logger.getLogger(IntervPfaDAOImpl.class);
	
	private static final int STATO_PROGRAMMATO =1;
	private final IntervPfaMapper intervSelvicolturaleMapper= new IntervPfaMapper();
	private final IntervPfaFatMapper intervSelvicolturaleFatMapper= new IntervPfaFatMapper();

	@Override
	public void insertIntervSelvicolturale (IntervPfa intervPfa, Integer idIntervento) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO idf_t_interv_selvicolturale(");
		sql.append("id_intervento, idgeo_pl_pfa, flg_conforme_deroga, nr_progressivo_interv, fk_stato_intervento, data_presa_in_carico");
		sql.append(", fk_fascia_altimetrica, annata_silvana, flg_piedilista, fk_tipo_intervento, fk_finalita_taglio");
		sql.append(", fk_dest_legname, nr_piante, volume_ramaglia_m3, stima_massa_retraibile_m3, note_esbosco");
		sql.append(", flg_interv_non_previsto, flg_istanza_taglio, fk_config_utente, fk_governo )");
		sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		
		List<Object> parameters = new ArrayList<>();
		
		parameters.add(idIntervento);
		parameters.add(intervPfa.getIdgeoPlPfa());
		parameters.add(intervPfa.getFlgConformeDeroga());
		parameters.add(intervPfa.getNrProgressivoInterv());
		parameters.add(intervPfa.getFkStatoIntervento()); // not null constraint
		parameters.add(intervPfa.getDataPresaInCarico() == null ? null
				: Date.valueOf(intervPfa.getDataPresaInCarico()));
		parameters.add(intervPfa.getFkFasciaAltimetrica()); // not null constraint
		parameters.add(intervPfa.getAnnataSilvana());
		parameters.add(intervPfa.getFlgPiedilista()); // not null constraint
		parameters.add(intervPfa.getFkTipoIntervento()); // not null constraint
		parameters.add(intervPfa.getFkFinalitaTaglio()); // not null constraint
		parameters.add(intervPfa.getFkDestLegname()); // not null constraint
		parameters.add(intervPfa.getNrPiante());
		parameters.add(intervPfa.getVolumeRamagliaM3());
		parameters.add(intervPfa.getStimaMassaRetraibileM3());
		parameters.add(intervPfa.getNoteEsbosco());
		parameters.add(intervPfa.getFlgIntervNonPrevisto()); // not null constraint
		parameters.add(intervPfa.getFlgIstanzaTaglio()); // not null constraint
		parameters.add(intervPfa.getFkConfigUtente()); // not null constraint
		parameters.add(intervPfa.getFkGoverno());
		
		//parameters.add(intervPfa.getFkTipoForestalePrevalente());
		//parameters.add(intervPfa.getFlgPiedilista());
		
		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}
	
	@Override
	public void insertIntervSelvicolturaleNEW(IntervPfa intervPfa, Integer idGeoPlPfa, ConfigUtente loggedConfig, Integer idIntervento) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO idf_t_interv_selvicolturale(");
		sql.append("id_intervento, idgeo_pl_pfa, flg_conforme_deroga, nr_progressivo_interv, fk_stato_intervento, data_presa_in_carico");
		sql.append(", fk_fascia_altimetrica, annata_silvana, flg_piedilista, fk_tipo_intervento, fk_finalita_taglio");
		sql.append(", fk_dest_legname, nr_piante, volume_ramaglia_m3, stima_massa_retraibile_m3, note_esbosco");
		sql.append(", flg_interv_non_previsto, flg_istanza_taglio, fk_config_utente, fk_governo, data_inserimento )");
		sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		
		List<Object> parameters = new ArrayList<>();
		
		parameters.add(idIntervento);
		parameters.add(idGeoPlPfa);
		parameters.add(intervPfa.getFlgConformeDeroga());
		parameters.add(getProssimoNrProgInterv(idGeoPlPfa));// TO CHECK
		parameters.add(STATO_PROGRAMMATO); // not null constraint
		parameters.add(intervPfa.getDataPresaInCarico() == null ? null
				: Date.valueOf(intervPfa.getDataPresaInCarico()));
		parameters.add(intervPfa.getFkFasciaAltimetrica()); // not null constraint
		parameters.add(intervPfa.getAnnataSilvana());
		parameters.add(intervPfa.getFlgPiedilista()); // not null constraint
		parameters.add(intervPfa.getFkTipoIntervento()); // not null constraint
		parameters.add(intervPfa.getFkFinalitaTaglio()); // not null constraint
		parameters.add(intervPfa.getFkDestLegname()); // not null constraint
		parameters.add(intervPfa.getNrPiante());
		parameters.add(intervPfa.getVolumeRamagliaM3());
		parameters.add(intervPfa.getStimaMassaRetraibileM3());
		parameters.add(intervPfa.getNoteEsbosco());
		parameters.add(0); // not null constraint
		parameters.add(intervPfa.getFlgIstanzaTaglio()); // not null constraint
		parameters.add(loggedConfig.getIdConfigUtente()); // not null constraint
		parameters.add(intervPfa.getFkGoverno());
		parameters.add(Date.valueOf(LocalDate.now()));
		
		//parameters.add(intervPfa.getFkTipoForestalePrevalente());
		//parameters.add(intervPfa.getFlgPiedilista());
		
		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}

	@Override
	public void updateIntervSelvicolturale(IntervPfa intervPfa, Integer idIntervento) {
		
		StringBuilder update = new StringBuilder();
		
		update.append("UPDATE idf_t_interv_selvicolturale SET");
		update.append(" nr_piante=?, stima_massa_retraibile_m3=?");
		update.append(",volume_ramaglia_m3=?, m3_prelevati=?, note_esbosco=?");
		update.append(",fk_tipo_intervento=?, fk_stato_intervento=?");
		update.append(",fk_finalita_taglio=?, fk_dest_legname=?");
		update.append(",fk_fascia_altimetrica=?, annata_silvana =?");
		update.append(",flg_piedilista=?,fk_governo=?");
		update.append(",flg_conforme_deroga=?, flg_istanza_taglio=?");
		update.append(",data_inserimento=?,data_presa_in_carico=?");
		
		update.append(" WHERE id_intervento=?");
		
		List<Object> parameters = new ArrayList<>();
		
		parameters.add(intervPfa.getNrPiante());
		parameters.add(intervPfa.getStimaMassaRetraibileM3());
		parameters.add(intervPfa.getVolumeRamagliaM3());
		parameters.add(intervPfa.getM3Prelevati());
		parameters.add(intervPfa.getNoteEsbosco());
		parameters.add(intervPfa.getFkTipoIntervento());
		parameters.add(intervPfa.getFkStatoIntervento());
		parameters.add(intervPfa.getFkFinalitaTaglio());
		parameters.add(intervPfa.getFkDestLegname());
		parameters.add(intervPfa.getFkFasciaAltimetrica());
		parameters.add(intervPfa.getAnnataSilvana());
		parameters.add(intervPfa.getFlgPiedilista());
		parameters.add(intervPfa.getFkGoverno());
		parameters.add(intervPfa.getFlgConformeDeroga());
		parameters.add(intervPfa.getFlgIstanzaTaglio());
		parameters.add(Date.valueOf(LocalDate.now()));
		parameters.add(intervPfa.getDataPresaInCarico()==null?
				null:java.sql.Date.valueOf(intervPfa.getDataPresaInCarico()));
		parameters.add(idIntervento);
		
		DBUtil.jdbcTemplate.update(update.toString(), parameters.toArray());
	}


	@Override
	public void insertStep1(IntervPfa intervPfa, Integer idIntervento) {
		StringBuilder sql = new StringBuilder();

		sql.append("INSERT INTO idf_t_interv_selvicolturale(");
		sql.append("id_intervento, fk_tipo_intervento, fk_governo, fk_categ_intervento, fk_proprieta, ");
		sql.append("fk_finalita_taglio, fk_dest_legname, fk_fascia_altimetrica, idgeo_pl_pfa, ");
		sql.append("fk_stato_intervento, data_presa_in_carico, annata_silvana, data_inserimento, fk_config_utente , fk_intervento_padre_variante, fk_intervento_padre_proroga, flg_conforme_deroga, nr_progressivo_interv) ");
		sql.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		List<Object> parameters = new ArrayList<>();

		parameters.add(idIntervento);
		parameters.add(intervPfa.getFkTipoIntervento());
		parameters.add(intervPfa.getFkGoverno());
		parameters.add(intervPfa.getFkCategoriaIntervento());
		parameters.add(intervPfa.getFkProprieta());

		parameters.add(Constants.NO_DATA);
		parameters.add(Constants.NO_DATA);
		parameters.add(Constants.NO_DATA);
		parameters.add(Constants.NO_DATA);

		parameters.add(intervPfa.getFkStatoIntervento());
		parameters.add(intervPfa.getDataPresaInCarico() == null ? null: Date.valueOf(intervPfa.getDataPresaInCarico()));
		parameters.add(intervPfa.getAnnataSilvana());
		parameters.add(Date.valueOf(LocalDate.now()));
		parameters.add(intervPfa.getFkConfigUtente()); // not null constraint
		parameters.add(intervPfa.getFkInterventoPadreProroga());
		parameters.add(intervPfa.getFkInterventoPadreVariante());
		parameters.add(intervPfa.getFlgConformeDeroga());
		
		intervPfa.setNrProgressivoInterv(getProssimoNrProgInterv(0));// TODO CHECK
		
		parameters.add(intervPfa.getNrProgressivoInterv());// TODO CHECK
		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
		
		// JC - Inicio
		if (null != intervPfa.getIdEvento()) {
			sql = new StringBuilder();
			parameters = new ArrayList<>();
	
			sql.append("INSERT INTO idf_r_intervselv_evento(");
			sql.append("id_evento, id_intervento, data_inserimento, fk_config_utente) ");
			sql.append("VALUES(?, ?, ?, ?)");
	
			parameters.add(intervPfa.getIdEvento());
			parameters.add(idIntervento);
			parameters.add(Date.valueOf(LocalDate.now()));
			parameters.add(intervPfa.getFkConfigUtente()); // not null constraint
	
			DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
		}
		// JC - Fin
	}


	@Override
	public int updateStep1(IntervPfa intervPfa, Integer idIntervento) {
		StringBuilder sql = new StringBuilder();
		StringBuilder sql1 = new StringBuilder();
		StringBuilder sql2 = new StringBuilder();
		
		sql.append("UPDATE idf_t_interv_selvicolturale SET ");
		sql.append("fk_tipo_intervento = ? , fk_governo = ?,");
		sql.append("fk_categ_intervento = ?, fk_proprieta = ?, ");
		sql.append("fk_stato_intervento = ?, data_presa_in_carico = ?, ");
		sql.append("annata_silvana = ?, data_aggiornamento = ?, fk_config_utente = ? ,flg_conforme_deroga = ? ");
		sql.append("WHERE id_intervento = ?");

		List<Object> parameters = new ArrayList<>();
		List<Object> parameters1 = new ArrayList<>();
		parameters.add(intervPfa.getFkTipoIntervento());
		parameters.add(intervPfa.getFkGoverno());
		parameters.add(intervPfa.getFkCategoriaIntervento());
		parameters.add(intervPfa.getFkProprieta());
		parameters.add(intervPfa.getFkStatoIntervento());
		parameters.add(intervPfa.getDataPresaInCarico() == null ? null: Date.valueOf(intervPfa.getDataPresaInCarico()));
		parameters.add(intervPfa.getAnnataSilvana());
		parameters.add(Date.valueOf(LocalDate.now()));
		parameters.add(intervPfa.getFkConfigUtente()); // not null constraint
		parameters.add(intervPfa.getFlgConformeDeroga());	
		parameters.add(idIntervento);
		
		logger.info("<------ updateStep1 "+parameters.toString());

		if (null != intervPfa.getIdEvento()) {
			///7777
			String sqlidevento = "SELECT COUNT(*) FROM idf_r_intervselv_evento WHERE id_intervento = ?";

			int res = DBUtil.jdbcTemplate.queryForObject(sqlidevento,Integer.class, idIntervento);
			logger.info("<----- updateStep1 res: " + res);	
			if(res > 0) {
				sql1 = new StringBuilder();
				List<Object> parameters2 = new ArrayList<>();
		
				sql1.append("UPDATE idf_r_intervselv_evento SET ");
				sql1.append("id_evento = ? ");			
				sql1.append("WHERE id_intervento = ?");
		
				parameters2.add(intervPfa.getIdEvento());
				parameters2.add(idIntervento);	
				DBUtil.jdbcTemplate.update(sql1.toString(), parameters2.toArray());
			}
			else {
				logger.info("<----- updateStep1 else: " + res);
				sql2 = new StringBuilder();
				parameters1 = new ArrayList<>();
		
				sql2.append("INSERT INTO idf_r_intervselv_evento(");
				sql2.append("id_evento, id_intervento, data_inserimento, fk_config_utente) ");
				sql2.append("VALUES(?, ?, ?, ?)");
		
				parameters1.add(intervPfa.getIdEvento());
				parameters1.add(idIntervento);
				parameters1.add(Date.valueOf(LocalDate.now()));
				parameters1.add(intervPfa.getFkConfigUtente()); // not null constraint
				logger.info("<----- updateStep1 parameters1: " + parameters1.toArray());
				DBUtil.jdbcTemplate.update(sql2.toString(), parameters1.toArray());

			}
			
		} else {
			String sqlidevento = "SELECT COUNT(*) FROM idf_r_intervselv_evento WHERE id_intervento = ?";
			int res = DBUtil.jdbcTemplate.queryForObject(sqlidevento,Integer.class, idIntervento);
			logger.info("<----- updateStep1 res: " + res);
			if(res > 0) {
				sql1 = new StringBuilder();
				List<Object> parameters2 = new ArrayList<>();

				sql1.append("DELETE FROM idf_r_intervselv_evento ");
				sql1.append("WHERE id_intervento = ?");

				parameters2.add(idIntervento);
				DBUtil.jdbcTemplate.update(sql1.toString(), parameters2.toArray());
			}
		}
		return DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}






	@Override
	public void updateIntervSelvicolturaleWithChiusuraData(InterventoRiepilogo interventoRiepilogo,
			Integer idIntervento, ConfigUtente loggedConfig) {
		
		StringBuilder update = new StringBuilder();
		
		update.append("UPDATE idf_t_interv_selvicolturale set data_inizio = ?, data_fine = ?, data_aggiornamento  = ?");
		update.append(" ,stima_valore_lotto = ? , valore_aggiudicazione_asta = ?, fk_config_utente = ? "); 
		update.append(" where id_intervento = ? ");
		
		List<Object> parameters = new ArrayList<>();
		parameters.add(java.sql.Date.valueOf(interventoRiepilogo.getChiusuraInformazioni().getDataInizio()));
		parameters.add(java.sql.Date.valueOf(interventoRiepilogo.getChiusuraInformazioni().getDataFine()));
		parameters.add(java.sql.Date.valueOf(LocalDate.now()));
		parameters.add(interventoRiepilogo.getChiusuraInformazioni().getStimaValoreLotto());
		parameters.add(interventoRiepilogo.getChiusuraInformazioni().getValoreDellAsta());
		parameters.add(loggedConfig.getIdConfigUtente());
		parameters.add(idIntervento);
		
		DBUtil.jdbcTemplate.update(update.toString(), parameters.toArray());
		
		
	}

	@Override
	public int updateIntervSelvicolturaleWithFasciaAltimetrica(Integer idFasciaAltimetrica, Integer idIntervento) {
		StringBuilder update = new StringBuilder();

		StringBuilder sql = new StringBuilder();

		sql.append("UPDATE idf_t_interv_selvicolturale SET ");
		sql.append("fk_fascia_altimetrica = ? , data_aggiornamento = ? ");
		sql.append("WHERE id_intervento = ?");

		List<Object> parameters = new ArrayList<>();
		parameters.add(idFasciaAltimetrica);
		parameters.add(Date.valueOf(LocalDate.now()));
		parameters.add(idIntervento);

		return DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}

	@Override
	public int updateIntervSelvicolturaleWithLocalita(String localita, Integer idIntervento) {
		StringBuilder update = new StringBuilder();

		StringBuilder sql = new StringBuilder();

		sql.append("UPDATE idf_t_intervento SET ");
		sql.append("localita = ? , data_aggiornamento = ? ");
		sql.append("WHERE id_intervento = ?");

		List<Object> parameters = new ArrayList<>();
		parameters.add(localita);
		parameters.add(Date.valueOf(LocalDate.now()));
		parameters.add(idIntervento);

		return DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}

	@Override
	public void updateIntervSelvicolturaleWithStatoIntervento(Integer fkStatoIntervento, Integer idIntervento) {

		StringBuilder sql = new StringBuilder();

		sql.append("UPDATE idf_t_interv_selvicolturale SET ");
		sql.append("fk_stato_intervento = ? , data_aggiornamento = ? ");
		sql.append("WHERE id_intervento = ?");

		List<Object> parameters = new ArrayList<>();
		parameters.add(fkStatoIntervento);
		parameters.add(Date.valueOf(LocalDate.now()));
		parameters.add(idIntervento);

		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}




	@Override
	public int updateIntervSelvicolturaletagliStep4(IntervPfaFat dto, Integer idIntervento) {

		StringBuilder update = new StringBuilder();

		StringBuilder sql = new StringBuilder();

		sql.append("UPDATE idf_t_interv_selvicolturale SET ");
		sql.append("fk_governo = ? , fk_governo2 = ?, ");
		sql.append("fk_tipo_intervento = ? , fk_tipo_intervento2 = ?, stima_massa_retraibile_m3 = ?, ");
		sql.append("superficie_int1_ha = ? , superficie_int2_ha = ?, ");
		sql.append("volume_ramaglia_m3 = ? , note_esbosco = ?, data_aggiornamento = ?, nr_piante = ? , m3_prelevati = ?  ");
		sql.append("WHERE id_intervento = ?");

		List<Object> parameters = new ArrayList<>();
		parameters.add(dto.getFkGoverno());
		parameters.add(dto.getFkGoverno2());
		parameters.add(dto.getFkTipoIntervento());
		parameters.add(dto.getFkTipoIntervento2());
		parameters.add(dto.getStimaMassaRetraibileM3());
		parameters.add(dto.getSuperficieInt1Ha());
		parameters.add(dto.getSuperficieInt2Ha());
		parameters.add(dto.getVolumeRamagliaM3());
		parameters.add(dto.getNoteEsbosco());
		parameters.add(Date.valueOf(LocalDate.now()));
		parameters.add(dto.getNrPiante());
		parameters.add(dto.getM3Prelevati());
		parameters.add(idIntervento);

		return DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}

	@Override
	public void deleteIntervSelvicolturale(Integer idIntervento) {
		
		String sql = "DELETE FROM idf_t_interv_selvicolturale WHERE id_intervento = ? ";
		
		DBUtil.jdbcTemplate.update(sql, idIntervento);
		
	}

	@Override
	public int getProssimoNrProgInterv(int idTipoIntervento) {
		return DBUtil.jdbcTemplate.queryForInt(
				//"SELECT COALESCE(MAX(nr_progressivo_interv), 0) FROM idf_t_interv_selvicolturale WHERE fk_tipo_intervento = ?",
				"SELECT COALESCE(MAX(nr_progressivo_interv), 0) FROM idf_t_interv_selvicolturale WHERE idgeo_pl_pfa = ?",
				idTipoIntervento) + 1;
	}

	@Override
	public IntervPfa findInterventoSelvicolturaleTagliNew(Integer idIntervento) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select intselv.id_intervento, fk_tipo_intervento, fk_stato_intervento, idgeo_pl_pfa, fk_tipo_forestale_prevalente");
		sql.append(",fk_finalita_taglio, fk_dest_legname, fk_fascia_altimetrica, flg_interv_non_previsto");		
		
		// 777 AbiSoft
		sql.append(", cod_stato_intervento, descr_stato_intervento, note_finali_richiedente");
		// 777
		sql.append(",nr_piante, stima_massa_retraibile_m3, m3_prelevati, volume_ramaglia_m3");
		sql.append(",data_presa_in_carico, annata_silvana, nr_progressivo_interv, flg_istanza_taglio");
		sql.append(", flg_piedilista, flg_conforme_deroga, note_esbosco, intselv.data_inserimento, intselv.data_aggiornamento");
		sql.append(", intselv.fk_config_utente, ripresa_prevista_mc, ripresa_reale_fine_interv_mc, fk_governo");
		//sql.append(", intselvuso.fk_uso_viabilita, intselesb.cod_esbosco");
		sql.append(", fk_proprieta, fk_categ_intervento, intselv.fk_tipo_intervento2, intselv.fk_governo2, intselv.superficie_int1_ha, intselv.superficie_int2_ha ");
		
		sql.append(", gov1.descr_governo descr_governo1, gov2.descr_governo descr_governo2 ");
		sql.append(", tpint1.descr_intervento as desc_tipo_int1, tpint2.descr_intervento as desc_tipo_int2  ");
		sql.append(", tpint1.cod_intervento as cod_tipo_int1, tpint2.cod_intervento as cod_tipo_int2  ");
		sql.append(", intselv.fk_intervento_padre_variante, intselv.fk_intervento_padre_proroga ");
		sql.append(", tpdevento.id_tipo_evento ");
		sql.append(", tprevent.id_evento ");
		
		sql.append(" from IDF_T_INTERV_SELVICOLTURALE intselv");
		
		sql.append(" left join idf_d_governo gov1 on gov1.id_governo = intselv.fk_governo ");
		sql.append(" left join idf_d_governo gov2 on gov2.id_governo = intselv.fk_governo2 ");
		sql.append(" left join idf_d_tipo_intervento tpint1 on tpint1.id_tipo_intervento = intselv.fk_tipo_intervento");
		sql.append(" left join idf_d_tipo_intervento tpint2 on tpint2.id_tipo_intervento = intselv.fk_tipo_intervento2");
		sql.append(" left join idf_r_intervselv_evento tprevent on  intselv.id_intervento = tprevent.id_intervento");
		sql.append(" left join idf_d_tipo_evento tpdevento on tprevent.id_evento = tpdevento.id_tipo_evento"); 

		
		//sql.append(" LEFT JOIN IDF_R_INTERVENTOSELV_USOVIAB intselvuso using (id_intervento)");
		//sql.append(" LEFT JOIN IDF_R_INTERVSELV_ESBOSCO intselesb using (id_intervento)");
		// 777 AbiSoft
		sql.append(" LEFT JOIN IDF_D_STATO_INTERVENTO statointselv on fk_stato_intervento = statointselv.id_stato_intervento ");
		// 777
		sql.append(" WHERE intselv.id_intervento = ?");
		
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(),intervSelvicolturaleMapper ,idIntervento);
	}


	@Override
	public IntervPfaFat findInterventoSelvicolturaleTagli(Integer idIntervento) {
		StringBuilder sql = new StringBuilder();
		//logger.info("findInterventoSelvicolturaleTagli: "+idIntervento);
		sql.append("select intselv.id_intervento, fk_tipo_intervento, fk_stato_intervento, idgeo_pl_pfa, fk_tipo_forestale_prevalente");
		sql.append(",fk_finalita_taglio, fk_dest_legname, fk_fascia_altimetrica, flg_interv_non_previsto");
		sql.append(",nr_piante, stima_massa_retraibile_m3, m3_prelevati, volume_ramaglia_m3");
		sql.append(",data_presa_in_carico, annata_silvana, nr_progressivo_interv, flg_istanza_taglio");
		sql.append(", flg_piedilista, intselv.flg_conforme_deroga, note_esbosco, intselv.data_inserimento, intselv.data_aggiornamento");
		sql.append(", intselv.fk_config_utente, ripresa_prevista_mc, ripresa_reale_fine_interv_mc, fk_governo");
		sql.append(", fk_proprieta, fk_categ_intervento, intselv.fk_tipo_intervento2, intselv.fk_governo2, intselv.superficie_int1_ha, intselv.superficie_int2_ha ");
		sql.append(", gov1.descr_governo descr_governo1, gov2.descr_governo descr_governo2 ");
		sql.append(", tpint1.descr_intervento as desc_tipo_int1, tpint2.descr_intervento as desc_tipo_int2  ");
		sql.append(", tpint1.cod_intervento as cod_tipo_int1, tpint2.cod_intervento as cod_tipo_int2  ");
		sql.append(", intselv.fk_intervento_padre_variante, intselv.fk_intervento_padre_proroga ");
		sql.append(", tpdevento.id_tipo_evento ");
		sql.append(", note_finali_richiedente  ");		
		sql.append(" from IDF_T_INTERV_SELVICOLTURALE intselv " );
		sql.append(" left join idf_d_governo gov1 on gov1.id_governo = intselv.fk_governo ");
		sql.append(" left join idf_d_governo gov2 on gov2.id_governo = intselv.fk_governo2 ");
		sql.append(" left join idf_d_tipo_intervento tpint1 on tpint1.id_tipo_intervento = intselv.fk_tipo_intervento");
		sql.append(" left join idf_d_tipo_intervento tpint2 on tpint2.id_tipo_intervento = intselv.fk_tipo_intervento2");
		sql.append(" left join idf_r_intervselv_evento tprevent on  intselv.id_intervento = tprevent.id_intervento");
		sql.append(" left join idf_d_tipo_evento tpdevento on tprevent.id_evento = tpdevento.id_tipo_evento"); 
		sql.append(" WHERE intselv.id_intervento = ?");

		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), intervSelvicolturaleFatMapper ,idIntervento);
	}

	@Override
		public DettagliDiTaglio getDettaglioDiTaglioByInterventoId(Integer idIntervento) {
			StringBuilder sql = new StringBuilder();
			
			sql.append("SELECT flg_conforme_deroga,nr_piante, itis.volume_ramaglia_m3, itis.stima_massa_retraibile_m3 "); 
			sql.append(", iduv.descr_uso_viabilita ,descr_esbosco, itis.note_esbosco, idti.descr_dett_tipoistanza, itif.nr_istanza_forestale ");
			sql.append(" from idf_t_interv_selvicolturale itis ");
			sql.append(" LEFT JOIN idf_r_intervselv_esbosco irie using (id_intervento) ");
			sql.append(" LEFT JOIN idf_d_esbosco ide using (cod_esbosco) ");
			sql.append(" LEFT JOIN idf_r_interventoselv_usoviab iriu using (id_intervento) ");
			sql.append(" LEFT JOIN idf_d_uso_viabilita iduv on iriu.fk_uso_viabilita = iduv.id_uso_viabilita");
			sql.append(" LEFT JOIN idf_t_istanza_forest itif on itis.id_intervento = itif.id_istanza_intervento ");
			sql.append(" LEFT JOIN idf_d_tipo_istanza idti on itif.fk_tipo_istanza = id_tipo_istanza ");
			sql.append(" WHERE id_intervento = ?");
			
			List<DettagliDiTaglio> list =  DBUtil.jdbcTemplate.query(sql.toString(), new DettagliDiTaglioMapper(),idIntervento );
			if(list == null || list.size() == 0) {
				return new DettagliDiTaglio();
			}else {
				return list.get(0);
			}
		}

		@Override
		public RicadenzaInfo getRicadenzaInfo(Integer idIntervento) {
			
			StringBuilder sql = new StringBuilder();
			
			sql.append("SELECT id_intervento, iria.codice_aapp, STRING_AGG(irir.codice_rn_2000, ', ') as rete_natura ");
			sql.append(", STRING_AGG(igps.denominazione, ', ') as popolamento_seme");
			sql.append(", STRING_AGG(idcf.descrizione, ', ') as categoria_forestale");
			sql.append(" from idf_t_interv_selvicolturale itis ");
			sql.append(" LEFT JOIN idf_r_intervento_aapp iria using (id_intervento)");
			sql.append(" LEFT JOIN idf_r_intervento_rn_2000 irir using (id_intervento)");
			sql.append(" LEFT JOIN idf_r_intervento_pop_seme irips using (id_intervento)");
			sql.append(" LEFT JOIN idf_geo_pl_popolamento_seme igps using(id_popolamento_seme)");
			sql.append(" LEFT JOIN idf_r_intervento_catfor iric using (id_intervento)");
			sql.append(" LEFT JOIN idf_d_categoria_forestale idcf using(id_categoria_forestale)");
			sql.append(" where id_intervento = ? ");
			sql.append(" group by id_intervento, iria.codice_aapp");
			
			//return DBUtil.jdbcTemplate.queryForObject(sql.toString(), new RicadenzaInfoMapper(),idIntervento );
		return DataAccessUtils.singleResult(DBUtil.jdbcTemplate.query(sql.toString(), new RicadenzaInfoMapper(),idIntervento));
		}

		@Override
		public void cambiamentoStato(Integer idIntervento,String statoIntervento ) {
			
			StringBuilder update = new StringBuilder();
			
			update.append("UPDATE idf_t_interv_selvicolturale SET fk_stato_intervento = ( ");
			update.append(" SELECT id_stato_intervento FROM idf_d_stato_intervento WHERE descr_stato_intervento =?) ");
			update.append(" WHERE id_intervento = ? ");
			
			DBUtil.jdbcTemplate.update(update.toString(), statoIntervento,idIntervento );
			
		}

		@Override
		public Integer getStimaMassaByIntervento(Integer idIntervento) {
			String sql = "SELECT stima_massa_retraibile_m3 from idf_t_interv_selvicolturale WHERE id_intervento = ?";
			return DBUtil.jdbcTemplate.queryForInt(sql.toString(),idIntervento);
		}

		@Override
		public String getStatoInterventoByIdIntervento(Integer idIntervento) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT descr_stato_intervento FROM idf_t_interv_selvicolturale itis ");
			sql.append(" JOIN idf_d_stato_intervento idsi on itis.fk_stato_intervento = idsi.id_stato_intervento "); 
			sql.append(" WHERE id_intervento = ? ");
			String result;
			try {
				result = DBUtil.jdbcTemplate.queryForObject(sql.toString(), String.class,idIntervento);
			} catch(EmptyResultDataAccessException exception ){
				result = null;
			}
			return result;
		}
		
		@Override
		public Integer getIdgeoPlPfaByIdIntervento(Integer idIntervento) {
			String sql = "SELECT idgeo_pl_pfa from idf_t_interv_selvicolturale WHERE id_intervento = ?";
			return DBUtil.jdbcTemplate.queryForInt(sql.toString(),idIntervento);
		}

	@Override
	public Boolean isDrelMancante(Integer idIntervento) {
		return this.getDrel(idIntervento) == null;
	}


	@Override
	public FatDocumentoAllegato getDrel(Integer idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT da.id_documento_allegato, da.idgeo_pl_pfa, da.fk_intervento, da.fk_tipo_allegato");
		sql.append(", da.nome_allegato, da.dimensione_allegato_kb, da.data_inizio_validita");
		sql.append(", da.data_fine_validita, da.data_inserimento, da.data_aggiornamento, da.fk_config_utente");
		sql.append(", da.note, ta.id_tipo_allegato, ta.descr_tipo_allegato , da.uid_index,da.id_doc_doqui,da.id_classificazione_doqui");
		sql.append(" FROM idf_t_documento_allegato da");
		sql.append(" JOIN idf_d_tipo_allegato ta ON ta.id_tipo_allegato = da.fk_tipo_allegato");
		sql.append(" WHERE da.fk_intervento = ?");
		sql.append(" AND da.fk_tipo_allegato = ? ");

		List<FatDocumentoAllegato> list = DBUtil.jdbcTemplate.query(sql.toString(), new FatDocumentoMapper(), idIntervento, TipoAllegatoPfaEnum.DICHIARAZIONE_DREL.getValue());

		if(list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public void updateTitolaritaIntervento(Integer idIntervento, Integer idConfUtente) {
		String sql = "update idf_t_interv_selvicolturale set fk_config_utente = ? where id_intervento = ? ";
		DBUtil.jdbcTemplate.update(sql, idConfUtente, idIntervento) ;
		sql = "update idf_t_intervento set fk_config_utente_compilatore = ? where id_intervento = ? ";
		DBUtil.jdbcTemplate.update(sql, idConfUtente, idIntervento) ;
	}

	@Override
	public void updateNoteFinaliRichiedente(Integer idIntervento, String noteFinaliRichiedente) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE idf_t_interv_selvicolturale SET note_finali_richiedente= ?");
		sql.append(" WHERE id_intervento = ? ");

		List<Object> parameters = new ArrayList<>();
		parameters.add(noteFinaliRichiedente);
		parameters.add(idIntervento);
		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}

	@Override
	public IntervPfa intervPfa(Integer idIntervento) {
		// TODO Auto-generated method stub
		return null;
	}
}
