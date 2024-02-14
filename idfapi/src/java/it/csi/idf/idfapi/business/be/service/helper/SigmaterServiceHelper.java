/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.service.helper;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import it.csi.idf.idfapi.business.be.exceptions.ServiceException;
import it.csi.idf.idfapi.util.ConvertUtil;
import it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.AutorizzMancanteEnteException;
import it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.CSIException;
import it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.OutputException;
import it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.ParInputObblMancantiException;
import it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.ParInputValNonCorrettoException;
import it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.PermissionException;
import it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.SigwgssrvSigwgssrvServiceLocator;
import it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.SigwgssrvSigwgssrv_PortType;
import it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.SystemException;
import it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.UnrecoverableException;
import it.csi.sigmater.ws.client.sigalfsrvTerreni.CSIException_Exception;
import it.csi.sigmater.ws.client.sigalfsrvTerreni.DettaglioDatiTerreno;
import it.csi.sigmater.ws.client.sigalfsrvTerreni.IdentificativoCatastaleTerreno;
import it.csi.sigmater.ws.client.sigalfsrvTerreni.SigalfsrvTerreni;
import it.csi.sigmater.ws.client.sigalfsrvTerreni.SigalfsrvTerreniService;
import it.csi.sigmater.ws.client.sigalfsrvTerreni.SystemException_Exception;
import it.csi.sigmater.ws.client.sigalfsrvTerreni.UnrecoverableException_Exception;
import it.csi.wso2.apiman.oauth2.helper.GenericWrapperFactoryBean;

public class SigmaterServiceHelper extends AbstractServiceHelper {
	static final Logger logger = Logger.getLogger(SigmaterServiceHelper.class);
	
	private ApiManagerServiceHelper apiManagerServiceHelper;
	private SigalfsrvTerreni sigalfTerreni;
//	private SigwgssrvSigwgssrv sigwgService;
	private SigwgssrvSigwgssrv_PortType sigwgService;
	
	private static final String WSDL_URL = "conf/wsdl/sigalfsrvTerreni.wsdl";
	
	private String urlTerreni; 
	private String urlSigwg;
	private String credenziali;
	
	
	
	/**
	 * @return the apiManagerServiceHelper
	 */
	public ApiManagerServiceHelper getApiManagerServiceHelper() {
		return apiManagerServiceHelper;
	}



	/**
	 * @param apiManagerServiceHelper the apiManagerServiceHelper to set
	 */
	public void setApiManagerServiceHelper(ApiManagerServiceHelper apiManagerServiceHelper) {
		this.apiManagerServiceHelper = apiManagerServiceHelper;
	}

	
	public SigmaterServiceHelper(String urlTerreni, String urlSigwg, String credenziali) {
		
		this.urlTerreni = urlTerreni;
		this.urlSigwg = urlSigwg;
		this.credenziali = credenziali;
	}


//	private SigwgssrvSigwgssrv getSigwgService() throws ServiceException  {
//		LOGGER.debug("[SigmaterServiceHelper::getSigwgService] BEGIN");
//		try {
//			GenericWrapperFactoryBean gwfb = new GenericWrapperFactoryBean();
//			gwfb.setEndPoint(this.urlSigwg);
//			gwfb.setTokenRetryManager(this.apiManagerServiceHelper.getTokenRetryManagerCxf(this.credenziali));
//			SigwgssrvSigwgssrvService service = new SigwgssrvSigwgssrvService();
////			URL url =  new URL("http://tst-api-ent.ecosis.csi.it:80/api/CATASTO_sigwgssrv/2.0");
////			URL url =  new URL("http://tst-applogic.reteunitaria.piemonte.it/sigwgssrvApplSigwgssrvWsfad/services/sigwgssrvSigwgssrv");
////			SigwgssrvSigwgssrvService service = new SigwgssrvSigwgssrvService(url);
//			gwfb.setPort(service.getPort(SigwgssrvSigwgssrv.class));
//			gwfb.setWrappedInterface(SigwgssrvSigwgssrv.class);
//			sigwgService = (SigwgssrvSigwgssrv) gwfb.create();
//		}
//		
//		catch (Exception e) {
//			throw new ServiceException("[Errore SIGMATER getSigwgService] "+e.getMessage());
//		}
//		finally {
//			LOGGER.debug("[SigmaterServiceHelper::getSigwgService] END");
//		}
//		return sigwgService;
//	}
	
	private SigwgssrvSigwgssrv_PortType getSigwgService() throws ServiceException{
		LOGGER.debug("[SigmaterServiceHelper::getSigwgService] BEGIN");
		try {
			SigwgssrvSigwgssrvServiceLocator serviceLocator = new SigwgssrvSigwgssrvServiceLocator();
			SigwgssrvSigwgssrv_PortType port = serviceLocator.getsigwgssrvSigwgssrv();
			GenericWrapperFactoryBean gwfb = new GenericWrapperFactoryBean();
			gwfb.setPort(port);
			gwfb.setEndPoint(this.urlSigwg);
			gwfb.setWrappedInterface(SigwgssrvSigwgssrv_PortType.class);
			gwfb.setTokenRetryManager(this.apiManagerServiceHelper.getTokenRetryManagerAxis1(this.credenziali));
			
			this.sigwgService = (SigwgssrvSigwgssrv_PortType)gwfb.create();
		}
		catch (Exception e) {
			throw new ServiceException("[Errore SIGMATER getSigwgService] "+e.getMessage());
		}
		finally {
			LOGGER.debug("[SigmaterServiceHelper::getSigwgService] END");
		}
		return sigwgService;
	}
	
