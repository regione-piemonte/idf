/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.AreaDiSaggio;
import it.csi.idf.idfapi.util.DBUtil;

public class AreaDiSaggioDTOMapper implements RowMapper<AreaDiSaggio> {
	private static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	public AreaDiSaggio mapRow(ResultSet rs, int arg1) throws SQLException {
		String[] arr={"Superficie nota", "Relascopica semplice", "Relascopica completa"};
        Random r=new Random();
        int randomNumber=r.nextInt(arr.length);
        
		AreaDiSaggio areaDiSaggio = new AreaDiSaggio();
		areaDiSaggio.setIdgeoPtAds(rs.getLong("idgeo_pt_ads"));
		areaDiSaggio.setCodiceADS(rs.getString("codice_ads"));
		areaDiSaggio.setComune(rs.getString("denominazione_comune"));
		areaDiSaggio.setTipologia(rs.getString("descr_tipo_ads"));
		areaDiSaggio.setTipoAds(rs.getInt("fk_tipo_ads"));
		
		areaDiSaggio.setDescrTipoAds(rs.getString("descr_tipo_ads"));
		areaDiSaggio.setCategoriaForestale(rs.getString("descrizione"));
		areaDiSaggio.setDataRilevamento(formatData(rs.getDate("data_ril")));
		areaDiSaggio.setAmbitoDiRilevamento(rs.getString("descr_ambito_parent"));
		areaDiSaggio.setIdDettaglioAmbito(DBUtil.getIntegerValueFromResultSet(rs, "fk_ambito"));
		areaDiSaggio.setDettaglioAmbito(rs.getString("descr_ambito"));	
		areaDiSaggio.setSoggettoPf(rs.getString("codice_fiscale"));	
		
		areaDiSaggio.setStatoScheda(new StatoAdsMapper().mapRow(rs, -1));
	
		return areaDiSaggio;
	}
	private String formatData(java.sql.Date data) {
		if(data != null) {
			return df.format(new Date(data.getTime()));
		}
		return null;
	}
}
