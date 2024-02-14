/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class SpecieInterventoReport {

	private String nome;
	private String numPiante;
	private String volumeM3;
	private String volumeT;
	private String volumeQ;
	private String finalita;
	private String autoConsumo;
	private String commerciale;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumPiante() {
		return numPiante;
	}

	public void setNumPiante(String numPiante) {
		this.numPiante = numPiante;
	}

	public String getVolumeM3() {
		return volumeM3;
	}

	public void setVolumeM3(String volumeM3) {
		this.volumeM3 = volumeM3;
	}

	public String getVolumeT() {
		return volumeT;
	}

	public void setVolumeT(String volumeT) {
		this.volumeT = volumeT;
	}

	public String getVolumeQ() {
		return volumeQ;
	}

	public void setVolumeQ(String volumeQ) {
		this.volumeQ = volumeQ;
	}

	public String getFinalita() {
		return finalita;
	}

	public void setFinalita(String finalita) {
		this.finalita = finalita;
	}

	public String getAutoConsumo() {
		return autoConsumo;
	}

	public void setAutoConsumo(String autoConsumo) {
		this.autoConsumo = autoConsumo;
	}

	public String getCommerciale() {
		return commerciale;
	}

	public void setCommerciale(String commerciale) {
		this.commerciale = commerciale;
	}
}
