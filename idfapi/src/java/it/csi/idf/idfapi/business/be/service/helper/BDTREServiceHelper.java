/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.service.helper;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BDTREServiceHelper extends AbstractServiceHelper {

	public static Logger logger = Logger.getLogger(BDTREServiceHelper.class);

	private String url;
    private String tokenUrl;

    public BDTREServiceHelper(String url, String tokenUrl) {
        this.url = url;
        this.tokenUrl = tokenUrl;
    }

    public String getUrlBDTRE() {
		return this.url;
	}

    public String getTokenUrlBDTRE() {
        logger.info("<--------------------URLTOKEN->>>>>>>>>>>>>>>>>>>>>>>>>" + tokenUrl);
		return this.tokenUrl;
	}
}
