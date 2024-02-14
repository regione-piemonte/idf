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
import javax.ws.rs.core.UriInfo;

import it.csi.idf.idfapi.business.be.exceptions.DuplicateRecordException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.dto.ExcelDTO;
import it.csi.idf.idfapi.dto.GeoPlPfa;

@Path("/private/geoPlPfi")
@Produces({ "application/json" })
public interface GeoPlPfaApi {

	@GET
	@Path("/search")
	@Produces({ "application/json" })
	public Response search(@Context UriInfo info, @Context HttpServletRequest httpRequest);

	@GET
	@Path("/search/{idGeoPlPfa}")
	@Produces({ "application/json" })
	public Response getPfaSearchByID(@PathParam("idGeoPlPfa") Integer idGeoPlPfa,
			@Context HttpServletRequest httpRequest);

	@GET
	@Produces({ "application/json" })
	public Response getAllGeoPlPfa();

	@GET
	@Path("/{idGeoPlPfa}")
	@Produces({ "application/json" })
	public Response getGeoPlPfaByID(@PathParam("idGeoPlPfa") Integer idGeoPlPfa,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) throws RecordNotFoundException;

	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response saveGeoPlPfa(GeoPlPfa body) throws DuplicateRecordException;

	@POST
	@Path("/searchExcel")
	@Produces("application/vnd.ms-excel")
	public Response generateExcel(ExcelDTO excel, @Context UriInfo info, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	
	@GET
	@Path("/home")
	@Produces({ "application/json" })
	public Response IsUserForPFA(@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/isPfaEntePubblico/{idGeoPlPfa}")
	@Produces({ "application/json" })
	public Response isPfaEntePubblico(@PathParam("idGeoPlPfa") Integer idGeoPlPfa,@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
}
