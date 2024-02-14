/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class ParametroTrasfResource {
	
	private Integer idParametroTrasf;
	private Integer fkTipoParametroTrasf;
	private String descParametroTrasf;
	
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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ParametroTrasfResource [idParametroTrasf=");
		builder.append(idParametroTrasf);
		builder.append(", fkTipoParametroTrasf=");
		builder.append(fkTipoParametroTrasf);
		builder.append(", descParametroTrasf=");
		builder.append(descParametroTrasf);
		builder.append("]");
		return builder.toString();
	}
}
