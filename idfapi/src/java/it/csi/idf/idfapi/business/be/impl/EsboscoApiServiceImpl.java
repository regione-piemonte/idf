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

import it.csi.idf.idfapi.business.be.EsboscoApi;
import it.csi.idf.idfapi.business.be.impl.dao.EsboscoDAO;
import it.csi.idf.idfapi.dto.Esbosco;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class EsboscoApiServiceImpl extends SpringSupportedResource implements EsboscoApi {
	
	@Autowired
	EsboscoDAO esboscoDAO;

	@Override
	public Response getAllEsbosco(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		List<Esbosco> esbosco = esboscoDAO.findAllEsbosco();
		return Response.ok(esbosco).build();
	}

}
