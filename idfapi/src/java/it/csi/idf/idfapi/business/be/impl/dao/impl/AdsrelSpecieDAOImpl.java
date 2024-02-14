/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.idf.idfapi.business.be.impl.dao.AdsrelSpecieDAO;
import it.csi.idf.idfapi.dto.PlainAdsrelSpecie;
import it.csi.idf.idfapi.dto.RAdsrelSpecie;
import it.csi.idf.idfapi.mapper.AdsrelSpecieMapper;
import it.csi.idf.idfapi.util.DBUtil;
@Component
public class AdsrelSpecieDAOImpl implements AdsrelSpecieDAO {
	public static final Calendar tzUTC = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
	
	String [] columnNamesDomCam = {"cod_tipo_campione", "id_specie", "qualita", "diametro", "altezza", "incremento", "eta", "note", "data_inizio_validita", "idgeo_pt_ads"};
	String [] columnNamesCav = {"cod_tipo_campione", "id_specie", "diametro", "data_inizio_validita", "idgeo_pt_ads", "flg_pollone_seme" };
	
	String tableName = "idf_r_adsrel_specie";
	String pkColumnName = "idgeo_pt_ads";
	
	
	@Override
	public List<RAdsrelSpecie> search() {
		String SQL = "SELECT * FROM idf_r_adsrel_specie";
		return DBUtil.jdbcTemplate.query(SQL, new AdsrelSpecieMapper());	
	}
	@Override
	public List<RAdsrelSpecie> searchByIdgeoPtAds(Long idgeoPtAds) {
		String SQL = "SELECT * FROM idf_r_adsrel_specie where idgeo_pt_ads = ? and data_fine_validita is null and cod_tipo_campione<>'CAV'";
		return DBUtil.jdbcTemplate.query(SQL, new AdsrelSpecieMapper(), idgeoPtAds);	
	}
	
	public int deleteByCodiceAdsNotCAV(Long idgeoPtAds) {
		String SQL = "DELETE FROM idf_r_adsrel_specie where idgeo_pt_ads = ? and data_fine_validita is null and cod_tipo_campione<>'CAV'";		
		return DBUtil.jdbcTemplate.update(SQL, idgeoPtAds);	
	}
	
	public int deleteByCodiceAdsCAV(Long idgeoPtAds) {
		String SQL = "DELETE FROM idf_r_adsrel_specie where idgeo_pt_ads = ? and data_fine_validita is null and cod_tipo_campione='CAV'";		
		return DBUtil.jdbcTemplate.update(SQL, idgeoPtAds);	
	}
		
	@Override
	public List<RAdsrelSpecie> searchAdsRelSpecByCodiceADSForCAV(String codiceADS) {
		String SQL = "SELECT * FROM idf_r_adsrel_specie where idgeo_pt_ads = ? and data_fine_validita is null and cod_tipo_campione='CAV'";
		return DBUtil.jdbcTemplate.query(SQL, new AdsrelSpecieMapper(), Long.parseLong(codiceADS));	
	}

	@Override
	public int saveAdsrelSpecieDOMeCAM(RAdsrelSpecie radsrelSpecie) {
	    try {
		return DBUtil.jdbcTemplate.update(DBUtil.createInsertQueryString(tableName, columnNamesDomCam), getParamsForAdsRel(radsrelSpecie, false).toArray());
	    }catch (Exception e) {
			// TODO: handle exception
	    	e.printStackTrace();
	    	throw e;
		}
	    
	}
	
	public int updateAdsrelSpecieDOMeCAM(RAdsrelSpecie radsrelSpecie) {
		try {

			return DBUtil.jdbcTemplate.update(DBUtil.createUpdateQueryString(tableName, columnNamesDomCam, pkColumnName), getParamsForAdsRel(radsrelSpecie, true).toArray());
		
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	    
	}
	
	public void setDataFineValidaForAdsSpecieDOMeCAM(Long idgeoPtAds) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE idf_r_adsrel_specie SET ")
		.append(" data_fine_validita = ? ")
		.append(" WHERE idgeo_pt_ads = ?");
		
		Object[] params = {new Timestamp(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()), idgeoPtAds};		
		DBUtil.jdbcTemplate.update(sql.toString(), params);
		
	}
	
	@Transactional
	@Override
	public void insertAllSpecieDomeCAM(List<RAdsrelSpecie> listOfRAdsrelSpecie) {
		if (listOfRAdsrelSpecie.size()> 0) {
			setDataFineValidaForAdsSpecieDOMeCAM(listOfRAdsrelSpecie.get(0).getIdgeoPtAds());
		}
		for (Iterator<RAdsrelSpecie> iterator = listOfRAdsrelSpecie.iterator(); iterator.hasNext();) {
			RAdsrelSpecie rAdsrelSpecie = (RAdsrelSpecie) iterator.next();
			saveAdsrelSpecieDOMeCAM(rAdsrelSpecie);
		}
		
	}
	
