/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.business.be.impl.dao.AdsrelSpecieDAO;
import it.csi.idf.idfapi.business.be.impl.dao.CombustibileDAO;
import it.csi.idf.idfapi.business.be.impl.dao.GeoPtAreaDiSaggioDAO;
import it.csi.idf.idfapi.business.be.impl.dao.RAdsCombustibileDAO;
import it.csi.idf.idfapi.business.be.impl.dao.RelascopicaSempliceDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SkOkAdsDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SuperficieNotaDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperAdsDAO;
import it.csi.idf.idfapi.dto.AdsDatiStazionaliOne;
import it.csi.idf.idfapi.dto.AdsDatiStazionaliTwo;
import it.csi.idf.idfapi.dto.AlberiCampioneDominanteDTO;
import it.csi.idf.idfapi.dto.AreaDISaggioDataStazionaliTwo;
import it.csi.idf.idfapi.dto.AreaDiSaggio;
import it.csi.idf.idfapi.dto.AreaDiSaggioDTO;
import it.csi.idf.idfapi.dto.AreaDiSaggioDatiGeneraliDTO;
import it.csi.idf.idfapi.dto.Combustibile;
import it.csi.idf.idfapi.dto.GeoPtAreaDiSaggio;
import it.csi.idf.idfapi.dto.RAdsrelSpecie;
import it.csi.idf.idfapi.dto.RelascopicaSempliceDTO;
import it.csi.idf.idfapi.dto.StepNumber;
import it.csi.idf.idfapi.dto.Superficie;
import it.csi.idf.idfapi.dto.TSoggetto;
import it.csi.idf.idfapi.mapper.AlberiCampioneDominanteDTOMapper;
import it.csi.idf.idfapi.mapper.AreaDiSaggioDTOMapper;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.GenericDAO;
import it.csi.idf.idfapi.util.PaginatedList;
import it.csi.idf.idfapi.util.TipoAdsEnum;
import it.csi.idf.idfapi.validation.AllStepsValidator;
import it.csi.idf.idfapi.validation.AreaDiSaggioAlberiCamDomValidator;
import it.csi.idf.idfapi.validation.AreaDiSaggioAlberiCavValidator;
import it.csi.idf.idfapi.validation.AreaDiSaggioDatiStazionaliOneValidator;
import it.csi.idf.idfapi.validation.AreaDiSaggioDatiStazionaliTwoValidator;
import it.csi.idf.idfapi.validation.StepValidationErrors;
import it.csi.idf.idfapi.validation.StepsValidationException;

@Component
public class WrapperAdsDAOImpl extends GenericDAO implements WrapperAdsDAO {
	
	private static final Logger logger = Logger.getLogger(WrapperAdsDAOImpl.class);
	
	@Autowired
	private GeoPtAreaDiSaggioDAO geoPtAreaDiSaggioDAO;

	@Autowired
	private RAdsCombustibileDAO combustibile;

	@Autowired
	private SuperficieNotaDAO superficieNotaDAO;
	
	@Autowired
	private CombustibileDAO combustibileDAO;

	@Autowired
	private SkOkAdsDAO skOkAdsDAO;

	@Autowired
	private AdsrelSpecieDAO adsrelSpecieDAO;
	
	@Autowired
	AdsrelSpecieDAO adsrelSpecieDao;
	
	@Autowired
	private RelascopicaSempliceDAO relascopicaSempliceDAO;

	@Override
	public PaginatedList<AreaDiSaggio> search(StringBuilder s, int page, int limit, List<Object> parameters) {
		
		return geoPtAreaDiSaggioDAO.search(s, page, limit, parameters);
	}
	
	public List<AreaDiSaggio> getSearchResultsForExcel(StringBuilder s, List<Object>parameters){
		return DBUtil.jdbcTemplate.query(s.toString(), new AreaDiSaggioDTOMapper(), parameters.toArray());
	}
	
