/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.integration.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import it.csi.idf.idfbo.dto.MailConfigDto;
import it.csi.idf.idfbo.integration.ConfigDAO;

/**
 * RowMapper specifico del DAO MailConfigDao
 * @generated
 */
public class MailConfigDaoRowMapper extends BaseDaoRowMapper implements org.springframework.jdbc.core.RowMapper {

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
	public MailConfigDaoRowMapper(String[] columnsToRead, Class dtoClass, ConfigDAO dao) {
		super(columnsToRead, dtoClass);
		this.dao =  dao;
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return MailConfigDto
	 * @generated
	 */
	public Object mapRow(ResultSet rs, int row) throws SQLException {
		Object dtoInstance = getNewDto();

		if (dtoInstance instanceof MailConfigDto) {
			return mapRow_internal((MailConfigDto) dtoInstance, rs, row);
		}

		return dtoInstance;
	}

	public MailConfigDto mapRow_internal(MailConfigDto objectToFill, ResultSet rs, int row) throws SQLException {
		MailConfigDto dto = objectToFill;

		// colonna [ID_MAIL]
		if (mapAllColumns || columnsToReadMap.get("ID_MAIL") != null)
			dto.setIdMail((Integer) rs.getObject("ID_MAIL"));

		// colonna [HOST]
		if (mapAllColumns || columnsToReadMap.get("HOST") != null)
			dto.setHost(rs.getString("HOST"));

		// colonna [PORTA]
		if (mapAllColumns || columnsToReadMap.get("PORTA") != null)
			dto.setPorta(rs.getBigDecimal("PORTA"));

		// colonna [USER_ID]
		if (mapAllColumns || columnsToReadMap.get("USER_ID") != null)
			dto.setUserId(rs.getString("USER_ID"));

		// colonna [PSW]
		if (mapAllColumns || columnsToReadMap.get("PSW") != null)
			dto.setPsw(rs.getString("PSW"));

		// colonna [PROTOCOLLO]
		if (mapAllColumns || columnsToReadMap.get("PROTOCOLLO") != null)
			dto.setProtocollo(rs.getString("PROTOCOLLO"));

		// colonna [MITTENTE]
		if (mapAllColumns || columnsToReadMap.get("MITTENTE") != null)
			dto.setMittente(rs.getString("MITTENTE"));

		// colonna [DES_TIPO_POSTA]
		if (mapAllColumns || columnsToReadMap.get("DES_TIPO_POSTA") != null)
			dto.setDesTipoPosta(rs.getString("DES_TIPO_POSTA"));

		return dto;
	}

}
