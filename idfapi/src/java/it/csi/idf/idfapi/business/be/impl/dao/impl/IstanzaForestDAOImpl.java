/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.csi.idf.idfapi.dto.*;
import it.csi.idf.idfapi.mapper.*;
import it.csi.idf.idfapi.util.*;
import org.apache.log4j.Logger;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.IstanzaForestDAO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.AutorizzaVincolo;

@Component
public class IstanzaForestDAOImpl extends GenericDAO implements IstanzaForestDAO {
	
	static final Logger logger = Logger.getLogger(IstanzaForestDAOImpl.class);

	private static final String COMUNE = "comune";
	private static final String TIP_TRASF = "tipTrasf";
	private static final String UBICAZIONE = "ubicazione";
	private static final String CAT_FOR = "catFor";
	private static final String GOV_FORM = "govForm";
	private static final String CALC_ECON_A = "calcEconA";
	private static final String CALC_ECON_DA = "calcEconDa";
	private static final String DATA_PRES_A = "dataPresA";
	private static final String DATA_PRES_DA = "dataPresDa";
	private static final String VINC_IDRO = "vincIdro";
	private static final String STATO_ISTANZA = "statoIstanza";
	private static final String ANNO_ISTANZA = "annoIstanza";

	private static final String ASCENDING = "ASC";
	private static final String DESCENDING = "DESC";
	private static final char ORDER = '-';
	private final IstanzaForestMapper istanzaMapper = new IstanzaForestMapper();
	private final BOSearchResultMapper boSearchResultMapper = new BOSearchResultMapper();

	@Override
	public int createIstanzaForest(IstanzaForest istanzaForest) {
		StringBuilder sql = new StringBuilder("INSERT INTO idf_t_istanza_forest(");
		sql.append("id_istanza_intervento, fk_sogg_settore_regionale, fk_stato_istanza, nr_istanza_forestale");
		sql.append(", data_invio, data_verifica, data_protocollo, nr_protocollo, data_ult_agg, data_inserimento");
		sql.append(", data_aggiornamento, fk_config_utente, fk_tipo_istanza");
		sql.append(") VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");

		List<Object> params = new ArrayList<>();
		params.add(istanzaForest.getIdIstanzaIntervento());
		params.add(istanzaForest.getFkSoggSettoreRegionale());
		params.add(istanzaForest.getFkStatoIstanza());
		params.add(istanzaForest.getNrIstanzaForestale());
		params.add(istanzaForest.getDataInvio() == null ? null : Date.valueOf(istanzaForest.getDataInvio()));
		params.add(istanzaForest.getDataVerifica() == null ? null : Date.valueOf(istanzaForest.getDataVerifica()));
		params.add(istanzaForest.getDataProtocollo() == null ? null : Date.valueOf(istanzaForest.getDataProtocollo()));
		params.add(istanzaForest.getNrProtocollo());
		params.add(istanzaForest.getDataUltAgg() == null ? null : Date.valueOf(istanzaForest.getDataUltAgg()));
		params.add(
				istanzaForest.getDataInserimento() == null ? null : Date.valueOf(istanzaForest.getDataInserimento()));
		params.add(istanzaForest.getDataAggiornamento() == null ? null
				: Date.valueOf(istanzaForest.getDataAggiornamento()));
		params.add(istanzaForest.getFkConfigUtente());
		params.add(istanzaForest.getFkTipoIstanza());
		// 777 
		System.out.println("IstanzaForestDAO::createIstanzaForest");
		System.out.println(sql.toString());
		return DBUtil.jdbcTemplate.update(sql.toString(), params.toArray());
	}

	@Override
	public int getNumberOfInstanceType(int instanceTypeId) {
		int val = DBUtil.jdbcTemplate.queryForObject(
				"SELECT COUNT(id_istanza_intervento) FROM idf_t_istanza_forest WHERE fk_tipo_istanza = ?",
				Integer.class, instanceTypeId);
		if(val == 0)
			return val;
		return DBUtil.jdbcTemplate.queryForObject(
				"SELECT MAX(COALESCE (nr_istanza_forestale, 0)) FROM idf_t_istanza_forest WHERE fk_tipo_istanza = ?",
				Integer.class, instanceTypeId);
	}

	@Override
	public int getNumberOfAllInstanceType() {
		int val = DBUtil.jdbcTemplate.queryForObject(
				"SELECT COUNT(id_istanza_intervento) FROM idf_t_istanza_forest",
				Integer.class);
		if(val == 0)
			return val;
		return DBUtil.jdbcTemplate.queryForObject(
				"SELECT MAX(COALESCE (nr_istanza_forestale, 0)) FROM idf_t_istanza_forest",
				Integer.class);
	}

	@Override
	public int getNumberOfInstanceTagli() {
		int val = DBUtil.jdbcTemplate.queryForObject(
			"SELECT COUNT(id_istanza_intervento) FROM idf_t_istanza_forest WHERE fk_tipo_istanza in (2,3)",	Integer.class);
		if(val == 0)
			return val;
		return DBUtil.jdbcTemplate.queryForObject(
			"SELECT MAX(COALESCE (nr_istanza_forestale, 0)) FROM idf_t_istanza_forest WHERE fk_tipo_istanza in(2,3) ",	Integer.class);
	}

	@Override
	public IstanzaForest getByIdIntervento(int idIntervento) {
		String sql = "SELECT * FROM idf_t_istanza_forest WHERE id_istanza_intervento = ?";
		List<IstanzaForest> res = DBUtil.jdbcTemplate.query(sql, istanzaMapper, idIntervento);
		if(res != null && res.size() > 0) {
			return res.get(0);
		}
		return null;
	}

	@Override
	public void updateInvioIstanza(Integer idIntervento, String modificaUtilizzatore) {
		logger.info("-------modificaUtilizzatore---------: " + modificaUtilizzatore);
		
		StringBuilder sql = new StringBuilder();

		if(modificaUtilizzatore != null && modificaUtilizzatore == "MODIFICA_UTILIZZATORE_TAGLI") {
			sql.append("UPDATE idf_t_istanza_forest SET data_invio = ? ");	
		} else {
			sql.append("UPDATE idf_t_istanza_forest SET data_invio = ?, fk_stato_istanza = ? ");
		}
			sql.append(", data_aggiornamento = ? ");
			sql.append(" WHERE id_istanza_intervento = ? ");

		List<Object> parameters = new ArrayList<>();

		if(modificaUtilizzatore != null && modificaUtilizzatore == "MODIFICA_UTILIZZATORE_TAGLI") {
			parameters.add(Date.valueOf(LocalDate.now()));
			parameters.add(Date.valueOf(LocalDate.now()));
			parameters.add(idIntervento);
		 } else {
			parameters.add(Date.valueOf(LocalDate.now()));
			parameters.add(InstanceStateEnum.INVIATA.getStateValue());
			parameters.add(Date.valueOf(LocalDate.now()));
			parameters.add(idIntervento);
		 }

		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}

