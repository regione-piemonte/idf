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

import it.csi.idf.idfapi.business.be.CategoriaProfessionaleApi;
import it.csi.idf.idfapi.business.be.impl.dao.CategoriaProfessionaleDAO;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class CategoriaProfessionaleApiServiceImpl extends SpringSupportedResource implements CategoriaProfessionaleApi {

	@Autowired
	CategoriaProfessionaleDAO categoriaProfessionaleDAO;

	@Override
	public Response getAllCategoriaProfessionale(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return Response.ok(categoriaProfessionaleDAO.findAllCategoriaProfessionale()).build();
	}

	@Override
	public Response getCategoriaProfessionaleById(Integer id, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		return Response.ok(categoriaProfessionaleDAO.getCategoriaProfessionaleById(id)).build();
	}

}
