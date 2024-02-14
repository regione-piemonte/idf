/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.time.LocalDate;

public class GeoLnLottoIntervento {

	private Integer idgeoLnLottoIntervento;
	private LocalDate datainserimento;
	private Object geometria;
	private Integer idIntervento;
	private Integer lunghezzaMetri; 
	
	
	public Integer getIdgeoLnLottoIntervento() {
		return idgeoLnLottoIntervento;
	}
	public void setIdgeoLnLottoIntervento(Integer idgeoLnLottoIntervento) {
		this.idgeoLnLottoIntervento = idgeoLnLottoIntervento;
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
		builder.append("GeoLnLottoIntervento [");
		if (idgeoLnLottoIntervento != null) {
			builder.append("idgeoLnLottoIntervento=");
			builder.append(idgeoLnLottoIntervento);
			builder.append(", ");
		}
		if (datainserimento != null) {
			builder.append("datainserimento=");
			builder.append(datainserimento);
			builder.append(", ");
		}
		if (geometria != null) {
			builder.append("geometria=");
			builder.append(geometria);
			builder.append(", ");
		}
		if (idIntervento != null) {
			builder.append("idIntervento=");
			builder.append(idIntervento);
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