	@Transactional
	@Override
	public void updateAllSpecieDomeCAM(List<RAdsrelSpecie> listOfRAdsrelSpecie) {

		for (Iterator<RAdsrelSpecie> iterator = listOfRAdsrelSpecie.iterator(); iterator.hasNext();) {
			RAdsrelSpecie rAdsrelSpecie = (RAdsrelSpecie) iterator.next();
			updateAdsrelSpecieDOMeCAM(rAdsrelSpecie);
		}
		
	}


	private List<Object> getParamsForAdsRel(RAdsrelSpecie radsrelSpecie, boolean updateQuery) {
		
		List<Object> parameters = new ArrayList<>();
		parameters.add(radsrelSpecie.getCodTipoCampione());
		parameters.add(radsrelSpecie.getIdSpecie());
        parameters.add(radsrelSpecie.getQualita());
        parameters.add(radsrelSpecie.getDiametro());
        parameters.add(radsrelSpecie.getAltezza());
        parameters.add(radsrelSpecie.getIncremento());
        parameters.add(radsrelSpecie.getEta());
        parameters.add(radsrelSpecie.getNote());
        parameters.add(new Timestamp(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()));
        parameters.add(radsrelSpecie.getIdgeoPtAds());
        
        if (updateQuery) { parameters.add(radsrelSpecie.getIdgeoPtAds());} // added for where clause
		
        return parameters;
	}
	@Override
	public int saveAdsrelSpecieCAV(RAdsrelSpecie radsrelSpecie) {
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO idf_r_adsrel_specie (");
		sql.append(" cod_tipo_campione, id_specie, diametro, incremento, ");
		sql.append(" data_inizio_validita, idgeo_pt_ads, flg_pollone_seme,num_tavola_coeff_cub) "); // 
		sql.append("VALUES(?,?,?,?,?,?,?,0)");
	
		
		List<Object> parameters = new ArrayList<>();
		parameters.add(radsrelSpecie.getCodTipoCampione());
		parameters.add(radsrelSpecie.getIdSpecie());
        parameters.add(radsrelSpecie.getDiametro());
        parameters.add(radsrelSpecie.getIncremento()!=null ? radsrelSpecie.getIncremento() : 0 );
        parameters.add(new Timestamp(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()));
        parameters.add(radsrelSpecie.getIdgeoPtAds());
        parameters.add(radsrelSpecie.getFlgPolloneSeme());

        return DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}

	@Override
	public int saveAdsrelSpecieForRelascopica(RAdsrelSpecie plainAdsrelSpecie, Long codiceAds) {
		if(plainAdsrelSpecie.getIdSpecie() != null) {
			StringBuilder sql=new StringBuilder();
			sql.append("INSERT INTO idf_r_adsrel_specie (");
			sql.append(" idgeo_pt_ads, id_specie, nr_alberi_seme,");
			sql.append(" nr_alberi_pollone, note, data_inizio_validita, cod_tipo_campione, diametro, flg_pollone_seme, ");
			sql.append("altezza, incremento,num_tavola_coeff_cub)");
			sql.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,0)");
			
			List<Object> parameters = new ArrayList<>();
			parameters.add(codiceAds);
			parameters.add(plainAdsrelSpecie.getIdSpecie());
	        parameters.add(plainAdsrelSpecie.getNrAlberiSeme());
	        parameters.add(plainAdsrelSpecie.getNrAlberiPollone());
	        parameters.add(plainAdsrelSpecie.getNote());
	        parameters.add(new Timestamp(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()));
	        parameters.add("CAV");//Mocked
	        parameters.add(plainAdsrelSpecie.getDiametro());
	        parameters.add(plainAdsrelSpecie.getFlgPolloneSeme());
	        parameters.add(plainAdsrelSpecie.getAltezza());
	        parameters.add(plainAdsrelSpecie.getIncremento());
	
	        return DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
		}
		return 0;
	}

	@Override
	public int saveAdsrelSpecieAlberi(PlainAdsrelSpecie plainAdsrelSpecie, String codiceAds, Integer idSpecie) {
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE idf_r_adsrel_specie SET");
		sql.append(" diametro = ?, flg_pollone_seme = ?, altezza = ?, incremento = ?");
		sql.append(" where idgeo_pt_ads = ? AND id_specie =?");
	
	
		List<Object> parameters = new ArrayList<>();
	
        parameters.add(plainAdsrelSpecie.getDiametro());
        parameters.add(plainAdsrelSpecie.getFlgPolloneSeme());
        parameters.add(plainAdsrelSpecie.getAltezza());
        parameters.add(plainAdsrelSpecie.getIncremento());
        parameters.add(Long.parseLong(codiceAds));
        parameters.add(idSpecie);

        return DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}

	// TODO: Refactor and make Generic DAO solution
	public void deleteAdsRelSpecie(Long idgeoPtAds) {
		String sql = String.format("DELETE FROM %s WHERE %s = ?", tableName, pkColumnName);
		DBUtil.jdbcTemplate.update(sql, idgeoPtAds);
	}
	
}
