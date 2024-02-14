/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.impl.dao.ConfigUtenteDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SoggettoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.UserSessionDAO;
import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.dto.TSoggetto;
import it.csi.idf.idfapi.dto.UserInfo;
import it.csi.idf.idfapi.util.SpringSupportedResource;

@Component
public class UserSessionDAOImpl extends SpringSupportedResource implements UserSessionDAO {
	
	@Autowired
	private SoggettoDAO soggettoDAO;
	
	@Autowired
	private ConfigUtenteDAO configUtenteDAO;

	public static final String USERINFO_SESSIONATTR = "appDatacurrentUser";

	@Override
	public UserInfo getUser(HttpServletRequest httpRequest) {
		return (UserInfo)httpRequest.getSession().getAttribute(USERINFO_SESSIONATTR);
	}

	@Override
	public TSoggetto getSoggetoFromUser(UserInfo userInfo) throws RecordNotUniqueException {
		return soggettoDAO.findSoggettoByCodiceFiscale(userInfo.getCodFisc());
	}

	@Override
	public ConfigUtente getUtenteFromUserSoggetto(TSoggetto soggetto) {
		return configUtenteDAO.getCofigUtenteById(soggetto.getFkConfigUtente());
	}
}
