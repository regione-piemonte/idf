/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class TipoAds {
	
	private Integer idTipoAds;
	private String codTipoAds;
	private String descrTipoAds;
	private Integer mtdOrdinamento;
	private Byte flgVisibile;
	
	
	public Integer getIdTipoAds() {
		return idTipoAds;
	}
	public void setIdTipoAds(Integer idTipoAds) {
		this.idTipoAds = idTipoAds;
	}
	public String getCodTipoAds() {
		return codTipoAds;
	}
	public void setCodTipoAds(String codTipoAds) {
		this.codTipoAds = codTipoAds;
	}
	public String getDescrTipoAds() {
		return descrTipoAds;
	}
	public void setDescrTipoAds(String descrTipoAds) {
		this.descrTipoAds = descrTipoAds;
	}
	public Integer getMtdOrdinamento() {
		return mtdOrdinamento;
	}
	public void setMtdOrdinamento(Integer mtdOrdinamento) {
		this.mtdOrdinamento = mtdOrdinamento;
	}
	public Byte getFlgVisibile() {
		return flgVisibile;
	}
	public void setFlgVisibile(Byte flgVisibile) {
		this.flgVisibile = flgVisibile;
	}
	
	
}
