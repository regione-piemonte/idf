/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import it.csi.idf.idfapi.dto.GeneratedFileTagliParameters;
import it.csi.idf.idfapi.util.SuperficieCompensationEnum;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DichiarazioneTagliSummaryMapper implements RowMapper<GeneratedFileTagliParameters>{

	@Override
	public GeneratedFileTagliParameters mapRow(ResultSet rs, int arg1) throws SQLException {

		GeneratedFileTagliParameters genFileParams = new GeneratedFileTagliParameters();
		
		genFileParams.setIdIntervento(rs.getInt("id_intervento")); 
		genFileParams.setNrIstanza(rs.getInt("nr_istanza_forestale")); 
		genFileParams.setFkConfigUtente(rs.getInt("fk_config_utente"));
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

		//todo:
		// add other fields

/*
		genFileParams.setFlgCompensazione(rs.getString("flg_compensazione") == null ? null
				: SuperficieCompensationEnum.getCompensationEnum(rs.getString("flg_compensazione")));
		genFileParams.setFlgArt7a(rs.getByte("flg_art7_a") == 1);
		genFileParams.setFlgArt7b(rs.getByte("flg_art7_b") == 1);
		genFileParams.setFlgArt7c(rs.getByte("flg_art7_c") == 1);
		genFileParams.setFlgArt7d(rs.getByte("flg_art7_d") == 1);		
		
		genFileParams.setFlgArt7A21(rs.getByte("flg_art7_a_21") == 1);
		genFileParams.setFlgArt7B21(rs.getByte("flg_art7_b_21") == 1);
		genFileParams.setFlgArt7C21(rs.getByte("flg_art7_c_21") == 1);
		genFileParams.setFlgArt7D21(rs.getByte("flg_art7_d_21") == 1);
		genFileParams.setFlgArt7Dter21(rs.getByte("flg_art7_dter_21") == 1);
		genFileParams.setFlgArt7Dquater21(rs.getByte("flg_art7_dquater_21") == 1);
		genFileParams.setFlgArt7Dquinquies21(rs.getByte("flg_art7_dquinquies_21") == 1);	
		String[] thirdStep = rs.getString("id_parametro_trasf").split(",");
*/

/*
		for(String val: thirdStep) {
			switch (val.trim()) {
			case "1":
				genFileParams.setFormaGovernoFlg1(true);
				break;
			case "2":
				genFileParams.setFormaGovernoFlg2(true);
				break;
			case "3":
				genFileParams.setCategForestFlg1(true);
				break;
			case "4":
				genFileParams.setCategForestFlg2(true);
				break;
			case "5":
				genFileParams.setCategForestFlg3(true);
				break;
			case "6":
				genFileParams.setUbicazioneFlg1(true);
				break;
			case "7":
				genFileParams.setUbicazioneFlg2(true);
				break;
			case "8":
				genFileParams.setUbicazioneFlg3(true);
				break;
			case "9":
				genFileParams.setDestVincFlg1(true);
				break;
			case "10":
				genFileParams.setDestVincFlg2(true);
				break;
			case "11":
				genFileParams.setDestVincFlg3(true);
				break;
			case "12":
				genFileParams.setTipologiaFlg1(true);
				break;
			case "13":
				genFileParams.setTipologiaFlg2(true);
				break;
			case "14":
				genFileParams.setTipologiaFlg3(true);
				break;
			default:
			}
		}
*/

		genFileParams.setProfCognome(rs.getString("prof_cognome"));
		genFileParams.setProfNome(rs.getString("prof_nome"));
		genFileParams.setProfCodiceFiscale(rs.getString("prof_codice_fiscale"));
		genFileParams.setProfProvinciaOrdine(rs.getString("prof_prov_ordine"));
		genFileParams.setProfNIscrizione(rs.getString("prof_iscrizione"));
		genFileParams.setProfTelefonico(rs.getString("prof_telefonico"));
		genFileParams.setProfPec(rs.getString("prof_pec"));

/*
		genFileParams.setCompenzazioneEuro( ( rs.getBigDecimal("compensazione_euro_teorica")==null)? null: rs.getBigDecimal("compensazione_euro_teorica").toString());
		genFileParams.setCompenzazioneEuroReale(( rs.getBigDecimal("compensazione_euro_reale")==null)? null: rs.getBigDecimal("compensazione_euro_reale").toString());
		genFileParams.setNoteCompenzazioneEuroReale(rs.getString("compensazione_note"));
		genFileParams.setDichProprietario(rs.getByte("flg_proprieta") == 1);
		genFileParams.setDichDissenso(rs.getByte("flg_dissensi") == 1);
		genFileParams.setDichAutPaesaggistica(rs.getByte("flg_aut_paesaggistica") == 1);
		genFileParams.setDichDataPaesaggistica(rs.getDate("data_aut_paesaggistica") == null ? null
				: rs.getDate("data_aut_paesaggistica").toLocalDate());
		genFileParams.setDichNrPaesaggistica(rs.getString("nr_aut_paesaggistica"));
		genFileParams.setDichEntePaesaggistica(rs.getString("ente_aut_paesaggistica"));
		genFileParams.setDichAutVidro(rs.getByte("flg_aut_vidro") == 1);
		genFileParams.setDichDataVidro(
				rs.getDate("data_aut_vidro") == null ? null : rs.getDate("data_aut_vidro").toLocalDate());
		genFileParams.setDichNrVidro(rs.getString("nr_aut_vidro"));
		genFileParams.setDichEnteVidro(rs.getString("ente_aut_vidro"));
		genFileParams.setDichAutIncidenza(rs.getByte("flg_aut_incidenza") == 1);
		genFileParams.setDichDataIncidenza(
				rs.getDate("data_aut_incidenza") == null ? null : rs.getDate("data_aut_incidenza").toLocalDate());
		genFileParams.setDichNrIncidenza(rs.getString("nr_aut_incidenza"));
		genFileParams.setDichEnteIncidenza(rs.getString("ente_aut_incidenza"));
		genFileParams.setDichAltriPareri(rs.getString("altri_pareri"));
*/

		genFileParams.setSuperficieInteressata( ( rs.getBigDecimal("superficie_interessata_ha")==null)? null: rs.getBigDecimal("superficie_interessata_ha"));

		return genFileParams;
	}
}
