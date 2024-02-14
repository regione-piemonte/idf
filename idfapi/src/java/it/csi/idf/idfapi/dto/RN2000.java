/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RN2000 {
	
	private Integer idgeoPlPfa;
	private Integer idRn2000;
	private LocalDate dataInizioValidita;
	private String nomeRn2000;
	private LocalDate dataFineValidita;
	private BigDecimal supRicadenzaHa;
	private BigDecimal percRicadenza;
	
	public Integer getIdgeoPlPfa() {
		return idgeoPlPfa;
	}
	public void setIdgeoPlPfa(Integer idgeoPlPfa) {
		this.idgeoPlPfa = idgeoPlPfa;
	}
	public Integer getIdRn2000() {
		return idRn2000;
	}
	public void setIdRn2000(Integer idRn2000) {
		this.idRn2000 = idRn2000;
	}
	public LocalDate getDataInizioValidita() {
		return dataInizioValidita;
	}
	public void setDataInizioValidita(LocalDate dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}
	public String getNomeRn2000() {
		return nomeRn2000;
	}
	public void setNomeRn2000(String nomeRn2000) {
		this.nomeRn2000 = nomeRn2000;
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
		builder.append("RN2000 [idgeoPlPfa=");
		builder.append(idgeoPlPfa);
		builder.append(", idRn2000=");
		builder.append(idRn2000);
		builder.append(", dataInizioValidita=");
		builder.append(dataInizioValidita);
		builder.append(", nomeRn2000=");
		builder.append(nomeRn2000);
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
