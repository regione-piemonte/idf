/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.EventoDatiTehniciApi;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperEventoDAO;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class EventoDatiTehniciApiImpl extends SpringSupportedResource implements EventoDatiTehniciApi {

	@Autowired
	WrapperEventoDAO wrapperEventoDAO;

	@Override
	public Response deleteEventi(Integer idEvento, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		
		try {
			wrapperEventoDAO.deleteEventi(idEvento);
			return Response.ok().build();
			
		} catch (RecordNotFoundException e) {
			return Response.serverError().build();
		}
	}
}
