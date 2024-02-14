/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.idf.idfapi.dto.PlainRelascopicaSemplice;

@Path("/private/relascopicaCompleta")
@Produces({ "application/json" })
public interface RelascopicaCompletaApi {
	@POST
	@Path("/dettaglio/{codiceAds}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response insertRelascopicaCompleta(@PathParam("codiceAds") String codiceAds,PlainRelascopicaSemplice plainRelascopicaSemplice, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
//	
//	@POST
//	@Path("/alberiCavallettati/{codiceAds}")
//	@Consumes({ "application/json" })
//	@Produces({ "application/json" })
//	public Response insertRelascopicaCompletaAlberi(@PathParam("codiceAds") String codiceAds, PlainAdsrelSpecie plainAdsrelSpecie, @Context SecurityContext securityContext,
//			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

}
