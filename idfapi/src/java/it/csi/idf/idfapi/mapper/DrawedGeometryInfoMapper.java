/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.DrawedGeometryInfo;
import it.csi.idf.idfapi.util.StringUtils;

public class DrawedGeometryInfoMapper implements RowMapper<DrawedGeometryInfo>{
	@Override
	public DrawedGeometryInfo mapRow(ResultSet rs, int arg1) throws SQLException {
		DrawedGeometryInfo info = new DrawedGeometryInfo();
		info.setTipoGeometria(rs.getString("geomtype"));
		info.setDescrizione(rs.getString("descrizione"));
		info.setParticelleForet(sortParticelleForest(rs.getString("partforest")));
		if(info.getParticelleForet().trim().length() == 0) {
			info.setParticelleForet("Fuori piano");
		}
		if(rs.getInt("x_pun") > -1) {
			info.setPuntoCoord("UTM EST: " + formatNumber(rs.getString("x_pun"),0) 
				+ ", UTM NORD: " + formatNumber(rs.getString("y_pun"),0) );
			info.setGeometryInfo(info.getPuntoCoord());
		}
		if(rs.getInt("length_lin") > -1) {
			info.setLunghezzaLinea(formatNumber(rs.getString("length_lin"),0));
			info.setGeometryInfo("Lunghezza (m): " + info.getLunghezzaLinea());
		}

		Double area = rs.getDouble("area_pol");
		if(area > -1) {
			info.setSuperficiePoligon(area.toString().replace(".", ","));
			info.setGeometryInfo("Superficie (ha): " + (formatNumber(area.toString(),2)));
		}
		return info;
	}
	
	private String formatNumber(String num, int decimalPlace) {
		if(num != null) {
		BigDecimal bg = new BigDecimal(num);
		 return bg.setScale(decimalPlace, RoundingMode.HALF_UP).toString().replace(".", ",");
		}
		return "";
	}
	
	private String sortParticelleForest(String particelle) {
		if(particelle != null) {
			String[] array = particelle.split(", ");
			StringBuilder sb = new StringBuilder();
			boolean isFirst = true;
			StringUtils.sort(array);
			for(String item: array) {
				if(!isFirst) {
					sb.append(", ");
				}else {
					isFirst = false;
				}
				sb.append(item);
			}
			return sb.toString();
		}
		return "";
	}
}
