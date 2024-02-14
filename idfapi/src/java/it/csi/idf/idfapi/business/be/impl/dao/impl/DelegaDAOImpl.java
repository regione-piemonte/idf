/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.impl.dao.DelegaDAO;
import it.csi.idf.idfapi.dto.Delega;
import it.csi.idf.idfapi.mapper.CategoriaForestaleMapper;
import it.csi.idf.idfapi.mapper.DelegaMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class DelegaDAOImpl implements DelegaDAO {
	
	private final DelegaMapper delegaMapper = new DelegaMapper();
	
	@Override
	public Delega getByCfDeleganteAndConfigUtente(String cfDelegante, Integer fkConfigUtente) throws RecordNotUniqueException {
		String sql = "SELECT * FROM idf_cnf_delega WHERE cf_delegante = ? AND id_config_utente = ?";
		List<Delega> delegas = DBUtil.jdbcTemplate.query(sql, delegaMapper,cfDelegante.toUpperCase(), fkConfigUtente);
		
		if(delegas.isEmpty()) {
			return null;
		} else if(delegas.size() > 1) {
			throw new RecordNotUniqueException();
		} else {
			return delegas.get(0);
		}
	}

	@Override
	public List<Delega> getAllDelegaByIdConfigUtente(Integer fkConfigUtente) {
		String sql = "SELECT * FROM idf_cnf_delega WHERE id_config_utente = ?";
		return DBUtil.jdbcTemplate.query(sql, delegaMapper, fkConfigUtente);


	}
	
	@Override
	public Delega getByUidIndex(String uidIndex) throws RecordNotUniqueException {
		String sql = "SELECT * FROM idf_cnf_delega WHERE uid_index_delega = ? ";
		return DBUtil.jdbcTemplate.queryForObject(sql, delegaMapper,uidIndex);
	}

	@Override
	public int createDelega(Delega delega) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO idf_cnf_delega(");
		sql.append("cf_delegante, id_config_utente, data_inizio, data_fine, uid_index_delega, nome_file_delega)");
		sql.append(" VALUES(?, ?, ?, ?, ?, ?)");
		
		Timestamp now = new Timestamp(System.currentTimeMillis());
		List<Object> params = new ArrayList<>();
		params.add(delega.getCfDelegante().toUpperCase());
		params.add(delega.getIdConfigUtente());
		params.add(now);
		params.add(delega.getDataFine() == null ? null : Date.valueOf(delega.getDataFine()));
		params.add(delega.getUidIndex());
		params.add(delega.getFileName());

		return DBUtil.jdbcTemplate.update(sql.toString(), params.toArray());
	}

	@Override
	public List<Delega> getMieiDelegati(Integer fkConfigUtente) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM idf_cnf_delega");
		sql.append(" WHERE id_config_utente = ?");
		sql.append(" AND (data_fine IS NULL or data_fine > now()) order by cf_delegante ");
		return DBUtil.jdbcTemplate.query(sql.toString(), delegaMapper, fkConfigUtente);
	}
	
	@Override
	public Delega getIfActiveByCfDeleganteAndConfigUtente(String cfDelegante, Integer fkConfigUtente) throws RecordNotUniqueException {
		String sql = "SELECT * FROM idf_cnf_delega WHERE cf_delegante = ? AND id_config_utente = ?  AND data_fine IS NULL";
		List<Delega> delegas = DBUtil.jdbcTemplate.query(sql, delegaMapper,cfDelegante, fkConfigUtente);
		
		if(delegas.isEmpty()) {
			return null;
		} else if(delegas.size() > 1) {
			throw new RecordNotUniqueException();
		} else {
			return delegas.get(0);
		}
	}
	@Override
	public int disabilita(String cfDelegante, Integer fkConfigUtente) {
        StringBuilder sql = new StringBuilder();
		sql.append("update idf_cnf_delega ");
		sql.append("SET data_fine=? ");
		sql.append("where cf_delegante = ? and  id_config_utente = ? ");
		
		Timestamp now = new Timestamp(System.currentTimeMillis());
		List<Object> params = new ArrayList<Object>();
        params.add(now);
        params.add(cfDelegante.toUpperCase());
		params.add(fkConfigUtente);
		return DBUtil.jdbcTemplate.update(sql.toString(), params.toArray());
	}
	
	@Override
	public int riabilita(Delega delega) {
		
        StringBuilder sql = new StringBuilder();
		sql.append("update idf_cnf_delega ");
		sql.append("SET data_fine= ?, uid_index_delega = ?, nome_file_delega = ? ");
		sql.append("where cf_delegante = ? and  id_config_utente = ? ");

		List<Object> params = new ArrayList<Object>();
		params.add(java.sql.Date.valueOf(delega.getDataFine()));
		params.add(delega.getUidIndex());
		params.add(delega.getFileName());
		params.add(delega.getCfDelegante().toUpperCase());
		params.add(delega.getIdConfigUtente());
        
		return DBUtil.jdbcTemplate.update(sql.toString(), params.toArray());
		
	}

}
