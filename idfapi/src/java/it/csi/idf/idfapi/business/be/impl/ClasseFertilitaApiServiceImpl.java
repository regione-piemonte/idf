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

import it.csi.idf.idfapi.business.be.ClasseFertilitaApi;
import it.csi.idf.idfapi.business.be.impl.dao.CategoriaForestaleDAO;
import it.csi.idf.idfapi.business.be.impl.dao.ClasseFertilitaDAO;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class ClasseFertilitaApiServiceImpl extends SpringSupportedResource implements ClasseFertilitaApi {

	@Autowired
	ClasseFertilitaDAO classeFertilitaDAO;

	
	@Override
	public Response getAllClasseFertilita(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		
		return Response.ok().entity(classeFertilitaDAO.findAll()).build();
	}

}
