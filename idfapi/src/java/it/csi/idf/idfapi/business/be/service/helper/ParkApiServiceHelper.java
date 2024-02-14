/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.service.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import it.csi.idf.idfapi.business.be.service.helper.factory.RicadenzaDtoFactory;
import it.csi.idf.idfapi.util.service.integration.gsareprot.areeprotette.ParkapiAreeprotetteServiceLocator;
import it.csi.idf.idfapi.util.service.integration.gsareprot.areeprotette.ParkapiAreeprotette_PortType;
import it.csi.idf.idfapi.util.service.integration.gsareprot.areeprotette.Ricadenza;
import it.csi.idf.idfapi.util.service.integration.gsareprot.sic.ParkapiSicServiceLocator;
import it.csi.idf.idfapi.util.service.integration.gsareprot.sic.ParkapiSic_PortType;
import it.csi.idf.idfapi.util.service.integration.gsareprot.zps.ParkapiZpsServiceLocator;
import it.csi.idf.idfapi.util.service.integration.gsareprot.zps.ParkapiZps_PortType;
import it.csi.wso2.apiman.oauth2.helper.GenericWrapperFactoryBean;

public class ParkApiServiceHelper extends AbstractServiceHelper {
	
	ParkapiAreeprotette_PortType serviceAreeProtette;
	ParkapiSic_PortType serviceSic;
	ParkapiZps_PortType serviceZps;
	
	private ApiManagerServiceHelper apiManagerServiceHelper;
	
	
	
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

	protected String urlServiceAreeProtette = null;
	protected String urlServiceSic = null;
	protected String urlServiceZps = null;
	
	
	public ParkApiServiceHelper(String urlServiceAreeProtette, String urlServiceSic, String urlServiceZps) {
		this.urlServiceAreeProtette = urlServiceAreeProtette;
		this.urlServiceSic = urlServiceSic;
		this.urlServiceZps = urlServiceZps;
	}
	
	
	private ParkapiAreeprotette_PortType getServiceAreeProtette() throws ServiceException, IOException, ClassNotFoundException {
		LOGGER.debug("[ParkApiServiceHelper::getServiceAreeProtette] BEGIN");
		ParkapiAreeprotetteServiceLocator serviceLocator = new ParkapiAreeprotetteServiceLocator();
		ParkapiAreeprotette_PortType port = serviceLocator.getparkapiAreeprotette();
		GenericWrapperFactoryBean gwfb = new GenericWrapperFactoryBean();
		LOGGER.debug("url service " + this.urlServiceAreeProtette);
		gwfb.setEndPoint(this.urlServiceAreeProtette);
		gwfb.setWrappedInterface(ParkapiAreeprotette_PortType.class);
		gwfb.setPort(port);
		gwfb.setTokenRetryManager(this.apiManagerServiceHelper.getTokenRetryManagerAxis1());
		this.serviceAreeProtette = (ParkapiAreeprotette_PortType) gwfb.create();

		LOGGER.debug("[ParkApiServiceHelper::getServiceAreeProtette] Service 'ParkapiAreeprotette_PortType' INITIALIZED");
		LOGGER.debug("[ParkApiServiceHelper::getServiceAreeProtette] END");

		return this.serviceAreeProtette;
	}
	
	private ParkapiSic_PortType getServiceSic() throws ServiceException, IOException, ClassNotFoundException {
		LOGGER.debug("[ParkApiServiceHelper::getServiceSic] BEGIN");
		ParkapiSicServiceLocator serviceLocator = new ParkapiSicServiceLocator();
		ParkapiSic_PortType port = serviceLocator.getparkapiSic();
		GenericWrapperFactoryBean gwfb = new GenericWrapperFactoryBean();
		LOGGER.debug("url service " + this.urlServiceSic);
		gwfb.setEndPoint(this.urlServiceSic);
		gwfb.setWrappedInterface(ParkapiSic_PortType.class);
		gwfb.setPort(port);
		gwfb.setTokenRetryManager(this.apiManagerServiceHelper.getTokenRetryManagerAxis1());
		this.serviceSic = (ParkapiSic_PortType) gwfb.create();

		LOGGER.debug("[ParkApiServiceHelper::getServiceSic] Service 'ParkapiSic_PortType' INITIALIZED");
		LOGGER.debug("[ParkApiServiceHelper::getServiceSic] END");

		return this.serviceSic;
	}
	
	private ParkapiZps_PortType getServiceZps() throws ServiceException, IOException, ClassNotFoundException {
		LOGGER.debug("[ParkApiServiceHelper::getServiceZps] BEGIN");
		ParkapiZpsServiceLocator serviceLocator = new ParkapiZpsServiceLocator();
		ParkapiZps_PortType port = serviceLocator.getparkapiZps();
		GenericWrapperFactoryBean gwfb = new GenericWrapperFactoryBean();
		LOGGER.debug("url service " + this.urlServiceZps);
		gwfb.setEndPoint(this.urlServiceZps);
		gwfb.setWrappedInterface(ParkapiZps_PortType.class);
		gwfb.setPort(port);
		gwfb.setTokenRetryManager(this.apiManagerServiceHelper.getTokenRetryManagerAxis1());
		this.serviceZps = (ParkapiZps_PortType) gwfb.create();

		LOGGER.debug("[ParkApiServiceHelper::getServiceZps] Service 'ParkapiAreeprotette_PortType' INITIALIZED");
		LOGGER.debug("[ParkApiServiceHelper::getServiceZps] END");

		return this.serviceZps;
	}
	
	
	public String getRicadenzaAreeProtette(String geometria) throws it.csi.idf.idfapi.business.be.exceptions.ServiceException{
		LOGGER.info("[ParkApiServiceHelper::getRicadenzaAreeProtette] BEGIN");
		StringBuilder sb = new StringBuilder();
		try {
			Ricadenza[] r =  this.getServiceAreeProtette().determinaRicadenzaSuAreeProtettePerGeometriaGML(geometria);
			if(r!=null && r.length>0) {
				for (int i = 0; i < r.length; i++) {
					if(r[i].isFlagRicadenzaSignificativa()) {
						if(i!=0) {
							sb.append("; ");
						}
						sb.append(r[i].getCodiceAmministrativo());
						sb.append(" ");
						sb.append(r[i].getNome());
						sb.append(" ");
						sb.append(r[i].getPercentualeDiGeometriaOccupataDalParco());
						sb.append("%");
					}
				}
			}
		} catch (ClassNotFoundException | ServiceException | IOException e) {
			throw new it.csi.idf.idfapi.business.be.exceptions.ServiceException("[Errore PARKAPI getRicadenzaAreeProtette] "+e.getMessage());
		}
		LOGGER.info("[ParkApiServiceHelper::getRicadenzaAreeProtette] END");
		return sb.toString();
	}
	
