/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import it.csi.idf.idfapi.dto.TagliXls;
import it.csi.idf.idfapi.dto.TrasformazioniXls;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TagliXlsMapper implements RowMapper<TagliXls>{
	
	@Override
	public TagliXls mapRow(ResultSet rs, int arg1) throws SQLException {
		TagliXls item = new TagliXls();
		item.setNrIstanzaForestale(rs.getInt("nr_istanza_forestale"));
		item.setAnnoIstanza(rs.getString("anno_istanza"));
		item.setTipoIstanza(rs.getString("tipo_istanza"));
		item.setStatoIstanza(rs.getString("stato_istanza"));
		item.setStringComune(rs.getString("string_comune"));
		item.setCategIntervento(rs.getString("categ_intervento"));
		item.setProprietaBosco(rs.getString("proprieta_bosco"));
		item.setAnnataSilvana(rs.getString("annata_silvana"));
		item.setDataInizio(rs.getDate("data_inizio"));
		item.setStatoIntervento(rs.getString("stato_intervento"));
		item.setInterventoCompensativo(rs.getString("intervento_compensativo"));
		item.setTipoRichiedente(rs.getString("tipo_richiedente"));
		item.setRichiedente(rs.getString("richiedente"));
		item.setNrAlboForestale(rs.getString("nr_albo_forestale"));
		item.setUtilizzatore(rs.getString("utilizzatore"));
		item.setTecnicoForestale(rs.getString("tecnico_forestale"));
		item.setSuperficieInteressataHa(rs.getBigDecimal("superficie_interessata_ha"));
		item.setRicadenzaAapp(rs.getString("ricadenza_aapp"));
		item.setRicadenzaRn2000(rs.getString("ricadenza_rn2000"));
		item.setRicadenzaPopseme(rs.getString("ricadenza_popseme"));
		item.setCategorieForestali(rs.getString("categorie_forestali"));
		item.setPfa(rs.getString("pfa"));
		item.setFasciaAltimetrica(rs.getString("fascia_altimetrica"));
		item.setDescrizioneIntervento(rs.getString("descrizione_intervento"));
		item.setGovernoPrincipale(rs.getString("governo_principale"));
		item.setTipoInterventoPrincipale(rs.getString("tipo_intervento_principale"));
		item.setSuperficieInt1Ha(rs.getBigDecimal("superficie_int1_ha"));
		item.setGovernoSecondario(rs.getString("governo_secondario"));
		item.setTipoInterventoSecondario(rs.getString("tipo_intervento_secondario"));
		item.setSuperficieInt2Ha(rs.getBigDecimal("superficie_int2_ha"));
		item.setStringUsoviab(rs.getString("string_usoviab"));
		item.setNoteEsbosco(rs.getString("note_esbosco"));
		item.setVolumeSpecie(rs.getBigDecimal("volume_specie"));
		item.setDescrUdm(rs.getString("descr_udm"));
		item.setVolumeRamagliaM3(rs.getBigDecimal("volume_ramaglia_m3"));
		item.setDataInserimento(rs.getDate("data_inserimento"));
		item.setDataInvio(rs.getDate("data_invio"));
		item.setNrProtocollo(rs.getString("nr_protocollo"));
		item.setDataProtocollo(rs.getDate("data_protocollo"));
		item.setDataVerifica(rs.getDate("data_verifica"));
		item.setNrDeterminaAut(rs.getString("nr_determina_aut"));
		item.setDataTermineAut(rs.getDate("data_termine_aut"));
		item.setCompilatore(rs.getString("compilatore"));
		return item;
	}

}
