/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import it.csi.idf.idfapi.business.be.service.MailService;
import it.csi.idf.idfapi.business.be.service.TagliSelvicolturaliService;
import it.csi.idf.idfapi.dto.*;
import it.csi.idf.idfapi.util.*;
import it.csi.idf.idfapi.util.mail.MailEnum;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.IstanzaForestAPI;
import it.csi.idf.idfapi.business.be.exceptions.ServiceException;
import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.business.be.impl.dao.ConfigUtenteDAO;
import it.csi.idf.idfapi.business.be.impl.dao.InterventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.IstanzaForestDAO;
import it.csi.idf.idfapi.business.be.impl.dao.PlainSoggettoIstanzaDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SkOkTrasfDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SoggettoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.TrasformazioniXlsDAO;
import it.csi.idf.idfapi.business.be.impl.dao.UserSessionDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperAdpforHomeDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperIstanzaDAO;
import it.csi.idf.idfapi.business.be.service.helper.PrimpaforservServiceHelper;
import it.csi.idf.idfapi.business.be.vincolo.pojo.AutorizzaVincolo;
import it.csi.idf.idfapi.business.be.vincolo.pojo.VincoloXls;
import it.csi.idf.idfapi.errors.ErrorConstants;
import it.csi.idf.idfapi.util.service.integration.primpaforserv.Istanza;

public class IstanzaForestAPIServiceImpl extends SpringSupportedResource implements IstanzaForestAPI {
	public static final Logger logger = Logger.getLogger(IstanzaForestAPIServiceImpl.class);
	
	public static final String LOGGED_CONFIG = "confUtente";
	
	@Autowired
	WrapperAdpforHomeDAO wrapperAdpforHomeDAO;
	
	@Autowired
	UserSessionDAO userSessionDAO;

	@Autowired
	private SoggettoDAO soggettoDAO;
	
	@Autowired
	private ConfigUtenteDAO configUtenteDAO;
	
	@Autowired
	private WrapperIstanzaDAO wrapperIstanzaDAO;
	
	@Autowired
	private IstanzaForestDAO istanzaForestDAO;

	@Autowired
	private PlainSoggettoIstanzaDAO plainSoggettoIstanzaDAO;
	
	@Autowired
	private InterventoDAO interventoDAO;
	
	@Autowired
	private SkOkTrasfDAO skOkTrasfDAO;
	
	@Autowired
	TrasformazioniXlsDAO trasformazioniXlsDAO;
	
	@Autowired
	PrimpaforservServiceHelper primpaforservServiceHelper;

	@Autowired
	TagliSelvicolturaliService tagliSelvicolturaliService;

