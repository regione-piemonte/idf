/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.service.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.exceptions.ServiceException;
//import it.csi.idf.idfapi.business.be.impl.dao.ActaDao;
import it.csi.idf.idfapi.dto.ApiManagerDto;
import it.doqui.acta.acaris.backofficeservice.BackOfficeServicePort;
import it.doqui.acta.acaris.documentservice.DocumentServicePort;
import it.doqui.acta.acaris.managementservice.ManagementServicePort;
import it.doqui.acta.acaris.multifilingservice.MultifilingServicePort;
import it.doqui.acta.acaris.navigationservice.NavigationServicePort;
import it.doqui.acta.acaris.objectservice.ObjectServicePort;
import it.doqui.acta.acaris.officialbookservice.OfficialBookServicePort;
import it.doqui.acta.acaris.relationshipsservice.RelationshipsServicePort;
import it.doqui.acta.acaris.repositoryservice.RepositoryServicePort;
import it.doqui.acta.acaris.smsservice.SMSServicePort;
import it.doqui.acta.acaris.subjectregistryservice.SubjectRegistryServicePort;
import it.doqui.acta.actasrv.client.AcarisServiceAuthenticationClient;
import it.doqui.acta.actasrv.dto.acaris.type.archive.AcarisRepositoryEntryType;

public class ActaServiceHelper extends AbstractServiceHelper {
	
	private BackOfficeServicePort backOfficeServicePort;
	private ManagementServicePort managementServicePort;
	private MultifilingServicePort multifilingServicePort;
	private NavigationServicePort navigationServicePort;
	private ObjectServicePort objectServicePort;
	private OfficialBookServicePort officialBookServicePort;
	private RelationshipsServicePort relationshipsServicePort;
	private DocumentServicePort documentServicePort;
	private RepositoryServicePort repositoryServicePort;
	private SMSServicePort sMSServicePort;
	private SubjectRegistryServicePort subjectRegistryServicePort;
	
	private ApiManagerServiceHelper apiManagerServiceHelper;
	
//	@Autowired
//	private ActaDao actaDao;
	
	

	/**
	 * @return the actaDao
	 */
//	public ActaDao getActaDao() {
//		return actaDao;
//	}

	/**
	 * @param actaDao the actaDao to set
	 */
//	public void setActaDao(ActaDao actaDao) {
//		this.actaDao = actaDao;
//	}

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

	protected String backoffice = null;
	protected String management = null;
	protected String multifiling = null;
	protected String navigation = null;
	protected String object = null;
	protected String officialbook = null;
	protected String relationships = null;
	protected String document = null;
	protected String repository = null;
	protected String sms = null;
	protected String subjectregistry = null;
	
	public ActaServiceHelper(String backoffice, String management, String multifiling, String navigation, String object,
			String officialbook, String relationships, String document, String repository, String sms,
			String subjectregistry) {

		LOGGER.debug("[ActaServiceHelper::] BEGIN");

		LOGGER.debug("[ActaServiceHelper::] backoffice " + backoffice);
		LOGGER.debug("[ActaServiceHelper::] management " + management);
		LOGGER.debug("[ActaServiceHelper::] multifiling " + multifiling);
		LOGGER.debug("[ActaServiceHelper::] navigation " + navigation);
		LOGGER.debug("[ActaServiceHelper::] object " + object);
		LOGGER.debug("[ActaServiceHelper::] officialbook " + officialbook);
		LOGGER.debug("[ActaServiceHelper::] relationships " + relationships);
		LOGGER.debug("[ActaServiceHelper::] document " + document);
		LOGGER.debug("[ActaServiceHelper::] repository " + repository);
		LOGGER.debug("[ActaServiceHelper::] sms " + sms);
		LOGGER.debug("[ActaServiceHelper::] subjectregistry " + subjectregistry);

		this.backoffice = backoffice;
		this.management = management;
		this.multifiling = multifiling;
		this.navigation = navigation;
		this.object = object;
		this.officialbook = officialbook;
		this.relationships = relationships;
		this.document = document;
		this.repository = repository;
		this.sms = sms;
		this.subjectregistry = subjectregistry;

		
		LOGGER.debug("[ActaServiceHelper::] END");

	}
	
