/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import org.codehaus.jackson.annotate.JsonIgnore;

public class TipoIntervento {
	
	private Integer idTipoIntervento;
	private String descrTipoIntervento;
	private String codTipoIntervento;

	@JsonIgnore
	private Integer mtdOrdinamento;
	@JsonIgnore
	private Integer fkMacroIntervento;
	@JsonIgnore
	private Integer fkConfigIpla;
	@JsonIgnore
	private Byte flgVisibile;
	
	public Integer getIdTipoIntervento() {
		return idTipoIntervento;
	}
	public void setIdTipoIntervento(Integer idTipoIntervento) {
		this.idTipoIntervento = idTipoIntervento;
	}
	public String getDescrTipoIntervento() {
		return descrTipoIntervento;
	}
	public void setDescrTipoIntervento(String descrTipoIntervento) {
		this.descrTipoIntervento = descrTipoIntervento;
	}
	public String getCodTipoIntervento() {
		return codTipoIntervento;
	}
	public void setCodTipoIntervento(String codTipoIntervento) {
		this.codTipoIntervento = codTipoIntervento;
	}
	public Integer getMtdOrdinamento() {
		return mtdOrdinamento;
	}
	public void setMtdOrdinamento(Integer mtdOrdinamento) {
		this.mtdOrdinamento = mtdOrdinamento;
	}
	public Integer getFkMacroIntervento() {
		return fkMacroIntervento;
	}
	public void setFkMacroIntervento(Integer fkMacroIntervento) {
		this.fkMacroIntervento = fkMacroIntervento;
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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TipoIntervento [idTipoIntervento=");
		builder.append(idTipoIntervento);
		builder.append(", descrTipoIntervento=");
		builder.append(descrTipoIntervento);
		builder.append(", codTipoIntervento=");
		builder.append(codTipoIntervento);
		builder.append(", mtdOrdinamento=");
		builder.append(mtdOrdinamento);
		builder.append(", fkMacroIntervento=");
		builder.append(fkMacroIntervento);
		builder.append(", fkConfigIpla=");
		builder.append(fkConfigIpla);
		builder.append(", flgVisibile=");
		builder.append(flgVisibile);
		builder.append("]");
		return builder.toString();
	}
}
