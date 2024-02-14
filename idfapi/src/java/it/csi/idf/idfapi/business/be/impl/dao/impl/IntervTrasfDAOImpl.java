/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.IntervTrasfDAO;
import it.csi.idf.idfapi.dto.IntervTrasf;
import it.csi.idf.idfapi.mapper.DoubleMapper;
import it.csi.idf.idfapi.mapper.IntervTrasfMapper;
import it.csi.idf.idfapi.mapper.StringMapper;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.TipoIstanzaEnum;

@Component
public class IntervTrasfDAOImpl implements IntervTrasfDAO {
	
	public static Logger logger = Logger.getLogger(IntervTrasfDAOImpl.class);
	
	@Override
	public void insertIntervTrasf(IntervTrasf intervTrasf) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO idf_geo_pl_interv_trasf(");
		//sql.append("fk_intervento, data_inserimento");
		//append(") VALUES (?, ?)");
		sql.append("fk_intervento, data_inserimento, geometria");
		sql.append(") VALUES (?, ?, ST_GeomFromText(?, 32632))");
		
		List<Object> parameters = new ArrayList<>();
		parameters.add(intervTrasf.getFkIntervento());
		parameters.add(intervTrasf.getDataInserimento() == null ? null : Date.valueOf(intervTrasf.getDataInserimento()));
		parameters.add(intervTrasf.getGeometria());
		logger.info("insertIntervTrasf:) "+sql.toString());
		logger.info("insertIntervTrasf array "+parameters.toString());
		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}

	@Override
	public int deleteIntervTrasfByFkIntervento(Integer idIntervento) {
		String sql = "DELETE FROM idf_geo_pl_interv_trasf WHERE fk_intervento = ?";
		return DBUtil.jdbcTemplate.update(sql.toString(), new Object[] {idIntervento});
	}

	@Override
	public IntervTrasf getIntervTrasfByid(int idgeo_pl_interv_trasf) {
		String sql = "SELECT * FROM idf_geo_pl_interv_trasf WHERE idgeo_pl_interv_trasf = ?";
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), 
				new Object[] {idgeo_pl_interv_trasf}, new IntervTrasfMapper());
	}

	@Override
	public void addGeometriaGML(Integer idIntervento, Object geometriGml) {
		logger.info("addGeometriaGML - idIntervento: " + idIntervento + " - geometriGml: " + geometriGml);
		String sql = "select fn_interv_trasf_aggiungi_geo(?,ST_AsGML(ST_GeomFromText(?, 32632)))";
		// 777 String sql = "select fn_interv_trasf_aggiungi_geo(?,ST_GML(ST_GeomFromText(?)))";
		
		Integer res = DBUtil.jdbcTemplate.queryForObject(sql.toString(), Integer.class, new Object[] {idIntervento,geometriGml.toString()});
		if(res == null || res > 0) {
			logger.error("addGeometria - Error code: " + res);
		}
		logger.info("addGeometriaGML - done");
	}

	@Override
	public void addGeometria(Integer idIntervento, String geometriGml) {
		logger.info("addGeometria - idIntervento: " + idIntervento + " - geometriGml: " + geometriGml);
		String sql = "select fn_interv_trasf_aggiungi_geo(?,?)";
		// 777 String sql = "select fn_interv_trasf_aggiungi_geo(?,ST_GML(ST_GeomFromText(?)))";
		
		Integer res = DBUtil.jdbcTemplate.queryForObject(sql.toString(),Integer.class, new Object[] {idIntervento,geometriGml});
		if(res == null || res > 0) {
			logger.error("addGeometria - Error code: " + res);
		}
		logger.info("addGeometria - done");
	}

	@Override
	public void removeGeometria(Integer idIntervento, Integer idGeoPlPropCatasto) {
		String sql = "select fn_interv_trasf_togli_geo(?,(select st_asgml(geometria) from idf_geo_pl_prop_catasto where idgeo_pl_prop_catasto = ?))";
		Integer res = DBUtil.jdbcTemplate.queryForObject(sql.toString(),Integer.class, new Object[] {idIntervento,idGeoPlPropCatasto});
		if(res == null || res > 0) {
			logger.error("addGeometria - Error code: " + res);
		}
		logger.info("removeGeometria - done: ");
	}

	@Override
	public List<String> getGeometrieGmlByFkIntervento(Integer idIntervento) {
		// 777 original
		// 777 String sql = "SELECT st_asgml(geometria) as geometriagml FROM idf_geo_pl_interv_trasf WHERE fk_intervento = ?";
		// 777 ultima modificacion 
		// String sql = "SELECT geometria as geometriagml FROM idf_geo_pl_interv_trasf WHERE fk_intervento = ?";
		String sql = "SELECT ST_ASGEOJSON( ST_TRANSFORM(geometria, 4326) ) as geometriagml FROM idf_geo_pl_interv_trasf WHERE fk_intervento = ?";
		return DBUtil.jdbcTemplate.query(sql.toString(), new Object[] {idIntervento}, new StringMapper());
	}