	public String getRicadenzaNatura2000(String geometria)  throws it.csi.idf.idfapi.business.be.exceptions.ServiceException{
		LOGGER.info("[ParkApiServiceHelper::getRicadenzaNatura2000] BEGIN");
		StringBuilder sb = new StringBuilder();
		ArrayList<String> codZps = new ArrayList<String>();
		Map<String, it.csi.idf.idfapi.business.be.service.helper.dto.Ricadenza> sic = this.getRicadenzaSic(geometria);
		Map<String, it.csi.idf.idfapi.business.be.service.helper.dto.Ricadenza> zps = this.getRicadenzaZps(geometria);
		/*
		try {
			Map<String, it.csi.idf.idfapi.business.be.service.helper.dto.Ricadenza> sic = this.getRicadenzaSic(geometria);
		} catch (it.csi.idf.idfapi.business.be.exceptions.ServiceException e) {
			// TODO Auto-generated catch block
			System.out.println("error -------> sic");
			e.printStackTrace();
		}
		try {
			Map<String, it.csi.idf.idfapi.business.be.service.helper.dto.Ricadenza> zps = this.getRicadenzaZps(geometria);
		} catch (it.csi.idf.idfapi.business.be.exceptions.ServiceException e) {
			// TODO Auto-generated catch block
			System.out.println("error -------> zps");
			e.printStackTrace();
		}
			*/
		for (Map.Entry<String, it.csi.idf.idfapi.business.be.service.helper.dto.Ricadenza> e : sic.entrySet()) {
			if(sb.length()>0) {
				sb.append("; ");
			}
			sb.append(e.getValue().getCodiceAmministrativo());
			sb.append(" ");
			sb.append(e.getValue().getNome());
			sb.append(" ");
			if(zps.containsKey(e.getKey())) {
				sb.append("SIC/ZPS");
				codZps.add(e.getKey());
			}
			else {
				sb.append(e.getValue().getTipologiaSito());
			}
			sb.append(" ");
			sb.append(e.getValue().getPercentualeRicadenza());
			sb.append("%");
		}
		
		for(Map.Entry<String, it.csi.idf.idfapi.business.be.service.helper.dto.Ricadenza> e: zps.entrySet()) {
			if(!codZps.contains(e.getKey())) {
				if(sb.length()>0) {
					sb.append("; ");
				}
				sb.append(e.getValue().getCodiceAmministrativo());
				sb.append(" ");
				sb.append(e.getValue().getNome());
				sb.append(" ");
				sb.append(e.getValue().getTipologiaSito());
				sb.append(" ");
				sb.append(e.getValue().getPercentualeRicadenza());
				sb.append("%");
			}
		}
		
		return sb.toString();
	}
	
	private Map<String, it.csi.idf.idfapi.business.be.service.helper.dto.Ricadenza> getRicadenzaSic(String geometria) throws it.csi.idf.idfapi.business.be.exceptions.ServiceException {
		LOGGER.info("[ParkApiServiceHelper::getRicadenzaSic] BEGIN");
		try {
			return RicadenzaDtoFactory.createMapRicadenzeSic(this.getServiceSic().determinaRicadenzaSuSicPerGeometriaGML(geometria));
		} catch (ClassNotFoundException | ServiceException | IOException e) {
			throw new it.csi.idf.idfapi.business.be.exceptions.ServiceException("[Errore PARKAPI getRicadenzaSic] "+e.getMessage());
		}
		finally {
			LOGGER.info("[ParkApiServiceHelper::getRicadenzaSic] BEGIN");
		}
	}
	
	private Map<String, it.csi.idf.idfapi.business.be.service.helper.dto.Ricadenza> getRicadenzaZps(String geometria) throws it.csi.idf.idfapi.business.be.exceptions.ServiceException {
		LOGGER.info("[ParkApiServiceHelper::getRicadenzaZps] BEGIN");
		try {
			return RicadenzaDtoFactory.createMapRicadenzeZps(this.getServiceZps().determinaRicadenzaSuZpsPerGeometriaGML(geometria));
		} catch (ClassNotFoundException | ServiceException | IOException e) {
			throw new it.csi.idf.idfapi.business.be.exceptions.ServiceException("[Errore PARKAPI getRicadenzaZps] "+e.getMessage());
		}
		finally {
			LOGGER.info("[ParkApiServiceHelper::getRicadenzaZps] BEGIN");
		}
	}

}
