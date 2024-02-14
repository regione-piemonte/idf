/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;


import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.exceptions.dao.ApiManagerDaoException;
import it.csi.idf.idfapi.business.be.impl.dao.ApiManagerDao;
import it.csi.idf.idfapi.business.be.impl.dao.ComuneDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SezioneDAO;
import it.csi.idf.idfapi.business.be.service.helper.ApiManagerServiceHelper;
import it.csi.idf.idfapi.dto.ApiManagerDto;
import it.csi.idf.idfapi.dto.Comune;
import it.csi.idf.idfapi.dto.ComuneResource;
import it.csi.idf.idfapi.dto.SezioneResource;
import it.csi.idf.idfapi.mapper.ComuneMapper;
import it.csi.idf.idfapi.mapper.ComuneResourceMapper;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.ListUtil;
import it.csi.idf.idfapi.util.oauth2.InternalOauthHelper;

import org.apache.log4j.Logger;
import it.csi.idf.idfapi.business.be.service.helper.BDTREServiceHelper;
@Component
public class SezioneDAOImpl implements SezioneDAO {
	
	@Autowired
	private ApiManagerDao apiManagerDao;
	
	@Autowired
	private BDTREServiceHelper bdtreServiceHelper;
	
	public static Logger logger = Logger.getLogger(SezioneDAOImpl.class);
	
	private final ComuneMapper comuneMapper = new ComuneMapper();
	private final ComuneResourceMapper comuneResourceMapper = new ComuneResourceMapper();
	private static final String ORDER_BY_DENOMINAZIONE_COMUNE = " ORDER BY denominazione_comune";
	private RestTemplate restTemplate = new RestTemplate();

	@Override
	public String findAllSezioneByComune(String municipality_code) throws JsonParseException, JsonMappingException, IOException {
		logger.info("MUNICIPALITY: " + municipality_code);
		String endpoint= this.bdtreServiceHelper.getUrlBDTRE()
				+ "giscataapi/v1/collections/municipalities/";
		logger.info("<<<<<<<<<-------------ENDPOINT ------------------>>>>>>>>>" + endpoint);

		String url_parametrico= endpoint + municipality_code + "/"+"sections"; 
		//String json = restTemplate.getForObject(url_parametrico, String.class);
		HttpHeaders headers = new HttpHeaders();
		ApiManagerDto apiManager= new ApiManagerDto();
		try {
			apiManager = apiManagerDao.findParametriInternetValidi();
		} catch (ApiManagerDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String urlToken = this.bdtreServiceHelper.getTokenUrlBDTRE();
		InternalOauthHelper oauthHelper = new InternalOauthHelper(urlToken,
				apiManager.getConsumerKey(), apiManager.getConsumerSecret());
		String token = oauthHelper.getToken();
		logger.info("SONO TOKEN BARER "+ "");
		headers.set("Authorization", "Bearer "+ token );
		HttpEntity<String> entity = new HttpEntity<>(headers);
		String json = restTemplate.exchange(url_parametrico,HttpMethod.GET, entity, String.class).getBody();
//		ResponseEntity<Object>json = restTemplate.exchange(
//				url_parametrico, HttpMethod.GET, entity, Object.class);
		//ObjectMapper objectMapper = new ObjectMapper();
		//String jsonString = objectMapper.writeValueAsString(json.getBody());
		
		
		
		logger.info("CHIAMATA URL : " + url_parametrico);
		//logger.info("chiamata_header: " + json);
		
		//ObjectMapper mapper = new ObjectMapper();
		//SezioneResource sezione = mapper.readValue(json,  SezioneResource.class);
		//logger.info(("SEZIONE " + sezione));
		
		return json;
	}
}
