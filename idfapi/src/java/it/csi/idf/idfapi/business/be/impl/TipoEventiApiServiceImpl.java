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

import it.csi.idf.idfapi.business.be.TipoEventoApi;
import it.csi.idf.idfapi.business.be.impl.dao.TipoEventoDAO;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class TipoEventiApiServiceImpl extends SpringSupportedResource implements TipoEventoApi{

	@Autowired
	private TipoEventoDAO tipoEventoDAO;
	
	@Override
	public Response getTipiDiEventi(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		return Response.ok(tipoEventoDAO.findAllTipoEventi()).build();
	}
}
