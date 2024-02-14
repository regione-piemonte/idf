/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.impl.dao.ComuneDAO;
import it.csi.idf.idfapi.dto.Comune;
import it.csi.idf.idfapi.dto.ComuneResource;
import it.csi.idf.idfapi.mapper.ComuneMapper;
import it.csi.idf.idfapi.mapper.ComuneResourceMapper;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.ListUtil;
import org.apache.log4j.Logger;

@Component
public class ComuneDAOImpl implements ComuneDAO {
	
	public static Logger logger = Logger.getLogger(ComuneDAOImpl.class);
	
	private final ComuneMapper comuneMapper = new ComuneMapper();
	private final ComuneResourceMapper comuneResourceMapper = new ComuneResourceMapper();
	private static final String ORDER_BY_DENOMINAZIONE_COMUNE = " ORDER BY denominazione_comune";

	@Override
	public List<ComuneResource> findAllComune() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id_comune, istat_comune, istat_prov, denominazione_comune, codice_belfiore FROM idf_geo_pl_comune");
		sql.append(ORDER_BY_DENOMINAZIONE_COMUNE);


		return DBUtil.jdbcTemplate.query(sql.toString(), comuneResourceMapper);
	}

	@Override
	public List<ComuneResource> findComuneByNome(String comune) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id_comune, istat_comune, istat_prov, denominazione_comune, codice_belfiore FROM idf_geo_pl_comune WHERE LOWER(denominazione_comune) LIKE CONCAT('%', ?, '%')");
		sql.append(ORDER_BY_DENOMINAZIONE_COMUNE);

		return DBUtil.jdbcTemplate.query(sql.toString(), comuneResourceMapper, comune.toLowerCase());
	}

	@Override
	public Comune findComuneByID(Integer idComune) {
		//TODO: Check if this needs to be used in IDFINV, IDFPFA
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM idf_geo_pl_comune");
		sql.append(" WHERE id_comune = ?");
		sql.append(ORDER_BY_DENOMINAZIONE_COMUNE);

		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), comuneMapper, idComune);
	}
	
	@Override
	public Comune findComuneByName(String name) throws RecordNotUniqueException {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM idf_geo_pl_comune");
		sql.append(" WHERE UPPER(denominazione_comune) = ?");
		
		List<Comune> comunes = DBUtil.jdbcTemplate.query(sql.toString(), comuneMapper, name.toUpperCase());
		
		if(comunes.size() > 1) {
			throw new RecordNotUniqueException();
		} else if(comunes.isEmpty()) {
			return null;
		} else {
			return comunes.get(0);
		}
	}
	
	@Override
	public int createComune(Comune comune) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO idf_geo_pl_comune (");
		sql.append("id_comune, istat_comune, istat_prov, denominazione_comune, fk_area_forestale");
		sql.append(", codice_belfiore, data_inizio_validita, data_fine_validita");
		sql.append(") VALUES (?,?,?,?,?,?,?,?)");
		
		List<Object>parameters= new ArrayList<>();
		parameters.add(comune.getIdComune());
		parameters.add(comune.getIstatComune());
		parameters.add(comune.getIstatProv());
		parameters.add(comune.getDenominazioneComune());
		parameters.add(comune.getFk_area_forestale());
		parameters.add(comune.getCodiceBelfiore());
		parameters.add(comune.getDataInizioValidita() == null ? null : Date.valueOf(comune.getDataInizioValidita()));
		parameters.add(comune.getDataFineValidita() == null ? null : Date.valueOf(comune.getDataFineValidita()));
		
		return	DBUtil.jdbcTemplate.update(sql.toString(),parameters.toArray());
	}

	@Override
	public ComuneResource findComuneResourceByID(Integer idComune) throws RecordNotUniqueException {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id_comune, istat_comune, istat_prov, denominazione_comune, codice_belfiore");
		sql.append(" FROM idf_geo_pl_comune");
		sql.append(" WHERE id_comune = ?");
		sql.append(ORDER_BY_DENOMINAZIONE_COMUNE);
		
		List<ComuneResource> comunes = DBUtil.jdbcTemplate.query(sql.toString(), comuneResourceMapper, idComune);
		
		if(comunes.size() > 1) {
			throw new RecordNotUniqueException();
		} else if(ListUtil.isEmpty(comunes)) {
			return null;
		} else {
			return comunes.get(0);
		}
	}
	
	@Override
	public List<ComuneResource> findAllComuneByProvincia(String istatProv) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id_comune, istat_comune, istat_prov, denominazione_comune, codice_belfiore");
		sql.append(" FROM idf_geo_pl_comune");
		sql.append(" WHERE istat_prov = ?");
		sql.append(ORDER_BY_DENOMINAZIONE_COMUNE);
		logger.info("QUERY: "+ sql);
		return DBUtil.jdbcTemplate.query(sql.toString(), comuneResourceMapper, istatProv);
	}

	@Override
	public ComuneResource findComuneResourceByIstatComune(String istatComune) throws RecordNotUniqueException {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id_comune, istat_comune, istat_prov, denominazione_comune, codice_belfiore");
		sql.append(" FROM idf_geo_pl_comune");
		sql.append(" WHERE istat_comune = ?");
		sql.append(ORDER_BY_DENOMINAZIONE_COMUNE);
		
		List<ComuneResource> comunes = DBUtil.jdbcTemplate.query(sql.toString(), comuneResourceMapper, istatComune);
		
		if(comunes.size() > 1) {
			throw new RecordNotUniqueException();
		} else if(comunes.isEmpty()) {
			return null;
		} else {
			return comunes.get(0);
		}
	}

	@Override
	public List<ComuneResource> findAllComuneByPfa(Integer idGeoPlPfa) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id_comune, istat_comune, istat_prov, denominazione_comune, codice_belfiore");
		sql.append(" FROM idf_r_comune_pfa");
		sql.append(" JOIN idf_geo_pl_comune using(id_comune)");
		sql.append(" WHERE idgeo_pl_pfa = ?");
		sql.append(ORDER_BY_DENOMINAZIONE_COMUNE);
		
		List<ComuneResource> comunes =  DBUtil.jdbcTemplate.query(sql.toString(), comuneResourceMapper, idGeoPlPfa);
		return comunes;
		
	}

	@Override
	public List<ComuneResource> findAllComuneByIntervento(Integer idIntervento) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("select distinct id_comune, istat_comune, istat_prov, denominazione_comune, codice_belfiore ");
		sql.append("from( ");
		sql.append("select id_comune, istat_comune, istat_prov, denominazione_comune, codice_belfiore ");
		sql.append("from idf_geo_pl_lotto_intervento lotto ");
		sql.append("inner join idf_geo_pl_comune com on ST_INTERSECTS(com.geometria, lotto.geometria) ");
		sql.append("WHERE lotto.fk_intervento = ? ");
		sql.append("union all  ");
		sql.append("select id_comune, istat_comune, istat_prov, denominazione_comune, codice_belfiore  ");
		sql.append("from idf_geo_ln_lotto_intervento lotto  ");
		sql.append("inner join idf_geo_pl_comune com on ST_INTERSECTS(com.geometria, lotto.geometria) ");
		sql.append("WHERE lotto.id_intervento = ? ");
		sql.append("union all ");
		sql.append("select id_comune, istat_comune, istat_prov, denominazione_comune, codice_belfiore  ");
		sql.append("from idf_geo_pt_lotto_intervento lotto  ");
		sql.append("inner join idf_geo_pl_comune com on ST_INTERSECTS(com.geometria, lotto.geometria) ");
		sql.append("WHERE lotto.id_intervento = ? ");
		sql.append(") as com order by denominazione_comune ");
		Object[] params = new Object[] {idIntervento, idIntervento, idIntervento};
		List<ComuneResource> comunes =  DBUtil.jdbcTemplate.query(sql.toString(), comuneResourceMapper, params);
		if (comunes.isEmpty()) {
			return null;
		}
		else 
			return comunes;
	}

	@Override
	public List<ComuneResource> findComuneBoEnabled(int idSoggetto, String istatProv) {
		String sql = "select id_comune, istat_comune, istat_prov , denominazione_comune ,fk_area_forestale," + 
				"codice_belfiore ,null as geometria, " + 
				"data_inizio_validita ,data_fine_validita  " + 
				"from idf_geo_pl_comune igpc where istat_prov = ? " + 
				" and istat_comune in ( " + 
				"select pg.istat_comune_competenza_terr from IDF_R_PF_PG pfpg "+ 
				"inner join idf_t_soggetto pg on pfpg.id_soggetto_pg = pg.id_soggetto  " + 
				"where pfpg.id_soggetto_pf = ?) " + 
				"order by denominazione_comune ";
		List<ComuneResource> comuni =  DBUtil.jdbcTemplate.query(sql, comuneResourceMapper,istatProv, idSoggetto);
		if (comuni.isEmpty()) {
			return null;
		}
		else 
			return comuni;
	}

	
	@Override
	public ComuneResource findComuneResourceByCodiceBelfiore(String codiceBelfiore) throws RecordNotUniqueException {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id_comune, istat_comune, istat_prov, denominazione_comune, codice_belfiore");
		sql.append(" FROM idf_geo_pl_comune");
		sql.append(" WHERE codice_belfiore = ?");
		sql.append(ORDER_BY_DENOMINAZIONE_COMUNE);

		List<ComuneResource> comunes = DBUtil.jdbcTemplate.query(sql.toString(), comuneResourceMapper, codiceBelfiore);
		
		if(comunes.size() > 1) {
			throw new RecordNotUniqueException();
		} else if(comunes.isEmpty()) {
			return null;
		} else {
			return comunes.get(0);
		}
	}

}
