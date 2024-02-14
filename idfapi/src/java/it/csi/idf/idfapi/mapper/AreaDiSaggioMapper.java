/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.AreaDiSaggio;

public class AreaDiSaggioMapper extends AreaDiSaggioDTOMapper implements RowMapper<AreaDiSaggio> {

	@Override
	public AreaDiSaggio mapRow(ResultSet rs, int arg1) throws SQLException {
		AreaDiSaggio areaDiSaggio = super.mapRow(rs, arg1);

		areaDiSaggio.setAmbitoSpecifico(rs.getString("ambito_specifico"));
		areaDiSaggio.setAssettoEvolutivoColturale(rs.getString("descr_assetto"));
		areaDiSaggio.setCoperturaChiome(rs.getString("perc_copertura_chiome"));
		areaDiSaggio.setCoperturaErbacea(rs.getString("perc_copertura_erbacea"));
		areaDiSaggio.setCoperturaCespugli(rs.getString("perc_copertura_cespugli"));
		areaDiSaggio.setDefogliazione(rs.getString("perc_defogliazione"));
		areaDiSaggio.setDefp(rs.getString("dist_esbosco_fuori_pista"));
		areaDiSaggio.setDensitaCamp(rs.getString("densita_campionamento"));
		areaDiSaggio.setDesp(rs.getString("dist_esbosco_su_pista"));
		areaDiSaggio.setDestinazione(rs.getString("descr_destinazione"));
		areaDiSaggio.setEsbosco(rs.getString("cod_esbosco"));
		areaDiSaggio.setEsboscoDescr(rs.getString("descr_esbosco"));
		areaDiSaggio.setEspozione(rs.getString("descr_esposizione"));
		areaDiSaggio.setInclinazione(rs.getString("inclinazione"));
		areaDiSaggio.setIngiallimento(rs.getString("perc_ingiallimento"));
		areaDiSaggio.setIntervento(rs.getString("descr_intervento"));
		areaDiSaggio.setIntesitaDanni(rs.getString("danni_perc"));
		areaDiSaggio.setLettiera(rs.getString("perc_copertura_lettiera"));
		areaDiSaggio.setMdp(rs.getString("min_distanza_planimetrica"));
		areaDiSaggio.setnCepaie(rs.getString("nr_ceppaie"));
		areaDiSaggio.setnPianteMorte(rs.getString("nr_piante_morte"));
		areaDiSaggio.setParticellaForestale(rs.getString("denominazione_particella"));
		areaDiSaggio.setPascolamento(rs.getString("flg_pascolamento"));
		areaDiSaggio.setQuota(rs.getString("quota"));
		areaDiSaggio.setRaggioArea(rs.getString("raggio_mt"));
		areaDiSaggio.setRinnovazione(rs.getString("rinnovazione"));
		areaDiSaggio.setSpeciePrevalenteRinnovazione(rs.getString("rin_specie_prevalente"));
		areaDiSaggio.setSpeciePrevalenteRinnovDescr(rs.getString("rin_specie_descr"));
		areaDiSaggio.setCombustibilePrincipale(rs.getByte("combust_p"));
		areaDiSaggio.setUtmEst(rs.getBigDecimal("st_x"));
		areaDiSaggio.setUtmNord(rs.getBigDecimal("st_y"));
		areaDiSaggio.setTipoStrutturale(rs.getInt("fk_tipo_strutturale_princ"));
		areaDiSaggio.setDescrTipoStrutturale(rs.getString("descTipoStructPrinc"));
		areaDiSaggio.setNote(rs.getString("note"));
		if (rs.getByte("flg_principale") == 0) {
			areaDiSaggio.setCombustibileSecondario((byte) 0);
		} else {
			areaDiSaggio.setCombustibilePrincipale((byte) 1);
		}
		areaDiSaggio.setProprieta(rs.getString("descr_proprieta"));
		areaDiSaggio.setTipoForestale(rs.getString("tipo"));
		areaDiSaggio.setStadioDiSviluppo(rs.getString("cod_stadio_sviluppo"));
		areaDiSaggio.setPriorita(rs.getString("descr_priorita"));
		areaDiSaggio.setClasseDiFertilita(rs.getString("descr_classe_fertilita"));
		areaDiSaggio.setDannoPrevalente(rs.getString("descr_danno"));
		areaDiSaggio.setFattoreNumerazione(rs.getLong("fattore_numerazione"));
		
		areaDiSaggio.setStatoScheda(new StatoAdsMapper().mapRow(rs, -1));
				
		return areaDiSaggio;

	}
}
