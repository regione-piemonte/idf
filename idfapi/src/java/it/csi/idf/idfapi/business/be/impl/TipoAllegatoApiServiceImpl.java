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

import it.csi.idf.idfapi.business.be.TipoAllegatoAPI;
import it.csi.idf.idfapi.business.be.impl.dao.TipoAllegatoDAO;
import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.util.SpringSupportedResource;


public class TipoAllegatoApiServiceImpl extends SpringSupportedResource implements TipoAllegatoAPI {

	@Autowired
	TipoAllegatoDAO tipoAllegatoDAO;

	@Override 
	public Response getAllTippoAllegato(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(tipoAllegatoDAO.getAllTipoAllegato()).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}

	}

	@Override
	public Response getAllTipoAllegatoPfa(SecurityContext securityContext, HttpHeaders httpHeaders,
										  HttpServletRequest httpRequest) {		try {
			return Response.ok(tipoAllegatoDAO.getAllTipoAllegatoPfa()).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}
	
	@Override
	public Response getAllTipoAllegatoTipoById(Integer idTipoAllegato,
								 SecurityContext securityContext, HttpHeaders httpHeaders,
								 HttpServletRequest httpRequest) {
		try {
			return Response.ok(tipoAllegatoDAO.getAllTipoAllegatoTipoById(idTipoAllegato)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

}