	public List<AlberiCampioneDominanteDTO> findAlberiForExcel(String idList){
		StringBuilder sql= new StringBuilder("SELECT ads.codice_ads, adsrel.idgeo_pt_ads, adsrel.id_specie, spec.latino, adsrel.qualita, adsrel.diametro, adsrel.altezza, adsrel.incremento, adsrel.eta, adsrel.note, adsrel.cod_tipo_campione, spec.codice, spec.cod_gruppo, adsrel.flg_pollone_seme ")				
				.append(" FROM idf_r_adsrel_specie adsrel ")
				.append(" INNER JOIN idf_geo_pt_area_di_saggio ads on ads.idgeo_pt_ads = adsrel.idgeo_pt_ads")
				.append(" INNER JOIN idf_t_specie spec ON spec.id_specie = adsrel.id_specie ")
				.append(" WHERE adsrel.idgeo_pt_ads in ( " + idList + " )");
		
		return DBUtil.jdbcTemplate.query(sql.toString(), new AlberiCampioneDominanteDTOMapper());		
	}

	@Override
	public List<AlberiCampioneDominanteDTO> findAlberiCampioneDominante(StringBuilder s, List<Object> parameters) {
		//
		return geoPtAreaDiSaggioDAO.findAlberiCampioneDominante(s,parameters);
	}

	@Override
	public List<RelascopicaSempliceDTO> findRelascopica(StringBuilder s, List<Object> parameters) {
		return geoPtAreaDiSaggioDAO.findRelascopica(s, parameters);
	}

	@Override
	public List<RelascopicaSempliceDTO> findRelascopicaCompleta(StringBuilder s, List<Object> parameters) {
		return geoPtAreaDiSaggioDAO.findRelascopicaCompleta(s, parameters);
	}

	@Override
	public List<AreaDiSaggio> findAreaDiSaggioByCodiceAdsList(StringBuilder s) {
		return geoPtAreaDiSaggioDAO.findAreaDiSaggioByCodiceAdsList(s);
	}

	@Override
	public AreaDiSaggio findAreaDiSaggioByCodiceAds(StringBuilder s, List<Object> parameters)
			throws RecordNotFoundException {
		return geoPtAreaDiSaggioDAO.findAreaDiSaggioByCodiceAds(s, parameters);
	}
	
	@Override
	public boolean isCodiceAdsAlreadyUsed(String codiceAds, Long idgeoPtAds) {
		return geoPtAreaDiSaggioDAO.isCodiceAdsAlreadyUsed(codiceAds, idgeoPtAds);
	}
	
	@Override
	public AreaDiSaggio findDatiStazionali1(StringBuilder s, List<Object> parameters)
			throws RecordNotFoundException {
		return geoPtAreaDiSaggioDAO.findDatiStazionali1(s, parameters);
	}
	
	@Override
	public AreaDISaggioDataStazionaliTwo findDatiStazionali2(Long idgeoPtAds) throws RecordNotFoundException {		
		return geoPtAreaDiSaggioDAO.findDatiStazionali2(idgeoPtAds);
	}
	
	@Override
	public int createAreaDiSaggio(AreaDiSaggioDTO areaDiSaggio, TSoggetto soggetto) throws RecordNotFoundException {
		return geoPtAreaDiSaggioDAO.createAreaDiSaggio(areaDiSaggio, soggetto);
	}
	
	@Override
	public int createEmptyAreaDiSaggio(AreaDiSaggioDTO areaDiSaggio,TSoggetto tSoggetto) {
		return geoPtAreaDiSaggioDAO.createEmptyAreaDiSaggio(areaDiSaggio, tSoggetto);
	}
	
	public int updateAreaDiSaggio(AreaDiSaggioDTO areaDiSaggio, TSoggetto soggetto) throws RecordNotFoundException {
		return geoPtAreaDiSaggioDAO.updateAreaDiSaggio(areaDiSaggio, soggetto);
	}

	@Override
	public StepNumber getNumberOfLastCompletedStep(Long idgeoPtAds)  {
		return new StepNumber(skOkAdsDAO.getLastStepDone(idgeoPtAds));
	}

