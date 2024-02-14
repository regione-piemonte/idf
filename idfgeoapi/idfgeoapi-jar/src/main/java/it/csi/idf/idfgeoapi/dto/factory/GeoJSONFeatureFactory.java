/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfgeoapi.dto.factory;

import static it.csi.idf.idfgeoapi.util.builder.ToStringBuilder.objectToString;

import it.csi.ecogis.util.dto.GeoJSONFeature;
import it.csi.idf.idfgeoapi.dto.AdsForLayerDto;
import it.csi.idf.idfgeoapi.dto.EventoForLayerDto;
import it.csi.idf.idfgeoapi.dto.EventoPlForLayerDto;
import it.csi.idf.idfgeoapi.dto.EventoPtForLayerDto;
import it.csi.idf.idfgeoapi.dto.InterventoForLayerDto;
import it.csi.idf.idfgeoapi.dto.TaglioForLayerDto;
import it.csi.idf.idfgeoapi.dto.TrasformazioneForLayerDto;
import it.csi.idf.idfgeoapi.dto.VincoloForLayerDto;
import it.csi.idf.idfgeoapi.util.Constants;
import it.csi.idf.idfgeoapi.util.ConvertUtil;

public class GeoJSONFeatureFactory {

	public static GeoJSONFeature create(long newPk, AdsForLayerDto dto) {
		GeoJSONFeature result = new GeoJSONFeature();
		result.setId(ConvertUtil.convertToString(newPk));
		result.setGeometry(dto.getGeometry());
		result.setType(Constants.GEECO_TYPE_FEATURE);
		result.getProperties().put(Constants.GEECO_ADS_LABEL_IDENTIFICATIVO, ConvertUtil.convertToString(newPk));
		result.getProperties().put(Constants.GEECO_ADS_LABEL_N_ADS, dto.getCodiceAds());
		return result;
	}
	
	public static GeoJSONFeature create(long newPk, EventoForLayerDto dto) {
		GeoJSONFeature result = new GeoJSONFeature();
		result.setId(ConvertUtil.convertToString(newPk));
		result.setGeometry(dto.getGeometry());
		result.setType(Constants.GEECO_TYPE_FEATURE);
		result.getProperties().put(Constants.GEECO_PFA_LABEL_IDENTIFICATIVO, ConvertUtil.convertToString(newPk));
		result.getProperties().put(Constants.GEECO_PFA_LABEL_DENOMINAZIONE, dto.getDenominazionePfa());
		result.getProperties().put(Constants.GEECO_PFA_LABEL_CODICE_EVENTO, dto.getCodiceEvento());
		return result;
	}
	
//	public static GeoJSONFeature create(long newPk, EventoPlForLayerDto dto) {
//		GeoJSONFeature result = new GeoJSONFeature();
//		result.setId(ConvertUtil.convertToString(newPk));
//		result.setGeometry(dto.getGeometry());
//		result.setType(Constants.GEECO_TYPE_FEATURE);
//		result.getProperties().put(Constants.GEECO_PFA_LABEL_IDENTIFICATIVO, ConvertUtil.convertToString(newPk));
//		result.getProperties().put(Constants.GEECO_PFA_LABEL_DENOMINAZIONE, dto.getDenominazionePfa());
//		result.getProperties().put(Constants.GEECO_PFA_LABEL_CODICE_EVENTO, dto.getCodiceEvento());
//		return result;
//	}
	
	public static GeoJSONFeature create(AdsForLayerDto dto) {
		GeoJSONFeature result = new GeoJSONFeature();
		result.setId(ConvertUtil.convertToString(dto.getIdFeature()));
		result.setGeometry(dto.getGeometry());
		result.setType(Constants.GEECO_TYPE_FEATURE);
		result.getProperties().put(Constants.GEECO_ADS_LABEL_IDENTIFICATIVO, ConvertUtil.convertToString(dto.getIdFeature()));
		result.getProperties().put(Constants.GEECO_ADS_LABEL_N_ADS, dto.getCodiceAds());
		return result;
	}

	public static GeoJSONFeature create(TrasformazioneForLayerDto dto, GeoJSONFeature featureInput) {
		
		
		
		GeoJSONFeature result = new GeoJSONFeature();
		result.setId(ConvertUtil.convertToString(dto.getIdgeoPlIntervTrasf()));
		//result.setGeometry(dto.getGeometry());
		// la geometria da TrasformazioniRowMapper (problemi di conversione con GeoJSONGeometryConverter di Geeco)
		// quindi come workaround la geometria viene recuperata dalla feature di input
		result.setGeometry(featureInput.getGeometry());
		result.setType(Constants.GEECO_TYPE_FEATURE);
		result.getProperties().put(Constants.GEECO_TRS_LABEL_IDENTIFICATIVO, ConvertUtil.convertToString(dto.getIdgeoPlIntervTrasf()));
		result.getProperties().put(Constants.GEECO_TRS_LABEL_INTERVENTO, dto.getFkIntervento());
		result.getProperties().put(Constants.GEECO_TRS_LABEL_SUPERFICIE, dto.getSuperficie());
		
		
		
		return result;
	}

	public static GeoJSONFeature create(EventoForLayerDto dto, GeoJSONFeature feature) {
		
		
		
		GeoJSONFeature result = new GeoJSONFeature();
		result.setId(ConvertUtil.convertToString(dto.getIdFeature()));
		result.setGeometry(feature.getGeometry());
		result.setType(Constants.GEECO_TYPE_FEATURE);
		result.getProperties().put(Constants.GEECO_PFA_LABEL_IDENTIFICATIVO, ConvertUtil.convertToString(dto.getIdFeature()));
		result.getProperties().put(Constants.GEECO_PFA_LABEL_DENOMINAZIONE, dto.getDenominazionePfa());
		result.getProperties().put(Constants.GEECO_PFA_LABEL_CODICE_EVENTO, dto.getCodiceEvento());
		result.getProperties().put(Constants.GEECO_PFA_LABEL_ID_EVENTO, ConvertUtil.convertToString(dto.getFkEvento()));
		return result;
	}

