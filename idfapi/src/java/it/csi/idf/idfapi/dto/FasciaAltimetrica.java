/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class FasciaAltimetrica {

	private Integer idFasciaAltimetrica;
	private Integer fasciaAltimetricaMIN;
	private Integer fasciaAltimetricaMAX;
	private Integer mtdOrdinamento;
	private Integer flgVisible;
	
	public Integer getIdFasciaAltimetrica() {
		return idFasciaAltimetrica;
	}
	public void setIdFasciaAltimetrica(Integer idFasciaAltimetrica) {
		this.idFasciaAltimetrica = idFasciaAltimetrica;
	}
	public Integer getFasciaAltimetricaMIN() {
		return fasciaAltimetricaMIN;
	}
	public void setFasciaAltimetricaMIN(Integer fasciaAltimetricaMIN) {
		this.fasciaAltimetricaMIN = fasciaAltimetricaMIN;
	}
	public Integer getFasciaAltimetricaMAX() {
		return fasciaAltimetricaMAX;
	}
	public void setFasciaAltimetricaMAX(Integer fasciaAltimetricaMAX) {
		this.fasciaAltimetricaMAX = fasciaAltimetricaMAX;
	}
	public Integer getMtdOrdinamento() {
		return mtdOrdinamento;
	}
	public void setMtdOrdinamento(Integer mtdOrdinamento) {
		this.mtdOrdinamento = mtdOrdinamento;
	}
	public Integer getFlgVisible() {
		return flgVisible;
	}
	public void setFlgVisible(Integer flgVisible) {
		this.flgVisible = flgVisible;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fasciaAltimetricaMAX == null) ? 0 : fasciaAltimetricaMAX.hashCode());
		result = prime * result + ((fasciaAltimetricaMIN == null) ? 0 : fasciaAltimetricaMIN.hashCode());
		result = prime * result + ((flgVisible == null) ? 0 : flgVisible.hashCode());
		result = prime * result + ((idFasciaAltimetrica == null) ? 0 : idFasciaAltimetrica.hashCode());
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
		FasciaAltimetrica other = (FasciaAltimetrica) obj;
		if (fasciaAltimetricaMAX == null) {
			if (other.fasciaAltimetricaMAX != null)
				return false;
		} else if (!fasciaAltimetricaMAX.equals(other.fasciaAltimetricaMAX))
			return false;
		if (fasciaAltimetricaMIN == null) {
			if (other.fasciaAltimetricaMIN != null)
				return false;
		} else if (!fasciaAltimetricaMIN.equals(other.fasciaAltimetricaMIN))
			return false;
		if (flgVisible == null) {
			if (other.flgVisible != null)
				return false;
		} else if (!flgVisible.equals(other.flgVisible))
			return false;
		if (idFasciaAltimetrica == null) {
			if (other.idFasciaAltimetrica != null)
				return false;
		} else if (!idFasciaAltimetrica.equals(other.idFasciaAltimetrica))
			return false;
		if (mtdOrdinamento == null) {
			if (other.mtdOrdinamento != null)
				return false;
		} else if (!mtdOrdinamento.equals(other.mtdOrdinamento))
			return false;
		return true;
	}
	
	
	
}
