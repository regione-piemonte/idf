/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.service;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.util.DateUtil;

@Component("actaScheduler")
@EnableScheduling	
public class ActaScheduler {
	
	@Scheduled(cron = "0 0 18,19 * * *")
	public void schedule() {
		System.out.println("PROVA SCHEDULAZIONE CON ESPRESSIONE CRON "+DateUtil.getTimestampDataCorrente());
	}
	
	
	
}
