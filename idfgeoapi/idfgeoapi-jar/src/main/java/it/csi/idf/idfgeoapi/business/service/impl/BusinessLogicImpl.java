/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfgeoapi.business.service.impl;

import static it.csi.idf.idfgeoapi.util.builder.ToStringBuilder.objectToString;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.ecogis.util.dto.GeoJSONFeature;
import it.csi.idf.idfgeoapi.business.service.BusinessLogic;
import it.csi.idf.idfgeoapi.dto.*;
import it.csi.idf.idfgeoapi.dto.factory.AdsForLayerFactory;
import it.csi.idf.idfgeoapi.dto.factory.GeoJSONFeatureFactory;
import it.csi.idf.idfgeoapi.dto.factory.PfaForLayerFactory;
import it.csi.idf.idfgeoapi.dto.factory.TrasformazioneForLayerFactory;
import it.csi.idf.idfgeoapi.dto.factory.VincoloForLayerFactory;
import it.csi.idf.idfgeoapi.dto.factory.TaglioForLayerFactory;
import it.csi.idf.idfgeoapi.exception.DaoException;
import it.csi.idf.idfgeoapi.exception.IdfGeoException;
import it.csi.idf.idfgeoapi.integration.dao.*;
import it.csi.idf.idfgeoapi.util.Constants;

@Component("businessLogic")
public class BusinessLogicImpl implements BusinessLogic {
	
	protected transient Logger LOG = Logger.getLogger(Constants.COMPONENT_NAME);
	
	@Autowired
	private LayerDao layerDao;
	
	@Autowired GeecoDao geecoDao;

	@Override
	public void deleteFeatureOnLayer(String layerId, Long featureId) throws DaoException {
		LOG.info("[BusinessLogicImpl::insertFeatureOnLayer] BEGIN");
		
		GeecoProfiloDto profilo = this.geecoDao.getGeecoProfiloByIdLayer(layerId);
		
		switch (profilo.getFkProcedura()) {
			case Constants.TIPO_PROCEDURA_INV:
				this.layerDao.deleteAdsOnLayer(featureId);
			case Constants.TIPO_PROCEDURA_ISTAFOR:
				this.deleteIstaforOnLayer(layerId,featureId); 
			case Constants.TIPO_PROCEDURA_PFA:
				this.deletePfaOnLayer(layerId,featureId);
			default:
				break;
		
		}
		
		
		
	}


	@Override
	public GeoJSONFeature insertFeatureOnLayer(String layerId, GeoJSONFeature feature) throws DaoException, IdfGeoException {
		LOG.info("[BusinessLogicImpl::insertFeatureOnLayer] BEGIN");
		
		LOG.info("---- VALORE DI LAYER ID --  *"+layerId+"*");
		
		GeecoProfiloDto profilo = this.geecoDao.getGeecoProfiloByIdLayer(layerId);
		
		switch (profilo.getFkProcedura()) {
			case Constants.TIPO_PROCEDURA_INV:
				return this.insertAdsOnLayer(layerId,feature);
			case Constants.TIPO_PROCEDURA_ISTAFOR:
				return this.insertIstaforOnLayer(layerId, feature);
			case Constants.TIPO_PROCEDURA_PFA:
				return this.insertPfaOnLayer(layerId, feature);
			default:
				break;
		}
		
		return null;
	}
	
	


