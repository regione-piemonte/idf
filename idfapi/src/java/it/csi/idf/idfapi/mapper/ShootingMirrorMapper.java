/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.ShootingMirrorDTO;
import it.csi.idf.idfapi.util.DBUtil;

public class ShootingMirrorMapper implements RowMapper<ShootingMirrorDTO> {

	/*
	@Override
	public ShootingMirrorDTO mapRow(ResultSet rs, int arg1) throws SQLException {
		
		ShootingMirrorDTO shootingMirror = new ShootingMirrorDTO();
		
		shootingMirror.setIdgeoPlParticellaForest(rs.getInt("idgeo_pl_particella_forest"));
		shootingMirror.setIdgeoPlPfa(rs.getObject("idgeo_pl_pfa")==null ? null : rs.getInt("idgeo_pl_pfa"));
		shootingMirror.setEttari(rs.getBigDecimal("ettari")== null ? null : rs.getBigDecimal("ettari").setScale(2, RoundingMode.FLOOR));
		shootingMirror.setSupTagliataHa(rs.getBigDecimal("sup_tagliata_ha")== null ? null : rs.getBigDecimal("sup_tagliata_ha").setScale(2, RoundingMode.FLOOR));
		shootingMirror.setCodParticellaFor(rs.getObject("cod_particella_for")==null ? null :rs.getInt("cod_particella_for"));
		shootingMirror.setProvvigioneHa(rs.getBigDecimal("provvigione_ha")== null ? null : rs.getBigDecimal("provvigione_ha").setScale(2, RoundingMode.FLOOR));
		shootingMirror.setProvvigioneReale(rs.getBigDecimal("provvigione_reale")== null ? null : rs.getBigDecimal("provvigione_reale").setScale(2, RoundingMode.FLOOR));
		shootingMirror.setPercTassoRipresaPot(rs.getInt("perc_tasso_ripresa_pot"));
		shootingMirror.setPercTara(rs.getInt("perc_tara"));
		shootingMirror.setRipresaTotHa(rs.getBigDecimal("ripresa_tot_ha")== null ? null : rs.getBigDecimal("ripresa_tot_ha").setScale(2, RoundingMode.FLOOR));
		shootingMirror.setRipresaIntervento(rs.getBigDecimal("ripresa_intervento")== null ? null : rs.getBigDecimal("ripresa_intervento").setScale(2, RoundingMode.FLOOR));
		shootingMirror.setRipresaAttuale(rs.getBigDecimal("ripresa_attuale")== null ? null : rs.getBigDecimal("ripresa_attuale").setScale(2, RoundingMode.FLOOR));
		shootingMirror.setRipresaResidua(rs.getBigDecimal("ripresa_residua")== null ? null : rs.getBigDecimal("ripresa_residua").setScale(2, RoundingMode.FLOOR));
		
		return shootingMirror;
	}
	*/
	@Override
	public ShootingMirrorDTO mapRow(ResultSet rs, int arg1) throws SQLException {
		
		ShootingMirrorDTO shootingMirror = new ShootingMirrorDTO();
		
		shootingMirror.setIdgeoPlParticellaForest(rs.getInt("idgeo_pl_particella_forest"));
		shootingMirror.setCodParticellaForest(rs.getString("cod_particella_for"));
		shootingMirror.setIdgeoPlPfa(DBUtil.getIntegerValueFromResultSet(rs, "idgeo_pl_pfa"));
		shootingMirror.setEttari(rs.getBigDecimal("ettari")== null ? null : rs.getBigDecimal("ettari").setScale(2, RoundingMode.FLOOR));
		shootingMirror.setSupTagliataHa(rs.getBigDecimal("sup_tagliata_ha")== null ? null : rs.getBigDecimal("sup_tagliata_ha").setScale(2, RoundingMode.FLOOR));
		shootingMirror.setAlreadyCut(rs.getBigDecimal("already_cut")== null ? new BigDecimal(0) : rs.getBigDecimal("already_cut"));		
		shootingMirror.setNotAlreadyCut(rs.getBigDecimal("not_already_cut") == null? new BigDecimal(0): rs.getBigDecimal("not_already_cut"));		
		shootingMirror.setProvvigioneHa(rs.getBigDecimal("provvigione_ha")== null ? null : rs.getBigDecimal("provvigione_ha").setScale(2, RoundingMode.FLOOR));
		shootingMirror.setProvvigioneReale(rs.getBigDecimal("provvigione_reale")== null ? null : rs.getBigDecimal("provvigione_reale").setScale(2, RoundingMode.FLOOR));
		shootingMirror.setPercTassoRipresaPot(rs.getBigDecimal("perc_tasso_ripresa_pot")== null ? null : rs.getBigDecimal("perc_tasso_ripresa_pot"));
		shootingMirror.setPercTara(rs.getBigDecimal("perc_tara")== null ? null : rs.getBigDecimal("perc_tara"));		
		shootingMirror.setRipresaTotHa(rs.getBigDecimal("ripresa_tot_ha")== null ? null : rs.getBigDecimal("ripresa_tot_ha"));
		shootingMirror.setRipresaIntervento(rs.getBigDecimal("ripresa_intervento")== null ? null : rs.getBigDecimal("ripresa_intervento").setScale(2, RoundingMode.FLOOR));
		shootingMirror.setRipresaAttuale(calcRipresaAttuale(shootingMirror));
		shootingMirror.setRipresaResidua(calcRipresaResidua(shootingMirror));
		
		return shootingMirror;
	}
	
	
	public BigDecimal calcRipresaTotHa(ShootingMirrorDTO shootingMirror) {
		if(shootingMirror.getProvvigioneReale()==null || shootingMirror.getPercTassoRipresaPot()==null|| shootingMirror.getPercTara()==null) {
			return null;
		}
		BigDecimal x = (shootingMirror.getPercTassoRipresaPot().subtract(shootingMirror.getPercTara())).divide(BigDecimal.valueOf(100.0),3,RoundingMode.FLOOR);
		BigDecimal ripresaTotHa = shootingMirror.getProvvigioneReale().multiply(x);
		return ripresaTotHa;
	}
	
	public BigDecimal calcRipresaAttuale(ShootingMirrorDTO shootingMirror) {
		if(shootingMirror.getRipresaTotHa()==null || shootingMirror.getAlreadyCut()==null) {
			return null;
		}
		BigDecimal ripresaAttuale = shootingMirror.getRipresaTotHa().subtract(shootingMirror.getAlreadyCut());
		return ripresaAttuale;
	}
	
//	public BigDecimal calcRipresaResidua(ShootingMirrorDTO shootingMirror) {
//		if(shootingMirror.getRipresaAttuale()==null || shootingMirror.getRipresaIntervento()==null) {
//			return null;
//		}
//		BigDecimal ripresaResidua = shootingMirror.getRipresaAttuale().subtract(shootingMirror.getRipresaIntervento());		
//				return ripresaResidua;
//	}
	
	public BigDecimal calcRipresaResidua(ShootingMirrorDTO shootingMirror) {
		if(shootingMirror.getRipresaAttuale()==null || shootingMirror.getNotAlreadyCut()==null) {
			return null;
		}
		BigDecimal ripresaResidua = shootingMirror.getRipresaAttuale().subtract(shootingMirror.getNotAlreadyCut());		
				return ripresaResidua;
	}
	
	
	

}