	public BackOfficeServicePort getBackOfficeServicePort() throws ServiceException {
		LOGGER.debug("[ActaServiceHelper:: getBackOfficeServicePort] BEGIN");
		try {
			LOGGER.debug("............ 1");
			ApiManagerDto api = this.apiManagerServiceHelper.getApiManagerConfig().getApiManagerDto();
			LOGGER.debug("............ 2");
			
			this.backOfficeServicePort = AcarisServiceAuthenticationClient.getBackofficeServiceTokenAPI(api.getUrl(),
					backoffice, api.getConsumerKey(), api.getConsumerSecret());
			
			LOGGER.debug("............ 3");
			
			
		} 
//			catch (it.doqui.acta.acaris.backofficeservice.AcarisException e) {
//			e.printStackTrace();
//			final Message message = this.msgMgr.getMessage(MsgCodeEnum.G002);
//			message.replacePlaceholder(Constants.ERR_ACTA + "b");
//			throw new ServiceException(message);
//		}
		catch (Exception e) {
			e.printStackTrace();
			
			LOGGER.debug("[ActaServiceHelper:: getBackOfficeServicePort] ERRRRR "+e.getMessage());
			throw new ServiceException("[Errore ACTA getSubjectRegistryServicePort] "+e.getMessage());
		}

		LOGGER.debug("[ActaServiceHelper:: getBackOfficeServicePort] END");
		return backOfficeServicePort;
	}

	public void setBackOfficeServicePort(BackOfficeServicePort backOfficeServicePort) {
		this.backOfficeServicePort = backOfficeServicePort;
	}
	
	public ManagementServicePort getManagementServicePort() throws ServiceException {
		LOGGER.debug("[ActaServiceHelper:: getManagementServicePort] BEGIN");
		try {
			ApiManagerDto api = this.apiManagerServiceHelper.getApiManagerConfig().getApiManagerDto();
			this.managementServicePort = AcarisServiceAuthenticationClient.getManagementServiceTokenAPI(api.getUrl(),
					management, api.getConsumerKey(), api.getConsumerSecret());
		} 
//		catch (it.doqui.acta.acaris.managementservice.AcarisException e) {
//			e.printStackTrace();
//			LOGGER.debug("[ActaServiceHelper::getManagementServicePort] ERROR --> "+e.getMessage());
//			throw new ServiceException("[Errore ACTA getSubjectRegistryServicePort] "+e.getMessage());
//		}
		catch (Exception e) {
			e.printStackTrace();
			
			LOGGER.debug("[ActaServiceHelper:: getBackOfficeServicePort] ERRRRR "+e.getMessage());
			throw new ServiceException("[Errore ACTA getSubjectRegistryServicePort] "+e.getMessage());
		}
		LOGGER.debug("[ActaServiceHelper:: getManagementServicePort] END");
		return managementServicePort;
	}

	public void setManagementServicePort(ManagementServicePort managementServicePort) {
		this.managementServicePort = managementServicePort;
	}

	public MultifilingServicePort getMultifilingServicePort() throws ServiceException {
		LOGGER.debug("[ActaServiceHelper:: getMultifilingServicePort] BEGIN");
		try {
			ApiManagerDto api = this.apiManagerServiceHelper.getApiManagerConfig().getApiManagerDto();
			this.multifilingServicePort = AcarisServiceAuthenticationClient.getMultifillingServiceTokenAPI(api.getUrl(),
					multifiling, api.getConsumerKey(), api.getConsumerSecret());
		} 
//		catch (it.doqui.acta.acaris.multifilingservice.AcarisException e) {
//			e.printStackTrace();
//			LOGGER.debug("[ActaServiceHelper::getMultifilingServicePort] ERROR --> "+e.getMessage());
//			throw new ServiceException("[Errore ACTA getSubjectRegistryServicePort] "+e.getMessage());
//		}
		catch (Exception e) {
			e.printStackTrace();
			
			LOGGER.debug("[ActaServiceHelper:: getBackOfficeServicePort] ERRRRR "+e.getMessage());
			throw new ServiceException("[Errore ACTA getSubjectRegistryServicePort] "+e.getMessage());
		}
		LOGGER.debug("[ActaServiceHelper:: getMultifilingServicePort] END");
		return multifilingServicePort;
	}

