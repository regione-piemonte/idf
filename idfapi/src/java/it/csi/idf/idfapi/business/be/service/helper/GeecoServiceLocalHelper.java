/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.service.helper;

import static it.csi.idf.idfapi.util.builder.ToStringBuilder.objectToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.ecogis.geeco_java_client.build.AttributeSchemaFactory;
import it.csi.ecogis.geeco_java_client.build.ConfigurationFactory;
import it.csi.ecogis.geeco_java_client.dto.Configuration;
import it.csi.ecogis.geeco_java_client.dto.internal.ApiInfo;
import it.csi.ecogis.geeco_java_client.dto.internal.GeoJsonFeature;
import it.csi.ecogis.geeco_java_client.dto.internal.Layer;
import it.csi.ecogis.geeco_java_client.dto.internal.QuitInfo;
import it.csi.ecogis.geeco_java_client.dto.internal.StartupInfo;
import it.csi.ecogis.geeco_java_client.dto.internal.schema.AttributeSchema;
import it.csi.ecogis.geeco_java_client.dto.internal.schema.HiddenSchema;
import it.csi.ecogis.geeco_java_client.dto.internal.schema.LineSchema;
import it.csi.ecogis.geeco_java_client.dto.internal.schema.PointSchema;
import it.csi.ecogis.geeco_java_client.dto.internal.schema.PolygonSchema;
import it.csi.ecogis.geeco_java_client.dto.internal.schema.TextSchema;
import it.csi.ecogis.geeco_java_client.util.ConversionUtils;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.exceptions.ServiceException;
import it.csi.idf.idfapi.business.be.impl.dao.GeecoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.GeecoLocalDAO;
import it.csi.idf.idfapi.dto.AreaDiSaggioDatiGeneraliDTO;
import it.csi.idf.idfapi.dto.GeecoAdsFeature;
import it.csi.idf.idfapi.dto.GeecoLayer;
import it.csi.idf.idfapi.dto.GeecoPfaEventoFeature;
import it.csi.idf.idfapi.dto.GeecoPfaFeature;
import it.csi.idf.idfapi.dto.GeecoPfaInterventoFeature;
import it.csi.idf.idfapi.dto.GeecoProfilo;
import it.csi.idf.idfapi.dto.GeecoTrsFeature;
import it.csi.idf.idfapi.dto.GeecoVincoloFeature;
import it.csi.idf.idfapi.util.Constants;
import it.csi.idf.idfapi.util.ConvertUtil;
import it.csi.idf.idfapi.util.DateUtil;


public class GeecoServiceLocalHelper extends AbstractServiceHelper{
	
	final String GEECO_CSS = "idf"; 
	final String GEECO_AUTH_LEVEL = "liv2";
	final String GEECO_AUTH_COMMUNITY = it.csi.ecogis.geeco_java_client.util.Constants.SHIB_COMMUNITY_SISP;
	final String PROFILO_AUTENTICATO = "A";
	final String PROFILO_NON_AUTENTICATO = "NA";
	
	// TODO da rimuovere dopo aver completato la configurazione via db

	
	@Autowired 
	private GeecoLocalDAO geecoLocalDAO;
	
	
	
	
	/**
	 * @return the geecoLocalDAO
	 */
	public GeecoLocalDAO getGeecoLocalDAO() {
		return geecoLocalDAO;
	}


	/**
	 * @param geecoLocalDAO the geecoLocalDAO to set
	 */
	public void setGeecoLocalDAO(GeecoLocalDAO geecoLocalDAO) {
		this.geecoLocalDAO = geecoLocalDAO;
	}


	protected String urlApi = null;
	protected String env = null;
	
	
	
