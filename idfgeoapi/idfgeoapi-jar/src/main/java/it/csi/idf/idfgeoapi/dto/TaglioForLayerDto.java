/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfgeoapi.dto;

public class TaglioForLayerDto extends FeatureForLayerDto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8131974631332950123L;
	
	private Integer fkIntervento;
	private String dataInserimento;
	private String superficie;	
	private String geometria;
	private Integer idfGeoPlLottoIntervento;
	
	/**
	 * @return the fkIntervento
	 */
	public Integer getFkIntervento() {
		return fkIntervento;
	}
	/**
	 * @param fkIntervento the fkIntervento to set
	 */
	public void setFkIntervento(Integer fkIntervento) {
		this.fkIntervento = fkIntervento;
	}
	/**
	 * @return the dataInserimento
	 */
	public String getDataInserimento() {
		return dataInserimento;
	}
	/**
	 * @param dataInserimento the dataInserimento to set
	 */
	public void setDataInserimento(String dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	/**
	 * @return the superficie
	 */
	public String getSuperficie() {
		return superficie;
	}
	/**
	 * @param superficie the superficie to set
	 */
	public void setSuperficie(String superficie) {
		this.superficie = superficie;
	}
	/**
	 * @return the geometria
	 */
	public String getGeometria() {
		return geometria;
	}
	/**
	 * @param geometria the geometria to set
	 */
	public void setGeometria(String geometria) {
		this.geometria = geometria;
	}
	/**
	 * @return the idfGeoPlLottoIntervento
	 */
	public Integer getIdfGeoPlLottoIntervento() {
		return idfGeoPlLottoIntervento;
	}
	/**
	 * @param idfGeoPlLottoIntervento the idfGeoPlLottoIntervento to set
	 */
	public void setIdfGeoPlLottoIntervento(Integer idfGeoPlLottoIntervento) {
		this.idfGeoPlLottoIntervento = idfGeoPlLottoIntervento;
	}
	
	
	
}
