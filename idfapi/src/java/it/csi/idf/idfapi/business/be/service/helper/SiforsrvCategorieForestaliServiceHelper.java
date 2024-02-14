/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.service.helper;

import java.io.IOException;

import javax.xml.rpc.ServiceException;

import it.csi.idf.idfapi.util.service.integration.sifor.categorieforestali.CategoriaForestale;
import it.csi.idf.idfapi.util.service.integration.sifor.categorieforestali.SiforsrvCategorieforestaliServiceLocator;
import it.csi.idf.idfapi.util.service.integration.sifor.categorieforestali.SiforsrvCategorieforestali_PortType;
import it.csi.wso2.apiman.oauth2.helper.GenericWrapperFactoryBean;

public class SiforsrvCategorieForestaliServiceHelper extends AbstractServiceHelper {
	
	private SiforsrvCategorieforestali_PortType service;
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

	protected String urlService = null;
	
	public SiforsrvCategorieForestaliServiceHelper(String urlService) {
		this.urlService = urlService;
	}
	
	
	private SiforsrvCategorieforestali_PortType getService() throws ServiceException, IOException, ClassNotFoundException {
		LOGGER.debug("[SiforsrvCategorieForestaliServiceHelper::getService] BEGIN");
		SiforsrvCategorieforestaliServiceLocator serviceLocator = new SiforsrvCategorieforestaliServiceLocator();
		SiforsrvCategorieforestali_PortType port = serviceLocator.getsiforsrvCategorieforestali();
		GenericWrapperFactoryBean gwfb = new GenericWrapperFactoryBean();
		LOGGER.debug("url service " + this.urlService);
		gwfb.setEndPoint(this.urlService);
		gwfb.setWrappedInterface(SiforsrvCategorieforestali_PortType.class);
		gwfb.setPort(port);
		gwfb.setTokenRetryManager(this.apiManagerServiceHelper.getTokenRetryManagerAxis1());
		this.service = (SiforsrvCategorieforestali_PortType) gwfb.create();

		LOGGER.debug("[SiforsrvCategorieForestaliServiceHelper::getService] Service 'SiforsrvCategorieforestali_PortType' INITIALIZED");
		LOGGER.debug("[SiforsrvCategorieForestaliServiceHelper::getService] END");

		return this.service;
	}
	
	public String testRicadenza() throws it.csi.idf.idfapi.business.be.exceptions.ServiceException {
		try {
			CategoriaForestale[] list = getService().cercaTutteCategorieForestali();
			StringBuffer sb = new StringBuffer();
			for (CategoriaForestale dto : list) {
				sb.append(dto.getDescrizione());
				sb.append(" - ");
			}
			return sb.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new it.csi.idf.idfapi.business.be.exceptions.ServiceException("Errore RICADENZA "+e.getMessage());
		}
		
	}

}
