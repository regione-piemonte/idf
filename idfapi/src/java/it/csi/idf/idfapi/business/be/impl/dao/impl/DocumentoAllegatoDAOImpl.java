/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.SecurityContext;

import it.csi.idf.idfapi.business.be.service.helper.dto.MetadatiIndexPrimpa;
import it.csi.idf.idfapi.util.TipoAllegatoPfaEnum;
import org.apache.log4j.Logger;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.idf.idfapi.business.be.exceptions.ServiceException;
import it.csi.idf.idfapi.business.be.impl.dao.DocumentoAllegatoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.TipoAllegatoDAO;
import it.csi.idf.idfapi.business.be.service.helper.IndexServiceHelper;
import it.csi.idf.idfapi.business.be.service.helper.dto.MetadatiIndexPfa;
import it.csi.idf.idfapi.business.be.service.helper.dto.MetadatiIndexTrasformazioni;
import it.csi.idf.idfapi.business.be.service.helper.dto.MetadatiIndexVincolo;
import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.dto.DocumentoAllegato;
import it.csi.idf.idfapi.dto.FatDocumentoAllegato;
import it.csi.idf.idfapi.dto.FileUploadForm;
import it.csi.idf.idfapi.dto.TipoAllegatoExtended;
import it.csi.idf.idfapi.mapper.DocumentoMapper;
import it.csi.idf.idfapi.mapper.FatDocumentoMapper;
import it.csi.idf.idfapi.mapper.TipoAllegatoExtendedMapper;
import it.csi.idf.idfapi.util.AIKeyHolderUtil;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.TipoAllegatoEnum;

@Component
public class DocumentoAllegatoDAOImpl implements DocumentoAllegatoDAO {
	
	static final Logger logger = Logger.getLogger(DocumentoAllegatoDAOImpl.class);

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	private TipoAllegatoDAO tipoAllegatoDAO;

	private final DocumentoMapper documentoMapper = new DocumentoMapper();
	private final FatDocumentoMapper fatDocumentoMapper = new FatDocumentoMapper();

	@Override
	public List<FatDocumentoAllegato> findAllDocumentiByPfa(Integer idGeoPlPfa) {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT da.id_documento_allegato, da.idgeo_pl_pfa, da.fk_intervento, da.fk_tipo_allegato");
		sql.append(", da.nome_allegato, da.dimensione_allegato_kb, da.data_inizio_validita");
		sql.append(", da.data_fine_validita, da.data_inserimento, da.data_aggiornamento, da.fk_config_utente");
		sql.append(", da.note, ta.id_tipo_allegato, ta.descr_tipo_allegato , da.uid_index,da.id_doc_doqui,da.id_classificazione_doqui");
		sql.append(" FROM idf_t_documento_allegato da");
		sql.append(" JOIN idf_d_tipo_allegato ta ON ta.id_tipo_allegato = da.fk_tipo_allegato");
		sql.append(" WHERE da.idgeo_pl_pfa = ? and da.fk_intervento is null ");

		return DBUtil.jdbcTemplate.query(sql.toString(), fatDocumentoMapper, idGeoPlPfa);
	}

	@Override
	public List<DocumentoAllegato> findAllDocumenti() {

		String query = "SELECT * FROM idf_t_documento_allegato;";

		return DBUtil.jdbcTemplate.query(query, documentoMapper);
	}

	@Override
	public DocumentoAllegato findDocumentoByID(Integer idDocumentoAllegato) {

		String query = "SELECT * FROM idf_t_documento_allegato doc WHERE doc.id_documento_allegato = ?";
		List<DocumentoAllegato> documentoAllegato = null;
		try {
			documentoAllegato = DBUtil.jdbcTemplate.query(query, documentoMapper, idDocumentoAllegato);
		}catch(Exception ex) {
			logger.error("file not found for idDocumentoAllegato: " + idDocumentoAllegato);
			return null;
		}

		return documentoAllegato == null || documentoAllegato.size()==0?null:documentoAllegato.get(0);
	}

