/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.csi.idf.idfapi.business.be.impl.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import it.csi.idf.idfapi.business.be.InterventoInsericiApi;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.dto.EventoPiano;
import it.csi.idf.idfapi.dto.FatDocumentoAllegato;
import it.csi.idf.idfapi.dto.IntervSelvicolturale;
import it.csi.idf.idfapi.dto.InterventoDatiTehnici;
import it.csi.idf.idfapi.dto.InterventoUtilizzatore;
import it.csi.idf.idfapi.dto.PfaLottoLocalizza;
import it.csi.idf.idfapi.dto.ShootingMirrorDTO;
import it.csi.idf.idfapi.dto.SpeciePfaIntervento;
import it.csi.idf.idfapi.dto.TipoInterventoDatiTecnici;
import it.csi.idf.idfapi.errors.ErrorConstants;
import it.csi.idf.idfapi.rest.BaseResponses;
import it.csi.idf.idfapi.util.IplaEnum;
import it.csi.idf.idfapi.util.ProceduraEnum;
import it.csi.idf.idfapi.util.SpringSupportedResource;
import it.csi.idf.idfapi.util.TipoUtilizzatoreRicercaEnum;
import it.csi.idf.idfapi.validation.AllStepsValidator;
import it.csi.idf.idfapi.validation.PfaAllegatiValidator;
import it.csi.idf.idfapi.validation.StepValidationErrors;

public class InterventoInsericiApiServisImpl extends SpringSupportedResource implements InterventoInsericiApi {

	public static final String LOGGED_CONFIG = "loggedConfig";

	@Autowired
	WrapperInterventoDAO wrapperInterventoDAO;

	@Autowired
	WrapperPfaDAO wrapperPfaDAO;


	@Autowired
	UserSessionDAO userSessionDAO;

	@Autowired
	TipoInterventoDatiTecniciDAO tipoInterventoDatiTecniciDAO;

	@Autowired
	IntervSelvicoulturaleDAO intervSelvicolturaleDAO;

	@Autowired
	GeoPlParticellaForestDAO geoPlParticellaForestDAO;

	@Autowired
	SkOkSelvDAO skOkSelvDAO;

	@Autowired
	SoggettoDAO soggettoDAO;
	
	@Autowired
	private WrapperAdpforHomeDAO wrapperAdpforHomeDAO;

	@Autowired
	SoggettoInterventoDAO soggettoInterventoDAO;
	
	@Autowired
	EventoDAO eventoDAO;
	
	@Autowired
	DocumentoAllegatoDAO documentoAllegatoDAO;