/*
	@Override
	public RicadenzaInfo getRicadenzaInfo(Integer idIntervento) {
			
			StringBuilder sql = new StringBuilder();
			
			sql.append("SELECT id_intervento, iria.codice_aapp, STRING_AGG(irir.codice_rn_2000, ', ') as rete_natura ");
			sql.append(", STRING_AGG(igps.denominazione, ', ') as popolamento_seme");
			sql.append(", STRING_AGG(idcf.descrizione, ', ') as categoria_forestale");
			sql.append(" from idf_t_interv_trasformazione itis ");
			sql.append(" LEFT JOIN idf_r_intervento_aapp iria using (id_intervento)");
			sql.append(" LEFT JOIN idf_r_intervento_rn_2000 irir using (id_intervento)");
			sql.append(" LEFT JOIN idf_r_intervento_pop_seme irips using (id_intervento)");
			sql.append(" LEFT JOIN idf_geo_pl_popolamento_seme igps using(id_popolamento_seme)");
			sql.append(" LEFT JOIN idf_r_intervento_catfor iric using (id_intervento)");
			sql.append(" LEFT JOIN idf_d_categoria_forestale idcf using(id_categoria_forestale)");
			sql.append(" where id_intervento = ? ");
			sql.append(" group by id_intervento, iria.codice_aapp");
			
			//return DBUtil.jdbcTemplate.queryForObject(sql.toString(), new RicadenzaInfoMapper(),idIntervento );
		return DataAccessUtils.singleResult(DBUtil.jdbcTemplate.query(sql.toString(), new RicadenzaInfoMapper(),idIntervento));
		}
		*/


	@Override
	public Double getAreaTrasformazioneByFkIntervento(Integer idIntervento) {
		
		String sql = "select sum(ST_AREA (geometria)) FROM idf_geo_pl_interv_trasf WHERE fk_intervento = ?";
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), 
				new Object[] {idIntervento}, new DoubleMapper());
	}
	
	@Override
	public Double getAreaItersecWithParticella(Integer idIntervento, Integer idgeo_pl_prop_catasto) {
		
		try {
		String sql = "select sum(ST_AREA (ST_INTERSECTION(a.geometria , b.geometria))) as area "
				+ "from idf_geo_pl_interv_trasf a,  idf_geo_pl_prop_catasto b "
				+ "where a.fk_intervento = ? and b.idgeo_pl_prop_catasto = ? "
				+ "and ST_Intersects(a.geometria , b.geometria )";
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), 
				new Object[] {idIntervento,idgeo_pl_prop_catasto}, new DoubleMapper());
		}
		catch(Exception e) {
			e.printStackTrace();
			logger.info("<----- getAreaItersecWithParticella idIntervento:" + idIntervento +"--" + idgeo_pl_prop_catasto + e);
			return 0.0;
		}
	
}

	@Override
	public TipoIstanzaEnum getTipoIntervento(Integer idIntervento) {
		String sql = "select fk_tipo_istanza from idf_t_istanza_forest itif where id_istanza_intervento = ? ";
		 int value = DBUtil.jdbcTemplate.queryForObject(sql.toString(), 
				new Object[] {idIntervento}, Integer.class);
		 return TipoIstanzaEnum.getEnum(value);
	}

}
