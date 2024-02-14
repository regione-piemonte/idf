/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;

public class PlainParticelleCat {
	
	private Integer id;
	private ComuneResource comune;
	private String sezione;
	private Integer foglio;
	private String particella;
	private BigDecimal supCatastale;
	private boolean toDelete;
	// GEOMETRIA:geometry(Geometry,32632);
	private Object geometry;
	
	
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
	
	
	public Object getGeometry() {
		return geometry;
	}
	public void setGeometry(Object geometry) {
		this.geometry = geometry;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlainParticelleCatastali [id=");
		builder.append(id);
		builder.append(", comune=");
		builder.append(comune);
		builder.append(", sezione=");
		builder.append(sezione);
		builder.append(", foglio=");
		builder.append(foglio);
		builder.append(", particella=");
		builder.append(particella);
		builder.append(", supCatastale=");
		builder.append(supCatastale);
		builder.append(", toDelete=");
		builder.append(toDelete);
		builder.append("]");
		return builder.toString();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlainParticelleCat other = (PlainParticelleCat) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
