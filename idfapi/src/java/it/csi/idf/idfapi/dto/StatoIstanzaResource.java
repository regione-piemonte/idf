/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.util.Objects;

import it.csi.idf.idfapi.util.TipoIstanzaEnum;

public class StatoIstanzaResource {
	
	private Integer idStatoIstanza;
	private String descrStatoIstanza;
	
	public Integer getIdStatoIstanza() {
		return idStatoIstanza;
	}
	public void setIdStatoIstanza(Integer idStatoIstanza) {
		this.idStatoIstanza = idStatoIstanza;
	}
	public String getDescrStatoIstanza() {
		return descrStatoIstanza;
	}
	public void setDescrStatoIstanza(String descrStatoIstanza) {
		this.descrStatoIstanza = descrStatoIstanza;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		StatoIstanzaResource statoIstanza = (StatoIstanzaResource) o;
		return Objects.equals(idStatoIstanza, statoIstanza.idStatoIstanza)
			&& Objects.equals(descrStatoIstanza, statoIstanza.descrStatoIstanza);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idStatoIstanza, descrStatoIstanza);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class StatoIstanza {\n");
		sb.append("    idStatoIstanza: ").append(idStatoIstanza).append("\n");
		sb.append("    descrStatoIstanza: ").append(descrStatoIstanza).append("\n");
		sb.append("}");
		return sb.toString();
	}
	
	public static String getDescrByTypeIstanza(String descr, TipoIstanzaEnum typeIstanza) {
		switch (typeIstanza) {
        case DICHIARAZIONE_SOSTITUTIVA: // 1
        case COMUNICAZIONE: //2
        	if("Verificata".equals(descr)) {
        		return "Verificata e accolta";
        	}else if("Rifiutata".equals(descr)) {
        		return "Verificata e negata";
        	}
        	break;
      
        case AUTORIZZAZIONE://4	
        case VINCOLO_AUTORIZZAZIONE_REGIONALE://4
        case VINCOLO_AUTORIZZAZIONE_COMUNALE: //5
        	if("Verificata".equals(descr)) {
        		return "Autorizzata";
        	}else if("Rifiutata".equals(descr)) {
        		return "Non autorizzata";
        	}
        	break;
        	
        default:
            return descr;
    }
		
		return descr;
	}
}
