/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class CompensationForesteDTO {
	private boolean nonNecessaria;
	private boolean flgA;
	private boolean flgB;
	private boolean flgC;
	private boolean flgD;
	private boolean nonNecessaria21;
	private boolean flgA21;
	private boolean flgB21;
	private boolean flgC21;
	private boolean flgD21;
	private boolean flgDter21;
	private boolean flgDquater21;
	private boolean flgDquinquies21;
	private boolean necessaria;
	private boolean compFisica;
	private boolean compMonetaria;
	
	
	public boolean isNonNecessaria() {
		return nonNecessaria;
	}

	public boolean isFlgA() {
		return flgA;
	}

	public boolean isFlgB() {
		return flgB;
	}

	public boolean isFlgC() {
		return flgC;
	}

	public boolean isFlgD() {
		return flgD;
	}

	public boolean isNonNecessaria21() {
		return nonNecessaria21;
	}

	public boolean isFlgA21() {
		return flgA21;
	}

	public boolean isFlgB21() {
		return flgB21;
	}

	public boolean isFlgC21() {
		return flgC21;
	}

	public boolean isFlgD21() {
		return flgD21;
	}

	public boolean isFlgDter21() {
		return flgDter21;
	}

	public boolean isFlgDquater21() {
		return flgDquater21;
	}

	public boolean isFlgDquinquies21() {
		return flgDquinquies21;
	}

	public boolean isNecessaria() {
		return necessaria;
	}

	public boolean isCompFisica() {
		return compFisica;
	}

	public boolean isCompMonetaria() {
		return compMonetaria;
	}

	public void setNonNecessaria(boolean nonNecessaria) {
		this.nonNecessaria = nonNecessaria;
	}

	public void setFlgA(boolean flgA) {
		this.flgA = flgA;
	}

	public void setFlgB(boolean flgB) {
		this.flgB = flgB;
	}

	public void setFlgC(boolean flgC) {
		this.flgC = flgC;
	}

	public void setFlgD(boolean flgD) {
		this.flgD = flgD;
	}

	public void setNonNecessaria21(boolean nonNecessaria21) {
		this.nonNecessaria21 = nonNecessaria21;
	}

	public void setFlgA21(boolean flgA21) {
		this.flgA21 = flgA21;
	}

	public void setFlgB21(boolean flgB21) {
		this.flgB21 = flgB21;
	}

	public void setFlgC21(boolean flgC21) {
		this.flgC21 = flgC21;
	}

	public void setFlgD21(boolean flgD21) {
		this.flgD21 = flgD21;
	}

	public void setFlgDter21(boolean flgDter21) {
		this.flgDter21 = flgDter21;
	}

	public void setFlgDquater21(boolean flgDquater21) {
		this.flgDquater21 = flgDquater21;
	}

	public void setFlgDquinquies21(boolean flgDquinquies21) {
		this.flgDquinquies21 = flgDquinquies21;
	}

	public void setNecessaria(boolean necessaria) {
		this.necessaria = necessaria;
	}

	public void setCompFisica(boolean compFisica) {
		this.compFisica = compFisica;
	}

	public void setCompMonetaria(boolean compMonetaria) {
		this.compMonetaria = compMonetaria;
	}

	@Override
	public String toString() {
		return "CompensationForesteDTO [nonNecessaria=" + nonNecessaria + ", flgA=" + flgA + ", flgB=" + flgB
				+ ", flgC=" + flgC + ", flgD=" + flgD + ", nonNecessaria21=" + nonNecessaria21 + ", flgA21=" + flgA21
				+ ", flgB21=" + flgB21 + ", flgC21=" + flgC21 + ", flgD21=" + flgD21 + ", flgDter21=" + flgDter21
				+ ", flgDquater21=" + flgDquater21 + ", flgDquinquies21=" + flgDquinquies21 + ", necessaria="
				+ necessaria + ", compFisica=" + compFisica + ", compMonetaria=" + compMonetaria
				+ ", isNonNecessaria()=" + isNonNecessaria() + ", isFlgA()=" + isFlgA() + ", isFlgB()=" + isFlgB()
				+ ", isFlgC()=" + isFlgC() + ", isFlgD()=" + isFlgD() + ", isNonNecessaria21()=" + isNonNecessaria21()
				+ ", isFlgA21()=" + isFlgA21() + ", isFlgB21()=" + isFlgB21() + ", isFlgC21()=" + isFlgC21()
				+ ", isFlgD21()=" + isFlgD21() + ", isFlgDter21()=" + isFlgDter21() + ", isFlgDquater21()="
				+ isFlgDquater21() + ", isFlgDquinquies21()=" + isFlgDquinquies21() + ", isNecessaria()="
				+ isNecessaria() + ", isCompFisica()=" + isCompFisica() + ", isCompMonetaria()=" + isCompMonetaria()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	
}