	public GeecoServiceLocalHelper(String urlApi, String env) {
		this.urlApi = urlApi;
		this.env = env;
	}

	
	public String getGeecoConfiguration(String idProfiloGeeco, String[] id) throws ServiceException {

		LOGGER.info("[GeecoServiceHelper::getGeecoConfiguration] START");
		try {
			LOGGER.info("idProfiloGeeco: " + idProfiloGeeco + " - id: " + id);	
			
			Configuration config = this.buildNewConfiguration(idProfiloGeeco, id).build();
			
			LOGGER.info("config: " + config);


//			
//			
//			
//			
			String json = ConversionUtils.configurationBeanToJson(config);
//			
			
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("[Errore GEECO getGeecoConfiguration] "+e.getMessage());	
		}
		finally {
			LOGGER.info("[GeecoServiceHelper::getGeecoConfiguration] END");
		}
	}
	
	
	private ConfigurationFactory buildNewConfiguration(String idProfiloGeeco, String[] id) throws Exception{
		LOGGER.info("[GeecoServiceHelper::buildNewConfiguration] BEGIN");
		try {
			GeecoProfilo profilo = geecoLocalDAO.findProfiloById(idProfiloGeeco);
			
			
			ConfigurationFactory builder = new ConfigurationFactory();
			// TODO configurazioni generali
			builder.createAppInfo(profilo.getUuid(), env);
			// Dettaglio idProfiloGeeco
			
			
			
			
			List<GeecoLayer> layer = this.getLayers(idProfiloGeeco, id);
			
			if(Constants.FLAG_TRUE.equalsIgnoreCase(profilo.getFlgAutenticLetturaScrittura())) {
				builder.createStartupInfo(false, 
										  this.getEditableLayers(layer), 
										  true,
										  true, 
										  PROFILO_AUTENTICATO.equalsIgnoreCase(profilo.getEnvInfo())?GEECO_AUTH_LEVEL:null, 
										  PROFILO_AUTENTICATO.equalsIgnoreCase(profilo.getEnvInfo())?GEECO_AUTH_COMMUNITY:null, 
										  true, 
										  false, 
										  null);
			}
			else {
				builder.createStartupInfo(true, 
										  this.getEditableLayers(layer), 
										  true,
										  true, 
										  PROFILO_AUTENTICATO.equalsIgnoreCase(profilo.getEnvInfo())?GEECO_AUTH_LEVEL:null, 
										  PROFILO_AUTENTICATO.equalsIgnoreCase(profilo.getEnvInfo())?GEECO_AUTH_COMMUNITY:null, 
										  true, 
										  false, 
										  null);
			}
			
			builder.createQuitInfo(this.createUrlQuit(profilo.getUrlRitorno(), id));
			
			int i = 0;
			
			for (GeecoLayer geecoLayer : layer) {
				LOGGER.info("..................   "+i++);
				LOGGER.info(objectToString(geecoLayer));
				LOGGER.info("___________________ ");
				
				
				builder.createEditingLayerWithGeoJsonFeatures(
						geecoLayer.getIdGeecoLayer(), 
						this.createSchema(geecoLayer),
						this.createGeojsonFeatureList(profilo, geecoLayer,  id),
						this.creatDefaultValues(profilo, geecoLayer, id),
						null, 
						ConvertUtil.getBooleanFromString(geecoLayer.getFlgTipoAccessoScrittura()), 
						ConvertUtil.getBooleanFromString(geecoLayer.getFlgTipoAccessoCanc()),
						null);
				
				
			}
			
			return builder;
		}
		finally {
			LOGGER.info("[GeecoServiceHelper::buildNewConfiguration] END");
		}
		
	}


	private List<GeecoLayer> getLayers(String idProfiloGeeco, String[] id) {
		// Elenco layer
		List<GeecoLayer> layers = geecoLocalDAO.findLayerByIdProfilo(idProfiloGeeco);
		
		switch (ConvertUtil.convertToInteger(idProfiloGeeco)) {
		case Constants.GEECO_PROFILO_ADS_INSERIMENTO_MODIFICA:
			if(id!=null && id.length!=0) {
				AreaDiSaggioDatiGeneraliDTO ads = this.geecoLocalDAO.findADSByIdgeoPtAds(ConvertUtil.convertToLong(id[0]));
				if(ads!=null) {
					//GeecoLayer layerAds = layers.stream().filter(layer -> TipoAdsLayer.getIdLayerFromIdTipoAds(ads.getTipologia()) == ConvertUtil.convertToInteger(layer.getIdGeecoLayer())).findAny().orElse(null);
					int idLayer = TipoAdsLayer.getIdLayerFromIdTipoAds(ads.getTipologia());
					for (GeecoLayer geecoLayer : layers) {
						if(idLayer == ConvertUtil.convertToInteger(geecoLayer.getIdGeecoLayer())) {
							return Arrays.asList(geecoLayer);
						}
					}
				}
			} 
		}
		return layers;
	}

	private String createUrlQuit(String url, String[] id) {
		
		
		if(id!=null&&id.length!=0) {
			
			return StringUtils.replaceOnce(url, Constants.GEECO_TOKEN_ID, id[0]);
		} 
		else {
			
		}
		return url;
	}

	private HashMap<String, Object> creatDefaultValues(GeecoProfilo profilo, GeecoLayer layer, String[] id) throws RecordNotFoundException {
		LOGGER.info("[GeecoServiceHelper::creatDefaultValues] BEGIN");
		HashMap<String, Object> result = null;
		try {
			switch (profilo.getIdGeecoProfilo()) {
			case Constants.GEECO_PROFILO_ADS_CARTOGRAFIA:
			case Constants.GEECO_PROFILO_ADS_INSERIMENTO_MODIFICA:
			case Constants.GEECO_PROFILO_ADS_ELENCO_RICERCA:
			case Constants.GEECO_PROFILO_ADS_DETTAGLIO:
				// ADS
//				GeecoAdsFeature geo = getGeecoDAO().findGeometriaAdsById(id);
//				result = this.createDefaultValuesADS(geo);
				result = this.createDefaultValuesADS(this.buildGeometrieAds(id));
				break;
			case Constants.GEECO_PROFILO_TRASFORMAZIONI_GESTORE_CONSULTAZIONE:
			case Constants.GEECO_PROFILO_TRASFORMAZIONI_CITTADINO_INSERIMENTO_MODIFICA:
			case Constants.GEECO_PROFILO_TRASFORMAZIONI_DETTAGLIO:
				// TRASFORMAZIONI
				result = this.createDefaultValuesTrs(this.buildGeometrieTrs(id));
				break;
			case Constants.GEECO_PROFILO_VINCOLO_GESTORE_CONSULTAZIONE:
			case Constants.GEECO_PROFILO_VINCOLO_CITTADINO_INSERIMENTO_MODIFICA:
			case Constants.GEECO_PROFILO_VINCOLO_DETTAGLIO:
				// VINCOLO
				result = this.createDefaultValuesVincolo(this.buildGeometrieVincolo(id));
				break; 
			case Constants.GEECO_PROFILO_PFA_CITTADINO_CARTOGRAFIA:
			case Constants.GEECO_PROFILO_PFA_CITTADINO_DETTAGLIO:
			case Constants.GEECO_PROFILO_PFA_CITTADINO_ELENCO:
			case Constants.GEECO_PROFILO_PFA_GESTORE_CARTOGRAFIA:
			case Constants.GEECO_PROFILO_PFA_GESTORE_DETTAGLIO:
			case Constants.GEECO_PROFILO_PFA_GESTORE_ELENCO:
				result = this.createDefaultValuesPfa();
				break;
			case Constants.GEECO_PROFILO_PFA_GESTORE_INTERVENTO_DETTAGLIO:
			case Constants.GEECO_PROFILO_PFA_GESTORE_INTERVENTO_INSERIMENTO_MODIFICA:
				result = this.createDefaultValuesPfaIntervento(layer, id);
				break;
			case Constants.GEECO_PROFILO_PFA_GESTORE_EVENTO_DETTAGLIO:
			case Constants.GEECO_PROFILO_PFA_GESTORE_EVENTO_INSERIMENTO_MODIFICA:
				result = this.createDefaultValuesPfaEvento(layer, id);
				break;
			default:
				result = new HashMap<String, Object>();
			}
			
			return result;
		}
		finally {
			LOGGER.info("[GeecoServiceHelper::creatDefaultValues] END");
		}
	}
	
	


