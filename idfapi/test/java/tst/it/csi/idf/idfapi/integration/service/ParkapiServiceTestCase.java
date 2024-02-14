/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package tst.it.csi.idf.idfapi.integration.service;



import it.csi.idf.idfapi.business.be.exceptions.ServiceException;
import it.csi.idf.idfapi.business.be.service.helper.ParkApiServiceHelper;
import it.csi.idf.idfapi.util.Constants;
import static it.csi.idf.idfapi.util.builder.ToStringBuilder.objectToString;
import it.csi.util.beanlocatorfactory.ServiceBeanLocator;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ParkapiServiceTestCase {

    private static final Logger LOGGER = Logger.getLogger(Constants.COMPONENT_NAME + ".test.service");

    private static final String APPLICATION_CONTEXT_XML = "beanContext.xml";

    private static final String PARKAPI_SERVICE_BEAN = "parkapiServiceHelper";

    private ParkApiServiceHelper parkApiServiceHelper;

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
        this.parkApiServiceHelper = (ParkApiServiceHelper) ServiceBeanLocator.getBeanByName(PARKAPI_SERVICE_BEAN);
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
    public void testSicZps() throws ServiceException {
        LOGGER.debug("[ParkapiServiceTestCase::testSicZps] BEGIN");
        try {
        	
        	String geometria = "<gml:Polygon srsName=\"EPSG:32632\" xmlns:gml=\"http://www.opengis.net/gml\"><gml:outerBoundaryIs><gml:LinearRing><gml:coordinates decimal=\".\" cs=\",\" ts=\" \">371801.01751313487,5037016.9067425551 400291.30560420314,5030871.9426444815 418726.19789842388,5011319.7841506116 417050.29859894922,4985064.0284588421 406436.26970227668,4962718.7044658475 392470.44220665505,4944283.8121716268 364538.78721541155,4950987.4093695255 347221.16112084064,4968863.668563921 343869.36252189142,4991767.6256567407 355042.02451838879,5016906.11514886 371801.01751313487,5037016.9067425551 </gml:coordinates></gml:LinearRing></gml:outerBoundaryIs></gml:Polygon>";
        	
            String result = this.parkApiServiceHelper.getRicadenzaNatura2000(geometria);
            
            String aree = this.parkApiServiceHelper.getRicadenzaAreeProtette(geometria);
            
            
            
            

            LOGGER.debug("[ParkapiServiceTestCase::testSicZps]: " + result);
        } finally {
            LOGGER.debug("[ParkapiServiceTestCase::testSicZps] END");
        }
    }    

}
