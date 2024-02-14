/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl;

import it.csi.idf.idfapi.business.be.TipoViabilitaApi;
import it.csi.idf.idfapi.business.be.impl.dao.TipoViabilitaDAO;
import it.csi.idf.idfapi.dto.TipoViabilita;
import it.csi.idf.idfapi.util.SpringSupportedResource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

public class TipoViabilitaApiImpl extends SpringSupportedResource implements TipoViabilitaApi {

	@Autowired
	private TipoViabilitaDAO tipoViabilitaDAO;
	@Override
	public Response getAllTipoViabilita(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		List<TipoViabilita> list = tipoViabilitaDAO.findAll();
		return Response.ok(list).build();
	}

	@Override
	public Response getAllTipoViabilitaByIpla(Integer fkConfigIpla, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		List<TipoViabilita> list = tipoViabilitaDAO.findAllByConfigIpla(fkConfigIpla);
		return Response.ok(list).build();
	}
}
