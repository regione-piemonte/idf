/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.pojo;

import java.util.List;

public class VincoloStep3DTO {
	
	String descrizioneIntervento;
	Integer tipoIntervento;
	String altroTipoIntervento;
	//Integer totaliSuperfici;
	Integer totaleTotMovimentiTerra;
	Integer totaleTotMovimentiTerraVincolo;
	Integer tempoPrevisto;
	boolean presenzaAreeDissesto;
	boolean presenzaAreeEsondazione;
	List<VincoloHeading> headings; // Copertura vegetale.

	public String getDescrizioneIntervento() {
		return descrizioneIntervento;
	}

	public void setDescrizioneIntervento(String descrizioneIntervento) {
		this.descrizioneIntervento = descrizioneIntervento;
	}

	public Integer getTipoIntervento() {
		return tipoIntervento;
	}

	public void setTipoIntervento(Integer tipoIntervento) {
		this.tipoIntervento = tipoIntervento;
	}



	public String getAltroTipoIntervento() {
		return altroTipoIntervento;
	}

	public void setAltroTipoIntervento(String altroTipoIntervento) {
		this.altroTipoIntervento = altroTipoIntervento;
	}

	public Integer getTotaleTotMovimentiTerra() {
		return totaleTotMovimentiTerra;
	}

	public void setTotaleTotMovimentiTerra(Integer totaleTotMovimentiTerra) {
		this.totaleTotMovimentiTerra = totaleTotMovimentiTerra;
	}

	public Integer getTotaleTotMovimentiTerraVincolo() {
		return totaleTotMovimentiTerraVincolo;
	}

	public void setTotaleTotMovimentiTerraVincolo(Integer totaleTotMovimentiTerraVincolo) {
		this.totaleTotMovimentiTerraVincolo = totaleTotMovimentiTerraVincolo;
	}

	public Integer getTempoPrevisto() {
		return tempoPrevisto;
	}

	public void setTempoPrevisto(Integer tempoPrevisto) {
		this.tempoPrevisto = tempoPrevisto;
	}

	public boolean isPresenzaAreeDissesto() {
		return presenzaAreeDissesto;
	}

	public void setPresenzaAreeDissesto(boolean presenzaAreeDissesto) {
		this.presenzaAreeDissesto = presenzaAreeDissesto;
	}

	public boolean isPresenzaAreeEsondazione() {
		return presenzaAreeEsondazione;
	}

	public void setPresenzaAreeEsondazione(boolean presenzaAreeEsondazione) {
		this.presenzaAreeEsondazione = presenzaAreeEsondazione;
	}

	public List<VincoloHeading> getHeadings() {
		return headings;
	}

	public void setHeadings(List<VincoloHeading> headings) {
		this.headings = headings;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n\nVincoloStep3DTO:");
		builder.append("\n\tdescrizioneIntervento = " + descrizioneIntervento);
		builder.append("\n\ttipoIntervento = " + tipoIntervento);
		builder.append("\n\taltroTipoIntervento = " + altroTipoIntervento);
		//builder.append("\n\ttotaliSuperfici = " + totaliSuperfici);
		builder.append("\n\ttotaleTotMovimentiTerra = " + totaleTotMovimentiTerra);
		builder.append("\n\ttotaleTotMovimentiTerraVincolo = " + totaleTotMovimentiTerraVincolo);
		builder.append("\n\ttempoPrevisto = " + tempoPrevisto);
		builder.append("\n\tpresenzaAreeDissesto = " + presenzaAreeDissesto);
		builder.append("\n\tpresenzaAreeEsondazione = " + presenzaAreeEsondazione);
		builder.append("\n\theadings (copertura vegetale):");
		for(VincoloHeading heading : headings) {
			builder.append("\n\t\t" + heading.getName() + ":");
			
			for(VincoloSubHeading subHeading : heading.getSubheadings())
				builder.append("\n\t\t\t" + subHeading.getName() + ": " + subHeading.isChecked());
		}
		builder.append("\n");
		return builder.toString();
	}
}
