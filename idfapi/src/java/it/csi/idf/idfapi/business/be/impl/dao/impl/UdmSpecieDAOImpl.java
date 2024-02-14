/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.UdmSpecieDAO;
import it.csi.idf.idfapi.dto.UdmSpecie;
import it.csi.idf.idfapi.mapper.UdmSpecieMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class UdmSpecieDAOImpl implements UdmSpecieDAO {

	@Override
	public List<UdmSpecie> findAllUdm() {
		
		String sql = " SELECT * FROM idf_d_udm";
		
		return DBUtil.jdbcTemplate.query(sql, new UdmSpecieMapper());
	}

}
