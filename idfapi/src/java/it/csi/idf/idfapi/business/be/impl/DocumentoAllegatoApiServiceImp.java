/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.SecurityContext;

import com.google.common.base.CaseFormat;
import it.csi.idf.idfapi.business.be.impl.dao.*;
import it.csi.idf.idfapi.business.be.service.TagliSelvicolturaliService;
import it.csi.idf.idfapi.dto.*;
import it.csi.idf.idfapi.util.*;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import it.csi.idf.idfapi.business.be.DocumentoAllegatoApi;
import it.csi.idf.idfapi.business.be.exceptions.ServiceException;
import it.csi.idf.idfapi.business.be.service.FileGenerator;
import it.csi.idf.idfapi.business.be.service.MailService;
import it.csi.idf.idfapi.business.be.service.helper.IndexServiceHelper;
import it.csi.idf.idfapi.business.be.service.helper.dto.MetadatiIndexPfa;
import it.csi.idf.idfapi.business.be.service.helper.dto.MetadatiIndexTrasformazioni;
import it.csi.idf.idfapi.rest.BaseResponses;
import it.csi.idf.idfapi.util.mail.MailEnum;

public class DocumentoAllegatoApiServiceImp extends SpringSupportedResource implements DocumentoAllegatoApi {

	public static final String LOGGED_CONFIG = "loggedConfig";
 
	@Autowired
	private DocumentoAllegatoDAO documentoAllegatoDAO;

	@Autowired
	private IstanzaForestDAO istanzaForestDAO;

	@Autowired
	private WrapperAdpforHomeDAO wrapperAdpforHomeDAO;
	
	@Autowired
	private UserSessionDAO userSessionDAO;

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	private FileGenerator fileGenerator;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	CnfBoprocLogDAO cnfBoprocLogDAO;

	@Autowired
	TipoAllegatoDAO tipoAllegatoDAO;

	@Autowired
	TagliSelvicolturaliService tagliSelvicolturaliService;

	@Override
	public Response getDocumenti(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		List<DocumentoAllegato> documentoList = documentoAllegatoDAO.findAllDocumenti();

		return Response.ok(documentoList).build();
	}

	@Override
	public Response getDocumentoByID(Integer idDocumentoAllegato, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			return Response.ok(documentoAllegatoDAO.findDocumentoByID(idDocumentoAllegato)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}

	}

	@Override
	public Response saveDocumento(DocumentoAllegato body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return Response.ok(documentoAllegatoDAO.createDocumentoAllegato(body)).build();
	}

