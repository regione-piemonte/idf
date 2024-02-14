/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.service;


import java.util.List;

import org.opengis.geometry.Geometry;

import it.csi.idf.idfapi.dto.PlainParticelleCatastali;

public interface SIGMATER {
	
	PlainParticelleCatastali getParticelleCatastali(PlainParticelleCatastali particelleCatastali);
	Geometry getGeometryFromParticelleCatastali(PlainParticelleCatastali particelleCatastali);
	List<Integer> getRandomForestParticlesByIdGeoPfa(Integer idgeoPlPfa, Integer size);
}
