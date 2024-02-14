/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.service.helper;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import it.csi.idf.idfapi.business.be.exceptions.ServiceException;
import it.csi.idf.idfapi.business.be.service.helper.dto.MetadatiIndexDelega;
import it.csi.idf.idfapi.business.be.service.helper.dto.MetadatiIndexPfa;
import it.csi.idf.idfapi.business.be.service.helper.dto.MetadatiIndexPrimpa;
import it.csi.idf.idfapi.business.be.service.helper.dto.MetadatiIndexTrasformazioni;
import it.csi.idf.idfapi.business.be.service.helper.dto.MetadatiIndexVincolo;
import it.csi.idf.idfapi.business.be.service.helper.factory.IndexDtoFactory;
import it.csi.idf.idfapi.util.Constants;
import it.csi.idf.idfapi.util.ConvertUtil;
import it.csi.idf.idfapi.util.DateUtil;
import it.doqui.index.ecmengine.client.webservices.dto.Node;
import it.doqui.index.ecmengine.client.webservices.dto.OperationContext;
import it.doqui.index.ecmengine.client.webservices.dto.engine.management.Content;
import it.doqui.index.ecmengine.client.webservices.dto.engine.management.Mimetype;
import it.doqui.index.ecmengine.client.webservices.dto.engine.management.Property;
import it.doqui.index.ecmengine.client.webservices.dto.engine.search.ResultContent;
import it.doqui.index.ecmengine.client.webservices.dto.engine.search.SearchParams;
import it.doqui.index.ecmengine.client.webservices.dto.engine.search.SearchResponse;
import it.doqui.index.ecmengine.client.webservices.engine.EcmEngineWebServiceDelegate;
import it.doqui.index.ecmengine.client.webservices.engine.EcmEngineWebServiceDelegateServiceLocator;
import it.doqui.index.ecmengine.client.webservices.exception.InvalidParameterException;
import it.doqui.index.ecmengine.client.webservices.exception.publishing.InvalidCredentialsException;
import it.doqui.index.ecmengine.client.webservices.exception.publishing.NoDataExtractedException;

public class IndexServiceHelper extends AbstractServiceHelper {

	
	EcmEngineWebServiceDelegate ecmEngineWebServiceDelegate;

	/**
	 * @return the ecmEngineWebServiceDelegate
	 */
	public EcmEngineWebServiceDelegate getEcmEngineWebServiceDelegate() {
		return ecmEngineWebServiceDelegate;
	}

	/**
	 * @param ecmEngineWebServiceDelegate 
	 *            the ecmEngineWebServiceDelegate to set
	 */
	public void setEcmEngineWebServiceDelegate(EcmEngineWebServiceDelegate ecmEngineWebServiceDelegate) {
		this.ecmEngineWebServiceDelegate = ecmEngineWebServiceDelegate;
	}

