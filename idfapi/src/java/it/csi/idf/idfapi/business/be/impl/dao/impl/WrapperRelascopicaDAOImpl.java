/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.idf.idfapi.business.be.impl.dao.AdsrelSpecieDAO;
import it.csi.idf.idfapi.business.be.impl.dao.GeoPtAreaDiSaggioDAO;
import it.csi.idf.idfapi.business.be.impl.dao.RelascopicaSempliceDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperRelascopicaDAO;
import it.csi.idf.idfapi.dto.PlainAdsrelSpecie;
import it.csi.idf.idfapi.dto.PlainRelascopicaSemplice;
import it.csi.idf.idfapi.dto.RAdsrelSpecie;

@Component
public class WrapperRelascopicaDAOImpl implements WrapperRelascopicaDAO {

	@Autowired
	private RelascopicaSempliceDAO relascopicaSempliceDAO;

	@Autowired
	private AdsrelSpecieDAO adsrelSpecieDAO;
	
	@Autowired
	private GeoPtAreaDiSaggioDAO geoPtAreaDiSaggioDAO;

	@Transactional
	@Override
	public void insertRelascopicaSemplice(PlainRelascopicaSemplice plainRelascopicaSemplice) {
		if(plainRelascopicaSemplice.getFkTipoStrutturalePrinc() == null) {
			plainRelascopicaSemplice.setFkTipoStrutturalePrinc(0);
		}
		if (!relascopicaSempliceDAO.relascopicaExists(plainRelascopicaSemplice.getIdgeoPtAds())) {
			relascopicaSempliceDAO.saveRelascopicaSemplice(plainRelascopicaSemplice);
		} else {
			relascopicaSempliceDAO.updateRelascopica(plainRelascopicaSemplice);
		}
		adsrelSpecieDAO.deleteByCodiceAdsCAV(plainRelascopicaSemplice.getIdgeoPtAds());
		for (RAdsrelSpecie plainAdsrelSpecie : plainRelascopicaSemplice.getPlainAdsrelSpecie()) {
			adsrelSpecieDAO.saveAdsrelSpecieForRelascopica(plainAdsrelSpecie, plainRelascopicaSemplice.getIdgeoPtAds());
		}
	}
	
	@Transactional
	@Override
	public void consolidaRelascopicaSemplice(PlainRelascopicaSemplice plainRelascopicaSemplice) {
		if(plainRelascopicaSemplice.getFkTipoStrutturalePrinc() == null) {
			plainRelascopicaSemplice.setFkTipoStrutturalePrinc(0);
		}
		if (!relascopicaSempliceDAO.relascopicaExists(plainRelascopicaSemplice.getIdgeoPtAds())) {
			relascopicaSempliceDAO.saveRelascopicaSemplice(plainRelascopicaSemplice);
		} else {
			relascopicaSempliceDAO.updateRelascopica(plainRelascopicaSemplice);
		}
		adsrelSpecieDAO.deleteByCodiceAdsCAV(plainRelascopicaSemplice.getIdgeoPtAds());
		for (RAdsrelSpecie plainAdsrelSpecie : plainRelascopicaSemplice.getPlainAdsrelSpecie()) {
			adsrelSpecieDAO.saveAdsrelSpecieForRelascopica(plainAdsrelSpecie, plainRelascopicaSemplice.getIdgeoPtAds());
		}
		
		geoPtAreaDiSaggioDAO.consolidaAreaDiSaggio(plainRelascopicaSemplice.getIdgeoPtAds());
		
	}
	

	@Override
	public void insertRelascopicaCompleta(PlainAdsrelSpecie plainAdsrelSpecie, String codiceAds, Integer idSpecie) {
		adsrelSpecieDAO.saveAdsrelSpecieAlberi(plainAdsrelSpecie, codiceAds, idSpecie);

	}

	public PlainRelascopicaSemplice getPlainRelascopicaByCodiceAds(String codiceAds) {
		PlainRelascopicaSemplice relSempl = new PlainRelascopicaSemplice();
		if (relascopicaSempliceDAO.relascopicaExists(Long.parseLong(codiceAds))) {
			relSempl = relascopicaSempliceDAO.getPlainRelascopicaSemplice(codiceAds);
			relSempl.setPlainAdsrelSpecie(adsrelSpecieDAO.searchAdsRelSpecByCodiceADSForCAV(codiceAds));
		}

		return relSempl;
	}

}
