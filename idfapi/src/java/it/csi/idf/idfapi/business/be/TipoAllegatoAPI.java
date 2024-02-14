/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be;


import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;


@Path("/private/tipoAllegato")
@Produces({ "application/json" })
public interface TipoAllegatoAPI {

	@GET
	@Produces({ "application/json" })
	public Response getAllTippoAllegato(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/pfa")
	@Produces({ "application/json" })
	Response getAllTipoAllegatoPfa(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
								   @Context HttpServletRequest httpRequest);	
	
	@POST
	@Path("/allTipoAllegatoTipoById/{idTipoAllegato}")
	@Produces({ "application/json" })
	public Response getAllTipoAllegatoTipoById(
			@PathParam("idTipoAllegato") Integer idTipoAllegato,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
}
