/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.impl.dao.GeoPtAreaDiSaggioDAO;
import it.csi.idf.idfapi.dto.AdsDatiStazionaliOne;
import it.csi.idf.idfapi.dto.AdsDatiStazionaliTwo;
import it.csi.idf.idfapi.dto.AlberiCampioneDominanteDTO;
import it.csi.idf.idfapi.dto.AreaDISaggioDataStazionaliTwo;
import it.csi.idf.idfapi.dto.AreaDiSaggio;
import it.csi.idf.idfapi.dto.AreaDiSaggioDTO;
import it.csi.idf.idfapi.dto.AreaDiSaggioDatiGeneraliDTO;
import it.csi.idf.idfapi.dto.ComuneProvincia;
import it.csi.idf.idfapi.dto.GeoPtAreaDiSaggio;
import it.csi.idf.idfapi.dto.RelascopicaSempliceDTO;
import it.csi.idf.idfapi.dto.TSoggetto;
import it.csi.idf.idfapi.mapper.AlberiCampioneDominanteDTOMapper;
import it.csi.idf.idfapi.mapper.AlberiCavallettatiMapper;
import it.csi.idf.idfapi.mapper.AreaDiSaggioDTOMapper;
import it.csi.idf.idfapi.mapper.AreaDiSaggioDatiGeneraliMapper;
import it.csi.idf.idfapi.mapper.AreaDiSaggioMapper;
import it.csi.idf.idfapi.mapper.ComuneProvinciaMapper;
import it.csi.idf.idfapi.mapper.DatiStazionali1Mapper;
import it.csi.idf.idfapi.mapper.DatiStazionaliTwoMapper;
import it.csi.idf.idfapi.mapper.GeoPtAreaDiSaggioMapper;
import it.csi.idf.idfapi.mapper.RelascopicaSempliceMapper;
import it.csi.idf.idfapi.util.AIKeyHolderUtil;
import it.csi.idf.idfapi.util.AreaDiSaggioDefaults;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.GenericDAO;
import it.csi.idf.idfapi.util.PaginatedList;

@Component
public class GeoPtAreaDiSaggioDAOImpl extends GenericDAO implements GeoPtAreaDiSaggioDAO {
	
	private static final Logger logger = Logger.getLogger(GeoPtAreaDiSaggioDAOImpl.class);

	String [] areaDiSaggioColumns = {	"codice_ads", "fk_tipo_ads", "fk_ambito", "ambito_specifico", "data_ril", "fk_comune", "quota", "fk_esposizione", 
										"inclinazione", "idgeo_pl_particella_forest", "fk_proprieta", "fk_soggetto", "fk_settore", 
										"fk_assetto", "fk_comunita_montana", "fk_tipo_forestale", "fk_destinazione", "fk_priorita", "data_inizio_validita", 
										"fk_danno", "fk_tipo_intervento", "geometria", "fk_stato_ads"};

	String tableName = "idf_geo_pt_area_di_saggio";
	String pkColumnName = "idgeo_pt_ads";
	/*
	 * REQUIRED FIELDS FOR AREA DI Saggio
	 * 
	 * "idgeoPtAds", "codiceAds", "fkSettore", "fkProprieta", "fkTipo_ads", "fkAssetto", "fkEsposizione", "fkTipoForestale", "fkDestinazione", "fkPriorita", "dataInizioValidita", "fkAmbito", "fkDanno", "fkTipoIntervento"
	 * 
	 */
	@Override
	public PaginatedList<AreaDiSaggio> search(StringBuilder s, int page, int limit, List<Object> parameters) {
		return super.paginatedList(s.toString(), parameters, new AreaDiSaggioDTOMapper(), page, limit);
	}
	
	
	@Override
	public List<AreaDiSaggio> findAreaDiSaggioByCodiceAdsList(StringBuilder s) {
		return DBUtil.jdbcTemplate.query(s.toString(), new AreaDiSaggioMapper());

	}

