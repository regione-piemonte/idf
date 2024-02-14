/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("/private/test")
@Produces({ "application/json" })
public interface TestApi {
	
	@GET
	@Path("/success")
	@Produces({ "application/json" })
	Response getSuccess(@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/error/validation")
	@Produces({ "application/json" })
	Response getValidationError();
	
	@GET
	@Path("/tryresources")
	@Produces({ "application/json" })
	Response tryResources();
}
