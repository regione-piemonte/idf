/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import it.csi.idf.idfapi.dto.RicadenzaPfa;
import it.csi.idf.idfapi.dto.RicadenzaPortaseme;
import it.csi.idf.idfapi.mapper.RicadenzaPfaMapper;
import it.csi.idf.idfapi.mapper.RicadenzaPortaSemeMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.RicadenzeDAO;
import it.csi.idf.idfapi.dto.RicadenzaInformazioni;
import it.csi.idf.idfapi.dto.RicadenzaIntervento;
import it.csi.idf.idfapi.mapper.DoubleMapper;
import it.csi.idf.idfapi.mapper.RicadenzaInformazioniMapper;
import it.csi.idf.idfapi.mapper.RicadenzaInterventoMapper;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.ProceduraEnum;

@Component
public class RicadenzeDAOImpl implements RicadenzeDAO {
	
	public static Logger logger = Logger.getLogger(RicadenzeDAOImpl.class);

	@Override
	public List<RicadenzaInformazioni> getRicadenzeForestali(Integer idIntervento,ProceduraEnum procedura) {
		String tableName = null;
		switch(procedura.getValue()) {
			case 1: tableName = "idf_geo_pl_lotto_intervento";break;//pfa
			case 3: tableName = "idf_geo_pl_interv_trasf";break;//istafor - trasformazioni 
			case 33: tableName = "idf_geo_pl_interv_vincidro";break;//istafor-vincolo
		}
		logger.info("getRicadenzeForestali table: " + tableName + " - Procedura: " + procedura);
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("categoriaForestale.ID_CATEGORIA_FORESTALE, ");
		sql.append("categoriaForestale.codice_amministrativo, ");
		sql.append("categoriaForestale.descrizione, ");
		sql.append("SUM(ST_AREA(ST_Intersection(geoTipoForestale.geometria, (SELECT ");
		sql.append("ST_UNION (geomTrasf) as geomTrasfUnion ");
		sql.append("FROM ( ");
		sql.append("SELECT geoTrasf.geometria as geomTrasf ");
		sql.append("FROM ");
		sql.append(tableName);
		sql.append(" geoTrasf ");
		sql.append("WHERE geoTrasf.fk_intervento = ? ");
		sql.append(") as sbqry ))) ");
		sql.append(")*100/ST_AREA((SELECT ST_UNION (geomTrasf) as geomTrasfUnion ");
		sql.append("FROM ( ");
		sql.append("SELECT geoTrasf.geometria as geomTrasf ");
		sql.append("FROM ");
		sql.append(tableName);
		sql.append(" geoTrasf ");
		sql.append("WHERE geoTrasf.fk_intervento = ? ");
		sql.append(") as sbqry) ");
		sql.append(") AS percentuale ");
		sql.append("FROM ");
		sql.append("IDF_D_CATEGORIA_FORESTALE categoriaForestale ");
		sql.append("JOIN IDF_T_TIPO_FORESTALE tipoForestale ON categoriaForestale.id_categoria_forestale = tipoForestale.fk_categoria_forestale ");
		sql.append("JOIN IDF_GEO_PL_TIPO_FORESTALE geoTipoForestale ON tipoForestale.id_tipo_forestale = geoTipoForestale.fk_tipo_forestale ");
		sql.append("WHERE ");
		sql.append("ST_Intersects (( ");
		sql.append("SELECT ST_UNION (geomTrasf) as geomTrasfUnion ");
		sql.append("FROM ( ");
		sql.append("SELECT geoTrasf.geometria as geomTrasf ");
		sql.append("FROM ");
		sql.append(tableName);
		sql.append(" geoTrasf ");
		sql.append("WHERE geoTrasf.fk_intervento = ? ");
		sql.append(") as sbqry ");
		sql.append("), geoTipoForestale.geometria) ");
		sql.append("GROUP BY categoriaForestale.ID_CATEGORIA_FORESTALE");
		logger.info("getRicadenzeForestali sql: " + sql.toString());
		return DBUtil.jdbcTemplate.query(sql.toString(), 
				new Object[] {idIntervento,idIntervento,idIntervento}, new RicadenzaInformazioniMapper());
	}

