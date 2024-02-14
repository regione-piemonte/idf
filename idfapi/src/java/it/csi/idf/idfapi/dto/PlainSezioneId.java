/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class PlainSezioneId {

	private Integer istanzaId;

	public PlainSezioneId() {
	}

	public PlainSezioneId(int istanzaId) {
		this.istanzaId = istanzaId;
	}

	public Integer getIstanzaId() {
		return istanzaId;
	}

	public void setIstanzaId(Integer istanzaId) {
		this.istanzaId = istanzaId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlainSezioneId [istanzaId=");
		builder.append(istanzaId);
		builder.append("]");
		return builder.toString();
	}
}
