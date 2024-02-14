/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import org.springframework.http.ResponseEntity;

import it.csi.idf.idfapi.dto.GeoPLProvincia;

@Path("/private/foglio")
@Produces({ "application/json" })
public interface FoglioApi {

	@GET
	@Path("/{municipality_code}/{section_code}")
	@Produces({"application/json"})
	public Response getFoglioBySezione(@PathParam("municipality_code") String municipality_code, 
			@PathParam("section_code") String section_code,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest,HttpServletResponse response);

}
