/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.sql.Date;

public class GeecoPfaInterventoFeature extends GeecoPfaFeature {

	/**
	 * 
	 */
	private static final long serialVersionUID = 966087229903967468L;
	
	private String idIntervento;
	private String idInterventoTipo;
	private String tipoGeometria;
	private String codiceIntervento;
	private String geometriaIntervento;
	private String descrizione;
	//FixGP
	private String idgeoPlPfa;
	private String superficie;
	private Date dataInserimento;
	
	
	public String getSuperficie() {
		return superficie;
	}
	public void setSuperficie(String superficie) {
		this.superficie = superficie;
	}
	public String getIdgeoPlPfa() {
		return idgeoPlPfa;
	}
	public void setIdgeoPlPfa(String idgeoPlPfa) {
		this.idgeoPlPfa = idgeoPlPfa;
	}
	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}
	/**
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
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
	/**
	 * @return the codiceIntervento
	 */
	public String getCodiceIntervento() {
		return codiceIntervento;
	}
	/**
	 * @param codiceIntervento the codiceIntervento to set
	 */
	public void setCodiceIntervento(String codiceIntervento) {
		this.codiceIntervento = codiceIntervento;
	}
	/**
	 * @return the geometriaIntervento
	 */
	public String getGeometriaIntervento() {
		return geometriaIntervento;
	}
	/**
	 * @param geometriaIntervento the geometriaIntervento to set
	 */
	public void setGeometriaIntervento(String geometriaIntervento) {
		this.geometriaIntervento = geometriaIntervento;
	}
	/**
	 * @return the idInteventoTipo
	 */
	public String getIdInterventoTipo() {
		return idInterventoTipo;
	}
	/**
	 * @param idInteventoTipo the idInteventoTipo to set
	 */
	public void setIdInterventoTipo(String idInterventoTipo) {
		this.idInterventoTipo = idInterventoTipo;
	}
	/**
	 * @return the tipoGeometria
	 */
	public String getTipoGeometria() {
		return tipoGeometria;
	}
	/**
	 * @param tipoGeometria the tipoGeometria to set
	 */
	public void setTipoGeometria(String tipoGeometria) {
		this.tipoGeometria = tipoGeometria;
	}
	
	
	
}
