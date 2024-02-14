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

import it.csi.idf.idfapi.business.be.UsoViabilitaApi;
import it.csi.idf.idfapi.business.be.impl.dao.UsoViabilitaDAO;
import it.csi.idf.idfapi.dto.UsoViabilita;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class UsoViabilitaApiImpl extends SpringSupportedResource implements UsoViabilitaApi {

	@Autowired
	private UsoViabilitaDAO usoViabilitaDao;
	
	@Override
	public Response getAllUsoViabilita(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		
		List<UsoViabilita> viabilitaList = usoViabilitaDao.findAllUsoViabilita();
		
		return Response.ok(viabilitaList).build();
	}

}
