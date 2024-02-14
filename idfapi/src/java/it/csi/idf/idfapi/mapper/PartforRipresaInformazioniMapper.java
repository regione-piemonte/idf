/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import it.csi.idf.idfapi.dto.PartforRipresaInformazioni;
import it.csi.idf.idfapi.util.DBUtil;

public class PartforRipresaInformazioniMapper implements RowMapper<PartforRipresaInformazioni> {

	@Override
	public PartforRipresaInformazioni mapRow(ResultSet rs, int arg1) throws SQLException {
		PartforRipresaInformazioni partforRipresaInformazioni = new PartforRipresaInformazioni();

		
		partforRipresaInformazioni.setCodParticcella(DBUtil.getIntegerValueFromResultSet(rs, "cod_particella_for"));
		partforRipresaInformazioni.setRipresaIntervento(DBUtil.getIntegerValueFromResultSet(rs, "ripresa_intervento"));
		partforRipresaInformazioni.setRipresaRealeFineInt(DBUtil.getIntegerValueFromResultSet(rs, "ripresa_attuale"));
		//partforRipresaInformazioni.setRipresaResidua(DBUtil.getIntegerValueFromResultSet(rs, "ripresa_residua"));

		return partforRipresaInformazioni;
	}

}
