/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;

import it.csi.idf.idfapi.util.TipoAllegatoPfaEnum;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import it.csi.idf.idfapi.business.be.exceptions.ServiceException;
import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.dto.DocumentoAllegato;
import it.csi.idf.idfapi.dto.FatDocumentoAllegato;
import it.csi.idf.idfapi.dto.FileUploadForm;
import it.csi.idf.idfapi.dto.TipoAllegatoExtended;
import it.csi.idf.idfapi.util.TipoAllegatoEnum;

public interface DocumentoAllegatoDAO {

	List<DocumentoAllegato> findAllDocumenti();

	DocumentoAllegato findDocumentoByID(Integer idDocumentoAllegato);

	List<FatDocumentoAllegato> findAllDocumentiByPfa(Integer idGeoPlPfa);

	int createDocumentoAllegato(DocumentoAllegato documento);

	int createDocumentoAllegato(FatDocumentoAllegato documento);

	List<DocumentoAllegato> findDocumentiByIdIntervento(Integer idIntervento);
	
	List<FatDocumentoAllegato> findFatDocumentiByIdIntervento(Integer idIntervento);

	int deleteDocumentoAllegatoByID(int idDocumentoAllegato);

	List<FatDocumentoAllegato> findDocumentiByIdAndTipos(Integer idIntervento, List<TipoAllegatoEnum> tipoAllegati);
	

	List<FatDocumentoAllegato> findDocumentiByIdAndTiposPfa(Integer idIntervento, List<TipoAllegatoPfaEnum> tipoAllegati);
	
	List<FatDocumentoAllegato> findDocumentiByIdAndTiposPfaGestoreNew(Integer idIntervento, List<TipoAllegatoPfaEnum> tipoAllegati);

	FatDocumentoAllegato uploadDocumento(int idIntervento, int tipoDocumento, FileUploadForm form,
			ConfigUtente loggedConfig);

	FatDocumentoAllegato uploadDocumentoVincolo(int idIntervento, int tipoDocumento, FileUploadForm form,	ConfigUtente loggedConfig);

	FatDocumentoAllegato uploadDocumentoTagli(int idIntervento, int tipoDocumento, FileUploadForm form,	ConfigUtente loggedConfig);

	FatDocumentoAllegato uploadDocumentoForPfa(int idIntervento, int idGeoPlPfa, int tipoDocumento,
			ConfigUtente loggedConfig, MultipartFormDataInput form, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest);
	
	FatDocumentoAllegato uploadDocumentoForPfaV2(int idIntervento, int idGeoPlPfa, int tipoDocumento,
			ConfigUtente loggedConfig, FileUploadForm form, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest)throws ServiceException;

	String deleteDocumentoById(Integer idDocumentoAllegato);
	
	String deletePfaDocumentoById(Integer idDocumentoAllegato);

	void uploadPfaDocumentoWithStringUid(FatDocumentoAllegato documentoAllegato);
	
	List<Integer> getAllDocumentsByIdIntervento(Integer idIntervento);
	
	void deleteDocumentoAllegatoByIdIntervento(Integer idIntervento);
	
	List<TipoAllegatoExtended> getTipiDocumentoPagamentoVincolo();


}
