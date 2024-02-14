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

import it.csi.idf.idfapi.business.be.PrioritaApi;
import it.csi.idf.idfapi.business.be.impl.dao.PrioritaDAO;
import it.csi.idf.idfapi.util.IplaEnum;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class PrioritaApiServiceImpl extends SpringSupportedResource implements PrioritaApi {

	@Autowired
	PrioritaDAO prioritaDAO;
	
	@Override
	public Response getAllPriorita(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		
		return Response.ok(prioritaDAO.findAllPriorita()).build();
	}
	
	@Override
	public Response getAllPrioritaInventari(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		
		return Response.ok(prioritaDAO.findAllPrioritaByIpla(IplaEnum.INVENTARI)).build();
	}
	
	@Override
	public Response getAllPrioritaPfa(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		
		return Response.ok(prioritaDAO.findAllPrioritaByIpla(IplaEnum.PFA)).build();
	}

}
