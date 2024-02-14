/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.time.LocalDate;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import it.csi.idf.idfapi.config.LocalDateDefaultDeserializer;
import it.csi.idf.idfapi.config.LocalDateSerializer;

public class InvioIstanzaDTO {
	
	public InvioIstanzaDTO() {}
	
	public InvioIstanzaDTO(LocalDate dataInvio) {
		this.dataInvio = dataInvio;
	}
	
	private LocalDate dataInvio;
	
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDefaultDeserializer.class)
	public LocalDate getDataInvio() {
		return dataInvio;
	}

	public void setDataInvio(LocalDate dataInvio) {
		this.dataInvio = dataInvio;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InvioIstanzaDTO [dataInvio=");
		builder.append(dataInvio);
		builder.append("]");
		return builder.toString();
	}
}
