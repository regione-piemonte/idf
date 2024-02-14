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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/private/tipoInterventi")

@Produces({"application/json"})
public interface TipoInterventoApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTipoIntervento(@Context SecurityContext securityContext,
                                         @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

    @GET
    @Path("/{fkConfigIpla}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTipoInterventoByFkConfigIpla(@PathParam("fkConfigIpla") Integer fkConfigIpla,
                                                       @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

    @GET
    @Path("/{fkConfigIpla}/gov")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTipoInterventoByGovernoAndFkConfigIpla(@PathParam("fkConfigIpla") Integer fkConfigIpla,
                                                                 @QueryParam("idGoverno") Integer idGoverno, @Context SecurityContext securityContext,
                                                                 @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
    @GET
    @Path("/tipoInterventoConformeDeroga/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTipoInterventoConformeDeroga(@Context SecurityContext securityContext,
                                         @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
    
    @GET
    @Path("/{fkConfigIpla}/{idGoverno}/{idMacroIntervento}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getAllTipoInterventoByGovernoAndFkConfigIpla(@PathParam("fkConfigIpla") Integer fkConfigIpla,
                                                          @PathParam("idGoverno") Integer idGoverno,
                                                          @PathParam("idMacroIntervento") Integer idMacroIntervento,
                                                          @Context SecurityContext securityContext,
                                                          @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

    @GET
    @Path("/{fkConfigIpla}/macro")
    @Produces(MediaType.APPLICATION_JSON)
    Response getAllTipoInterventoByFkConfigIplaAndCategoriaIntervento(@PathParam("fkConfigIpla") Integer fkConfigIpla,
                                                                      @QueryParam("idCategoriaIntervento") Integer idCategoriaIntervento,
                                                                      @Context SecurityContext securityContext,
                                                                      @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
}
