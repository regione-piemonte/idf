/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.time.LocalDate;

public class GeoLnEvento {

	private Integer idgeoLnEvento;
	private LocalDate dataInserimento;
	private Object geometria;
	private Integer fkEvento;
	private Integer lunghezzaMetri;
	
	public Integer getIdgeoLnEvento() {
		return idgeoLnEvento;
	}
	public void setIdgeoLnEvento(Integer idgeoLnEvento) {
		this.idgeoLnEvento = idgeoLnEvento;
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
		builder.append("GeoLnEvento [");
		if (idgeoLnEvento != null) {
			builder.append("idgeoLnEvento=");
			builder.append(idgeoLnEvento);
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
		if (lunghezzaMetri != null) {
			builder.append("lunghezzaMetri=");
			builder.append(lunghezzaMetri);
		}
		builder.append("]");
		return builder.toString();
	}
	public Integer getLunghezzaMetri() {
		return lunghezzaMetri;
	}
	public void setLunghezzaMetri(Integer lunghezzaMetri) {
		this.lunghezzaMetri = lunghezzaMetri;
	}
}
