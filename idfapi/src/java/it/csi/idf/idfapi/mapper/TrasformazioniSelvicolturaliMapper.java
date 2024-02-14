/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.StatoIstanzaResource;
import it.csi.idf.idfapi.dto.TrasformazSelvicolturali;
import it.csi.idf.idfapi.util.TipoIstanzaEnum;

public class TrasformazioniSelvicolturaliMapper implements RowMapper<TrasformazSelvicolturali>{
	
	@Override
	public TrasformazSelvicolturali mapRow(ResultSet rs, int arg1) throws SQLException {
		TrasformazSelvicolturali elem = new TrasformazSelvicolturali();
		elem.setIdIstanza(rs.getInt("id_istanza_intervento"));
		elem.setNrIstanza(rs.getInt("nr_istanza_forestale"));
		elem.setAnno(rs.getString("anno"));
		String descStato = StatoIstanzaResource.getDescrByTypeIstanza(rs.getString("descr_stato_istanza"), 
				TipoIstanzaEnum.DICHIARAZIONE_SOSTITUTIVA);
		elem.setStatoIstanza(descStato);
		elem.setRichiedente(rs.getString("richiedente"));
		elem.setComune(rs.getString("string_comune"));
		elem.setCompensazione(rs.getString("compensazione"));
		return elem;
	}

}
