/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import it.csi.idf.idfapi.business.be.impl.dao.ApiManagerDao;
import it.csi.idf.idfapi.business.be.impl.dao.impl.ApiManagerDaoImpl;
import it.csi.idf.idfapi.dto.ApiManagerDto;

/**
 * RowMapper specifico del DAO ApiManagerDao
 * @generated
 */
public class ApiManagerDaoRowMapper extends BaseDaoRowMapper implements org.springframework.jdbc.core.RowMapper {

	/**
	 * Dao associato al RowMapper. Serve per i supplier DAO
	 * @generated
	 */
	ApiManagerDaoImpl dao;

	/**
	 * costruttore
	 * @param columnsToRead elenco delle colonne da includere nel mapping (per query
	 *        incomplete, esempio distinct, custom select...) nella classe padre
	 */
	public ApiManagerDaoRowMapper(String[] columnsToRead, Class dtoClass, ApiManagerDao dao) {
		super(columnsToRead, dtoClass);
		this.dao = (ApiManagerDaoImpl) dao;
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
		Object dtoInstance = getNewDto();

		if (dtoInstance instanceof ApiManagerDto) {
			return mapRow_internal((ApiManagerDto) dtoInstance, rs, row);
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

		return dto;
	}

}
