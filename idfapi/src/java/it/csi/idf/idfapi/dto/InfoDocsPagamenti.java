/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.util.List;

public class InfoDocsPagamenti {
	private boolean cauzioneMancante;
	private String compensazioneType;
	private boolean compensazioneFisicaMancante;
	private boolean compensazioneMonetariaMancante;
	private List<TipoAllegatoExtended> listDocs;
	
	public boolean isCauzioneMancante() {
		return cauzioneMancante;
	}
	public String getCompensazioneType() {
		return compensazioneType;
	}
	public boolean isCompensazioneFisicaMancante() {
		return compensazioneFisicaMancante;
	}
	public boolean isCompensazioneMonetariaMancante() {
		return compensazioneMonetariaMancante;
	}
	public void setCauzioneMancante(boolean cauzioneMancante) {
		this.cauzioneMancante = cauzioneMancante;
	}
	public void setCompensazioneType(String compensazioneType) {
		this.compensazioneType = compensazioneType;
	}
	public void setCompensazioneFisicaMancante(boolean compensazioneFisicaMancante) {
		this.compensazioneFisicaMancante = compensazioneFisicaMancante;
	}
	public void setCompensazioneMonetariaMancante(boolean compensazioneMonetariaMancante) {
		this.compensazioneMonetariaMancante = compensazioneMonetariaMancante;
	}
	public List<TipoAllegatoExtended> getListDocs() {
		return listDocs;
	}
	public void setListDocs(List<TipoAllegatoExtended> listDocs) {
		this.listDocs = listDocs;
	}
	
	
}
