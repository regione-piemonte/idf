/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.ConfigurationDAO;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.SpringSupportedResource;

@Component
public class ConfigurationDAOImpl extends SpringSupportedResource implements ConfigurationDAO {

	@Override
	public String getConfigurationByName(String name) {
		String sql = "select p.parametro_appl_char from idf_cnf_tipo_param_appl t "
				+ " inner join idf_cnf_parametro_appl p on p.fk_tipo_param_appl = t.id_tipo_param_appl "
				+ " where t.descr_tipo_param_appl = ? ";
		return DBUtil.jdbcTemplate.query(sql, new Object[] {name},  new ResultSetExtractor<String>()
        {
	    	@Override
			public String extractData(ResultSet rs) throws SQLException {
	    		while(rs.next()) {
	    			return rs.getString(1);
	    		}
				return null ;
			}
        });
	}	
	
	@Override
	public String getConfigurationNumberByName(String name) {
		String sql = "select p.parametro_appl_num from idf_cnf_tipo_param_appl t "
				+ " inner join idf_cnf_parametro_appl p on p.fk_tipo_param_appl = t.id_tipo_param_appl "
				+ " where t.descr_tipo_param_appl = ? ";
		return DBUtil.jdbcTemplate.query(sql, new Object[] {name},  new ResultSetExtractor<String>()
        {
	    	@Override
			public String extractData(ResultSet rs) throws SQLException {
	    		while(rs.next()) {
	    			return rs.getString(1);
	    		}
				return null ;
			}
        });
	}	
}
