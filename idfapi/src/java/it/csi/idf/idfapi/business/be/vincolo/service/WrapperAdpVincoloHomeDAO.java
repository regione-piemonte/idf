/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;

import org.springframework.transaction.annotation.Transactional;

import it.csi.idf.idfapi.business.be.exceptions.MultiErrorException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.business.be.vincolo.pojo.PlainAdpVincoloHome;
import it.csi.idf.idfapi.dto.AmbitoDto;
import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.dto.PlainParticelleCatastali;
import it.csi.idf.idfapi.dto.TSoggetto;
import it.csi.idf.idfapi.dto.UserInfo;

@Transactional(rollbackFor = Exception.class)
public interface WrapperAdpVincoloHomeDAO {
	PlainAdpVincoloHome getAdpforHome(UserInfo user, Integer profilo, int tipoIstanzaId) throws MultiErrorException, ValidationException, RecordNotUniqueException;

	ConfigUtente updateAdpVincoloRichidente(UserInfo user, Integer idProfilo, PlainAdpVincoloHome body)
			throws MultiErrorException, RecordNotUniqueException;

	ConfigUtente updateAdpVincoloProfessionista(UserInfo user, Integer idProfilo, PlainAdpVincoloHome body)
			throws MultiErrorException, RecordNotUniqueException;

	PlainAdpVincoloHome createMeAsRichidente(TSoggetto body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) throws MultiErrorException, RecordNotUniqueException;

	ConfigUtente getHomeData(UserInfo user, int profilo, HttpServletRequest httpRequest)
			throws RecordNotUniqueException;
	 List<AmbitoDto> getAmbito();

	void updateIdfRSoggettoTipoSoggeto(Integer idSoggetto, Integer fkTipoAccreditamento);

	void plainHomeValidationProfes(PlainAdpVincoloHome plainHome) throws MultiErrorException;
}
