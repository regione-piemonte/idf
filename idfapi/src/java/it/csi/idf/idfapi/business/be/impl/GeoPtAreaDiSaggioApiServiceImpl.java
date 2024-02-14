/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import it.csi.idf.idfapi.business.be.GeoPtAreaDiSaggioApi;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.business.be.impl.dao.AmbitoRilievoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.GeoPlParticellaForestDAO;
import it.csi.idf.idfapi.business.be.impl.dao.GeoPtAreaDiSaggioDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SoggettoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.UserSessionDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperAdsDAO;
import it.csi.idf.idfapi.dto.AdsDatiStazionaliOne;
import it.csi.idf.idfapi.dto.AdsDatiStazionaliTwo;
import it.csi.idf.idfapi.dto.AlberiCampioneDominanteDTO;
import it.csi.idf.idfapi.dto.AmbitoRilievo;
import it.csi.idf.idfapi.dto.AreaDISaggioDataStazionaliTwo;
import it.csi.idf.idfapi.dto.AreaDiSaggio;
import it.csi.idf.idfapi.dto.AreaDiSaggioDTO;
import it.csi.idf.idfapi.dto.AreaDiSaggioDatiGeneraliDTO;
import it.csi.idf.idfapi.dto.ComuneProvincia;
import it.csi.idf.idfapi.dto.ExcelDTO;
import it.csi.idf.idfapi.dto.RelascopicaSempliceDTO;
import it.csi.idf.idfapi.errors.ErrorConstants;
import it.csi.idf.idfapi.rest.BaseResponses;
import it.csi.idf.idfapi.util.AberiCAMeDOMEnum;
import it.csi.idf.idfapi.util.AlberiCavallettatiEnum;
import it.csi.idf.idfapi.util.AreaDiSaggioEnum;
import it.csi.idf.idfapi.util.CodiceEnum;
import it.csi.idf.idfapi.util.DatiStazionaliEnum;
import it.csi.idf.idfapi.util.PaginatedList;
import it.csi.idf.idfapi.util.QueryUtil;
import it.csi.idf.idfapi.util.RelascopicaCompletaEnum;
import it.csi.idf.idfapi.util.RelascopicaSempliceEnum;
import it.csi.idf.idfapi.util.SpringSupportedResource;
import it.csi.idf.idfapi.util.ValidationUtil;
import it.csi.idf.idfapi.validation.StepValidationErrors;

public class GeoPtAreaDiSaggioApiServiceImpl extends SpringSupportedResource implements GeoPtAreaDiSaggioApi {
	private static final Logger logger = Logger.getLogger(GeoPtAreaDiSaggioApiServiceImpl.class);
	private static final String ASCENDING = "ASC";
	private static final String DESCENDING = "DESC";
	private static final char ORDER = '-';
	public static final String SEARCH_QUERY_KEY = "AREADISAGGIO_SEARCH_QUERY";
	public static final String SEARCH_QUERY_PARAMS = "AREADISAGGIO_SEARCH_QUERY_PARAMS";
	@Autowired
	WrapperAdsDAO wrapperAdsDAO;

	@Autowired
	private UserSessionDAO userSessionDAO;

	@Autowired
	private SoggettoDAO soggettoDAO;
	
	@Autowired
	private GeoPtAreaDiSaggioDAO geoPtAreaDiSaggioDAO;
	
	@Autowired
	private AmbitoRilievoDAO ambitoRilievoDAO;
	
	@Autowired
	private GeoPlParticellaForestDAO geoPlParticellaForestDAO;

	List<Object> parameters = null;

	@Override
	public Response search(@QueryParam("idComune") String idComuneStr[], @QueryParam("istatProv") String istatProv[],
			@QueryParam("idCategoriaForestale") Integer idCategoriaForestale[], @QueryParam("idAmbito") Integer idAmbito [],
			@QueryParam("dettaglioAmbito") String dettaglioAmbitoStr [], @QueryParam("fromDate") String fromDate[],
			@QueryParam("toDate") String toDate[], @QueryParam("tipologia") Integer tipoAds[], 
			@QueryParam("statoScheda") Integer statoScheda [], @QueryParam("page") int page,
			@QueryParam("limit") int limit, @QueryParam("sort") String sort, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		try {
			
			if (!ValidationUtil.hasNotNullValue(new Object[][] {idComuneStr,istatProv,idCategoriaForestale, idAmbito, dettaglioAmbitoStr, fromDate, toDate, tipoAds, statoScheda})) {
				return BaseResponses.errorResponse(new ValidationException(CodiceEnum.E05, "Valorizzare almeno un parametro di ricerca"));
			}

		PaginatedList<AreaDiSaggio> ads;
		
		// It will have empty value and not null value. That will cause problem if only istatProv has value
			Integer idComune[] = convertFromStringToIntArray(idComuneStr);			
			Integer dettaglioAmbito[] = convertFromStringToIntArray(dettaglioAmbitoStr);
		
			List<Object> params = new ArrayList<Object>();
			StringBuilder searchQuery = createWhereClauseForSearchAndPopulateParameters(idComune, istatProv, idCategoriaForestale, idAmbito,
					dettaglioAmbito, fromDate, toDate, tipoAds, statoScheda, sort, params);
			ads = wrapperAdsDAO.search(searchQuery, page, limit, params);
			
			
			return Response.ok(ads).build();
		} catch (ParseException e) {
			e.printStackTrace();
			throw new ValidationException("Validation error: Search params are not in correct format");
		}
	}

	@GET
	@Path("/datiGenerali/{idgeoPtAds}")
	@Produces({ "application/json" })
	public Response getDatiGenerali(@PathParam("idgeoPtAds") Long idgeoPtAds, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) {
		
		try {
			AreaDiSaggioDatiGeneraliDTO areaDiSaggio = wrapperAdsDAO.findADSByIdgeoPtAds(idgeoPtAds);	
			if(areaDiSaggio.getUtmEst()!=null && areaDiSaggio.getUtmNord()!=null) {
				
				ComuneProvincia  comuneProv = geoPtAreaDiSaggioDAO.getComuneProvinciaByPoint(areaDiSaggio.getUtmEst().doubleValue()
						,areaDiSaggio.getUtmNord().doubleValue());
				if(comuneProv != null) {
					areaDiSaggio.setComune(comuneProv.getIdComune());
				}else {
					areaDiSaggio.setComune(null);
				}
			}
			return Response.ok(areaDiSaggio).build();
		}catch(Exception ex) {	
			ex.printStackTrace();
			return Response.serverError().entity(ex).build();
		}
	}
	
