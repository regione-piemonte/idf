/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/private/categorie-selvicolturali")
@Produces({ "application/json" })
public interface CategoriaSelvicolturaleApi {
	
	@GET  
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll(@Context SecurityContext securityContext,
					@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	

}
