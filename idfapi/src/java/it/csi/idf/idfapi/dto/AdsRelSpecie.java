/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.time.LocalDateTime;

public class AdsRelSpecie {

	Integer idgeoPtAds;
    Integer idSpecie;
    LocalDateTime dataInizioValidita;
    String codTipoCampione;
    String flgPolloneSeme;
    String qualita;
    Integer diametro;
    Integer altezza;
    Integer incremento;
    Integer eta;
    Integer nrAlberiSeme;
    Integer nrAlberiPollone;
    LocalDateTime dataFineValidita;
    String note;
	
    public AdsRelSpecie() {
		
	}

	public Integer getIdgeoPtAds() {
		return idgeoPtAds;
	}
	public void setIdgeoPtAds(Integer idgeoPtAds) {
		this.idgeoPtAds = idgeoPtAds;
	}

	public Integer getIdSpecie() {
		return idSpecie;
	}
	public void setIdSpecie(Integer idSpecie) {
		this.idSpecie = idSpecie;
	}

	public LocalDateTime getDataInizioValidita() {
		return dataInizioValidita;
	}
	public void setDataInizioValidita(LocalDateTime dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	public String getCodTipoCampione() {
		return codTipoCampione;
	}
	public void setCodTipoCampione(String codTipoCampione) {
		this.codTipoCampione = codTipoCampione;
	}

	public String getFlgPolloneSeme() {
		return flgPolloneSeme;
	}
	public void setFlgPolloneSeme(String flgPolloneSeme) {
		this.flgPolloneSeme = flgPolloneSeme;
	}

	public String getQualita() {
		return qualita;
	}
	public void setQualita(String qualita) {
		this.qualita = qualita;
	}

	public Integer getDiametro() {
		return diametro;
	}
	public void setDiametro(Integer diametro) {
		this.diametro = diametro;
	}

	public Integer getAltezza() {
		return altezza;
	}
	public void setAltezza(Integer altezza) {
		this.altezza = altezza;
	}

	public Integer getIncremento() {
		return incremento;
	}
	public void setIncremento(Integer incremento) {
		this.incremento = incremento;
	}

	public Integer getEta() {
		return eta;
	}
	public void setEta(Integer eta) {
		this.eta = eta;
	}

	public Integer getNrAlberiSeme() {
		return nrAlberiSeme;
	}
	public void setNrAlberiSeme(Integer nrAlberiSeme) {
		this.nrAlberiSeme = nrAlberiSeme;
	}

	public Integer getNrAlberiPollone() {
		return nrAlberiPollone;
	}
	public void setNrAlberiPollone(Integer nrAlberiPollone) {
		this.nrAlberiPollone = nrAlberiPollone;
	}

	public LocalDateTime getDataFineValidita() {
		return dataFineValidita;
	}
	public void setDataFineValidita(LocalDateTime dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataInizioValidita == null) ? 0 : dataInizioValidita.hashCode());
		result = prime * result + ((idSpecie == null) ? 0 : idSpecie.hashCode());
		result = prime * result + ((idgeoPtAds == null) ? 0 : idgeoPtAds.hashCode());
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
		AdsRelSpecie other = (AdsRelSpecie) obj;
		if (dataInizioValidita == null) {
			if (other.dataInizioValidita != null)
				return false;
		} else if (!dataInizioValidita.equals(other.dataInizioValidita))
			return false;
		if (idSpecie == null) {
			if (other.idSpecie != null)
				return false;
		} else if (!idSpecie.equals(other.idSpecie))
			return false;
		if (idgeoPtAds == null) {
			if (other.idgeoPtAds != null)
				return false;
		} else if (!idgeoPtAds.equals(other.idgeoPtAds))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AdsRelSpecie [idgeoPtAds=");
		builder.append(idgeoPtAds);
		builder.append(", idSpecie=");
		builder.append(idSpecie);
		builder.append(", dataInizioValidita=");
		builder.append(dataInizioValidita);
		builder.append(", codTipoCampione=");
		builder.append(codTipoCampione);
		builder.append(", flgPolloneSeme=");
		builder.append(flgPolloneSeme);
		builder.append(", qualita=");
		builder.append(qualita);
		builder.append(", diametro=");
		builder.append(diametro);
		builder.append(", altezza=");
		builder.append(altezza);
		builder.append(", incremento=");
		builder.append(incremento);
		builder.append(", eta=");
		builder.append(eta);
		builder.append(", nrAlberiSeme=");
		builder.append(nrAlberiSeme);
		builder.append(", nrAlberiPollone=");
		builder.append(nrAlberiPollone);
		builder.append(", dataFineValidita=");
		builder.append(dataFineValidita);
		builder.append(", note=");
		builder.append(note);
		builder.append("]");
		return builder.toString();
	}
	    
}
