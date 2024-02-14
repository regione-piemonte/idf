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
import javax.ws.rs.core.*;

@Path("/private/taif")
@Produces({MediaType.APPLICATION_JSON})
public interface TAIFApi {


    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    Response getAll(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
                    @Context HttpServletRequest httpRequest);


    @GET
    @Path("/cf/{codiceFiscale}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getByCodiceFiscale(@PathParam("codiceFiscale") String codiceFiscale,
                                @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
                                 @Context HttpServletRequest httpRequest);
}
