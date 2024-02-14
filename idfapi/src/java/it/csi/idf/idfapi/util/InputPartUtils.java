/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

import javax.ws.rs.core.MultivaluedMap;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;

public class InputPartUtils {
	
	public static String getFileNameWithExtension(InputPart inputPart) {

		MultivaluedMap<String, String> header = inputPart.getHeaders();
		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");

		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {

				String[] name = filename.split("=");

				return name[1].trim().replaceAll("\"", "");
			}
		}
		return "unknown";
	}
	
	public static String getFileExtension(String fileName) {
		int idx = fileName.lastIndexOf(".");
		if(idx > -1) {
			return fileName.substring(idx,fileName.length());
		}
		return "";
		
	}

}
