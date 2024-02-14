/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.TipoStrutturaleApi;
import it.csi.idf.idfapi.business.be.impl.dao.TipoStrutturaleDAO;
import it.csi.idf.idfapi.dto.TipoStrutturale;
import it.csi.idf.idfapi.util.IplaEnum;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class TipoStrutturaleApiServiceImpl extends SpringSupportedResource implements TipoStrutturaleApi {
	
	@Autowired
	TipoStrutturaleDAO tipoStrutturaleDAO;

	@Override
	public Response getAllTipoStrutturale(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		List<TipoStrutturale> tipoStrutturale = tipoStrutturaleDAO.findAllTipoStrutturale();
		return Response.ok(tipoStrutturale).build();
	}
	
	@Override
	public Response getAllTipoStrutturalePfa(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		List<TipoStrutturale> tipoStrutturale = tipoStrutturaleDAO.findAllTipoStrutturaleByIpla(IplaEnum.PFA);
		return Response.ok(tipoStrutturale).build();
	}
	
	@Override
	public Response getAllTipoStrutturaleInventari(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		List<TipoStrutturale> tipoStrutturale = tipoStrutturaleDAO.findAllTipoStrutturaleByIpla(IplaEnum.INVENTARI);
		return Response.ok(tipoStrutturale).build();
	}
}
