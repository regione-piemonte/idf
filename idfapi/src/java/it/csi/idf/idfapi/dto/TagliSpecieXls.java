/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;
import java.util.StringJoiner;

public class TagliSpecieXls {

	private Integer nrIstanzaForestale;
	private String latino;
	private String volgare;
	private String flgSpeciePriorita;
	private Integer numeroPiante;
	private BigDecimal volumeSpecie;
	private String descrUdm;
	private BigDecimal legnaOperaAutoconsumoPerc;
	private BigDecimal legnaOperaCommercioPerc;
	private BigDecimal legnaArdereAutoconsumoPerc;
	private BigDecimal legnaArdereCommercioPerc;
	private BigDecimal legnaUsoEnerAutoconsumoPerc;
	private BigDecimal legnaUsoEnerCommercioPerc;
	private BigDecimal nessunUtilizzoAutoconsumoPerc;
	private BigDecimal nessunUtilizzoCommercioPerc;

	public Integer getNrIstanzaForestale() {
		return nrIstanzaForestale;
	}

	public void setNrIstanzaForestale(Integer nrIstanzaForestale) {
		this.nrIstanzaForestale = nrIstanzaForestale;
	}

	public String getLatino() {
		return latino;
	}

	public void setLatino(String latino) {
		this.latino = latino;
	}

	public String getVolgare() {
		return volgare;
	}

	public void setVolgare(String volgare) {
		this.volgare = volgare;
	}

	public String getFlgSpeciePriorita() {
		return flgSpeciePriorita;
	}

	public void setFlgSpeciePriorita(String flgSpeciePriorita) {
		this.flgSpeciePriorita = flgSpeciePriorita;
	}

	public Integer getNumeroPiante() {
		return numeroPiante;
	}

	public void setNumeroPiante(Integer numeroPiante) {
		this.numeroPiante = numeroPiante;
	}

	public BigDecimal getVolumeSpecie() {
		return volumeSpecie;
	}

	public void setVolumeSpecie(BigDecimal volumeSpecie) {
		this.volumeSpecie = volumeSpecie;
	}

	public String getDescrUdm() {
		return descrUdm;
	}

	public void setDescrUdm(String descrUdm) {
		this.descrUdm = descrUdm;
	}

	public BigDecimal getLegnaOperaAutoconsumoPerc() {
		return legnaOperaAutoconsumoPerc;
	}

	public void setLegnaOperaAutoconsumoPerc(BigDecimal legnaOperaAutoconsumoPerc) {
		this.legnaOperaAutoconsumoPerc = legnaOperaAutoconsumoPerc;
	}

	public BigDecimal getLegnaOperaCommercioPerc() {
		return legnaOperaCommercioPerc;
	}

	public void setLegnaOperaCommercioPerc(BigDecimal legnaOperaCommercioPerc) {
		this.legnaOperaCommercioPerc = legnaOperaCommercioPerc;
	}

	public BigDecimal getLegnaArdereAutoconsumoPerc() {
		return legnaArdereAutoconsumoPerc;
	}

	public void setLegnaArdereAutoconsumoPerc(BigDecimal legnaArdereAutoconsumoPerc) {
		this.legnaArdereAutoconsumoPerc = legnaArdereAutoconsumoPerc;
	}

	public BigDecimal getLegnaArdereCommercioPerc() {
		return legnaArdereCommercioPerc;
	}

	public void setLegnaArdereCommercioPerc(BigDecimal legnaArdereCommercioPerc) {
		this.legnaArdereCommercioPerc = legnaArdereCommercioPerc;
	}

	public BigDecimal getLegnaUsoEnerAutoconsumoPerc() {
		return legnaUsoEnerAutoconsumoPerc;
	}

	public void setLegnaUsoEnerAutoconsumoPerc(BigDecimal legnaUsoEnerAutoconsumoPerc) {
		this.legnaUsoEnerAutoconsumoPerc = legnaUsoEnerAutoconsumoPerc;
	}

	public BigDecimal getLegnaUsoEnerCommercioPerc() {
		return legnaUsoEnerCommercioPerc;
	}

	public void setLegnaUsoEnerCommercioPerc(BigDecimal legnaUsoEnerCommercioPerc) {
		this.legnaUsoEnerCommercioPerc = legnaUsoEnerCommercioPerc;
	}

	public BigDecimal getNessunUtilizzoAutoconsumoPerc() {
		return nessunUtilizzoAutoconsumoPerc;
	}

	public void setNessunUtilizzoAutoconsumoPerc(BigDecimal nessunUtilizzoAutoconsumoPerc) {
		this.nessunUtilizzoAutoconsumoPerc = nessunUtilizzoAutoconsumoPerc;
	}

	public BigDecimal getNessunUtilizzoCommercioPerc() {
		return nessunUtilizzoCommercioPerc;
	}

	public void setNessunUtilizzoCommercioPerc(BigDecimal nessunUtilizzoCommercioPerc) {
		this.nessunUtilizzoCommercioPerc = nessunUtilizzoCommercioPerc;
	}

	@Override
	public String toString() {
		return new StringJoiner("\n ", TagliSpecieXls.class.getSimpleName() + "[", "]")
				.add("nrIstanzaForestale=" + nrIstanzaForestale)
				.add("latino='" + latino + "'")
				.add("volgare='" + volgare + "'")
				.add("flgSpeciePriorita='" + flgSpeciePriorita + "'")
				.add("numeroPiante=" + numeroPiante)
				.add("volumeSpecie=" + volumeSpecie)
				.add("descrUdm='" + descrUdm + "'")
				.add("legnaOperaAutoconsumoPerc=" + legnaOperaAutoconsumoPerc)
				.add("legnaOperaCommercioPerc=" + legnaOperaCommercioPerc)
				.add("legnaArdereAutoconsumoPerc=" + legnaArdereAutoconsumoPerc)
				.add("legnaArdereCommercioPerc=" + legnaArdereCommercioPerc)
				.add("legnaUsoEnerAutoconsumoPerc=" + legnaUsoEnerAutoconsumoPerc)
				.add("legnaUsoEnerCommercioPerc=" + legnaUsoEnerCommercioPerc)
				.add("nessunUtilizzoAutoconsumoPerc=" + nessunUtilizzoAutoconsumoPerc)
				.add("nessunUtilizzoCommercioPerc=" + nessunUtilizzoCommercioPerc)
				.toString();
	}
}
