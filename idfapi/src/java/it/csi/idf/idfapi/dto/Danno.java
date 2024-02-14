/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class Danno {
	
	private Integer idDanno;
	private String codDanno;
	private String descrDanno;
	private Integer mtdOrdinamento;
	private Byte flgVisibile;
	
	public Danno() {	
	}

	public Integer getIdDanno() {
		return idDanno;
	}

	public void setIdDanno(Integer idDanno) {
		this.idDanno = idDanno;
	}

	public String getCodDanno() {
		return codDanno;
	}

	public void setCodDanno(String cod_danno) {
		this.codDanno = cod_danno;
	}

	public String getDescrDanno() {
		return descrDanno;
	}

	public void setDescrDanno(String descr_danno) {
		this.descrDanno = descr_danno;
	}

	public Integer getMtdOrdinamento() {
		return mtdOrdinamento;
	}

	public void setMtdOrdinamento(Integer mtdOrdinamento) {
		this.mtdOrdinamento = mtdOrdinamento;
	}

	public Byte getFlgVisibile() {
		return flgVisibile;
	}

	public void setFlgVisibile(Byte flgVisibile) {
		this.flgVisibile = flgVisibile;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codDanno == null) ? 0 : codDanno.hashCode());
		result = prime * result + ((descrDanno == null) ? 0 : descrDanno.hashCode());
		result = prime * result + ((flgVisibile == null) ? 0 : flgVisibile.hashCode());
		result = prime * result + ((idDanno == null) ? 0 : idDanno.hashCode());
		result = prime * result + ((mtdOrdinamento == null) ? 0 : mtdOrdinamento.hashCode());
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
		Danno other = (Danno) obj;
		if (codDanno == null) {
			if (other.codDanno != null)
				return false;
		} else if (!codDanno.equals(other.codDanno))
			return false;		
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Danno [idDanno=");
		builder.append(idDanno);
		builder.append(", cod_danno=");
		builder.append(codDanno);
		builder.append(", descr_danno=");
		builder.append(descrDanno);
		builder.append(", mtdOrdinamento=");
		builder.append(mtdOrdinamento);
		builder.append(", flgVisibile=");
		builder.append(flgVisibile);
		builder.append("]");
		return builder.toString();
	}
	
}
