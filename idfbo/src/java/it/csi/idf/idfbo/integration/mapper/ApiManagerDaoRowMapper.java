/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.integration.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import it.csi.idf.idfbo.dto.ApiManagerDto;
import it.csi.idf.idfbo.integration.ConfigDAO;
import it.csi.idf.idfbo.util.Constants;

import static it.csi.idf.idfbo.util.builder.ToStringBuilder.objectToString;


/**
 * RowMapper specifico del DAO ApiManagerDao
 * @generated
 */
public class ApiManagerDaoRowMapper extends BaseDaoRowMapper implements org.springframework.jdbc.core.RowMapper {
	
	protected static final Logger logger = Logger.getLogger(Constants.LOGGING.LOGGER_NAME + ".integration");
	
	/**
	 * Dao associato al RowMapper. Serve per i supplier DAO
	 * @generated
	 */
	ConfigDAO dao;

	/**
	 * costruttore
	 * @param columnsToRead elenco delle colonne da includere nel mapping (per query
	 *        incomplete, esempio distinct, custom select...) nella classe padre
	 */
	public ApiManagerDaoRowMapper(String[] columnsToRead, Class dtoClass, ConfigDAO dao) {
		super(columnsToRead, dtoClass);
		this.dao =  dao;
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return ApiManagerDto
	 * @generated
	 */
	public Object mapRow(ResultSet rs, int row) throws SQLException {
		logger.info("Entro in mapper");
		Object dtoInstance = getNewDto();

		if (dtoInstance instanceof ApiManagerDto) {
			logger.info("Entro in mapper map internal");
			
			return mapRow_internal((ApiManagerDto) dtoInstance, rs, row);
		}
		else {
			logger.info("Non Entro in mapper map internal");
			
		}

		return dtoInstance;
	}

	public ApiManagerDto mapRow_internal(ApiManagerDto objectToFill, ResultSet rs, int row) throws SQLException {
		ApiManagerDto dto = objectToFill;
		
		// colonna [ID_CONFIG_PARAM_APIMGR]
		if (mapAllColumns || columnsToReadMap.get("ID_CONFIG_PARAM_APIMGR") != null)
			dto.setIdConfigParamApimgr((Integer) rs.getObject("ID_CONFIG_PARAM_APIMGR"));

		// colonna [CONSUMER_KEY]
		if (mapAllColumns || columnsToReadMap.get("CONSUMER_KEY") != null)
			dto.setConsumerKey(rs.getString("CONSUMER_KEY"));

		// colonna [CONSUMER_SECRET]
		if (mapAllColumns || columnsToReadMap.get("CONSUMER_SECRET") != null)
			dto.setConsumerSecret(rs.getString("CONSUMER_SECRET"));

		// colonna [DATA_INIZIO_VALIDITA]
		if (mapAllColumns || columnsToReadMap.get("DATA_INIZIO_VALIDITA") != null)
			dto.setDataInizioValidita(rs.getDate("DATA_INIZIO_VALIDITA"));

		// colonna [DATA_FINE_VALIDITA]
		if (mapAllColumns || columnsToReadMap.get("DATA_FINE_VALIDITA") != null)
			dto.setDataFineValidita(rs.getDate("DATA_FINE_VALIDITA"));
		
		logger.info("In uscita dal mapper....");
		logger.info(objectToString(dto));
		return dto;
	}

}
