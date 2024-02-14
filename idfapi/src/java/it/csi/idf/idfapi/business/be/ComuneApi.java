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

import it.csi.idf.idfapi.dto.Comune;

@Path("/private/comuni")
@Produces({"application/json"})
public interface ComuneApi {
	
	@GET
	@Produces({"application/json"})
	public Response getAllComune();

	@GET
	@Path("/find")
	@Produces({"application/json"})
	public Response findComuneByNome(@QueryParam("comune") String comune);
	
	@GET
	@Path("/{idComune}")
	@Produces({"application/json"})
	public Response getComuneByID(@PathParam("idComune") Integer idComune, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,  @Context HttpServletRequest httpRequest);

	@GET
	@Path("/provincia/{istatProv}")
	@Produces({"application/json"})
	public Response getComuniByProvincia(@PathParam("istatProv") String istatProv, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,  @Context HttpServletRequest httpRequest);
	
	@POST
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response saveComune(Comune body);
	
	@GET
	@Path("/bo/provincia/{istatProv}")
	@Produces({"application/json"})
	public Response getComuniByProvinciaForBackOfficeSearch(@PathParam("istatProv") String istatProv,  @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/pfa/{idGeoPlPfa}")
	@Produces({"application/json"})
	public Response getComuniByPfa(@PathParam("idGeoPlPfa") Integer idGeoPlPfa,  @Context HttpServletRequest httpRequest);



}
