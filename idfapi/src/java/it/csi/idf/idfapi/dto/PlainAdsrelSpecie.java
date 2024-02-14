/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class PlainAdsrelSpecie {
	
	private Long idSpecie;
	private Integer nrAlberiSeme;
	private Integer nrAlberiPollone;
	private Integer diametro;
	private Integer altezza;
	private Integer incremento;
	private String flgPolloneSeme;
	private String note;
	
	public Long getIdSpecie() {
		return idSpecie;
	}
	public void setIdSpecie(Long idSpecie) {
		this.idSpecie = idSpecie;
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
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
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
	public String getFlgPolloneSeme() {
		return flgPolloneSeme;
	}
	public void setFlgPolloneSeme(String flgPolloneSeme) {
		this.flgPolloneSeme = flgPolloneSeme;
	}
}
