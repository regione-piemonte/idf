/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.impl.dao.EventoDAO;
import it.csi.idf.idfapi.dto.DrawedGeometryInfo;
import it.csi.idf.idfapi.dto.EventoDTO;
import it.csi.idf.idfapi.dto.EventoPiano;
import it.csi.idf.idfapi.dto.PlainSecondoPfaEvento;
import it.csi.idf.idfapi.dto.ProgressivoNomeBreve;
import it.csi.idf.idfapi.mapper.DrawedGeometryInfoMapper;
import it.csi.idf.idfapi.mapper.EventoDTOMapper;
import it.csi.idf.idfapi.mapper.EventoPianoMapper;
import it.csi.idf.idfapi.mapper.PlainSecondoPfaEventoMapper;
import it.csi.idf.idfapi.mapper.ProgressivoNomeBreveMapper;
import it.csi.idf.idfapi.util.AIKeyHolderUtil;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.GenericDAO;
import it.csi.idf.idfapi.util.PaginatedList;

@Component
public class EventoDAOImpl extends GenericDAO implements EventoDAO {
	
	private static Logger logger = Logger.getLogger(EventoDAOImpl.class);
	
	private static final String ASCENDING = "ASC";
	private static final String DESCENDING = "DESC";
	private static final char ORDER = '-';
	
	private static final String[] eventoPianoColumns = new String[] {
			"evento.id_evento", "evento.progressivo_evento_pfa", "evento.nome_breve", "evento.data_evento"
			, "string_agg(distinct plpfa.denominazione::varchar,', '  order by plpfa.denominazione::varchar ) as denominazione"
			, "ARRAY_AGG(parFore.cod_particella_for) as cod_particella_for"
			, "ARRAY_AGG(parFore.idgeo_pl_particella_forest) as idgeo_pl_particella_forest"
			, "ARRAY_AGG(parFore.denominazione_particella) as denominazione_particella"
			, "evento.fk_tipo_evento", "evento.descr_evento", "evento.localita"
			, "evento.sup_interessata_ha, sum(perc_danno) as perc_danno, descr_tipo_evento"};
	
	private final EventoDTOMapper eventoMapper = new EventoDTOMapper();
	private final EventoPianoMapper eventoPianoMapper = new EventoPianoMapper();
	private final PlainSecondoPfaEventoMapper secondoPfaEventomapper = new PlainSecondoPfaEventoMapper();
	private final ProgressivoNomeBreveMapper progressivoNomeBreveMapper = new ProgressivoNomeBreveMapper();

	@Override
	public List<EventoDTO> findAllEventi() {
		StringBuilder sql = getEventoMainQuery();
		sql.append(getEventoMainQueryGroup());
		return DBUtil.jdbcTemplate.query(sql.toString(), eventoMapper);
	}

