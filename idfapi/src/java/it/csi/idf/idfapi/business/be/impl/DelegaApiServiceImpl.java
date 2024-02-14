/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl;

import java.io.InputStream;
import java.net.URLDecoder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.dto.*;
import it.csi.idf.idfapi.util.*;
import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.csi.idf.idfapi.business.be.DelegaApi;
import it.csi.idf.idfapi.business.be.exceptions.ServiceException;
import it.csi.idf.idfapi.business.be.impl.dao.DelegaDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SoggettoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.UserSessionDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperAdpforHomeDAO;
import it.csi.idf.idfapi.business.be.service.helper.IndexServiceHelper;
import it.csi.idf.idfapi.business.be.service.helper.dto.MetadatiIndexDelega;

public class DelegaApiServiceImpl extends SpringSupportedResource implements DelegaApi {
	public static final String LOGGED_CONFIG = "loggedConfig";

	@Autowired
	private DelegaDAO delegaDAO;
	
	@Autowired
	private WrapperAdpforHomeDAO wrapperAdpforHomeDAO;
	
	@Autowired
	private UserSessionDAO userSessionDAO;
	
	@Autowired
	private SoggettoDAO soggettoDAO;
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired
	IndexServiceHelper indexServiceHelper;

	@Override
	public Response getUtenteDelegati(String codiceFiscale, HttpServletRequest httpRequest) {
		try {
		
			boolean alreadyInDB = false;
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);

			List<Delega> delege = delegaDAO.getMieiDelegati(confUtente.getIdConfigUtente());
			for(Delega delega : delege) {
				if(codiceFiscale.equals(delega.getCfDelegante()) ) {
					alreadyInDB = true;
				}
			}

			if(!alreadyInDB && confUtente.getFkProfiloUtente() == ProfiloUtenteEnum.CITTADINO.getValue()) {
				throw new ValidationException("Questo codice non e specificato come delegato");
			} 
			FatSoggetto fatSoggetto = soggettoDAO.findFatSoggettoByCodiceFiscale(codiceFiscale);
			if(fatSoggetto == null) {
				fatSoggetto = new FatSoggetto();
				fatSoggetto.setCodiceFiscale(codiceFiscale);
			}
			return Response.ok(fatSoggetto).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getUtenteDelegatiCodici(HttpServletRequest httpRequest) {
		try {
			logger.info("<----- getUtenteDelegatiCodici INITIAL");
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			logger.info("<----- getUtenteDelegatiCodici: "+confUtente.toString());
			List<String> codici = new ArrayList<>();
			if(confUtente.getFkProfiloUtente() == ProfiloUtenteEnum.CITTADINO.getValue()) {
				List<Delega> deleghe = delegaDAO.getMieiDelegati(confUtente.getIdConfigUtente());
				for(Delega delega : deleghe) {
					codici.add(delega.getCfDelegante());
				}
			}else if(confUtente.getFkProfiloUtente() > ProfiloUtenteEnum.CITTADINO.getValue() 
					&& confUtente.getFkProfiloUtente() < ProfiloUtenteEnum.CONSULTAZIONE.getValue())
			{
				codici = soggettoDAO.getListCodicifiscaliCittadini();
			}else if(confUtente.getFkProfiloUtente() == ProfiloUtenteEnum.SPORTELLO.getValue()) //7777
			{
				List<Delega> deleghe = delegaDAO.getMieiDelegati(confUtente.getIdConfigUtente());
				for(Delega delega : deleghe) {
					codici.add(delega.getCfDelegante());
				}
			}
			return Response.ok(codici).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getMiaListaDeleghe(HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			return Response.ok(delegaDAO.getMieiDelegati(confUtente.getIdConfigUtente())).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response deleteDelega(String codiceFiscale, HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);			
			return Response.ok(delegaDAO.disabilita(codiceFiscale, confUtente.getIdConfigUtente())).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	public Response findDelegaByCodFiscale(String codiceFiscale, HttpServletRequest httpServletRequest) throws RecordNotUniqueException {
		ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpServletRequest, wrapperAdpforHomeDAO, userSessionDAO);
		return Response.ok(delegaDAO.getByCfDeleganteAndConfigUtente(codiceFiscale, confUtente.getIdConfigUtente())).build();

	}

	@Override
	public Response saveDelega(@MultipartForm MultipartFormDataInput data, 
			SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			Map<String, List<InputPart>> mapData = data.getFormDataMap();
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);

			InputPart inputPart = mapData.get("data").get(0);
			String specieObjAsString = URLDecoder.decode(inputPart.getBodyAsString(), "UTF-8");

			Map<String, Object> value = new ObjectMapper().readValue(specieObjAsString,
		        new TypeReference<HashMap<String, Object>>() {
		        });
			String cfDelegante = (String) value.get("cfDelegante");
			
			Delega delega = delegaDAO.getByCfDeleganteAndConfigUtente(cfDelegante, confUtente.getIdConfigUtente());
			if(delega != null && delega.getUidIndex() != null) {
				indexServiceHelper.indexDeleteFile(delega.getUidIndex());
			}
			
			FileUploadForm fileUpload = new FileUploadForm();
			inputPart = mapData.get("delegaFirmata").get(0);
			InputStream is = inputPart.getBody(InputStream.class, null);
			fileUpload.setData(IOUtils.toByteArray(is));
			String fileExtension = InputPartUtils.getFileExtension(InputPartUtils.getFileNameWithExtension(inputPart));
			fileUpload.setName("delega_" + cfDelegante.toUpperCase() + fileExtension );

			String errore = null;
			if(".p7m".equalsIgnoreCase(fileExtension)) {
				boolean isSigned = !SignatureTypeEnum.UNSIGNED.toString().equals(indexServiceHelper.getSignatureType(fileUpload.getData()));
				if(!isSigned) {
					errore = "Documento con firma digitale non valida";	
				}
			}
			if(errore != null) {
				Map<String,String> res = new HashMap<String,String>();
				res.put("error",errore);
				return Response.ok(res).build();
			}
			List<Delega> delegaList = delegaDAO.getAllDelegaByIdConfigUtente(confUtente.getIdConfigUtente());
			//save to index
			if(delegaList.isEmpty()){
				MetadatiIndexDelega metadatiIndexDelega = new MetadatiIndexDelega();
				metadatiIndexDelega.setIdConfigUtente(String.valueOf(confUtente.getIdConfigUtente()));
				indexServiceHelper.createFolder(metadatiIndexDelega);
			}
			Delega newDelega =  saveDocumento(fileUpload, cfDelegante, confUtente.getIdConfigUtente());
			
			LocalDate date = DateUtil.parseLocalDate((String)value.get("dataFine"), "yyyy-MM-dd");
			newDelega.setDataFine(date);
			int res = 0;
			if(delega == null) {
				res = delegaDAO.createDelega(newDelega);
			}else {
				res = delegaDAO.riabilita(newDelega);
			}

			return Response.ok(res).build();
			
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	private Delega saveDocumento(FileUploadForm fileUpload, String cfDelegante, int idConfUtente) throws Exception {

			MetadatiIndexDelega metadatiIndexDelega = new MetadatiIndexDelega();
			metadatiIndexDelega.setIdConfigUtente(String.valueOf(idConfUtente));
			logger.info("MetadatiIndexDelega -> idConfUtente: " + idConfUtente );
			String fileUID = indexServiceHelper.indexUploadFile(fileUpload.getName(), fileUpload.getData(), metadatiIndexDelega);
			logger.info("fileUID: " + fileUID);
			return new Delega(cfDelegante, idConfUtente, fileUpload.getName(), fileUID);
	}

	@Override
	public Response downloadFileDelega(String uuid, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServiceException {
		try {
			
			byte[] contDownloaded = indexServiceHelper.indexDownloadFileByUuid(uuid);
			
			Delega delega = delegaDAO.getByUidIndex(uuid);

			return Response.ok(contDownloaded).header("Content-disposition", "attachment; filename=" + delega.getFileName()).build();
			
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	
}
