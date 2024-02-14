/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package tst.it.csi.idf.idfapi.integration.service;


import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import it.csi.idf.idfapi.business.be.exceptions.ServiceException;
import it.csi.idf.idfapi.business.be.service.helper.AaepServiceHelper;
import it.csi.idf.idfapi.business.be.service.helper.SiforsrvCategorieForestaliServiceHelper;
import it.csi.idf.idfapi.util.Constants;
import static it.csi.idf.idfapi.util.builder.ToStringBuilder.objectToString;
import it.csi.idf.idfapi.util.service.integration.aaep.AziendaAAEP;
import it.csi.util.beanlocatorfactory.ServiceBeanLocator;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SiforServiceTestCase {

    private static final Logger LOGGER = Logger.getLogger(Constants.COMPONENT_NAME + ".test.service");

    private static final String APPLICATION_CONTEXT_XML = "beanContext.xml";

    private static final String CATEGORIE_FORESTALI_SERVICE_BEAN = "siforsrvCategorieForestaliServiceHelper";

    private SiforsrvCategorieForestaliServiceHelper siforsrvCategorieForestaliServiceHelper;

    /**
     * @throws java.lang.Exception
     */
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

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.siforsrvCategorieForestaliServiceHelper = (SiforsrvCategorieForestaliServiceHelper) ServiceBeanLocator.getBeanByName(CATEGORIE_FORESTALI_SERVICE_BEAN);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link it.csi.sipra.sipraweb.business.helper.AaepServiceHelper#getAziendaByCodiceFiscale(java.lang.String, java.lang.String)}.
     *
     * @throws ServiceException
     */
    @Test
    public void testRicadenza() throws ServiceException {
        LOGGER.debug("[SiforServiceTestCase::testRicadenza] BEGIN");
        try {
            String result = this.siforsrvCategorieForestaliServiceHelper.testRicadenza();

            LOGGER.debug("[SiforServiceTestCase::testRicadenza]: " + result);
        } finally {
            LOGGER.debug("[SiforServiceTestCase::testRicadenza] END");
        }
    }    

}
