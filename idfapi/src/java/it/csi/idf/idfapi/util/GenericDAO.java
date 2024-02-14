/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import org.apache.log4j.Logger;

public abstract class GenericDAO {
	
	private static final Logger logger = Logger.getLogger(GenericDAO.class);
	
	public <T> PaginatedList<T> paginatedList(String sql, List<Object> parameters, RowMapper<T> rowMapper, int page,
			int limit) {
		if (ListUtil.isEmpty(parameters)) {
			return this.paginatedList(sql, rowMapper, page, limit);
		}
		return this.paginatedList(sql, parameters.toArray(), rowMapper, page, limit);
	}
	
	public <T> PaginatedList<T> paginatedList(String sql, Map<String, Object> paramMap, RowMapper<T> rowMapper, int page, int limit) {
		
		String  countSql     = this.getCountSql(sql);
		String  paginatedSql = this.getPaginatedSql(sql, page, limit);

		int     count        = DBUtil.jdbcTemplate.queryForObject(countSql, Integer.class, paramMap);
		List<T> list         = DBUtil.jdbcTemplate.query(paginatedSql, rowMapper, paramMap);
		
		return new PaginatedList<>(list, count, limit, page);
	}
	
	public <T> PaginatedList<T> paginatedList(String sql, RowMapper<T> rowMapper, int page, int limit) {
		String  countSql     = this.getCountSql(sql);
		String  paginatedSql = this.getPaginatedSql(sql, page, limit);
		
		int     count        = DBUtil.jdbcTemplate.queryForObject(countSql, Integer.class);
		List<T> list         = DBUtil.jdbcTemplate.query(paginatedSql, rowMapper);
		
		return new PaginatedList<>(list, count, limit, page);
	}
	
	public <T> PaginatedList<T> paginatedList(String sql, Object[] args, RowMapper<T> rowMapper, int page, int limit) {
		String  countSql     = this.getCountSql(sql);
		String  paginatedSql = this.getPaginatedSql(sql, page, limit);
		
		logger.info("paginatedSql: " + paginatedSql);
		
	    int count        = DBUtil.jdbcTemplate.queryForObject(countSql, Integer.class, args);
		List<T> list         = DBUtil.jdbcTemplate.query(paginatedSql, rowMapper, args);
		return new PaginatedList<>(list, count, limit, page);
	}
	
	public PaginatedList<Map<String, Object>> paginatedList(String sql, int page, int limit) {
		String countSql = this.getCountSql(sql);
		String paginatedSql = this.getPaginatedSql(sql, page, limit);
		 int count = DBUtil.jdbcTemplate.queryForObject(countSql, Integer.class);
		List<Map<String, Object>> list = this.queryForList(paginatedSql);
		
		return new PaginatedList<>(list, count, limit, page);
	}
	
	protected String getPaginatedSql(String sql, int page, int limit) {
		int offset = (page - 1) * limit;
		return concateneString(sql, this.getLimitCondition(limit), " OFFSET ", offset);
	}
	
	protected String getLimitCondition(int limit) {
		if (limit > 0)
			return concateneString(" LIMIT ", limit);
		else 
			return "";
	}
	
	public <T> T queryForObject(String sql, Class<T> clazz) { 
		return DBUtil.jdbcTemplate.queryForObject(sql, clazz);
	}
	
	public <T> T queryForObject(String sql, Class<T> clazz, Object...args) { 
		return DBUtil.jdbcTemplate.queryForObject(sql, clazz, args);
	}
	
	public <T> T queryForObject(String sql, Class<T> clazz, List<Object> args) { 
		if(ListUtil.isEmpty(args))
			return this.queryForObject(sql, clazz);
		return this.queryForObject(sql, clazz, args.toArray());
	}
	
	public <T> T queryForObject(String sql, RowMapper<T> rowMapper, List<Object> args) {
		if(ListUtil.isEmpty(args))
			return this.queryForObject(sql, rowMapper);
		else
			return this.queryForObject(sql, rowMapper, args.toArray());
	}
	
