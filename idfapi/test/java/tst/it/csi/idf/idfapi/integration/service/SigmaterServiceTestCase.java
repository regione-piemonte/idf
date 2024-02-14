/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package tst.it.csi.idf.idfapi.integration.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import it.csi.idf.idfapi.business.be.exceptions.ServiceException;
import it.csi.idf.idfapi.business.be.service.helper.SigmaterServiceHelper;
import it.csi.idf.idfapi.util.Constants;
import it.csi.sigmater.ws.client.sigwgssrvSigwgssrv.InfoParticella;
import it.csi.util.beanlocatorfactory.ServiceBeanLocator;

import static it.csi.idf.idfapi.util.builder.ToStringBuilder.objectToString;

public class SigmaterServiceTestCase {
	
	private static final Logger LOGGER = Logger.getLogger(Constants.COMPONENT_NAME + ".test.service");
    private static final String APPLICATION_CONTEXT_XML = "beanContext.xml";
    private static final String SIGMATER_SERVICE_BEAN = "sigmaterServiceHelper";
    
    private SigmaterServiceHelper sigmaterServiceHelper;
    
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
		sigmaterServiceHelper = (SigmaterServiceHelper)ServiceBeanLocator.getBeanByName(SIGMATER_SERVICE_BEAN);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetDettaglioTerreno() {
		LOGGER.debug("[SigmaterServiceTestCase::testGetDettaglioTerreno] BEGIN");
		
		final String istatComune = "L219";
		final String sezione = "";
		final String foglio = "1179";
		final String numero = "415";
		final String codiceFiscale = "AAAAAA00A11H000P";
		
		try {
			this.sigmaterServiceHelper.getDettagliDatiTerreno(istatComune, sezione, foglio, numero, codiceFiscale);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			LOGGER.debug("[SigmaterServiceTestCase::testGetDettaglioTerreno] END");
		}
	}
	

	
	@Test
	public void testGetDettaglioDatiTerrenoGeometrie() {
		LOGGER.debug("[SigmaterServiceTestCase::testGetDettaglioDatiTerrenoGeometrie] BEGIN");
		final String istatComune = "005042";
		final String sezione = "";
		final String foglio = "16";
		final String particella = "238";
		final String codiceFiscale = "AAAAAA00A11H000P";
		
		try {
//			List<InfoParticella> list = this.sigmaterServiceHelper.getDettaglioDatiTerrenoGeometrie(istatComune, sezione, foglio, particella, codiceFiscale);
			it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.InfoParticella[] list = this.sigmaterServiceHelper.getDettaglioDatiTerrenoGeometrie(istatComune, sezione, foglio, particella, codiceFiscale);
//			for (InfoParticella dto : list) {
//				LOGGER.debug("--- "+objectToString(dto));
//			}
			for (it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.InfoParticella dto : list) {
				LOGGER.debug("--- "+objectToString(dto));
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			LOGGER.debug("[SigmaterServiceTestCase::testGetDettaglioDatiTerrenoGeometrie] END");
		}
	}

}
