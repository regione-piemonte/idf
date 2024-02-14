/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.dto.*;
import it.csi.idf.idfapi.util.TipoTitolaritaEnum;
import it.csi.idf.idfapi.util.service.integration.aaep.ListaAttEconProd;

public interface SoggettoDAO {

	TSoggetto findSoggettoById(Integer idSoggetto) throws RecordNotFoundException;
	TSoggetto findSoggettoByIdConfigUtente(Integer idConfigUtente) throws RecordNotFoundException;

	TSoggetto findSoggettoByCodiceFiscale(String codiceFiscale) throws RecordNotUniqueException;
	
	TSoggetto findAziendaByCodiceFiscale(String codiceFiscale) throws RecordNotUniqueException;
	TSoggetto findAziendaByCodiceFiscaleAndIsPubblic(String codiceFiscale, Boolean isPubblic) throws RecordNotUniqueException;


	List<TSoggetto> findByPartialCodiceFiscale(String codiceFiscale);

	int createSoggetto(TSoggetto soggetto);

	int createSoggettoFisica(TSoggetto soggetto);

	int updateSoggetto(TSoggetto soggetto);

	void updateFkConfigUtente(int idSoggetto, int configUtenteId);

	void updateSoggettoProfess(TSoggetto soggettoProfess);

	List<SoggettoResource> getPersFisicaForBOSearch(Integer idTipoIstanza);

	List<SoggettoResource> getPersGiuridicaForBOSearch(Integer idTipoIstanza);

	List<SoggettoResource> getProfessForBOSearch(Integer idTipoIstanza);
	
	List<TSoggetto> getProfessionistiList();

	FatSoggetto findFatSoggettoByCodiceFiscale(String codiceFiscale) throws RecordNotUniqueException;

	List<TSoggetto> getPersonaGiurdicaByOwnerCodiceFiscale(String codiceFiscale);
	List<TSoggetto> getPersonaGiurdicaByOwnerCodiceFiscaleAndIsPubblic(String codiceFiscale, Boolean isPubblic);

	TipoTitolaritaEnum getTitolaritaforMe();

	TSoggetto getSoggettoPfByPg(int soggPgId);

	TSoggetto getSoggettoPgByPf(int soggPfId);

	TSoggetto getLastInterventoByConfigUtente(ConfigUtente configUtente);
	
    List<FatSoggetto> findAllFatProfess();
    
    SoggettoResource getSoggettoByIdInterventoAndTipoSoggetto(Integer idIntervento, Integer idTipoSoggetto);
    
    List<SoggettoResource> getAllTecnicoForestale();

	List<TSoggetto> mapAziendaToSoggetto(ListaAttEconProd[] aziendaList);

	List<PersonaFisGiu> searchPgByCForDenom(String searchParam, Boolean entePubblico);

	void insertIntoSoggettoTipoSoggetto(Integer idSoggetto, Integer idTipoSoggetto);
	
	TSoggetto findSoggettoCompetenzaIstanza(Integer idIstanza) throws RecordNotFoundException;
	
	List<String> getListCodicifiscaliCittadini();
	
	String getRichiedentiInterventoPfa(Integer idIntervento);


	List<SoggettoResource> getGestoriList();

	List<PersonaFisGiu> searchPfByCForName(String search);

	PersonaFisGiu getTecnicoForestaleByIdIntervento(Integer idIntervento);

	List<SoggettoResource> getUtilizzatorePFForBOSearch(String search);

	List<SoggettoResource> getUtilizzatorePGForBOSearch(String search);

	List<SoggettoResource> getTecniciForestaliForTagli(String search);

	List<SoggettoResource> getSportelloList();

	List<TSoggetto> findSportelliByIdConfigUtente(Integer idConfigUtente) throws RecordNotFoundException;
	TSoggetto findSportelloByIdConfigUtente(Integer idConfigUtente) throws RecordNotFoundException;

	List<SoggettoResource> getPersFisicaForBOTagliSearch(String search);

	List<SoggettoResource> getPersGiuridicaForBOTagliSearch(String search);

	List<String> findEmailFromAreaProtettaByIdIntervento(Integer idIntervento);
	List<String> findEmailFromAreaProtetta2000ByIdIntervento(Integer idIntervento);
}