	public void setMultifilingServicePort(MultifilingServicePort multifilingServicePort) {
		this.multifilingServicePort = multifilingServicePort;
	}

	public NavigationServicePort getNavigationServicePort() throws ServiceException {
		LOGGER.debug("[ActaServiceHelper:: getNavigationServicePort] BEGIN");
		try {
			ApiManagerDto api = this.apiManagerServiceHelper.getApiManagerConfig().getApiManagerDto();
			this.navigationServicePort = AcarisServiceAuthenticationClient.getNavigationServiceTokenAPI(api.getUrl(),
					navigation, api.getConsumerKey(), api.getConsumerSecret());
		} 
//		catch (it.doqui.acta.acaris.navigationservice.AcarisException e) {
//			e.printStackTrace();
//			LOGGER.debug("[ActaServiceHelper::getNavigationServicePort] ERROR --> "+e.getMessage());
//			throw new ServiceException("[Errore ACTA getSubjectRegistryServicePort] "+e.getMessage());
//		}
		catch (Exception e) {
			e.printStackTrace();
			
			LOGGER.debug("[ActaServiceHelper:: getBackOfficeServicePort] ERRRRR "+e.getMessage());
			throw new ServiceException("[Errore ACTA getSubjectRegistryServicePort] "+e.getMessage());
		}
		LOGGER.debug("[ActaServiceHelper:: getNavigationServicePort] END");
		return navigationServicePort;
	}

	public void setNavigationServicePort(NavigationServicePort navigationServicePort) {
		this.navigationServicePort = navigationServicePort;
	}

	public ObjectServicePort getObjectServicePort() throws ServiceException {
		LOGGER.debug("[ActaServiceHelper:: getObjectServicePort] BEGIN");
		try {
			ApiManagerDto api = this.apiManagerServiceHelper.getApiManagerConfig().getApiManagerDto();
			this.objectServicePort = AcarisServiceAuthenticationClient.getObjectServiceTokenAPI(api.getUrl(), object,
					api.getConsumerKey(), api.getConsumerSecret(), false);
		} 
//		catch (it.doqui.acta.acaris.objectservice.AcarisException e) {
//			e.printStackTrace();
//			LOGGER.debug("[ActaServiceHelper::getObjectServicePort] ERROR --> "+e.getMessage());
//			throw new ServiceException("[Errore ACTA getSubjectRegistryServicePort] "+e.getMessage());
//		}
		catch (Exception e) {
			e.printStackTrace();
			
			LOGGER.debug("[ActaServiceHelper:: getBackOfficeServicePort] ERRRRR "+e.getMessage());
			throw new ServiceException("[Errore ACTA getSubjectRegistryServicePort] "+e.getMessage());
		}
		LOGGER.debug("[ActaServiceHelper:: getObjectServicePort] END");
		return objectServicePort;
	}

	public void setObjectServicePort(ObjectServicePort objectServicePort) {
		this.objectServicePort = objectServicePort;
	}

	public OfficialBookServicePort getOfficialBookServicePort() throws ServiceException {
		LOGGER.debug("[ActaServiceHelper:: getOfficialBookServicePort] BEGIN");
		try {
			ApiManagerDto api = this.apiManagerServiceHelper.getApiManagerConfig().getApiManagerDto();
			this.officialBookServicePort = AcarisServiceAuthenticationClient.getOfficialBookServiceTokenAPI(
					api.getUrl(), officialbook, api.getConsumerKey(), api.getConsumerSecret());
		} 
//		catch (it.doqui.acta.acaris.officialbookservice.AcarisException e) {
//			e.printStackTrace();
//			LOGGER.debug("[ActaServiceHelper::getOfficialBookServicePort] ERROR --> "+e.getMessage());
//			throw new ServiceException("[Errore ACTA getSubjectRegistryServicePort] "+e.getMessage());
//		}
		catch (Exception e) {	
			e.printStackTrace();
			
			LOGGER.debug("[ActaServiceHelper:: getBackOfficeServicePort] ERRRRR "+e.getMessage());
			throw new ServiceException("[Errore ACTA getSubjectRegistryServicePort] "+e.getMessage());
		}
		LOGGER.debug("[ActaServiceHelper:: getOfficialBookServicePort] END");
		return officialBookServicePort;
	}

