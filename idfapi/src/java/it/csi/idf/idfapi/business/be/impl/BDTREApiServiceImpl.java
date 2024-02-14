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

import it.csi.idf.idfapi.business.be.BDTREApi;
import it.csi.idf.idfapi.business.be.SigmaterApi;
import it.csi.idf.idfapi.business.be.impl.dao.ComuneDAO;
import it.csi.idf.idfapi.business.be.service.SIGMATER;
import it.csi.idf.idfapi.dto.PlainParticelleCatastali;
import it.csi.idf.idfapi.dto.SezioneResource;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class BDTREApiServiceImpl extends SpringSupportedResource implements BDTREApi {
	
	@Autowired
	private SIGMATER sigmater;
	
	@Autowired
	private ComuneDAO comuneDao;

	@Override
	public Response getSezioneCatastale(String istatComune,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
//		try {
//			SezioneResource sezioniCatastali = new SezioneResource();
//			
//			PlainParticelleCatastali particelleCatastali = new PlainParticelleCatastali();
//			
//			particelleCatastali.setComune(comuneDao.findComuneResourceByIstatComune(istatComune));
//			particelleCatastali.setSezione(sezione);
//			particelleCatastali.setFoglio(foglio);
//			particelleCatastali.setParticella(particella);
//			
//			return Response.ok(sigmater.getParticelleCatastali(particelleCatastali)).build();
//		} catch (Exception e) {
//			return Response.serverError().build();
//		}
	return null;
	}
}