	@Override
	public int createDocumentoAllegato(DocumentoAllegato documento) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO idf_t_documento_allegato(");
		sql.append("  idgeo_pl_pfa, fk_intervento, fk_tipo_allegato");
		sql.append(", nome_allegato, dimensione_allegato_kb, data_inizio_validita");
		sql.append(", data_fine_validita, data_inserimento, data_aggiornamento, fk_config_utente, note");
		sql.append(", uid_index");
		sql.append(") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");

		List<Object> parameters = new ArrayList<>();
		parameters.add(documento.getIdGeoPlPfa());
		parameters.add(documento.getFkIntervento());
		parameters.add(documento.getFkTipoAllegato());
		parameters.add(documento.getNomeAllegato());
		parameters.add(documento.getDimensioneAllegatoKB());
		parameters
				.add(documento.getDataIniziValidita() == null ? null : Date.valueOf(documento.getDataIniziValidita()));
		parameters.add(documento.getDataFineValidita() == null ? null : Date.valueOf(documento.getDataFineValidita()));
		parameters.add(documento.getDataInserimento() == null ? null : Date.valueOf(documento.getDataInserimento()));
		parameters
				.add(documento.getDataAggiornamento() == null ? null : Date.valueOf(documento.getDataAggiornamento()));
		parameters.add(documento.getFkConfigUtente());
		parameters.add(documento.getNote());
		parameters.add(documento.getUidIndex());

		return AIKeyHolderUtil.updateWithGenKey("id_documento_allegato", sql.toString(), parameters);
	}

