/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.ReportByCsvDAO;
import it.csi.idf.idfapi.dto.CampoReport;
import it.csi.idf.idfapi.dto.TipoReport;
import it.csi.idf.idfapi.util.DBUtil;
@Component
public class ReportByCsvDAOImpl implements ReportByCsvDAO {

	@Override
	public List<TipoReport> getReportList(int adsType) {
		String sql = "SELECT ID,DENOMINAZIONE FROM IDF_REPORT_TIPOLOGIA WHERE fk_report_tipo_ads = ? ORDER BY ID ";
		return DBUtil.jdbcTemplate.query(sql, new Object[] {adsType}, new ResultSetExtractor<List<TipoReport>>()
        {
            @Override
            public List<TipoReport> extractData(ResultSet rs) throws SQLException, DataAccessException
            {
            	List<TipoReport> res = new ArrayList<TipoReport>();
            	while(rs.next()){  
            		res.add(new TipoReport(rs.getInt(1),rs.getString(2)));
            	}	
            	return res;
            }
        });
	}

	@Override
	public List<CampoReport> getReportDetail(Integer idReport) {
		
		String sql = "SELECT B.ID, B.DENOMINAZIONE,A.TIPO  FROM IDF_REPORT_TIPO_CAMPO A "
				+ " INNER JOIN IDF_REPORT_CAMPO B ON A.ID = B.TIPO "
				+ " WHERE ID_REPORT = ? ORDER BY B.ID";
		return DBUtil.jdbcTemplate.query(sql, new Object[] {idReport},  new ResultSetExtractor<List<CampoReport>>()
        {
	    	@Override
			public List<CampoReport> extractData(ResultSet rs) throws SQLException {
	    		List<CampoReport> res = new ArrayList<CampoReport>();
	    		while(rs.next()) {
	    			res.add(new CampoReport(rs.getInt(1),rs.getString(2),rs.getString(3)));
	    		}
				return res ;
			}
        });
	}

	@Override
	public List<TipoReport> getAdsTypeList() {
		String sql = "SELECT ID,DENOMINAZIONE FROM idf_report_tipo_ads ORDER BY ID ";
		return DBUtil.jdbcTemplate.query(sql, new ResultSetExtractor<List<TipoReport>>()
        {
            @Override
            public List<TipoReport> extractData(ResultSet rs) throws SQLException, DataAccessException
            {
            	List<TipoReport> res = new ArrayList<TipoReport>();
            	while(rs.next()){  
            		res.add(new TipoReport(rs.getInt(1),rs.getString(2)));
            	}	
            	return res;
            }
        });
	}

}
