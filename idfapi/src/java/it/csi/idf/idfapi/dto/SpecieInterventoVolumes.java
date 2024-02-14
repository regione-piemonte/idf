/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.util.StringJoiner;

public class SpecieInterventoVolumes {

	private Integer idSpecie;
	private String volgare;
	private Float volumeSpecie;
	private String priorita;
	private String udm;
	private Integer fkUdm;

	private Integer numPiante;

	private Float densita;


	public Integer getIdSpecie() {
		return idSpecie;
	}

	public void setIdSpecie(Integer idSpecie) {
		this.idSpecie = idSpecie;
	}

	public String getVolgare() {
		return volgare;
	}

	public void setVolgare(String volgare) {
		this.volgare = volgare;
	}

	public Float getVolumeSpecie() {
		return volumeSpecie;
	}

	public void setVolumeSpecie(Float volumeSpecie) {
		this.volumeSpecie = volumeSpecie;
	}

	public String getPriorita() {
		return priorita;
	}

	public void setPriorita(String priorita) {
		this.priorita = priorita;
	}

	public String getUdm() {
		return udm;
	}

	public void setUdm(String udm) {
		this.udm = udm;
	}


	public Integer getNumPiante() {
		return numPiante;
	}

	public void setNumPiante(Integer numPiante) {
		this.numPiante = numPiante;
	}


	public Float getDensita() {
		return densita;
	}

	public void setDensita(Float densita) {
		this.densita = densita;
	}

	public Integer getFkUdm() {
		return fkUdm;
	}

	public void setFkUdm(Integer fkUdm) {
		this.fkUdm = fkUdm;
	}

	@Override
	public String toString() {
		return new StringJoiner("\n ", SpecieInterventoVolumes.class.getSimpleName() + "[", "]")
				.add("idSpecie=" + idSpecie)
				.add("volgare='" + volgare + "'")
				.add("volumeSpecie=" + volumeSpecie)
				.add("priorita='" + priorita + "'")
				.add("udm='" + udm + "'")
				.add("fkUdm=" + fkUdm)
				.add("numPiante=" + numPiante)
				.add("densita=" + densita)
				.toString();
	}
}
