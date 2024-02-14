/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.service.helper.dto;

import java.io.Serializable;

public class MetadatiIndexDelega implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String idConfigUtente;
	
	
	public static final String META_ID_CONFIG_UTENTE = "deleghe:idConfigUtente";


	public String getIdConfigUtente() {
		return idConfigUtente;
	}


	public void setIdConfigUtente(String idConfigUtente) {
		this.idConfigUtente = idConfigUtente;
	}

}
