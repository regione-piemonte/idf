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

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.PropCatastoApi;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.impl.dao.GeoPlLottoInterventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.IntervTrasfDAO;
import it.csi.idf.idfapi.business.be.impl.dao.PropCatastoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.PropcatastoInterventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.RicadenzeDAO;
import it.csi.idf.idfapi.business.be.impl.dao.UserSessionDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperAdpforHomeDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperInterventoDAO;
import it.csi.idf.idfapi.business.be.service.GEECO;
import it.csi.idf.idfapi.business.be.service.helper.ParkApiServiceHelper;
import it.csi.idf.idfapi.business.be.vincolo.dao.IdfTIntervVincIdroDAO;
import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.dto.PlainParticelleCatastali;
import it.csi.idf.idfapi.dto.PropCatasto;
import it.csi.idf.idfapi.errors.Error;
import it.csi.idf.idfapi.errors.ErrorConstants;
import it.csi.idf.idfapi.util.SpringSupportedResource;

public class PropCatastoServiceApiImpl extends SpringSupportedResource implements PropCatastoApi {

	public static final String LOGGED_CONFIG = "loggedConfig";

	@Autowired
	private PropCatastoDAO propCatastoDao;
	
	@Autowired
	private GEECO geeco;
	
	@Autowired
	PropcatastoInterventoDAO propcatastoInterventoDAO;
	
	@Autowired
	private WrapperAdpforHomeDAO wrapperAdpforHomeDAO;
	
	@Autowired
	private WrapperInterventoDAO wrapperInterventoDAO;
	
	@Autowired
	private UserSessionDAO userSessionDAO;
	
	@Autowired
	IntervTrasfDAO intervTrasfDAO;
	
	@Autowired
	IdfTIntervVincIdroDAO idfTIntervVincIdroDAO;
	
	@Autowired
	RicadenzeDAO ricadenzeDAO;
	
	@Autowired
	GeoPlLottoInterventoDAO geoPlLottoInterventoDAO;
	
	@Autowired
	private ParkApiServiceHelper parkApiServiceHelper;

	@Override
	public Response getCatasti(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		List<PropCatasto> listCatasti = propCatastoDao.findAllCatasti();
		return Response.ok(listCatasti).build();
	}

	@Override
	public Response getCatastiByID(Integer idGeoPlPropCatasto, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			PropCatasto catasto = propCatastoDao.findCatastoByID(idGeoPlPropCatasto);
			return Response.ok(catasto).build();
		} catch (RecordNotFoundException e) {
			Error err = new Error();
			err.setCode(ErrorConstants.NON_TROVATO_404);
			err.setErrorMessage(ErrorConstants.NON_TROVATO + idGeoPlPropCatasto);
			return Response.serverError().status(ErrorConstants.NON_TROVATO_404).entity(err).build();
		}
	}



	@Override
	public Response getSezione(Integer comune, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			
			return Response.ok(propCatastoDao.findSezioniByComune(comune)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getFoglio(Integer comune,String sezione, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(propCatastoDao.findFogliByComune(comune,sezione)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getParticella(Integer comune,String sezione,Integer foglio, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(propCatastoDao.findParticelleByComuneSezioneFoglio(comune,sezione,foglio)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Override
	public Response getSezioneByTipoIstanza(Integer idTipoIstanza, Integer comune, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			return Response.ok(propCatastoDao.findSezioniByComuneAndTipoIstanza(comune, idTipoIstanza)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getFoglioByTipoIstanza(Integer idTipoIstanza, Integer comune, String sezione,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			return Response.ok(propCatastoDao.findFogliByComuneAndSezioneAndTipoIstnaza(
					comune, sezione, idTipoIstanza)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getParticellaByTipoIstanza(Integer idTipoIstanza, Integer comune, String sezione, Integer foglio,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			return Response.ok(propCatastoDao.findParticelleByComuneAndSezioneAndFoglioAndTipoIstanza(
					comune, sezione, foglio, idTipoIstanza)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	
	
	@Override
	public Response getPlainParticella(Integer comune, String sezione, Integer foglio, String particella,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			return Response.ok(propCatastoDao.findParticelleByAll(comune,sezione,foglio,particella)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}
	@Override
	public Response getCatastiByPfaAndComune(Integer idGeoPlPfa, Integer comune, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			logger.info("<------ getAllPropCatastoByComune" +Response.ok(propCatastoDao.getAllPropCatastoByComune(idGeoPlPfa, comune)).build());
			return Response.ok(propCatastoDao.getAllPropCatastoByComune(idGeoPlPfa, comune)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response retreiveSupIntervento(Integer idIntervento, List<PlainParticelleCatastali> particelle,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			// MK mock geeco servis      for record  sup_intervento_ha
			// in propcatasto_intervento tab
			geeco.editSupInterventoValues(idIntervento, particelle, confUtente);
			List<PlainParticelleCatastali> list = propCatastoDao.getPlainParticelleByIdIntervento(idIntervento);
			return Response.ok(list).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response deleteIntervento(Integer idIntervento, Integer idGeoPlPropCatasto, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			
			propcatastoInterventoDAO.deletePropcatastoIntervento(idGeoPlPropCatasto,idIntervento);
			
			intervTrasfDAO.removeGeometria(idIntervento, idGeoPlPropCatasto);
			
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Override
	public Response deleteInterventoVincolo(Integer idIntervento, Integer idGeoPlPropCatasto, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			
			propcatastoInterventoDAO.deletePropcatastoIntervento(idGeoPlPropCatasto,idIntervento);
			idfTIntervVincIdroDAO.removeGeometria(idIntervento, idGeoPlPropCatasto);
			
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Override
	public Response deleteInterventoPFA(Integer idIntervento, Integer idGeoPlPropCatasto, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			propcatastoInterventoDAO.deletePropcatastoIntervento(idGeoPlPropCatasto,idIntervento);
			geoPlLottoInterventoDAO.removeGeometria(idIntervento, idGeoPlPropCatasto);
			wrapperInterventoDAO.updateRipresaInterventoPfa(idIntervento);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("<------ deleteInterventoPFA" + e);
			return Response.serverError().build();
		}
	}

	@Override
	public Response returnRicadenzePfa(Integer idIntervento, SecurityContext securityContext,
			 HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperInterventoDAO.getRicadenzePfa(idIntervento)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	

	
}
