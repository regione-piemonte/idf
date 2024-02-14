/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class DBUtil extends SpringSupportedResource {

	private static final String JNDI_DATA_SOURCE_NAME = "java:/idfDS";

	public static JdbcTemplate jdbcTemplate;
	public static NamedParameterJdbcTemplate namedJdbcTemplate;

	static {
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			jdbcTemplate = new JdbcTemplate((DataSource) ctx.lookup(JNDI_DATA_SOURCE_NAME));
			namedJdbcTemplate = new NamedParameterJdbcTemplate((DataSource) ctx.lookup(JNDI_DATA_SOURCE_NAME));
		} catch (NamingException e) {
			throw new IllegalStateException(e.getMessage());
		}
	}

	public static String createQueryListOfColumnsForInsert(String[] columnNames) {

		StringBuilder sql = new StringBuilder();
		String separator = "";
		for (int i = 0; i < columnNames.length; i++) {
			sql.append(separator.concat(columnNames[i]));
			if (i == 0) {
				separator = ",";
			}
		}

		return sql.toString();
	}

	public static String createQueryListOfParamsForInsert(String[] columnNames) {

		StringBuilder sql = new StringBuilder();
		String separator = "";
		for (int i = 0; i < columnNames.length; i++) {
			sql.append(separator.concat(" ?"));
			if (i == 0) {
				separator = ",";
			}
		}

		return sql.toString();
	}

	public static String createQueryOfColumnsForUpdate(String[] columnNames) {

		StringBuilder sql = new StringBuilder();
		String separator = ",";
		for (int i = 0; i < columnNames.length; i++) {
			if (i == columnNames.length - 1) {
				separator = " ";
			}
			sql.append(columnNames[i]).append("=?" + separator);
		}

		return sql.toString();
	}

	public static String createInsertQueryString(String tableName, String[] columnNames) {
		
		StringBuilder sql=new StringBuilder();
		sql.append(String.format("INSERT INTO  %s(", tableName))
		.append(DBUtil.createQueryListOfColumnsForInsert(columnNames)).append(")")
		.append(String.format("VALUES( %s )", DBUtil.createQueryListOfParamsForInsert(columnNames)));
		
		return sql.toString();
	}
	
	public static String createUpdateQueryString(String tableName, String[] columnNames, String pkColumnName) {
		
		StringBuilder sql=new StringBuilder();
		sql.append(String.format("UPDATE %s SET ", tableName))
		.append(DBUtil.createQueryOfColumnsForUpdate(columnNames))	
		.append(getPkWhereClause(pkColumnName));
		
		return sql.toString();
	}
	

	public static String createCountByPkQuery(String tableName, String pkColumnName) {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM " + tableName).append(String.format(" WHERE %s=?", pkColumnName));

		return sql.toString();
	}
	
	public static String getPkWhereClause(String pkColumnName) {
		return String.format(" WHERE %s=?", pkColumnName);
	}
	
	public static Long getLongValueFromResultSet(ResultSet rs, String columnName) throws SQLException {
		
		long value = rs.getLong(columnName);
		if (!rs.wasNull()) {
			return Long.valueOf(value);
		}
		return null;
	}

	public static Integer getIntegerValueFromResultSet(ResultSet rs, String columnName) throws SQLException {
		
		int value = rs.getInt(columnName);
		if (!rs.wasNull()) {
			return Integer.valueOf(value);
		}
		return null;
	}
	
	public static Double getDoubleValueFromResultSet(ResultSet rs, String columnName) throws SQLException {
		
		double value = rs.getDouble(columnName);
		if (!rs.wasNull()) {
			return Double.valueOf(value);
		}
		return null;
	}
	
}
