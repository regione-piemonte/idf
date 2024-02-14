/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.pojo;

import java.util.List;

public class PlainVincoloTerzaSezione {
	
	List<VincoloHeading> headings;

	public List<VincoloHeading> getHeadings() {
		return headings;
	}

	public void setHeadings(List<VincoloHeading> headings) {
		this.headings = headings;
	}
}
