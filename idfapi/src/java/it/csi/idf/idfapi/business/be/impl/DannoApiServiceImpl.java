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

import it.csi.idf.idfapi.business.be.DannoApi;
import it.csi.idf.idfapi.business.be.impl.dao.DannoDAO;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class DannoApiServiceImpl extends SpringSupportedResource implements DannoApi {

	@Autowired
	DannoDAO dannoDAO;
	
	@Override
	public Response getDanni(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		
		return Response.ok(dannoDAO.findAllDanno()).build();
		
	}

}
