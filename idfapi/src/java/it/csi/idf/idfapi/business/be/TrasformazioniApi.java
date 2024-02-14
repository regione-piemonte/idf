/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/private/trasformazioni")

@Produces({"application/json"})
public interface TrasformazioniApi {

    @GET
    @Path("/search-base")
    @Produces(MediaType.APPLICATION_JSON)
    Response searchByOneParam(@QueryParam("search") String search,
                              @Context SecurityContext securityContext,
                              @Context HttpHeaders httpHeaders,
                              @Context HttpServletRequest httpRequest);



}
