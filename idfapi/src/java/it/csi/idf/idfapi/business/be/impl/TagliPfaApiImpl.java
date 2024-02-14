/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl;

import it.csi.idf.idfapi.business.be.TagliPfaApi;
import it.csi.idf.idfapi.business.be.exceptions.CustomValidator;
import it.csi.idf.idfapi.business.be.exceptions.MultiErrorException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.business.be.impl.dao.PlainSoggettoIstanzaDAO;
import it.csi.idf.idfapi.business.be.impl.dao.UserSessionDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperAdpforHomeDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperIstanzaDAO;
import it.csi.idf.idfapi.business.be.service.TagliPfaService;
import it.csi.idf.idfapi.business.be.service.TipiRichiedenteService;
import it.csi.idf.idfapi.business.be.vincolo.pojo.*;
import it.csi.idf.idfapi.dto.*;
import it.csi.idf.idfapi.rest.BaseResponses;
import it.csi.idf.idfapi.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static it.csi.idf.idfapi.util.builder.ToStringBuilder.objectToString;

public class TagliPfaApiImpl extends SpringSupportedResource implements TagliPfaApi {

	public static final String LOGGED_CONFIG = "loggedConfig";

	@Autowired
	TagliPfaService tagliPfaService;

	@Autowired
	private WrapperAdpforHomeDAO wrapperAdpforHomeDAO;

	@Autowired
	private UserSessionDAO userSessionDAO;

	@Autowired
	private PlainSoggettoIstanzaDAO plainSoggettoIstanzaDAO;

	@Autowired
	TipiRichiedenteService tipiRichiedenteService;

	@Autowired
	private WrapperIstanzaDAO wrapperIstanzaDAO;

