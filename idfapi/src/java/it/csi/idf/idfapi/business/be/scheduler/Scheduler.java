/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.scheduler;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import it.csi.idf.idfapi.business.be.impl.dao.IstanzaForestDAO;
import it.csi.idf.idfapi.util.DateUtil;

@Configuration
@EnableScheduling
public class Scheduler {
	
	static final Logger logger = Logger.getLogger(Scheduler.class);
	
	@Autowired
	private IstanzaForestDAO istanzaForestDAO;
	
	@Scheduled(fixedDelay = 86400000)
	public void scheduleFixedDelayTask() {
		try {
		istanzaForestDAO.updateToAccoltaInSilenzio();
	    logger.info(" Istanze accolte in silezione: " 
	    		+ istanzaForestDAO.updateToAccoltaInSilenzio() + " in data: " + DateUtil.getDataAndOraCorrente());
		}catch(Exception ex) {
			logger.error(ex);
		}
	}
	
}
