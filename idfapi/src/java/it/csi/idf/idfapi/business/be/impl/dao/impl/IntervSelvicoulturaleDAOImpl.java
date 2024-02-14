/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.csi.idf.idfapi.dto.*;
import it.csi.idf.idfapi.mapper.*;
import it.csi.idf.idfapi.util.Constants;
import it.csi.idf.idfapi.util.TipoAllegatoPfaEnum;

import org.apache.log4j.Logger;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.IntervSelvicoulturaleDAO;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.SpringSupportedResource;

@Component
public class IntervSelvicoulturaleDAOImpl implements IntervSelvicoulturaleDAO {

	private static final int STATO_PROGRAMMATO =1;
	private final IntervSelvicolturaleMapper intervSelvicolturaleMapper= new IntervSelvicolturaleMapper();
	private final IntervSelvicolturaleFatMapper intervSelvicolturaleFatMapper= new IntervSelvicolturaleFatMapper();

	public static Logger logger = Logger.getLogger(IntervSelvicoulturaleDAOImpl.class);
	@Override
	public void insertIntervSelvicolturale(IntervSelvicolturale intervSelvicolturale, Integer idIntervento) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO idf_t_interv_selvicolturale(");
		sql.append("id_intervento, idgeo_pl_pfa, flg_conforme_deroga, nr_progressivo_interv, fk_stato_intervento, data_presa_in_carico");
		sql.append(", fk_fascia_altimetrica, annata_silvana, flg_piedilista, fk_tipo_intervento, fk_finalita_taglio");
		sql.append(", fk_dest_legname, nr_piante, volume_ramaglia_m3, stima_massa_retraibile_m3, note_esbosco");
		sql.append(", flg_interv_non_previsto, flg_istanza_taglio, fk_config_utente, fk_governo, note_finali_richiedente )");
		sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		
		List<Object> parameters = new ArrayList<>();
		
		parameters.add(idIntervento);
		parameters.add(intervSelvicolturale.getIdgeoPlPfa());
		parameters.add(intervSelvicolturale.getFlgConformeDeroga());
		parameters.add(intervSelvicolturale.getNrProgressivoInterv());
		parameters.add(intervSelvicolturale.getFkStatoIntervento()); // not null constraint
		parameters.add(intervSelvicolturale.getDataPresaInCarico() == null ? null
				: Date.valueOf(intervSelvicolturale.getDataPresaInCarico()));
		parameters.add(intervSelvicolturale.getFkFasciaAltimetrica()); // not null constraint
		parameters.add(intervSelvicolturale.getAnnataSilvana());
		parameters.add(intervSelvicolturale.getFlgPiedilista()); // not null constraint
		parameters.add(intervSelvicolturale.getFkTipoIntervento()); // not null constraint
		parameters.add(intervSelvicolturale.getFkFinalitaTaglio()); // not null constraint
		parameters.add(intervSelvicolturale.getFkDestLegname()); // not null constraint
		parameters.add(intervSelvicolturale.getNrPiante());
		parameters.add(intervSelvicolturale.getVolumeRamagliaM3());
		parameters.add(intervSelvicolturale.getStimaMassaRetraibileM3());
		parameters.add(intervSelvicolturale.getNoteEsbosco());
		parameters.add(intervSelvicolturale.getFlgIntervNonPrevisto()); // not null constraint
		parameters.add(intervSelvicolturale.getFlgIstanzaTaglio()); // not null constraint
		parameters.add(intervSelvicolturale.getFkConfigUtente()); // not null constraint
		parameters.add(intervSelvicolturale.getFkGoverno());
		parameters.add(intervSelvicolturale.getNoteFinaliRichiedente());
		
		//parameters.add(intervSelvicolturale.getFkTipoForestalePrevalente());
		//parameters.add(intervSelvicolturale.getFlgPiedilista());
		
		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}
	
