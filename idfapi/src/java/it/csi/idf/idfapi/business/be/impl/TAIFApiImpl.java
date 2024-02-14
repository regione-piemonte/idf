/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl;

import it.csi.idf.idfapi.business.be.TAIFApi;
import it.csi.idf.idfapi.business.be.service.TAIFService;
import it.csi.idf.idfapi.errors.ErrorConstants;
import it.csi.idf.idfapi.util.Constants;
import it.csi.idf.idfapi.util.SpringSupportedResource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;

public class TAIFApiImpl extends SpringSupportedResource implements TAIFApi {

	public static final String LOGGED_CONFIG = "loggedConfig";

	@Autowired
	private TAIFService taifService;



	@Override
	public Response getAll(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			return Response.ok(taifService.findAll()).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.ok(new ArrayList<>()).build();
		}
	}

	@Override
	public Response getByCodiceFiscale(String codiceFiscale, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			return Response.ok(taifService.findByCodiceFiscale(codiceFiscale)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.ok().build();
		}
	}
}
