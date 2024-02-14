/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.util.List;

public class ProfessionistaElencoAllegati {
	
	private List<FatDocumentoAllegato> allegati;

	public List<FatDocumentoAllegato> getAllegati() {
		return allegati;
	}

	public void setAllegati(List<FatDocumentoAllegato> allegati) {
		this.allegati = allegati;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProfessionistaElencoAllegati [allegati=");
		builder.append(allegati);
		builder.append("]");
		return builder.toString();
	}
}