	public IndexServiceHelper(String url) {
		EcmEngineWebServiceDelegateServiceLocator ecmengineLocator = new EcmEngineWebServiceDelegateServiceLocator();

		try {
			ecmEngineWebServiceDelegate = ecmengineLocator.getEcmEngineManagement(new URL(url));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (javax.xml.rpc.ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getSignatureType(byte[] doc) throws InvalidCredentialsException, InvalidParameterException, RemoteException {
		return this.ecmEngineWebServiceDelegate.getSignatureType(doc, indexGetOperationContext(Constants.INDEX_USERNAME_ADMIN));
	}

	private OperationContext indexGetOperationContext(String user) {
		LOGGER.debug("[IndexServiceHelper::indexGetOperationContext] BEGIN");
		final OperationContext ctx = new OperationContext();
		ctx.setUsername(user);
		ctx.setPassword(Constants.INDEX_PSW);
		ctx.setNomeFisico(Constants.INDEX_UTENTE);
		ctx.setFruitore(Constants.INDEX_FRUITORE);
		ctx.setRepository(Constants.INDEX_REPOSITORY);
		LOGGER.debug("[IndexServiceHelper::indexGetOperationContext] END");
		return ctx;
	}

	private Content indexGetContent(String fileName, String typeName, String prefixName) throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexGetContent] BEGIN");
		final Content c = new Content();
		c.setContentPropertyPrefixedName(Constants.INDEX_PREFIX_NAME);
		c.setPrefixedName(prefixName + fileName);
		c.setParentAssocTypePrefixedName(Constants.INDEX_PREFIX_CONTAINS);
		c.setTypePrefixedName(typeName);
		c.setMimeType(this.indexGetMimeType(fileName));
		c.setEncoding(Constants.INDEX_ENCODING);
		LOGGER.debug("[IndexServiceHelper::indexGetContent] END");
		return c;
	}

	private Content indexGetContentFolder(String folderName) {
		LOGGER.debug("[IndexServiceHelper::indexGetContentFolder] BEGIN");
		final Content myFolder = new Content();
		myFolder.setPrefixedName(Constants.INDEX_DEFAULT_PREFIX + folderName);
		myFolder.setParentAssocTypePrefixedName(Constants.INDEX_PREFIX_CONTAINS);
		myFolder.setModelPrefixedName(Constants.INDEX_PREFIX_MODEL);
		myFolder.setTypePrefixedName(Constants.INDEX_PREFIX_FOLDER);
		final Property p = new Property();
		p.setPrefixedName(Constants.INDEX_PREFIX_NAME_SHORT);
		p.setDataType("text");
		p.setValues(new String[] { folderName });
		myFolder.setProperties(new Property[] { p });
		LOGGER.debug("[IndexServiceHelper::indexGetContentFolder] END");
		return myFolder;
	}

	private String indexGetMimeType(String fileName) throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexGetMimeType] BEGIN");
		final String estensione = StringUtils.substringAfterLast(fileName, ".");
		final Mimetype mt = new Mimetype();
		mt.setFileExtension(estensione);
		Mimetype[] mime = null;
		try {
			mime = this.getEcmEngineWebServiceDelegate().getMimetype(mt);
		} catch (InvalidParameterException e) {
			throw new ServiceException("[Errore INDEX indexGetMimeType InvalidParameterException] " + e.getMessage());
		} catch (RemoteException e) {
			throw new ServiceException("[Errore INDEX indexGetMimeType RemoteException] " + e.getMessage());
		} catch (Exception e) {
			throw new ServiceException("[Errore INDEX indexGetMimeType Exception] " + e.getMessage());
		}

