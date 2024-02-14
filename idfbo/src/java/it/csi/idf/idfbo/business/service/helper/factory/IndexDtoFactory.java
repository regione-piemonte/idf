/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.business.service.helper.factory;

import org.apache.commons.io.FilenameUtils;

import it.csi.idf.idfbo.business.service.helper.dto.MetadatiIndexPfa;
import it.csi.idf.idfbo.business.service.helper.dto.MetadatiIndexPrimpa;
import it.csi.idf.idfbo.business.service.helper.dto.MetadatiIndexTrasformazioni;
import it.csi.idf.idfbo.business.service.helper.dto.MetadatiIndexVincolo;
import it.csi.idf.idfbo.util.Constants;
import it.doqui.index.ecmengine.client.webservices.dto.Node;
import it.doqui.index.ecmengine.client.webservices.dto.OperationContext;
import it.doqui.index.ecmengine.client.webservices.dto.engine.management.Content;
import it.doqui.index.ecmengine.client.webservices.dto.engine.management.Mimetype;
import it.doqui.index.ecmengine.client.webservices.dto.engine.management.Property;
import it.doqui.index.ecmengine.client.webservices.dto.engine.search.SearchParams;

/**
 * Classe factory per <code>DTO</code> utilizzati nel contesto delle operazioni
 * col servizio <code>INDEX</code>.
 *
 * @author 1346
 */
public final class IndexDtoFactory {

	/**
	 * Parametro di Ricerca, Limite = 0.
	 */
	public static final int SEARCH_PARAM_LIMIT_0 = 0;

	/**
	 * Parametro di Ricerca, Limite = 1.
	 */
	public static final int SEARCH_PARAM_LIMIT_1 = 1;

	/**
	 * Crea e restituisce un'istanza {@link OperationContext}.
	 *
	 * @param user nome utente
	 * @return un'istanza {@link OperationContext}
	 */
	public static OperationContext createOperationContext(String user) {
		final OperationContext ctx = new OperationContext();
		ctx.setUsername(user);
		ctx.setPassword(Constants.INDEX.INDEX_PSW);
		ctx.setNomeFisico(Constants.INDEX.INDEX_UTENTE);
		ctx.setFruitore(Constants.INDEX.INDEX_FRUITORE);
		ctx.setRepository(Constants.INDEX.INDEX_REPOSITORY);

		return ctx;
	}

	/**
	 * Crea e restituisce un'istanza {@link SearchParams}.
	 *
	 * @param limit limite
	 * @return un'istanza {@link SearchParams}.
	 */
	public static SearchParams createSearchParams(int limit) {
		final SearchParams params = new SearchParams();
		params.setLimit(limit);

		return params;
	}

	/**
	 * Crea e restituisce un'istanza {@link SearchParams}.
	 *
	 * @return un'istanza {@link SearchParams}
	 */
	public static SearchParams createSearchParamsForRootFolder(String rootFolderName) {
		final SearchParams params = createSearchParams(SEARCH_PARAM_LIMIT_1);
		final String luceneQuery = createLuceneQueryForRootFolder(rootFolderName);

		params.setXPathQuery(luceneQuery);

		return params;
	}

	/**
	 * Crea e restituisce un'istanza {@link SearchParams}.
	 *
	 * @param metadati istanza di tipo {@link MetadatiIndex}
	 * @return un'istanza {@link SearchParams}
	 */

	/**
	 * Crea e restituisce un'istanza {@link SearchParams}.
	 *
	 * @param metadati istanza di tipo {@link MetadatiIndex}
	 * @return un'istanza {@link SearchParams}
	 */
	public static SearchParams createSearchParamsForIndexFolder(String luceneQuery) {
		final SearchParams params = createSearchParams(SEARCH_PARAM_LIMIT_1);

		params.setXPathQuery(luceneQuery);

		return params;
	}