	@Override
	public AreaDiSaggio findAreaDiSaggioByCodiceAds(StringBuilder s, List<Object> parameters)
			throws RecordNotFoundException {
		try {
			logger.debug("findAreaDiSaggioByCodiceAds - sql: " + s.toString());
			return DBUtil.jdbcTemplate.queryForObject(s.toString(), new AreaDiSaggioMapper(), parameters.toArray());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RecordNotFoundException();
		}
	}

	@Override
	public List<AlberiCampioneDominanteDTO> findAlberiCampioneDominante(StringBuilder s, List<Object> parameters) {
		logger.debug("findAlberiCampioneDominante - sql: " + s.toString());
		return DBUtil.jdbcTemplate.query(s.toString(), new AlberiCampioneDominanteDTOMapper(), parameters.toArray());
	}
	
	public int createAreaDiSaggioBasicData(AreaDiSaggioDatiGeneraliDTO areaDiSaggio) {
		StringBuilder sql = new StringBuilder();
		//	idgeo_pt_ads,
		sql.append("INSERT INTO idf_geo_pt_area_di_saggio(");
		// we must save all NOT NULL VALUES...WE wil put the default values for unknown stuff		
		sql.append("codiceAds, fk_comune, fkSettore, fkProprieta, fkTipo_ads, fkAssetto, fkEsposizione, fkTipoForestale, fkDestinazione, fkPriorita, dataInizioValidita, fkAmbito, fkDanno, fkTipoIntervento, geometria ");
		sql.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,ST_GeomFromText('POINT("); 
		sql.append(areaDiSaggio.getUtmEst()); 
		sql.append(" "); 
		sql.append(areaDiSaggio.getUtmNord()); 
		sql.append(")', 32632)");
		
		List<Object> parameters = new ArrayList<>();
		
		parameters.add(areaDiSaggio.getCodiceADS());
		parameters.add(areaDiSaggio.getComune());
		parameters.add(AreaDiSaggioDefaults.FK_SETTORE_DEFAULT);
		parameters.add(AreaDiSaggioDefaults.FK_PROPRIETA_DEFAULT);
		parameters.add(AreaDiSaggioDefaults.FK_TIPO_ADS_DEFAULT);
		parameters.add(AreaDiSaggioDefaults.FK_ASSETO_DEFAULT);
		parameters.add(AreaDiSaggioDefaults.FK_ESPOSIZIONE_DEFAULT);
		parameters.add(AreaDiSaggioDefaults.FK_TIPO_FORESTALE_DEFAULT);
		parameters.add(AreaDiSaggioDefaults.FK_DESTINAZIONE_DEFAULT);
		parameters.add(java.sql.Date.valueOf(LocalDate.now()));
		parameters.add(AreaDiSaggioDefaults.FK_AMBITO_DEFAULT);
		parameters.add(AreaDiSaggioDefaults.FK_DANNO_DEFAULT);
		parameters.add(AreaDiSaggioDefaults.FK_TIPO_INTERVENTO_DEFAULT);
		
		
		return AIKeyHolderUtil.updateWithGenKey("idgeo_pt_ads", sql.toString(), parameters);
		
	}
	
	@Override
	public int createEmptyAreaDiSaggio(AreaDiSaggioDTO areaDiSaggio, TSoggetto soggetto) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO idf_geo_pt_area_di_saggio(fk_tipo_ads, codice_ads) VALUES(?, ?)");
		List<Object> parameters = new ArrayList<>();
		parameters.add(Integer.parseInt(areaDiSaggio.getTipologiaDiRilievo()));
		parameters.add(areaDiSaggio.getCodiceADS());
		return AIKeyHolderUtil.updateWithGenKey("idgeo_pt_ads", sql.toString(), parameters);
	}

