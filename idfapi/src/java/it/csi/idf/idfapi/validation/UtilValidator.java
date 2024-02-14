/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.validation;

import java.util.HashMap;

import it.csi.idf.idfapi.business.be.exceptions.ValidationException;

public class UtilValidator {

	
	public static final String MANDATORY_FIELD_MESSAGE = "Campo obligatorio!";
	public static final String NOT_NEGATIVE_FIELD_MESSAGE = "Il valore non puo' essere negativo!";
	
	public static void checkNotNullFiledValidity(String fieldName, HashMap<String,String> errorMap, Object value, Object defaultValue, String defaultMessage, Object... messageArgs) {
		if (value ==null || (defaultValue!=null && defaultValue.equals(value))) {
			errorMap.put(fieldName, String.format(defaultMessage, messageArgs));
		}
	}
	
	public static void checkNotNullFiledValidity(String fieldName, HashMap<String,String> errorMap, Object value, String defaultMessage, Object... messageArgs) throws ValidationException {
		if (value ==null || isEmptyString(value) ) {
			errorMap.put(fieldName, String.format(defaultMessage, messageArgs));
		}
	}
	
	public static void checkNotNegativeNumberValidity(String fieldName, HashMap<String,String> errorMap, Object value, String defaultMessage, Object... messageArgs) throws ValidationException {
		if (value !=null) {
			Double num = Double.parseDouble(value.toString()); 
			if(num < 0) {
				errorMap.put(fieldName, String.format(defaultMessage, messageArgs));
			}
		}
	}
	
	private static boolean isEmptyString(Object value) {
		if(value != null && value instanceof String) {
			String str = (String) value;
			if(str.trim().equals("")) {
				return true;
			}
		}
		return false;
	}
}
