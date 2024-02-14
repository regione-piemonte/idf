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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import it.csi.idf.idfapi.business.be.vincolo.pojo.AutorizzaVincolo;
import it.csi.idf.idfapi.dto.*;

@Path("/private/istanze")
@Produces({ "application/json" })
public interface IstanzaForestAPI {
	
	@GET
	@Path("/{idIntervento}")
	@Produces({ "application/json" })
	public Response getNumberOfNextStep(@PathParam("idIntervento") Integer idIntervento, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Produces({ "application/json" })
	public Response getIstanzeForConfigUtente(@QueryParam("page") int page, @QueryParam("limit") int limit,
			@QueryParam("sort") String sort, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/{idIntervento}/prima")
	@Produces({ "application/json" })
	public Response getPrimaSezione(@PathParam("idIntervento") Integer idIntervento,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("/prima")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response savePrimaSezione(PlainPrimaSezione body, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@PUT
	@Path("/{idIntervento}/prima")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response updatePrimaSezione(@PathParam("idIntervento") Integer idIntervento, PlainPrimaSezione body,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/{idIntervento}/secondo")
	@Produces({ "application/json" })
	public Response getSecondoSezione(@PathParam("idIntervento") Integer idIntervento,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@POST
	@Path("/{idIntervento}/secondo")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	Response saveSecondoSezione(@PathParam("idIntervento") Integer idIntervento, PlainSecondoSezione body, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);

	@PUT
	@Path("/{idIntervento}/secondo")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	Response updateSecondoSezione(@PathParam("idIntervento") Integer idIntervento, PlainSecondoSezione body, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);

	@GET
	@Path("/{idIntervento}/terza")
	@Produces({ "application/json" })
	public Response getTerzaSezione(@PathParam("idIntervento") Integer idIntervento,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("/{idIntervento}/terza")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response saveTerzaSezione(@PathParam("idIntervento") Integer idIntervento, PlainTerzaSezione body,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/{idIntervento}/quarta")
	public Response getQuartaSezione(@PathParam("idIntervento") Integer idIntervento,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@PUT
	@Path("/{idIntervento}/quarta")
	@Produces({ "application/json" })
	public Response saveQuartaSezione(@PathParam("idIntervento") Integer idIntervento,
			CompensationForesteDTO compensationForesteDTO, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/{idIntervento}/quinta")
	@Produces({ "application/json" })
	public Response getQuintaSezione(@PathParam("idIntervento") Integer idIntervento,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Path("/{idIntervento}/quinta")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response saveQuintaSezione(@PathParam("idIntervento") Integer idIntervento, PlainQuintaSezione quintaSezione,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/{idIntervento}/sesto")
	@Produces({ "application/json" })
	public Response getSestoSezione(@PathParam("idIntervento") Integer idIntervento,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@POST
	@Path("/{idIntervento}/sesto")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response saveSestoSezione(@PathParam("idIntervento") Integer idIntervento, PlainSestoSezione plainSestoSezione,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/bo/search")
	@Produces({ "application/json" })
	public Response backOfficeSearch(@Context UriInfo info, @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/bo/excel")
	@Produces("application/vnd.ms-excel")
	public Response backOfficeExcel(@Context UriInfo info, @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/{idIntervento}/elencaDich")
	@Produces({ "application/json" })
	public Response getTuttiElencaForIntervento(@PathParam("idIntervento") Integer idIntervento, @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/{idIntervento}/elencoDocsGestore")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDocsGestoreByIntervento(@PathParam("idIntervento") Integer idIntervento,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/{idIntervento}/invio")
	@Produces({ "application/json" })
	public Response getDataInvioIstanza(@PathParam("idIntervento") Integer idIntervento, @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/{idIntervento}/inviata")
	@Produces({ "application/json" })
	public Response getIstanzaInviata(@PathParam("idIntervento") Integer idIntervento, @Context HttpServletRequest httpRequest);
	
	
	@POST
	@Path("/{idIntervento}/invio")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response invioIstanza(@PathParam("idIntervento") Integer idIntervento, ProfessionistaElencoAllegati body, @Context HttpServletRequest httpRequest);

	@GET
	@Path("/bo/{idIntervento}/verificata")
	@Produces({ "application/json" })
	public Response updateIstanzaVerificata(@PathParam("idIntervento") Integer idIntervento, @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/bo/{idIntervento}/rifiutata")
	@Produces({ "application/json" })
	public Response updateIstanzaRifiutata(@PathParam("idIntervento") Integer idIntervento, 
			@QueryParam("motivazioneRifiuto") String motivazioneRifiuto, @Context HttpServletRequest httpRequest);
	
	@PUT
	@Path("/bo/vincolo/autorizzata")
	@Produces({ "application/json" })
	public Response updateIstanzaVincoloAutorizzata(AutorizzaVincolo body, @Context HttpServletRequest httpRequest);
	
	@PUT
	@Path("/bo/vincolo/update/dataFineIntervento")
	@Produces({ "application/json" })
	public Response updateDataFineIntervento(AutorizzaVincolo body, @Context HttpServletRequest httpRequest);
	
	@PUT
	@Path("/bo/tagli/update/dataFineIntervento")
	@Produces({ "application/json" })
	Response updateDataFineInterventoTagli(AutorizzaIstanza body, @Context HttpServletRequest httpRequest);

	@PUT
	@Path("/bo/tagli/autorizza")
	@Produces({ "application/json" })
	Response updateIstanzaTagliAutorizzata(AutorizzaIstanza body, @Context HttpServletRequest httpRequest);

	@PUT
	@Path("/bo/tagli/verifica")
	@Produces({ "application/json" })
	Response updateIstanzaTagliVerifica(AutorizzaIstanza body, @Context HttpServletRequest httpRequest);

	@PUT
	@Path("/bo/tagli/annulla")
	@Produces({ "application/json" })
	Response updateIstanzaTagliAnnulla(AnnullaIstanza body, @Context HttpServletRequest httpRequest);



	@GET
	@Path("/{idIntervento}/bo")
	@Produces({ "application/json" })
	public Response getUtenteForIstanza(@PathParam("idIntervento") Integer idIntervento, @Context HttpServletRequest httpRequest);
	
	
	@GET
	@Path("bo/{idIntervento}/ceo")
	@Produces({ "application/json" })
	public Response getCeoIstanza(@PathParam("idIntervento") Integer idIntervento, @Context HttpServletRequest httpRequest);
	
	@POST
	@Path("/bo/{idIntervento}/asociare")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response associareDocumenti(@PathParam("idIntervento") Integer idIntervento, ProfessionistaElencoAllegati body, @Context HttpServletRequest httpRequest);

	@GET
	@Path("/recalculate/particelle/{idIntervento}")
	@Produces({ "application/json" })
	public Response recalculateParticelleIntervento(@PathParam("idIntervento") Integer idIntervento, @Context HttpServletRequest httpRequest);

	@DELETE
	@Produces({ "application/json" })
	@Path("{idIntervento}")
	public Response deleteIntervento(@PathParam("idIntervento") Integer idIntervento, @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("primpa/{idIstanza}")
	@Produces({ "application/json" })
	public Response getDettaglioIstanzaPrimpafor(@PathParam("idIstanza") Integer idIstanza, @Context HttpServletRequest httpRequest);

}
