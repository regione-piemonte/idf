/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import it.csi.idf.idfapi.business.be.EventoApi;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.impl.dao.EventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperEventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperPlPfaDAO;
import it.csi.idf.idfapi.dto.EventoDTO;
import it.csi.idf.idfapi.dto.EventoPiano;
import it.csi.idf.idfapi.dto.ExcelDTO;
import it.csi.idf.idfapi.dto.PlainPrimaPfaEvento;
import it.csi.idf.idfapi.dto.PlainSecondoPfaEvento;
import it.csi.idf.idfapi.errors.Error;
import it.csi.idf.idfapi.errors.ErrorConstants;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class EventoApiServiceImpl extends SpringSupportedResource implements EventoApi {

	static final Logger logger = Logger.getLogger(EventoApiServiceImpl.class);
	
	@Autowired
	private WrapperEventoDAO wrapperEventoDAO;

	@Autowired
	private EventoDAO eventoDao;

	@Autowired
	private WrapperPlPfaDAO wrapperPlPfaDAO;

	@Override
	public Response getAllEventi(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		List<EventoDTO> eventi = eventoDao.findAllEventi();

		return Response.ok(eventi).build();
	}

	@Override
	public Response getEventoByID(Integer idEvento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		EventoDTO evento;

		try {
			evento = eventoDao.findEventoById(idEvento);
			return Response.ok(evento).build();

		} catch (RecordNotFoundException | EmptyResultDataAccessException e) {
			e.printStackTrace();
			Error err = new Error();
			err.setCode(ErrorConstants.NON_TROVATO_404);
			err.setErrorMessage(ErrorConstants.NON_TROVATO);
			return Response.serverError().status(ErrorConstants.NON_TROVATO_404).entity(err).build();
		}

	}

	@Override
	public Response saveEvento(EventoDTO body, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		try {
			return Response.ok(eventoDao.createEvento(body)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response generateExcel(ExcelDTO excel, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		HSSFWorkbook hwb = new HSSFWorkbook();
		ResponseBuilder response = null;
		List<EventoPiano> list = eventoDao.findEventiPianoDTOByIdGeoPlPfa(excel.getIdGeoPfaEventi());

		CellStyle headerCellStyle = wrapperPlPfaDAO.getCellStyle(hwb);
		wrapperPlPfaDAO.createSheetEventi(hwb, headerCellStyle, list);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			hwb.write(baos);
		} catch (IOException e) {
			logger.fatal("An exception occurred.", e);
		}

		LocalDateTime now = LocalDateTime.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

		String formatDateTime = now.format(formatter);
		String filename = "eventi_" + formatDateTime;
		response = Response.ok(baos.toByteArray());
		response.header("Content-disposition", "attachment; filename=" + filename + ".xls");
		return response.build();
	}

	@Override
	public Response getEventiByPlPfa(int idGeoPlPfa, int page, int limit, String sort, HttpServletRequest httpRequest) {
		try {
			return Response.ok(eventoDao.findEventiPianoDTOByIdGeoPlPfa(idGeoPlPfa, page, limit, sort)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response savePrimaPfaEvento(PlainPrimaPfaEvento body, int idGeoPlPfa, HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperEventoDAO.savePrimaPfaEvento(body, idGeoPlPfa)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Override
	public Response getSecondoPfaEvento(int idEvento, int idGeoPlPfa, PlainSecondoPfaEvento body, HttpServletRequest httpRequest) {
		try {
			return Response.ok(eventoDao.findSecondoPfaEventoById(idEvento)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response saveSecondoPfaEvento(int idEvento, int idGeoPlPfa, PlainSecondoPfaEvento body, HttpServletRequest httpRequest) {
		try {
			wrapperEventoDAO.saveSecondoPfaEvento(body, idEvento);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getProgressiviNomiBreve(int idGeoPlPfa, HttpServletRequest httpRequest) {
		try {
			return Response.ok(eventoDao.findProgressiviNomeBreve(idGeoPlPfa)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getIdPfaByIdEvento(Integer idEvento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(eventoDao.getIdgeoPlPfaByIdEvento(idEvento)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getDrawedGeometryList(Integer idEvento) {
		try {
			return Response.ok(eventoDao.getDrawedGeometryList(idEvento)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response ricalcolaSuperficiFromGeeco(Integer idEvento) {
		wrapperEventoDAO.ricalcolaSuperficiFromGeeco(idEvento);
		return Response.ok().build();
	}
}
