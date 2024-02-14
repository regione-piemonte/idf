/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class GruppoSpecie {
	
	String codGruppo;
    String descrGruppo;
    Integer mtdOrdinamento;
	Byte flgVisibile;
	
	public GruppoSpecie() {
		
	}

	public String getCodGruppo() {
		return codGruppo;
	}
	public void setCodGruppo(String codGruppo) {
		this.codGruppo = codGruppo;
	}

	public String getDescrGruppo() {
		return descrGruppo;
	}
	public void setDescrGruppo(String descrGruppo) {
		this.descrGruppo = descrGruppo;
	}

	public Integer getMtdOrdinamento() {
		return mtdOrdinamento;
	}
	public void setMtdOrdinamento(Integer mtd_ordinamento) {
		this.mtdOrdinamento = mtd_ordinamento;
	}

	public Byte getFlgVisibile() {
		return flgVisibile;
	}
	public void setFlgVisibile(Byte flg_visibile) {
		this.flgVisibile = flg_visibile;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codGruppo == null) ? 0 : codGruppo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GruppoSpecie other = (GruppoSpecie) obj;
		if (codGruppo == null) {
			if (other.codGruppo != null)
				return false;
		} else if (!codGruppo.equals(other.codGruppo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GruppoSpecie [codGruppo=");
		builder.append(codGruppo);
		builder.append(", descrGruppo=");
		builder.append(descrGruppo);
		builder.append(", mtd_ordinamento=");
		builder.append(mtdOrdinamento);
		builder.append(", flg_visibile=");
		builder.append(flgVisibile);
		builder.append("]");
		return builder.toString();
	}
	
	
}
