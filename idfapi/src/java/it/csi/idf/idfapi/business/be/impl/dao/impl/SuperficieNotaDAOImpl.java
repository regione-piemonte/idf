/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.SuperficieNotaDAO;
import it.csi.idf.idfapi.dto.AdsDatiStazionaliOne;
import it.csi.idf.idfapi.dto.AdsDatiStazionaliTwo;
import it.csi.idf.idfapi.dto.AreaDiSaggio;
import it.csi.idf.idfapi.dto.Superficie;
import it.csi.idf.idfapi.mapper.SuperficieMapper;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.ISuperficieNotaDefaults;
import it.csi.idf.idfapi.util.StringUtils;

@Component
public class SuperficieNotaDAOImpl implements SuperficieNotaDAO {

	
	String [] datiSuperficieOneColumns = { "idgeo_pt_ads","densita_campionamento", "raggio_mt", "fk_tipo_strutturale_princ", "cod_stadio_sviluppo",
				"nr_ceppaie", "rinnovazione", "rin_specie_prevalente", "perc_copertura_chiome", "fk_tipo_strutturale_second", 
				"combust_p","flg_pascolamento", "cod_esbosco", "fk_classe_fertilita" };
	
	String [] datiSuperficieTwoColumns = {"cod_esbosco" , "dist_esbosco_fuori_pista" , "dist_esbosco_su_pista" , 
			"min_distanza_planimetrica" , "danni_perc" , "nr_piante_morte" , "perc_defogliazione" , 
			"perc_ingiallimento" , "flg_pascolamento","note"};
	
	String tableName = "idf_t_ads_superficie_nota";
	String pkColumnName = "idgeo_pt_ads";
	
	// NOT NULL  fields: "idgeo_pt_ads", "cod_stadio_sviluppo", "cod_esbosco", "nr_ceppaie", "flg_pascolamento", "fk_tipo_strutturale_princ", "fk_tipo_strutturale_second", "combust_p", "fk_classe_fertilita"
	@Override
	public void saveSuperficie(AdsDatiStazionaliOne adsDatiStazionaliOne) {
		if (!superficiaNotaExists(adsDatiStazionaliOne.getIdgeoPtAds())) {
			insertSuperficieDatiStazionaliOne(adsDatiStazionaliOne);
		} else {
			updateSuperficieDatiStazionaliOne(adsDatiStazionaliOne);
		}
	}
	
