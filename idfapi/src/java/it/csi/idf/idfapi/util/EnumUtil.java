/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

public class EnumUtil {

	private EnumUtil() {}
	
	@SuppressWarnings("unchecked")
	public static <E extends Enum<E>> E stringToEnum(String stringToCheck, Class<E> enumToCheckForMatch) {
		for (Enum<E> enumVal : enumToCheckForMatch.getEnumConstants()) {
			if (enumVal.toString().equalsIgnoreCase(stringToCheck)) {
				return (E) enumVal;
			}
		}
		return null;
	}
}
