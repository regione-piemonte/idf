/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.PlainUtenteApi;
import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.business.be.impl.dao.ConfigUtenteDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SoggettoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.UserSessionDAO;
import it.csi.idf.idfapi.business.be.impl.dao.impl.WrapperAdpforHomeDAOImpl;
import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.dto.PlainUtenteSoggetto;
import it.csi.idf.idfapi.dto.TSoggetto;
import it.csi.idf.idfapi.dto.UserInfo;
import it.csi.idf.idfapi.dto.Utente;
import it.csi.idf.idfapi.errors.ErrorConstants;
import it.csi.idf.idfapi.rest.BaseResponses;
import it.csi.idf.idfapi.util.ProfiloUtenteEnum;
import it.csi.idf.idfapi.util.SpringSupportedResource;
import it.csi.idf.idfapi.util.TipoAccreditamentoEnum;
import it.csi.idf.idfapi.util.ValidationUtil;

public class PlainUtenteApiServiceImpl extends SpringSupportedResource implements PlainUtenteApi {
	
	public static final String UPDATE_USER_DONE = "updateUserDone";

	@Autowired
	private SoggettoDAO soggettoDAO;

	@Autowired
	private ConfigUtenteDAO configUtenteDAO;

	@Autowired
	private UserSessionDAO userSessionDAO;

	@Override
	public Response searchUtente(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		logger.info("[PlainUtenteApiServiceImpl::searchUtente] START");

		PlainUtenteSoggetto plainUtenteSoggetto = new PlainUtenteSoggetto();
		
		UserInfo userInfo = userSessionDAO.getUser(httpRequest);
		

		try {
			TSoggetto soggetto = soggettoDAO.findSoggettoByCodiceFiscale(userInfo.getCodFisc());
			
			if(soggetto != null) {
				ConfigUtente configUtente;
	
				if (soggetto.getFkConfigUtente() != null) {
					configUtente = configUtenteDAO.getCofigUtenteById(soggetto.getFkConfigUtente());
					plainUtenteSoggetto.setFlgPrivacy(configUtente.getFlgPrivacy());
				}
	
				plainUtenteSoggetto.setIdSoggetto(soggetto.getIdSoggetto());
				plainUtenteSoggetto.setIdConfigUtente(soggetto.getFkConfigUtente());
				plainUtenteSoggetto.setCodiceFiscale(soggetto.getCodiceFiscale());
				plainUtenteSoggetto.setCognome(soggetto.getCognome());
				plainUtenteSoggetto.setNome(soggetto.getNome());
				plainUtenteSoggetto.setRuolo(userInfo.getRuolo());
				// Added on 02.07.2020 - customer instruction
				plainUtenteSoggetto.setProvider(userInfo.getCommunity());				
				plainUtenteSoggetto.setNrTelefonico(soggetto.getNrTelefonico());
				plainUtenteSoggetto.setEmail(soggetto.geteMail());
			} else {
				Utente utente = new Utente();
				utente.setCodiceFiscale(userInfo.getCodFisc());
				utente.setNome(userInfo.getNome());
				utente.setCognome(userInfo.getCognome());
				utente.setRuolo(userInfo.getRuolo());
				// Added on 02.07.2020 - customer instruction
				utente.setProvider(userInfo.getCommunity());
				return Response.ok(utente).build();
			}
		} catch (Exception e) {
			return Response.serverError().build();
		}
		finally {
			logger.info("[PlainUtenteApiServiceImpl::searchUtente] END");
		}
		return Response.ok(plainUtenteSoggetto).build();
	}

	@Override
	public Response createUtente(PlainUtenteSoggetto body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		logger.info("[PlainUtenteApiServiceImpl::createUtente] START");
		try {
			utenteValidation(body);
			TSoggetto soggetto = new TSoggetto();		
			
			soggetto.setCodiceFiscale(body.getCodiceFiscale());
			soggetto.setCognome(body.getCognome());
			soggetto.setNome(body.getNome());
			soggetto.setNrTelefonico(body.getNrTelefonico());
			soggetto.seteMail(body.getEmail());
			soggetto.setDataInserimento(LocalDate.now());
			soggetto.setFlgSettoreRegionale((byte) 0);

			int idSoggetto = soggettoDAO.createSoggettoFisica(soggetto);
			
			ConfigUtente configUtente = new ConfigUtente();
			Timestamp now = new Timestamp(System.currentTimeMillis());
			configUtente.setDataPrimoAccesso(now);
			configUtente.setDataUltimoAccesso(now);
			configUtente.setNrAccessi(1);
			configUtente.setFkProfiloUtente(ProfiloUtenteEnum.NO_DATA.getValue());
			configUtente.setFkTipoAccreditamento(TipoAccreditamentoEnum.NO_DATA.getValue());
			configUtente.setFlgPrivacy(body.getFlgPrivacy());
			configUtente.setFkSoggetto(idSoggetto);

			soggettoDAO.updateFkConfigUtente(idSoggetto, configUtenteDAO.createConfigUtente(configUtente));
		} catch(Exception e) {
			e.printStackTrace();
			logger.info("Exception e "+e.getMessage());
			return Response.serverError().build();
		}
		finally {
			logger.info("[PlainUtenteApiServiceImpl::createUtente] END");
		}
		httpRequest.getSession().setAttribute(UPDATE_USER_DONE, true);
		return Response.ok().build();
	}