	@Override
	public int createAreaDiSaggio(AreaDiSaggioDTO areaDiSaggio, TSoggetto soggetto) throws RecordNotFoundException {
		StringBuilder sql = new StringBuilder();
		//	idgeo_pt_ads,
		sql.append("INSERT INTO idf_geo_pt_area_di_saggio(");
		sql.append("codice_ads, fk_tipo_ads, fk_ambito, ambito_specifico, data_ril, fk_comune, quota, fk_esposizione, ");// 8
		sql.append("inclinazione, idgeo_pl_particella_forest, fk_proprieta, fk_soggetto, fk_settore, ");// 5
		sql.append("fk_assetto,fk_comunita_montana,fk_tipo_forestale,fk_destinazione,fk_priorita,data_inizio_validita,fk_danno,fk_tipo_intervento,geometria, fk_stato_ads) ");// 8
		sql.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?");
		if(areaDiSaggio.getUtmEST() != null && areaDiSaggio.getUtmNORD() != null) {
			sql.append(",ST_GeomFromText('POINT(");
			sql.append(areaDiSaggio.getUtmEST());
			sql.append(" ");
			sql.append(areaDiSaggio.getUtmNORD());
			sql.append(")', 32632)");			
		}else {
			sql.append(",null ");
		}
		sql.append(", ?)");

		List<Object> parameters = new ArrayList<>();
		
//		parameters.add(Integer.parseInt(areaDiSaggio.getCodiceADS()));
		parameters.add(areaDiSaggio.getCodiceADS());
		parameters.add(areaDiSaggio.getTipologiaDiRilievo() != null ? Integer.parseInt(areaDiSaggio.getTipologiaDiRilievo()) : AreaDiSaggioDefaults.FK_TIPO_ADS_DEFAULT);
		parameters.add(areaDiSaggio.getIdDettaglioAmbito() != null ? areaDiSaggio.getIdDettaglioAmbito():AreaDiSaggioDefaults.FK_AMBITO_DEFAULT);
		parameters.add(areaDiSaggio.getAmbitoSpecifico());
		parameters.add(areaDiSaggio.getDataRilevamento() == null ? null
				: java.sql.Date.valueOf(areaDiSaggio.getDataRilevamento()));
		parameters.add(areaDiSaggio.getComune() == null || areaDiSaggio.getComune().length() == 0 ? null:Integer.parseInt(areaDiSaggio.getComune()));
		parameters.add(areaDiSaggio.getQuota()!=null ? Integer.parseInt(areaDiSaggio.getQuota()): null);
		parameters.add(areaDiSaggio.getEspozione() !=null ? Integer.parseInt(areaDiSaggio.getEspozione()) : AreaDiSaggioDefaults.FK_ESPOSIZIONE_DEFAULT);
		parameters.add(areaDiSaggio.getInclinazione()!= null ? Integer.parseInt(areaDiSaggio.getInclinazione()): null);
		parameters.add(areaDiSaggio.getParticellaForestale()!= null ? Integer.parseInt(areaDiSaggio.getParticellaForestale()): null);
		parameters.add(areaDiSaggio.getProprieta() !=null ? Integer.parseInt(areaDiSaggio.getProprieta()) :  AreaDiSaggioDefaults.FK_PROPRIETA_DEFAULT);
	
		parameters.add(soggetto.getIdSoggetto());
		
		parameters.add(AreaDiSaggioDefaults.FK_SETTORE_DEFAULT);
		parameters.add(AreaDiSaggioDefaults.FK_ASSETO_DEFAULT);//TODO: Somebody set 9 - check again
		parameters.add(1); // TODO: CHECK THIS Mocked Comunita montana
		parameters.add(AreaDiSaggioDefaults.FK_TIPO_FORESTALE_DEFAULT);// mocked fk_tipo_forestale
		parameters.add(AreaDiSaggioDefaults.FK_DESTINAZIONE_DEFAULT);
		parameters.add(AreaDiSaggioDefaults.FK_PRIORITA_DEFAULT);
		parameters.add(java.sql.Date.valueOf(LocalDate.now()));
		parameters.add(AreaDiSaggioDefaults.FK_DANNO_DEFAULT);
		parameters.add(AreaDiSaggioDefaults.FK_TIPO_INTERVENTO_DEFAULT);
		parameters.add(1); // Bozza Status  TODO: VS Add Enumration

		return AIKeyHolderUtil.updateWithGenKey("idgeo_pt_ads", sql.toString(), parameters);
	}
	
