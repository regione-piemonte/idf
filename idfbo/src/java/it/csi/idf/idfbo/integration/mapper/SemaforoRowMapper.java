/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.integration.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import it.csi.idf.idfbo.dto.SemaforoDto;
import it.csi.idf.idfbo.integration.ConfigDAO;

/**
 * RowMapper specifico del DAO TipoMailDao
 * @generated
 */
public class SemaforoRowMapper extends BaseDaoRowMapper implements org.springframework.jdbc.core.RowMapper {

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
	public SemaforoRowMapper(String[] columnsToRead, Class dtoClass, ConfigDAO dao) {
		super(columnsToRead, dtoClass);
		this.dao = dao;
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return SemaforoDto
	 * @generated
	 */
	public Object mapRow(ResultSet rs, int row) throws SQLException {
		Object dtoInstance = getNewDto();

		if (dtoInstance instanceof SemaforoDto) {
			return mapRow_internal((SemaforoDto) dtoInstance, rs, row);
		}

		return dtoInstance;
	}

	public SemaforoDto mapRow_internal(SemaforoDto objectToFill, ResultSet rs, int row) throws SQLException {
		SemaforoDto dto = objectToFill;

		// colonna [ID_SEMAFORO]
		if (mapAllColumns || columnsToReadMap.get("ID_SEMAFORO") != null)
			dto.setIdSemaforo((Integer) rs.getObject("ID_SEMAFORO"));

		// colonna [CODICE]
		if (mapAllColumns || columnsToReadMap.get("CODICE") != null)
			dto.setCodice(rs.getString("CODICE"));

		// colonna [VALORE]
		if (mapAllColumns || columnsToReadMap.get("VALORE") != null)
			dto.setValore(rs.getInt("VALORE"));


		return dto;
	}

}
