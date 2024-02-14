/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.util.Objects;

public class GeoPLProvincia {
	
	private String istatProv;
	private String siglaProv;
	private String denominazioneProv;
    private Object geometria;
    private Integer fkRegione;
	
	public String getIstatProv() {
		return istatProv;
	}

	public void setIstatProv(String istatProv) {
		this.istatProv = istatProv;
	}

	public String getSiglaProv() {
		return siglaProv;
	}

	public void setSiglaProv(String siglaProv) {
		this.siglaProv = siglaProv;
	}

	public String getDenominazioneProv() {
		return denominazioneProv;
	}

	public void setDenominazioneProv(String denominazioneProv) {
		this.denominazioneProv = denominazioneProv;
	}

	public Object getGeometria() {
		return geometria;
	}

	public void setGeometria(Object geometria) {
		this.geometria = geometria;
	}

	public Integer getFkRegione() {
		return fkRegione;
	}

	public void setFkRegione(Integer fkRegione) {
		this.fkRegione = fkRegione;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GeoPLProvincia provincia = (GeoPLProvincia) o;
		return Objects.equals(istatProv, provincia.istatProv) && Objects.equals(siglaProv, provincia.siglaProv)
				&& Objects.equals(denominazioneProv, provincia.denominazioneProv)
				&& Objects.equals(fkRegione, provincia.fkRegione);
	}

	@Override
	public int hashCode() {
		return Objects.hash(istatProv, siglaProv, denominazioneProv, fkRegione);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class GeoPLProvincia {\n");

		sb.append("    istatProv: ").append(istatProv).append("\n");
		sb.append("    siglaProv: ").append(siglaProv).append("\n");
		sb.append("    denominazioneProv: ").append(denominazioneProv).append("\n");
		sb.append("    fkRegione: ").append(fkRegione).append("\n");
		sb.append("}");
		return sb.toString();
	}

}