	public void setOfficialBookServicePort(OfficialBookServicePort officialBookServicePort) {
		this.officialBookServicePort = officialBookServicePort;
	}

	public RelationshipsServicePort getRelationshipsServicePort() throws ServiceException {
		LOGGER.debug("[ActaServiceHelper:: getRelationshipsServicePort] BEGIN");
		try {
			ApiManagerDto api = this.apiManagerServiceHelper.getApiManagerConfig().getApiManagerDto();
			this.relationshipsServicePort = AcarisServiceAuthenticationClient.getRelationshipsServiceTokenAPI(
					api.getUrl(), relationships, api.getConsumerKey(), api.getConsumerSecret(), false);
		} 
//		catch (it.doqui.acta.acaris.relationshipsservice.AcarisException e) {
//			e.printStackTrace();
//			LOGGER.debug("[ActaServiceHelper::getRelationshipsServicePort] ERROR --> "+e.getMessage());
//			throw new ServiceException("[Errore ACTA getSubjectRegistryServicePort] "+e.getMessage());
//		}
		catch (Exception e) {
			e.printStackTrace();
			
			LOGGER.debug("[ActaServiceHelper:: getBackOfficeServicePort] ERRRRR "+e.getMessage());
			throw new ServiceException("[Errore ACTA getSubjectRegistryServicePort] "+e.getMessage());
		}
		LOGGER.debug("[ActaServiceHelper:: getRelationshipsServicePort] END");
		return relationshipsServicePort;
	}

	public void setRelationshipsServicePort(RelationshipsServicePort relationshipsServicePort) {
		this.relationshipsServicePort = relationshipsServicePort;
	}

	public DocumentServicePort getDocumentServicePort() throws ServiceException {
		LOGGER.debug("[ActaServiceHelper:: getDocumentServicePort] BEGIN");
		try {
			ApiManagerDto api = this.apiManagerServiceHelper.getApiManagerConfig().getApiManagerDto();
			this.documentServicePort = AcarisServiceAuthenticationClient.getDocumentServiceTokenAPI(api.getUrl(),
					document, api.getConsumerKey(), api.getConsumerSecret(), false);
		} 
//		catch (it.doqui.acta.acaris.documentservice.AcarisException e) {
//			e.printStackTrace();
//			LOGGER.debug("[ActaServiceHelper::getDocumentServicePort] ERROR --> "+e.getMessage());
//			throw new ServiceException("[Errore ACTA getSubjectRegistryServicePort] "+e.getMessage());
//		}
		catch (Exception e) {
			e.printStackTrace();
			
			LOGGER.debug("[ActaServiceHelper:: getBackOfficeServicePort] ERRRRR "+e.getMessage());
			throw new ServiceException("[Errore ACTA getSubjectRegistryServicePort] "+e.getMessage());
		}
		LOGGER.debug("[ActaServiceHelper:: getDocumentServicePort] END");
		return documentServicePort;
	}

	public void setDocumentServicePort(DocumentServicePort documentServicePort) {
		this.documentServicePort = documentServicePort;
	}

	public RepositoryServicePort getRepositoryServicePort() throws ServiceException {
		LOGGER.debug("[ActaServiceHelper:: getRepositoryServicePort] BEGIN");
		try {
			
			ApiManagerDto api = this.apiManagerServiceHelper.getApiManagerConfig().getApiManagerDto();
			this.repositoryServicePort = AcarisServiceAuthenticationClient.getRepositoryServiceTokenAPI(api.getUrl(),
					repository, api.getConsumerKey(), api.getConsumerSecret());
		} 
//		catch (it.doqui.acta.acaris.repositoryservice.AcarisException e) {
//			e.printStackTrace();
//			LOGGER.debug("[ActaServiceHelper::getRepositoryServicePort] ERROR --> "+e.getMessage());
//			throw new ServiceException("[Errore ACTA getSubjectRegistryServicePort] "+e.getMessage());
//		}
		catch (Exception e) {
			e.printStackTrace();
			
			LOGGER.debug("[ActaServiceHelper:: getBackOfficeServicePort] ERRRRR "+e.getMessage());
			throw new ServiceException("[Errore ACTA getSubjectRegistryServicePort] "+e.getMessage());
		}

		LOGGER.debug("[ActaServiceHelper:: getRepositoryServicePort] END");
		return repositoryServicePort;
	}

