/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;


import java.io.IOException;
import java.util.List;

import javax.ws.rs.PathParam;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.node.ObjectNode;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.dto.Comune;
import it.csi.idf.idfapi.dto.ComuneResource;
import it.csi.idf.idfapi.dto.SezioneResource;

public interface ParticellaDAO {


	
	String findAllParticellaByFoglio(String municipality_code, String section_code, String sheet_number) throws JsonParseException, JsonMappingException, IOException;
	
	String findParticellaById(String municipality_code, String section_code, String sheet_number, String parcel_number) throws JsonParseException, JsonMappingException, IOException;
	
	String findParticellaByGML (ObjectNode jsonObject) throws JsonParseException, JsonMappingException, IOException;
	
	String findParticellaByString (String entry) throws JsonParseException, JsonMappingException, IOException;

	String findParticellaByGMLTSF(String gml)  throws JsonParseException, JsonMappingException, IOException;
	
	
}
