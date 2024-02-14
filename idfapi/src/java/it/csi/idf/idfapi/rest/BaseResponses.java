/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.rest;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import it.csi.idf.idfapi.business.be.exceptions.HttpException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.errors.Error;
import it.csi.idf.idfapi.errors.ErrorConstants;
import it.csi.idf.idfapi.util.CodiceEnum;
import it.csi.idf.idfapi.util.ErrorEnum;
import it.csi.idf.idfapi.util.TipologiaResponseEnum;
import it.csi.idf.idfapi.validation.BaseException;
import it.csi.idf.idfapi.validation.StepsValidationException;

public class BaseResponses {
	
	private BaseResponses() {}
	
	@SuppressWarnings("unchecked")
	public static Response successResponse(Object payload) {
		@SuppressWarnings("rawtypes")
		BaseRestResponse response = new BaseRestResponse();
		
		response.setCodice(CodiceEnum.S00);
		response.setTesto(TipologiaResponseEnum.SUCCESS.toString());
		response.setTipologia(TipologiaResponseEnum.SUCCESS);
		response.setPayload(payload);

		return Response.ok().entity(response).status(Status.OK).build();
	}
	
	@SuppressWarnings("unchecked")
	public static Response successResponse(List<Object> payload) {
		@SuppressWarnings("rawtypes")
		BaseRestResponse response = new BaseRestResponse();
		
		response.setCodice(CodiceEnum.S00);
		response.setTesto(TipologiaResponseEnum.SUCCESS.toString());
		response.setTipologia(TipologiaResponseEnum.SUCCESS);
		response.setPayload(payload);

		return Response.ok().entity(response).status(Status.OK).build();
	}
	
	public static Response errorResponse(Exception e) {
		@SuppressWarnings("rawtypes")
		BaseRestResponse response = new BaseRestResponse();
		response.setTipologia(TipologiaResponseEnum.BLOCCANTE);
		
		if(e instanceof RecordNotFoundException) {
			response.setCodice(CodiceEnum.E01);
			response.setTesto(ErrorEnum.DATA_MISSING_ERROR.toString());
		}
		else if(e instanceof SQLException || e instanceof RecordNotUniqueException) {
			response.setCodice(CodiceEnum.E02);
			response.setTesto(ErrorEnum.DATA_ERROR.toString());
		}
		else if(e instanceof ValidationException) {
			response.setCodice(CodiceEnum.E03);
			response.setTesto(ErrorEnum.VALIDATION_ERROR.toString());
		}
		else if(e instanceof HttpException) {
			response.setCodice(CodiceEnum.E04);
			response.setTesto(ErrorEnum.HTTP_ERROR.toString());
		} else if (e instanceof StepsValidationException) {
			response.setCodice(CodiceEnum.ADS_CONSOLIDA_VALIDATION_ERROR);
			response.setTesto(ErrorEnum.GENERIC_ERROR.toString());
			response.setPayload(e);
		}
		else {
			response.setCodice(CodiceEnum.E00);
			response.setTesto(ErrorEnum.GENERIC_ERROR.toString());
		}
		

		return Response.ok().entity(response).build();
	}
	
	// TODO: we need to change above method to conform to this one
	public static Response errorResponse(BaseException e) {
		BaseRestResponse response = new BaseRestResponse();
		
		response.setCodice(e.getCodice());
		response.setTesto(e.getTesto());
		response.setPayload(e.getPayload());
		
		return Response.ok().entity(response).build();
	}
	

	public static Response invalidEmail() {
		Error err = new Error();
		err.setCode(ErrorConstants.MAIL_NON_VALIDO_004);
		err.setErrorMessage(ErrorConstants.MAIL_NON_VALIDO);
		return Response.serverError().status(err.getCode()).entity(err).build();
	}
	
	public static Response invalidLength() {
		Error err = new Error();
		err.setCode(ErrorConstants.STRINGA_DIGITATA_ERROR_001);
		err.setErrorMessage(ErrorConstants.STRINGA_DIGITATA_ERROR);
		return Response.serverError().status(err.getCode()).entity(err).build();
	}
	
	public static Response requiredField() {
		Error err = new Error();
		err.setCode(ErrorConstants.CAMPO_OBBLIGATORIO_003);
		err.setErrorMessage(ErrorConstants.CAMPO_OBBLIGATORIO);
		return Response.serverError().status(err.getCode()).entity(err).build();
	}
	
	public static Response badInputParameters() {
		Error err = new Error();
		err.setCode(ErrorConstants.BAD_INPUT_PARAMETERS_400);
		err.setErrorMessage(ErrorConstants.BAD_INPUT_PARAMETERS);
		return Response.serverError().status(err.getCode()).entity(err).build();
	}
	
	public static Response itemNotFound() {
		Error err = new Error();
		err.setCode(ErrorConstants.NON_TROVATO_404);
		err.setErrorMessage(ErrorConstants.NON_TROVATO);
		return Response.serverError().status(err.getCode()).entity(err).build();
	}
	public static Response itemFound() {
		Error err = new Error();
		err.setCode(ErrorConstants.ID_ESISTE_IN_DB_005);
		err.setErrorMessage(ErrorConstants.ID_ESISTE_IN_DB);
		return Response.serverError().status(err.getCode()).entity(err).build();
	}
	public static Response databaseIntegrityDisturbed() {
		Error err = new Error();
		err.setCode(ErrorConstants.DB_INTEGRITY_19);
		err.setErrorMessage(ErrorConstants.DB_INTEGRITY_DISTURBED);
		return Response.serverError().status(err.getCode()).entity(err).build();
	}
}