	@Override
	public List<RicadenzaInformazioni> getRicadenzeForestaliFromDB(Integer idIntervento,ProceduraEnum procedura) {
		String tableName = null;
		switch(procedura.getValue()) {
			case 1: tableName = "idf_geo_pl_lotto_intervento";break;//pfa
			case 3: tableName = "idf_geo_pl_interv_trasf";break;//istafor - trasformazioni
			case 33: tableName = "idf_geo_pl_interv_vincidro";break;//istafor-vincolo
		}
		logger.info("getRicadenzeForestali table: " + tableName + " - Procedura: " + procedura);
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT categoriaForestale.ID_CATEGORIA_FORESTALE, categoriaForestale.codice_amministrativo, categoriaForestale.descrizione, ");
		sql.append("SUM(ST_AREA(ST_Intersection(geoTipoForestale.geometria, (SELECT ST_UNION (geomTrasf) as geomTrasfUnion FROM ( SELECT geoTrasf.geometria as geomTrasf ");
		sql.append("FROM ");
		sql.append(tableName);
		sql.append(" geoTrasf ");
		sql.append("WHERE geoTrasf.fk_intervento = ? ");
		sql.append(") as sbqry ))) ");
		sql.append(")*100/ST_AREA((SELECT ST_UNION (geomTrasf) as geomTrasfUnion ");
		sql.append("FROM ( ");
		sql.append("SELECT geoTrasf.geometria as geomTrasf ");
		sql.append("FROM ");
		sql.append(tableName);
		sql.append(" geoTrasf ");
		sql.append("WHERE geoTrasf.fk_intervento = ? ");
		sql.append(") as sbqry) ");
		sql.append(") AS percentuale ");
		sql.append("FROM ");
		sql.append("idf_r_intervento_catfor iric ");
		sql.append("JOIN IDF_D_CATEGORIA_FORESTALE categoriaForestale on categoriaforestale.id_categoria_forestale = iric.id_categoria_forestale ");
		sql.append("JOIN IDF_T_TIPO_FORESTALE tipoForestale ON categoriaForestale.id_categoria_forestale = tipoForestale.fk_categoria_forestale ");
		sql.append("JOIN IDF_GEO_PL_TIPO_FORESTALE geoTipoForestale ON tipoForestale.id_tipo_forestale = geoTipoForestale.fk_tipo_forestale ");
		sql.append("WHERE ");
		sql.append("iric.id_intervento = ? ");
		sql.append("GROUP BY categoriaForestale.ID_CATEGORIA_FORESTALE ");
		sql.append("ORDER BY percentuale DESC");
		logger.info("getRicadenzeForestaliFromDB sql: " + sql.toString());
		return DBUtil.jdbcTemplate.query(sql.toString(),
				new Object[] {idIntervento,idIntervento,idIntervento}, new RicadenzaInformazioniMapper());
	}

