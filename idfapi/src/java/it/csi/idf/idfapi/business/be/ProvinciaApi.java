/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.idf.idfapi.dto.GeoPLProvincia;

@Path("/private/provincia")
@Produces({ "application/json" })
public interface ProvinciaApi {

	@GET
	@Produces({ "application/json" })
	public Response getAllProvincia(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/search")
	@Produces({ "application/json" })
	public Response getAllProvinciaSearch(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/italia")
	@Produces({ "application/json" })
	public Response getAllProvinciaItaliaSearch(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
										  @Context HttpServletRequest httpRequest);

	@GET
	@Path("/{istatProv}")
	@Produces({ "application/json" })
	public Response getProvinciaByID(@PathParam("istatProv") String istatProv, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	public Response saveProvincia(GeoPLProvincia body,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);

	@GET
	@Path("/bo")
	@Produces({ "application/json" })
	public Response getProvinciaForBackOffice(@Context HttpServletRequest httpRequest);
}