	@Autowired
	MailService mailService;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public Response getNumberOfNextStep(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperIstanzaDAO.getNumberOfNextStep(idIntervento)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getIstanzeForConfigUtente(int page, int limit, String sort, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			//ambito istanza: Trasformazione del bosco "1"
			
			return Response.ok(plainSoggettoIstanzaDAO.getAllIstances(confUtente,confUtente.getFkTipoAccreditamento(), 
					AmbitoIstanzaEnum.TRASFORMAZIONE_DEL_BOSCO.getValue(),
					page, limit, sort)).build();

		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

	@Override
	public Response getPrimaSezione(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			
			return Response.ok(wrapperIstanzaDAO.getPrimaSezione(idIntervento)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response savePrimaSezione(PlainPrimaSezione body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		
		try {
			if (!ValidationUtil.isCodiceOk(body.getCodiceFiscale())) {
				throw new ValidationException(ErrorConstants.FISCAL_CODE_ERROR + " delega");
			}
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);

			return Response.ok(wrapperIstanzaDAO.savePrimaSezione(body, confUtente)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getSecondoSezione(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperIstanzaDAO.getSecondoSezione(idIntervento)).build();
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("<----- getSecondoSezione "+ e);
			return Response.serverError().build();
		}
	}

	@Override
	public Response saveSecondoSezione(Integer idIntervento, PlainSecondoSezione body, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {

			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			wrapperIstanzaDAO.saveSecondoSezione(body, confUtente, idIntervento);

			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response updateSecondoSezione(Integer idIntervento, PlainSecondoSezione body,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);

			wrapperIstanzaDAO.saveSecondoSezione(body, confUtente, idIntervento);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("<----- updateSecondoSezione "+ e);
			return Response.serverError().build();
		}
	}

	@Override
	public Response updatePrimaSezione(Integer idIntervento, PlainPrimaSezione body, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);

			wrapperIstanzaDAO.updatePrimaSezione(body,confUtente, idIntervento);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getTerzaSezione(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperIstanzaDAO.getTerzaSezione(idIntervento)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response saveTerzaSezione(Integer idIntervento, PlainTerzaSezione body, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);

			return Response
					.ok(wrapperIstanzaDAO.saveTerzaSezione(idIntervento, confUtente, body))
					.build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getQuartaSezione(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperIstanzaDAO.getQuartaSezione(idIntervento)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response saveQuartaSezione(Integer idIntervento, CompensationForesteDTO compensationForesteDTO,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			wrapperIstanzaDAO.saveQuartaSezione(idIntervento, compensationForesteDTO);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getQuintaSezione(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperIstanzaDAO.getQuintaSezione(idIntervento)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response saveQuintaSezione(Integer idIntervento, PlainQuintaSezione quintaSezione, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			logger.info("saveQuintaSezione: start");
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			quintaSezione.getSoggettoProfess().setFkConfigUtente(confUtente.getIdConfigUtente());
			wrapperIstanzaDAO.saveQuintaSezione(idIntervento, quintaSezione);
			logger.info("saveQuintaSezione: end");
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getSestoSezione(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperIstanzaDAO.getSestoSezione(idIntervento)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response saveSestoSezione(Integer idIntervento, PlainSestoSezione plainSestoSezione,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, 
					wrapperAdpforHomeDAO, userSessionDAO);

			wrapperIstanzaDAO.saveSestoSezione(idIntervento, confUtente,
					plainSestoSezione);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	private Map<String, String> getQueryParams(UriInfo info, TipoIstanzaEnum tipoIstanza) {
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("tipoIstanza", "" + tipoIstanza.getTypeValue());
		queryParams.put("numIstanza", info.getQueryParameters().getFirst("numIstanza"));
		queryParams.put("statoIstanza", info.getQueryParameters().getFirst("statoIstanza"));
		queryParams.put("annoIstanza", info.getQueryParameters().getFirst("annoIstanza"));
		queryParams.put("dataPresDa", info.getQueryParameters().getFirst("dataPresDa"));
		queryParams.put("dataPresA", info.getQueryParameters().getFirst("dataPresA"));
		queryParams.put("prov", info.getQueryParameters().getFirst("prov"));
		queryParams.put("comune", info.getQueryParameters().getFirst("comune"));
		
		String sezione = info.getQueryParameters().getFirst("sezione");
		queryParams.put("sezione", "".equals(sezione)?"_":sezione);
		queryParams.put("foglio", info.getQueryParameters().getFirst("foglio"));
		queryParams.put("particella", info.getQueryParameters().getFirst("particella"));
		queryParams.put("pf", info.getQueryParameters().getFirst("pf"));
		queryParams.put("pg", info.getQueryParameters().getFirst("pg"));
		
		queryParams.put("aapp", info.getQueryParameters().getFirst("aapp"));			
		queryParams.put("rn2k", info.getQueryParameters().getFirst("rn2k"));			
		queryParams.put("popSeme", info.getQueryParameters().getFirst("popSeme"));
		
		if(tipoIstanza == TipoIstanzaEnum.DICHIARAZIONE_SOSTITUTIVA) {
			queryParams.put("prof", info.getQueryParameters().getFirst("prof"));
			queryParams.put("vincIdro", info.getQueryParameters().getFirst("vincIdro"));
			queryParams.put("compNec", info.getQueryParameters().getFirst("compNec"));
			queryParams.put("compO1", info.getQueryParameters().getFirst("compO1"));
			queryParams.put("compO2", info.getQueryParameters().getFirst("compO2"));
			queryParams.put("compO3", info.getQueryParameters().getFirst("compO3"));
			queryParams.put("compO4", info.getQueryParameters().getFirst("compO4"));
			queryParams.put("compO5", info.getQueryParameters().getFirst("compO5"));
			queryParams.put("govForm", info.getQueryParameters().getFirst("govForm"));
			queryParams.put("catFor", info.getQueryParameters().getFirst("catFor"));
			queryParams.put("ubicazione", info.getQueryParameters().getFirst("ubicazione"));
			queryParams.put("tipTrasf", info.getQueryParameters().getFirst("tipTrasf"));
			queryParams.put("calcEconDa", info.getQueryParameters().getFirst("calcEconDa"));
			queryParams.put("calcEconA", info.getQueryParameters().getFirst("calcEconA"));
		}else if(tipoIstanza == TipoIstanzaEnum.VINCOLO_AUTORIZZAZIONE_COMUNALE 
				|| tipoIstanza == TipoIstanzaEnum.VINCOLO_AUTORIZZAZIONE_REGIONALE) {
			queryParams.put("padreVariante", info.getQueryParameters().getFirst("padreVariante"));
			queryParams.put("padreProroga", info.getQueryParameters().getFirst("padreProroga"));
			queryParams.put("annoAutorizzazione", info.getQueryParameters().getFirst("annoAutorizzazione"));
			queryParams.put("numAutorizzazione", info.getQueryParameters().getFirst("numAutorizzazione"));
			queryParams.put("dataScadenzaProvDa", info.getQueryParameters().getFirst("dataScadenzaProvDa"));
			queryParams.put("dataScadenzaProvA", info.getQueryParameters().getFirst("dataScadenzaProvA"));
			queryParams.put("dataConclusioneIntervDa", info.getQueryParameters().getFirst("dataConclusioneIntervDa"));
			queryParams.put("dataConclusioneIntervA", info.getQueryParameters().getFirst("dataConclusioneIntervA"));
			queryParams.put("varianti", info.getQueryParameters().getFirst("varianti"));
			queryParams.put("proroghe", info.getQueryParameters().getFirst("proroghe"));
			queryParams.put("compensazioneVincolo", info.getQueryParameters().getFirst("compensazioneVincolo"));
			queryParams.put("cauzione", info.getQueryParameters().getFirst("cauzione"));
		} else if (tipoIstanza == TipoIstanzaEnum.COMUNICAZIONE || tipoIstanza == TipoIstanzaEnum.AUTORIZZAZIONE) {
			queryParams.put("tipoIstTaglio", info.getQueryParameters().getFirst("tipoIstTaglio"));
			queryParams.put("descr", info.getQueryParameters().getFirst("descr"));
			queryParams.put("annoAutorizzazione", info.getQueryParameters().getFirst("annoAutorizzazione"));
			queryParams.put("numAutorizzazione", info.getQueryParameters().getFirst("numAutorizzazione"));
			queryParams.put("dataScadenzaProvDa", info.getQueryParameters().getFirst("dataScadenzaProvDa"));
			queryParams.put("dataScadenzaProvA", info.getQueryParameters().getFirst("dataScadenzaProvA"));
			queryParams.put("dataConclusioneIntervDa", info.getQueryParameters().getFirst("dataConclusioneIntervDa"));
			queryParams.put("dataConclusioneIntervA", info.getQueryParameters().getFirst("dataConclusioneIntervA"));
			queryParams.put("catSelv", info.getQueryParameters().getFirst("catSelv"));
			queryParams.put("prop", info.getQueryParameters().getFirst("prop"));
			queryParams.put("govForm", info.getQueryParameters().getFirst("govForm"));
			queryParams.put("tipoInt", info.getQueryParameters().getFirst("tipoInt"));
			queryParams.put("statoInt", info.getQueryParameters().getFirst("statoInt"));
			queryParams.put("tipoRich", info.getQueryParameters().getFirst("tipoRich"));
			queryParams.put("upf", info.getQueryParameters().getFirst("upf"));
			queryParams.put("upg", info.getQueryParameters().getFirst("upg"));
			queryParams.put("und", info.getQueryParameters().getFirst("und"));
			queryParams.put("prof", info.getQueryParameters().getFirst("prof"));
			queryParams.put("pfa", info.getQueryParameters().getFirst("pfa"));
			queryParams.put("catFor", info.getQueryParameters().getFirst("catFor"));
			queryParams.put("varianti", info.getQueryParameters().getFirst("varianti"));
			queryParams.put("proroghe", info.getQueryParameters().getFirst("proroghe"));
			queryParams.put("intComp", info.getQueryParameters().getFirst("intComp"));
			queryParams.put("sport", info.getQueryParameters().getFirst("sport"));
			queryParams.put("padreVariante", info.getQueryParameters().getFirst("padreVariante"));
			queryParams.put("padreProroga", info.getQueryParameters().getFirst("padreProroga"));
		}
		
		queryParams.put("page", info.getQueryParameters().getFirst("page"));
		queryParams.put("limit", info.getQueryParameters().getFirst("limit"));
		queryParams.put("sort", info.getQueryParameters().getFirst("sort"));
		
		return queryParams;
	}

	@Override
	public Response backOfficeSearch(UriInfo info, HttpServletRequest httpRequest) {
		try {
			
			
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			String tipoIstanzaStr = info.getQueryParameters().getFirst("tipoIstanza");
			TipoIstanzaEnum tipoIstanza = TipoIstanzaEnum.getEnum(Integer.parseInt(tipoIstanzaStr));
			Map<String, String> queryParams = getQueryParams(info, tipoIstanza);

			if (tipoIstanza == TipoIstanzaEnum.DICHIARAZIONE_SOSTITUTIVA) {
				return Response.ok(wrapperIstanzaDAO.backOfficeSearch(queryParams, confUtente)).build();
			} else if (	tipoIstanza == TipoIstanzaEnum.VINCOLO_AUTORIZZAZIONE_REGIONALE ||
						tipoIstanza == TipoIstanzaEnum.VINCOLO_AUTORIZZAZIONE_COMUNALE ) {
				return Response.ok(wrapperIstanzaDAO.backOfficeVincoloSearch(queryParams, confUtente)).build();
			} else if (tipoIstanza == TipoIstanzaEnum.AUTORIZZAZIONE ||
						tipoIstanza == TipoIstanzaEnum.COMUNICAZIONE) {
				// tagli selvicolturali
				return Response.ok(wrapperIstanzaDAO.backOfficeTagliSearch(queryParams, confUtente)).build();
			}

			return Response.serverError().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Override
	public Response backOfficeExcel(UriInfo info, HttpServletRequest httpRequest) {
		try {
			
			String tipoIstanzaStr = info.getQueryParameters().getFirst("tipoIstanza");
			TipoIstanzaEnum tipoIstanza = TipoIstanzaEnum.getEnum(Integer.parseInt(tipoIstanzaStr));
			Map<String, String> queryParams = getQueryParams(info, tipoIstanza);
			
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			
			HSSFWorkbook hwb = new HSSFWorkbook();
			ResponseBuilder response = null;

			// Create a Font for styling header cells
			Font headerFont = hwb.createFont();

			headerFont.setFontHeightInPoints((short) 16);
			headerFont.setColor(IndexedColors.DARK_BLUE.getIndex());

			// Create a CellStyle with the font
			CellStyle headerCellStyle = hwb.createCellStyle();
			headerCellStyle.setFont(headerFont);
			headerCellStyle.setWrapText(true);
			
			List<Integer> ids = new ArrayList<Integer>();
			if(tipoIstanza == TipoIstanzaEnum.DICHIARAZIONE_SOSTITUTIVA) {
				PaginatedList<BOSearchResult> result = wrapperIstanzaDAO.backOfficeSearch(queryParams, confUtente);
				List<BOSearchResult> listIstanze = result.getContent();

				for (BOSearchResult item : listIstanze) {
					ids.add(item.getNumeroIstanza());
				}

				// Create a Sheet interventi
				List<TrasformazioniXls> listTrafIstanze = trasformazioniXlsDAO.getTraformazinoniXlsByIstanze(ids);
				Sheet sheet = hwb.createSheet("Interventi");
				fillSheetTrasformazioni(sheet, headerCellStyle, listTrafIstanze);

				// Create a Sheet interCatasto

				List<TrasformazCatastoXls> listTrafCatasto = trasformazioniXlsDAO.getTraformazCatastoXlsByIstanze(ids);
				sheet = hwb.createSheet("Catasto");
				fillSheetTrasformCatasto(sheet, headerCellStyle, listTrafCatasto);
			} else if (tipoIstanza == TipoIstanzaEnum.COMUNICAZIONE || tipoIstanza == TipoIstanzaEnum.AUTORIZZAZIONE) {

				// interventi selvicolturali

				PaginatedList<PlainSoggettoIstanzaTagli> result = wrapperIstanzaDAO.backOfficeTagliSearch(queryParams, confUtente);
				List<PlainSoggettoIstanzaTagli> listIstanze = result.getContent();

				for (PlainSoggettoIstanzaTagli item : listIstanze) {
					ids.add(item.getNrIstanzaForestale());
				}

				List<TagliXls> listTagliIstanze = trasformazioniXlsDAO.getTagliXlsByIstanze(ids);
				Sheet sheet = hwb.createSheet("Interventi Selvicolturali");
				fillSheetTagli(sheet, headerCellStyle, listTagliIstanze);

				List<TagliSpecieXls> listTagliSpecie = trasformazioniXlsDAO.getTagliSpecieXlsByIstanze(ids);
				sheet = hwb.createSheet("Specie");
				fillSheetTagliSpecie(sheet, headerCellStyle, listTagliSpecie);

				List<TagliCatastoXls> listTagliCatasto = trasformazioniXlsDAO.getTagliCatastoXlsByIstanze(ids);
				sheet = hwb.createSheet("Catasto");
				fillSheetTagliCatasto(sheet, headerCellStyle, listTagliCatasto);


			} else {
				PaginatedList<PlainSoggettoIstanzaVincolo> result = wrapperIstanzaDAO.backOfficeVincoloSearch(queryParams, confUtente);
				List<PlainSoggettoIstanzaVincolo> listIstanze = result.getContent();

				for(PlainSoggettoIstanzaVincolo item : listIstanze) {
					ids.add(item.getNrIstanzaForestale());
				}

				// Create a Sheet vincolo
				List<VincoloXls> listTrafIstanze = trasformazioniXlsDAO.getVincoloXlsByIstanze(ids);
				Sheet sheet = hwb.createSheet("Vincolo");
				fillSheetVincolo(sheet, headerCellStyle, listTrafIstanze);

				// Create a Sheet interCatasto

				List<TrasformazCatastoXls> listTrafCatasto = trasformazioniXlsDAO.getVincoloCatastoXlsByIstanze(ids);
				sheet = hwb.createSheet("Catasto");
				fillSheetTrasformCatasto(sheet, headerCellStyle, listTrafCatasto);

			}
			
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			try {
				hwb.write(baos);
			} catch (IOException e) {
				logger.fatal("An exception occurred.", e);
			}

			LocalDateTime now = LocalDateTime.now();

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
			String formatDateTime = now.format(formatter);

			String filename = (tipoIstanza == TipoIstanzaEnum.DICHIARAZIONE_SOSTITUTIVA?
					"Trasformazioni_" :
					(tipoIstanza == TipoIstanzaEnum.COMUNICAZIONE || tipoIstanza == TipoIstanzaEnum.AUTORIZZAZIONE) ?
					"InterventiSelvicolturali_" :
					"VincoloIdrogeologico_" ) + formatDateTime;
			response = Response.ok(baos.toByteArray());
			response.header("Content-disposition", "attachment; filename=" + filename + ".xls");

			return response.build();

		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getTuttiElencaForIntervento(Integer idIntervento, HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			boolean isGestore = confUtente.getFkProfiloUtente() == ProfiloUtenteEnum.GESTORE.getValue();
			return Response.ok(wrapperIstanzaDAO.getTuttiElencaForIntervento(idIntervento, isGestore)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	public Response getDocsGestoreByIntervento(Integer idIntervento, HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperIstanzaDAO.getDocsGestoreByIntervento(idIntervento)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getDataInvioIstanza(Integer idIntervento, HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperIstanzaDAO.getDataInvioIstanza(idIntervento)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response invioIstanza(Integer idIntervento, ProfessionistaElencoAllegati body,
			HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);

			wrapperIstanzaDAO.invioIstanza(idIntervento, body,
					soggettoDAO.findSoggettoById(confUtente.getFkSoggetto()),confUtente);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}


	@Override
	public Response updateIstanzaVerificata(Integer idIntervento, HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);

			wrapperIstanzaDAO.updateIstanzaVerificata(idIntervento,
					soggettoDAO.findSoggettoById(confUtente.getFkSoggetto()),confUtente);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}


	@Override
	public Response updateIstanzaRifiutata(Integer idIntervento, String motivazioneRifiuto, HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			logger.info("updateIstanzaRifiutata - idIntervento: " + idIntervento + 
					" - motivazioneRifiuto: " + motivazioneRifiuto);
			wrapperIstanzaDAO.updateIstanzaRifiutata(idIntervento, motivazioneRifiuto,
					soggettoDAO.findSoggettoById(confUtente.getFkSoggetto()),confUtente);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Override
	public Response updateIstanzaVincoloAutorizzata(AutorizzaVincolo body, HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);

			wrapperIstanzaDAO.updateIstanzaVincoloAutorizzata(body,confUtente);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response updateIstanzaTagliAutorizzata(AutorizzaIstanza body, HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			tagliSelvicolturaliService.updateIstanzaAutorizzata(body,confUtente);
			//TODO: send mail to....
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}


	@Override
	public Response updateIstanzaTagliVerifica(AutorizzaIstanza body, HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			tagliSelvicolturaliService.updateIstanzaVerificata(body,confUtente);

			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}


	@Override
	public Response updateIstanzaTagliAnnulla(AnnullaIstanza body, HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			tagliSelvicolturaliService.updateIstanzaTo(
					body.getIdIntervento(),
					body.getMotivazione(),
					confUtente.getIdConfigUtente(),
					InstanceStateEnum.ANNULLATA);

			//TODO: send mail to GESTORE....
			mailService.sendMailTagliByIdIntervento(body.getIdIntervento(), MailEnum.ANNULLAMENTO_ISTANZA_TAGLI);



			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response updateDataFineIntervento(AutorizzaVincolo body, HttpServletRequest httpRequest) {
		try {
			wrapperIstanzaDAO.updateDataFineIntervento(body);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response updateDataFineInterventoTagli(AutorizzaIstanza body, HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			tagliSelvicolturaliService.updateDataFineInterventoTagli(body,confUtente);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getUtenteForIstanza(Integer idIntervento, HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperIstanzaDAO.getUtenteForIstanza(idIntervento)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response getCeoIstanza(Integer idIntervento, HttpServletRequest httpRequest) {
		
		try {
			return Response.ok(wrapperIstanzaDAO.getCeoIstanza(idIntervento)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response associareDocumenti(Integer idIntervento, ProfessionistaElencoAllegati body,
			HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);

			wrapperIstanzaDAO.associareDocumenti(idIntervento, body,confUtente);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getIstanzaInviata(Integer idIntervento, HttpServletRequest httpRequest) {
		return Response.ok(wrapperIstanzaDAO.getIstanzaInviata(idIntervento)).build();
	}

	@Override
	public Response recalculateParticelleIntervento(Integer idIntervento, HttpServletRequest httpRequest) {
		
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			TSoggetto soggetto = soggettoDAO.findSoggettoById(confUtente.getFkSoggetto());
			wrapperIstanzaDAO.recalculateParticelleIntervento(idIntervento, soggetto.getCodiceFiscale(), confUtente);
			skOkTrasfDAO.deleteByIdIntervento(idIntervento);
			skOkTrasfDAO.insertFlgSez1(idIntervento);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("<------ recalculateParticelleIntervento "+e);
			return Response.serverError().build();
		}
		return Response.ok(idIntervento).build();
	}

	@Override
	public Response deleteIntervento(Integer idIntervento, HttpServletRequest httpRequest) {
		wrapperIstanzaDAO.deleteIntervento(idIntervento);
		logger.info("ok ***************************************");
		return Response.ok().build();
	}
	
	private String formatDate(Date d) {
		if(d == null) {
			return "";
		}
		return sdf.format(d);
	}

	@Override
	public Response getDettaglioIstanzaPrimpafor(Integer idIstanza, HttpServletRequest httpRequest) {
		logger.info("[IstanzaForestAPIServiceImpl::getDettaglioIstanzaPrimpafor] BEGIN");
		try {
			Istanza result = this.primpaforservServiceHelper.getDettaglioIstanzaPrimpa(idIstanza);
			return Response.ok(result).build();
		} catch (ServiceException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		
	}




	private void fillSheetTagliCatasto(Sheet sheet, CellStyle headerCellStyle, List<TagliCatastoXls> listTagliCatasto) {

		String[] columns = new String[] {"numeroIstanza","anno","comune","sezione","foglio","particella"};
		buildHeaderRow(sheet, headerCellStyle, columns);


		int rowNum = 1;
		if (listTagliCatasto != null) {
			for (TagliCatastoXls item : listTagliCatasto) {
				int i = 0;
				Row row = sheet.createRow(rowNum++);
				row.createCell(i++).setCellValue(item.getNrIstanzaForestale() == null ? "" : ""+item.getNrIstanzaForestale());
				row.createCell(i++).setCellValue(item.getAnno() == null ? "" : ""+item.getAnno());
				row.createCell(i++).setCellValue(item.getComune() == null ? "" : ""+item.getComune());
				row.createCell(i++).setCellValue(item.getSezione() == null ? "" : ""+item.getSezione());
				row.createCell(i++).setCellValue(item.getFoglio() == null ? "" : ""+item.getFoglio());
				row.createCell(i++).setCellValue(item.getParticella() == null ? "" : ""+item.getParticella());
			}
		}
		// Resize all columns to fit the content size
		autosizeColumns(sheet, columns);
	}

	private void fillSheetTagliSpecie(Sheet sheet, CellStyle headerCellStyle, List<TagliSpecieXls> listTagliSpecie) {

		String[] columns = new String[] {"nrIstanzaForestale","latino","volgare","flgSpeciePriorita","numeroPiante","volumeSpecie",
				"descrUdm","legnaOperaAutoconsumoPerc","legnaOperaCommercioPerc","legnaArdereAutoconsumoPerc",
				"legnaArdereCommercioPerc","legnaUsoEnerAutoconsumoPerc","legnaUsoEnerCommercioPerc",
				"nessunUtilizzoAutoconsumoPerc","nessunUtilizzoCommercioPerc"};
		buildHeaderRow(sheet, headerCellStyle, columns);

		int rowNum = 1;
		if (listTagliSpecie != null) {
			for (TagliSpecieXls item : listTagliSpecie) {
				int i = 0;
				Row row = sheet.createRow(rowNum++);
				row.createCell(i++).setCellValue(item.getNrIstanzaForestale() == null ? "" : ""+item.getNrIstanzaForestale());
				row.createCell(i++).setCellValue(item.getLatino() == null ? "" : ""+item.getLatino());
				row.createCell(i++).setCellValue(item.getVolgare() == null ? "" : ""+item.getVolgare());
				row.createCell(i++).setCellValue(item.getFlgSpeciePriorita() == null ? "" : ""+item.getFlgSpeciePriorita());
				row.createCell(i++).setCellValue(item.getNumeroPiante() == null ? "" : ""+item.getNumeroPiante());
				row.createCell(i++).setCellValue(item.getVolumeSpecie() == null?"":""+item.getVolumeSpecie().floatValue() );
				row.createCell(i++).setCellValue(item.getDescrUdm() == null ? "" : ""+item.getDescrUdm());
				row.createCell(i++).setCellValue(item.getLegnaOperaAutoconsumoPerc() == null?"":""+item.getLegnaOperaAutoconsumoPerc().floatValue() );
				row.createCell(i++).setCellValue(item.getLegnaArdereCommercioPerc() == null?"":""+item.getLegnaArdereCommercioPerc().floatValue() );
				row.createCell(i++).setCellValue(item.getLegnaArdereAutoconsumoPerc() == null?"":""+item.getLegnaArdereAutoconsumoPerc().floatValue() );
				row.createCell(i++).setCellValue(item.getLegnaArdereCommercioPerc() == null?"":""+item.getLegnaArdereCommercioPerc().floatValue() );
				row.createCell(i++).setCellValue(item.getLegnaUsoEnerAutoconsumoPerc() == null?"":""+item.getLegnaUsoEnerAutoconsumoPerc().floatValue() );
				row.createCell(i++).setCellValue(item.getLegnaUsoEnerCommercioPerc() == null?"":""+item.getLegnaUsoEnerCommercioPerc().floatValue() );
				row.createCell(i++).setCellValue(item.getNessunUtilizzoAutoconsumoPerc() == null?"":""+item.getNessunUtilizzoAutoconsumoPerc().floatValue() );
				row.createCell(i++).setCellValue(item.getNessunUtilizzoCommercioPerc() == null?"":""+item.getNessunUtilizzoCommercioPerc().floatValue() );
			}
		}
		// Resize all columns to fit the content size

		autosizeColumns(sheet, columns);
	}

	private void fillSheetTagli(Sheet sheet, CellStyle headerCellStyle, List<TagliXls> listTagliIstanze) {

		String[] columns = new String[] {"nr_istanza","anno_istanza","tipo_istanza","stato_istanza","comune","categ_intervento",
				"proprieta_bosco","annata_silvana","data_inizio","stato_intervento","intervento_compensativo",
				"tipo_richiedente","richiedente","nr_albo_forestale","utilizzatore","tecnico_forestale",
				"superficie_interessata_ha","ricadenza_aapp","ricadenza_rn2000","ricadenza_popseme",
				"categorie_forestali","pfa","fascia_altimetrica","descrizione_intervento","governo_principale",
				"tipo_intervento_principale","superficie_int1_ha","governo_secondario","tipo_intervento_secondario",
				"superficie_int2_ha","usoviab","note_esbosco","volume_specie","descr_udm","volume_ramaglia_m3",
				"data_inserimento","data_invio","nr_protocollo","data_protocollo",
				"data_verifica","nr_determina_aut","data_termine_aut","compilatore"};

		buildHeaderRow(sheet, headerCellStyle, columns);

		int rowNum = 1;
		if (listTagliIstanze != null) {
			for (TagliXls item : listTagliIstanze) {
				int i = 0;
				Row row = sheet.createRow(rowNum++);
				row.createCell(i++).setCellValue(item.getNrIstanzaForestale() == null ? "" : ""+item.getNrIstanzaForestale());
				row.createCell(i++).setCellValue(item.getAnnoIstanza() == null ? "" : ""+item.getAnnoIstanza());
				row.createCell(i++).setCellValue(item.getTipoIstanza() == null ? "" : ""+item.getTipoIstanza());
				row.createCell(i++).setCellValue(item.getStatoIstanza() == null ? "" : ""+item.getStatoIstanza());
				row.createCell(i++).setCellValue(item.getStringComune() == null ? "" : ""+item.getStringComune());
				row.createCell(i++).setCellValue(item.getCategIntervento() == null ? "" : ""+item.getCategIntervento());
				row.createCell(i++).setCellValue(item.getProprietaBosco() == null ? "" : ""+item.getProprietaBosco());
				row.createCell(i++).setCellValue(item.getAnnataSilvana() == null ? "" : ""+item.getAnnataSilvana());
				row.createCell(i++).setCellValue(item.getDataInizio() == null ? "" : ConvertUtil.convertToString(item.getDataInizio()));
				row.createCell(i++).setCellValue(item.getStatoIntervento() == null ? "" : ""+item.getStatoIntervento());
				row.createCell(i++).setCellValue(item.getInterventoCompensativo() == null ? "" : ""+item.getInterventoCompensativo());
				row.createCell(i++).setCellValue(item.getTipoRichiedente() == null ? "" : ""+item.getTipoRichiedente());
				row.createCell(i++).setCellValue(item.getRichiedente() == null ? "" : ""+item.getRichiedente());
				row.createCell(i++).setCellValue(item.getNrAlboForestale() == null ? "" : ""+item.getNrAlboForestale());
				row.createCell(i++).setCellValue(item.getUtilizzatore() == null ? "" : ""+item.getUtilizzatore());
				row.createCell(i++).setCellValue(item.getTecnicoForestale() == null ? "" : ""+item.getTecnicoForestale());
				row.createCell(i++).setCellValue(item.getSuperficieInteressataHa() == null?"":""+item.getSuperficieInteressataHa().doubleValue());
				row.createCell(i++).setCellValue(item.getRicadenzaAapp() == null ? "" : ""+item.getRicadenzaAapp());
				row.createCell(i++).setCellValue(item.getRicadenzaRn2000() == null ? "" : ""+item.getRicadenzaRn2000());
				row.createCell(i++).setCellValue(item.getRicadenzaPopseme() == null ? "" : ""+item.getRicadenzaPopseme());
				row.createCell(i++).setCellValue(item.getCategorieForestali() == null ? "" : ""+item.getCategorieForestali());
				row.createCell(i++).setCellValue(item.getPfa() == null ? "" : ""+item.getPfa());
				row.createCell(i++).setCellValue(item.getFasciaAltimetrica() == null ? "" : ""+item.getFasciaAltimetrica());
				row.createCell(i++).setCellValue(item.getDescrizioneIntervento() == null ? "" : ""+item.getDescrizioneIntervento());
				row.createCell(i++).setCellValue(item.getGovernoPrincipale() == null ? "" : ""+item.getGovernoPrincipale());
				row.createCell(i++).setCellValue(item.getTipoInterventoPrincipale() == null ? "" : ""+item.getTipoInterventoPrincipale());
				row.createCell(i++).setCellValue(item.getSuperficieInt1Ha() == null?"":""+item.getSuperficieInt1Ha().doubleValue());
				row.createCell(i++).setCellValue(item.getGovernoSecondario() == null ? "" : ""+item.getGovernoSecondario());
				row.createCell(i++).setCellValue(item.getTipoInterventoSecondario() == null ? "" : ""+item.getTipoInterventoSecondario());
				row.createCell(i++).setCellValue(item.getSuperficieInt2Ha() == null?"":""+item.getSuperficieInt2Ha().doubleValue());
				row.createCell(i++).setCellValue(item.getStringUsoviab() == null ? "" : ""+item.getStringUsoviab());
				row.createCell(i++).setCellValue(item.getNoteEsbosco() == null ? "" : ""+item.getNoteEsbosco());
				row.createCell(i++).setCellValue(item.getVolumeSpecie() == null?"":""+item.getVolumeSpecie().doubleValue());
				row.createCell(i++).setCellValue(item.getDescrUdm() == null ? "" : ""+item.getDescrUdm());
				row.createCell(i++).setCellValue(item.getVolumeRamagliaM3() == null?"":""+item.getVolumeRamagliaM3().doubleValue());
				row.createCell(i++).setCellValue(item.getDataInserimento() == null ? "" : ConvertUtil.convertToString(item.getDataInserimento()));
				row.createCell(i++).setCellValue(item.getDataInvio() == null ? "" : ConvertUtil.convertToString(item.getDataInvio()));
				row.createCell(i++).setCellValue(item.getNrProtocollo() == null ? "" : ""+item.getNrProtocollo());
				row.createCell(i++).setCellValue(item.getDataProtocollo() == null ? "" : ConvertUtil.convertToString(item.getDataProtocollo()));
				row.createCell(i++).setCellValue(item.getDataVerifica() == null ? "" :  ConvertUtil.convertToString(item.getDataVerifica()));
				row.createCell(i++).setCellValue(item.getNrDeterminaAut() == null ? "" : ""+item.getNrDeterminaAut());
				row.createCell(i++).setCellValue(item.getDataTermineAut() == null ? "" : ConvertUtil.convertToString(item.getDataTermineAut()));
				row.createCell(i++).setCellValue(item.getCompilatore() == null ? "" : ""+item.getCompilatore());
			}
		}
		// Resize all columns to fit the content size
		autosizeColumns(sheet, columns);

	}



	private void fillSheetTrasformazioni(Sheet sheet, CellStyle headerCellStyle, List<TrasformazioniXls> listTrafIstanze) {
		// Create a Row
		Row headerRow = sheet.createRow(0);
		headerRow.setHeightInPoints(24);

		String[] columnsTrafIstanze = new String[] {"numeroIstanza","anno","statoIstanza","richiedente","comune","ricadenzaAapp",
				"ricadenzaRn2000","ricadenzaPopSeme","ricadenzaVincIdro","supInteressataHa","governoPrevalente","catForPrevalente",
				"ubicazionePrevalente","vincoloPaesaggistico","vincoloIdrogeologico","vincoloAreaProtteta","tipoTrasf1","tipoTrasf2",
				"tipoTrasf3","compensazione","flagArt7a","flagArt7b","flagArt7c","flagArt7d",
				"flagArt7a21","flagArt7b21","flagArt7c21","flagArt7d21","flagArt7dter21","flagArt7dquater21","flagArt7dquinquies",
				"compensazioneEuroTeorica","compensazioneEuroReale","noteCompensazione","tecnicoForestale","dichiarazProprieta",
				"dichiarazDissensi","autPaesaggistica","numAutorizPaesagg"," dataAutorizPaesagg","enteAutorizPaesagg","autorizVincoloIdro",
				"numAutorizVincoloIdro"," dataAutorizVincoloIdro","enteAutorizVincoloIdro","valIncidenzaNr2000","numAutValIncidenzaNr2000",
				" dataAutValIncidenzaNr2000","enteAutValIncidenzaNr2000","altriPareri","flagCauzioneCf","flagVersamenteCm","noteDichiarazione",
				" dataInserimento"," dataInvio","nrProtocollo"," dataProtocollo","utenteCompilatore"," dataVerifica","motiviazioneRifiuto",
				"utenteGestore"};

		// Create cells
		for (int i = 0; i < columnsTrafIstanze.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columnsTrafIstanze[i]);
			cell.setCellStyle(headerCellStyle);
			headerRow.setRowStyle(headerCellStyle);
		}

		int rowNum = 1;
		if (listTrafIstanze != null) {
			int i = 0;
			for (TrasformazioniXls item : listTrafIstanze) {
				Row row = sheet.createRow(rowNum++);
				i = 0;
				row.createCell(i++).setCellValue(item.getIdIstanza());
				row.createCell(i++).setCellValue(item.getAnno());
				row.createCell(i++).setCellValue(item.getStatoIstanza());
				row.createCell(i++).setCellValue(item.getRichiedente());
				row.createCell(i++).setCellValue(item.getComune());
				row.createCell(i++).setCellValue(item.getRicadenzaAapp());
				row.createCell(i++).setCellValue(item.getRicadenzaRn2000());
				row.createCell(i++).setCellValue(item.getRicadenzaPopSeme());
				row.createCell(i++).setCellValue(item.getRicadenzaVincIdro());
				row.createCell(i++).setCellValue(item.getSupInteressataHa() == null?"":""+item.getSupInteressataHa().floatValue());
				row.createCell(i++).setCellValue(item.getGovernoPrevalente());
				row.createCell(i++).setCellValue(item.getCatForPrevalente());
				row.createCell(i++).setCellValue(item.getUbicazionePrevalente());
				row.createCell(i++).setCellValue(item.getVincoloPaesaggistico());
				row.createCell(i++).setCellValue(item.getVincoloIdrogeologico());
				row.createCell(i++).setCellValue(item.getVincoloAreaProtteta());
				row.createCell(i++).setCellValue(item.getTipoTrasf1());
				row.createCell(i++).setCellValue(item.getTipoTrasf2());
				row.createCell(i++).setCellValue(item.getTipoTrasf3());
				row.createCell(i++).setCellValue(item.getCompensazione());
				row.createCell(i++).setCellValue(item.getFlagArt7a());
				row.createCell(i++).setCellValue(item.getFlagArt7b());
				row.createCell(i++).setCellValue(item.getFlagArt7c());
				row.createCell(i++).setCellValue(item.getFlagArt7d());
				row.createCell(i++).setCellValue(item.getFlagArt7A21());
				row.createCell(i++).setCellValue(item.getFlagArt7B21());
				row.createCell(i++).setCellValue(item.getFlagArt7C21());
				row.createCell(i++).setCellValue(item.getFlagArt7D21());
				row.createCell(i++).setCellValue(item.getFlagArt7Dter21());
				row.createCell(i++).setCellValue(item.getFlagArt7Dquater21());
				row.createCell(i++).setCellValue(item.getFlagArt7Dquinquies21());
				row.createCell(i++).setCellValue(item.getCompensazioneEuro() == null?"":""+item.getCompensazioneEuro().doubleValue());
				row.createCell(i++).setCellValue(item.getCompensazioneEuroReale() == null?"":""+item.getCompensazioneEuroReale().doubleValue());
				row.createCell(i++).setCellValue(item.getCompensazioneNote());
				row.createCell(i++).setCellValue(item.getTecnicoForestale());
				row.createCell(i++).setCellValue(item.getDichiarazProprieta());
				row.createCell(i++).setCellValue(item.getDichiarazDissensi());
				row.createCell(i++).setCellValue(item.getAutPaesaggistica());
				row.createCell(i++).setCellValue(item.getNumAutorizPaesagg());
				row.createCell(i++).setCellValue(formatDate(item.getDataAutorizPaesagg()));
				row.createCell(i++).setCellValue(item.getEnteAutorizPaesagg());
				row.createCell(i++).setCellValue(item.getAutorizVincoloIdro());
				row.createCell(i++).setCellValue(item.getNumAutorizVincoloIdro());
				row.createCell(i++).setCellValue(formatDate(item.getDataAutorizVincoloIdro()));
				row.createCell(i++).setCellValue(item.getEnteAutorizVincoloIdro());
				row.createCell(i++).setCellValue(item.getValIncidenzaNr2000());
				row.createCell(i++).setCellValue(item.getNumAutValIncidenzaNr2000());
				row.createCell(i++).setCellValue(formatDate(item.getDataAutValIncidenzaNr2000()));
				row.createCell(i++).setCellValue(item.getEnteAutValIncidenzaNr2000());
				row.createCell(i++).setCellValue(item.getAltriPareri());
				row.createCell(i++).setCellValue(item.getFlagCauzioneCf());
				row.createCell(i++).setCellValue(item.getFlagVersamenteCm());
				row.createCell(i++).setCellValue(item.getNoteDichiarazione());
				row.createCell(i++).setCellValue(formatDate(item.getDataInserimento()));
				row.createCell(i++).setCellValue(formatDate(item.getDataInvio()));
				row.createCell(i++).setCellValue(item.getNrProtocollo());
				row.createCell(i++).setCellValue(formatDate(item.getDataProtocollo()));
				row.createCell(i++).setCellValue(item.getUtenteCompilatore());
				row.createCell(i++).setCellValue(formatDate(item.getDataVerifica()));
				row.createCell(i++).setCellValue(item.getMotiviazioneRifiuto());
				row.createCell(i++).setCellValue(item.getUtenteGestore());
			}
		}

		// Resize all columns to fit the content size

		autosizeColumns(sheet, columnsTrafIstanze);
	}

	private void fillSheetVincolo(Sheet sheet, CellStyle headerCellStyle, List<VincoloXls> listTrafIstanze) {
		// Create a Row
		Row headerRow = sheet.createRow(0);
		headerRow.setHeightInPoints(24);

		String[] columnsTrafIstanze = new String[] {"nrIstanzaForestale", "variante", "nrIstanzaOrigineVariante", "proroga",
				"nrIstanzaOrigineProroga", "competenza", "soggettoCompetenza", "anno", "descrStatoIstanza", "richiedente",
				"stringComune", "ricadenzaAapp", "ricadenzaRn2000", "ricadenzaPopseme", "descrizioneIntervento", "tipoIntervento",
				"descTipoIntervAltro", "superficieInteressataHa", "movimentiTerraMc", "movimentiTerraVincidroMc", "mesiIntervento",
				"presenzaAreeDissesto", "presenzaAreeEsondazione", "coperturaVegetaleInteressata", "cauzione", "compensazione",
				"cmSupboscHa", "cmSupnoboscHa", "cmBoscEuro", "cmNoboscEuro", "art9_4a", "art9_4b", "art9_4c", "art19_7a", "art19_7b",
				"art19_7c", "art19_7d", "art19_7dbis", "proprietario", "dissensi", "importoIntervento", "suap", "confServizi",
				"copiaConforme", "versamentoSpeseIstruttoria", "marcaDaBollo", "nMarcaBollo", "allegatoProgettoDefinitivo",
				"allegatoRelazioneTecnica", "allegatoRelazioneGeologica", "allegatoRelazioneForestale", "allegatoProgettoRimboschimento",
				"allegatoRicevutaVersCompMonetaria", "allegatoDocumentazioneFotografica", "allegatoRelazioneNivologica",
				"allegatoEstrattoPlanimetrico", "allegatoSchedaTecnica", "allegatoPlanimetriaCatastale",
				"allegatoEstrattoAerofotogrammetrico", "allegatoDichsostDispArea", "allegatoLibero1", "allegatoLibero2",
				"dataInserimento", "dataInvio", "nrProtocollo", "dataProtocollo", "utenteCompilatore", "dataVerifica",
				"motivazioneRifiuto", "utenteGestore"};

		// Create cells
		for (int i = 0; i < columnsTrafIstanze.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columnsTrafIstanze[i]);
			cell.setCellStyle(headerCellStyle);
			headerRow.setRowStyle(headerCellStyle);
		}

		int rowNum = 1;
		if (listTrafIstanze != null) {
			int i = 0;
			for (VincoloXls item : listTrafIstanze) {
				Row row = sheet.createRow(rowNum++);
				i = 0;
				row.createCell(i++).setCellValue(item.getNrIstanzaForestale());
				row.createCell(i++).setCellValue(item.getVariante());
				row.createCell(i++).setCellValue(item.getNrIstanzaOrigineVariante());
				row.createCell(i++).setCellValue(item.getProroga());
				row.createCell(i++).setCellValue(item.getNrIstanzaOrigineProroga());
				row.createCell(i++).setCellValue(item.getCompetenza());
				row.createCell(i++).setCellValue(item.getSoggettoCompetenza());
				row.createCell(i++).setCellValue(item.getAnno());
				row.createCell(i++).setCellValue(item.getDescrStatoIstanza());
				row.createCell(i++).setCellValue(item.getRichiedente());
				row.createCell(i++).setCellValue(item.getStringComune());
				row.createCell(i++).setCellValue(item.getRicadenzaAapp());
				row.createCell(i++).setCellValue(item.getRicadenzaRn2000());
				row.createCell(i++).setCellValue(item.getRicadenzaPopseme());
				row.createCell(i++).setCellValue(item.getDescrizioneIntervento());
				row.createCell(i++).setCellValue(item.getTipoIntervento());
				row.createCell(i++).setCellValue(item.getDescTipoIntervAltro());
				row.createCell(i++).setCellValue(item.getSuperficieInteressataHa() == null? "":item.getSuperficieInteressataHa().toString());
				row.createCell(i++).setCellValue(item.getMovimentiTerraMc());
				row.createCell(i++).setCellValue(item.getMovimentiTerraVincidroMc());
				row.createCell(i++).setCellValue(item.getMesiIntervento());
				row.createCell(i++).setCellValue(item.getPresenzaAreeDissesto());
				row.createCell(i++).setCellValue(item.getPresenzaAreeEsondazione());
				row.createCell(i++).setCellValue(item.getCoperturaVegetaleInteressata());
				row.createCell(i++).setCellValue(item.getCauzione());
				row.createCell(i++).setCellValue(item.getCompensazione());
				row.createCell(i++).setCellValue(item.getCmSupboscHa() == null?"":item.getCmSupboscHa().toString());
				row.createCell(i++).setCellValue(item.getCmSupnoboscHa() == null?"":item.getCmSupnoboscHa().toString());
				row.createCell(i++).setCellValue(item.getCmBoscEuro() == null?"":item.getCmBoscEuro().toString());
				row.createCell(i++).setCellValue(item.getCmNoboscEuro() == null?"":item.getCmNoboscEuro().toString());
				row.createCell(i++).setCellValue(item.getArt9_4a());
				row.createCell(i++).setCellValue(item.getArt9_4b());
				row.createCell(i++).setCellValue(item.getArt9_4c());
				row.createCell(i++).setCellValue(item.getArt19_7a());
				row.createCell(i++).setCellValue(item.getArt19_7b());
				row.createCell(i++).setCellValue(item.getArt19_7c());
				row.createCell(i++).setCellValue(item.getArt19_7d());
				row.createCell(i++).setCellValue(item.getArt19_7dbis());
				row.createCell(i++).setCellValue(item.getProprietario());
				row.createCell(i++).setCellValue(item.getDissensi());
				row.createCell(i++).setCellValue(item.getImportoIntervento());
				row.createCell(i++).setCellValue(item.getSuap());
				row.createCell(i++).setCellValue(item.getConfServizi());
				row.createCell(i++).setCellValue(item.getCopiaConforme());
				row.createCell(i++).setCellValue(item.getVersamentoSpeseIstruttoria());
				row.createCell(i++).setCellValue(item.getMarcaDaBollo());
				row.createCell(i++).setCellValue(item.getnMarcaBollo());
				row.createCell(i++).setCellValue(item.getAllegatoProgettoDefinitivo());
				row.createCell(i++).setCellValue(item.getAllegatoRelazioneTecnica());
				row.createCell(i++).setCellValue(item.getAllegatoRelazioneGeologica());
				row.createCell(i++).setCellValue(item.getAllegatoRelazioneForestale());
				row.createCell(i++).setCellValue(item.getAllegatoProgettoRimboschimento());
				row.createCell(i++).setCellValue(item.getAllegatoRicevutaVersCompMonetaria());
				row.createCell(i++).setCellValue(item.getAllegatoDocumentazioneFotografica());
				row.createCell(i++).setCellValue(item.getAllegatoRelazioneNivologica());
				row.createCell(i++).setCellValue(item.getAllegatoEstrattoPlanimetrico());
				row.createCell(i++).setCellValue(item.getAllegatoSchedaTecnica());
				row.createCell(i++).setCellValue(item.getAllegatoPlanimetriaCatastale());
				row.createCell(i++).setCellValue(item.getAllegatoEstrattoAerofotogrammetrico());
				row.createCell(i++).setCellValue(item.getAllegatoDichsostDispArea());
				row.createCell(i++).setCellValue(item.getAllegatoLibero1());
				row.createCell(i++).setCellValue(item.getAllegatoLibero2());
				row.createCell(i++).setCellValue(formatDate(item.getDataInserimento()));
				row.createCell(i++).setCellValue(formatDate(item.getDataInvio()));
				row.createCell(i++).setCellValue(item.getNrProtocollo());
				row.createCell(i++).setCellValue(formatDate(item.getDataProtocollo()));
				row.createCell(i++).setCellValue(item.getUtenteCompilatore());
				row.createCell(i++).setCellValue(formatDate(item.getDataVerifica()));
				row.createCell(i++).setCellValue(item.getMotivazioneRifiuto());
				row.createCell(i++).setCellValue(item.getUtenteGestore());
			}
		}

		// Resize all columns to fit the content size

		autosizeColumns(sheet, columnsTrafIstanze);
	}

	private void fillSheetTrasformCatasto(Sheet sheet, CellStyle headerCellStyle, List<TrasformazCatastoXls> listTrafCatasto) {
		// Create a Row
		Row headerRow = sheet.createRow(0);
		headerRow.setHeightInPoints(24);

		String[] columnsTrafCatasto = new String[] {"numeroIstanza","anno","comune","sezione","foglio","particella"};

		// Create cells
		for (int i = 0; i < columnsTrafCatasto.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columnsTrafCatasto[i]);
			cell.setCellStyle(headerCellStyle);
			headerRow.setRowStyle(headerCellStyle);
		}

		int rowNum = 1;
		if (listTrafCatasto != null) {
			int i = 0;
			for (TrasformazCatastoXls item : listTrafCatasto) {
				Row row = sheet.createRow(rowNum++);
				i = 0;
				row.createCell(i++).setCellValue(item.getIdIstanza());
				row.createCell(i++).setCellValue(item.getAnno());
				row.createCell(i++).setCellValue(item.getComune());
				row.createCell(i++).setCellValue(item.getSezione());
				row.createCell(i++).setCellValue(item.getFoglio());
				row.createCell(i++).setCellValue(item.getParticella());
			}
		}

		// Resize all columns to fit the content size

		autosizeColumns(sheet, columnsTrafCatasto);
	}


	private void autosizeColumns(Sheet sheet, String[] columns) {
		for (int i = 0; i < columns.length; i++) {
			sheet.autoSizeColumn(i);
		}
	}

	private void buildHeaderRow(Sheet sheet, CellStyle headerCellStyle, String[] columns) {
		Row headerRow = sheet.createRow(0);
		headerRow.setHeightInPoints(24);

		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
			cell.setCellStyle(headerCellStyle);
			headerRow.setRowStyle(headerCellStyle);
		}
	}

}
