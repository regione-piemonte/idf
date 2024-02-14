/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.exceptions.DuplicateRecordException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.impl.dao.GeoPlPfaDAO;
import it.csi.idf.idfapi.dto.GeoPfaSearch;
import it.csi.idf.idfapi.dto.GeoPfaSearchDettaglio;
import it.csi.idf.idfapi.dto.GeoPlPfa;
import it.csi.idf.idfapi.dto.LabelValore;
import it.csi.idf.idfapi.mapper.GeoPfaDettaglioSearchMapper;
import it.csi.idf.idfapi.mapper.GeoPfaSearchMapper;
import it.csi.idf.idfapi.mapper.GeoPlPfaMapper;
import it.csi.idf.idfapi.mapper.LabelValoreMapper;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.GenericDAO;
import it.csi.idf.idfapi.util.PaginatedList;

@Component
public class GeoPlPfaDAOImpl extends GenericDAO implements GeoPlPfaDAO {

	private static final String TO_DATE = "toDate";
	private static final String FROM_DATE = "fromDate";
	private static final String DENOMINAZIONE = "denominazione";
	private static final String ID_COMUNE = "idComune";
	private static final String ISTAT_PROV = "istatProv";
	private static final String ID_SOGGETTO = "idSoggetto";

	static final Logger logger = Logger.getLogger(GeoPlPfaDAOImpl.class);

	private static final String ASCENDING = "ASC";
	private static final String DESCENDING = "DESC";
	private static final char ORDER = '-';
	private final GeoPlPfaMapper geoPlPfaMapper = new GeoPlPfaMapper();
	private final GeoPfaSearchMapper geoPfaSearchMapper = new GeoPfaSearchMapper();
	private final GeoPfaDettaglioSearchMapper geoPfaDettaglioSearchMapper = new GeoPfaDettaglioSearchMapper();

