/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.InterventoApi;
import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.business.be.impl.dao.IntervSelvicoulturaleDAO;
import it.csi.idf.idfapi.business.be.impl.dao.InterventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.UserSessionDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperAdpforHomeDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperInterventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperIstanzaDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperPlPfaDAO;
import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.dto.ExcelDTO;
import it.csi.idf.idfapi.dto.Intervento;
import it.csi.idf.idfapi.dto.InterventoPiano;
import it.csi.idf.idfapi.dto.InterventoRiepilogo;
import it.csi.idf.idfapi.dto.PfaLottoLocalizza;
import it.csi.idf.idfapi.dto.TipoInterventoDatiTecnici;
import it.csi.idf.idfapi.errors.ErrorConstants;
import it.csi.idf.idfapi.rest.BaseResponses;
import it.csi.idf.idfapi.util.InterventoExcelEnum;
import it.csi.idf.idfapi.util.SpringSupportedResource;
import it.csi.idf.idfapi.validation.StepValidationErrors;

public class InterventoApiServiceImpl extends SpringSupportedResource implements InterventoApi {

	static final Logger logger = Logger.getLogger(InterventoApiServiceImpl.class);
	public static final String LOGGED_CONFIG = "loggedConfig";

	@Autowired
	private InterventoDAO interventoDao;
	
	@Autowired
	private IntervSelvicoulturaleDAO intervSelvicoulturaleDAO;

	@Autowired
	private WrapperIstanzaDAO wrapperIstanzaDAO;

	@Autowired
	private WrapperInterventoDAO wrapperInterventoDAO;

	@Autowired
	private WrapperAdpforHomeDAO wrapperAdpforHomeDAO;
	
	@Autowired
	private UserSessionDAO userSessionDAO;
	
	@Autowired
	private WrapperPlPfaDAO wrapperPlPfaDAO;

	@Override
	public Response getInterventi(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(interventoDao.findAllInterventi()).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response getInterventoByID(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		try {
			return Response.ok(interventoDao.findInterventoPianoByID(idIntervento)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response saveIntervento(TipoInterventoDatiTecnici body, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		try {
			return Response.ok(interventoDao.createIntervento(body)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response updateIntervento(Intervento body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		// TODO See if this is needed
		return null;
	}

	@Override
	public Response generateExcel(ExcelDTO excel, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		HSSFWorkbook hwb = new HSSFWorkbook();
		ResponseBuilder response = null;
		List<InterventoPiano> list = interventoDao.findInterventiPianiByIdGeoPlPfa(excel.getIdGeoPfaInterventi());

		CellStyle headerCellStyle = wrapperPlPfaDAO.getCellStyle(hwb);
		wrapperPlPfaDAO.createSheetInterventi(hwb, headerCellStyle, list);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			hwb.write(baos);
		} catch (IOException e) {
			logger.fatal("An exception occurred.", e);
		}

		LocalDateTime now = LocalDateTime.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

		String formatDateTime = now.format(formatter);
		String filename = "Interventi_" + formatDateTime;
		response = Response.ok(baos.toByteArray());
		response.header("Content-disposition", "attachment; filename=" + filename + ".xls");

		return response.build();
	}

	@Override
	public Response getInterventiPianiByPlPfa(int idGeoPlPfa, int page, int limit, String sort,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(interventoDao.findInterventiPianiByIdGeoPlPfa(idGeoPlPfa, page, limit, sort)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response savePrimaPfaIntervento(PfaLottoLocalizza body, int idGeoPlPfa, HttpServletRequest httpRequest) {


		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			return Response.ok(wrapperIstanzaDAO.saveLocalizzaLotto(body, confUtente, idGeoPlPfa)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response getNumberOfNextStep(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperInterventoDAO.getNumberOfNextStep(idIntervento)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}

	}

	@Override
	public Response getDataForChiusura(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperInterventoDAO.getDataForChiusura(idIntervento)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}

	}

	@Override
	public Response completaIntervento(Integer idIntervento, InterventoRiepilogo interventoRiepilogo,
			HttpServletRequest httpRequest) {



		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			wrapperInterventoDAO.chiudiIntervento(idIntervento, interventoRiepilogo,confUtente);
			return Response.ok().build();
		} catch (ValidationException ve) {
			if(ErrorConstants.CAMPO_OBBLIGATORIO.equalsIgnoreCase(ve.getMessage())) {
				return BaseResponses.requiredField();
			}
			return Response.serverError().entity(ve.getMessage()).build();
		} catch (Exception e) {
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

	@Override
	public Response trasmettiIntervento(Integer idIntervento, Object obj, HttpServletRequest httpRequest) {
		try {
			List<StepValidationErrors> listErrors = wrapperInterventoDAO.getErrorsInterventoPfa(idIntervento);
			if(listErrors.size() > 0) {
				int errors = 0;
				for(StepValidationErrors item : listErrors) {
					errors+= item.getNoOfErrors();
				}
				if(errors > 0){
					return Response.ok(listErrors).build(); 
				}
			}
			wrapperInterventoDAO.trasmettiAPrimpa(idIntervento);
			
			return Response.ok().build();
		} catch (ValidationException ve) {
			logger.error(ve.getMessage());
			if(ErrorConstants.CAMPO_OBBLIGATORIO.equalsIgnoreCase(ve.getTesto())) {
				return BaseResponses.requiredField();
			}
			Map<String,String> res = new HashMap<String,String>();
			return Response.serverError().entity(res).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

	@Override
	public Response salvaIntervento(Integer idIntervento, InterventoRiepilogo interventoRiepilogo,
			HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			wrapperInterventoDAO.salvaIntervento(idIntervento, interventoRiepilogo,confUtente);
			return Response.ok().build();
		}  catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

	@Override
	public Response getIdPfaByIdIntervento(Integer idIntervento, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			return Response.ok(intervSelvicoulturaleDAO.getIdgeoPlPfaByIdIntervento(idIntervento)).build();
		}  catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().entity(e.getMessage()).build();
		}
	}
	

}
