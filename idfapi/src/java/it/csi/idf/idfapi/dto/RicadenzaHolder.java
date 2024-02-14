/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;

public class RicadenzaHolder {
	
	private String nome;
	private BigDecimal supHa;
	private BigDecimal percRicadenza;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public BigDecimal getSupHa() {
		return supHa;
	}
	public void setSupHa(BigDecimal supHa) {
		this.supHa = supHa;
	}
	public BigDecimal getPercRicadenza() {
		return percRicadenza;
	}
	public void setPercRicadenza(BigDecimal percRicadenza) {
		this.percRicadenza = percRicadenza;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RicadenzaHolder [nome=");
		builder.append(nome);
		builder.append(", supHa=");
		builder.append(supHa);
		builder.append(", percRicadenza=");
		builder.append(percRicadenza);
		builder.append("]");
		return builder.toString();
	}
}
