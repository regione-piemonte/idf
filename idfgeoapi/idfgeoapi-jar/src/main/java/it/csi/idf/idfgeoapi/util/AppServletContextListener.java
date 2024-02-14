/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfgeoapi.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

public class AppServletContextListener implements ServletContextListener {

	private static ServletContext sc;
	private static final Logger LOG = Logger.getLogger(Constants.COMPONENT_NAME);
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		/* Sets the context in a static variable */
		AppServletContextListener.sc = sce.getServletContext();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

	public static ServletContext getServletContext() {
		return sc;
	}
}
