/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.idf.idfapi.dto.RAdsrelSpecie;

@Path("/private/adsrelSpecie")

@Produces({ "application/json" })
public interface AdsrelSpecieApi {
	@GET  
	@Produces({ "application/json" })
	 public Response getAllSpecie(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);
	
	@GET  
	@Path("/alberiCAMeDOM/{idgeoPtAds}")
	@Produces({ "application/json" })

	 public Response getAllSpecieByIdgeoPtAds(@PathParam("idgeoPtAds") Long idgeoPtAds,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);
	
	@GET  
	@Path("/alberiCAV/{codiceAds}")
	@Produces({ "application/json" })
	 public Response getAllSpecieCAVByCodiceADS(@PathParam("codiceAds") String codiceAds,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);
	
	
	@GET  
	@Path("/allStepErrors/{idgeoPtAds}")
	@Produces({ "application/json" })
	public Response getAllStepErrors(@PathParam("idgeoPtAds") Long idgeoPtAds, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@Path("/alberiCAMeDOM")
	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response insertAlberiCAMDOM(List<RAdsrelSpecie> specie, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@Path("/alberiCAMeDOM")
	@PUT
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response updateAlberiCAMeDOM(List<RAdsrelSpecie> specie, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@Path("/alberiCav")
	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response saveAlberiCAV(List<RAdsrelSpecie> alberiCAVList, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@Path("/alberiCav/consolida")
	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response saveAlberiCAVConsolida(List<RAdsrelSpecie> alberiCAVList, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
}