	private GeecoAdsFeature buildGeometrieAds(String[] idAds) {
		LOGGER.info("[GeecoServiceHelper::buildGeometrieAds] BEGIN");
		GeecoAdsFeature result = null;
		
		if(idAds!=null) {
			
			if(idAds.length==1) {
				
				List<GeecoAdsFeature> list = geecoLocalDAO.findGeometriaAdsById(idAds);
					return list.get(0);
				}
			else {
				
			}
		}
		else {
			
		}
//		if(idAds!=null && idAds.length==1 && StringUtils.isNotEmpty(idAds[0])) {
//			result = geecoDAO.findGeometriaAdsById(idAds);
//		}
//		else {
			result = new GeecoAdsFeature();
			result.setCodiceAds("-");
			result.setIdgeoPtAds("-");
//		}
			LOGGER.info("[GeecoServiceHelper::buildGeometrieAds] END");
			
		return result;
	}
	
	private GeecoTrsFeature buildGeometrieTrs(String[] idIntervento) {
		LOGGER.info("[GeecoServiceHelper::buildGeometrieTrs] BEGIN");
		GeecoTrsFeature result = new GeecoTrsFeature();
		result.setDataInserimento(DateUtil.getSqlDataCorrente());
		if(idIntervento.length==1) {
			result.setFkIntervento(idIntervento[0]);
		}
		else {
			result.setFkIntervento("-");
		}
		result.setIdgeoPlIntervTrasf("");
		result.setSuperficie("0");
		LOGGER.info("[GeecoServiceHelper::buildGeometrieTrs] END");
		return result;
	}
	
	private GeecoVincoloFeature buildGeometrieVincolo(String[] idIntervento) {
		LOGGER.info("[GeecoServiceHelper::buildGeometrieVincolo] BEGIN");
		GeecoVincoloFeature result = new GeecoVincoloFeature();
		result.setDataInserimento(DateUtil.getSqlDataCorrente());
		if(idIntervento.length==1) {
			result.setFkIntervento(idIntervento[0]);
		}
		else {
			result.setFkIntervento("-");
		}
		result.setIdgeoPlIntervVincIdro("");
		result.setSuperficie("0");
		LOGGER.info("[GeecoServiceHelper::buildGeometrieVincolo] END");
		return result;
	}
	
	private GeecoPfaFeature buildGeometriePfa(String[] id) {
		LOGGER.info("[GeecoServiceHelper::buildGeometrieTrs] BEGIN");
		GeecoPfaFeature result = new GeecoPfaFeature();
//		result.setDataInserimento(DateUtil.getSqlDataCorrente());
//		if(id.length==1) {
//			result.setFkIntervento(idIntervento[0]);
//		}
//		else {
//			result.setFkIntervento("-");
//		}
//		result.setIdgeoPlIntervTrasf("");
//		result.setSuperficie("0");
		LOGGER.info("[GeecoServiceHelper::buildGeometrieTrs] END");
		return result;
	}

	private HashMap<String, Object> createDefaultValuesADS(GeecoAdsFeature geo) {
		LOGGER.info("[GeecoServiceHelper::createDefaultValuesADS] BEGIN");
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put(Constants.GEECO_ADS_LABEL_IDENTIFICATIVO, geo.getIdgeoPtAds());
		result.put(Constants.GEECO_ADS_LABEL_N_ADS, geo.getCodiceAds());
		LOGGER.info("[GeecoServiceHelper::createDefaultValuesADS] END");
		return result;
	}
	
