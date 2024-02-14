/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Intervento {

	private Integer idIntervento;
	private BigDecimal superficieInteressata;
	private String localita;
	private String descrizioneIntervento;
	private Integer fasciaAltimetrica;
	private Integer fkSoggettoProfess;
	private LocalDate dataInizioIntervento;
	private LocalDate dataFineIntervento;
	private LocalDate dataInserimento;
	private LocalDate dataAggiornamento;
	private Integer fkConfigUtenteCompilatore;
	
	public Integer getIdIntervento() {
		return idIntervento;
	}
	public void setIdIntervento(Integer idIntervento) {
		this.idIntervento = idIntervento;
	}
	public BigDecimal getSuperficieInteressata() {
		return superficieInteressata;
	}
	public void setSuperficieInteressata(BigDecimal superficieInteressata) {
		this.superficieInteressata = superficieInteressata;
	}
	public String getLocalita() {
		return localita;
	}
	public void setLocalita(String localita) {
		this.localita = localita;
	}
	public String getDescrizioneIntervento() {
		return descrizioneIntervento;
	}
	public void setDescrizioneIntervento(String descrizioneIntervento) {
		this.descrizioneIntervento = descrizioneIntervento;
	}
	public Integer getFasciaAltimetrica() {
		return fasciaAltimetrica;
	}
	public void setFasciaAltimetrica(Integer fasciaAltimetrica) {
		this.fasciaAltimetrica = fasciaAltimetrica;
	}
	public Integer getFkSoggettoProfess() {
		return fkSoggettoProfess;
	}
	public void setFkSoggettoProfess(Integer fkSoggettoProfess) {
		this.fkSoggettoProfess = fkSoggettoProfess;
	}
	public LocalDate getDataInizioIntervento() {
		return dataInizioIntervento;
	}
	public void setDataInizioIntervento(LocalDate dataInizioIntervento) {
		this.dataInizioIntervento = dataInizioIntervento;
	}
	public LocalDate getDataFineIntervento() {
		return dataFineIntervento;
	}
	public void setDataFineIntervento(LocalDate dataFineIntervento) {
		this.dataFineIntervento = dataFineIntervento;
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
	public Integer getFkConfigUtenteCompilatore() {
		return fkConfigUtenteCompilatore;
	}
	public void setFkConfigUtenteCompilatore(Integer fkConfigUtenteCompilatore) {
		this.fkConfigUtenteCompilatore = fkConfigUtenteCompilatore;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataAggiornamento == null) ? 0 : dataAggiornamento.hashCode());
		result = prime * result + ((dataFineIntervento == null) ? 0 : dataFineIntervento.hashCode());
		result = prime * result + ((dataInizioIntervento == null) ? 0 : dataInizioIntervento.hashCode());
		result = prime * result + ((dataInserimento == null) ? 0 : dataInserimento.hashCode());
		result = prime * result + ((descrizioneIntervento == null) ? 0 : descrizioneIntervento.hashCode());
		result = prime * result + ((fasciaAltimetrica == null) ? 0 : fasciaAltimetrica.hashCode());
		result = prime * result + ((fkConfigUtenteCompilatore == null) ? 0 : fkConfigUtenteCompilatore.hashCode());
		result = prime * result + ((fkSoggettoProfess == null) ? 0 : fkSoggettoProfess.hashCode());
		result = prime * result + ((idIntervento == null) ? 0 : idIntervento.hashCode());
		result = prime * result + ((localita == null) ? 0 : localita.hashCode());
		result = prime * result + ((superficieInteressata == null) ? 0 : superficieInteressata.hashCode());
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
		Intervento other = (Intervento) obj;
		if (dataAggiornamento == null) {
			if (other.dataAggiornamento != null)
				return false;
		} else if (!dataAggiornamento.equals(other.dataAggiornamento))
			return false;
		if (dataFineIntervento == null) {
			if (other.dataFineIntervento != null)
				return false;
		} else if (!dataFineIntervento.equals(other.dataFineIntervento))
			return false;
		if (dataInizioIntervento == null) {
			if (other.dataInizioIntervento != null)
				return false;
		} else if (!dataInizioIntervento.equals(other.dataInizioIntervento))
			return false;
		if (dataInserimento == null) {
			if (other.dataInserimento != null)
				return false;
		} else if (!dataInserimento.equals(other.dataInserimento))
			return false;
		if (descrizioneIntervento == null) {
			if (other.descrizioneIntervento != null)
				return false;
		} else if (!descrizioneIntervento.equals(other.descrizioneIntervento))
			return false;
		if (fasciaAltimetrica == null) {
			if (other.fasciaAltimetrica != null)
				return false;
		} else if (!fasciaAltimetrica.equals(other.fasciaAltimetrica))
			return false;
		if (fkConfigUtenteCompilatore == null) {
			if (other.fkConfigUtenteCompilatore != null)
				return false;
		} else if (!fkConfigUtenteCompilatore.equals(other.fkConfigUtenteCompilatore))
			return false;
		if (fkSoggettoProfess == null) {
			if (other.fkSoggettoProfess != null)
				return false;
		} else if (!fkSoggettoProfess.equals(other.fkSoggettoProfess))
			return false;
		if (idIntervento == null) {
			if (other.idIntervento != null)
				return false;
		} else if (!idIntervento.equals(other.idIntervento))
			return false;
		if (localita == null) {
			if (other.localita != null)
				return false;
		} else if (!localita.equals(other.localita))
			return false;
		if (superficieInteressata == null) {
			if (other.superficieInteressata != null)
				return false;
		} else if (!superficieInteressata.equals(other.superficieInteressata))
			return false;
		return true;
	}
	
	
}
