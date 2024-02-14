/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.api;

import java.util.List;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.business.be.vincolo.pojo.VincoloStep1DTO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.VincoloStep2DTO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.VincoloStep3DTO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.VincoloStep4DTO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.VincoloStep5DTO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.VincoloStep6DTO;
import it.csi.idf.idfapi.dto.IstanzaTaglio;
import it.csi.idf.idfapi.dto.PlainParticelleCatastali;
import it.csi.idf.idfapi.dto.ProfessionistaElencoAllegati;

@Path("/private/vincolo")
@Produces({MediaType.APPLICATION_JSON})
public interface VincoloStepsApi {
	
	@GET
	@Produces({ "application/json" })
	public Response getPlainHome(@QueryParam("idTipoIstanza") Integer idTipoIstanza, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/cauzione")
	@Produces({ "application/json" })
	public Response getInfoCauzione(@QueryParam("idIntervento") Integer idIntervento, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	
	@POST
	@Path("/bo/{idIntervento}/asociare")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response associareDocumenti(@PathParam("idIntervento") Integer idIntervento,
			ProfessionistaElencoAllegati body, @Context HttpServletRequest httpRequest);

	@POST
	@Path("/{idIntervento}/inserisciParticella")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	Response checkAndInsertParticellaVincolo(@PathParam("idIntervento") Integer idIntervento, PlainParticelleCatastali particella, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@DELETE
	@Produces({MediaType.APPLICATION_JSON})
	@Path("{idIntervento}")
	public Response deleteIntervento(@PathParam("idIntervento") Integer idIntervento, @Context HttpServletRequest httpRequest);

	@GET
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<VincoloStep1DTO> findAll();

	@GET
	@Path("bo/{idIntervento}/ceo")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCeoIstanza(@PathParam("idIntervento") Integer idIntervento,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/{idIntervento}/invio")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDataInvioIstanza(@PathParam("idIntervento") Integer idIntervento,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/idfvincidro")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getIstanzeForConfigUtente(@QueryParam("tipoIstanzaId") int tipoIstanzaId,@QueryParam("page") int page, @QueryParam("limit") int limit,
			@QueryParam("sort") String sort, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/{idIntervento}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNumberOfNextStep(@PathParam("idIntervento") Integer idIntervento,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/{idIntervento}/prima")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStep1(@PathParam("idIntervento") Integer idIntervento, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest);

	@GET
	@Path("/{idIntervento}/seconda")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getStep2(@PathParam("idIntervento") Integer idIntervento,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/{idIntervento}/terza")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStep3(@PathParam("idIntervento") Integer idIntervento,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/{idIntervento}/quarta")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStep4(@PathParam("idIntervento") Integer idIntervento,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/{idIntervento}/quinta")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStep5(@PathParam("idIntervento") Integer idIntervento,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/{idIntervento}/sesta")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStep6(@PathParam("idIntervento") Integer idIntervento,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/menu")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStepMenu(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest);

	@GET
	@Path("/{idIntervento}/elencaDich")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTuttiElencaForIntervento(@PathParam("idIntervento") Integer idIntervento,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/{idIntervento}/elencoDocsGestore")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDocsGestoreByIntervento(@PathParam("idIntervento") Integer idIntervento,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/{idIntervento}/bo")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUtenteForIstanza(@PathParam("idIntervento") Integer idIntervento,
			@Context HttpServletRequest httpRequest);
	
	@POST
	@Path("/{idIntervento}/invio")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response invioIstanza(@PathParam("idIntervento") Integer idIntervento, ProfessionistaElencoAllegati body,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/recalculate/particelle/{idIntervento}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recalculateParticelleIntervento(@PathParam("idIntervento") Integer idIntervento, @Context HttpServletRequest httpRequest);

	@GET
	@Path("/secondo/elencoRicadenze")
    @Produces(MediaType.APPLICATION_JSON)
	public Response returnRicadenze(@QueryParam("idIntervento") Integer idIntervento, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);

	@POST
	@Path("/prima")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveStep1(VincoloStep1DTO body, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) throws ValidationException, RecordNotUniqueException;

	@POST
	@Path("/{idIntervento}/seconda")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveStep2(@PathParam("idIntervento") Integer idIntervento, VincoloStep2DTO body,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest)
			throws ValidationException, RecordNotUniqueException;

	@POST
	@Path("/{idIntervento}/terza")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveStep3(@PathParam("idIntervento") Integer idIntervento, VincoloStep3DTO body,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest)
					throws ValidationException, RecordNotUniqueException;
	
	@POST
	@Path("/{idIntervento}/quarta")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveStep4(@PathParam("idIntervento") Integer idIntervento, VincoloStep4DTO body,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest)
					throws ValidationException, RecordNotUniqueException;
	@POST
	@Path("/{idIntervento}/quinta")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveStep5(@PathParam("idIntervento") Integer idIntervento, VincoloStep5DTO body, 
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest)
			throws ValidationException, RecordNotUniqueException;
	
	@POST
	@Path("/{idIntervento}/sesta")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveStep6(@PathParam("idIntervento") Integer idIntervento, VincoloStep6DTO body, 
			@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest)
			throws ValidationException, RecordNotUniqueException;
	
	@GET
	@Path("/bo/{idIntervento}/rifiutata")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateIstanzaRifiutata(@PathParam("idIntervento") Integer idIntervento,
			@PathParam("motivazioneRifiuto")String motivazioneRifiuto, 
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/bo/{idIntervento}/verificata")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateIstanzaVerificata(@PathParam("idIntervento") Integer idIntervento,
			@Context HttpServletRequest httpRequest);

	@PUT
	@Path("/{idIntervento}/prima")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateStep1(@PathParam("idIntervento") Integer idIntervento, VincoloStep1DTO vincoloDto,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest)
			throws ValidationException, RecordNotUniqueException;
	
	@PUT
	@Path("/{idIntervento}/seconda")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateStep2(@PathParam("idIntervento") Integer idIntervento,
			VincoloStep2DTO vincoloDto, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) throws ValidationException, RecordNotUniqueException;

	@GET
	@Path("/{idIntervento}/superfici")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSuperfici(@PathParam("idIntervento") Integer idIntervento,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) throws ValidationException, RecordNotUniqueException;

	@GET
	@Path("/{idIntervento}/cauzione")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCauzione(@PathParam("idIntervento") Integer idIntervento,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) throws ValidationException, RecordNotUniqueException;

	@GET
	@Path("/parametri/{parametro}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getParametro(@PathParam("parametro") String parametro,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) throws RecordNotUniqueException;

	@GET
	@Path("/{idIntervento}/tipiallegato")
	@Produces(MediaType.APPLICATION_JSON)
	Response getTipiDocumentoByIdIntervento(@PathParam("idIntervento") Integer idIntervento,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) throws ValidationException;
	
	
	@GET
	@Path("/{idIntervento}/creaVarianteProroga")
	@Produces(MediaType.APPLICATION_JSON)
	Response creaVarianteProroga(@PathParam("idIntervento") Integer idIntervento, 
			@QueryParam("isVariante") Boolean isVariante, @QueryParam("isCompetenzaRegionale") Boolean isCompetenzaRegionale,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) throws ValidationException;
	
	
	@GET
	@Path("/{idIntervento}/getInfoVarianteProroga")
	@Produces(MediaType.APPLICATION_JSON)
	Response getInfoVarianteProroga(@PathParam("idIntervento") Integer idIntervento,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) throws ValidationException;
	
	@PUT
	@Path("/{idIntervento}/update/titolarita")
	@Produces(MediaType.APPLICATION_JSON)
	Response updateTitolaritaIntervento(@PathParam("idIntervento") Integer idIntervento,
			@QueryParam("idConfUtente") Integer idConfUtente,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) throws ValidationException;
	
	
	@GET
	@Path("/load/istanzeTaglio")
	@Produces({ "application/json" })
	Response getIstanzeTaglioByIdIntervento(@QueryParam("idIntervento") Integer idIntervento, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);

	@PUT
	@Path("/update/istanzaTaglio")
	@Produces(MediaType.APPLICATION_JSON)
	Response saveOrDeleteIstanzaTaglio(@QueryParam("idIntervento") Integer idIntervento, IstanzaTaglio istanzaTaglio,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) throws ValidationException;

}
