/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PropcatastoIntervento {
	
	private Integer idgeoPlPropCatasto;
	private Integer idIntervento;
	private LocalDate dataInserimento;
	private LocalDate dataAggiornamento;
	private Integer fkConfigUtente;
	private BigDecimal supIntervento;
	
	public Integer getIdgeoPlPropCatasto() {
		return idgeoPlPropCatasto;
	}
	public void setIdgeoPlPropCatasto(Integer idgeoPlPropCatasto) {
		this.idgeoPlPropCatasto = idgeoPlPropCatasto;
	}
	public Integer getIdIntervento() {
		return idIntervento;
	}
	public void setIdIntervento(Integer idIntervento) {
		this.idIntervento = idIntervento;
	}
	public LocalDate getDataInserimento() {
		return dataInserimento;
	}
	public void setDataInserimento(LocalDate dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	public LocalDate getDataAggiornamento() {
		return dataAggiornamento;
	}
	public void setDataAggiornamento(LocalDate dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}
	public Integer getFkConfigUtente() {
		return fkConfigUtente;
	}
	public void setFkConfigUtente(Integer fkConfigUtente) {
		this.fkConfigUtente = fkConfigUtente;
	}
	public BigDecimal getSupIntervento() {
		return supIntervento;
	}
	public void setSupIntervento(BigDecimal supIntervento) {
		this.supIntervento = supIntervento;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PropcatastoIntervento [idgeoPlPropCatasto=");
		builder.append(idgeoPlPropCatasto);
		builder.append(", idIntervento=");
		builder.append(idIntervento);
		builder.append(", dataInserimento=");
		builder.append(dataInserimento);
		builder.append(", dataAggiornamento=");
		builder.append(dataAggiornamento);
		builder.append(", fkConfigUtente=");
		builder.append(fkConfigUtente);
		builder.append(", supIntervento=");
		builder.append(supIntervento);
		builder.append("]");
		return builder.toString();
	}
	
}
