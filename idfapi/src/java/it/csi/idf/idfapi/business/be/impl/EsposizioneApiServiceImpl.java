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

import it.csi.idf.idfapi.business.be.EsposizioneApi;
import it.csi.idf.idfapi.business.be.impl.dao.EsposizioneDAO;
import it.csi.idf.idfapi.dto.Esposizione;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class EsposizioneApiServiceImpl extends SpringSupportedResource implements EsposizioneApi {
	
	@Autowired
	EsposizioneDAO esposizioneDAO;

	@Override
	public Response getAllEsposizione(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		List<Esposizione> esposizione =esposizioneDAO.findAllEsposizione();
		return Response.ok(esposizione).build();
	}

}
