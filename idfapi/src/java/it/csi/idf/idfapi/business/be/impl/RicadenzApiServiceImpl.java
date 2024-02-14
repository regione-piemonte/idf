/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.RicadenzaApi;
import it.csi.idf.idfapi.business.be.service.GSAREPORT;
import it.csi.idf.idfapi.business.be.service.RicadenzaService;
import it.csi.idf.idfapi.dto.RicadenzaInformazioni;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class RicadenzApiServiceImpl extends SpringSupportedResource implements RicadenzaApi {
	
	@Autowired
	private GSAREPORT gsaReport;
	
	@Autowired
	private RicadenzaService ricadenzaService;
	
	@Override
	public Response getAreeProtete(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(gsaReport.cercaTutteLePotettePerFiltri()).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response getNature2000(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			List<RicadenzaInformazioni> nature = gsaReport.cercaTuttiSic();
			nature.addAll(gsaReport.cercaTuttiZps());
			
			return Response.ok(new ArrayList<>(nature)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response getPopulamenti(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(ricadenzaService.cercaTuttiPopolamentiDaSeme()).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}
}
