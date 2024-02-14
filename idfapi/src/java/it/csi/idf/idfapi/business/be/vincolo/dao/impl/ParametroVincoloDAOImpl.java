/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.vincolo.dao.ParametroVincoloDAO;
import it.csi.idf.idfapi.dto.ParametroTrasf;
import it.csi.idf.idfapi.dto.ParametroTrasfResource;
import it.csi.idf.idfapi.dto.ParamtrasfTrasformazion;
import it.csi.idf.idfapi.dto.SceltiParameter;
import it.csi.idf.idfapi.mapper.ParametroTrasfMapper;
import it.csi.idf.idfapi.mapper.ParametroTrasfResourceMapper;
import it.csi.idf.idfapi.mapper.ParamtrasfTrasformazionMapper;
import it.csi.idf.idfapi.mapper.SceltiParameterMapper;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.TipoParametroTrasfEnum;

@Component
public class ParametroVincoloDAOImpl implements ParametroVincoloDAO {
	
	private final ParametroTrasfMapper parametroMapper = new ParametroTrasfMapper();
	private final ParametroTrasfResourceMapper parametroResourceMapper = new ParametroTrasfResourceMapper();
	private final SceltiParameterMapper sceltiParameterMapper = new SceltiParameterMapper();

	@Override
	public List<ParametroTrasf> getParametroTrasfs() {
		StringBuilder sql = new StringBuilder("SELECT id_parametro_trasf, fk_tipo_paramero_trasf, desc_parametro_trasf, mtd_ordinamento, flg_visibile, valore");
		sql.append(" FROM idf_d_parametro_trasf WHERE id_parametro_trasf <> 0 AND flg_visibile = 1 ");
		sql.append(" ORDER BY mtd_ordinamento");
		
		return DBUtil.jdbcTemplate.query(sql.toString(), parametroMapper);
	}

	@Override
	public ParametroTrasf getParametroTrasfById(int idParametroTrasf) {
		String sql = "SELECT * FROM idf_d_parametro_trasf WHERE id_parametro_trasf = ?";
		
		return DBUtil.jdbcTemplate.queryForObject(sql, parametroMapper, idParametroTrasf);
	}

	@Override
	public List<SceltiParameter> getSceltoParemeterByParamtrasfTrasformazion(
			List<ParamtrasfTrasformazion> paramtrasfTrasformazion) {
		
		StringBuilder sql = new StringBuilder("SELECT tpt.tipo_paremetro_trasf, pt.valore");
		sql.append(" FROM idf_d_parametro_trasf pt");
		sql.append(" JOIN idf_d_tipo_parametro_trasf tpt ON pt.fk_tipo_paramero_trasf = tpt.id_tipo_paramero_trasf");
		sql.append(" WHERE pt.id_parametro_trasf IN(");
		
		for(int i = 0; i < paramtrasfTrasformazion.size(); i++) {
			sql.append(paramtrasfTrasformazion.get(i).getIdParametroTrasf());
			if(i != paramtrasfTrasformazion.size() - 1)  {
				sql.append(", ");
			}
		}
		
		sql.append(")");
		
		return DBUtil.jdbcTemplate.query(sql.toString(), sceltiParameterMapper);
	}

	@Override
	public List<ParametroTrasfResource> getParametroTrasfsByTipoParametro(TipoParametroTrasfEnum tipoParametroEnum) {
		StringBuilder sql = new StringBuilder("SELECT id_parametro_trasf, fk_tipo_paramero_trasf, desc_parametro_trasf");
		sql.append(" FROM idf_d_parametro_trasf");
		sql.append(" WHERE flg_visibile = 1 AND fk_tipo_paramero_trasf = ?");
		sql.append(" ORDER BY mtd_ordinamento ");
		
		return DBUtil.jdbcTemplate.query(sql.toString(), parametroResourceMapper, tipoParametroEnum.getValue());
	}
	
	@Override
	public List<ParamtrasfTrasformazion> getParamtrasfTrasformazionsByIdIntervento(int idIntervento) {
		String sql = "SELECT * FROM idf_r_paramtrasf_trasformazion WHERE id_intervento = ?";
		
		return DBUtil.jdbcTemplate.query(sql, new ParamtrasfTrasformazionMapper(), idIntervento);
	}
}