	@Override
	public int updateAreaDiSaggio(AreaDiSaggioDTO areaDiSaggio, TSoggetto soggetto) throws RecordNotFoundException {
		StringBuilder sql = new StringBuilder();
		int result = 0;
		if (soggetto == null ) throw new RuntimeException("Soggeto is not present");
		//	idgeo_pt_ads,
		sql.append("UPDATE idf_geo_pt_area_di_saggio ");
		sql.append(" SET codice_ads=?, fk_tipo_ads=?, fk_ambito=?, ambito_specifico=?, data_ril=?, fk_comune=?, quota=?, fk_esposizione=?, ");// 8
		sql.append(" inclinazione=?, idgeo_pl_particella_forest=?, fk_proprieta=?, fk_soggetto=?, fk_stato_ads=?");// 5		
		if(areaDiSaggio.getUtmEST()!=null && areaDiSaggio.getUtmNORD() != null) {
			sql.append(", geometria=ST_GeomFromText('POINT(");
			sql.append(areaDiSaggio.getUtmEST());
			sql.append(" ");
			sql.append(areaDiSaggio.getUtmNORD());
			sql.append(")', 32632) ");
		}
		//sql.append(" fk_stato_ads=? ");// 9		
		sql.append(" WHERE idgeo_pt_ads = ?");

		List<Object> parameters = new ArrayList<>();
		
		try {


			parameters.add(areaDiSaggio.getCodiceADS());
			parameters.add(areaDiSaggio.getTipologiaDiRilievo() != null ? Integer.parseInt(areaDiSaggio.getTipologiaDiRilievo()) : AreaDiSaggioDefaults.FK_TIPO_ADS_DEFAULT);
			parameters.add(areaDiSaggio.getIdDettaglioAmbito() != null ? areaDiSaggio.getIdDettaglioAmbito():AreaDiSaggioDefaults.FK_AMBITO_DEFAULT);
			parameters.add(areaDiSaggio.getAmbitoSpecifico());
			parameters.add(areaDiSaggio.getDataRilevamento() == null ? null
					: java.sql.Date.valueOf(areaDiSaggio.getDataRilevamento()));
			parameters.add(areaDiSaggio.getComune() != null && areaDiSaggio.getComune().length() > 0? Integer.parseInt(areaDiSaggio.getComune()):null);
			parameters.add(areaDiSaggio.getQuota()!=null ? Integer.parseInt(areaDiSaggio.getQuota()): null);
			parameters.add(areaDiSaggio.getEspozione() !=null ? Integer.parseInt(areaDiSaggio.getEspozione()) : AreaDiSaggioDefaults.FK_ESPOSIZIONE_DEFAULT);
			parameters.add(areaDiSaggio.getInclinazione()!= null ? Integer.parseInt(areaDiSaggio.getInclinazione()): null);
			parameters.add(areaDiSaggio.getParticellaForestale()!=null && !areaDiSaggio.getParticellaForestale().isEmpty() ? Integer.parseInt(areaDiSaggio.getParticellaForestale()): null);
			parameters.add(areaDiSaggio.getProprieta() !=null ? Integer.parseInt(areaDiSaggio.getProprieta()) :  AreaDiSaggioDefaults.FK_PROPRIETA_DEFAULT);		
		
			parameters.add(soggetto!=null ? soggetto.getIdSoggetto() : 0);
			parameters.add(1); //Adding BOZZA status
			
			/*parameters.add(AreaDiSaggioDefaults.FK_SETTORE_DEFAULT);
			parameters.add(AreaDiSaggioDefaults.FK_ASSETO_DEFAULT);//TODO: Somebody set 9 - check again
			parameters.add(1); // TODO: CHECK THIS Mocked Comunita montana
			parameters.add(AreaDiSaggioDefaults.FK_TIPO_FORESTALE_DEFAULT);// mocked fk_tipo_forestale
			parameters.add(AreaDiSaggioDefaults.FK_DESTINAZIONE_DEFAULT);
			parameters.add(AreaDiSaggioDefaults.FK_PRIORITA_DEFAULT);
			parameters.add(java.sql.Date.valueOf(LocalDate.now()));
			parameters.add(AreaDiSaggioDefaults.FK_DANNO_DEFAULT);
			parameters.add(AreaDiSaggioDefaults.FK_TIPO_INTERVENTO_DEFAULT);
			parameters.add(1); // Bozza Status  TODO: VS Add Enumration*/
			
			parameters.add(areaDiSaggio.getIdgeoPtAds());
			 
			result = DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
			
		}catch(Exception ex) {
			ex.printStackTrace();
			//throw ex;
			result = 1;
		}
		
		

		return result;
	}

