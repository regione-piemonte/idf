/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.time.LocalDate;

public class ParamtrasfTrasformazion {
	
	public ParamtrasfTrasformazion() {}
	
	public ParamtrasfTrasformazion(int idParametroTrasf, int idIntervento) {
		this.idParametroTrasf = idParametroTrasf;
		this.idIntervento = idIntervento;
	}
	
	private Integer idParametroTrasf;
	private Integer idIntervento;
	private LocalDate dataInserimento;
	private LocalDate dataAggiornamento;
	private Integer fkConfigUtente;
	
	public Integer getIdParametroTrasf(){
		return idParametroTrasf;
	}
	public void setIdParameroTrasf(Integer idParameroTrasf) {
		this.idParametroTrasf = idParameroTrasf;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idIntervento == null) ? 0 : idIntervento.hashCode());
		result = prime * result + ((idParametroTrasf == null) ? 0 : idParametroTrasf.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParamtrasfTrasformazion other = (ParamtrasfTrasformazion) obj;
		if (idIntervento == null) {
			if (other.idIntervento != null)
				return false;
		} else if (!idIntervento.equals(other.idIntervento)) {
			return false;
		}
		if (idParametroTrasf == null) {
			if (other.idParametroTrasf != null)
				return false;
		} else if (!idParametroTrasf.equals(other.idParametroTrasf)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ParamtrasfTrasformazion [idParametroTrasf=");
		builder.append(idParametroTrasf);
		builder.append(", idIntervento=");
		builder.append(idIntervento);
		builder.append(", dataInserimento=");
		builder.append(dataInserimento);
		builder.append(", dataAggiornamento=");
		builder.append(dataAggiornamento);
		builder.append(", fkConfigUtente=");
		builder.append(fkConfigUtente);
		builder.append("]");
		return builder.toString();
	}
}
