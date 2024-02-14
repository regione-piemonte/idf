/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.service.helper.dto;

import java.io.Serializable;

public class MetadatiIndexVincolo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2285408933626327972L;
	
	private String idAllegato;
	private String idTipoAllegato;
	private String idIstanza;
	private String idIntervento;
	
	
	public static final String META_ID_ALLEGATO = "vincoloIdrogeologico:idAllegato";
	public static final String META_ID_TIPO_ALLEGATO = "vincoloIdrogeologico:idTipoAllegato";
	public static final String META_ID_ISTANZA = "vincoloIdrogeologico:idIstanza";
	
	/**
	 * @return the idAllegato
	 */
	public String getIdAllegato() {
		return idAllegato;
	}
	/**
	 * @param idAllegato the idAllegato to set
	 */
	public void setIdAllegato(String idAllegato) {
		this.idAllegato = idAllegato;
	}
	/**
	 * @return the idTipoAllegato
	 */
	public String getIdTipoAllegato() {
		return idTipoAllegato;
	}
	/**
	 * @param idTipoAllegato the idTipoAllegato to set
	 */
	public void setIdTipoAllegato(String idTipoAllegato) {
		this.idTipoAllegato = idTipoAllegato;
	}
	/**
	 * @return the idIstanza
	 */
	public String getIdIstanza() {
		return idIstanza;
	}
	/**
	 * @param idIstanza the idIstanza to set
	 */
	public void setIdIstanza(String idIstanza) {
		this.idIstanza = idIstanza;
	}
	/**
	 * @return the idIntervento
	 */
	public String getIdIntervento() {
		return idIntervento;
	}
	/**
	 * @param idIntervento the idIntervento to set
	 */
	public void setIdIntervento(String idIntervento) {
		this.idIntervento = idIntervento;
	}
	
	
}