	@Override
	public Response getNumberOfNextStep(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(tagliPfaService.getNumberOfNextStep(idIntervento)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response deleteIntervento(Integer idIntervento, HttpServletRequest httpRequest) {
		try {
			tagliPfaService.deleteIntervento(idIntervento);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getIstanzeForConfigUtente(int tipoIstanzaId, int page, int limit, String sort,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO,
					userSessionDAO);

			return Response.ok(plainSoggettoIstanzaDAO.getAllIstancesTagli(confUtente, page, limit, sort)).build();

			/*
			 * return Response.ok(plainSoggettoIstanzaDAO.getAllIstancesTagli(confUtente,
			 * confUtente.getFkTipoAccreditamento(),
			 * AmbitoIstanzaEnum.TAGLIO_IN_BOSCO.getValue(), tipoIstanzaId, page, limit,
			 * sort)).build();
			 */

		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

	@Override
	public Response getStep1(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(tagliPfaService.getStep1(idIntervento)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response saveStep1(TagliPfaStep1 body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) throws ValidationException, RecordNotUniqueException {
		ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO,
				userSessionDAO);

		try {
			validateBodyStep1(body, confUtente);
			PlainSezioneId record = tagliPfaService.saveStep1(body, confUtente);
			return Response.status(Status.CREATED).entity(record).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response updateStep1(Integer idIntervento, TagliPfaStep1 dto, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest)
			throws ValidationException, RecordNotUniqueException {
		try {

			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO,
					userSessionDAO);
			validateBodyStep1(dto, confUtente);
			logger.info("<------- updateStep1 request "+dto);
			boolean updated = tagliPfaService.updateStep1(dto, confUtente, idIntervento);
		} catch (MultiErrorException e) {
			e.printStackTrace();
			return BaseResponses.errorResponse(e);
			// Not updated with no error => 404 not found
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();
		} // Actually updated (found and updated) => 200 OKeturn
		return Response.status(Status.OK).build();
	}

	@Override
	public Response getStep2(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {

			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO,
					userSessionDAO);
			return Response.ok(tagliPfaService.getStep2(idIntervento, confUtente)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response saveStep2(TagliPfaStep2 body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) throws ValidationException, RecordNotUniqueException {
		ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO,
				userSessionDAO);

		try {
			// validateBodyStep2(body, confUtente);
			TagliPfaStep2 record = tagliPfaService.saveStep2(body, confUtente);
			return Response.status(Status.CREATED).entity(record).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response updateStep2(Integer idIntervento, TagliPfaStep2 dto, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest)
			throws ValidationException, RecordNotUniqueException {
		try {

			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO,
					userSessionDAO);
			// validateBodyStep1(dto, confUtente);

			boolean updated = tagliPfaService.updateStep2(dto, confUtente, idIntervento);
		} catch (MultiErrorException e) {
			e.printStackTrace();
			return BaseResponses.errorResponse(e);
			// Not updated with no error => 404 not found
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();
		} // Actually updated (found and updated) => 200 OKeturn
		return Response.status(Status.OK).build();
	}

	@Override
	public Response getStep3(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {

			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO,
					userSessionDAO);
			return Response.ok(tagliPfaService.getStep3(idIntervento, confUtente)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getStep4(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {

			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO,
					userSessionDAO);
			return Response.ok(tagliPfaService.getStep4(idIntervento, confUtente)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response saveStep4(Integer idIntervento, TagliPfaStep4 body, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest)
			throws ValidationException, RecordNotUniqueException {
		ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO,
				userSessionDAO);

		try {
			// validateBodyStep4(body, confUtente);
			body.getIntervento().setIdIntervento(idIntervento);
			TagliPfaStep4 record = tagliPfaService.saveStep4(body, confUtente);
			return Response.status(Status.CREATED).entity(record).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response updateStep4(Integer idIntervento, TagliPfaStep4 dto, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest)
			throws ValidationException, RecordNotUniqueException {
		try {

			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO,
					userSessionDAO);
			// validateBodyStep1(dto, confUtente);

			boolean updated = tagliPfaService.updateStep4(dto, confUtente, idIntervento);
		} catch (MultiErrorException e) {
			e.printStackTrace();
			return BaseResponses.errorResponse(e);
			// Not updated with no error => 404 not found
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();
		} // Actually updated (found and updated) => 200 OKeturn
		return Response.status(Status.OK).build();
	}

	@Override
	public Response getStep5(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {

			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO,
					userSessionDAO);
			return Response.ok(tagliPfaService.getStep5(idIntervento, confUtente)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response saveStep5(Integer idIntervento, TagliPfaStep5 body, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest)
			throws ValidationException, RecordNotUniqueException {
		ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO,
				userSessionDAO);

		try {
			// validateBodyStep4(body, confUtente);
			body.getIntervento().setIdIntervento(idIntervento);
			TagliPfaStep5 record = tagliPfaService.saveStep5(body, confUtente);
			// TagliSelvicolturaliStep5 record = body;
			return Response.status(Status.CREATED).entity(record).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response updateStep5(Integer idIntervento, TagliPfaStep5 dto, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest)
			throws ValidationException, RecordNotUniqueException {
		try {

			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO,
					userSessionDAO);
			// validateBodyStep1(dto, confUtente);

			boolean updated = tagliPfaService.updateStep5(dto, confUtente, idIntervento);
		} catch (MultiErrorException e) {
			e.printStackTrace();
			return BaseResponses.errorResponse(e);
			// Not updated with no error => 404 not found
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();
		} // Actually updated (found and updated) => 200 OKeturn
		return Response.status(Status.OK).build();
	}

	@Override
	public Response recalculateParticelleIntervento(Integer idIntervento, HttpServletRequest httpRequest) {

		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO,
					userSessionDAO);
			tagliPfaService.recalculateParticelleIntervento(idIntervento, confUtente);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		return Response.ok(idIntervento).build();
	}

	@Override
	public Response saveStep3(Integer idIntervento, TagliPfaStep3 body, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest)
			throws ValidationException, RecordNotUniqueException {
		ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO,
				userSessionDAO);

		try {
			// validateBodyStep2(body, confUtente);
			body.setIdIntervento(idIntervento);
			TagliPfaStep3 record = tagliPfaService.saveStep3(body, confUtente);
			return Response.status(Status.CREATED).entity(record).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response updateStep3(Integer idIntervento, TagliPfaStep3 dto, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest)
			throws ValidationException, RecordNotUniqueException {
		try {

			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO,
					userSessionDAO);
			// validateBodyStep1(dto, confUtente);

			boolean updated = tagliPfaService.updateStep3(dto, confUtente, idIntervento);
		} catch (MultiErrorException e) {
			e.printStackTrace();
			return BaseResponses.errorResponse(e);
			// Not updated with no error => 404 not found
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();
		} // Actually updated (found and updated) => 200 OKeturn
		return Response.status(Status.OK).build();
	}

	@Override
	public Response getAllTipiRichiedente(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			List<TipoRichiedente> result = tipiRichiedenteService.getAll();
			return Response.ok(result).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getTipoRichiedenteById(Integer idTIpo, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			TipoRichiedente result = tipiRichiedenteService.getTipoRichiedenteById(idTIpo);
			return Response.ok(result).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response checkAndInsertParticellaTagli(Integer idIntervento, PlainParticelleCatastali particellaCatasto,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO,
					userSessionDAO);
			PlainParticelleCatastali response = tagliPfaService.insertParticellaTagli(confUtente,
					particellaCatasto, idIntervento);

			if (response == null) {
				Map<String, String> error = new HashMap<String, String>();
				error.put("error", "Nessuna particella trovata con i dati inseriti!");
				return Response.ok(error).build();
			}

			return Response.ok(particellaCatasto).build();
		} catch (DuplicateKeyException de) {
			de.printStackTrace();
			return Response.serverError().entity(de.getMessage()).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getRicadenze(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO,
					userSessionDAO);

			TagliPfaStep3 response = tagliPfaService.getRicadenze(idIntervento);

			if (response == null) {
				Map<String, String> error = new HashMap<String, String>();
				error.put("error", "Errore nel recupero delle ricadenze !!");
				return Response.ok(error).build();
			}
			return Response.ok(response).build();
		} catch (DuplicateKeyException de) {
			de.printStackTrace();
			return Response.serverError().entity(de.getMessage()).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getTuttiElencaForIntervento(Integer idIntervento, HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO,
					userSessionDAO);
			boolean isGestore = confUtente.getFkProfiloUtente() == ProfiloUtenteEnum.GESTORE.getValue();
			return Response.ok(tagliPfaService.getTuttiElencaForIntervento(idIntervento, isGestore)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response invioIstanza(Integer idIntervento, ProfessionistaElencoAllegati body,
			HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO,
					userSessionDAO);
			tagliPfaService.invioIstanzaTagli(idIntervento, body, confUtente);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	public Response invioEmail(Integer idIntervento, ProfessionistaElencoAllegati body,
			HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO,
					userSessionDAO);
			tagliPfaService.invioIstanzaTagliEmail(idIntervento, body, confUtente);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getDataInvioIstanza(Integer idIntervento, HttpServletRequest httpRequest) {
		try {
			return Response.ok(tagliPfaService.getDataInvioIstanza(idIntervento)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getCeoIstanza(Integer idIntervento, HttpServletRequest httpRequest) {
		try {
			return Response.ok(tagliPfaService.getCeoIstanza(idIntervento)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getDocsGestoreByIntervento(Integer idIntervento, HttpServletRequest httpRequest) {
		try {
			return Response.ok(tagliPfaService.getDocsGestoreByIntervento(idIntervento)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getInfoDrel(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			InfoDocsPfa info = tagliPfaService.isDrelMancante(idIntervento);
			return Response.ok(info).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getDrel(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) throws RecordNotUniqueException {
		try {
			FatDocumentoAllegato drel = tagliPfaService.getDrel(idIntervento);
			return Response.ok(drel).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response creaVarianteProroga(Integer idIntervento, Boolean isVariante, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws ValidationException {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO,
					userSessionDAO);

			PlainSezioneId res = tagliPfaService.creaVarianteProroga(idIntervento, isVariante, confUtente);
			return Response.ok(res).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getInfoVarianteProroga(Integer idIntervento, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws ValidationException {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO,
					userSessionDAO);

			InfoVarianteProroga res = tagliPfaService.getInfoVarianteProroga(idIntervento);
			return Response.ok(res).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response cambioUtlizzatore(Integer idIntervento, TagliPfaStep2 dto,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest)
			throws ValidationException, RecordNotUniqueException {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO,
					userSessionDAO);
			boolean updated = tagliPfaService.cambioUtilizzatore(dto, confUtente, idIntervento);
		} catch (MultiErrorException e) {
			e.printStackTrace();
			return BaseResponses.errorResponse(e);
			// Not updated with no error => 404 not found
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();
		} // Actually updated (found and updated) => 200 OKeturn
		return Response.status(Status.OK).build();
	}

	@Override
	public Response updateTitolaritaIntervento(Integer idIntervento, Integer idConfUtente,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest)
			throws ValidationException {
		try {
			tagliPfaService.updateTitolaritaIntervento(idIntervento, idConfUtente);
			return Response.ok(wrapperIstanzaDAO.getIstanzaInviata(idIntervento)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response updateTipologiaIstanza(Integer idIntervento, TagliPfaStep5 dto,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest)
			throws ValidationException, RecordNotUniqueException {
		try {

			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO,
					userSessionDAO);
			// validateBodyStep1(dto, confUtente);
			dto.getIntervento().setIdIntervento(idIntervento);
			TagliPfaStep5 updated = tagliPfaService.updateTipologiaIstanza(dto, confUtente);
		} catch (MultiErrorException e) {
			e.printStackTrace();
			return BaseResponses.errorResponse(e);
			// Not updated with no error => 404 not found
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();
		} // Actually updated (found and updated) => 200 OKeturn
		return Response.status(Status.OK).build();
	}

	private void validateBodyStep1(TagliPfaStep1 body, ConfigUtente confUtente) {
		MultiErrorException errCheck = CustomValidator.getValidator(HttpStatus.BAD_REQUEST)
				.errorIfNULL("ConfigUtente", confUtente).errorIfNULL("TipoIstanzaId", body.getTipoIstanzaId())
				.errorIfNULL("AnnataSilvana", body.getAnnataSilvana())
				.errorIfNULL("fkTipoIntervento", body.getFkTipoIntervento())
				.errorIfNULL("fkGoverno", body.getFkGoverno())
				.errorIfNULL("fkStatoIntervento", body.getFkStatoIntervento());

		if (null != body.getCategoriaSelvicolturale()) {
			errCheck.errorIfNULL("idCategoriaSelvicolturale",
					body.getCategoriaSelvicolturale().getIdCategoriaSelvicolturale());
		} else {
			errCheck.errorIfNULL("CategoriaSelvicolturale", body.getCategoriaSelvicolturale());
		}

		if (null != body.getProprieta()) {
			errCheck.errorIfNULL("idProprieta", body.getProprieta().getIdProprieta());
		} else {
			errCheck.errorIfNULL("Proprieta", body.getProprieta());
		}

		/*
		 * if(null != body.getTrasformazSelvicolturale()) {
		 * errCheck.errorIfNULL("idIstanzaSelvicolturale",
		 * body.getTrasformazSelvicolturale().getIdIstanza()); } else {
		 * errCheck.errorIfNULL("TrasformazSelvicolturale",
		 * body.getTrasformazSelvicolturale()); }
		 */
		errCheck.go();

	}

	private void validateBodyStep2(TagliPfaStep2 body, ConfigUtente confUtente) {

		PersonaFisGiu soggetto = body.getSoggetto();

		MultiErrorException errCheck = CustomValidator.getValidator(HttpStatus.BAD_REQUEST)
				.errorIfNULL("Soggetto", soggetto).errorIfNULL("ConfigUtente", confUtente)
				.codiceIsValid("codiceFiscaleDelega", soggetto.getCodiceFiscale())
				.errorIfNULL("TipoIstanzaId", body.getTipoIstanzaId())
				.errorIfNULL("IdIntervento", body.getIdIntervento())
				.errorIfNULL("TipoRichiedente", body.getTipoRichiedenteId())
				.errorIfNULL("TipoUtilizzatore", body.getTipoUtilizzatore())
				.errorIfNULL("PersonaDatiOption", soggetto.getPersonaDatiOption());

		if (soggetto.getPersonaDatiOption().toString().endsWith("F")) {
			// Persona fisica.
			errCheck.errorIfNULL("Cognome", soggetto.getCognome()).errorIfNULL("Nome", soggetto.getNome())
					.errorIfNULL("CodiceFiscale", soggetto.getCodiceFiscale());
		} else {
			// Persona giuridica.
			errCheck.errorIfNULL("PartitaIva", soggetto.getPartitaIva())
					.errorIf("PartitaIva", soggetto.getPartitaIva().length() > 11, "Partita IVA conflict")
					.errorIfNULL("Pec", soggetto.getPec())
					.errorIf("body.getPec().length() > 50", soggetto.getPec().length() > 50, "Pec length() > 50")
					.errorIfNULL("Denominazione", soggetto.getDenominazione())
					.errorIf("Denominazione", soggetto.getDenominazione().length() > 200, "Denominazione length > 200");
		}

		errCheck.errorIfNULL("Indirizzo", soggetto.getIndirizzo())
				.errorIfNULL("NrTelefonico", soggetto.getNrTelefonico()).errorIfNULL("Email", soggetto.geteMail())
				.errorIfNULL("Civico", soggetto.getCivico()).errorIfNULL("Cap", soggetto.getCap())
				.errorIfNULL("Comune", soggetto.getComune())
				.errorIf("Indirizzo", soggetto.getIndirizzo().length() > 50, "Indirizzo length >50")
				.errorIf("Civico", soggetto.getCivico().length() > 10, "Civico length > 10")
				.errorIf("Cap", soggetto.getCap().length() != 5, "Cap length > 10")
				.errorIf("NrTelefonico", soggetto.getNrTelefonico().length() > 20, "NrTelefonico length > 20")
				.errorIf("Email", soggetto.geteMail().length() > 30, "Email length > 30").go();
	}

}
