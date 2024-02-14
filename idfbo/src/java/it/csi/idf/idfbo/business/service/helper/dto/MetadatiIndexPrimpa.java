/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.business.service.helper.dto;

import java.io.Serializable;

public class MetadatiIndexPrimpa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2285408933626327972L;
	
	private String idTipoAllegato;
	private String noteAllegato;
	private String dataInserimento;
	private String dataCancellazione;
	
	private String idIstanza;
	
	public static final String META_ID_TIPO_ALLEGATO = "primpa:idTipoAllegato";
	public static final String META_NOTE_ALLEGATO = "primpa:noteAllegato";
	public static final String META_DATA_INSERIMENTO = "primpa:dataInserimento";
	public static final String META_DATA_CANCELLAZIONE = "primpa:dataCancellazione";
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
	 * @return the noteAllegato
	 */
	public String getNoteAllegato() {
		return noteAllegato;
	}
	/**
	 * @param noteAllegato the noteAllegato to set
	 */
	public void setNoteAllegato(String noteAllegato) {
		this.noteAllegato = noteAllegato;
	}
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
	 * @return the dataCancellazione
	 */
	public String getDataCancellazione() {
		return dataCancellazione;
	}
	/**
	 * @param dataCancellazione the dataCancellazione to set
	 */
	public void setDataCancellazione(String dataCancellazione) {
		this.dataCancellazione = dataCancellazione;
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
	
	
	
}
