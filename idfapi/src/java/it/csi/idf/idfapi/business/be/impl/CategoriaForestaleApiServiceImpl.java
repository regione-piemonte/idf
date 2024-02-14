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

import it.csi.idf.idfapi.business.be.CategoriaForestaleApi;
import it.csi.idf.idfapi.business.be.impl.dao.CategoriaForestaleDAO;
import it.csi.idf.idfapi.dto.CategoriaForestale;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class CategoriaForestaleApiServiceImpl extends SpringSupportedResource implements CategoriaForestaleApi {
	@Autowired
	CategoriaForestaleDAO categoriaForestaleDAO;

	

	@Override
	public Response getAllCategoriaForestale(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		List<CategoriaForestale> categoria = categoriaForestaleDAO.findAllCategoriaForestale();
		return Response.ok(categoria).build();
	}

}
