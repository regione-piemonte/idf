/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/private/sigmater")
@Produces({ "application/json" })
public interface SigmaterApi {
	@GET
	@Path("/particella")
	@Produces({ "application/json" })
	Response getParticellaCatastale(@QueryParam("istatComune") String istatComune, @QueryParam("sezione") String sezione,
			@QueryParam("foglio") Integer foglio, @QueryParam("particella") String particella,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
}
