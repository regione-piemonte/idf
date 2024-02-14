/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import it.csi.idf.idfapi.dto.*;
import it.csi.idf.idfapi.mapper.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.impl.dao.SoggettoDAO;
import it.csi.idf.idfapi.util.AIKeyHolderUtil;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.ListUtil;
import it.csi.idf.idfapi.util.TipoTitolaritaEnum;
import it.csi.idf.idfapi.util.service.integration.aaep.ListaAttEconProd;

@Component
public class SoggettoDAOImpl implements SoggettoDAO {
	
	static final Logger logger = Logger.getLogger(SoggettoDAOImpl.class);

	private final TSoggettoMapper soggettoMapper = new TSoggettoMapper();
	private final SoggettoResourceMapper soggettoResourceMapper = new SoggettoResourceMapper();
	private final FatSoggettoMapper fatSoggettoMapper = new FatSoggettoMapper();

	@Override
	public TSoggetto findSoggettoById(Integer idSoggetto) throws RecordNotFoundException {
		String sql = "SELECT * FROM idf_t_soggetto s where s.id_soggetto=?";
		List<TSoggetto> soggettos = DBUtil.jdbcTemplate.query(sql, soggettoMapper, idSoggetto);
		if (soggettos.isEmpty()) {
			throw new RecordNotFoundException();
		} else {

			return soggettos.get(0);
		}

	}

	@Override
	public TSoggetto findSoggettoByIdConfigUtente(Integer idConfigUtente) throws RecordNotFoundException {
		String sql ="SELECT its.*  " +
				"FROM idf_t_soggetto its  " +
				"INNER JOIN idf_cnf_config_utente iccu ON iccu.fk_soggetto = its.id_soggetto " +
				"WHERE iccu.id_config_utente = ?";
		List<TSoggetto> soggettos = DBUtil.jdbcTemplate.query(sql, soggettoMapper, idConfigUtente);
		if (soggettos.isEmpty()) {
			throw new RecordNotFoundException();
		} else {

			return soggettos.get(0);
		}
	}

	@Override
	public TSoggetto findSoggettoByCodiceFiscale(String codiceFiscale) throws RecordNotUniqueException {

		String sql = "SELECT * FROM idf_t_soggetto s where s.codice_fiscale= ? "
				+ " and nome is not null and nome <> '' and cognome is not null and cognome <> ''" +
				" ORDER BY codice_fiscale, data_inserimento desc , data_aggiornamento desc";
		logger.debug("findSoggettoByCodiceFiscale sql: " + sql + " - cf: " + codiceFiscale);
		List<TSoggetto> soggettos = DBUtil.jdbcTemplate.query(sql, soggettoMapper, codiceFiscale);
		if (soggettos.isEmpty()) {
			return null;
		} else {
			return soggettos.get(0);
		}

	}
	
	@Override
	public TSoggetto findAziendaByCodiceFiscale(String codiceFiscale) throws RecordNotUniqueException {

/*
		String sql = "SELECT * FROM idf_t_soggetto s where s.codice_fiscale= ? "
				+ " and denominazione is not null and denominazione <> '' and partita_iva is not null" +
				" ORDER BY codice_fiscale, data_inserimento desc , data_aggiornamento desc";
*/

		String sql ="SELECT DISTINCT * FROM idf_t_soggetto s where s.codice_fiscale= ? " +
				"and ( " +
				"	(denominazione is not null and denominazione <> '' and partita_iva is not NULL) OR flg_ente_pubblico = 1 " +
				") ORDER BY codice_fiscale, data_inserimento desc , data_aggiornamento desc";

		logger.debug("findSoggettoByCodiceFiscale sql: " + sql + " - cf: " + codiceFiscale);
		List<TSoggetto> soggettos = DBUtil.jdbcTemplate.query(sql, soggettoMapper, codiceFiscale);
		if (soggettos.isEmpty()) {
			return null;
		} else {
			return soggettos.get(0);
		}

	}

	@Override
	public TSoggetto findAziendaByCodiceFiscaleAndIsPubblic(String codiceFiscale, Boolean isPubblic) throws RecordNotUniqueException {
		String sql = "SELECT * FROM idf_t_soggetto s where s.codice_fiscale= ? "
				+ " and denominazione is not null and denominazione <> '' and partita_iva is not null and flg_ente_pubblico = ?" +
				" ORDER BY codice_fiscale, data_inserimento desc , data_aggiornamento desc";
		logger.debug("findSoggettoByCodiceFiscaleIsPubblic sql: " + sql + " - cf: " + codiceFiscale);
		List<TSoggetto> soggettos = DBUtil.jdbcTemplate.query(sql, soggettoMapper, codiceFiscale, isPubblic ? 1 : 0);
		if (soggettos.isEmpty()) {
			return null;
		} else {
			return soggettos.get(0);
		}
	}

	@Override
	public List<TSoggetto> findByPartialCodiceFiscale(String codiceFiscale) {
		String sql = "SELECT * FROM idf_t_soggetto s where s.codice_fiscale LIKE '" + codiceFiscale + "%' ";

		return DBUtil.jdbcTemplate.query(sql, soggettoMapper);
	}

	@Override
	public int createSoggetto(TSoggetto soggetto) {

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO idf_t_soggetto(");
		sql.append("fk_comune, nome, cognome, codice_fiscale, partita_iva, denominazione, indirizzo, ");
		sql.append(
				"nr_telefonico, e_mail, pec, n_iscrizione_ordine, istat_prov_iscrizione_ordine, istat_prov_competenza_terr, ");
		sql.append("flg_settore_regionale, data_inserimento, fk_config_utente, civico, cap, flg_ente_pubblico, nr_martello_forestale)");
		sql.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, ?)");
		List<Object> parameters = new ArrayList<>();
		parameters.add(soggetto.getFkComune());
		parameters.add(soggetto.getNome());
		parameters.add(soggetto.getCognome());
		parameters.add(soggetto.getCodiceFiscale());
		parameters.add(soggetto.getPartitaIva());
		parameters.add(soggetto.getDenominazione());
		parameters.add(soggetto.getIndirizzo());
		parameters.add(soggetto.getNrTelefonico());
		parameters.add(soggetto.geteMail());
		parameters.add(soggetto.getPec());
		parameters.add(soggetto.getnIscrizioneOrdine());
		parameters.add(soggetto.getIstatProvIscrizioneOrdine());
		parameters.add(soggetto.getIstatProvCompetenzaTerr());
		parameters.add(soggetto.getFlgSettoreRegionale());
		parameters.add(Date.valueOf(LocalDate.now()));
		parameters.add(soggetto.getFkConfigUtente());
		parameters.add(soggetto.getCivico());
		parameters.add(soggetto.getCap());
		parameters.add(soggetto.getFlgEntePubblco() != null ? soggetto.getFlgEntePubblco() : 0);
		parameters.add(soggetto.getNrMartelloForestale());

		return AIKeyHolderUtil.updateWithGenKey("id_soggetto", sql.toString(), parameters);

	}

