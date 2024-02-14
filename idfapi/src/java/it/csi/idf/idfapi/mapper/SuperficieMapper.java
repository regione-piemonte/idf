/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import it.csi.idf.idfapi.dto.Superficie;
import it.csi.idf.idfapi.util.DBUtil;

public class SuperficieMapper implements RowMapper<Superficie>{

	@Override
	public Superficie mapRow(ResultSet rs, int arg1) throws SQLException {
		
		Superficie superficie = new Superficie();
		
		superficie.setIdgeoPtAds(rs.getLong("idgeo_pt_ads"));
		superficie.setDensitaCampionamento(DBUtil.getDoubleValueFromResultSet(rs, "densita_campionamento"));
		superficie.setRaggioMt(DBUtil.getIntegerValueFromResultSet(rs, "raggio_mt"));
		superficie.setCodStadioSviluppo(rs.getString("cod_stadio_sviluppo"));
		superficie.setPercCoperturaChiome(DBUtil.getIntegerValueFromResultSet(rs, "perc_copertura_chiome"));
		superficie.setAttitudineProduttiva(rs.getString("attitudine_produttiva"));
		superficie.setCodEsbosco(rs.getString("cod_esbosco"));
		superficie.setDistEsboscoFuoriPista(DBUtil.getIntegerValueFromResultSet(rs, "dist_esbosco_fuori_pista"));
		superficie.setDistEsboscoSuPista(DBUtil.getIntegerValueFromResultSet(rs, "dist_esbosco_su_pista"));
		superficie.setMinDistanzaPlanimetrica(DBUtil.getIntegerValueFromResultSet(rs, "min_distanza_planimetrica"));
		superficie.setNrCeppaie(DBUtil.getIntegerValueFromResultSet(rs, "nr_ceppaie"));
		superficie.setNrPianteMorte(DBUtil.getIntegerValueFromResultSet(rs, "nr_piante_morte"));
		superficie.setRinnovazione(DBUtil.getIntegerValueFromResultSet(rs, "rinnovazione"));
		superficie.setRinSpeciePrevalente(rs.getString("rin_specie_prevalente"));
		superficie.setDanniPerc(DBUtil.getIntegerValueFromResultSet(rs, "danni_perc"));
		superficie.setFlgPascolamento(rs.getByte("flg_pascolamento"));
		superficie.setPercDefogliazione(DBUtil.getIntegerValueFromResultSet(rs, "perc_defogliazione"));
		superficie.setPercIngiallimento(DBUtil.getIntegerValueFromResultSet(rs, "perc_ingiallimento"));
		superficie.setFkTipoStrutturalePrinc(DBUtil.getIntegerValueFromResultSet(rs, "fk_tipo_strutturale_princ"));
		superficie.setFkTipoStrutturaleSecond(DBUtil.getIntegerValueFromResultSet(rs, "fk_tipo_strutturale_second"));
		superficie.setCombustP(rs.getByte("combust_p"));
		superficie.setNote(rs.getString("note"));
		superficie.setFkClasseFertilita(DBUtil.getIntegerValueFromResultSet(rs, "fk_classe_fertilita"));
		
		return superficie;
		
		
	}
}
