/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfgeoapi.dto;

public class InterventoForLayerDto extends PfaForLayerDto{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5844422456323397928L;
	
	private String codiceIntervento;
	private String tipo;
	private String dataInserimento;
	private Integer fkIntervento;
	
	
	
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
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
		
	
	public String getDataInserimento() {
		return dataInserimento;
	}
	/**
	 * @param dataInserimento the dataInserimento to set
	 */
	public void setDataInserimento(String dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	
	
}