	public Response getComuneProvincia(@PathParam("x") Double x, @PathParam("y") Double y, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) {
		return Response.ok(geoPtAreaDiSaggioDAO.getComuneProvinciaByPoint(x, y)).build();
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
				sortField = translateFieldName(sortField);
				sql.append(sortField).append(" ").append(sortOrder);
			}
		}
		return sql.toString();
	}

	private StringBuilder createMainQueryForSearch() {

		return new StringBuilder(
				"SELECT ads.idgeo_pt_ads as idgeo_pt_ads, ads.codice_ads, ads.fk_tipo_ads, ads.ambito_specifico, ads.fk_ambito, tipoads.descr_tipo_ads, comune.denominazione_comune, c.descrizione, "
				+ " am.descr_ambito, ambitoParent.descr_ambito descr_ambito_parent, ambitoParent.id_ambito id_ambito_parent, ads.data_ril,ST_X(ST_TRANSFORM(ads.geometria,32632)),ST_Y(ST_TRANSFORM(ads.geometria,32632)), "
				+ " stato_ads.id_stato_ads, stato_ads.id_stato_ads, stato_ads.descr_stato_ads, stato_ads.mtd_ordinamento, stato_ads.flg_visibile, sog.codice_fiscale ")	
						.append("FROM idf_geo_pt_area_di_saggio ads ")
						.append("LEFT OUTER JOIN idf_geo_pl_tipo_forestale geo ON ads.fk_tipo_forestale = geo.id_geo_tipo_forestale ")
						.append("LEFT OUTER JOIN idf_t_tipo_forestale b ON b.id_tipo_forestale = geo.id_geo_tipo_forestale ")
						.append("LEFT OUTER JOIN idf_d_tipo_ads tipoads ON ads.fk_tipo_ads = tipoads.id_tipo_ads ")
						.append("LEFT OUTER JOIN idf_d_categoria_forestale c ON c.id_categoria_forestale = b.fk_categoria_forestale ")
						.append("LEFT OUTER JOIN idf_d_ambito_rilievo am ON am.id_ambito = ads.fk_ambito ")
						.append("LEFT OUTER JOIN idf_d_ambito_rilievo ambitoParent ON am.fk_padre = ambitoParent.id_ambito ")
						.append("LEFT OUTER JOIN idf_geo_pl_comune comune ON ads.fk_comune = comune.id_comune ")
						.append("LEFT OUTER JOIN idf_geo_pl_provincia p ON p.istat_prov= comune.istat_prov ")
						.append("LEFT OUTER JOIN idf_d_stato_ads stato_ads ON stato_ads.id_stato_ads = ads.fk_stato_ads ")
						.append("LEFT OUTER JOIN idf_t_soggetto sog on ads.fk_soggetto = sog.id_soggetto ");
	}

	private StringBuilder createMainQueryForSearch1() {
		return new StringBuilder("SELECT distinct ads.idgeo_pt_ads, ads.codice_ads, ads.fk_tipo_ads, ads.ambito_specifico, ads.fk_ambito, tipoads.descr_tipo_ads,  ")
				.append("comune.denominazione_comune, c.codice_amministrativo ||' - '||c.descrizione as descrizione, am.descr_ambito,ads.data_ril,  ")
				.append("esp.descr_esposizione, b.codice_amministrativo ||' - '||b.tipo as tipo, ads.quota, ads.inclinazione, ")
				.append(" particella.denominazione_particella, proprieta.descr_proprieta, intervento.descr_intervento, superficie.raggio_mt,  ")
				.append(" superficie.densita_campionamento, danno.descr_danno, ")
				.append(" ambitoParent.descr_ambito descr_ambito_parent, ambitoParent.id_ambito id_ambito_parent, relascopica.fattore_numerazione,  ")
				.append(" assetto.cod_assetto ||' - '||assetto.descr_assetto as descr_assetto,")
				.append(" idss.cod_stadio_sviluppo  ||' - '|| idss.descr_stadio_sviluppo  as cod_stadio_sviluppo, superficie.nr_ceppaie,  ")
				.append(" superficie.rinnovazione, superficie.rin_specie_prevalente, specie.codice ||' - '||specie.volgare as rin_specie_descr , ")
				.append(" superficie.perc_copertura_chiome, superficie.cod_esbosco,esbosco.descr_esbosco ,superficie.dist_esbosco_fuori_pista,  ")
				.append(" superficie.fk_classe_fertilita, combustibile.perc_copertura_lettiera,combustibile.perc_copertura_erbacea, ")
				.append(" combustibile.perc_copertura_cespugli, superficie.dist_esbosco_su_pista,superficie.min_distanza_planimetrica,  ")
				.append(" superficie.danni_perc,superficie.nr_piante_morte,superficie.perc_defogliazione,superficie.perc_ingiallimento, ")
				.append(" superficie.flg_pascolamento,superficie.note,dest.descr_destinazione,superficie.combust_p, ")
				.append(" superficie.fk_tipo_strutturale_princ, tipoStr.cod_tipo_strutturale ||' - '||tipoStr.descr_tipo_strutturale as descTipoStructPrinc, ")
				.append(" priorita.descr_priorita, combustibile.flg_principale,fertilita.descr_classe_fertilita,ST_X(ST_TRANSFORM(ads.geometria,32632)), ")
				.append(" ST_Y(ST_TRANSFORM(ads.geometria,32632)), statoAds.id_stato_ads as ids_stato_ads, statoAds.id_stato_ads,  ")
				.append(" statoAds.descr_stato_ads, statoAds.mtd_ordinamento, statoAds.flg_visibile, sog.codice_fiscale  ")
				.append("FROM idf_geo_pt_area_di_saggio ads  ")
				.append("LEFT OUTER JOIN idf_d_stato_ads statoAds ON statoAds.id_stato_ads = ads.fk_stato_ads  ")
				.append("LEFT OUTER JOIN idf_t_tipo_forestale b ON b.id_tipo_forestale = ads.fk_tipo_forestale  ")
				.append("LEFT OUTER JOIN idf_d_tipo_ads tipoads ON ads.fk_tipo_ads = tipoads.id_tipo_ads  ")
				.append("LEFT OUTER JOIN idf_d_categoria_forestale c ON c.id_categoria_forestale = b.fk_categoria_forestale  ")
				.append("LEFT OUTER JOIN idf_d_ambito_rilievo am ON am.id_ambito = ads.fk_ambito  ")
				.append("LEFT OUTER JOIN idf_d_ambito_rilievo ambitoParent ON am.fk_padre = ambitoParent.id_ambito  ")
				.append("LEFT OUTER JOIN idf_geo_pl_comune comune ON ads.fk_comune = comune.id_comune  ")
				.append("LEFT OUTER JOIN idf_geo_pl_provincia p ON p.istat_prov= comune.istat_prov  ")
				.append("LEFT OUTER JOIN idf_d_destinazione dest ON ads.fk_destinazione = dest.id_destinazione  ")
				.append("LEFT OUTER JOIN idf_geo_pl_particella_forest particella ON ads.idgeo_pl_particella_forest = particella.idgeo_pl_particella_forest  ")
				.append("LEFT OUTER JOIN idf_d_proprieta proprieta ON ads.fk_proprieta = proprieta.id_proprieta  ")
				.append("LEFT OUTER JOIN idf_d_priorita priorita ON ads.fk_priorita = priorita.id_priorita  ")
				.append("LEFT OUTER JOIN idf_d_tipo_intervento intervento ON ads.fk_tipo_intervento = intervento.id_tipo_intervento  ")
				.append("LEFT OUTER JOIN idf_t_ads_superficie_nota superficie ON ads.idgeo_pt_ads = superficie.idgeo_pt_ads  ")
				.append("LEFT OUTER JOIN idf_t_specie specie on specie.codice = superficie.rin_specie_prevalente and specie.flg_visibile = 1   ")
				.append("LEFT OUTER JOIN idf_d_esbosco esbosco on esbosco.cod_esbosco = superficie.cod_esbosco  ")
				.append("LEFT OUTER JOIN idf_r_ads_combustibile combustibile ON combustibile.idgeo_pt_ads = superficie.idgeo_pt_ads  ")
				.append("LEFT OUTER JOIN idf_d_assetto assetto ON ads.fk_assetto = assetto.id_assetto  ")
				.append("LEFT OUTER JOIN idf_t_ads_relascopica relascopica ON ads.idgeo_pt_ads = relascopica.idgeo_pt_ads ")
				.append("LEFT OUTER JOIN idf_d_classe_fertilita fertilita ON superficie.fk_classe_fertilita = fertilita.id_classe_fertilita ")
				.append("LEFT OUTER JOIN idf_d_danno danno ON ads.fk_danno = danno.id_danno  ")
				.append("LEFT OUTER JOIN idf_d_tipo_strutturale tipoStr ON superficie.fk_tipo_strutturale_princ = tipoStr.id_tipo_strutturale ")
				.append("LEFT OUTER JOIN idf_d_esposizione esp ON ads.fk_esposizione= esp.id_esposizione   ")
				.append("left join idf_d_stadio_sviluppo idss on superficie.cod_stadio_sviluppo = idss.cod_stadio_sviluppo ")
				.append("LEFT OUTER JOIN idf_t_soggetto sog on ads.fk_soggetto = sog.id_soggetto ");
	}

	private StringBuilder createMainQueryForSearch2() {
		return new StringBuilder(
				"SELECT distinct ads.idgeo_pt_ads, ads.codice_ads, ads.fk_tipo_ads, ads.ambito_specifico, ads.fk_ambito, tipoads.descr_tipo_ads, "
						+ "	comune.denominazione_comune, c.descrizione, am.descr_ambito,ads.data_ril, esp.descr_esposizione, b.tipo, ads.quota, ads.inclinazione,"
						+ " particella.denominazione_particella, proprieta.descr_proprieta, intervento.descr_intervento,"
						+ " ambitoParent.descr_ambito descr_ambito_parent, ambitoParent.id_ambito id_ambito_parent,"
						+ " assetto.descr_assetto, " + " dest.descr_destinazione, priorita.descr_priorita, "
						+ " ST_X(ST_TRANSFORM(ads.geometria,32632)),ST_Y(ST_TRANSFORM(ads.geometria,32632)), "
						+ " statoAds.id_stato_ads as ids_stato_ads, statoAds.id_stato_ads, statoAds.descr_stato_ads, statoAds.mtd_ordinamento, "
						+ " statoAds.flg_visibile, sog.codice_fiscale ")
								.append("FROM idf_geo_pt_area_di_saggio ads ")
								.append("LEFT OUTER JOIN idf_d_stato_ads statoAds ON statoAds.id_stato_ads = ads.fk_stato_ads ")
								.append("LEFT OUTER JOIN idf_t_tipo_forestale b ON b.id_tipo_forestale = ads.fk_tipo_forestale ")
								.append("LEFT OUTER JOIN idf_d_tipo_ads tipoads ON ads.fk_tipo_ads = tipoads.id_tipo_ads ")
								.append("LEFT OUTER JOIN idf_d_categoria_forestale c ON c.id_categoria_forestale = b.fk_categoria_forestale ")
								.append("LEFT OUTER JOIN idf_d_ambito_rilievo am ON am.id_ambito = ads.fk_ambito ")								
								.append("LEFT OUTER JOIN idf_d_ambito_rilievo ambitoParent ON am.fk_padre = ambitoParent.id_ambito ")
								.append("LEFT OUTER JOIN idf_geo_pl_comune comune ON ads.fk_comune = comune.id_comune ")
								.append("LEFT OUTER JOIN idf_geo_pl_provincia p ON p.istat_prov= comune.istat_prov ")
								.append("LEFT OUTER JOIN idf_d_destinazione dest ON ads.fk_destinazione = dest.id_destinazione ")
								.append("LEFT OUTER JOIN idf_geo_pl_particella_forest particella ON ads.idgeo_pl_particella_forest = particella.idgeo_pl_particella_forest ")
								.append("LEFT OUTER JOIN idf_d_proprieta proprieta ON ads.fk_proprieta = proprieta.id_proprieta ")
								.append("LEFT OUTER JOIN idf_d_priorita priorita ON ads.fk_priorita = priorita.id_priorita ")
								.append("LEFT OUTER JOIN idf_d_tipo_intervento intervento ON ads.fk_tipo_intervento = intervento.id_tipo_intervento ")
								.append("LEFT OUTER JOIN idf_d_assetto assetto ON ads.fk_assetto = assetto.id_assetto ")
								.append("LEFT OUTER JOIN idf_d_esposizione esp ON ads.fk_esposizione= esp.id_esposizione  ")
								.append("LEFT OUTER JOIN idf_t_soggetto sog on ads.fk_soggetto = sog.id_soggetto ");
	}

	private StringBuilder createRelascopicaQueryForSearch() {
		return new StringBuilder(
				"SELECT ads.idgeo_pt_ads, ads.codice_ads, ads.ambito_specifico, ads.fk_tipo_ads, ads.fk_ambito, t.id_specie, t.codice || ' - ' || t.volgare as latino, s.nr_alberi_seme, s.nr_alberi_pollone, tipoads.descr_tipo_ads, s.note, relascopica.fattore_numerazione, comune.denominazione_comune, c.descrizione,ads.data_ril, esp.descr_esposizione, b.tipo, ads.quota, ads.inclinazione,"
						+ " particella.denominazione_particella, proprieta.descr_proprieta, strutturale.descr_tipo_strutturale, s.diametro, s.altezza, s.incremento, s.flg_pollone_seme, s.cod_tipo_campione,ST_X(ST_TRANSFORM(ads.geometria,32632)),ST_Y(ST_TRANSFORM(ads.geometria,32632)), "
						+ " statoAds.id_stato_ads as ids_stato_ads, statoAds.id_stato_ads, statoAds.descr_stato_ads, statoAds.mtd_ordinamento, statoAds.flg_visibile ")
								.append("FROM idf_geo_pt_area_di_saggio ads ")
								.append("LEFT OUTER JOIN idf_d_stato_ads statoAds ON statoAds.id_stato_ads = ads.fk_stato_ads ")
								.append("LEFT OUTER JOIN idf_t_tipo_forestale b ON b.id_tipo_forestale = ads.fk_tipo_forestale ")
								.append("LEFT OUTER JOIN idf_d_tipo_ads tipoads ON ads.fk_tipo_ads = tipoads.id_tipo_ads ")
								.append("LEFT OUTER JOIN idf_d_categoria_forestale c ON c.id_categoria_forestale = b.fk_categoria_forestale ")
								.append("LEFT OUTER JOIN idf_geo_pl_comune comune ON ads.fk_comune = comune.id_comune ")
								.append("LEFT OUTER JOIN idf_geo_pl_provincia p ON p.istat_prov= comune.istat_prov ")
								.append("LEFT OUTER JOIN idf_geo_pl_particella_forest particella ON ads.idgeo_pl_particella_forest = particella.idgeo_pl_particella_forest ")
								.append("LEFT OUTER JOIN idf_d_proprieta proprieta ON ads.fk_proprieta = proprieta.id_proprieta ")
								.append("LEFT OUTER JOIN idf_t_ads_relascopica relascopica ON relascopica.idgeo_pt_ads = ads.idgeo_pt_ads ")
								.append("LEFT OUTER JOIN idf_d_tipo_strutturale strutturale ON strutturale.id_tipo_strutturale = relascopica.fk_tipo_strutturale_princ ")
								.append("LEFT OUTER JOIN idf_r_adsrel_specie s ON ads.idgeo_pt_ads = s.idgeo_pt_ads ")
								.append("LEFT OUTER JOIN idf_t_specie t ON t.id_specie = s.id_specie ")
								.append("LEFT OUTER JOIN idf_d_esposizione esp ON ads.fk_esposizione= esp.id_esposizione  ");

	}

	private StringBuilder createRelascopicaCompletaSearch() {
		return new StringBuilder(
				"SELECT ads.idgeo_pt_ads, ads.codice_ads, ads.ambito_specifico, ads.fk_tipo_ads, t.id_specie, s.diametro, s.altezza, s.incremento, s.flg_pollone_seme ")
						.append("FROM idf_geo_pt_area_di_saggio ads ")
						.append("INNER JOIN idf_r_adsrel_specie s ON ads.idgeo_pt_ads = s.idgeo_pt_ads ")
						.append("INNER JOIN idf_t_specie t ON t.id_specie = s.id_specie ");

	}

	/* TODO: We should review if ambito_specifico is required here. */
	private StringBuilder searchForAlberiCampioneDominante() {
		return new StringBuilder(
				"SELECT ads.idgeo_pt_ads, ads.codice_ads, ads.fk_tipo_ads, ads.ambito_specifico, ")
					.append("s.id_specie, s.qualita, s.diametro, s.altezza, s.incremento, s.eta, s.note, s.cod_tipo_campione, ")
					.append("t.codice, t.cod_gruppo, s.flg_pollone_seme, t.codice || ' - ' || t.volgare as latino, t.volgare ")
					.append("FROM idf_geo_pt_area_di_saggio ads ")
					.append("INNER JOIN idf_r_adsrel_specie s ON ads.idgeo_pt_ads = s.idgeo_pt_ads ")
					.append("INNER JOIN idf_t_specie t ON t.id_specie = s.id_specie ");

	}

	
	private StringBuilder searchForAlberiForExport(String idsgeoPtAdsList) {
		return new StringBuilder(
				"SELECT ads.idgeo_pt_ads, ads.codice_ads, ads.fk_tipo_ads, ads.ambito_specifico, s.id_specie, s.qualita, s.diametro, s.altezza, s.incremento, s.eta, s.note, s.cod_tipo_campione, t.codice, t.cod_gruppo, t.codice || ' - ' || t.volgare as latino, s.flg_pollone_seme, t.volgare ")
						.append(" FROM idf_geo_pt_area_di_saggio ads ")
						.append(" INNER JOIN idf_r_adsrel_specie s ON ads.idgeo_pt_ads = s.idgeo_pt_ads ")
						.append(" INNER JOIN idf_t_specie t ON t.id_specie = s.id_specie ")
						.append(" WHERE s.idgeo_pt_ads in (" + idsgeoPtAdsList + ")");

	}
	
	private StringBuilder createMainQueryForGetDatiStazionali1() {
		return new StringBuilder(
				"SELECT ads.idgeo_pt_ads, ads.codice_ads, ads.fk_tipo_ads, ads.ambito_specifico, ads.fk_ambito, fertilita.descr_classe_fertilita, superficie.fk_classe_fertilita, superficie.densita_campionamento, c.id_categoria_forestale ,superficie.raggio_mt, ads.fk_tipo_forestale, assetto.id_assetto,"
						+ " superficie.fk_tipo_strutturale_princ,combustibile.perc_copertura_lettiera,combustibile.perc_copertura_erbacea,combustibile.perc_copertura_cespugli,"
						+ " superficie.perc_copertura_chiome,superficie.cod_stadio_sviluppo, superficie.nr_ceppaie, superficie.rinnovazione,superficie.rin_specie_prevalente ")
								.append("FROM idf_geo_pt_area_di_saggio ads ")
								.append("LEFT OUTER JOIN idf_t_tipo_forestale b ON b.id_tipo_forestale = ads.fk_tipo_forestale ")
								.append("LEFT OUTER JOIN idf_d_tipo_ads tipoads ON ads.fk_tipo_ads = tipoads.id_tipo_ads ")
								.append("LEFT OUTER JOIN idf_d_categoria_forestale c ON c.id_categoria_forestale = b.fk_categoria_forestale ")
								.append("LEFT OUTER JOIN idf_t_ads_superficie_nota superficie ON ads.idgeo_pt_ads = superficie.idgeo_pt_ads ")
								.append("LEFT OUTER JOIN idf_t_ads_relascopica relascopica ON relascopica.idgeo_pt_ads = ads.idgeo_pt_ads ")
								.append("LEFT OUTER JOIN idf_d_classe_fertilita fertilita ON fertilita.id_classe_fertilita = superficie.fk_classe_fertilita ")
								.append("LEFT OUTER JOIN idf_r_ads_combustibile combustibile ON combustibile.idgeo_pt_ads = superficie.idgeo_pt_ads ")
								.append("LEFT OUTER JOIN idf_d_assetto assetto ON ads.fk_assetto = assetto.id_assetto ");
	}

	private StringBuilder createWhereClauseForSearchAndPopulateParameters(Integer idsComune[], String istatsProv[],
			Integer idsCategoriaForestale[], Integer idsAmbito[], Integer dettagliosAmbito[], String fromDates[], String toDates[],
			Integer tipoAds[], Integer statoScheda[], String sort, List parameters) throws ParseException {
		String sep = "WHERE";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		//parameters = new ArrayList<Object>()
				;
		StringBuilder sql = new StringBuilder();
		StringBuilder sqlPartCondition = new StringBuilder();
		for (int i = 0; i < idsComune.length; i++) {
			StringBuilder partialSQLCondition = new StringBuilder();
			QueryUtil.addConditionForNotNullParams(partialSQLCondition, parameters, true, "AND", 
					new it.csi.idf.idfapi.util.QueryParam(idsComune[i], "comune.id_comune"),
					new it.csi.idf.idfapi.util.QueryParam(istatsProv[i], "comune.istat_prov")
					);
			QueryUtil.appendCondition(sqlPartCondition, "OR", partialSQLCondition);
		}		
		QueryUtil.addBrackets(sqlPartCondition);
		QueryUtil.appendCondition(sql, "AND", sqlPartCondition);
				
		sqlPartCondition = new StringBuilder();
		QueryUtil.addConditionForNotNullParams(sqlPartCondition, parameters, true, "OR",idsCategoriaForestale, "b.fk_categoria_forestale");
		QueryUtil.appendCondition(sql, "AND", sqlPartCondition);
				
		/* TODO: Check is this is OK. It is strange that it is implemented in this way. Now user can add as many detaggiloAmbito as he want.   */
		sqlPartCondition = new StringBuilder();
		for (int i = 0; i < idsAmbito.length; i++) {
			StringBuilder partialSQLCondition = new StringBuilder();
			QueryUtil.addConditionForNotNullParams(partialSQLCondition, parameters, false, "AND", 
					new it.csi.idf.idfapi.util.QueryParam(idsAmbito[i], "am.fk_padre"),
					new it.csi.idf.idfapi.util.QueryParam(dettagliosAmbito[i], "am.id_ambito")
					);
			QueryUtil.appendCondition(sqlPartCondition, "OR", partialSQLCondition);
		}		
		QueryUtil.addBrackets(sqlPartCondition);
		QueryUtil.appendCondition(sql, "AND", sqlPartCondition);
		

		sqlPartCondition = new StringBuilder();
		for (int i = 0; i < fromDates.length; i++) {
			StringBuilder partialSQLCondition = new StringBuilder();
			QueryUtil.addConditionForNotNullParams(partialSQLCondition, parameters, true, "AND", 
					new it.csi.idf.idfapi.util.QueryParam(fromDates[i]!=null && !fromDates[i].isEmpty() ? formatter.parse(fromDates[i]) : null, "ads.data_ril", ">="),
					new it.csi.idf.idfapi.util.QueryParam(toDates[i]!=null && !toDates[i].isEmpty() ? formatter.parse(toDates[i]): null, "ads.data_ril", "<="));
			QueryUtil.appendCondition(sqlPartCondition, "OR", partialSQLCondition);
		}		
		
		QueryUtil.addBrackets(sqlPartCondition);
		QueryUtil.appendCondition(sql, "AND", sqlPartCondition);
		
		sqlPartCondition = new StringBuilder();
		QueryUtil.addConditionForNotNullParams(sqlPartCondition, parameters, true, "OR", tipoAds, "ads.fk_tipo_ads");
		QueryUtil.appendCondition(sql, "AND", sqlPartCondition);
		
		sqlPartCondition = new StringBuilder();
		QueryUtil.addConditionForNotNullParams(sqlPartCondition, parameters, true, "OR", statoScheda, "ads.fk_stato_ads");
		QueryUtil.appendCondition(sql, "AND", sqlPartCondition);
		QueryUtil.makeWhereQueryStatement(sql);
		
		if (sort != null) {
			String sortSql = getQuerySortingSegment(sort);
			return createMainQueryForSearch().append(sql).append(sortSql);
		}
		
		
		return createMainQueryForSearch().append(sql);
	}

	private StringBuilder createWhereClauseForSearch(Long idgeoPtAds, StringBuilder sb) {
		String sep = "WHERE";
		parameters = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();

		if (idgeoPtAds != null) {
			sql.append(sep).append(" ads.idgeo_pt_ads = ?");
			parameters.add(idgeoPtAds);
		}
		sb.append(sql);
		logger.info(sb);
		return sb;
	}

	private StringBuilder createWhereClauseForSearchCAV(Long idgeoPtAds, StringBuilder sb, String sort) {
		String sep = "WHERE";
		parameters = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();

		if (idgeoPtAds != null) {
			sql.append(sep).append(" s.cod_tipo_campione = 'CAV'").append(" AND").append(" ads.idgeo_pt_ads = ?");

			parameters.add(idgeoPtAds);
		}
		if (sort != null) {
			String sortSql = getQuerySortingSegment(sort);
			return sb.append(sql).append(sortSql);
		}

		return sb.append(sql);
	}

	private StringBuilder createWhereClauseForSearchIn(List<String> codiceAdsList, StringBuilder sb) {
		String sep = "WHERE";
		StringBuilder sql = new StringBuilder();
		if (codiceAdsList != null) {
			sql.append(sep).append(" ads.idgeo_pt_ads IN (");
			StringBuilder allCodices = new StringBuilder();
			for (String codice : codiceAdsList) {
				allCodices.append("'");
				allCodices.append(codice);
				allCodices.append("'");
				allCodices.append(',');
			}
			String allCodice = allCodices.substring(0, allCodices.toString().length() - 1);
			sql.append(allCodice);
			sql.append(")");
		}
		return sb.append(sql);
	}

	@Override
	public Response getAreaDiSaggioByIdgeoPtAds(Long idgeoPtAds, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		AreaDiSaggio ads;
		try {
			ads = wrapperAdsDAO.findAreaDiSaggioByCodiceAds(
					createWhereClauseForSearch(idgeoPtAds, createMainQueryForSearch1()), parameters);
		} catch (Exception e) {		
			e.printStackTrace();
			return Response.serverError().entity(e.getMessage()).build();
		}
		return Response.ok(ads).build();
	}

	@Override
	public Response getADSByIdgeoPtAds(boolean includeStep, Long idgeoPtAds, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		AreaDiSaggio ads;
		try {
			ads = wrapperAdsDAO.findADSByIdgeoPtAds(createWhereClauseForSearch(idgeoPtAds, createMainQueryForSearch2()),
					parameters);
			if (includeStep) {
				ads.setLastCompletedStep(wrapperAdsDAO.getNumberOfLastCompletedStep(idgeoPtAds).getNextStep());
			}
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
			return Response.ok(ErrorConstants.NON_TROVATO).build();
		}
		return Response.ok(ads).build();
	}

	public Response getAlberiCampioneDominante(Long idgeoPtAds, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		List<AlberiCampioneDominanteDTO> ads = wrapperAdsDAO.findAlberiCampioneDominante(
				createWhereClauseForSearch(idgeoPtAds, searchForAlberiCampioneDominante()), parameters);
		return Response.ok(ads).build();
	}
	
	@Override
	public Response exportSearchResults2Excel(@QueryParam("idComune") String idComuneStr[], @QueryParam("istatProv") String istatProv[],
			@QueryParam("idCategoriaForestale") Integer idCategoriaForestale[], @QueryParam("idAmbito") Integer idAmbito [],
			@QueryParam("dettaglioAmbito") String idDettaglioAmbitoStr [], @QueryParam("fromDate") String fromDate[],
			@QueryParam("toDate") String toDate[], @QueryParam("tipologia") Integer tipoAds[], 
			@QueryParam("statoScheda") Integer statoScheda [], @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		try {
			List<AreaDiSaggio> ads;
			// It will have empty value and not null value. That will cause problem if only istatProv has value
			Integer idComune[] = convertFromStringToIntArray(idComuneStr);			
			Integer dettaglioAmbito[] = convertFromStringToIntArray(idDettaglioAmbitoStr);
		
			List<Object> params = new ArrayList<Object>();
			StringBuilder searchQuery = createWhereClauseForSearchAndPopulateParameters(idComune, istatProv, idCategoriaForestale, idAmbito,
					dettaglioAmbito, fromDate, toDate, tipoAds, statoScheda, null, params);
			ads = wrapperAdsDAO.getSearchResultsForExcel(searchQuery, params);
			return exportAds2Excel(ads, true);
			//return Response.ok(ads).build();
		}catch(Exception ex) {
			ex.printStackTrace();
			return Response.serverError().build();
		}
		
		
	}
	
	@Override
	public Response exportSearchResults2ExcelBasicInfo(@QueryParam("idComune") String idComuneStr[], @QueryParam("istatProv") String istatProv[],
			@QueryParam("idCategoriaForestale") Integer idCategoriaForestale[], @QueryParam("idAmbito") Integer idAmbito [],
			@QueryParam("dettaglioAmbito") String idDettaglioAmbitoStr [], @QueryParam("fromDate") String fromDate[],
			@QueryParam("toDate") String toDate[], @QueryParam("tipologia") Integer tipoAds[], 
			@QueryParam("statoScheda") Integer statoScheda [], @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		try {
			List<AreaDiSaggio> ads;
			// It will have empty value and not null value. That will cause problem if only istatProv has value
			Integer idComune[] = convertFromStringToIntArray(idComuneStr);			
			Integer dettaglioAmbito[] = convertFromStringToIntArray(idDettaglioAmbitoStr);
		
			List<Object> params = new ArrayList<Object>();
			StringBuilder searchQuery = createWhereClauseForSearchAndPopulateParameters(idComune, istatProv, idCategoriaForestale, idAmbito,
					dettaglioAmbito, fromDate, toDate, tipoAds, statoScheda, null, params);
			ads = wrapperAdsDAO.getSearchResultsForExcel(searchQuery, params);
			return exportAds2Excel(ads, false);
			//return Response.ok(ads).build();
		}catch(Exception ex) {
			ex.printStackTrace();
			return Response.serverError().build();
		}
		
		
	}
	
	@Override
	public Response searchExcel(ExcelDTO excel, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		List<AreaDiSaggio> list = wrapperAdsDAO.findAreaDiSaggioByCodiceAdsListExcel(
				createWhereClauseForSearchIn(excel.getCodiceADSList(), createMainQueryForSearch()));
		return exportAds2Excel(list, true);
		
	}
	
	
	
	public Response exportAds2Excel(List<AreaDiSaggio> list, boolean exportDetails) {
		
		HSSFWorkbook hwb = new HSSFWorkbook();
		ResponseBuilder response = null;
		try {
			List<AreaDiSaggioEnum> columns = Arrays.asList(AreaDiSaggioEnum.values());
	
			// Create a Sheet
			Sheet sheet = hwb.createSheet("Area di saggio");
	
			// Create a Font for styling header cells
			Font headerFont = hwb.createFont();
	
			headerFont.setFontHeightInPoints((short) 16);
			headerFont.setColor(IndexedColors.DARK_BLUE.getIndex());
	
			// Create a CellStyle with the font
			CellStyle headerCellStyle = hwb.createCellStyle();
			headerCellStyle.setFont(headerFont);
			headerCellStyle.setWrapText(true);
	
			// Create a Row
			Row headerRow = sheet.createRow(0);
			headerRow.setHeightInPoints(24);
	
			// Create cells
			for (int i = 0; i < columns.size(); i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(columns.get(i).getValue());
				cell.setCellStyle(headerCellStyle);
				headerRow.setRowStyle(headerCellStyle);
			}
	
			int rowNum = 1;
			if (list != null) {
				
				
				String allIds = "";
				for (AreaDiSaggio area : list) {
					Row row = sheet.createRow(rowNum++);
	
					row.createCell(0).setCellValue(area.getCodiceADS());
	
					row.createCell(1).setCellValue(area.getComune());
	
					row.createCell(2).setCellValue(area.getCategoriaForestale());
	
					row.createCell(3).setCellValue(area.getAmbitoDiRilevamento());
	
					row.createCell(4).setCellValue(area.getDettaglioAmbito());
	
					row.createCell(5).setCellValue(area.getTipologia());
	
					row.createCell(6).setCellValue(area.getDataRilevamento());
					
					row.createCell(7).setCellValue(area.getStatoScheda()==null?"":area.getStatoScheda().getDescrStatoAds());
					
					allIds += area.getIdgeoPtAds()+","; 
					//rowNumDomCam = createExcelAlberiInfo(area.getIdgeoPtAds(), hwb, true,sheetAlberiCamDom, rowNumDomCam);
					//rowNumCav = createExcelAlberiInfo(area.getIdgeoPtAds(), hwb, false, sheetAlberiCav, rowNumCav);
	
				}
				
				if (exportDetails && !allIds.isEmpty()) {
					allIds = allIds.substring(0, allIds.length()-1); // remove last comma	
					exportAllAlberiInfo2Excel(hwb, allIds);
				}
				
				
			}
			// Resize all columns to fit the content size
			for (int i = 0; i < columns.size(); i++) {
				sheet.autoSizeColumn(i);
			}
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				hwb.write(baos);
			} catch (IOException e) {
				e.printStackTrace();
				logger.fatal("An exception occurred.", e);
			}
			
			LocalDateTime now = LocalDateTime.now();
	
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
	
			String formatDateTime = now.format(formatter);
	
			String filename = "area_di_saggio_" + formatDateTime;
			
			response = Response.ok(baos.toByteArray());
			response.header("Content-disposition", "attachment; filename=" + filename + ".xls");
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return response.build();
	}

	@Override
	public Response exportAreaDiSaggionDetails(Long idgeoPtAds, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		HSSFWorkbook hwb = new HSSFWorkbook();
		ResponseBuilder response = null;

		AreaDiSaggio ads = null;
		try {
			ads = wrapperAdsDAO.findAreaDiSaggioByCodiceAds(
					createWhereClauseForSearch(idgeoPtAds, createMainQueryForSearch1()), parameters);

			// Create a Sheet
			Sheet sheet = hwb.createSheet("Area di saggio");
			for (int i = 0; i < 8; i++) {
				sheet.setColumnWidth(i, 6000);
			}

			final int rowNum = 15;

			Font defaultFontTitle = hwb.createFont();
			defaultFontTitle.setFontHeightInPoints((short) 12);
			defaultFontTitle.setBold(true);
			//;Boldweight(Font.BOLDWEIGHT_BOLD);
			
			Font defaultFontValue = hwb.createFont();
			defaultFontValue.setFontHeightInPoints((short) 12);
			
			for (int i = 0; i < rowNum; i++) {
				List<String> columnNames = getColumnNamesForExportAreaDiSaggio(i);
				List<Object> rowValues = getRowValuesForAreaDiSaggioExport(ads, i);
				Row row = sheet.createRow(i);
				row.setHeightInPoints(30);
				CellStyle cellStyle = getCellStyleForExportAreaDiSaggio(hwb, i);
				CellStyle cellStyleVaue = hwb.createCellStyle();
				cellStyleVaue.cloneStyleFrom(cellStyle);
				cellStyleVaue.setFont(defaultFontValue);
				cellStyle.setFont(defaultFontTitle);
				for (int j = 0; j < 3; j++) { // in order to write all 6 columns in every row
					
					Cell columnNamecell = row.createCell(j * 2);
					Cell columnValueCell = row.createCell(j * 2 + 1);
					columnNamecell.setCellStyle(cellStyle);
					columnValueCell.setCellStyle(cellStyleVaue);
					if (columnNames.size() > j) {
						columnNamecell.setCellValue(getCellValueAsString(columnNames.get(j)));
						setCellValue(columnValueCell, rowValues.get(j));
					} else {
						columnNamecell.setCellValue("");
						columnValueCell.setCellValue("");
					}
				}
			}

			Sheet sheetAlberiCamDom = hwb.createSheet("Area di saggio-Alberi Campione e Dominante");
			createExcelAlberiInfo(idgeoPtAds, hwb, true,sheetAlberiCamDom, 1);
			Sheet sheetAlberiCav = hwb.createSheet("Area di saggio-Cavallettamento");
			createExcelAlberiCavallettati(idgeoPtAds, hwb, sheetAlberiCav, 1);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				hwb.write(baos);
			} catch (IOException e) {
				e.printStackTrace();
				logger.fatal("An exception occurred.", e);
			}

			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
			String formatDateTime = now.format(formatter);
			String filename = "area_di_saggio_" + idgeoPtAds + "_" + formatDateTime;

			response = Response.ok(baos.toByteArray());
			response.header("Content-disposition", "attachment; filename=" + filename + ".xls");
			return response.build();

		} catch (RecordNotFoundException e) {
			e.printStackTrace();
			return Response.ok(ErrorConstants.NON_TROVATO).build();
		}

	}

	private String getCellValueAsString(Object value) {
		return value != null ? value.toString() : "";
	}

	private void setCellValue(Cell cell, Object value) {

		if (value == null) {
			cell.setCellType(CellType.BLANK);
			return;
		}

		if (value instanceof BigDecimal) {
			double doubleValue = ((BigDecimal) value).doubleValue();
			cell.setCellValue(doubleValue);
		}

		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		}
		if (value instanceof String) {
			cell.setCellValue((String) value);
		}

		if (value instanceof Byte) {
			cell.setCellValue((Byte) value);
		}

	}

	private CellStyle getCellStyleForExportAreaDiSaggio(HSSFWorkbook hwb, int rowNumber) {

		
//		defaultFont.setColor(IndexedColors.WHITE.getIndex());
		CellStyle defaultCellStyle = hwb.createCellStyle();
		defaultCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//		defaultCellStyle.setFont(defaultFont);
		defaultCellStyle.setWrapText(true);
		defaultCellStyle.setBorderBottom(BorderStyle.THIN);
		defaultCellStyle.setBottomBorderColor(IndexedColors.WHITE.getIndex());
		defaultCellStyle.setBorderLeft(BorderStyle.THIN);
		defaultCellStyle.setLeftBorderColor(IndexedColors.WHITE.getIndex());
		defaultCellStyle.setBorderRight(BorderStyle.THIN);
		defaultCellStyle.setRightBorderColor(IndexedColors.WHITE.getIndex());
		defaultCellStyle.setBorderTop(BorderStyle.THIN);
		defaultCellStyle.setTopBorderColor(IndexedColors.WHITE.getIndex());

		switch (rowNumber) {

		case 0:
		case 1:
		case 2:
		case 3:
			HSSFColor grey20 = this.setColor(hwb, IndexedColors.GREY_25_PERCENT.getIndex(), (byte)248, (byte)248, (byte)248);
			defaultCellStyle.setFillForegroundColor(grey20.getIndex());
//			defaultCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			break;

		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
			HSSFColor grey40 = this.setColor(hwb, IndexedColors.GREY_40_PERCENT.getIndex(), (byte)245, (byte)245, (byte)245);
			defaultCellStyle.setFillForegroundColor(grey40.getIndex());
//			defaultCellStyle.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
			break;
		
		case 9:
		case 10:
		case 11:			
		case 12:
		case 13:
		case 14:
			HSSFColor grey50 = this.setColor(hwb, IndexedColors.GREY_50_PERCENT.getIndex(), (byte)240, (byte)240, (byte)240);
			defaultCellStyle.setFillForegroundColor(grey50.getIndex());
			//defaultCellStyle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
			break;
		}
				
		return defaultCellStyle;

	}
	
	public HSSFColor setColor(HSSFWorkbook workbook,short index, byte r,byte g, byte b){
	    HSSFPalette palette = workbook.getCustomPalette();
	    HSSFColor hssfColor = null;
	    try {
	        hssfColor= palette.findColor(r, g, b); 
	        
	        if (hssfColor == null ){
	        	palette.setColorAtIndex(index, r, g, b);
	            hssfColor = palette.getColor(index);
	        }
	    } catch (Exception e) {
	    	e.printStackTrace();
	        logger.error(e);
	    }

	    return hssfColor;
	}

	private List<Object> getRowValuesForAreaDiSaggioExport(AreaDiSaggio ads, int row) {

		List<Object> valueList = new ArrayList<Object>();

		switch (row) {

		case 0:
			valueList.add(ads.getComune());
			valueList.add(ads.getUtmEst()); // .doubleValue());
			valueList.add(ads.getUtmNord()); // .doubleValue());
			break;
		case 1:
			valueList.add(ads.getQuota());
			valueList.add(ads.getEspozione());
			valueList.add(ads.getInclinazione());
			break;
		case 2:
			valueList.add(ads.getDensitaCamp());
			valueList.add(ads.getRaggioArea());
			valueList.add(ads.getParticellaForestale());
			break;
		case 3:
			valueList.add(ads.getProprieta());
			break;
		case 4:
			valueList.add(ads.getCategoriaForestale());
			valueList.add(ads.getTipoForestale());
			valueList.add(ads.getAssettoEvolutivoColturale());
			break;
		case 5:
			valueList.add(ads.getDescrTipoStrutturale());
			valueList.add(ads.getStadioDiSviluppo());
			valueList.add(ads.getClasseDiFertilita());
			break;
		case 6:
			valueList.add(ads.getnCepaie());
			valueList.add(ads.getRinnovazione());
			valueList.add(ads.getSpeciePrevalenteRinnovDescr());
			break;

		case 7:
			valueList.add(ads.getCoperturaChiome());
			valueList.add(ads.getCoperturaCespugli());
			valueList.add(ads.getCoperturaErbacea());
			break;

		case 8:
			valueList.add(ads.getLettiera());
			break;

		case 9:
			valueList.add(ads.getDestinazione());
			valueList.add(ads.getIntervento());
			valueList.add(ads.getPriorita());
			break;
		case 10:
			valueList.add(ads.getEsboscoDescr());
			valueList.add(ads.getDefp());
			valueList.add(ads.getDesp());
			break;
		case 11:
			valueList.add(ads.getMdp());
			valueList.add(ads.getDannoPrevalente());
			valueList.add(ads.getIntesitaDanni());
			break;
		case 12:
			valueList.add(ads.getnPianteMorte());
			valueList.add(ads.getDefogliazione());
			valueList.add(ads.getIngiallimento());
			break;
		case 13:
			valueList.add(ads.getPascolamento());
			valueList.add(ads.getNote());
//			valueList.add(ads.getCombustibilePrincipale());
//			valueList.add(ads.getCombustibileSecondario());
//			break;
//		case 13:
//			valueList.add(ads.getTavola());
			break;

		}

		return valueList;
	}

	private List<String> getColumnNamesForExportAreaDiSaggio(int row) {

		List<String> columnName = new ArrayList<String>();

		switch (row) {

		case 0:
			columnName.add("Comune");
			columnName.add("UTM EST");
			columnName.add("UTM NORD");
			break;

		case 1:
			columnName.add("Quota (m s.l.m.)");
			columnName.add("Esposizione");
			columnName.add("Inclinazione (gradi)");
			break;

		case 2:
			columnName.add("Densita camp.");
			columnName.add("Raggio area");
			columnName.add("Particella forestale");
			break;
		case 3:
			columnName.add("Proprieta'");
			break;

		case 4:
			columnName.add("Categoria forestale");
			columnName.add("Tipo forestale");
			columnName.add("Assetto evolutivo-colturale");
			break;

		case 5:
			columnName.add("Tipo Strutturale");
			columnName.add("Stadio di sviluppo");
			columnName.add("Classe di fertilita");
			break;

		case 6:
			columnName.add("N. Ceppaie");
			columnName.add("Rinnovazione");
			columnName.add("Specie prevalente rinnovazione");
			break;

		case 7:
			columnName.add("Copertura chiome (%)");
			columnName.add("Copertura cespugli/ suffrutici (%)");
			columnName.add("Copertura erbacea (%)");
			break;

		case 8:
			columnName.add("Lettiera (%)");
			break;

		case 9:
			columnName.add("Destinazione");
			columnName.add("intervento");
			columnName.add("Priorita intervento");
			break;

		case 10:
			columnName.add("Esbosco");
			columnName.add("DEFP");
			columnName.add("DESP");
			break;
			
		case 11:
			columnName.add("MDP");
			columnName.add("Danno prevalente");
			columnName.add("Intensita danni (%)");
			break;
			
		case 12:
			columnName.add("N. Piante morte");
			columnName.add("Defogliazione");
			columnName.add("Ingiallimento");
			break;
			
		case 13:
			columnName.add("Pascolamento");
			columnName.add("Note");
//			columnName.add("Combustibile principale");
//			columnName.add("Combustibile secondario");
			break;
//		case 13:
//			columnName.add("Tavola");
//			break;
		}
	
		return columnName;

	}

	@Override
	public Response excelAlberiCAMeDOM(ExcelDTO excel, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		HSSFWorkbook hwb = new HSSFWorkbook();
		ResponseBuilder response = null;
		
		Sheet sheetAlberiCamDom = hwb.createSheet("Area di saggio-Alberi Campione e Dominante");
		createExcelAlberiInfo(excel.getIdgeoPtAdsList().get(0), hwb, true, sheetAlberiCamDom, 1);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			hwb.write(baos);
		} catch (IOException e) {
			logger.fatal("An exception occurred.", e);
		}

		LocalDateTime now = LocalDateTime.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

		String formatDateTime = now.format(formatter);

		String filename = "alberi_cam_dom_" + formatDateTime;
		response = Response.ok(baos.toByteArray());
		response.header("Content-disposition", "attachment; filename=" + filename + ".xls");
		return response.build();
	}

	private void exportAllAlberiInfo2Excel(HSSFWorkbook hwb, String idsForQuery) {
		
		Sheet sheetAlberiCamDom = hwb.createSheet("Area di saggio-Alberi Campione e Dominante");
		Sheet sheetAlberiCav = hwb.createSheet("Area di saggio-Cavallettamento");
		int rowNumDomCam  = 1;
		int rowNumCav  = 1;
		try {
		List<AlberiCampioneDominanteDTO> listOfAlberi  = wrapperAdsDAO.findAlberiForExcel(idsForQuery);
		
		List<AberiCAMeDOMEnum> columns = Arrays.asList(AberiCAMeDOMEnum.values());
		
		// Create a Font for styling header cells
				Font headerFont = hwb.createFont();

				headerFont.setFontHeightInPoints((short) 16);
				headerFont.setColor(IndexedColors.DARK_BLUE.getIndex());

				// Create a CellStyle with the font
				CellStyle headerCellStyle = hwb.createCellStyle();
				headerCellStyle.setFont(headerFont);
				headerCellStyle.setWrapText(true);

				// Create a Row
				Row headerRow = sheetAlberiCamDom.createRow(0);
				headerRow.setHeightInPoints(24);

				// Create cells
				for (int i = 0; i < columns.size(); i++) {
					Cell cell = headerRow.createCell(i);
					cell.setCellValue(columns.get(i).getValue());
					cell.setCellStyle(headerCellStyle);
					headerRow.setRowStyle(headerCellStyle);
				}
				
				Row headerRowCav = sheetAlberiCav.createRow(0);
				headerRowCav.setHeightInPoints(24);

				// Create cells
				for (int i = 0; i < columns.size(); i++) {
					Cell cell = headerRowCav.createCell(i);
					cell.setCellValue(columns.get(i).getValue());
					cell.setCellStyle(headerCellStyle);
					headerRowCav.setRowStyle(headerCellStyle);
				}

				Sheet activeSheet;
				if (listOfAlberi != null) {
					for (AlberiCampioneDominanteDTO area : listOfAlberi) {
						//if ((alberiDomCav && !area.getCodTipoCampione().equalsIgnoreCase("CAV")) || (!alberiDomCav && area.getCodTipoCampione().equalsIgnoreCase("CAV"))) {
							// TODO: ADD ENUMERATION for CAV
							Row row;
							if ("CAV".equals(area.getCodTipoCampione())) {
								if 	(rowNumCav > 65530) break; // reached Excel limit
								row = sheetAlberiCav.createRow(rowNumCav++);								
							} else {
								if 	(rowNumDomCam > 65530) break; // reached Excel limit
								row = sheetAlberiCamDom.createRow(rowNumDomCam++);		
								
							}
						
							
							row.createCell(0).setCellValue(area.getCodiceAds());

							row.createCell(1).setCellValue(area.getCodTipoCampione());

							row.createCell(2).setCellValue(area.getSpecieLationo());

							row.createCell(3).setCellValue(area.getQualita());

							row.createCell(4).setCellValue(area.getDiametro());

							row.createCell(5).setCellValue(area.getAltezza());

							row.createCell(6).setCellValue(area.getIncremento());

							row.createCell(7).setCellValue(area.getEta());

							row.createCell(8).setCellValue(area.getNote());
						//}
					}
				}
				
				for (int i = 0; i < columns.size(); i++) {
					sheetAlberiCamDom.autoSizeColumn(i);
					sheetAlberiCav.autoSizeColumn(i);
				}
		}catch(Exception e) {
			e.printStackTrace();throw e;
		}
		
	}
	
	private int createExcelAlberiInfo(Long idgeoPtAds, HSSFWorkbook hwb, boolean alberiDomCav, Sheet sheet, int rowNum) {
		List<AlberiCampioneDominanteDTO> list;
		
		List<AberiCAMeDOMEnum> columns = Arrays.asList(AberiCAMeDOMEnum.values());		
		list = wrapperAdsDAO.findAlberiCampioneDominante( createWhereClauseForSearch(idgeoPtAds, searchForAlberiCampioneDominante()), parameters);

		// Create a Font for styling header cells
		Font headerFont = hwb.createFont();

		headerFont.setFontHeightInPoints((short) 16);
		headerFont.setColor(IndexedColors.DARK_BLUE.getIndex());

		// Create a CellStyle with the font
		CellStyle headerCellStyle = hwb.createCellStyle();
		headerCellStyle.setFont(headerFont);
		headerCellStyle.setWrapText(true);

		// Create a Row
		Row headerRow = sheet.createRow(0);
		headerRow.setHeightInPoints(24);

		// Create cells
		for (int i = 0; i < columns.size(); i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns.get(i).getValue());
			cell.setCellStyle(headerCellStyle);
			headerRow.setRowStyle(headerCellStyle);
		}

		
		if (list != null) {
			for (AlberiCampioneDominanteDTO area : list) {
				if (!area.getCodTipoCampione().equalsIgnoreCase("CAV")) {
					Row row = sheet.createRow(rowNum++);
					
					row.createCell(0).setCellValue(area.getCodiceAds());

					row.createCell(1).setCellValue(area.getCodTipoCampione());

					row.createCell(2).setCellValue(area.getSpecieLationo());

					row.createCell(3).setCellValue(area.getQualita());

					row.createCell(4).setCellValue(area.getDiametro());

					row.createCell(5).setCellValue(area.getAltezza());

					row.createCell(6).setCellValue(area.getIncremento());

					row.createCell(7).setCellValue(area.getEta());

					row.createCell(8).setCellValue(area.getNote());
				}
			}
		}
		// Resize all columns to fit the content size
		for (int i = 0; i < columns.size(); i++) {
			sheet.autoSizeColumn(i);
		}

		return rowNum;
	}
	
	private int createExcelAlberiCavallettati(Long idgeoPtAds, HSSFWorkbook hwb, Sheet sheet, int rowNum) {
		List<AlberiCampioneDominanteDTO> list;
		
		List<AlberiCavallettatiEnum> columns = Arrays.asList(AlberiCavallettatiEnum.values());		
		list = wrapperAdsDAO.findAlberiCampioneDominante( createWhereClauseForSearch(idgeoPtAds, searchForAlberiCampioneDominante()), parameters);

		// Create a Font for styling header cells
		Font headerFont = hwb.createFont();

		headerFont.setFontHeightInPoints((short) 16);
		headerFont.setColor(IndexedColors.DARK_BLUE.getIndex());

		// Create a CellStyle with the font
		CellStyle headerCellStyle = hwb.createCellStyle();
		headerCellStyle.setFont(headerFont);
		headerCellStyle.setWrapText(true);

		// Create a Row
		Row headerRow = sheet.createRow(0);
		headerRow.setHeightInPoints(24);

		// Create cells
		for (int i = 0; i < columns.size(); i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns.get(i).getValue());
			cell.setCellStyle(headerCellStyle);
			headerRow.setRowStyle(headerCellStyle);
		}

		
		if (list != null) {
			for (AlberiCampioneDominanteDTO area : list) {
				if ((area.getCodTipoCampione().equalsIgnoreCase("CAV"))) {
					Row row = sheet.createRow(rowNum++);
					
					//row.createCell(0).setCellValue(area.getCodiceAds());

					row.createCell(0).setCellValue(area.getSpecieLationo());

					//row.createCell(2).setCellValue(area.getGruppo());

					row.createCell(1).setCellValue(area.getDiametro());

					row.createCell(2).setCellValue(area.getSemePollone());
				}
			}
		}
		// Resize all columns to fit the content size
		for (int i = 0; i < columns.size(); i++) {
			sheet.autoSizeColumn(i);
		}

		return rowNum;
	}

	@Override
	public Response excelAlberiCavallettati(ExcelDTO excel, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		HSSFWorkbook hwb = new HSSFWorkbook();
		ResponseBuilder response = null;
		List<AlberiCampioneDominanteDTO> list;
		List<AberiCAMeDOMEnum> columns = Arrays.asList(AberiCAMeDOMEnum.values());

		list = wrapperAdsDAO.findAlberiCampioneDominante(
				createWhereClauseForSearch(excel.getIdgeoPtAdsList().get(0), searchForAlberiCampioneDominante()),
				parameters);

		// Create a Sheet
		Sheet sheet = hwb.createSheet("Area di saggio-Alberi Campione e Dominante");

		// Create a Font for styling header cells
		Font headerFont = hwb.createFont();

		headerFont.setFontHeightInPoints((short) 16);
		headerFont.setColor(IndexedColors.DARK_BLUE.getIndex());

		// Create a CellStyle with the font
		CellStyle headerCellStyle = hwb.createCellStyle();
		headerCellStyle.setFont(headerFont);
		headerCellStyle.setWrapText(true);

		// Create a Row
		Row headerRow = sheet.createRow(0);
		headerRow.setHeightInPoints(24);

		// Create cells
		for (int i = 0; i < columns.size(); i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns.get(i).getValue());
			cell.setCellStyle(headerCellStyle);
			headerRow.setRowStyle(headerCellStyle);
		}

		int rowNum = 1;
		if (list != null) {
			for (AlberiCampioneDominanteDTO area : list) {
				if (area.getCodTipoCampione().equalsIgnoreCase("CAV")) {
					Row row = sheet.createRow(rowNum++);

					row.createCell(1).setCellValue(area.getCodTipoCampione());

					row.createCell(2).setCellValue(area.getSpecieLationo());

					row.createCell(3).setCellValue(area.getQualita());

					row.createCell(4).setCellValue(area.getDiametro());

					row.createCell(5).setCellValue(area.getAltezza());

					row.createCell(6).setCellValue(area.getIncremento());

					row.createCell(7).setCellValue(area.getEta());

					row.createCell(8).setCellValue(area.getNote());
				}
			}
		}
		// Resize all columns to fit the content size
		for (int i = 0; i < columns.size(); i++) {
			sheet.autoSizeColumn(i);
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			hwb.write(baos);
		} catch (IOException e) {
			logger.fatal("An exception occurred.", e);
		}

		LocalDateTime now = LocalDateTime.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

		String formatDateTime = now.format(formatter);

		String filename = "alberi_cav_" + formatDateTime;
		response = Response.ok(baos.toByteArray());
		response.header("Content-disposition", "attachment; filename=" + filename + ".xls");
		return response.build();
	}

	@Override
	public Response excelRelascopicaSemplice(Long idgeoPtAds, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			HSSFWorkbook hwb = new HSSFWorkbook();
			ResponseBuilder response = null;
			List<RelascopicaSempliceDTO> list;

			list = wrapperAdsDAO.findRelascopica(
					createWhereClauseForSearch(idgeoPtAds, createRelascopicaQueryForSearch()), parameters);

			// Create a Sheet
			Sheet sheet = hwb.createSheet("Area di saggio-Relascopica Semplice");

			// Create a Row

			if (list != null) {

				if (list.size() > 0) {
					RelascopicaSempliceDTO area = list.get(0);
					createRelascopicaDetailsContent(hwb,sheet, area);
				}

				fillConteggioAngolare(hwb,sheet, list);
			}

			// Resize all columns to fit the content size
			for (int i = 0; i < 8; i++) {
				sheet.autoSizeColumn(i);
			}

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				hwb.write(baos);
			} catch (IOException e) {
				logger.fatal("An exception occurred.", e);
			}

			LocalDateTime now = LocalDateTime.now();

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

			String formatDateTime = now.format(formatter);

			String filename = "relascopica_semplice_" + formatDateTime;
			response = Response.ok(baos.toByteArray());
			response.header("Content-disposition", "attachment; filename=" + filename + ".xls");

			return response.build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}

	}
	
	private void fillConteggioAngolare(HSSFWorkbook hwb,Sheet sheet, List<RelascopicaSempliceDTO> list ) {
		int rowNum = 7;
		// Create a Font for styling header cells
		Font headerFont = hwb.createFont();
		headerFont.setFontHeightInPoints((short) 16);
		headerFont.setColor(IndexedColors.WHITE.getIndex());
		// Create a CellStyle with the font
		CellStyle headerCellStyle = hwb.createCellStyle();
		headerCellStyle.setFont(headerFont);
		headerCellStyle.setWrapText(true);
		headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
		headerCellStyle.setBorderTop(BorderStyle.THIN);
		headerCellStyle.setTopBorderColor(IndexedColors.WHITE.getIndex());

		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 1, 3));
		Row mergeHeaderRow = sheet.createRow(rowNum++);
		Cell mergedHeader = mergeHeaderRow.createCell(1);
		mergedHeader.setCellStyle(headerCellStyle);
		mergedHeader.setCellValue("Conteggio Angolare");
		mergeHeaderRow.setHeightInPoints(24);

		Row headerRow = sheet.createRow(rowNum++);
		headerRow.setHeightInPoints(24);

		String relascopicaHeaders[] = { RelascopicaSempliceEnum.SPECIE.getValue(),
				RelascopicaSempliceEnum.N_ALBERI_CONTATI_SEME.getValue(),
				RelascopicaSempliceEnum.N_ALBERI_CONTATI_SEME.getValue(),
				RelascopicaSempliceEnum.NOTE.getValue() };

		for (int i = 1; i < relascopicaHeaders.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(relascopicaHeaders[i - 1]);
			cell.setCellStyle(headerCellStyle);
		}

		Row row;

		for (RelascopicaSempliceDTO area : list) {

			row = sheet.createRow(rowNum++);

			row.createCell(1).setCellValue(area.getSpecieLatino());
			row.createCell(2).setCellValue(area.getAlberiSeme());
			row.createCell(3).setCellValue(area.getAlberiPollone());
			row.createCell(4).setCellValue(area.getNote());
		}
	}

	private void createRelascopicaDetailsContent(HSSFWorkbook hwb, Sheet sheet, RelascopicaSempliceDTO area) {
		// TODO Auto-generated method stub
		int rowNum = 1;

		Row row = sheet.createRow(rowNum++);
		
		Font defaultFontTitle = hwb.createFont();
		defaultFontTitle.setFontHeightInPoints((short) 12);
		defaultFontTitle.setBold(true);
		
		Font defaultFontValue = hwb.createFont();
		defaultFontValue.setFontHeightInPoints((short) 12);
		
		CellStyle cellStyle = getCellStyleForExportAreaDiSaggio(hwb, 1);
		CellStyle cellStyleVaue = hwb.createCellStyle();
		cellStyleVaue.cloneStyleFrom(cellStyle);
		cellStyleVaue.setFont(defaultFontValue);
		cellStyle.setFont(defaultFontTitle);
		
		int i = 0;

		row.createCell(i).setCellValue(RelascopicaSempliceEnum.COMUNE.getValue());
		row.getCell(i).setCellStyle(cellStyle);
		i++;
		row.createCell(i).setCellValue(area.getComune());
		row.getCell(i).setCellStyle(cellStyleVaue);
		
		i++;
		row.createCell(i).setCellValue(RelascopicaSempliceEnum.UTM_EST.getValue());
		row.getCell(i).setCellStyle(cellStyle);
		i++;
		row.createCell(i).setCellValue(area.getUtmEst() != null ? area.getUtmEst().doubleValue() : null);
		row.getCell(i).setCellStyle(cellStyleVaue);
		
		i++;
		row.createCell(i).setCellValue(RelascopicaSempliceEnum.UTM_NORD.getValue());
		row.getCell(i).setCellStyle(cellStyle);
		i++;
		row.createCell(i).setCellValue(area.getUtmNord() != null ? area.getUtmNord().doubleValue() : null);
		row.getCell(i).setCellStyle(cellStyleVaue);

		row = sheet.createRow(rowNum++);
		i = 0;
		row.createCell(i).setCellValue(RelascopicaSempliceEnum.QUOTA.getValue());
		row.getCell(i).setCellStyle(cellStyle);
		i++;
		row.createCell(i).setCellValue(area.getQuota());
		row.getCell(i).setCellStyle(cellStyleVaue);
		
		i++;
		row.createCell(i).setCellValue(RelascopicaSempliceEnum.ESPOSIZIONE.getValue());
		row.getCell(i).setCellStyle(cellStyle);
		i++;
		row.createCell(i).setCellValue(area.getEspozione());
		row.getCell(i).setCellStyle(cellStyleVaue);
		
		i++;
		row.createCell(i).setCellValue(RelascopicaSempliceEnum.INCLINAZIONE.getValue());
		row.getCell(i).setCellStyle(cellStyle);
		i++;
		row.createCell(i).setCellValue(area.getInclinazione());
		row.getCell(i).setCellStyle(cellStyleVaue);
		
		row = sheet.createRow(rowNum++);
		i = 0;
		row.createCell(i).setCellValue(RelascopicaSempliceEnum.PROPRIETA.getValue());
		row.getCell(i).setCellStyle(cellStyle);
		i++;
		row.createCell(i).setCellValue(area.getProprieta());
		row.getCell(i).setCellStyle(cellStyleVaue);
		
		i++;
		row.createCell(i).setCellValue(RelascopicaSempliceEnum.FATTORE_DI_NUMERAZIONE_RELASCOPICA.getValue());
		row.getCell(i).setCellStyle(cellStyle);
		i++;
		row.createCell(i).setCellValue(area.getFattoreNumerazione());
		row.getCell(i).setCellStyle(cellStyleVaue);

		// Resize all columns to fit the content size
		for (i = 0; i < 8; i++) {
			sheet.autoSizeColumn(i);
		}
	}

	@Override
	public Response excelRelascopicaCompleta(Long idgeoPtAds, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		HSSFWorkbook hwb = new HSSFWorkbook();
		ResponseBuilder response = null;
		// RelascopicaSempliceDTO has all data same as RelascopicaCompleta
		List<RelascopicaSempliceDTO> relascopicaList;

		relascopicaList = wrapperAdsDAO
				.findRelascopica(createWhereClauseForSearch(idgeoPtAds, createRelascopicaQueryForSearch()), parameters);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		Sheet sheet = hwb.createSheet("Area di saggio-Relascopica Completa");
		// Create a Sheet
		if (relascopicaList != null && relascopicaList.size() > 0) {
			RelascopicaSempliceDTO area = relascopicaList.get(0);
			createRelascopicaDetailsContent(hwb, sheet, area);
			fillConteggioAngolare(hwb,sheet, relascopicaList);
			sheet = hwb.createSheet("Cavallettamento");
			addRelascopicaAlberiCAvalleattatiSheet(hwb, sheet, relascopicaList);

			try {
				hwb.write(baos);
			} catch (IOException e) {
				logger.fatal("An exception occurred.", e);
			}
		}

		LocalDateTime now = LocalDateTime.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

		String formatDateTime = now.format(formatter);

		String filename = "relascopica_completa_" + formatDateTime;
		response = Response.ok(baos.toByteArray());
		response.header("Content-disposition", "attachment; filename=" + filename + ".xls");
		return response.build();
	}

	private void addRelascopicaAlberiCAvalleattatiSheet(HSSFWorkbook hwb, Sheet sheet,
			List<RelascopicaSempliceDTO> relascopicaList) {

		// Create a Font for styling header cells
		Font headerFont = hwb.createFont();
		headerFont.setFontHeightInPoints((short) 16);
		headerFont.setColor(IndexedColors.DARK_BLUE.getIndex());
		// Create a CellStyle with the font
		CellStyle headerCellStyle = hwb.createCellStyle();
		headerCellStyle.setFont(headerFont);
		headerCellStyle.setWrapText(true);

		// Create a Row
		Row headerRow = sheet.createRow(0);
		headerRow.setHeightInPoints(24);

		List<RelascopicaCompletaEnum> columns = Arrays.asList(RelascopicaCompletaEnum.values());
		// Create cells
		for (int i = 0; i < columns.size(); i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns.get(i).getValue());
			cell.setCellStyle(headerCellStyle);
			headerRow.setRowStyle(headerCellStyle);
		}

		int rowNum = 1;
		if (relascopicaList != null) {
			for (RelascopicaSempliceDTO area : relascopicaList) {

				if ("CAV".equals(area.getCodTipoCampione()) && (area.getDiametro() != null || area.getAltezza() != null || area.getIncremento() != null)) {
					Row row = sheet.createRow(rowNum++);

					row.createCell(0).setCellValue(area.getSpecieLatino());
					row.createCell(1).setCellValue(area.getTipo());
					row.createCell(2).setCellValue(area.getDiametro());
					row.createCell(3).setCellValue(area.getAltezza());
					row.createCell(4).setCellValue(area.getIncremento());
				}
			}
		}

		// Resize all columns to fit the content size
		for (int i = 0; i < columns.size(); i++) {
			sheet.autoSizeColumn(i);
		}
	}

	@Override
	public Response excelDatiStazionali(ExcelDTO excel, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		HSSFWorkbook hwb = new HSSFWorkbook();
		ResponseBuilder response = null;
		List<AreaDiSaggio> list;
		List<DatiStazionaliEnum> columns = Arrays.asList(DatiStazionaliEnum.values());

		list = wrapperAdsDAO.findAreaDiSaggioByCodiceAdsList(
				createWhereClauseForSearchIn(excel.getCodiceADSList(), createMainQueryForSearch1()));

		// Create a Sheet
		Sheet sheet = hwb.createSheet("Area di saggio-Dati Stazionali");

		// Create a Font for styling header cells
		Font headerFont = hwb.createFont();

		headerFont.setFontHeightInPoints((short) 16);
		headerFont.setColor(IndexedColors.DARK_BLUE.getIndex());

		// Create a CellStyle with the font
		CellStyle headerCellStyle = hwb.createCellStyle();
		headerCellStyle.setFont(headerFont);
		headerCellStyle.setWrapText(true);

		// Create a Row
		Row headerRow = sheet.createRow(0);
		headerRow.setHeightInPoints(24);

		// Create cells
		for (int i = 0; i < columns.size(); i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns.get(i).getValue());
			cell.setCellStyle(headerCellStyle);
			headerRow.setRowStyle(headerCellStyle);
		}

		int rowNum = 1;
		if (list != null) {

			for (AreaDiSaggio area : list) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(area.getComune());
				row.createCell(1).setCellValue((area.getUtmEst() != null) ? area.getUtmEst().doubleValue() : 0);
				row.createCell(2).setCellValue((area.getUtmNord() != null) ? area.getUtmNord().doubleValue() : 0);
				row.createCell(3).setCellValue(area.getQuota());
				row.createCell(4).setCellValue(area.getEspozione());
				row.createCell(5).setCellValue(area.getInclinazione());
				row.createCell(6).setCellValue(area.getDensitaCamp());
				row.createCell(7).setCellValue(area.getRaggioArea());
				row.createCell(8).setCellValue(area.getParticellaForestale());
				row.createCell(9).setCellValue(area.getProprieta());
				row.createCell(10).setCellValue(area.getCategoriaForestale());
				row.createCell(11).setCellValue(area.getTipoForestale());
				row.createCell(12).setCellValue(area.getAssettoEvolutivoColturale());
				row.createCell(13).setCellValue(area.getTipoStrutturale());
				row.createCell(14).setCellValue(area.getStadioDiSviluppo());
				row.createCell(15).setCellValue(area.getClasseDiFertilita());
				row.createCell(16).setCellValue(area.getnCepaie());
				row.createCell(17).setCellValue(area.getRinnovazione());
				row.createCell(18).setCellValue(area.getSpeciePrevalenteRinnovazione());
				row.createCell(19).setCellValue(area.getCoperturaChiome());
				row.createCell(20).setCellValue(area.getCoperturaCespugli());
				row.createCell(21).setCellValue(area.getCoperturaErbacea());
				row.createCell(22).setCellValue(area.getLettiera());
				row.createCell(23).setCellValue(area.getDestinazione());
				row.createCell(24).setCellValue(area.getIntervento());
				row.createCell(25).setCellValue(area.getPriorita());
				row.createCell(26).setCellValue(area.getEsbosco());
				row.createCell(27).setCellValue(area.getDefp());
				row.createCell(28).setCellValue(area.getDesp());
				row.createCell(29).setCellValue(area.getMdp());
				row.createCell(30).setCellValue(area.getDannoPrevalente());
				row.createCell(31).setCellValue(area.getIntesitaDanni());
				row.createCell(32).setCellValue(area.getnPianteMorte());
				row.createCell(33).setCellValue(area.getDefogliazione());
				row.createCell(34).setCellValue(area.getIngiallimento());
				row.createCell(35).setCellValue(area.getPascolamento());
				row.createCell(36).setCellValue(area.getCombustibilePrincipale());
				row.createCell(37).setCellValue(area.getCombustibileSecondario());
				row.createCell(38).setCellValue("");// todo tavola

			}
		}
		// Resize all columns to fit the content size
		for (int i = 0; i < columns.size(); i++) {
			sheet.autoSizeColumn(i);
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			hwb.write(baos);
		} catch (IOException e) {
			logger.fatal("An exception occurred.", e);
		}

		LocalDateTime now = LocalDateTime.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

		String formatDateTime = now.format(formatter);

		String filename = "area_di_saggio_dati_stazionali" + formatDateTime;
		response = Response.ok(baos.toByteArray());
		response.header("Content-disposition", "attachment; filename=" + filename + ".xls");
		return response.build();
	}

	// input.getFkSoggettoPf() == null || input.getFkSoggettoPg() == null
	private boolean validationObligatory(AreaDiSaggioDTO input) {
		return (input.getAmbitoDiRilevamento() == null || input.getComune() == null
				|| input.getDataRilevamento() == null || input.getEspozione() == null || input.getInclinazione() == null
				|| input.getParticellaForestale() == null || input.getProprieta() == null || input.getQuota() == null
				|| input.getTipologiaDiRilievo() == null && input.getUtmEST() == null || input.getUtmNORD() == null
				|| input.getIdDettaglioAmbito() == null);
	}

	@Override
	public Response insert(AreaDiSaggioDTO areaDiSaggio, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {	
			Map<String,String> errorCodiceAds =  getErrorValidationCodiceAds(areaDiSaggio.getCodiceADS(),null);
			if(errorCodiceAds != null) {
				return Response.ok(errorCodiceAds).build();
			}
			
			return Response
					.ok(wrapperAdsDAO.createAreaDiSaggio(areaDiSaggio,
							soggettoDAO.findSoggettoByCodiceFiscale(userSessionDAO.getUser(httpRequest).getCodFisc())))
					.build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().entity(e.getMessage()).build();
		}
	}
	
	@Override
	public Response insertEmpty(AreaDiSaggioDTO areaDiSaggio, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		
		try {	
			return Response
					
					.ok(wrapperAdsDAO.createEmptyAreaDiSaggio(areaDiSaggio,
							soggettoDAO.findSoggettoByCodiceFiscale(userSessionDAO.getUser(httpRequest).getCodFisc())))
					.build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().entity(e.getMessage()).build();
		}
		
	}
	
	private Map<String,String> getErrorValidationCodiceAds(String codiceADS, Long idgeoPtAds){
		boolean isCodiceAdsValid = !wrapperAdsDAO.isCodiceAdsAlreadyUsed(codiceADS,idgeoPtAds);
		if (codiceADS == null || !isCodiceAdsValid) {
			Map<String,String> error = new HashMap<String,String>();
			if(!isCodiceAdsValid) {
				error.put("error", "\"N. Ads\" gia' presente in archivio!");
			}else {
				error.put("error", "Valorizzare il campo \"N. Ads\"");
			}
			return error ;
		}
		return null;
	}

	public Response update(AreaDiSaggioDTO areaDiSaggio,@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		try {
			
			Map<String,String> errorCodiceAds =  getErrorValidationCodiceAds(areaDiSaggio.getCodiceADS(),areaDiSaggio.getIdgeoPtAds());
			if(errorCodiceAds != null) {
				return Response.ok(errorCodiceAds).build();
			}
			
			logger.info("areaDiSaggio is null? " + (areaDiSaggio == null));
			if(areaDiSaggio.getParticellaForestale() != null && areaDiSaggio.getParticellaForestale().length() > 0) {
				try {
					geoPlParticellaForestDAO.getForestParticleById(Integer.parseInt(areaDiSaggio.getParticellaForestale()));
				}catch(EmptyResultDataAccessException ex) {
					Map<String,String> resp = new HashMap<String,String>();
					resp.put("error", "Particella non valida!");
					return Response.ok(resp).build();
				}
			}
			
			if((areaDiSaggio.getIdDettaglioAmbito() == null || areaDiSaggio.getIdDettaglioAmbito() == -1) 
					&& areaDiSaggio.getAmbitoSpecifico() != null 
					&& areaDiSaggio.getAmbitoDiRilevamento() != null) {
				areaDiSaggio.setIdDettaglioAmbito(getAmbitoDettaglio(areaDiSaggio.getAmbitoDiRilevamento(),areaDiSaggio.getAmbitoSpecifico()));
				logger.info("idDettaglioAmbito: " + areaDiSaggio.getIdDettaglioAmbito());
			}
			return Response
					.ok(wrapperAdsDAO.updateAreaDiSaggio(areaDiSaggio,
							soggettoDAO.findSoggettoByCodiceFiscale(userSessionDAO.getUser(httpRequest).getCodFisc())))
					.build();
			
		}catch(Exception ex) {
			ex.printStackTrace();
			return Response.serverError().entity(ex.getMessage()).build();
		}
	}
	
	@Override
	@Transactional
	public Response insertSuperficieDati1(AdsDatiStazionaliOne adsDatiStazionaliOne, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			
			List<StepValidationErrors> listOfErrors = wrapperAdsDAO.insertSuperficieDati1(adsDatiStazionaliOne);
			return BaseResponses.successResponse(listOfErrors);
			
		}catch(Exception ex) {
			ex.printStackTrace();
			return BaseResponses.errorResponse(ex);
		}

	}

	@Override
	public Response getRelascopicaQueryForSearch(Long idgeoPtAds, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		List<RelascopicaSempliceDTO> ads = wrapperAdsDAO
				.findRelascopica(createWhereClauseForSearch(idgeoPtAds, createRelascopicaQueryForSearch()), parameters);
		return Response.ok(ads).build();
	}

	public Response getRelascopicaCompleta(Long idgeoPtAds, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		List<RelascopicaSempliceDTO> ads = wrapperAdsDAO.findRelascopicaCompleta(
				createWhereClauseForSearch(idgeoPtAds, createRelascopicaCompletaSearch()), parameters);
		return Response.ok(ads).build();
	}

	@Override
	public Response insertSuperficieDati2(AdsDatiStazionaliTwo adsDatiStazionaliTwo, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		try {
			
			List<StepValidationErrors> listOfErrors =wrapperAdsDAO.insertSuperficieDati2(adsDatiStazionaliTwo);
			return BaseResponses.successResponse(listOfErrors);
			
		}catch(Exception ex) {
			ex.printStackTrace();
			return BaseResponses.errorResponse(ex);
		}
	}

	public static String translateFieldName(String fieldName) {
		switch (fieldName) {
		case "codiceADS":
			return "codice_ads";
		case "comune":
			return "denominazione_comune";
		case "ambitoDiRilevamento":
			return "descr_ambito";
		case "dettaglioAmbito":
			return "descr_ambito";
		case "tipologia":
			return "descr_tipo_ads";
		case "dataRilevamento":
			return "data_ril";
		case "categoriaForestale":
			return "tipo";
		case "specie":
			return "id_specie";
		case "tipo":
			return "flg_pollone_seme";
		case "diametro":
			return "diametro";
		case "altezza":
			return "altezza";
		case "incremento":
			return "incremento";
		case "gruppo":
			return "cod_gruppo";
		case "seme":
			return "flg_pollone_seme";
		case "denominazionePfa":
			return "pfa.denominazione";
		case "provinciaPfa":
			return "prov.denominazione_prov";
		case "comunePfa":
			return "comune.denominazione_comune";
		case "idgeoPtAds":
			return "idgeo_pt_ads";
		default:
			return null;
		}
	}

	@Override
	public Response getNumberOfNextStep(Long idgeoPtAds, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperAdsDAO.getNumberOfLastCompletedStep(idgeoPtAds)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getRelascopicaCompletaSort(Long idgeoPtAds, int page, int limit, String sort,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		PaginatedList<RelascopicaSempliceDTO> ads = wrapperAdsDAO.findRelascopicaCompletaSort(
				createWhereClauseForSearchCompleta(idgeoPtAds, createRelascopicaQueryForSearch(), sort), page, limit,
				parameters);
		return Response.ok(ads).build();
	}
	
	private Integer getAmbitoDettaglio(String ambitoDiRilevamento,String ambitoSpecifico) {
		Integer idAmbito = Integer.parseInt(ambitoDiRilevamento);
		logger.info("getAmbitoDettaglio - ambitoDiRilevamento: " + ambitoDiRilevamento +
				" - ambitoSpecifico: " + ambitoSpecifico);
		List<AmbitoRilievo> listAmbito = ambitoRilievoDAO.findAmbitoByPadreAndDescr(idAmbito,ambitoSpecifico);
		if(listAmbito.size() > 0) {
			return listAmbito.get(0).getIdAmbito();
		}else {
			AmbitoRilievo ambito = new AmbitoRilievo();
			ambito.setFkPadre(idAmbito);
			ambito.setDescrAmbito(ambitoSpecifico);
			ambito = ambitoRilievoDAO.saveNewAmbito(ambito);
			listAmbito = ambitoRilievoDAO.findAmbitoByPadreAndDescr(idAmbito,ambitoSpecifico);
			return listAmbito.get(0).getIdAmbito();
		}
	}

	private StringBuilder createWhereClauseForSearchCompleta(Long idgeoPtAds,
			StringBuilder createRelascopicaCompletaSearch, String sort) {
		String sep = "WHERE";
		parameters = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();

		if (idgeoPtAds != null) {
			sql.append(sep).append(" ads.idgeo_pt_ads = ?");
			parameters.add(idgeoPtAds);
		}
		if (sort != null) {
			String sortSql = getQuerySortingSegment(sort);
			return createRelascopicaCompletaSearch().append(sql).append(sortSql);
		}

		return createRelascopicaCompletaSearch.append(sql);
	}

	private StringBuilder createWhereClauseForSearchCompletaCAV(Long idgeoPtAds,
			StringBuilder createRelascopicaCompletaSearch, String sort) {
		String sep = "WHERE";
		parameters = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();

		if (idgeoPtAds != null) {
			sql.append(sep).append(" s.cod_tipo_campione = 'CAV'").append(" AND").append(" ads.idgeo_pt_ads = ?");
			parameters.add(idgeoPtAds);
		}
		if (sort != null) {
			String sortSql = getQuerySortingSegment(sort);
			return createRelascopicaCompletaSearch.append(sql).append(sortSql);
		}

		return createRelascopicaCompletaSearch.append(sql);
	}

	@Override
	public Response getAlberiCAV(Long idgeoPtAds, int page, int limit, String sort, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		PaginatedList<AlberiCampioneDominanteDTO> ads = wrapperAdsDAO.findAlberiCAV(
				createWhereClauseForSearchCAV(idgeoPtAds, searchForAlberiCampioneDominante(), sort), page, limit,
				parameters);
		return Response.ok(ads).build();
	}

	@Override
	public Response getRelascopicaCompletaSortCAV(Long idgeoPtAds, int page, int limit, String sort,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		PaginatedList<RelascopicaSempliceDTO> ads = wrapperAdsDAO.findRelascopicaCompletaSort(
				createWhereClauseForSearchCompletaCAV(idgeoPtAds, createRelascopicaQueryForSearch(), sort), page, limit,
				parameters);
		return Response.ok(ads).build();
	}

	@Override
	public Response getSuperficieDati1(Long idgeoPtAds, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		AreaDiSaggio ads = null;
		logger.info("getSuperficieDati1 start");
		try {
			ads = wrapperAdsDAO.findDatiStazionali1(
					createWhereClauseForSearch(idgeoPtAds, createMainQueryForGetDatiStazionali1()), parameters);
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
			return Response.serverError().entity(ErrorConstants.NON_TROVATO).build();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return Response.ok(ads).build();
	}

	@Override
	public Response getSuperficieDati2(Long idgeoPtAds, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		AreaDISaggioDataStazionaliTwo ads;
		try {
			ads = wrapperAdsDAO.findDatiStazionali2(idgeoPtAds);
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
			return Response.ok(ErrorConstants.NON_TROVATO).build();
		}
		return Response.ok(ads).build();
	}
	
	
	private Integer[] convertFromStringToIntArray(String [] listOfStringIDs) {
		Integer listOfIntegerIds[] = new Integer[listOfStringIDs.length];
		for (int i = 0; i < listOfIntegerIds.length; i++) {
			listOfIntegerIds[i] = listOfStringIDs[i]==null || listOfStringIDs[i].isEmpty() ? null: Integer.parseInt(listOfStringIDs[i]);
		}
		
		return listOfIntegerIds;
	}

	public Response deleteAds(@PathParam("idgeoPtAds") Long idgeoPtAds, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) {
		
		try {
			wrapperAdsDAO.deleteAreaDiSaggio(idgeoPtAds);		
		} catch (Exception e) {
			e.printStackTrace();
			return BaseResponses.errorResponse(e); 
		}
		return BaseResponses.successResponse("Eliminazione riuscita");
	}
}
