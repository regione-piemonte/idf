/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.service.helper;

import java.util.ArrayList;
import java.util.List;

import it.csi.ecogis.geeco_java_client.build.AttributeSchemaFactory;
import it.csi.ecogis.geeco_java_client.dto.internal.schema.AttributeSchema;
import it.csi.ecogis.geeco_java_client.dto.internal.schema.PointSchema;
import it.csi.ecogis.geeco_java_client.dto.internal.schema.PolygonSchema;
import it.csi.ecogis.geeco_java_client.dto.internal.schema.TextSchema;
import it.csi.idf.idfapi.util.Constants;

public enum GeecoSchema {
	
	INV_CARTOGRAFIA("1", getSchemaInv()),
	INV_MODIFICA("2", getSchemaInv()),
	INV_ELENCO("3", getSchemaInv()),
	ISTAFOR_GESTORE("4", getSchemaIstafor()),
	ISTAFOR_CITTADINO("5", getSchemaIstafor()),
	INV_DETTAGLIO("6", getSchemaInv()),
	ISTAFOR_CITTADINO_DETTAGLIO("7", getSchemaIstafor())
	;
	
	private String idProfilo;
	private List<AttributeSchema> schema;
	
	private GeecoSchema(String idProfilo, List<AttributeSchema> schema) {
		this.idProfilo = idProfilo;
		this.schema = schema;
	}

	private static List<AttributeSchema> getSchemaInv() {
		
		AttributeSchemaFactory asf = new AttributeSchemaFactory();
		// Non va aggiunto perche' altrimenti viene replicato nel JSON
		//asf.addTextAttributeSchema(new TextSchema(Constants.GEECO_ADS_LABEL_IDENTIFICATIVO,Constants.GEECO_ADS_LABEL_IDENTIFICATIVO, true, true, 30));
//		asf.addTextAttributeSchema(new TextSchema(Constants.GEECO_ADS_LABEL_N_ADS,Constants.GEECO_ADS_LABEL_N_ADS, true, false, 30));
		asf.addTextAttributeSchema(new TextSchema(Constants.GEECO_ADS_LABEL_N_ADS,Constants.GEECO_ADS_LABEL_N_ADS,true, false, true));
		//asf.addPolygonAttributeSchema(new PolygonSchema(Constants.GEECO_GEOMETRY,Constants.GEECO_GEOMETRY));
		asf.addPointAttributeSchema(new PointSchema(Constants.GEECO_GEOMETRY, Constants.GEECO_GEOMETRY));
		
		return asf.create();
	}

	private static List<AttributeSchema> getSchemaPfa() {
		
		AttributeSchemaFactory asf = new AttributeSchemaFactory();
		// TODO
		
		return asf.create();
	}

	private static List<AttributeSchema> getSchemaIstafor() {
		
		AttributeSchemaFactory asf = new AttributeSchemaFactory();
		asf.addPolygonAttributeSchema(new PolygonSchema(Constants.GEECO_GEOMETRY,Constants.GEECO_GEOMETRY));
		asf.addTextAttributeSchema(new TextSchema(Constants.GEECO_TRS_LABEL_COD_ISTANZA,Constants.GEECO_TRS_LABEL_COD_ISTANZA,false, true, true));
		asf.addTextAttributeSchema(new TextSchema(Constants.GEECO_TRS_LABEL_SUPERFICIE,Constants.GEECO_TRS_LABEL_SUPERFICIE,true, true, true));
		//asf.addDateAttributeSchema(new DateSchema(Constants.GEECO_TRS_LABEL_DATA_INVIO_ISTANZA,Constants.GEECO_TRS_LABEL_DATA_INVIO_ISTANZA,false, false, 10, true));
				
		return asf.create();
		
	}
	
	public static List<AttributeSchema> getSchema(String idProfilo){
		
		for (GeecoSchema gs : values()) {
			
			if(gs.getIdProfilo().equalsIgnoreCase(idProfilo)) {
				return gs.getSchema();
			}
		}
		
		return new ArrayList<AttributeSchema>();
	}

	public String getIdProfilo() {
		return idProfilo;
	}

	public List<AttributeSchema> getSchema() {
		return schema;
	}
	
	
	
}