	/**
	 * Crea e restituisce un'istanza {@link SearchParams}.
	 *
	 * @param idAllegato identificativo dell'Allegato da scaricare
	 * @return un'istanza {@link SearchParams}.
	 */
	public static SearchParams createSearchParamsForIndexUid(String cmName, String idAllegato) {
		final SearchParams params = createSearchParams(SEARCH_PARAM_LIMIT_0);
		final String luceneQuery = createLuceneQueryForIndexDownload(cmName, idAllegato);

		params.setLuceneQuery(luceneQuery);

		return params;
	}

	/**
	 * Crea e restituisce un'istanza {@link SearchParams}.
	 *
	 * @param metadati istanza di tipo {@link MetadatiIndex}
	 * @param fileName nome del file del quale recuperare l'<code>UID</code>
	 * @return un'istanza {@link SearchParams}.
	 */

	/**
	 * Crea e restituisce un'istanza {@link Content} per la <em>folder</em> della
	 * quale &egrave; stato specificato il nome.
	 *
	 * @param folderName nome della <em>folder</em>
	 * @return un'istanza {@link Content}
	 */
	public static Content createContentFolder(String folderName) {
		final Content contentFolder = new Content();
		contentFolder.setPrefixedName(Constants.INDEX.INDEX_DEFAULT_PREFIX + folderName);
		contentFolder.setParentAssocTypePrefixedName(Constants.INDEX.INDEX_PREFIX_CONTAINS);
		contentFolder.setModelPrefixedName(Constants.INDEX.INDEX_PREFIX_MODEL);
		contentFolder.setTypePrefixedName(Constants.INDEX.INDEX_PREFIX_FOLDER);
		contentFolder.setProperties(createContentFolderProperties(folderName));

		return contentFolder;
	}

	/**
	 *
	 * @param folderName
	 * @return
	 */
	public static Property[] createContentFolderProperties(String folderName) {
		final Property p = new Property();
		p.setPrefixedName(Constants.INDEX.INDEX_PREFIX_NAME_SHORT);
		p.setDataType("text");
		p.setValues(new String[] { folderName });

		return new Property[] { p };
	}

	/**
	 * Crea e restituisce un'istanza {@link Node} per l'<code>UID</code> dato.
	 *
	 * @param uid l'<code>UID</code>
	 * @return un'istanza {@link Node}
	 */
	public static Node createNode(String uid) {
		return new Node(uid);
	}

	/**
	 * Crea e restituisce un'istanza {@link Mimetype} per un file del quale &egrave;
	 * stato indicato il nome.
	 *
	 * @param fileName nome del file
	 * @return un'istanza {@link Mimetype}
	 */
	public static Mimetype createMimetypeFromFileName(String fileName) {
		final Mimetype mimeType = new Mimetype();
		mimeType.setFileExtension(FilenameUtils.getExtension(fileName));

		return mimeType;
	}

	/**
	 * Crea e restituisce un'istanza {@link Content} per il <em>file</em> del quale
	 * &egrave; stato specificato il nome, il contenuto ed il mimeType.
	 *
	 * @param metadati metadati
	 * @param fileName nome del file
	 * @param file     contenuto del file del quale &egrave; stato indicato il nome
	 * @param mimeType mimeType
	 * @return un'istanza {@link Content}
	 */
	public static Content createContent(MetadatiIndexTrasformazioni metadati, String prefixModelName, String prefixName,
			String typeName, String fileName, byte[] file, String mimeType) {
		final Content content = createContent(prefixName, typeName, fileName, mimeType);
		content.setModelPrefixedName(prefixModelName);
		content.setProperties(createContentProperties(metadati));
		content.setContent(file);

		return content;
	}
	