	@Override
	public int createDocumentoAllegato(FatDocumentoAllegato documento) {

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO idf_t_documento_allegato(");
		sql.append("  idgeo_pl_pfa, fk_intervento, fk_tipo_allegato");
		sql.append(", nome_allegato, dimensione_allegato_kb, data_inizio_validita");
		sql.append(", data_fine_validita, data_inserimento, data_aggiornamento, fk_config_utente, note");
		sql.append(", uid_index");
		sql.append(") VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?)");

		List<Object> parameters = new ArrayList<>();
		parameters.add(documento.getIdGeoPlPfa());
		parameters.add(documento.getFkIntervento());
		parameters.add(documento.getIdTipoAllegato());
		parameters.add(documento.getNomeAllegato());
		parameters.add(documento.getDimensioneAllegatoKB());
		parameters
				.add(documento.getDataIniziValidita() == null ? null : Date.valueOf(documento.getDataIniziValidita()));
		parameters.add(documento.getDataFineValidita() == null ? null : Date.valueOf(documento.getDataFineValidita()));
		parameters.add(documento.getDataInserimento() == null ? null : Date.valueOf(documento.getDataInserimento()));
		parameters
				.add(documento.getDataAggiornamento() == null ? null : Date.valueOf(documento.getDataAggiornamento()));
		parameters.add(documento.getFkConfigUtente());
		parameters.add(documento.getNote());
		parameters.add(documento.getUidIndex());
		return AIKeyHolderUtil.updateWithGenKey("id_documento_allegato", sql.toString(), parameters);
	}

	@Override
	public List<DocumentoAllegato> findDocumentiByIdIntervento(Integer idIntervento) {
		String query = "SELECT * FROM idf_t_documento_allegato WHERE fk_intervento = ?";
		return DBUtil.jdbcTemplate.query(query, documentoMapper, idIntervento);
	}
	
	@Override
	public List<FatDocumentoAllegato> findFatDocumentiByIdIntervento(Integer idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT da.id_documento_allegato, da.idgeo_pl_pfa, da.fk_intervento, da.fk_tipo_allegato");
		sql.append(", da.nome_allegato, da.dimensione_allegato_kb, da.data_inizio_validita");
		sql.append(", da.data_fine_validita, da.data_inserimento, da.data_aggiornamento, da.fk_config_utente");
		sql.append(", COALESCE(da.note, '') AS note, ta.id_tipo_allegato, ta.descr_tipo_allegato , da.uid_index,da.id_doc_doqui,da.id_classificazione_doqui");
		sql.append(" FROM idf_t_documento_allegato da");
		sql.append(" JOIN idf_d_tipo_allegato ta ON ta.id_tipo_allegato = da.fk_tipo_allegato");
		sql.append(" WHERE fk_intervento = ?");
		return DBUtil.jdbcTemplate.query(sql.toString(), fatDocumentoMapper, idIntervento);
	}

	@Override
	public int deleteDocumentoAllegatoByID(int idDocumentoAllegato) {
		String sql = "DELETE FROM idf_t_documento_allegato WHERE id_documento_allegato = ?";
		return DBUtil.jdbcTemplate.update(sql, idDocumentoAllegato);
	}

	@Override
	public List<FatDocumentoAllegato> findDocumentiByIdAndTipos(Integer idIntervento,
			List<TipoAllegatoEnum> tipoAllegati) {

		if (tipoAllegati.isEmpty()) {
			throw new IllegalArgumentException();
		}

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT da.id_documento_allegato, da.idgeo_pl_pfa, da.fk_intervento, da.fk_tipo_allegato");
		sql.append(", da.nome_allegato, da.dimensione_allegato_kb, da.data_inizio_validita");
		sql.append(", da.data_fine_validita, da.data_inserimento, da.data_aggiornamento, da.fk_config_utente");
		sql.append(", da.note, ta.id_tipo_allegato, ta.descr_tipo_allegato , da.uid_index,da.id_doc_doqui,da.id_classificazione_doqui");
		sql.append(" FROM idf_t_documento_allegato da");
		sql.append(" JOIN idf_d_tipo_allegato ta ON ta.id_tipo_allegato = da.fk_tipo_allegato");
		sql.append(" WHERE da.fk_intervento = ?");
		sql.append(" AND da.fk_tipo_allegato IN(");

		for (int i = 0; i < tipoAllegati.size(); i++) {
			sql.append(tipoAllegati.get(i).getValue());
			if (i != tipoAllegati.size() - 1) {
				sql.append(",");
			}
		}
		sql.append(")");
		return DBUtil.jdbcTemplate.query(sql.toString(), fatDocumentoMapper, idIntervento);
	}
	

	

	@Override
	public List<FatDocumentoAllegato> findDocumentiByIdAndTiposPfa(Integer idIntervento, List<TipoAllegatoPfaEnum> tipoAllegati) {
		if (tipoAllegati.isEmpty()) {
			throw new IllegalArgumentException();
		}

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT da.id_documento_allegato, da.idgeo_pl_pfa, da.fk_intervento, da.fk_tipo_allegato");
		sql.append(", da.nome_allegato, da.dimensione_allegato_kb, da.data_inizio_validita");
		sql.append(", da.data_fine_validita, da.data_inserimento, da.data_aggiornamento, da.fk_config_utente");
		sql.append(", da.note, ta.id_tipo_allegato, ta.descr_tipo_allegato , da.uid_index,da.id_doc_doqui,da.id_classificazione_doqui");
		sql.append(" FROM idf_t_documento_allegato da");
		sql.append(" JOIN idf_d_tipo_allegato ta ON ta.id_tipo_allegato = da.fk_tipo_allegato");
		sql.append(" WHERE da.fk_intervento = ? ");
		//FIX GP
		//sql.append(" and ta.fk_origine_allegato  in (7)");
	
		//777 
		
		sql.append(" AND da.fk_tipo_allegato IN(");

		for (int i = 0; i < tipoAllegati.size(); i++) {
			sql.append(tipoAllegati.get(i).getValue());
			if (i != tipoAllegati.size() - 1) {
				sql.append(",");
			}
		}
		sql.append(")");
		
		//END 777
		
        System.out.println("findDocumentiByIdAndTiposPfa: " +sql.toString());      
        System.out.println("findDocumentiByIdAndTiposPfa --- idIntervento : " +idIntervento);     
		return DBUtil.jdbcTemplate.query(sql.toString(), fatDocumentoMapper, idIntervento);
	}
	
	@Override
	public List<FatDocumentoAllegato> findDocumentiByIdAndTiposPfaGestoreNew(Integer idIntervento, List<TipoAllegatoPfaEnum> tipoAllegati) {
		if (tipoAllegati.isEmpty()) {
			throw new IllegalArgumentException();
		}

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT da.id_documento_allegato, da.idgeo_pl_pfa, da.fk_intervento, da.fk_tipo_allegato");
		sql.append(", da.nome_allegato, da.dimensione_allegato_kb, da.data_inizio_validita");
		sql.append(", da.data_fine_validita, da.data_inserimento, da.data_aggiornamento, da.fk_config_utente");
		sql.append(", da.note, ta.id_tipo_allegato, ta.descr_tipo_allegato , da.uid_index,da.id_doc_doqui,da.id_classificazione_doqui");
		sql.append(" FROM idf_t_documento_allegato da");
		sql.append(" JOIN idf_d_tipo_allegato ta ON ta.id_tipo_allegato = da.fk_tipo_allegato");
		sql.append(" WHERE da.fk_intervento = ? ");
		//FIX GP
		//sql.append(" and ta.fk_origine_allegato  in (5)");
	
		//777
		
		sql.append(" AND da.fk_tipo_allegato IN(");

		for (int i = 0; i < tipoAllegati.size(); i++) {
			sql.append(tipoAllegati.get(i).getValue());
			if (i != tipoAllegati.size() - 1) {
				sql.append(",");
			}
		}
		sql.append(")");
		
		//END 777
        System.out.println("findDocumentiByIdAndTiposPfaGestoreNew: " +sql.toString());      
        System.out.println("findDocumentiByIdAndTiposPfaGestoreNew --- idIntervento : " +idIntervento);     
		return DBUtil.jdbcTemplate.query(sql.toString(), fatDocumentoMapper, idIntervento);
	}
	
	

	@Override
	public FatDocumentoAllegato uploadDocumento(int idIntervento, int tipoDocumento, FileUploadForm form,
			ConfigUtente configUtente) {
		Integer idAllegato = null;
		try {
			byte[] uploadForm = form.getData();

			String descrTipoAllegato = tipoAllegatoDAO.getTipoById(tipoDocumento).getDescrTipoAllegato();
			FatDocumentoAllegato documentoAllegato = new FatDocumentoAllegato();
			documentoAllegato.setIdTipoAllegato(tipoDocumento);
			documentoAllegato.setDescrTipoAllegato(descrTipoAllegato);
			documentoAllegato.setDeleted(false);
			documentoAllegato.setNomeAllegato(form.getName());
			if(form.getNote() != null) {
				documentoAllegato.setNote(URLDecoder.decode(form.getNote(), "UTF-8"));
			}
			documentoAllegato.setDimensioneAllegatoKB(Long.valueOf(uploadForm.length) / 1024);
			documentoAllegato.setFkIntervento(idIntervento);
			documentoAllegato.setDataIniziValidita(LocalDate.now());
			documentoAllegato.setDataInserimento(LocalDate.now());
			documentoAllegato.setFkConfigUtente(configUtente.getIdConfigUtente());

			documentoAllegato.setIdGeoPlPfa(0);

			idAllegato = createDocumentoAllegato(documentoAllegato);
			IndexServiceHelper indexSrvHelper = (IndexServiceHelper) applicationContext.getBean("indexServiceHelper");
			MetadatiIndexTrasformazioni metadatiIndexTrasf = new MetadatiIndexTrasformazioni();
			metadatiIndexTrasf.setIdIntervento(String.valueOf(idIntervento));
			metadatiIndexTrasf.setIdTipoAllegato(String.valueOf(tipoDocumento));
			metadatiIndexTrasf.setIdAllegato(String.valueOf(idAllegato));
			logger.info("metadatiIndexTrasf -> idIntervento: " + idIntervento + " - tipoDocumento: " + tipoDocumento
					+ " - idAllegato: " + idAllegato);
			String fileUID = indexSrvHelper.indexUploadFile(form.getName(), uploadForm, metadatiIndexTrasf);
			logger.info("fileUID: " + fileUID);
			documentoAllegato.setIdDocumentoAllegato(idAllegato);
			documentoAllegato.setUidIndex(fileUID);
			uploadPfaDocumentoWithStringUid(documentoAllegato);
			return documentoAllegato;

		} catch (Exception e) {
			if(idAllegato != null) {
				deleteDocumentoAllegatoByID(idAllegato);
			}
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public FatDocumentoAllegato uploadDocumentoVincolo(int idIntervento, int tipoDocumento, FileUploadForm form,
			ConfigUtente configUtente) {
		try {
			byte[] uploadForm = form.getData();

			String descrTipoAllegato = tipoAllegatoDAO.getTipoById(tipoDocumento).getDescrTipoAllegato();
			FatDocumentoAllegato documentoAllegato = new FatDocumentoAllegato();
			documentoAllegato.setIdTipoAllegato(tipoDocumento);
			documentoAllegato.setDescrTipoAllegato(descrTipoAllegato);
			documentoAllegato.setDeleted(false);
			documentoAllegato.setNomeAllegato(form.getName());
			if(form.getNote() != null) {
				documentoAllegato.setNote(URLDecoder.decode(form.getNote(), "UTF-8"));
			}
			documentoAllegato.setDimensioneAllegatoKB(Long.valueOf(uploadForm.length) / 1024);
			documentoAllegato.setFkIntervento(idIntervento);
			documentoAllegato.setDataIniziValidita(LocalDate.now());
			documentoAllegato.setDataInserimento(LocalDate.now());
			documentoAllegato.setFkConfigUtente(configUtente.getIdConfigUtente());

			documentoAllegato.setIdGeoPlPfa(0);

			int idAllegato = createDocumentoAllegato(documentoAllegato);
			IndexServiceHelper indexSrvHelper = (IndexServiceHelper) applicationContext.getBean("indexServiceHelper");
			MetadatiIndexVincolo metadatiIndexVincolo = new MetadatiIndexVincolo();
			metadatiIndexVincolo.setIdIntervento(String.valueOf(idIntervento));
			metadatiIndexVincolo.setIdTipoAllegato(String.valueOf(tipoDocumento));
			metadatiIndexVincolo.setIdAllegato(String.valueOf(idAllegato));
			logger.info("metadatiIndexTrasf -> idIntervento: " + idIntervento + " - tipoDocumento: " + tipoDocumento
					+ " - idAllegato: " + idAllegato);
			String fileUID = indexSrvHelper.indexUploadFile(form.getName(), uploadForm, metadatiIndexVincolo);
			logger.info("fileUID: " + fileUID);
			documentoAllegato.setIdDocumentoAllegato(idAllegato);
			documentoAllegato.setUidIndex(fileUID);
			uploadPfaDocumentoWithStringUid(documentoAllegato);
			return documentoAllegato;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	@Transactional
	@Override
	public FatDocumentoAllegato uploadDocumentoTagli(int idIntervento, int tipoDocumento, FileUploadForm form, ConfigUtente loggedConfig) {
		try {
			byte[] uploadForm = form.getData();

			String descrTipoAllegato = tipoAllegatoDAO.getTipoById(tipoDocumento).getDescrTipoAllegato();
			FatDocumentoAllegato documentoAllegato = new FatDocumentoAllegato();
			documentoAllegato.setIdTipoAllegato(tipoDocumento);
			documentoAllegato.setDescrTipoAllegato(descrTipoAllegato);
			documentoAllegato.setDeleted(false);
			documentoAllegato.setNomeAllegato(form.getName());
			if(form.getNote() != null) {
				documentoAllegato.setNote(URLDecoder.decode(form.getNote(), "UTF-8"));
			}
			documentoAllegato.setDimensioneAllegatoKB(Long.valueOf(uploadForm.length) / 1024);
			documentoAllegato.setFkIntervento(idIntervento);
			documentoAllegato.setDataIniziValidita(LocalDate.now());
			documentoAllegato.setDataInserimento(LocalDate.now());
			documentoAllegato.setFkConfigUtente(loggedConfig.getIdConfigUtente());

			documentoAllegato.setIdGeoPlPfa(0);

			int idAllegato = createDocumentoAllegato(documentoAllegato);
			IndexServiceHelper indexSrvHelper = (IndexServiceHelper) applicationContext.getBean("indexServiceHelper");

			MetadatiIndexPrimpa metadatiIndexPrimpa = new MetadatiIndexPrimpa();
			metadatiIndexPrimpa.setIdIntervento(String.valueOf(idIntervento));
			metadatiIndexPrimpa.setIdTipoAllegato(String.valueOf(tipoDocumento));
			metadatiIndexPrimpa.setIdAllegato(String.valueOf(idAllegato));

			logger.info("metadatiIndexTrasf -> idIntervento: " + idIntervento + " - tipoDocumento: " + tipoDocumento
					+ " - idAllegato: " + idAllegato);
			String fileUID = indexSrvHelper.indexUploadFile(form.getName(), uploadForm, metadatiIndexPrimpa);


			logger.info("fileUID: " + fileUID);
			documentoAllegato.setIdDocumentoAllegato(idAllegato);
			documentoAllegato.setUidIndex(fileUID);
			uploadPfaDocumentoWithStringUid(documentoAllegato);
			return documentoAllegato;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Transactional
	@Override
	public FatDocumentoAllegato uploadDocumentoForPfa(int idIntervento, int idGeoPlPfa, int tipoDocumento,
			ConfigUtente loggedConfig, MultipartFormDataInput form, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {

			Map<String, List<InputPart>> uploadForm = form.getFormDataMap();
			InputPart inputPart = uploadForm.get("form").get(0);
			String fileNameWithExtension = getFileNameWithExtension(inputPart.getHeaders());

			byte[] content = inputPart.getBodyAsString().getBytes(StandardCharsets.UTF_8);
			IndexServiceHelper indexSrvHelper = (IndexServiceHelper) applicationContext.getBean("indexServiceHelper");

			String descrTipoAllegato = tipoAllegatoDAO.getTipoById(tipoDocumento).getDescrTipoAllegato();
			FatDocumentoAllegato documentoAllegato = new FatDocumentoAllegato();

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO idf_t_documento_allegato(");
			sql.append("  idgeo_pl_pfa, fk_intervento, fk_tipo_allegato");
			sql.append(", nome_allegato, dimensione_allegato_kb");
			sql.append(", data_inizio_validita, fk_config_utente");

			sql.append(") VALUES ( ?, ?, ?, ?, ?, ?, ?)");

			List<Object> parameters = new ArrayList<>();

			parameters.add(idGeoPlPfa);
			parameters.add(idIntervento);
			parameters.add(tipoDocumento);
			parameters.add(fileNameWithExtension);
			parameters.add(Long.valueOf(content.length / 1024));
			parameters.add(Date.valueOf(LocalDate.now()));
			// parameters.add(documento.getDataFineValidita() == null ? null :
			// Date.valueOf(documento.getDataFineValidita()));
			// parameters.add(documento.getDataInserimento() == null ? null :
			// Date.valueOf(documento.getDataInserimento()));
			// parameters.add(documento.getDataAggiornamento() == null ? null :
			// Date.valueOf(documento.getDataAggiornamento()));
			parameters.add(loggedConfig.getIdConfigUtente());

			int idAllegato = AIKeyHolderUtil.updateWithGenKey("id_documento_allegato", sql.toString(), parameters);

			MetadatiIndexPfa metadatiIndexPfa = new MetadatiIndexPfa();
			metadatiIndexPfa.setIdPfa(String.valueOf(idGeoPlPfa));
			metadatiIndexPfa.setIdIntervento(String.valueOf(idIntervento));
			metadatiIndexPfa.setIdTipoAllegato(String.valueOf(tipoDocumento));
			metadatiIndexPfa.setNotifica(true);
			metadatiIndexPfa.setIdAllegato(String.valueOf(idAllegato));

			String fileUID = indexSrvHelper.indexUploadFile(fileNameWithExtension, content, metadatiIndexPfa);

			String home = System.getProperty("user.home");
			String path = home + "\\Downloads\\" + fileNameWithExtension;

			File file = new File(path);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream fop = new FileOutputStream(file);
			fop.write(content);
			fop.flush();
			fop.close();

			documentoAllegato.setUidIndex(fileUID);
			documentoAllegato.setIdDocumentoAllegato(idAllegato);

			return documentoAllegato;

		} catch (Exception e) {
			return null;
		}

	}

	@Transactional
	@Override
	public FatDocumentoAllegato uploadDocumentoForPfaV2(int idIntervento, int idGeoPlPfa, int tipoDocumento,
			ConfigUtente loggedConfig, FileUploadForm form, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) throws ServiceException{
	
		byte[] content = form.getData();

		IndexServiceHelper indexSrvHelper = (IndexServiceHelper) applicationContext.getBean("indexServiceHelper");

		String descrTipoAllegato = tipoAllegatoDAO.getTipoById(tipoDocumento).getDescrTipoAllegato();
		FatDocumentoAllegato documentoAllegato = new FatDocumentoAllegato();
		
		documentoAllegato.setIdTipoAllegato(tipoDocumento);
		documentoAllegato.setDescrTipoAllegato(descrTipoAllegato);
		documentoAllegato.setDeleted(false);
		documentoAllegato.setNomeAllegato(form.getName());
		documentoAllegato.setDimensioneAllegatoKB(Long.valueOf(content.length) / 1024);
		documentoAllegato.setFkIntervento(idIntervento);
		documentoAllegato.setDataIniziValidita(LocalDate.now());
		documentoAllegato.setDataInserimento(LocalDate.now());
		documentoAllegato.setFkConfigUtente(loggedConfig.getIdConfigUtente());
		documentoAllegato.setIdGeoPlPfa(idGeoPlPfa);

		int idAllegato = createDocumentoAllegato(documentoAllegato);
			
		try {
			
			MetadatiIndexPfa metadatiIndexPfa = new MetadatiIndexPfa();
			metadatiIndexPfa.setIdPfa(String.valueOf(idGeoPlPfa));
			metadatiIndexPfa.setIdIntervento(String.valueOf(idIntervento));
			metadatiIndexPfa.setIdTipoAllegato(String.valueOf(tipoDocumento));
			metadatiIndexPfa.setNotifica(true);
			metadatiIndexPfa.setIdAllegato(String.valueOf(idAllegato));

			String fileUID = indexSrvHelper.indexUploadFile(form.getName(), content, metadatiIndexPfa);

			documentoAllegato.setUidIndex(fileUID);
			documentoAllegato.setIdDocumentoAllegato(idAllegato);

			return documentoAllegato;

		} catch (ServiceException e) {
			deleteDocumentoAllegatoByID(idAllegato);
			e.printStackTrace();
			throw e;
		}

	}

	@Override 
	public String deleteDocumentoById(Integer idDocumentoAllegato) {

		try {

			DocumentoAllegato documentoAllegato = findDocumentoByID(idDocumentoAllegato);
			if(documentoAllegato != null) {
				MetadatiIndexTrasformazioni metadatiIndexTrasf = new MetadatiIndexTrasformazioni();
				metadatiIndexTrasf.setIdIntervento(String.valueOf(documentoAllegato.getFkIntervento()));
				metadatiIndexTrasf.setIdTipoAllegato(String.valueOf(documentoAllegato.getFkTipoAllegato()));
				String uidIn = documentoAllegato.getUidIndex();
	
				String uidDeleted = deleteDocumentoFromPortal(uidIn, metadatiIndexTrasf);
	
				if (uidDeleted == null) {
					return null;
				} else {
					deleteDocumentoAllegatoByID(idDocumentoAllegato);
					return uidDeleted;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public String deletePfaDocumentoById(Integer idDocumentoAllegato) {
		try {

			DocumentoAllegato documentoAllegato = findDocumentoByID(idDocumentoAllegato);
			MetadatiIndexPfa metadatiIndexPfa = new MetadatiIndexPfa();
			metadatiIndexPfa.setIdIntervento(String.valueOf(documentoAllegato.getFkIntervento()));
			metadatiIndexPfa.setIdTipoAllegato(String.valueOf(documentoAllegato.getFkTipoAllegato()));
			String uidIn = documentoAllegato.getUidIndex();

			String uidDeleted = deletePfaDocumentoFromPortal(uidIn, metadatiIndexPfa);

			if (uidDeleted == null) {

				return null;
			} else {
				deleteDocumentoAllegatoByID(idDocumentoAllegato);

				return uidDeleted;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	private String deletePfaDocumentoFromPortal(String uidIn, MetadatiIndexPfa metadatiIndexPfa) {
		try {
			IndexServiceHelper indexSrvHelper = (IndexServiceHelper) applicationContext.getBean("indexServiceHelper");
			indexSrvHelper.indexGetFolder(metadatiIndexPfa);
			indexSrvHelper.indexDeleteFile(uidIn);
			return uidIn;

		} catch (ServiceException e) {
			e.printStackTrace();
			return null;
		}
	}

	private String getFileNameWithExtension(MultivaluedMap<String, String> header) {

		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");

		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {

				String[] name = filename.split("=");

				return name[1].trim().replaceAll("\"", "");
			}
		}
		return "unknown";
	}

	public String deleteDocumentoFromPortal(String uidIndex, MetadatiIndexTrasformazioni metadati) {

		try {
			IndexServiceHelper indexSrvHelper = (IndexServiceHelper) applicationContext.getBean("indexServiceHelper");

			indexSrvHelper.indexDeleteFile(uidIndex);
			return uidIndex;

		} catch (ServiceException e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public void uploadPfaDocumentoWithStringUid(FatDocumentoAllegato documentoAllegato) {

		StringBuilder sql = new StringBuilder();

		sql.append("UPDATE idf_t_documento_allegato ");
		sql.append(" SET note = ?, uid_index = ? ");
		sql.append(" WHERE id_documento_allegato = ?");

		List<Object> parameters = new ArrayList<>();
		parameters.add(documentoAllegato.getNote());
		parameters.add(documentoAllegato.getUidIndex());
		parameters.add(documentoAllegato.getIdDocumentoAllegato());

		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());

	}

	@Override
	public List<Integer> getAllDocumentsByIdIntervento(Integer idIntervento) {

		String sql = "SELECT id_documento_allegato FROM idf_t_documento_allegato WHERE fk_intervento = ?";

		return DBUtil.jdbcTemplate.queryForList(sql, Integer.class, idIntervento);
	}

	@Override
	public void deleteDocumentoAllegatoByIdIntervento(Integer idIntervento) {
		String sql = "DELETE FROM idf_t_documento_allegato WHERE fk_intervento = ?";
		DBUtil.jdbcTemplate.update(sql, idIntervento);

	}

	@Override
	public List<TipoAllegatoExtended> getTipiDocumentoPagamentoVincolo() {
		String sql = "SELECT id_tipo_allegato, descr_tipo_allegato, null as flg_obbligatorio "
				+ " FROM idf_d_tipo_allegato where id_tipo_allegato in (106,152,153) ";
		return DBUtil.jdbcTemplate.query(sql.toString(), new TipoAllegatoExtendedMapper());
	}


}
