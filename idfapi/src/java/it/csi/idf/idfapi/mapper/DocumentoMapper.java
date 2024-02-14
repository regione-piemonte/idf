/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.DocumentoAllegato;

public class DocumentoMapper implements RowMapper<DocumentoAllegato> {

	@Override
	public DocumentoAllegato mapRow(ResultSet rs, int arg1) throws SQLException {
		
		DocumentoAllegato documento = new DocumentoAllegato();
		
		documento.setIdDocumentoAllegato(rs.getInt("id_documento_allegato"));
		documento.setIdGeoPlPfa(rs.getInt("idgeo_pl_pfa"));
		documento.setFkIntervento(rs.getInt("fk_intervento"));
		documento.setFkTipoAllegato(rs.getInt("fk_tipo_allegato"));
		documento.setNomeAllegato(rs.getString("nome_allegato"));
		documento.setDimensioneAllegatoKB(rs.getLong("dimensione_allegato_kb"));
		documento.setDataIniziValidita(rs.getDate("data_inizio_validita").toLocalDate()== null ? null : rs.getDate("data_inizio_validita").toLocalDate());
		documento.setDataFineValidita(rs.getDate("data_fine_validita")== null ? null : rs.getDate("data_fine_validita").toLocalDate());
		documento.setDataInserimento(rs.getDate("data_inserimento")== null ? null :rs.getDate("data_inserimento").toLocalDate());
		documento.setDataAggiornamento(rs.getDate("data_aggiornamento")== null ? null :rs.getDate("data_aggiornamento").toLocalDate());
		documento.setFkConfigUtente(rs.getInt("fk_config_utente"));
		documento.setNote(rs.getString("note"));
		documento.setUidIndex(rs.getString("uid_index"));
		documento.setIdDocDoqui(rs.getString("id_doc_doqui"));
		documento.setUdClassificazioneDoqui(rs.getString("id_classificazione_doqui"));
		return documento;
	}

}
