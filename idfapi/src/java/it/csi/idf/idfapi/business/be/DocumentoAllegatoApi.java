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

import it.csi.idf.idfapi.util.TipoAllegatoEnum;
import it.csi.idf.idfapi.util.TipoAllegatoPfaEnum;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import it.csi.idf.idfapi.business.be.exceptions.ServiceException;
import it.csi.idf.idfapi.dto.DocumentoAllegato;
import it.csi.idf.idfapi.dto.FatDocumentoAllegato;
import it.csi.idf.idfapi.dto.FileUploadForm;

@Path("/private/documenti")
@Produces({ "application/json" })
public interface DocumentoAllegatoApi {

	@GET
	@Produces({ "application/json" })
	public Response getDocumenti(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/{idDocumentoAllegato}")
	@Produces({ "application/json" })
	public Response getDocumentoByID(@PathParam("idDocumentoAllegato") Integer idDocumentoAllegato,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@GET
	@Path("/pfa/{idGeoPlPfa}")
	@Produces({ "application/json" })
	public Response getDocumentiByPfa(@PathParam("idGeoPlPfa") Integer idGeoPlPfa,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);

	@POST
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public Response saveDocumento(DocumentoAllegato body, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);


	@GET
	@Path("/download")
	public Response downloadDocumento(@QueryParam("idDocumento") int idDocumento,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest, @Context HttpServletResponse httpResponse) throws IOException;

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces({ "application/json" })
	public Response uploadDocumento(@QueryParam("intervento") int idIntervento, @QueryParam("tipo") int tipo,
			 @MultipartForm FileUploadForm form, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);

	
	@POST
	@Path("/uploadVincolo")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces({ "application/json" })
	public Response uploadDocumentoVincolo(@QueryParam("intervento") int idIntervento, @QueryParam("tipo") int tipo,
			 @MultipartForm FileUploadForm form, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	@POST
	@Path("/uploadRicevutePagamentoVincolo")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces({ "application/json" })
	public Response uploadRicevutePagamento(@QueryParam("intervento") int idIntervento,
			 @MultipartForm MultipartFormDataInput form, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);
	
	/*
	 * @Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response saveSpecie(@MultipartForm MultipartFormDataInput data, 
	 */
	
	@POST
	@Path("/uploadPfa")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces({ "application/json" })
	Response uploadPFADocumento(@QueryParam("intervento") int idIntervento, @QueryParam("idGeoPlPfa") int idGeoPlPfa,
			@QueryParam("tipo") int tipoDocumento, @MultipartForm MultipartFormDataInput form,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);
	
	@POST
	@Path("/uploadPfaV2")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces({ "application/json" })
	Response uploadPFADocumentoV2(@QueryParam("intervento") int idIntervento, @QueryParam("idGeoPlPfa") int idGeoPlPfa,
			@QueryParam("tipo") int tipoDocumento, @MultipartForm FileUploadForm form,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);


	@POST
	@Path("/uploadDrel/{idIntervento}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces({ "application/json" })
	Response uploadDrel(@PathParam("idIntervento") Integer idIntervento,
					   @MultipartForm MultipartFormDataInput form,
					   @Context SecurityContext securityContext,
					   @Context HttpHeaders httpHeaders,
					   @Context HttpServletRequest httpRequest);


	@GET
	@Path("/getDoc")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response downloadPortalDocumento(@QueryParam("idDocumento") int idDocumento,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest, @Context HttpServletResponse httpResponse) throws ServiceException;
	
	@GET
	@Path("/getVincoloDoc")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response downloadVincoloPortalDocumento(@QueryParam("idDocumento") int idDocumento,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest, @Context HttpServletResponse httpResponse) throws ServiceException;
	
	@GET
	@Path("/getPfaDoc/{idDocumentoAllegato}")
	public Response downloadPortalPfaDocumento(@PathParam("idDocumentoAllegato") Integer idDocumentoAllegato,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest, @Context HttpServletResponse httpResponse) throws ServiceException;

	@DELETE
	@Path("/delete/{idDocumentoAllegato}")
	public Response deleteDocumentoById(@PathParam("idDocumentoAllegato") Integer idDocumentoAllegato,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest, @Context HttpServletResponse httpResponse) throws IOException;

	@DELETE
	@Path("/deletePfa/{idDocumentoAllegato}")
	public Response deletePfaDocumentoById(@PathParam("idDocumentoAllegato") Integer idDocumentoAllegato,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest, @Context HttpServletResponse httpResponse) throws IOException;
	
	
	@GET
	@Path("/dichiarazione/{idIntervento}/{tipoAllegato}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	Response getDichiarazione(@PathParam("idIntervento") Integer idIntervento,
			@PathParam("tipoAllegato") Integer tipoAllegato, @Context HttpServletRequest httpRequest);

	@GET
	@Path("/dichiarazione/xdoc/{idIntervento}/{tipoAllegato}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	Response getDichiarazioneXdoc(@PathParam("idIntervento") Integer idIntervento,
								  @PathParam("tipoAllegato") TipoAllegatoPfaEnum tipoAllegato, @Context HttpServletRequest httpRequest);

	@PUT
	@Path("/updateUploadingPfa")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	Response updateUpload(FatDocumentoAllegato documentoAllegato, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest,
			@Context HttpServletResponse httpResponse);

	@GET
	@Path("/interventi/{idIntervento}")
	@Produces({ "application/json" })
	public Response getDocumentiByIdIntervento(@PathParam("idIntervento") Integer idIntervento,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest);


	@POST
	@Path("/uploadTagli")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces({ "application/json" })
	Response uploadDocumentoTagli(@QueryParam("intervento") int idIntervento, @QueryParam("tipo") int tipo,
							     @MultipartForm FileUploadForm form, @Context SecurityContext securityContext,
							     @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest);


	@GET
	@Path("/getTagliDoc")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	Response downloadTagliPortalDocumento(@QueryParam("idDocumento") int idDocumento,
												   @Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
												   @Context HttpServletRequest httpRequest, @Context HttpServletResponse httpResponse) throws ServiceException;


}
