/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;



import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
import it.csi.idf.idfapi.business.be.impl.dao.FoglioDAO;
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
public class FoglioDAOImpl implements FoglioDAO {
	
	@Autowired
	private ApiManagerDao apiManagerDao;

	@Autowired
	private BDTREServiceHelper bdtreServiceHelper;
	
	public static Logger logger = Logger.getLogger(FoglioDAOImpl.class);
	
	private final ComuneMapper comuneMapper = new ComuneMapper();
	private final ComuneResourceMapper comuneResourceMapper = new ComuneResourceMapper();
	private static final String ORDER_BY_DENOMINAZIONE_COMUNE = " ORDER BY denominazione_comune";
	private RestTemplate restTemplate = new RestTemplate();

	@Override
	public String findAllFoglioBySezione(String municipality_code,String section_code) throws JsonParseException, JsonMappingException, IOException {
		logger.info("MUNICIPALITY: " + municipality_code);
		String url_parametrico= this.bdtreServiceHelper.getUrlBDTRE() + "giscataapi/v1/collections/municipalities/" + municipality_code + "/"+"sections/"+ section_code + "/sheets?limit=100&omit_geometry=true";
		//String json = restTemplate.getForObject(url_parametrico, String.class);
		HttpHeaders headers = new HttpHeaders();
		ApiManagerDto apiManager= new ApiManagerDto();
		try {
			apiManager = apiManagerDao.findParametriInternetValidi();
		} catch (ApiManagerDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String urlToken=this.bdtreServiceHelper.getTokenUrlBDTRE();
		InternalOauthHelper oauthHelper = new InternalOauthHelper(urlToken,
				apiManager.getConsumerKey(), apiManager.getConsumerSecret());
		String token = oauthHelper.getToken();
		logger.info("SONO TOKEN BARER "+ token);
		headers.set("Authorization", "Bearer "+ token );
		HttpEntity<String> entity = new HttpEntity<>(headers);

		ResponseEntity<String> result = restTemplate.exchange(url_parametrico,HttpMethod.GET, entity, String.class);
		String limit = result.getHeaders().getFirst("X-Total-Elements");
		String pages = result.getHeaders().getFirst("X-Total-Pages");


		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode content = objectMapper.readTree(result.getBody());
		ArrayList<JsonNode> features = new ArrayList<>();
		content = content.get("features");
		for(JsonNode elemento : content){
			features.add(elemento);
		};

		if(Integer.parseInt(limit) > 100){
			int offset = 100;
			for (int i = 1; i < Integer.parseInt(pages); i++) {
				url_parametrico= this.bdtreServiceHelper.getUrlBDTRE() + "giscataapi/v1/collections/municipalities/" + municipality_code + "/"+"sections/"+ section_code + "/sheets?limit="+limit+"&offset="+offset+"&omit_geometry=true";
				JsonNode json = objectMapper.readTree(restTemplate.exchange(url_parametrico,HttpMethod.GET, entity, String.class).getBody());
				json = json.get("features");
				for(JsonNode elemento : json){
					features.add(elemento);
				};
				offset = offset + 100;
				//logger.info(offset + limit + url_parametrico);
			}
		} else {
			return result.getBody();
		}
		ObjectNode finalContent = objectMapper.createObjectNode();
		finalContent.putPOJO("bbox", new ArrayList<>());
		finalContent.putPOJO("features", features);
		finalContent.put("type","FeatureCollection");

		return finalContent.toString();
	}
}
