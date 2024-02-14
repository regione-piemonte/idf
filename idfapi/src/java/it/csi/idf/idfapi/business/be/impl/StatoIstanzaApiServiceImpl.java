/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.StatoIstanzaApi;
import it.csi.idf.idfapi.business.be.impl.dao.StatoIstanzaDAO;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class StatoIstanzaApiServiceImpl extends SpringSupportedResource implements StatoIstanzaApi{
	
	@Autowired
	private StatoIstanzaDAO statoIstanzaDAO;

	@Override
	public Response getAllVisibleStatiForBackEndSearch(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return Response.ok(statoIstanzaDAO.getAllVisibleStatesForBackEndSearch()).build();
	}
	@Override
	public Response getAllVisibleStatesForSportelloSearch(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return Response.ok(statoIstanzaDAO.getAllVisibleStatesForSportelloSearch()).build();
	}
}
