/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class EventoPartfor {

	private Integer idEvento;
	private Integer idgeoPlParticellaForest;
	private Integer percDano;
	
	public Integer getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
	}
	public Integer getIdgeoPlParticellaForest() {
		return idgeoPlParticellaForest;
	}
	public void setIdgeoPlParticellaForest(Integer idgeoPlParticellaForest) {
		this.idgeoPlParticellaForest = idgeoPlParticellaForest;
	}
	public Integer getPercDano() {
		return percDano;
	}
	public void setPercDano(Integer percDano) {
		this.percDano = percDano;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idEvento == null) ? 0 : idEvento.hashCode());
		result = prime * result + ((idgeoPlParticellaForest == null) ? 0 : idgeoPlParticellaForest.hashCode());
		result = prime * result + ((percDano == null) ? 0 : percDano.hashCode());
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
		EventoPartfor other = (EventoPartfor) obj;
		if (idEvento == null) {
			if (other.idEvento != null)
				return false;
		} else if (!idEvento.equals(other.idEvento))
			return false;
		if (idgeoPlParticellaForest == null) {
			if (other.idgeoPlParticellaForest != null)
				return false;
		} else if (!idgeoPlParticellaForest.equals(other.idgeoPlParticellaForest))
			return false;
		if (percDano == null) {
			if (other.percDano != null)
				return false;
		} else if (!percDano.equals(other.percDano))
			return false;
		return true;
	}
	
	
	
}
