/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.SpecieApi;
import it.csi.idf.idfapi.business.be.impl.dao.SpecieDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SpeciePfaInterventoDAO;
import it.csi.idf.idfapi.dto.GruppoSpecie;
import it.csi.idf.idfapi.dto.SpeciePfaIntervento;
import it.csi.idf.idfapi.dto.TSpecie;
import it.csi.idf.idfapi.util.IplaEnum;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class SpecieApiServiceImpl extends SpringSupportedResource implements SpecieApi {

	@Autowired
	SpecieDAO specieDao;

	@Autowired
	SpeciePfaInterventoDAO speciePfaInterventoDao;

	private static Logger logger = Logger.getLogger(SpecieApiServiceImpl.class);

	public SpecieDAO getSpecieDao() {
		return specieDao;
	}

	@Override
	public Response getAllSpecie(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		List<TSpecie> specie = specieDao.findAllSpecie();
		return Response.ok(specie).build();
	}
	
	@Override
	public Response getAllSpecieInventari(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		List<TSpecie> specie = specieDao.findAllSpecieByIpla(IplaEnum.INVENTARI);
		return Response.ok(specie).build();
	}
	
	@Override
	public Response getAllSpeciePfa(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		List<TSpecie> specie = specieDao.findAllSpecieByIpla(IplaEnum.PFA);
		return Response.ok(specie).build();
	}

	@Override
	public Response saveSpeciePfaIntervento(SpeciePfaIntervento specieInterv, Integer idIntervento,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		return Response.ok(speciePfaInterventoDao.createSpeciePfaIntervento(specieInterv, idIntervento)).build();
	}

	@Override
	public Response deleteSpeciePfaIntervento(Integer idSpecie, Integer idIntervento, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		speciePfaInterventoDao.deleteSpeciePfaIntervento(idSpecie, idIntervento);

		return Response.ok().build();
	}

	public Response findAllGruppoSpecie(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) {

		try {

			List<GruppoSpecie> gruppoList = specieDao.findAllGruppoSpecie();
			return Response.ok(gruppoList).build();

		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return Response.serverError().build();
		}
	}

	@Override
	public Response calculateSpecieVolumeByIntervento(Integer idIntervento, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		try {
			return Response.ok(speciePfaInterventoDao.getSpeciaVolumeByInterventoId(idIntervento)).build();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return Response.serverError().build();

		}
	}

	@Override
	public Response updateSpeciePfaIntervento(Integer idIntervento, SpeciePfaIntervento specieInterv,SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			speciePfaInterventoDao.updateSpeciePfaIntervento(specieInterv,idIntervento);
			return Response.ok("Specie volume updated").build();
			
		}catch (Exception ex) {
			logger.error(ex.getMessage());
			return Response.serverError().build();

		}
		
		
	}

	@Override
	public Response batchSaveSpeciePfaIntervento(SpeciePfaIntervento[] specieIntervs, Integer idIntervento,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		
		try {
			speciePfaInterventoDao.batchCreate(specieIntervs,idIntervento);
			return Response.ok().build();
		}catch (Exception ex) {
			logger.error(ex.getMessage());
			return Response.serverError().build();

		}
	}

	@Override
	public Response getSpecieByIntervento(Integer idIntervento,SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		
		try {
			return Response.ok(speciePfaInterventoDao.getSpecieByInterventoId(idIntervento)).build();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return Response.serverError().build();

		}
	}
	
	

}