	@Override
	public void insertIntervSelvicolturaleNEW(IntervSelvicolturale intervSelvicolturale, Integer idGeoPlPfa, ConfigUtente loggedConfig, Integer idIntervento) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO idf_t_interv_selvicolturale(");
		sql.append("id_intervento, idgeo_pl_pfa, flg_conforme_deroga, nr_progressivo_interv, fk_stato_intervento, data_presa_in_carico");
		sql.append(", fk_fascia_altimetrica, annata_silvana, flg_piedilista, fk_tipo_intervento, fk_finalita_taglio");
		sql.append(", fk_dest_legname, nr_piante, volume_ramaglia_m3, stima_massa_retraibile_m3, note_esbosco");
		sql.append(", flg_interv_non_previsto, flg_istanza_taglio, fk_config_utente, fk_governo, data_inserimento, note_finali_richiedente )");
		sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		
		List<Object> parameters = new ArrayList<>();
		
		parameters.add(idIntervento);
		parameters.add(idGeoPlPfa);
		parameters.add(intervSelvicolturale.getFlgConformeDeroga());
		parameters.add(getProssimoNrProgInterv(idGeoPlPfa));// TO CHECK
		parameters.add(STATO_PROGRAMMATO); // not null constraint
		parameters.add(intervSelvicolturale.getDataPresaInCarico() == null ? null
				: Date.valueOf(intervSelvicolturale.getDataPresaInCarico()));
		parameters.add(intervSelvicolturale.getFkFasciaAltimetrica()); // not null constraint
		parameters.add(intervSelvicolturale.getAnnataSilvana());
		parameters.add(intervSelvicolturale.getFlgPiedilista()); // not null constraint
		parameters.add(intervSelvicolturale.getFkTipoIntervento()); // not null constraint
		parameters.add(intervSelvicolturale.getFkFinalitaTaglio()); // not null constraint
		parameters.add(intervSelvicolturale.getFkDestLegname()); // not null constraint
		parameters.add(intervSelvicolturale.getNrPiante());
		parameters.add(intervSelvicolturale.getVolumeRamagliaM3());
		parameters.add(intervSelvicolturale.getStimaMassaRetraibileM3());
		parameters.add(intervSelvicolturale.getNoteEsbosco());
		parameters.add(0); // not null constraint
		parameters.add(intervSelvicolturale.getFlgIstanzaTaglio()); // not null constraint
		parameters.add(loggedConfig.getIdConfigUtente()); // not null constraint
		parameters.add(intervSelvicolturale.getFkGoverno());
		parameters.add(Date.valueOf(LocalDate.now()));
		parameters.add(intervSelvicolturale.getNoteFinaliRichiedente());
		
		//parameters.add(intervSelvicolturale.getFkTipoForestalePrevalente());
		//parameters.add(intervSelvicolturale.getFlgPiedilista());
		
		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}

	@Override
	public void updateIntervSelvicolturale(IntervSelvicolturale interventoSelvi, Integer idIntervento) {
		
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
		update.append(",note_finali_richiedente=?");
		
		update.append(" WHERE id_intervento=?");
		
		List<Object> parameters = new ArrayList<>();
		
		parameters.add(interventoSelvi.getNrPiante());
		parameters.add(interventoSelvi.getStimaMassaRetraibileM3());
		parameters.add(interventoSelvi.getVolumeRamagliaM3());
		parameters.add(interventoSelvi.getM3Prelevati());
		parameters.add(interventoSelvi.getNoteEsbosco());
		parameters.add(interventoSelvi.getFkTipoIntervento());
		parameters.add(interventoSelvi.getFkStatoIntervento());
		parameters.add(interventoSelvi.getFkFinalitaTaglio());
		parameters.add(interventoSelvi.getFkDestLegname());
		parameters.add(interventoSelvi.getFkFasciaAltimetrica());
		parameters.add(interventoSelvi.getAnnataSilvana());
		parameters.add(interventoSelvi.getFlgPiedilista());
		parameters.add(interventoSelvi.getFkGoverno());
		parameters.add(interventoSelvi.getFlgConformeDeroga());
		parameters.add(interventoSelvi.getFlgIstanzaTaglio());
		parameters.add(Date.valueOf(LocalDate.now()));
		parameters.add(interventoSelvi.getDataPresaInCarico()==null?
				null:java.sql.Date.valueOf(interventoSelvi.getDataPresaInCarico()));
		parameters.add(interventoSelvi.getNoteFinaliRichiedente());
		parameters.add(idIntervento);
		
		DBUtil.jdbcTemplate.update(update.toString(), parameters.toArray());
	}


