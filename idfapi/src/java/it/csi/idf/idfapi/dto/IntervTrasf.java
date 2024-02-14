/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.time.LocalDate;

public class IntervTrasf {
	private Integer idgeoPlIntervTrasf;
	private Integer fkIntervento;
	private LocalDate dataInserimento;
	private Object geometria;
	
	public Integer getIdgeoPlIntervTrasf() {
		return idgeoPlIntervTrasf;
	}
	public void setIdgeoPlIntervTrasf(Integer idgeoPlIntervTrasf) {
		this.idgeoPlIntervTrasf = idgeoPlIntervTrasf;
	}
	public Integer getFkIntervento() {
		return fkIntervento;
	}
	public void setFkIntervento(Integer fkIntervento) {
		this.fkIntervento = fkIntervento;
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
	
}
