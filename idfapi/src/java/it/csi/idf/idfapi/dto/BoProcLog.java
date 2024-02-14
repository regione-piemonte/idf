/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.util.Date;

public class BoProcLog {
	private Integer idCnfBoprocLog;
	private String fkIstanza;
	private Integer fkAmbitoIstanza;
	private Date dataInizio;
	private Date dataFine;
	private Integer fkStepBoproc;
	private String esito;
	private String noteStepBoproc;
	private String noteErroreBoproc;
	private Integer countTentativo;
	private String codErroreBoproc;
	public Integer getIdCnfBoprocLog() {
		return idCnfBoprocLog;
	}
	public String getFkIstanza() {
		return fkIstanza;
	}
	public Integer getFkAmbitoIstanza() {
		return fkAmbitoIstanza;
	}
	public Date getDataInizio() {
		return dataInizio;
	}
	public Date getDataFine() {
		return dataFine;
	}
	public Integer getFkStepBoproc() {
		return fkStepBoproc;
	}
	public String getEsito() {
		return esito;
	}
	public String getNoteStepBoproc() {
		return noteStepBoproc;
	}
	public String getNoteErroreBoproc() {
		return noteErroreBoproc;
	}
	public Integer getCountTentativo() {
		return countTentativo;
	}
	public String getCodErroreBoproc() {
		return codErroreBoproc;
	}
	public void setIdCnfBoprocLog(Integer idCnfBoprocLog) {
		this.idCnfBoprocLog = idCnfBoprocLog;
	}
	public void setFkIstanza(String fkIstanza) {
		this.fkIstanza = fkIstanza;
	}
	public void setFkAmbitoIstanza(Integer fkAmbitoIstanza) {
		this.fkAmbitoIstanza = fkAmbitoIstanza;
	}
	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}
	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}
	public void setFkStepBoproc(Integer fkStepBoproc) {
		this.fkStepBoproc = fkStepBoproc;
	}
	public void setEsito(String esito) {
		this.esito = esito;
	}
	public void setNoteStepBoproc(String noteStepBoproc) {
		this.noteStepBoproc = noteStepBoproc;
	}
	public void setNoteErroreBoproc(String noteErroreBoproc) {
		this.noteErroreBoproc = noteErroreBoproc;
	}
	public void setCountTentativo(Integer countTentativo) {
		this.countTentativo = countTentativo;
	}
	public void setCodErroreBoproc(String codErroreBoproc) {
		this.codErroreBoproc = codErroreBoproc;
	}
	
}
