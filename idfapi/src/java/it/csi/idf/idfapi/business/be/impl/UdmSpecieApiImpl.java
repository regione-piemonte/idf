/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.UdmSpecieApi;
import it.csi.idf.idfapi.business.be.impl.dao.UdmSpecieDAO;
import it.csi.idf.idfapi.dto.UdmSpecie;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class UdmSpecieApiImpl extends SpringSupportedResource implements UdmSpecieApi{

	@Autowired
	UdmSpecieDAO udmSpecieDao;
	
	@Override
	public Response getAllUdm(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		
		List<UdmSpecie> udmSpecie = udmSpecieDao.findAllUdm();
		
		return Response.ok(udmSpecie).build();
	}

}
