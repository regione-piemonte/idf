/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import it.csi.idf.idfapi.business.be.TestApi;
import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.business.be.service.FileGenerator;
import it.csi.idf.idfapi.rest.BaseResponses;
import it.csi.idf.idfapi.util.SpringSupportedResource;
import it.csi.idf.idfapi.util.TipoAllegatoEnum;

public class TestApiServiceImpl extends SpringSupportedResource implements TestApi {
	
	@Autowired
	private FileGenerator fileGenerator;
	
	@Override
	public Response getSuccess(HttpServletRequest httpRequest) {
		try {
			int idIntervento = 1;
			return BaseResponses.successResponse(fileGenerator.generateDichiarazione(TipoAllegatoEnum.DICHIARAZIONE, idIntervento));
		} catch (Exception e) {
			e.printStackTrace();
			return BaseResponses.errorResponse(e);
		}
	}

	@Override
	public Response getValidationError() {
		try {
			throw new ValidationException();
		} catch (Exception e) {
			return BaseResponses.errorResponse(e);
		}
	}

	@Override
	public Response tryResources() {
		// TODO Auto-generated method stub
		
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			URL resource = classLoader.getResource("/");
			
			FileInputStream fis = new FileInputStream( new ClassPathResource("",this.getClass().getClassLoader()).getFile());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
