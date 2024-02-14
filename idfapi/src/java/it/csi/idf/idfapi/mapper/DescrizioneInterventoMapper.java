/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.DescrizioneIntervento;
import it.csi.idf.idfapi.util.DBUtil;

public class DescrizioneInterventoMapper implements RowMapper<DescrizioneIntervento>{

	@Override
	public DescrizioneIntervento mapRow(ResultSet rs, int arg1) throws SQLException {
		DescrizioneIntervento descrizioneIntervento =new DescrizioneIntervento();
		descrizioneIntervento.setDescrizione(rs.getString("descrizione")==null ? null :rs.getString("descrizione"));
		descrizioneIntervento.setGoverno(rs.getString("governo"));
		descrizioneIntervento.setTipoDiIntervento(rs.getString("tipo_di_intervento"));
		descrizioneIntervento.setCategorieForestali(rs.getString("categorie_forestali"));
		descrizioneIntervento.setLocalita(rs.getString("localita")==null ? null : rs.getString("localita"));
		descrizioneIntervento.setFlgPiedilista("1".equals(rs.getString("flg_piedilista")) ? "Si" : "No");
		descrizioneIntervento.setNumeroPiante(DBUtil.getIntegerValueFromResultSet(rs,"numero_piante"));
		descrizioneIntervento.setFinalitaDelTaglio(rs.getString("finalita_del_taglio"));
		descrizioneIntervento.setDestinazioneDelLegname(rs.getString("destinazione_del_legname"));
		descrizioneIntervento.setRamaglia(DBUtil.getIntegerValueFromResultSet(rs,"ramaglia"));
		descrizioneIntervento.setStimaMassaRetraibileM3(DBUtil.getIntegerValueFromResultSet(rs,"stima_massa_retraibile_m3"));
		descrizioneIntervento.setViabilita(rs.getString("viabilita"));
		descrizioneIntervento.setTipoDiEsbosco(rs.getString("tipo_di_esbosco"));
		descrizioneIntervento.setNoteEsbosco(rs.getString("note_esbosco"));
		descrizioneIntervento.setFasciaAltimetrica(rs.getString("fascia_altimetrica"));
		descrizioneIntervento.setSpecieInteresate(rs.getString("specie_interesate"));
		descrizioneIntervento.setSupTagliata(DBUtil.getIntegerValueFromResultSet(rs, "sup_tagliata_ha"));
		descrizioneIntervento.setSupInteressata(rs.getBigDecimal("sup_tagliata_ha"));//la stessa di superficie tagliata
		descrizioneIntervento.setSupCatastale(rs.getBigDecimal("totale_sup_catastale"));
		
		return descrizioneIntervento;
	}

}
