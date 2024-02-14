/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class CsvReport {

	String title;
	String dataCsv;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDataCsv() {
		return dataCsv;
	}
	public void setDataCsv(String dataCsv) {
		this.dataCsv = dataCsv;
	}
	
}
