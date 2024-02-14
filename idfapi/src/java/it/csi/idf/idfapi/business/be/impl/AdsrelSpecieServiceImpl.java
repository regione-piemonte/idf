/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.AdsrelSpecieApi;
import it.csi.idf.idfapi.business.be.impl.dao.AdsrelSpecieDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperAdsDAO;
import it.csi.idf.idfapi.dto.RAdsrelSpecie;
import it.csi.idf.idfapi.rest.BaseResponses;
import it.csi.idf.idfapi.util.SpringSupportedResource;
import it.csi.idf.idfapi.validation.StepValidationErrors;
import it.csi.idf.idfapi.validation.StepsValidationException;

public class AdsrelSpecieServiceImpl extends SpringSupportedResource implements AdsrelSpecieApi {

	static final Logger logger = Logger.getLogger(AdsrelSpecieServiceImpl.class);

	@Autowired
	AdsrelSpecieDAO adsrelSpecieDao;

	@Autowired
	WrapperAdsDAO wrapperAdsDAO;

	@Override
	public Response getAllSpecie(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			List<RAdsrelSpecie> specie = adsrelSpecieDao.search();
			return Response.ok(specie).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.serverError().build();
		}
	}

	@Override
	public Response insertAlberiCAMDOM(List<RAdsrelSpecie> radsrelSpecie, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			List<StepValidationErrors> errors =wrapperAdsDAO.insertAllSpecieDomeCAM(radsrelSpecie);
			return Response.ok(errors).build();
		} catch (Exception e) {
			logger.info(e.getMessage());
			return Response.serverError().build();
		}
	}

	@Override
	public Response updateAlberiCAMeDOM(List<RAdsrelSpecie> radsrelSpecieList, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			List<StepValidationErrors> errors = wrapperAdsDAO.updateAllSpecieDomeCAM(radsrelSpecieList);
			return Response.ok(errors).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response saveAlberiCAV(List<RAdsrelSpecie> alberiCAVList, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		try {

			wrapperAdsDAO.saveAdsrelSpecieCAV(alberiCAVList);
			return Response.ok().build();

		} catch (Exception e) {
			e.printStackTrace();
			if (e.getCause() != null) {
				logger.error(e.getCause().getMessage());
			}
			return Response.serverError().build();
		}

	}

	@Override
	public Response saveAlberiCAVConsolida(List<RAdsrelSpecie> alberiCAVList, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		try { 
			wrapperAdsDAO.consolidaAdsrelSpecieCAV(alberiCAVList);

			return BaseResponses.successResponse("Consolida succesfull");

		}catch(StepsValidationException sve) {
			logger.debug("Error: StepsValidationException");
			return BaseResponses.errorResponse(sve);
		}catch (Exception e) {
			e.printStackTrace();
			if (e.getCause() != null) {
				logger.error(e.getCause().getMessage());
			}
			return Response.serverError().build();
		}

	}
	
	@Override
	public Response getAllStepErrors(Long idgeoPtAds, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			logger.info("idgeoPtAds: " + idgeoPtAds);
			wrapperAdsDAO.validateForConsolidaSchedaState(idgeoPtAds);
		}catch(StepsValidationException sve) {
			return Response.ok(sve.getStepsErrors()).build();
		}
		return Response.ok().build();
	}

	@Override
	public Response getAllSpecieByIdgeoPtAds(Long idgeoPtAds, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			List<RAdsrelSpecie> specie = adsrelSpecieDao.searchByIdgeoPtAds(idgeoPtAds);
			return Response.ok(specie).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.serverError().build();
		}
	}

	public Response getAllSpecieCAVByCodiceADS(@PathParam("codiceAds") String codiceAds,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders,
			@Context HttpServletRequest httpRequest) {

		try {
			List<RAdsrelSpecie> specie = adsrelSpecieDao.searchAdsRelSpecByCodiceADSForCAV(codiceAds);
			return Response.ok(specie).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.serverError().build();
		}

	}

}