	private HashMap<String, Object> createDefaultValuesTrs(GeecoTrsFeature geo) {
		LOGGER.info("[GeecoServiceHelper::createDefaultValuesTrs] BEGIN");
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put(Constants.GEECO_TRS_LABEL_IDENTIFICATIVO, geo.getIdgeoPlIntervTrasf());
		result.put(Constants.GEECO_TRS_LABEL_COD_ISTANZA, geo.getFkIntervento());
		//result.put(Constants.GEECO_TRS_LABEL_DATA_INVIO_ISTANZA, geo.getDataInserimento());
		result.put(Constants.GEECO_TRS_LABEL_SUPERFICIE, geo.getSuperficie());
		LOGGER.info("[GeecoServiceHelper::createDefaultValuesTrs] END");
		return result;
	}
	
	private HashMap<String, Object> createDefaultValuesVincolo(GeecoVincoloFeature geo){
		LOGGER.info("[GeecoServiceHelper::createDefaultValuesVincolo] BEGIN");
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		result.put(Constants.GEECO_TRS_LABEL_IDENTIFICATIVO, geo.getIdgeoPlIntervVincIdro());
		result.put(Constants.GEECO_TRS_LABEL_COD_ISTANZA, geo.getFkIntervento());
		//result.put(Constants.GEECO_TRS_LABEL_DATA_INVIO_ISTANZA, geo.getDataInserimento());
		result.put(Constants.GEECO_TRS_LABEL_SUPERFICIE, geo.getSuperficie());
		
		LOGGER.info("[GeecoServiceHelper::createDefaultValuesVincolo] END");
		return result;
	}
	
	private HashMap<String, Object> createDefaultValuesPfaEvento(GeecoLayer layer, String[] id) {
		LOGGER.info("[GeecoServiceHelper::createDefaultValuesPfaEvento] BEGIN");
		HashMap<String, Object> result = new HashMap<String, Object>();
		if(id!=null && id.length>0) {
			result.put(Constants.GEECO_PFA_LABEL_ID_EVENTO, id[0]);
			result.put(Constants.GEECO_PFA_LABEL_DESCRIZIONE, "");
			
			List<GeecoPfaEventoFeature> list =  this.geecoLocalDAO.findGeometriePfaEventoById(id, layer.getFlgTipoLayer());
			if(list!=null && !list.isEmpty()) {
				GeecoPfaEventoFeature evento = list.get(0);
				result.put(Constants.GEECO_PFA_LABEL_CODICE_EVENTO, evento.getCodiceEvento());
				result.put(Constants.GEECO_PFA_LABEL_DENOMINAZIONE, evento.getDenominazionePfa());
				result.put(Constants.GEECO_PFA_LABEL_ID_EVENTO, evento.getIdEvento());
			}
			
		}
		else {
			result.put(Constants.GEECO_PFA_LABEL_CODICE_EVENTO, "-");
			result.put(Constants.GEECO_PFA_LABEL_DENOMINAZIONE, "-");
			result.put(Constants.GEECO_PFA_LABEL_ID_EVENTO, 0);
		}
		LOGGER.info("[GeecoServiceHelper::createDefaultValuesPfaEvento] END");
		return result;
	}
	
	private HashMap<String, Object> createDefaultValuesPfaIntervento(GeecoLayer layer, String[] id) {
		LOGGER.info("[GeecoServiceHelper::createDefaultValuesPfaIntervento] BEGIN");
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		switch(layer.getIdGeecoLayer()) {
		case Constants.GEECO_LAYER_PFA_AREALI:
			result.put(Constants.GEECO_PFA_LABEL_DENOMINAZIONE, "");
			result.put(Constants.GEECO_PFA_LABEL_DESCRIZIONE, "");
			break;
		case Constants.GEECO_LAYER_PFA_INTERVENTO_LINEE:
		case Constants.GEECO_LAYER_PFA_INTERVENTO_POLIGONI:
		case Constants.GEECO_LAYER_PFA_INTERVENTO_PUNTI:
			if (id != null && id.length > 0) {
				result.put(Constants.GEECO_PFA_LABEL_ID_INTERVENTO, id[0]);
				result.put(Constants.GEECO_PFA_LABEL_DESCRIZIONE, "");
				
	
				List<GeecoPfaInterventoFeature> list = this.geecoLocalDAO.findGeometriePfaInterventoById(id, layer.getFlgTipoLayer());
				if (list != null && !list.isEmpty()) {
					GeecoPfaInterventoFeature intervento = list.get(0);
					result.put(Constants.GEECO_PFA_LABEL_CODICE_INTERVENTO, intervento.getCodiceIntervento());
					result.put(Constants.GEECO_PFA_LABEL_DENOMINAZIONE, intervento.getDenominazionePfa());
					result.put(Constants.GEECO_PFA_LABEL_ID_INTERVENTO, intervento.getIdIntervento());
				}
	
			} else {
				result.put(Constants.GEECO_PFA_LABEL_CODICE_INTERVENTO, "-");
				result.put(Constants.GEECO_PFA_LABEL_DENOMINAZIONE, "-");
				result.put(Constants.GEECO_PFA_LABEL_ID_INTERVENTO, 0);
			}
			break;
		}
		
		
		LOGGER.info("[GeecoServiceHelper::createDefaultValuesPfaIntervento] END");
		return result;
	}

