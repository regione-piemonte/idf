/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.api.impl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import it.csi.idf.idfapi.business.be.exceptions.CustomValidator;
import it.csi.idf.idfapi.business.be.exceptions.MultiErrorException;
import it.csi.idf.idfapi.business.be.impl.PlainAdpforHomeApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.dao.UserSessionDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperAdpforHomeDAO;
import it.csi.idf.idfapi.business.be.vincolo.api.PlainAdpVincoloHomeApi;
import it.csi.idf.idfapi.business.be.vincolo.pojo.PlainAdpVincoloHome;
import it.csi.idf.idfapi.business.be.vincolo.service.WrapperAdpVincoloHomeDAO;
import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.dto.TSoggetto;
import it.csi.idf.idfapi.dto.UserInfo;
import it.csi.idf.idfapi.rest.BaseResponses;
import it.csi.idf.idfapi.util.CodiceEnum;
import it.csi.idf.idfapi.util.ProfiloUtenteEnum;
import it.csi.idf.idfapi.util.SpringSupportedResource;
import it.csi.idf.idfapi.util.TipoAccreditamentoEnum;

public class PlainAdpVincoloHomeApiServiceImpl extends SpringSupportedResource implements PlainAdpVincoloHomeApi {
	public static final String LOGGED_CONFIG = "loggedConfig";

	@Autowired
	private UserSessionDAO userSessionDAO;

	@Autowired
	private WrapperAdpVincoloHomeDAO wrapperAdpVincoloHomeDAO;
	
	@Autowired
	WrapperAdpforHomeDAO wrapperAdpforHomeDAO;

	@Override
	public Response getPlainHome(int tipoIstanzaId, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			UserInfo user = userSessionDAO.getUser(httpRequest);
			CustomValidator.getValidator(HttpStatus.BAD_REQUEST).errorIfNULL("User", user).go();
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);

			return Response.ok(wrapperAdpVincoloHomeDAO.getAdpforHome(user, confUtente.getFkProfiloUtente(), tipoIstanzaId)).build();
		} catch (MultiErrorException e) {
			e.printStackTrace();
			return BaseResponses.errorResponse(e);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response updateFromPlainHome(PlainAdpVincoloHome body, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		ConfigUtente logedConfig = new ConfigUtente();
		logger.info("inside updateFromPlainHome");
		try {
			CustomValidator.getValidator(HttpStatus.BAD_REQUEST).errorIfNULL("body", body).go();
			UserInfo user = userSessionDAO.getUser(httpRequest);
			CustomValidator.getValidator(HttpStatus.EXPECTATION_FAILED, CodiceEnum.E03).errorIfNULL("userInfo", user)
					.go();
			CustomValidator.getValidator(HttpStatus.EXPECTATION_FAILED, CodiceEnum.E03)
					.errorIfNULL("FkTipoAccreditamento", body.getFkTipoAccreditamento()).go();
			if (body.getFkTipoAccreditamento() == TipoAccreditamentoEnum.PROFESSIONISTA.name()) {
				
				// TODO validation here or in service?
				logedConfig = wrapperAdpVincoloHomeDAO.updateAdpVincoloProfessionista(user,
						ProfiloUtenteEnum.CITTADINO.getValue(), body);

			} else {
				// TODO validation here or in service?
				logedConfig = wrapperAdpVincoloHomeDAO.updateAdpVincoloRichidente(user,
						ProfiloUtenteEnum.CITTADINO.getValue(), body);
			}
			logger.debug("logedConfgi is null: " + (logedConfig == null));
			httpRequest.getSession().setAttribute(LOGGED_CONFIG, logedConfig);
			logger.debug("cnut.getIdConfigUtente(): " + logedConfig.getIdConfigUtente());
			logger.debug("cnut.getFkTipoAccreditamento(): " + logedConfig.getFkTipoAccreditamento());
			logger.debug("cnut.getFkSoggetto(): " + logedConfig.getFkSoggetto());
			
			return Response.ok(logedConfig).build();
		} catch (MultiErrorException e) {
			return BaseResponses.errorResponse(e);
		} catch (Exception e) {
			return Response.serverError().build();
		}

	}

	@Override
	public Response IsUserVincolo(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			UserInfo user = userSessionDAO.getUser(httpRequest);
			CustomValidator.getValidator(HttpStatus.EXPECTATION_FAILED, CodiceEnum.E03).errorIfNULL("userInfo", user)
					.go();
			return Response
					.ok(wrapperAdpVincoloHomeDAO.getHomeData(user, 1, httpRequest))
					.build();
		} catch (MultiErrorException e) {
			return BaseResponses.errorResponse(e);
		} catch (Exception e) {
			return BaseResponses.errorResponse(e);
		}
	}

	@Override
	public Response createMeAsRichidente(TSoggetto body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			CustomValidator.getValidator(HttpStatus.BAD_REQUEST).errorIfNULL("body", body).go();
			return Response
					.ok(wrapperAdpVincoloHomeDAO.createMeAsRichidente(body, securityContext, httpHeaders, httpRequest))
					.build();
		} catch (MultiErrorException e) {
			return BaseResponses.errorResponse(e);
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response getAmbito(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return Response.ok(wrapperAdpVincoloHomeDAO.getAmbito()).build();

	}

}
