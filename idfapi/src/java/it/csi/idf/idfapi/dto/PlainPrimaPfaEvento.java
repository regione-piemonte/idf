/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class PlainPrimaPfaEvento {
	
	private Object plEventoGeometria;
	private Object ptEventoGeometria;
	private Object lnEventoGeometria;
	
	public Object getPlEventoGeometria() {
		return plEventoGeometria;
	}
	public void setPlEventoGeometria(Object plEventoGeometria) {
		this.plEventoGeometria = plEventoGeometria;
	}
	public Object getPtEventoGeometria() {
		return ptEventoGeometria;
	}
	public void setPtEventoGeometria(Object ptEventoGeometria) {
		this.ptEventoGeometria = ptEventoGeometria;
	}
	public Object getLnEventoGeometria() {
		return lnEventoGeometria;
	}
	public void setLnEventoGeometria(Object lnEventoGeometria) {
		this.lnEventoGeometria = lnEventoGeometria;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlainPrimaPfaEvento [plEventoGeometria=");
		builder.append(plEventoGeometria);
		builder.append(", ptEventoGeometria=");
		builder.append(ptEventoGeometria);
		builder.append(", lnEventoGeometria=");
		builder.append(lnEventoGeometria);
		builder.append("]");
		return builder.toString();
	}
}
