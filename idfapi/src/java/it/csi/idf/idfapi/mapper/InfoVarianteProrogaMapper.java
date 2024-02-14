/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.InfoVarianteProroga;

public class InfoVarianteProrogaMapper implements RowMapper<InfoVarianteProroga>{

		@Override
		public InfoVarianteProroga mapRow(ResultSet rs, int arg1) throws SQLException {
			
			InfoVarianteProroga item = new InfoVarianteProroga();
			item.setIdPadreProroga(rs.getInt("fk_intervento_padre_proroga"));
			item.setIdPadreVariante(rs.getInt("fk_intervento_padre_variante"));
			item.setNumProroghe(rs.getInt("proroghe"));
			item.setNumVarianti(rs.getInt("varianti"));
			return item;
		}
}