	/**
	 * Crea e restituisce un'istanza {@link Content} per il <em>file</em> del quale
	 * &egrave; stato specificato il nome, il contenuto ed il mimeType.
	 *
	 * @param metadati metadati
	 * @param fileName nome del file
	 * @param file     contenuto del file del quale &egrave; stato indicato il nome
	 * @param mimeType mimeType
	 * @return un'istanza {@link Content}
	 */
	public static Content createContent(MetadatiIndexPrimpa metadati, String prefixModelName, String prefixName,
			String typeName, String fileName, byte[] file, String mimeType) {
		final Content content = createContent(prefixName, typeName, fileName, mimeType);
		content.setModelPrefixedName(prefixModelName);
		content.setProperties(createContentProperties(metadati));
		content.setContent(file);

		return content;
	}
	
	/**
	 * Crea e restituisce un'istanza {@link Content} per il <em>file</em> del quale
	 * &egrave; stato specificato il nome, il contenuto ed il mimeType.
	 *
	 * @param metadati metadati
	 * @param fileName nome del file
	 * @param file     contenuto del file del quale &egrave; stato indicato il nome
	 * @param mimeType mimeType
	 * @return un'istanza {@link Content}
	 */
	public static Content createContent(MetadatiIndexPfa metadati, String prefixModelName, String prefixName,
			String typeName, String fileName, byte[] file, String mimeType) {
		final Content content = createContent(prefixName, typeName, fileName, mimeType);
		content.setModelPrefixedName(prefixModelName);
		content.setProperties(createContentProperties(metadati));
		content.setContent(file);

		return content;
	}

	public static Content createContent() {
		final Content content = new Content();
		content.setContentPropertyPrefixedName(Constants.INDEX.INDEX_PREFIX_NAME);
		return content;
	}

	/**
	 * Crea e restituisce un'istanza {@link Content} per il <em>file</em> del quale
	 * &egrave; stato specificato il nome ed il mimeType.
	 *
	 * @param fileName nome del file
	 * @param mimeType mimeType
	 * @return un'istanza {@link Content}
	 */
	public static Content createContent(String prefixName, String typeName, String fileName, String mimeType) {
		final Content content = createContent();
		content.setPrefixedName(prefixName + fileName);
		content.setParentAssocTypePrefixedName(Constants.INDEX.INDEX_PREFIX_CONTAINS);
		content.setTypePrefixedName(typeName);
		content.setMimeType(mimeType);
		content.setEncoding(Constants.INDEX.INDEX_ENCODING);

		return content;
	}

	/**
	 *
	 * @param metadati
	 * @return
	 */
	public static Property[] createContentProperties(MetadatiIndexTrasformazioni metadati) {
		final Property[] result = new Property[3];

		result[0] = new Property();
		result[0].setDataType(Constants.INDEX.INDEX_TYPE_TEXT);
		result[0].setPrefixedName(MetadatiIndexTrasformazioni.META_ID_ALLEGATO);
		result[0].setValues(new String[] { metadati.getIdAllegato() });

		result[1] = new Property();
		result[1].setDataType(Constants.INDEX.INDEX_TYPE_TEXT);
		result[1].setPrefixedName(MetadatiIndexTrasformazioni.META_ID_TIPO_ALLEGATO);
		result[1].setValues(new String[] { metadati.getIdTipoAllegato() });

		result[2] = new Property();
		result[2].setDataType(Constants.INDEX.INDEX_TYPE_TEXT);
		result[2].setPrefixedName(MetadatiIndexTrasformazioni.META_ID_ISTANZA);
		result[2].setValues(new String[] { metadati.getIdIstanza() });

		return result;
	}
	
