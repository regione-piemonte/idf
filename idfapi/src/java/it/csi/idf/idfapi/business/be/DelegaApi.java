/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import it.csi.idf.idfapi.business.be.exceptions.ServiceException;

@Path("/private/deleghe")
@Produces({ "application/json" })
public interface DelegaApi {
	
	@GET
	@Path("/mieiDelegati")
	@Produces({ "application/json" })
	public Response getUtenteDelegati(@QueryParam("codiceFiscale") String codiceFiscale, @Context HttpServletRequest httpRequest);
	@GET
	@Path("/find")
	@Produces({ "application/json" })
	public Response findDelegaByCodFiscale(@QueryParam("codiceFiscale") String codiceFiscale, @Context HttpServletRequest httpRequest) throws RecordNotUniqueException;
	
	@GET
	@Path("/mieiCodiciDelegati")
	@Produces({ "application/json" })
	public Response getUtenteDelegatiCodici(@Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/miaListaDeleghe")
	@Produces({ "application/json" })
	public Response getMiaListaDeleghe(@Context HttpServletRequest httpRequest);
	
	@POST
    @Produces({ "application/json" })
	@Path("/save")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response saveDelega(@MultipartForm MultipartFormDataInput data, 
			@Context SecurityContext securityContext, 
			@Context HttpHeaders httpHeaders,@Context HttpServletRequest httpRequest);
	
	@DELETE
	@Path("/delete")
	@Produces({ "application/json" })
	public Response deleteDelega(@QueryParam("codiceFiscale") String codiceFiscale, @Context HttpServletRequest httpRequest);
	
	@GET
	@Path("/download")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response downloadFileDelega(@QueryParam("uuid") String uuid,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest, @Context HttpServletResponse httpResponse) throws ServiceException;
}
