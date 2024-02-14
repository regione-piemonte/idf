/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import org.codehaus.jackson.annotate.JsonSetter;

public class SezioneResource {

	
	private Integer id;
	private String codComBelfiore;
	private String codComIstat;
	private String comucomunene;
	private String sezione;
	private String nomeSezione;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCodComBelfiore() {
		return codComBelfiore;
	}
	public void setCodComBelfiore(String codComBelfiore) {
		this.codComBelfiore = codComBelfiore;
	}
	public String getCodComIstat() {
		return codComIstat;
	}
	public void setCodComIstat(String codComIstat) {
		this.codComIstat = codComIstat;
	}
	public String getComucomunene() {
		return comucomunene;
	}
	public void setComucomunene(String comucomunene) {
		this.comucomunene = comucomunene;
	}
	public String getSezione() {
		return sezione;
	}
	public void setSezione(String sezione) {
		this.sezione = sezione;
	}
	public String getNomeSezione() {
		return nomeSezione;
	}
	public void setNomeSezione(String nomeSezione) {
		this.nomeSezione = nomeSezione;
	}
	@Override
	public String toString() {
		return "SezioneResource [id=" + id + ", codComBelfiore=" + codComBelfiore + ", codComIstat=" + codComIstat
				+ ", comucomunene=" + comucomunene + ", sezione=" + sezione + ", nomeSezione=" + nomeSezione + "]";
	}

	

}
