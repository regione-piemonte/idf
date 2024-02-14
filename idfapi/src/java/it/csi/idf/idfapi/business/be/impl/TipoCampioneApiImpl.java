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

import it.csi.idf.idfapi.business.be.TipoCampioneApi;
import it.csi.idf.idfapi.business.be.impl.dao.TipoCampioneDAO;
import it.csi.idf.idfapi.dto.TipoCampione;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class TipoCampioneApiImpl  extends SpringSupportedResource implements TipoCampioneApi {

	@Autowired
	TipoCampioneDAO tipoCampioneDAO;
	
	@Override
	public Response getAllTipoCampioneByFlagVisibile(Byte flgVisibile, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		
		List<TipoCampione> tipoCampioneList = tipoCampioneDAO.findAllTipoCampione(flgVisibile);
		return Response.ok(tipoCampioneList).build();
	}

}
