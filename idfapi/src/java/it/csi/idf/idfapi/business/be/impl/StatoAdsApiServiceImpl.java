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

import it.csi.idf.idfapi.business.be.StatoAdsApi;
import it.csi.idf.idfapi.business.be.impl.dao.StatoAdsDAO;
import it.csi.idf.idfapi.dto.StatoAds;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class StatoAdsApiServiceImpl extends SpringSupportedResource implements StatoAdsApi {
	
	@Autowired
	StatoAdsDAO statoAdsDAO;
	
	@Override
	public Response getAllStatoAds(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		List<StatoAds> specie = statoAdsDAO.search();
		return Response.ok(specie).build();
	}

}
