/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.pojo;

import java.util.List;

import it.csi.idf.idfapi.dto.FatDocumentoAllegato;

public class VincoloStep6DTO {

	private boolean flagProprieta;
	private boolean flagDissensi;
	private boolean flagImporto;
	private boolean flagCopiaConforme;
	private boolean flagConfServizi;
	private boolean flagSuap;
	private String annotazioni;
	
	private boolean flagSpeseIstruttoria;
	private boolean flagEsenzioneMarcaBollo;
	private String nMarcaBollo;
	
	List<FatDocumentoAllegato> documentiAllegati;

	public boolean isFlagProprieta() {
		return flagProprieta;
	}

	public void setFlagProprieta(boolean flagProprieta) {
		this.flagProprieta = flagProprieta;
	}

	public boolean isFlagDissensi() {
		return flagDissensi;
	}

	public void setFlagDissensi(boolean flagDissensi) {
		this.flagDissensi = flagDissensi;
	}

	public boolean isFlagImporto() {
		return flagImporto;
	}

	public void setFlagImporto(boolean flagImporto) {
		this.flagImporto = flagImporto;
	}

	public boolean isFlagCopiaConforme() {
		return flagCopiaConforme;
	}

	public void setFlagCopiaConforme(boolean flagCopiaConforme) {
		this.flagCopiaConforme = flagCopiaConforme;
	}

	public boolean isFlagConfServizi() {
		return flagConfServizi;
	}

	public void setFlagConfServizi(boolean flagConfServizi) {
		this.flagConfServizi = flagConfServizi;
	}

	public boolean isFlagSuap() {
		return flagSuap;
	}

	public void setFlagSuap(boolean flagSuap) {
		this.flagSuap = flagSuap;
	}

	public String getAnnotazioni() {
		return annotazioni;
	}

	public void setAnnotazioni(String annotazioni) {
		this.annotazioni = annotazioni;
	}

	public boolean isFlagSpeseIstruttoria() {
		return flagSpeseIstruttoria;
	}

	public void setFlagSpeseIstruttoria(boolean flagSpeseIstruttoria) {
		this.flagSpeseIstruttoria = flagSpeseIstruttoria;
	}

	public boolean isFlagEsenzioneMarcaBollo() {
		return flagEsenzioneMarcaBollo;
	}

	public void setFlagEsenzioneMarcaBollo(boolean flagEsenzioneMarcaBollo) {
		this.flagEsenzioneMarcaBollo = flagEsenzioneMarcaBollo;
	}

	public String getnMarcaBollo() {
		return nMarcaBollo;
	}

	public void setnMarcaBollo(String nMarcaBollo) {
		this.nMarcaBollo = nMarcaBollo;
	}

	public List<FatDocumentoAllegato> getDocumentiAllegati() {
		return documentiAllegati;
	}

	public void setDocumentiAllegati(List<FatDocumentoAllegato> documentiAllegati) {
		this.documentiAllegati = documentiAllegati;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("VincoloStep6DTO [flagProprieta=");
		builder.append(flagProprieta);
		builder.append(", flagDissensi=");
		builder.append(flagDissensi);
		builder.append(", flagImporto=");
		builder.append(flagImporto);
		builder.append(", flagCopiaConforme=");
		builder.append(flagCopiaConforme);
		builder.append(", flagConfServizi=");
		builder.append(flagConfServizi);
		builder.append(", flagSuap=");
		builder.append(flagSuap);
		builder.append(", annotazioni=");
		builder.append(annotazioni);
		builder.append(", flagSpeseIstruttoria=");
		builder.append(flagSpeseIstruttoria);
		builder.append(", flagEsenzioneMarcaBollo=");
		builder.append(flagEsenzioneMarcaBollo);
		builder.append(", nMarcaBollo=");
		builder.append(nMarcaBollo);
		builder.append(", documentiAllegati=");
		builder.append(documentiAllegati);
		builder.append("]");
		return builder.toString();
	}
	
	
}
