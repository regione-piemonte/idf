/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.integration.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import it.csi.idf.idfbo.dto.TipoMailDto;
import it.csi.idf.idfbo.integration.ConfigDAO;

/**
 * RowMapper specifico del DAO TipoMailDao
 * @generated
 */
public class TipoMailDaoRowMapper extends BaseDaoRowMapper implements org.springframework.jdbc.core.RowMapper {

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
	public TipoMailDaoRowMapper(String[] columnsToRead, Class dtoClass, ConfigDAO dao) {
		super(columnsToRead, dtoClass);
		this.dao = dao;
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return TipoMailDto
	 * @generated
	 */
	public Object mapRow(ResultSet rs, int row) throws SQLException {
		Object dtoInstance = getNewDto();

		if (dtoInstance instanceof TipoMailDto) {
			return mapRow_internal((TipoMailDto) dtoInstance, rs, row);
		}

		return dtoInstance;
	}

	public TipoMailDto mapRow_internal(TipoMailDto objectToFill, ResultSet rs, int row) throws SQLException {
		TipoMailDto dto = objectToFill;

		// colonna [ID_TIPO_MAIL]
		if (mapAllColumns || columnsToReadMap.get("ID_TIPO_MAIL") != null)
			dto.setIdTipoMail((Integer) rs.getObject("ID_TIPO_MAIL"));

		// colonna [FK_AMBITO_ISTANZA]
		if (mapAllColumns || columnsToReadMap.get("FK_AMBITO_ISTANZA") != null)
			dto.setFkAmbitoIstanza(rs.getBigDecimal("FK_AMBITO_ISTANZA"));

		// colonna [OGGETTO]
		if (mapAllColumns || columnsToReadMap.get("OGGETTO") != null)
			dto.setOggetto(rs.getString("OGGETTO"));

		// colonna [TESTO]
		if (mapAllColumns || columnsToReadMap.get("TESTO") != null)
			dto.setTesto(rs.getString("TESTO"));

		return dto;
	}

}