	private GeoJSONFeature insertPfaOnLayer(String layerId, GeoJSONFeature feature) throws DaoException {
		
		LOG.info("[BusinessLogicImpl::insertPfaOnLayer	] BEGIN");
		try {
			
			
			GeoJSONFeature result = null;
			Long newPk = null; 
			String idEvento = null;
			String idIntervento = null;
			
			switch (layerId) {
			case Constants.LAYER_PFA_EVENTO_LINEE:
				newPk = this.layerDao.insertEventoLnOnLayer(PfaForLayerFactory.createLnEvento(feature));
				idEvento = (String)feature.getProperties().get(Constants.GEECO_PFA_LABEL_ID_EVENTO);
				//result = GeoJSONFeatureFactory.create(layerDao.findInterventoById(idIntervento, layerId), feature);
				result = GeoJSONFeatureFactory.createEvento(newPk, feature);
				break;
			case Constants.LAYER_PFA_EVENTO_POLIGONI:
				newPk = this.layerDao.insertEventoPlOnLayer(PfaForLayerFactory.createPlEvento(feature));
				idEvento = (String)feature.getProperties().get(Constants.GEECO_PFA_LABEL_ID_EVENTO);
				//result = GeoJSONFeatureFactory.create(layerDao.findInterventoById(idIntervento, layerId), feature);
				result = GeoJSONFeatureFactory.createEvento(newPk, feature);
				break;
			case Constants.LAYER_PFA_EVENTO_PUNTI:
				newPk = this.layerDao.insertEventoPtOnLayer(PfaForLayerFactory.createPtEvento(feature));
				idEvento = (String)feature.getProperties().get(Constants.GEECO_PFA_LABEL_ID_EVENTO);
				//result = GeoJSONFeatureFactory.create(layerDao.findInterventoById(idIntervento, layerId), feature);
				result = GeoJSONFeatureFactory.createEvento(newPk, feature);
				break;
			case Constants.LAYER_PFA_INTERVENTO_LINEE:
				newPk = this.layerDao.insertInterventoLnOnLayer(PfaForLayerFactory.createLnIntervento(feature));
				idIntervento = (String)feature.getProperties().get(Constants.GEECO_PFA_LABEL_ID_INTERVENTO);
				//result = GeoJSONFeatureFactory.create(layerDao.findInterventoById(idIntervento, layerId), feature);
				result = GeoJSONFeatureFactory.createIntervento(newPk, feature);
				break;
			case Constants.LAYER_PFA_INTERVENTO_POLIGONI:
				newPk = this.layerDao.insertInterventoPlOnLayer(PfaForLayerFactory.createPlIntervento(feature));
				idIntervento = (String)feature.getProperties().get(Constants.GEECO_PFA_LABEL_ID_INTERVENTO);
				//result = GeoJSONFeatureFactory.create(layerDao.findInterventoById(idIntervento, layerId), feature);
				result = GeoJSONFeatureFactory.createIntervento(newPk, feature);
				break;
			case Constants.LAYER_PFA_INTERVENTO_PUNTI:
				newPk = this.layerDao.insertInterventoPtOnLayer(PfaForLayerFactory.createPtIntervento(feature));
				idIntervento = (String)feature.getProperties().get(Constants.GEECO_PFA_LABEL_ID_INTERVENTO);
				//result = GeoJSONFeatureFactory.create(layerDao.findInterventoById(idIntervento, layerId), feature);
				result = GeoJSONFeatureFactory.createIntervento(newPk, feature);
				break;
				
			default:
				break;
			}
			
			
			return result;
		}
		finally {
			LOG.info("[BusinessLogicImpl::insertPfaOnLayer] END");
		}
	}
	
	private GeoJSONFeature insertIstaforOnLayer(String layerId, GeoJSONFeature feature) throws DaoException, IdfGeoException {
		LOG.info("[BusinessLogicImpl::insertIstaforOnLayer] BEGIN");
		switch (layerId) {
			case Constants.LAYER_TRASFORMAZIONI_CITTADINO:
				return  this.insertTrasformazioneOnLayer(layerId, feature);
			
			case Constants.LAYER_VINCOLO_CITTADINO:
				return this.insertVincoloOnLayer(layerId, feature);
			
			case Constants.LAYER_TAGLI_CITTADINO:
				return this.insertTaglioOnLayer(layerId, feature);
		}
		LOG.info("[BusinessLogicImpl::insertIstaforOnLayer] END");
		return feature;
	}

	private GeoJSONFeature insertTrasformazioneOnLayer(String layerId, GeoJSONFeature feature) throws DaoException, IdfGeoException {
		LOG.info("[BusinessLogicImpl::insertTrasformazioneOnLayer] BEGIN");
		try {
			
			
			TrasformazioneForLayerDto dto = TrasformazioneForLayerFactory.create(layerId, feature);
			Long idTrs = layerDao.insertTrasfomazioneOnLayer(dto);
			// Richiamo la query per farmi restituire la superficie aggiornata
			return GeoJSONFeatureFactory.create(layerDao.findTrasformazioneById(idTrs),feature);
		}
		finally {
			LOG.info("[BusinessLogicImpl::insertTrasformazioneOnLayer] END");
		}
	}
	
