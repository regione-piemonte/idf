/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class TipoCampione {

	String codTipoCampione;
    String descrTipoCampione;
    Integer mtdOrdinamento;
    Byte flgVisibile;
	
    public TipoCampione() { 
    	
    }

	public String getCodTipoCampione() {
		return codTipoCampione;
	}
	public void setCodTipoCampione(String codTipoCampione) {
		this.codTipoCampione = codTipoCampione;
	}

	public String getDescrTipoCampione() {
		return descrTipoCampione;
	}
	public void setDescrTipoCampione(String descrTipoCampione) {
		this.descrTipoCampione = descrTipoCampione;
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
		result = prime * result + ((codTipoCampione == null) ? 0 : codTipoCampione.hashCode());
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
		TipoCampione other = (TipoCampione) obj;
		if (codTipoCampione == null) {
			if (other.codTipoCampione != null)
				return false;
		} else if (!codTipoCampione.equals(other.codTipoCampione))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TipoCampione [codTipoCampione=");
		builder.append(codTipoCampione);
		builder.append(", descrTipoCampione=");
		builder.append(descrTipoCampione);
		builder.append(", mtdOrdinamento=");
		builder.append(mtdOrdinamento);
		builder.append(", flgVisibile=");
		builder.append(flgVisibile);
		builder.append("]");
		return builder.toString();
	}
        	
}