	public void setRepositoryServicePort(RepositoryServicePort repositoryServicePort) {
		this.repositoryServicePort = repositoryServicePort;
	}

	public SMSServicePort getsMSServicePort() throws ServiceException {
		LOGGER.debug("[ActaServiceHelper:: getsMSServicePort] BEGIN");
		try {
			ApiManagerDto api = this.apiManagerServiceHelper.getApiManagerConfig().getApiManagerDto();
			this.sMSServicePort = AcarisServiceAuthenticationClient.getSmsServiceTokenAPI(api.getUrl(), sms,
					api.getConsumerKey(), api.getConsumerSecret());
		} 
//		catch (it.doqui.acta.acaris.smsservice.AcarisException e) {
//			e.printStackTrace();
//			LOGGER.debug("[ActaServiceHelper::getsMSServicePort] ERROR --> "+e.getMessage());
//			throw new ServiceException("[Errore ACTA getSubjectRegistryServicePort] "+e.getMessage());
//		}
		catch (Exception e) {
			e.printStackTrace();
			
			LOGGER.debug("[ActaServiceHelper:: getBackOfficeServicePort] ERRRRR "+e.getMessage());
			throw new ServiceException("[Errore ACTA getSubjectRegistryServicePort] "+e.getMessage());
		}
		LOGGER.debug("[ActaServiceHelper:: getsMSServicePort] END");
		return sMSServicePort;
	}

	public void setsMSServicePort(SMSServicePort sMSServicePort) {
		this.sMSServicePort = sMSServicePort;
	}

	public SubjectRegistryServicePort getSubjectRegistryServicePort() throws ServiceException {
		LOGGER.debug("[ActaServiceHelper:: getSubjectRegistryServicePort] BEGIN");
		try {
			ApiManagerDto api = this.apiManagerServiceHelper.getApiManagerConfig().getApiManagerDto();
			this.subjectRegistryServicePort = AcarisServiceAuthenticationClient.getSubjectRegistryServiceTokenAPI(
					api.getUrl(), subjectregistry, api.getConsumerKey(), api.getConsumerSecret());
		} 
//		catch (it.doqui.acta.acaris.subjectregistryservice.AcarisException e) {
//			e.printStackTrace();
//			LOGGER.debug("[ActaServiceHelper::getSubjectRegistryServicePort] ERROR --> "+e.getMessage());
//			throw new ServiceException("[Errore ACTA getSubjectRegistryServicePort] "+e.getMessage());
//		}
		catch (Exception e) {
			e.printStackTrace();
			
			LOGGER.debug("[ActaServiceHelper:: getBackOfficeServicePort] ERRRRR "+e.getMessage());
			throw new ServiceException("[Errore ACTA getSubjectRegistryServicePort] "+e.getMessage());
		}
		LOGGER.debug("[ActaServiceHelper:: getSubjectRegistryServicePort] END");
		return subjectRegistryServicePort;
	}

	public void setSubjectRegistryServicePort(SubjectRegistryServicePort subjectRegistryServicePort) {
		this.subjectRegistryServicePort = subjectRegistryServicePort;
	}
	
	public List<String> testActaRepo()throws ServiceException{
		LOGGER.debug("[ActaServiceHelper:: testActaRepo] BEGIN");
		List<String> result = new ArrayList<String>();
		try {
			
			AcarisRepositoryEntryType[] elencoRepository = this.getRepositoryServicePort().getRepositories();
			if(elencoRepository!=null) {
				for (AcarisRepositoryEntryType dto : elencoRepository) {
					result.add(dto.getRepositoryName());
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			
			LOGGER.debug("[ActaServiceHelper:: testActaRepo] ERRRRR "+e.getMessage());
			throw new ServiceException("[Errore ACTA testActaRepo] "+e.getMessage());
		}
		return result;
	}
	
}