	@Override
	public int createGeoPlPfa(GeoPlPfa newGeoPlPfa) throws DuplicateRecordException {

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO idf_geo_pl_pfa(");
		sql.append("idgeo_pl_pfa, denominazione, fonte_finanziamento, geometria, data_inizio_validita");
		sql.append(
				", data_fine_validita, data_approvazione, data_revisione, sup_pianificata_totale_ha, sup_forestale_gest_attiva_ha");
		sql.append(", note, utente_aggiornamento, n_delibera, durata_pfa_anni, flg_revisione");
		sql.append(
				", proprieta_silvopast_ha, proprieta_forestale_ha, superf_tot_boscata_ha, superf_bosc_gest_attiva_ha, superf_gest_non_attiva_tot_ha");
		sql.append(
				", superf_gest_non_attiva_mon_ha, superf_gest_non_attiva_evl_ha, superf_altri_usi_ha, nome_breve_pfa, fk_versione");
		sql.append(
				", data_inserimento, data_aggiornamento, prop_non_forestale_ha, sup_pianif_non_forestale_ha, sup_pianif_forestale_ha)");
		sql.append(
				" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		List<Object> params = new ArrayList<>();

		params.add(newGeoPlPfa.getIdGeoPlPfa());
		params.add(newGeoPlPfa.getDenominazione());
		params.add(newGeoPlPfa.getFonteFinanziamento());
		params.add(newGeoPlPfa.getGeometry());
		params.add(newGeoPlPfa.getDataInizioValidita());
		params.add(newGeoPlPfa.getDataFineValidita());
		params.add(newGeoPlPfa.getDataApprovazione());
		params.add(newGeoPlPfa.getDataRevisione());
		params.add(newGeoPlPfa.getSupPianificataTotale());
		params.add(newGeoPlPfa.getSupForestaleGestAttiva());
		params.add(newGeoPlPfa.getNote());
		params.add(newGeoPlPfa.getUtenteAggiornamento());
		params.add(newGeoPlPfa.getnDelibera());
		params.add(newGeoPlPfa.getDurataPfaAnni());
		params.add(newGeoPlPfa.getFlgRevisione());
		params.add(newGeoPlPfa.getProprietaSilvopastHa());
		params.add(newGeoPlPfa.getProprietaForestaleHa());
		params.add(newGeoPlPfa.getSuperfTotBoscataHa());
		params.add(newGeoPlPfa.getSuperfBoscGestAttivaHa());
		params.add(newGeoPlPfa.getSuperfGestNonAttivaTotHa());
		params.add(newGeoPlPfa.getSuperfGestNonAttivaMonHa());
		params.add(newGeoPlPfa.getSuperfGestNonAttivaEvlHa());
		params.add(newGeoPlPfa.getSuperfAltriUsiHa());
		params.add(newGeoPlPfa.getNomeBrevePfa());
		params.add(newGeoPlPfa.getFk_versione());
		params.add(newGeoPlPfa.getDataInserimento());
		params.add(newGeoPlPfa.getDataAggiornamento());
		params.add(newGeoPlPfa.getPropNonForestaleHa());
		params.add(newGeoPlPfa.getSupPianifNonForestaleHa());
		params.add(newGeoPlPfa.getSupPianifForestaleHa());

		return DBUtil.jdbcTemplate.update(sql.toString(), params);
	}

	@Override
	public GeoPlPfa findGeoPlPfaById(Integer idGeoPlPfa) throws RecordNotFoundException {

		String query = "SELECT * FROM idf_geo_pl_pfa geo WHERE geo.idgeo_pl_pfa = ? ";
		return DBUtil.jdbcTemplate.queryForObject(query, geoPlPfaMapper, idGeoPlPfa);
	}

	@Override
	public List<GeoPlPfa> findAllGeoPlPfa() {

		String query = "SELECT * FROM idf_geo_pl_pfa";
		return DBUtil.jdbcTemplate.query(query, geoPlPfaMapper);
	}
	
	@Override
	public boolean isPfaEntePubblico(Integer idIntervento) {
		String query = "SELECT count(idgeo_pl_pfa) "
				+ " from idf_geo_pl_pfa inner join idf_t_interv_selvicolturale using(idgeo_pl_pfa) "
				+ " WHERE id_intervento = ? and fk_proprieta_primpa in (39,40,41) ";
		 return DBUtil.jdbcTemplate.queryForObject(query,Integer.class, idIntervento) > 0;
	}

	@Override
	public GeoPfaSearchDettaglio findSearchPfaByID(StringBuilder s, List<Object> parameters) {

		return DBUtil.jdbcTemplate.queryForObject(s.toString(), geoPfaDettaglioSearchMapper, parameters.toArray());
	}

	@Override
	public PaginatedList<GeoPfaSearchDettaglio> search(StringBuilder s, int page, int limit, List<Object> parameters) {

		return super.paginatedList(s.toString(), parameters, geoPfaDettaglioSearchMapper, page, limit);
	}

	@Override
	public PaginatedList<GeoPfaSearch> searchPianiForestali(Map<String, String> queryParams) {

		List<Object> parameters = new ArrayList<>();
		StringBuilder sql = createWhereClauseForSearch(queryParams, createMainQueryForSearch(), parameters);
		logger.info("createMainQueryForSearch - sql: " + sql.toString());
		return super.paginatedList(sql.toString(), parameters, geoPfaSearchMapper,
				Integer.parseInt(queryParams.get("page")), Integer.parseInt(queryParams.get("limit")));
	}

	@Override
	public GeoPfaSearchDettaglio pianoForestaleSearchDettaglio(Integer idGeoPlPfa) {

		StringBuilder sql = new StringBuilder();
		sql.append(createDettaglioQueryForSearch());
		sql.append(" WHERE pfa.idgeo_pl_pfa = ?");
//		sql.append(" GROUP BY pfa.idgeo_pl_pfa, prov.denominazione_prov, prov.istat_prov");
		sql.append(" GROUP BY pfa.idgeo_pl_pfa ");
		
		logger.info("pianoForestaleSearchDettaglio - sql: " + sql.toString() + " - idGeoPlPfa: " + idGeoPlPfa);
		
		List<GeoPfaSearchDettaglio> list = DBUtil.jdbcTemplate.query(sql.toString(), geoPfaDettaglioSearchMapper, idGeoPlPfa);

		return list.get(0);

	}
	
	@Override
	public GeoPfaSearchDettaglio pianoForestaleSearchDettaglioByUser(Integer idGeoPlPfa, Integer idSoggetto) {

		StringBuilder sql = new StringBuilder();
		sql.append(createDettaglioQueryForSearch());
		sql.append(" WHERE pfa.idgeo_pl_pfa = ? and pg.id_soggetto_coinvolto = ? ");
//		sql.append(" GROUP BY pfa.idgeo_pl_pfa, prov.denominazione_prov, prov.istat_prov");
		sql.append(" GROUP BY pfa.idgeo_pl_pfa ");
		
		logger.info("pianoForestaleSearchDettaglio - sql: " + sql.toString() 
			+ " - idGeoPlPfa: " + idGeoPlPfa + " - idSoggetto: " + idSoggetto);
		
		List<GeoPfaSearchDettaglio> list = DBUtil.jdbcTemplate.query(sql.toString(), geoPfaDettaglioSearchMapper, idGeoPlPfa, idSoggetto);

		return list!=null && list.size() > 0? list.get(0):null ;
	}

	private String createDettaglioQueryForSearch() {

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT pfa.idgeo_pl_pfa, pfa.denominazione, pfa.data_inizio_validita, pfa.data_fine_validita");
		sql.append(", pfa.data_approvazione, pfa.data_revisione, pfa.geometria, pfa.fonte_finanziamento");
		sql.append(", pfa.flg_revisione, pfa.prop_non_forestale_ha, pfa.sup_pianif_non_forestale_ha");
		sql.append(", pfa.proprieta_silvopast_ha, pfa.proprieta_forestale_ha");
		sql.append(", pfa.superf_bosc_gest_attiva_ha, pfa.sup_pianif_forestale_ha");
		sql.append(", pfa.superf_gest_non_attiva_mon_ha, pfa.superf_gest_non_attiva_tot_ha");
		sql.append(", pfa.superf_gest_non_attiva_evl_ha");
		sql.append(", ARRAY_AGG(DISTINCT soggeto.denominazione) AS gestori");
		sql.append(", STRING_AGG(DISTINCT comune.denominazione_comune, ', ') AS denominazione_comuni");
		sql.append(", ARRAY_AGG(DISTINCT comune.id_comune) AS id_comuni");
		sql.append(", STRING_AGG(DISTINCT prov.denominazione_prov, ', ') AS denominazione_provincie");
		sql.append(", ARRAY_AGG(DISTINCT prov.istat_prov) AS istat_provincie");
		sql.append(", STRING_AGG(DISTINCT tab.descr_proprieta, ', ') AS descr_propriete, n_delibera");

		sql.append(" FROM idf_geo_pl_pfa pfa");
		sql.append(" left join idf_r_pfa_pg pg on pfa.idgeo_pl_pfa = pg.idgeo_pl_pfa and pg.id_tipo_soggetto = 5");
		sql.append(" LEFT JOIN idf_t_soggetto soggeto ON soggeto.id_soggetto = pg.id_soggetto_coinvolto");
		sql.append(" LEFT JOIN idf_r_comune_pfa com ON pfa.idgeo_pl_pfa = com.idgeo_pl_pfa");
		sql.append(" LEFT JOIN idf_geo_pl_comune comune ON com.id_comune = comune.id_comune");
		sql.append(" LEFT JOIN idf_geo_pl_provincia prov ON comune.istat_prov = prov.istat_prov");
		sql.append(" left join (select distinct	catasto.idgeo_pl_pfa, proprieta.descr_proprieta from idf_geo_pl_prop_catasto catasto");
		sql.append(" join idf_d_proprieta proprieta on catasto.fk_proprieta = proprieta.id_proprieta ) as tab on pfa.idgeo_pl_pfa = tab.idgeo_pl_pfa");

		return sql.toString();
	}

	private String createMainQueryForSearch() {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT distinct pfa.idgeo_pl_pfa, pfa.denominazione, pfa.data_inizio_validita");
		sql.append(", pfa.data_fine_validita, pfa.data_approvazione, pfa.geometria");
		sql.append(", STRING_AGG(DISTINCT comune.denominazione_comune, ', ') AS denominazione_comuni");
		sql.append(", ARRAY_AGG(DISTINCT comune.id_comune) AS id_comuni");
		sql.append(", STRING_AGG(DISTINCT prov.denominazione_prov, ', ') AS denominazione_provincie");
		sql.append(", STRING_AGG(DISTINCT prov.sigla_prov, ', ') AS sigla_provincie");
		sql.append(", ARRAY_AGG(DISTINCT prov.istat_prov) AS istat_provincie");
		sql.append(" FROM idf_geo_pl_pfa pfa");
		sql.append(" LEFT JOIN idf_r_pfa_pg pg ON pfa.idgeo_pl_pfa = pg.idgeo_pl_pfa");
		sql.append(" LEFT JOIN idf_r_comune_pfa com ON pfa.idgeo_pl_pfa = com.idgeo_pl_pfa");
		sql.append(" LEFT JOIN idf_geo_pl_comune comune ON com.id_comune = comune.id_comune");
		sql.append(" LEFT JOIN idf_geo_pl_provincia prov ON comune.istat_prov = prov.istat_prov");
		return sql.toString();
	}

	private StringBuilder createWhereClauseForSearch(Map<String, String> queryParams, String mainQuery,
			List<Object> parameters) {

		StringBuilder sql = new StringBuilder();
		sql.append(mainQuery);
		sql.append(" where pfa.idgeo_pl_pfa > 0 ");
		
		String sep = " AND ";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		if (queryParams.get(ID_SOGGETTO) != null) {
			sql.append(sep).append(" pg.id_soggetto_coinvolto = ? ");
			parameters.add(Integer.parseInt(queryParams.get(ID_SOGGETTO)));
			sep = " AND ";
		}
		if (queryParams.get(ISTAT_PROV) != null) {
			sql.append(sep).append(" prov.istat_prov = ? ");
			parameters.add(queryParams.get(ISTAT_PROV));
			sep = " AND ";
		}
		if (queryParams.get(ID_COMUNE) != null) {
			sql.append(sep).append(" comune.id_comune = ? ");
			parameters.add(Integer.parseInt(queryParams.get(ID_COMUNE)));
			sep = " AND ";
		}
		if (queryParams.get(DENOMINAZIONE) != null) {
			sql.append(sep).append(" upper(pfa.denominazione) LIKE CONCAT('%', ?, '%')");
			parameters.add(queryParams.get(DENOMINAZIONE).toUpperCase());
			sep = " AND ";
		}
		if (queryParams.get(FROM_DATE) != null) {
			Date date;
			try {
				date = formatter.parse(queryParams.get(FROM_DATE));
				sql.append(sep).append(" pfa.data_fine_validita >= ? ");
				parameters.add(date);
			} catch (ParseException e) {
				logger.error(e.getMessage());
			}
			sep = " AND ";
		}
		if (queryParams.get(TO_DATE) != null) {
			Date date;
			try {
				date = formatter.parse(queryParams.get(TO_DATE));
				sql.append(sep).append(" pfa.data_inizio_validita <= ? ");
				parameters.add(date);
			} catch (ParseException e) {
				logger.error(e.getMessage());
			}
		}
		//sql.append(" GROUP BY pfa.idgeo_pl_pfa, prov.denominazione_prov, prov.istat_prov ");
		sql.append(" GROUP BY pfa.idgeo_pl_pfa  ");
		return sql.append(getQuerySortingSegment(queryParams.get("sort")));
	}

	private String getQuerySortingSegment(String sort) {
		String sortField = null;
		String sortOrder = null;

		StringBuilder sql = new StringBuilder();
		if (sort != null && !sort.isEmpty()) {
			sortOrder = sort.charAt(0) == ORDER ? DESCENDING : ASCENDING;
			sortField = sortOrder.equals(DESCENDING) ? sort.substring(1) : sort;
			if (sortField != null && !sortField.isEmpty()) {
				sql.append(" ORDER BY ");
				sql.append(translateFieldName(sortField)).append(" ").append(sortOrder);
			}
		}

		return sql.toString();
	}

	private static String translateFieldName(String fieldName) {
		switch (fieldName) {
		case DENOMINAZIONE:
			return DENOMINAZIONE;
		case "denominazioneProvincie":
			return "denominazione_provincie";
		case "denominazioneComuni":
			return "denominazione_comuni";
		case "dataApprovazione":
			return "data_approvazione";
		case "dataInizioValiditaString":
			return "data_inizio_validita";
		case "dataFineValidita":
			return "data_fine_validita";
		default:
			return DENOMINAZIONE;
		}
	}

	@Override
	public List<LabelValore> getDescrizioniPfa(String istatProv, Integer idComune) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT distinct pfa.denominazione as label, pfa.denominazione as valore  ");
		sql.append("FROM idf_geo_pl_pfa pfa LEFT JOIN idf_r_pfa_pg pg ON pfa.idgeo_pl_pfa = pg.idgeo_pl_pfa ");
		sql.append("LEFT JOIN idf_r_comune_pfa com ON pfa.idgeo_pl_pfa = com.idgeo_pl_pfa "); 
		sql.append("LEFT JOIN idf_geo_pl_comune comune ON com.id_comune = comune.id_comune "); 
		sql.append("LEFT JOIN idf_geo_pl_provincia prov ON comune.istat_prov = prov.istat_prov ");
		sql.append(" where pfa.idgeo_pl_pfa > 0");
		
		String sep = " and ";
		List<Object> parameters = new ArrayList<>();
		if(istatProv != null) {
			sql.append(sep).append("prov.istat_prov = ? ");
			parameters.add(istatProv);
			sep = " AND ";
		}
		if(idComune != null) {
			sql.append(sep).append("comune.id_comune = ? ");
			parameters.add(idComune);
		}
		sql.append("order by pfa.denominazione");
		return DBUtil.jdbcTemplate.query(sql.toString(),parameters.toArray(), new LabelValoreMapper());
	}
}
