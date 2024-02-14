/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.io.Serializable;

public class GeecoLayer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8217673116383765116L;
	
	private String idGeecoProfilo;
	private String idGeecoLayer;
	private String descrGeecoLayer;
	private String flgTipoLayer;
	private String flgTipoAccessoScrittura;
	private String flgTipoAccessoCanc;
	/**
	 * @return the idGeecoProfilo
	 */
	public String getIdGeecoProfilo() {
		return idGeecoProfilo;
	}
	/**
	 * @param idGeecoProfilo the idGeecoProfilo to set
	 */
	public void setIdGeecoProfilo(String idGeecoProfilo) {
		this.idGeecoProfilo = idGeecoProfilo;
	}
	/**
	 * @return the idGeecoLayer
	 */
	public String getIdGeecoLayer() {
		return idGeecoLayer;
	}
	/**
	 * @param idGeecoLayer the idGeecoLayer to set
	 */
	public void setIdGeecoLayer(String idGeecoLayer) {
		this.idGeecoLayer = idGeecoLayer;
	}
	/**
	 * @return the descrGeecoLayer
	 */
	public String getDescrGeecoLayer() {
		return descrGeecoLayer;
	}
	/**
	 * @param descrGeecoLayer the descrGeecoLayer to set
	 */
	public void setDescrGeecoLayer(String descrGeecoLayer) {
		this.descrGeecoLayer = descrGeecoLayer;
	}
	/**
	 * @return the flgTipoAccessoScrittura
	 */
	public String getFlgTipoAccessoScrittura() {
		return flgTipoAccessoScrittura;
	}
	/**
	 * @param flgTipoAccessoScrittura the flgTipoAccessoScrittura to set
	 */
	public void setFlgTipoAccessoScrittura(String flgTipoAccessoScrittura) {
		this.flgTipoAccessoScrittura = flgTipoAccessoScrittura;
	}
	/**
	 * @return the flgTipoAccessoCanc
	 */
	public String getFlgTipoAccessoCanc() {
		return flgTipoAccessoCanc;
	}
	/**
	 * @param flgTipoAccessoCanc the flgTipoAccessoCanc to set
	 */
	public void setFlgTipoAccessoCanc(String flgTipoAccessoCanc) {
		this.flgTipoAccessoCanc = flgTipoAccessoCanc;
	}
	/**
	 * @return the flgTipoLayer
	 */
	public String getFlgTipoLayer() {
		return flgTipoLayer;
	}
	/**
	 * @param flgTipoLayer the flgTipoLayer to set
	 */
	public void setFlgTipoLayer(String flgTipoLayer) {
		this.flgTipoLayer = flgTipoLayer;
	}
	
	

}
