/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

import java.util.regex.Pattern;

public class ValidationUtil {
	private final static Pattern patternItalianFiscalCode;
	static {
		patternItalianFiscalCode = Pattern.compile(
				"^([A-Za-z]{6}[0-9lmnpqrstuvLMNPQRSTUV]{2}[abcdehlmprstABCDEHLMPRST]{1}[0-9lmnpqrstuvLMNPQRSTUV]{2}[A-Za-z]{1}[0-9lmnpqrstuvLMNPQRSTUV]{3}[A-Za-z]{1})$|([0-9]{11})$");

	}

	private ValidationUtil() {
	}

	public static boolean isEMail(String mail) {
		String pattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
		return mail.matches(pattern);
	}

	public static boolean isNumber(String value) {
		String pattern = "\\d+";
		return value.matches(pattern);
	}

	public static boolean isBigDecimal(String value) {
		String pattern = "^(((\\d{1,3})(?:,[0-9]{3}){0,4}|(\\d{1,12}))(\\.[0-9]{1,2})?)?$";
		return value.matches(pattern);
	}

	public static boolean hasArrayNotNullValue(Object[] paramArray) {
		if (paramArray == null)
			return false;

		for (int i = 0; i < paramArray.length; i++) {
			if (paramArray[i] != null || (paramArray[i] instanceof String && !paramArray[i].toString().isEmpty()))
				return true;
		}
		return false;
	}

	public static boolean hasNotNullValue(Object[][] arrayOfParamArrays) {
		if (arrayOfParamArrays == null)
			return false;
		for (int i = 0; i < arrayOfParamArrays.length; i++) {
			if (hasArrayNotNullValue(arrayOfParamArrays[i]))
				return true;
		}
		return false;
	}

	public static boolean isCodiceOk(String codice) {
		if (codice == null) {
			return false;
		}
		return patternItalianFiscalCode.matcher(codice).find();
	}
}
