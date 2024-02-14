/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.vincolo.dao.SkOkVincoloDAO;
import it.csi.idf.idfapi.dto.SkOkTrasf;
import it.csi.idf.idfapi.mapper.SkOkTrasfMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class SkOkVincoloDAOImpl implements SkOkVincoloDAO {

	@Override
	public void insertFlgSez1(int idIntervento) {
		StringBuilder sql = new StringBuilder("INSERT INTO idf_cnf_sk_ok_vinc_idro(");
		sql.append("id_intervento, flg_sez1, flg_sez2, flg_sez3, flg_sez4, flg_sez5, flg_sez6");
		sql.append(") VALUES (?, 1, 0, 0, 0, 0, 0)");
		
		DBUtil.jdbcTemplate.update(sql.toString(), idIntervento);
	}
	
	@Override
	public boolean isFlgSez1Done(int idIntervento) {
		SkOkTrasf skOkTrasf = getSkOkTrasf(idIntervento);
		return skOkTrasf.getFlgSez1() == 1;
	}

	@Override
	public boolean isFlgSez2Done(int idIntervento) {
		SkOkTrasf skOkTrasf = getSkOkTrasf(idIntervento);
		return skOkTrasf.getFlgSez2() == 1;
	}

	@Override
	public boolean isFlgSez3Done(int idIntervento) {
		SkOkTrasf skOkTrasf = getSkOkTrasf(idIntervento);
		return skOkTrasf.getFlgSez3() == 1;
	}

	@Override
	public boolean isFlgSez4Done(int idIntervento) {
		SkOkTrasf skOkTrasf = getSkOkTrasf(idIntervento);
		return skOkTrasf.getFlgSez4() == 1;
	}

	@Override
	public boolean isFlgSez5Done(int idIntervento) {
		SkOkTrasf skOkTrasf = getSkOkTrasf(idIntervento);
		return skOkTrasf.getFlgSez5() == 1;
	}
	
	@Override
	public void updateFlgSez1(int idIntervento) {
		int[] flgs = {1,0,0,0,0,0};
		updateSkOkTrasf(idIntervento, flgs);
	}
	
	@Override
	public void updateFlgSez2(int idIntervento) {
		int[] flgs = {1,1,0,0,0,0};
		updateSkOkTrasf(idIntervento, flgs);
	}
	
	@Override
	public void updateFlgSez3(int idIntervento) {
		int[] flgs = {1,1,1,0,0,0};
		updateSkOkTrasf(idIntervento, flgs);
	}
	
	@Override
	public void updateFlgSez4(int idIntervento) {
		int[] flgs = {1,1,1,1,0,0};
		updateSkOkTrasf(idIntervento, flgs);
	}
	
	@Override
	public void updateFlgSez5(int idIntervento) {
		int[] flgs = {1,1,1,1,1,0};
		updateSkOkTrasf(idIntervento, flgs);
	}
	
	@Override
	public void updateFlgSez6(int idIntervento) {
		int[] flgs = {1,1,1,1,1,1};
		updateSkOkTrasf(idIntervento, flgs);
	}
	
	@Override
	public void initVariante(int idIntervento) {
		int[] flgs = {0,0,0,0,0,0};
		updateSkOkTrasf(idIntervento, flgs);
		
	}
	
	@Override
	public int sumFlgSeziones(int idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT SUM(flg_sez1 + flg_sez2 + flg_sez3 + flg_sez4 + flg_sez5 + flg_sez6)");
		sql.append(" FROM idf_cnf_sk_ok_vinc_idro WHERE id_intervento = ?");
		
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), Integer.class, idIntervento);
	}
	
	@Override
	public void resetFlgToStep4(int idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE idf_cnf_sk_ok_vinc_idro SET ");
		sql.append(" flg_sez5 = 0,");
		sql.append(" flg_sez6 = 0");
		sql.append(" WHERE id_intervento = ?");
		
		DBUtil.jdbcTemplate.update(sql.toString(), idIntervento);
	}
	
	private SkOkTrasf getSkOkTrasf(int idIntervento) {
		String sql = "SELECT * FROM idf_cnf_sk_ok_vinc_idro where id_intervento= ?";
		return DBUtil.jdbcTemplate.queryForObject(sql, new SkOkTrasfMapper(),idIntervento);
	} 
	
	private void updateSkOkTrasf(int idIntervento, int[] flgs) {
		StringBuilder sql = new StringBuilder("UPDATE idf_cnf_sk_ok_vinc_idro SET ");
		List<Integer> parameters = new ArrayList<Integer>();
		sql.append("flg_sez1 = ? ,");
		parameters.add(flgs[0]);
		sql.append("flg_sez2 = ? ,");
		parameters.add(flgs[1]);
		sql.append("flg_sez3 = ? ,");
		parameters.add(flgs[2]);
		sql.append("flg_sez4 = ? ,");
		parameters.add(flgs[3]);
		sql.append("flg_sez5 = ? ,");
		parameters.add(flgs[4]);
		sql.append("flg_sez6 = ?");
		parameters.add(flgs[5]);
		sql.append(" WHERE id_intervento = ?");
		parameters.add(idIntervento);
		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}

	@Override
	public int deleteByIdIntervento(int idIntervento) {
		String sql = "delete from idf_cnf_sk_ok_vinc_idro where id_intervento = ? ";
		return DBUtil.jdbcTemplate.update(sql, idIntervento);
	}

}
