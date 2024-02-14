/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Popseme {
	
	private Integer idgeoPlPfa;
	private Integer idPopolamentoSeme;
	private LocalDate dataInizioValidita;
	private LocalDate dataFineValidita;
	private BigDecimal supRicadenzaHa;
	private BigDecimal percRicadenza;
	private String denominazione;
	
	public Integer getIdgeoPlPfa() {
		return idgeoPlPfa;
	}
	public void setIdgeoPlPfa(Integer idgeoPlPfa) {
		this.idgeoPlPfa = idgeoPlPfa;
	}
	public Integer getIdPopolamentoSeme() {
		return idPopolamentoSeme;
	}
	public void setIdPopolamentoSeme(Integer idPopolamentoSeme) {
		this.idPopolamentoSeme = idPopolamentoSeme;
	}
	public LocalDate getDataInizioValidita() {
		return dataInizioValidita;
	}
	public void setDataInizioValidita(LocalDate dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}
	public LocalDate getDataFineValidita() {
		return dataFineValidita;
	}
	public void setDataFineValidita(LocalDate dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}
	public BigDecimal getSupRicadenzaHa() {
		return supRicadenzaHa;
	}
	public void setSupRicadenzaHa(BigDecimal supRicadenzaHa) {
		this.supRicadenzaHa = supRicadenzaHa;
	}
	public BigDecimal getPercRicadenza() {
		return percRicadenza;
	}
	public void setPercRicadenza(BigDecimal percRicadenza) {
		this.percRicadenza = percRicadenza;
	}
	
	public String getDenominazione() {
		return denominazione;
	}
	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Popseme [idgeoPlPfa=");
		builder.append(idgeoPlPfa);
		builder.append(", idPopolamentoSeme=");
		builder.append(idPopolamentoSeme);
		builder.append(", dataInizioValidita=");
		builder.append(dataInizioValidita);
		builder.append(", dataFineValidita=");
		builder.append(dataFineValidita);
		builder.append(", supRicadenzaHa=");
		builder.append(supRicadenzaHa);
		builder.append(", percRicadenza=");
		builder.append(percRicadenza);
		builder.append(", denominazione=");
		builder.append(denominazione);
		builder.append("]");
		return builder.toString();
	}
}
