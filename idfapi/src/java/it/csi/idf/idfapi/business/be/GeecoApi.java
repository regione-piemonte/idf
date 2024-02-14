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

@Path("/private/geeco")
@Produces({"application/json"})
public interface GeecoApi {
	
	@POST
	@Path("/{idProfiloGeeco}/id")    
	@Produces({ "application/json" })
	public Response postGeecoConfiguration(@PathParam("idProfiloGeeco") String idProfiloGeeco, String idIdfObject, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/{idProfiloGeeco}/id")    
	@Produces({ "application/json" })
	public Response getGeecoConfiguration(@PathParam("idProfiloGeeco") String idProfiloGeeco, @QueryParam("idIdfObject") String idIdfObject, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@POST
	@Path("/{idProfiloGeeco}/list")    
	@Produces({ "application/json" })
	public Response postGeecoConfiguration(@PathParam("idProfiloGeeco") String idProfiloGeeco, String[] idIdfObjects, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/{idProfiloGeeco}/list")    
	@Produces({ "application/json" })
	public Response getGeecoConfiguration(@PathParam("idProfiloGeeco") String idProfiloGeeco, @QueryParam("idIdfObjects") String[] idIdfObjects, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@POST
	@Path("/{idProfiloGeeco}")    
	@Produces({ "application/json" })
	public Response postGeecoConfiguration(@PathParam("idProfiloGeeco") String idProfiloGeeco, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/{idProfiloGeeco}")    
	@Produces({ "application/json" })
	public Response getGeecoConfiguration(@PathParam("idProfiloGeeco") String idProfiloGeeco, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

}
