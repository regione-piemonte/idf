/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class BoOwnerIstanze {

	private ConfigUtente configUtente;
	private TSoggetto tSoggetto;
	
	
	
	public BoOwnerIstanze(ConfigUtente configUtente, TSoggetto tSoggetto) {
		super();
		this.configUtente = configUtente;
		this.tSoggetto = tSoggetto;
	}
	public ConfigUtente getConfigUtente() {
		return configUtente;
	}
	public void setConfigUtente(ConfigUtente configUtente) {
		this.configUtente = configUtente;
	}
	public TSoggetto gettSoggetto() {
		return tSoggetto;
	}
	public void settSoggetto(TSoggetto tSoggetto) {
		this.tSoggetto = tSoggetto;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BoOwnerIstanze [");
		if (configUtente != null) {
			builder.append("configUtente=");
			builder.append(configUtente);
			builder.append(", ");
		}
		if (tSoggetto != null) {
			builder.append("tSoggetto=");
			builder.append(tSoggetto);
		}
		builder.append("]");
		return builder.toString();
	}
	
}
