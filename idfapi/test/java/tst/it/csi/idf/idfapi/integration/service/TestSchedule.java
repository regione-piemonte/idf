/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package tst.it.csi.idf.idfapi.integration.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestSchedule {
	
	public static void main(String[] args) {
		final ApplicationContext context = new ClassPathXmlApplicationContext("beanContext.xml");
	} 
}
