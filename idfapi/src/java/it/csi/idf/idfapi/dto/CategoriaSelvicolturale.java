/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.util.Objects;
import java.util.StringJoiner;

public class CategoriaSelvicolturale {
	
	private Integer idCategoriaSelvicolturale;
	private String descrCategoriaSelvicolturale;
	private Integer mtdOrdinamento;
	private Byte flgVisibile;


	public Integer getIdCategoriaSelvicolturale() {
		return idCategoriaSelvicolturale;
	}

	public void setIdCategoriaSelvicolturale(Integer idCategoriaSelvicolturale) {
		this.idCategoriaSelvicolturale = idCategoriaSelvicolturale;
	}

	public String getDescrCategoriaSelvicolturale() {
		return descrCategoriaSelvicolturale;
	}

	public void setDescrCategoriaSelvicolturale(String descrCategoriaSelvicolturale) {
		this.descrCategoriaSelvicolturale = descrCategoriaSelvicolturale;
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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CategoriaSelvicolturale)) return false;
		CategoriaSelvicolturale that = (CategoriaSelvicolturale) o;
		return Objects.equals(idCategoriaSelvicolturale, that.idCategoriaSelvicolturale) && Objects.equals(descrCategoriaSelvicolturale, that.descrCategoriaSelvicolturale) && Objects.equals(mtdOrdinamento, that.mtdOrdinamento) && Objects.equals(flgVisibile, that.flgVisibile);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCategoriaSelvicolturale, descrCategoriaSelvicolturale, mtdOrdinamento, flgVisibile);
	}

	@Override
	public String toString() {
		return new StringJoiner("\n ", CategoriaSelvicolturale.class.getSimpleName() + "[", "]")
				.add("idCategoriaSelvicolturale=" + idCategoriaSelvicolturale)
				.add("descrCategoriaSelvicolturale='" + descrCategoriaSelvicolturale + "'")
				.add("mtdOrdinamento=" + mtdOrdinamento)
				.add("flgVisibile=" + flgVisibile)
				.toString();
	}
}
