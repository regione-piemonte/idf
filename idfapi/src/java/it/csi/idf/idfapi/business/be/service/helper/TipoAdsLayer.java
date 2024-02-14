/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.service.helper;

public enum TipoAdsLayer {

	MONITORAGGIO(9, 4), SUPERFICIE_NOTA(10, 1), RELASCOPICA_COMPLETA(11, 3), RELASCOPICA_SEMPLICE(14, 2);

	private int idLayerGeeco;
	private int idTipoAds;

	/**
	 * @return the idLayerGeeco
	 */
	public int getIdLayerGeeco() {
		return idLayerGeeco;
	}

	/**
	 * @param idLayerGeeco the idLayerGeeco to set
	 */
	public void setIdLayerGeeco(int idLayerGeeco) {
		this.idLayerGeeco = idLayerGeeco;
	}

	/**
	 * @return the idTipoAds
	 */
	public int getIdTipoAds() {
		return idTipoAds;
	}

	/**
	 * @param idTipoAds the idTipoAds to set
	 */
	public void setIdTipoAds(int idTipoAds) {
		this.idTipoAds = idTipoAds;
	}

	private TipoAdsLayer(int idLayerGeeco, int idTipoAds) {
		this.idLayerGeeco = idLayerGeeco;
		this.idTipoAds = idTipoAds;
	}
	
	
	public static int getIdLayerFromIdTipoAds(int idTipoAds) {
		for (TipoAdsLayer tipo : values()) {
			if(idTipoAds == tipo.idTipoAds) {
				return tipo.getIdLayerGeeco(); 
			}
		}
		return 0;
	}
	
}