	@Override
	public List<RelascopicaSempliceDTO> findRelascopica(StringBuilder s, List<Object> parameters) {
		return DBUtil.jdbcTemplate.query(s.toString(), new RelascopicaSempliceMapper(), parameters.toArray());
	}

	@Override
	public List<RelascopicaSempliceDTO> findRelascopicaCompleta(StringBuilder s, List<Object> parameters) {
		return DBUtil.jdbcTemplate.query(s.toString(), new RelascopicaSempliceMapper(), parameters.toArray());
	}

	@Override
	public int updateAreaDiSaggioD1(AdsDatiStazionaliOne adsDatiStazionaliOne) {

		StringBuilder update = new StringBuilder();
		update.append("UPDATE idf_geo_pt_area_di_saggio SET");
		update.append(" fk_tipo_forestale = ?");
		update.append(", fk_assetto= ?");
		update.append(" where idgeo_pt_ads = ?");
		
		logger.info("updateAreaDiSaggioD1 -> getTipoForestale: " + adsDatiStazionaliOne.getTipoForestale()
		+ " - getAssettoEvolutivoColturale: " + adsDatiStazionaliOne.getAssettoEvolutivoColturale());

		List<Object> parameters = new ArrayList<>();
		parameters.add(adsDatiStazionaliOne.getTipoForestale()!=null?
				adsDatiStazionaliOne.getTipoForestale():AreaDiSaggioDefaults.FK_TIPO_FORESTALE_DEFAULT);
		parameters.add(adsDatiStazionaliOne.getAssettoEvolutivoColturale()!=null?
				adsDatiStazionaliOne.getAssettoEvolutivoColturale():AreaDiSaggioDefaults.FK_ASSETO_DEFAULT);
		parameters.add(adsDatiStazionaliOne.getIdgeoPtAds());

		return DBUtil.jdbcTemplate.update(update.toString(), parameters.toArray());
	}

	@Override
	public int updateAreaDiSaggioD2(AdsDatiStazionaliTwo adsDatiStazionaliTwo) {

		StringBuilder update = new StringBuilder();
		update.append("UPDATE idf_geo_pt_area_di_saggio SET");
		update.append(" fk_destinazione = ?");
		update.append(", fk_tipo_intervento = ?");
		update.append(", fk_priorita = ?");
		update.append(", fk_danno = ?");
		update.append(" where idgeo_pt_ads = ?");

		List<Object> parameters = new ArrayList<>();
		parameters.add(adsDatiStazionaliTwo.getDestinazione() !=null ? adsDatiStazionaliTwo.getDestinazione(): AreaDiSaggioDefaults.FK_DESTINAZIONE_DEFAULT);
		parameters.add(adsDatiStazionaliTwo.getIntervento() != null ? adsDatiStazionaliTwo.getIntervento(): AreaDiSaggioDefaults.FK_TIPO_INTERVENTO_DEFAULT);
		parameters.add(adsDatiStazionaliTwo.getPriorita()!= null ? adsDatiStazionaliTwo.getPriorita(): AreaDiSaggioDefaults.FK_PRIORITA_DEFAULT);
		parameters.add(adsDatiStazionaliTwo.getDannoPrevalente() != null ? adsDatiStazionaliTwo.getDannoPrevalente(): AreaDiSaggioDefaults.FK_DANNO_DEFAULT);
		parameters.add(adsDatiStazionaliTwo.getIdgeoPtAds());

		return DBUtil.jdbcTemplate.update(update.toString(), parameters.toArray());
	}

