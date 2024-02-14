/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package tst.it.csi.idf.idfapi.integration.service;

import java.io.PrintWriter;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import it.csi.idf.idfapi.business.be.exceptions.ServiceException;
import it.csi.idf.idfapi.business.be.service.helper.GeecoServiceHelper;
import it.csi.idf.idfapi.business.be.service.helper.GeecoServiceLocalHelper;
import it.csi.idf.idfapi.util.Constants;
import it.csi.idf.idfapi.util.DateUtil;
import it.csi.util.beanlocatorfactory.ServiceBeanLocator;

public class GeecoServiceTestCase {
	
	private static final Logger LOGGER = Logger.getLogger(Constants.COMPONENT_NAME + ".test.service");
    private static final String APPLICATION_CONTEXT_XML = "beanContext.xml";
    private static final String GEECO_SERVICE_BEAN = "geecoServiceLocalHelper";
    
    private GeecoServiceLocalHelper geecoServiceLocalHelper;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		 @SuppressWarnings("unused")
     	final ApplicationContext context = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_XML);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		geecoServiceLocalHelper = (GeecoServiceLocalHelper)ServiceBeanLocator.getBeanByName(GEECO_SERVICE_BEAN);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetGeecoConfiguration() {
		
//		final String idProfiloGeeco = "5";
		final String[] id = new String[] {"1120"};
//		final String id = null;
//		final String idProfiloGeeco = "9";
//		final String[] id = new String[] {"1595"};
		final String idProfiloGeeco = "19";
//		final String[] id = new String[] {"1", "3", "6", "12"};
		
		
		try {
			
			String body = geecoServiceLocalHelper.getGeecoConfiguration(idProfiloGeeco, id);
			
			
			
			LOGGER.debug("--------------- inizio body --------------------");
			JSONObject obj = new JSONObject(body);
			
			
			
			LOGGER.debug("Pretty JSON");
			String s = obj.toString(2);
			LOGGER.debug(s);
			
			//LOGGER.debug("--------------- fine body --------------------");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testQuery() {
		String first = "PFA -";
		String input = "GESTORE";
		String s = geecoServiceLocalHelper.testQuery(first, input);
	}
}
