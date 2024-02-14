/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.GeoPlPfaApi;
import it.csi.idf.idfapi.business.be.exceptions.DuplicateRecordException;
import it.csi.idf.idfapi.business.be.impl.dao.GeoPlPfaDAO;
import it.csi.idf.idfapi.business.be.impl.dao.UserSessionDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperAdpforHomeDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperPlPfaDAO;
import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.dto.ExcelDTO;
import it.csi.idf.idfapi.dto.GeoPlPfa;
import it.csi.idf.idfapi.util.ProfiloUtenteEnum;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class GeoPlPfaApiServiceImpl extends SpringSupportedResource implements GeoPlPfaApi {

	static final Logger logger = Logger.getLogger(GeoPlPfaApiServiceImpl.class);

	@Autowired
	private GeoPlPfaDAO geoPlPfaDAO;

	@Autowired
	private WrapperPlPfaDAO wrapperPlPfaDAO;
	
	@Autowired
	private WrapperAdpforHomeDAO wrapperAdpforHomeDAO;
	
	@Autowired
	private UserSessionDAO userSessionDAO;

	@Override
	public Response search(@Context UriInfo info, HttpServletRequest httpRequest) {
		
		try {
			Map<String, String> queryParams = new HashMap<>();
			if(userSessionDAO.getUser(httpRequest) != null) {
				ConfigUtente user = wrapperAdpforHomeDAO.getDataForPFAAccess(userSessionDAO.getUser(httpRequest), ProfiloUtenteEnum.GESTORE_PFA.getValue(), httpRequest);
				if(user.getFkProfiloUtente() > 0) {
					queryParams.put("idSoggetto", user.getFkSoggetto().toString());
				}
			}

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
	public Response getPfaSearchByID(Integer idGeoPlPfa, HttpServletRequest httpRequest) {

		try {
			Integer idSoggetto = null;
			if(userSessionDAO.getUser(httpRequest) != null) {
				ConfigUtente user = wrapperAdpforHomeDAO.getDataForPFAAccess(userSessionDAO.getUser(httpRequest), ProfiloUtenteEnum.GESTORE_PFA.getValue(), httpRequest);
				idSoggetto = user == null? null: user.getFkSoggetto();
			}
			return Response.ok(wrapperPlPfaDAO.getPublicPfaSearchByID(idGeoPlPfa,idSoggetto)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getAllGeoPlPfa() {
		return Response.ok(geoPlPfaDAO.findAllGeoPlPfa()).build();
	}

	@Override
	public Response getGeoPlPfaByID(Integer idGeoPlPfa, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		logger.info("into getGeoPlPfaByID");

		try {
			return Response.ok(geoPlPfaDAO.findGeoPlPfaById(idGeoPlPfa)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response saveGeoPlPfa(GeoPlPfa body) throws DuplicateRecordException {

		try {
			return Response.ok(geoPlPfaDAO.createGeoPlPfa(body)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response generateExcel(ExcelDTO excel, UriInfo uriInfo, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		try {
			ResponseBuilder response = Response.ok(wrapperPlPfaDAO.generateExcel(excel, uriInfo));

			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
			String formatDateTime = now.format(formatter);
			String filename = "PGF_Search_Result_" + formatDateTime;

			response.header("Content-disposition", "attachment; filename=" + filename + ".xls");
			return response.build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response IsUserForPFA(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {

			return Response.ok(wrapperAdpforHomeDAO.getDataForPFAAccess(userSessionDAO.getUser(httpRequest), ProfiloUtenteEnum.GESTORE_PFA.getValue(), httpRequest))
					.build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response isPfaEntePubblico(Integer idGeoPlPfa, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return Response.ok(geoPlPfaDAO.isPfaEntePubblico(idGeoPlPfa)).build();
	}
}
