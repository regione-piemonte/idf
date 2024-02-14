/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.exceptions;

import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;

import it.csi.idf.idfapi.util.CodiceEnum;

public class CustomValidator {
	public static final Pattern digit11;
	public static final Pattern ItalianFiscalCode;
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX ; 

	static {
		digit11 = Pattern.compile("\\d{11}");
		ItalianFiscalCode = Pattern.compile(
				"^([A-Za-z]{6}[0-9lmnpqrstuvLMNPQRSTUV]{2}[abcdehlmprstABCDEHLMPRST]{1}[0-9lmnpqrstuvLMNPQRSTUV]{2}[A-Za-z]{1}[0-9lmnpqrstuvLMNPQRSTUV]{3}[A-Za-z]{1})$|([0-9]{11})$");
		 VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	}

	public static MultiErrorException getValidator(HttpStatus HttpStatus) {

		return new MultiErrorException(HttpStatus);
	}
	public static MultiErrorException getValidator(HttpStatus HttpStatus,CodiceEnum codice) {

		return new MultiErrorException(HttpStatus,codice);
	}
	public static MultiErrorException getValidator() {

		return new MultiErrorException();
	}


	public static String getMessage(String messageKey, Object[] args) {

		return messageKey + " - " + args.toString();// TODO
	}

}
