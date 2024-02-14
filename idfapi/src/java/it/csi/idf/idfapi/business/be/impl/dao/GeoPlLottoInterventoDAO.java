/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.math.BigDecimal;
import java.util.List;

import it.csi.idf.idfapi.dto.LottoIntervento;

public interface GeoPlLottoInterventoDAO {
	
	void deleteGeoPlLottoIntervento(int idIntervento);
	
	BigDecimal getCuttingVolumesShareByForestParticella(Integer idIntervento, Integer idGeoParticellaForest);
	
	BigDecimal getSupTagliataForForestParticle(Integer idIntervento,Integer idGeoParticellaForest);
	
	List<LottoIntervento> getGeometrieGmlByFkIntervento(Integer idIntervento);
	List<LottoIntervento> getGeometrieGmlByFkInterventoNew(Integer idIntervento);//7777
	
	Double getAreaTrasformazioneByFkIntervento(Integer idIntervento);
	
	void addGeometria(Integer idIntervento, Integer idgeoPlPropCatasto);
	
	void removeGeometria(Integer idIntervento, Integer idgeoPlPropCatasto);
	
	void updateSupTagliataByIntervento(int idIntervento);

	void updateSupTagliataById(int idGeo);
	
	Double getAreaItersecWithParticella(Integer idIntervento, Integer idgeo_pl_prop_catasto);


	void duplicateIntervento(Integer idIntervento, Integer idInterventoNew, Integer idConfUtente);

	Double getAreaInterventoByIdIntervento(Integer idIntervento);

	List<String> getIntersezioneGeometriaComune (Integer idIntervento);

	List<String> getIntersezioneGeometriaComuneTrasf(Integer idIntervento);

}