	private GeoJSONFeature insertVincoloOnLayer(String layerId, GeoJSONFeature feature) throws DaoException, IdfGeoException {
		LOG.info("[BusinessLogicImpl::insertVincoloOnLayer] BEGIN");
		try {
			
			
			VincoloForLayerDto dto = VincoloForLayerFactory.create(feature);
			Long idVincolo = layerDao.insertVincoloOnLayer(dto);
			
			return GeoJSONFeatureFactory.create(layerDao.findVincoloById(idVincolo),feature);
			
		}
		finally {
			LOG.info("[BusinessLogicImpl::insertVincoloOnLayer] END");
		}
	}
	
	private GeoJSONFeature insertTaglioOnLayer(String layerId, GeoJSONFeature feature) throws DaoException, IdfGeoException {
		LOG.info("[BusinessLogicImpl::insertTagliOnLayer] BEGIN");
		try {
			
			
			TaglioForLayerDto dto = TaglioForLayerFactory.create(feature);
			Long idTaglio = layerDao.insertTaglioOnLayer(dto);
			
			return GeoJSONFeatureFactory.create(layerDao.findTaglioById(idTaglio),feature);
			
		}
		finally {
			LOG.info("[BusinessLogicImpl::insertTagliOnLayer] END");
		}
	}

	private GeoJSONFeature insertAdsOnLayer(String layerId,GeoJSONFeature feature) throws DaoException, IdfGeoException {
		LOG.info("[BusinessLogicImpl::insertAdsOnLayer] BEGIN");
		try {
			
			
			
			GeoJSONFeature result = null;
			String id = (String)feature.getProperties().get(Constants.GEECO_ADS_LABEL_IDENTIFICATIVO);
			
			
			AdsForLayerDto check = this.layerDao.findAdsById(id);
			
			
			
			
			if(check!=null && StringUtils.isNotEmpty(check.getGeometriaString())) {
				
				throw new IdfGeoException("E' possibile inserire una sola geometria per Area di Saggio");
			}
			// TODO Inserire il controllo corretto
//			if(1==2) {
//				throw new IdfGeoException("E' possibile inserire una sola geometria per Area di Saggio");
//			}
			else {
				LOG.info("--------ENTRO IN INSERT ---------");
				AdsForLayerDto dto = AdsForLayerFactory.createForInsert(layerId, feature);
				
				if(Constants.GEECO_ID_DEFAULT_VALUE.equalsIgnoreCase(id)) {
					result = this.layerDao.insertAdsOnLayer(dto); 	
				}
				else {
					LOG.info("--------ENTRO IN UPDATE ---------");
					
					this.layerDao.updateAdsOnLayer(dto);
					
					LOG.info(objectToString(dto));
					
					result = GeoJSONFeatureFactory.create(dto);
				}
			}
			
			LOG.info(objectToString(result));
			
			
			return result;
		}
		finally {
			LOG.info("[BusinessLogicImpl::insertAdsOnLayer] END");
		}
		
	}


	@Override
	public GeoJSONFeature updateFeatureOnLayer(String layerId, GeoJSONFeature feature) throws DaoException {
		LOG.info("[BusinessLogicImpl::updateFeatureOnLayer] BEGIN");
		GeecoProfiloDto profilo = this.geecoDao.getGeecoProfiloByIdLayer(layerId);
		
		switch (profilo.getFkProcedura()) {
			case Constants.TIPO_PROCEDURA_INV:
				this.layerDao.updateAdsOnLayer(AdsForLayerFactory.createForUpdate(layerId,feature));
				break;
			case Constants.TIPO_PROCEDURA_ISTAFOR:
				this.updateIstaforOnLayer(layerId,feature);
				break;
			case Constants.TIPO_PROCEDURA_PFA:
				this.updatePfaOnLayer(layerId, feature);
				break;
			default:
				break;
		}
		LOG.info("[BusinessLogicImpl::updateFeatureOnLayer] END");
		return feature;
	}


	private void updateIstaforOnLayer(String layerId, GeoJSONFeature feature) throws DaoException {
		LOG.info("[BusinessLogicImpl::updateIstaforOnLayer] BEGIN");
		LOG.info("----__------- Valore di layer id? *"+layerId+"*");
		
		LOG.info("---- oggetto feature ----");
		LOG.info(objectToString(feature));
		
		switch (layerId){
		case Constants.LAYER_TRASFORMAZIONI_CITTADINO:
			this.layerDao.updateTrasformazioneOnLayer(TrasformazioneForLayerFactory.createForUpdate(layerId, feature));
			break;
		case Constants.LAYER_VINCOLO_CITTADINO:
			this.layerDao.updateVincoloOnLayer(VincoloForLayerFactory.createForUpdate(feature));
			break;
		case Constants.LAYER_TAGLI_CITTADINO:
			this.layerDao.updateTaglioOnLayer(TaglioForLayerFactory.createForUpdate(feature));
			break;	
		default:
			break;
		}
	}


