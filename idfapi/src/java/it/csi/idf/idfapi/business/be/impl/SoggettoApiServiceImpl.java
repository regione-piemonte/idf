/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.HTML;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.*;

import it.csi.idf.idfapi.business.be.service.TAIFService;
import it.csi.idf.idfapi.dto.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import it.csi.idf.idfapi.business.be.SoggettoApi;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.exceptions.ServiceException;
import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.business.be.impl.dao.ComuneDAO;
import it.csi.idf.idfapi.business.be.impl.dao.ConfigUtenteDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SoggettoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.UserSessionDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperAdpforHomeDAO;
import it.csi.idf.idfapi.business.be.service.helper.AaepServiceHelper;
import it.csi.idf.idfapi.rest.BaseResponses;
import it.csi.idf.idfapi.util.SpringSupportedResource;
import it.csi.idf.idfapi.util.ValidationUtil;
import it.csi.idf.idfapi.util.service.integration.aaep.AziendaAAEP;
import it.csi.idf.idfapi.util.service.integration.aaep.ListaAttEconProd;
import it.csi.idf.idfapi.util.service.integration.aaep.ListaSediAAEP;

public class SoggettoApiServiceImpl extends SpringSupportedResource implements SoggettoApi {
	public static final String LOGGED_CONFIG = "loggedConfig";

	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private WrapperAdpforHomeDAO wrapperAdpforHomeDAO;

	@Autowired
	private SoggettoDAO soggettoDAO;

	@Autowired
	private UserSessionDAO userSessionDAO;
	
	@Autowired
	private ConfigUtenteDAO configUtenteDAO;
	
	@Autowired
	private ComuneDAO comuneDAO;

	@Autowired
	private TAIFService taifService;


	@Override
	public Response getSoggetto(@PathParam("idSoggetto") Integer idSoggetto, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		TSoggetto soggetto;
		try {
			soggetto = soggettoDAO.findSoggettoById(idSoggetto);
			return Response.ok(soggetto).build();
		} catch (RecordNotFoundException e) {
			return BaseResponses.itemNotFound();
		}
	}

	@Override
	public Response updateSoggetto(TSoggetto body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			if (validationObligatory(body)) {
				return BaseResponses.requiredField();
			}

			if (validateRequiredFields(body) != null) {
				return validateRequiredFields(body);
			}
			return Response.ok(soggettoDAO.updateSoggetto(body)).build();

		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response saveSoggetto(TSoggetto body, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			if (validationObligatory(body)) {
				return BaseResponses.requiredField();
			}

			if (validateRequiredFields(body) != null)
				return validateRequiredFields(body);
			return Response.ok(soggettoDAO.createSoggetto(body)).build();
		} catch (Exception e) {
			return Response.serverError().build();

		}
	}

	@Override
	public Response getSoggettoByPartialCodiceFiscale(String codiceFiscale, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		if (codiceFiscale == null || codiceFiscale.length() > 16) {
			return BaseResponses.badInputParameters();
		}
		return Response.ok(soggettoDAO.findByPartialCodiceFiscale(codiceFiscale)).build();
	}

