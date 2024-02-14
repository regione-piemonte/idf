/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.time.LocalDate;

public class GeoPtLottoIntervento {
	
	private Integer idgeoPtLottoIntervento;
	private LocalDate datainserimento;
	private Object geometria;
	private Integer idIntervento;
	
	public Integer getIdgeoPtLottoIntervento() {
		return idgeoPtLottoIntervento;
	}
	public void setIdgeoPtLottoIntervento(Integer idgeoPtLottoIntervento) {
		this.idgeoPtLottoIntervento = idgeoPtLottoIntervento;
	}
	public LocalDate getDatainserimento() {
		return datainserimento;
	}
	public void setDatainserimento(LocalDate datainserimento) {
		this.datainserimento = datainserimento;
	}
	public Object getGeometria() {
		return geometria;
	}
	public void setGeometria(Object geometria) {
		this.geometria = geometria;
	}
	public Integer getIdIntervento() {
		return idIntervento;
	}
	public void setIdIntervento(Integer idIntervento) {
		this.idIntervento = idIntervento;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GeoPtLottoIntervento [idgeoPtLottoIntervento=");
		builder.append(idgeoPtLottoIntervento);
		builder.append(", datainserimento=");
		builder.append(datainserimento);
		builder.append(", geometria=");
		builder.append(geometria);
		builder.append(", idIntervento=");
		builder.append(idIntervento);
		builder.append("]");
		return builder.toString();
	}
}
