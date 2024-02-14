/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

public enum TipoAdsEnum {

	SUPERFICIE_NOTA(1),
	RELASCOPICA_SEMPLICE(2),
	RELASCOPICA_COMPLETA(3),
	MONITORAGGIO_PERMANENTE(4);
	
	TipoAdsEnum(int idTipoAds) {
		this.idTipoAds = idTipoAds;
	}
	
	private int idTipoAds;
			
	public int getIdTipoAds() {
		return idTipoAds;
	}
}
