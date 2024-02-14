/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import it.csi.idf.idfapi.errors.ErrorConstants;
import it.csi.idf.idfapi.util.CodiceEnum;
import it.csi.idf.idfapi.validation.BaseException;

public class MultiErrorException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	{
		setCodice(CodiceEnum.ADS_CONSOLIDA_VALIDATION_ERROR);
		setTesto("text for all instances of Multierror error");
		// DM is it correct or?
		status = HttpStatus.OK;
	}
	// list of errors
	private List<FieldError> errors = new ArrayList<>(10);

	// HTTP Status code to be returned
	private HttpStatus status;

	public MultiErrorException(HttpStatus httpStatus) {
		this.status = httpStatus;
	}

	public MultiErrorException() {
		super();
	}

	public MultiErrorException(String message, Throwable cause, CodiceEnum codice) {
		super(message, cause, codice);
	}

	public MultiErrorException(HttpStatus httpStatus, CodiceEnum codice) {
		setCodice(codice);
		this.status = httpStatus;
	}

	public MultiErrorException httpStatus(HttpStatus status) {
		this.status = status;
		return this;
	}

	public MultiErrorException validate(String fieldName, boolean valid, int errorCode, String messageKey,
			String... args) {

		if (!valid)
			errors.add(new FieldError(fieldName, errorCode, messageKey, args));

		return this;
	}

	public MultiErrorException errorIf(String fieldName, boolean valid, int errorCode, String messageKey,
			String... args) {
		if (valid)
			errors.add(new FieldError(fieldName, errorCode, messageKey, args));

		return this;
	}

	public MultiErrorException errorIfNULL(String fieldName, Object obj) {
		if (obj == null )
			errors.add(new FieldError(fieldName, ErrorConstants.OGGETTO_OBBLIGATORIO_002,
					ErrorConstants.OGGETTO_OBBLIGATORIO, "null value"));

		return this;
	}

	public MultiErrorException errorIf(String fieldName, boolean valid, String messageKey) {
		if (valid)
			errors.add(new FieldError(fieldName, ErrorConstants.OGGETTO_OBBLIGATORIO_002, messageKey,
					ErrorConstants.BAD_INPUT_PARAMETERS));

		return this;
	}

	public MultiErrorException codiceIsValid(String fieldName, String codice) {
		if (codice == null) {
			errors.add(new FieldError(fieldName, ErrorConstants.BAD_INPUT_PARAMETERS_400,
					ErrorConstants.FISCAL_CODE_ERROR, "null in codice"));
		} else {
			// charseq like AAABBBnnCnnDnnnE
			if (!CustomValidator.ItalianFiscalCode.matcher(codice).find()) {
				errors.add(new FieldError(fieldName, ErrorConstants.BAD_INPUT_PARAMETERS_400,
						ErrorConstants.FISCAL_CODE_ERROR, codice));

			}
		}
		return this;
	}
	public MultiErrorException errorIfNotNumber(String fieldName, Object codice) {
		if (codice == null) {
			errors.add(new FieldError(fieldName, ErrorConstants.BAD_INPUT_PARAMETERS_400,
					ErrorConstants.FISCAL_CODE_ERROR, "null value"));
		} else {
			
			if (!CustomValidator.digit11.matcher(codice.toString()).find()) {
				errors.add(new FieldError(fieldName, ErrorConstants.BAD_INPUT_PARAMETERS_400,
						ErrorConstants.FISCAL_CODE_ERROR, codice.toString()));

			}
		}
		return this;
	}
	public MultiErrorException isMail(String fieldName, String mail) {
		if (mail == null) {
			errors.add(new FieldError(fieldName, ErrorConstants.BAD_INPUT_PARAMETERS_400,
					ErrorConstants.FISCAL_CODE_ERROR, "null value"));
		} else {
			
			if (!CustomValidator.VALID_EMAIL_ADDRESS_REGEX.matcher(mail).find()) {
				errors.add(new FieldError(fieldName, ErrorConstants.BAD_INPUT_PARAMETERS_400,
						ErrorConstants.FISCAL_CODE_ERROR, mail));

			}
		}
		return this;
	}
	public void go() throws MultiErrorException {
		if (!errors.isEmpty())
			throw this;
	}

	public List<FieldError> getErrors() {
		return errors;
	}

	public void setErrors(List<FieldError> errors) {
		this.errors = errors;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	@Override
	public Object getPayload() {
		// TODO Auto-generated method stub
		return errors;
	}

	public MultiErrorException withCodice(CodiceEnum codiceEnum) {
		super.setCodice(codiceEnum);
		return this;
	}

}
