/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.RelascopicaDetailsApi;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperRelascopicaDAO;
import it.csi.idf.idfapi.dto.PlainRelascopicaSemplice;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class RelascopicadetailsApiServiceImpl extends SpringSupportedResource implements RelascopicaDetailsApi {

	static final Logger logger = Logger.getLogger(AdsrelSpecieServiceImpl.class);

	@Autowired
	WrapperRelascopicaDAO wrapperRelascopicaDAO;

	@Override
	public Response insertRelascopicaSemplice(PlainRelascopicaSemplice plainRelascopicaSemplice,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {

			wrapperRelascopicaDAO.insertRelascopicaSemplice(plainRelascopicaSemplice);
			return Response.ok().build();

		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	public Response consolidaRelascopicaSemplice(PlainRelascopicaSemplice plainRelascopicaSemplice, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		try {

			wrapperRelascopicaDAO.consolidaRelascopicaSemplice(plainRelascopicaSemplice);
			return Response.ok().build();

		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			logger.error(e.getCause().toString());
			return Response.serverError().build();
		}
		
	}
	
	
	@Override
	public Response getRelascopicaByCodiceAds(@PathParam("codiceAds") String codiceAds, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperRelascopicaDAO.getPlainRelascopicaByCodiceAds(codiceAds)).build();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			logger.error(e.getCause());
			return Response.serverError().build();
		}
	}

}
