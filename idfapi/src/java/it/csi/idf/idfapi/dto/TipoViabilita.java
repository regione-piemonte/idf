/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class TipoViabilita {

	private Integer idTipoViabilita;
	private String codiceTipoViabilita;
	private String tipoViabilita;
	private Integer fkConfigIpla;
	private Integer mtdOrdinamento;
	private Byte flgVisible;


	public Integer getIdTipoViabilita() {
		return idTipoViabilita;
	}

	public void setIdTipoViabilita(Integer idTipoViabilita) {
		this.idTipoViabilita = idTipoViabilita;
	}

	public String getCodiceTipoViabilita() {
		return codiceTipoViabilita;
	}

	public void setCodiceTipoViabilita(String codiceTipoViabilita) {
		this.codiceTipoViabilita = codiceTipoViabilita;
	}

	public String getTipoViabilita() {
		return tipoViabilita;
	}

	public void setTipoViabilita(String tipoViabilita) {
		this.tipoViabilita = tipoViabilita;
	}

	public Integer getFkConfigIpla() {
		return fkConfigIpla;
	}

	public void setFkConfigIpla(Integer fkConfigIpla) {
		this.fkConfigIpla = fkConfigIpla;
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
}
