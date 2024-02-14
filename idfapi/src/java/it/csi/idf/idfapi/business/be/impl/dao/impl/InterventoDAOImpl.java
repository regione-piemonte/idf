/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.exceptions.DuplicateRecordException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.impl.dao.InterventoDAO;
import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.dto.DrawedGeometryInfo;
import it.csi.idf.idfapi.dto.Intervento;
import it.csi.idf.idfapi.dto.InterventoPiano;
import it.csi.idf.idfapi.dto.InterventoRiepilogo;
import it.csi.idf.idfapi.dto.TSoggetto;
import it.csi.idf.idfapi.dto.TipoInterventoDatiTecnici;
import it.csi.idf.idfapi.mapper.DrawedGeometryInfoMapper;
import it.csi.idf.idfapi.mapper.InterventoMapper;
import it.csi.idf.idfapi.mapper.InterventoPianoMapper;
import it.csi.idf.idfapi.mapper.TSoggettoMapper;
import it.csi.idf.idfapi.util.AIKeyHolderUtil;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.GenericDAO;
import it.csi.idf.idfapi.util.PaginatedList;

@Component
public class InterventoDAOImpl extends GenericDAO implements InterventoDAO {

	static final Logger logger = Logger.getLogger(InterventoDAOImpl.class);

	private static final String ASCENDING = "ASC";
	private static final String DESCENDING = "DESC";
	private static final char ORDER = '-';

	private final InterventoPianoMapper interventoPianoMapper = new InterventoPianoMapper();
	private final InterventoMapper interventoMapper = new InterventoMapper();

	@Override
	public List<Intervento> findAllInterventi() {

		String sql = "SELECT * from idf_t_intervento";
		return DBUtil.jdbcTemplate.query(sql, interventoMapper);
	}