	@Override
	public int createSoggettoFisica(TSoggetto soggetto) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO idf_t_soggetto(");
		sql.append("fk_comune, nome, cognome, codice_fiscale, indirizzo, nr_telefonico, e_mail, ");
		sql.append("flg_settore_regionale, data_inserimento, fk_config_utente, civico, cap)");
		sql.append(" VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
		List<Object> parameters = new ArrayList<>();
		parameters.add(soggetto.getFkComune());
		parameters.add(soggetto.getNome());
		parameters.add(soggetto.getCognome());
		parameters.add(soggetto.getCodiceFiscale());
		parameters.add(soggetto.getIndirizzo());
		parameters.add(soggetto.getNrTelefonico());
		parameters.add(soggetto.geteMail());
		parameters.add(0);
		parameters.add(Date.valueOf(LocalDate.now()));
		parameters.add(soggetto.getFkConfigUtente());
		parameters.add(soggetto.getCivico());
		parameters.add(soggetto.getCap());

		return AIKeyHolderUtil.updateWithGenKey("id_soggetto", sql.toString(), parameters);
	}

	@Override
	public int updateSoggetto(TSoggetto soggetto) {

		List<Object> parameters = new ArrayList<>();

		StringBuilder update = new StringBuilder();
		update.append("UPDATE idf_t_soggetto SET");

		update.append(" flg_settore_regionale = ?");
		parameters.add(soggetto.getFlgSettoreRegionale()); // NOT NULL constraint

		if (soggetto.getFkComune() != null) {
			update.append(", fk_comune = ?");
			parameters.add(soggetto.getFkComune());
		}
		if (soggetto.getNome() != null) {
			update.append(", nome = ?");
			parameters.add(soggetto.getNome());
		}
		if (soggetto.getCognome() != null) {
			update.append(", cognome = ?");
			parameters.add(soggetto.getCognome());
		}
		if (soggetto.getCodiceFiscale() != null) {
			update.append(", codice_fiscale = ?");
			parameters.add(soggetto.getCodiceFiscale());
		}
		if (soggetto.getPartitaIva() != null) {
			update.append(", partita_iva = ?");
			parameters.add(soggetto.getPartitaIva());
		}
		if (soggetto.getDenominazione() != null) {
			update.append(", denominazione = ?");
			parameters.add(soggetto.getDenominazione());
		}
		if (soggetto.getIndirizzo() != null) {
			update.append(", indirizzo = ?");
			parameters.add(soggetto.getIndirizzo());
		}
		if (soggetto.getNrTelefonico() != null) {
			update.append(", nr_telefonico = ?");
			parameters.add(soggetto.getNrTelefonico());
		}
		if (soggetto.geteMail() != null) {
			update.append(", e_mail = ?");
			parameters.add(soggetto.geteMail());
		}
		if (soggetto.getPec() != null) {
			update.append(", pec = ?");
			parameters.add(soggetto.getPec());
		}
		if (soggetto.getnIscrizioneOrdine() != null) {
			update.append(", n_iscrizione_ordine = ?");
			parameters.add(soggetto.getnIscrizioneOrdine());
		}
		if (soggetto.getIstatProvIscrizioneOrdine() != null) {
			update.append(", istat_prov_iscrizione_ordine = ?");
			parameters.add(soggetto.getIstatProvIscrizioneOrdine());
		}

		if (soggetto.getNrMartelloForestale() != null) {
			update.append(", nr_martello_forestale = ?");
			parameters.add(soggetto.getNrMartelloForestale());
		}


		if (soggetto.getIstatProvCompetenzaTerr() != null) {
			update.append(", istat_prov_competenza_terr = ?");
			parameters.add(soggetto.getIstatProvCompetenzaTerr());
		}
		if (soggetto.getDataAggiornamento() != null) {
			update.append(", data_aggiornamento = ?");
			parameters.add(Date.valueOf(soggetto.getDataAggiornamento()));
		}
		if (soggetto.getFkConfigUtente() != null) {
			update.append(", fk_config_utente = ?");
			parameters.add(soggetto.getFkConfigUtente());
		}
		if (soggetto.getCivico() != null) {
			update.append(", civico = ?");
			parameters.add(soggetto.getCivico());
		}
		if (soggetto.getCap() != null) {
			update.append(", cap = ?");
			parameters.add(soggetto.getCap());
		}
		if (soggetto.getFkCategoriaProfessionale() != null) {
			update.append(", fk_categoria_professionale = ?");
			parameters.add(soggetto.getFkCategoriaProfessionale());
		}

		if (soggetto.getFlgEntePubblco() != null) {
			update.append(", flg_ente_pubblico = ?");
			parameters.add(soggetto.getFlgEntePubblco());
		}




		update.append(" where id_soggetto = ?");
		parameters.add(soggetto.getIdSoggetto());

		return DBUtil.jdbcTemplate.update(update.toString(), parameters.toArray());
	}

	public boolean validation(TSoggetto soggetto) {
		return soggetto.getCodiceFiscale() != null && soggetto.getNome() != null && soggetto.getCognome() != null
				&& soggetto.getNrTelefonico() != null && soggetto.geteMail() != null;
	}

	@Override
	public void updateFkConfigUtente(int idSoggetto, int configUtenteId) {

		String sql = "Update idf_t_soggetto SET fk_config_utente = ? WHERE id_soggetto = ?";
		DBUtil.jdbcTemplate.update(sql, configUtenteId, idSoggetto);
	}

	@Override
	public void updateSoggettoProfess(TSoggetto soggettoProfess) {
		StringBuilder update = new StringBuilder();
		update.append("UPDATE idf_t_soggetto SET");
		update.append(" nome = ?");
		update.append(", cognome = ?");
		update.append(", codice_fiscale = ?");
		update.append(", partita_iva = ?");
		update.append(", nr_telefonico = ?");
		update.append(", e_mail = ?");
		update.append(", pec = ?");
		update.append(", data_aggiornamento = ?");
		update.append(", fk_config_utente = ?");

		if (soggettoProfess.getFlgEntePubblco() != null) {
			update.append(", flg_ente_pubblico = ?");
		}

		update.append(" where id_soggetto = ?");

		List<Object> parameters = new ArrayList<>();
		parameters.add(soggettoProfess.getNome());
		parameters.add(soggettoProfess.getCognome());
		parameters.add(soggettoProfess.getCodiceFiscale());
		parameters.add(soggettoProfess.getPartitaIva());
		parameters.add(soggettoProfess.getNrTelefonico());
		parameters.add(soggettoProfess.geteMail());
		parameters.add(soggettoProfess.getPec());
		parameters.add(Date.valueOf(LocalDate.now()));
		parameters.add(soggettoProfess.getFkConfigUtente());
		if (soggettoProfess.getFlgEntePubblco() != null) {
			parameters.add(soggettoProfess.getFlgEntePubblco());
		}

		parameters.add(soggettoProfess.getIdSoggetto());

		DBUtil.jdbcTemplate.update(update.toString(), parameters.toArray());
	}

	@Override
	public List<SoggettoResource> getPersFisicaForBOSearch(Integer idTipoIstanza) {
		StringBuilder sql = new StringBuilder();

		List<Integer> tipoIstanze = new ArrayList<>();
		tipoIstanze.add(idTipoIstanza);
		if (idTipoIstanza == 2) {
			tipoIstanze.add(3);
		}
		MapSqlParameterSource inQueryParams = new MapSqlParameterSource();
		inQueryParams.addValue("tipoIstanze", tipoIstanze);

		sql.append("select idfr.id_soggetto, its.codice_fiscale, its.partita_iva, its.nome, its.cognome, its.denominazione ");
		sql.append(" from (SELECT distinct  id_soggetto FROM idf_r_soggetto_intervento si ");
		sql.append(" inner join idf_t_istanza_forest ist ");
		sql.append(" on ist.id_istanza_intervento = si.id_intervento and fk_stato_istanza > 1 and fk_tipo_istanza IN (:tipoIstanze) ");
		sql.append("where id_tipo_soggetto = 1)  idfr ");
		sql.append("left join idf_t_soggetto its on its.id_soggetto =idfr.id_soggetto ");
		sql.append("where its.nome <>'' and its.nome is not null ");
		sql.append("and its.cognome <>'' and its.cognome is not null ");
		sql.append("and (denominazione is null or denominazione = '') ");
		sql.append("order by cognome");
		logger.info("getPersFisicaForBOSearch - sql: " + sql.toString() + " -- idTipoIstanza: " + idTipoIstanza);
		//return DBUtil.jdbcTemplate.query(sql.toString(), soggettoResourceMapper, idTipoIstanza);
		return DBUtil.namedJdbcTemplate.query(
				sql.toString(),
				inQueryParams,
				soggettoResourceMapper);
	}

	@Override
	public List<SoggettoResource> getPersGiuridicaForBOSearch(Integer idTipoIstanza) {
		StringBuilder sql = new StringBuilder();

		List<Integer> tipoIstanze = new ArrayList<>();
		tipoIstanze.add(idTipoIstanza);
		if (idTipoIstanza == 2) {
			tipoIstanze.add(3);
		}
		MapSqlParameterSource inQueryParams = new MapSqlParameterSource();
		inQueryParams.addValue("tipoIstanze", tipoIstanze);

		sql.append(" select idfr.id_soggetto, its.codice_fiscale, its.partita_iva, its.nome, its.cognome, its.denominazione ");
		sql.append(" from (SELECT distinct  id_soggetto FROM idf_r_soggetto_intervento si ");
		sql.append(" inner join idf_t_istanza_forest ist ");
		sql.append(" on ist.id_istanza_intervento = si.id_intervento and fk_stato_istanza > 1 and fk_tipo_istanza  IN (:tipoIstanze) ");
		sql.append("where id_tipo_soggetto = 1)  idfr ");
		sql.append("left join idf_t_soggetto its on its.id_soggetto =idfr.id_soggetto ");
		sql.append("where partita_iva <> '' and partita_iva is not null ");
		sql.append("and denominazione <> '' and denominazione is not null ");
		sql.append("and (nome is null or nome = '') ");
		sql.append("and (cognome is null or cognome = '') ");
		sql.append("order by denominazione");
		logger.info("getPersGiuridicaForBOSearch - sql: " + sql.toString() + " -- idTipoIstanza: " + idTipoIstanza);
		//return DBUtil.jdbcTemplate.query(sql.toString(), soggettoResourceMapper, idTipoIstanza);
		return DBUtil.namedJdbcTemplate.query(
				sql.toString(),
				inQueryParams,
				soggettoResourceMapper);
	}

	@Override
	public List<SoggettoResource> getPersFisicaForBOTagliSearch(String search) {
		StringBuilder sql = new StringBuilder();

		MapSqlParameterSource inQueryParams = new MapSqlParameterSource();
		inQueryParams.addValue("search", "%" + search +"%");

		sql.append("SELECT distinct soggetto.id_soggetto, soggetto.codice_fiscale, soggetto.partita_iva,  ");
		sql.append("soggetto.nome, soggetto.cognome, soggetto.denominazione ");
		sql.append("FROM idf_t_soggetto soggetto ");
		sql.append("join idf_r_soggetto_intervento sogint ON soggetto.id_soggetto=sogint.id_soggetto ");
		sql.append("join idf_t_interv_selvicolturale ivi ON sogint.id_intervento=ivi.id_intervento ");
		sql.append("join idf_t_istanza_forest itif on itif.id_istanza_intervento = ivi.id_intervento  ");
		sql.append("WHERE sogint.id_tipo_soggetto = 1  and itif.fk_stato_istanza <> 1 ");
		sql.append("AND  (soggetto.denominazione is null or soggetto.denominazione = '') ");
		sql.append("AND (soggetto.cognome ilike :search or soggetto.codice_fiscale ilike :search or soggetto.nome ilike :search) ");
		sql.append("ORDER BY soggetto.cognome ");

		logger.info("getPersFisicaForBOSearch - sql: " + sql.toString() + " -- search: " + search);
		return DBUtil.namedJdbcTemplate.query(
				sql.toString(),
				inQueryParams,
				soggettoResourceMapper);
	}

	@Override
	public List<SoggettoResource> getPersGiuridicaForBOTagliSearch(String search) {
		StringBuilder sql = new StringBuilder();

		MapSqlParameterSource inQueryParams = new MapSqlParameterSource();
		inQueryParams.addValue("search", "%" + search +"%");

		sql.append("SELECT distinct soggetto.id_soggetto, soggetto.codice_fiscale, soggetto.partita_iva, soggetto.nome, soggetto.cognome, soggetto.denominazione ");
		sql.append("FROM idf_t_soggetto soggetto ");
		sql.append("join idf_r_soggetto_intervento sogint ON soggetto.id_soggetto=sogint.id_soggetto ");
		sql.append("join idf_t_interv_selvicolturale ivi ON sogint.id_intervento=ivi.id_intervento ");
		sql.append("join idf_t_istanza_forest itif on itif.id_istanza_intervento = ivi.id_intervento  ");
		sql.append("WHERE sogint.id_tipo_soggetto = 1 and itif.fk_stato_istanza <> 1  ");
		sql.append("and (nome is null or nome = '') and (cognome is null or cognome = '')  ");
		sql.append("AND (soggetto.denominazione ilike :search or soggetto.codice_fiscale ilike :search) ");
		sql.append("ORDER BY soggetto.denominazione; ");

		logger.info("getPersFisicaForBOSearch - sql: " + sql.toString() + " -- search: " + search);
		return DBUtil.namedJdbcTemplate.query(
				sql.toString(),
				inQueryParams,
				soggettoResourceMapper);
	}

	@Override
	public List<SoggettoResource> getUtilizzatorePFForBOSearch(String search) {
		StringBuilder sql = new StringBuilder();

		MapSqlParameterSource inQueryParams = new MapSqlParameterSource();
		inQueryParams.addValue("search", "%" + search +"%");

		sql.append("SELECT distinct soggetto.id_soggetto, soggetto.codice_fiscale, soggetto.partita_iva,  ");
		sql.append("soggetto.nome, soggetto.cognome, soggetto.denominazione ");
		sql.append("FROM idf_t_soggetto soggetto ");
		sql.append("join idf_r_soggetto_intervento sogint ON soggetto.id_soggetto=sogint.id_soggetto ");
		sql.append("join idf_t_interv_selvicolturale ivi ON sogint.id_intervento=ivi.id_intervento ");
		sql.append("join idf_t_istanza_forest itif on itif.id_istanza_intervento = ivi.id_intervento  ");
		sql.append("WHERE sogint.id_tipo_soggetto = 2 and itif.fk_stato_istanza <> 1 ");
		sql.append("AND  (soggetto.denominazione is null or soggetto.denominazione = '') ");
		sql.append("AND (soggetto.cognome ilike :search or soggetto.codice_fiscale ilike :search or soggetto.nome ilike :search) ");
		sql.append("ORDER BY soggetto.cognome ");
		logger.info("getUtilizzatorePF - sql: " + sql.toString() + " -- search: " + search);
		return DBUtil.namedJdbcTemplate.query(
				sql.toString(),
				inQueryParams,
				soggettoResourceMapper);
	}

	@Override
	public List<SoggettoResource> getUtilizzatorePGForBOSearch(String search) {
		StringBuilder sql = new StringBuilder();

		MapSqlParameterSource inQueryParams = new MapSqlParameterSource();
		inQueryParams.addValue("search", "%" + search +"%");

		sql.append("SELECT distinct soggetto.id_soggetto, soggetto.codice_fiscale, soggetto.partita_iva, soggetto.nome, soggetto.cognome, soggetto.denominazione ");
		sql.append("FROM idf_t_soggetto soggetto ");
		sql.append("join idf_r_soggetto_intervento sogint ON soggetto.id_soggetto=sogint.id_soggetto ");
		sql.append("join idf_t_interv_selvicolturale ivi ON sogint.id_intervento=ivi.id_intervento ");
		sql.append("join idf_t_istanza_forest itif on itif.id_istanza_intervento = ivi.id_intervento  ");
		sql.append("WHERE sogint.id_tipo_soggetto = 1 and itif.fk_stato_istanza <> 1 ");
		sql.append("and (nome is null or nome = '') and (cognome is null or cognome = '')  ");
		sql.append("AND (soggetto.denominazione ilike :search or soggetto.codice_fiscale ilike :search) ");
		sql.append("ORDER BY soggetto.denominazione; ");

		logger.info("getUtilizzatorePG - sql: " + sql.toString() + " -- search: " + search);
		return DBUtil.namedJdbcTemplate.query(
				sql.toString(),
				inQueryParams,
				soggettoResourceMapper);
	}

	@Override
	public List<SoggettoResource> getTecniciForestaliForTagli(String search) {
		StringBuilder sql = new StringBuilder();

		StringJoiner searchJoined = new StringJoiner("", "%", "%");
		searchJoined.add(search);
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("stringa", searchJoined.toString());

		sql.append(" SELECT DISTINCT");
		sql.append(" s.id_soggetto, s.codice_fiscale, s.cognome, s.nome, s.partita_iva, s.denominazione ");
		sql.append(" FROM ");
		sql.append(" idf_t_soggetto s ");
		sql.append(" join idf_t_intervento intt on s.id_soggetto = intt.fk_soggetto_profess ");
		sql.append(" join idf_t_interv_selvicolturale ivi ON intt.id_intervento=ivi.id_intervento ");
		sql.append("join idf_t_istanza_forest itif on itif.id_istanza_intervento = ivi.id_intervento  ");
		sql.append(" WHERE  itif.fk_stato_istanza <> 1 AND ");
		sql.append(" (LOWER(s.codice_fiscale) LIKE LOWER(:stringa) OR");
		sql.append(" LOWER(s.nome) LIKE LOWER(:stringa) OR");
		sql.append(" LOWER(s.cognome) LIKE LOWER(:stringa) )");
		sql.append(" ORDER BY s.cognome ");

		logger.info("getUtilizzatorePG - sql: " + sql.toString() + " -- search: " + search);

		return DBUtil.namedJdbcTemplate.query(sql.toString(), namedParameters, soggettoResourceMapper);
	}

	@Override
	public List<SoggettoResource> getProfessForBOSearch(Integer idTipoIstanza) {
		StringBuilder sql = new StringBuilder();

		sql.append("select its.id_soggetto, its.codice_fiscale, its.partita_iva, its.nome, its.cognome, its.denominazione ");
		sql.append(" from (SELECT distinct  fk_soggetto_profess as id_soggetto FROM IDF_T_INTERV_TRASFORMAZIONE tr ");
		sql.append(" inner join IDF_T_INTERVENTO intv on intv.id_intervento = tr.id_intervento ");
		sql.append(" inner join idf_t_istanza_forest ist ");
		sql.append(" on ist.id_istanza_intervento = tr.id_intervento and fk_stato_istanza > 1 and fk_tipo_istanza = ? ");
		sql.append(" where fk_soggetto_profess is not null) idfr ");
		sql.append(" join idf_t_soggetto its on its.id_soggetto = idfr.id_soggetto ");
		sql.append(" order by cognome");
		logger.info("getProfessForBOSearch - sql: " + sql.toString() + " -- idTipoIstanza: " + idTipoIstanza);
		return DBUtil.jdbcTemplate.query(sql.toString(), soggettoResourceMapper, idTipoIstanza);
	}
	
	@Override
	public List<TSoggetto> getProfessionistiList() {
		StringBuilder sql = new StringBuilder();

		sql.append("select id_soggetto, fk_comune, nome, cognome, codice_fiscale, partita_iva, denominazione, indirizzo, ");
		sql.append("nr_telefonico, e_mail, pec, n_iscrizione_ordine,istat_prov_competenza_terr, ");
		sql.append("flg_settore_regionale, data_inserimento, data_aggiornamento, fk_config_utente, civico, cap, ");
		sql.append("fk_categoria_professionale, istat_comune_competenza_terr, prov.sigla_prov, ");
		sql.append("prov.sigla_prov  as istat_prov_iscrizione_ordine ");
		sql.append("from idf_t_soggetto sog ");
		sql.append("inner join idf_geo_pl_provincia prov on prov.istat_prov = istat_prov_iscrizione_ordine ");
		sql.append(" where id_soggetto in (select fk_soggetto from idf_cnf_config_utente) ");
		sql.append(" and n_iscrizione_ordine is not null ");
		sql.append(" order by cognome");
		logger.info("getProfessionistiList - sql: " + sql.toString());
		return DBUtil.jdbcTemplate.query(sql.toString(), soggettoMapper);
	}

	@Override
	public List<SoggettoResource> getGestoriList() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT nome, cognome, denominazione, id_soggetto, codice_fiscale, partita_iva, flg_ente_pubblico");
		sql.append(" FROM idf_t_soggetto its ");
		sql.append(" WHERE its.flg_gestore = 1  ");

		return DBUtil.jdbcTemplate.query(sql.toString(), soggettoResourceMapper);
	}