	private List<GeoJsonFeature> createGeojsonFeatureList(GeecoProfilo profilo, GeecoLayer layer, String[] id) throws RecordNotFoundException {
		LOGGER.info("[GeecoServiceHelper::createGeojsonFeatureList] BEGIN");
		try {
			List<GeoJsonFeature> result = null;
			if(id!=null && id.length>0&&StringUtils.isNotEmpty(id[0])) {
				
						
				List<GeecoAdsFeature> list;
				
				switch (profilo.getIdGeecoProfilo()) {
				case Constants.GEECO_PROFILO_ADS_CARTOGRAFIA:
				case Constants.GEECO_PROFILO_ADS_INSERIMENTO_MODIFICA:
				case Constants.GEECO_PROFILO_ADS_ELENCO_RICERCA:
				case Constants.GEECO_PROFILO_ADS_DETTAGLIO:
					list = geecoLocalDAO.findGeometriaAdsById(id);
					for (GeecoAdsFeature dto : list) {
						if(StringUtils.isNotEmpty(dto.getGeometria())) {
							result = new ArrayList<GeoJsonFeature>();
							result.add(dto.getFeature());
						}
					}
					break;
				case Constants.GEECO_PROFILO_TRASFORMAZIONI_GESTORE_CONSULTAZIONE:
				case Constants.GEECO_PROFILO_TRASFORMAZIONI_CITTADINO_INSERIMENTO_MODIFICA:
				case Constants.GEECO_PROFILO_TRASFORMAZIONI_DETTAGLIO:
					// Trasformazioni del bosco
					result = this.createFeatureTrasformazioni(id);
					break;
					
				case Constants.GEECO_PROFILO_VINCOLO_GESTORE_CONSULTAZIONE:
				case Constants.GEECO_PROFILO_VINCOLO_CITTADINO_INSERIMENTO_MODIFICA:
				case Constants.GEECO_PROFILO_VINCOLO_DETTAGLIO:
					result = this.createFeatureVincolo(id);
					break;
				case Constants.GEECO_PROFILO_PFA_CITTADINO_CARTOGRAFIA:
				case Constants.GEECO_PROFILO_PFA_CITTADINO_DETTAGLIO:
				case Constants.GEECO_PROFILO_PFA_CITTADINO_ELENCO:
				case Constants.GEECO_PROFILO_PFA_GESTORE_CARTOGRAFIA:
				case Constants.GEECO_PROFILO_PFA_GESTORE_DETTAGLIO:
				case Constants.GEECO_PROFILO_PFA_GESTORE_ELENCO:
					result = this.createFeaturePfa(id);
					break;
				case Constants.GEECO_PROFILO_PFA_GESTORE_INTERVENTO_DETTAGLIO:
				case Constants.GEECO_PROFILO_PFA_GESTORE_INTERVENTO_INSERIMENTO_MODIFICA:
					result = this.createFeaturePfaIntervento(id, layer);
					break;
				case Constants.GEECO_PROFILO_PFA_GESTORE_EVENTO_DETTAGLIO:
				case Constants.GEECO_PROFILO_PFA_GESTORE_EVENTO_INSERIMENTO_MODIFICA:
					result = this.createFeaturePfaEvento(id, layer);
					break;
				default:
					break;
				}
			}
			return result;
		}
		finally {
			LOGGER.info("[GeecoServiceHelper::createGeojsonFeatureList] END");
		}
	}

	private List<GeoJsonFeature> createFeatureTrasformazioni(String[] idIntervento) {
		LOGGER.info("[GeecoServiceHelper::createFeatureTrasformazioni] BEGIN");
		List<GeoJsonFeature> result = new ArrayList<GeoJsonFeature>();
		List<GeecoTrsFeature> list = geecoLocalDAO.findGeometrieTrsById(idIntervento);
		if(list!=null && !list.isEmpty()) {
			for (GeecoTrsFeature geecoTrsFeature : list) {
				result.add(geecoTrsFeature.getFeature());
			}
		}
		LOGGER.info("[GeecoServiceHelper::createFeatureTrasformazioni] END");
		return result;
	}
	
	private List<GeoJsonFeature> createFeatureVincolo(String[] idIntervento) {
		LOGGER.info("[GeecoServiceHelper::createFeatureVincolo] BEGIN");
		List<GeoJsonFeature> result = new ArrayList<GeoJsonFeature>();
		List<GeecoVincoloFeature> list = geecoLocalDAO.findGeometrieVincoloById(idIntervento);
		if(list!=null && !list.isEmpty()) {
			for (GeecoVincoloFeature geecoVincoloFeature : list) {
				result.add(geecoVincoloFeature.getFeature());
			}
		}
		LOGGER.info("[GeecoServiceHelper::createFeatureVincolo] END");
		return result;
	}
	
//	private List<GeoJsonFeature> createFeaturePfaEvento(String[] idEvento, String tipo){
//		LOGGER.info("[GeecoServiceHelper::createFeaturePfaEvento] BEGIN");
//		List<GeoJsonFeature> result = new ArrayList<GeoJsonFeature>();
//		List<GeecoPfaEventoFeature> list = geecoLocalDAO.findGeometriePfaEventoById(idEvento, tipo);
//		if(list!=null && !list.isEmpty()) {
//			for (GeecoPfaEventoFeature geecoFeature : list) {
//				if(StringUtils.isNotEmpty(geecoFeature.getGeometriaEvento())) {
//					result.add(geecoFeature.getFeature());
//				}
//			}
//		}
//		LOGGER.info("[GeecoServiceHelper::createFeaturePfaEvento] END");
//		return result;
//	}
	
