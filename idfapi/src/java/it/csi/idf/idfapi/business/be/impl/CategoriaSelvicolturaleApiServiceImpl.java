/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl;

import it.csi.idf.idfapi.business.be.CategoriaSelvicolturaleApi;
import it.csi.idf.idfapi.business.be.impl.dao.CategoriaSelvicolturaleDAO;
import it.csi.idf.idfapi.util.SpringSupportedResource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

public class CategoriaSelvicolturaleApiServiceImpl extends SpringSupportedResource implements CategoriaSelvicolturaleApi {

	@Autowired
	CategoriaSelvicolturaleDAO categoriaSelvicolturaleDAO;


	@Override
	public Response getAll(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return Response.ok(categoriaSelvicolturaleDAO.findAll()).build();	}
}
