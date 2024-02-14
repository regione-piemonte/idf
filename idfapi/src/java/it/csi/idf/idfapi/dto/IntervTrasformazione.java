/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class IntervTrasformazione {
	
	private Integer idIntervento;
	private String flgCompensazione;
	private Byte flgArt7A;
	private Byte flgArt7B;
	private Byte flgArt7C;
	private Byte flgArt7D;
	private Byte flgArt7A21;
	private Byte flgArt7B21;
	private Byte flgArt7C21;
	private Byte flgArt7D21;
	private Byte flgArt7DTer21;
	private Byte flgArt7DQuater21;
	private Byte flgArt7DQuinquies21;
	private Byte flgProprieta;
	private Byte flgDissensi;
	private Byte flgAutPaesaggistica;
	private LocalDate dataAutPaesaggistica;
	private String nrAutPaesaggistica;
	private String enteAutPaesaggistica;
	private Byte flgVincIdro;
	private Byte flgAutVidro;
	private LocalDate dataAutVidro;
	private String nrAutVidro;
	private Byte flgAutIncidenza;
	private LocalDate dataAutIncidenza;
	private String nrAutIncidenza;
	private String enteAutIncidenza;
	private String enteAutVidro;
	private Byte flgCauzioneCf;
	private Byte flgVersamentoCm;
	private String altriPareri;
	private String noteDichiarazione;
	private BigDecimal compenzazioneEuro;	
	private BigDecimal compenzazioneEuroReale;
	private String noteCompenzazione;	
	private LocalDate dataInserimento;
	private LocalDate dataAggiornamento;
	private Integer fkConfigUtente;
	
	
	
	public Integer getIdIntervento() {
		return idIntervento;
	}

	public String getFlgCompensazione() {
		return flgCompensazione;
	}

	public Byte getFlgArt7A() {
		return flgArt7A;
	}

	public Byte getFlgArt7B() {
		return flgArt7B;
	}

	public Byte getFlgArt7C() {
		return flgArt7C;
	}

	public Byte getFlgArt7D() {
		return flgArt7D;
	}

	public Byte getFlgArt7A21() {
		return flgArt7A21;
	}

	public Byte getFlgArt7B21() {
		return flgArt7B21;
	}

	public Byte getFlgArt7C21() {
		return flgArt7C21;
	}

	public Byte getFlgArt7D21() {
		return flgArt7D21;
	}

	public Byte getFlgArt7DTer21() {
		return flgArt7DTer21;
	}

	public Byte getFlgArt7DQuater21() {
		return flgArt7DQuater21;
	}

	public Byte getFlgArt7DQuinquies21() {
		return flgArt7DQuinquies21;
	}

	public Byte getFlgProprieta() {
		return flgProprieta;
	}

	public Byte getFlgDissensi() {
		return flgDissensi;
	}

	public Byte getFlgAutPaesaggistica() {
		return flgAutPaesaggistica;
	}

	public LocalDate getDataAutPaesaggistica() {
		return dataAutPaesaggistica;
	}

	public String getNrAutPaesaggistica() {
		return nrAutPaesaggistica;
	}

	public String getEnteAutPaesaggistica() {
		return enteAutPaesaggistica;
	}

	public Byte getFlgVincIdro() {
		return flgVincIdro;
	}

	public Byte getFlgAutVidro() {
		return flgAutVidro;
	}

	public LocalDate getDataAutVidro() {
		return dataAutVidro;
	}

	public String getNrAutVidro() {
		return nrAutVidro;
	}

	public Byte getFlgAutIncidenza() {
		return flgAutIncidenza;
	}

	public LocalDate getDataAutIncidenza() {
		return dataAutIncidenza;
	}

	public String getNrAutIncidenza() {
		return nrAutIncidenza;
	}

	public String getEnteAutIncidenza() {
		return enteAutIncidenza;
	}

	public String getEnteAutVidro() {
		return enteAutVidro;
	}

	public Byte getFlgCauzioneCf() {
		return flgCauzioneCf;
	}

	public Byte getFlgVersamentoCm() {
		return flgVersamentoCm;
	}

	public String getAltriPareri() {
		return altriPareri;
	}

	public String getNoteDichiarazione() {
		return noteDichiarazione;
	}

	public BigDecimal getCompenzazioneEuro() {
		return compenzazioneEuro;
	}

	public BigDecimal getCompenzazioneEuroReale() {
		return compenzazioneEuroReale;
	}

	public String getNoteCompenzazione() {
		return noteCompenzazione;
	}

	public void setNoteCompenzazione(String noteCompenzazione) {
		this.noteCompenzazione = noteCompenzazione;
	}

	public void setCompenzazioneEuroReale(BigDecimal compenzazioneEuroReale) {
		this.compenzazioneEuroReale = compenzazioneEuroReale;
	}

	

	public LocalDate getDataInserimento() {
		return dataInserimento;
	}

	public LocalDate getDataAggiornamento() {
		return dataAggiornamento;
	}

	public Integer getFkConfigUtente() {
		return fkConfigUtente;
	}

	public void setIdIntervento(Integer idIntervento) {
		this.idIntervento = idIntervento;
	}

	public void setFlgCompensazione(String flgCompensazione) {
		this.flgCompensazione = flgCompensazione;
	}

	public void setFlgArt7A(Byte flgArt7A) {
		this.flgArt7A = flgArt7A;
	}

	public void setFlgArt7B(Byte flgArt7B) {
		this.flgArt7B = flgArt7B;
	}

	public void setFlgArt7C(Byte flgArt7C) {
		this.flgArt7C = flgArt7C;
	}

	public void setFlgArt7D(Byte flgArt7D) {
		this.flgArt7D = flgArt7D;
	}

	public void setFlgArt7A21(Byte flgArt7A21) {
		this.flgArt7A21 = flgArt7A21;
	}

	public void setFlgArt7B21(Byte flgArt7B21) {
		this.flgArt7B21 = flgArt7B21;
	}

	public void setFlgArt7C21(Byte flgArt7C21) {
		this.flgArt7C21 = flgArt7C21;
	}

	public void setFlgArt7D21(Byte flgArt7D21) {
		this.flgArt7D21 = flgArt7D21;
	}

	public void setFlgArt7DTer21(Byte flgArt7DTer21) {
		this.flgArt7DTer21 = flgArt7DTer21;
	}

	public void setFlgArt7DQuater21(Byte flgArt7DQuater21) {
		this.flgArt7DQuater21 = flgArt7DQuater21;
	}

	public void setFlgArt7DQuinquies21(Byte flgArt7DQuinquies21) {
		this.flgArt7DQuinquies21 = flgArt7DQuinquies21;
	}

	public void setFlgProprieta(Byte flgProprieta) {
		this.flgProprieta = flgProprieta;
	}

	public void setFlgDissensi(Byte flgDissensi) {
		this.flgDissensi = flgDissensi;
	}

	public void setFlgAutPaesaggistica(Byte flgAutPaesaggistica) {
		this.flgAutPaesaggistica = flgAutPaesaggistica;
	}

	public void setDataAutPaesaggistica(LocalDate dataAutPaesaggistica) {
		this.dataAutPaesaggistica = dataAutPaesaggistica;
	}

	public void setNrAutPaesaggistica(String nrAutPaesaggistica) {
		this.nrAutPaesaggistica = nrAutPaesaggistica;
	}

	public void setEnteAutPaesaggistica(String enteAutPaesaggistica) {
		this.enteAutPaesaggistica = enteAutPaesaggistica;
	}

	public void setFlgVincIdro(Byte flgVincIdro) {
		this.flgVincIdro = flgVincIdro;
	}

	public void setFlgAutVidro(Byte flgAutVidro) {
		this.flgAutVidro = flgAutVidro;
	}

	public void setDataAutVidro(LocalDate dataAutVidro) {
		this.dataAutVidro = dataAutVidro;
	}

	public void setNrAutVidro(String nrAutVidro) {
		this.nrAutVidro = nrAutVidro;
	}

	public void setFlgAutIncidenza(Byte flgAutIncidenza) {
		this.flgAutIncidenza = flgAutIncidenza;
	}

	public void setDataAutIncidenza(LocalDate dataAutIncidenza) {
		this.dataAutIncidenza = dataAutIncidenza;
	}

	public void setNrAutIncidenza(String nrAutIncidenza) {
		this.nrAutIncidenza = nrAutIncidenza;
	}

	public void setEnteAutIncidenza(String enteAutIncidenza) {
		this.enteAutIncidenza = enteAutIncidenza;
	}

	public void setEnteAutVidro(String enteAutVidro) {
		this.enteAutVidro = enteAutVidro;
	}

	public void setFlgCauzioneCf(Byte flgCauzioneCf) {
		this.flgCauzioneCf = flgCauzioneCf;
	}

	public void setFlgVersamentoCm(Byte flgVersamentoCm) {
		this.flgVersamentoCm = flgVersamentoCm;
	}

	public void setAltriPareri(String altriPareri) {
		this.altriPareri = altriPareri;
	}

	public void setNoteDichiarazione(String noteDichiarazione) {
		this.noteDichiarazione = noteDichiarazione;
	}

	public void setCompenzazioneEuro(BigDecimal compenzazioneEuro) {
		this.compenzazioneEuro = compenzazioneEuro;
	}

	public void setDataInserimento(LocalDate dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public void setDataAggiornamento(LocalDate dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}

	public void setFkConfigUtente(Integer fkConfigUtente) {
		this.fkConfigUtente = fkConfigUtente;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((altriPareri == null) ? 0 : altriPareri.hashCode());
		result = prime * result + ((compenzazioneEuro == null) ? 0 : compenzazioneEuro.hashCode());
		result = prime * result + ((compenzazioneEuroReale == null) ? 0 : compenzazioneEuroReale.hashCode());
		result = prime * result + ((noteCompenzazione == null) ? 0 : noteCompenzazione.hashCode());
		result = prime * result + ((dataAggiornamento == null) ? 0 : dataAggiornamento.hashCode());
		result = prime * result + ((dataAutIncidenza == null) ? 0 : dataAutIncidenza.hashCode());
		result = prime * result + ((dataAutPaesaggistica == null) ? 0 : dataAutPaesaggistica.hashCode());
		result = prime * result + ((dataAutVidro == null) ? 0 : dataAutVidro.hashCode());
		result = prime * result + ((dataInserimento == null) ? 0 : dataInserimento.hashCode());
		result = prime * result + ((enteAutIncidenza == null) ? 0 : enteAutIncidenza.hashCode());
		result = prime * result + ((enteAutPaesaggistica == null) ? 0 : enteAutPaesaggistica.hashCode());
		result = prime * result + ((enteAutVidro == null) ? 0 : enteAutVidro.hashCode());
		result = prime * result + ((fkConfigUtente == null) ? 0 : fkConfigUtente.hashCode());
		result = prime * result + ((flgArt7A == null) ? 0 : flgArt7A.hashCode());
		result = prime * result + ((flgArt7A21 == null) ? 0 : flgArt7A21.hashCode());
		result = prime * result + ((flgArt7B == null) ? 0 : flgArt7B.hashCode());
		result = prime * result + ((flgArt7B21 == null) ? 0 : flgArt7B21.hashCode());
		result = prime * result + ((flgArt7C == null) ? 0 : flgArt7C.hashCode());
		result = prime * result + ((flgArt7C21 == null) ? 0 : flgArt7C21.hashCode());
		result = prime * result + ((flgArt7D == null) ? 0 : flgArt7D.hashCode());
		result = prime * result + ((flgArt7D21 == null) ? 0 : flgArt7D21.hashCode());
		result = prime * result + ((flgArt7DQuater21 == null) ? 0 : flgArt7DQuater21.hashCode());
		result = prime * result + ((flgArt7DQuinquies21 == null) ? 0 : flgArt7DQuinquies21.hashCode());
		result = prime * result + ((flgArt7DTer21 == null) ? 0 : flgArt7DTer21.hashCode());
		result = prime * result + ((flgAutIncidenza == null) ? 0 : flgAutIncidenza.hashCode());
		result = prime * result + ((flgAutPaesaggistica == null) ? 0 : flgAutPaesaggistica.hashCode());
		result = prime * result + ((flgAutVidro == null) ? 0 : flgAutVidro.hashCode());
		result = prime * result + ((flgCauzioneCf == null) ? 0 : flgCauzioneCf.hashCode());
		result = prime * result + ((flgCompensazione == null) ? 0 : flgCompensazione.hashCode());
		result = prime * result + ((flgDissensi == null) ? 0 : flgDissensi.hashCode());
		result = prime * result + ((flgProprieta == null) ? 0 : flgProprieta.hashCode());
		result = prime * result + ((flgVersamentoCm == null) ? 0 : flgVersamentoCm.hashCode());
		result = prime * result + ((flgVincIdro == null) ? 0 : flgVincIdro.hashCode());
		result = prime * result + ((idIntervento == null) ? 0 : idIntervento.hashCode());
		result = prime * result + ((noteDichiarazione == null) ? 0 : noteDichiarazione.hashCode());
		result = prime * result + ((nrAutIncidenza == null) ? 0 : nrAutIncidenza.hashCode());
		result = prime * result + ((nrAutPaesaggistica == null) ? 0 : nrAutPaesaggistica.hashCode());
		result = prime * result + ((nrAutVidro == null) ? 0 : nrAutVidro.hashCode());
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
		IntervTrasformazione other = (IntervTrasformazione) obj;
		if (altriPareri == null) {
			if (other.altriPareri != null)
				return false;
		} else if (!altriPareri.equals(other.altriPareri))
			return false;
		if (compenzazioneEuro == null) {
			if (other.compenzazioneEuro != null)
				return false;
		} else if (!compenzazioneEuro.equals(other.compenzazioneEuro))
			return false;
		if (compenzazioneEuroReale == null) {
			if (other.compenzazioneEuroReale != null)
				return false;
		} else if (!compenzazioneEuroReale.equals(other.compenzazioneEuroReale))
			return false;
		if (noteCompenzazione == null) {
			if (other.noteCompenzazione != null)
				return false;
		} else if (!noteCompenzazione.equals(other.noteCompenzazione))
			return false;
		if (dataAggiornamento == null) {
			if (other.dataAggiornamento != null)
				return false;
		} else if (!dataAggiornamento.equals(other.dataAggiornamento))
			return false;
		if (dataAutIncidenza == null) {
			if (other.dataAutIncidenza != null)
				return false;
		} else if (!dataAutIncidenza.equals(other.dataAutIncidenza))
			return false;
		if (dataAutPaesaggistica == null) {
			if (other.dataAutPaesaggistica != null)
				return false;
		} else if (!dataAutPaesaggistica.equals(other.dataAutPaesaggistica))
			return false;
		if (dataAutVidro == null) {
			if (other.dataAutVidro != null)
				return false;
		} else if (!dataAutVidro.equals(other.dataAutVidro))
			return false;
		if (dataInserimento == null) {
			if (other.dataInserimento != null)
				return false;
		} else if (!dataInserimento.equals(other.dataInserimento))
			return false;
		if (enteAutIncidenza == null) {
			if (other.enteAutIncidenza != null)
				return false;
		} else if (!enteAutIncidenza.equals(other.enteAutIncidenza))
			return false;
		if (enteAutPaesaggistica == null) {
			if (other.enteAutPaesaggistica != null)
				return false;
		} else if (!enteAutPaesaggistica.equals(other.enteAutPaesaggistica))
			return false;
		if (enteAutVidro == null) {
			if (other.enteAutVidro != null)
				return false;
		} else if (!enteAutVidro.equals(other.enteAutVidro))
			return false;
		if (fkConfigUtente == null) {
			if (other.fkConfigUtente != null)
				return false;
		} else if (!fkConfigUtente.equals(other.fkConfigUtente))
			return false;
		if (flgArt7A == null) {
			if (other.flgArt7A != null)
				return false;
		} else if (!flgArt7A.equals(other.flgArt7A))
			return false;
		if (flgArt7A21 == null) {
			if (other.flgArt7A21 != null)
				return false;
		} else if (!flgArt7A21.equals(other.flgArt7A21))
			return false;
		if (flgArt7B == null) {
			if (other.flgArt7B != null)
				return false;
		} else if (!flgArt7B.equals(other.flgArt7B))
			return false;
		if (flgArt7B21 == null) {
			if (other.flgArt7B21 != null)
				return false;
		} else if (!flgArt7B21.equals(other.flgArt7B21))
			return false;
		if (flgArt7C == null) {
			if (other.flgArt7C != null)
				return false;
		} else if (!flgArt7C.equals(other.flgArt7C))
			return false;
		if (flgArt7C21 == null) {
			if (other.flgArt7C21 != null)
				return false;
		} else if (!flgArt7C21.equals(other.flgArt7C21))
			return false;
		if (flgArt7D == null) {
			if (other.flgArt7D != null)
				return false;
		} else if (!flgArt7D.equals(other.flgArt7D))
			return false;
		if (flgArt7D21 == null) {
			if (other.flgArt7D21 != null)
				return false;
		} else if (!flgArt7D21.equals(other.flgArt7D21))
			return false;
		if (flgArt7DQuater21 == null) {
			if (other.flgArt7DQuater21 != null)
				return false;
		} else if (!flgArt7DQuater21.equals(other.flgArt7DQuater21))
			return false;
		if (flgArt7DQuinquies21 == null) {
			if (other.flgArt7DQuinquies21 != null)
				return false;
		} else if (!flgArt7DQuinquies21.equals(other.flgArt7DQuinquies21))
			return false;
		if (flgArt7DTer21 == null) {
			if (other.flgArt7DTer21 != null)
				return false;
		} else if (!flgArt7DTer21.equals(other.flgArt7DTer21))
			return false;
		if (flgAutIncidenza == null) {
			if (other.flgAutIncidenza != null)
				return false;
		} else if (!flgAutIncidenza.equals(other.flgAutIncidenza))
			return false;
		if (flgAutPaesaggistica == null) {
			if (other.flgAutPaesaggistica != null)
				return false;
		} else if (!flgAutPaesaggistica.equals(other.flgAutPaesaggistica))
			return false;
		if (flgAutVidro == null) {
			if (other.flgAutVidro != null)
				return false;
		} else if (!flgAutVidro.equals(other.flgAutVidro))
			return false;
		if (flgCauzioneCf == null) {
			if (other.flgCauzioneCf != null)
				return false;
		} else if (!flgCauzioneCf.equals(other.flgCauzioneCf))
			return false;
		if (flgCompensazione == null) {
			if (other.flgCompensazione != null)
				return false;
		} else if (!flgCompensazione.equals(other.flgCompensazione))
			return false;
		if (flgDissensi == null) {
			if (other.flgDissensi != null)
				return false;
		} else if (!flgDissensi.equals(other.flgDissensi))
			return false;
		if (flgProprieta == null) {
			if (other.flgProprieta != null)
				return false;
		} else if (!flgProprieta.equals(other.flgProprieta))
			return false;
		if (flgVersamentoCm == null) {
			if (other.flgVersamentoCm != null)
				return false;
		} else if (!flgVersamentoCm.equals(other.flgVersamentoCm))
			return false;
		if (flgVincIdro == null) {
			if (other.flgVincIdro != null)
				return false;
		} else if (!flgVincIdro.equals(other.flgVincIdro))
			return false;
		if (idIntervento == null) {
			if (other.idIntervento != null)
				return false;
		} else if (!idIntervento.equals(other.idIntervento))
			return false;
		if (noteDichiarazione == null) {
			if (other.noteDichiarazione != null)
				return false;
		} else if (!noteDichiarazione.equals(other.noteDichiarazione))
			return false;
		if (nrAutIncidenza == null) {
			if (other.nrAutIncidenza != null)
				return false;
		} else if (!nrAutIncidenza.equals(other.nrAutIncidenza))
			return false;
		if (nrAutPaesaggistica == null) {
			if (other.nrAutPaesaggistica != null)
				return false;
		} else if (!nrAutPaesaggistica.equals(other.nrAutPaesaggistica))
			return false;
		if (nrAutVidro == null) {
			if (other.nrAutVidro != null)
				return false;
		} else if (!nrAutVidro.equals(other.nrAutVidro))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IntervTrasformazione [idIntervento=" + idIntervento + ", flgCompensazione=" + flgCompensazione
				+ ", flgArt7A=" + flgArt7A + ", flgArt7B=" + flgArt7B + ", flgArt7C=" + flgArt7C + ", flgArt7D="
				+ flgArt7D + ", flgArt7A21=" + flgArt7A21 + ", flgArt7B21=" + flgArt7B21 + ", flgArt7C21=" + flgArt7C21
				+ ", flgArt7D21=" + flgArt7D21 + ", flgArt7DTer21=" + flgArt7DTer21 + ", flgArt7DQuater21="
				+ flgArt7DQuater21 + ", flgArt7DQuinquies21=" + flgArt7DQuinquies21 + ", flgProprieta=" + flgProprieta
				+ ", flgDissensi=" + flgDissensi + ", flgAutPaesaggistica=" + flgAutPaesaggistica
				+ ", dataAutPaesaggistica=" + dataAutPaesaggistica + ", nrAutPaesaggistica=" + nrAutPaesaggistica
				+ ", enteAutPaesaggistica=" + enteAutPaesaggistica + ", flgVincIdro=" + flgVincIdro + ", flgAutVidro="
				+ flgAutVidro + ", dataAutVidro=" + dataAutVidro + ", nrAutVidro=" + nrAutVidro + ", flgAutIncidenza="
				+ flgAutIncidenza + ", dataAutIncidenza=" + dataAutIncidenza + ", nrAutIncidenza=" + nrAutIncidenza
				+ ", enteAutIncidenza=" + enteAutIncidenza + ", enteAutVidro=" + enteAutVidro + ", flgCauzioneCf="
				+ flgCauzioneCf + ", flgVersamentoCm=" + flgVersamentoCm + ", altriPareri=" + altriPareri
				+ ", noteDichiarazione=" + noteDichiarazione + ", compenzazioneEuro=" + compenzazioneEuro
				+ ", compenzazioneEuroReale=" + compenzazioneEuroReale + ", noteCompenzazione=" + noteCompenzazione + ", dataInserimento="
				+ dataInserimento + ", dataAggiornamento=" + dataAggiornamento + ", fkConfigUtente=" + fkConfigUtente
				+ "]";
	}

	

	
}
