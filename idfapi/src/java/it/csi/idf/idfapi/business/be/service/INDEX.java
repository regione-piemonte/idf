/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.service;

public interface INDEX {
	int uploadDocument(byte[] file, String fileName, long fileSize, int idIntervento, int tipoDocumento);
	boolean fileExistWithID(int id);
	void deleteFileWithID(int id);
}