	@Override
	public void insertStep1(IntervSelvicolturale intervSelvicolturale, Integer idIntervento) {
		StringBuilder sql = new StringBuilder();
		List<Object> parameters = new ArrayList<>();
		try {
		sql.append("INSERT INTO idf_t_interv_selvicolturale(");
		sql.append("id_intervento, fk_tipo_intervento, fk_governo, fk_categ_intervento, fk_proprieta, ");
		sql.append("fk_finalita_taglio, fk_dest_legname, fk_fascia_altimetrica, idgeo_pl_pfa, ");
		sql.append("fk_stato_intervento, data_presa_in_carico, annata_silvana, data_inserimento, fk_config_utente , fk_intervento_padre_variante, fk_intervento_padre_proroga) ");
		sql.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		

		parameters.add(idIntervento);
		parameters.add(intervSelvicolturale.getFkTipoIntervento());
		parameters.add(intervSelvicolturale.getFkGoverno());
		parameters.add(intervSelvicolturale.getFkCategoriaIntervento());
		parameters.add(intervSelvicolturale.getFkProprieta());

		parameters.add(Constants.NO_DATA);
		parameters.add(Constants.NO_DATA);
		parameters.add(Constants.NO_DATA);
		parameters.add(Constants.NO_DATA);

		parameters.add(intervSelvicolturale.getFkStatoIntervento());
		parameters.add(intervSelvicolturale.getDataPresaInCarico() == null ? null: Date.valueOf(intervSelvicolturale.getDataPresaInCarico()));
		parameters.add(intervSelvicolturale.getAnnataSilvana());
		parameters.add(Date.valueOf(LocalDate.now()));
		parameters.add(intervSelvicolturale.getFkConfigUtente()); // not null constraint
		parameters.add(intervSelvicolturale.getFkInterventoPadreProroga());
		parameters.add(intervSelvicolturale.getFkInterventoPadreVariante());
		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.info("<---- insertStep1 ERROR "+ e + "---"+parameters.toString());
		}
	}


