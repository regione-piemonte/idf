/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.dto.GeoPlParticellaForest;
import it.csi.idf.idfapi.dto.PlainParticelleCatastali;

public interface GeoPlParticellaForestDAO {

	GeoPlParticellaForest getOneIdByGeoPlPfa(Integer idGeoPlPfa);
	GeoPlParticellaForest getForestParticleById(Integer idGeoPlPartiellaForest);
	List<GeoPlParticellaForest> getForestParticleByInterventoId(Integer idIntervento);
	List<GeoPlParticellaForest> getForestParticleForShootingMirror(Integer idIntervento);
	List<Integer> getIdsForestParticlesByIdEvento(Integer idEvento);
	List<Integer> getIdForestParticlesByIdGeoPfaWithSize(Integer idgeoPlfa, Integer size);
	List<GeoPlParticellaForest> getForestParticlesByCadastralParticles(
			List<PlainParticelleCatastali> particelleCatastali, Integer idGeoPlPfa);
	List<GeoPlParticellaForest> getForestParticlesByGeomIntervento(Integer idIntervento);
	
}
