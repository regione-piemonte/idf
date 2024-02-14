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

@Path("/private/tipiIstanza")
@Produces({ "application/json" })
public interface TipoIstanzaApi {
	
	@GET
	@Path("/trasf")
	@Produces({ "application/json" })
	public Response getTipoTrasformazione();
	
	@GET
	@Path("/attive")
	@Produces({ "application/json" })
	public Response getTipiIstanzaAttivi(@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@GET
	@Path("/attive/{idAmbito}")
	@Produces({ "application/json" })
	public Response getTipiIstanzaAttiveAmbito(
			@PathParam("idAmbito") Integer idAmbito,
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@GET
	@Produces({ "application/json" })
	public Response getAllTipoInstanza();
	
}