	@Override
	public Response saveDatiTehnici(Integer idIntervento, InterventoDatiTehnici intervDatiTehnici,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			wrapperInterventoDAO.saveSecondStep(intervDatiTehnici, confUtente, idIntervento);
			return Response.ok().build();
		} catch (ValidationException ve) {
			return Response.serverError().entity(ve.getMessage()).build();
		} catch (Exception e) {
			return Response.serverError().entity(e).build();
		}
	}
	
	@Override
	public Response createInterventoNEW(Integer idGeoPlPfa, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		
		try {
			
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO,
					ProceduraEnum.PIANI_FORESTALI_AZIENDALI);
			logger.info("id_config_utente: " + (confUtente==null?"isNull":confUtente.getIdConfigUtente()));
			InterventoDatiTehnici intervDatiTehnici = new InterventoDatiTehnici();
			intervDatiTehnici.setIdTipoIstanza(2);//communicaizione
			intervDatiTehnici.setTipoIntervento(new TipoInterventoDatiTecnici());
			intervDatiTehnici.setIntervSelvicolturale(new IntervSelvicolturale());
			setDefaultIntervSelvicolturale(intervDatiTehnici.getIntervSelvicolturale());
			intervDatiTehnici.setSpeciePfaIntervento(new SpeciePfaIntervento[0]);
			
			return Response.ok(wrapperInterventoDAO.saveDatiTecniciNEW(intervDatiTehnici, confUtente, idGeoPlPfa))
					.build();
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.serverError().build();
		}
		
	}
	
	private boolean setDefaultInterventoDatiTecnici(Integer idGeoPlPfa, TipoInterventoDatiTecnici tipoIntervento) {
		
		List<EventoPiano> list = eventoDAO.findEventiPianoDTOByIdGeoPlPfa(idGeoPlPfa);
		if(list == null || list.size() == 0) {
			return false;
		}else {
			tipoIntervento.setIdEventoCorelato(list.get(list.size()-1).getIdEvento());
		}
		return true;
	}
	
	private void setDefaultIntervSelvicolturale(IntervSelvicolturale intervSelv) {
		intervSelv.setFkTipoIntervento(0);
		intervSelv.setFkStatoIntervento(1);
		intervSelv.setFkFinalitaTaglio(0);
		intervSelv.setFkDestLegname(0);
		intervSelv.setFkFasciaAltimetrica(0);
		intervSelv.setFlgIntervNonPrevisto(new Integer(0).byteValue());
		intervSelv.setFkConfigIpla(IplaEnum.PFA.getTypeValue());
		intervSelv.setFlgIstanzaTaglio(new Integer(0).byteValue());
		intervSelv.setFlgPiedilista(new Integer(0).byteValue());
	}

	@Override
	public Response saveDatiTehniciNEW(Integer idGeoPlPfa, InterventoDatiTehnici intervDatiTehnici,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

			try {
				ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO,
						ProceduraEnum.PIANI_FORESTALI_AZIENDALI);
				return Response.ok(wrapperInterventoDAO.saveDatiTecniciNEW(intervDatiTehnici, confUtente, idGeoPlPfa))
						.build();
			} catch (ValidationException ve) {
				ve.printStackTrace();
				if (ErrorConstants.CAMPO_OBBLIGATORIO.equalsIgnoreCase(ve.getMessage())) {
					return BaseResponses.requiredField();
				}
				return Response.serverError().build();
			} catch (Exception e) {
				e.printStackTrace();
				return Response.serverError().build();
			}

	}

	@Override
	public Response deleteIntervento(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			wrapperInterventoDAO.deleteIntervento(idIntervento);
			return Response.ok().build();

		} catch (ValidationException ve) {
			if(ErrorConstants.BAD_INPUT_PARAMETERS.equalsIgnoreCase(ve.getMessage())) {
			return BaseResponses.badInputParameters();
			}
			return Response.serverError().build();
		}
		catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getDatiTehniciForStepTwo(Integer idIntervento, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperInterventoDAO.getDatiTechiciForStep2(idIntervento)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response getDatiTehniciForEditNEW(Integer idIntervento, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperInterventoDAO.getDatiTechiciForEditNEW(idIntervento)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getDataForShootingMirror(Integer idIntervento, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		try {
			List<ShootingMirrorDTO> list = wrapperInterventoDAO.getDataForShootingMirror(idIntervento);
			return Response.ok(list).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response updateDatiTechiciAtStep2(Integer idIntervento, InterventoDatiTehnici intervDatiTehnici,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			wrapperInterventoDAO.updateDatiTechici(idIntervento, intervDatiTehnici, confUtente);
			return Response.ok().entity("Intervento updated successfully").build();

		} catch (Exception e) {
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

	@Override
	public Response saveSecondStepOfInserisciNuovoIntervento(Integer idIntervento, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			skOkSelvDAO.updateFlgSez2(idIntervento);
			return Response.ok().build();

		} catch (Exception e) {
			return Response.serverError().build();
		}

	}

	@Override
	public Response getUtilizzatoreDataFromBaseOrAAEP(String codiceFiscale, Integer persona,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest)
			throws RecordNotUniqueException {

		if (codiceFiscale == null || codiceFiscale.length() > 16 || persona == null) {
			return BaseResponses.badInputParameters();
		}

		if (TipoUtilizzatoreRicercaEnum.PERSONA_FISICA.getValue().equals(persona)) {
			return Response.ok(wrapperInterventoDAO.getDataForSavingFisicaAtUtilizzatoreStep(codiceFiscale)).build();
		} else if (TipoUtilizzatoreRicercaEnum.PERSONA_GIURIDICA.getValue().equals(persona)) {
			return Response.ok(wrapperInterventoDAO.getDataForSavingGiurdicaAtUtilizzatoreStep(codiceFiscale)).build();
		} else
			return Response.serverError().build();

	}

	@Override
	public Response saveOrUpdateUtilizzatoreETecnicoStep(Integer idIntervento,
			InterventoUtilizzatore interventoUtilizzatore, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		
		if (soggettoInterventoDAO.getAllSoggettosByInterventoId(idIntervento).isEmpty()) {
			try {
				ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
				List<StepValidationErrors> listErrors = wrapperInterventoDAO.saveUtilizzatoreETecnico(interventoUtilizzatore, confUtente, idIntervento);
				return Response.ok(listErrors).build();

			} catch (ValidationException ve) {
				ve.printStackTrace();
				if(ErrorConstants.CAMPO_OBBLIGATORIO.equalsIgnoreCase(ve.getMessage())) {
					return BaseResponses.requiredField();
				}
				return Response.serverError().entity(ve.getMessage()).build();
			} catch (Exception e) {
				e.printStackTrace();
				return Response.serverError().build();
			}

		} else {
			try {
				ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
				List<StepValidationErrors> listErrors = wrapperInterventoDAO.updateUtilizzatoreETecnico(interventoUtilizzatore, confUtente, idIntervento);
				return Response.ok(listErrors).build();

			} catch (ValidationException ve) {
				ve.printStackTrace();
				if(ErrorConstants.CAMPO_OBBLIGATORIO.equalsIgnoreCase(ve.getMessage())) {
					return BaseResponses.requiredField();
				}
				return Response.serverError().entity(ve.getMessage()).build();
			} catch (Exception e) {
				e.printStackTrace();
				return Response.serverError().build();
			}

		}

	}

	@Override
	public Response getUtilizzatoreETecnicoForEditNEW(Integer idIntervento, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperInterventoDAO.getUtilizzatoreETecnico(idIntervento)).build();

		} catch (ValidationException ve) {
			ve.printStackTrace();
			return Response.serverError().entity(ve.getMessage()).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response saveAllegatiStepOfInserisciNuovoIntervento(Integer idIntervento, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			skOkSelvDAO.updateFlgSez4(idIntervento);
			List<FatDocumentoAllegato> listAllegati = documentoAllegatoDAO.findFatDocumentiByIdIntervento(idIntervento);
			TipoInterventoDatiTecnici tipoIntervDatiTechici = tipoInterventoDatiTecniciDAO
					.findTipoIntervDatiTehniciNEW(idIntervento);
			
			AllStepsValidator allStepsValidator = new AllStepsValidator();
			allStepsValidator.addStepValidator(new PfaAllegatiValidator(listAllegati,tipoIntervDatiTechici));
			allStepsValidator.validateAllSteps();
			return Response.ok(allStepsValidator.getStepValidatorErrors()).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response saveLocalizzaLottoNEW(Integer idIntervento, Integer idGeoPlPfa, PfaLottoLocalizza body,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			wrapperPfaDAO.updateLocalizzaLotto(body, confUtente, idIntervento, idGeoPlPfa);
			//wrapperInterventoDAO.saveLocalizzaLottoNEW(idIntervento, body, confUtente, idGeoPlPfa);
			return Response.ok().build();
		} catch (DuplicateKeyException de) {
			return Response.serverError().entity(de.getMessage()).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Override
	public Response updateLocalizzaLotto(Integer idIntervento, Integer idGeoPlPfa, PfaLottoLocalizza body, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			//wrapperInterventoDAO.updateLocalizzaLotto(body, confUtente, idIntervento, idGeoPlPfa);
			wrapperPfaDAO.updateLocalizzaLotto(body, confUtente, idIntervento, idGeoPlPfa);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getLocalizzaLottoForEditNEW(Integer idIntervento, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		try {
			return Response.ok(wrapperInterventoDAO.getCadastralParticlesForEdit(idIntervento)).build();
		} catch (DuplicateKeyException de) {
			return Response.serverError().entity(de.getMessage()).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Override
	public Response getLocalizzaLottoFromGeeco(Integer idIntervento, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			return Response.ok(wrapperInterventoDAO.getLocalizzaLottoFromGeeco(idIntervento, confUtente)).build();
		} catch (DuplicateKeyException de) {
			return Response.serverError().entity(de.getMessage()).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}


	@Override
	public Response sendParticelsToGeeco(Integer idIntervento, PfaLottoLocalizza body, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			// simulating user's drawing in geo layer, sending list of particles on which intervention polygon should be drawn
			wrapperInterventoDAO.sendParticlesToGeeco(idIntervento, body.getParticelleCatastali(), confUtente);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response retreiveDataFromGeeco(Integer idIntervento, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperInterventoDAO.retreiveDataFromGeeco(idIntervento)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Override
	public Response getDataLotto(Integer idIntervento)
			throws RecordNotUniqueException, ValidationException {
		try {
			return Response.ok(wrapperInterventoDAO.getDataLotto(idIntervento)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response updateDatiTechiciNEW(Integer idIntervento, InterventoDatiTehnici intervDatiTehnici,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			List<StepValidationErrors> errors = wrapperInterventoDAO.updateDatiTecniciNEW(idIntervento, intervDatiTehnici, confUtente, intervDatiTehnici.getIntervSelvicolturale().getIdgeoPlPfa());
			return Response.ok(errors).build();
		} catch (ValidationException ve) {
			return Response.serverError().entity(ve.getMessage()).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getErroriInterventoPfa(Integer idIntervento) throws RecordNotUniqueException, ValidationException {
		try {
			List<StepValidationErrors> errors = wrapperInterventoDAO.getErrorsInterventoPfa(idIntervento);
			return Response.ok(errors).build();
		} catch (Exception ve) {
			return Response.serverError().entity(ve.getMessage()).build();
		}
	}

	@Override
	public Response getDrawedGeometryPfaList(Integer idIntervento) {
		try {
			return Response.ok(wrapperInterventoDAO.getDrawedGeometryPfaList(idIntervento)).build();
		} catch (Exception ve) {
			return Response.serverError().entity(ve.getMessage()).build();
		}
	}
}
