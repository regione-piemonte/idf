/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;
import java.sql.Date;
import java.time.LocalDate;
public class GeecoIvnTagliFeature extends GeecoFeature {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1192661629101349749L;

	public String getGeometria() {
		return geometria;
	}
	public void setGeometria(String geometria) {
		this.geometria = geometria;
	}
	public String getFkIntervento() {
		return fkIntervento;
	}
	public void setFkIntervento(String fkIntervento) {
		this.fkIntervento = fkIntervento;
	}
	public Date getDatainserimento() {
		return datainserimento;
	}
	public void setDatainserimento(Date datainserimento) {
		this.datainserimento = datainserimento;
	}
	public String getSuperficie() {
		return superficie;
	}
	public void setSuperficie(String superficie) {
		this.superficie = superficie;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getIdgeoPtLottoIntervento() {
		return idgeoPtLottoIntervento;
	}
	public void setIdgeoPtLottoIntervento(String idgeoPtLottoIntervento) {
		this.idgeoPtLottoIntervento = idgeoPtLottoIntervento;
	}

	private String idgeoPtLottoIntervento;
	private String geometria;	
	private String fkIntervento;
	private Date datainserimento;
	private String superficie;

}
