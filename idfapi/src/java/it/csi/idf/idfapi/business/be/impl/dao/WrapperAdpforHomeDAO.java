/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;

import org.springframework.transaction.annotation.Transactional;

import it.csi.idf.idfapi.business.be.exceptions.GenericException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.dto.PlainAdpforHome;
import it.csi.idf.idfapi.dto.PlainBackOfficeInfo;
import it.csi.idf.idfapi.dto.TSoggetto;
import it.csi.idf.idfapi.dto.UserInfo;
import it.csi.idf.idfapi.util.ProceduraEnum;

@Transactional(rollbackFor=Exception.class)
public interface WrapperAdpforHomeDAO {
	PlainAdpforHome getAdpforHome(Integer idTipoIstanza, UserInfo user, Integer profilo) throws RecordNotUniqueException;
	
	ConfigUtente updateAdpforHome(UserInfo user, PlainAdpforHome body)
			throws RecordNotUniqueException, ValidationException, GenericException;

	PlainBackOfficeInfo getAdpforBackOfficeHome(UserInfo user, int profilo) throws RecordNotUniqueException;


	PlainAdpforHome createMeAsRichidente(TSoggetto body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) throws RecordNotUniqueException;

	ConfigUtente getHomeData(UserInfo user, int profilo, HttpServletRequest httpRequest) throws RecordNotUniqueException;
	
	ConfigUtente getHomeData(UserInfo user, ProceduraEnum procedura, HttpServletRequest httpRequest) throws RecordNotUniqueException;
	
	ConfigUtente getDataForPFAAccess(UserInfo user, int profilo, HttpServletRequest httpRequest) throws RecordNotUniqueException;

	 void updateIdfRSoggettoTipoSoggeto(Integer idSoggetto, Integer fkTipoAccreditamento);



	
}
