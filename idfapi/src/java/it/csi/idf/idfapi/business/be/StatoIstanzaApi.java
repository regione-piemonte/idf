/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/private/statiIstanza")

@Produces({ "application/json" })
public interface StatoIstanzaApi {
	
	@GET
	@Produces({ "application/json" })
	public Response getAllVisibleStatiForBackEndSearch(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);

	@GET
	@Produces({ "application/json" })
	@Path("/sportello")
	Response getAllVisibleStatesForSportelloSearch(SecurityContext securityContext, HttpHeaders httpHeaders,
												   HttpServletRequest httpRequest);
}
