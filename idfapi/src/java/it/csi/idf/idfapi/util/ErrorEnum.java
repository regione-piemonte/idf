/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

public enum ErrorEnum {
	DATA_ERROR,
	VALIDATION_ERROR,
	DATA_MISSING_ERROR,
	HTTP_ERROR,
	GENERIC_ERROR;

	/*public static String getErrorFromException(Exception e) {
		ErrorEnum result = null;
		if(e instanceof RecordNotFoundException)
			result = DATA_MISSING_ERROR; 
		else if(e instanceof SQLException)
			result = DATA_ERROR;
		else if(e instanceof ValidationException)
			result = VALIDATION_ERROR;  
		else if(e instanceof HttpException)
			result = HTTP_ERROR; 
		else
			result = GENERIC_ERROR;
		return result.name();
	}*/
}