	@Override
	public Response updateUtente(PlainUtenteSoggetto body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		if (body.getIdSoggetto() == null || body.getIdConfigUtente() == null) {
			return BaseResponses.requiredField();
		}

		try {
			utenteValidation(body);

			TSoggetto soggetto = soggettoDAO.findSoggettoById(body.getIdSoggetto());
			

			soggetto.setNrTelefonico(body.getNrTelefonico());
			soggetto.seteMail(body.getEmail());
			soggetto.setDataAggiornamento(LocalDate.now());
			soggettoDAO.updateSoggetto(soggetto);

			//estraggo configUtente per utente idfacc ossia con profilo 0
			List<ConfigUtente> configUtenteList = configUtenteDAO.getCofigUtenteBySoggettoIdAnProfilo(soggetto.getIdSoggetto(),
					ProfiloUtenteEnum.NO_DATA.getValue());
			if(configUtenteList!=null){
				ConfigUtente configUtente = configUtenteList.get(0);
				Timestamp now = new Timestamp(System.currentTimeMillis());
				configUtente.setDataUltimoAccesso(now);
				configUtente.setNrAccessi(configUtente.getNrAccessi() + 1);
				configUtente.setFlgPrivacy(body.getFlgPrivacy());
				configUtenteDAO.updateConfigUtente(configUtente);
			} else {
				ConfigUtente configUtente = new ConfigUtente();
				Timestamp now = new Timestamp(System.currentTimeMillis());
				configUtente.setDataPrimoAccesso(now);
				configUtente.setDataUltimoAccesso(now);
				configUtente.setNrAccessi(1);
				configUtente.setFkProfiloUtente(ProfiloUtenteEnum.NO_DATA.getValue());
				configUtente.setFkTipoAccreditamento(TipoAccreditamentoEnum.NO_DATA.getValue());
				configUtente.setFlgPrivacy(body.getFlgPrivacy());
				configUtente.setFkSoggetto(soggetto.getIdSoggetto());

				soggettoDAO.updateFkConfigUtente(soggetto.getIdSoggetto(), configUtenteDAO.createConfigUtente(configUtente));
			}


		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		httpRequest.getSession().setAttribute(UPDATE_USER_DONE, true);
		return Response.ok().build();
	}

	private void utenteValidation(PlainUtenteSoggetto utenteSoggetto) throws ValidationException {

		if (utenteSoggetto.getCodiceFiscale() == null 
				|| utenteSoggetto.getCognome() == null
				|| utenteSoggetto.getNome() == null 
				|| utenteSoggetto.getRuolo() == null 
				|| utenteSoggetto.getProvider() == null 
				|| utenteSoggetto.getNrTelefonico() == null
				|| utenteSoggetto.getEmail() == null 
				|| utenteSoggetto.getFlgPrivacy() != 1) {
			throw new ValidationException(ErrorConstants.OGGETTO_OBBLIGATORIO);
		}

		if (utenteSoggetto.getNrTelefonico().length() > 20
				|| utenteSoggetto.getEmail().length() > 100) {
			throw new ValidationException(ErrorConstants.STRINGA_DIGITATA_ERROR);
		}

		if (!ValidationUtil.isEMail(utenteSoggetto.getEmail())) {
			throw new ValidationException(ErrorConstants.MAIL_NON_VALIDO);
		}
	}

	@Override
	public Response isUtenteUpdate(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		Map<String,Object> resp = new HashMap<String,Object>();
		logger.info("UPDATE_USER_DONE: " + httpRequest.getSession().getAttribute(UPDATE_USER_DONE));
		if("localhost".equals(httpRequest.getServerName())) {
			resp.put("isUpdate", true);
		}else {
			resp.put("isUpdate", httpRequest.getSession().getAttribute(UPDATE_USER_DONE));
		}
		return  Response.ok(resp).build();
	}

	@Override
	public Response logout(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		httpRequest.getSession().invalidate();
		return Response.ok().build();
	}
}
