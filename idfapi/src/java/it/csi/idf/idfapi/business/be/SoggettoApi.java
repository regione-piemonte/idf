/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.*;

import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.dto.TSoggetto;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;

@Path("/private/soggetti")
@Produces({ "application/json" })

public interface SoggettoApi {

	@GET
	@Path("/{idSoggetto}")
	@Produces({ "application/json" })
	public Response getSoggetto(@PathParam("idSoggetto") Integer idSoggetto, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Produces({ "application/json" })
	public Response getSoggettoByPartialCodiceFiscale(@QueryParam("codiceFiscale") String codiceFiscale, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	public Response saveSoggetto(TSoggetto body, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);
	
	@PUT
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public Response updateSoggetto(TSoggetto body,@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest );
	
	@GET
	@Path("/io")
	@Produces({ "application/json" })
	public Response getMyself(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/bo/pf")
	@Produces({ "application/json" })
	public Response getPersonaFisicaForSearch(@QueryParam("idTipoIstanza") Integer idTipoIstanza);
	
	@GET
	@Path("/bo/pg")
	@Produces({ "application/json" })
	public Response getPersonaGiurdicaForSearch(@QueryParam("idTipoIstanza") Integer idTipoIstanza);
	
	@GET
	@Path("/bo/tagli/pf")
	@Produces({ "application/json" })
	Response getPersonaFisicaForTagliSearch(@QueryParam("s") String search);

	@GET
	@Path("/bo/tagli/pg")
	@Produces({ "application/json" })
	Response getPersonaGiurdicaForTagliSearch(@QueryParam("s") String search);

	@GET
	@Path("/bo/utlizzatore/pf")
	@Produces({ "application/json" })
	Response getUtilizzatorePFForSearch(@QueryParam("q") String search);

	@GET
	@Path("/bo/utlizzatore/pg")
	@Produces({ "application/json" })
	Response getUtilizzatorePGForSearch(@QueryParam("q") String search);

	@GET
	@Path("/bo/utlizzatore/prof")
	@Produces({ "application/json" })
	Response getTecniciForestaliForTagli(@QueryParam("q") String search);

	@GET
	@Path("/bo/sportello/list")
	@Produces({ "application/json" })
	Response getAllSportello();


	@GET
	@Path("/bo/prof")
	@Produces({ "application/json" })
	public Response getProfessionistaForSearch(@QueryParam("idTipoIstanza") Integer idTipoIstanza);


	@GET
	@Path("/bo/edit/enabled")
	@Produces({ "application/json" })
	public Response isUserEnabledEditIstanza(@QueryParam("idIntervento") Integer idIntervento , @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/bo/professionisti/list")
	@Produces({ "application/json" })
	public Response getProfessionistiList();


	@GET
	@Path("/bo/sportello/current")
	@Produces({ "application/json" })
    Response getSportelloCorrente(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);

	@GET
	@Path("/bo/sportello/associato/list")
	@Produces({ "application/json" })
    Response getSportelliAssociati(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);

    @GET
	@Path("/bo/gestori/list")
	@Produces({ "application/json" })
	Response getGestoriList(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);


	@GET
	@Path("/myGiuridica")
	@Produces({ "application/json" })
	public Response getPersonaGiurdicaByOwnerCodiceFiscale(@QueryParam("codiceFiscale") String codiceFiscale);
	
	@GET
	@Path("/myGiuridicaPubblicaPrivata")
	@Produces({ "application/json" })
	Response getPersonaGiurdicaPubOrPrivByOwnerCodiceFiscale(
			@QueryParam("codiceFiscale") String codiceFiscale,
			@QueryParam("isPubblico") Boolean isPubblico);

	@GET
	@Path("/giuridica")
	@Produces({ "application/json" })
	public Response getPersonaGiurdicaByCodiceFiscale(@QueryParam("codiceFiscale") String codiceFiscale);

	@GET
	@Path("/giuridica/pubblic")
	@Produces({ "application/json" })
	Response getPersonaGiurdicaByCodiceFiscaleAndIsPubblic(
			@QueryParam("codiceFiscale") String codiceFiscale,
			@QueryParam("isPubblic") Boolean isPubblic);

	@GET
	@Path("/fisica")
	@Produces({ "application/json" })
	public Response getPersonaFisicaByCodiceFiscale(@QueryParam("codiceFiscale") String codiceFiscale);

	@GET
	@Path("/pf")
	@Produces({ "application/json" })
	public Response getAllProfessionista(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/aaepAutentification")
	@Produces({ "application/json" })
	public Response aaepByCodiceFiscale(@QueryParam("codiceFiscale") String codiceFiscale);
	
	@GET
    @Path("/cercaAziende")
	@Produces({ "application/json" })
	public Response cercaAziendeAAEPByCodiceFiscale(@QueryParam("codiceFiscale") String codiceFiscale);
	
	@GET
    @Path("/tecnicoForestale")
	@Produces({ "application/json" })
	public Response getAllTecnicoForestale(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);


	@GET
	@Path("/search-pf-by-param")
	@Produces(MediaType.APPLICATION_JSON)
	Response searchByOneParam(@QueryParam("search") String search,
							  @Context SecurityContext securityContext,
							  @Context HttpHeaders httpHeaders,
							  @Context HttpServletRequest httpRequest);

	@GET
	@Path("/search-pg-by-param")
	@Produces(MediaType.APPLICATION_JSON)
	Response searchPgByOneParam(@QueryParam("search") String search,
								@QueryParam("entePubblico") Boolean entePubblico,
							  @Context SecurityContext securityContext,
							  @Context HttpHeaders httpHeaders,
							  @Context HttpServletRequest httpRequest);

}
