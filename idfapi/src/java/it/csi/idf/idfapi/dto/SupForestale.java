/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;

public class SupForestale {
	private Integer idGeoSuperficieForestale;
	private Integer idGeoPlPfa;
	private Integer fkDestinazione;
	private Integer fkTipoForestale;
	private Integer fkTipoStrutturale;
	private Integer fkCompresa;
	private Integer fkPriorita;
	private BigDecimal ettari;
	private String flgTfUs;
	//private Object geometry;
	private Integer fkTipoIntervento;
	
	public Integer getIdGeoSuperficieForestale() {
		return idGeoSuperficieForestale;
	}
	public void setIdGeoSuperficieForestale(Integer idGeoSuperficieForestale) {
		this.idGeoSuperficieForestale = idGeoSuperficieForestale;
	}
	public Integer getIdGeoPlPfa() {
		return idGeoPlPfa;
	}
	public void setIdGeoPlPfa(Integer idGeoPlPfa) {
		this.idGeoPlPfa = idGeoPlPfa;
	}
	public Integer getFkDestinazione() {
		return fkDestinazione;
	}
	public void setFkDestinazione(Integer fkDestinazione) {
		this.fkDestinazione = fkDestinazione;
	}
	public Integer getFkTipoForestale() {
		return fkTipoForestale;
	}
	public void setFkTipoForestale(Integer fkTipoForestale) {
		this.fkTipoForestale = fkTipoForestale;
	}
	public Integer getFkTipoStrutturale() {
		return fkTipoStrutturale;
	}
	public void setFkTipoStrutturale(Integer fkTipoStrutturale) {
		this.fkTipoStrutturale = fkTipoStrutturale;
	}
	public Integer getFkCompresa() {
		return fkCompresa;
	}
	public void setFkCompresa(Integer fkCompresa) {
		this.fkCompresa = fkCompresa;
	}
	public Integer getFkPriorita() {
		return fkPriorita;
	}
	public void setFkPriorita(Integer fkPriorita) {
		this.fkPriorita = fkPriorita;
	}
	public BigDecimal getEttari() {
		return ettari;
	}
	public void setEttari(BigDecimal ettari) {
		this.ettari = ettari;
	}
	public String getFlgTfUs() {
		return flgTfUs;
	}
	public void setFlgTfUs(String flgTfUs) {
		this.flgTfUs = flgTfUs;
	}
	public Integer getFkTipoIntervento() {
		return fkTipoIntervento;
	}
	public void setFkTipoIntervento(Integer fkTipoIntervento) {
		this.fkTipoIntervento = fkTipoIntervento;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SupForestale [idGeoSuperficieForestale=");
		builder.append(idGeoSuperficieForestale);
		builder.append(", idGeoPlPfa=");
		builder.append(idGeoPlPfa);
		builder.append(", fkDestinazione=");
		builder.append(fkDestinazione);
		builder.append(", fkTipoForestale=");
		builder.append(fkTipoForestale);
		builder.append(", fkTipoStrutturale=");
		builder.append(fkTipoStrutturale);
		builder.append(", fkCompresa=");
		builder.append(fkCompresa);
		builder.append(", fkPriorita=");
		builder.append(fkPriorita);
		builder.append(", ettari=");
		builder.append(ettari);
		builder.append(", flgTfUs=");
		builder.append(flgTfUs);
		builder.append(", fkTipoIntervento=");
		builder.append(fkTipoIntervento);
		builder.append("]");
		return builder.toString();
	}
}
