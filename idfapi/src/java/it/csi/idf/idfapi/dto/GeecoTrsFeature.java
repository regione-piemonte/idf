/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.sql.Date;

public class GeecoTrsFeature extends GeecoFeature {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1192661629101349749L;
	
	private String idgeoPlIntervTrasf;
	private String geometria;
	private String fkIntervento;
	private Date dataInserimento;
	private String superficie;
	
	/**
	 * @return the idgeoPlIntervTrasf
	 */
	public String getIdgeoPlIntervTrasf() {
		return idgeoPlIntervTrasf;
	}
	/**
	 * @param idgeoPlIntervTrasf the idgeoPlIntervTrasf to set
	 */
	public void setIdgeoPlIntervTrasf(String idgeoPlIntervTrasf) {
		this.idgeoPlIntervTrasf = idgeoPlIntervTrasf;
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
	 * @return the fkIntervento
	 */
	public String getFkIntervento() {
		return fkIntervento;
	}
	/**
	 * @param fkIntervento the fkIntervento to set
	 */
	public void setFkIntervento(String fkIntervento) {
		this.fkIntervento = fkIntervento;
	}
	/**
	 * @return the dataInserimento
	 */
	public Date getDataInserimento() {
		return dataInserimento;
	}
	/**
	 * @param dataInserimento the dataInserimento to set
	 */
	public void setDataInserimento(Date dataInserimento) {
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
	
	

}
