/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.util.Constants;

@Component
public abstract class AbstractDAO {
	
	protected static final Logger LOGGER = Logger.getLogger(Constants.COMPONENT_NAME);

}