	@Override
	public int updateStep1(IntervSelvicolturale intervSelvicolturale, Integer idIntervento) {
		StringBuilder sql = new StringBuilder();

		sql.append("UPDATE idf_t_interv_selvicolturale SET ");
		sql.append("fk_tipo_intervento = ? , fk_governo = ?, ");
		sql.append("fk_categ_intervento = ?, fk_proprieta = ?, ");
		sql.append("fk_stato_intervento = ?, data_presa_in_carico = ?, ");
		sql.append("annata_silvana = ?, data_aggiornamento = ?, fk_config_utente = ? ");
		sql.append("WHERE id_intervento = ?");

		List<Object> parameters = new ArrayList<>();
		parameters.add(intervSelvicolturale.getFkTipoIntervento());
		parameters.add(intervSelvicolturale.getFkGoverno());
		parameters.add(intervSelvicolturale.getFkCategoriaIntervento());
		parameters.add(intervSelvicolturale.getFkProprieta());
		parameters.add(intervSelvicolturale.getFkStatoIntervento());
		parameters.add(intervSelvicolturale.getDataPresaInCarico() == null ? null: Date.valueOf(intervSelvicolturale.getDataPresaInCarico()));
		parameters.add(intervSelvicolturale.getAnnataSilvana());
		parameters.add(Date.valueOf(LocalDate.now()));
		parameters.add(intervSelvicolturale.getFkConfigUtente()); // not null constraint
		parameters.add(idIntervento);

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
	public int updateIntervSelvicolturaletagliStep4(IntervSelvicolturale dto, Integer idIntervento) {

		StringBuilder update = new StringBuilder();

		StringBuilder sql = new StringBuilder();

		sql.append("UPDATE idf_t_interv_selvicolturale SET ");
		sql.append("fk_governo = ? , fk_governo2 = ?, ");
		sql.append("fk_tipo_intervento = ? , fk_tipo_intervento2 = ?, ");
		sql.append("superficie_int1_ha = ? , superficie_int2_ha = ?, ");
		sql.append("volume_ramaglia_m3 = ? , note_esbosco = ?, data_aggiornamento = ?  ");
		sql.append("WHERE id_intervento = ?");

		List<Object> parameters = new ArrayList<>();
		parameters.add(dto.getFkGoverno());
		parameters.add(dto.getFkGoverno2());
		parameters.add(dto.getFkTipoIntervento());
		parameters.add(dto.getFkTipoIntervento2());
		parameters.add(dto.getSuperficieInt1Ha());
		parameters.add(dto.getSuperficieInt2Ha());
		parameters.add(dto.getVolumeRamagliaM3());
		parameters.add(dto.getNoteEsbosco());
		parameters.add(Date.valueOf(LocalDate.now()));
		parameters.add(idIntervento);

		return DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}

	@Override
	public void updateNoteFinaliRichiedente(Integer idIntervento, String noteFinaliRichiedente) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE idf_t_interv_selvicolturale SET note_finali_richiedente= ?");
		sql.append(" WHERE id_intervento = ? ");

		List<Object> parameters = new ArrayList<>();
	    parameters.add(noteFinaliRichiedente);
	    parameters.add(idIntervento);
		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}
	
//	@Override
//	public String getNoteFinaliRichiedente(Integer idIntervento) {
//		StringBuilder sql = new StringBuilder();
//		sql.append("SELCT note_finali_richiedente FROM idf_t_interv_selvicolturale WHERE id_intervento = ?");
//		String noteRichiedente = DBUtil.jdbcTemplate.query(sql.toString(), String.class, idIntervento);
//		logger.info("---NOTE RICHIEDEENTE--- "+noteRichiedente);
//		logger.info("---RISULTATO IF--- " + noteRichiedente!=null? noteRichiedente : "");
//		return noteRichiedente!=null? noteRichiedente : "";
//	}
	
	@Override
	public void deleteIntervSelvicolturale(Integer idIntervento) {
		
		String sql = "DELETE FROM idf_t_interv_selvicolturale WHERE id_intervento = ? ";
		
		DBUtil.jdbcTemplate.update(sql, idIntervento);
		
	}

	@Override
	public int getProssimoNrProgInterv(int idTipoIntervento) {
		return DBUtil.jdbcTemplate.queryForObject(
				//"SELECT COALESCE(MAX(nr_progressivo_interv), 0) FROM idf_t_interv_selvicolturale WHERE fk_tipo_intervento = ?",
				"SELECT COALESCE(MAX(nr_progressivo_interv), 0) FROM idf_t_interv_selvicolturale WHERE idgeo_pl_pfa = ?",Integer.class,
				idTipoIntervento) + 1;
	}

