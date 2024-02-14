/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.ConfigurationApi;
import it.csi.idf.idfapi.business.be.impl.dao.ConfigurationDAO;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class ConfigurationApiServiceImpl extends SpringSupportedResource implements ConfigurationApi {

	static final Logger logger = Logger.getLogger(ConfigurationApiServiceImpl.class);
	
	@Autowired
	private ConfigurationDAO configurationDAO;

	@Override
	public Response getConfigurationByName(String name, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		Map<String,String> mapConf = new HashMap<String,String>();
		mapConf.put("value",configurationDAO.getConfigurationByName(name));
		return Response.ok(mapConf).build();
	}

}
