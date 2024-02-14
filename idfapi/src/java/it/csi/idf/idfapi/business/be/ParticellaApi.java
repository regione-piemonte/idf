/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import org.springframework.http.ResponseEntity;

import it.csi.idf.idfapi.dto.GeoPLProvincia;

@Path("/private/particella")
@Produces({ "application/json" })
public interface ParticellaApi {

	@GET
	@Path("/{municipality_code}/{section_code}/{sheet_number}")
	@Produces({"application/json"})
	public Response getParticellaByFoglio(@PathParam("municipality_code") String municipality_code, 
			@PathParam("section_code") String section_code,
			@PathParam("sheet_number") String sheet_number,			
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest,HttpServletResponse response);

	@GET
	@Path("/{municipality_code}/{section_code}/{sheet_number}/{parcel_number}")
	@Produces({"application/json"})
	public Response getParticellaById(@PathParam("municipality_code") String municipality_code, 
			@PathParam("section_code") String section_code,
			@PathParam("sheet_number") String sheet_number,
			@PathParam("parcel_number") String parcel_number,	
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest,HttpServletResponse response);
	

}
