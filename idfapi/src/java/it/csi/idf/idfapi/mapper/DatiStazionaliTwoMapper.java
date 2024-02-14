/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import it.csi.idf.idfapi.dto.AreaDISaggioDataStazionaliTwo;
import it.csi.idf.idfapi.util.DBUtil;

public class DatiStazionaliTwoMapper implements RowMapper<AreaDISaggioDataStazionaliTwo> {

	@Override
	public AreaDISaggioDataStazionaliTwo mapRow(ResultSet rs, int arg1) throws SQLException {

		AreaDISaggioDataStazionaliTwo areaDiSaggio = new AreaDISaggioDataStazionaliTwo();

		areaDiSaggio.setCodiceADS(rs.getString("codice_ads"));
		areaDiSaggio.setDestinazione(rs.getInt("fk_destinazione"));
		areaDiSaggio.setTipoIntervento(rs.getInt("fk_tipo_intervento"));
		areaDiSaggio.setTipoIntervento(rs.getInt("fk_tipo_intervento"));
		areaDiSaggio.setPriorita(rs.getInt("fk_priorita"));
		areaDiSaggio.setDanno(rs.getInt("fk_danno"));
		
		areaDiSaggio.setCodEsbosco(rs.getString("cod_esbosco"));
		areaDiSaggio.setDistEsboscoFuoriPista(DBUtil.getIntegerValueFromResultSet(rs, "dist_esbosco_fuori_pista"));
		areaDiSaggio.setDistEsboscoSuPista(DBUtil.getIntegerValueFromResultSet(rs, "dist_esbosco_su_pista"));		
		areaDiSaggio.setMinDistanzaPlanimetrica(DBUtil.getIntegerValueFromResultSet(rs, "min_distanza_planimetrica"));
		areaDiSaggio.setDanniPerc(DBUtil.getIntegerValueFromResultSet(rs, "danni_perc"));
		areaDiSaggio.setNrPianteMorte(DBUtil.getIntegerValueFromResultSet(rs, "nr_piante_morte"));
		areaDiSaggio.setPercDefogliazione(DBUtil.getIntegerValueFromResultSet(rs, "perc_defogliazione"));
		areaDiSaggio.setPercIngiallimento(DBUtil.getIntegerValueFromResultSet(rs, "perc_ingiallimento"));
		areaDiSaggio.setFlgPascolamento(rs.getByte("flg_pascolamento"));
		areaDiSaggio.setNote(rs.getString("note"));
		
		return areaDiSaggio;
	}

}
