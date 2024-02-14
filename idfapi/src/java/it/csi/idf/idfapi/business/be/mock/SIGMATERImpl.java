/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.mock;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import org.opengis.geometry.Geometry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.GeoPlParticellaForestDAO;
import it.csi.idf.idfapi.business.be.service.SIGMATER;
import it.csi.idf.idfapi.dto.PlainParticelleCatastali;

@Component
public class SIGMATERImpl implements SIGMATER {
	
	@Autowired
	private GeoPlParticellaForestDAO geoPlParticellaForestDAO;
	
	
	@Override
	public PlainParticelleCatastali getParticelleCatastali(PlainParticelleCatastali particelleCatastali) {
		
		BigDecimal[] surfaces = { BigDecimal.valueOf(2.9100), BigDecimal.valueOf(1.4200),
				BigDecimal.valueOf(3.6400), BigDecimal.valueOf(3.5300), BigDecimal.valueOf(4.4200),
				BigDecimal.valueOf(4.0700), BigDecimal.valueOf(2.6100), BigDecimal.valueOf(5.5000),
				BigDecimal.valueOf(4.6600), BigDecimal.valueOf(3.3100), BigDecimal.valueOf(2.3500),
				BigDecimal.valueOf(4.1100), BigDecimal.valueOf(5.1300), BigDecimal.valueOf(3.3200) };

		Random r=new Random();
		int randomNumber=r.nextInt(surfaces. length);
		
		particelleCatastali.setSupCatastale(surfaces[randomNumber]);
		
		return particelleCatastali;
	}

	@Override
	public Geometry getGeometryFromParticelleCatastali(PlainParticelleCatastali particelleCatastali) {
		// TODO MOCK IMPLEMENTATION, returning null for now
		return null;
	}

	
	//TODO MOCK IMPLEMENTATION, based on idgeo_pl_pfa -> currently set to randomly choose for forest particles belonging to PFA
	@Override
	public List<Integer> getRandomForestParticlesByIdGeoPfa(Integer idgeoPlPfa,Integer size) {
		
		return geoPlParticellaForestDAO.getIdForestParticlesByIdGeoPfaWithSize(idgeoPlPfa, size);
		
	}
	
	
	
	
}
