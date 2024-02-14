/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.business.service.helper.dto;

import java.io.Serializable;

public class MetadatiIndexPfa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2285408933626327972L;
	
	private String idAllegato;
	private String idTipoAllegato;
	private String idIstanza;
	private String idPfa;
	private boolean isNotifica;
	private String idIntervento;
	
	public static final String META_ID_ALLEGATO = "pfa:idAllegato";
	public static final String META_ID_TIPO_ALLEGATO = "pfa:idTipoAllegato";
	public static final String META_ID_ISTANZA = "pfa:idIstanza";
	
	public static final String FOLDER_INTERVENTI = "INTERVENTI";
	
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
	 * @return the idPfa
	 */
	public String getIdPfa() {
		return idPfa;
	}
	/**
	 * @param idPfa the idPfa to set
	 */
	public void setIdPfa(String idPfa) {
		this.idPfa = idPfa;
	}
	/**
	 * @return the isNotifica
	 */
	public boolean isNotifica() {
		return isNotifica;
	}
	/**
	 * @param isNotifica the isNotifica to set
	 */
	public void setNotifica(boolean isNotifica) {
		this.isNotifica = isNotifica;
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
