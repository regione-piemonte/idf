/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfgeoapi.dto;

import java.io.Serializable;

public class GeecoProfiloDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5372582819283228932L;
	
	private Integer idGeecoProfilo;
	private String descrGeecoProfilo;
	private Integer fkProcedura;
	private String urlRitorno;
	private String envInfo;
	private String flgAutenticLetturaScrittura;
	
	/**
	 * @return the idGeecoProfilo
	 */
	public Integer getIdGeecoProfilo() {
		return idGeecoProfilo;
	}
	/**
	 * @param idGeecoProfilo the idGeecoProfilo to set
	 */
	public void setIdGeecoProfilo(Integer idGeecoProfilo) {
		this.idGeecoProfilo = idGeecoProfilo;
	}
	/**
	 * @return the descrGeecoProfilo
	 */
	public String getDescrGeecoProfilo() {
		return descrGeecoProfilo;
	}
	/**
	 * @param descrGeecoProfilo the descrGeecoProfilo to set
	 */
	public void setDescrGeecoProfilo(String descrGeecoProfilo) {
		this.descrGeecoProfilo = descrGeecoProfilo;
	}
	/**
	 * @return the fkProcedura
	 */
	public Integer getFkProcedura() {
		return fkProcedura;
	}
	/**
	 * @param fkProcedura the fkProcedura to set
	 */
	public void setFkProcedura(Integer fkProcedura) {
		this.fkProcedura = fkProcedura;
	}
	/**
	 * @return the urlRitorno
	 */
	public String getUrlRitorno() {
		return urlRitorno;
	}
	/**
	 * @param urlRitorno the urlRitorno to set
	 */
	public void setUrlRitorno(String urlRitorno) {
		this.urlRitorno = urlRitorno;
	}
	/**
	 * @return the envInfo
	 */
	public String getEnvInfo() {
		return envInfo;
	}
	/**
	 * @param envInfo the envInfo to set
	 */
	public void setEnvInfo(String envInfo) {
		this.envInfo = envInfo;
	}
	/**
	 * @return the flgAutenticLetturaScrittura
	 */
	public String getFlgAutenticLetturaScrittura() {
		return flgAutenticLetturaScrittura;
	}
	/**
	 * @param flgAutenticLetturaScrittura the flgAutenticLetturaScrittura to set
	 */
	public void setFlgAutenticLetturaScrittura(String flgAutenticLetturaScrittura) {
		this.flgAutenticLetturaScrittura = flgAutenticLetturaScrittura;
	}
	
	
}
