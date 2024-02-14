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

import it.csi.idf.idfapi.business.be.AssettoApi;
import it.csi.idf.idfapi.business.be.impl.dao.AssettoDAO;
import it.csi.idf.idfapi.dto.Assetto;
import it.csi.idf.idfapi.util.SpringSupportedResource;


public class AssettoApiServiceImpl extends SpringSupportedResource implements AssettoApi {
	@Autowired
	AssettoDAO assettoDAO;

	

	@Override
	public Response getAllAssetto(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		List<Assetto> assetto = assettoDAO.findAllAssetto();
		return Response.ok(assetto).build();
	}

}
