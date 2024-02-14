/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.util.List;

public class PlainSestoSezione {
	private String etichetta;
	private DichProprieta proprieta;
	private DichDissensi dissensi;
	private DichAutorizzazione paesaggistica;
	private DichAutorizzazione vincIdrogeologico;
	private DichAutorizzazione valIncidenza;
	private DichAltriPareri altriPareri;
	private DichCompensazione compFisica;
	private DichCompensazione compMonetaria;
	private DichCompensazione altroAllega;
	private List<FatDocumentoAllegato> allegati;
	private DichIstanzaTaglio istanzi;
	private DichNota nota;
	
	public String getEtichetta() {
		return etichetta;
	}
	public void setEtichetta(String etichetta) {
		this.etichetta = etichetta;
	}
	public DichProprieta getProprieta() {
		return proprieta;
	}
	public void setProprieta(DichProprieta proprieta) {
		this.proprieta = proprieta;
	}
	public DichDissensi getDissensi() {
		return dissensi;
	}
	public void setDissensi(DichDissensi dissensi) {
		this.dissensi = dissensi;
	}
	public DichAutorizzazione getPaesaggistica() {
		return paesaggistica;
	}
	public void setPaesaggistica(DichAutorizzazione paesaggistica) {
		this.paesaggistica = paesaggistica;
	}
	public DichAutorizzazione getVincIdrogeologico() {
		return vincIdrogeologico;
	}
	public void setVincIdrogeologico(DichAutorizzazione vincIdrogeologico) {
		this.vincIdrogeologico = vincIdrogeologico;
	}
	public DichAutorizzazione getValIncidenza() {
		return valIncidenza;
	}
	public void setValIncidenza(DichAutorizzazione valIncidenza) {
		this.valIncidenza = valIncidenza;
	}
	public DichAltriPareri getAltriPareri() {
		return altriPareri;
	}
	public void setAltriPareri(DichAltriPareri altriPareri) {
		this.altriPareri = altriPareri;
	}
	public DichCompensazione getCompFisica() {
		return compFisica;
	}
	public void setCompFisica(DichCompensazione compFisica) {
		this.compFisica = compFisica;
	}
	public DichCompensazione getCompMonetaria() {
		return compMonetaria;
	}
	public void setCompMonetaria(DichCompensazione compMonetaria) {
		this.compMonetaria = compMonetaria;
	}
	public DichCompensazione getAltroAllega() {
		return altroAllega;
	}
	public void setAltroAllega(DichCompensazione altroAllega) {
		this.altroAllega = altroAllega;
	}
	public List<FatDocumentoAllegato> getAllegati() {
		return allegati;
	}
	public void setAllegati(List<FatDocumentoAllegato> allegati) {
		this.allegati = allegati;
	}
	public DichIstanzaTaglio getIstanzi() {
		return istanzi;
	}
	public void setIstanzi(DichIstanzaTaglio istanzi) {
		this.istanzi = istanzi;
	}
	public DichNota getNota() {
		return nota;
	}
	public void setNota(DichNota nota) {
		this.nota = nota;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlainSestoSezione [etichetta=");
		builder.append(etichetta);
		builder.append(", proprieta=");
		builder.append(proprieta);
		builder.append(", dissensi=");
		builder.append(dissensi);
		builder.append(", paesaggistica=");
		builder.append(paesaggistica);
		builder.append(", vincIdrogeologico=");
		builder.append(vincIdrogeologico);
		builder.append(", valIncidenza=");
		builder.append(valIncidenza);
		builder.append(", altriPareri=");
		builder.append(altriPareri);
		builder.append(", compFisica=");
		builder.append(compFisica);
		builder.append(", compMonetaria=");
		builder.append(compMonetaria);
		builder.append(", altroAllega=");
		builder.append(altroAllega);
		builder.append(", allegati=");
		builder.append(allegati);
		builder.append(", istanzi=");
		builder.append(istanzi);
		builder.append(", nota=");
		builder.append(nota);
		builder.append("]");
		return builder.toString();
	}
}
