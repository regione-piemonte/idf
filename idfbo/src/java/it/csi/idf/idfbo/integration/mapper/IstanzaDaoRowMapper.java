/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.integration.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import it.csi.idf.idfbo.dto.IstanzaInfoMailByIstanzaDto;
import it.csi.idf.idfbo.integration.IstanzaDAO;

/**
 * RowMapper specifico del DAO IstanzaDao
 * @generated
 */
public class IstanzaDaoRowMapper extends BaseDaoRowMapper implements org.springframework.jdbc.core.RowMapper {

	/**
	 * Dao associato al RowMapper. Serve per i supplier DAO
	 * @generated
	 */
	IstanzaDAO dao;

	/**
	 * costruttore
	 * @param columnsToRead elenco delle colonne da includere nel mapping (per query
	 *        incomplete, esempio distinct, custom select...) nella classe padre
	 */
	public IstanzaDaoRowMapper(String[] columnsToRead, Class dtoClass, IstanzaDAO dao) {
		super(columnsToRead, dtoClass);
		this.dao = dao;
	}

	/**
	 * Method 'mapRow'
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return IstanzaDto
	 * @generated
	 */
	public Object mapRow(ResultSet rs, int row) throws SQLException {
		Object dtoInstance = getNewDto();

		
		if (dtoInstance instanceof IstanzaInfoMailByIstanzaDto) {
			return mapRow_internal((IstanzaInfoMailByIstanzaDto) dtoInstance, rs, row);
		}

		return dtoInstance;
	}

	

	/**
	 * Metodo specifico di mapping relativo al DTO custom IstanzaInfoMailByIstanzaDto.
	 * 
	 * @param rs
	 * @param row
	 * @throws SQLException
	 * @return IstanzaInfoMailByIstanzaDto
	 * @generated
	 */

	public IstanzaInfoMailByIstanzaDto mapRow_internal(IstanzaInfoMailByIstanzaDto objectToFill, ResultSet rs, int row)
			throws SQLException {
		IstanzaInfoMailByIstanzaDto dto = objectToFill;

		if (mapAllColumns || columnsToReadMap.get("ID_ISTANZA_INTERVENTO") != null)
			dto.setItifIdIstanzaIntervento(rs.getBigDecimal("ID_ISTANZA_INTERVENTO"));
		
		if (mapAllColumns || columnsToReadMap.get("NR_ISTANZA_FORESTALE") != null)
			dto.setItifNrIstanzaForestale(rs.getBigDecimal("NR_ISTANZA_FORESTALE"));

		if (mapAllColumns || columnsToReadMap.get("MAIL_RICHIEDENTE") != null)
			dto.setMail_richiedente(rs.getString("mail_richiedente"));

		if (mapAllColumns || columnsToReadMap.get("IS_SOGGETTO_GESTORE") != null)
			dto.setIs_soggetto_gestore(rs.getBigDecimal("is_soggetto_gestore"));

		if (mapAllColumns || columnsToReadMap.get("DENOMINAZIONE_GESTORE") != null)
			dto.setDenominazione_gestore(rs.getString("denominazione_gestore"));

		if (mapAllColumns || columnsToReadMap.get("TELEFONO_GESTORE") != null)
			dto.setTelefono_gestore(rs.getString("telefono_gestore"));

		if (mapAllColumns || columnsToReadMap.get("MAIL_GESTORE") != null)
			dto.setMail_gestore(rs.getString("mail_gestore"));

		if (mapAllColumns || columnsToReadMap.get("PEC_GESTORE") != null)
			dto.setPec_gestore(rs.getString("pec_gestore"));
		
		if (mapAllColumns || columnsToReadMap.get("TIPO_ISTANZA") != null)
			dto.setTipoIstanza(rs.getString("tipo_istanza"));
		
		

		return dto;
	}

}
