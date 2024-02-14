/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfgeoapi.dto;

public class EventoForLayerDto extends PfaForLayerDto{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5844422456323397928L;
	
	private String codiceEvento;
	private String tipo;
	
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
	private String dataInserimento;
	private Integer fkEvento;
	/**
	 * @return the dataInserimento
	 */
	public String getDataInserimento() {
		return dataInserimento;
	}
	/**
	 * @param dataInserimento the dataInserimento to set
	 */
	public void setDataInserimento(String dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	/**
	 * @return the fkEvento
	 */
	public Integer getFkEvento() {
		return fkEvento;
	}
	/**
	 * @param fkEvento the fkEvento to set
	 */
	public void setFkEvento(Integer fkEvento) {
		this.fkEvento = fkEvento;
	}
	
	
}
