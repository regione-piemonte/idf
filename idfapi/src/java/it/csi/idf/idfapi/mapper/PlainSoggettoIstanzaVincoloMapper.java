/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.ComuneResource;
import it.csi.idf.idfapi.dto.PlainSoggettoIstanzaVincolo;
import it.csi.idf.idfapi.dto.SoggettoResource;
import it.csi.idf.idfapi.dto.StatoIstanzaResource;
import it.csi.idf.idfapi.util.TipoIstanzaEnum;

public class PlainSoggettoIstanzaVincoloMapper implements RowMapper<PlainSoggettoIstanzaVincolo> {

	@Override
	public PlainSoggettoIstanzaVincolo mapRow(ResultSet rs, int arg1) throws SQLException {
		PlainSoggettoIstanzaVincolo plainSoggettoIstanza = new PlainSoggettoIstanzaVincolo();
		
		plainSoggettoIstanza.setIdIntervento(rs.getInt("id_istanza_intervento"));
		plainSoggettoIstanza.setNrIstanzaForestale(rs.getInt("nr_istanza_forestale"));
		plainSoggettoIstanza.setIdTipoIstanza(rs.getInt("fk_tipo_istanza"));
		plainSoggettoIstanza.setDataInvio(rs.getDate("data_invio") == null ? null : rs.getDate("data_invio").toLocalDate());
		
		SoggettoResource soggettoResource = new SoggettoResource();
		soggettoResource.setIdSoggetto(rs.getInt("id_soggetto"));
		soggettoResource.setCodiceFiscale(rs.getString("codice_fiscale"));
		soggettoResource.setPartitaIva(rs.getString("partita_iva"));
		soggettoResource.setNome(rs.getString("nome"));
		soggettoResource.setCognome(rs.getString("cognome"));
		soggettoResource.setDenominazione(rs.getString("denominazione"));
		plainSoggettoIstanza.setRichiedente(soggettoResource);
		
		ComuneResource comuneResource = new ComuneResource();
		//comuneResource.setIdComune(rs.getInt("id_comune"));
		//comuneResource.setIstatComune(rs.getString("istat_comune"));
		//comuneResource.setIstatProv(rs.getString("istat_prov"));
		comuneResource.setDenominazioneComune(rs.getString("denominazione_comune"));
		plainSoggettoIstanza.setComune(comuneResource);
		
		String descStato = StatoIstanzaResource.getDescrByTypeIstanza(rs.getString("descr_stato_istanza"), 
				TipoIstanzaEnum.VINCOLO_AUTORIZZAZIONE_REGIONALE);
		
		plainSoggettoIstanza.setStato(descStato);
		plainSoggettoIstanza.setIdStato(rs.getInt("fk_stato_istanza"));
		
		plainSoggettoIstanza.setStatoCauzione(rs.getString("stato_cauzione") + rs.getString("stato_compensazione"));
		plainSoggettoIstanza.setVarianteProroga(rs.getString("variante_proroga"));
		
		plainSoggettoIstanza.setCompetenza(rs.getString("competenza"));
		plainSoggettoIstanza.setRimboschimento(rs.getString("rimboschimento"));
		
		return plainSoggettoIstanza;
	}

}
