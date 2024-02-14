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
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import it.csi.idf.idfapi.dto.CsvReport;

@Path("/private/report/csv")
@Produces({"application/json"})
public interface ReportByCsvApi {
	
	
	@POST
	@Path("/elabora")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response getReportData(@QueryParam("adsType") String adsType, @QueryParam("idReport") int idReport,
			@MultipartForm MultipartFormDataInput file,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,  @Context HttpServletRequest httpRequest);

	@GET
	@Path("/list")
	@Produces({"application/json"})
	public Response getReportsListByAdsType(@QueryParam("idAdsType") int idAdsType,	@Context SecurityContext securityContext, 
			@Context HttpHeaders httpHeaders,  @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/ads/list")
	@Produces({"application/json"})
	public Response getAdsTypeList(@Context SecurityContext securityContext, 
			@Context HttpHeaders httpHeaders,  @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/detail")
	@Produces({"application/json"})
	public Response getReportsDetail(@QueryParam("reportId") Integer reportId,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,  @Context HttpServletRequest httpRequest);
	
	@Path("/excel/table")
	@POST
	@Produces("application/vnd.ms-excel")
	public Response downloadExcel(CsvReport csvReport, @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, 
			@Context HttpServletRequest httpRequest);
	
	@Path("/excel/get")
	@GET
	@Produces("application/vnd.ms-excel")
	public Response downloadExcelGet(@QueryParam("title")String title,@QueryParam("dataCsv")String dataCsv, 
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,  @Context HttpServletRequest httpRequest);

}	
