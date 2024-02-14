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

import it.csi.idf.idfapi.business.be.TipoForestaleApi;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.impl.dao.TipoForestaleDAO;
import it.csi.idf.idfapi.dto.TipoForestale;
import it.csi.idf.idfapi.errors.Error;
import it.csi.idf.idfapi.errors.ErrorConstants;
import it.csi.idf.idfapi.util.IplaEnum;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class TipoForestaleApiServiceImpl extends SpringSupportedResource implements TipoForestaleApi {
	@Autowired
	TipoForestaleDAO tipoForestaleDAO;
	
	@Override
	public Response getAllTipoForestale(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		List<TipoForestale> tipoForestale = tipoForestaleDAO.findAllTipoForestale();
		return Response.ok(tipoForestale).build();
	}
	
	@Override
	public Response getAllTipoForestalePfa(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		List<TipoForestale> tipoForestale = tipoForestaleDAO.findAllTipoForestaleByIpla(IplaEnum.PFA);
		return Response.ok(tipoForestale).build();
	}
	
	@Override
	public Response getAllTipoForestaleInventari(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		List<TipoForestale> tipoForestale = tipoForestaleDAO.findAllTipoForestaleByIpla(IplaEnum.INVENTARI);
		return Response.ok(tipoForestale).build();
	}
	
	@Override
	public Response getTipoByCategoriaPfa(Integer categoriaForestale, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			List<TipoForestale> tipoForestale = tipoForestaleDAO.findAllTipoByCategoriaAndIpla(categoriaForestale, IplaEnum.PFA);
			
			return Response.ok(tipoForestale).build();
			} catch (RecordNotFoundException e) {
				Error err = new Error();
				err.setCode(ErrorConstants.NON_TROVATO_404);
				err.setErrorMessage(ErrorConstants.NON_TROVATO + categoriaForestale);
				return Response.serverError().status(ErrorConstants.NON_TROVATO_404).entity(err).build();
			}
	}
	
	@Override
	public Response getTipoByCategoriaInventari(Integer categoriaForestale, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			List<TipoForestale> tipoForestale = tipoForestaleDAO.findAllTipoByCategoriaAndIpla(categoriaForestale, IplaEnum.INVENTARI);
			
			return Response.ok(tipoForestale).build();
			} catch (RecordNotFoundException e) {
				Error err = new Error();
				err.setCode(ErrorConstants.NON_TROVATO_404);
				err.setErrorMessage(ErrorConstants.NON_TROVATO + categoriaForestale);
				return Response.serverError().status(ErrorConstants.NON_TROVATO_404).entity(err).build();
			}
	}
	
	@Override
	public Response getTipoByCategoria(Integer categoriaForestale, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			List<TipoForestale> tipoForestale = tipoForestaleDAO.findAllTipoByCategoria(categoriaForestale);
			
			return Response.ok(tipoForestale).build();
			} catch (RecordNotFoundException e) {
				Error err = new Error();
				err.setCode(ErrorConstants.NON_TROVATO_404);
				err.setErrorMessage(ErrorConstants.NON_TROVATO + categoriaForestale);
				return Response.serverError().status(ErrorConstants.NON_TROVATO_404).entity(err).build();
			}
	}

}