		if (mime != null && mime.length > 0) {
			return mime[0].getMimetype();
		} else {
			return Constants.MIMETYPE_DEFAULT;
		}
	}

	private Content indexCreateContent(MetadatiIndexPfa metadati, String fileName, byte[] file) throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexGetContent] BEGIN");
		final Content c = this.indexGetContent(fileName, Constants.INDEX_TYPE_ALLEGATO_PFA, Constants.INDEX_PREFIX_PFA);
		c.setModelPrefixedName(Constants.INDEX_PREFIX_MODEL_PFA);
		c.setProperties(IndexDtoFactory.createContentProperties(metadati));
		c.setContent(file);
		LOGGER.debug("[IndexServiceHelper::indexGetContent] END");
		return c;
	}

	private Content indexCreateContent(MetadatiIndexPrimpa metadati, String fileName, byte[] file)
			throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexGetContent] BEGIN");
		final Content c = this.indexGetContent(fileName, Constants.INDEX_TYPE_ALLEGATO_PRIMPA, Constants.INDEX_PREFIX_PRIMPA);
		c.setModelPrefixedName(Constants.INDEX_PREFIX_MODEL_PRIMPA);
		c.setProperties(IndexDtoFactory.createContentProperties(metadati));
		c.setContent(file);
		LOGGER.debug("[IndexServiceHelper::indexGetContent] END");
		return c;
	}

	private Content indexCreateContent(MetadatiIndexTrasformazioni metadati, String fileName, byte[] file)
			throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexGetContent] BEGIN");
		final Content c = this.indexGetContent(fileName, Constants.INDEX_TYPE_ALLEGATO_TRASFORMAZIONI,
				Constants.INDEX_PREFIX_TRASFORMAZIONI);
		
		c.setModelPrefixedName(Constants.INDEX_PREFIX_MODEL_TRASFORMAZIONI);
		
		c.setProperties(IndexDtoFactory.createContentProperties(metadati));
		c.setContent(file);
		LOGGER.debug("[IndexServiceHelper::indexGetContent] END");
		return c;
	}
	
	private Content indexCreateContent(MetadatiIndexVincolo metadati, String fileName, byte[] file)
			throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexGetContent] BEGIN");
		final Content c = this.indexGetContent(fileName, Constants.INDEX_TYPE_ALLEGATO_VINCOLO,
				Constants.INDEX_PREFIX_VINCOLO);
		
		c.setModelPrefixedName(Constants.INDEX_PREFIX_MODEL_VINCOLO);
		
		c.setProperties(IndexDtoFactory.createContentProperties(metadati));
		c.setContent(file);
		LOGGER.debug("[IndexServiceHelper::indexGetContent] END");
		return c;

	}
	
	private Content indexCreateContent(MetadatiIndexDelega metadati, String fileName, byte[] file)
			throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexGetContent] BEGIN");
		final Content c = this.indexGetContent(fileName, Constants.INDEX_TYPE_ALLEGATO_DELEGHE,
				Constants.INDEX_PREFIX_DELEGHE);
		
		c.setModelPrefixedName(Constants.INDEX_PREFIX_MODEL_DELEGHE);
		
		c.setProperties(IndexDtoFactory.createContentProperties(metadati));
		c.setContent(file);
		LOGGER.debug("[IndexServiceHelper::indexGetContent] END");
		return c;

	}

	private String indexGetRootFolder(String rootFolderName) throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexGetRootFolder] BEGIN");
		LOGGER.info("[IndexServiceHelper::indexGetRootFolder] rootFolderName: " + rootFolderName);
		try {
			final SearchParams params = new SearchParams();

			params.setLimit(1);

			final StringBuilder query = new StringBuilder();
			query.append(rootFolderName);

			params.setXPathQuery(query.toString());
			return this.ecmEngineWebServiceDelegate.nodeExists(params,
					indexGetOperationContext(Constants.INDEX_USERNAME_ADMIN));
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("[Errore INDEX indexGetRootFolder Exception] " + e.getMessage());
		} finally {
			LOGGER.debug("[IndexServiceHelper::indexGetRootFolder] END");
		}
	}

	private String indexSearchFolder(SearchParams params, String folderName, String uidParent) throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexSearchFolder] BEGIN");

		LOGGER.debug("[IndexServiceHelper::indexSearchFolder] folderName " + folderName);
		LOGGER.debug("[IndexServiceHelper::indexSearchFolder] uidParent " + uidParent);

		System.out.println("[IndexServiceHelper::indexSearchFolder] folderName " + folderName);
		System.out.println("[IndexServiceHelper::indexSearchFolder] uidParent " + uidParent);
		
		try {
			LOGGER.info("[IndexServiceHelper::this.getEcmEngineWebServiceDelegate()] *"
					+ this.getEcmEngineWebServiceDelegate() + "*");
			return this.getEcmEngineWebServiceDelegate().nodeExists(params,
					indexGetOperationContext(Constants.INDEX_USERNAME_ADMIN));
		} catch (NoDataExtractedException e) {
			LOGGER.error("[IndexServiceHelper::indexSearchFolder] ERROR: " + e.getMessage(), e);

			if (uidParent == null) {
				throw new ServiceException("[Errore INDEX indexSearchFolder uidParent null ] " + e.getMessage());
			} else {
				return this.indexCreateFolder(folderName, uidParent);
			}
		} catch (RuntimeException e) {
			LOGGER.error("[IndexServiceHelper::indexSearchFolder] ERROR RUNTIME: " + e.getMessage(), e);
			throw new ServiceException("[Errore INDEX indexGetMimeType RuntimeException] " + e.getMessage());
		} catch (Exception e) {
			LOGGER.error("[IndexServiceHelper::indexSearchFolder] ERROR GENERICO: " + e.getMessage(), e);
			throw new ServiceException("[Errore INDEX indexSearchFolder Exception] " + e.getMessage());
		} finally {
			LOGGER.debug("[IndexServiceHelper::indexSearchFolder] END");
		}
	}

	public void createFolder(MetadatiIndexDelega metadatiIndexDelega) throws ServiceException {

		this.indexCreateFolder(metadatiIndexDelega.getIdConfigUtente(),this.indexGetRootFolder(Constants.INDEX_ROOT_DELEGHE));

	}


	private String indexCreateFolder(String folderName, String uidParent) throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexCreateFolder] BEGIN");

		String uidFolder = null;
		try {
			uidFolder = this.getEcmEngineWebServiceDelegate()
					.createContent(new Node(uidParent), this.indexGetContentFolder(folderName),
							this.indexGetOperationContext(Constants.INDEX_USERNAME_ADMIN))
					.getUid();
		} catch (Exception e) {
			LOGGER.error("[IndexServiceHelper::indexCreateFolder] ERROR GENERICO: " + e.getMessage(), e);
			throw new ServiceException("[Errore INDEX indexCreateFolder Exception] " + e.getMessage());
		}

		LOGGER.debug("[IndexServiceHelper::indexCreateFolder] END");

		return uidFolder;
	}

	public String indexGetFolder(MetadatiIndexPfa metadati) throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexGetFolder] BEGIN");

		try {

			final String annoCorrente = ConvertUtil.convertToString(DateUtil.getAnnoCorrente());
			final StringBuilder luceneQuery = new StringBuilder();
			// Path /app:company_home/cm:pfa/ cm:<anno>/cm:<idPfa>/

			luceneQuery.append(Constants.INDEX_ROOT_PFA).append(Constants.INDEX_FOLDER_SUFFIX).append(annoCorrente);

			String uidFolderAnno = this.indexSearchFolder(
					IndexDtoFactory.createSearchParamsForIndexFolder(luceneQuery.toString()), annoCorrente,
					this.indexGetRootFolder(Constants.INDEX_ROOT_PFA));

			luceneQuery.append(Constants.INDEX_FOLDER_SUFFIX);
			luceneQuery.append(metadati.getIdPfa());

			String uidFolderIdPfa = this.indexSearchFolder(
					IndexDtoFactory.createSearchParamsForIndexFolder(luceneQuery.toString()), metadati.getIdPfa(),
					uidFolderAnno);

			String uidFolder = null;

			if (metadati.isNotifica()) {

				// Viene creata la dir "INTERVENTO" (nome fisso)
//				StringBuilder luceneQueryFolderIntervento = new StringBuilder(luceneQuery.toString());
				luceneQuery.append(Constants.INDEX_FOLDER_SUFFIX);
				luceneQuery.append(MetadatiIndexPfa.FOLDER_INTERVENTI);

				uidFolder = this.indexSearchFolder(
						IndexDtoFactory.createSearchParamsForIndexFolder(luceneQuery.toString()),
						MetadatiIndexPfa.FOLDER_INTERVENTI, uidFolderIdPfa);

				
				// Viene create la dir con ID INTEVENTO
//				StringBuilder luceneQueryIdIntervento = new StringBuilder(luceneQuery.toString());
				luceneQuery.append(Constants.INDEX_FOLDER_SUFFIX);
				luceneQuery.append(metadati.getIdIntervento());

				uidFolder = this.indexSearchFolder(
						IndexDtoFactory.createSearchParamsForIndexFolder(luceneQuery.toString()),
						metadati.getIdIntervento(), uidFolder);

			}

			else {
				uidFolder = uidFolderIdPfa;
			}

			luceneQuery.append(Constants.INDEX_FOLDER_SUFFIX);
			luceneQuery.append(metadati.getIdTipoAllegato());

			String uidFolderIdTipoAllegato = this.indexSearchFolder(
					IndexDtoFactory.createSearchParamsForIndexFolder(luceneQuery.toString()),
					metadati.getIdTipoAllegato(), uidFolder);

			return uidFolderIdTipoAllegato;
		} catch (Exception e) {
			LOGGER.error("[IndexServiceHelper::indexGetFolder] ERROR GENERICO: " + e.getMessage(), e);
			throw new ServiceException("[Errore INDEX indexGetFolder Exception] " + e.getMessage());

		} finally {
			LOGGER.debug("[IndexServiceHelper::indexGetFolder] END");
		}
	}

	public String indexGetFolder(MetadatiIndexPrimpa metadati) throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexGetFolder] BEGIN");

		try {

			final StringBuilder luceneQuery = new StringBuilder();
			// Path /app:company_home/cm:primpa/ cm:<idistanza>/

			final String annoCorrente = ConvertUtil.convertToString(DateUtil.getAnnoCorrente());

			luceneQuery.append(Constants.INDEX_ROOT_PRIMPA);
			luceneQuery.append(Constants.INDEX_FOLDER_SUFFIX).append(Constants.INDEX_ROOT_TAGLI);

			String uiFolderTagli = this.indexSearchFolder(
					IndexDtoFactory.createSearchParamsForIndexFolder(luceneQuery.toString()), Constants.INDEX_ROOT_TAGLI,
					this.indexGetRootFolder(Constants.INDEX_ROOT_PRIMPA));

			luceneQuery.append(Constants.INDEX_FOLDER_SUFFIX);
			luceneQuery.append(metadati.getIdIntervento());

			String uidFolderIdIntervento = this.indexSearchFolder(
					IndexDtoFactory.createSearchParamsForIndexFolder(luceneQuery.toString()),
					metadati.getIdIntervento(), uiFolderTagli);


			luceneQuery.append(Constants.INDEX_FOLDER_SUFFIX);
			luceneQuery.append(metadati.getIdTipoAllegato());

			String uidFolderIdTipoAllegato = this.indexSearchFolder(
					IndexDtoFactory.createSearchParamsForIndexFolder(luceneQuery.toString()),
					metadati.getIdTipoAllegato(), uidFolderIdIntervento);


/*			luceneQuery.append(Constants.INDEX_ROOT_PRIMPA).append(Constants.INDEX_FOLDER_SUFFIX)
					.append(metadati.getIdIstanza());

			String uidFolderIdIstanza = this.indexSearchFolder(
					IndexDtoFactory.createSearchParamsForIndexFolder(luceneQuery.toString()), metadati.getIdIntervento(),
					this.indexGetRootFolder(Constants.INDEX_ROOT_PRIMPA));*/

			return uidFolderIdTipoAllegato;

		} catch (Exception e) {
			LOGGER.error("[IndexServiceHelper::indexGetFolder] ERROR GENERICO: " + e.getMessage(), e);
			throw new ServiceException("[Errore INDEX indexGetFolder Exception] " + e.getMessage());

		} finally {
			LOGGER.debug("[IndexServiceHelper::indexGetFolder] END");
		}
	}

	public String indexGetFolder(MetadatiIndexTrasformazioni metadati) throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexGetFolder] BEGIN");

		try {

			// Path /app:company_home/cm:trasformazioni/
			// cm:<anno>/cm:<idIntervento/cm:<anno>/cm:<idTipoAllegato>/

			final String annoCorrente = ConvertUtil.convertToString(DateUtil.getAnnoCorrente());
			final StringBuilder luceneQuery = new StringBuilder();

			luceneQuery.append(Constants.INDEX_ROOT_TRASFORMAZIONI).append(Constants.INDEX_FOLDER_SUFFIX)
					.append(annoCorrente);

			String uidFolderAnno = this.indexSearchFolder(
					IndexDtoFactory.createSearchParamsForIndexFolder(luceneQuery.toString()), annoCorrente,
					this.indexGetRootFolder(Constants.INDEX_ROOT_TRASFORMAZIONI));

			luceneQuery.append(Constants.INDEX_FOLDER_SUFFIX);
			luceneQuery.append(metadati.getIdIntervento());

			String uidFolderIdIntervento = this.indexSearchFolder(
					IndexDtoFactory.createSearchParamsForIndexFolder(luceneQuery.toString()),
					metadati.getIdIntervento(), uidFolderAnno);

			luceneQuery.append(Constants.INDEX_FOLDER_SUFFIX);
			luceneQuery.append(metadati.getIdTipoAllegato());

			String uidFolderIdTipoAllegato = this.indexSearchFolder(
					IndexDtoFactory.createSearchParamsForIndexFolder(luceneQuery.toString()),
					metadati.getIdTipoAllegato(), uidFolderIdIntervento);

			return uidFolderIdTipoAllegato;

		} catch (Exception e) {
			LOGGER.error("[IndexServiceHelper::indexGetFolder] ERROR GENERICO: " + e.getMessage(), e);
			throw new ServiceException("[Errore INDEX indexGetFolder Exception] " + e.getMessage());

		} finally {
			LOGGER.debug("[IndexServiceHelper::indexGetFolder] END");
		}
	}
	
	public String indexGetFolder(MetadatiIndexVincolo metadati) throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexGetFolder] BEGIN");

		try {

			// Path /app:company_home/cm:vincoloIdrogeologico/
			// cm:<anno>/cm:<idIntervento/cm:<anno>/cm:<idTipoAllegato>/

			final String annoCorrente = ConvertUtil.convertToString(DateUtil.getAnnoCorrente());
			final StringBuilder luceneQuery = new StringBuilder();

			luceneQuery.append(Constants.INDEX_ROOT_VINCOLO).append(Constants.INDEX_FOLDER_SUFFIX)
					.append(annoCorrente);

			String uidFolderAnno = this.indexSearchFolder(
					IndexDtoFactory.createSearchParamsForIndexFolder(luceneQuery.toString()), annoCorrente,
					this.indexGetRootFolder(Constants.INDEX_ROOT_VINCOLO));

			luceneQuery.append(Constants.INDEX_FOLDER_SUFFIX);
			luceneQuery.append(metadati.getIdIntervento());

			String uidFolderIdIntervento = this.indexSearchFolder(
					IndexDtoFactory.createSearchParamsForIndexFolder(luceneQuery.toString()),
					metadati.getIdIntervento(), uidFolderAnno);

			luceneQuery.append(Constants.INDEX_FOLDER_SUFFIX);
			luceneQuery.append(metadati.getIdTipoAllegato());

			String uidFolderIdTipoAllegato = this.indexSearchFolder(
					IndexDtoFactory.createSearchParamsForIndexFolder(luceneQuery.toString()),
					metadati.getIdTipoAllegato(), uidFolderIdIntervento);

			return uidFolderIdTipoAllegato;

		} catch (Exception e) {
			LOGGER.error("[IndexServiceHelper::indexGetFolder] ERROR GENERICO: " + e.getMessage(), e);
			throw new ServiceException("[Errore INDEX indexGetFolder Exception] " + e.getMessage());

		} finally {
			LOGGER.debug("[IndexServiceHelper::indexGetFolder] END");
		}
	}
	
	public String indexGetFolder(MetadatiIndexDelega metadati) throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexGetFolder] BEGIN");

		try {
			// Path /app:company_home/cm:deleghe/cm:<IdConfigUtente>
			final StringBuilder luceneQuery = new StringBuilder();

			luceneQuery.append(Constants.INDEX_ROOT_DELEGHE);
			luceneQuery.append(Constants.INDEX_FOLDER_SUFFIX);
			luceneQuery.append(metadati.getIdConfigUtente());

			String uidFolderIdConfigUtente = this.indexSearchFolder(
					IndexDtoFactory.createSearchParamsForIndexFolder(luceneQuery.toString()),
					metadati.getIdConfigUtente(),this.indexGetRootFolder(Constants.INDEX_ROOT_DELEGHE));
			

			return uidFolderIdConfigUtente;

		} catch (Exception e) {
			LOGGER.error("[IndexServiceHelper::indexGetFolder] ERROR GENERICO: " + e.getMessage(), e);
			throw new ServiceException("[Errore INDEX indexGetFolder Exception] " + e.getMessage());

		} finally {
			LOGGER.debug("[IndexServiceHelper::indexGetFolder] END");
		}
	}

	public String indexUploadFile(String fileName, byte[] file, MetadatiIndexPfa metadati) throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexUploadFile] BEGIN");
		String uidFile = null;
		try {
			final Node n = new Node(this.indexGetFolder(metadati));
			final Content c = this.indexCreateContent(metadati, fileName, file);
			final OperationContext oc = this.indexGetOperationContext(Constants.INDEX_USERNAME_ADMIN);
			final Node nodoFile = this.ecmEngineWebServiceDelegate.createContent(n, c, oc);

			uidFile = nodoFile.getUid();
		} catch (Exception e) {
			LOGGER.error("[IndexServiceHelper::indexUploadFile] ERROR GENERICO: " + e.getMessage(), e);
			throw new ServiceException("[Errore INDEX indexUploadFile Exception] " + e.getMessage());
		} finally {
			LOGGER.debug("[IndexServiceHelper::indexUploadFile] END");
		}
		return uidFile;
	}

	public String indexUploadFile(String fileName, byte[] file, MetadatiIndexPrimpa metadati) throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexUploadFile] BEGIN");
		String uidFile = null;
		try {
			final Node n = new Node(this.indexGetFolder(metadati));
			final Content c = this.indexCreateContent(metadati, fileName, file);
			final OperationContext oc = this.indexGetOperationContext(Constants.INDEX_USERNAME_ADMIN);
			final Node nodoFile = this.ecmEngineWebServiceDelegate.createContent(n, c, oc);

			uidFile = nodoFile.getUid();
		} catch (Exception e) {
			LOGGER.error("[IndexServiceHelper::indexUploadFile] ERRORE GENERICO: " + e.getMessage(), e);
			throw new ServiceException("[Errore INDEX indexUploadFile Exception] " + e.getMessage());
		} finally {
			LOGGER.debug("[IndexServiceHelper::indexUploadFile] END");
		}
		return uidFile;
	}

	public String indexUploadFile(String fileName, byte[] file, MetadatiIndexTrasformazioni metadati)
			throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexUploadFile] BEGIN");
		String uidFile = null;
		try {
			final Node n = new Node(this.indexGetFolder(metadati));
			final Content c = this.indexCreateContent(metadati, fileName, file);
			final OperationContext oc = this.indexGetOperationContext(Constants.INDEX_USERNAME_ADMIN);
			final Node nodoFile = this.ecmEngineWebServiceDelegate.createContent(n, c, oc);

			uidFile = nodoFile.getUid();
		} catch (Exception e) {
			LOGGER.error("[IndexServiceHelper::indexUploadFile] ERROR GENERICO: " + e.getMessage(), e);
			throw new ServiceException("[Errore INDEX indexUploadFile Exception] " + e.getMessage());
		} finally {
			LOGGER.debug("[IndexServiceHelper::indexUploadFile] END");
		}
		return uidFile;
	}
	
	public String indexUploadFile(String fileName, byte[] file, MetadatiIndexVincolo metadati)
			throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexUploadFile] BEGIN");
		String uidFile = null;
		try {
			final Node n = new Node(this.indexGetFolder(metadati));
			final Content c = this.indexCreateContent(metadati, fileName, file);
			final OperationContext oc = this.indexGetOperationContext(Constants.INDEX_USERNAME_ADMIN);
			final Node nodoFile = this.ecmEngineWebServiceDelegate.createContent(n, c, oc);

			uidFile = nodoFile.getUid();
		} catch (Exception e) {
			LOGGER.error("[IndexServiceHelper::indexUploadFile] ERROR GENERICO: " + e.getMessage(), e);
			throw new ServiceException("[Errore INDEX indexUploadFile Exception] " + e.getMessage());
		} finally {
			LOGGER.debug("[IndexServiceHelper::indexUploadFile] END");
		}
		return uidFile;
	}
	
	//*********** start test
	
	public String indexUploadFile(String fileName, byte[] file, MetadatiIndexDelega metadati)
			throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexUploadFile] BEGIN");
		String uidFile = null;
		try {
			final Node n = new Node(this.indexGetFolder(metadati));
			final Content c = this.indexCreateContent(metadati, fileName, file);
			final OperationContext oc = this.indexGetOperationContext(Constants.INDEX_USERNAME_ADMIN);
			final Node nodoFile = this.ecmEngineWebServiceDelegate.createContent(n, c, oc);

			uidFile = nodoFile.getUid();
		} catch (Exception e) {
			LOGGER.error("[IndexServiceHelper::indexUploadFile] ERROR GENERICO: " + e.getMessage(), e);
			throw new ServiceException("[Errore INDEX indexUploadFile Exception] " + e.getMessage());
		} finally {
			LOGGER.debug("[IndexServiceHelper::indexUploadFile] END");
		}
		return uidFile;
	}
	
	//************ end test

	public String indexDeleteFile(String uid) throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexDeleteFile] BEGIN");
		try {
			if (StringUtils.isNotEmpty(uid)) {
				this.getEcmEngineWebServiceDelegate().deleteContent(new Node(uid),
						this.indexGetOperationContext(Constants.INDEX_USERNAME_ADMIN));
			}

			return uid;
		} catch (Exception e) {
			LOGGER.error("[IndexServiceHelper::indexDeleteFile] ERROR GENERICO: " + e.getMessage(), e);
			throw new ServiceException("[Errore INDEX indexDeleteFile Exception] " + e.getMessage());
		} finally {
			LOGGER.debug("[IndexServiceHelper::indexDeleteFile] END");
		}
	}

	public void indexDeleteFileByIdAllegato(String cmName, String idAllegato) throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexDeleteFileByIdAllegato] BEGIN");
		String uuidd = this.indexGetUidAllegato(cmName, idAllegato);
		String k = this.indexDeleteFile(uuidd);
		LOGGER.debug("[IndexServiceHelper::indexDeleteFileByIdAllegato] END");
	}

	private String indexGetUidAllegato(String cmName, String idAllegato) throws ServiceException {
		return this.indexGetUidAllegato(IndexDtoFactory.createSearchParamsForIndexUid(cmName, idAllegato));
	}

	private String indexGetUidAllegato(SearchParams params) throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexGetUidAllegato] BEGIN");

		String uid = null;
		try {
			final OperationContext oc = this.indexGetOperationContext(Constants.INDEX_USERNAME_ADMIN);

			final SearchResponse response = this.getEcmEngineWebServiceDelegate().luceneSearch(params, oc);

			final ResultContent[] results = response.getResultContentArray();

			if (ArrayUtils.isNotEmpty(results)) {
				uid = results[0].getUid();

			}

			return uid;
		} catch (Exception e) {
			LOGGER.error("[IndexServiceHelper::indexGetUidAllegato] ERROR GENERICO: " + e.getMessage(), e);
			throw new ServiceException("[Errore INDEX indexGetUidAllegato Exception] " + e.getMessage());
		} finally {
			LOGGER.debug("[IndexServiceHelper::indexGetUidAllegato] END");
		}
	}

	public byte[] indexDownloadFilePrimpa(String idAllegato) throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexDownloadFilePrimpa] END");
		return this.indexDownloadFile(Constants.INDEX_CM_NAME_PRIMPA, idAllegato);
	}

	public byte[] indexDownloadFilePfa(String idAllegato) throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexDownloadFilePfa] END");
		return this.indexDownloadFile(Constants.INDEX_CM_NAME_PFA, idAllegato);
	}

	public byte[] indexDownloadFileTrasformazioni(String idAllegato) throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexDownloadFileTrasformazioni] END");
		return this.indexDownloadFile(Constants.INDEX_CM_NAME_TRASFORMAZIONI, idAllegato);
	}
	
	public byte[] indexDownloadFileVincolo(String idAllegato) throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexDownloadFileTrasformazioni] END");
		return this.indexDownloadFile(Constants.INDEX_CM_NAME_VINCOLO, idAllegato);
	}
	
	public byte[] indexDownloadFileByUuid(String uuid) throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexDownloadFileByUuid] START");
		byte[] result = null;
		try {
			
			final OperationContext oc = this.indexGetOperationContext(Constants.INDEX_USERNAME_ADMIN);

			result = this.ecmEngineWebServiceDelegate.retrieveContentData(IndexDtoFactory.createNode(uuid),
					IndexDtoFactory.createContent(), oc);
		} catch (Exception e) {
			LOGGER.error("[IndexServiceHelper::indexDownloadFile] ERROR GENERICO: " + e.getMessage(), e);
			throw new ServiceException("[Errore INDEX indexDownloadFile Exception] " + e.getMessage());
		} finally {
			LOGGER.debug("[IndexServiceHelper::indexDownloadFile] END");
		}
		return result;
		
	}

	private byte[] indexDownloadFile(String cmName, String idAllegato) throws ServiceException {
		LOGGER.debug("[IndexServiceHelper::indexDownloadFile] START");
		byte[] result = null;
		try {
			String uidAllegato = this.indexGetUidAllegato(cmName, idAllegato);
			if(uidAllegato == null){
				uidAllegato = idAllegato;
			}
			
			final OperationContext oc = this.indexGetOperationContext(Constants.INDEX_USERNAME_ADMIN);

			result = this.ecmEngineWebServiceDelegate.retrieveContentData(IndexDtoFactory.createNode(uidAllegato),
					IndexDtoFactory.createContent(), oc);
		} catch (Exception e) {
			LOGGER.error("[IndexServiceHelper::indexDownloadFile] ERROR GENERICO: " + e.getMessage(), e);
			throw new ServiceException("[Errore INDEX indexDownloadFile Exception] " + e.getMessage());
		} finally {
			LOGGER.debug("[IndexServiceHelper::indexDownloadFile] END");
		}
		return result;
	}
	

	public String testIndex()throws ServiceException{
		LOGGER.debug("[IndexServiceHelper::testIndex] BEGIN");
		return this.indexGetRootFolder(Constants.INDEX_ROOT_PRIMPA);
	}

}