	@Override
	public Response getMyself(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {

			return Response
					.ok(soggettoDAO.findFatSoggettoByCodiceFiscale(userSessionDAO.getUser(httpRequest).getCodFisc()))
					.build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getPersonaFisicaForSearch(Integer idTipoIstanza) {
		try {
			return Response.ok(soggettoDAO.getPersFisicaForBOSearch(idTipoIstanza)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getPersonaGiurdicaForSearch(Integer idTipoIstanza) {
		try {
			return Response.ok(soggettoDAO.getPersGiuridicaForBOSearch(idTipoIstanza)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getPersonaFisicaForTagliSearch(String search) {
		try {
			return Response.ok(soggettoDAO.getPersFisicaForBOTagliSearch(search)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getPersonaGiurdicaForTagliSearch(String search) {
		try {
			return Response.ok(soggettoDAO.getPersGiuridicaForBOTagliSearch(search)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getUtilizzatorePFForSearch(String search) {
		try {
			return Response.ok(soggettoDAO.getUtilizzatorePFForBOSearch(search)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getUtilizzatorePGForSearch(String search) {
		try {
			return Response.ok(soggettoDAO.getUtilizzatorePGForBOSearch(search)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getTecniciForestaliForTagli(String search) {
		try {
			return Response.ok(soggettoDAO.getTecniciForestaliForTagli(search)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getProfessionistaForSearch(Integer idTipoIstanza) {
		try {
			return Response.ok(soggettoDAO.getProfessForBOSearch(idTipoIstanza)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Override
	public Response isUserEnabledEditIstanza(Integer idIntervento, HttpServletRequest httpRequest) {
		
		try {
			ConfigUtente configUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			Map<String, Boolean> res = new HashMap<String, Boolean>();
			res.put("enabled", configUtenteDAO.isUserEnabledEditIstanza(configUtente, idIntervento));
			return Response.ok(res).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	private boolean validationObligatory(TSoggetto input) {
		return input.getNrTelefonico() == null || input.geteMail() == null || input.getCodiceFiscale() == null
				|| input.getNome() == null || input.getCognome() == null;
	}

	private Response validateRequiredFields(TSoggetto input) {
		if (input.getNrTelefonico().length() < 7 || input.getNrTelefonico().length() > 20) {
			return BaseResponses.invalidLength();
		}

		if (!ValidationUtil.isEMail(input.geteMail())) {
			return BaseResponses.invalidEmail();
		}
		return null;
	}

	@Override
	public Response getPersonaGiurdicaByOwnerCodiceFiscale(String codiceFiscale) {
		try {
			List<TSoggetto> res = soggettoDAO.getPersonaGiurdicaByOwnerCodiceFiscale(codiceFiscale);
			AaepServiceHelper aaepService = (AaepServiceHelper) applicationContext.getBean("aaepServiceHelper");
			if(res == null) {
				res = new ArrayList<TSoggetto>();
			}
			try {
		 		ListaAttEconProd[] aziendaList = aaepService.cercaAziendeAAEPByCodiceFiscale(codiceFiscale);
		 		res = mapNewAziendeAAEP(res,aziendaList);
			} catch (ServiceException ex) {
				logger.error(ex.getMessage());
				return Response.serverError().build();//ripristinare questa riga
			}
			return Response.ok(res).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response getPersonaGiurdicaPubOrPrivByOwnerCodiceFiscale(String codiceFiscale, Boolean isPubblico) {
		try {
			List<TSoggetto> res = soggettoDAO.getPersonaGiurdicaByOwnerCodiceFiscaleAndIsPubblic(codiceFiscale, isPubblico);
			if (isPubblico) {
				// ente pubblico
				return Response.ok(res).build();

			} else {
				// privato
				if(res == null) {
					res = new ArrayList<TSoggetto>();
				}
				//integrazione TAIF
				try {
					List<TAIFAnagraficaAzienda> aziendeTaif = taifService.findByCodiceFiscaleTitolare(codiceFiscale);
					res = mapNewAziendeTAIF(res, aziendeTaif);
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
				// inteegrazione Aeep
				AaepServiceHelper aaepService = (AaepServiceHelper) applicationContext.getBean("aaepServiceHelper");
				try {
					ListaAttEconProd[] aziendaList = aaepService.cercaAziendeAAEPByCodiceFiscale(codiceFiscale);
					res = mapNewAziendeAAEP(res,aziendaList);
				} catch (ServiceException ex) {
					logger.error(ex.getMessage());
					//return Response.serverError().build();//ripristinare questa riga
				}
				return Response.ok(res).build();
			}

		} catch (Exception e) {
			logger.error(e);
			return Response.ok(new ArrayList<>()).build();
			//return Response.serverError().build();
		}
	}


	@Override
	public Response getPersonaGiurdicaByCodiceFiscale(String codiceFiscale) {
		try {
			TSoggetto azienda = soggettoDAO.findAziendaByCodiceFiscale(codiceFiscale);
			if(azienda == null) {
				azienda = new TSoggetto();
				azienda = fillAziendaByAaepService(codiceFiscale,azienda);
			}
			return Response.ok(azienda).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getPersonaGiurdicaByCodiceFiscaleAndIsPubblic(String codiceFiscale, Boolean isPubblic) {
		try {
			TSoggetto azienda = soggettoDAO.findAziendaByCodiceFiscaleAndIsPubblic(codiceFiscale, isPubblic);
			if(azienda == null && !isPubblic) {
				// search in TAIF by CF azienda
				TAIFAnagraficaAzienda taifAzienda = taifService.findByCodiceFiscale(codiceFiscale);
				if (null != taifAzienda) {
					azienda = taifService.fillAziendaByTaifService(taifAzienda);
				}
				if (null == azienda) {
					azienda = fillAziendaByAaepService(codiceFiscale,new TSoggetto());
				}
			}
			return Response.ok(azienda).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	private TSoggetto fillAziendaByAaepService(String codiceFiscale, TSoggetto azienda) {
		AaepServiceHelper aaepService = (AaepServiceHelper) applicationContext.getBean("aaepServiceHelper");
		try {
 			azienda.setCodiceFiscale(codiceFiscale);
			AziendaAAEP aziendaAAEP = aaepService.getAziendaByCodiceFiscale(codiceFiscale);
	 		if(aziendaAAEP != null) {
	 			azienda.setPartitaIva(aziendaAAEP.getPartitaIva());
	 			azienda.setDenominazione(aziendaAAEP.getRagioneSociale());
	 			if(aziendaAAEP.getListaSediAAEP() != null && aziendaAAEP.getListaSediAAEP().length > 0) {
	 				for(ListaSediAAEP item : aziendaAAEP.getListaSediAAEP()) {
	 					logger.info("****** getIdAAEPSede: " + item.getIdAAEPSede() + " - indirizzo: " + item.getIndirizzo());
	 					if("1".equals(item.getIdAAEPSede())) {
	 						azienda.setIndirizzo(item.getIndirizzo());
	 						try {
	 							Comune comune = comuneDAO.findComuneByName(item.getDescrComuneUL());
	 							azienda.setFkComune(comune != null?comune.getIdComune():null);
	 						}catch(Exception ex) {
	 							logger.error("Comune not found per nome: " + item.getDescrComuneUL());
	 						}
	 					}
	 				}
	 			}
	 		}
		} catch (ServiceException ex) {
			logger.error(ex.getMessage());
			//return Response.serverError().build();//ripristinare questa riga
		}
		return azienda;
	}
	
	private PersonaFisGiu fillAziendaByAaepService(String codiceFiscale) {
		AaepServiceHelper aaepService = (AaepServiceHelper) applicationContext.getBean("aaepServiceHelper");
		PersonaFisGiu azienda = new PersonaFisGiu();
		try {
 			azienda.setCodiceFiscale(codiceFiscale);
			AziendaAAEP aziendaAAEP = aaepService.getAziendaByCodiceFiscale(codiceFiscale);
	 		if(aziendaAAEP != null) {
	 			azienda.setPartitaIva(aziendaAAEP.getPartitaIva());
	 			azienda.setDenominazione(aziendaAAEP.getRagioneSociale());
	 			if(aziendaAAEP.getListaSediAAEP() != null && aziendaAAEP.getListaSediAAEP().length > 0) {
	 				for(ListaSediAAEP item : aziendaAAEP.getListaSediAAEP()) {
	 					logger.info("****** getIdAAEPSede: " + item.getIdAAEPSede() + " - indirizzo: " + item.getIndirizzo());
	 					if("1".equals(item.getIdAAEPSede())) {
	 						azienda.setIndirizzo(item.getIndirizzo());
	 						try {
	 							Comune comune = comuneDAO.findComuneByName(item.getDescrComuneUL());
								 if (null != comune) {
									 ComuneResource cr = new ComuneResource();
									 cr.setIdComune(comune.getIdComune());
									 cr.setDenominazioneComune(comune.getDenominazioneComune());
									 cr.setIstatComune(comune.getIstatComune());
									 cr.setIstatProv(comune.getIstatProv());
									 azienda.setComune(cr);
								 }
	 						}catch(Exception ex) {
	 							logger.error("Comune not found per nome: " + item.getDescrComuneUL());
	 						}
	 					}
	 				}
	 			}
	 		}
		} catch (ServiceException ex) {
			logger.error(ex.getMessage());
			//return Response.serverError().build();//ripristinare questa riga
		}
		return azienda;
	}

	@Override
	public Response getPersonaFisicaByCodiceFiscale(String codiceFiscale) {
		try {
			TSoggetto sogFisico = soggettoDAO.findSoggettoByCodiceFiscale(codiceFiscale);
			if(sogFisico == null) {
				sogFisico = new TSoggetto();
				sogFisico.setCodiceFiscale(codiceFiscale);
			}
			return Response.ok(sogFisico).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}
	
	private List<TSoggetto> mapNewAziendeAAEP(List<TSoggetto> aziendeList, ListaAttEconProd[] aziendaAAEPList){
		Map<String,TSoggetto> mapAziende = new HashMap<String,TSoggetto>();
		if(!aziendeList.isEmpty()) {
			for(TSoggetto item: aziendeList) {
				mapAziende.put(item.getCodiceFiscale().toUpperCase(),item);
			}
		}
		if(aziendaAAEPList != null && aziendaAAEPList.length > 0) {
			for(ListaAttEconProd item: aziendaAAEPList) {
				logListaAttEconProd(item);
				if(mapAziende.get(item.getCodiceFiscale().toUpperCase()) == null) {
					aziendeList.add(fillAziendaByAaepService(item.getCodiceFiscale(),new TSoggetto()));
				}else {
					fillAziendaByAaepService(item.getCodiceFiscale(),mapAziende.get(item.getCodiceFiscale().toUpperCase()));
				}
			}
		}
		return aziendeList;
	}
	
	private void logListaAttEconProd(ListaAttEconProd item) {
		logger.info("getAziendaCessata: " + item.getAziendaCessata() + " - CodATECO2002: " + item.getCodATECO2002()
				 + " - CodATECO2007: " + item.getCodATECO2007()  + " - CodATECO91: " + item.getCodATECO91() 
				 + " - CodiceFiscale: " + item.getCodiceFiscale()  + " - DescrATECO2002: " + item.getDescrATECO2002() 
				 + " - DescrATECO2007: " + item.getDescrATECO2007()  + " - DescrATECO91: " + item.getDescrATECO91() 
				 + " - DescrizioneFonteDato: " + item.getDescrizioneFonteDato()  + " - DescrizioneNaturaGiuridica: " + item.getDescrizioneNaturaGiuridica()   
				 + " - tIdFonteDato: " + item.getIdFonteDato()  + " - IdNaturaGiuridica: " + item.getIdNaturaGiuridica()  
				 + " - tLocalitaUbicazSL: " + item.getLocalitaUbicazSL()  + " - RagioneSociale: " + item.getRagioneSociale()  
				 + " - SiglaProvUbicazSL: " + item.getSiglaProvUbicazSL() );
	}

	@Override
	public Response getAllProfessionista(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		try {
			return Response.ok(soggettoDAO.findAllFatProfess()).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response aaepByCodiceFiscale(@QueryParam("codiceFiscale") String codiceFiscale) {
		
		if (codiceFiscale.length() != 16) {
			throw new ValidationException("Codice fiscale must have 16 digits");
		}
		AaepServiceHelper aaepService = (AaepServiceHelper) applicationContext.getBean("aaepServiceHelper");
		try {
			AziendaAAEP aziendaAAEP = aaepService.getAziendaByCodiceFiscale(codiceFiscale);
			
			return Response.ok().entity(aziendaAAEP).build();
		} catch (ServiceException e) {
			e.printStackTrace(); // TODO: Remove this line afrer testing
			return Response.serverError().entity(e).build();
		}
	}

	@Override
	public Response cercaAziendeAAEPByCodiceFiscale(String codiceFiscale) {
		AaepServiceHelper aaepService = (AaepServiceHelper) applicationContext.getBean("aaepServiceHelper");
		try {
			ListaAttEconProd[] aziendaList = aaepService.cercaAziendeAAEPByCodiceFiscale(codiceFiscale);
			
			return Response.ok().entity(aziendaList).build();
		} catch (ServiceException e) {
			e.printStackTrace(); // TODO: Remove this line afrer testing
			return Response.serverError().entity(e).build();
		}
	}

	// list of tecnico forestale will be populated by back office
	@Override
	public Response getAllTecnicoForestale(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(soggettoDAO.getAllTecnicoForestale()).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response getProfessionistiList() {
		try {
			return Response.ok(soggettoDAO.getProfessionistiList()).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getAllSportello() {
		try {
			return Response.ok(soggettoDAO.getSportelloList()).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getSportelloCorrente(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			ConfigUtente configUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			return Response.ok(soggettoDAO.findSportelloByIdConfigUtente(configUtente.getIdConfigUtente())).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getSportelliAssociati(SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			ConfigUtente configUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			return Response.ok(soggettoDAO.findSportelliByIdConfigUtente(configUtente.getIdConfigUtente())).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getGestoriList(@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders , @Context HttpServletRequest httpRequest) {
		try {
			return Response.ok(soggettoDAO.getGestoriList()).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}



	@Override
	public Response searchByOneParam(String search, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			return Response.ok(soggettoDAO.searchPfByCForName(search)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}

	}

	@Override
	public Response searchPgByOneParam(String search, Boolean entePubblico,
									   SecurityContext securityContext,
									   HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			List<PersonaFisGiu> soggetti = soggettoDAO.searchPgByCForDenom(search, entePubblico);
			if(!entePubblico && !soggetti.isEmpty()){
				logger.info("trovato a db");
				for( PersonaFisGiu sog: soggetti){
					List<TAIFAnagraficaAzienda> aziendaTaif = new ArrayList<>();
					if(sog.getCodiceFiscale() != null){
						aziendaTaif = taifService.searchByCodiceFiscaleDenominazionePartitaIVa(sog.getCodiceFiscale());
					} else if (sog.getPartitaIva() != null) {
						aziendaTaif = taifService.searchByCodiceFiscaleDenominazionePartitaIVa(sog.getPartitaIva());
					} else if (sog.getDenominazione() != null) {
						aziendaTaif = taifService.searchByCodiceFiscaleDenominazionePartitaIVa(sog.getDenominazione());
					}

					if(!aziendaTaif.isEmpty()){
						sog.setNumAlboForestale(aziendaTaif.get(0).getNumeroAlbo());
					}
				}
			}
			if (!entePubblico && soggetti.isEmpty()) {
				logger.info("trovato a TAIF");
				List<TAIFAnagraficaAzienda> taif = taifService.searchByCodiceFiscaleDenominazionePartitaIVa(search);
				if (taif != null && !taif.isEmpty()) {
					for (TAIFAnagraficaAzienda taifAnagraficaAzienda : taif) {
						PersonaFisGiu soggetto = taifService.fillPersonaGiuridicaByTaifService(taifAnagraficaAzienda);
						soggetti.add(soggetto);
					}
				} else {
					soggetti.add(this.fillAziendaByAaepService(search));
				}
			}

			return Response.ok(soggetti).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}


	}


	private List<TSoggetto> mapNewAziendeTAIF(List<TSoggetto> aziendeList, List<TAIFAnagraficaAzienda> aziendeTaif) {

		for (TAIFAnagraficaAzienda taif : aziendeTaif) {
			TSoggetto azienda = taifService.fillAziendaByTaifService(taif);

			Object found = CollectionUtils.find(aziendeList, new Predicate() {
				@Override
				public boolean evaluate(Object o) {
					TSoggetto p = (TSoggetto) o;
					return p.getCodiceFiscale().equalsIgnoreCase(taif.getCodiceFiscale());
				}
			});

			if (null != found) {
				aziendeList.remove((TSoggetto) found);
			}
			aziendeList.add(azienda);
		}

		return aziendeList;
	}




}