	@Override
	public void updateIstanzaTo(Integer idIntervento, String motivazione, Integer fkConfigUtente,
			InstanceStateEnum instanceStateEnum) {
		StringBuilder sql = new StringBuilder();
		
		List<Object> parameters = new ArrayList<>();

		switch (instanceStateEnum) {
		case RIFIUTATA:
			sql.append("UPDATE idf_t_istanza_forest SET  fk_stato_istanza = ?,data_aggiornamento = ?, fk_config_utente = ?, "
					+ "motivazione_rifiuto = ? ");
			parameters.add(InstanceStateEnum.RIFIUTATA.getStateValue());
			parameters.add(Date.valueOf(LocalDate.now()));
			parameters.add(fkConfigUtente);
			parameters.add(motivazione);
			break;
		case VERIFICATA:
			sql.append("UPDATE idf_t_istanza_forest SET  fk_stato_istanza = ?, data_aggiornamento = ?, data_verifica = ? , fk_config_utente = ? ");
			parameters.add(InstanceStateEnum.VERIFICATA.getStateValue());
			parameters.add(Date.valueOf(LocalDate.now()));
			parameters.add(Date.valueOf(LocalDate.now()));
			parameters.add(fkConfigUtente);
			break;
		case ARCHIVIATA:
			sql.append("UPDATE idf_t_istanza_forest SET  fk_stato_istanza = ?, data_aggiornamento = ?, data_verifica = ? , fk_config_utente = ? ");
			parameters.add(InstanceStateEnum.ARCHIVIATA.getStateValue());
			parameters.add(Date.valueOf(LocalDate.now()));
			parameters.add(Date.valueOf(LocalDate.now()));
			parameters.add(fkConfigUtente);
			break;
		case SCADUTA:
			sql.append("UPDATE idf_t_istanza_forest SET  fk_stato_istanza = ?, data_aggiornamento = ?, data_verifica = ? , fk_config_utente = ? ");
			parameters.add(InstanceStateEnum.SCADUTA.getStateValue());
			parameters.add(Date.valueOf(LocalDate.now()));
			parameters.add(Date.valueOf(LocalDate.now()));
			parameters.add(fkConfigUtente);
			break;
		case ANNULLATA:
			sql.append("UPDATE idf_t_istanza_forest SET  fk_stato_istanza = ?,data_aggiornamento = ?, fk_config_utente = ?, "
					+ "motivazione_annullamento = ? ");
			parameters.add(InstanceStateEnum.ANNULLATA.getStateValue());
			parameters.add(Date.valueOf(LocalDate.now()));
			parameters.add(fkConfigUtente);
			parameters.add(motivazione);
			break;
		default:
			
		}
		
		sql.append("  WHERE id_istanza_intervento = ? ");
		parameters.add(idIntervento);
		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());

	}
	
	@Override
	public void updateIstanzaVincoloAutorizzata(AutorizzaVincolo autorizzazione, ConfigUtente confUtente) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE idf_t_istanza_forest SET  fk_stato_istanza = ?, data_aggiornamento = ?, ");
		sql.append("data_verifica = ? ,nr_determina_aut = ? , data_termine_aut = ?, ");
		sql.append("fk_config_utente = ? WHERE id_istanza_intervento = ? ");
		
		List<Object> parameters = new ArrayList<>();
		parameters.add(InstanceStateEnum.VERIFICATA.getStateValue());
		parameters.add(Date.valueOf(LocalDate.now()));
		parameters.add(autorizzazione.getDataVerifica());
		parameters.add(autorizzazione.getNrDeterminaAutoriz());
		parameters.add(autorizzazione.getDataTermineAut());
		parameters.add(confUtente.getIdConfigUtente());
		parameters.add(autorizzazione.getIdIntervento());
		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}
	
	@Override
	public void updateIstanzaTagliAutorizzata(AutorizzaIstanza autorizzazione, ConfigUtente confUtente) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE idf_t_istanza_forest SET  fk_stato_istanza = ?, data_aggiornamento = ?, ");
		sql.append("data_verifica = ? ,nr_determina_aut = ? , data_termine_aut = ?, ");
		sql.append("fk_config_utente = ? WHERE id_istanza_intervento = ? ");

		List<Object> parameters = new ArrayList<>();
		parameters.add(InstanceStateEnum.VERIFICATA.getStateValue());
		parameters.add(Date.valueOf(LocalDate.now()));
		parameters.add(autorizzazione.getDataVerifica());
		parameters.add(autorizzazione.getNrDeterminaAutoriz());
		parameters.add(autorizzazione.getDataTermineAut());
		parameters.add(confUtente.getIdConfigUtente());
		parameters.add(autorizzazione.getIdIntervento());
		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}

	@Override
	public void updateEnteCompetente(Integer idIntervento, Integer idSoggetto) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE idf_t_istanza_forest SET fk_sogg_settore_regionale = ?");
		sql.append(" WHERE id_istanza_intervento = ? ");

		DBUtil.jdbcTemplate.update(sql.toString(), new Object[] {idSoggetto,idIntervento});
	}
	
	
	@Override
	public void updateMotivazione(Integer idIntervento, String motivazione) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE idf_t_istanza_forest SET note_istanza_alternativa = ?");
		sql.append(" WHERE id_istanza_intervento = ? ");

		List<Object> parameters = new ArrayList<>();
	    parameters.add(motivazione);
	    parameters.add(idIntervento);
		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}

	@Override
	public PaginatedList<BOSearchResult> backOfficeSearch(Map<String, String> queryParams, ConfigUtente configUtente) throws ParseException {
		List<Object> parameters = new ArrayList<>();
		String sql = createWhereClauseForSearchAndPopulateParameters(queryParams, backOfficeSearchJoinTables(),
				parameters, configUtente);
		logger.info("backOfficeSearch sql: " + sql);
		return super.paginatedList(sql, parameters, boSearchResultMapper, Integer.parseInt(queryParams.get("page")),
				Integer.parseInt(queryParams.get("limit")));
	}


	@Override
	public PaginatedList<PlainSoggettoIstanzaTagli> backOfficeTagliSearch(Map<String, String> queryParams, ConfigUtente configUtente) throws ParseException {
		List<Object> parameters = new ArrayList<>();

		StringBuilder sql = getSqlBackOfficeTagliSearch(queryParams);
		sql.append(getWhereClauseForSearchAndPopulateParametersTagli(queryParams, parameters, configUtente));

		logger.info("backOfficeTagliSearch sql: " + sql);
		return super.paginatedList(sql.toString(), parameters, new PlainSoggettoIstanzaTagliMapper(), Integer.parseInt(queryParams.get("page")),
				Integer.parseInt(queryParams.get("limit")));
	}



	private String backOfficeSearchJoinTables() {
		StringBuilder sql = new StringBuilder();

		sql.append(
				"SELECT distinct ifo.nr_istanza_forestale,i.id_intervento,ifo.data_invio, ifo.id_istanza_intervento, ");
		sql.append(" TO_CHAR(ifo.data_inserimento, 'YYYY') as anno_istanza , ");
		sql.append(" ti.id_tipo_istanza, ti.descr_dett_tipoistanza,sti.id_stato_istanza, sti.descr_stato_istanza, ");
		sql.append(
				" s.id_soggetto, s.codice_fiscale , s.partita_iva, s.nome, s.cognome, s.denominazione,s.fk_config_utente as worked_conf_utente , ");
		sql.append(
				" aapp.id_intervento as intervento_aapp, rn.id_intervento as intervento_rn, pop.id_intervento as intervento_pop, ");
		sql.append(" it.flg_vinc_idro, it.flg_compensazione, it.compensazione_euro_teorica,comune_info,transf_info  ");
		sql.append(" FROM idf_t_istanza_forest ifo  ");
		sql.append(" LEFT JOIN idf_t_intervento i ON ifo.id_istanza_intervento = i.id_intervento  ");
		sql.append(" LEFT JOIN idf_t_interv_trasformazione it ON i.id_intervento = it.id_intervento  ");

		sql.append(
				" INNER JOIN (SELECT idfr.id_intervento,idfr.id_soggetto,idfr.fk_config_utente,codice_fiscale,partita_iva, nome, cognome, denominazione ,ifts.fk_comune ");
		sql.append(" FROM idf_r_soggetto_intervento idfr ");
		sql.append(" LEFT JOIN idf_t_soggetto ifts using (id_soggetto)  ) s ON i.id_intervento = s.id_intervento  ");

		sql.append(" LEFT JOIN idf_r_intervento_catfor cat ON i.id_intervento = cat.id_intervento  ");

		sql.append(" LEFT JOIN idf_d_stato_istanza sti ON ifo.fk_stato_istanza = sti.id_stato_istanza ");

		return sql.toString();
	}

	private String createWhereClauseForSearchAndPopulateParameters(Map<String, String> queryParams, String joinedTables,
			List<Object> parameters, ConfigUtente configUtente) throws ParseException {
		StringBuilder sql = new StringBuilder();
		sql.append(joinedTables);
		
		String sep = " WHERE ";

		sql.append(" INNER JOIN (select * from idf_d_tipo_istanza where id_tipo_istanza= ? )   ti ON ifo.fk_tipo_istanza = ti.id_tipo_istanza ");
		
		parameters.add(Integer.parseInt(queryParams.get("tipoIstanza")));
		if (queryParams.get(GOV_FORM) != null || queryParams.get(CAT_FOR) != null || queryParams.get(UBICAZIONE) != null
				|| queryParams.get(TIP_TRASF) != null) {
			sql.append(
					" INNER JOIN (SELECT id_intervento,STRING_AGG  ('{id:'||id_parametro_trasf||'}',', ') transf_info FROM idf_r_paramtrasf_trasformazion ");

			sql.append(sep).append(" id_parametro_trasf IN(");
			StringBuilder additionalSql = new StringBuilder();
			if (queryParams.get(GOV_FORM) != null) {
				additionalSql.append(Integer.parseInt(queryParams.get(GOV_FORM))).append(",");
			}
			if (queryParams.get(CAT_FOR) != null) {
				additionalSql.append(Integer.parseInt(queryParams.get(CAT_FOR))).append(",");
			}
			if (queryParams.get(UBICAZIONE) != null) {
				additionalSql.append(Integer.parseInt(queryParams.get(UBICAZIONE))).append(",");
			}
			if (queryParams.get(TIP_TRASF) != null) {
				additionalSql.append(Integer.parseInt(queryParams.get(TIP_TRASF))).append(",");
			}
			sql.append(additionalSql.toString().substring(0, additionalSql.length() - 1)).append(")");
			sep = " AND ";
		} else {
			sql.append(
					" LEFT JOIN (SELECT id_intervento,STRING_AGG  ('{id:'||id_parametro_trasf||'}',', ') transf_info FROM idf_r_paramtrasf_trasformazion ");

		}

		sql.append(" group by id_intervento) pt ON i.id_intervento = pt.id_intervento ");

		sep = " WHERE ";

		if (queryParams.get("prov") != null || configUtente.getFkProfiloUtente() == 3 || configUtente.getFkProfiloUtente() == 4 ) {
			sql.append(
					" INNER JOIN ( SELECT inCom.id_intervento,STRING_AGG  ('{\"id_comune\":'||inCom.id_comune || ',\"' ||'istat_comune\":\"'|| inCom.istat_comune || '\",\"' || 'istat_prov\":\"'|| inCom.istat_prov || '\",\"' ||'denominazione_comune\":\"'|| inCom.denominazione_comune||'\"}',', ')comune_info ");

			sql.append(
					"FROM ( SELECT id_intervento,idgeo_pl_prop_catasto,id_comune,istat_comune,istat_prov,denominazione_comune FROM idf_r_propcatasto_intervento idfr ");
			sql.append(" LEFT JOIN idf_geo_pl_prop_catasto using (idgeo_pl_prop_catasto) ");
			sql.append(" LEFT JOIN idf_geo_pl_comune on id_comune=fk_comune  ");

			if(queryParams.get("prov") != null) {
				sql.append(sep).append(" istat_prov = ?");
				parameters.add((queryParams.get("prov")));
				sep = " AND ";
			}
			if(configUtente.getFkProfiloUtente() == 3) {
				sql.append(sep).append(" istat_prov in (select pg.istat_prov_competenza_terr " + 
						"from IDF_R_PF_PG pfpg inner join idf_t_soggetto pg on pfpg.id_soggetto_pg = pg.id_soggetto " + 
						"where pfpg.id_soggetto_pf = ?)");
				parameters.add(configUtente.getFkSoggetto());
				sep = " AND ";
			}
			
			if (queryParams.get(COMUNE) != null) {
				sql.append(sep).append(" id_comune = ?");
				parameters.add(Integer.parseInt(queryParams.get(COMUNE)));
				sep = " AND ";
			}
			
			if (configUtente.getFkProfiloUtente() == 4) {
				sql.append(sep).append(" istat_comune in (select pg.istat_comune_competenza_terr " + 
						"from IDF_R_PF_PG pfpg inner join idf_t_soggetto pg on pfpg.id_soggetto_pg = pg.id_soggetto " + 
						"where pfpg.id_soggetto_pf = ?)");
				parameters.add(configUtente.getFkSoggetto());
				sep = " AND ";
			}

			if (queryParams.get("foglio") != null) {
				sql.append(sep).append(" foglio = ?");
				parameters.add(Integer.parseInt(queryParams.get("foglio")));
				sep = " AND ";
			}

			if (queryParams.get("particella") != null) {
				sql.append(sep).append(" TRIM(particella) = ? ");
				parameters.add(queryParams.get("particella"));
				sep = " AND ";
			}

			if (queryParams.get("sezione") != null) {
				sql.append(sep).append(" sezione = ? ");
				parameters.add(queryParams.get("sezione"));
				sep = " AND ";
			}
			sql.append("  ) inCom group by id_intervento) cmn ON cmn.id_intervento=i.id_intervento ");
			sep = " WHERE ";

		} else {

			sql.append(
					" LEFT JOIN ( SELECT inCom.id_intervento,STRING_AGG  ('{\"id_comune\":'||inCom.id_comune || ',\"' ||'istat_comune\":\"'|| inCom.istat_comune || '\",\"' || 'istat_prov\":\"'|| inCom.istat_prov || '\",\"' ||'denominazione_comune\":\"'|| inCom.denominazione_comune||'\"}',', ')comune_info ");

			sql.append(
					"FROM ( SELECT id_intervento,idgeo_pl_prop_catasto,id_comune,istat_comune,istat_prov,denominazione_comune FROM idf_r_propcatasto_intervento idfr ");
			sql.append(" LEFT join idf_geo_pl_prop_catasto using (idgeo_pl_prop_catasto) ");
			sql.append(" LEFT join idf_geo_pl_comune on id_comune=fk_comune ) inCom ");
			sql.append("  group by id_intervento) cmn ON cmn.id_intervento=i.id_intervento ");
		}

		sep = " WHERE ";


		if (("true").equals(queryParams.get("aapp"))) {
			sql.append(" INNER JOIN ( SELECT DISTINCT(id_intervento) from idf_r_intervento_aapp ) aapp ON i.id_intervento = aapp.id_intervento  ");
		}
		else {
			sql.append("LEFT JOIN ( SELECT DISTINCT(id_intervento) from idf_r_intervento_aapp ) aapp ON i.id_intervento = aapp.id_intervento  ");
		}
	
		if (("true").equals(queryParams.get("rn2k"))) {
			sql.append(" INNER JOIN ( SELECT DISTINCT(id_intervento) from idf_r_intervento_rn_2000 ) rn ON i.id_intervento = rn.id_intervento  ");
			}
		else {
			sql.append("LEFT JOIN ( SELECT DISTINCT(id_intervento) from idf_r_intervento_rn_2000 ) rn ON i.id_intervento = rn.id_intervento  ");
		}
	
		if (("true").equals(queryParams.get("popSeme"))) {
			sql.append(" INNER JOIN ( SELECT DISTINCT(id_intervento) from idf_r_intervento_pop_seme ) pop ON i.id_intervento = pop.id_intervento  ");
			}
		else {
			sql.append("LEFT JOIN ( SELECT DISTINCT(id_intervento) from idf_r_intervento_pop_seme ) pop ON i.id_intervento = pop.id_intervento  ");
		}

		if (queryParams.get("numIstanza") != null) {
			sql.append(sep).append(" ifo.nr_istanza_forestale = ? ");
			parameters.add(Integer.parseInt(queryParams.get("numIstanza")));
			sep = " AND ";
		}

		if (queryParams.get(STATO_ISTANZA) != null) {
			sql.append(sep).append(" ifo.fk_stato_istanza = ? ");
			parameters.add(Integer.parseInt(queryParams.get(STATO_ISTANZA)));
			sep = " AND ";
		}else {
			sql.append(sep).append(" ifo.fk_stato_istanza > 1  ");
			sep = " AND ";
		}

		if (queryParams.get(ANNO_ISTANZA) != null) {
			sql.append(sep).append(" TO_CHAR(ifo.data_inserimento, 'YYYY') = ? ");
			parameters.add(queryParams.get(ANNO_ISTANZA));
			sep = " AND ";
		}

		if (queryParams.get(DATA_PRES_DA) != null && queryParams.get(DATA_PRES_A) != null) {
			Date dateDa = java.sql.Date.valueOf(queryParams.get(DATA_PRES_DA));
			Date dateA = java.sql.Date.valueOf(queryParams.get(DATA_PRES_A));
			sql.append(sep).append(" ( ifo.data_invio >= ? AND ifo.data_invio <= ? ) ");
			parameters.add(dateDa);
			parameters.add(dateA);
			sep = " AND ";
		} else if (queryParams.get(DATA_PRES_DA) != null) {
			Date date = java.sql.Date.valueOf(queryParams.get(DATA_PRES_DA));
			sql.append(sep).append(" ifo.data_invio >= ? ");
			parameters.add(date);
			sep = " AND ";
		} else if (queryParams.get(DATA_PRES_A) != null) {
			Date date = java.sql.Date.valueOf(queryParams.get(DATA_PRES_A));
			sql.append(sep).append(" ifo.data_invio <= ? ");
			parameters.add(date);
			sep = " AND ";
		}

		
		if (queryParams.get("pf") != null || queryParams.get("pg") != null || queryParams.get("prof") != null) {
			sql.append(sep).append(" s.id_soggetto IN(");
			StringBuilder tempSql = new StringBuilder();
			if (queryParams.get("pf") != null) {
				tempSql.append(queryParams.get("pf")).append(",");
			}
			if (queryParams.get("pg") != null) {
				tempSql.append(queryParams.get("pg")).append(",");
			}
			if (queryParams.get("prof") != null) {
				tempSql.append(queryParams.get("prof")).append(",");
			}
			sql.append(tempSql.toString().substring(0, tempSql.length() - 1)).append(")");
			sep = " AND ";
		}
		
		
		
		if ("true".equals(queryParams.get(VINC_IDRO))) {
			sql.append(sep).append(" it.flg_vinc_idro = 1 ");
			sep = " AND ";
		}

		if (queryParams.get("compNec") != null) {
			if (Boolean.valueOf(queryParams.get("compNec"))) {
				sql.append(sep).append(" it.flg_compensazione IN ('M', 'F') ");
				sep = " AND ";
			} else {
				sql.append(sep).append(" it.flg_compensazione = 'N' ");
				sep = " AND ";
				if (Boolean.valueOf(queryParams.get("compO1"))) {
					sql.append(sep).append(" flg_art7_a = '1' ");
				}
				if (Boolean.valueOf(queryParams.get("compO2"))) {
					sql.append(sep).append(" flg_art7_b = '1' ");
				}
				if (Boolean.valueOf(queryParams.get("compO3"))) {
					sql.append(sep).append(" flg_art7_c = '1' ");
				}
				if (Boolean.valueOf(queryParams.get("compO4"))) {
					sql.append(sep).append(" flg_art7_d = '1' ");
				}
				if (Boolean.valueOf(queryParams.get("compO5"))) {
					sql.append(sep).append(" flg_art7_d_bis = '1' ");
				}
			}
		}

		if (queryParams.get(CALC_ECON_DA) != null && queryParams.get(CALC_ECON_A) != null) {
			sql.append(sep).append(" ( it.compensazione_euro_teorica >= ? AND it.compensazione_euro_teorica <= ? )");
			parameters.add(Integer.parseInt(queryParams.get(CALC_ECON_DA)));
			parameters.add(Integer.parseInt(queryParams.get(CALC_ECON_A)));
		} else if (queryParams.get(CALC_ECON_DA) != null) {
			sql.append(sep).append(" it.compensazione_euro_teorica >= ? ");
			parameters.add(Integer.parseInt(queryParams.get(CALC_ECON_DA)));
		} else if (queryParams.get(CALC_ECON_A) != null) {
			sql.append(sep).append(" it.compensazione_euro_teorica <= ? ");
			parameters.add(Integer.parseInt(queryParams.get(CALC_ECON_A)));
		}

		return sql.append(getQuerySortingSegment(queryParams.get("sort"))).toString();
	}

	private static String getQuerySortingSegment(String sort) {
		String sortField = null;
		String sortOrder = null;

		StringBuilder sql = new StringBuilder();
		if (sort != null && !sort.isEmpty()) {
			sortOrder = sort.charAt(0) == ORDER ? DESCENDING : ASCENDING;
			sortField = sortOrder.equals(DESCENDING) ? sort.substring(1) : sort;
			if (sortField != null && !sortField.isEmpty()) {
				sql.append(" ORDER BY ");
				sql.append(translateFieldNameForBackOffice(sortField)).append(" ").append(sortOrder);
			}
		}
		return sql.toString();
	}

	private static String translateFieldNameForBackOffice(String fieldName) {
		Map<String, String> mappedColumnNames = new HashMap<>();
		mappedColumnNames.put("tipologiaIstanza", "fk_tipo_istanza");
		mappedColumnNames.put("numeroIstanza", "nr_istanza_forestale");
		mappedColumnNames.put(STATO_ISTANZA, "fk_stato_istanza");
		mappedColumnNames.put(ANNO_ISTANZA, "data_inserimento");
		mappedColumnNames.put("dataPresentazione", "data_invio");
		mappedColumnNames.put("prov", "provincia");
		mappedColumnNames.put(COMUNE, "denominazione_comune");
		mappedColumnNames.put("richiedente", "codice_fiscale");
		mappedColumnNames.put("areeProtette", "");
		mappedColumnNames.put("natura2000", "");
		mappedColumnNames.put("populamenti", "");
		mappedColumnNames.put("vincIdrogeologico", "");
		mappedColumnNames.put("compensazione", "");
		mappedColumnNames.put("euro", "");
		return mappedColumnNames.get(fieldName) != null ? mappedColumnNames.get(fieldName) : "nr_istanza_forestale";
	}

	@Override
	public ConfigUtente getUtenteForIstanzaById(Integer idIntervento) {
		String sql = "SELECT * FROM idf_t_istanza_forest WHERE id_istanza_intervento = ?";

		IstanzaForest res = DBUtil.jdbcTemplate.queryForObject(sql, istanzaMapper, idIntervento);
		String sql1 = "SELECT * FROM idf_cnf_config_utente  WHERE id_config_utente = ? ";
		ConfigUtente conUt = DBUtil.jdbcTemplate.queryForObject(sql1, new ConfigUtenteMapper(),
				res.getFkConfigUtente());
		return conUt;
	}


	@Override
	public IstanzaDetails getIstanzaDetailsByIdIntervento(Integer idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.descr_proprieta, nr_progressivo_interv , flg_conforme_deroga, "); 
		sql.append(" itis.data_inserimento,itis.data_presa_in_carico "); 
		sql.append(" from idf_t_interv_selvicolturale itis "); 
		sql.append(" join idf_geo_pl_pfa pfa on itis.idgeo_pl_pfa  = pfa.idgeo_pl_pfa "); 
		sql.append(" left join idf_d_proprieta p on pfa.fk_proprieta_primpa = p.id_proprieta "); 
		sql.append(" where itis.id_intervento = ?");
		
		
		return DataAccessUtils.singleResult(DBUtil.jdbcTemplate.query(sql.toString(), new IstanzaDetailsMapper(),idIntervento));
	}

	@Override
	public void deleteById(Integer idIntervento) {
		
		String sql = "DELETE FROM idf_t_istanza_forest WHERE id_istanza_intervento =?";
		
		DBUtil.jdbcTemplate.update(sql.toString(), idIntervento);
	}

	@Override
	public int updateTipoIstanza(Integer idTipoIstanza, Integer fkConfigUtente, Integer idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE idf_t_istanza_forest SET fk_tipo_istanza = ?, ");
		sql.append(" data_aggiornamento = ?, fk_config_utente = ? ");
		sql.append(" WHERE id_istanza_intervento = ? ");

		List<Object> parameters = new ArrayList<>();
		parameters.add(idTipoIstanza);
		parameters.add(Date.valueOf(LocalDate.now()));
		parameters.add(fkConfigUtente);
		parameters.add(idIntervento);
		return DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}

	@Override
	public TipoIstanzaEnum getLastIstanzaTipo(Integer fkConfigUtente) {
		StringBuilder sql = new StringBuilder();
		sql.append("select fk_tipo_istanza from idf_t_istanza_forest ");
		sql.append("where fk_config_utente = ? ");
		sql.append("order by data_aggiornamento ,data_inserimento limit 1 ");
		List<Integer> res = DBUtil.jdbcTemplate.queryForList(sql.toString(), Integer.class, fkConfigUtente);
		if(res != null && res.size() > 0) {
			return TipoIstanzaEnum.getEnum(res.get(0));
		}
		return null;
	}

	@Override
	public PaginatedList<PlainSoggettoIstanzaVincolo> backOfficeVincoloSearch(Map<String, String> queryParams,
			ConfigUtente configUtente) throws ParseException {
		StringBuilder sql = getSqlBackOfficeVincoloSearch(queryParams);
		List<Object> parameters = new ArrayList<Object>();
		//(Map<String, String> queryParams,	List<Object> parameters, ConfigUtente configUtente)
		sql.append(createWhereClauseForSearchAndPopulateParametersVincolo(queryParams, parameters, configUtente));
		
		logger.info("backOfficeVincoloSearch - sql: " + sql.toString());
		
		return super.paginatedList(sql.toString(), parameters, new PlainSoggettoIstanzaVincoloMapper(), 
				Integer.parseInt(queryParams.get("page")), Integer.parseInt(queryParams.get("limit")));
	}
	
	private StringBuilder getSqlBackOfficeVincoloSearch(Map<String, String> queryParams) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT distinct ifor.id_istanza_intervento,ifor.nr_istanza_forestale,ifor.data_invio,si.descr_stato_istanza, ");
		sql.append("sog.id_soggetto,sog.codice_fiscale,sog.partita_iva,sog.nome,sog.cognome,sog.denominazione, ");
		sql.append("tab_string_comune.string_comune as denominazione_comune, ifor.fk_stato_istanza, ifor.fk_tipo_istanza, ");
		
		sql.append("CASE ");
		sql.append("    WHEN ifor.fk_tipo_istanza = 4 then 'Regionale' ");
		sql.append("   	WHEN ifor.fk_tipo_istanza = 5 then 'Comunale' ");
		sql.append("END competenza, ");
		
		sql.append("CASE ");
		sql.append("WHEN invi.flg_cauzione_vi IS NULL THEN 'Ricevuta Cauzione INDETERMINATO; ' ");
		sql.append("WHEN invi.flg_cauzione_vi IN (2,3,4,5) THEN 'Ricevuta Cauzione ESENTE; '  ");
		sql.append("WHEN invi.flg_cauzione_vi = 1 AND doc106.id_documento_allegato IS NULL THEN 'Ricevuta Cauzione MANCANTE; '  ");
		sql.append("WHEN invi.flg_cauzione_vi = 1 AND doc106.id_documento_allegato IS NOT NULL THEN 'Ricevuta Cauzione PRESENTE; '  ");
		sql.append("END stato_cauzione, ");
		
		sql.append("CASE ");
		sql.append("WHEN invi.flg_compensazione IS NULL THEN '' ");
		sql.append("WHEN invi.flg_compensazione = 'N' THEN 'Ricevuta compensazione ESENTE;' "); 
		sql.append("WHEN invi.flg_compensazione = 'F' AND doc152.id_documento_allegato IS NULL THEN 'Ricevuta dep. Comp. Fisica MANCANTE;' "); 
		sql.append("WHEN invi.flg_compensazione = 'F' AND doc152.id_documento_allegato IS NOT NULL THEN 'Ricevuta dep. Comp. Fisica PRESENTE;' "); 
		sql.append("WHEN invi.flg_compensazione = 'M' AND doc153.id_documento_allegato IS NULL THEN 'Ricevuta dep. Comp. Monetaria MANCANTE;' "); 
		sql.append("WHEN invi.flg_compensazione = 'M' AND doc153.id_documento_allegato IS NOT NULL THEN 'Ricevuta dep. Comp. Monetaria PRESENTE;' "); 
		sql.append("END stato_compensazione, ");
		
		sql.append("CASE ");
		sql.append("WHEN invi.fk_intervento_padre_variante IS NOT NULL THEN 'VARIANTE' ");
		sql.append("WHEN invi.fk_intervento_padre_proroga IS NOT NULL THEN 'PROROGA' ");
		sql.append("WHEN ");
		sql.append("( ");
		sql.append("invi.id_intervento IN (SELECT fk_intervento_padre_variante FROM idf_t_interv_vinc_idro WHERE id_intervento != invi.id_intervento ) ");
		sql.append("OR ");
		sql.append("invi.id_intervento IN (SELECT fk_intervento_padre_proroga FROM idf_t_interv_vinc_idro WHERE id_intervento != invi.id_intervento ) ");
		sql.append(") ");
		sql.append("THEN 'SI' ");
		sql.append("WHEN invi.fk_intervento_padre_variante IS NULL AND invi.fk_intervento_padre_proroga IS NULL THEN 'NO' ");
		sql.append("END variante_proroga, ");
		
		sql.append("CASE ");
		sql.append("WHEN cat.id_intervento IS NULL THEN 'Non ricade in bosco' ");
		sql.append("WHEN invi.flg_compensazione = 'F' and taglio.fk_intervento is null THEN 'Compensazione fisica - senza istanze di taglio' ");
		sql.append("WHEN invi.flg_compensazione = 'F' and taglio.fk_intervento is not null THEN 'Compensazione fisica - con istanze di taglio' ");
		sql.append("WHEN invi.flg_compensazione = 'M' THEN 'Compensazione monetaria' ");
		sql.append("WHEN invi.flg_compensazione = 'N' THEN 'Esente da compensazione' ");
		sql.append("END rimboschimento ");
		
		sql.append("from ");
		sql.append("IDF_T_ISTANZA_FOREST ifor ");
		sql.append("inner join IDF_T_INTERVENTO inter on ifor.id_istanza_intervento = inter.id_intervento ");
		sql.append("inner join idf_d_stato_istanza si ON ifor.fk_stato_istanza = si.id_stato_istanza ");
		sql.append("inner join IDF_T_INTERV_VINC_IDRO invi on invi.id_intervento = inter.id_intervento ");		
		sql.append("inner join IDF_R_PROPCATASTO_INTERVENTO pc on pc.id_intervento  = inter.id_intervento ");
		sql.append("inner join IDF_GEO_PL_PROP_CATASTO pl on pl.idgeo_pl_prop_catasto = pc.idgeo_pl_prop_catasto ");		
		sql.append("inner join IDF_R_SOGGETTO_INTERVENTO soginv on soginv.id_intervento = inter.id_intervento ");
		sql.append("inner join IDF_T_SOGGETTO sog on sog.id_soggetto = soginv.id_soggetto ");
		sql.append("left join idf_r_intervento_catfor cat ON inter.id_intervento = cat.id_intervento ");
		sql.append("left join ( select fk_intervento from idf_temp_istanza_compensazione) as taglio ON taglio.fk_intervento = cat.id_intervento ");
		//sql.append("left join idf_temp_istanza_compensazione temp on temp.fk_intervento = inter.id_intervento ");
		if("true".equals(queryParams.get("aapp"))) {
			sql.append("inner join IDF_R_INTERVENTO_AAPP aapp on aapp.id_intervento = inter.id_intervento ");
		}
		if("true".equals(queryParams.get("rn2k"))) {
			sql.append("inner join IDF_R_INTERVENTO_RN_2000 rn on rn.id_intervento = inter.id_intervento ");
		}
		if("true".equals(queryParams.get("popSeme"))) {
			sql.append("inner join IDF_R_INTERVENTO_POP_SEME sem on sem.id_intervento = inter.id_intervento ");
		}
		
		sql.append("left join idf_t_documento_allegato doc106 on inter.id_intervento = doc106.fk_intervento and doc106.fk_tipo_allegato=106 ");
		sql.append("LEFT JOIN idf_t_documento_allegato doc152 on inter.id_intervento = doc152.fk_intervento and doc152.fk_tipo_allegato=152 ");
		sql.append("LEFT JOIN idf_t_documento_allegato doc153 on inter.id_intervento = doc153.fk_intervento and doc153.fk_tipo_allegato=153 ");
		
		sql.append("LEFT JOIN ( ");
		sql.append("      SELECT zzz.id_intervento, ");
		sql.append("          array_to_string(z_cat(zzz.denominazione_comune), '; '::text) AS string_comune ");
		sql.append("      FROM ( ");
		sql.append("      SELECT DISTINCT ");
		sql.append("      r_catasto_intervento.id_intervento, comune.denominazione_comune ");
		sql.append("      FROM ");
		sql.append("      idf_r_propcatasto_intervento as r_catasto_intervento ");
		sql.append("      JOIN idf_geo_pl_prop_catasto as catasto ON r_catasto_intervento.idgeo_pl_prop_catasto=catasto.idgeo_pl_prop_catasto ");
		sql.append("      JOIN idf_geo_pl_comune as comune ON catasto.fk_comune=comune.id_comune ");
		sql.append("      ) as zzz ");
		sql.append("      GROUP BY zzz.id_intervento ");
		sql.append("      ) as tab_string_comune ON inter.id_intervento=tab_string_comune.id_intervento ");
		return sql;
	}
	
	private String createWhereClauseForSearchAndPopulateParametersVincolo(Map<String, String> queryParams,
			List<Object> parameters, ConfigUtente configUtente) throws ParseException {
		StringBuilder sql = new StringBuilder();

		if(queryParams.get("padreVariante") != null) {
			sql.append("where invi.fk_intervento_padre_variante = ? ");
			parameters.add(Integer.parseInt(queryParams.get("padreVariante")));
		}else if(queryParams.get("padreProroga") != null) {
			sql.append("where invi.fk_intervento_padre_proroga = ? ");
			parameters.add(Integer.parseInt(queryParams.get("padreProroga")));
		}else {
			sql.append("where ifor.FK_TIPO_ISTANZA = ? ");
			parameters.add(Integer.parseInt(queryParams.get("tipoIstanza")));
		
			if(queryParams.get("numIstanza") != null) {
				sql.append("AND ifor.NR_ISTANZA_FORESTALE = ? ");
				parameters.add(Integer.parseInt(queryParams.get("numIstanza")));
			}
			if(queryParams.get("statoIstanza") != null) {
				sql.append("AND ifor.FK_STATO_ISTANZA = ? ");
				parameters.add(Integer.parseInt(queryParams.get("statoIstanza")));
			}
			if(queryParams.get("annoIstanza") != null) {
				sql.append("AND TO_CHAR(ifor.data_inserimento, 'YYYY') = ? ");
				parameters.add(queryParams.get("annoIstanza"));
			}
			if(queryParams.get("dataPresDa") != null) {
				sql.append("AND ifor.DATA_INVIO >= ? ");
				parameters.add(java.sql.Date.valueOf(queryParams.get("dataPresDa")));
			}
			if(queryParams.get("dataPresA") != null) {
				sql.append("AND ifor.DATA_INVIO <= ? ");
				parameters.add(java.sql.Date.valueOf(queryParams.get("dataPresA")));
			}
			if(queryParams.get("annoAutorizzazione") != null) {
				sql.append("AND TO_CHAR(ifor.data_verifica, 'YYYY') = ? ");
				parameters.add(queryParams.get("annoAutorizzazione"));
			}
			if(queryParams.get("numAutorizzazione") != null) {
				sql.append("AND ifor.NR_DETERMINA_AUT like ? ");
				parameters.add("%" + queryParams.get("numAutorizzazione") + "%");
			}
			if(queryParams.get("dataScadenzaProvDa") != null) {
				sql.append("AND ifor.DATA_TERMINE_AUT >= ? ");
				parameters.add(java.sql.Date.valueOf(queryParams.get("dataScadenzaProvDa")));
			}
			if(queryParams.get("dataScadenzaProvA") != null) {
				sql.append("AND ifor.DATA_TERMINE_AUT <= ? ");
				parameters.add(java.sql.Date.valueOf(queryParams.get("dataScadenzaProvA")));
			}
			if(queryParams.get("dataConclusioneIntervDa") != null) {
				sql.append("AND inter.data_fine_intervento >= ? "); 
				parameters.add(java.sql.Date.valueOf(queryParams.get("dataConclusioneIntervDa")));
			}
			if(queryParams.get("dataConclusioneIntervA") != null) {
				sql.append("AND inter.data_fine_intervento <= ? "); 
				parameters.add(java.sql.Date.valueOf(queryParams.get("dataConclusioneIntervA")));
			}
			if(configUtente.getFkProfiloUtente() == ProfiloUtenteEnum.UFFICIO_TERRITORIALE.getValue()) {
				sql.append("AND pl.FK_COMUNE IN ( select id_comune from idf_geo_pl_comune igpc ");
				sql.append( "where istat_prov in (select pg.istat_prov_competenza_terr ");
				sql.append("from IDF_R_PF_PG pfpg inner join idf_t_soggetto pg on pfpg.id_soggetto_pg = pg.id_soggetto "); 
				sql.append("where pfpg.id_soggetto_pf = ?))");
				parameters.add(configUtente.getFkSoggetto());
			}else if(queryParams.get("prov") != null && queryParams.get("comune") == null) {
				sql.append("AND pl.FK_COMUNE IN ( select id_comune from idf_geo_pl_comune igpc where istat_prov = ?) ");
				parameters.add(queryParams.get("prov"));
			}
			if(configUtente.getFkProfiloUtente() == ProfiloUtenteEnum.COMUNE.getValue()) {
				sql.append("AND pl.FK_COMUNE IN ( select id_comune from idf_geo_pl_comune igpc ");
				sql.append( "where istat_comune in (select pg.istat_comune_competenza_terr ");
				sql.append("from IDF_R_PF_PG pfpg inner join idf_t_soggetto pg on pfpg.id_soggetto_pg = pg.id_soggetto "); 
				sql.append("where pfpg.id_soggetto_pf = ?))");
				parameters.add(configUtente.getFkSoggetto());
			}else if(queryParams.get("comune") != null) {
				sql.append("AND pl.FK_COMUNE = ? ");
				parameters.add(Integer.parseInt(queryParams.get("comune")));
			}
			if(queryParams.get("sezione") != null) {
				sql.append("AND pl.sezione = ? ");
				parameters.add(queryParams.get("sezione"));
			}
			if(queryParams.get("foglio") != null) {
				sql.append("AND pl.foglio = ? ");
				parameters.add(Integer.parseInt(queryParams.get("foglio")));
			}
			if(queryParams.get("particella") != null) {
				sql.append("AND RTRIM(pl.particella) = ? ");
				parameters.add(queryParams.get("particella"));
			}
			if(queryParams.get("pf") != null) {
				sql.append("AND sog.ID_SOGGETTO= ? ");
				parameters.add(Integer.parseInt(queryParams.get("pf")));
			}
			if(queryParams.get("pg") != null) {
				sql.append("AND sog.ID_SOGGETTO= ? ");
				parameters.add(Integer.parseInt(queryParams.get("pg")));
			}
			if("cv".equals(queryParams.get("varianti"))) {
				sql.append("AND inter.id_intervento IN (SELECT FK_INTERVENTO_PADRE_VARIANTE FROM ");
				sql.append("IDF_T_INTERV_VINC_IDRO WHERE FK_INTERVENTO_PADRE_VARIANTE IS NOT NULL) ");
			}
			if("sv".equals(queryParams.get("varianti"))) {
				sql.append("AND inter.id_intervento IN (SELECT ID_INTERVENTO FROM IDF_T_INTERV_VINC_IDRO WHERE ");
				sql.append("FK_INTERVENTO_PADRE_VARIANTE IS NOT NULL) ");
			}
			if("cp".equals(queryParams.get("proroghe"))) {
				sql.append("AND inter.id_intervento IN (SELECT FK_INTERVENTO_PADRE_PROROGA FROM  ");
				sql.append("IDF_T_INTERV_VINC_IDRO WHERE FK_INTERVENTO_PADRE_PROROGA IS NOT NULL) ");
			}
			if("sp".equals(queryParams.get("proroghe"))) {
				sql.append("AND inter.id_intervento IN (SELECT ID_INTERVENTO FROM IDF_T_INTERV_VINC_IDRO WHERE  ");
				sql.append("FK_INTERVENTO_PADRE_PROROGA IS NOT NULL) ");
			}
			if(queryParams.get("compensazioneVincolo") != null) {
				sql.append("AND invi.FLG_COMPENSAZIONE = ? ");
				parameters.add(queryParams.get("compensazioneVincolo"));
			}
			if("S".equals(queryParams.get("cauzione"))) {
				sql.append("AND invi.FLG_CAUZIONE_VI = 1 ");
			}
			if("N".equals(queryParams.get("cauzione"))) {
				sql.append("AND invi.FLG_CAUZIONE_VI in (2,3,4,5) ");
			}
			sql.append(" and si.id_stato_istanza > 1");
		}
		
		return sql.append(getQuerySortingSegment(queryParams.get("sort"))).toString();
	}

	private StringBuilder getSqlBackOfficeTagliSearch(Map<String, String> queryParams) {
		StringBuilder sql = new StringBuilder();

		sql.append(" select distinct ");
		sql.append(" 	intervento.id_intervento, ");
		sql.append(" 	istanza.nr_istanza_forestale, ");
		sql.append(" 	concat (date_part('year'::text, intervento.data_inserimento),'/',istanza.nr_istanza_forestale) as nr_istanza, ");
		sql.append(" 	idti.descr_dett_tipoistanza, ");
		sql.append(" 	concat(soggetto.codice_fiscale,' - ', soggetto.cognome,' ', soggetto.nome, soggetto.denominazione) as richiedente, ");
		sql.append(" 	tab_string_comune.string_comune, ");
		sql.append(" 	concat(idcis.descr_categ_intervento, ' - ',idti2.descr_intervento, ' ', ");
		sql.append(" 	case when idti3.id_tipo_intervento = 0 then '' else  concat ('+ ',idti3.descr_intervento) end ) as tipo_intervento, ");
		sql.append(" 	intervento.descrizione_intervento, ");
		sql.append(" 	intervento.data_inserimento, ");
//		sql.append(" 	CASE ");
//		sql.append(" 	    WHEN istanza.fk_stato_istanza = 3::numeric AND istanza.fk_tipo_istanza = 2::numeric THEN 'Verificata e accolta'::text::character varying ");
//		sql.append(" 	    WHEN istanza.fk_stato_istanza = 3::numeric AND istanza.fk_tipo_istanza = 3::numeric THEN 'Autorizzata'::text::character varying ");
//		sql.append(" 	    WHEN istanza.fk_stato_istanza = 4::numeric AND istanza.fk_tipo_istanza = 2::numeric THEN ' Verificata e negata'::text::character varying ");
//		sql.append(" 	    WHEN istanza.fk_stato_istanza = 4::numeric AND istanza.fk_tipo_istanza = 3::numeric THEN 'Non autorizzata'::text::character varying ");
//		sql.append(" 	    ELSE statoistanza.descr_stato_istanza ");
//		sql.append(" 	END AS stato_istanza, istanza.fk_stato_istanza, istanza.fk_tipo_istanza ");
		sql.append(" 	statoistanza.descr_stato_istanza AS stato_istanza, istanza.fk_stato_istanza, istanza.fk_tipo_istanza ");
		sql.append(" FROM idf_t_intervento intervento ");
		sql.append("      JOIN idf_t_interv_selvicolturale itis ON intervento.id_intervento = itis.id_intervento ");
		sql.append("      JOIN idf_t_istanza_forest istanza ON intervento.id_intervento = istanza.id_istanza_intervento ");
		sql.append("      JOIN idf_d_stato_istanza statoistanza ON istanza.fk_stato_istanza = statoistanza.id_stato_istanza ");
		//sql.append("      --richiedente ");
		sql.append("      LEFT JOIN (select rsi.id_soggetto, rsi.id_intervento, rsi.fk_tipo_richiedente from idf_r_soggetto_intervento rsi where rsi.id_tipo_soggetto = 1)  r_soggetto_intervento ON itis.id_intervento = r_soggetto_intervento.id_intervento ");
		sql.append("      LEFT JOIN idf_t_soggetto soggetto ON soggetto.id_soggetto = r_soggetto_intervento.id_soggetto ");
		//sql.append("      --richiedente pf per criterio ricerca ");
		sql.append("      LEFT JOIN (select its1.id_soggetto,its1.codice_fiscale, its1.cognome, its1.nome  ");
		sql.append(" 			from idf_t_soggetto its1 ");
		sql.append(" 			where its1.cognome is not null and its1.nome is not null) richiedente_pf on  r_soggetto_intervento.id_soggetto = richiedente_pf.id_soggetto ");
		//sql.append(" 	 --richiedente pg per criterio ricerca ");
		sql.append(" 	 LEFT JOIN (select its2.id_soggetto,its2.codice_fiscale, its2.denominazione ");
		sql.append(" 			from idf_t_soggetto its2 ");
		sql.append(" 			where its2.denominazione is not null) richiedente_pg on  r_soggetto_intervento.id_soggetto = richiedente_pg.id_soggetto ");
		//sql.append(" 	 --utilizzatore ");
		sql.append("      LEFT JOIN (select rsi2.id_soggetto, rsi2.id_intervento, rsi2.fk_tipo_richiedente from idf_r_soggetto_intervento rsi2 where rsi2.id_tipo_soggetto = 2)  r_utilizzatore_intervento ON itis.id_intervento = r_utilizzatore_intervento.id_intervento ");
//		sql.append("      --utilizzatore pf per criterio ricerca ");
		sql.append("      LEFT JOIN (select its3.id_soggetto,its3.codice_fiscale, its3.cognome, its3.nome  ");
		sql.append(" 			from idf_t_soggetto its3 ");
		sql.append(" 			where its3.cognome is not null and its3.nome is not null) utilizzatore_pf on  r_utilizzatore_intervento.id_soggetto = utilizzatore_pf.id_soggetto ");
//		sql.append(" 	 --utilizzatore pg per criterio ricerca ");
		sql.append(" 	 LEFT JOIN (select its4.id_soggetto,its4.codice_fiscale, its4.denominazione ");
		sql.append(" 			from idf_t_soggetto its4 ");
		sql.append(" 			where its4.denominazione is not null) utilizzatore_pg on  r_utilizzatore_intervento.id_soggetto = utilizzatore_pg.id_soggetto ");
//		sql.append(" 	 -- professionista ");
		sql.append(" 	 LEFT join idf_t_soggetto tecnico_forestale on intervento.fk_soggetto_profess = tecnico_forestale.id_soggetto  ");
		sql.append(" 	 LEFT JOIN idf_d_tipo_istanza idti ON istanza.fk_tipo_istanza = idti.id_tipo_istanza ");
		sql.append("     LEFT JOIN idf_d_proprieta idp ON itis.fk_proprieta = idp.id_proprieta ");
		sql.append("     LEFT JOIN idf_d_governo idg ON itis.fk_governo = idg.id_governo ");
		sql.append("     LEFT join idf_d_governo idg2 on itis.fk_governo2 = idg2.id_governo  ");
		sql.append("     LEFT JOIN idf_d_categ_interv_selvicolturale idcis ON itis.fk_categ_intervento = idcis.id_categ_intervento ");
		sql.append("     LEFT JOIN idf_d_tipo_intervento idti2 ON itis.fk_tipo_intervento = idti2.id_tipo_intervento ");
		sql.append("     LEFT join idf_d_tipo_intervento idti3 on itis.fk_tipo_intervento2 = idti3.id_tipo_intervento ");
		sql.append("     LEFT JOIN idf_d_proprieta idp2 on itis.fk_proprieta = idp2.id_proprieta  ");
		sql.append("     LEFT JOIN idf_d_stato_intervento idsi on itis.fk_stato_intervento = idsi.id_stato_intervento  ");
		sql.append("     LEFT JOIN idf_r_propcatasto_intervento irpi on intervento.id_intervento = irpi.id_intervento  ");
		sql.append("     LEFT JOIN idf_geo_pl_prop_catasto igppc on irpi.idgeo_pl_prop_catasto = igppc.idgeo_pl_prop_catasto ");
		sql.append("     LEFT JOIN idf_geo_pl_comune igpc on igppc.fk_comune = igpc.id_comune  ");
		sql.append("     LEFT JOIN idf_d_tipo_richiedente idtr on r_soggetto_intervento.fk_tipo_richiedente = idtr.id_tipo_richiedente  ");
		sql.append("     LEFT JOIN idf_r_intervento_catfor iric on intervento.id_intervento = iric.id_intervento  ");
		sql.append("     LEFT JOIN idf_d_categoria_forestale idcf on iric.id_categoria_forestale = idcf.id_categoria_forestale ");
//		sql.append("      --- appiattimento comuni ");
		sql.append("     LEFT JOIN ( SELECT zzz.id_intervento, ");
		sql.append("             array_to_string(z_cat(zzz.denominazione_comune), '; '::text) AS string_comune ");
		sql.append("            FROM ( SELECT DISTINCT r_catasto_intervento.id_intervento, ");
		sql.append("                     comune.denominazione_comune ");
		sql.append("                    FROM idf_r_propcatasto_intervento r_catasto_intervento ");
		sql.append("                      JOIN idf_geo_pl_prop_catasto catasto ON r_catasto_intervento.idgeo_pl_prop_catasto = catasto.idgeo_pl_prop_catasto ");
		sql.append("                      JOIN idf_geo_pl_comune comune ON catasto.fk_comune = comune.id_comune) zzz ");
		sql.append("           GROUP BY zzz.id_intervento) tab_string_comune ON intervento.id_intervento = tab_string_comune.id_intervento ");
		return sql;
	}

	private String getWhereClauseForSearchAndPopulateParametersTagli(Map<String, String> queryParams, List<Object> parameters, ConfigUtente configUtente) {
		StringBuilder sql = new StringBuilder();


		if(queryParams.get("padreVariante") != null) {
			sql.append("where itis.fk_intervento_padre_variante = ? ");
			parameters.add(Integer.parseInt(queryParams.get("padreVariante")));
		} else if(queryParams.get("padreProroga") != null) {
			sql.append("where itis.fk_intervento_padre_proroga = ? ");
			parameters.add(Integer.parseInt(queryParams.get("padreProroga")));
		} else {

			sql.append(" WHERE itis.idgeo_pl_pfa = 0 ");

			if(queryParams.get("tipoIstanza") != null) {
				if (queryParams.get("tipoIstanza").equalsIgnoreCase("2")) {
					sql.append("AND istanza.FK_TIPO_ISTANZA in (2,3) ");
				} else {
					sql.append("AND istanza.FK_TIPO_ISTANZA = ? ");
					parameters.add(Integer.parseInt(queryParams.get("tipoIstanza")));
				}
			}

			if(queryParams.get("numIstanza") != null) {
				sql.append("AND istanza.NR_ISTANZA_FORESTALE = ? ");
				parameters.add(Integer.parseInt(queryParams.get("numIstanza")));
			}

			if(queryParams.get("statoIstanza") != null) {
				sql.append("AND istanza.fk_stato_istanza = ? ");
				parameters.add(Integer.parseInt(queryParams.get("statoIstanza")));
			} else {
				if (configUtente.getFkTipoAccreditamento() != TipoAccreditamentoEnum.SPORTELLO.getValue()) {
					sql.append("AND istanza.fk_stato_istanza  > 1 ");
				}
			}

			if(queryParams.get("annoIstanza") != null) {
				sql.append("AND TO_CHAR(istanza.data_inserimento, 'YYYY') = ? ");
				parameters.add(queryParams.get("annoIstanza"));
			}
			if(queryParams.get("dataPresDa") != null) {
				sql.append("AND istanza.DATA_INVIO >= ? ");
				parameters.add(java.sql.Date.valueOf(queryParams.get("dataPresDa")));
			}
			if(queryParams.get("dataPresA") != null) {
				sql.append("AND istanza.DATA_INVIO <= ? ");
				parameters.add(java.sql.Date.valueOf(queryParams.get("dataPresA")));
			}

			if(queryParams.get("tipoIstTaglio") != null) {
				sql.append("AND istanza.FK_TIPO_ISTANZA = ? ");
				parameters.add(Integer.parseInt(queryParams.get("tipoIstTaglio")));
			} else {
				sql.append("AND istanza.FK_TIPO_ISTANZA in (2,3) ");
			}

			if(queryParams.get("descr") != null) {
				String like = "%" + queryParams.get("descr") + "%";
				sql.append("AND UPPER(intervento.descrizione_intervento) like UPPER(?) ");
				parameters.add(like);
			}
			if(queryParams.get("annoAutorizzazione") != null) {
				sql.append("AND TO_CHAR(istanza.data_verifica, 'YYYY') = ? ");
				parameters.add(queryParams.get("annoAutorizzazione"));
			}
			if(queryParams.get("numAutorizzazione") != null) {
				sql.append("AND istanza.NR_DETERMINA_AUT like ? ");
				parameters.add("%" + queryParams.get("numAutorizzazione") + "%");
			}
			if(queryParams.get("dataScadenzaProvDa") != null) {
				sql.append("AND istanza.DATA_TERMINE_AUT >= ? ");
				parameters.add(java.sql.Date.valueOf(queryParams.get("dataScadenzaProvDa")));
			}
			if(queryParams.get("dataScadenzaProvA") != null) {
				sql.append("AND istanza.DATA_TERMINE_AUT <= ? ");
				parameters.add(java.sql.Date.valueOf(queryParams.get("dataScadenzaProvA")));
			}
			if(queryParams.get("dataConclusioneIntervDa") != null) {
				sql.append("AND intervento.data_fine_intervento >= ? ");
				parameters.add(java.sql.Date.valueOf(queryParams.get("dataConclusioneIntervDa")));
			}
			if(queryParams.get("dataConclusioneIntervA") != null) {
				sql.append("AND intervento.data_fine_intervento <= ? ");
				parameters.add(java.sql.Date.valueOf(queryParams.get("dataConclusioneIntervA")));
			}

			if(queryParams.get("catSelv") != null) {
				sql.append("AND  idcis.id_categ_intervento = ? ");
				parameters.add(Integer.parseInt(queryParams.get("catSelv")));
			}

			if(queryParams.get("prop") != null) {
				sql.append("AND idp2.id_proprieta = ? ");
				parameters.add(Integer.parseInt(queryParams.get("prop")));
			}

			if(queryParams.get("govForm") != null) {
				sql.append("AND (idg.id_governo = ? or idg2.id_governo = ?) ");
				parameters.add(Integer.parseInt(queryParams.get("govForm")));
				parameters.add(Integer.parseInt(queryParams.get("govForm")));
			}

			if(queryParams.get("tipoInt") != null) {
				sql.append("AND (idti2.id_tipo_intervento = ? or idti3.id_tipo_intervento = ?) ");
				parameters.add(Integer.parseInt(queryParams.get("tipoInt")));
				parameters.add(Integer.parseInt(queryParams.get("tipoInt")));
			}

			if(queryParams.get("statoInt") != null) {
				sql.append("AND idsi.id_stato_intervento = ? ");
				parameters.add(Integer.parseInt(queryParams.get("statoInt")));
			}

			if(queryParams.get("prov") != null) {
				sql.append("AND igpc.istat_prov = ? ");
				parameters.add(queryParams.get("prov"));
			}

			if(queryParams.get("comune") != null) {
				sql.append("AND igpc.id_comune = ? ");
				parameters.add(Integer.parseInt(queryParams.get("comune")));
			}

			if(queryParams.get("sezione") != null) {
				if (queryParams.get("sezione").equalsIgnoreCase("")) {
					sql.append("AND (igppc.sezione = ? or igppc.sezione = '_' ) ");
					parameters.add(queryParams.get("sezione"));
				} else {
					sql.append("AND igppc.sezione = ? ");
					parameters.add(queryParams.get("sezione"));
				}
			}
			if(queryParams.get("foglio") != null) {
				sql.append("AND igppc.foglio = ? ");
				parameters.add(Integer.parseInt(queryParams.get("foglio")));
			}

			if(queryParams.get("particella") != null) {
				sql.append("AND RTRIM(igppc.particella) = ? ");
				parameters.add(queryParams.get("particella"));
			}

			if(queryParams.get("tipoRich") != null) {
				sql.append("AND idtr.id_tipo_richiedente = ? ");
				parameters.add(Integer.parseInt(queryParams.get("tipoRich")));
			}

			if(queryParams.get("pf") != null) {
//			 --richiedente PF
				sql.append("and richiedente_pf.id_soggetto = ? ");
				parameters.add(Integer.parseInt(queryParams.get("pf")));

			}
			if(queryParams.get("pg") != null) {
//			 --richiedente PG
				sql.append("and richiedente_pg.id_soggetto = ? ");
				parameters.add(Integer.parseInt(queryParams.get("pg")));

			}
			if(queryParams.get("upf") != null) {
//			 --utilizzatore PF
				sql.append("and utilizzatore_pf.id_soggetto = ? ");
				parameters.add(Integer.parseInt(queryParams.get("upf")));
			}

			if(queryParams.get("upg") != null) {
//			 --utilizzatore PG
				sql.append("and utilizzatore_pg.id_soggetto = ? ");
				parameters.add(Integer.parseInt(queryParams.get("upg")));
			}

			if(queryParams.get("und") != null) {
//			 -- da individuare
				sql.append("and intervento.id_intervento not in (select rsi3.id_intervento from idf_r_soggetto_intervento rsi3 where rsi3.id_tipo_soggetto = 2) ");
			}

			if(queryParams.get("prof") != null) {
//			 --tecnico forestale
				sql.append("and tecnico_forestale.id_soggetto = ? ");
				parameters.add(Integer.parseInt(queryParams.get("prof")));
			}

			if(queryParams.get("aapp") != null) {
				sql.append("and intervento.id_intervento in (select iria.id_intervento from idf_r_intervento_aapp iria) ");
			}

			if(queryParams.get("rn2k") != null) {
				sql.append("and intervento.id_intervento in (select irir.id_intervento from idf_r_intervento_rn_2000 irir) ");
			}

			if(queryParams.get("popSeme") != null) {
				sql.append("and intervento.id_intervento in (select irips.id_intervento from idf_r_intervento_pop_seme irips) ");
			}

			if(queryParams.get("pfa") != null) {
				sql.append("and itis.idgeo_pl_pfa <> 0 ");
			}

			if(queryParams.get("catFor") != null) {
				sql.append("and idcf.id_categoria_forestale = ? ");
				parameters.add(Integer.parseInt(queryParams.get("catFor")));
			}

			if(queryParams.get("varianti") != null) {
				if(queryParams.get("varianti").equalsIgnoreCase("cv")) {
//				-- Autorizzazione con variante
					sql.append("and intervento.id_intervento in (select itis2.fk_intervento_padre_variante  from idf_t_interv_selvicolturale itis2 where itis2.fk_intervento_padre_variante is not null) ");
				}
				if(queryParams.get("varianti").equalsIgnoreCase("sv")) {
//				-- variante
					sql.append("and intervento.id_intervento in (select itis2.id_intervento  from idf_t_interv_selvicolturale itis2 where itis2.fk_intervento_padre_variante is not null) ");
				}
			}

			if(queryParams.get("proroghe") != null) {
				if(queryParams.get("proroghe").equalsIgnoreCase("cp")) {
//				-- Autorizzazione con proroga
					sql.append("and intervento.id_intervento in (select itis2.fk_intervento_padre_proroga  from idf_t_interv_selvicolturale itis2 where itis2.fk_intervento_padre_proroga  is not null) ");
				}
				if(queryParams.get("proroghe").equalsIgnoreCase("sp")) {
//				-- proroga
					sql.append("and intervento.id_intervento in (select itis2.id_intervento  from idf_t_interv_selvicolturale itis2 where itis2.fk_intervento_padre_proroga  is not null) ");
				}
			}
			if(queryParams.get("intComp") != null) {
				//-- intervento compensativo
				sql.append("and intervento.id_intervento in (select irist.id_intervento_selv  from idf_r_int_selv_trasf irist) ");
			}

			if (queryParams.get("sport") != null) {

				sql.append(" 	and intervento.fk_tipo_accreditamento = 3 ");
				sql.append(" 	AND intervento.fk_soggetto_sportello = ? ");
/*
				sql.append("and intervento.fk_config_utente_compilatore in ( ");
				sql.append("	select iccu.id_config_utente  ");
				sql.append("	from idf_t_soggetto its  ");
				sql.append("	left join idf_r_pf_pg irpp on its.id_soggetto = irpp.id_soggetto_pg  ");
				sql.append("	left join idf_t_soggetto its2 on irpp.id_soggetto_pf = its2.id_soggetto ");
				sql.append("	left join idf_cnf_config_utente iccu on its2.id_soggetto = iccu.fk_soggetto ");
				sql.append("	where its.flg_sportello = 1 and iccu.fk_profilo_utente  = 7 and its.id_soggetto = ? )");
*/
				parameters.add(Integer.parseInt(queryParams.get("sport"))); // id soggetto
			}
		}

		return sql.append(getQuerySortingSegment(queryParams.get("sort"))).toString();
	}

	@Override
	public int updateToAccoltaInSilenzio() {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE idf_t_istanza_forest SET fk_stato_istanza = 8 " );
		sql.append("where fk_tipo_istanza = 1 and fk_stato_istanza = 2 and data_invio <= (NOW() - INTERVAL '30 DAY')");
		return 0;
	}

	@Override
	public Integer findNrIstanzaForestByIdIntervento(Integer idIntervento){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT nr_istanza_forestale FROM idf_t_istanza_forest WHERE id_istanza_intervento = ? " );
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), Integer.class, idIntervento);
	}

}
