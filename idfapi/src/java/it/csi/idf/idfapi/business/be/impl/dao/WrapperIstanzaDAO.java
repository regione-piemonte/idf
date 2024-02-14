/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import it.csi.idf.idfapi.dto.*;
import org.springframework.transaction.annotation.Transactional;

import it.csi.idf.idfapi.business.be.exceptions.DuplicateRecordException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.exceptions.ServiceException;
import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.business.be.vincolo.pojo.AutorizzaVincolo;
import it.csi.idf.idfapi.util.PaginatedList;

@Transactional(rollbackFor=Exception.class)
public interface WrapperIstanzaDAO {

	StepNumber getNumberOfNextStep(Integer idIntervento);

	PlainPrimaSezione getPrimaSezione(int idIntervento) throws RecordNotFoundException, RecordNotUniqueException;

	PlainSezioneId savePrimaSezione(PlainPrimaSezione body, ConfigUtente loggedConfig)
			throws RecordNotUniqueException, ValidationException;

	void updatePrimaSezione(PlainPrimaSezione body,  ConfigUtente loggedConfig, int idIntervento)
			throws ValidationException, RecordNotUniqueException;

	PlainSecondoSezione getSecondoSezione(Integer idIntervento) throws RecordNotUniqueException, ValidationException;

	void saveSecondoSezione(PlainSecondoSezione body, ConfigUtente loggedConfig, int idIntervento) 
			throws RecordNotUniqueException, ValidationException, RecordNotFoundException;

	PlainTerzaSezione getTerzaSezione(int idIntervento) throws RecordNotUniqueException, ValidationException;

	TerzaSezioneSaveResult saveTerzaSezione(int idIntervento, ConfigUtente loggedConfig, PlainTerzaSezione body)
			throws RecordNotFoundException, RecordNotUniqueException, ValidationException;

	CompensationForesteDTO getQuartaSezione(Integer idIntervento) throws RecordNotUniqueException, ValidationException;

	void saveQuartaSezione(Integer idIntervento, CompensationForesteDTO compensationForesteDTO)
			throws ValidationException, RecordNotUniqueException;

	PlainQuintaSezione getQuintaSezione(Integer idIntervento)
			throws RecordNotUniqueException, DuplicateRecordException, RecordNotFoundException, ValidationException;

	void saveQuintaSezione(Integer idIntervento, PlainQuintaSezione quintaSezione)
			throws RecordNotUniqueException, ValidationException;

	PlainSestoSezione getSestoSezione(Integer idIntervento) throws RecordNotUniqueException;

	void saveSestoSezione(Integer idIntervento, ConfigUtente loggedConfig, PlainSestoSezione plainSestoSezione)
			throws RecordNotUniqueException, ValidationException;

	List<FatDocumentoAllegato> getTuttiElencaForIntervento(Integer idIntervento, boolean isGestore);
	
	List<FatDocumentoAllegato> getDocsGestoreByIntervento(Integer idIntervento);

	InvioIstanzaDTO getDataInvioIstanza(Integer idIntervento);

	void invioIstanza(Integer idIntervento, ProfessionistaElencoAllegati body, TSoggetto tSoggetto,ConfigUtente confUtente) 
			throws RecordNotFoundException, RecordNotUniqueException, Exception;
	
	void invioIstanzaVincolo(Integer idIntervento, ProfessionistaElencoAllegati body, TSoggetto tSoggetto,ConfigUtente confUtente) 
			throws RecordNotFoundException, RecordNotUniqueException, Exception;
	
	PlainSezioneId saveLocalizzaLotto(PfaLottoLocalizza body, ConfigUtente configUtente, Integer idGeoPlPfa) throws RecordNotUniqueException;

	void updateIstanzaVerificata(Integer idIntervento, TSoggetto soggetto,ConfigUtente confUtente)
			throws RecordNotFoundException, RecordNotUniqueException;

	void updateIstanzaRifiutata(Integer idIntervento, String motivazioneRifiuto, TSoggetto soggetoFromUser, ConfigUtente confUtente) throws RecordNotFoundException, RecordNotUniqueException;

	void updateIstanzaVincoloAutorizzata(AutorizzaVincolo body, ConfigUtente confUtente);
	
	void updateDataFineIntervento(AutorizzaVincolo body);
	
	BoOwnerIstanze getUtenteForIstanza(Integer idIntervento);

	GeneratedFileTrasfParameters getCeoIstanza(Integer idIntervento);
	
	GeneratedFileVincoloParameters getCeoIstanzaVincolo(Integer idIntervento);
	
	void associareDocumenti(Integer idIntervento, ProfessionistaElencoAllegati body, ConfigUtente loggedConfig) throws RecordNotFoundException, RecordNotUniqueException;

	PaginatedList<BOSearchResult> backOfficeSearch(Map<String, String> queryParams, ConfigUtente confUtente)
			throws ParseException, RecordNotUniqueException, ValidationException;
	
	PaginatedList<PlainSoggettoIstanzaVincolo> backOfficeVincoloSearch(Map<String, String> queryParams, ConfigUtente confUtente)
			throws ParseException, RecordNotUniqueException, ValidationException;
	
	List<RicadenzaInformazioni> retrieveRicadenzaAreeProtette(Integer idIntervento);
	
	List<RicadenzaInformazioni> retrieveRicadenzaNatura2000Sic(Integer idIntervento);
	
	List<RicadenzaInformazioni> retrieveRicadenzaNatura2000Zps(Integer idIntervento);
	
	List<RicadenzaInformazioni> retrieveRicadenzaPopolamentiDaSeme(Integer idIntervento);
	
	List<RicadenzaInformazioni> retrieveRicadenzaCategorieForestali(Integer idIntervento);
	
	List<PlainParticelleCatastali> mapPropCatastosToParticelleCatastali(List<PropCatasto> propCatastos)
			throws RecordNotUniqueException;

	void recalculateParticelleIntervento(Integer idIntervento, String cfUtente, ConfigUtente configUtente)throws ServiceException;
	
	void deleteIntervento(Integer idIntervento);
	
	IstanzaTaglio getIstanzaTaglioByNumIstanza(Integer numIstanza) throws ServiceException;
	
	List<IstanzaTaglio> getIstanzeTaglioByIdIntervento(int idIntervento);
	
	IstanzaForest getIstanzaInviata(Integer idIntervento);
	
	List<IstanzaTaglio> mapToIstanziTaglioList(List<IstanzaCompensazione> istanzas);

	PaginatedList<PlainSoggettoIstanzaTagli> backOfficeTagliSearch(Map<String, String> queryParams, ConfigUtente confUtente)
			throws ParseException, RecordNotUniqueException, ValidationException;
}
