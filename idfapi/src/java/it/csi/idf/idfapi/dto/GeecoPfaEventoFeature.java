/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class GeecoPfaEventoFeature extends GeecoPfaFeature {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8025557967534044928L;
	
	private String idEvento;
	private String idEventoTipo;
	private String tipoGeometria;
	private String codiceEvento;
	private String geometriaEvento;
	private String descrizione;
	
	
	
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
	 * @return the idEvento
	 */
	public String getIdEvento() {
		return idEvento;
	}
	/**
	 * @param idEvento the idEvento to set
	 */
	public void setIdEvento(String idEvento) {
		this.idEvento = idEvento;
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
	/**
	 * @return the codiceEvento
	 */
	public String getCodiceEvento() {
		return codiceEvento;
	}
	/**
	 * @param codiceEvento the codiceEvento to set
	 */
	public void setCodiceEvento(String codiceEvento) {
		this.codiceEvento = codiceEvento;
	}
	/**
	 * @return the geometriaEvento
	 */
	public String getGeometriaEvento() {
		return geometriaEvento;
	}
	/**
	 * @param geometriaEvento the geometriaEvento to set
	 */
	public void setGeometriaEvento(String geometriaEvento) {
		this.geometriaEvento = geometriaEvento;
	}
	/**
	 * @return the idEventoTipo
	 */
	public String getIdEventoTipo() {
		return idEventoTipo;
	}
	/**
	 * @param idEventoTipo the idEventoTipo to set
	 */
	public void setIdEventoTipo(String idEventoTipo) {
		this.idEventoTipo = idEventoTipo;
	}
	
	
	
}
