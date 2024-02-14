/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.CategoriaForestaleDAO;
import it.csi.idf.idfapi.dto.CategoriaForestale;
import it.csi.idf.idfapi.dto.InterventoCatfor;
import it.csi.idf.idfapi.mapper.CategoriaForestaleMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class CategoriaForestaleDAOImpl implements CategoriaForestaleDAO {
	
	public static Logger logger = Logger.getLogger(CategoriaForestaleDAOImpl.class);
	
	@Override
	public List<CategoriaForestale> findAllCategoriaForestale() {
		String sql = "SELECT * FROM idf_d_categoria_forestale where flg_visibile = 1 order by mtd_ordinamento";
		return DBUtil.jdbcTemplate.query(sql, new CategoriaForestaleMapper());
		
	}

	@Override
	public CategoriaForestale getByCodiceAmministratico(String codiceAmministrativo) {
		String sql = "SELECT * FROM idf_d_categoria_forestale WHERE codice_amministrativo = ?";
		return DBUtil.jdbcTemplate.queryForObject(sql, new CategoriaForestaleMapper(), codiceAmministrativo);
	}

	@Override
	public CategoriaForestale getCategoriaForestaleById(int idCategoriaForestale) {
		String sql = "SELECT * FROM idf_d_categoria_forestale WHERE id_categoria_forestale = ?";
		return DBUtil.jdbcTemplate.queryForObject(sql, new CategoriaForestaleMapper(), idCategoriaForestale);
	}

	@Override
	public List<CategoriaForestale> getAllByInterventoCatfors(List<InterventoCatfor> interventoCatfors) {
		
		if(interventoCatfors.isEmpty()) {
			return Collections.emptyList();
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM idf_d_categoria_forestale WHERE id_categoria_forestale IN(");
		
		for(int i = 0; i < interventoCatfors.size(); i++) {
			sql.append(interventoCatfors.get(i).getIdCategoriaForestale());
			if(i != interventoCatfors.size() - 1) {
				sql.append(",");
			}
		}
		sql.append(") and flg_visibile = 1 order by mtd_ordinamento");
		
		return DBUtil.jdbcTemplate.query(sql.toString(), new CategoriaForestaleMapper());
	}


	@Override
	public List<CategoriaForestale> getAllByIdInterventoCatfors(Integer idIntervento) {
		String sql ="SELECT catfor.* FROM idf_d_categoria_forestale catfor " +
				"INNER JOIN idf_r_intervento_catfor iric ON iric.id_categoria_forestale = catfor.id_categoria_forestale " +
				"WHERE iric.id_intervento = ? and flg_visibile = 1 order by mtd_ordinamento";
		return DBUtil.jdbcTemplate.query(sql, new CategoriaForestaleMapper(), idIntervento);
	}

	@Override
	public Integer calculateCategoriaForestaleByIdIntervento(Integer idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("fk_param_macro_catfor ");
		sql.append("FROM ");
		sql.append("( ");
		sql.append("SELECT ");
		sql.append("ROW_NUMBER() OVER(ORDER BY ricadenza.percentualeDiGeoOccupataDaCatFor DESC) AS Row, ");
		sql.append("ricadenza.ID_CATEGORIA_FORESTALE, ");
		sql.append("ricadenza.fk_param_macro_catfor, ");
		sql.append("ricadenza.codice_amministrativo, ");
		sql.append("ricadenza.descrizione, ");
		sql.append("ricadenza.percentualeDiGeoOccupataDaCatFor ");
		sql.append("FROM( ");
		sql.append("SELECT ");
		sql.append("categoriaForestale.ID_CATEGORIA_FORESTALE, ");
		sql.append("categoriaforestale.fk_param_macro_catfor, ");
		sql.append("categoriaForestale.codice_amministrativo, ");
		sql.append("categoriaForestale.descrizione, ");
		sql.append("SUM(ST_AREA(ST_Intersection(geoTipoForestale.geometria, (SELECT ");
		sql.append("ST_UNION (geomTrasf) as geomTrasfUnion ");
		sql.append("FROM ( ");
		sql.append("SELECT geoTrasf.geometria as geomTrasf ");
		sql.append("FROM idf_geo_pl_interv_trasf geoTrasf ");
		sql.append("WHERE geoTrasf.fk_intervento = ? ");
		sql.append(") as sbqry))) ");
		sql.append(")*100/ST_AREA((SELECT ST_UNION (geomTrasf) as geomTrasfUnion ");
		sql.append("FROM ( ");
		sql.append("SELECT geoTrasf.geometria as geomTrasf ");
		sql.append("FROM idf_geo_pl_interv_trasf geoTrasf ");
		sql.append("WHERE geoTrasf.fk_intervento = ? ");
		sql.append(") as sbqry) ");
		sql.append(") AS percentualeDiGeoOccupataDaCatFor ");
		sql.append("FROM ");
		sql.append("IDF_D_CATEGORIA_FORESTALE categoriaForestale ");
		sql.append("JOIN IDF_T_TIPO_FORESTALE tipoForestale ON categoriaForestale.id_categoria_forestale = tipoForestale.fk_categoria_forestale ");
		sql.append("JOIN IDF_GEO_PL_TIPO_FORESTALE geoTipoForestale ON tipoForestale.id_tipo_forestale = geoTipoForestale.fk_tipo_forestale ");
		sql.append("WHERE ");
		sql.append("ST_Intersects (( ");
		sql.append("SELECT ST_UNION (geomTrasf) as geomTrasfUnion ");
		sql.append("FROM ( ");
		sql.append("SELECT geoTrasf.geometria as geomTrasf ");
		sql.append("FROM idf_geo_pl_interv_trasf geoTrasf ");
		sql.append("WHERE geoTrasf.fk_intervento = ? ");
		sql.append(") as sbqry ");
		sql.append("), geoTipoForestale.geometria) ");
		sql.append("GROUP BY categoriaForestale.ID_CATEGORIA_FORESTALE ");
		sql.append(") as ricadenza) as query WHERE query.row = 1 ");
		logger.info("calculateCategoriaForestaleByIdIntervento: " + sql.toString());
		try {
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), Integer.class,
				new Object[] {idIntervento, idIntervento, idIntervento});
		}catch(Exception ex) {
			logger.error(ex.getMessage());
			return null;
		}
	}

}
