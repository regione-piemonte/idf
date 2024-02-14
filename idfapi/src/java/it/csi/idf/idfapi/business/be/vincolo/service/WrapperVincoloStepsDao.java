/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.exceptions.ServiceException;
import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.business.be.vincolo.pojo.Superfici;
import it.csi.idf.idfapi.business.be.vincolo.pojo.VincoloStep1DTO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.VincoloStep2DTO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.VincoloStep3DTO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.VincoloStep4DTO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.VincoloStep5DTO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.VincoloStep6DTO;
import it.csi.idf.idfapi.dto.BOSearchResult;
import it.csi.idf.idfapi.dto.BoOwnerIstanze;
import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.dto.DocumentoAllegato;
import it.csi.idf.idfapi.dto.FatDocumentoAllegato;
import it.csi.idf.idfapi.dto.GeneratedFileTrasfParameters;
import it.csi.idf.idfapi.dto.InvioIstanzaDTO;
import it.csi.idf.idfapi.dto.IstanzaTaglio;
import it.csi.idf.idfapi.dto.PlainParticelleCatastali;
import it.csi.idf.idfapi.dto.PlainSezioneId;
import it.csi.idf.idfapi.dto.ProfessionistaElencoAllegati;
import it.csi.idf.idfapi.dto.StepNumber;
import it.csi.idf.idfapi.dto.TSoggetto;
import it.csi.idf.idfapi.util.PaginatedList;

@Transactional(rollbackFor = Exception.class)
public interface WrapperVincoloStepsDao {

	void associareDocumenti(Integer idIntervento, ProfessionistaElencoAllegati body, ConfigUtente loggedConfig)
			throws RecordNotFoundException, RecordNotUniqueException;

	PaginatedList<BOSearchResult> backOfficeSearch(Map<String, String> queryParams, ConfigUtente confUtente)
			throws ParseException, RecordNotUniqueException, ValidationException;

	void deleteIntervento(Integer idIntervento);

	public boolean deleteStepById(Integer idIntervento, Integer stepNum);

	public boolean exists(VincoloStep1DTO body);

	public boolean exists(VincoloStep2DTO body);

	List<VincoloStep1DTO> findAll();;

	GeneratedFileTrasfParameters getCeoIstanza(Integer idIntervento);

	InvioIstanzaDTO getDataInvioIstanza(Integer idIntervento);

	StepNumber getNumberOfNextStep(Integer idIntervento);

	public VincoloStep1DTO getStep1(Integer idIntervento) throws RecordNotFoundException, RecordNotUniqueException;
	
	public VincoloStep2DTO getStep2(Integer idIntervento) throws RecordNotUniqueException, ValidationException;
	
	public Superfici getSuperfici(Integer idIntervento) throws RecordNotUniqueException, ValidationException;
	
	BigDecimal getCauzione(Integer idIntervento) throws RecordNotUniqueException, ValidationException;

	public VincoloStep3DTO getStep3(int idIntervento) throws RecordNotUniqueException, ValidationException;

	public VincoloStep4DTO getStep4(int idIntervento) throws RecordNotUniqueException, ValidationException;
	
	public VincoloStep5DTO getStep5(int idIntervento) throws RecordNotUniqueException, ValidationException;
	
	public VincoloStep6DTO getStep6(int idIntervento) throws RecordNotUniqueException, ValidationException;

	List<FatDocumentoAllegato> getTuttiElencaForIntervento(Integer idIntervento, boolean isGestore);
	
	List<FatDocumentoAllegato> getDocsGestoreByIntervento(Integer idIntervento);

	BoOwnerIstanze getUtenteForIstanza(Integer idIntervento);

	void insertParticlesInPropcatastoIntervento(Integer idIntervento, PlainParticelleCatastali particella, ConfigUtente loggedConfig);

	void invioIstanza(Integer idIntervento, ProfessionistaElencoAllegati body, TSoggetto tSoggetto)
			throws RecordNotFoundException, RecordNotUniqueException;

	void recalculateParticelleIntervento(Integer idIntervento, String cfUtente, ConfigUtente configUtente) throws ServiceException;

	PlainSezioneId saveStep1(VincoloStep1DTO body, ConfigUtente loggedConfig)
			throws RecordNotUniqueException, ValidationException;

	VincoloStep2DTO saveStep2(VincoloStep2DTO body, ConfigUtente loggedConfig, Integer idIntervento)
			throws RecordNotUniqueException, ValidationException;
	
	VincoloStep3DTO saveStep3(int idIntervento, ConfigUtente loggedConfig, VincoloStep3DTO body)
			throws RecordNotFoundException, RecordNotUniqueException, ValidationException;
	
	VincoloStep4DTO saveStep4(int idIntervento, ConfigUtente loggedConfig, VincoloStep4DTO body)
			throws RecordNotFoundException, RecordNotUniqueException, ValidationException;
	
	VincoloStep5DTO saveStep5(int idIntervento, ConfigUtente loggedConfig, VincoloStep5DTO body)
			throws RecordNotFoundException, RecordNotUniqueException, ValidationException;
	
	VincoloStep6DTO saveStep6(int idIntervento, ConfigUtente loggedConfig, VincoloStep6DTO body)
			throws RecordNotFoundException, RecordNotUniqueException, ValidationException;
	
	void updateIstanzaRifiutata(Integer idIntervento, TSoggetto soggetoFromUser)
			throws RecordNotFoundException, RecordNotUniqueException;
	
	void updateTitolaritaIntervento(Integer idIntervento, Integer idConfUtente);
	
	void updateIstanzaVerificata(Integer idIntervento, TSoggetto soggetto)
			throws RecordNotFoundException, RecordNotUniqueException;
	
	public boolean updateStep1(VincoloStep1DTO vincoloDto, ConfigUtente loggedConfig, int idIntervento)
			throws ValidationException, RecordNotUniqueException;

	public BigDecimal getCoefficienteCalcoloSupNonBoscata()
			throws RecordNotUniqueException, ValidationException;

	public BigDecimal getValoreMarcaBollo() throws RecordNotUniqueException, ValidationException;
	
	public BigDecimal getCauzione() throws RecordNotUniqueException, ValidationException;


	public PlainSezioneId creaVarianteProroga(Integer idIntervento, boolean isVariante, ConfigUtente loggedConfig) throws Exception;

	void duplicaDocumento(DocumentoAllegato documentoAllegato, Integer idInterventoNew,  ConfigUtente loggedConfig) 
			throws Exception;
	
	List<IstanzaTaglio> saveOrDeleteIstanzaTaglio(IstanzaTaglio istanzaTaglio, int fkConfigUtente, int fkIntervento);

}