	public <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object... args) { 
		return DBUtil.jdbcTemplate.queryForObject(sql, rowMapper, args);
	}
	
	public <T> T queryForObject(String sql, RowMapper<T> rowMapper) { 
		return DBUtil.jdbcTemplate.queryForObject(sql, rowMapper);
	}
	
	public List<Map<String, Object>> queryForList(String sql) { 
		return DBUtil.jdbcTemplate.queryForList(sql);
	}
	
	public <T> List<T> queryForList(String sql, RowMapper<T> rowMapper) {
		return DBUtil.jdbcTemplate.query(sql, rowMapper);
	}
	
	public <T> List<T> queryForList(String sql, RowMapper<T> rowMapper, Object... args) {
		return DBUtil.jdbcTemplate.query(sql, rowMapper, args);
	}
	
	public <T> List<T> queryForListNamedParameters(String sql, ResultSetExtractor<List<T>> rse, Object... args) {
		return DBUtil.jdbcTemplate.query(sql, rse, args);
	}
	
	public <T> List<T> queryForList(String sql, RowMapper<T> rowMapper, List<Object> args) {
		if(ListUtil.isEmpty(args))
			return this.queryForList(sql, rowMapper);
		return this.queryForList(sql, rowMapper, args.toArray());
	}
	
	public List<Map<String, Object>> queryForList(String sql, Object... args) { 
		return DBUtil.jdbcTemplate.queryForList(sql, args);
	}
	
	public <T> List<T> queryForList(String sql, Class<T> clazz, Object... args) { 
		return DBUtil.jdbcTemplate.queryForList(sql, clazz, args);
	}

	public <T> List<T> queryForList(String sql, Class<T> clazz, List<Object> args) { 
		return this.queryForList(sql, clazz, args.toArray());
	}
	
	public List<Map<String, Object>> queryForList(String sql, List<Object> args) {
		if(ListUtil.isEmpty(args))
			return this.queryForList(sql);
		return this.queryForList(sql, args.toArray());
	}

	public int update(String sql) {
		
			return DBUtil.jdbcTemplate.update(sql);
		
	}
	
	public int update(String sql, Object... args) {
		
			return DBUtil.jdbcTemplate.update(sql, args);
		
	}
	
	public int update(String sql, List<Object> args) {
		if(ListUtil.isEmpty(args))
			return this.update(sql);
		return this.update(sql, args.toArray());
	}

	
	private String getCountSql(String sql) {
		return concateneString("select count(*) FROM (", sql , ") as cnt");
	}
	
	
	protected <T> T singleRow(String sql, RowMapper<T> rowMapper, Object[] args){
    	try{
    		return DBUtil.jdbcTemplate.queryForObject(sql, args, rowMapper);
		}catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	protected String addInCondition(String field, List<?> parametersToAdd, List<Object> parameters) {
		StringBuilder sb = new StringBuilder();
		if(ListUtil.isNotEmpty(parametersToAdd)) {
			sb.append("(").append(field).append(" in ");
			int idx = 1;
			String sep = "(";
			for(Iterator<?> iterator = parametersToAdd.iterator() ; iterator.hasNext() ; ) {
				sb.append(sep).append("?");
				sep = ",";
				parameters.add(iterator.next());
				if(idx == 100 && iterator.hasNext()) {
					sb.append(") OR (").append(field).append(" in ");
					idx = 1;
					sep = "(";
				}else {
					idx++;
				}
			}
			sb.append("))");
		}
		return sb.toString();
	}
	

	protected String addNotInCondition(String field, List<?> parametersToExclude, List<Object> parameters) {
		StringBuilder sb = new StringBuilder();
		if(ListUtil.isNotEmpty(parametersToExclude)) {
			sb.append("(").append(field).append(" not in ");
			int idx = 1;
			String sep = "(";
			for(Iterator<?> iterator = parametersToExclude.iterator() ; iterator.hasNext() ; ) {
				sb.append(sep).append("?");
				sep = ",";
				parameters.add(iterator.next());
				if(idx == 100 && iterator.hasNext()) {
					sb.append(") OR (").append(field).append(" in ");
					idx = 1;
					sep = "(";
				}else {
					idx++;
				}
			}
			sb.append("))");
		}
		return sb.toString();
	}
	
	public java.sql.Date getCurrentDate(){
		return this.queryForObject("select current_date", java.sql.Date.class);
	}
	
	public java.sql.Timestamp getCurrentTimestamp(){
		return this.queryForObject("select current_timestamp", java.sql.Timestamp.class);
	}
	public static String concateneString(Object... tokens) {
        if(tokens == null)
            return null;
        StringBuilder sb = new StringBuilder();
        for(Object token : tokens) {
            if(token instanceof char[])
                sb.append(new String((char[])token));
            else
                sb.append(token);
        }
        return sb.toString();
   
    }
}

