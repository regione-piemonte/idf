/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.sql.Timestamp;

public class PopolamentoSeme {
	
	private Integer idPopolamentoSeme;
	private String codiceAmministrativo;
	private String denominazione;
	private String specieIdonee;
	private Timestamp dataModifica;
	private Timestamp dataFineValidita;
	//private Object geometria;
	
	public Integer getIdPopolamentoSeme() {
		return idPopolamentoSeme;
	}
	public void setIdPopolamentoSeme(Integer idPopolamentoSeme) {
		this.idPopolamentoSeme = idPopolamentoSeme;
	}
	public String getCodiceAmministrativo() {
		return codiceAmministrativo;
	}
	public void setCodiceAmministrativo(String codiceAmministrativo) {
		this.codiceAmministrativo = codiceAmministrativo;
	}
	public String getDenominazione() {
		return denominazione;
	}
	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}
	public String getSpecieIdonee() {
		return specieIdonee;
	}
	public void setSpecieIdonee(String specieIdonee) {
		this.specieIdonee = specieIdonee;
	}
	public Timestamp getDataModifica() {
		return dataModifica;
	}
	public void setDataModifica(Timestamp dataModifica) {
		this.dataModifica = dataModifica;
	}
	public Timestamp getDataFineValidita() {
		return dataFineValidita;
	}
	public void setDataFineValidita(Timestamp dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PopolamentoSeme [idPopolamentoSeme=");
		builder.append(idPopolamentoSeme);
		builder.append(", codiceAmministrativo=");
		builder.append(codiceAmministrativo);
		builder.append(", denominazione=");
		builder.append(denominazione);
		builder.append(", specieIdonee=");
		builder.append(specieIdonee);
		builder.append(", dataModifica=");
		builder.append(dataModifica);
		builder.append(", dataFineValidita=");
		builder.append(dataFineValidita);
		builder.append("]");
		return builder.toString();
	}
}
