/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.dto.AdsDatiStazionaliOne;
import it.csi.idf.idfapi.dto.AdsDatiStazionaliTwo;
import it.csi.idf.idfapi.dto.AlberiCampioneDominanteDTO;
import it.csi.idf.idfapi.dto.AreaDISaggioDataStazionaliTwo;
import it.csi.idf.idfapi.dto.AreaDiSaggio;
import it.csi.idf.idfapi.dto.AreaDiSaggioDTO;
import it.csi.idf.idfapi.dto.AreaDiSaggioDatiGeneraliDTO;
import it.csi.idf.idfapi.dto.RAdsrelSpecie;
import it.csi.idf.idfapi.dto.RelascopicaSempliceDTO;
import it.csi.idf.idfapi.dto.StepNumber;
import it.csi.idf.idfapi.dto.TSoggetto;
import it.csi.idf.idfapi.util.PaginatedList;
import it.csi.idf.idfapi.validation.StepValidationErrors;

@Transactional(rollbackFor=Exception.class)
public interface WrapperAdsDAO {
	
	PaginatedList<AreaDiSaggio> search(StringBuilder s,int page, int limit, List<Object>parameters);
	List<AreaDiSaggio> getSearchResultsForExcel(StringBuilder s, List<Object>parameters);	
	List<AlberiCampioneDominanteDTO> findAlberiForExcel(String idList);
	List<AlberiCampioneDominanteDTO> findAlberiCampioneDominante(StringBuilder s,List<Object> parameters);
	PaginatedList<AlberiCampioneDominanteDTO> findAlberiCAV(StringBuilder s, int page, int limit,List<Object> parameters);
	List<RelascopicaSempliceDTO> findRelascopica(StringBuilder s, List<Object> parameters);
	List<RelascopicaSempliceDTO> findRelascopicaCompleta(StringBuilder s, List<Object> parameters);
	List<AreaDiSaggio> findAreaDiSaggioByCodiceAdsList(StringBuilder s);
	List<AreaDiSaggio> findAreaDiSaggioByCodiceAdsListExcel(StringBuilder s);
	boolean isCodiceAdsAlreadyUsed(String codiceAds, Long idgeoPtAds);
	AreaDiSaggio findAreaDiSaggioByCodiceAds(StringBuilder s,List<Object> parameters) throws RecordNotFoundException;
	AreaDiSaggio findDatiStazionali1(StringBuilder s,List<Object> parameters) throws RecordNotFoundException;
	AreaDISaggioDataStazionaliTwo findDatiStazionali2(Long idgeoPtAds) throws RecordNotFoundException;
	AreaDiSaggio findADSByIdgeoPtAds(StringBuilder s,List<Object> parameters) throws RecordNotFoundException;
	int createAreaDiSaggio(AreaDiSaggioDTO areaDiSaggio,TSoggetto tSoggetto) throws RecordNotFoundException;
	int createEmptyAreaDiSaggio(AreaDiSaggioDTO areaDiSaggio,TSoggetto tSoggetto);
	int updateAreaDiSaggio(AreaDiSaggioDTO areaDiSaggio, TSoggetto soggetto) throws RecordNotFoundException;	
	StepNumber getNumberOfLastCompletedStep(Long idgeoPtAds);

	List<StepValidationErrors> insertSuperficieDati1(AdsDatiStazionaliOne adsDatiStazionaliOne);
	List<StepValidationErrors> insertSuperficieDati2(AdsDatiStazionaliTwo adsDatiStazionaliTwo);
	void saveAdsrelSpecieDOMeCAM(RAdsrelSpecie radsrelSpecie);
	void saveAdsrelSpecieCAV(List<RAdsrelSpecie> listOfRAdsrelSpecie);
	void consolidaAdsrelSpecieCAV(List<RAdsrelSpecie> listOfRAdsrelSpecie);
	PaginatedList<RelascopicaSempliceDTO> findRelascopicaCompletaSort(StringBuilder createWhereClauseForSearchCompleta,
			int page, int limit, List<Object> parameters);
	
	List<StepValidationErrors> insertAllSpecieDomeCAM(List<RAdsrelSpecie> listOfRAdsrelSpecie);
	List<StepValidationErrors> updateAllSpecieDomeCAM(List<RAdsrelSpecie> listOfRAdsrelSpecie);
	
	void consolidaAds(Long idGeoPtAds);
	AreaDiSaggioDatiGeneraliDTO findADSByIdgeoPtAds(Long idgeoPtAds);
	void deleteAreaDiSaggio(Long idgeoPtAds);
	
	public void validateForConsolidaSchedaState(Long idgeoPtAds)throws ValidationException, RecordNotFoundException;
}
