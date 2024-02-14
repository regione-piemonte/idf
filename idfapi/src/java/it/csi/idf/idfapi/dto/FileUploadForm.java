/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import javax.ws.rs.FormParam;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class FileUploadForm {

	private byte[] data;
	private String name;
	private String note;
	public FileUploadForm() {
	}

	public byte[] getData() {
		return data;
	}

	@FormParam("form")
	@PartType("application/octet-stream")
	public void setData(byte[] data) {
		this.data = data;
		
	}

	public String getName() {
		return name;
	}
	@FormParam("fileName")
	@PartType("text/plain")
	public void setName(String name) {
		this.name = name;
	}
	
	public String getNote() {
		return note;
	}
	@FormParam("note")
	@PartType("text/plain")
	public void setNote(String note) {
		this.note = note;
	}

	

}
