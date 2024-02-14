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

import it.csi.idf.idfapi.dto.AdsDatiStazionaliOne;
import it.csi.idf.idfapi.dto.AdsDatiStazionaliTwo;
import it.csi.idf.idfapi.dto.AreaDiSaggioDTO;
import it.csi.idf.idfapi.dto.ExcelDTO;

@Path("/private/areaDiSaggio")
@Produces({ "application/json" })
public interface GeoPtAreaDiSaggioApi {
	
	@GET
	@Path("/{idgeoPtAds}")
	@Produces({ "application/json" })
	public Response getNumberOfNextStep(@PathParam("idgeoPtAds") Long idgeoPtAds, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/datiGenerali/{idgeoPtAds}")
	@Produces({ "application/json" })
	public Response getDatiGenerali(@PathParam("idgeoPtAds") Long idgeoPtAds, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/comuneProvincia/{x}/{y}")
	@Produces({ "application/json" })
	public Response getComuneProvincia(@PathParam("x") Double x, @PathParam("y") Double y, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@Path("/search")
	@GET
	@Produces({ "application/json" })

	public Response search(@QueryParam("idComune") String idComuneStr[], @QueryParam("istatProv") String istatProv[],
			@QueryParam("idCategoriaForestale") Integer idCategoriaForestale[], @QueryParam("idAmbito") Integer idAmbito [],
			@QueryParam("dettaglioAmbito") String dettaglioAmbito [], @QueryParam("fromDate") String fromDate[],
			@QueryParam("toDate") String toDate[], @QueryParam("tipologia") Integer tipoAds[], 
			@QueryParam("statoScheda") Integer statoScheda [], @QueryParam("page") int page, @QueryParam("limit") int limit,
			@QueryParam("sort") String sort,@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@Path("/search")
	@POST
	@Produces("application/vnd.ms-excel")

	public Response searchExcel(ExcelDTO excel, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	

	@Path("/export2excel")
	@POST
	@Produces("application/vnd.ms-excel")

	public Response exportSearchResults2Excel(@QueryParam("idComune") String idComuneStr[], @QueryParam("istatProv") String istatProv[],
			@QueryParam("idCategoriaForestale") Integer idCategoriaForestale[], @QueryParam("idAmbito") Integer idAmbito [],
			@QueryParam("dettaglioAmbito") String dettaglioAmbito [], @QueryParam("fromDate") String fromDate[],
			@QueryParam("toDate") String toDate[], @QueryParam("tipologia") Integer tipoAds[], 
			@QueryParam("statoScheda") Integer statoScheda [], @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@Path("/export2excel/basicinfo")
	@POST
	@Produces("application/vnd.ms-excel")

	public Response exportSearchResults2ExcelBasicInfo(@QueryParam("idComune") String idComuneStr[], @QueryParam("istatProv") String istatProv[],
			@QueryParam("idCategoriaForestale") Integer idCategoriaForestale[], @QueryParam("idAmbito") Integer idAmbito [],
			@QueryParam("dettaglioAmbito") String dettaglioAmbito [], @QueryParam("fromDate") String fromDate[],
			@QueryParam("toDate") String toDate[], @QueryParam("tipologia") Integer tipoAds[], 
			@QueryParam("statoScheda") Integer statoScheda [], @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@Path("/export/{idgeoPtAds}")
	@GET
	@Produces("application/vnd.ms-excel")

	public Response exportAreaDiSaggionDetails(@PathParam("idgeoPtAds") Long idgeoPtAds  , @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@Path("/excelDatiStazionali")
	@POST
	@Produces("application/vnd.ms-excel")

	public Response excelDatiStazionali(ExcelDTO excel, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@Path("/excelAlberiCAMeDOM")
	@POST
	@Produces("application/vnd.ms-excel")

	public Response excelAlberiCAMeDOM(ExcelDTO excel, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@Path("/excelAlberiCavallettati")
	@POST
	@Produces("application/vnd.ms-excel")

	public Response excelAlberiCavallettati(ExcelDTO excel, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@Path("/excelRelascopicaSemplice/{idgeoPtAds}")
	@GET
	@Produces("application/vnd.ms-excel")

	public Response excelRelascopicaSemplice(@PathParam("idgeoPtAds") Long idgeoPtAds, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@Path("/excelRelascopicaCompleta/{idgeoPtAds}")
	@GET
	@Produces("application/vnd.ms-excel")

	public Response excelRelascopicaCompleta(@PathParam("idgeoPtAds") Long idgeoPtAds, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@Path("/search/{idgeoPtAds}")
	@GET
	@Produces({ "application/json" })
	public Response getAreaDiSaggioByIdgeoPtAds(@PathParam("idgeoPtAds") Long idgeoPtAds,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@Path("/searchADS/{idgeoPtAds}")
	@GET
	@Produces({ "application/json" })
	public Response getADSByIdgeoPtAds(@QueryParam("step") boolean includeStep,  @PathParam("idgeoPtAds") Long idgeoPtAds,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@Path("/search/campione/{idgeoPtAds}")
	@GET
	@Produces({ "application/json" })
	public Response getAlberiCampioneDominante(@PathParam("idgeoPtAds") Long idgeoPtAds,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@Path("/search/alberiCAV/{idgeoPtAds}")
	@GET
	@Produces({ "application/json" })
	public Response getAlberiCAV(@PathParam("idgeoPtAds") Long idgeoPtAds,@QueryParam("page") int page, @QueryParam("limit") int limit,
			@QueryParam("sort") String sort,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@Path("/search/relascopicaSemplice/{idgeoPtAds}")
	@GET
	@Produces({ "application/json" })
	public Response getRelascopicaQueryForSearch(@PathParam("idgeoPtAds") Long idgeoPtAds,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@Path("/search/relascopicaCompleta/{idgeoPtAds}")
	@GET
	@Produces({ "application/json" })
	public Response getRelascopicaCompletaSort(@PathParam("idgeoPtAds") Long idgeoPtAds,@QueryParam("page") int page, @QueryParam("limit") int limit,
			@QueryParam("sort") String sort,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@Path("/search/relascopicaCompletaCAV/{idgeoPtAds}")
	@GET
	@Produces({ "application/json" })
	public Response getRelascopicaCompletaSortCAV(@PathParam("idgeoPtAds") Long idgeoPtAds,@QueryParam("page") int page, @QueryParam("limit") int limit,
			@QueryParam("sort") String sort,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response insert(AreaDiSaggioDTO areaDiSaggio,@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@Path("/insert/empty")
	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response insertEmpty(AreaDiSaggioDTO areaDiSaggio,@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	
	@PUT
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response update(AreaDiSaggioDTO areaDiSaggio,@Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	@Path("/datiStazionali1/create")
	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response insertSuperficieDati1(AdsDatiStazionaliOne adsDatiStazionaliOne, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@Path("/datiStazionali1/{idgeoPtAds}")
	@GET
	@Produces({ "application/json" })
	public Response getSuperficieDati1(@PathParam("idgeoPtAds") Long idgeoPtAds, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@Path("/datiStazionali2/{idgeoPtAds}")
	@GET
	@Produces({ "application/json" })
	public Response getSuperficieDati2(@PathParam("idgeoPtAds") Long idgeoPtAds, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);


	@Path("/datiStazionali2/create")
	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response insertSuperficieDati2(AdsDatiStazionaliTwo adsDatiStazionaliTwo, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@DELETE
	@Path("/{idgeoPtAds}")
	@Produces({ "application/json" })
	public Response deleteAds(@PathParam("idgeoPtAds") Long idgeoPtAds, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
}
