/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.dto.AreaDiSaggioDatiGeneraliDTO;
import it.csi.idf.idfapi.dto.GeecoAdsFeature;
import it.csi.idf.idfapi.dto.GeecoLayer;
import it.csi.idf.idfapi.dto.GeecoPfaEventoFeature;
import it.csi.idf.idfapi.dto.GeecoPfaFeature;
import it.csi.idf.idfapi.dto.GeecoPfaInterventoFeature;
import it.csi.idf.idfapi.dto.GeecoProfilo;
import it.csi.idf.idfapi.dto.GeecoTrsFeature;
import it.csi.idf.idfapi.dto.GeecoVincoloFeature;

public interface GeecoLocalDAO {
	
	public List<GeecoAdsFeature> findGeometriaAdsById(String[] idgeoPtAds)throws RecordNotFoundException;
	public GeecoProfilo findProfiloById(String idProfiloGeeco)throws RecordNotFoundException;
	public List<GeecoLayer> findLayerByIdProfilo(String idProfiloGeeco);
	public GeecoTrsFeature findGeometriaTrsById(String idTrs)throws RecordNotFoundException;
	public  List<GeecoTrsFeature> findGeometrieTrsById(String[] idIntervento) throws RecordNotFoundException;
	public List<GeecoPfaEventoFeature> findGeometriePfaEventoById(String[] idEvento, String tipo) throws RecordNotFoundException;
	public List<GeecoPfaInterventoFeature> findGeometriePfaInterventoById(String[] idIntervento, String tipo) throws RecordNotFoundException;
	public List<GeecoPfaFeature> findGeometriePfaById(String[] idPfa) throws RecordNotFoundException;
	public AreaDiSaggioDatiGeneraliDTO findADSByIdgeoPtAds(Long convertToLong);
	public List<GeecoPfaFeature> findGeometriePfaByIdEvento(String[] idEvento) throws RecordNotFoundException;
	public int countGeometrieEvento(String[] idEvento);
	public List<GeecoPfaFeature> findGeometriePfaByIdIntervento(String[] idIntervento) throws RecordNotFoundException;
	public int countGeometrieIntervento(String[] idIntervento);
	public List<GeecoVincoloFeature> findGeometrieVincoloById(String[] idIntervento);
	public List<GeecoProfilo> testQuery(String first, String input);
	
}
