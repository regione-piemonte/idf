/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.StadioSviluppoApi;
import it.csi.idf.idfapi.business.be.impl.dao.StadioSviluppoDAO;
import it.csi.idf.idfapi.dto.StadioSviluppo;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class StadioSviluppoApiServiceImpl extends SpringSupportedResource implements StadioSviluppoApi {
	
	@Autowired
	StadioSviluppoDAO stadioSviluppoDAO;

	@Override
	public Response getAllStadioSviluppo(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		List<StadioSviluppo> stadioSviluppo = stadioSviluppoDAO.findAllStadioSviluppo();
		return Response.ok(stadioSviluppo).build();
	}

}
