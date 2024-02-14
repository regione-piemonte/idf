/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.ZonaAltimetricaDAO;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class ZonaAltimetricaDAOImpl implements ZonaAltimetricaDAO {
	
	public static Logger logger = Logger.getLogger(ZonaAltimetricaDAOImpl.class);

	@Override
	public Integer getMaxOccurencesOfFkParamtrasf(List<String[]> sezioniFogli) {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT fk_paramtrasf_zona_altimetrica FROM (");
		sql.append("SELECT fk_paramtrasf_zona_altimetrica, COUNT(fk_paramtrasf_zona_altimetrica) AS countParam");
		sql.append(" FROM idf_geo_pl_zona_altimetrica WHERE ");
		
		for(int i = 0; i < sezioniFogli.size(); i++) {
			sql.append("(sezione = '");
			sql.append(sezioniFogli.get(i)[0]);
			sql.append("' AND foglio = ");
			sql.append(sezioniFogli.get(i)[1]);
			sql.append(")");
			if(i != sezioniFogli.size() - 1) {
				sql.append(" OR ");
			}
		}
		sql.append(" GROUP BY fk_paramtrasf_zona_altimetrica ORDER BY countParam DESC) AS x LIMIT 1");
		
		logger.info("getMaxOccurencesOfFkParamtrasf - sql: " + sql.toString());

		return DBUtil.jdbcTemplate.queryForInt(sql.toString());
	}

	@Override
	public Integer getZonaAltimentricaByIdIntervento(Integer idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("risultato1.fk_paramtrasf_zona_altimetrica ");
		sql.append("FROM( ");
		sql.append("SELECT ");
		sql.append("ROW_NUMBER() OVER(ORDER BY risultato.area DESC) AS Row, ");
		sql.append("risultato.fk_paramtrasf_zona_altimetrica, ");
		sql.append("risultato.area ");
		sql.append("FROM( ");
		sql.append("SELECT ");
		sql.append("ubicazione.fk_paramtrasf_zona_altimetrica, ");
		sql.append("SUM(ST_AREA(ST_Intersection(ubicazione.geometria, (SELECT ");
		sql.append("ST_UNION (geomTrasf) as geomTrasfUnion ");
		sql.append("FROM ( ");
		sql.append("SELECT geoTrasf.geometria as geomTrasf ");
		sql.append("FROM idf_geo_pl_interv_trasf geoTrasf ");
		sql.append("WHERE geoTrasf.fk_intervento = ? ");
		sql.append(") as sbqry)))) as area ");
		sql.append("FROM ");
		sql.append("IDF_GEO_PL_ZONA_ALTIMETRICA ubicazione ");
		sql.append("WHERE ");
		sql.append("ST_Intersects (( ");
		sql.append("SELECT ST_UNION (geomTrasf) as geomTrasfUnion ");
		sql.append("FROM ( ");
		sql.append("SELECT geoTrasf.geometria as geomTrasf ");
		sql.append("FROM idf_geo_pl_interv_trasf geoTrasf ");
		sql.append("WHERE geoTrasf.fk_intervento = ? ");
		sql.append(") as sbqry ");
		sql.append("), ubicazione.geometria)            ");
		sql.append("GROUP BY ");
		sql.append("ubicazione.fk_paramtrasf_zona_altimetrica ");
		sql.append(") as risultato ");
		sql.append(") as risultato1 WHERE risultato1.row = 1 ");
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(),Integer.class,new Object[] {idIntervento,idIntervento});
	}
}
