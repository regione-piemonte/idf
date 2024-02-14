/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package tst.it.csi.idf.idfapi.integration.service;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import it.csi.idf.idfapi.business.be.exceptions.ServiceException;
import it.csi.idf.idfapi.business.be.service.helper.PrimpaforservServiceHelper;
import it.csi.idf.idfapi.util.Constants;
import it.csi.idf.idfapi.util.service.integration.primpaforserv.Istanza;
import it.csi.util.beanlocatorfactory.ServiceBeanLocator;

import static it.csi.idf.idfapi.util.builder.ToStringBuilder.objectToString;

public class PrimpaforservServiceTestCase {
	
	private static final Logger LOGGER = Logger.getLogger(Constants.COMPONENT_NAME + ".test.service");
    private static final String APPLICATION_CONTEXT_XML = "beanContext.xml";
    private static final String PRIMPAFORSERV_SERVICE_BEAN = "primpaforservServiceHelper";
    
    private PrimpaforservServiceHelper primpaforservServiceHelper;
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		 try {
        	 @SuppressWarnings("unused")
        	final ApplicationContext context = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_XML);
        }
        catch (Exception e) {
        	System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		primpaforservServiceHelper = (PrimpaforservServiceHelper)ServiceBeanLocator.getBeanByName(PRIMPAFORSERV_SERVICE_BEAN);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetDettaglioIstanza() {
		LOGGER.debug("[PrimpaforservServiceTestCase::testGetDettaglioIstanza] BEGIN");
		
		final int idIstanza = 8100;
		
		try {
			Istanza result = this.primpaforservServiceHelper.getDettaglioIstanzaPrimpa(idIstanza);
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			LOGGER.debug("[PrimpaforservServiceTestCase::testGetDettaglioIstanza] END");
		}
	}
	

	
	
}
