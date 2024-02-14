/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import org.springframework.transaction.annotation.Transactional;

import it.csi.idf.idfapi.dto.PlainAdsrelSpecie;
import it.csi.idf.idfapi.dto.PlainRelascopicaSemplice;

@Transactional(rollbackFor=Exception.class)
public interface WrapperRelascopicaDAO {
	void insertRelascopicaSemplice(PlainRelascopicaSemplice plainRelascopicaSemplice);
	void insertRelascopicaCompleta(PlainAdsrelSpecie plainAdsrelSpecie, String codiceADS, Integer idSpecie);
	PlainRelascopicaSemplice getPlainRelascopicaByCodiceAds(String codiceAds);
	void consolidaRelascopicaSemplice(PlainRelascopicaSemplice plainRelascopicaSemplice);
}
