/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.business.service.helper;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import it.csi.idf.idfbo.util.Constants;


@Component
public abstract class AbstractServiceHelper {
	
	public static final Logger LOGGER = Logger.getLogger(Constants.LOGGING.LOGGER_NAME + ".service");
	
	
	public String getServiceUrl (String serviceName) {
		ResourceBundle res = ResourceBundle.getBundle("config");
		return res.getString(serviceName);
	}
	
}
