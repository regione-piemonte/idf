/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.AmbitoRilievoApi;
import it.csi.idf.idfapi.business.be.impl.dao.AmbitoRilievoDAO;
import it.csi.idf.idfapi.dto.AmbitoRilievo;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class AmbitoRilievoApiServiceImpl extends SpringSupportedResource implements AmbitoRilievoApi {
	
	private static final Logger logger = Logger.getLogger(AmbitoRilievoApiServiceImpl.class);
	
	@Autowired
	AmbitoRilievoDAO ambitoRilievoDAO;

	@Override
	public Response getAllAmbitoRilievo(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		List<AmbitoRilievo> ambito = ambitoRilievoDAO.findAllAmbitoRilievo();
		return Response.ok(ambito).build();
	}
	
	@Override
	public Response getAmbitoRilievoSpecificare(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		List<AmbitoRilievo> ambito = ambitoRilievoDAO.findAmbitoRilievoSpecificare();
		return Response.ok(ambito).build();
	}
	
	
	@Override
	public Response saveAmbitoRilievo(AmbitoRilievo ambito, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		
		try {
			AmbitoRilievo savedAmbito = ambitoRilievoDAO.saveNewAmbito(ambito);
			return Response.ok(savedAmbito).build();
		}catch(Exception e) {
			return Response.serverError().build();
		}
	}
	
	
	public Response findChildAmbitos(@PathParam("idAmbito") Integer idAmbito, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest) {
		try {
			List<AmbitoRilievo> ambitoList = ambitoRilievoDAO.findChildAmbitos(idAmbito);
			return Response.ok(ambitoList).build();
		}catch(Exception e) {
			return Response.serverError().build();
		}
	}

	

}
