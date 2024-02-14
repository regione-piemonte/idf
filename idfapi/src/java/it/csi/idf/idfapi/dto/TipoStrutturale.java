/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class TipoStrutturale {
	
	private Integer idTipoStrutturale;
	private String descrTipoStrutturale;
	private String codTipoStrutturale;
	private Integer mtdOrdinamento;
	private Integer fkConfigIpla;
	private Byte flgVisibile;
	
	public Integer getIdTipoStrutturale() {
		return idTipoStrutturale;
	}
	public void setIdTipoStrutturale(Integer idTipoStrutturale) {
		this.idTipoStrutturale = idTipoStrutturale;
	}
	public String getDescrTipoStrutturale() {
		return descrTipoStrutturale;
	}
	public void setDescrTipoStrutturale(String descrTipoStrutturale) {
		this.descrTipoStrutturale = descrTipoStrutturale;
	}
	public String getCodTipoStrutturale() {
		return codTipoStrutturale;
	}
	public void setCodTipoStrutturale(String codTipoStrutturale) {
		this.codTipoStrutturale = codTipoStrutturale;
	}
	public Integer getMtdOrdinamento() {
		return mtdOrdinamento;
	}
	public void setMtdOrdinamento(Integer mtdOrdinamento) {
		this.mtdOrdinamento = mtdOrdinamento;
	}
	public Integer getFkConfigIpla() {
		return fkConfigIpla;
	}
	public void setFkConfigIpla(Integer fkConfigIpla) {
		this.fkConfigIpla = fkConfigIpla;
	}
	public Byte getFlgVisibile() {
		return flgVisibile;
	}
	public void setFlgVisibile(Byte flgVisibile) {
		this.flgVisibile = flgVisibile;
	}
	
	
}
