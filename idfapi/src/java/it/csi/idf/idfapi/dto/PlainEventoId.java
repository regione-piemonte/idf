/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class PlainEventoId {
	
	public PlainEventoId() {}
	
	public PlainEventoId(int eventoId) {
		this.eventoId = eventoId;
	}
	
	private Integer eventoId;

	public Integer getEventoId() {
		return eventoId;
	}

	public void setEventoId(Integer eventoId) {
		this.eventoId = eventoId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlainEventoId [eventoId=");
		builder.append(eventoId);
		builder.append("]");
		return builder.toString();
	}
}
