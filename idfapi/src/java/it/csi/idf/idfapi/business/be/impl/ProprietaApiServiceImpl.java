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

import it.csi.idf.idfapi.business.be.ProprietaApi;
import it.csi.idf.idfapi.business.be.impl.dao.ProprietaDAO;
import it.csi.idf.idfapi.dto.Proprieta;
import it.csi.idf.idfapi.util.IplaEnum;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class ProprietaApiServiceImpl extends SpringSupportedResource implements ProprietaApi {
	
	@Autowired
	ProprietaDAO proprietaDAO;

	

	@Override
	public Response getAllProprieta(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		List<Proprieta> proprieta = proprietaDAO.findAllProprieta();
		return Response.ok(proprieta).build();
	}
	
	@Override
	public Response getAllProprietaPfa(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		List<Proprieta> proprieta = proprietaDAO.findAllProprietaByIpla(IplaEnum.PFA);
		return Response.ok(proprieta).build();
	}
	
	@Override
	public Response getAllProprietaInventari(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		List<Proprieta> proprieta = proprietaDAO.findAllProprietaByIpla(IplaEnum.INVENTARI);
		return Response.ok(proprieta).build();
	}


	@Override
	public Response getAllProprietaPrimpa(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		List<Proprieta> proprieta = proprietaDAO.findAllProprietaByIpla(IplaEnum.PRIMPA);
		return Response.ok(proprieta).build();
	}
}
