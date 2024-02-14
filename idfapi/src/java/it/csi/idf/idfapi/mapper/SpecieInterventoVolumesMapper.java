/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import it.csi.idf.idfapi.dto.SpecieInterventoVolumes;
import it.csi.idf.idfapi.util.DBUtil;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SpecieInterventoVolumesMapper implements RowMapper<SpecieInterventoVolumes>{

	@Override
	public SpecieInterventoVolumes mapRow(ResultSet rs, int arg1) throws SQLException {
		SpecieInterventoVolumes specieInterventoVolumes = new SpecieInterventoVolumes();
		
		specieInterventoVolumes.setIdSpecie(DBUtil.getIntegerValueFromResultSet(rs, "id_specie"));
		specieInterventoVolumes.setVolgare(rs.getString("volgare"));
		specieInterventoVolumes.setVolumeSpecie(rs.getFloat("volume_specie"));
		specieInterventoVolumes.setPriorita(rs.getString("flg_specie_priorita"));
		specieInterventoVolumes.setUdm(rs.getString("descr_udm"));
		specieInterventoVolumes.setNumPiante(rs.getInt("numero_piante"));
		specieInterventoVolumes.setDensita(rs.getFloat("densita"));
		specieInterventoVolumes.setFkUdm(rs.getInt("fk_udm"));



		return specieInterventoVolumes;
	}
	

}
