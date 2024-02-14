/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.dto.PlainAdsrelSpecie;
import it.csi.idf.idfapi.dto.RAdsrelSpecie;
import it.csi.idf.idfapi.util.DBUtil;

public interface AdsrelSpecieDAO {
	
	List<RAdsrelSpecie> search(); 
	void insertAllSpecieDomeCAM(List<RAdsrelSpecie> listOfRAdsrelSpecie) throws Exception;
	void updateAllSpecieDomeCAM(List<RAdsrelSpecie> listOfRAdsrelSpecie);
	int saveAdsrelSpecieDOMeCAM(RAdsrelSpecie radsrelSpecie);
	int deleteByCodiceAdsNotCAV(Long idgeoPtAds);
	int deleteByCodiceAdsCAV(Long idgeoPtAds);
	int saveAdsrelSpecieCAV(RAdsrelSpecie radsrelSpecie);
	int saveAdsrelSpecieForRelascopica(RAdsrelSpecie plainAdsrelSpecie, Long codiceADS);
	int saveAdsrelSpecieAlberi(PlainAdsrelSpecie plainAdsrelSpecie, String codiceADS,Integer idSpecie);
	List<RAdsrelSpecie> searchByIdgeoPtAds(Long idgeoPtAds);
	List<RAdsrelSpecie> searchAdsRelSpecByCodiceADSForCAV(String codiceADS);
	void setDataFineValidaForAdsSpecieDOMeCAM(Long idgeoPtAds);
	int updateAdsrelSpecieDOMeCAM(RAdsrelSpecie radsrelSpecie);
	void deleteAdsRelSpecie(Long idgeoPtAds);
}
