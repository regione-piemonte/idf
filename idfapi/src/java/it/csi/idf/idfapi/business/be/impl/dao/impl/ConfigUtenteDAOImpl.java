/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.ConfigUtenteDAO;
import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.mapper.ConfigUtenteExtendMapper;
import it.csi.idf.idfapi.mapper.ConfigUtenteMapper;
import it.csi.idf.idfapi.util.AIKeyHolderUtil;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.ListUtil;
import it.csi.idf.idfapi.util.ProceduraEnum;
import it.csi.idf.idfapi.util.ProfiloUtenteEnum;

@Component
public class ConfigUtenteDAOImpl implements ConfigUtenteDAO {
	
	static final Logger logger = Logger.getLogger(ConfigUtenteDAOImpl.class);

	@Override
	public ConfigUtente getCofigUtenteById(int id) {
		String sql = "SELECT * FROM idf_cnf_config_utente cu WHERE cu.id_config_utente = ? ";
		
		List<ConfigUtente> cnft = DBUtil.jdbcTemplate.query(sql, new ConfigUtenteMapper(), id);
		if (ListUtil.isEmpty(cnft)) {
			return null;
		} else {
			return cnft.get(0);
		}
	}
	
	@Override
	public ConfigUtente getCofigUtenteBySoggettoId(int id) {
		String sql = "SELECT * FROM idf_cnf_config_utente cu WHERE cu.fk_soggetto = ?";
		List<ConfigUtente> cnft = DBUtil.jdbcTemplate.query(sql, new ConfigUtenteMapper(), id);
		if (ListUtil.isEmpty(cnft)) {
			return null;
		} else {
			return cnft.get(0);
		}
	}
	
	@Override
	public ConfigUtente getCofigUtenteBySoggettoIdAndApplicationType(int id,ProceduraEnum proceduraType) { 
		String sql = "SELECT * FROM idf_cnf_config_utente cu "
				+ " inner join idf_cnf_profilo_utente pu on cu.fk_profilo_utente = pu.id_profilo_utente "  
				+ " WHERE cu.fk_soggetto = ? and pu.fk_procedura = ? order by id_profilo_utente desc";
		logger.info("getCofigUtenteBySoggettoIdAndApplicationType sql; " + sql 
				+ " - id_soggetto: " + id + " - fk_procedura: " + proceduraType.getValue());
		
		List<ConfigUtente> cnft = DBUtil.jdbcTemplate.query(sql, new ConfigUtenteExtendMapper(), id,proceduraType.getValue());
		if (ListUtil.isEmpty(cnft)) {
			return getCofigUtenteBySoggettoId(id);
		} else {
			return cnft.get(0);
		}
	}

	@Override
	public ConfigUtente getCofigUtenteBySoggettoIdProfiloAndTipoAccreditamento(Integer id,Integer prof, Integer accred) {
		String sql = "SELECT * FROM idf_cnf_config_utente cu WHERE cu.fk_soggetto = ? AND cu.fk_profilo_utente=?   AND cu.fk_tipo_accreditamento = ? ";
		List<ConfigUtente> cnft = DBUtil.jdbcTemplate.query(sql, new ConfigUtenteMapper(), id,prof,accred);
		if (ListUtil.isEmpty(cnft)) {
			return null;
		} else {
			return cnft.get(0);
		}
	}

	@Override
	public int createConfigUtente(ConfigUtente configUtente) {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO idf_cnf_config_utente(");
		sb.append(
				"fk_profilo_utente, fk_tipo_accreditamento, nr_accessi, data_primo_accesso, data_ultimo_accesso, flg_privacy, fk_soggetto, fk_soggetto_sportello)");
		sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?,?)");

		List<Object> parameters = new ArrayList<>();
		parameters.add(configUtente.getFkProfiloUtente());
		parameters.add(configUtente.getFkTipoAccreditamento());
		parameters.add(configUtente.getNrAccessi());
		parameters.add(configUtente.getDataPrimoAccesso());
		parameters.add(configUtente.getDataUltimoAccesso());
		parameters.add(configUtente.getFlgPrivacy());
		parameters.add(configUtente.getFkSoggetto());
		parameters.add(configUtente.getFkSoggettoSportello());

		return AIKeyHolderUtil.updateWithGenKey("id_config_utente", sb.toString(), parameters);
	}

	@Override
	public void updateConfigUtente(ConfigUtente configUtente) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE idf_cnf_config_utente SET");
		sql.append(" fk_profilo_utente = ?, fk_tipo_accreditamento = ?, nr_accessi = ?");
		sql.append(", data_primo_accesso = ?, data_ultimo_accesso = ?, flg_privacy = ?, fk_soggetto = ?, fk_soggetto_sportello = ?");
		sql.append(" where id_config_utente = ?");