	private List<GeoJsonFeature> createFeaturePfaEvento(String[] idEvento, GeecoLayer layer) {
		LOGGER.info("[GeecoServiceHelper::createFeaturePfaEvento] BEGIN");
		List<GeoJsonFeature> result = new ArrayList<GeoJsonFeature>();
		if(Constants.GEECO_LAYER_PFA_AREALI.equals(layer.getIdGeecoLayer())) {
			List<GeecoPfaFeature> list = geecoLocalDAO.findGeometriePfaByIdEvento(idEvento);
			if (list != null && !list.isEmpty()&& !this.isGeometriaEventoPresente(idEvento)) {
				for (GeecoPfaFeature dto : list) {
					if(StringUtils.isNotEmpty(dto.getGeometriaPfa())) {
						//result = new ArrayList<GeoJsonFeature>();
						result.add(dto.getFeature());
					}
				}
			}
		}
		else {
			List<GeecoPfaEventoFeature> list = geecoLocalDAO.findGeometriePfaEventoById(idEvento, layer.getFlgTipoLayer());
			if (list != null && !list.isEmpty()) {
				for (GeecoPfaEventoFeature dto : list) {
	
					// result.add(dto.getFeature());
					if (StringUtils.isNotEmpty(dto.getGeometriaEvento())) {
						//result = new ArrayList<GeoJsonFeature>();
						result.add(dto.getFeature());
					}
				}
			}
		}
		LOGGER.info("[GeecoServiceHelper::createFeaturePfaEvento] END");
		return result;
	}
	
//	private List<GeoJsonFeature> createFeaturePfaIntervento(String[] idIntervento, String tipo){
//		LOGGER.info("[GeecoServiceHelper::createFeaturePfaIntervento] BEGIN");
//		List<GeoJsonFeature> result = new ArrayList<GeoJsonFeature>();
//		List<GeecoPfaInterventoFeature> list = geecoLocalDAO.findGeometriePfaInterventoById(idIntervento, tipo);
//		if(list!=null && !list.isEmpty()) {
//			for (GeecoPfaInterventoFeature geecoFeature : list) {
//				if(StringUtils.isNotEmpty(geecoFeature.getGeometriaIntervento())) {
//					result.add(geecoFeature.getFeature());
//				}
//			}
//		}
//		LOGGER.info("[GeecoServiceHelper::createFeaturePfaIntervento] END");
//		return result;
//	}
	
	private List<GeoJsonFeature> createFeaturePfaIntervento(String[] idIntervento, GeecoLayer layer) {
		LOGGER.info("[GeecoServiceHelper::createFeaturePfaIntervento] BEGIN");
		List<GeoJsonFeature> result = new ArrayList<GeoJsonFeature>();
		if(Constants.GEECO_LAYER_PFA_AREALI.equals(layer.getIdGeecoLayer())) {
			List<GeecoPfaFeature> list = geecoLocalDAO.findGeometriePfaByIdIntervento(idIntervento);
			if (list != null && !list.isEmpty()&& !this.isGeometriaInterventoPresente(idIntervento)) {
				for (GeecoPfaFeature dto : list) {
					if(StringUtils.isNotEmpty(dto.getGeometriaPfa())) {
						//result = new ArrayList<GeoJsonFeature>();
						result.add(dto.getFeature());
					}
				}
			}
		}
		else {
			List<GeecoPfaInterventoFeature> list = geecoLocalDAO.findGeometriePfaInterventoById(idIntervento, layer.getFlgTipoLayer());
			if (list != null && !list.isEmpty()) {
				for (GeecoPfaInterventoFeature dto : list) {
	
					// result.add(dto.getFeature());
					
					// TODO CONTROLLARE
					
					if (StringUtils.isNotEmpty(dto.getGeometriaIntervento())) {
						
						result.add(dto.getFeature());
					}
				}
			}
		}
		LOGGER.info("[GeecoServiceHelper::createFeaturePfaIntervento] END");
		return result;
	}

	private boolean isGeometriaInterventoPresente(String[] idIntervento) {
		LOGGER.info("[GeecoServiceHelper::createSchema] BEGIN");
		int count = this.geecoLocalDAO.countGeometrieIntervento(idIntervento);
		return count>0;
	}
	
	private boolean isGeometriaEventoPresente(String[] idEvento) {
		LOGGER.info("[GeecoServiceHelper::createSchema] BEGIN");
		int count = this.geecoLocalDAO.countGeometrieEvento(idEvento);
		return count>0;
	}


