/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class SkOkSelv {

	private Integer idIntervento;
	private Byte flgSez1 = 0;
	private Byte flgSez2 = 0;
	private Byte flgSez3 = 0;
	private Byte flgSez4 = 0;
	private Byte flgSez5 = 0;
	
	public Integer getIdIntervento() {
		return idIntervento;
	}
	public void setIdIntervento(Integer idIntervento) {
		this.idIntervento = idIntervento;
	}
	
	public Byte getFlgSez1() {
		return flgSez1;
	}
	public void setFlgSez1(Byte flgSez1) {
		this.flgSez1 = flgSez1;
	}
	
	public Byte getFlgSez2() {
		return flgSez2;
	}
	public void setFlgSez2(Byte flgSez2) {
		this.flgSez2 = flgSez2;
	}
	
	public Byte getFlgSez3() {
		return flgSez3;
	}
	public void setFlgSez3(Byte flgSez3) {
		this.flgSez3 = flgSez3;
	}
	
	public Byte getFlgSez4() {
		return flgSez4;
	}
	public void setFlgSez4(Byte flgSez4) {
		this.flgSez4 = flgSez4;
	}
	
	public Byte getFlgSez5() {
		return flgSez5;
	}
	public void setFlgSez5(Byte flgSez5) {
		this.flgSez5 = flgSez5;
	}
	
	
}
