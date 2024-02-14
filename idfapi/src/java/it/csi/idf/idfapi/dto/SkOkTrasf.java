/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.util.Objects;

public class SkOkTrasf {
	
	private Integer idIntervento;
	private Byte flgSez1;
	private Byte flgSez2;
	private Byte flgSez3;
	private Byte flgSez4;
	private Byte flgSez5;
	private Byte flgSez6;
	
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
	public Byte getFlgSez6() {
		return flgSez6;
	}
	public void setFlgSez6(Byte flgSez6) {
		this.flgSez6 = flgSez6;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(idIntervento, flgSez1, flgSez2, flgSez3, flgSez4, flgSez5, flgSez6);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		SkOkTrasf skOkTrasf = (SkOkTrasf) o;
		return Objects.equals(idIntervento, skOkTrasf.idIntervento)
			&& Objects.equals(flgSez1, skOkTrasf.flgSez1)
			&& Objects.equals(flgSez2, skOkTrasf.flgSez2)
			&& Objects.equals(flgSez3, skOkTrasf.flgSez3)
			&& Objects.equals(flgSez4, skOkTrasf.flgSez4)
			&& Objects.equals(flgSez5, skOkTrasf.flgSez5)
			&& Objects.equals(flgSez6, skOkTrasf.flgSez6);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class SkOkTrasf {\n");
		sb.append("    idIntervento: ").append(idIntervento).append("\n");
		sb.append("    flgSez1: ").append(flgSez1).append("\n");
		sb.append("    flgSez2: ").append(flgSez2).append("\n");
		sb.append("    flgSez3: ").append(flgSez3).append("\n");
		sb.append("    flgSez4: ").append(flgSez4).append("\n");
		sb.append("    flgSez5: ").append(flgSez5).append("\n");
		sb.append("    flgSez6: ").append(flgSez6).append("\n");
		sb.append("}");
		return sb.toString();
	}
}