	@Override
	public List<SoggettoResource> getSportelloList() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT nome, cognome, denominazione, id_soggetto, codice_fiscale, partita_iva");
		sql.append(" FROM idf_t_soggetto ");
		sql.append(" WHERE flg_sportello = 1  ");
		sql.append(" ORDER BY cognome  ");

		return DBUtil.jdbcTemplate.query(sql.toString(), soggettoResourceMapper);
	}

	@Override
	public List<TSoggetto> findSportelliByIdConfigUtente(Integer idConfigUtente) throws RecordNotFoundException {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT * ");
		sql.append(" FROM idf_t_soggetto its ");
		sql.append(" JOIN idf_r_pf_pg irpp ON irpp.id_soggetto_pg = its.id_soggetto ");
		sql.append(" JOIN idf_cnf_config_utente iccu ON iccu.fk_soggetto = irpp.id_soggetto_pf ");
		sql.append(" WHERE iccu.id_config_utente = ? AND flg_sportello = 1");

		return DBUtil.jdbcTemplate.query(sql.toString(), soggettoMapper, idConfigUtente);
	}

	@Override
	public TSoggetto findSportelloByIdConfigUtente(Integer idConfigUtente) throws RecordNotFoundException {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT DISTINCT its.* ");
		sql.append(" FROM idf_t_soggetto its ");
		sql.append(" JOIN idf_r_pf_pg irpp ON irpp.id_soggetto_pg = its.id_soggetto ");
		sql.append(" JOIN idf_cnf_config_utente iccu ON iccu.fk_soggetto_sportello = irpp.id_soggetto_pg ");
		sql.append(" WHERE iccu.id_config_utente = ? AND flg_sportello = 1 ");

		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), soggettoMapper, idConfigUtente);
	}

	@Override
	public FatSoggetto findFatSoggettoByCodiceFiscale(String codiceFiscale) throws RecordNotUniqueException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT c.id_comune, c.denominazione_comune, c.istat_comune, c.istat_prov");
		sql.append(", s.id_soggetto, s.nome, s.cognome, s.codice_fiscale, s.partita_iva, s.denominazione");
		sql.append(", s.indirizzo, s.nr_telefonico, s.e_mail, s.pec, s.n_iscrizione_ordine");
		sql.append(", s.istat_prov_iscrizione_ordine, s.istat_prov_competenza_terr, s.flg_settore_regionale");
		sql.append(", s.data_inserimento, s.data_aggiornamento, s.fk_config_utente, s.civico, s.cap");
		sql.append(", s.flg_ente_pubblico, s.flg_sportello, s.flg_gestore, s.nr_martello_forestale , igpp.sigla_prov, igpp.denominazione_prov ");
		sql.append(" FROM idf_t_soggetto s");
		sql.append(" LEFT JOIN idf_geo_pl_comune c ON S.fk_comune = c.id_comune");
		sql.append(" LEFT JOIN idf_geo_pl_provincia igpp ON s.istat_prov_iscrizione_ordine = igpp.istat_prov");

		sql.append(" WHERE s.codice_fiscale= ? and nome is not null and nome <> '' and cognome is not null and cognome <> ''");

		List<FatSoggetto> soggettos = DBUtil.jdbcTemplate.query(sql.toString(), fatSoggettoMapper, codiceFiscale);
		if (soggettos.isEmpty()) {
			return null;
		} else if (soggettos.size() > 1) {
			throw new RecordNotUniqueException();
		} else {
			return soggettos.get(0);
		}
	}

	@Override
	public List<TSoggetto> getPersonaGiurdicaByOwnerCodiceFiscaleAndIsPubblic(String codiceFiscale, Boolean isPubblic) {
		StringBuilder sql = new StringBuilder();
		TSoggetto sogPF;
		List<Object> parameters = new ArrayList<>();
		try {
			sogPF = findSoggettoByCodiceFiscale(codiceFiscale);
			if (sogPF == null) {
				List<TSoggetto> emptyList = new ArrayList<TSoggetto>();
				return emptyList;
			}
			parameters.add(sogPF.getIdSoggetto());

			sql.append(" SELECT id_soggetto_pf  ,id_soggetto_pg as id_soggetto, codice_fiscale, partita_iva ");
			sql.append(" , nome, cognome, denominazione, fk_categoria_professionale, fk_comune,indirizzo,nr_telefonico ");
			sql.append(" , e_mail,pec,n_iscrizione_ordine,istat_prov_iscrizione_ordine,istat_prov_competenza_terr ");
			sql.append(" , flg_settore_regionale,civico,cap ,its.data_inserimento,its.fk_config_utente,its.data_aggiornamento ");
			sql.append(" FROM idf_r_pf_pg irpp  ");
			sql.append(" JOIN idf_t_soggetto its ON its.id_soggetto = irpp.id_soggetto_pg  ");
			sql.append(" WHERE irpp.id_soggetto_pf = ? ");
			if (null != isPubblic) {
				sql.append(" AND its.flg_ente_pubblico = ?");
				parameters.add(isPubblic ? 1 : 0);
			}
			sql.append(" ORDER BY partita_iva  ");

		} catch (RecordNotUniqueException e) {
			e.printStackTrace();
		}
		logger.debug("getPersonaGiurdicaByOwnerCodiceFiscaleAnd is public  sql: " +  sql.toString());
		List<TSoggetto> res = DBUtil.jdbcTemplate.query(sql.toString(), soggettoMapper, parameters.toArray());

		return res;
	}

	@Override
	public List<TSoggetto> getPersonaGiurdicaByOwnerCodiceFiscale(String codiceFiscale) {
		StringBuilder sql = new StringBuilder();
		TSoggetto sogPF;
		try {
			sogPF = findSoggettoByCodiceFiscale(codiceFiscale);
			if (sogPF == null) {
				List<TSoggetto> emptyList = new ArrayList<TSoggetto>();
				return emptyList;
			}
			sql.append("select id_soggetto_pf  ,id_soggetto_pg as id_soggetto, codice_fiscale, partita_iva, ");
			sql.append("nome, cognome, denominazione, fk_categoria_professionale, fk_comune,indirizzo,nr_telefonico ");
			sql.append(",e_mail,pec,n_iscrizione_ordine,istat_prov_iscrizione_ordine,istat_prov_competenza_terr ");
			sql.append(", flg_settore_regionale,civico,cap ,i.data_inserimento,i.fk_config_utente,i.data_aggiornamento ");
			sql.append(" from (select * from idf_r_pf_pg where id_soggetto_pf= ");
			sql.append(sogPF.getIdSoggetto());
			sql.append(") pfg left join idf_t_soggetto i on   pfg.id_soggetto_pg=i.id_soggetto  ORDER BY partita_iva ");

		} catch (RecordNotUniqueException e) {

			e.printStackTrace();
		}
		logger.debug("getPersonaGiurdicaByOwnerCodiceFiscale sql: " +  sql.toString());
		List<TSoggetto> res = DBUtil.jdbcTemplate.query(sql.toString(), soggettoMapper);

		return res;

	}

	@Override
	public TipoTitolaritaEnum getTitolaritaforMe() {
		TipoTitolaritaEnum tipo = TipoTitolaritaEnum.PF;
		return tipo;
	}

	@Override
	public TSoggetto getSoggettoPfByPg(int soggPgId) {

		StringBuilder sql = new StringBuilder();
		sql.append("select id_soggetto, fk_comune, nome, cognome, codice_fiscale, partita_iva, denominazione,");
		sql.append("indirizzo, nr_telefonico, fk_categoria_professionale,e_mail, pec, n_iscrizione_ordine,");
		sql.append(" istat_prov_iscrizione_ordine, istat_prov_competenza_terr, flg_settore_regionale, ");
		sql.append("data_inserimento, data_aggiornamento, fk_config_utente, civico, cap ");
		sql.append(" from (SELECT id_soggetto_pf  FROM idf_r_pf_pg where id_soggetto_pg= ? )p left join idf_t_soggetto i on i.id_soggetto=p.id_soggetto_pf;");
		List<TSoggetto> sog = DBUtil.jdbcTemplate.query(sql.toString(), soggettoMapper, soggPgId);
		if (sog.size() == 0) {
			return null;
		} else {
			return sog.get(0);
		}

	}

	@Override
	public TSoggetto getSoggettoPgByPf(int soggPfId) {

		StringBuilder sql = new StringBuilder();
		sql.append("select id_soggetto, fk_comune, nome, cognome, codice_fiscale, partita_iva, denominazione,");
		sql.append("indirizzo, nr_telefonico, fk_categoria_professionale,e_mail, pec, n_iscrizione_ordine,");
		sql.append(" istat_prov_iscrizione_ordine, istat_prov_competenza_terr, flg_settore_regionale, ");
		sql.append("data_inserimento, data_aggiornamento, fk_config_utente, civico, cap ");
		sql.append(" from (SELECT id_soggetto_pg  FROM idf_r_pf_pg where id_soggetto_pf= ? ) p left join idf_t_soggetto i on i.id_soggetto=p.id_soggetto_pg;");
		List<TSoggetto> sog = DBUtil.jdbcTemplate.query(sql.toString(), soggettoMapper, soggPfId);
		if (sog.size() == 0) {
			return null;
		} else {
			return sog.get(0);
		}

	}

	@Override
	public TSoggetto getLastInterventoByConfigUtente(ConfigUtente configUtente) {
		StringBuilder sql = new StringBuilder();
		sql.append("select i.id_soggetto, fk_comune, nome, cognome, codice_fiscale, partita_iva, denominazione, ");
		sql.append("indirizzo, nr_telefonico, fk_categoria_professionale, e_mail, pec, n_iscrizione_ordine, ");
		sql.append("istat_prov_iscrizione_ordine, istat_prov_competenza_terr, flg_settore_regionale, ");
		sql.append("data_inserimento, data_aggiornamento, i.fk_config_utente, civico, cap ");
		sql.append(" FROM (SELECT data_inizio_validita,fk_config_utente,id_soggetto,ID_INTERVENTO ");
		sql.append(" FROM idf_r_soggetto_intervento WHERE fk_config_utente= ?  and id_soggetto <> ? ");
		sql.append(" ORDER BY data_inizio_validita DESC,ID_INTERVENTO DESC limit 1 ) p ");
		sql.append(" LEFT JOIN idf_t_soggetto i ON i.id_soggetto=p.id_soggetto  ");
		sql.append(" ORDER BY data_inizio_validita DESC,p.ID_INTERVENTO DESC; ");
		List<TSoggetto> sog = DBUtil.jdbcTemplate.query(sql.toString(), soggettoMapper,
				configUtente.getIdConfigUtente(), configUtente.getFkSoggetto());
		if (sog.size() == 0) {
			return null;
		} else {
			return sog.get(0);
		}

	}

	@Override
	public List<FatSoggetto> findAllFatProfess() {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT s.id_soggetto, s.nome, c.id_comune, c.denominazione_comune, c.istat_comune, c.istat_prov, ");
		sql.append(
				" s.cognome, s.codice_fiscale, s.partita_iva, s.denominazione, s.id_soggetto, s.indirizzo, s.nr_telefonico,");
		sql.append(
				" s.e_mail, s.pec, s.n_iscrizione_ordine, s.istat_prov_iscrizione_ordine, s.istat_prov_competenza_terr,");
		sql.append(
				" s.flg_settore_regionale, s.data_inserimento, s.data_aggiornamento, s.fk_config_utente, s.civico,s.cap ");
		sql.append(" FROM idf_t_soggetto s left JOIN idf_geo_pl_comune c ON s.fk_comune = c.id_comune ");
		sql.append(" WHERE s.n_iscrizione_ordine is not null ");
				//" WHERE fk_config_utente IN( SELECT distinct id_config_utente FROM idf_cnf_delega   ) ORDER BY id_soggetto ");

		List<FatSoggetto> soggettos = DBUtil.jdbcTemplate.query(sql.toString(), new FatBaseInfoSoggettoMapper());
		if (soggettos.isEmpty()) {
			return null;
		} else {
			return soggettos;
		}
	}

	@Override
	public SoggettoResource getSoggettoByIdInterventoAndTipoSoggetto(Integer idIntervento, Integer idTipoSoggetto) {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT nome, cognome, denominazione, id_soggetto, codice_fiscale, partita_iva");
		sql.append(" FROM idf_t_soggetto its ");
		sql.append(" JOIN idf_r_soggetto_intervento irsi using(id_soggetto)");
		sql.append(" WHERE id_intervento = ? and id_tipo_soggetto =?");

		return DataAccessUtils.singleResult(
				DBUtil.jdbcTemplate.query(sql.toString(), soggettoResourceMapper, idIntervento, idTipoSoggetto));
		// return
		// DBUtil.jdbcTemplate.queryForObject(sql.toString(),soggettoResourceMapper,
		// idIntervento, idTipoSoggetto );
	}

	@Override
	public List<SoggettoResource> getAllTecnicoForestale() {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT nome, cognome, denominazione, id_soggetto, codice_fiscale, partita_iva");
		sql.append(" FROM idf_t_soggetto ");
		sql.append(" JOIN idf_r_soggetto_tipo_soggetto using(id_soggetto)");
		sql.append(" WHERE id_tipo_soggetto =3 and flg_visibile = 1 order by cognome, denominazione ");

		List<SoggettoResource> list = DBUtil.jdbcTemplate.query(sql.toString(), soggettoResourceMapper);
		if (ListUtil.isEmpty(list)) {
			return null;
		}
		return list;
	}


	@Override
	public PersonaFisGiu getTecnicoForestaleByIdIntervento(Integer idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ts.*, itv.*, c.*, igpp.sigla_prov, igpp.denominazione_prov ");
		sql.append(" FROM idf_t_soggetto ts ");
		sql.append(" JOIN idf_t_intervento itv ON itv.fk_soggetto_profess = ts.id_soggetto  ");
		sql.append(" LEFT JOIN idf_geo_pl_comune c ON ts.fk_comune = c.id_comune");
		sql.append(" LEFT JOIN idf_geo_pl_provincia igpp ON ts.istat_prov_iscrizione_ordine = igpp.istat_prov");
		sql.append(" WHERE itv.id_intervento = :id_intervento ");

		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id_intervento", idIntervento);

		PersonaFisGiu tecnico = null;
		try {
			tecnico = DBUtil.namedJdbcTemplate.queryForObject(
					sql.toString(),
					namedParameters,
					new PersonaFisicaoGiuridicaMapper());
		} catch (DataAccessException e) {
			logger.info("Tecnico forestale non trovato");
		}

		return tecnico;
	}

	@Override
	public List<PersonaFisGiu> searchPfByCForName(String searchParam) {
		if (StringUtils.isBlank(searchParam)) {
			return new ArrayList<>();
		}
		StringJoiner search = new StringJoiner("", "%", "%");
		search.add(searchParam);
		System.out.println("search: ------ " +search.toString());
		//System.out.println("entePubblico: ------ " +entePubblico);
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("search", search.toString());

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT c.id_comune, c.denominazione_comune, c.istat_comune, c.istat_prov");
		sql.append(", s.id_soggetto, s.nome, s.cognome, s.codice_fiscale, s.partita_iva, s.denominazione");
		sql.append(", s.id_soggetto, s.indirizzo, s.nr_telefonico, s.e_mail, s.pec, s.n_iscrizione_ordine");
		sql.append(", s.istat_prov_iscrizione_ordine, s.istat_prov_competenza_terr, s.flg_settore_regionale");
		sql.append(", s.data_inserimento, s.data_aggiornamento, s.fk_config_utente, s.civico, s.cap");
		sql.append(", s.flg_ente_pubblico, s.flg_sportello, s.flg_gestore, s.nr_martello_forestale, igpp.sigla_prov, igpp.denominazione_prov ");
		sql.append(" FROM idf_t_soggetto s");
		sql.append(" LEFT JOIN idf_geo_pl_comune c ON S.fk_comune = c.id_comune");
		sql.append(" LEFT JOIN idf_geo_pl_provincia igpp ON s.istat_prov_iscrizione_ordine = igpp.istat_prov");		
		sql.append(" WHERE ");
		sql.append(" (LOWER(s.codice_fiscale) LIKE LOWER(:search) OR");
		sql.append(" LOWER(s.nome) LIKE LOWER(:search) OR");
		sql.append(" LOWER(s.cognome) LIKE LOWER(:search) )");
		sql.append(" AND (s.denominazione is null or s.denominazione = '') ");

		logger.info("search PF  sql: " + sql);
		 System.out.println("namedParameters: ------ " +namedParameters.toString());
		return DBUtil.namedJdbcTemplate.query(sql.toString(), namedParameters, new PersonaFisicaoGiuridicaMapper());
	}

	@Override
	public List<PersonaFisGiu> searchPgByCForDenom(String searchParam, Boolean entePubblico) {
		if (StringUtils.isBlank(searchParam)) {
			return new ArrayList<>();
		}
		StringJoiner search = new StringJoiner("", "%", "%");
		search.add(searchParam);
		
		
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("search", search.toString())
				.addValue("entePubblico", (entePubblico ? 1 : 0)
				);

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT c.id_comune, c.denominazione_comune, c.istat_comune, c.istat_prov");
		sql.append(", s.id_soggetto, s.nome, s.cognome, s.codice_fiscale, s.partita_iva, s.denominazione");
		sql.append(", s.id_soggetto, s.indirizzo, s.nr_telefonico, s.e_mail, s.pec, s.n_iscrizione_ordine");
		sql.append(", s.istat_prov_iscrizione_ordine, s.istat_prov_competenza_terr, s.flg_settore_regionale");
		sql.append(", s.data_inserimento, s.data_aggiornamento, s.fk_config_utente, s.civico, s.cap");
		sql.append(", s.flg_ente_pubblico, s.flg_sportello, s.flg_gestore");
		sql.append(" FROM idf_t_soggetto s");
		sql.append(" LEFT JOIN idf_geo_pl_comune c ON S.fk_comune = c.id_comune");
		sql.append(" WHERE ");
		sql.append(" (LOWER(s.denominazione) LIKE LOWER(:search) OR");
		sql.append(" LOWER(s.partita_iva) LIKE LOWER(:search) OR");
		sql.append(" LOWER(s.codice_fiscale) LIKE LOWER(:search) )");
		sql.append(" AND (s.nome is null or s.nome = '') ");
		sql.append(" AND (s.cognome is null or s.cognome = '') and flg_ente_pubblico = :entePubblico");

		logger.info("search PF  sql: " + sql);
	   System.out.println("namedParameters: ------ " +namedParameters.toString());
		return DBUtil.namedJdbcTemplate.query(sql.toString(), namedParameters, new PersonaFisicaoGiuridicaMapper());
	}

	@Override
	public void insertIntoSoggettoTipoSoggetto(Integer idSoggetto, Integer idTipoSoggetto) {
		// su richiesta si Roberto, questo applicativo non deve scrivere su questa tabella
//		String sql = "insert into idf_r_soggetto_tipo_soggetto (id_soggetto ,id_tipo_soggetto,flg_visibile) values(?,?,?) ";
//
//		List<Object> parameters = new ArrayList<>();
//		parameters.add(idSoggetto);
//		parameters.add(idTipoSoggetto);
//		parameters.add(1);
//
//		DBUtil.jdbcTemplate.update(sql, parameters.toArray());

	}

	public boolean checkIfSoggettoExistsInIntermediaryTable(Integer idSoggetto, Integer idTipoSoggetto) {
		String sql = "SELECT COUNT(*) FROM idf_r_soggetto_tipo_soggetto WHERE id_soggetto = ? AND id_tipo_soggetto = ?";
		return DBUtil.jdbcTemplate.queryForObject(sql, Integer.class, idSoggetto, idTipoSoggetto) == 1;
	}

	@Override
	public List<TSoggetto> mapAziendaToSoggetto(ListaAttEconProd[] aziendaList) {
		List<TSoggetto> soggettoList = new ArrayList<TSoggetto>();
		for (ListaAttEconProd rs : aziendaList) {
			TSoggetto soggetto = new TSoggetto();
			// TODO mapper azienda to soggetto
			soggetto.setCodiceFiscale(rs.getCodiceFiscale());
			soggetto.setIdSoggetto(null);

			soggettoList.add(soggetto);

		}
		return soggettoList;
	}

	@Override
	public TSoggetto findSoggettoCompetenzaIstanza(Integer idIstanza){
		String sql = "select * from idf_t_soggetto where istat_prov_competenza_terr in("
				+ "select risultato1.istat_prov "
				+ "FROM (select ROW_NUMBER() OVER(ORDER BY risultato.area DESC) AS Row, "
				+ "risultato.istat_prov,risultato.area "
				+ "FROM ( select provincia.istat_prov, SUM(ST_AREA(ST_Intersection(provincia.geometria, "
				+ "(select ST_UNION (geomTrasf) as geomTrasfUnion "
				+ "FROM ( SELECT geoTrasf.geometria as geomTrasf "
				+ "FROM idf_geo_pl_interv_trasf geoTrasf WHERE geoTrasf.fk_intervento = ? ) as sbqry ))))"
				+ " as area from idf_geo_pl_provincia provincia where ST_Intersects ( ("
				+ "SELECT ST_UNION (geomTrasf) as geomTrasfUnion "
				+ "FROM (SELECT geoTrasf.geometria as geomTrasf FROM idf_geo_pl_interv_trasf geoTrasf "
				+ "WHERE geoTrasf.fk_intervento = ?) as sbqry), provincia.geometria) "
				+ "GROUP by provincia.istat_prov) as risultato) as risultato1 WHERE risultato1.row = 1)";
		logger.debug("findSoggettoByCodiceFiscale sql: " + sql + " - idIstanza: " + idIstanza);
		List<TSoggetto> soggetti = DBUtil.jdbcTemplate.query(sql, soggettoMapper, idIstanza,idIstanza);
		if (soggetti.isEmpty()) {
			return null;
		} else {
			return soggetti.get(0);
		}
	}

	@Override
	public List<String> getListCodicifiscaliCittadini() {
		StringBuilder sql = new StringBuilder("select distinct codice_fiscale from idf_t_soggetto its ");
		sql.append("left join idf_cnf_config_utente cnf on cnf.fk_soggetto = its.id_soggetto ");
		sql.append("where (cnf.fk_profilo_utente = 1 or cnf.fk_profilo_utente is null) ");
		sql.append("order by codice_fiscale");
		
		return DBUtil.jdbcTemplate.queryForList(sql.toString(), String.class);
	}

	@Override
	public String getRichiedentiInterventoPfa(Integer idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("select string_agg(DISTINCT  sog.denominazione ,', ' order by sog.denominazione ) as denominazione ");
		sql.append("from idf_r_pfa_pg irpp ");
		sql.append("inner join idf_t_interv_selvicolturale selv on selv.idgeo_pl_pfa = irpp.idgeo_pl_pfa  ");
		sql.append("inner join idf_t_soggetto sog on sog.id_soggetto = irpp.id_soggetto_coinvolto ");
		sql.append("where irpp.id_tipo_soggetto = 5 and selv.id_intervento = ? ");
		sql.append("group by irpp.idgeo_pl_pfa  ");

		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), new Object[] { idIntervento }, String.class);
	}


	@Override
	public List<String> findEmailFromAreaProtettaByIdIntervento(Integer idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct its.e_mail  ");
		sql.append("from idf_t_soggetto its  ");
		sql.append("join idf_t_egap_aapp itea on itea.id_soggetto = its.id_soggetto  ");
		sql.append("join idf_r_intervento_aapp iria on iria.codice_aapp = itea.codice_amministrativo  ");
		sql.append("where id_intervento =  ? ");

		return DBUtil.jdbcTemplate.queryForList(sql.toString(), new Object[]{idIntervento} , String.class);
	}

	@Override
	public List<String> findEmailFromAreaProtetta2000ByIdIntervento(Integer idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct its.e_mail  ");
		sql.append("from idf_t_soggetto its  ");
		sql.append("join idf_t_egap_aapp itea on itea.id_soggetto = its.id_soggetto  ");
		sql.append("join idf_r_intervento_rn_2000 irir on irir.codice_rn_2000 = itea.codice_amministrativo   ");
		sql.append("where id_intervento = ? ");

		return DBUtil.jdbcTemplate.queryForList(sql.toString(), new Object[]{idIntervento} , String.class);
	}
}
