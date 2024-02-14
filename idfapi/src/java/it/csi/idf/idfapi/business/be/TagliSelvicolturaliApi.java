/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.dto.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/private/tagli-selvicolturali")
@Produces({ MediaType.APPLICATION_JSON })
public interface TagliSelvicolturaliApi {

	@GET
	@Path("/istanze/{idIntervento}")
	@Produces(MediaType.APPLICATION_JSON)
	Response getNumberOfNextStep(@PathParam("idIntervento") Integer idIntervento,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@DELETE
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("{idIntervento}")
	Response deleteIntervento(@PathParam("idIntervento") Integer idIntervento, @Context HttpServletRequest httpRequest);

	@GET
	@Path("/istanze")
	@Produces({ MediaType.APPLICATION_JSON })
	Response getIstanzeForConfigUtente(@QueryParam("tipoIstanzaId") int tipoIstanzaId, @QueryParam("page") int page,
			@QueryParam("limit") int limit, @QueryParam("sort") String sort, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@GET
	@Path("/tipi-richiedente")
	@Produces(MediaType.APPLICATION_JSON)
	Response getAllTipiRichiedente(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/tipi-richiedente/{idTIpo}")
	@Produces(MediaType.APPLICATION_JSON)
	Response getTipoRichiedenteById(@PathParam("idTIpo") Integer idTIpo, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@GET
	@Path("/{idIntervento}/step1")
	@Produces(MediaType.APPLICATION_JSON)
	Response getStep1(@PathParam("idIntervento") Integer idIntervento, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@POST
	@Path("/step1")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response saveStep1(TagliSelvicolturaliStep1 body, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest)
			throws ValidationException, RecordNotUniqueException;

	@PUT
	@Path("/{idIntervento}/step1")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response updateStep1(@PathParam("idIntervento") Integer idIntervento, TagliSelvicolturaliStep1 dto,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) throws ValidationException, RecordNotUniqueException;

	@GET
	@Path("/{idIntervento}/step2")
	@Produces(MediaType.APPLICATION_JSON)
	Response getStep2(@PathParam("idIntervento") Integer idIntervento, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@POST
	@Path("/step2")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response saveStep2(TagliSelvicolturaliStep2 body, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest)
			throws ValidationException, RecordNotUniqueException;

	@PUT
	@Path("/{idIntervento}/step2")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response updateStep2(@PathParam("idIntervento") Integer idIntervento, TagliSelvicolturaliStep2 dto,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) throws ValidationException, RecordNotUniqueException;

	@GET
	@Path("/{idIntervento}/step3")
	@Produces(MediaType.APPLICATION_JSON)
	Response getStep3(@PathParam("idIntervento") Integer idIntervento, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@GET
	@Path("/{idIntervento}/step3/elencoRicadenze")
	@Produces(MediaType.APPLICATION_JSON)
	Response getRicadenze(@PathParam("idIntervento") Integer idIntervento, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@POST
	@Path("/{idIntervento}/step3")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response saveStep3(@PathParam("idIntervento") Integer idIntervento, TagliSelvicolturaliStep3 body,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) throws ValidationException, RecordNotUniqueException;

	@PUT
	@Path("/{idIntervento}/step3")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response updateStep3(@PathParam("idIntervento") Integer idIntervento, TagliSelvicolturaliStep3 dto,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) throws ValidationException, RecordNotUniqueException;

	@POST
	@Path("/{idIntervento}/inserisciParticella")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	Response checkAndInsertParticellaTagli(@PathParam("idIntervento") Integer idIntervento,
			PlainParticelleCatastali particella, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@GET
	@Path("/{idIntervento}/recalculate_particelle")
	@Produces({ "application/json" })
	Response recalculateParticelleIntervento(@PathParam("idIntervento") Integer idIntervento,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/{idIntervento}/step4")
	@Produces(MediaType.APPLICATION_JSON)
	Response getStep4(@PathParam("idIntervento") Integer idIntervento, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@POST
	@Path("/{idIntervento}/step4")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response saveStep4(@PathParam("idIntervento") Integer idIntervento, TagliSelvicolturaliStep4 body,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) throws ValidationException, RecordNotUniqueException;

	@PUT
	@Path("/{idIntervento}/step4")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response updateStep4(@PathParam("idIntervento") Integer idIntervento, TagliSelvicolturaliStep4 dto,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) throws ValidationException, RecordNotUniqueException;

	@GET
	@Path("/{idIntervento}/step5")
	@Produces(MediaType.APPLICATION_JSON)
	Response getStep5(@PathParam("idIntervento") Integer idIntervento, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@POST
	@Path("/{idIntervento}/step5")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response saveStep5(@PathParam("idIntervento") Integer idIntervento, TagliSelvicolturaliStep5 body,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) throws ValidationException, RecordNotUniqueException;

	@PUT
	@Path("/{idIntervento}/step5")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response updateStep5(@PathParam("idIntervento") Integer idIntervento, TagliSelvicolturaliStep5 dto,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) throws ValidationException, RecordNotUniqueException;

	@GET
	@Path("/{idIntervento}/elencaDich")
	@Produces(MediaType.APPLICATION_JSON)
	Response getTuttiElencaForIntervento(@PathParam("idIntervento") Integer idIntervento,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("/{idIntervento}/invio")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response invioIstanza(@PathParam("idIntervento") Integer idIntervento, ProfessionistaElencoAllegati body,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("/{idIntervento}/invioEmail")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response invioEmail(@PathParam("idIntervento") Integer idIntervento, ProfessionistaElencoAllegati body,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/{idIntervento}/invio")
	@Produces(MediaType.APPLICATION_JSON)
	Response getDataInvioIstanza(@PathParam("idIntervento") Integer idIntervento,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("bo/{idIntervento}/ceo")
	@Produces(MediaType.APPLICATION_JSON)
	Response getCeoIstanza(@PathParam("idIntervento") Integer idIntervento, @Context HttpServletRequest httpRequest);

	@GET
	@Path("/{idIntervento}/elencoDocsGestore")
	@Produces(MediaType.APPLICATION_JSON)
	Response getDocsGestoreByIntervento(@PathParam("idIntervento") Integer idIntervento,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/{idIntervento}/drel/info")
	@Produces(MediaType.APPLICATION_JSON)
	Response getInfoDrel(@PathParam("idIntervento") Integer idIntervento, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@GET
	@Path("/{idIntervento}/drel")
	@Produces(MediaType.APPLICATION_JSON)
	Response getDrel(@PathParam("idIntervento") Integer idIntervento, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) throws RecordNotUniqueException;

	@GET
	@Path("/{idIntervento}/creaVarianteProroga")
	@Produces(MediaType.APPLICATION_JSON)
	Response creaVarianteProroga(@PathParam("idIntervento") Integer idIntervento,
			@QueryParam("isVariante") Boolean isVariante, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) throws ValidationException;

	@GET
	@Path("/{idIntervento}/getInfoVarianteProroga")
	@Produces(MediaType.APPLICATION_JSON)
	Response getInfoVarianteProroga(@PathParam("idIntervento") Integer idIntervento,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) throws ValidationException;

	@PUT
	@Path("/{idIntervento}/cambioUtlizzatore")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response cambioUtlizzatore(@PathParam("idIntervento") Integer idIntervento, TagliSelvicolturaliStep2 dto,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) throws ValidationException, RecordNotUniqueException;

	@PUT
	@Path("/{idIntervento}/update/titolarita")
	@Produces(MediaType.APPLICATION_JSON)
	Response updateTitolaritaIntervento(@PathParam("idIntervento") Integer idIntervento,
			@QueryParam("idConfUtente") Integer idConfUtente, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) throws ValidationException;

	@PUT
	@Path("/{idIntervento}/cambioTipologia")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Response updateTipologiaIstanza(@PathParam("idIntervento") Integer idIntervento, TagliSelvicolturaliStep5 dto,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) throws ValidationException, RecordNotUniqueException;

}
