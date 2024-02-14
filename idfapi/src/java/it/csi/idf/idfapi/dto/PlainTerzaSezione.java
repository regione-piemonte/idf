/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.util.List;


public class PlainTerzaSezione {

	List<BoscoHeadings> headings;

	public List<BoscoHeadings> getHeadings() {
		return headings;
	}
	public void setHeadings(List<BoscoHeadings> headings) {
		this.headings = headings;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlainTerzaSezione [headings=");
		builder.append(headings);
		builder.append("]");
		return builder.toString();
	}
}
