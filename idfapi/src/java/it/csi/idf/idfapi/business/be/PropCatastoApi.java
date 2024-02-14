/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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



@Path("/private/catasti")
@Produces({"application/json"})
public interface PropCatastoApi {
	
	@GET
	@Produces({"application/json"})
	public Response getCatasti(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);

	@GET
	@Path("/{idCatasto}")
	@Produces({"application/json"})
	public Response getCatastiByID(@PathParam("idCatasto") Integer idGeoPlPropCatasto, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	
	@GET
	@Path("/sezione")
	@Produces({"application/json"})
	public Response getSezione(@QueryParam("comune") Integer comune, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/foglio")
	@Produces({"application/json"})
	public Response getFoglio(@QueryParam("comune") Integer comune,@QueryParam("sezione") String sezione, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/particella")
	@Produces({"application/json"})
	public Response getParticella(@QueryParam("comune") Integer comune,@QueryParam("sezione") String sezione,@QueryParam("foglio") Integer foglio, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);
	
	
	@GET
	@Path("/tipoIstanza/sezione")
	@Produces({"application/json"})
	public Response getSezioneByTipoIstanza(@QueryParam("idTipoIstanza") Integer idTipoIstanza, @QueryParam("comune") Integer comune, 
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/tipoIstanza/foglio")
	@Produces({"application/json"})
	public Response getFoglioByTipoIstanza(@QueryParam("idTipoIstanza") Integer idTipoIstanza, @QueryParam("comune") Integer comune,
			@QueryParam("sezione") String sezione, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders ,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/tipoIstanza/particella")
	@Produces({"application/json"})
	public Response getParticellaByTipoIstanza(@QueryParam("idTipoIstanza") Integer idTipoIstanza, @QueryParam("comune") Integer comune,
			@QueryParam("sezione") String sezione,@QueryParam("foglio") Integer foglio, @Context SecurityContext securityContext, 
			@Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);
	
	
	@GET
	@Path("/plainParticella")
	@Produces({"application/json"})
	public Response getPlainParticella(@QueryParam("comune") Integer comune,@QueryParam("sezione") String sezione,@QueryParam("foglio") Integer foglio,@QueryParam("particella") String particella, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);
	
	
	@GET
	@Path("/{idGeoPlPfa}/{comune}")
	@Produces({"application/json"})
	public Response getCatastiByPfaAndComune(@PathParam("idGeoPlPfa") Integer idGeoPlPfa, @PathParam("comune") Integer comune, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@POST
	@Path("/geeco/{idIntervento}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	public Response retreiveSupIntervento(@PathParam("idIntervento") Integer idIntervento, List<PlainParticelleCatastali> particelle,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);
	
	
	@DELETE
	@Path("/{idIntervento}")
	@Produces({ "application/json" })
	public Response deleteIntervento(@PathParam("idIntervento") Integer idIntervento, @QueryParam("idGeoPlPropCatasto") Integer idGeoPlPropCatasto, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@DELETE
	@Path("/vincolo/{idIntervento}")
	@Produces({ "application/json" })
	public Response deleteInterventoVincolo(@PathParam("idIntervento") Integer idIntervento, @QueryParam("idGeoPlPropCatasto") Integer idGeoPlPropCatasto, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest);
	
	@DELETE
	@Path("/pfa/{idIntervento}")
	@Produces({ "application/json" })
	public Response deleteInterventoPFA(@PathParam("idIntervento") Integer idIntervento, @QueryParam("idGeoPlPropCatasto") Integer idGeoPlPropCatasto, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/pfa/elencoRicadenze")
    @Produces({ "application/json" })
	Response returnRicadenzePfa(@QueryParam("idIntervento") Integer idIntervento, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);


	
	
	
}
