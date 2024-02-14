/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.TipoForestale;

public class TipoForestaleMapper implements RowMapper<TipoForestale>{

	@Override
	public TipoForestale mapRow(ResultSet rs, int arg1) throws SQLException {
		TipoForestale tipoForestale = new TipoForestale();
		tipoForestale.setIdTipoForestale(rs.getInt("id_tipo_forestale"));
		tipoForestale.setFkCategoriaForestale(rs.getInt("fk_categoria_forestale"));
		tipoForestale.setCodiceAmministrativo(rs.getString("codice_amministrativo"));
		tipoForestale.setTipo(rs.getString("tipo"));
		tipoForestale.setSottotipo(rs.getString("sottotipo"));
		tipoForestale.setVariante(rs.getString("variante"));
		tipoForestale.setDataModifica(rs.getTimestamp("data_modifica"));
		tipoForestale.setDataFineValidita(rs.getTimestamp("data_fine_validita"));
		tipoForestale.setCodClc(rs.getString("cod_clc"));
		tipoForestale.setHabitatN2000(rs.getString("habitat_n2000"));
		tipoForestale.setFkConfigIpla(rs.getInt("fk_config_ipla"));
		
		return tipoForestale;
	}

}