	private List<AttributeSchema> createSchema(GeecoLayer geecoLayer) {
		LOGGER.info("[GeecoServiceHelper::createSchema] BEGIN");
		List<AttributeSchema> result = new ArrayList<AttributeSchema>();
		try {
			//result = GeecoSchema.getSchema(idProfiloGeeco);
			switch (ConvertUtil.convertToInteger(geecoLayer.getIdGeecoProfilo())) {
			case Constants.GEECO_PROFILO_ADS_CARTOGRAFIA:
			case Constants.GEECO_PROFILO_ADS_INSERIMENTO_MODIFICA:
			case Constants.GEECO_PROFILO_ADS_ELENCO_RICERCA:
			case Constants.GEECO_PROFILO_ADS_DETTAGLIO:
				return this.getSchemaInv();
			case Constants.GEECO_PROFILO_VINCOLO_GESTORE_CONSULTAZIONE:
			case Constants.GEECO_PROFILO_VINCOLO_CITTADINO_INSERIMENTO_MODIFICA:
			case Constants.GEECO_PROFILO_VINCOLO_DETTAGLIO:
			case Constants.GEECO_PROFILO_TRASFORMAZIONI_GESTORE_CONSULTAZIONE:
			case Constants.GEECO_PROFILO_TRASFORMAZIONI_CITTADINO_INSERIMENTO_MODIFICA:
			case Constants.GEECO_PROFILO_TRASFORMAZIONI_DETTAGLIO:
				return this.getSchemaIstafor();
			case Constants.GEECO_PROFILO_PFA_CITTADINO_CARTOGRAFIA:
			case Constants.GEECO_PROFILO_PFA_CITTADINO_DETTAGLIO:
			case Constants.GEECO_PROFILO_PFA_CITTADINO_ELENCO:
			case Constants.GEECO_PROFILO_PFA_GESTORE_CARTOGRAFIA:
			case Constants.GEECO_PROFILO_PFA_GESTORE_DETTAGLIO:
			case Constants.GEECO_PROFILO_PFA_GESTORE_ELENCO:
			case Constants.GEECO_PROFILO_PFA_GESTORE_EVENTO_DETTAGLIO:
			case Constants.GEECO_PROFILO_PFA_GESTORE_EVENTO_INSERIMENTO_MODIFICA:
			case Constants.GEECO_PROFILO_PFA_GESTORE_INTERVENTO_DETTAGLIO:
			case Constants.GEECO_PROFILO_PFA_GESTORE_INTERVENTO_INSERIMENTO_MODIFICA:
				return this.getSchemaPfa(geecoLayer);
			default:
				break;
			}
			
			
		}
		finally {
			LOGGER.info("[GeecoServiceHelper::createSchema] END");
		}
		return result;
	}
	
	private List<AttributeSchema> getSchemaInv() {
		LOGGER.info("[GeecoServiceHelper::getSchemaInv] BEGIN");
		AttributeSchemaFactory asf = new AttributeSchemaFactory();
		// Non va aggiunto perche' altrimenti viene replicato nel JSON
		//asf.addTextAttributeSchema(new TextSchema(Constants.GEECO_ADS_LABEL_IDENTIFICATIVO,Constants.GEECO_ADS_LABEL_IDENTIFICATIVO, true, true, 30));
//		asf.addTextAttributeSchema(new TextSchema(Constants.GEECO_ADS_LABEL_N_ADS,Constants.GEECO_ADS_LABEL_N_ADS, true, false, 30));
		asf.addTextAttributeSchema(new TextSchema(Constants.GEECO_ADS_LABEL_N_ADS,Constants.GEECO_ADS_LABEL_N_ADS,true, true,20));
		//asf.addPolygonAttributeSchema(new PolygonSchema(Constants.GEECO_GEOMETRY,Constants.GEECO_GEOMETRY));
		asf.addPointAttributeSchema(new PointSchema(Constants.GEECO_GEOMETRY, Constants.GEECO_GEOMETRY));
		LOGGER.info("[GeecoServiceHelper::getSchemaInv] END");
		return asf.create();
	}

	private List<AttributeSchema> getSchemaPfa(GeecoLayer layer) {
		LOGGER.info("[GeecoServiceHelper::getSchemaPfa] BEGIN");
		AttributeSchemaFactory asf = new AttributeSchemaFactory();
				
		// In base alla tipologia di layer indicata sul db aggiungo linea, punto o poligono nello schema
		switch(layer.getFlgTipoLayer()) {
		case Constants.GEECO_LINEA:
			asf.addLineAttributeSchema(new LineSchema(Constants.GEECO_GEOMETRY, Constants.GEECO_GEOMETRY));
			break;
		case Constants.GEECO_POLIGONO:
			asf.addPolygonAttributeSchema(new PolygonSchema(Constants.GEECO_GEOMETRY,Constants.GEECO_GEOMETRY));
			break;
		case Constants.GEECO_PUNTO:
			asf.addPointAttributeSchema(new PointSchema(Constants.GEECO_GEOMETRY, Constants.GEECO_GEOMETRY));
			break;
		}
		
		asf.addTextAttributeSchema(new TextSchema(Constants.GEECO_PFA_LABEL_DENOMINAZIONE,Constants.GEECO_PFA_LABEL_DENOMINAZIONE,false, true, 150));
		
		switch (layer.getIdGeecoLayer()) {
		case Constants.GEECO_LAYER_PFA_EVENTO_LINEE:
		case Constants.GEECO_LAYER_PFA_EVENTO_POLIGONI:
		case Constants.GEECO_LAYER_PFA_EVENTO_PUNTI:
			asf.addTextAttributeSchema(new TextSchema(Constants.GEECO_PFA_LABEL_CODICE_EVENTO,Constants.GEECO_PFA_LABEL_CODICE_EVENTO,false, true));
			asf.addHiddenAttributeSchema(new HiddenSchema(Constants.GEECO_PFA_LABEL_ID_EVENTO));
			break;
		case Constants.GEECO_LAYER_PFA_INTERVENTO_LINEE:
		case Constants.GEECO_LAYER_PFA_INTERVENTO_PUNTI:
		case Constants.GEECO_LAYER_PFA_INTERVENTO_POLIGONI:
			asf.addTextAttributeSchema(new TextSchema(Constants.GEECO_PFA_LABEL_CODICE_INTERVENTO,Constants.GEECO_PFA_LABEL_CODICE_INTERVENTO,false, true));
			asf.addHiddenAttributeSchema(new HiddenSchema(Constants.GEECO_PFA_LABEL_ID_INTERVENTO));
			break;
		default:
			break;
		}
		
		asf.addTextAttributeSchema(new TextSchema(Constants.GEECO_PFA_LABEL_DESCRIZIONE,Constants.GEECO_PFA_LABEL_DESCRIZIONE,false, false, 100));
		
		LOGGER.info("[GeecoServiceHelper::getSchemaPfa] END");
		List<AttributeSchema> result = asf.create();
		
		return result;
	}

