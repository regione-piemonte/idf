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

@Path("/private/tipoViabilita")
@Produces({ "application/json" })
public interface TipoViabilitaApi {

	@GET  
	@Produces({ "application/json" })
    Response getAllTipoViabilita(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);

	@GET
	@Path("/ipla/{fkConfigIpla}")
	@Produces({ "application/json" })
    Response getAllTipoViabilitaByIpla(@PathParam("fkConfigIpla") Integer fkConfigIpla,
									   @Context SecurityContext securityContext,
									   @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);
}
