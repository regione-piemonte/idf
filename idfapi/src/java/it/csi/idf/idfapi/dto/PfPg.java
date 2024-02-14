/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.time.LocalDate;
import java.util.Objects;

public class PfPg {
	private Integer idSoggettoPf;
	private Integer idSoggettoPg;
	private LocalDate dataInizioValidita;
	private LocalDate dataFineValidita;
	private LocalDate dataInserimento;
	private LocalDate dataAggiornamento;
	private Integer fkConfigUtente;
	
	public Integer getIdSoggettoPf() {
		return idSoggettoPf;
	}
	public void setIdSoggettoPf(Integer idSoggettoPf) {
		this.idSoggettoPf = idSoggettoPf;
	}
	public Integer getIdSoggettoPg() {
		return idSoggettoPg;
	}
	public void setIdSoggettoPg(Integer idSoggettoPg) {
		this.idSoggettoPg = idSoggettoPg;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataAggiornamento == null) ? 0 : dataAggiornamento.hashCode());
		result = prime * result + ((dataFineValidita == null) ? 0 : dataFineValidita.hashCode());
		result = prime * result + ((dataInizioValidita == null) ? 0 : dataInizioValidita.hashCode());
		result = prime * result + ((dataInserimento == null) ? 0 : dataInserimento.hashCode());
		result = prime * result + ((fkConfigUtente == null) ? 0 : fkConfigUtente.hashCode());
		result = prime * result + ((idSoggettoPf == null) ? 0 : idSoggettoPf.hashCode());
		result = prime * result + ((idSoggettoPg == null) ? 0 : idSoggettoPg.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
		      return true;
		    }
		    if (obj == null || getClass() != obj.getClass()) {
		      return false;
		    }
		    PfPg pfPg = (PfPg) obj;
		    
		return Objects.equals(idSoggettoPf, pfPg.idSoggettoPf) && Objects.equals(idSoggettoPg, pfPg.idSoggettoPg)
				&& Objects.equals(dataInizioValidita, pfPg.dataInizioValidita)
				&& Objects.equals(dataFineValidita, pfPg.dataFineValidita)
				&& Objects.equals(dataInserimento, pfPg.dataInserimento)
				&& Objects.equals(dataAggiornamento, pfPg.dataAggiornamento)
				&& Objects.equals(fkConfigUtente, pfPg.fkConfigUtente);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class TSoggetto {\n");

		sb.append("    idSoggettoPf: ").append(idSoggettoPf).append("\n");
		sb.append("    idSoggettoPg: ").append(idSoggettoPg).append("\n");
		sb.append("    dataInizioValidita: ").append(dataInizioValidita).append("\n");
		sb.append("    dataFineValidita: ").append(dataFineValidita).append("\n");
		sb.append("    dataInserimento: ").append(dataInserimento).append("\n");
		sb.append("    dataAggiornamento: ").append(dataAggiornamento).append("\n");
		sb.append("    fkConfigUtente: ").append(fkConfigUtente).append("\n");

		sb.append("}");
		return sb.toString();
	}
}
