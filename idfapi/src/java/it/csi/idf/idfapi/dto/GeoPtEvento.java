/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.time.LocalDate;

public class GeoPtEvento {
	
	private Integer idgeoPtEvento;
	private LocalDate dataInserimento;
	private Object geometria;
	private Integer fkEvento;
	
	public Integer getIdgeoPtEvento() {
		return idgeoPtEvento;
	}
	public void setIdgeoPtEvento(Integer idgeoPtEvento) {
		this.idgeoPtEvento = idgeoPtEvento;
	}
	public LocalDate getDataInserimento() {
		return dataInserimento;
	}
	public void setDataInserimento(LocalDate dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	public Object getGeometria() {
		return geometria;
	}
	public void setGeometria(Object geometria) {
		this.geometria = geometria;
	}
	public Integer getFkEvento() {
		return fkEvento;
	}
	public void setFkEvento(Integer fkEvento) {
		this.fkEvento = fkEvento;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GeoPtEvento [idgeoPtEvento=");
		builder.append(idgeoPtEvento);
		builder.append(", dataInserimento=");
		builder.append(dataInserimento);
		builder.append(", geometria=");
		builder.append(geometria);
		builder.append(", fkEvento=");
		builder.append(fkEvento);
		builder.append("]");
		return builder.toString();
	}
}
