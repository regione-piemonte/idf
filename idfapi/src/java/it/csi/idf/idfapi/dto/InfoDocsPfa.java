/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import it.csi.idf.idfapi.util.TipoAllegatoPfaEnum;

public class InfoDocsPfa {
	private boolean isMissing;
	private TipoAllegatoPfaEnum docType;
	private String docDescription;

	public boolean isMissing() {
		return isMissing;
	}

	public void setMissing(boolean missing) {
		isMissing = missing;
	}

	public TipoAllegatoPfaEnum getDocType() {
		return docType;
	}

	public void setDocType(TipoAllegatoPfaEnum docType) {
		this.docType = docType;
	}

	public String getDocDescription() {
		return docDescription;
	}

	public void setDocDescription(String docDescription) {
		this.docDescription = docDescription;
	}
}