	@Override
	public IntervSelvicolturale findInterventoSelvicolturale(Integer idIntervento) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select intselv.id_intervento, fk_tipo_intervento, fk_stato_intervento, idgeo_pl_pfa, fk_tipo_forestale_prevalente");
		sql.append(",fk_finalita_taglio, fk_dest_legname, fk_fascia_altimetrica, flg_interv_non_previsto");
		// 777 AbiSoft
		sql.append(",nr_progressivo_interv, cod_stato_intervento, descr_stato_intervento");
		// 777
		sql.append(",nr_piante, stima_massa_retraibile_m3, m3_prelevati, volume_ramaglia_m3");
		sql.append(",data_presa_in_carico, annata_silvana, nr_progressivo_interv, flg_istanza_taglio");
		sql.append(", flg_piedilista, flg_conforme_deroga, note_esbosco, intselv.data_inserimento, intselv.data_aggiornamento");
		sql.append(", intselv.fk_config_utente, ripresa_prevista_mc, ripresa_reale_fine_interv_mc, fk_governo");
		sql.append(", intselvuso.fk_uso_viabilita, intselesb.cod_esbosco");
		sql.append(", fk_proprieta, fk_categ_intervento, intselv.fk_tipo_intervento2, intselv.fk_governo2, intselv.superficie_int1_ha, intselv.superficie_int2_ha ");
		sql.append(" from IDF_T_INTERV_SELVICOLTURALE intselv");
		sql.append(" LEFT JOIN IDF_R_INTERVENTOSELV_USOVIAB intselvuso using (id_intervento)");
		sql.append(" LEFT JOIN IDF_R_INTERVSELV_ESBOSCO intselesb using (id_intervento)");
		// 777 AbiSoft
		sql.append(" LEFT JOIN IDF_D_STATO_INTERVENTO statointselv on fk_stato_intervento = statointselv.id_stato_intervento ");
		// 777
		sql.append(" WHERE id_intervento = ?");
		
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(),intervSelvicolturaleMapper ,idIntervento);
	}


	@Override
	public IntervSelvicolturaleFat findInterventoSelvicolturaleTagli(Integer idIntervento) {
		StringBuilder sql = new StringBuilder();

		sql.append("select intselv.id_intervento, fk_tipo_intervento, fk_stato_intervento, idgeo_pl_pfa, fk_tipo_forestale_prevalente");
		sql.append(",fk_finalita_taglio, fk_dest_legname, fk_fascia_altimetrica, flg_interv_non_previsto");
		sql.append(",nr_piante, stima_massa_retraibile_m3, m3_prelevati, volume_ramaglia_m3");
		sql.append(",data_presa_in_carico, annata_silvana, nr_progressivo_interv, flg_istanza_taglio");
		sql.append(", flg_piedilista, flg_conforme_deroga, note_esbosco, intselv.data_inserimento, intselv.data_aggiornamento");
		sql.append(", intselv.fk_config_utente, ripresa_prevista_mc, ripresa_reale_fine_interv_mc, fk_governo");
		sql.append(", fk_proprieta, fk_categ_intervento, intselv.fk_tipo_intervento2, intselv.fk_governo2, intselv.superficie_int1_ha, intselv.superficie_int2_ha ");
		sql.append(", gov1.descr_governo descr_governo1, gov2.descr_governo descr_governo2 ");
		sql.append(", tpint1.descr_intervento as desc_tipo_int1, tpint2.descr_intervento as desc_tipo_int2  ");
		sql.append(", tpint1.cod_intervento as cod_tipo_int1, tpint2.cod_intervento as cod_tipo_int2  ");
		sql.append(", intselv.fk_intervento_padre_variante, intselv.fk_intervento_padre_proroga   ");
		
		sql.append(", tpdevento.id_tipo_evento ");
		sql.append(", tprevent.id_evento ");
		
		sql.append(", intselv.note_finali_richiedente ");
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
			return DBUtil.jdbcTemplate.queryForObject(sql.toString(),Integer.class,idIntervento);
		}

		@Override
		public String getStatoInterventoByIdIntervento(Integer idIntervento) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT descr_stato_intervento FROM idf_t_interv_selvicolturale itis ");
			sql.append(" JOIN idf_d_stato_intervento idsi on itis.fk_stato_intervento = idsi.id_stato_intervento "); 
			sql.append(" WHERE id_intervento = ? ");
			
			return DBUtil.jdbcTemplate.queryForObject(sql.toString(), String.class,idIntervento);
		}
		
		@Override
		public Integer getIdgeoPlPfaByIdIntervento(Integer idIntervento) {
			String sql = "SELECT idgeo_pl_pfa from idf_t_interv_selvicolturale WHERE id_intervento = ?";
			return DBUtil.jdbcTemplate.queryForObject(sql,Integer.class, idIntervento);
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
	public Integer getFkProprietaByidIntervento(Integer idIntervento){
		String sql = "SELECT fk_proprieta FROM idf_t_interv_selvicolturale WHERE id_intervento = ?";
		return DBUtil.jdbcTemplate.queryForObject(sql, Integer.class, idIntervento) ;
	}
}