	private List<AttributeSchema> getSchemaIstafor() {
		LOGGER.info("[GeecoServiceHelper::getSchemaIstafor] BEGIN");
		AttributeSchemaFactory asf = new AttributeSchemaFactory();
		asf.addPolygonAttributeSchema(new PolygonSchema(Constants.GEECO_GEOMETRY,Constants.GEECO_GEOMETRY));
		asf.addTextAttributeSchema(new TextSchema(Constants.GEECO_TRS_LABEL_COD_ISTANZA,Constants.GEECO_TRS_LABEL_COD_ISTANZA,false, true, true));
		asf.addTextAttributeSchema(new TextSchema(Constants.GEECO_TRS_LABEL_SUPERFICIE,Constants.GEECO_TRS_LABEL_SUPERFICIE,true, true, true));
		//asf.addDateAttributeSchema(new DateSchema(Constants.GEECO_TRS_LABEL_DATA_INVIO_ISTANZA,Constants.GEECO_TRS_LABEL_DATA_INVIO_ISTANZA,false, false, 10, true));
		LOGGER.info("[GeecoServiceHelper::getSchemaIstafor] END");		
		return asf.create();
		
	}

	private int[] getEditableLayers(List<GeecoLayer> layer) {
		LOGGER.info("[GeecoServiceHelper::getEditableLayers] BEGIN");
		try {
			int[] result = null;
			if(layer!=null && !layer.isEmpty()) {
				result = new int[layer.size()];
				int i = 0;
				for (GeecoLayer dto : layer) {
					result[i] = ConvertUtil.convertToInteger(dto.getIdGeecoLayer());
					i++;
				}
			}
			return result;
		}
		finally {
			LOGGER.info("[GeecoServiceHelper::getEditableLayers] END");
		}	
	}
	
	public String getUrlApi() {
		return urlApi;
	}
	
	
	// TEST
	
	
	public void test() throws Exception {
		ConfigurationFactory builder = new ConfigurationFactory();
		
		
		Configuration conf = builder.build();
		conf.setApiInfo(this.getApiInfo());
		conf.setLayers(this.getLayers());
		conf.setQuitInfo(this.getQuitInfo());
		conf.setStartupInfo(this.getStartupInfo());
	}

	private ApiInfo getApiInfo() {
		ApiInfo info = new ApiInfo();
		info.setEnvironment(null);
		info.setUuid(null);
		return info;
	}
	
	private StartupInfo getStartupInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	private QuitInfo getQuitInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	private ArrayList<Layer> getLayers() {
		// TODO Auto-generated method stub
		return null;
	}

	private List<GeoJsonFeature> createFeaturePfa(String[] idPfa) {
		LOGGER.info("[GeecoServiceHelper::createFeaturePfa] BEGIN");
		List<GeoJsonFeature> result = new ArrayList<GeoJsonFeature>();
		List<GeecoPfaFeature> list = geecoLocalDAO.findGeometriePfaById(idPfa);
		if (list != null && !list.isEmpty()) {
			for (GeecoPfaFeature dto : list) {
				if (StringUtils.isNotEmpty(dto.getGeometriaPfa())) {
					result.add(dto.getFeature());
				}
			}
		}
		LOGGER.info("[GeecoServiceHelper::createFeaturePfa] END");
		return result;
	}
	
	private HashMap<String, Object> createDefaultValuesPfa() {
		LOGGER.info("[GeecoServiceHelper::createDefaultValuesPfa] BEGIN");
		HashMap<String, Object> result = new HashMap<String, Object>();
			
		result.put(Constants.GEECO_PFA_LABEL_DENOMINAZIONE, "-");
		result.put(Constants.GEECO_PFA_LABEL_DESCRIZIONE, "-");	
		
		LOGGER.info("[GeecoServiceHelper::createDefaultValuesPfa] END");
		return result;
	}


	public String testQuery(String first, String input) {
		LOGGER.info("[GeecoServiceHelper::testQuery] BEGIN");
		
		List<GeecoProfilo> list = geecoLocalDAO.testQuery(first, input);
		
		LOGGER.info("[GeecoServiceHelper::testQuery] END");
		return "TEST OK";
	}

	
}
