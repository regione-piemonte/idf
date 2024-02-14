/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfgeoapi.integration.dao;

import it.csi.ecogis.util.dto.GeoJSONFeature;
import it.csi.idf.idfgeoapi.dto.*;
import it.csi.idf.idfgeoapi.exception.*;

public interface LayerDao {

	public FeatureForLayerDto insertFeatureOnLayer(FeatureForLayerDto digaNaz) throws DaoException;
	public GeoJSONFeature insertAdsOnLayer(AdsForLayerDto feature) throws DaoException;
	public void updateAdsOnLayer(AdsForLayerDto feature) throws DaoException;
	public void deleteAdsOnLayer(Long featureId) throws DaoException;
	public AdsForLayerDto findAdsById(String idAds) throws DaoException;
	public Long insertTrasfomazioneOnLayer(TrasformazioneForLayerDto dto) throws DaoException;
	public void updateTrasformazioneOnLayer(TrasformazioneForLayerDto createForUpdate)throws DaoException;
	public TrasformazioneForLayerDto findTrasformazioneById(Long id) throws DaoException;
	public long insertEventoPtOnLayer(EventoPtForLayerDto dto) throws DaoException;
	public long insertEventoPlOnLayer(EventoPlForLayerDto dto) throws DaoException;
	public long insertEventoLnOnLayer(EventoLnForLayerDto dto) throws DaoException;
	public void updateEventoLnOnLayer(PfaForLayerDto dto) throws DaoException;
	public void updateEventoPtOnLayer(PfaForLayerDto dto) throws DaoException;
	public void updateEventoPlOnLayer(PfaForLayerDto dto) throws DaoException;
	public EventoForLayerDto findEventoById(String idEvento, String idLayer) throws DaoException;
	public long insertInterventoLnOnLayer(InterventoLnForLayerDto createLnIntervento)throws DaoException;
	public long insertInterventoPlOnLayer(InterventoPlForLayerDto createPlIntervento)throws DaoException;
	public long insertInterventoPtOnLayer(InterventoPtForLayerDto createPtIntervento)throws DaoException;
	public void updateInterventoLnOnLayer(PfaForLayerDto dto) throws DaoException;
	public void updateInterventoPtOnLayer(PfaForLayerDto dto) throws DaoException;
	public void updateInterventoPlOnLayer(PfaForLayerDto dto) throws DaoException;
	public InterventoForLayerDto findInterventoById(String idIntervento, String layerId) throws DaoException;
	public Long insertVincoloOnLayer(VincoloForLayerDto dto) throws DaoException;
	public VincoloForLayerDto findVincoloById(Long id) throws DaoException;
	public void updateVincoloOnLayer(VincoloForLayerDto dto) throws DaoException;
	public Long insertTaglioOnLayer(TaglioForLayerDto dto) throws DaoException;
	public TaglioForLayerDto findTaglioById(Long id) throws DaoException;
	public void updateTaglioOnLayer(TaglioForLayerDto dto) throws DaoException;
	public void deleteTrasformazioneOnLayer(Long featureId) throws DaoException;
	public void deleteVincoloOnLayer(Long featureId) throws DaoException;
	public void deleteTaglioOnLayer(Long featureId) throws DaoException;
	public void deleteEventoLnOnLayer(Long featureId) throws DaoException;
	public void deleteEventoPlOnLayer(Long featureId) throws DaoException;
	public void deleteEventoPtOnLayer(Long featureId) throws DaoException;
	public void deleteInterventoLnOnLayer(Long featureId) throws DaoException;
	public void deleteInterventoPlOnLayer(Long featureId) throws DaoException;
	public void deleteInterventoPtOnLayer(Long featureId) throws DaoException;
	
	

}
