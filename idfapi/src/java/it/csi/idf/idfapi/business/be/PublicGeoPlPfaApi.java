/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import javax.ws.rs.core.UriInfo;

import it.csi.idf.idfapi.dto.ExcelDTO;

@Path("/public/pfa")
@Produces({ "application/json" })
public interface PublicGeoPlPfaApi {
	
	@GET
	@Path("/configurazione/agid")
	@Produces({ "application/json" })
	public Response getAgidUrlConfigation(@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/search")
	@Produces({ "application/json" })
	public Response search(@Context UriInfo info, @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/filter/descrizioni")
	@Produces({ "application/json" })
	public Response getDescrizioniPfa(@Context UriInfo info, @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/search/{idGeoPlPfa}")
	@Produces({ "application/json" })
	public Response getPfaSearchByID(@PathParam("idGeoPlPfa") Integer idGeoPlPfa,
			@QueryParam("idComune") Integer idComune, @QueryParam("idPopolamento") Integer idPopolamento,
			@Context HttpServletRequest httpRequest);
	
	@POST
	@Path("/searchExcel")
	@Produces("application/vnd.ms-excel")
	public Response generateExcel(ExcelDTO excel,@Context UriInfo info);	
	
	@GET
	@Path("/provincia/search")
	@Produces({ "application/json" })
	public Response getAllProvinciaSearch();
	
	@GET
	@Path("/documenti/{idGeoPlPfa}")
	@Produces({"application/json"})
	public Response getDocumentiByGeoPlPfa(@PathParam("idGeoPlPfa") int geoPlPfa);
	
	@GET
	@Path("/documenti/download/{idDocumento}")
	public Response downloadDocumento(@PathParam("idDocumento") int idDocumento,
			@Context HttpServletResponse httpResponse) throws IOException;
	
	@GET
	@Path("/comuni/provincia/{istatProv}")
	@Produces({"application/json"})
	public Response getComuniByProvincia(@PathParam("istatProv") String istatProv);

	
//	@GET
//	@Path("/sezioni/{munipality_code}")
//	@Produces({"application/json"})
//	public Response getSezioniByComune(@PathParam("municipality_code") String istatCom);
}