	@Override
	public Response getDocumentiByPfa(Integer idGeoPlPfa, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		try {
			return Response.ok(documentoAllegatoDAO.findAllDocumentiByPfa(idGeoPlPfa)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response downloadDocumento(int idDocumento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {

		DocumentoAllegato documentoAllegato = documentoAllegatoDAO.findDocumentoByID(idDocumento);
		return Response.ok(documentoAllegato).build();
	}

	@Override
	public Response uploadDocumento(int idIntervento, int tipoDocumento,  FileUploadForm form,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			IndexServiceHelper indexSrvHelper = (IndexServiceHelper) applicationContext.getBean("indexServiceHelper");
			boolean isSigned = !SignatureTypeEnum.UNSIGNED.toString().equals(indexSrvHelper.getSignatureType(form.getData()));
			String errore = null;
			if(TipoAllegatoEnum.DICHIARAZIONE_DIGITALE.getValue() == tipoDocumento && !isSigned) {
				errore = "Documento con firma digitale non valida";				
			}else if(TipoAllegatoEnum.DICHIARAZIONE_DIGITALE.getValue() != tipoDocumento && isSigned) {
				errore = "Documento non valido: e' presente una firma digitale, che non e' prevista per questa tipologia di documento";
			}
			if(errore != null) {
				Map<String,String> res = new HashMap<String,String>();
				res.put("error",errore);
				return Response.ok(res).build();
			}
			ConfigUtente loggedConfig = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			FatDocumentoAllegato docWithId = documentoAllegatoDAO.uploadDocumento(idIntervento, tipoDocumento, form,
					loggedConfig);
			if (docWithId != null) {
				return Response.ok(docWithId).build();
			} else {
				return Response.serverError().build();
			}
		}catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}

	}
	
	@Override
	public Response uploadDocumentoVincolo(int idIntervento, int tipoDocumento,  FileUploadForm form,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			IndexServiceHelper indexSrvHelper = (IndexServiceHelper) applicationContext.getBean("indexServiceHelper");
			boolean isSigned = !SignatureTypeEnum.UNSIGNED.toString().equals(indexSrvHelper.getSignatureType(form.getData()));
			String errore = null;
			if(isDocDigitale(tipoDocumento) && !isSigned) {
				errore = "Documento con firma digitale non valida";				
			}else if( !isDocDigitale(tipoDocumento) && isSigned) {
				errore = "Documento non valido: e' presente una firma digitale, che non e' prevista per questa tipologia di documento";
			}
			if(errore != null) {
				Map<String,String> res = new HashMap<String,String>();
				res.put("error",errore);
				return Response.ok(res).build();
			}
			ConfigUtente loggedConfig = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			FatDocumentoAllegato docWithId = documentoAllegatoDAO.uploadDocumentoVincolo(idIntervento, tipoDocumento, form,
					loggedConfig);
			if (docWithId != null) {
				return Response.ok(docWithId).build();
			} else {
				return Response.serverError().build();
			}
		}catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	private boolean isDocDigitale(int id) {
		List<Integer> idsTipiAllegatoConFirmaDigitale = tipoAllegatoDAO.getIdsTipiAllegatoConFirmaDigitale();
		return idsTipiAllegatoConFirmaDigitale.contains(id);
	}
	
	@Override
	public Response uploadRicevutePagamento(int idIntervento, MultipartFormDataInput formIn,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			Map<String, List<InputPart>> mapData = formIn.getFormDataMap();
			ConfigUtente loggedConfig = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			
			int[] listDocsEnabled = new int[] {TipoAllegatoEnum.RICEVUTA_VERSAMENTO_DEPOSITO_CAUZIONALE.getValue(),
					TipoAllegatoEnum.RICEVUTA_VERSAMENTO_COMPENSAZIONE_FISICA.getValue(), TipoAllegatoEnum.RICEVUTA_VERSAMENTO_COMPENSAZIONE_MONETARIA.getValue()};
			
			
			boolean sentMail = false;
			FatDocumentoAllegato docWithId =  null;
			FileUploadForm form;
		
			for(int idTipoDoc:listDocsEnabled) {
				if(mapData.get("form_" + idTipoDoc) != null) {
					form = fillFileUploadForm(mapData,idTipoDoc);
					
					deleteAllegatoIfExist(idIntervento, idTipoDoc);
					docWithId = documentoAllegatoDAO.uploadDocumentoVincolo(idIntervento, idTipoDoc, form,
							loggedConfig);
					if (docWithId == null) {
						return Response.serverError().build();
					}else {
						if(!sentMail) {
							mailService.sendMailVincoloByIdIntervento(idIntervento, MailEnum.RICEVUTA_VERSAMENTO_VINCOLO);
							sentMail = true;	
							//protocollazione
							int idx = 2;
							String fkIstanza = cnfBoprocLogDAO.getLastFkIstanzaByidIstanza(idIntervento);//(idIntervento) <2 ? 2:3;
							if(fkIstanza != null) {
								String[]values = fkIstanza.split("-");
								if(values.length == 2 && "2".equals(values[1])) {
									idx = 3;
								}
							}								
								
							BoProcLog procLog = new BoProcLog();
							procLog.setFkIstanza(idIntervento + "-" + idx);
							procLog.setFkAmbitoIstanza(AmbitoIstanzaEnum.VINCOLO_IDROGEOLOGICO.getValue());
							procLog.setFkStepBoproc(1);
							procLog.setCountTentativo(0);
							cnfBoprocLogDAO.create(procLog);
						}
					}
				}
			}
			return Response.ok(docWithId).build();
			
		}catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		
	}

	@Override
	public Response uploadDrel(Integer idIntervento, MultipartFormDataInput formIn, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			Map<String, List<InputPart>> mapData = formIn.getFormDataMap();
			ConfigUtente loggedConfig = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);


			FatDocumentoAllegato docWithId =  null;
			FileUploadForm form;

			int idTipoDoc = TipoAllegatoPfaEnum.DICHIARAZIONE_DREL.getValue();
			if(mapData.get("form_" + idTipoDoc) != null) {
				form = fillFileUploadForm(mapData,idTipoDoc);
				deleteAllegatoPfaIfExist(idIntervento, idTipoDoc);

				docWithId = documentoAllegatoDAO.uploadDocumentoTagli(idIntervento, idTipoDoc, form, loggedConfig);
				if (docWithId == null) {
					return Response.serverError().build();
				}else {
					logger.info("befor sendMailTagliByIdIntervento in uploadDrel");
					mailService.sendMailTagliByIdIntervento(idIntervento, MailEnum.CONFERMA_INVIO_INTEGRAZIONE_TAGLI);
					logger.info("after sendMailTagliByIdIntervento in uploadDrel");
					//protocollazione
					//TODO: protocollazione COSMO / DOQUI ACTA

					BoProcLog procLog = new BoProcLog();
				   //procLog.setFkIstanza(idIntervento.toString());
					procLog.setFkIstanza(idIntervento + "-2");
					procLog.setFkAmbitoIstanza(AmbitoIstanzaEnum.TAGLIO_IN_BOSCO.getValue());
					///7777
					//procLog.setFkStepBoproc(3);
					procLog.setFkStepBoproc(1);
					procLog.setCountTentativo(0);
					cnfBoprocLogDAO.create(procLog);

					// set state to ARCHIVIATA
					tagliSelvicolturaliService.updateIstanzaTo(idIntervento,
							null,
							loggedConfig.getIdConfigUtente(),
							InstanceStateEnum.ARCHIVIATA);
				}
			}
			return Response.ok(docWithId).build();

		}catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}

	}

	private void deleteAllegatoIfExist(int idIntervento, int idTipoDoc) {
		List<TipoAllegatoEnum> tipoAllegati;
		List<FatDocumentoAllegato> listToDelete;
		tipoAllegati = new ArrayList<TipoAllegatoEnum>();
		tipoAllegati.add(TipoAllegatoEnum.getEnumByValue(idTipoDoc));
		listToDelete = documentoAllegatoDAO.findDocumentiByIdAndTipos(idIntervento, tipoAllegati);
		for(FatDocumentoAllegato item : listToDelete) {
			documentoAllegatoDAO.deleteDocumentoById(item.getIdDocumentoAllegato());
		}
	}
	
	private void deleteAllegatoPfaIfExist(int idIntervento, int idTipoDoc) {
		List<TipoAllegatoPfaEnum> tipoAllegati;
		List<FatDocumentoAllegato> listToDelete;
		tipoAllegati = new ArrayList<TipoAllegatoPfaEnum>();
		tipoAllegati.add(TipoAllegatoPfaEnum.getEnumByValue(idTipoDoc));
		listToDelete = documentoAllegatoDAO.findDocumentiByIdAndTiposPfa(idIntervento, tipoAllegati);
		for(FatDocumentoAllegato item : listToDelete) {
			documentoAllegatoDAO.deleteDocumentoById(item.getIdDocumentoAllegato());
		}
	}

	private FileUploadForm fillFileUploadForm(Map<String, List<InputPart>> mapData, int idTipoDoc) throws UnsupportedEncodingException, IOException {
		FileUploadForm form = new FileUploadForm();
		InputPart inputPart = mapData.get("note_" + idTipoDoc).get(0);
		String note = URLDecoder.decode(inputPart.getBodyAsString(), "UTF-8");
		form.setNote(note);
		inputPart = mapData.get("fileName_" + idTipoDoc).get(0);
		String fileName = URLDecoder.decode(inputPart.getBodyAsString(), "UTF-8");
		form.setName(fileName);
		inputPart = mapData.get("form_" + idTipoDoc).get(0);
		InputStream is = inputPart.getBody(InputStream.class, null);
		form.setData(IOUtils.toByteArray(is));
		return form;
	}
	
	public void callGetMethod() {
		String url = "http://tst-applogic.reteunitaria.piemonte.it/ecmenginews-exp02/services/EcmEngineManagement?wsdl";
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod(url);

		String response = null;
		try {

			logger.info("pre httpCall: " + url);

			int statusCode = client.executeMethod(method);
			logger.info("get httpCall");
			if (statusCode == 200) {
				response = method.getResponseBodyAsString();
			}
			logger.info("statusCode: " + statusCode);
			response = method.getResponseBodyAsString();
			logger.info("responseBody: " + (response == null?response: response.substring(200)));

			// release connection
			method.releaseConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Response uploadPFADocumento(int idIntervento, int idGeoPlPfa, int tipoDocumento, MultipartFormDataInput form,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			FatDocumentoAllegato docWithId = documentoAllegatoDAO.uploadDocumentoForPfa(idIntervento, idGeoPlPfa, tipoDocumento,
					confUtente, form, securityContext, httpHeaders, httpRequest);
			if (docWithId != null) {
				return Response.ok(docWithId).build();
			} else {
				return Response.serverError().build();
			}
		}catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response uploadPFADocumentoV2(int idIntervento, int idGeoPlPfa, int tipoDocumento, FileUploadForm form,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			FatDocumentoAllegato docWithId = documentoAllegatoDAO.uploadDocumentoForPfaV2(idIntervento, idGeoPlPfa, tipoDocumento,
					confUtente, form, securityContext, httpHeaders, httpRequest);
	
			if (docWithId != null) {
				return Response.ok(docWithId).build();
			} else {
				return Response.serverError().build();
			}
		}catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response deleteDocumentoById(Integer idDocumentoAllegato, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest, HttpServletResponse httpResponse)
			throws IOException {
		String uidDel = documentoAllegatoDAO.deleteDocumentoById(idDocumentoAllegato);
		if (uidDel != null) {
			return BaseResponses.successResponse(uidDel);

		} else {
			logger.info("deleteDocumentoById - uid not found for idDocumentoAllegato: " + idDocumentoAllegato);
			return Response.serverError().build();
		}

	}

	@Override
	public Response deletePfaDocumentoById(Integer idDocumentoAllegato, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest, HttpServletResponse httpResponse)
			throws IOException {
		String uidDel = documentoAllegatoDAO.deletePfaDocumentoById(idDocumentoAllegato);
		if (uidDel != null) {
			return BaseResponses.successResponse(uidDel);

		} else {
			return Response.serverError().build();
		}
	}

	@Override
	public Response downloadPortalDocumento(int idDocumento, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest, HttpServletResponse httpResponse)
			throws ServiceException {
		ResponseBuilder response = null;
		IndexServiceHelper indexSrvHelper = (IndexServiceHelper) applicationContext.getBean("indexServiceHelper");
		try {

			MetadatiIndexTrasformazioni metadatiIndexTrasf = new MetadatiIndexTrasformazioni();
			DocumentoAllegato documentoAllegato = documentoAllegatoDAO.findDocumentoByID(idDocumento);
			metadatiIndexTrasf.setIdIntervento(String.valueOf(documentoAllegato.getFkIntervento()));
			metadatiIndexTrasf.setIdTipoAllegato(String.valueOf(documentoAllegato.getFkTipoAllegato()));
			byte[] contDownloaded = indexSrvHelper
					.indexDownloadFileTrasformazioni(documentoAllegato.getIdDocumentoAllegato().toString());
			System.out.println("----- File <"+documentoAllegato.getNomeAllegato()+"-------->");
			response = Response.ok(contDownloaded).header("Content-disposition", "attachment; filename=" + documentoAllegato.getNomeAllegato());
			System.out.println("----- File <"+documentoAllegato.getNomeAllegato()+" OK -------->");
			return response.build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Override
	public Response downloadVincoloPortalDocumento(int idDocumento, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest, HttpServletResponse httpResponse)
			throws ServiceException {
		ResponseBuilder response = null;
		IndexServiceHelper indexSrvHelper = (IndexServiceHelper) applicationContext.getBean("indexServiceHelper");
		try {
			DocumentoAllegato documentoAllegato = documentoAllegatoDAO.findDocumentoByID(idDocumento);
			byte[] contDownloaded = indexSrvHelper
					.indexDownloadFileVincolo(documentoAllegato.getIdDocumentoAllegato().toString());

			response = Response.ok(contDownloaded).header("Content-disposition", "attachment; filename=" + documentoAllegato.getNomeAllegato());
			return response.build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response downloadTagliPortalDocumento(int idDocumento, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServiceException {
		ResponseBuilder response = null;
		IndexServiceHelper indexSrvHelper = (IndexServiceHelper) applicationContext.getBean("indexServiceHelper");
		try {
			DocumentoAllegato documentoAllegato = documentoAllegatoDAO.findDocumentoByID(idDocumento);
			byte[] contDownloaded = indexSrvHelper
					.indexDownloadFilePrimpa(documentoAllegato.getUidIndex());

			response = Response.ok(contDownloaded).header("Content-disposition", "attachment; filename=" + documentoAllegato.getNomeAllegato());
			return response.build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response downloadPortalPfaDocumento(Integer idDocumento, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest, HttpServletResponse httpResponse)
			throws ServiceException {
		ResponseBuilder response = null;
		IndexServiceHelper indexSrvHelper = (IndexServiceHelper) applicationContext.getBean("indexServiceHelper");
		try {

			MetadatiIndexPfa metadatiIndexPfa = new MetadatiIndexPfa();
			DocumentoAllegato documentoAllegato = documentoAllegatoDAO.findDocumentoByID(idDocumento);
			metadatiIndexPfa.setIdIntervento(String.valueOf(documentoAllegato.getFkIntervento()));
			metadatiIndexPfa.setIdTipoAllegato(String.valueOf(documentoAllegato.getFkTipoAllegato()));
			metadatiIndexPfa.setIdAllegato(String.valueOf(idDocumento));
			
			byte[] contDownloaded = null;
			if(documentoAllegato.getUidIndex() != null) {
				contDownloaded = indexSrvHelper.indexDownloadFilePfa(documentoAllegato.getUidIndex());
			}else {
				contDownloaded = indexSrvHelper.indexDownloadFilePfa(documentoAllegato.getIdDocumentoAllegato().toString());
			}

			

			response = Response.ok(contDownloaded);
			response.header("Content-disposition", "attachment; filename=" + documentoAllegato.getNomeAllegato());
			return response.build();

		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getDichiarazione(Integer idIntervento, Integer tipoAllegato, HttpServletRequest httpRequest) {
		try {
			TipoAllegatoEnum tipoAllegatoEnum = null;
			for (TipoAllegatoEnum e : TipoAllegatoEnum.values()) {
				if (e.getValue() == tipoAllegato) {
					tipoAllegatoEnum = e;
				}
			}
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			
			File file1 = fileGenerator.generateDichiarazione(tipoAllegatoEnum, idIntervento, confUtente);
		
			ResponseBuilder response = null;
			 response = Response.ok(file1).header("Content-disposition", "attachment; filename=" + file1.getName());
			return response.build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}


	@Override
	public Response getDichiarazioneXdoc(Integer idIntervento, TipoAllegatoPfaEnum tipoAllegato, HttpServletRequest httpRequest) {
		try {

			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);

			byte[] report = fileGenerator.generateDichiarazioneXdoc(tipoAllegato, idIntervento, confUtente);

			TipoAllegato tipAll = tipoAllegatoDAO.getTipoById(tipoAllegato.getValue());
			Integer nrIstanzaForestale = istanzaForestDAO.findNrIstanzaForestByIdIntervento(idIntervento);
//			String fileName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tipAll.getDescrTipoAllegato());
			String fileName = tipAll.getDescrTipoAllegato();

			ResponseBuilder response = null;
//			response = Response.ok(file1).header("Content-disposition", "attachment; filename=" + file1.getName());
			response = Response.ok(report)
					.header("Content-disposition", "attachment; filename=" + fileName + " - nr. "+ nrIstanzaForestale +".pdf");
			return response.build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response updateUpload(FatDocumentoAllegato documentoAllegato, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		try {
			documentoAllegatoDAO.uploadPfaDocumentoWithStringUid(documentoAllegato);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getDocumentiByIdIntervento(Integer idIntervento, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		try {
			return Response.ok(documentoAllegatoDAO.findFatDocumentiByIdIntervento(idIntervento)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}


	@Override
	public Response uploadDocumentoTagli(int idIntervento, int tipoDocumento, FileUploadForm form,
										 SecurityContext securityContext,
										 HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			IndexServiceHelper indexSrvHelper = (IndexServiceHelper) applicationContext.getBean("indexServiceHelper");
			boolean isSigned = !SignatureTypeEnum.UNSIGNED.toString().equals(indexSrvHelper.getSignatureType(form.getData()));
			String errore = null;
			if(isDocDigitale(tipoDocumento) && !isSigned) {
				errore = "Documento con firma digitale non valida";
			}else if( !isDocDigitale(tipoDocumento) && isSigned) {
				errore = "Documento non valido: e' presente una firma digitale, che non e' prevista per questa tipologia di documento";
			}
			if(errore != null) {
				Map<String,String> res = new HashMap<String,String>();
				res.put("error",errore);
				return Response.ok(res).build();
			}
			ConfigUtente loggedConfig = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			FatDocumentoAllegato docWithId = documentoAllegatoDAO.uploadDocumentoTagli(idIntervento, tipoDocumento, form,
					loggedConfig);
			if (docWithId != null) {
				return Response.ok(docWithId).build();
			} else {
				return Response.serverError().build();
			}
		}catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
}