	@Override
	public EventoDTO findEventoById(Integer idEvento) throws RecordNotFoundException,EmptyResultDataAccessException {
		
		StringBuilder sql = getEventoMainQuery();
		sql.append(" WHERE evento.id_evento = ?");
		sql.append(getEventoMainQueryGroup());
		
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), eventoMapper, idEvento);
		
	}

	@Override
	public int createEvento(EventoDTO evento) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO idf_t_evento(");
		sql.append("idgeo_pl_pfa, fk_tipo_evento, nome_breve, data_evento, progressivo_evento_pfa");
		sql.append(", descr_evento, localita, sup_interessata_ha, annata_silvana)");
		sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		List<Object>parameters = new ArrayList<>();
		
		parameters.add(evento.getIdPfa());
		parameters.add(evento.getTipoEvento());
		parameters.add(evento.getNomeBreve());
		parameters.add(evento.getDataEvento() == null ? null : Date.valueOf(evento.getDataEvento()));
		parameters.add(evento.getProgressivoEventoPfa());
		parameters.add(evento.getDescrEvento());
		parameters.add(evento.getLocalita());
		parameters.add(evento.getSupInteressataHa());
		parameters.add(evento.getAnnataSilvana());
		
		return AIKeyHolderUtil.updateWithGenKey("id_evento", sql.toString(), parameters);
	}
	
	@Override
	public void updateEvento(PlainSecondoPfaEvento evento, Integer idEvento) throws RecordNotFoundException {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE idf_t_evento ");
		sql.append("SET fk_tipo_evento = ?, nome_breve = ?, data_evento = ?");
		sql.append(", descr_evento = ?, localita = ?, sup_interessata_ha = ?, annata_silvana = ? ");
		sql.append(" WHERE id_evento = ? ");
		
		List<Object>parameters = new ArrayList<>();
		
		parameters.add(evento.getTipoEvento());
		parameters.add(evento.getNomeBreve());
		parameters.add(evento.getDataEvento()== null?null:java.sql.Date.valueOf(evento.getDataEvento()));
		parameters.add(evento.getDescrEvento());
		parameters.add(evento.getLocalita());
		parameters.add(evento.getSupInteressataHa());
		parameters.add(evento.getAnnataSilvana());
		parameters.add(idEvento);
		
		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
		
	}

	@Override
	public void deleteEvento(Integer idEvento) throws RecordNotFoundException {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("DELETE FROM idf_t_evento ");
		sql.append(" WHERE id_evento = ?; ");
		
		DBUtil.jdbcTemplate.update(sql.toString(),idEvento);
		
	}
	
	@Override
	public PaginatedList<EventoPiano> findEventiPianoDTOByIdGeoPlPfa(int idGeoPlPfa, int page, int limit, String sort) {
		StringBuilder sql = getEventoPianoMainQuery(eventoPianoColumns);
		sql.append(" WHERE parFore.idgeo_pl_pfa = ? ")
		.append(" GROUP BY evento.id_evento,descr_tipo_evento");
		if(sort != null) {
			sql.append(getQuerySortingSegment(sort));
		}else {
			sql.append(" ORDER BY progressivo_evento_pfa DESC ");
		}
		logger.info("findEventiPianoDTOByIdGeoPlPfa sql: " + sql.toString());
		return super.paginatedList(sql.toString(), Arrays.asList(idGeoPlPfa), eventoPianoMapper, page, limit);
	}
	
	@Override
	public List<EventoPiano> findEventiPianoDTOByIdGeoPlPfa(int idGeoPlPfa) {
		StringBuilder sql = getEventoPianoMainQuery(eventoPianoColumns);
		sql.append(" WHERE parFore.idgeo_pl_pfa = ? ");
		sql.append(" GROUP BY evento.id_evento,descr_tipo_evento");
		sql.append(" ORDER BY progressivo_evento_pfa DESC ");
		logger.info("findEventiPianoDTOByIdGeoPlPfa - sql: " + sql.toString() + " - idGeoPlPfa: " + idGeoPlPfa);
		return DBUtil.jdbcTemplate.query(sql.toString(), eventoPianoMapper, idGeoPlPfa);
	}
	
	@Override
	public int getProssimoProgressivoEventoPfa(int idGeoPlPfa) {
		return DBUtil.jdbcTemplate.queryForInt(
				"SELECT COALESCE(MAX(progressivo_evento_pfa), 0) " + 
				"FROM idf_t_evento evento " + 
				"INNER JOIN idf_r_evento_partfor partfor ON evento.id_evento = partfor.id_evento " + 
				"LEFT JOIN idf_geo_pl_particella_forest parFore ON partfor.idgeo_pl_particella_forest = parFore.idgeo_pl_particella_forest " + 
				"WHERE parFore.idgeo_pl_pfa = ?",
				idGeoPlPfa) + 1;
	}
	
	@Override
	public PlainSecondoPfaEvento findSecondoPfaEventoById(Integer idEvento) {
		String[] eventoPianoColumns = new String[] {
				"evento.id_evento", "evento.progressivo_evento_pfa", "evento.nome_breve", "evento.data_evento"
				, "ARRAY_AGG(parFore.cod_particella_for) as cod_particella_for"
				, "ARRAY_AGG(parFore.idgeo_pl_particella_forest) as idgeo_pl_particella_forest"
				, "ARRAY_AGG(parFore.denominazione_particella) as denominazione_particella"
				, "evento.fk_tipo_evento", "evento.descr_evento", "evento.localita", "evento.sup_interessata_ha"};
		
		
		StringBuilder sql = getSecondoPfaEventoQuery(eventoPianoColumns);
		sql.append(" WHERE evento.id_evento = ? ");
		sql.append(" GROUP BY evento.id_evento");
		
		logger.info("findSecondoPfaEventoById sql: " + sql.toString() + " - idEvento: " + idEvento);
		
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), secondoPfaEventomapper, idEvento);
	}

	@Override
	public List<ProgressivoNomeBreve> findProgressiviNomeBreve(int idGeoPlPfa) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT evento.progressivo_evento_pfa, evento.nome_breve, evento.id_evento");
		sql.append(" FROM idf_t_evento evento");
		sql.append(" where evento.idgeo_pl_pfa = ? ");
		sql.append(" GROUP BY evento.id_evento order by  evento.id_evento  desc ");
		logger.info("findProgressiviNomeBreve sql: " + sql.toString() + " -- idGeoPlPfa: " + idGeoPlPfa);
		return DBUtil.jdbcTemplate.query(sql.toString(), progressivoNomeBreveMapper, idGeoPlPfa); 
	}
	
	private StringBuilder getEventoMainQuery() {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT distinct evento.id_evento, evento.progressivo_evento_pfa, evento.nome_breve, evento.data_evento");
		sql.append(", ARRAY_AGG(parFore.idgeo_pl_particella_forest) as idgeo_pl_particella_forest");
		sql.append(", ARRAY_AGG(parFore.cod_particella_for) as cod_particella_for");
		sql.append(", ARRAY_AGG(parFore.denominazione_particella) as denominazione_particella");		
		sql.append(", evento.fk_tipo_evento, evento.descr_evento, evento.localita");
		sql.append(", evento.sup_interessata_ha, partfor.perc_danno, evento.annata_silvana");
		sql.append(" FROM idf_t_evento evento");
		sql.append(" INNER JOIN idf_d_tipo_evento tipo ON evento.fk_tipo_evento = tipo.id_tipo_evento");
		sql.append(" INNER JOIN idf_r_evento_partfor partfor ON evento.id_evento = partfor.id_evento");
		sql.append(" LEFT JOIN idf_geo_pl_particella_forest parFore ON partfor.idgeo_pl_particella_forest = parFore.idgeo_pl_particella_forest");
		
		return sql;
	}
	
	private StringBuilder getEventoMainQueryGroup() {
		StringBuilder sql = new StringBuilder();
		sql.append(" group by evento.id_evento, evento.progressivo_evento_pfa, evento.nome_breve, evento.data_evento ");
		sql.append(" , evento.fk_tipo_evento, evento.descr_evento, evento.localita ");
		sql.append(", evento.sup_interessata_ha, partfor.perc_danno, evento.annata_silvana ");
		return sql;
	}
	
	private StringBuilder getEventoPianoMainQuery(String[] columnNames) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append(DBUtil.createQueryListOfColumnsForInsert(columnNames));
		sql.append(" FROM idf_t_evento evento");
		sql.append(" INNER JOIN idf_d_tipo_evento tipo ON evento.fk_tipo_evento = tipo.id_tipo_evento");
		sql.append(" INNER JOIN idf_r_evento_partfor partfor ON evento.id_evento = partfor.id_evento");
		sql.append(" LEFT JOIN idf_geo_pl_particella_forest parFore ON partfor.idgeo_pl_particella_forest = parFore.idgeo_pl_particella_forest");
		sql.append(" INNER JOIN idf_geo_pl_pfa plpfa ON parFore.idgeo_pl_pfa = plpfa.idgeo_pl_pfa");
		
		return sql;
	}
	
	private StringBuilder getSecondoPfaEventoQuery(String[] columnNames) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append(DBUtil.createQueryListOfColumnsForInsert(columnNames));
		sql.append(", ARRAY_AGG(partfor.perc_danno) as perc_danni");
		sql.append(", evento.annata_silvana");
		sql.append(" FROM idf_t_evento evento");
		sql.append(" INNER JOIN idf_d_tipo_evento tipo ON evento.fk_tipo_evento = tipo.id_tipo_evento");
		sql.append(" INNER JOIN idf_r_evento_partfor partfor ON evento.id_evento = partfor.id_evento");
		sql.append(" LEFT JOIN idf_geo_pl_particella_forest parFore ON partfor.idgeo_pl_particella_forest = parFore.idgeo_pl_particella_forest");
		
		return sql;
	}

	private static String getQuerySortingSegment(String sort) {
		String sortField = null;
		String sortOrder = null;

		StringBuilder sql = new StringBuilder();
		if (sort != null && !sort.isEmpty()) {
			sortOrder = sort.charAt(0) == ORDER ? DESCENDING : ASCENDING;
			sortField = sortOrder.equals(DESCENDING) ? sort.substring(1) : sort;
			if (sortField != null && !sortField.isEmpty()) {
				sql.append(" ORDER BY ");
				sql.append(translateFieldNameForBackOffice(sortField)).append(" ").append(sortOrder);
			}
		}
		return sql.toString();
	}
	
	private static String translateFieldNameForBackOffice(String fieldName) {
		Map<String, String> mappedColumnNames = new HashMap<>();
		mappedColumnNames.put("idEvento", "id_evento");
		mappedColumnNames.put("nomeBreve", "nome_breve");
		mappedColumnNames.put("dataEvento", "data_evento");
		mappedColumnNames.put("idgeoPlParticelaForest", "idgeo_pl_particella_forest");
		mappedColumnNames.put("tipoEvento", "fk_tipo_evento");
		mappedColumnNames.put("descrEvento", "descr_evento");
		mappedColumnNames.put("localita", "localita");
		mappedColumnNames.put("progressivoEventoPfa", "progressivo_evento_pfa");
		mappedColumnNames.put("supInteressataHa", "sup_interessata_ha");
		mappedColumnNames.put("percentualeDanno", "perc_danno");
		
		return mappedColumnNames.get(fieldName) != null ? mappedColumnNames.get(fieldName) : "id_evento";
	}
	
	@Override
	public Integer getIdgeoPlPfaByIdEvento(Integer idEvento) {
		String sql = "select idgeo_pl_pfa from idf_t_evento ite where id_evento = ?";
		List<Integer> res = DBUtil.jdbcTemplate.queryForList(sql.toString(),new Object[]{idEvento},Integer.class);
		return res.size()>0?res.get(0):null;
	}

	@Override
	public List<DrawedGeometryInfo> getDrawedGeometryList(Integer idEvento) {
		StringBuilder sql = new StringBuilder();
		sql.append("select 'Poligono' as geomtype, descrizione, ");
		sql.append("string_agg(distinct pf.cod_particella_for::varchar(15),', '  ");
		sql.append("	order by pf.cod_particella_for::varchar(15) ) as partforest, ");
		sql.append("-1 as x_pun, -1 as y_pun, -1 as length_lin ");
		sql.append(",ROUND((ST_Area(ST_MakeValid(pl.geometria))/10000)::numeric , 4) as area_pol ");
		sql.append("from idf_geo_pl_evento pl ");
		sql.append("left join Idf_geo_pl_particella_forest pf on ST_Intersects(pl.geometria , pf.geometria) ");
		sql.append("where pl.fk_evento = ? ");
		sql.append("group by geomtype, descrizione, x_pun, y_pun, length_lin, area_pol ");
		sql.append("union all ");
		sql.append("select 'Linea' as geomtype, descrizione, ");
		sql.append("string_agg(distinct pf.cod_particella_for::varchar(15),', '  ");
		sql.append("	order by pf.cod_particella_for::varchar(15) ) as partforest, ");
		sql.append("-1 as x_pun, -1 as y_pun, ST_Length(lin.geometria)  as length_lin ");
		sql.append(",-1 as area_pol ");
		sql.append("from idf_geo_ln_evento lin ");
		sql.append("left join Idf_geo_pl_particella_forest pf on ST_Intersects(lin.geometria , pf.geometria) ");
		sql.append("where lin.fk_evento = ? ");
		sql.append("group by geomtype, descrizione, x_pun, y_pun, length_lin, area_pol ");
		sql.append("union all  ");
		sql.append("select 'Punto' as geomtype, descrizione, ");
		sql.append("string_agg(distinct pf.cod_particella_for::varchar(15),', '  ");
		sql.append("	order by pf.cod_particella_for::varchar(15) ) as partforest, ");
		sql.append("ST_X(pt.geometria)as x_pun, ST_Y(pt.geometria) as y_pun, -1 as length_lin ");
		sql.append(",-1 as area_pol ");
		sql.append("from idf_geo_pt_evento pt ");
		sql.append("left join Idf_geo_pl_particella_forest pf on ST_Intersects(pt.geometria , pf.geometria) ");
		sql.append("where pt.fk_evento = ? ");
		sql.append("group by geomtype, descrizione, x_pun, y_pun, length_lin, area_pol ");
		List<Object>params = new ArrayList<>();
		params.add(idEvento);
		params.add(idEvento);
		params.add(idEvento);
		
		logger.info("getDrawedGeometryList sql: " + sql.toString() + " -- idEvento: " + idEvento);
		
		return DBUtil.jdbcTemplate.query(sql.toString(), new DrawedGeometryInfoMapper(), params.toArray()); 
	}
	
}