	@Override
	public List<RicadenzaInformazioni> getRicadenzePopolamentiSeme(Integer idIntervento,ProceduraEnum procedura) {
		String tableName = null;
		switch(procedura.getValue()) {
			case 1: tableName = "idf_geo_pl_lotto_intervento";break;//pfa
			case 3: tableName = "idf_geo_pl_interv_trasf";break;//istafor - trasformazioni 
			case 33: tableName = "idf_geo_pl_interv_vincidro";break;//istafor-vincolo
		}
		logger.info("getRicadenzePopolamentiSeme table: " + tableName + " - Procedura: " + procedura);
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("geoPopseme.id_popolamento_seme, ");
		sql.append("geoPopseme.codice_amministrativo , ");
		sql.append("geoPopseme.denominazione as descrizione, ");
		sql.append("SUM(ST_AREA(ST_Intersection(geoPopseme.geometria, (SELECT ");
		sql.append("ST_UNION (geomTrasf) as geomTrasfUnion ");
		sql.append("FROM ( ");
		sql.append("SELECT geoTrasf.geometria as geomTrasf ");
		sql.append("FROM ");
		sql.append(tableName);
		sql.append(" geoTrasf ");
		sql.append("WHERE geoTrasf.fk_intervento = ? ");
		sql.append(") as sbqry))) ");
		sql.append(")*100/ST_AREA((SELECT ST_UNION (geomTrasf) as geomTrasfUnion ");
		sql.append("FROM ( ");
		sql.append("SELECT geoTrasf.geometria as geomTrasf ");
		sql.append("FROM ");
		sql.append(tableName);
		sql.append(" geoTrasf ");
		sql.append("WHERE geoTrasf.fk_intervento = ? ");
		sql.append(") as sbqry) ");
		sql.append(") AS percentuale ");
		sql.append("FROM ");
		sql.append("idf_geo_pl_popolamento_seme geoPopseme ");
		sql.append("WHERE ");
		sql.append("ST_Intersects (( ");
		sql.append("SELECT ST_UNION (geomTrasf) as geomTrasfUnion ");
		sql.append("FROM ( ");
		sql.append("SELECT geoTrasf.geometria as geomTrasf ");
		sql.append("FROM ");
		sql.append(tableName);
		sql.append(" geoTrasf ");
		sql.append("WHERE geoTrasf.fk_intervento = ? ");
		sql.append(") as sbqry ");
		sql.append("), geoPopseme.geometria) ");
		sql.append("GROUP BY geoPopseme.id_popolamento_seme");
		logger.info("getRicadenzePopolamentiSeme sql: " + sql.toString());
		return DBUtil.jdbcTemplate.query(sql.toString(), 
				new Object[] {idIntervento,idIntervento,idIntervento}, new RicadenzaInformazioniMapper());
	}

	@Override
	public List<RicadenzaPortaseme> getRicadenzePortaSeme(Integer idIntervento, ProceduraEnum procedura) {
		String tableName = null;
		switch(procedura.getValue()) {
			case 1: tableName = "idf_geo_pl_lotto_intervento";break;//pfa
			case 3: tableName = "idf_geo_pl_interv_trasf";break;//istafor - trasformazioni
			case 33: tableName = "idf_geo_pl_interv_vincidro";break;//istafor-vincolo
		}
		logger.info("getRicadenzePortaSeme table: " + tableName + " - Procedura: " + procedura);
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT geoPtPortaseme.idgeo_pt_portaseme, geoPtPortaseme.localita, its.volgare AS specie " +
				"FROM idf_geo_pt_portaseme geoPtPortaseme  " +
				"INNER JOIN idf_t_specie its ON its.id_specie = geoPtPortaseme.fk_specie " +
				"WHERE ST_Intersects ((" +
				"  SELECT ST_UNION (geomTrasf) AS geomTrasfUnion" +
				"  FROM ( SELECT geoTrasf.geometria AS geomTrasf FROM ");
		sql.append(tableName);
		sql.append("  geoTrasf WHERE geoTrasf.fk_intervento = ? ) AS sbqry ), geoPtPortaseme.geometria)");