	private void updatePfaOnLayer(String layerId, GeoJSONFeature feature) throws DaoException {
		LOG.info("[BusinessLogicImpl::updatePfaOnLayer] BEGIN");
		LOG.info("----__------- Valore di layer id? *"+layerId+"*");
		
		
		
		switch (layerId) {
		case Constants.LAYER_PFA_EVENTO_LINEE:
			this.layerDao.updateEventoLnOnLayer(PfaForLayerFactory.create(feature));
			break;
		case Constants.LAYER_PFA_EVENTO_POLIGONI:
			this.layerDao.updateEventoPlOnLayer(PfaForLayerFactory.create(feature));
			break;
		case Constants.LAYER_PFA_EVENTO_PUNTI:
			this.layerDao.updateEventoPtOnLayer(PfaForLayerFactory.create(feature));
			break;
		case Constants.LAYER_PFA_INTERVENTO_LINEE:
			this.layerDao.updateInterventoLnOnLayer(PfaForLayerFactory.create(feature));
			break;
		case Constants.LAYER_PFA_INTERVENTO_POLIGONI:
			this.layerDao.updateInterventoPlOnLayer(PfaForLayerFactory.create(feature));
			break;
		case Constants.LAYER_PFA_INTERVENTO_PUNTI:
			this.layerDao.updateInterventoPtOnLayer(PfaForLayerFactory.create(feature));
			break;
			
		default:
			break;
		}
		LOG.info("[BusinessLogicImpl::updatePfaOnLayer] END");
	}
	
	
	private void deleteIstaforOnLayer(String layerId, Long featureId) throws DaoException {
		LOG.info("[BusinessLogicImpl::deleteIstaforOnLayer] BEGIN");
		LOG.info("----__------- Valore di layer id? *"+layerId+"*");
		
		switch (layerId){
		case Constants.LAYER_TRASFORMAZIONI_CITTADINO:
			this.layerDao.deleteTrasformazioneOnLayer(featureId);
			break;
		case Constants.LAYER_VINCOLO_CITTADINO:
			this.layerDao.deleteVincoloOnLayer(featureId);
			break;
		case Constants.LAYER_TAGLI_CITTADINO:
			this.layerDao.deleteTaglioOnLayer(featureId);
			break;	
		default:
			break;
		}
		LOG.info("[BusinessLogicImpl::deleteIstaforOnLayer] END");
	}
	
	private void deletePfaOnLayer(String layerId, Long featureId) throws DaoException {
		LOG.info("[BusinessLogicImpl::deletePfaOnLayer] BEGIN");
		LOG.info("----__------- Valore di layer id? *"+layerId+"*");
		
		
		switch (layerId) {
		case Constants.LAYER_PFA_EVENTO_LINEE:
			this.layerDao.deleteEventoLnOnLayer(featureId);
			break;
		case Constants.LAYER_PFA_EVENTO_POLIGONI:
			this.layerDao.deleteEventoPlOnLayer(featureId);
			break;
		case Constants.LAYER_PFA_EVENTO_PUNTI:
			this.layerDao.deleteEventoPtOnLayer(featureId);
			break;
		case Constants.LAYER_PFA_INTERVENTO_LINEE:
			this.layerDao.deleteInterventoLnOnLayer(featureId);
			break;
		case Constants.LAYER_PFA_INTERVENTO_POLIGONI:
			this.layerDao.deleteInterventoPlOnLayer(featureId);
			break;
		case Constants.LAYER_PFA_INTERVENTO_PUNTI:
			this.layerDao.deleteInterventoPtOnLayer(featureId);
			break;
			
		default:
			break;
		}
		LOG.info("[BusinessLogicImpl::deletePfaOnLayer] END");
	}

	@Override
	public void testResources() throws DaoException {
		LOG.info("[BusinessLogicImpl::testResources] BEGIN");
		geecoDao.testResources();
		LOG.info("[BusinessLogicImpl::testResources] END");
	}
	
	
	

	

}
