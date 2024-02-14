/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GeoPlEvento {
	
	private Integer idgeoPlEvento;
	private LocalDate dataInserimento;
	private Object geometria;
	private Integer fkEvento;
	private BigDecimal superficie_ha ;
	
	
	public Integer getIdgeoPlEvento() {
		return idgeoPlEvento;
	}
	public void setIdgeoPlEvento(Integer idgeoPlEvento) {
		this.idgeoPlEvento = idgeoPlEvento;
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
		builder.append("GeoPlEvento [");
		if (idgeoPlEvento != null) {
			builder.append("idgeoPlEvento=");
			builder.append(idgeoPlEvento);
			builder.append(", ");
		}
		if (dataInserimento != null) {
			builder.append("dataInserimento=");
			builder.append(dataInserimento);
			builder.append(", ");
		}
		if (geometria != null) {
			builder.append("geometria=");
			builder.append(geometria);
			builder.append(", ");
		}
		if (fkEvento != null) {
			builder.append("fkEvento=");
			builder.append(fkEvento);
			builder.append(", ");
		}
		if (superficie_ha != null) {
			builder.append("superficie_ha=");
			builder.append(superficie_ha);
		}
		builder.append("]");
		return builder.toString();
	}
	public BigDecimal getSuperficie_ha() {
		return superficie_ha;
	}
	public void setSuperficie_ha(BigDecimal superficie_ha) {
		this.superficie_ha = superficie_ha;
	}
}
