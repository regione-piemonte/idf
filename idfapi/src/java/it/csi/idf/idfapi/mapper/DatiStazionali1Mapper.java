/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.AreaDiSaggio;
import it.csi.idf.idfapi.util.DBUtil;

public class DatiStazionali1Mapper implements RowMapper<AreaDiSaggio> {
	@Override
	public AreaDiSaggio mapRow(ResultSet rs, int arg1) throws SQLException {
		AreaDiSaggio areaDiSaggio = new AreaDiSaggio();
		
		areaDiSaggio.setIdgeoPtAds(rs.getLong("idgeo_pt_ads"));
		areaDiSaggio.setCodiceADS(rs.getString("codice_ads"));
		areaDiSaggio.setDensitaCamp(rs.getString("densita_campionamento"));
		areaDiSaggio.setRaggioArea(rs.getString("raggio_mt"));
		areaDiSaggio.setTipoForestale(rs.getString("fk_tipo_forestale"));
		areaDiSaggio.setAssettoEvolutivoColturale(rs.getString("id_assetto"));
		areaDiSaggio.setTipoStrutturale(rs.getInt("fk_tipo_strutturale_princ"));
		areaDiSaggio.setStadioDiSviluppo(rs.getString("cod_stadio_sviluppo"));
		areaDiSaggio.setClasseDiFertilita(rs.getString("descr_classe_fertilita"));
		areaDiSaggio.setIdClasseDiFertilita(DBUtil.getIntegerValueFromResultSet(rs, "fk_classe_fertilita"));
		areaDiSaggio.setnCepaie(rs.getString("nr_ceppaie"));
		areaDiSaggio.setRinnovazione(rs.getString("rinnovazione"));
		areaDiSaggio.setSpeciePrevalenteRinnovazione(rs.getString("rin_specie_prevalente"));
		areaDiSaggio.setCoperturaChiome(rs.getString("perc_copertura_chiome"));
		areaDiSaggio.setLettiera(rs.getString("perc_copertura_lettiera"));
		areaDiSaggio.setCoperturaErbacea(rs.getString("perc_copertura_erbacea"));
		areaDiSaggio.setCoperturaCespugli(rs.getString("perc_copertura_cespugli"));
		areaDiSaggio.setCategoriaForestale(rs.getString("id_categoria_forestale"));

		return areaDiSaggio;

	}
}
