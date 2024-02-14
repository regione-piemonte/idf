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

import it.csi.idf.idfapi.business.be.RiepilogoApi;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperInterventoDAO;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class RiepilogoApiServiceImpl extends SpringSupportedResource implements RiepilogoApi {

	@Autowired
	private WrapperInterventoDAO wrapperInterventoDAO;

	@Override
	public Response getSummaryInterventoData(Integer idIntervento, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		try {

			return Response.ok(wrapperInterventoDAO.getDataForRiepilogo(idIntervento)).build();

		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.serverError().build();
		}
	}

}
