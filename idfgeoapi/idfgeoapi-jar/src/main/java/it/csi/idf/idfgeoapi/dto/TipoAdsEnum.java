/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfgeoapi.dto;

public enum TipoAdsEnum {
	
	SUPERFICIE_NOTA (1,"10"),
	RELASCOPICA_SEMPLICE(2, "14"),
	RELASCOPICA_COMPLETA(3, "11"),
	MONITORAGGIO(4,"9");
	
	private int idTipoAds;
	private String idLayerGeeco;
	
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

	/**
	 * @return the idLayerGeeco
	 */
	public String getIdLayerGeeco() {
		return idLayerGeeco;
	}

	/**
	 * @param idLayerGeeco the idLayerGeeco to set
	 */
	public void setIdLayerGeeco(String idLayerGeeco) {
		this.idLayerGeeco = idLayerGeeco;
	}

	private TipoAdsEnum(int idTipoAds, String idLayerGeeco) {
		this.idLayerGeeco = idLayerGeeco;
		this.idTipoAds = idTipoAds;
	}
	
	public static int getIdFromLayer(String idLayerGeeco) {
		for (TipoAdsEnum tipoAds : values()) {
			if(tipoAds.getIdLayerGeeco().equals(idLayerGeeco)) {
				return tipoAds.getIdTipoAds();
			}
		}
		return 0;
	}
	
	
}
