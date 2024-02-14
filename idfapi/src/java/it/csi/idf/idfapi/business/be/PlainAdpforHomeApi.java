/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.idf.idfapi.dto.PlainAdpforHome;
import it.csi.idf.idfapi.dto.TSoggetto;

@Path("/private/idfistafor")
@Produces({ "application/json" })
public interface PlainAdpforHomeApi {

	@GET
	@Produces({ "application/json" })
	public Response getPlainHome(
			@QueryParam("tipoIstanzaId") Integer tipoIstanzaId,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@PUT
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response updateFromPlainHome(PlainAdpforHome body, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/bo")
	@Produces({ "application/json" })
	public Response getBackOfficeHome(@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/home")
	@Produces({ "application/json" })
	public Response IsUserCitadino(@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	Response createMeAsRichidente(TSoggetto body, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

}