	@Override
	public int insertSuperficieDatiStazionaliOne(AdsDatiStazionaliOne adsDatiStazionaliOne) {
		try {
		return DBUtil.jdbcTemplate.update(DBUtil.createInsertQueryString(tableName, datiSuperficieOneColumns), getParamsForDatiStazionaliOne(adsDatiStazionaliOne, false).toArray());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public void updateSuperficieDatiStazionaliOne(AdsDatiStazionaliOne adsDatiStazionaliOne) {				
		DBUtil.jdbcTemplate.update(DBUtil.createUpdateQueryString(tableName, datiSuperficieOneColumns, pkColumnName), getParamsForDatiStazionaliOne(adsDatiStazionaliOne, true).toArray());
	}
	
	@Override
	public int updateSuperficieDatiStazionaliTwo(AdsDatiStazionaliTwo adsDatiStazionaliTwo) {
		String sql = DBUtil.createUpdateQueryString(tableName, datiSuperficieTwoColumns, pkColumnName);
		Object[]  params = getParamsForDatiStazionaliTwo(adsDatiStazionaliTwo, true).toArray();
        return DBUtil.jdbcTemplate.update(sql, params);
	}
	
	public boolean superficiaNotaExists(Long idgeoPtAds) {
		String countSQL = DBUtil.createCountByPkQuery(tableName, pkColumnName);		
		return DBUtil.jdbcTemplate.queryForInt(countSQL, idgeoPtAds) > 0;
	}

	private List<Object> getParamsForDatiStazionaliOne(AdsDatiStazionaliOne adsDatiStazionaliOne, boolean updateQuery) {
		
		List<Object> parameters = new ArrayList<>();
		parameters.add(adsDatiStazionaliOne.getIdgeoPtAds());
        parameters.add(adsDatiStazionaliOne.getDensitaCamp());
        parameters.add(adsDatiStazionaliOne.getRaggioArea());
        parameters.add(adsDatiStazionaliOne.getTipoStrutturale()!= null ? adsDatiStazionaliOne.getTipoStrutturale() : ISuperficieNotaDefaults.FK_TIPO_STRUTTURALE_PRINC_DEFAULT );
        parameters.add(StringUtils.defaultValue(adsDatiStazionaliOne.getStadioDiSviluppo(), ISuperficieNotaDefaults.COD_STADIO_SVILUPPO_DEFAULT));
        parameters.add(adsDatiStazionaliOne.getnCepaie()!= null ? adsDatiStazionaliOne.getnCepaie(): ISuperficieNotaDefaults.NR_CEPPAIE_DEFAULT);
        parameters.add(adsDatiStazionaliOne.getRinnovazione());
        parameters.add(adsDatiStazionaliOne.getSpeciePrevalenteRinnovazione());
        parameters.add(adsDatiStazionaliOne.getCoperturaChiome());
        parameters.add(ISuperficieNotaDefaults.FK_TIPO_STRUTTURALE_SECOND_DEFAULT);//mocked not null
        parameters.add(ISuperficieNotaDefaults.COMBUST_P_DEFAULT);//mocked not null
        parameters.add(ISuperficieNotaDefaults.FLG_PASCOLAMENTO_DEFAULT);//mocked not null
        parameters.add(ISuperficieNotaDefaults.COD_ESBOSCO_DEFAULT);//mocked not null
        parameters.add(adsDatiStazionaliOne.getIdClasseDiFertilita()!=null ? adsDatiStazionaliOne.getIdClasseDiFertilita(): ISuperficieNotaDefaults.FK_CLASSE_FERTILITA_DEFAULT );
        
        if (updateQuery) {
        	parameters.add(adsDatiStazionaliOne.getIdgeoPtAds());
        }
        
        return parameters;
	}
	
	private List<Object> getParamsForDatiStazionaliTwo(AdsDatiStazionaliTwo adsDatiStazionaliTwo, boolean updateQuery) {

		List<Object> parameters = new ArrayList<>();
        parameters.add(adsDatiStazionaliTwo.getEsbosco()!=null ? adsDatiStazionaliTwo.getEsbosco() : ISuperficieNotaDefaults.COD_ESBOSCO_DEFAULT);
        parameters.add(adsDatiStazionaliTwo.getDefp());
        parameters.add(adsDatiStazionaliTwo.getDesp());
        parameters.add(adsDatiStazionaliTwo.getMdp());
        parameters.add(adsDatiStazionaliTwo.getIntesitaDanni());
        parameters.add(adsDatiStazionaliTwo.getnPianteMorte());
        parameters.add(adsDatiStazionaliTwo.getDefogliazione());
        parameters.add(adsDatiStazionaliTwo.getIngiallimento());
        parameters.add(adsDatiStazionaliTwo.getPascolamento()!=null ? adsDatiStazionaliTwo.getPascolamento() : ISuperficieNotaDefaults.FLG_PASCOLAMENTO_DEFAULT);
        parameters.add(adsDatiStazionaliTwo.getNote());
//        parameters.add(Integer.parseInt(areaDiSaggio.getCodiceADS()));
        
        if (updateQuery) {
        	parameters.add(adsDatiStazionaliTwo.getIdgeoPtAds());
        }
        
        return parameters;
		
	}
	
	public Superficie getSuperficieNotaByIdGeoPtAds(Long idGeoPtAds) {
		return DBUtil.jdbcTemplate.queryForObject("SELECT * FROM " + tableName + " WHERE idgeo_pt_ads=" + idGeoPtAds, new SuperficieMapper());
	}
	
	public void deleteSuperficieNota(Long idgeoPtAds) {
		String sql = String.format("DELETE FROM %s WHERE %s = ?", tableName, pkColumnName);
		DBUtil.jdbcTemplate.update(sql, idgeoPtAds);
	}
}
