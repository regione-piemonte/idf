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

@Path("/private/priorita")
@Produces({ "application/json" })
public interface PrioritaApi {

	@GET  
	@Produces({ "application/json" })
	 public Response getAllPriorita(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);
	
	@GET  
	@Path("/inventari")
	@Produces({ "application/json" })
	public Response getAllPrioritaInventari(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest);
	
	@GET  
	@Path("/pfa")
	@Produces({ "application/json" })
	public Response getAllPrioritaPfa(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest);
}
