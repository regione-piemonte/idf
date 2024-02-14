/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;


import java.util.List;

public class InterventoRiepilogo {

	private ChiusuraInformazioni chiusuraInformazioni;
	private SoggettoResource utilizzatore;
	private List<ShootingMirrorDTO> shootingMirrorInformazioni;
	
	public ChiusuraInformazioni getChiusuraInformazioni() {
		return chiusuraInformazioni;
	}
	public void setChiusuraInformazioni(ChiusuraInformazioni chiusuraInformazioni) {
		this.chiusuraInformazioni = chiusuraInformazioni;
	}
	public SoggettoResource getUtilizzatore() {
		return utilizzatore;
	}
	public void setUtilizzatore(SoggettoResource utilizzatore) {
		this.utilizzatore = utilizzatore;
	}
	public List<ShootingMirrorDTO> getShootingMirrorInformazioni() {
		return shootingMirrorInformazioni;
	}
	public void setShootingMirrorInformazioni(List<ShootingMirrorDTO> shootingMirrorInformazioni) {
		this.shootingMirrorInformazioni = shootingMirrorInformazioni;
	}
	
	


}
