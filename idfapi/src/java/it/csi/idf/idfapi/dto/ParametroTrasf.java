/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class ParametroTrasf {
	
	private Integer idParametroTrasf;
	private Integer fkTipoParametroTrasf;
	private String descParametroTrasf;
	private Integer mtdOrdinamento;
	private Byte flgVisible;
	private BigDecimal valore;
	
	public ParametroTrasf() {
		super();
	}
	
	public ParametroTrasf(Integer idParametroTrasf, Integer fkTipoParametroTrasf, String descParametroTrasf, Integer mtdOrdinamento, Byte flgVisible, BigDecimal valore) {
		super();
		this.idParametroTrasf = idParametroTrasf;
		this.fkTipoParametroTrasf = fkTipoParametroTrasf;
		this.descParametroTrasf = descParametroTrasf;
		this.mtdOrdinamento = mtdOrdinamento;
		this.flgVisible = flgVisible;
		this.valore = valore;
	}
	public Integer getIdParametroTrasf() {
		return idParametroTrasf;
	}
	public void setIdParametroTrasf(Integer idParametroTrasf) {
		this.idParametroTrasf = idParametroTrasf;
	}
	public Integer getFkTipoParametroTrasf() {
		return fkTipoParametroTrasf;
	}
	public void setFkTipoParametroTrasf(Integer fkTipoParametroTrasf) {
		this.fkTipoParametroTrasf = fkTipoParametroTrasf;
	}
	public String getDescParametroTrasf() {
		return descParametroTrasf;
	}
	public void setDescParametroTrasf(String descParametroTrasf) {
		this.descParametroTrasf = descParametroTrasf;
	}
	public Integer getMtdOrdinamento() {
		return mtdOrdinamento;
	}
	public void setMtdOrdinamento(Integer mtdOrdinamento) {
		this.mtdOrdinamento = mtdOrdinamento;
	}
	public Byte getFlgVisible() {
		return flgVisible;
	}
	public void setFlgVisible(Byte flgVisible) {
		this.flgVisible = flgVisible;
	}
	public BigDecimal getValore() {
		return valore;
	}
	public void setValore(BigDecimal valore) {
		this.valore = valore;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ParametroTrasf parametroTrasf = (ParametroTrasf) o;
		return Objects.equals(idParametroTrasf, parametroTrasf.idParametroTrasf)
			&& Objects.equals(fkTipoParametroTrasf, parametroTrasf.fkTipoParametroTrasf)
			&& Objects.equals(descParametroTrasf, parametroTrasf.descParametroTrasf)
			&& Objects.equals(mtdOrdinamento, parametroTrasf.mtdOrdinamento)
			&& Objects.equals(flgVisible, parametroTrasf.flgVisible)
			&& Objects.equals(valore, parametroTrasf.valore);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idParametroTrasf, fkTipoParametroTrasf, descParametroTrasf, mtdOrdinamento, flgVisible, valore);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ParametroTrasf {\n");
		sb.append("    idParametroTrasf: ").append(idParametroTrasf).append("\n");
		sb.append("    fkTipoParametroTrasf: ").append(fkTipoParametroTrasf).append("\n");
		sb.append("    descParametroTrasf: ").append(descParametroTrasf).append("\n");
		sb.append("    mtdOrdinamento: ").append(mtdOrdinamento).append("\n");
		sb.append("    flgVisible: ").append(flgVisible).append("\n");
		sb.append("    valore: ").append(valore).append("\n");
		sb.append("}");
		return sb.toString();
	}
}
