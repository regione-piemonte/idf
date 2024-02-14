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

import it.csi.idf.idfapi.business.be.TipoAdsApi;
import it.csi.idf.idfapi.business.be.impl.dao.TipoAdsDAO;
import it.csi.idf.idfapi.dto.TipoAds;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class TipoAdsApiServiceImpl extends SpringSupportedResource implements TipoAdsApi {
	
	@Autowired
	TipoAdsDAO tipoAdsDAO;


	@Override
	public Response getAllTipoAds(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		List<TipoAds> tipo = tipoAdsDAO.findAllTipoAds();
		return Response.ok(tipo).build();
	}
}
