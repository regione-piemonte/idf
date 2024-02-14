/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

public class PlainParticelleCatastali {
	
	private Integer idGeoPlPropCatasto;
	private Integer id;
	private ComuneResource comune;
	private String sezione;
	private Integer foglio;
	private String particella;
	private BigDecimal supCatastale;
	private BigDecimal supCartograficaHa;
	private BigDecimal supIntervento;
	private boolean toDelete;
	private Integer fkConfigUtente;
	//GEOMETRIA:geometry(Geometry,32632);
	private Object geometry;
	//7777
	private String geometria;
	private LocalDate dataAggiornamentoDatoCatastale;
	
	
	public Integer getIdGeoPlPropCatasto() {
		return idGeoPlPropCatasto;
	}
	public void setIdGeoPlPropCatasto(Integer idGeoPlPropCatasto) {
		this.idGeoPlPropCatasto = idGeoPlPropCatasto;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ComuneResource getComune() {
		return comune;
	}
	public void setComune(ComuneResource comune) {
		this.comune = comune;
	}
	public String getSezione() {
		return sezione;
	}
	public void setSezione(String sezione) {
		this.sezione = sezione;
	}
	public Integer getFoglio() {
		return foglio;
	}
	public void setFoglio(Integer foglio) {
		this.foglio = foglio;
	}
	public String getParticella() {
		return particella;
	}
	public void setParticella(String particella) {
		this.particella = particella;
	}
	public BigDecimal getSupCatastale() {
		return supCatastale;
	}
	public void setSupCatastale(BigDecimal supCatastale) {
		this.supCatastale = supCatastale;
	}
	public boolean isToDelete() {
		return toDelete;
	}
	public void setToDelete(boolean toDelete) {
		this.toDelete = toDelete;
	}
	
	public BigDecimal getSupIntervento() {
		return supIntervento;
	}
	public void setSupIntervento(BigDecimal supIntervento) {
		this.supIntervento = supIntervento;
	}
	
	public Object getGeometry() {
		return geometry;
	}
	public void setGeometry(Object geometry) {
		this.geometry = geometry;
	}
	public String getGeometria() {
		return geometria;
	}
	public void setGeometria(String geometria) {
		this.geometria = geometria;
	}	
	public BigDecimal getSupCartograficaHa() {
		return supCartograficaHa;
	}
	public void setSupCartograficaHa(BigDecimal supCartograficaHa) {
		this.supCartograficaHa = supCartograficaHa;
	}
	public Integer getFkConfigUtente() {
		return fkConfigUtente;
	}
	public void setFkConfigUtente(Integer fkConfigUtente) {
		this.fkConfigUtente = fkConfigUtente;
	}


	public LocalDate getDataAggiornamentoDatoCatastale() {
		return dataAggiornamentoDatoCatastale;
	}

	public void setDataAggiornamentoDatoCatastale(LocalDate dataAggiornamentoDatoCatastale) {
		this.dataAggiornamentoDatoCatastale = dataAggiornamentoDatoCatastale;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof PlainParticelleCatastali)) return false;
		PlainParticelleCatastali that = (PlainParticelleCatastali) o;
		return toDelete == that.toDelete && Objects.equals(idGeoPlPropCatasto, that.idGeoPlPropCatasto) && Objects.equals(id, that.id) && Objects.equals(comune, that.comune) && Objects.equals(sezione, that.sezione) && Objects.equals(foglio, that.foglio) && Objects.equals(particella, that.particella) && Objects.equals(supCatastale, that.supCatastale) && Objects.equals(supCartograficaHa, that.supCartograficaHa) && Objects.equals(supIntervento, that.supIntervento) && Objects.equals(fkConfigUtente, that.fkConfigUtente) && Objects.equals(geometry, that.geometry) && Objects.equals(dataAggiornamentoDatoCatastale, that.dataAggiornamentoDatoCatastale);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idGeoPlPropCatasto, id, comune, sezione, foglio, particella, supCatastale, supCartograficaHa, supIntervento, toDelete, fkConfigUtente, geometry, dataAggiornamentoDatoCatastale);
	}

	@Override
	public String toString() {
		return new StringJoiner("\n ", PlainParticelleCatastali.class.getSimpleName() + "[", "]")
				.add("idGeoPlPropCatasto=" + idGeoPlPropCatasto)
				.add("id=" + id)
				.add("comune=" + comune)
				.add("sezione='" + sezione + "'")
				.add("foglio=" + foglio)
				.add("particella='" + particella + "'")
				.add("supCatastale=" + supCatastale)
				.add("supCartograficaHa=" + supCartograficaHa)
				.add("supIntervento=" + supIntervento)
				.add("toDelete=" + toDelete)
				.add("fkConfigUtente=" + fkConfigUtente)
				.add("geometry=" + geometry)
				.add("dataAggiornamentoDatoCatastale=" + dataAggiornamentoDatoCatastale)
				.toString();
	}

		

}
