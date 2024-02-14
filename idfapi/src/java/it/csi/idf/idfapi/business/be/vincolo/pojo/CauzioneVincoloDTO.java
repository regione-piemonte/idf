/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.pojo;

public class CauzioneVincoloDTO {
	private boolean nonNecessaria;
	private boolean flgA;
	private boolean flgB;
	private boolean flgC;
	private boolean flgD;
	private boolean flgDbis;
	private boolean necessaria;
	private boolean compFisica;
	private boolean compMonetaria;
	
	public boolean isNonNecessaria() {
		return nonNecessaria;
	}
	
	public void setNonNecessaria(boolean nonNecessaria) {
		this.nonNecessaria = nonNecessaria;
	}
	
	public boolean isFlgA() {
		return flgA;
	}
	
	public void setFlgA(boolean flgA) {
		this.flgA = flgA;
	}
	
	public boolean isFlgB() {
		return flgB;
	}
	
	public void setFlgB(boolean flgB) {
		this.flgB = flgB;
	}
	
	public boolean isFlgC() {
		return flgC;
	}
	
	public void setFlgC(boolean flgC) {
		this.flgC = flgC;
	}
	
	public boolean isFlgD() {
		return flgD;
	}
	
	public void setFlgD(boolean flgD) {
		this.flgD = flgD;
	}
	
	public boolean isFlgDbis() {
		return flgDbis;
	}
	
	public void setFlgDbis(boolean flgDbis) {
		this.flgDbis = flgDbis;
	}
	
	public boolean isNecessaria() {
		return necessaria;
	}
	
	public void setNecessaria(boolean necessaria) {
		this.necessaria = necessaria;
	}
	
	public boolean isCompFisica() {
		return compFisica;
	}
	
	public void setCompFisica(boolean compFisica) {
		this.compFisica = compFisica;
	}
	
	public boolean isCompMonetaria() {
		return compMonetaria;
	}
	
	public void setCompMonetaria(boolean compMonetaria) {
		this.compMonetaria = compMonetaria;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CauzioneVincoloDTO [nonNecessaria=");
		builder.append(nonNecessaria);
		builder.append(", flgA=");
		builder.append(flgA);
		builder.append(", flgB=");
		builder.append(flgB);
		builder.append(", flgC=");
		builder.append(flgC);
		builder.append(", flgD=");
		builder.append(flgD);
		builder.append(", flgDbis=");
		builder.append(flgDbis);
		builder.append(", necessaria=");
		builder.append(necessaria);
		builder.append(", compFisica=");
		builder.append(compFisica);
		builder.append(", compMonetaria=");
		builder.append(compMonetaria);
		builder.append("]");
		return builder.toString();
	}
}
