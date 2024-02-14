/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.util.List;

public class PlainParticelleCatastals {
	private List<PlainParticelleCatastali> particelleCatastali;

	public List<PlainParticelleCatastali> getParticelleCatastali() {
		return particelleCatastali;
	}
	public void setParticelleCatastali(List<PlainParticelleCatastali> particelleCatastali) {
		this.particelleCatastali = particelleCatastali;
	}
}


