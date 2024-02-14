/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class ProgressivoNomeBreve {
	
	private Integer progressivoEvento;
	private String nomeBreve;
	private Integer idEvento;
	
	public Integer getProgressivoEvento() {
		return progressivoEvento;
	}
	public void setProgressivoEvento(Integer progressivoEvento) {
		this.progressivoEvento = progressivoEvento;
	}
	public String getNomeBreve() {
		return nomeBreve;
	}
	public void setNomeBreve(String nomeBreve) {
		this.nomeBreve = nomeBreve;
	}
	public Integer getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProgressivoNomeBreve [progressivoEvento=");
		builder.append(progressivoEvento);
		builder.append(", nomeBreve=");
		builder.append(nomeBreve);
		builder.append(", idEvento=");
		builder.append(idEvento);
		builder.append("]");
		return builder.toString();
	}
	
	
}