		List<Object> parameters = new ArrayList<>();
		parameters.add(configUtente.getFkProfiloUtente());
		parameters.add(configUtente.getFkTipoAccreditamento());
		parameters.add(configUtente.getNrAccessi());
		parameters.add(configUtente.getDataPrimoAccesso());
		parameters.add(configUtente.getDataUltimoAccesso());
		parameters.add(configUtente.getFlgPrivacy());
		parameters.add(configUtente.getFkSoggetto());
		parameters.add(configUtente.getFkSoggettoSportello());
		parameters.add(configUtente.getIdConfigUtente());

		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}

	@Override
	public List<ConfigUtente> getCofigUtenteBySoggettoIdAnProfilo(int id,int profilo) {
		String sql = "SELECT * FROM idf_cnf_config_utente cu WHERE cu.fk_soggetto = ? AND cu.fk_profilo_utente=? ";
		List<ConfigUtente> cnft = DBUtil.jdbcTemplate.query(sql, new ConfigUtenteMapper(), id,profilo);
		if (ListUtil.isEmpty(cnft)) {
			return null;
		} else {
			return cnft;
		}
	}
	@Override
	public List<ConfigUtente> getCofUtenteBySoggIdAnProfOrderByDate(int id,int profilo) {
		String sql = "SELECT * FROM idf_cnf_config_utente cu WHERE cu.fk_soggetto = ? AND cu.fk_profilo_utente=? ORDER BY cu.data_ultimo_accesso desc ";
		List<ConfigUtente> cnft = DBUtil.jdbcTemplate.query(sql, new ConfigUtenteMapper(), id,profilo);
		
		if (ListUtil.isEmpty(cnft)) {
			return null;
		} else {
			return cnft;
		}
	}
	
	@Override
	public ConfigUtente getConfigUtenteIstafor(int fkUtente) {
		String sql = "SELECT * FROM idf_cnf_config_utente cu WHERE cu.fk_soggetto = ? "
				+ "and fk_profilo_utente >= 1 and fk_profilo_utente <=5 ";
				//+ "order by fk_profilo_utente desc ";
		
		List<ConfigUtente> cnft = DBUtil.jdbcTemplate.query(sql, new ConfigUtenteMapper(), fkUtente);
		if (ListUtil.isEmpty(cnft)) {
			return null;
		} else {
			return cnft.get(0);
		}
	}

	@Override
	public boolean isUserEnabledEditIstanza(ConfigUtente configUtente, int idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) from idf_t_istanza_forest itif ");
		sql.append("inner join idf_cnf_configutente_ambitoist amb on amb.id_ambito_istanza = itif.fk_tipo_istanza ");
		sql.append("inner join IDF_R_PROPCATASTO_INTERVENTO pc on pc.id_intervento = itif.id_istanza_intervento ");
		sql.append("inner join IDF_GEO_PL_PROP_CATASTO pl on pl.idgeo_pl_prop_catasto = pc.idgeo_pl_prop_catasto ");
		sql.append("where amb.id_config_utente = ? and pc.id_intervento = ? ");
		if(configUtente.getFkProfiloUtente() == ProfiloUtenteEnum.UFFICIO_TERRITORIALE.getValue()) {
			sql.append("and pl.FK_COMUNE IN ( select id_comune from idf_geo_pl_comune igpc "); 
			sql.append("where istat_prov in (select pg.istat_prov_competenza_terr ");
			sql.append("from IDF_R_PF_PG pfpg inner join idf_t_soggetto pg on pfpg.id_soggetto_pg = pg.id_soggetto ");  
			sql.append("where pfpg.id_soggetto_pf = ?)) ");
		}else if(configUtente.getFkProfiloUtente() == ProfiloUtenteEnum.COMUNE.getValue()) {				
			sql.append("and pl.FK_COMUNE IN ( select id_comune from idf_geo_pl_comune igpc "); 
			sql.append("where istat_comune in (select pg.istat_comune_competenza_terr "); 
			sql.append("from IDF_R_PF_PG pfpg inner join idf_t_soggetto pg on pfpg.id_soggetto_pg = pg.id_soggetto ");  
			sql.append("where pfpg.id_soggetto_pf = ?)) ");
		}
		logger.info("sql: " + sql.toString() + " -- " + configUtente.getIdConfigUtente() + 
				"- " + idIntervento + "- " + configUtente.getFkSoggetto());
		Object[] params = new Object[] {configUtente.getIdConfigUtente(), idIntervento, configUtente.getFkSoggetto()};
		return DBUtil.jdbcTemplate.queryForInt(sql.toString(), params) > 0;
	}
}