	private SigalfsrvTerreni getSigalfTerreniService() throws ServiceException  {
		LOGGER.debug("[SigmaterServiceHelper::getSigalfTerreniService] BEGIN");
		try {
			
			GenericWrapperFactoryBean gwfb = new GenericWrapperFactoryBean();
			gwfb.setEndPoint(this.urlTerreni);
			gwfb.setTokenRetryManager(this.apiManagerServiceHelper.getTokenRetryManagerCxf(this.credenziali));
			URL ulr = Thread.currentThread().getContextClassLoader().getResource(WSDL_URL);
			SigalfsrvTerreniService service = new SigalfsrvTerreniService(ulr);
			gwfb.setPort(service.getPort(SigalfsrvTerreni.class));
			gwfb.setWrappedInterface(SigalfsrvTerreni.class);
			
			sigalfTerreni = (SigalfsrvTerreni) gwfb.create();
		}
		
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("[Errore SIGMATER getSigalfTerreniService] "+e.getMessage());
		}
		finally {
			LOGGER.debug("[SigmaterServiceHelper::getSigalfTerreniService] END");
		}
		return sigalfTerreni;
	}


	public List<DettaglioDatiTerreno> getDettagliDatiTerreno(String istatComune,  String sezione, String foglio, String numero, String codiceFiscale) throws ServiceException {
		LOGGER.debug("[SigmaterServiceHelper::getDettagliDatiTerreno] BEGIN");
		List<DettaglioDatiTerreno> result = new ArrayList<DettaglioDatiTerreno>();
		 try {
			List<IdentificativoCatastaleTerreno> list = getSigalfTerreniService().cercaElencoSubalterniTerreniConIstatESezione(istatComune, sezione, ConvertUtil.convertToLong(foglio), numero, codiceFiscale);
			for (IdentificativoCatastaleTerreno dto : list) {
				result.add(getSigalfTerreniService().cercaDettaglioDatiTerreno(dto, codiceFiscale));
			}
		} catch (SystemException_Exception e) {
			throw new ServiceException("[Errore SIGMATER SystemException_Exception getDettagliDatiTerreno] "+e.getMessage());
		} catch (CSIException_Exception e) {
			throw new ServiceException("[Errore SIGMATER CSIException_Exception getDettagliDatiTerreno] "+e.getMessage());
		} catch (UnrecoverableException_Exception e) {
			throw new ServiceException("[Errore SIGMATER UnrecoverableException_Exception getDettagliDatiTerreno] "+e.getMessage());
		}
		 finally {
			 LOGGER.debug("[SigmaterServiceHelper::getDettagliDatiTerreno] END");
		 }
		 return result;
	}
	
