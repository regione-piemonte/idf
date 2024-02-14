/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import it.csi.idf.idfapi.dto.TagliSpecieXls;
import it.csi.idf.idfapi.dto.TrasformazCatastoXls;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TagliSpecieXlsMapper implements RowMapper<TagliSpecieXls>{
	
	@Override
	public TagliSpecieXls mapRow(ResultSet rs, int arg1) throws SQLException {
		TagliSpecieXls item = new TagliSpecieXls();
		item.setNrIstanzaForestale(rs.getInt("nr_istanza_forestale"));
		item.setLatino(rs.getString("latino"));
		item.setVolgare(rs.getString("volgare"));
		item.setFlgSpeciePriorita(rs.getString("flg_specie_priorita"));
		item.setNumeroPiante(rs.getInt("numero_piante"));
		item.setVolumeSpecie(rs.getBigDecimal("volume_specie"));
		item.setDescrUdm(rs.getString("descr_udm"));
		item.setLegnaOperaAutoconsumoPerc(rs.getBigDecimal("legna_opera_autoconsumo_perc"));
		item.setLegnaOperaCommercioPerc(rs.getBigDecimal("legna_opera_commercio_perc"));
		item.setLegnaArdereAutoconsumoPerc(rs.getBigDecimal("legna_ardere_autoconsumo_perc"));
		item.setLegnaArdereCommercioPerc(rs.getBigDecimal("legna_ardere_commercio_perc"));
		item.setLegnaUsoEnerAutoconsumoPerc(rs.getBigDecimal("legna_uso_ener_autoconsumo_perc"));
		item.setLegnaUsoEnerCommercioPerc(rs.getBigDecimal("legna_uso_ener_commercio_perc"));
		item.setNessunUtilizzoAutoconsumoPerc(rs.getBigDecimal("nessun_utilizzo_autoconsumo_perc"));
		item.setNessunUtilizzoCommercioPerc(rs.getBigDecimal("nessun_utilizzo_commercio_perc"));
		return item;
	}

}
