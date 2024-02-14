/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.ParamtrasfTrasformazionDAO;
import it.csi.idf.idfapi.dto.ParamtrasfTrasformazion;
import it.csi.idf.idfapi.mapper.ParamtrasfTrasformazionMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class ParamtrasfTrasformazionDAOImpl implements ParamtrasfTrasformazionDAO {

	@Override
	public void insert(ParamtrasfTrasformazion paramtrasfTrasformazion) {
		StringBuilder sql = new StringBuilder("INSERT INTO idf_r_paramtrasf_trasformazion(");
		sql.append("id_parametro_trasf, id_intervento, data_inserimento, data_aggiornamento, fk_config_utente");
		sql.append(") VALUES(?,?,?,?,?)");
		
		List<Object> params = new ArrayList<>();
		params.add(paramtrasfTrasformazion.getIdParametroTrasf());
		params.add(paramtrasfTrasformazion.getIdIntervento());
		params.add(paramtrasfTrasformazion.getDataInserimento() == null ? null : Date.valueOf(paramtrasfTrasformazion.getDataInserimento()));
		params.add(paramtrasfTrasformazion.getDataAggiornamento() == null ? null : Date.valueOf(paramtrasfTrasformazion.getDataAggiornamento()));
		params.add(paramtrasfTrasformazion.getFkConfigUtente());
		
		DBUtil.jdbcTemplate.update(sql.toString(), params.toArray());
	}

	@Override
	public List<ParamtrasfTrasformazion> getParamtrasfTrasformazionsByIdIntervento(int idIntervento) {
		String sql = "SELECT * FROM idf_r_paramtrasf_trasformazion WHERE id_intervento = ?";
		
		return DBUtil.jdbcTemplate.query(sql, new ParamtrasfTrasformazionMapper(), idIntervento);
	}

	@Override
	public ParamtrasfTrasformazion getParamtrasfTrasformazionByIdInterventoAndIdParametroTrasf(int idIntervento,
			int idParametroTrasf) {
		String sql = "SELECT * FROM idf_r_paramtrasf_trasformazion WHERE id_intervento = ? AND id_parametro_trasf = ?";
		
		List<ParamtrasfTrasformazion> paramtrasfTrasformazions = DBUtil.jdbcTemplate.query(sql, new ParamtrasfTrasformazionMapper(), idIntervento, idParametroTrasf);

		if(paramtrasfTrasformazions.isEmpty()) {
			return null;			 
		} else {
			return paramtrasfTrasformazions.get(0);
		}
	}

	@Override
	public void deleteByIdInterventoAndIdParametroTrasf(int idIntervento, int idParametroTrasf) {
		String sql = "DELETE FROM idf_r_paramtrasf_trasformazion WHERE id_intervento = ? AND id_parametro_trasf = ?";
		DBUtil.jdbcTemplate.update(sql, idIntervento, idParametroTrasf);
	}
	
	@Override
	public void deleteByIdIntervento(int idIntervento) {
		String sql = "DELETE FROM idf_r_paramtrasf_trasformazion WHERE id_intervento = ? ";
		DBUtil.jdbcTemplate.update(sql, idIntervento);
	}
}
