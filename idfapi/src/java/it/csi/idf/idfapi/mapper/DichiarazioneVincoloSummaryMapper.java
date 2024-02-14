/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.GeneratedFileVincoloParameters;

public class DichiarazioneVincoloSummaryMapper  implements RowMapper<GeneratedFileVincoloParameters>{

	@Override
	public GeneratedFileVincoloParameters mapRow(ResultSet rs, int arg1) throws SQLException {
		
		GeneratedFileVincoloParameters genFileParams = new GeneratedFileVincoloParameters();
		
		genFileParams.setIdIntervento(rs.getInt("id_intervento")); 
		genFileParams.setNrIstanza(rs.getInt("nr_istanza_forestale")); 
		genFileParams.setFkConfigUtente(rs.getInt("fk_config_utente"));
//			genFileParams.setTipoTitolarita(TipoTitolaritaEnum.getTitolarita(rs.getInt("fk_tipo_titolarita")));
		genFileParams.setIdTipoIstanza(rs.getInt("fk_tipo_istanza"));
		genFileParams.setRichCognome(rs.getString("cognome"));
		genFileParams.setRichNome(rs.getString("nome"));
		genFileParams.setRichRagSociale(rs.getString("denominazione"));
		genFileParams.setRichCodiceFiscale(rs.getString("codice_fiscale"));
		genFileParams.setRichPartitaIva(rs.getString("partita_iva"));
		genFileParams.setRichIndirizzo(rs.getString("indirizzo"));
		genFileParams.setRichCivico(rs.getString("civico"));
		genFileParams.setRichCap(rs.getString("cap"));
		genFileParams.setRichTelefonico(rs.getString("nr_telefonico"));
		genFileParams.setRichEmail(rs.getString("e_mail"));
		genFileParams.setRichPec(rs.getString("pec"));
		genFileParams.setRichComune(rs.getString("denominazione_comune"));
		genFileParams.setRichProvincia(rs.getString("denominazione_prov"));
		genFileParams.setDescTipoTntervAltro(rs.getString("desc_tipo_interv_altro"));
		genFileParams.setMovimentiTerraMc(rs.getDouble("movimenti_terra_mc"));
		genFileParams.setMovimentiTerraVincidroMc(rs.getDouble("movimenti_terra_vincidro_mc"));
		genFileParams.setMesiIntervento(rs.getInt("mesi_intervento"));
		genFileParams.setFlgAreeDissesto(rs.getInt("flg_aree_dissesto")==1);
		genFileParams.setFlgAreeEsondazione(rs.getInt("flg_aree_esondazione")==1);
		genFileParams.setFlgCauzioneVi(rs.getInt("flg_cauzione_vi"));
		genFileParams.setFlgCompensazione(rs.getString("flg_compensazione"));
		genFileParams.setCmBoscoEuro(rs.getDouble("cm_bosc_euro"));
		genFileParams.setCmNoBoscoEuro(rs.getDouble("cm_nobosc_euro"));	
		genFileParams.setFlgArt7a(rs.getByte("flg_art7_a") == 1);
		genFileParams.setFlgArt7b(rs.getByte("flg_art7_b") == 1);
		genFileParams.setFlgArt7c(rs.getByte("flg_art7_c") == 1);
		genFileParams.setFlgArt7d(rs.getByte("flg_art7_d") == 1);
		genFileParams.setFlgArt7dBis(rs.getByte("flg_art7_d_bis") == 1);
		genFileParams.setFlgArt9a(rs.getByte("flg_art9_a") == 1);
		genFileParams.setFlgArt9b(rs.getByte("flg_art9_b") == 1);
		genFileParams.setFlgArt9c(rs.getByte("flg_art9_c") == 1);
		
		genFileParams.setFlgSpeseIstruttoria(rs.getByte("flg_spese_istruttoria") == 1);
		genFileParams.setFlgEsenzioneMarcaBollo(rs.getByte("flg_esenzione_marca_bollo") == 1);
		genFileParams.setnMarcaBollo(rs.getString("n_marca_bollo"));
		genFileParams.setFlgImporto(rs.getByte("flg_importo") == 1);
		genFileParams.setFlgCopiaConforme(rs.getByte("flg_copia_conforme") == 1);
		genFileParams.setFlgConfServizi(rs.getByte("flg_conf_servizi") == 1);
		genFileParams.setFlgSuap(rs.getByte("flg_suap") == 1);
		genFileParams.setFlgProprieta(rs.getByte("flg_proprieta") == 1);
		genFileParams.setFlgDissensi(rs.getByte("flg_dissensi") == 1);
		genFileParams.setCmSupboscHa(rs.getDouble("cm_supbosc_ha"));
		genFileParams.setCmSupnoboscHa(rs.getDouble("cm_supnobosc_ha"));

		genFileParams.setProfCognome(rs.getString("prof_cognome"));
		genFileParams.setProfNome(rs.getString("prof_nome"));
		genFileParams.setProfCodiceFiscale(rs.getString("prof_codice_fiscale"));
		genFileParams.setProfProvinciaOrdine(rs.getString("prof_prov_ordine"));
		genFileParams.setProfNIscrizione(rs.getString("prof_iscrizione"));
		genFileParams.setProfTelefonico(rs.getString("prof_telefonico"));
		genFileParams.setProfPec(rs.getString("prof_pec"));
		
		String vetTipiAllegatoStr = rs.getString("allegati_types");
		
		List<Integer> listTipiAlleato = new ArrayList<Integer>();
		if(vetTipiAllegatoStr.length()>0) {
			String[] vetTipiAllegato = vetTipiAllegatoStr.split(",");
			for(String id : vetTipiAllegato) {
				listTipiAlleato.add(Integer.parseInt(id));
			}
		}
		genFileParams.setAllegatiTypes(listTipiAlleato);
		

		String[] thirdStep = rs.getString("ids_governo").split(",");
		
		for(String val: thirdStep) {
			switch (val.trim()) {
			case "1":
				genFileParams.setFlgGovCeduo(true);
				break;
			case "2":
				genFileParams.setFlgGovFustaia(true);
				break;
			case "3":
				genFileParams.setFlgGovMisto(true);
				break;
			case "4":
				genFileParams.setFlgGovAltro(true);
				break;
			case "5":
				genFileParams.setFlgGovRobineti(true);
				break;
			case "6":
				genFileParams.setFlgGovCastagneti(true);
				break;
			default:
			}
		}
		
		return genFileParams;
	}
}
