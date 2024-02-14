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

import it.csi.idf.idfapi.business.be.TipoIstanzaApi;
import it.csi.idf.idfapi.business.be.impl.dao.TipoIstanzaDAO;
import it.csi.idf.idfapi.business.be.impl.dao.UserSessionDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperAdpforHomeDAO;
import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class TipoIstanzaApiServiceImpl extends SpringSupportedResource implements TipoIstanzaApi {

	@Autowired
	private TipoIstanzaDAO tipoIstanzaDAO;
	
	@Autowired
	WrapperAdpforHomeDAO wrapperAdpforHomeDAO;
	
	@Autowired
	UserSessionDAO userSessionDAO;
	
	@Override
	public Response getTipoTrasformazione() {
		try {
			return Response.ok(tipoIstanzaDAO.getTrasformazioneTipo()).build();
		} catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Override
	public Response getTipiIstanzaAttivi(SecurityContext securityContext, HttpHeaders httpHeaders, 
			HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			return Response.ok(tipoIstanzaDAO.getTipiIstanzaByUser(confUtente.getIdConfigUtente())).build();
		} catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getTipiIstanzaAttiveAmbito(Integer idAmbito, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			return Response.ok(tipoIstanzaDAO.getTipiIstanzaAttiveAmbito(idAmbito)).build();
		} catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getAllTipoInstanza() {
		try {
			return Response.ok(tipoIstanzaDAO.getAllTipoIstanza()).build();
		} catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	
}
