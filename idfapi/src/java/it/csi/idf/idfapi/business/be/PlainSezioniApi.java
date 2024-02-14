/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.idf.idfapi.dto.PlainParticelleCatastali;

@Path("/private/sezioni")
@Produces({ "application/json" })
public interface PlainSezioniApi {

	@GET
	@Path("/secondo/elencoRicadenze")
    @Produces({ "application/json" })
	Response returnRicadenze(@QueryParam("idIntervento") Integer idIntervento, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);
	
	
	@GET
	@Path("/secondo/particella")
	@Produces({ "application/json" })
	Response getParticellaCatastale(@QueryParam("istatComune") String istatComune, @QueryParam("sezione") String sezione,
			@QueryParam("foglio") Integer foglio, @QueryParam("particella") String particella,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/prima/legale")
	@Produces({ "application/json" })
	Response getSocietaLegale(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/sesto/istanzaTaglio")
	@Produces({ "application/json" })
	Response getIstanzaTaglio(@QueryParam("numIstanza") Integer numIstanza, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);

	@POST
	@Path("/{idIntervento}/inserisciParticelle")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	Response insertParticellaPFA(@PathParam("idIntervento") Integer idIntervento,PlainParticelleCatastali particella, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);
	
	@POST
	@Path("/{idIntervento}/inserisciParticella")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	Response checkAndInsertParticellaISTAFOR(@PathParam("idIntervento") Integer idIntervento,PlainParticelleCatastali particella, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);

}
