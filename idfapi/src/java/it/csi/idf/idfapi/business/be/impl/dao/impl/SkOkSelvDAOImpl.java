/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.SkOkSelvDAO;
import it.csi.idf.idfapi.dto.SkOkSelv;
import it.csi.idf.idfapi.mapper.SkOkSelvMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class SkOkSelvDAOImpl implements SkOkSelvDAO {
	private static Logger logger = Logger.getLogger(SkOkSelvDAOImpl.class);
	@Override
	public void insertFlgSez1(int idIntervento) {
		logger.info("<------ insertFlgSez1 "+ idIntervento +"---->");
		StringBuilder sql = new StringBuilder("INSERT INTO idf_cnf_sk_ok_selv(");
		sql.append("id_intervento, flg_sez1, flg_sez2, flg_sez3, flg_sez4, flg_sez5");
		sql.append(") VALUES (?, 1, 0, 0, 0, 0)");
		
		DBUtil.jdbcTemplate.update(sql.toString(), idIntervento);

	}

	@Override
	public boolean isFlgSez1Done(int idIntervento) {
		SkOkSelv skOkSelv = getSkOkSelv(idIntervento);
		return skOkSelv.getFlgSez1() == 1;
	}

	@Override
	public boolean isFlgSez2Done(int idIntervento) {
		
		SkOkSelv skOkSelv = getSkOkSelv(idIntervento);
		return skOkSelv.getFlgSez2() == 1;
	}

	@Override
	public boolean isFlgSez3Done(int idIntervento) {
		
		SkOkSelv skOkSelv = getSkOkSelv(idIntervento);
		return skOkSelv.getFlgSez3() == 1;
	}

	@Override
	public boolean isFlgSez4Done(int idIntervento) {
		
		SkOkSelv skOkSelv = getSkOkSelv(idIntervento);
		return skOkSelv.getFlgSez4() == 1;
	}

	@Override
	public boolean isFlgSez5Done(int idIntervento) {
		
		SkOkSelv skOkSelv = getSkOkSelv(idIntervento);
		return skOkSelv.getFlgSez5() == 1;
	}

	@Override
	public void updateFlgSez1(int idIntervento) {
		
		int[] flgs = {1,0,0,0,0};
		updateSkOkSelv(idIntervento, flgs);

	}


	@Override
	public void updateFlgSez2(int idIntervento) {

		int[] flgs = {1,1,0,0,0};
		updateSkOkSelv(idIntervento, flgs);

	}

	@Override
	public void updateFlgSez3(int idIntervento) {
		
		int[] flgs = {1,1,1,0,0};
		updateSkOkSelv(idIntervento, flgs);

	}

	@Override
	public void updateFlgSez4(int idIntervento) {
		
		int[] flgs = {1,1,1,1,0};
		updateSkOkSelv(idIntervento, flgs);
	}

	@Override
		
	public void updateFlgSez5(int idIntervento) {
		int[] flgs = {1,1,1,1,1};
		updateSkOkSelv(idIntervento, flgs);

	}

	@Override
	public int sumFlgSeziones(int idIntervento) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT SUM(flg_sez1 + flg_sez2 + flg_sez3 + flg_sez4 + flg_sez5)");
		sql.append(" FROM idf_cnf_sk_ok_selv WHERE id_intervento = ?");
		
		try {
			return DBUtil.jdbcTemplate.queryForObject(sql.toString(), Integer.class, idIntervento);
		}catch(Exception ex) {
			return 0;
		}
	}

	@Override
	public void initVariante(Integer idIntervento) {
			int[] flgs = {0,0,0,0,0,0};
			updateSkOkSelv(idIntervento, flgs);
	}

	private SkOkSelv getSkOkSelv(int idIntervento) {
		logger.info("<------ getSkOkSelv "+ idIntervento +"---->");
		String sql = "SELECT * FROM idf_cnf_sk_ok_selv where id_intervento= ?";
		List<SkOkSelv> res = DBUtil.jdbcTemplate.query(sql, new SkOkSelvMapper(),idIntervento);
		if(res != null && res.size() > 0) {
			return res.get(0);
		}
		return new SkOkSelv();
		
	}
	
	private void updateSkOkSelv(int idIntervento, int[] flgs) {
		logger.info("<------ updateSkOkSelv "+ idIntervento +"---->");
		StringBuilder sql = new StringBuilder("UPDATE idf_cnf_sk_ok_selv SET ");

		List<Integer> parameters = new ArrayList<Integer>();
		sql.append("flg_sez1 = ? ,");
		parameters.add(flgs[0]);
		sql.append("flg_sez2 = ? ,");
		parameters.add(flgs[1]);
		sql.append("flg_sez3 = ? ,");
		parameters.add(flgs[2]);
		sql.append("flg_sez4 = ? ,");
		parameters.add(flgs[3]);
		sql.append("flg_sez5 = ? ");
		parameters.add(flgs[4]);
		sql.append(" WHERE id_intervento = ?");
		parameters.add(idIntervento);
		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());


/*
		int tempFlg = 0;
		
		if(flgs[0] == 1) {
			sql.append("flg_sez1 = ?");
			tempFlg = flgs[0];
		} else if(flgs[1] == 1) {
			sql.append("flg_sez2 = ?");
			tempFlg = flgs[1];
		} else if(flgs[2] == 1) {
			sql.append("flg_sez3 = ?");
			tempFlg = flgs[2];
		} else if(flgs[3] == 1) {
			sql.append("flg_sez4 = ?");
			tempFlg = flgs[3];
		} else if(flgs[4] == 1) {
			sql.append("flg_sez5 = ?");
			tempFlg = flgs[4];
		} 
		sql.append(" WHERE id_intervento = ?");
		
		DBUtil.jdbcTemplate.update(sql.toString(), tempFlg, idIntervento);*/
	}

	@Override
	public void deleteByIdIntervento(int idIntervento) {
		
		String sql = "DELETE FROM idf_cnf_sk_ok_selv WHERE id_intervento = ?";
		
		DBUtil.jdbcTemplate.update(sql.toString(), idIntervento);
		
	}

}
