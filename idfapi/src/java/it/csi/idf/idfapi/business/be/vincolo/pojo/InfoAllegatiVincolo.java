/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.pojo;

public class InfoAllegatiVincolo {
	private String nomeAllegato;
	private Boolean checked;
	
	public String getNomeAllegato() {
		return nomeAllegato;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setNomeAllegato(String nomeAllegato) {
		this.nomeAllegato = nomeAllegato;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	
	
}