	@Transactional
	@Override
	public List<StepValidationErrors> insertSuperficieDati1(AdsDatiStazionaliOne adsDatiStazionaliOne) {
		
		//areaDiSaggio.getTipologia().equalsIgnoreCase(TipologiaEnum.A_SUPERFICIE_NOTA.getValue()) && 		
		if (adsDatiStazionaliOne.getCodiceADS() != null) {
			
			superficieNotaDAO.saveSuperficie(adsDatiStazionaliOne);			
			combustibile.saveCombustibile(adsDatiStazionaliOne);
			geoPtAreaDiSaggioDAO.updateAreaDiSaggioD1(adsDatiStazionaliOne);
			if (!skOkAdsDAO.skOdsExists(adsDatiStazionaliOne.getIdgeoPtAds())) {
				skOkAdsDAO.insertFlgSez1(adsDatiStazionaliOne.getIdgeoPtAds(), 1);
			} else {
				skOkAdsDAO.updateStepFinished(adsDatiStazionaliOne.getIdgeoPtAds(), 1);
			}
		}
		
		GeoPtAreaDiSaggio areaDiSaggio = geoPtAreaDiSaggioDAO.findAdsStepsDataByIdgeoPtAds(adsDatiStazionaliOne.getIdgeoPtAds());
		Superficie superficieNota = superficieNotaDAO.getSuperficieNotaByIdGeoPtAds(adsDatiStazionaliOne.getIdgeoPtAds());		
		Combustibile combustibile = combustibileDAO.getSuperficieNotaByIdGeoPtAds(adsDatiStazionaliOne.getIdgeoPtAds());		
		AllStepsValidator allStepsValidator = new AllStepsValidator();		
		allStepsValidator.addStepValidator(new AreaDiSaggioDatiStazionaliOneValidator(areaDiSaggio, combustibile, superficieNota));
		allStepsValidator.validateAllSteps();
		return allStepsValidator.getStepValidatorErrors();
	}

	@Override
	public List<StepValidationErrors> insertSuperficieDati2(AdsDatiStazionaliTwo adsDatiStazionaliTwo) {
		geoPtAreaDiSaggioDAO.updateAreaDiSaggioD2(adsDatiStazionaliTwo);
		superficieNotaDAO.updateSuperficieDatiStazionaliTwo(adsDatiStazionaliTwo);
		skOkAdsDAO.updateStepFinished(adsDatiStazionaliTwo.getIdgeoPtAds(), 2);
		
		GeoPtAreaDiSaggio areaDiSaggio = geoPtAreaDiSaggioDAO.findAdsStepsDataByIdgeoPtAds(adsDatiStazionaliTwo.getIdgeoPtAds());
		Superficie superficieNota = superficieNotaDAO.getSuperficieNotaByIdGeoPtAds(adsDatiStazionaliTwo.getIdgeoPtAds());		
		AllStepsValidator allStepsValidator = new AllStepsValidator();		
		allStepsValidator.addStepValidator(new AreaDiSaggioDatiStazionaliTwoValidator(areaDiSaggio, superficieNota));
		allStepsValidator.validateAllSteps();
		return allStepsValidator.getStepValidatorErrors();
	}

