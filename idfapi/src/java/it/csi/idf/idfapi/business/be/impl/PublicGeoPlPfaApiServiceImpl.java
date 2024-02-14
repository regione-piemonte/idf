/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import it.csi.idf.idfapi.business.be.PublicGeoPlPfaApi;
import it.csi.idf.idfapi.business.be.impl.dao.ComuneDAO;
import it.csi.idf.idfapi.business.be.impl.dao.ConfigurationDAO;
import it.csi.idf.idfapi.business.be.impl.dao.DocumentoAllegatoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.GeoPlPfaDAO;
import it.csi.idf.idfapi.business.be.impl.dao.ProvinciaDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SezioneDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperPlPfaDAO;
import it.csi.idf.idfapi.business.be.service.helper.IndexServiceHelper;
import it.csi.idf.idfapi.business.be.service.helper.dto.MetadatiIndexPfa;
import it.csi.idf.idfapi.dto.DocumentoAllegato;
import it.csi.idf.idfapi.dto.ExcelDTO;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class PublicGeoPlPfaApiServiceImpl  extends SpringSupportedResource implements PublicGeoPlPfaApi {
	
	@Autowired
	private WrapperPlPfaDAO wrapperPlPfaDAO;
	
	@Autowired
	private GeoPlPfaDAO geoPlPfaDAO;
	
	@Autowired
	ProvinciaDAO provinciaDAO;
	
	@Autowired
	private ComuneDAO comuneDAO;
	
	@Autowired
	private SezioneDAO sezioneDAO;
	
	@Autowired
	private DocumentoAllegatoDAO documentoDAO;
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired
	private DocumentoAllegatoDAO documentoAllegatoDAO;
	
	@Autowired
	ConfigurationDAO configurationDAO;
	
	
	@Override
	public Response getAgidUrlConfigation(@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		Map<String,String> mapConf = new HashMap<String,String>();
		mapConf.put("value",configurationDAO.getConfigurationByName("AGID_VALIDATION_LINK_PFAPUB"));
		return Response.ok(mapConf).build();
	}

	@Override
	public Response search(UriInfo info, HttpServletRequest httpRequest) {

		try {
			Map<String, String> queryParams = new HashMap<>();

			queryParams.put("istatProv", info.getQueryParameters().getFirst("istatProv"));
			queryParams.put("idComune", info.getQueryParameters().getFirst("idComune"));
			queryParams.put("denominazione", info.getQueryParameters().getFirst("denominazione"));
			queryParams.put("fromDate", info.getQueryParameters().getFirst("fromDate"));
			queryParams.put("toDate", info.getQueryParameters().getFirst("toDate"));
			
			queryParams.put("page", info.getQueryParameters().getFirst("page"));
			queryParams.put("limit", info.getQueryParameters().getFirst("limit"));
			queryParams.put("sort", info.getQueryParameters().getFirst("sort"));

			return Response.ok(wrapperPlPfaDAO.getPublicPianiForestaliSearch(queryParams)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Override
	public Response getDescrizioniPfa(UriInfo info, HttpServletRequest httpRequest) {
		
		String istatProv = info.getQueryParameters().getFirst("istatProv");
		Integer idComune = info.getQueryParameters().getFirst("idComune") == null? null:
			Integer.parseInt(info.getQueryParameters().getFirst("idComune"));
		return Response.ok(geoPlPfaDAO.getDescrizioniPfa(istatProv, idComune)).build();
	}

	@Override
	public Response getPfaSearchByID(Integer idGeoPlPfa, Integer idComune, Integer idPopolamento,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperPlPfaDAO.getPublicPfaSearchByID(idGeoPlPfa, null)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response generateExcel(ExcelDTO excel, UriInfo uriInfo) {
		try {
		    ResponseBuilder response = Response.ok(wrapperPlPfaDAO.generateExcel(excel, uriInfo));
		    
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
			String formatDateTime = now.format(formatter);
			String filename = "PFA_Search_Result_" + formatDateTime;
			
			response.header("Content-disposition", "attachment; filename=" + filename + ".xls");
			return response.build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getAllProvinciaSearch() {
		try {
			return Response.ok(provinciaDAO.findAllSearchProvincia()).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getDocumentiByGeoPlPfa(int geoPlPfa) {
		try {
			return Response.ok(documentoDAO.findAllDocumentiByPfa(geoPlPfa)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	
	@Override
	public Response downloadDocumento(int idDocumento, HttpServletResponse httpResponse) throws IOException {
		ResponseBuilder response = null;
		IndexServiceHelper indexSrvHelper = (IndexServiceHelper) applicationContext.getBean("indexServiceHelper");
		try {

			MetadatiIndexPfa metadatiIndexPfa = new MetadatiIndexPfa();
			DocumentoAllegato documentoAllegato = documentoAllegatoDAO.findDocumentoByID(idDocumento);
			metadatiIndexPfa.setIdIntervento(String.valueOf(documentoAllegato.getFkIntervento()));
			metadatiIndexPfa.setIdTipoAllegato(String.valueOf(documentoAllegato.getFkTipoAllegato()));
			metadatiIndexPfa.setIdAllegato(String.valueOf(idDocumento));

			byte[] contDownloaded = indexSrvHelper.indexDownloadFilePfa(documentoAllegato.getIdDocumentoAllegato().toString());

			response = Response.ok(contDownloaded);
			response.header("Content-disposition", "attachment; filename=" + documentoAllegato.getNomeAllegato());
			return response.build();

		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getComuniByProvincia(String istatProv) {
		try {
			return Response.ok(comuneDAO.findAllComuneByProvincia(istatProv)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

//	@Override
//	public Response  getSezioniByComune (String istatCom) {
//		logger.info("SONO ENTRATO");
//		try {
//			
//		     //headers.add("Location", "https://www.example.com");
//			return Response.ok(sezioneDAO.findAllSezioneByComune(istatCom)).build();
//		} catch (Exception e) {
//			e.printStackTrace();
//			return Response.serverError().build();
//		}
//	}	
	
	
	
}