//	public List<InfoParticella> getDettaglioDatiTerrenoGeometrie(String istatComune,  String sezione, String foglio, String particella, String codiceFiscale) throws ServiceException {
//		LOGGER.debug("[SigmaterServiceHelper::getDettaglioDatiTerrenoGeometrie] BEGIN");
//		try {
//			return getSigwgService().cercaParticelleByFilter(istatComune, null, sezione, null, particella, foglio, codiceFiscale);
//			
//		} catch (it.csi.sigmater.ws.client.sigwgssrvSigwgssrv.UnrecoverableException_Exception e) {
//			throw new ServiceException("[Errore SIGMATER UnrecoverableException_Exception getDettaglioDatiTerrenoGeometrie] "+e.getMessage());
//		} catch (PermissionException_Exception e) {
//			throw new ServiceException("[Errore SIGMATER PermissionException_Exception getDettaglioDatiTerrenoGeometrie] "+e.getMessage());
//		} catch (it.csi.sigmater.ws.client.sigwgssrvSigwgssrv.CSIException_Exception e) {
//			throw new ServiceException("[Errore SIGMATER CSIException_Exception getDettaglioDatiTerrenoGeometrie] "+e.getMessage());
//		} catch (AutorizzMancanteEnteException_Exception e) {
//			throw new ServiceException("[Errore SIGMATER AutorizzMancanteEnteException_Exception getDettaglioDatiTerrenoGeometrie] "+e.getMessage());
//		} catch (it.csi.sigmater.ws.client.sigwgssrvSigwgssrv.SystemException_Exception e) {
//			throw new ServiceException("[Errore SIGMATER SystemException_Exception getDettaglioDatiTerrenoGeometrie] "+e.getMessage());
//		} catch (ParInputValNonCorrettoException_Exception e) {
//			throw new ServiceException("[Errore SIGMATER ParInputValNonCorrettoException_Exception getDettaglioDatiTerrenoGeometrie] "+e.getMessage());
//		} catch (OutputException_Exception e) {
//			throw new ServiceException("[Errore SIGMATER OutputException_Exception getDettagliDatiTerreno] "+e.getMessage());
//		} catch (ParInputObblMancantiException_Exception e) {
//			throw new ServiceException("[Errore SIGMATER ParInputObblMancantiException_Exception getDettagliDatiTerreno] "+e.getMessage());
//		}
//		finally {
//			 LOGGER.debug("[SigmaterServiceHelper::getDettaglioDatiTerrenoGeometrie] END");
//		}
//	}
	
	public it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.InfoParticella[] getDettaglioDatiTerrenoGeometrie(String istatComune,  String sezione, String foglio, String particella, String codiceFiscale) throws ServiceException {
		LOGGER.debug("[SigmaterServiceHelper::getDettaglioDatiTerrenoGeometrie] BEGIN");
		try {
			return getSigwgService().cercaParticelleByFilter(istatComune, null, sezione, null, particella, foglio, codiceFiscale);
			
		} catch (PermissionException e) {
			throw new ServiceException("[Errore SIGMATER PermissionException getDettaglioDatiTerrenoGeometrie] "+e.getMessage());
		} catch (AutorizzMancanteEnteException e) {
			throw new ServiceException("[Errore SIGMATER AutorizzMancanteEnteException getDettaglioDatiTerrenoGeometrie] "+e.getMessage());
		} catch (ParInputValNonCorrettoException e) {
			throw new ServiceException("[Errore SIGMATER ParInputValNonCorrettoException getDettaglioDatiTerrenoGeometrie] "+e.getMessage());
		} catch (OutputException e) {
			throw new ServiceException("[Errore SIGMATER OutputException getDettaglioDatiTerrenoGeometrie] "+e.getMessage());
		} catch (ParInputObblMancantiException e) {
			throw new ServiceException("[Errore SIGMATER ParInputObblMancantiException getDettaglioDatiTerrenoGeometrie] "+e.getMessage());
		} catch (UnrecoverableException e) {
			throw new ServiceException("[Errore SIGMATER UnrecoverableException getDettaglioDatiTerrenoGeometrie] "+e.getMessage());
		} catch (SystemException e) {
			throw new ServiceException("[Errore SIGMATER SystemException getDettaglioDatiTerrenoGeometrie] "+e.getMessage());
		} catch (CSIException e) {
			throw new ServiceException("[Errore SIGMATER CSIException getDettaglioDatiTerrenoGeometrie] "+e.getMessage());
		} catch (RemoteException e) {
			throw new ServiceException("[Errore SIGMATER RemoteException getDettaglioDatiTerrenoGeometrie] "+e.getMessage());
		} 
		finally {
			 LOGGER.debug("[SigmaterServiceHelper::getDettaglioDatiTerrenoGeometrie] END");
		}
	}
	
	public it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.InfoParticella[] getParticelleByGeometry(String geometryGML,  String cfUtente) throws ServiceException {
		LOGGER.debug("[SigmaterServiceHelper::getParticelleByGeometry] BEGIN");
		try {
			return getSigwgService().cercaParticelleByEnvelope(geometryGML, cfUtente);
			
		} catch (PermissionException e) {
			throw new ServiceException("[Errore SIGMATER PermissionException getDettaglioDatiTerrenoGeometrie] "+e.getMessage());
		} catch (AutorizzMancanteEnteException e) {
			throw new ServiceException("[Errore SIGMATER AutorizzMancanteEnteException getDettaglioDatiTerrenoGeometrie] "+e.getMessage());
		} catch (ParInputValNonCorrettoException e) {
			throw new ServiceException("[Errore SIGMATER ParInputValNonCorrettoException getDettaglioDatiTerrenoGeometrie] "+e.getMessage());
		} catch (OutputException e) {
			throw new ServiceException("[Errore SIGMATER OutputException getDettaglioDatiTerrenoGeometrie] "+e.getMessage());
		} catch (ParInputObblMancantiException e) {
			throw new ServiceException("[Errore SIGMATER ParInputObblMancantiException getDettaglioDatiTerrenoGeometrie] "+e.getMessage());
		} catch (UnrecoverableException e) {
			throw new ServiceException("[Errore SIGMATER UnrecoverableException getDettaglioDatiTerrenoGeometrie] "+e.getMessage());
		} catch (SystemException e) {
			throw new ServiceException("[Errore SIGMATER SystemException getDettaglioDatiTerrenoGeometrie] "+e.getMessage());
		} catch (CSIException e) {
			throw new ServiceException("[Errore SIGMATER CSIException getDettaglioDatiTerrenoGeometrie] "+e.getMessage());
		} catch (RemoteException e) {
			throw new ServiceException("[Errore SIGMATER RemoteException getDettaglioDatiTerrenoGeometrie] "+e.getMessage());
		} 
		finally {
			 LOGGER.debug("[SigmaterServiceHelper::getDettaglioDatiTerrenoGeometrie] END");
		}
	}
}
