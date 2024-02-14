/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import it.csi.idf.idfapi.business.be.DettaglioInterventiApi;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperInterventoDAO;
import it.csi.idf.idfapi.errors.Error;
import it.csi.idf.idfapi.errors.ErrorConstants;

import it.csi.idf.idfapi.util.SpringSupportedResource;

public class DettaglioInterventiApiServiceImpl extends SpringSupportedResource implements DettaglioInterventiApi {

	@Autowired
	private WrapperInterventoDAO wrapperInterventoDAO;

	@Override
	public Response getTipoInterventoDetails(Integer idIntervento, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperInterventoDAO.getTipoInterventoDettaglioByIdIntervento(idIntervento)).build();

		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.serverError().entity(ex).build();
		}

	}

	@Override
	public Response getParticelleDetails(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperInterventoDAO.getParticelleInterventoDettaglioByIdIntervento(idIntervento))
					.build();

		} catch (EmptyResultDataAccessException e) {
			Error err = new Error();
			err.setCode(ErrorConstants.NON_TROVATO_404);
			err.setErrorMessage(ErrorConstants.NON_TROVATO + idIntervento);
			return Response.serverError().status(ErrorConstants.NON_TROVATO_404).entity(err).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.serverError().entity(ex).build();
		}
	}

	@Override
	public Response getSpeciesDetails(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperInterventoDAO.getSpecieInterventoDettaglioByIdIntervento(idIntervento)).build();

		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			Error err = new Error();
			err.setCode(ErrorConstants.NON_TROVATO_404);
			err.setErrorMessage(ErrorConstants.NON_TROVATO + idIntervento);
			return Response.serverError().status(ErrorConstants.NON_TROVATO_404).entity(err).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.serverError().entity(ex).build();
		}
	}

	@Override
	public Response getUtilizzatoreDetails(Integer idIntervento, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		try {
			return Response.ok(wrapperInterventoDAO.getUtilizzatoreForIntervento(idIntervento)).build();

		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.serverError().entity(ex).build();
		}
	}

}
