/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.StatoIstanzaResource;
import it.csi.idf.idfapi.dto.TrasformazioniXls;
import it.csi.idf.idfapi.util.TipoIstanzaEnum;

public class TrasformazioniXlsMapper implements RowMapper<TrasformazioniXls>{
	
	@Override
	public TrasformazioniXls mapRow(ResultSet rs, int arg1) throws SQLException {
		TrasformazioniXls elem = new TrasformazioniXls();
		elem.setIdIstanza(rs.getInt("nr_istanza_forestale"));
		elem.setAnno(rs.getString("anno"));
		String descStato = StatoIstanzaResource.getDescrByTypeIstanza(rs.getString("descr_stato_istanza"), 
				TipoIstanzaEnum.DICHIARAZIONE_SOSTITUTIVA);
		elem.setStatoIstanza(descStato);
		elem.setRichiedente(rs.getString("richiedente"));
		elem.setComune(rs.getString("string_comune"));
		elem.setRicadenzaAapp(rs.getString("ricadenza_aapp"));
		elem.setRicadenzaRn2000(rs.getString("ricadenza_rn2000"));
		elem.setRicadenzaPopSeme(rs.getString("ricadenza_popseme"));
		elem.setRicadenzaVincIdro(rs.getString("ricadenza_vinc_idro"));
		elem.setSupInteressataHa(rs.getBigDecimal("superficie_interessata_ha"));
		elem.setGovernoPrevalente(rs.getString("governo_prevalente"));
		elem.setCatForPrevalente(rs.getString("cat_for_prevalente"));
		elem.setUbicazionePrevalente(rs.getString("ubicazione_prevalente"));
		elem.setVincoloPaesaggistico(rs.getString("vinc_paesaggistico"));
		elem.setVincoloIdrogeologico(rs.getString("vinc_idrogeologico"));
		elem.setVincoloAreaProtteta(rs.getString("vinc_area_protetta"));
		elem.setTipoTrasf1(rs.getString("tipo_trasf_1"));
		elem.setTipoTrasf2(rs.getString("tipo_trasf_2"));
		elem.setTipoTrasf3(rs.getString("tipo_trasf_3"));
		elem.setCompensazione(rs.getString("compensazione"));
		elem.setFlagArt7a(rs.getString("flg_art7_a"));
		elem.setFlagArt7b(rs.getString("flg_art7_b"));
		elem.setFlagArt7c(rs.getString("flg_art7_c"));
		elem.setFlagArt7d(rs.getString("flg_art7_d"));
		elem.setFlagArt7A21(rs.getString("flg_art7_a_21"));
		elem.setFlagArt7B21(rs.getString("flg_art7_b_21"));
		elem.setFlagArt7C21(rs.getString("flg_art7_c_21"));
		elem.setFlagArt7D21(rs.getString("flg_art7_d_21"));
		elem.setFlagArt7Dter21(rs.getString("flg_art7_dter_21"));
		elem.setFlagArt7Dquater21(rs.getString("flg_art7_dquater_21"));
		elem.setFlagArt7Dquinquies21(rs.getString("flg_art7_dquinquies_21"));
		elem.setCompensazioneEuro(rs.getBigDecimal("compensazione_euro_teorica"));
		elem.setCompensazioneEuroReale(rs.getBigDecimal("compensazione_euro_reale"));
		elem.setCompensazioneNote(rs.getString("compensazione_note"));		
		elem.setTecnicoForestale(rs.getString("tecnico_forestale"));
		elem.setDichiarazProprieta(rs.getString("dichiarazione_proprieta"));
		elem.setDichiarazDissensi(rs.getString("dichiarazione_dissensi"));
		elem.setAutPaesaggistica(rs.getString("aut_paesaggistica"));
		elem.setNumAutorizPaesagg(rs.getString("nr_aut_paesaggistica"));
		elem.setDataAutorizPaesagg(rs.getDate("data_aut_paesaggistica"));
		elem.setEnteAutorizPaesagg(rs.getString("ente_aut_paesaggistica"));
		elem.setAutorizVincoloIdro(rs.getString("aut_vinc_idro"));
		elem.setNumAutorizVincoloIdro(rs.getString("nr_aut_vidro"));
		elem.setDataAutorizVincoloIdro(rs.getDate("data_aut_vidro"));
		elem.setEnteAutorizVincoloIdro(rs.getString("ente_aut_vidro"));
		elem.setValIncidenzaNr2000(rs.getString("val_incidenza_rn2000"));
		elem.setNumAutValIncidenzaNr2000(rs.getString("nr_aut_incidenza"));
		elem.setDataAutValIncidenzaNr2000(rs.getDate("data_aut_incidenza"));
		elem.setEnteAutValIncidenzaNr2000(rs.getString("ente_aut_incidenza"));
		elem.setAltriPareri(rs.getString("altri_pareri"));
		elem.setFlagCauzioneCf(rs.getString("flg_cauzione_cf"));
		elem.setFlagVersamenteCm(rs.getString("flg_versamento_cm"));
		elem.setNoteDichiarazione(rs.getString("note_dichiarazione"));
		elem.setDataInserimento(rs.getDate("data_inserimento"));
		elem.setDataInvio(rs.getDate("data_invio"));
		elem.setNrProtocollo(rs.getString("nr_protocollo"));
		elem.setDataProtocollo(rs.getDate("data_protocollo"));
		elem.setUtenteCompilatore(rs.getString("utente_compilatore"));
		elem.setDataVerifica(rs.getDate("data_verifica"));
		elem.setMotiviazioneRifiuto(rs.getString("motivazione_rifiuto"));
		elem.setUtenteGestore(rs.getString("utente_gestore"));
		return elem;
	}

}
