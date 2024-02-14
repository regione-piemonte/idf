/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.ComuneResource;
import it.csi.idf.idfapi.dto.PlainSoggettoIstanzaTagli;
import it.csi.idf.idfapi.dto.StatoIstanzaResource;
import it.csi.idf.idfapi.util.TipoIstanzaEnum;

public class PlainSoggettoIstanzaTagliMapper implements RowMapper<PlainSoggettoIstanzaTagli> {

	@Override
	public PlainSoggettoIstanzaTagli mapRow(ResultSet rs, int arg1) throws SQLException {
		PlainSoggettoIstanzaTagli dto = new PlainSoggettoIstanzaTagli();

		dto.setIdIntervento(rs.getInt("id_intervento"));
		dto.setNrIstanza(rs.getString("nr_istanza"));
		dto.setIdTipoIstanza(rs.getInt("fk_tipo_istanza"));
		dto.setNrIstanzaForestale(rs.getInt("nr_istanza_forestale"));
		dto.setDataInserimento(rs.getDate("data_inserimento") == null ? null : rs.getDate("data_inserimento").toLocalDate());
		dto.setRichiedente(rs.getString("richiedente"));
		dto.setDescrizioneTipoIstanza(rs.getString("descr_dett_tipoistanza"));
		dto.setDescrizioneIntervento(rs.getString("descrizione_intervento"));
		dto.setTipoIntervento(rs.getString("tipo_intervento"));

		ComuneResource comuneResource = new ComuneResource();
		comuneResource.setDenominazioneComune(rs.getString("string_comune"));
		dto.setComune(comuneResource);
		String descStato = StatoIstanzaResource.getDescrByTypeIstanza(rs.getString("stato_istanza"), 
				TipoIstanzaEnum.getEnumByValue(rs.getInt("fk_tipo_istanza")));
		dto.setStato(descStato);
		dto.setIdStato(rs.getInt("fk_stato_istanza"));
		

		return dto;
	}

}
