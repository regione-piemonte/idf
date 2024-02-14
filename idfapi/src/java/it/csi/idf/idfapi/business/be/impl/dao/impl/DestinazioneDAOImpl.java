/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.DestinazioneDAO;
import it.csi.idf.idfapi.dto.Destinazione;
import it.csi.idf.idfapi.mapper.DestinazioneMapper;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.IplaEnum;

@Component
public class DestinazioneDAOImpl implements DestinazioneDAO {
	
	public static final Logger logger = Logger.getLogger(DestinazioneDAOImpl.class);
	
	@Override
	public List<Destinazione> findAllDestinazione(IplaEnum type) {
		logger.info("findAllDestinazione - ipla: " + type.getTypeValue());
		String sql = "SELECT * FROM idf_d_destinazione where fk_config_ipla = ? and flg_visibile = 1 order by mtd_ordinamento";
		return DBUtil.jdbcTemplate.query(sql, new DestinazioneMapper(), type.getTypeValue());	
	}


}
