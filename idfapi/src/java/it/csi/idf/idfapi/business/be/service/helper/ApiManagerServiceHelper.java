/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.service.helper;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.exceptions.dao.DaoException;
import it.csi.idf.idfapi.business.be.impl.dao.ApiManagerDao;
import it.csi.idf.idfapi.business.be.service.helper.dto.InfoApiManager;
import it.csi.idf.idfapi.dto.ApiManagerDto;
import it.csi.idf.idfapi.util.StringUtils;
import it.csi.wso2.apiman.oauth2.helper.OauthHelper;
import it.csi.wso2.apiman.oauth2.helper.TokenRetryManager;
import it.csi.wso2.apiman.oauth2.helper.WsProvider;
import it.csi.wso2.apiman.oauth2.helper.extra.axis1.Axis1Impl;
import it.csi.wso2.apiman.oauth2.helper.extra.cxf.CxfImpl;


public class ApiManagerServiceHelper extends AbstractServiceHelper{

	
	private OauthHelper oauthHelper;
	private String idfTokenUrl;
	
	public static final String X_AUTH = "X-Authorization";
	
	public ApiManagerServiceHelper(String idfTokenUrl){
		this.idfTokenUrl = idfTokenUrl;
	}
	
	@Autowired
	private ApiManagerDao apiManagerDao;
	
	

	/**
	 * @return the apiManagerDao
	 */
	public ApiManagerDao getApiManagerDao() {
		return apiManagerDao;
	}

	/**
	 * @param apiManagerDao the apiManagerDao to set
	 */
	public void setApiManagerDao(ApiManagerDao apiManagerDao) {
		this.apiManagerDao = apiManagerDao;
	}

	public final OauthHelper getOauthHelper(ApiManagerDto dto) throws IOException{
		LOGGER.debug("[ApiManagerServiceHelper::getOauthHelper] BEGIN");
	    	
		if (this.oauthHelper == null) {
			this.oauthHelper = new OauthHelper(
					this.idfTokenUrl,
					//"https://tst-api-ent.ecosis.csi.it/api/token",
					dto.getConsumerKey(),
					//"_kPhXq8tFmuNWn0NAc2kXFHZXr4a",
					dto.getConsumerSecret(), 10000);
					//"3LOJ57emkDnlnWm9kbfiBct1yQUa", 10000);
		}
		
		LOGGER.debug("[ApiManagerServiceHelper::getOauthHelper] END");
		return this.oauthHelper;
		

	}
	
	public TokenRetryManager getTokenRetryManagerCxf(String credenziali) throws IOException {
		LOGGER.debug("[ApiManagerServiceHelper::getTokenRetryManagerCxf] BEGIN");
		final TokenRetryManager trm = new TokenRetryManager();
		if(StringUtils.isNotEmpty(credenziali)) {
			trm.putHeader(X_AUTH, "Basic " + this.getJaasAutentication(credenziali));
		}
		trm.setOauthHelper(this.getApiManagerConfig().getOauthHelper());
		
		WsProvider wsp = new CxfImpl();
		
		
		trm.setWsProvider(wsp);
		LOGGER.debug("[ApiManagerServiceHelper::getTokenRetryManagerCxf] END");
		return trm;
	}
	
	public TokenRetryManager getTokenRetryManagerCxf() throws IOException {
		return this.getTokenRetryManagerCxf(null);
	}
	
	public TokenRetryManager getTokenRetryManagerAxis1()throws IOException{
		return this.getTokenRetryManagerAxis1(null);
	}
	
	public TokenRetryManager getTokenRetryManagerAxis1(String credenziali)throws IOException{
		LOGGER.debug("[ApiManagerServiceHelper::getTokenRetryManagerAxis1] BEGIN");
		final TokenRetryManager trm = new TokenRetryManager();
		trm.setOauthHelper(this.getApiManagerConfig().getOauthHelper());
		if(StringUtils.isNotEmpty(credenziali)) {
			trm.putHeader(X_AUTH, "Basic " + this.getJaasAutentication(credenziali));
		}
		WsProvider wsp = new Axis1Impl();
		
		trm.setWsProvider(wsp);
		LOGGER.debug("[ApiManagerServiceHelper::getTokenRetryManagerAxis1] END");
		return trm;
	}
	
	private String getJaasAutentication(String credenziali) {
		// Autenticazione con JAAS
		String encoded = new String(Base64.encodeBase64(credenziali.getBytes()));
		
		return encoded;
	}
	
	public final InfoApiManager getApiManagerConfig() throws IOException{
		LOGGER.debug("[ApiManagerServiceHelper::getApiManagerConfig] BEGIN");
		
		InfoApiManager result = new InfoApiManager();
	
		result.setApiManagerDto(this.getApiManagerInfo());
		result.setOauthHelper(this.getOauthHelper(result.getApiManagerDto()));
		
		LOGGER.debug("[ApiManagerServiceHelper::getApiManagerConfig] END");
		return result;
	}

	private ApiManagerDto getApiManagerInfo(){
		LOGGER.debug("[ApiManagerServiceHelper::getApiManagerInfo] BEGIN");
		ApiManagerDto result = null;
		try {
			if(apiManagerDao!=null) {
				result = apiManagerDao.findParametriValidi();
			}
			else {
				// SOLO PER CASI DI TEST IN LOCALE
				result = this.getLocalApiManagerDto();
			}
		} catch (DaoException e) {
			// Do Nothing
			e.printStackTrace();
		}
		result.setUrl(this.idfTokenUrl);
		LOGGER.debug("[ApiManagerServiceHelper::getApiManagerInfo] END");
		return result;
	}

	private ApiManagerDto getLocalApiManagerDto() {
		ApiManagerDto result = new ApiManagerDto();
		result.setConsumerKey("_kPhXq8tFmuNWn0NAc2kXFHZXr4a");
		result.setConsumerSecret("3LOJ57emkDnlnWm9kbfiBct1yQUa");
		return result;
	}

}
