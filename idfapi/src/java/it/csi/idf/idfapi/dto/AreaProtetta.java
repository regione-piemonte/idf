/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AreaProtetta {
	
	private Integer idgeoPlPfa;
	private Integer idAreaProtetta;
	private LocalDate dataInizioValidita;
	private String nomeAreaProtetta;
	private LocalDate dataFineValidita;
	private BigDecimal supRicadenzaHa;
	private BigDecimal percRicadenza;
	
	public Integer getIdgeoPlPfa() {
		return idgeoPlPfa;
	}
	public void setIdgeoPlPfa(Integer idgeoPlPfa) {
		this.idgeoPlPfa = idgeoPlPfa;
	}
	public Integer getIdAreaProtetta() {
		return idAreaProtetta;
	}
	public void setIdAreaProtetta(Integer idAreaProtetta) {
		this.idAreaProtetta = idAreaProtetta;
	}
	public LocalDate getDataInizioValidita() {
		return dataInizioValidita;
	}
	public void setDataInizioValidita(LocalDate dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}
	public String getNomeAreaProtetta() {
		return nomeAreaProtetta;
	}
	public void setNomeAreaProtetta(String nomeAreaProtetta) {
		this.nomeAreaProtetta = nomeAreaProtetta;
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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AreaProtetta [idgeoPlPfa=");
		builder.append(idgeoPlPfa);
		builder.append(", idAreaProtetta=");
		builder.append(idAreaProtetta);
		builder.append(", dataInizioValidita=");
		builder.append(dataInizioValidita);
		builder.append(", nomeAreaProtetta=");
		builder.append(nomeAreaProtetta);
		builder.append(", dataFineValidita=");
		builder.append(dataFineValidita);
		builder.append(", supRicadenzaHa=");
		builder.append(supRicadenzaHa);
		builder.append(", percRicadenza=");
		builder.append(percRicadenza);
		builder.append("]");
		return builder.toString();
	}
}
