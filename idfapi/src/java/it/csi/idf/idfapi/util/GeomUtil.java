/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

public class GeomUtil {
	public static String getGeometryGmlExtend(String geom) {
		geom = geom.replace("srsName=\"EPSG:32632\"", "srsName=\"EPSG:32632\" xmlns:gml=\"http://www.opengis.net/gml\"");
		geom = geom.replace("<gml:coordinates", "<gml:coordinates decimal=\".\" cs=\",\" ts=\" \"");
		return geom;
	}
}
