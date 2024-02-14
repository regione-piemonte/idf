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

import it.csi.idf.idfapi.dto.PlainRelascopicaSemplice;

@Path("/private/relascopica")
@Produces({ "application/json" })
public interface RelascopicaDetailsApi {

	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response insertRelascopicaSemplice(PlainRelascopicaSemplice plainRelascopicaSemplice, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	
	@GET
	@Path(value = "/{codiceAds}")	
	@Produces({ "application/json" })
	public Response getRelascopicaByCodiceAds( @PathParam(value = "codiceAds") String codiceAds, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@Path("/consolida")
	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response consolidaRelascopicaSemplice(PlainRelascopicaSemplice plainRelascopicaSemplice, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
}
