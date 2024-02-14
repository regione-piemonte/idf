/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.util.Objects;

public class TipoParametroTrasf {
	
	private Integer idTipoParametroTrasf;
	private String tipoParemetroTrasf;
	private Integer mtdOrdinamento;
	private Byte flgVisible;
	
	public TipoParametroTrasf() {
		super();
	}
	
	public TipoParametroTrasf(Integer idTipoParametroTrasf, String tipoParemetroTrasf, Integer mtdOrdinamento, Byte flgVisible) {
		super();
		this.idTipoParametroTrasf = idTipoParametroTrasf;
		this.tipoParemetroTrasf = tipoParemetroTrasf;
		this.mtdOrdinamento = mtdOrdinamento;
		this.flgVisible = flgVisible;
	}
	
	public Integer getIdTipoParametroTrasf() {
		return idTipoParametroTrasf;
	}
	public void setIdTipoParametroTrasf(Integer idTipoParametroTrasf) {
		this.idTipoParametroTrasf = idTipoParametroTrasf;
	}
	public String getTipoParemetroTrasf() {
		return tipoParemetroTrasf;
	}
	public void setTipoParemetroTrasf(String tipoParemetroTrasf) {
		this.tipoParemetroTrasf = tipoParemetroTrasf;
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
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		TipoParametroTrasf tipoParametro = (TipoParametroTrasf) o;
		return Objects.equals(idTipoParametroTrasf, tipoParametro.idTipoParametroTrasf)
			&& Objects.equals(tipoParemetroTrasf, tipoParametro.tipoParemetroTrasf)
			&& Objects.equals(mtdOrdinamento, tipoParametro.mtdOrdinamento)
			&& Objects.equals(flgVisible, tipoParametro.flgVisible);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idTipoParametroTrasf, tipoParemetroTrasf, mtdOrdinamento, flgVisible);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class TipoParametroTrasf {\n");
		sb.append("    idTipoParametroTrasf: ").append(idTipoParametroTrasf).append("\n");
		sb.append("    tipoParemetroTrasf: ").append(tipoParemetroTrasf).append("\n");
		sb.append("    mtdOrdinamento: ").append(mtdOrdinamento).append("\n");
		sb.append("    flgVisible: ").append(flgVisible).append("\n");
		sb.append("}");
		return sb.toString();
	}
	
}
