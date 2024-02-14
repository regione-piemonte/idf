/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class GeecoPfaFeature extends GeecoFeature {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3910650307722020825L;
	
	
	private String idgeoPlPfa;
	private String denominazionePfa;
	private String geometriaPfa;
	private String denominazioneComunePfa;
	
	
	
	
	/**
	 * @return the denominazioneComunePfa
	 */
	public String getDenominazioneComunePfa() {
		return denominazioneComunePfa;
	}
	/**
	 * @param denominazioneComunePfa the denominazioneComunePfa to set
	 */
	public void setDenominazioneComunePfa(String denominazioneComunePfa) {
		this.denominazioneComunePfa = denominazioneComunePfa;
	}
	/**
	 * @return the idgeoPlPfa
	 */
	public String getIdgeoPlPfa() {
		return idgeoPlPfa;
	}
	/**
	 * @param idgeoPlPfa the idgeoPlPfa to set
	 */
	public void setIdgeoPlPfa(String idgeoPlPfa) {
		this.idgeoPlPfa = idgeoPlPfa;
	}
	/**
	 * @return the denominazionePfa
	 */
	public String getDenominazionePfa() {
		return denominazionePfa;
	}
	/**
	 * @param denominazionePfa the denominazionePfa to set
	 */
	public void setDenominazionePfa(String denominazionePfa) {
		this.denominazionePfa = denominazionePfa;
	}
	/**
	 * @return the geometria
	 */
	public String getGeometriaPfa() {
		return geometriaPfa;
	}
	/**
	 * @param geometria the geometria to set
	 */
	public void setGeometriaPfa(String geometriaPfa) {
		this.geometriaPfa = geometriaPfa;
	}
	
	

}
