/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

public enum TipoIstanzaEnum {
	DICHIARAZIONE_SOSTITUTIVA(1),
	COMUNICAZIONE(2),
	AUTORIZZAZIONE(3),
	VINCOLO_AUTORIZZAZIONE_REGIONALE(4),
	VINCOLO_AUTORIZZAZIONE_COMUNALE(5);
	
	private int value;
	
	private TipoIstanzaEnum(int value) {
		this.value = value;
	}
	
	public int getTypeValue() {
		return value;
	}
	
	public static TipoIstanzaEnum getEnum(int instanceTypeValue) {
		for (TipoIstanzaEnum instance : TipoIstanzaEnum.values()) { 
		    if(instance.getTypeValue() == instanceTypeValue) {
		    	return instance;
		    } 
		}
		throw new IllegalArgumentException("InstanceTypeEnum does not have enum with provided parameter");
	}
	
	public static TipoIstanzaEnum getEnumByValue(int value) {
		for (TipoIstanzaEnum item : TipoIstanzaEnum.values()) { 
		    if(item.getTypeValue() == value) {
		    	return item;
		    } 
		}
		return null;
	}
}
