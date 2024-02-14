/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.time.LocalDate;

public class DocumentoAllegato {

	private Integer idDocumentoAllegato;
	private Integer idGeoPlPfa;
	private Integer fkIntervento;
	private Integer fkTipoAllegato;
	private String nomeAllegato;
	private Long dimensioneAllegatoKB;
	private LocalDate dataIniziValidita;
	private LocalDate dataFineValidita;
	private LocalDate dataInserimento;
	private LocalDate dataAggiornamento;
	private Integer fkConfigUtente;
	private String note;
	private String uidIndex;
	private String idDocDoqui;
	private String udClassificazioneDoqui;
		
	public String getUidIndex() {
		return uidIndex;
	}
	public void setUidIndex(String uidIndex) {
		this.uidIndex = uidIndex;
	}
	
	public String getUdClassificazioneDoqui() {
		return udClassificazioneDoqui;
	}
	public void setUdClassificazioneDoqui(String udClassificazioneDoqui) {
		this.udClassificazioneDoqui = udClassificazioneDoqui;
	}
	

	
	public Integer getIdDocumentoAllegato() {
		return idDocumentoAllegato;
	}
	public void setIdDocumentoAllegato(Integer idDocumentoAllegato) {
		this.idDocumentoAllegato = idDocumentoAllegato;
	}
	public Integer getIdGeoPlPfa() {
		return idGeoPlPfa;
	}
	public void setIdGeoPlPfa(Integer idGeoPlPfa) {
		this.idGeoPlPfa = idGeoPlPfa;
	}
	public Integer getFkIntervento() {
		return fkIntervento;
	}
	public void setFkIntervento(Integer fkIntervento) {
		this.fkIntervento = fkIntervento;
	}
	public Integer getFkTipoAllegato() {
		return fkTipoAllegato;
	}
	public void setFkTipoAllegato(Integer fkTipoAllegato) {
		this.fkTipoAllegato = fkTipoAllegato;
	}
	public String getNomeAllegato() {
		return nomeAllegato;
	}
	public void setNomeAllegato(String nomeAllegato) {
		this.nomeAllegato = nomeAllegato;
	}
	public Long getDimensioneAllegatoKB() {
		return dimensioneAllegatoKB;
	}
	public void setDimensioneAllegatoKB(Long dimensioneAllegatoKB) {
		this.dimensioneAllegatoKB = dimensioneAllegatoKB;
	}
	public LocalDate getDataIniziValidita() {
		return dataIniziValidita;
	}
	public void setDataIniziValidita(LocalDate dataIniziValidita) {
		this.dataIniziValidita = dataIniziValidita;
	}
	public LocalDate getDataFineValidita() {
		return dataFineValidita;
	}
	public void setDataFineValidita(LocalDate dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
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
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DocumentoAllegato [idDocumentoAllegato=");
		builder.append(idDocumentoAllegato);
		builder.append(", idGeoPlPfa=");
		builder.append(idGeoPlPfa);
		builder.append(", fkIntervento=");
		builder.append(fkIntervento);
		builder.append(", fkTipoAllegato=");
		builder.append(fkTipoAllegato);
		builder.append(", nomeAllegato=");
		builder.append(nomeAllegato);
		builder.append(", dimensioneAllegatoKB=");
		builder.append(dimensioneAllegatoKB);
		builder.append(", dataIniziValidita=");
		builder.append(dataIniziValidita);
		builder.append(", dataFineValidita=");
		builder.append(dataFineValidita);
		builder.append(", dataInserimento=");
		builder.append(dataInserimento);
		builder.append(", dataAggiornamento=");
		builder.append(dataAggiornamento);
		builder.append(", fkConfigUtente=");
		builder.append(fkConfigUtente);
		builder.append(", note=");
		builder.append(note);
		builder.append(", uidIndex=");
		builder.append(uidIndex);
		builder.append("]");
		return builder.toString();
	}
	public String getIdDocDoqui() {
		return idDocDoqui;
	}
	public void setIdDocDoqui(String idDocDoqui) {
		this.idDocDoqui = idDocDoqui;
	}
}
