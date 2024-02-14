/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.dto;

import java.io.Serializable;


/**
 * DTO specifico della query modellata nel finder infoMailByIstanza.
 * @generated
 */
public class IstanzaInfoMailByIstanzaDto implements Serializable {

	/*	 
	 * @generated
	 */
	private java.math.BigDecimal itifIdIstanzaIntervento;

	/**
	 * @generated
	 */
	public void setItifIdIstanzaIntervento(java.math.BigDecimal val) {

		itifIdIstanzaIntervento = val;

	}
	/**
	 * @generated
	 */
	public java.math.BigDecimal getItifIdIstanzaIntervento() {

		return itifIdIstanzaIntervento;

	}

	/*	 
	 * @generated
	 */
	private java.math.BigDecimal itifNrIstanzaForestale;

	/**
	 * @generated
	 */
	public void setItifNrIstanzaForestale(java.math.BigDecimal val) {

		itifNrIstanzaForestale = val;

	}
	/**
	 * @generated
	 */
	public java.math.BigDecimal getItifNrIstanzaForestale() {

		return itifNrIstanzaForestale;

	}

	/*	 
	 * @generated
	 */
	private String mail_richiedente;

	/**
	 * @generated
	 */
	public void setMail_richiedente(String val) {

		mail_richiedente = val;

	}
	/**
	 * @generated
	 */
	public String getMail_richiedente() {

		return mail_richiedente;

	}

	/*	 
	 * @generated
	 */
	private java.math.BigDecimal is_soggetto_gestore;

	/**
	 * @generated
	 */
	public void setIs_soggetto_gestore(java.math.BigDecimal val) {

		is_soggetto_gestore = val;

	}
	/**
	 * @generated
	 */
	public java.math.BigDecimal getIs_soggetto_gestore() {

		return is_soggetto_gestore;

	}

	/*	 
	 * @generated
	 */
	private String denominazione_gestore;

	/**
	 * @generated
	 */
	public void setDenominazione_gestore(String val) {

		denominazione_gestore = val;

	}
	/**
	 * @generated
	 */
	public String getDenominazione_gestore() {

		return denominazione_gestore;

	}

	/*	 
	 * @generated
	 */
	private String telefono_gestore;

	/**
	 * @generated
	 */
	public void setTelefono_gestore(String val) {

		telefono_gestore = val;

	}
	/**
	 * @generated
	 */
	public String getTelefono_gestore() {

		return telefono_gestore;

	}

	/*	 
	 * @generated
	 */
	private String mail_gestore;

	/**
	 * @generated
	 */
	public void setMail_gestore(String val) {

		mail_gestore = val;

	}
	/**
	 * @generated
	 */
	public String getMail_gestore() {

		return mail_gestore;

	}

	/*	 
	 * @generated
	 */
	private String pec_gestore;

	/**
	 * @generated
	 */
	public void setPec_gestore(String val) {

		pec_gestore = val;

	}
	/**
	 * @generated
	 */
	public String getPec_gestore() {

		return pec_gestore;

	}
	
	/*	 
	 * @generated
	 */
	private String tipoIstanza;
	
	

	/**
	 * @return the tipoIstanza
	 */
	public String getTipoIstanza() {
		return tipoIstanza;
	}
	/**
	 * @param tipoIstanza the tipoIstanza to set
	 */
	public void setTipoIstanza(String tipoIstanza) {
		this.tipoIstanza = tipoIstanza;
	}
	/**
	 * Method 'equals'
	 * 
	 * @param _other
	 * @return boolean
	 * @generated
	 */
	public boolean equals(Object _other) {
		return super.equals(_other);
	}

	/**
	 * Method 'hashCode'
	 * 
	 * @return int
	 * @generated
	 */
	public int hashCode() {
		return super.hashCode();
	}

}
