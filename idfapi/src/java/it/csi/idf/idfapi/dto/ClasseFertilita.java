/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class ClasseFertilita {
	
	private Integer idClasseFertilita;
	private String descrClasseFertilita;
	private Integer mtdOrdinamento;
	private boolean flgVisibile;
	
	public ClasseFertilita() {
		super();
	}

	public Integer getIdClasseFertilita() {
		return idClasseFertilita;
	}

	public void setIdClasseFertilita(Integer idClasseFertilita) {
		this.idClasseFertilita = idClasseFertilita;
	}

	public String getDescrClasseFertilita() {
		return descrClasseFertilita;
	}

	public void setDescrClasseFertilita(String descrClasseFertilita) {
		this.descrClasseFertilita = descrClasseFertilita;
	}

	public Integer getMtdOrdinamento() {
		return mtdOrdinamento;
	}

	public void setMtdOrdinamento(Integer mtdOrdinamento) {
		this.mtdOrdinamento = mtdOrdinamento;
	}

	public boolean isFlgVisibile() {
		return flgVisibile;
	}

	public void setFlgVisibile(boolean flgVisibile) {
		this.flgVisibile = flgVisibile;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClasseFertilita [idClasseFertilita=");
		builder.append(idClasseFertilita);
		builder.append(", descrClasseFertilita=");
		builder.append(descrClasseFertilita);
		builder.append(", mtdOrdinamento=");
		builder.append(mtdOrdinamento);
		builder.append(", flgVisibile=");
		builder.append(flgVisibile);
		builder.append("]");
		return builder.toString();
	}
		
}