	@Override
	public void saveAdsrelSpecieDOMeCAM(RAdsrelSpecie radsrelSpecie) {
		try {
		adsrelSpecieDAO.saveAdsrelSpecieDOMeCAM(radsrelSpecie);
		skOkAdsDAO.updateStepFinished(radsrelSpecie.getIdgeoPtAds(), 3);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	public List<StepValidationErrors> insertAllSpecieDomeCAM(List<RAdsrelSpecie> listOfRAdsrelSpecie) {
		RAdsrelSpecie radsrelSpecie = null;
		if (listOfRAdsrelSpecie.size()> 0) {
			radsrelSpecie = listOfRAdsrelSpecie.get(0);
			adsrelSpecieDAO.setDataFineValidaForAdsSpecieDOMeCAM(radsrelSpecie.getIdgeoPtAds());
		}
		for (Iterator<RAdsrelSpecie> iterator = listOfRAdsrelSpecie.iterator(); iterator.hasNext();) {
			RAdsrelSpecie rAdsrelSpecie = (RAdsrelSpecie) iterator.next();
			if(rAdsrelSpecie.getIdSpecie() != null) {
				adsrelSpecieDAO.saveAdsrelSpecieDOMeCAM(rAdsrelSpecie);
			}
		}
//		if ( radsrelSpecie != null) {
			skOkAdsDAO.updateStepFinished(radsrelSpecie.getIdgeoPtAds(), 3);
//		}
		
		AllStepsValidator allStepsValidator = new AllStepsValidator();		
		allStepsValidator.addStepValidator(new AreaDiSaggioAlberiCamDomValidator(listOfRAdsrelSpecie));
		allStepsValidator.validateAllSteps();
		return allStepsValidator.getStepValidatorErrors();

	}
	
	@Transactional
	public List<StepValidationErrors> updateAllSpecieDomeCAM(List<RAdsrelSpecie> listOfRAdsrelSpecie) {

		RAdsrelSpecie radsrelSpecie = null;
		if (listOfRAdsrelSpecie.size()> 0) {
			radsrelSpecie = listOfRAdsrelSpecie.get(0);
			adsrelSpecieDAO.deleteByCodiceAdsNotCAV(radsrelSpecie.getIdgeoPtAds());
		}
		List<RAdsrelSpecie> newListOfRAdsrelSpecie = new ArrayList<RAdsrelSpecie>();
		for (Iterator<RAdsrelSpecie> iterator = listOfRAdsrelSpecie.iterator(); iterator.hasNext();) {
			RAdsrelSpecie rAdsrelSpecie = (RAdsrelSpecie) iterator.next();
			if(rAdsrelSpecie.getIdSpecie() != null) {
				newListOfRAdsrelSpecie.add(rAdsrelSpecie);
				adsrelSpecieDAO.saveAdsrelSpecieDOMeCAM(rAdsrelSpecie);
			}
		}
//		if ( radsrelSpecie != null) {
			skOkAdsDAO.updateStepFinished(radsrelSpecie.getIdgeoPtAds(), 3); 
//		}
		
		listOfRAdsrelSpecie = newListOfRAdsrelSpecie;
		AllStepsValidator allStepsValidator = new AllStepsValidator();		
		allStepsValidator.addStepValidator(new AreaDiSaggioAlberiCamDomValidator(listOfRAdsrelSpecie));
		allStepsValidator.validateAllSteps();
		return allStepsValidator.getStepValidatorErrors();
		
	}
	
	
	
	@Transactional
	@Override
	public void saveAdsrelSpecieCAV(List<RAdsrelSpecie> listOfRAdsrelSpecie) {
						
		
		RAdsrelSpecie radsrelSpecie = null;
		if (listOfRAdsrelSpecie.size()> 0) {
			radsrelSpecie = listOfRAdsrelSpecie.get(0);
			adsrelSpecieDAO.deleteByCodiceAdsCAV(radsrelSpecie.getIdgeoPtAds());
			
			for (Iterator<RAdsrelSpecie> iterator = listOfRAdsrelSpecie.iterator(); iterator.hasNext();) {
				RAdsrelSpecie rAdsrelSpecieForSave = (RAdsrelSpecie) iterator.next();
				adsrelSpecieDAO.saveAdsrelSpecieCAV(rAdsrelSpecieForSave);
			}
			
			skOkAdsDAO.updateStepFinished(radsrelSpecie.getIdgeoPtAds(), 4);
		}

		
	}
	
	@Transactional
	@Override
	public void consolidaAdsrelSpecieCAV(List<RAdsrelSpecie> listOfRAdsrelSpecie) {
		
		if (listOfRAdsrelSpecie!=null && listOfRAdsrelSpecie.isEmpty()) return;						
		saveAdsrelSpecieCAV(listOfRAdsrelSpecie);
		consolidaAds(listOfRAdsrelSpecie.get(0).getIdgeoPtAds());
	}

	@Override
	public List<AreaDiSaggio> findAreaDiSaggioByCodiceAdsListExcel(StringBuilder s) {
		return geoPtAreaDiSaggioDAO.findAreaDiSaggioByCodiceAdsListExcel(s);
	}

	@Override
	public PaginatedList<RelascopicaSempliceDTO> findRelascopicaCompletaSort(
			StringBuilder createWhereClauseForSearchCompleta, int page, int limit, List<Object> parameters) {
		return geoPtAreaDiSaggioDAO.findRelascopicaCompletaSort(createWhereClauseForSearchCompleta, page, limit,
				parameters);
	}

	@Override
	public AreaDiSaggio findADSByIdgeoPtAds(StringBuilder s, List<Object> parameters)
			throws RecordNotFoundException {
		return geoPtAreaDiSaggioDAO.findADSByIdgeoPtAds(s, parameters);
	}

	@Override
	public PaginatedList<AlberiCampioneDominanteDTO> findAlberiCAV(StringBuilder s, int page, int limit,
			List<Object> parameters) {
	
		return geoPtAreaDiSaggioDAO.findAlberiCAV(s, page, limit,
				parameters);
	}
	
	
	public void consolidaAds(Long idgeoPtAds) throws ValidationException {
		validateForConsolidaSchedaState(idgeoPtAds);
		geoPtAreaDiSaggioDAO.consolidaAreaDiSaggio(idgeoPtAds);
	}

	public AreaDiSaggioDatiGeneraliDTO findADSByIdgeoPtAds(Long idgeoPtAds) {
		return geoPtAreaDiSaggioDAO.findADSByIdgeoPtAds(idgeoPtAds);
	}
	
	@Override
	public void validateForConsolidaSchedaState(Long idgeoPtAds) throws ValidationException, RecordNotFoundException {
		GeoPtAreaDiSaggio areaDiSaggio = geoPtAreaDiSaggioDAO.findAdsStepsDataByIdgeoPtAds(idgeoPtAds);
		if (areaDiSaggio.getFkTipoAds() == TipoAdsEnum.SUPERFICIE_NOTA.getIdTipoAds() ) {
			validateSuperficieNotaForConsolidaScheda(areaDiSaggio);
		}
	}
	
	private void validateSuperficieNotaForConsolidaScheda(GeoPtAreaDiSaggio areaDiSaggio) throws ValidationException, RecordNotFoundException {
		
		
		if(superficieNotaDAO.superficiaNotaExists(areaDiSaggio.getIdgeoPtAds())) {
			
			int lastStepDone = skOkAdsDAO.getLastStepDone(areaDiSaggio.getIdgeoPtAds());
			Superficie superficieNota = superficieNotaDAO.getSuperficieNotaByIdGeoPtAds(areaDiSaggio.getIdgeoPtAds());
			Combustibile combustibile = combustibileDAO.getSuperficieNotaByIdGeoPtAds(areaDiSaggio.getIdgeoPtAds()); 
			List<RAdsrelSpecie> listOdAlberiCamDov = adsrelSpecieDao.searchByIdgeoPtAds(areaDiSaggio.getIdgeoPtAds());
//			if(listOdAlberiCamDov == null || listOdAlberiCamDov.isEmpty()) {
//	
//				if(lastStepDone > 2) {
//					for(int i=0;i<4;i++) {
//						listOdAlberiCamDov.add(new RAdsrelSpecie());
//					}
//				}
//			}
			
			AllStepsValidator allStepsValidator = new AllStepsValidator();
			
			allStepsValidator.addStepValidator(new AreaDiSaggioDatiStazionaliOneValidator(areaDiSaggio, combustibile, superficieNota));
			allStepsValidator.addStepValidator(new AreaDiSaggioDatiStazionaliTwoValidator(areaDiSaggio, superficieNota));
			allStepsValidator.addStepValidator(new AreaDiSaggioAlberiCamDomValidator(listOdAlberiCamDov));
			allStepsValidator.addStepValidator(new AreaDiSaggioAlberiCavValidator());
			
			if (!allStepsValidator.validateAllSteps()) {
				throw new StepsValidationException(allStepsValidator.getStepValidatorErrors());
			}
		}
	}
	
	@Transactional
	public void deleteAreaDiSaggio(Long idgeoPtAds) {
		
		adsrelSpecieDao.deleteAdsRelSpecie(idgeoPtAds);
		skOkAdsDAO.deleteSkOkAds(idgeoPtAds);		
		combustibile.deleteCompustibile(idgeoPtAds);
		superficieNotaDAO.deleteSuperficieNota(idgeoPtAds);
		geoPtAreaDiSaggioDAO.deleteAreaDiSaggio(idgeoPtAds);
		
	}

	
	
}
