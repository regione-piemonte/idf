/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.idf.idfapi.util.TipoAccreditamentoEnum;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.FoglioApi;
import it.csi.idf.idfapi.business.be.ProvinciaApi;
import it.csi.idf.idfapi.business.be.SezioneApi;
import it.csi.idf.idfapi.business.be.exceptions.DuplicateRecordException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.impl.dao.ComuneDAO;
import it.csi.idf.idfapi.business.be.impl.dao.FoglioDAO;
import it.csi.idf.idfapi.business.be.impl.dao.ProvinciaDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SezioneDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SoggettoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.UserSessionDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperAdpforHomeDAO;
import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.dto.GeoPLProvincia;
import it.csi.idf.idfapi.dto.GeoPLProvinciaSearch;
import it.csi.idf.idfapi.dto.TSoggetto;
import it.csi.idf.idfapi.dto.UserInfo;
import it.csi.idf.idfapi.errors.Error;
import it.csi.idf.idfapi.errors.ErrorConstants;
import it.csi.idf.idfapi.util.ProfiloUtenteEnum;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class FoglioApiServiceImpl extends SpringSupportedResource implements FoglioApi {
	public static final String LOGGED_CONFIG = "loggedConfig";

	
	@Autowired
	private FoglioDAO foglioDAO;
	
	@Override
	public Response  getFoglioBySezione (@PathParam("municipality_code") String municipality_code, 
			@PathParam("section_code") String section_code,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest,HttpServletResponse response) {
		logger.info("SONO ENTRATO");
		//logger.info("SONO municipality_code: " + municipality_code);
		
        //headers.add("MyHeader", "headerValue");
		//logger.info("primo_header " +httpHeaders.getHeaderString(LOGGED_CONFIG));
		logger.info("secondo_header " +httpHeaders.getRequestHeaders().getFirst("Authorization"));
		logger.info("header_request " +httpRequest.getHeader("Authorization"));
		
		
		try {
			
		     //headers.add("Location", "https://www.example.com");
			return Response.ok(foglioDAO.findAllFoglioBySezione(municipality_code,section_code)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}	


	
}
