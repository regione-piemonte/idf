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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.idf.idfapi.dto.AmbitoRilievo;

@Path("/private/ambitoRilievo")

@Produces({ "application/json" })
public interface AmbitoRilievoApi {
	@GET  
	@Produces({ "application/json" })
	 public Response getAllAmbitoRilievo(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);
	
	@Path("/specificare")
	@GET  
	@Produces({ "application/json" })
	 public Response getAmbitoRilievoSpecificare(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);
	
	
	@Path("")
	@POST
	@Produces("application/json")

	public Response saveAmbitoRilievo(AmbitoRilievo ambito, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@Path("/{idAmbito}/dettaglio")
	@GET  
	@Produces({ "application/json" })
	
	public Response findChildAmbitos(@PathParam("idAmbito") Integer idAmbito, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);
	
}
