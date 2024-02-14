/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.business.be.vincolo.pojo.VincoloXls;
import it.csi.idf.idfapi.dto.StatoIstanzaResource;
import it.csi.idf.idfapi.util.TipoIstanzaEnum;

public class VincoloXlsMapper implements RowMapper<VincoloXls> {

	@Override
	public VincoloXls mapRow(ResultSet rs, int arg1) throws SQLException {

		VincoloXls item = new VincoloXls();
		
		item.setNrIstanzaForestale(rs.getInt("nr_istanza_forestale"));
		item.setVariante(rs.getString("variante"));
		item.setNrIstanzaOrigineVariante(rs.getInt("nr_istanza_origine_variante"));
		item.setProroga(rs.getString("proroga"));
		item.setNrIstanzaOrigineProroga(rs.getInt("nr_istanza_origine_proroga"));
		item.setCompetenza(rs.getString("competenza"));
		item.setSoggettoCompetenza(rs.getString("soggetto_competenza"));
		item.setAnno(rs.getString("anno"));
		String descStato = StatoIstanzaResource.getDescrByTypeIstanza(rs.getString("descr_stato_istanza"), 
				TipoIstanzaEnum.VINCOLO_AUTORIZZAZIONE_COMUNALE);
		item.setDescrStatoIstanza(descStato);
		item.setRichiedente(rs.getString("richiedente"));
		item.setStringComune(rs.getString("string_comune"));
		item.setRicadenzaAapp(rs.getString("ricadenza_aapp"));
		item.setRicadenzaRn2000(rs.getString("ricadenza_rn2000"));
		item.setRicadenzaPopseme(rs.getString("ricadenza_popseme"));
		item.setDescrizioneIntervento(rs.getString("descrizione_intervento"));
		item.setTipoIntervento(rs.getString("tipo_intervento"));
		item.setDescTipoIntervAltro(rs.getString("desc_tipo_interv_altro"));
		item.setSuperficieInteressataHa(rs.getDouble("superficie_interessata_ha"));
		item.setMovimentiTerraMc(rs.getDouble("movimenti_terra_mc"));
		item.setMovimentiTerraVincidroMc(rs.getDouble("movimenti_terra_vincidro_mc"));
		item.setMesiIntervento(rs.getString("mesi_intervento"));
		item.setPresenzaAreeDissesto(rs.getString("presenza_aree_dissesto"));
		item.setPresenzaAreeEsondazione(rs.getString("presenza_aree_esondazione"));
		item.setCoperturaVegetaleInteressata(rs.getString("copertura_vegetale_interessata"));
		item.setCauzione(rs.getString("cauzione"));
		item.setCompensazione(rs.getString("compensazione"));
		item.setCmSupboscHa(rs.getDouble("cm_supbosc_ha"));
		item.setCmNoboscEuro(rs.getDouble("cm_supnobosc_ha"));
		item.setCmBoscEuro(rs.getDouble("cm_bosc_euro"));
		item.setCmNoboscEuro(rs.getDouble("cm_nobosc_euro"));
		item.setArt9_4a(rs.getString("art9_4a"));
		item.setArt9_4b(rs.getString("art9_4b"));
		item.setArt9_4c(rs.getString("art9_4c"));
		item.setArt19_7a(rs.getString("art19_7a"));
		item.setArt19_7b(rs.getString("art19_7b"));
		item.setArt19_7c(rs.getString("art19_7c"));
		item.setArt19_7d(rs.getString("art19_7d"));
		item.setArt19_7dbis(rs.getString("art19_7dbis"));
		item.setProprietario(rs.getString("proprietario"));
		item.setDissensi(rs.getString("dissensi"));
		item.setImportoIntervento(rs.getString("importo_intervento"));
		item.setSuap(rs.getString("suap"));
		item.setConfServizi(rs.getString("conf_servizi"));
		item.setCopiaConforme(rs.getString("copia_conforme"));
		item.setVersamentoSpeseIstruttoria(rs.getString("versamento_spese_istruttoria"));
		item.setMarcaDaBollo(rs.getString("marca_da_bollo"));
		item.setnMarcaBollo(rs.getString("n_marca_bollo"));
		item.setAllegatoProgettoDefinitivo(rs.getString("allegato_progetto_definitivo"));
		item.setAllegatoRelazioneTecnica(rs.getString("allegato_relazione_tecnica"));
		item.setAllegatoRelazioneGeologica(rs.getString("allegato_relazione_geologica"));
		item.setAllegatoRelazioneForestale(rs.getString("allegato_relazione_forestale"));
		item.setAllegatoProgettoRimboschimento(rs.getString("allegato_progetto_rimboschimento"));
		item.setAllegatoRicevutaVersCompMonetaria(rs.getString("allegato_ricevuta_vers_comp_monetaria"));
		item.setAllegatoDocumentazioneFotografica(rs.getString("allegato_documentazione_fotografica"));
		item.setAllegatoRelazioneNivologica(rs.getString("allegato_relazione_nivologica"));
		item.setAllegatoEstrattoPlanimetrico(rs.getString("allegato_estratto_planimetrico"));
		item.setAllegatoSchedaTecnica(rs.getString("allegato_scheda_tecnica"));
		item.setAllegatoPlanimetriaCatastale(rs.getString("allegato_planimetria_catastale"));
		item.setAllegatoEstrattoAerofotogrammetrico(rs.getString("allegato_estratto_aerofotogrammetrico"));
		item.setAllegatoDichsostDispArea(rs.getString("allegato_dich_sost_disp_area"));
		item.setAllegatoLibero1(rs.getString("allegato_libero_1"));
		item.setAllegatoLibero2(rs.getString("allegato_libero_2"));
		item.setDataInserimento(rs.getDate("data_inserimento"));
		item.setDataInvio(rs.getDate("data_invio"));
		item.setNrProtocollo(rs.getString("nr_protocollo"));
		item.setDataProtocollo(rs.getDate("data_protocollo"));
		item.setUtenteCompilatore(rs.getString("utente_compilatore"));
		item.setDataVerifica(rs.getDate("data_verifica"));
		item.setMotivazioneRifiuto(rs.getString("motivazione_rifiuto"));
		item.setUtenteGestore(rs.getString("utente_gestore"));
		
		return item;
	}

}
