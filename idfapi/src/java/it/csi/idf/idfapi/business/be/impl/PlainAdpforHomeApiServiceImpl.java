/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.PlainAdpforHomeApi;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.impl.dao.UserSessionDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperAdpforHomeDAO;
import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.dto.PlainAdpforHome;
import it.csi.idf.idfapi.dto.TSoggetto;
import it.csi.idf.idfapi.dto.UserInfo;
import it.csi.idf.idfapi.util.ProceduraEnum;
import it.csi.idf.idfapi.util.ProfiloUtenteEnum;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class PlainAdpforHomeApiServiceImpl extends SpringSupportedResource implements PlainAdpforHomeApi {
	
	static final Logger logger = Logger.getLogger(PlainAdpforHomeApiServiceImpl.class);
	
	public static final String LOGGED_CONFIG = "loggedConfig";

	@Autowired
	private UserSessionDAO userSessionDAO;

	@Autowired
	private WrapperAdpforHomeDAO wrapperAdpforHomeDAO;

	@Override
	public Response getPlainHome(Integer tipoIstanzaId,
								 SecurityContext securityContext, HttpHeaders httpHeaders,
								 HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			return Response.ok(wrapperAdpforHomeDAO.getAdpforHome(tipoIstanzaId, userSessionDAO.getUser(httpRequest),confUtente.getFkProfiloUtente())).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response updateFromPlainHome(PlainAdpforHome body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			UserInfo userInfo = userSessionDAO.getUser(httpRequest);
			logger.debug("userInfo: " + userInfo);
			ConfigUtente logedConfig = wrapperAdpforHomeDAO.updateAdpforHome(userInfo, body);
			httpRequest.getSession().setAttribute(LOGGED_CONFIG,logedConfig);
			logger.info("put user in session: " + logedConfig);
			return Response.ok(logedConfig).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getBackOfficeHome(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);

			return Response.ok(wrapperAdpforHomeDAO.getAdpforBackOfficeHome( userSessionDAO.getUser(httpRequest) , confUtente.getFkProfiloUtente()))
					.build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response IsUserCitadino(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		try {
			ConfigUtente confUtente = getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			return Response.ok(confUtente).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	@Override
	public Response createMeAsRichidente(TSoggetto body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {

			return Response.ok(wrapperAdpforHomeDAO.createMeAsRichidente( body,  securityContext,  httpHeaders,
					 httpRequest)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}
	
	public static ConfigUtente getConfUtente(HttpServletRequest httpRequest, WrapperAdpforHomeDAO wrapperAdpforHomeDAO,
			UserSessionDAO userSessionDAO) throws RecordNotUniqueException {
		ConfigUtente confUtente = (ConfigUtente) httpRequest.getSession().getAttribute(LOGGED_CONFIG);
		if(confUtente != null) {
			logger.info("confUtente.getFkProcedura: " + confUtente.getFkProcedura());
			if(confUtente.getFkProcedura() != null) {
				if(ProceduraEnum.GESTIONE_ISTANZE_FORESTALI.getValue() == confUtente.getFkProcedura()) {
					return confUtente;
				}
			}
		}
		confUtente = wrapperAdpforHomeDAO.getHomeData(userSessionDAO.getUser(httpRequest),1,httpRequest);
		httpRequest.getSession().setAttribute(LOGGED_CONFIG,confUtente);
		logger.info("put user in session: " + confUtente);
		
		return confUtente;
	}
	public static ConfigUtente getConfUtente(HttpServletRequest httpRequest, WrapperAdpforHomeDAO wrapperAdpforHomeDAO,
			UserSessionDAO userSessionDAO, ProceduraEnum procedura) throws RecordNotUniqueException {
		ConfigUtente confUtente = (ConfigUtente) httpRequest.getSession().getAttribute(LOGGED_CONFIG);
		logger.info("ProceduraEnum: " + procedura);
		try {
			if(confUtente == null || confUtente.getFkProcedura() !=	procedura.getValue()) {
				confUtente = wrapperAdpforHomeDAO.getHomeData(userSessionDAO.getUser(httpRequest),procedura,httpRequest);
				httpRequest.getSession().setAttribute(LOGGED_CONFIG,confUtente);
				logger.info("put user in session: " + confUtente);
			}
		}catch(Exception ex) {
			confUtente = wrapperAdpforHomeDAO.getHomeData(userSessionDAO.getUser(httpRequest),procedura,httpRequest);
			httpRequest.getSession().setAttribute(LOGGED_CONFIG,confUtente);
		}
		return confUtente;
	}
}
