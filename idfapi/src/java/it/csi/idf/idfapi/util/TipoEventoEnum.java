/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

public enum TipoEventoEnum {
	VALANGA(1),
	VENTO(2),
	FRANA(3),
	INCENDIO(4);
	
	private int value;
	
	private TipoEventoEnum(int value) {
		this.value = value;
	}
	
	public int getTypeValue() {
		return value;
	}
	
	public static TipoEventoEnum getEnum(int instanceTypeValue) {
		for (TipoEventoEnum instance : TipoEventoEnum.values()) { 
		    if(instance.getTypeValue() == instanceTypeValue) {
		    	return instance;
		    } 
		}
		throw new IllegalArgumentException("InstanceTypeEnum does not have enum with provided parameter");
	}
}