	@Override
	public List<AreaDiSaggio> findAreaDiSaggioByCodiceAdsListExcel(StringBuilder s) {
		return DBUtil.jdbcTemplate.query(s.toString(), new AreaDiSaggioDTOMapper());
	}

	@Override
	public PaginatedList<RelascopicaSempliceDTO> findRelascopicaCompletaSort(StringBuilder s, int page, int limit,
			List<Object> parameters) {
		logger.info("findRelascopicaCompletaSort sql: " + s.toString());
		return super.paginatedList(s.toString(), parameters, new RelascopicaSempliceMapper(), page, limit);
	}

	@Override
	public AreaDiSaggio findADSByIdgeoPtAds(StringBuilder s, List<Object> parameters) throws RecordNotFoundException {
		try {
			logger.info("UPIT:" + s.toString());
			return DBUtil.jdbcTemplate.queryForObject(s.toString(), new AreaDiSaggioDTOMapper(), parameters.toArray());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RecordNotFoundException();
		}
	}
	
	
	@Override
	public GeoPtAreaDiSaggio findAdsStepsDataByIdgeoPtAds(Long idgeoPtAds) throws RecordNotFoundException {
		String sql = String.format("SELECT * FROM %s WHERE %s= %d" ,tableName, pkColumnName, idgeoPtAds );	
		logger.info("findAdsStepsDataByIdgeoPtAds - sql: " + sql + " - idgeoPtAds: " + idgeoPtAds);
		return	DBUtil.jdbcTemplate.queryForObject(sql, new GeoPtAreaDiSaggioMapper());		
	}
	

	@Override
	public PaginatedList<AlberiCampioneDominanteDTO> findAlberiCAV(StringBuilder s, int page, int limit,
			List<Object> parameters) {
		return super.paginatedList(s.toString(), parameters, new AlberiCavallettatiMapper(), page, limit);
	}

	@Override
	public AreaDiSaggio findDatiStazionali1(StringBuilder s, List<Object> parameters) throws RecordNotFoundException {
		try {
			logger.info("findDatiStazionali1 - sql: " + s.toString());
			return DBUtil.jdbcTemplate.queryForObject(s.toString(), new DatiStazionali1Mapper(), parameters.toArray());
		} catch (Exception e) {
			throw new RecordNotFoundException();
		}
	}

	@Override
	public AreaDISaggioDataStazionaliTwo findDatiStazionali2(Long idgeoPtAds) throws RecordNotFoundException {
		try {

			StringBuilder sql = new StringBuilder();
			List<Object> parameters = new ArrayList<>();
			createMainQueryForDatiStazionaliPartTwo(idgeoPtAds, sql);
			addWhereClauseForDatiStazionaliPartTwo(idgeoPtAds, sql, parameters);

			return DBUtil.jdbcTemplate.queryForObject(sql.toString(), new DatiStazionaliTwoMapper(),
					parameters.toArray());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RecordNotFoundException();
		}
	}
	
	@Override
	public int consolidaAreaDiSaggio(Long idgeoPtAds) {

		StringBuilder update = new StringBuilder();
		update.append("UPDATE idf_geo_pt_area_di_saggio SET");
		update.append(" fk_stato_ads = 2 ");
		update.append(" where idgeo_pt_ads = ?");

		List<Object> parameters = new ArrayList<>();
		parameters.add(idgeoPtAds);

		return DBUtil.jdbcTemplate.update(update.toString(), parameters.toArray());
	}

