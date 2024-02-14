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

import it.csi.idf.idfapi.business.be.FasciaAltimetricaApi;
import it.csi.idf.idfapi.business.be.impl.dao.FasciaAltimetricaDAO;
import it.csi.idf.idfapi.dto.FasciaAltimetrica;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class FasciaAltimetricaApiImpl extends SpringSupportedResource implements FasciaAltimetricaApi {

	@Autowired
	FasciaAltimetricaDAO fasciaAltimetricaDao;
	
	@Override
	public Response getFasciaAltimetrica(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		List<FasciaAltimetrica> fascia = fasciaAltimetricaDao.findAllFasciaAltimetrica();
		
		
		return Response.ok(fascia).build();
	}

}
