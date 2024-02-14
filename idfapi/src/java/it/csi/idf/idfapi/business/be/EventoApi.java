/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.idf.idfapi.dto.EventoDTO;
import it.csi.idf.idfapi.dto.ExcelDTO;
import it.csi.idf.idfapi.dto.PlainPrimaPfaEvento;
import it.csi.idf.idfapi.dto.PlainSecondoPfaEvento;


@Path("/private/eventi")
@Produces({"application/json"})
public interface EventoApi {
	
	@GET
	@Produces({"application/json"})
	public Response getAllEventi(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
            @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/{idEvento}")
	@Produces({ "application/json" })
	public Response getEventoByID(@PathParam("idEvento") Integer idEvento, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
            @Context HttpServletRequest httpRequest);

	@POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	public Response saveEvento(EventoDTO body, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);
    
	@POST
	@Path("/eventoExcel")
	@Produces("application/vnd.ms-excel")
	public Response generateExcel(ExcelDTO excel,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/pfa/{idGeoPlPfa}")
	@Produces({ "application/json" })
	public Response getEventiByPlPfa(@PathParam("idGeoPlPfa") int idGeoPlPfa, @QueryParam("page") int page,
			@QueryParam("limit") int limit, @QueryParam("sort") String sort, @Context HttpServletRequest httpRequest);
	
	@POST
	@Path("/pfa/{idGeoPlPfa}/prima")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response savePrimaPfaEvento(PlainPrimaPfaEvento body, @PathParam("idGeoPlPfa") int idGeoPlPfa, @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/{idEvento}/pfa/{idGeoPlPfa}/secondo")
	@Produces({ "application/json" })
	public Response getSecondoPfaEvento(@PathParam("idEvento") int idEvento, @PathParam("idGeoPlPfa") int idGeoPlPfa, PlainSecondoPfaEvento body, @Context HttpServletRequest httpRequest);
	
	@POST
	@Path("/{idEvento}/pfa/{idGeoPlPfa}/secondo")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response saveSecondoPfaEvento(@PathParam("idEvento") int idEvento, @PathParam("idGeoPlPfa") int idGeoPlPfa, PlainSecondoPfaEvento body, @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/pfa/{idGeoPlPfa}/progressiviNomi")
	@Produces({ "application/json" })
	public Response getProgressiviNomiBreve(@PathParam("idGeoPlPfa") int idGeoPlPfa, @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/pfaId/{idEvento}")
	@Produces({ "application/json" })
	public Response getIdPfaByIdEvento(@PathParam("idEvento") Integer idEvento,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/{idEvento}/geometries/info")
	@Produces({ "application/json" })
	public Response getDrawedGeometryList(@PathParam("idEvento")Integer idEvento);
	
	@PUT
	@Path("/{idEvento}/ricalcolaSuperficiFromGeeco")
	@Produces({ "application/json" })
	public Response ricalcolaSuperficiFromGeeco(@PathParam("idEvento")Integer idEvento);
	
	
}