	private void createMainQueryForDatiStazionaliPartTwo(Long idgeoPtAds, StringBuilder sql) {
		sql.append("SELECT ads.codice_ads, ads.fk_destinazione, ads.fk_tipo_intervento, ads.fk_priorita, ads.fk_danno,")
				.append(" asn.cod_esbosco, asn.dist_esbosco_fuori_pista, asn.dist_esbosco_su_pista, "
						+ "asn.min_distanza_planimetrica, asn.danni_perc, asn.nr_piante_morte, asn.perc_defogliazione, "
						+ "asn.perc_ingiallimento, asn.flg_pascolamento,asn.note")
				.append(" FROM idf_geo_pt_area_di_saggio ads ")
				.append(" INNER JOIN idf_t_ads_superficie_nota asn ON ads.idgeo_pt_ads = asn.idgeo_pt_ads");
	}

	private void addWhereClauseForDatiStazionaliPartTwo(Long idgeoPtAds, StringBuilder sql, List<Object> parameters) {
		String sep = " WHERE";
		if (idgeoPtAds != null) {
			sql.append(sep).append(" ads.idgeo_pt_ads = ?");
			parameters.add(idgeoPtAds);
		}
	}
	
	@Override
	public AreaDiSaggioDatiGeneraliDTO findADSByIdgeoPtAds(Long idgeoPtAds) {
		StringBuilder sql = new StringBuilder("Select ads.*, ST_X(ST_TRANSFORM(ads.geometria,32632)),ST_Y(ST_TRANSFORM(ads.geometria,32632)), comune.istat_prov istat_prov, ambito.fk_padre as ambitoDiRilevamento "); 
		sql.append(" FROM idf_geo_pt_area_di_saggio ads left join idf_geo_pl_comune comune on ads.fk_comune= comune.id_comune "); 
		sql.append(" left JOIN idf_d_ambito_rilievo ambito on ads.fk_ambito=ambito.id_ambito ");
		sql.append(" WHERE ads.idgeo_pt_ads = ?") ;
		logger.info("findADSByIdgeoPtAds sql: " + sql.toString());
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), new AreaDiSaggioDatiGeneraliMapper(), idgeoPtAds);
		
	}

	@Override
	public void deleteAreaDiSaggio(Long idgeoPtAds) {
		String sql = "DELETE FROM idf_geo_pt_area_di_saggio WHERE idgeo_pt_ads = ?";
		DBUtil.jdbcTemplate.update(sql, idgeoPtAds);
	}


	@Override
	public ComuneProvincia getComuneProvinciaByPoint(Double x, Double y) {
		StringBuilder sql = new StringBuilder("select comune.id_comune,comune.istat_comune,comune.denominazione_comune, " + 
				"provincia.istat_prov, provincia.denominazione_prov " + 
				"from idf_geo_pl_comune comune " + 
				"JOIN idf_geo_pl_provincia provincia on comune.istat_prov=provincia.istat_prov " + 
				"WHERE st_intersects (ST_GeomFromText('POINT (" + x +  " " + y + ")',32632),comune.geometria )") ;
		logger.info("getComuneProvinciaByPoint sql: " + sql.toString() + " - x: " + x + " - y: " + y);
		
		try {
			return DBUtil.jdbcTemplate.queryForObject(sql.toString(), new ComuneProvinciaMapper());
		}catch(Exception e) {
			return new ComuneProvincia();
		}
		
		
	}


	@Override
	public boolean isCodiceAdsAlreadyUsed(String codiceAds, Long idgeoPtAds) {
		if(idgeoPtAds == null) {
			String sql = "SELECT COUNT(*) FROM idf_geo_pt_area_di_saggio WHERE codice_ads = ?";
			return  DBUtil.jdbcTemplate.queryForInt(sql,codiceAds) > 0;
		}else {
			String sql = "SELECT COUNT(*) FROM idf_geo_pt_area_di_saggio WHERE codice_ads = ? and idgeo_pt_ads <> ? ";
			return  DBUtil.jdbcTemplate.queryForInt(sql,codiceAds, idgeoPtAds) > 0;
		}
	}
	
}
