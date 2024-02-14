/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;


import java.util.List;
import java.util.Map;

import it.csi.idf.idfapi.business.be.exceptions.DuplicateRecordException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.dto.GeoPfaSearch;
import it.csi.idf.idfapi.dto.GeoPfaSearchDettaglio;
import it.csi.idf.idfapi.dto.GeoPlPfa;
import it.csi.idf.idfapi.dto.LabelValore;
import it.csi.idf.idfapi.util.PaginatedList;

public interface GeoPlPfaDAO {

	int createGeoPlPfa(GeoPlPfa newGeoPlPfa) throws DuplicateRecordException;

	GeoPlPfa findGeoPlPfaById(Integer idGeoPlPfa) throws RecordNotFoundException;

	List<GeoPlPfa> findAllGeoPlPfa();
	
	List<LabelValore> getDescrizioniPfa(String istatProv, Integer idComune);
	
	boolean isPfaEntePubblico(Integer idIntervento);
	
	GeoPfaSearchDettaglio findSearchPfaByID(StringBuilder s, List<Object> parameters);
	
	PaginatedList<GeoPfaSearchDettaglio> search(StringBuilder s, int page, int limit, List<Object>parameters);

	PaginatedList<GeoPfaSearch> searchPianiForestali(Map<String, String> queryParams);
	
	GeoPfaSearchDettaglio pianoForestaleSearchDettaglio(Integer idGeoPlPfa);
	
	public GeoPfaSearchDettaglio pianoForestaleSearchDettaglioByUser(Integer idGeoPlPfa, Integer idSoggetto);
}
