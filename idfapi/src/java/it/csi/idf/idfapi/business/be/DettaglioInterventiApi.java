/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/private/dettaglioInterventi")
@Produces({ "application/json" })
public interface DettaglioInterventiApi {

	@Path("/{idIntervento}/tipoInt")
	@Produces({ "application/json" })
	@GET 
	public Response getTipoInterventoDetails(@PathParam ("idIntervento") Integer idIntervento, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);
	
	@Path("/{idIntervento}/particelle")
	@Produces({ "application/json" })
	@GET 
	public Response getParticelleDetails(@PathParam ("idIntervento") Integer idIntervento, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);
	
	@Path("/{idIntervento}/species")
	@Produces({ "application/json" })
	@GET 
	public Response getSpeciesDetails(@PathParam ("idIntervento") Integer idIntervento, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);
	
	@Path("/{idIntervento}/utilizzatore")
	@Produces({ "application/json" })
	@GET 
	public Response getUtilizzatoreDetails(@PathParam ("idIntervento") Integer idIntervento, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);

}
