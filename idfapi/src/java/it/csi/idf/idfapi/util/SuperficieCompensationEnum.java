/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

/**
 * COMPENSAZIONE DELLA SUPERFICIE FORESTALE DA TRASFORMARE
 * F - physical compensation
 * M - monetary compensation
 * N - not required
 * 
 * @author stefan.simonovic
 */

public enum SuperficieCompensationEnum {
	F,
	M,
	N;
	
	public static SuperficieCompensationEnum getCompensationEnum(String value) {
		for (SuperficieCompensationEnum compensation : SuperficieCompensationEnum.values()) { 
		    if(compensation.toString().equals(value)) {
		    	return compensation;
		    } 
		}
		return null;
	}
}
