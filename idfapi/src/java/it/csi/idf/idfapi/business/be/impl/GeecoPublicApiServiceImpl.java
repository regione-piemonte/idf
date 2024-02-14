/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.GeecoPublicApi;
import it.csi.idf.idfapi.business.be.impl.dao.UserSessionDAO;
import it.csi.idf.idfapi.business.be.service.helper.ApiManagerServiceHelper;
import it.csi.idf.idfapi.business.be.service.helper.GeecoServiceHelper;
import it.csi.idf.idfapi.dto.UserInfo;
import it.csi.idf.idfapi.util.SpringSupportedResource;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class GeecoPublicApiServiceImpl extends SpringSupportedResource implements GeecoPublicApi {
	
	@Autowired
	public GeecoServiceHelper geecoServiceHelper;
	@Autowired
	private ApiManagerServiceHelper apiManagerServiceHelper;
	@Autowired
	private UserSessionDAO userSessionDAO;

	@Override
	public Response getGeecoConfiguration(String idProfiloGeeco, String idIdfObject, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		logger.debug("[GeecoApiServiceImpl]::getGeecoConfiguration BEGIN ");
		logger.info("idProfiloGeeco con singolo ID: " + idProfiloGeeco + " -  idIdfObject: " + idIdfObject);
		try {
			String[] ids = null;
			if(StringUtils.isNotEmpty(idIdfObject)) {
				ids = new String[] {idIdfObject};
			}
			return this.getGeecoConfiguration(idProfiloGeeco, ids, securityContext, httpHeaders, httpRequest);			
		} 
		finally {
			logger.debug("[GeecoApiServiceImpl]::getGeecoConfiguration END ");
		}
	}

	@Override
	public Response postGeecoConfiguration(String idProfiloGeeco, String idIdfObject, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return getGeecoConfiguration(idProfiloGeeco, idIdfObject, securityContext,httpHeaders,  httpRequest);
	}

	@Override
	public Response postGeecoConfiguration(String idProfiloGeeco, String[] idIdfObjects, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		return getGeecoConfiguration(idProfiloGeeco, idIdfObjects, securityContext,httpHeaders,  httpRequest);
	}

	@Override
	public Response getGeecoConfiguration(String idProfiloGeeco, String[] idIdfObjects, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		Response response = null;
		logger.debug("[GeecoApiServiceImpl]::getGeecoConfiguration BEGIN ");
		logger.info("idProfiloGeeco: " + idProfiloGeeco + " -  idIdfObjects: " + idIdfObjects);
//		for (String id : idIdfObjects) {
//			
//			
//		}
		System.out.println("userInfo........................................: " );
		
		
		try {
			// Nel caso di accesso libero viene INTENZIONALMENTE passato NULL come valore del parametro PROVIDER
			String body = geecoServiceHelper.getGeecoConfiguration(idProfiloGeeco, idIdfObjects, null);
			logger.info("Body: " + body);
			RequestBody rb = RequestBody.create(body, okhttp3.MediaType.parse("application/json") );
			Request request = new okhttp3.Request.Builder()
				      .url(geecoServiceHelper.getUrlApi() + "/prod/configuration" )
				      .addHeader("Authorization", "Bearer " + apiManagerServiceHelper.getApiManagerConfig().getOauthHelper().getToken())
				      .post(rb)
				      .build();
			
			
			okhttp3.Response apiResponse = new OkHttpClient().newCall(request).execute();
						
			String urlGeecoResult = apiResponse.body().string(); 
			
			response = Response.ok().entity(urlGeecoResult).build();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			response = Response.serverError().entity("GeecoApiServiceImpl:getGeecoConfiguration - Errore nella generazione del JSON per geeco "+e.getMessage()).build();
		}
		finally {
			logger.debug("[GeecoApiServiceImpl]::getGeecoConfiguration END ");
		}
		
		return response;
	}

	@Override
	public Response postGeecoConfiguration(String idProfiloGeeco, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		logger.debug("[GeecoApiServiceImpl]::getGeecoConfiguration BEGIN ");
		logger.info("idProfiloGeeco: " + idProfiloGeeco);
		try {
			return this.getGeecoConfiguration(idProfiloGeeco, securityContext, httpHeaders, httpRequest);			
		} 
		finally {
			logger.debug("[GeecoApiServiceImpl]::getGeecoConfiguration END ");
		}
	}

	@Override
	public Response getGeecoConfiguration(String idProfiloGeeco, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		logger.debug("[GeecoApiServiceImpl]::getGeecoConfiguration BEGIN ");
		logger.info("idProfiloGeeco: " + idProfiloGeeco);
		try {
			return this.getGeecoConfiguration(idProfiloGeeco, ArrayUtils.EMPTY_STRING_ARRAY, securityContext, httpHeaders, httpRequest);			
		} 
		finally {
			logger.debug("[GeecoApiServiceImpl]::getGeecoConfiguration END ");
		}
	}
	

}