	public static GeoJSONFeature create(InterventoForLayerDto dto, GeoJSONFeature feature) {
		
		
		GeoJSONFeature result = new GeoJSONFeature();
		result.setId(ConvertUtil.convertToString(dto.getIdFeature()));
		result.setGeometry(feature.getGeometry());
		result.setType(Constants.GEECO_TYPE_FEATURE);
		result.getProperties().put(Constants.GEECO_PFA_LABEL_IDENTIFICATIVO, ConvertUtil.convertToString(dto.getIdFeature()));
		result.getProperties().put(Constants.GEECO_PFA_LABEL_DENOMINAZIONE, dto.getDenominazionePfa());
		result.getProperties().put(Constants.GEECO_PFA_LABEL_CODICE_INTERVENTO, dto.getCodiceIntervento());
		result.getProperties().put(Constants.GEECO_PFA_LABEL_ID_INTERVENTO, ConvertUtil.convertToString(dto.getFkIntervento()));
		return result;
	}

	public static GeoJSONFeature createIntervento(Long newPk, GeoJSONFeature feature) {
		GeoJSONFeature result = new GeoJSONFeature();
		result.setId(ConvertUtil.convertToString(newPk));
		result.setGeometry(feature.getGeometry());
		result.setType(Constants.GEECO_TYPE_FEATURE);
		result.getProperties().put(Constants.GEECO_PFA_LABEL_IDENTIFICATIVO, ConvertUtil.convertToString(newPk));
		result.getProperties().put(Constants.GEECO_PFA_LABEL_DENOMINAZIONE, (String)feature.getProperties().get(Constants.GEECO_PFA_LABEL_DENOMINAZIONE));
		result.getProperties().put(Constants.GEECO_PFA_LABEL_CODICE_INTERVENTO, (String)feature.getProperties().get(Constants.GEECO_PFA_LABEL_CODICE_INTERVENTO));
		result.getProperties().put(Constants.GEECO_PFA_LABEL_ID_INTERVENTO, (String)feature.getProperties().get(Constants.GEECO_PFA_LABEL_ID_INTERVENTO));
		return result;
	}
	
	public static GeoJSONFeature createEvento(Long newPk, GeoJSONFeature feature) {
		GeoJSONFeature result = new GeoJSONFeature();
		result.setId(ConvertUtil.convertToString(newPk));
		result.setGeometry(feature.getGeometry());
		result.setType(Constants.GEECO_TYPE_FEATURE);
		result.getProperties().put(Constants.GEECO_PFA_LABEL_IDENTIFICATIVO, ConvertUtil.convertToString(newPk));
		result.getProperties().put(Constants.GEECO_PFA_LABEL_DENOMINAZIONE, (String)feature.getProperties().get(Constants.GEECO_PFA_LABEL_DENOMINAZIONE));
		result.getProperties().put(Constants.GEECO_PFA_LABEL_CODICE_EVENTO, (String)feature.getProperties().get(Constants.GEECO_PFA_LABEL_CODICE_EVENTO));
		result.getProperties().put(Constants.GEECO_PFA_LABEL_ID_EVENTO, (String)feature.getProperties().get(Constants.GEECO_PFA_LABEL_ID_EVENTO));
		return result;
	}

	public static GeoJSONFeature create(VincoloForLayerDto dto, GeoJSONFeature feature) {
		
		GeoJSONFeature result = new GeoJSONFeature();
		result.setId(ConvertUtil.convertToString(dto.getIdGeoPlIntervVincidro()));
		//result.setGeometry(dto.getGeometry());
		// la geometria da TrasformazioniRowMapper (problemi di conversione con GeoJSONGeometryConverter di Geeco)
		// quindi come workaround la geometria viene recuperata dalla feature di input
		result.setGeometry(feature.getGeometry());
		result.setType(Constants.GEECO_TYPE_FEATURE);
		result.getProperties().put(Constants.GEECO_VINC_LABEL_IDENTIFICATIVO, ConvertUtil.convertToString(dto.getIdGeoPlIntervVincidro()));
		result.getProperties().put(Constants.GEECO_VINC_LABEL_INTERVENTO, dto.getFkIntervento());
		result.getProperties().put(Constants.GEECO_VINC_LABEL_SUPERFICIE, dto.getSuperficie());
		
		
		return result;
	}
	
	public static GeoJSONFeature create(TaglioForLayerDto dto, GeoJSONFeature featureInput) {
		GeoJSONFeature result = new GeoJSONFeature();
		result.setId(ConvertUtil.convertToString(dto.getIdfGeoPlLottoIntervento()));
		//result.setGeometry(dto.getGeometry());
		// la geometria da TrasformazioniRowMapper (problemi di conversione con GeoJSONGeometryConverter di Geeco)
		// quindi come workaround la geometria viene recuperata dalla feature di input
		result.setGeometry(featureInput.getGeometry());
		result.setType(Constants.GEECO_TYPE_FEATURE);
		result.getProperties().put(Constants.GEECO_TAGLI_LABEL_IDENTIFICATIVO, ConvertUtil.convertToString(dto.getIdfGeoPlLottoIntervento()));
		result.getProperties().put(Constants.GEECO_TAGLI_LABEL_INTERVENTO, dto.getFkIntervento());
		result.getProperties().put(Constants.GEECO_TAGLI_LABEL_SUPERFICIE, dto.getSuperficie());
		return result;
	}

	

}
