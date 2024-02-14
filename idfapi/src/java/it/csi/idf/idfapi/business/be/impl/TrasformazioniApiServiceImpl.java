/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl;

import it.csi.idf.idfapi.business.be.TipoInterventoApi;
import it.csi.idf.idfapi.business.be.TrasformazioniApi;
import it.csi.idf.idfapi.business.be.impl.dao.TipoInterventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.TrasformazioniSelvicolturaliDAO;
import it.csi.idf.idfapi.business.be.impl.dao.TrasformazioniXlsDAO;
import it.csi.idf.idfapi.util.SpringSupportedResource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

public class TrasformazioniApiServiceImpl extends SpringSupportedResource implements TrasformazioniApi {
	
	@Autowired
	TrasformazioniSelvicolturaliDAO trasformazioniSelvicolturaliDAO;

	@Override
	public Response searchByOneParam(String search, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return Response.ok(trasformazioniSelvicolturaliDAO.searchTraformazioniXls(search)).build();
	}
}
