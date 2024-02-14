/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import it.csi.idf.idfapi.config.LocalDateDeserializer;
import it.csi.idf.idfapi.config.LocalDateSerializer;

public class ChiusuraInformazioni {

	private LocalDate dataInizio;
	private LocalDate dataFine;
	private BigDecimal superficieRealeTagliata;
	private BigDecimal superficieTagliataInRiduzione;
	private Integer stimaValoreLotto;
	private Integer valoreDellAsta;
	private String flgConformeDeroga;

	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	public LocalDate getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}
	
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	public LocalDate getDataFine() {
		return dataFine;
	}

	public void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
	}

	public BigDecimal getSuperficieRealeTagliata() {
		return superficieRealeTagliata;
	}

	public void setSuperficieRealeTagliata(BigDecimal superficieRealeTagliata) {
		this.superficieRealeTagliata = superficieRealeTagliata;
	}

	public Integer getStimaValoreLotto() {
		return stimaValoreLotto;
	}

	public void setStimaValoreLotto(Integer stimaValoreLotto) {
		this.stimaValoreLotto = stimaValoreLotto;
	}

	public Integer getValoreDellAsta() {
		return valoreDellAsta;
	}

	public void setValoreDellAsta(Integer valoreDellAsta) {
		this.valoreDellAsta = valoreDellAsta;
	}

	public BigDecimal getSuperficieTagliataInRiduzione() {
		return superficieTagliataInRiduzione;
	}

	public String getFlgConformeDeroga() {
		return flgConformeDeroga;
	}

	public void setFlgConformeDeroga(String flgConformeDeroga) {
		this.flgConformeDeroga = flgConformeDeroga;
	}

	public void setSuperficieTagliataInRiduzione(BigDecimal superficieTagliataInRiduzione) {
		this.superficieTagliataInRiduzione = superficieTagliataInRiduzione;
	}

}
