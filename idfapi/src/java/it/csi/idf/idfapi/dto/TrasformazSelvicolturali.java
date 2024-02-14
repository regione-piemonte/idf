/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.Objects;
import java.util.StringJoiner;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TrasformazSelvicolturali {
	

	private Integer idIstanza;
	private Integer nrIstanza;
	private String anno;
	private String statoIstanza;
	private String richiedente;
	private String comune;
	private String compensazione;



	public Integer getIdIstanza() {
		return idIstanza;
	}

	public void setIdIstanza(Integer idIstanza) {
		this.idIstanza = idIstanza;
	}

	public Integer getNrIstanza() {
		return nrIstanza;
	}

	public void setNrIstanza(Integer nrIstanza) {
		this.nrIstanza = nrIstanza;
	}

	public String getAnno() {
		return anno;
	}

	public void setAnno(String anno) {
		this.anno = anno;
	}

	public String getStatoIstanza() {
		return statoIstanza;
	}

	public void setStatoIstanza(String statoIstanza) {
		this.statoIstanza = statoIstanza;
	}

	public String getRichiedente() {
		return richiedente;
	}

	public void setRichiedente(String richiedente) {
		this.richiedente = richiedente;
	}

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	public String getCompensazione() {
		return compensazione;
	}

	public void setCompensazione(String compensazione) {
		this.compensazione = compensazione;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof TrasformazSelvicolturali)) return false;
		TrasformazSelvicolturali that = (TrasformazSelvicolturali) o;
		return Objects.equals(idIstanza, that.idIstanza) && Objects.equals(nrIstanza, that.nrIstanza) && Objects.equals(anno, that.anno) && Objects.equals(statoIstanza, that.statoIstanza) && Objects.equals(richiedente, that.richiedente) && Objects.equals(comune, that.comune) && Objects.equals(compensazione, that.compensazione);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idIstanza, nrIstanza, anno, statoIstanza, richiedente, comune, compensazione);
	}

	@Override
	public String toString() {
		return new StringJoiner("\n ", TrasformazSelvicolturali.class.getSimpleName() + "[", "]")
				.add("idIstanza=" + idIstanza)
				.add("nrIstanza=" + nrIstanza)
				.add("anno='" + anno + "'")
				.add("statoIstanza='" + statoIstanza + "'")
				.add("richiedente='" + richiedente + "'")
				.add("comune='" + comune + "'")
				.add("compensazione='" + compensazione + "'")
				.toString();
	}
}
