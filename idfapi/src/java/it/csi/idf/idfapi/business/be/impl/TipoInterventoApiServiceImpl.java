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

import it.csi.idf.idfapi.business.be.TipoInterventoApi;
import it.csi.idf.idfapi.business.be.impl.dao.TipoInterventoDAO;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class TipoInterventoApiServiceImpl extends SpringSupportedResource implements TipoInterventoApi {
	
	@Autowired
	TipoInterventoDAO tipoInterventoDAO;

	@Override
	public Response getAllTipoIntervento(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return Response.ok(tipoInterventoDAO.findAllTipoIntervento()).build();
	}

	@Override
	public Response getAllTipoInterventoByFkConfigIpla(Integer fkConfigIpla, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return Response.ok(tipoInterventoDAO.findAllTipoInterventoByFkConfigIpla(fkConfigIpla)).build();
	}

	@Override
	public Response getAllTipoInterventoByGovernoAndFkConfigIpla(Integer fkConfigIpla, Integer idGoverno,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return Response.ok(tipoInterventoDAO.findAllTipoInterventoByGovernoAndFkConfigIpla(fkConfigIpla, idGoverno)).build();
	}


	@Override
	public Response getAllTipoInterventoByGovernoAndFkConfigIpla(Integer fkConfigIpla, Integer idGoverno, Integer idMacroIntervento, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return Response.ok(tipoInterventoDAO.findAllByGovernoAndFkConfigIplaAndMacroIntervento(fkConfigIpla, idGoverno, idMacroIntervento)).build();
	}

	@Override
	public Response getAllTipoInterventoByFkConfigIplaAndCategoriaIntervento(Integer fkConfigIpla, Integer idCategoriaIntervento, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return Response.ok(tipoInterventoDAO.findAllByFkConfigIplaAndCategoriaIntervento(fkConfigIpla, idCategoriaIntervento)).build();
	}
	  //GP 20062023
	@Override
	public Response getTipoInterventoConformeDeroga(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return Response.ok(tipoInterventoDAO.getTipoInterventoConformeDeroga()).build();
	}
}