	/**
	 *
	 * @param metadati
	 * @return
	 */
	public static Property[] createContentProperties(MetadatiIndexVincolo metadati) {
		final Property[] result = new Property[3];

		result[0] = new Property();
		result[0].setDataType(Constants.INDEX.INDEX_TYPE_TEXT);
		result[0].setPrefixedName(MetadatiIndexVincolo.META_ID_ALLEGATO);
		result[0].setValues(new String[] { metadati.getIdAllegato() });

		result[1] = new Property();
		result[1].setDataType(Constants.INDEX.INDEX_TYPE_TEXT);
		result[1].setPrefixedName(MetadatiIndexVincolo.META_ID_TIPO_ALLEGATO);
		result[1].setValues(new String[] { metadati.getIdTipoAllegato() });

		result[2] = new Property();
		result[2].setDataType(Constants.INDEX.INDEX_TYPE_TEXT);
		result[2].setPrefixedName(MetadatiIndexVincolo.META_ID_ISTANZA);
		result[2].setValues(new String[] { metadati.getIdIstanza() });

		return result;
	}
	
	/**
	 *
	 * @param metadati
	 * @return
	 */
	public static Property[] createContentProperties(MetadatiIndexPrimpa metadati) {
		final Property[] result = new Property[4];

		result[0] = new Property();
		result[0].setDataType(Constants.INDEX.INDEX_TYPE_TEXT);
		result[0].setPrefixedName(MetadatiIndexPrimpa.META_ID_TIPO_ALLEGATO);
		result[0].setValues(new String[] { metadati.getIdTipoAllegato()});

		result[1] = new Property();
		result[1].setDataType(Constants.INDEX.INDEX_TYPE_TEXT);
		result[1].setPrefixedName(MetadatiIndexPrimpa.META_NOTE_ALLEGATO);
		result[1].setValues(new String[] { metadati.getNoteAllegato() });

		result[2] = new Property();
		result[2].setDataType(Constants.INDEX.INDEX_TYPE_TEXT);
		result[2].setPrefixedName(MetadatiIndexPrimpa.META_DATA_INSERIMENTO);
		result[2].setValues(new String[] { metadati.getDataInserimento()});
		
		result[3] = new Property();
		result[3].setDataType(Constants.INDEX.INDEX_TYPE_TEXT);
		result[3].setPrefixedName(MetadatiIndexPrimpa.META_DATA_CANCELLAZIONE);
		result[3].setValues(new String[] { metadati.getDataCancellazione()});

		return result;
	}

	/**
	 *
	 * @param metadati
	 * @return
	 */
	public static Property[] createContentProperties(MetadatiIndexPfa metadati) {
		final Property[] result = new Property[3];

		result[0] = new Property();
		result[0].setDataType(Constants.INDEX.INDEX_TYPE_TEXT);
		result[0].setPrefixedName(MetadatiIndexPfa.META_ID_ALLEGATO);
		result[0].setValues(new String[] { metadati.getIdAllegato() });

		result[1] = new Property();
		result[1].setDataType(Constants.INDEX.INDEX_TYPE_TEXT);
		result[1].setPrefixedName(MetadatiIndexPfa.META_ID_TIPO_ALLEGATO);
		result[1].setValues(new String[] { metadati.getIdTipoAllegato() });

		result[2] = new Property();
		result[2].setDataType(Constants.INDEX.INDEX_TYPE_TEXT);
		result[2].setPrefixedName(MetadatiIndexPfa.META_ID_ISTANZA);
		result[2].setValues(new String[] { metadati.getIdIstanza() });

		return result;
	}

	/**
	 *
	 * @return
	 */
	public static String createLuceneQueryForRootFolder(String rootFolderName) {
		final StringBuilder luceneQuery = new StringBuilder(rootFolderName);

		return luceneQuery.toString();
	}

	

	/**
	 *
	 * @param idAllegato
	 * @return
	 */
	private static String createLuceneQueryForIndexDownload(String cmName, String idAllegato) {
		final StringBuilder luceneQuery = new StringBuilder();

		luceneQuery.append(cmName + "\\:idAllegato:\"").append(idAllegato).append("\"");

		return luceneQuery.toString();
	}

	/**
	 *
	 * @param metadati
	 * @param fileName
	 * @return
	 */

	/**
	 * Constructor.
	 */
	private IndexDtoFactory() {
		/* NOP */
	}

}
