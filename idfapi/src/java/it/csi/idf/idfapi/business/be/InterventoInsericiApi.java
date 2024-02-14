/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.dto.InterventoDatiTehnici;
import it.csi.idf.idfapi.dto.InterventoUtilizzatore;
import it.csi.idf.idfapi.dto.PfaLottoLocalizza;

@Path("/private/intervento")
@Produces({ "application/json" })
public interface InterventoInsericiApi {

	@GET
	@Path("/{idIntervento}/datiTehnici")
	@Produces({ "application/json" })
	public Response getDatiTehniciForStepTwo(@PathParam("idIntervento") Integer idIntervento,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/{idIntervento}/datiTehniciNEW")
	@Produces({ "application/json" })
	public Response getDatiTehniciForEditNEW(@PathParam("idIntervento") Integer idIntervento,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/{idIntervento}/localizzaLottoNEW")
	@Produces({ "application/json" })
	public Response getLocalizzaLottoForEditNEW(@PathParam("idIntervento") Integer idIntervento,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/{idIntervento}/localizzaLottoFromGeeco")
	@Produces({ "application/json" })
	public Response getLocalizzaLottoFromGeeco(@PathParam("idIntervento") Integer idIntervento,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@Path("/{idIntervento}/datiTehnici")
	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response saveDatiTehnici(@PathParam("idIntervento") Integer idIntervento,
			InterventoDatiTehnici intervDatiTehnici, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@Path("/{idGeoPlPfa}/interventoNew")
	@GET	
	@Produces({ "application/json" })
	public Response createInterventoNEW(@PathParam("idGeoPlPfa") Integer idGeoPlPfa, @Context SecurityContext securityContext, 
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@Path("/{idGeoPlPfa}/datiTehniciNEW")
	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response saveDatiTehniciNEW(@PathParam("idGeoPlPfa") Integer idGeoPlPfa,
			InterventoDatiTehnici intervDatiTehnici, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@Path("/{idIntervento}/localizzaLottoNEW")
	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response saveLocalizzaLottoNEW(@PathParam("idIntervento") Integer idIntervento,
			@QueryParam("idGeoPlPfa") Integer idGeoPlPfa, PfaLottoLocalizza body,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@PUT
	@Path("/{idIntervento}/updateLocalizzaLottoNEW")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	Response updateLocalizzaLotto(@PathParam("idIntervento") Integer idIntervento, @QueryParam("idGeoPlPfa") Integer idGeoPlPfa, PfaLottoLocalizza body,@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	

	@Path("/{idIntervento}")
	@DELETE
	@Produces({ "application/json" })
	public Response deleteIntervento(@PathParam("idIntervento") Integer idIntervento,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("{idIntervento}/shootingMirror")
	@Produces({ "application/json" })
	public Response getDataForShootingMirror(@PathParam("idIntervento") Integer idIntervento,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@PUT
	@Path("/{idIntervento}/datiTehniciNEW")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response updateDatiTechiciNEW(@PathParam("idIntervento") Integer idIntervento,
			InterventoDatiTehnici intervDatiTehnici, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@PUT
	@Path("/{idIntervento}/datiTehnici")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response updateDatiTechiciAtStep2(@PathParam("idIntervento") Integer idIntervento,
			InterventoDatiTehnici intervDatiTehnici, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@Path("/{idIntervento}/saveSecondStep")
	@GET
	@Produces({ "application/json" })
	public Response saveSecondStepOfInserisciNuovoIntervento(@PathParam("idIntervento") Integer idIntervento,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@Path("/utilizzatore")
	@GET
	@Produces({ "application/json" })
	public Response getUtilizzatoreDataFromBaseOrAAEP(@QueryParam("codiceFiscale") String codiceFiscale,
			@QueryParam("ricercaEnum") Integer persona, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) throws RecordNotUniqueException;

	@Path("/{idIntervento}/utilizzatore")
	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response saveOrUpdateUtilizzatoreETecnicoStep(@PathParam("idIntervento") Integer idIntervento,
			InterventoUtilizzatore interventoUtilizzatore, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@Path("/{idIntervento}/allegati")
	@GET
	@Produces({ "application/json" })
	public Response saveAllegatiStepOfInserisciNuovoIntervento(@PathParam("idIntervento") Integer idIntervento,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/{idIntervento}/utilizzatoreETecnico")
	@Produces({ "application/json" })
	Response getUtilizzatoreETecnicoForEditNEW(@PathParam("idIntervento") Integer idIntervento,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest);

	

	@Path("/{idIntervento}/geeco")
	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response sendParticelsToGeeco(@PathParam("idIntervento") Integer idIntervento, PfaLottoLocalizza body,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/{idIntervento}/geeco")
	@Produces({ "application/json" })
	Response retreiveDataFromGeeco(@PathParam("idIntervento") Integer idIntervento,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest);
	
	@GET
	@Path("/{idIntervento}/lottoData")
	@Produces({ "application/json" })
	public Response getDataLotto(@PathParam("idIntervento")Integer idIntervento)
			throws RecordNotUniqueException, ValidationException;
	
	@GET
	@Path("/{idIntervento}/errori/pfa")
	@Produces({ "application/json" })
	public Response getErroriInterventoPfa(@PathParam("idIntervento")Integer idIntervento)
			throws RecordNotUniqueException, ValidationException;
	
	@GET
	@Path("/{idIntervento}/geometries/info")
	@Produces({ "application/json" })
	public Response getDrawedGeometryPfaList(@PathParam("idIntervento")Integer idIntervento);
	
	
}
