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

@Path("/private/tipoForestale")

@Produces({ "application/json" })
public interface TipoForestaleApi {
	
	@GET  
	@Produces({ "application/json" })
	 public Response getAllTipoForestale(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);
	
	@GET 
	@Path("/pfa")
	@Produces({ "application/json" })
	 public Response getAllTipoForestalePfa(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);
	
	@GET 
	@Path("/inventari")
	@Produces({ "application/json" })
	 public Response getAllTipoForestaleInventari(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);
	
	
	@GET
	@Path("/categoria/{categoriaForestale}")
	@Produces({"application/json"})
	public Response getTipoByCategoria(@PathParam("categoriaForestale") Integer categoriaForestale, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,  @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/pfa/categoria/{categoriaForestale}")
	@Produces({"application/json"})
	public Response getTipoByCategoriaPfa(@PathParam("categoriaForestale") Integer categoriaForestale, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,  @Context HttpServletRequest httpRequest);
	
	
	@GET
	@Path("/inventari/categoria/{categoriaForestale}")
	@Produces({"application/json"})
	public Response getTipoByCategoriaInventari(@PathParam("categoriaForestale") Integer categoriaForestale, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,  @Context HttpServletRequest httpRequest);
	
}