	@Override
	public InterventoPiano findInterventoPianoByID(Integer idIntervento) {

		StringBuilder sql = getGeoPlPfaMainQuery();
		sql.append(" WHERE interv.id_intervento = ?");
		geoPlPfaQueryGroupBy(sql);

		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), interventoPianoMapper, idIntervento);
	}




	@Override
	public int createInterventoWithConfigUtente(int fkConfigUtente) {
		StringBuilder sql = new StringBuilder("INSERT INTO idf_t_intervento(");
		sql.append("data_inserimento, fk_config_utente_compilatore");
		sql.append(") VALUES(?, ?)");

		List<Object> params = new ArrayList<>();
		params.add(Date.valueOf(LocalDate.now()));
		params.add(fkConfigUtente);

		return AIKeyHolderUtil.updateWithGenKey("id_intervento", sql.toString(), params);
	}

	@Override
	public int createInterventoTagliWithConfigUtente(ConfigUtente configUtente) {
		StringBuilder sql = new StringBuilder("INSERT INTO idf_t_intervento(");
		sql.append("data_inserimento, fk_config_utente_compilatore, fk_tipo_accreditamento, fk_soggetto_sportello");
		sql.append(") VALUES(?, ?, ?, ?)");

		List<Object> params = new ArrayList<>();
		params.add(Date.valueOf(LocalDate.now()));
		params.add(configUtente.getIdConfigUtente());
		params.add(configUtente.getFkTipoAccreditamento());
		params.add(configUtente.getFkSoggettoSportello() == 0 ? null : configUtente.getFkSoggettoSportello());

		return AIKeyHolderUtil.updateWithGenKey("id_intervento", sql.toString(), params);
	}

	@Override
	public int createIntervento(TipoInterventoDatiTecnici interventoDatiTehnici) throws DuplicateRecordException {

		StringBuilder sql = new StringBuilder();

		sql.append("INSERT INTO idf_t_intervento ");
		sql.append("(descrizione_intervento, localita, superficie_interessata_ha, fk_config_utente_compilatore) ");
		sql.append("VALUES (?, ?, ?, ?, ?, ?, ?)");

		List<Object> parameters = new ArrayList<>();

		parameters.add(interventoDatiTehnici.getDescrizione());
		parameters.add(interventoDatiTehnici.getLocalita());
		parameters.add(interventoDatiTehnici.getSuperficieInteressata());
		parameters.add(1);// mocked because is notnull in database

		return AIKeyHolderUtil.updateWithGenKey("id_intervento", sql.toString(), parameters);
	}
	
	@Override
	public int createInterventoNEW(TipoInterventoDatiTecnici tipoInterventoDatiTehnici,ConfigUtente loggedConfig){

		StringBuilder sql = new StringBuilder();

		sql.append("INSERT INTO idf_t_intervento ");
		sql.append("(descrizione_intervento, localita, fk_config_utente_compilatore ");
		sql.append(" ,data_inizio_intervento, data_fine_intervento, data_inserimento ) ");
		sql.append("VALUES (?, ?, ?, ?, ?, ?)");

		List<Object> parameters = new ArrayList<>();

		parameters.add(tipoInterventoDatiTehnici.getDescrizione());
		parameters.add(tipoInterventoDatiTehnici.getLocalita());
		parameters.add(loggedConfig.getIdConfigUtente());
		if(tipoInterventoDatiTehnici.getDataPresuntaIntervento() == null) {
			parameters.add(null);
			parameters.add(null);
		}else {
			parameters.add(java.sql.Date.valueOf(tipoInterventoDatiTehnici.getDataPresuntaIntervento()));
			parameters.add(java.sql.Date.valueOf(tipoInterventoDatiTehnici.getDataPresuntaIntervento().plusMonths(3).plusDays(12)));
		}
		parameters.add(java.sql.Date.valueOf(LocalDate.now()));

		return AIKeyHolderUtil.updateWithGenKey("id_intervento", sql.toString(), parameters);
	}

	@Override
	public void updateIntervento(TipoInterventoDatiTecnici tipoInterventoDatiTehnici, Integer idIntervento,
			int fkConfigUtente) throws RecordNotFoundException {

		StringBuilder sql = new StringBuilder();

		sql.append("UPDATE idf_t_intervento ");
		sql.append("SET  descrizione_intervento= ?,localita= ?, superficie_interessata_ha= ?, "
				+ "fk_config_utente_compilatore= ?,"
				+ " data_inizio_intervento = ? , data_fine_intervento = ?, data_inserimento = ? ");
		sql.append("WHERE id_intervento = ?");

		List<Object> parameters = new ArrayList<>();

		parameters.add(tipoInterventoDatiTehnici.getDescrizione());
		parameters.add(tipoInterventoDatiTehnici.getLocalita());
		parameters.add(tipoInterventoDatiTehnici.getSuperficieInteressata());
		//parameters.add(null); // should be removed as a field from database, 08.07.2020
		parameters.add(fkConfigUtente);
		parameters.add(java.sql.Date.valueOf(tipoInterventoDatiTehnici.getDataPresuntaIntervento()));
		parameters.add(java.sql.Date.valueOf(tipoInterventoDatiTehnici.getDataPresuntaIntervento().plusMonths(3).plusDays(12)));
		parameters.add(java.sql.Date.valueOf(LocalDate.now()));
		parameters.add(idIntervento);

		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());

		logger.info("Data in intervento is updated");
	}
	
	@Override
	public void updateDataFineIntervento(java.util.Date dataFineIntervento, Integer idIntervento) {
		StringBuilder sql = new StringBuilder();

		sql.append("UPDATE idf_t_intervento ");
		sql.append("SET data_fine_intervento = ?, data_aggiornamento = ? ");
		sql.append("WHERE id_intervento = ?");

		List<Object> parameters = new ArrayList<>();
		parameters.add(dataFineIntervento);
		parameters.add(java.sql.Date.valueOf(LocalDate.now()));
		parameters.add(idIntervento);

		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
		
	}
	
	@Override
	public void updateInterventoDescrizione(String descrizione, Integer idIntervento,
			int fkConfigUtente) throws RecordNotFoundException {

		StringBuilder sql = new StringBuilder();

		sql.append("UPDATE idf_t_intervento ");
		sql.append("SET  descrizione_intervento= ?, fk_config_utente_compilatore= ? ");
		sql.append("WHERE id_intervento = ?");

		List<Object> parameters = new ArrayList<>();

		parameters.add(descrizione);
		parameters.add(fkConfigUtente);
		parameters.add(idIntervento);

		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());

		logger.info("Data in intervento is updated");
	}


	@Override
	public void updateInterventoSoggettoProfess(Integer idSoggetto, Integer idIntervento, int fkConfigUtente) {
		StringBuilder sql = new StringBuilder();

		sql.append("UPDATE idf_t_intervento ");
		sql.append("SET  fk_soggetto_profess= ?, fk_config_utente_compilatore= ? ");
		sql.append("WHERE id_intervento = ?");

		List<Object> parameters = new ArrayList<>();

		parameters.add(idSoggetto);
		parameters.add(fkConfigUtente);
		parameters.add(idIntervento);

		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());

		logger.info("fk_soggetto_profess in intervento is updated");
	}

	@Override
	public Intervento findInterventoByIdIntervento(int idIntervento) {
		String sql = "SELECT * from idf_t_intervento WHERE id_intervento = ?";

		return DBUtil.jdbcTemplate.queryForObject(sql, interventoMapper, idIntervento);
	}
	@Override
	public String findDenominazionePianoByID(Integer idGeoPlPfa){
		String sql = "SELECT denominazione FROM idf.idf_geo_pl_pfa WHERE idgeo_pl_pfa = ?";

		return DBUtil.jdbcTemplate.queryForObject(sql, String.class, idGeoPlPfa);
	}

	@Override
	public void updateInterventoAtDatiTecnici(TipoInterventoDatiTecnici interventoDatiTehnici, Integer idIntervento,
			int fkConfigUtente) throws RecordNotFoundException {
		StringBuilder sql = new StringBuilder();

		sql.append("UPDATE idf_t_intervento ");
		sql.append("SET  descrizione_intervento= ?,localita= ?"); 
		sql.append(", fk_config_utente_compilatore= ?, data_aggiornamento = ? ");
				
		sql.append(" WHERE id_intervento = ?");

		List<Object> parameters = new ArrayList<>();

		parameters.add(interventoDatiTehnici.getDescrizione());
		parameters.add(interventoDatiTehnici.getLocalita());
		//parameters.add(interventoDatiTehnici.getSuperficieInteressata());
		parameters.add(fkConfigUtente);
		parameters.add(java.sql.Date.valueOf(LocalDate.now()));
		parameters.add(idIntervento);

		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());

		logger.info("Data in intervento is updated");

	}

	@Override
	public void updateInterventoWithSuperficieInteressata(Integer idIntervento, BigDecimal superficieInteressata) {
		System.out.println("-------- superficieInteressata: <"+superficieInteressata+"------------->");
		String sql = "UPDATE idf_t_intervento SET superficie_interessata_ha = ? WHERE id_intervento = ?";
		DBUtil.jdbcTemplate.update(sql, superficieInteressata, idIntervento);
	}

	@Override
	public void updateInterventoWithFkSoggettoProfess(Integer idIntervento, Integer fkSoggettoProfess) {
		String sql = "UPDATE idf_t_intervento SET fk_soggetto_profess = ? WHERE id_intervento = ?";
		DBUtil.jdbcTemplate.update(sql, fkSoggettoProfess, idIntervento);
	}

	@Override
	public void deleteIntervento(Integer idIntervento) {

		String sql = "DELETE FROM idf_t_intervento WHERE id_intervento = ?";
		DBUtil.jdbcTemplate.update(sql, idIntervento);
	}

	@Override
	public PaginatedList<InterventoPiano> findInterventiPianiByIdGeoPlPfa(int idGeoPlPfa, int page, int limit,
			String sort) {
		StringBuilder sql = getGeoPlPfaMainQuery();
		sql.append(" WHERE intervSel.idgeo_pl_pfa = ? ");
		geoPlPfaQueryGroupBy(sql);
		if(sort != null) {
		sql.append(getQuerySortingSegment(sort));
		}else {
			sql.append(" order by intervsel.id_intervento desc");
		}
		logger.info("findInterventiPianiByIdGeoPlPfa: " + sql.toString() + " - idGeoPlPfa: " + idGeoPlPfa + " - sort: " + sort);
		return super.paginatedList(sql.toString(), Arrays.asList(idGeoPlPfa), interventoPianoMapper, page, limit);
	}

	@Override
	public List<InterventoPiano> findInterventiPianiByIdGeoPlPfa(int idGeoPlPfa) {
		StringBuilder sql = getGeoPlPfaMainQuery();
		sql.append(" WHERE intervSel.idgeo_pl_pfa = ? ");
		geoPlPfaQueryGroupBy(sql);

		return DBUtil.jdbcTemplate.query(sql.toString(), interventoPianoMapper, idGeoPlPfa);
	}

	private StringBuilder getGeoPlPfaMainQuery() {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT intervSel.id_intervento, ");
		sql.append("string_agg(distinct plpfa.denominazione::varchar,', '  order by plpfa.denominazione::varchar ) as denominazione,");
		sql.append("intervSel.nr_progressivo_interv, intervSel.annata_silvana, intervSel.data_presa_in_carico");
		sql.append(", ARRAY_AGG(partfor.idgeo_pl_particella_forest) AS idgeo_pl_particella_forest");
		sql.append(", ARRAY_AGG(forest.cod_particella_for) AS cod_particella_for");
		sql.append(", ARRAY_AGG(forest.denominazione_particella) AS denominazione_particella");
		sql.append(", interv.data_inizio_intervento, interv.data_fine_intervento, interv.descrizione_intervento");
		sql.append(", interv.localita, interv.superficie_interessata_ha, intervSel.m3_prelevati, stato.descr_stato_intervento, intervSel.flg_istanza_taglio,");
		sql.append(" CASE WHEN istfor.nr_istanza_forestale is null or istfor.data_inserimento is null THEN 'NO' ");
		sql.append(" ELSE istfor.nr_istanza_forestale || '/' || extract(year from istfor.data_inserimento) end as num_istanza "); 
		sql.append(" FROM idf_t_interv_selvicolturale intervSel");
		sql.append(" INNER JOIN idf_t_intervento interv ON intervSel.id_intervento = interv.id_intervento");
		sql.append(" LEFT JOIN idf_d_stato_intervento stato ON intervSel.fk_stato_intervento = stato.id_stato_intervento");
		sql.append(" LEFT JOIN idf_r_partfor_intervento partfor ON partfor.id_intervento = intervSel.id_intervento");
		sql.append(" and partfor.idgeo_pl_particella_forest");
		sql.append(" not in (select idgeo_pl_particella_forest from idf_geo_pl_particella_forest where cod_particella_for = '0')");
		sql.append(" LEFT JOIN idf_geo_pl_particella_forest forest ON forest.cod_particella_for <> '0' AND partfor.idgeo_pl_particella_forest = forest.idgeo_pl_particella_forest");
		sql.append(" LEFT JOIN  idf_geo_pl_pfa plpfa ON forest.idgeo_pl_pfa = plpfa.idgeo_pl_pfa");
		sql.append(" LEFT JOIN  idf_t_istanza_forest istfor on interv.id_intervento = istfor.id_istanza_intervento");
		return sql;
	}

	private void geoPlPfaQueryGroupBy(StringBuilder sql) {
		sql.append(" GROUP BY istfor.nr_istanza_forestale ,istfor.data_inserimento, intervSel.id_intervento, interv.data_inizio_intervento, interv.data_fine_intervento");
		sql.append(
				" , interv.descrizione_intervento, interv.localita, interv.superficie_interessata_ha, stato.descr_stato_intervento");
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
		mappedColumnNames.put("nIntervento", "nr_progressivo_interv");
		mappedColumnNames.put("annataSilvana", "annata_silvana");
		mappedColumnNames.put("nParticelaForestale", "idgeo_pl_particella_forest");
		mappedColumnNames.put("dataInizioString", "data_inizio_intervento");
		mappedColumnNames.put("dataFineString", "data_fine_intervento");
		mappedColumnNames.put("descrizione", "descrizione_intervento");
		mappedColumnNames.put("localita", "localita");
		mappedColumnNames.put("superficieInteressata", "superficie_interessata_ha");
		mappedColumnNames.put("m3Prelevati", "m3_prelevati");
		mappedColumnNames.put("descrStatoIntervento", "descr_stato_intervento");
		mappedColumnNames.put("comunicazioneDiTaglio", "flg_istanza_taglio");

		return mappedColumnNames.get(fieldName) != null ? mappedColumnNames.get(fieldName) : "nr_progressivo_interv";
	}

	@Override
	public void updateInterventoWithChiusuraData(InterventoRiepilogo interventoRiepilogo,
			Integer idIntervento) {
		
		StringBuilder update = new StringBuilder();
		
		update.append("UPDATE idf_t_intervento set data_inizio_intervento = ?, data_fine_intervento = ?, data_aggiornamento  = ?");
		update.append(" where id_intervento = ? ");
		
		List<Object> parameters = new ArrayList<>();
		parameters.add(java.sql.Date.valueOf(interventoRiepilogo.getChiusuraInformazioni().getDataInizio()));
		parameters.add(java.sql.Date.valueOf(interventoRiepilogo.getChiusuraInformazioni().getDataFine()));
		parameters.add(java.sql.Date.valueOf(LocalDate.now()));
		parameters.add(idIntervento);
		
		DBUtil.jdbcTemplate.update(update.toString(), parameters.toArray());
		
	}

	@Override
	public TSoggetto getUserCompilatoreByIdIntervento(int idIntervento) {
		String sql = "select sog.* from IDF_T_INTERVENTO a " + 
				"inner join idf_cnf_config_utente ut on a.fk_config_utente_compilatore = ut.id_config_utente " + 
				"inner join idf_t_soggetto sog on sog.id_soggetto = ut.fk_soggetto " + 
				"where a.id_intervento = ?";
		
		List<TSoggetto> soggettos = DBUtil.jdbcTemplate.query(sql, new TSoggettoMapper(), idIntervento);
		if (soggettos.isEmpty()) {
			throw new RecordNotFoundException();
		} else {
			return soggettos.get(0);
		}
	}
	
	public int getCountLocalita(int idIntervento) {
		String sql = " SELECT count(distinct cat.fk_comune) FROM IDF_GEO_PL_PROP_CATASTO cat join " + 
				" IDF_R_PROPCATASTO_INTERVENTO int " + 
				" ON cat.idgeo_pl_prop_catasto = int.idgeo_pl_prop_catasto WHERE int.id_intervento = ?";
		return DBUtil.jdbcTemplate.queryForInt(sql, idIntervento);
	}

	@Override
	public List<DrawedGeometryInfo> getDrawedGeometryPfaList(Integer idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("select 'Poligono' as geomtype, descrizione, ");
		sql.append("string_agg(distinct pf.cod_particella_for::varchar(15),', '  ");
		sql.append("	order by pf.cod_particella_for::varchar(15) ) as partforest, ");
		sql.append("-1 as x_pun, -1 as y_pun, -1 as length_lin ");
		sql.append(",sup_tagliata_ha as area_pol ");
		sql.append("from idf_geo_pl_lotto_intervento pl ");
		sql.append("left join Idf_geo_pl_particella_forest pf on ST_Intersects(pl.geometria , pf.geometria) ");
		sql.append("where pl.fk_intervento = ?  ");
		sql.append("group by geomtype, descrizione, x_pun, y_pun, length_lin, area_pol ");
		sql.append("union all ");
		sql.append("select 'Linea' as geomtype, descrizione, ");
		sql.append("string_agg(distinct pf.cod_particella_for::varchar(15),', '  ");
		sql.append("	order by pf.cod_particella_for::varchar(15) ) as partforest, ");
		sql.append("-1 as x_pun, -1 as y_pun, ST_Length(lin.geometria)  as length_lin ");
		sql.append(",-1 as area_pol ");
		sql.append("from idf_geo_ln_lotto_intervento lin ");
		sql.append("left join Idf_geo_pl_particella_forest pf on ST_Intersects(lin.geometria , pf.geometria) ");
		sql.append("where lin.id_intervento = ? ");
		sql.append("group by geomtype, descrizione, x_pun, y_pun, length_lin, area_pol ");
		sql.append("union all  ");
		sql.append("select 'Punto' as geomtype, descrizione, ");
		sql.append("string_agg(distinct pf.cod_particella_for::varchar(15),', '  ");
		sql.append("	order by pf.cod_particella_for::varchar(15) ) as partforest, ");
		sql.append("ST_X(pt.geometria)as x_pun, ST_Y(pt.geometria) as y_pun, -1 as length_lin ");
		sql.append(",-1 as area_pol ");
		sql.append("from idf_geo_pt_lotto_intervento pt ");
		sql.append("left join Idf_geo_pl_particella_forest pf on ST_Intersects(pt.geometria , pf.geometria) ");
		sql.append("where pt.id_intervento = ? ");
		sql.append("group by geomtype, descrizione, x_pun, y_pun, length_lin, area_pol ");
		List<Object>params = new ArrayList<>();
		params.add(idIntervento);
		params.add(idIntervento);
		params.add(idIntervento);
		
		logger.info("getDrawedGeometryPfaList sql: " + sql.toString() + " -- idIntervento: " + idIntervento);

		return DBUtil.jdbcTemplate.query(sql.toString(), new DrawedGeometryInfoMapper(), params.toArray());
	}

	@Override
	public String getStringParticelleForestaliByIntervento(Integer idIntervento){
		StringBuilder sql = new StringBuilder();
		sql.append("select string_agg(distinct igppf.cod_particella_for::varchar(15),', ' order by igppf.cod_particella_for::varchar(15) ) as partforest ");
		sql.append("from idf_r_partfor_intervento irpi ");
		sql.append("join idf_geo_pl_particella_forest igppf on irpi.idgeo_pl_particella_forest = igppf.idgeo_pl_particella_forest ");
		sql.append("where irpi.id_intervento = ?");
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), String.class, idIntervento);
	}

}
