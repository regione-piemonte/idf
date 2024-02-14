/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.mock;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.GeoPlParticellaForestDAO;
import it.csi.idf.idfapi.business.be.service.GEECO;
import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.dto.GeoPlParticellaForest;
import it.csi.idf.idfapi.dto.PfaLottoLocalizza;
import it.csi.idf.idfapi.dto.PlainParticelleCatastali;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class GEECOImpl implements GEECO {

	@Autowired
	GeoPlParticellaForestDAO geoPlParticellaForestDAO;

	@Override
	public BigDecimal retrieveSupInterventoForParticelleCatastali(PlainParticelleCatastali plainParticelleCatastali) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void editSupInterventoValues(Integer idIntervento, List<PlainParticelleCatastali> particelle,
			ConfigUtente loggedConfig) {

		StringBuilder sql = new StringBuilder();
		
		sql.append(
				"UPDATE idf_r_propcatasto_intervento SET sup_intervento_ha = ?, data_aggiornamento = ?, fk_config_utente = ? ");
		sql.append(" WHERE idgeo_pl_prop_catasto = ? AND id_intervento =? ");

		for (PlainParticelleCatastali plainParticelleCatastali : particelle) {
			List<Object> parameters = new ArrayList<>();

			// TODO mocked sup_intervento_ha to be 30% of sup_catastale
			parameters.add(plainParticelleCatastali.getSupCatastale().multiply(BigDecimal.valueOf(0.30)));
			parameters.add(java.sql.Date.valueOf(LocalDate.now()));
			parameters.add(loggedConfig.getIdConfigUtente());
			parameters.add(plainParticelleCatastali.getId());
			parameters.add(idIntervento);

			DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
		}

	}
	/*
	 * @Override public void insertSupInterventoValues(Integer idIntervento,
	 * List<PlainParticelleCatastali> particelle) {
	 * 
	 * StringBuilder sql = new StringBuilder();
	 * sql.append("UPDATE idf_r_propcatasto_intervento SET sup_intervento = ?");
	 * sql.append(" WHERE idgeo_pl_prop_catasto = ? and id_intervento = ?");
	 * 
	 * List<Object> parameters = new ArrayList<>();
	 * 
	 * for (PlainParticelleCatastali plainParticelleCatastali : particelle) { //MK
	 * mocked sup_intervento_ha to be 30% of sup_catastale
	 * parameters.add(plainParticelleCatastali.getSupCatastale().multiply(BigDecimal
	 * .valueOf(0.30))); parameters.add(plainParticelleCatastali.getId());
	 * parameters.add(idIntervento); DBUtil.jdbcTemplate.update(sql.toString(),
	 * parameters.toArray()); }
	 * 
	 * }
	 * 
	 */

	@Override
	public void insertSupInterventoValue(Integer idIntervento, PlainParticelleCatastali plainParticelleCatastali,
			ConfigUtente loggedConfig) {

		StringBuilder sql = new StringBuilder();
		sql.append(
				"INSERT INTO idf_r_propcatasto_intervento( idgeo_pl_prop_catasto, id_intervento , data_inserimento, sup_intervento_ha, fk_config_utente) ");
		sql.append("VALUES(?, ?, ?, ?, ?) ");

		List<Object> parameters = new ArrayList<>();

		parameters.add(plainParticelleCatastali.getId());
		parameters.add(idIntervento);
		parameters.add(java.sql.Date.valueOf(LocalDate.now()));
		// MK mocked sup_intervento_ha to be 30% of sup_catastale
		parameters.add(plainParticelleCatastali.getSupCatastale().multiply(BigDecimal.valueOf(0.30)));
		parameters.add(loggedConfig.getIdConfigUtente());

		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}

	@Override
	public List<GeoPlParticellaForest> determinaForestParticellePerGeometriaGML(PfaLottoLocalizza body,
			Integer idGeoPlPfa) {
		//TODO should depend on intervento geometry passed as a parameter in body
		List<GeoPlParticellaForest> forestParticelle = geoPlParticellaForestDAO
				.getForestParticlesByCadastralParticles(body.getParticelleCatastali(), idGeoPlPfa);

		return forestParticelle;
	}

}
