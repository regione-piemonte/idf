/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfgeoapi.business.service.impl;

import static it.csi.idf.idfgeoapi.util.builder.ToStringBuilder.objectToString;

import java.io.IOException;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.idf.idfgeoapi.business.service.BusinessLogic;
import it.csi.idf.idfgeoapi.exception.DaoException;
import it.csi.idf.idfgeoapi.exception.IdfGeoException;
import it.csi.idf.idfgeoapi.util.Constants;
import it.csi.idf.idfgeoapi.util.ConvertUtil;
import it.csi.ecogis.geeco_integration_api.interfaces.SaveFeatureApiMonitoringAware;
import it.csi.ecogis.util.dto.GeoJSONFeature;

@Service
public class SaveFeatureApiImpl implements SaveFeatureApiMonitoringAware {

	@Autowired
	public BusinessLogic businessLogic;

	private Logger LOG = Logger.getLogger(Constants.COMPONENT_NAME);
	
	@Override
	public Response insertFeatureForEditingLayer(String apiVersion, String environment, String layerId, String idEditingSession,
			GeoJSONFeature feature, SecurityContext context, HttpHeaders header) {
		LOG.info("SaveFeatureApiImpl::insertFeatureForEditingLayer - IN");
		Response resp = null;
		try {
			
			GeoJSONFeature result = businessLogic.insertFeatureOnLayer(layerId, feature);
			
			
			
			resp = Response.ok(new ObjectMapper().writeValueAsString(result)).build();
		} catch (DaoException e) {
			LOG.error("SaveFeatureApiImpl::insertFeatureForEditingLayer::DaoException",e);
			resp = Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).entity("Attenzione si è verificato un errore interno. Contattare l'assistenza").build();
		}catch (JsonGenerationException e) {
			LOG.error("SaveFeatureApiImpl::insertFeatureForEditingLayer::JsonGenerationException", e);
			resp = Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
					.entity("Attenzione si � verificato un errore interno. Contattare l'assistenza").build();
		} catch (JsonMappingException e) {
			LOG.error("SaveFeatureApiImpl::insertFeatureForEditingLayer::JsonGenerationException", e);
			resp = Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
					.entity("Attenzione si e' verificato un errore interno. Contattare l'assistenza").build();
		} catch (IOException e) {
			LOG.error("SaveFeatureApiImpl::insertFeatureForEditingLayer::JsonGenerationException", e);
			resp = Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
					.entity("Attenzione si e' verificato un errore interno. Contattare l'assistenza").build();
		} catch (IdfGeoException e) {
			LOG.error("SaveFeatureApiImpl::insertFeatureForEditingLayer::IdfGeoException", e);
			resp = Response.status(HttpStatus.SC_PRECONDITION_FAILED).entity(e.getMessage()).build();
		}
		return resp;
	}

	@Override
	public Response updateFeatureForEditingLayer(String apiVersion, String environment, String layerId, String featureId, String idEditingSession,
			GeoJSONFeature feature, SecurityContext context, HttpHeaders header) {
		LOG.info("SaveFeatureApiImpl::updateFeatureForEditingLayer - IN");
		Response resp = null;
		try {
			businessLogic.updateFeatureOnLayer(layerId, feature);
			
			String output = new ObjectMapper().writeValueAsString(feature);
			
			LOG.info("OUTPUT == "+output);
				
			resp = Response.ok(output).build();
		} catch (DaoException e) {
			LOG.error("SaveFeatureApiImpl::insertFeatureForEditingLayer::DaoException",e);
			resp = Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).entity("Attenzione si è verificato un errore interno. Contattare l'assistenza").build();
		} catch (JsonGenerationException e) {
			LOG.error("SaveFeatureApiImpl::updateFeatureForEditingLayer::JsonGenerationException", e);
			resp = Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
					.entity("Attenzione si e' verificato un errore interno. Contattare l'assistenza").build();
		} catch (JsonMappingException e) {
			LOG.error("SaveFeatureApiImpl::updateFeatureForEditingLayer::JsonGenerationException", e);
			resp = Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
					.entity("Attenzione si e' verificato un errore interno. Contattare l'assistenza").build();
		} catch (IOException e) {
			LOG.error("SaveFeatureApiImpl::updateFeatureForEditingLayer::JsonGenerationException", e);
			resp = Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
					.entity("Attenzione si e' verificato un errore interno. Contattare l'assistenza").build();
		}
		return resp;
	}

	@Override
	public Response deleteFeatureForEditingLayer(String apiVersion, String environment, String layerId,
			String featureId, String idEditingSession, SecurityContext securityContext, HttpHeaders httpHeaders) {
		LOG.info("SaveFeatureApiImpl::deleteFeatureForEditingLayer - IN");
		Response resp = null;
		try {
			businessLogic.deleteFeatureOnLayer(layerId, ConvertUtil.convertToLong(featureId));
			resp = Response.ok().build();
			return resp;
		}
		catch (DaoException e) {
			LOG.error("SaveFeatureApiImpl::deleteFeatureForEditingLayer::DaoException",e);
			resp = Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).entity("Attenzione si è verificato un errore interno. Contattare l'assistenza").build();
		}
		return resp;
	}

	@Override
	public Response testResources() {
		LOG.info("SaveFeatureApiImpl::testResources - IN");
		try {
			businessLogic.testResources();
			return Response.ok().build();
		}
		catch (DaoException e) {
			LOG.error("SaveFeatureApiImpl::testResources::DaoException",e);
			return Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).entity("Attenzione si è verificato un errore interno. Contattare l'assistenza").build();
		}
	}


}