		logger.info("getRicadenzePortaSeme sql: " + sql.toString());
		return DBUtil.jdbcTemplate.query(sql.toString(),
				new Object[] {idIntervento}, new RicadenzaPortaSemeMapper());
	}

	@Override
	public List<RicadenzaPfa> getRicadenzePFA(Integer idIntervento, ProceduraEnum procedura) {
		String tableName = null;
		switch(procedura.getValue()) {
			case 1: tableName = "idf_geo_pl_lotto_intervento";break;//pfa
			case 3: tableName = "idf_geo_pl_interv_trasf";break;//istafor - trasformazioni
			case 33: tableName = "idf_geo_pl_interv_vincidro";break;//istafor-vincolo
		}
		logger.info("getRicadenzePFA table: " + tableName + " - Procedura: " + procedura);
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT igpp.idgeo_pl_pfa, igpp.denominazione ");
		sql.append(" FROM idf_geo_pl_pfa igpp  ");
		sql.append(" INNER JOIN idf_geo_pl_pfa_dett igppd ON igppd.idgeo_pl_pfa = igpp.idgeo_pl_pfa ");
		sql.append(" WHERE ST_Intersects (( ");
		sql.append("  SELECT ST_UNION (geomTrasf) AS geomTrasfUnion ");
		sql.append("  FROM ( SELECT geoTrasf.geometria AS geomTrasf FROM ");
		sql.append(tableName);
		sql.append("  geoTrasf WHERE geoTrasf.fk_intervento = ? ) AS sbqry ), igppd.geometria)");
		sql.append(" AND igppd.flg_blocco_pfa = 1");

		logger.info("getRicadenzePFA sql: " + sql.toString());
		return DBUtil.jdbcTemplate.query(sql.toString(),
				new Object[] {idIntervento}, new RicadenzaPfaMapper());
	}

	@Override
	public Double getRicadenzeVincoloIdrogeologico(Integer idIntervento,ProceduraEnum procedura) {
		String tableName = null;
		switch(procedura.getValue()) {
			case 1: tableName = "idf_geo_pl_lotto_intervento";break;//pfa
			case 3: tableName = "idf_geo_pl_interv_trasf";break;//istafor - trasformazioni 
			case 33: tableName = "idf_geo_pl_interv_vincidro";break;//istafor-vincolo
		}
		logger.info("getRicadenzeVincoloIdrogeologico table: " + tableName + " - Procedura: " + procedura);
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("SUM(ST_AREA(ST_Intersection(geoVincIdro.geometria, (SELECT ");
		sql.append("ST_UNION (geomTrasf) as geomTrasfUnion ");
		sql.append("FROM ( ");
		sql.append("SELECT geoTrasf.geometria as geomTrasf ");
		sql.append("FROM ");
		sql.append(tableName);
		sql.append(" geoTrasf ");
		sql.append("WHERE geoTrasf.fk_intervento = ? ");
		sql.append(") as sbqry))) ");
		sql.append(")*100/ST_AREA((SELECT ST_UNION (geomTrasf) as geomTrasfUnion ");
		sql.append("FROM ( ");
		sql.append("SELECT geoTrasf.geometria as geomTrasf ");
		sql.append("FROM ");
		sql.append(tableName);
		sql.append(" geoTrasf ");
		sql.append("WHERE geoTrasf.fk_intervento = ? ");
		sql.append(") as sbqry) ");
		sql.append(") AS percentuale ");
		sql.append("FROM ");
		sql.append("idf_geo_pl_vinc_idro10k geoVincIdro ");
		sql.append("WHERE ");
		sql.append("ST_Intersects (( ");
		sql.append("SELECT ST_UNION (geomTrasf) as geomTrasfUnion ");
		sql.append("FROM ( ");
		sql.append("SELECT geoTrasf.geometria as geomTrasf ");
		sql.append("FROM ");
		sql.append(tableName);
		sql.append(" geoTrasf ");
		sql.append("WHERE geoTrasf.fk_intervento = ? ");
		sql.append(") as sbqry ");
		sql.append("), geoVincIdro.geometria)");
		logger.info("getRicadenzeVincoloIdrogeologico sql: " + sql.toString());
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), 
				new Object[] {idIntervento,idIntervento,idIntervento}, new DoubleMapper());
	}

	@Override
	public List<RicadenzaInformazioni> getRicadenzeCategForestaliPfa(Integer idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("select codice_amministrativo, descrizione, (ha/ha_tot) * 100 as percentuale  ");
		sql.append("from(  ");
		sql.append("select fk_intervento, cfo.id_categoria_forestale as codice_amministrativo, cfo.descrizione , SUM(ST_AREA(ST_Intersection(supfor.geometria, pl_lotto.geometria))) as ha  ");
		sql.append("FROM idf_geo_pl_sup_forestale supfor  ");
		sql.append("INNER JOIN idf_geo_pl_lotto_intervento pl_lotto ON ST_Intersects(supfor.geometria, pl_lotto.geometria)  ");
		sql.append("inner JOIN idf_t_interv_selvicolturale itis on itis.id_intervento = pl_lotto.fk_intervento and itis.idgeo_pl_pfa = supfor.idgeo_pl_pfa   ");
		sql.append("INNER JOIN idf_t_tipo_forestale tfo on supfor.fk_tipo_forestale = tfo.id_tipo_forestale  ");
		sql.append("inner join idf_d_categoria_forestale cfo on cfo.id_categoria_forestale = tfo.fk_categoria_forestale   ");
		sql.append("WHERE pl_lotto.fk_intervento  = ?   ");
		sql.append("group by fk_intervento,  pl_lotto.fk_intervento, cfo.descrizione,cfo.id_categoria_forestale  ");
		sql.append(") as foo ");
		sql.append("inner join (select fk_intervento, SUM(ST_AREA(pl_lotto.geometria)) as ha_tot from idf_geo_pl_lotto_intervento  pl_lotto ");
		sql.append("	 group by fk_intervento) as tot on tot.fk_intervento = foo.fk_intervento ");
		sql.append("order by descrizione  ");
	
		return DBUtil.jdbcTemplate.query(sql.toString(), new RicadenzaInformazioniMapper(), idIntervento);
	}

	@Override
	public List<RicadenzaIntervento> getRicadenzeIntervento(Integer idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("igppt.tipo_vincolo,igppt.nome_vincolo,igppt.provvedimento,");
		sql.append("SUM (ST_AREA (ST_Intersection(igppt.geometria,");
		sql.append("(SELECT ST_UNION (geometria) as geomIntSelvUnion ");
		sql.append("FROM (");
		sql.append("SELECT ");
		sql.append("idf_geo_pl_lotto_intervento.fk_intervento AS id_intervento,"
		           + "st_union(idf_geo_pl_lotto_intervento.geometria) AS geometria ");
		sql.append("FROM ");
		sql.append("idf_geo_pl_lotto_intervento ");
		sql.append("GROUP BY ");
		sql.append("idf_geo_pl_lotto_intervento.fk_intervento ");
		sql.append("UNION ");
		sql.append("SELECT ");
		sql.append("idf_geo_ln_lotto_intervento.id_intervento,"
				+ "st_union(st_buffer(idf_geo_ln_lotto_intervento.geometria,"
				+ " 1::double precision)) AS geometria ");
		sql.append("FROM ");
		sql.append("idf_geo_ln_lotto_intervento ");
		sql.append("GROUP BY ");
		sql.append("idf_geo_ln_lotto_intervento.id_intervento ");
		sql.append("UNION ");
		sql.append("SELECT ");
		sql.append("idf_geo_pt_lotto_intervento.id_intervento,"
				+ "st_union(st_buffer(idf_geo_pt_lotto_intervento.geometria,"
				+ "1::double precision))AS geometria ");
		sql.append("FROM ");
				sql.append("idf_geo_pt_lotto_intervento ");
				sql.append("GROUP BY idf_geo_pt_lotto_intervento.id_intervento) as sbqry ");
		sql.append("WHERE ");
		sql.append("sbqry.id_intervento = ?))))"
				+ "*100/ST_AREA((");
		sql.append("SELECT ");
		sql.append("ST_UNION (geometria) as geomIntSelvUnion ");
		sql.append("FROM (");
		sql.append("SELECT ");
		sql.append("idf_geo_pl_lotto_intervento.fk_intervento AS id_intervento,"
				+ "st_union(idf_geo_pl_lotto_intervento.geometria) AS geometria ");
		sql.append("FROM ");
		sql.append("idf_geo_pl_lotto_intervento ");
		sql.append("GROUP BY ");
		sql.append("idf_geo_pl_lotto_intervento.fk_intervento ");
		sql.append("UNION ");
		sql.append("SELECT ");
		sql.append("idf_geo_ln_lotto_intervento.id_intervento,"
				+ "st_union(st_buffer(idf_geo_ln_lotto_intervento.geometria,"
				+ " 1::double precision)) AS geometria ");
		sql.append("FROM ");
		sql.append("idf_geo_ln_lotto_intervento ");
		sql.append("GROUP BY ");
		sql.append("idf_geo_ln_lotto_intervento.id_intervento ");
		sql.append("UNION ");
		sql.append("SELECT ");
		sql.append("idf_geo_pt_lotto_intervento.id_intervento,"
				+ "st_union(st_buffer(idf_geo_pt_lotto_intervento.geometria, "
				+ "1::double precision)) AS geometria ");
		sql.append("FROM ");
		sql.append("idf_geo_pt_lotto_intervento ");
		sql.append("GROUP BY ");
		sql.append("idf_geo_pt_lotto_intervento.id_intervento)as sbqry ");
		sql.append("WHERE ");
		sql.append("sbqry.id_intervento = ?"
				+ ")) AS percentuale_Ricadenza ");
		sql.append("FROM ");
		sql.append("idf_geo_pl_ppr_tavola2 igppt ");
		sql.append("WHERE ");
		sql.append("ST_Intersects (( ");
		sql.append("SELECT ");
		sql.append("ST_UNION (geometria) as geomIntSelvUnion ");
		sql.append("FROM ( ");
		sql.append("SELECT ");
		sql.append("idf_geo_pl_lotto_intervento.fk_intervento AS id_intervento,"
				+ " st_union(idf_geo_pl_lotto_intervento.geometria) AS geometria ");
		sql.append("FROM ");
		sql.append("idf_geo_pl_lotto_intervento ");
		sql.append("GROUP BY ");
		sql.append("idf_geo_pl_lotto_intervento.fk_intervento ");
		sql.append("UNION ");
		sql.append("SELECT ");
		sql.append(" idf_geo_ln_lotto_intervento.id_intervento,"
				+ " st_union(st_buffer(idf_geo_ln_lotto_intervento.geometria,"
				+ " 1::double precision)) AS geometria ");
		sql.append("FROM ");
		sql.append("idf_geo_ln_lotto_intervento ");
		sql.append("GROUP BY ");
		sql.append("idf_geo_ln_lotto_intervento.id_intervento ");
		sql.append("UNION ");
		sql.append("SELECT ");
		sql.append("idf_geo_pt_lotto_intervento.id_intervento,"
				+ " st_union(st_buffer(idf_geo_pt_lotto_intervento.geometria,"
				+ " 1::double precision)) AS geometria ");
		sql.append("FROM ");
		sql.append("idf_geo_pt_lotto_intervento ");
		sql.append("GROUP BY ");
		sql.append("idf_geo_pt_lotto_intervento.id_intervento"
				+ " ) as sbqry"
				+ " where sbqry.id_intervento = ? "
				+ "), igppt.geometria) ");
		sql.append("GROUP BY ");
		sql.append("igppt.tipo_vincolo, igppt.nome_vincolo, igppt.provvedimento ");
		sql.append("order by ");
		sql.append("igppt.tipo_vincolo, igppt.nome_vincolo;");
		
		return DBUtil.jdbcTemplate.query(sql.toString(), 
				new Object[] {idIntervento,idIntervento,idIntervento}, new RicadenzaInterventoMapper());

	}

}
